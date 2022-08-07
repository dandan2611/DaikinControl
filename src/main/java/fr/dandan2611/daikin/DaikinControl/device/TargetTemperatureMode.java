package fr.dandan2611.daikin.DaikinControl.device;

public enum TargetTemperatureMode {

    AUTO(18, 31),
    HOT(10, 31),
    COLD(18, 33);

    int minTemp, maxTemp;

    TargetTemperatureMode(int minTemp, int maxTemps) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemps;
    }

    public boolean isInRange(int temp) {
        return minTemp < temp && temp < maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

}
