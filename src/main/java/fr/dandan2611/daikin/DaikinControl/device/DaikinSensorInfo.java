package fr.dandan2611.daikin.DaikinControl.device;

public class DaikinSensorInfo {

    private double innerTemperature;
    private double outerTemperature;
    private double innerHumidity;

    public DaikinSensorInfo(double innerTemperature, double outerTemperature, double innerHumidity) {
        this.innerTemperature = innerTemperature;
        this.outerTemperature = outerTemperature;
        this.innerHumidity = innerHumidity;
    }

    protected DaikinSensorInfo() {

    }

    public double getInnerTemperature() {
        return innerTemperature;
    }

    public double getOuterTemperature() {
        return outerTemperature;
    }

    public double getInnerHumidity() {
        return innerHumidity;
    }

    protected void setInnerTemperature(double innerTemperature) {
        this.innerTemperature = innerTemperature;
    }

    protected void setOuterTemperature(double outerTemperature) {
        this.outerTemperature = outerTemperature;
    }

    protected void setInnerHumidity(double innerHumidity) {
        this.innerHumidity = innerHumidity;
    }

    public static DaikinSensorInfo parseInfos(String string) {
        DaikinSensorInfo daikinSensorInfo = new DaikinSensorInfo();
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
            if(key.equalsIgnoreCase("htemp"))
                daikinSensorInfo.setInnerTemperature(Double.parseDouble(value));
            else if(key.equalsIgnoreCase("hhum"))
                daikinSensorInfo.setInnerHumidity(Double.parseDouble(value));
            else if(key.equalsIgnoreCase("otemp"))
                daikinSensorInfo.setOuterTemperature(Double.parseDouble(value));

        }
        return daikinSensorInfo;
    }

}
