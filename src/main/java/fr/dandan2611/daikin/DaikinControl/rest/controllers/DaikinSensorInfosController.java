package fr.dandan2611.daikin.DaikinControl.rest.controllers;

import fr.dandan2611.daikin.DaikinControl.device.DaikinControlInfo;
import fr.dandan2611.daikin.DaikinControl.device.DaikinRequest;
import fr.dandan2611.daikin.DaikinControl.device.DaikinSensorInfo;
import fr.dandan2611.daikin.DaikinControl.device.PowerState;
import fr.dandan2611.daikin.DaikinControl.service.DaikinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DaikinSensorInfosController {

    @Autowired
    private DaikinService daikinService;

    @RequestMapping(method = RequestMethod.GET, name = "sensorInfosController", path = "/sensor/infos")
    private DaikinSensorInfo daikinInfos() {
        return daikinService.getSensorInfo();
    }

}
