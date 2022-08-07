package fr.dandan2611.daikin.DaikinControl.option;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ScheduleUtils {

    public static ArrayList<Schedule> parseSchedules(String line) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        if(line == null || line.isBlank()) {
            return schedules;
        }
        String[] splittedSchedules = line.split("/");
        for(String s : splittedSchedules) {
            String[] splittedSingleSchedule = s.split("-");
            if(splittedSingleSchedule.length != 2)
                continue;
            Integer beginHour = null, beginMinutes = null, endHour = null, endMinutes = null;
            boolean begin = true;
            for(String time : splittedSingleSchedule) {
                String[] splittedTime = time.split(":");
                if(splittedTime.length != 2)
                    break;
                if(begin) {
                    beginHour = Integer.parseInt(splittedTime[0]);
                    beginMinutes = Integer.parseInt(splittedTime[1]);
                    begin = false;
                }
                else {
                    endHour = Integer.parseInt(splittedTime[0]);
                    endMinutes = Integer.parseInt(splittedTime[1]);
                }
            }
            if(beginHour == null || beginMinutes == null || endHour == null || endMinutes == null)
                continue;

            Schedule schedule = new Schedule(beginHour, beginMinutes, endHour, endMinutes);
            schedules.add(schedule);
        }

        return schedules;
    }

    public static class Schedule {

        public int beginHour;
        public int beginMinutes;

        public int endHour;
        public int endMinutes;

        public Schedule(int beginHour, int beginMinutes, int endHour, int endMinutes) {
            this.beginHour = beginHour;
            this.beginMinutes = beginMinutes;
            this.endHour = endHour;
            this.endMinutes = endMinutes;
        }

        public boolean isInSchedule() {
            LocalDateTime dateTime = LocalDateTime.now();
            return beginHour <= dateTime.getHour()
                    && endHour >= dateTime.getHour()
                    && beginMinutes <= dateTime.getMinute()
                    && endMinutes >= dateTime.getMinute();
        }

    }

}
