package fr.dandan2611.daikin.DaikinControl.rest.controllers;

import fr.dandan2611.daikin.DaikinControl.device.*;
import fr.dandan2611.daikin.DaikinControl.service.DaikinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DaikinControlRequestController {

    @Autowired
    private DaikinService daikinService;

    @RequestMapping(method = RequestMethod.POST, name = "controlRequestController", path = "/control/request")
    private ResponseEntity<Integer> daikinRequest(@RequestParam(required = true) Integer powerState, @RequestParam(required = false) Integer operatingMode,
                                                  @RequestParam(required = false) String fanRate, @RequestParam(required = false) Integer fanDirection,
                                                  @RequestParam(required = false) Double targetTemperature, @RequestParam(required = false) Integer targetHumidity) {

        DaikinControlInfo previousControlInfo = daikinService.getControlInfos();

        DaikinRequest daikinRequest = new DaikinRequest.DaikinRequestBuilder(PowerState.getPowerStateById(powerState))
                .setOperatingMode(operatingMode != null ? OperatingMode.getOperatingModeById(operatingMode) : previousControlInfo.getOperatingMode())
                .setFanRate(fanRate != null ? FanRate.getFanRateById(fanRate) : previousControlInfo.getFanRate())
                .setFanDirection(fanDirection != null ? FanDirection.getFanDirectionById(fanDirection) : previousControlInfo.getFanDirection())
                .setTargetTemperature(targetTemperature != null ? targetTemperature : previousControlInfo.getTargetTemperature())
                .setTargetHumidity(targetHumidity != null ? targetHumidity : previousControlInfo.getTargetHumidity())
                .build();

        daikinService.executeRequest(daikinRequest);

        return ResponseEntity.ok(1);
    }

}
