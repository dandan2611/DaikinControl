package fr.dandan2611.daikin.DaikinControl.device;

import java.util.HashMap;

public class DaikinRequest {

    private DaikinControlInfo controlInfo;

    protected DaikinRequest(DaikinControlInfo controlInfo) {
        this.controlInfo = controlInfo;
    }

    public HashMap<String, String> toMap() {
        final HashMap<String, String> map = new HashMap<>();
        map.put("pow", String.valueOf(this.controlInfo.getPowerState().getId()));
        map.put("mode", String.valueOf(this.controlInfo.getOperatingMode().getIds()[0]));
        map.put("stemp", String.valueOf(this.controlInfo.getTargetTemperature()));
        map.put("f_rate", this.controlInfo.getFanRate().getId());
        map.put("f_dir", String.valueOf(this.controlInfo.getFanDirection().getId()));
        map.put("shum", String.valueOf(this.controlInfo.getTargetHumidity()));
        return map;
    }

    public static class DaikinRequestBuilder {

        private PowerState powerState = PowerState.OFF;
        private OperatingMode operatingMode = OperatingMode.AUTO;
        private FanRate fanRate = FanRate.LEVEL_3;
        private FanDirection fanDirection = FanDirection.STOPPED;
        private double targetTemperature = 23d;
        private int targetHumidity = 0;

        public DaikinRequestBuilder(PowerState powerState) {
            this.powerState = powerState;
        }

        public DaikinRequestBuilder setPowerState(PowerState powerState) {
            this.powerState = powerState;
            return this;
        }

        public DaikinRequestBuilder setOperatingMode(OperatingMode operatingMode) {
            this.operatingMode = operatingMode;
            return this;
        }

        public DaikinRequestBuilder setFanRate(FanRate fanRate) {
            this.fanRate = fanRate;
            return this;
        }

        public DaikinRequestBuilder setFanDirection(FanDirection fanDirection) {
            this.fanDirection = fanDirection;
            return this;
        }

        public DaikinRequestBuilder setTargetTemperature(double targetTemperature) {
            this.targetTemperature = targetTemperature;
            return this;
        }

        public DaikinRequestBuilder setTargetHumidity(int targetHumidity) {
            this.targetHumidity = targetHumidity;
            return this;
        }

        public DaikinRequestBuilder fillFromInfos(DaikinControlInfo info) {
            //this.powerState = info.getPowerState();
            this.operatingMode = info.getOperatingMode();
            this.fanRate = info.getFanRate();
            this.fanDirection = info.getFanDirection();
            this.targetTemperature = info.getTargetTemperature();
            this.targetHumidity = info.getTargetHumidity();
            return this;
        }

        public DaikinRequest build() {
            return new DaikinRequest(new DaikinControlInfo(
                    this.powerState,
                    this.operatingMode,
                    this.fanRate,
                    this.fanDirection,
                    this.targetTemperature,
                    this.targetHumidity
            ));
        }

    }

}
