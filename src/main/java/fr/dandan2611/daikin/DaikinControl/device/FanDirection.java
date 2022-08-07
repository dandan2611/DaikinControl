package fr.dandan2611.daikin.DaikinControl.device;

public enum FanDirection {

    STOPPED(0),
    ONLY_VERTICAL(1),
    ONLY_HORIZONTAL(2),
    VERRTICAL_AND_HORIZONTAL(3);

    int id;

    FanDirection(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static FanDirection getFanDirectionById(int id) {
        for(FanDirection fanDirection : values())
            if(fanDirection.getId() == id)
                return fanDirection;
        return null;
    }

}
