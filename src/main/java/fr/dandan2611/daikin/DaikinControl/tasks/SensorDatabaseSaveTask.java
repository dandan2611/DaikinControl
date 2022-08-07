package fr.dandan2611.daikin.DaikinControl.tasks;

import fr.dandan2611.daikin.DaikinControl.database.entities.SensoredTemperature;
import fr.dandan2611.daikin.DaikinControl.device.*;
import fr.dandan2611.daikin.DaikinControl.option.ScheduleUtils;
import fr.dandan2611.daikin.DaikinControl.service.DaikinService;
import fr.dandan2611.daikin.DaikinControl.service.OptionsManager;
import fr.dandan2611.daikin.DaikinControl.service.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SensorDatabaseSaveTask {

    private static final Logger logger = LoggerFactory.getLogger(SensorDatabaseSaveTask.class);

    @Autowired
    private DaikinService daikinService;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private OptionsManager optionsManager;

    boolean onBySchedule = false;
    boolean onByTimeLimit = false;

    @Scheduled(fixedRate = 60000)
    public void saveData() {
        DaikinSensorInfo sensorInfo = daikinService.getSensorInfo();
        DaikinControlInfo controlInfo = daikinService.getControlInfos();

        sensorRepository.save(new SensoredTemperature(sensorInfo.getInnerTemperature(), sensorInfo.getInnerHumidity(), sensorInfo.getOuterTemperature()));

        double maxTemp = optionsManager.getMaxRoomTemperature();
        double minTemp = optionsManager.getMinRoomTemperature();

        String timeRemaining = "";

        boolean isInSchedule = false;
        for(ScheduleUtils.Schedule schedule : optionsManager.getSchedules()) {
            if(schedule.isInSchedule()) {
                isInSchedule = true;
                break;
            }
        }

        if(controlInfo.getPowerState().isOn()) {
            if(!onByTimeLimit && optionsManager.getTimeLimit() > 0)
                onByTimeLimit = true;
            if(!onBySchedule && isInSchedule)
                onBySchedule = true;
        }

        boolean cont = true;

        if(!optionsManager.isStarted() && controlInfo.getPowerState().isOn()) {
            turnOff();
            logger.info("Stopping system");
            cont = false;
        }

        if (optionsManager.isOnline() && optionsManager.isStarted() && cont) {
            int timeLimit = optionsManager.getTimeLimit();
            if(timeLimit > 0) {
                if(controlInfo.getPowerState().isOff()) {
                    daikinService.executeRequest(new DaikinRequest.DaikinRequestBuilder(PowerState.ON)
                            .setOperatingMode(optionsManager.getTimeLimitMode())
                            .setTargetTemperature(optionsManager.getPreferredRoomTemperature())
                            .build());
                    onByTimeLimit = true;
                    timeRemaining = String.valueOf(timeLimit);
                    logger.info("Time limit activated (" + timeLimit + ")");
                }
                optionsManager.setTimeLimit(timeLimit - 1);
            }
            else {
                if(onByTimeLimit) {
                    if(!isInSchedule) {
                        turnOff();
                        optionsManager.setStarted(false);
                    }
                    logger.info("Time limit reached.");
                }
            }

            if(isInSchedule) {
                if(controlInfo.getPowerState().isOff()) {
                    daikinService.executeRequest(new DaikinRequest.DaikinRequestBuilder(PowerState.ON)
                            .setOperatingMode(optionsManager.getScheduleMode())
                            .setTargetTemperature(optionsManager.getPreferredRoomTemperature())
                            .build());
                    onBySchedule = true;
                    logger.info("Starting from schedule");
                }
            }
            else
                if(onBySchedule) {
                    turnOff();
                    optionsManager.setStarted(false);
                    logger.info("Stopping from schedule");
                }

            if(optionsManager.isAutoFan() && (optionsManager.isAutoFanStartFan() || isInSchedule))
                if(controlInfo.getPowerState().isOff() || OperatingMode.FAN != controlInfo.getOperatingMode())
                    daikinService.executeRequest(new DaikinRequest.DaikinRequestBuilder(PowerState.ON)
                            .setOperatingMode(OperatingMode.FAN)
                            .setTargetTemperature(optionsManager.getPreferredRoomTemperature())
                            .build());

            if (sensorInfo.getInnerTemperature() > maxTemp) {
                daikinService.executeRequest(new DaikinRequest.DaikinRequestBuilder(PowerState.ON)
                        .setOperatingMode(OperatingMode.COLD)
                        .setTargetTemperature(optionsManager.getPreferredRoomTemperature())
                        .build());
                logger.info("Air conditioning : on (max temp reached)");
            }

            if(controlInfo.getPowerState().isOn()) {
                if (sensorInfo.getInnerTemperature() <= minTemp) {
                    if (optionsManager.isAutoFan()) {
                        if(!controlInfo.getOperatingMode().equals(OperatingMode.FAN)) {
                            // Autofan ON, changing mode to fans
                            daikinService.executeRequest(new DaikinRequest.DaikinRequestBuilder(PowerState.ON)
                                    .setOperatingMode(OperatingMode.FAN)
                                    .build());
                            logger.info("Air conditioning : off (min temp reached) [AUTOFAN MODE]");
                        }
                    } else {
                        // Autofan OFF, just stopping the system
                        if(!isInSchedule && optionsManager.getTimeLimit() > 0) {
                            turnOff();
                            logger.info("Air conditioning : off (min temp reached)");
                        }
                    }
                }
            }
        }

        logger.info("[" + (controlInfo.getPowerState().isOn() ? controlInfo.getOperatingMode().name() : "OFF") + "] " + sensorInfo.getInnerTemperature() + "°C (" + minTemp + "-" + maxTemp + ") " +
                "| Outside " + sensorInfo.getOuterTemperature() + "°C" +
                (!timeRemaining.isBlank() ? " | Time limit remaining: " + timeRemaining : ""));
    }

    private void turnOff() {
        daikinService.executeRequest(new DaikinRequest.DaikinRequestBuilder(PowerState.OFF).build());
    }

}
