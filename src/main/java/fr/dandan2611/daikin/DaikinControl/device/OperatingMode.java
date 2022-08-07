package fr.dandan2611.daikin.DaikinControl.device;

public enum OperatingMode {

    AUTO(new int[]{0, 1, 7}),
    DEHUMDIFICATOR(new int[]{2}),
    COLD(new int[]{3}),
    HOT(new int[]{4}),
    FAN(new int[]{6});

    int[] ids;

    OperatingMode(int[] ids) {
        this.ids = ids;
    }

    public int[] getIds() {
        return ids;
    }

    public static OperatingMode getOperatingModeById(int id) {
        for(OperatingMode operatingMode : values())
            for(int i : operatingMode.getIds())
                if(i == id)
                    return operatingMode;
        return null;
    }

}
