package fr.dandan2611.daikin.DaikinControl.rest.controllers;

import fr.dandan2611.daikin.DaikinControl.database.entities.SensoredTemperature;
import fr.dandan2611.daikin.DaikinControl.device.DaikinSensorInfo;
import fr.dandan2611.daikin.DaikinControl.service.DaikinService;
import fr.dandan2611.daikin.DaikinControl.service.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseSaveController {

    @Autowired
    private DaikinService daikinService;

    @Autowired
    private SensorRepository sensorRepository;

    @RequestMapping(method = RequestMethod.GET, name = "databaseSaveController", path = "/database/save")
    private ResponseEntity<Integer> databaseSave() {
        sensorRepository.save(new SensoredTemperature(20, 0, 30));
        return ResponseEntity.ok(1);
    }

}
