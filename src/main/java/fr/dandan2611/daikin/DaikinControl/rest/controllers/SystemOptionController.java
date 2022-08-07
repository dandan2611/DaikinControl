package fr.dandan2611.daikin.DaikinControl.rest.controllers;

import fr.dandan2611.daikin.DaikinControl.option.PeripheralOptionKey;
import fr.dandan2611.daikin.DaikinControl.service.OptionsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemOptionController {

    @Autowired
    private OptionsManager optionsManager;

    @RequestMapping(method = RequestMethod.POST, name = "systemOptionController", path = "/system/option/set")
    private ResponseEntity<Integer> daikinRequest(@RequestParam(required = true) String name, @RequestParam(required = true) String value) {

        PeripheralOptionKey optionKey = PeripheralOptionKey.valueOf(name);
        if(optionKey == null)
            return ResponseEntity.badRequest().build();

        optionsManager.set(optionKey, value);

        return ResponseEntity.ok(1);
    }

}
