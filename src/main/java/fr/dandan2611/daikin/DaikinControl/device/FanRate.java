package fr.dandan2611.daikin.DaikinControl.device;

public enum FanRate {

    AUTO("A"),
    SILENCE("B"),
    LEVEL_1(3),
    LEVEL_2(4),
    LEVEL_3(5),
    LEVEL_4(6),
    LEVEL_5(7);

    String id;

    FanRate(String id) {
        this.id = id;
    }

    FanRate(int id) {
        this(String.valueOf(id));
    }

    public String getId() {
        return id;
    }

    public static FanRate getFanRateById(String id) {
        for(FanRate fanRate : values())
            if(fanRate.getId().equals(id))
                return fanRate;
        return null;
    }

    public static FanRate getFanRateById(int id) {
        return getFanRateById(String.valueOf(id));
    }

}
