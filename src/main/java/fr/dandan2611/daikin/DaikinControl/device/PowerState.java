package fr.dandan2611.daikin.DaikinControl.device;

public enum PowerState {

    OFF(0),
    ON(1);

    int id;

    PowerState(int state) {
        this.id = state;
    }

    public boolean isOn() {
        return ON.equals(this);
    }

    public boolean isOff() {
        return OFF.equals(this);
    }

    public int getId() {
        return id;
    }

    public static PowerState getPowerStateById(int id) {
        for(PowerState state : values())
            if(state.getId() == id)
                return state;
        return null;
    }

}
