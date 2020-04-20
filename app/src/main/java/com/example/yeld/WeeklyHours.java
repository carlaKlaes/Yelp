package com.example.yeld;

public class WeeklyHours {
    public String start = "";
    public String end = "";
    public Integer day;
    public String weekday = "";
    public String hours = "";


    public WeeklyHours() {
    }

    public WeeklyHours(String start, String end, Integer day) {
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start.substring(0,2) + ":" + start.substring(2,4);
        setHours();
    }

    private void setHours(){
        this.hours = this.start + " - " + this.end;
    }

    public String getHours(){
        return this.hours;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end.substring(0,2) + ":" + end.substring(2,4);
        setHours();
    }

    public Integer getDay() {
        return day;
    }

    public String getWeekDay() {
        return weekday;
    }

    public void setDay(Integer day) {
        switch (day){
            case 0:
                weekday="Mon";
                break;
            case 1:
                weekday="Tue";
                break;
            case 2:
                weekday="Wed";
                break;
            case 3:
                weekday="Thr";
                break;
            case 4:
                weekday="Fri";
                break;
            case 5:
                weekday="Sat";
                break;
            case 6:
                weekday="Sun";
                break;
                default:
                    weekday="Special day";
                    break;
        }
        this.day = day;
    }
}
