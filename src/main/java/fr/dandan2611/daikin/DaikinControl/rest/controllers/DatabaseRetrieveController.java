package fr.dandan2611.daikin.DaikinControl.rest.controllers;

import fr.dandan2611.daikin.DaikinControl.service.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class DatabaseRetrieveController {

    @Autowired
    private SensorRepository sensorRepository;

    @RequestMapping(method = RequestMethod.GET, name = "databaseRetrieveController", path = "/database/search")
    private ResponseEntity<Integer> databaseRetrieve() {
        return null;
    }

}
