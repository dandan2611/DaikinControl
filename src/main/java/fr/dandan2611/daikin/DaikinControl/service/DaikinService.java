package fr.dandan2611.daikin.DaikinControl.service;

import fr.dandan2611.daikin.DaikinControl.device.DaikinControlInfo;
import fr.dandan2611.daikin.DaikinControl.device.DaikinRequest;
import fr.dandan2611.daikin.DaikinControl.device.DaikinSensorInfo;

public interface DaikinService {

    boolean start();
    boolean stop();

    boolean executeRequest(DaikinRequest daikinRequest);

    DaikinControlInfo getControlInfos();
    DaikinSensorInfo getSensorInfo();

}
