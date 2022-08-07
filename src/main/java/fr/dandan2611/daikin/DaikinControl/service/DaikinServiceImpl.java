package fr.dandan2611.daikin.DaikinControl.service;

import fr.dandan2611.daikin.DaikinControl.device.DaikinControlInfo;
import fr.dandan2611.daikin.DaikinControl.device.DaikinRequest;
import fr.dandan2611.daikin.DaikinControl.device.DaikinSensorInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class DaikinServiceImpl implements DaikinService {

    public static String IP = "192.168.1.45";

    private RestTemplate restTemplate;

    @Autowired
    private OptionsManager optionsManager;

    public DaikinServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }

    @Override
    public boolean executeRequest(DaikinRequest daikinRequest) {
        final HashMap<String, String> map = daikinRequest.toMap();
        String response = restTemplate.postForObject("http://" + IP + "/aircon/set_control_info?pow=" + map.get("pow") + "&mode=" + map.get("mode") + "&stemp=" + map.get("stemp") + "&f_rate=" + map.get("f_rate") + "&f_dir=" + map.get("f_dir") + "&shum=" + map.get("shum"), null, String.class);
        return false;
    }

    @Override
    public DaikinControlInfo getControlInfos() {
        String response = restTemplate.getForObject("http://" + IP + "/aircon/get_control_info", String.class);
        return DaikinControlInfo.parseInfos(response, optionsManager);
    }

    @Override
    public DaikinSensorInfo getSensorInfo() {
        String response = restTemplate.getForObject("http://" + IP + "/aircon/get_sensor_info", String.class);
        return DaikinSensorInfo.parseInfos(response);
    }

}
