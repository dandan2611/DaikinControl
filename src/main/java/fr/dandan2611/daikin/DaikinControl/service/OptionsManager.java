package fr.dandan2611.daikin.DaikinControl.service;

import fr.dandan2611.daikin.DaikinControl.database.entities.PeripheralOption;
import fr.dandan2611.daikin.DaikinControl.device.OperatingMode;
import fr.dandan2611.daikin.DaikinControl.option.PeripheralOptionKey;
import fr.dandan2611.daikin.DaikinControl.option.ScheduleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

@Service
public class OptionsManager {

    public static final Integer DEFAULT_ONLINE = 1;
    public static final Integer DEFAULT_STARTED = 0;
    public static final Integer DEFAULT_AUTOFAN = 1;
    public static final Integer DEFAULT_AUTOFAN_STARTFAN = 1;
    public static final Double DEFAULT_MAX_TEMPERATURE = 24d;
    public static final Double DEFAULT_MIN_TEMPERATURE = 22d;
    public static final Double DEFAULT_PREFERRED_TEMPERATURE = 23d;
    public static final String DEFAULT_SCHEDULE = "";
    public static final OperatingMode DEFAULT_SCHEDULE_MODE = OperatingMode.COLD;
    public static final Integer DEFAULT_TIMELIMIT = 0;
    public static final OperatingMode DEFAULT_TIMELIMIT_MODE = OperatingMode.COLD;

    @Autowired
    private OptionsRepository optionsRepository;

    public void set(PeripheralOptionKey key, String value) {
        PeripheralOption option = optionsRepository.findByKey(key);
        option.setValue(value);
        option.setLastEdited(Date.from(Instant.now()));
        optionsRepository.save(option);
    }

    public boolean isOnline() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.ONLINE;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_ONLINE.toString(), Date.from(Instant.now())));
            return DEFAULT_ONLINE == 1;
        }
        return Integer.parseInt(option.getValue()) == 1;
    }

    public void setOnline(boolean online) {
        set(PeripheralOptionKey.ONLINE, String.valueOf(online ? 1 : 0));
    }

    public boolean isStarted() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.START;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_STARTED.toString(), Date.from(Instant.now())));
            return DEFAULT_STARTED == 1;
        }
        return Integer.parseInt(option.getValue()) == 1;
    }

    public void setStarted(boolean started) {
        set(PeripheralOptionKey.START, String.valueOf(started ? 1 : 0));
    }

    public boolean isAutoFan() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.AUTOFAN;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_AUTOFAN.toString(), Date.from(Instant.now())));
            return DEFAULT_AUTOFAN == 1;
        }
        return Integer.parseInt(option.getValue()) == 1;
    }

    public void setAutoFan(boolean online) {
        set(PeripheralOptionKey.AUTOFAN, String.valueOf(online ? 1 : 0));
    }

    public boolean isAutoFanStartFan() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.AUTOFAN_STARTFAN;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_AUTOFAN_STARTFAN.toString(), Date.from(Instant.now())));
            return DEFAULT_AUTOFAN_STARTFAN == 1;
        }
        return Integer.parseInt(option.getValue()) == 1;
    }

    public void setAutoFanStartFan(boolean online) {
        set(PeripheralOptionKey.AUTOFAN_STARTFAN, String.valueOf(online ? 1 : 0));
    }

    public Double getMaxRoomTemperature() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.MAX_TEMPERATURE;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_MAX_TEMPERATURE.toString(), Date.from(Instant.now())));
            return DEFAULT_MAX_TEMPERATURE;
        }
        return Double.parseDouble(option.getValue());
    }

    public void setMaxRoomTemperature(Double max) {
        set(PeripheralOptionKey.MAX_TEMPERATURE, max.toString());
    }

    public Double getMinRoomTemperature() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.MIN_TEMPERATURE;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_MIN_TEMPERATURE.toString(), Date.from(Instant.now())));
            return DEFAULT_MIN_TEMPERATURE;
        }
        return Double.parseDouble(option.getValue());
    }

    public void setMinRoomTemperature(Double min) {
        set(PeripheralOptionKey.MIN_TEMPERATURE, min.toString());
    }

    public Double getPreferredRoomTemperature() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.PREFERRED_TEMPERATURE;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_PREFERRED_TEMPERATURE.toString(), Date.from(Instant.now())));
            return DEFAULT_PREFERRED_TEMPERATURE;
        }
        return Double.parseDouble(option.getValue());
    }

    public void setPreferredRoomTemperature(Double preferred) {
        set(PeripheralOptionKey.PREFERRED_TEMPERATURE, preferred.toString());
    }

    public ArrayList<ScheduleUtils.Schedule> getSchedules() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.SCHEDULES;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_SCHEDULE, Date.from(Instant.now())));
            return ScheduleUtils.parseSchedules(DEFAULT_SCHEDULE);
        }
        return ScheduleUtils.parseSchedules(option.getValue());
    }

    public void setSchedules(String line) {
        set(PeripheralOptionKey.SCHEDULES, line);
    }

    public OperatingMode getScheduleMode() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.SCHEDULES_MODE;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_SCHEDULE_MODE.toString(), Date.from(Instant.now())));
            return DEFAULT_SCHEDULE_MODE;
        }
        return OperatingMode.valueOf(option.getValue());
    }

    public void setScheduleMode(OperatingMode mode) {
        set(PeripheralOptionKey.SCHEDULES_MODE, mode.name());
    }

    public Integer getTimeLimit() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.TIMELIMIT;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_TIMELIMIT.toString(), Date.from(Instant.now())));
            return DEFAULT_TIMELIMIT;
        }
        return Integer.parseInt(option.getValue());
    }

    public void setTimeLimit(Integer timeLimit) {
        set(PeripheralOptionKey.TIMELIMIT, timeLimit.toString());
    }

    public OperatingMode getTimeLimitMode() {
        PeripheralOptionKey optionKey = PeripheralOptionKey.TIMELIMIT_MODE;
        PeripheralOption option = optionsRepository.findByKey(optionKey);
        if(option == null) {
            optionsRepository.save(new PeripheralOption(optionKey.toString(), DEFAULT_TIMELIMIT_MODE.toString(), Date.from(Instant.now())));
            return DEFAULT_TIMELIMIT_MODE;
        }
        return OperatingMode.valueOf(option.getValue());
    }

    public void setDefaultTimelimitMode(OperatingMode mode) {
        set(PeripheralOptionKey.TIMELIMIT_MODE, mode.name());
    }

}
