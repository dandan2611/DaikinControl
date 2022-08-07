package fr.dandan2611.daikin.DaikinControl.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ping")
public class PingController {

    @RequestMapping(method = RequestMethod.GET)
    public String ping(ModelMap model) {
        model.addAttribute("message", "Pong!");
        return "ping";
    }

}
