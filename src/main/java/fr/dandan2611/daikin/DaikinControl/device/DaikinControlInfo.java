package fr.dandan2611.daikin.DaikinControl.device;

import fr.dandan2611.daikin.DaikinControl.service.OptionsManager;

public class DaikinControlInfo {

    private PowerState powerState;
    private OperatingMode operatingMode;
    private FanRate fanRate;
    private FanDirection fanDirection;
    private double targetTemperature;
    private int targetHumidity;

    public DaikinControlInfo(PowerState powerState, OperatingMode operatingMode, FanRate fanRate, FanDirection fanDirection, double targetTemperature, int targetHumidity) {
        this.powerState = powerState;
        this.operatingMode = operatingMode;
        this.fanRate = fanRate;
        this.fanDirection = fanDirection;
        this.targetTemperature = targetTemperature;
        this.targetHumidity = targetHumidity;
    }

    protected DaikinControlInfo() {

    }

    public PowerState getPowerState() {
        return powerState;
    }

    public OperatingMode getOperatingMode() {
        return operatingMode;
    }

    public FanRate getFanRate() {
        return fanRate;
    }

    public FanDirection getFanDirection() {
        return fanDirection;
    }

    public double getTargetTemperature() {
        return targetTemperature;
    }

    public int getTargetHumidity() {
        return targetHumidity;
    }

    protected void setPowerState(PowerState powerState) {
        this.powerState = powerState;
    }

    protected void setOperatingMode(OperatingMode operatingMode) {
        this.operatingMode = operatingMode;
    }

    protected void setFanRate(FanRate fanRate) {
        this.fanRate = fanRate;
    }

    protected void setFanDirection(FanDirection fanDirection) {
        this.fanDirection = fanDirection;
    }

    protected void setTargetTemperature(double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    protected void setTargetHumidity(int targetHumidity) {
        this.targetHumidity = targetHumidity;
    }

    public static DaikinControlInfo parseInfos(String string, OptionsManager optionsManager) {
        DaikinControlInfo daikinControlInfo = new DaikinControlInfo();
        if(string == null)
            return null;
        String[] splittedCommas = string.split(",");
        if(splittedCommas == null || splittedCommas.length == 0)
            return null;
        for(String text : splittedCommas) {
            String[] s = text.split("=");
            String key = s[0];
            String value = s[1];
            if(key == null || value == null)
                return null;
            if(key.equalsIgnoreCase("pow"))
                daikinControlInfo.setPowerState(PowerState.getPowerStateById(Integer.parseInt(value)));
            else if(key.equalsIgnoreCase("mode"))
                daikinControlInfo.setOperatingMode(OperatingMode.getOperatingModeById(Integer.parseInt(value)));
            else if(key.equalsIgnoreCase("stemp"))
                daikinControlInfo.setTargetTemperature(!value.equals("--") ? Double.parseDouble(value) : optionsManager.getPreferredRoomTemperature());
            else if(key.equalsIgnoreCase("shum"))
                daikinControlInfo.setTargetHumidity(!value.equals("--") ? Integer.parseInt(value) : 0);
            else if(key.equalsIgnoreCase("f_rate"))
                daikinControlInfo.setFanRate(FanRate.getFanRateById(value));
            else if(key.equalsIgnoreCase("f_dir"))
                daikinControlInfo.setFanDirection(FanDirection.getFanDirectionById(Integer.parseInt(value)));
        }
        return daikinControlInfo;
    }

}
