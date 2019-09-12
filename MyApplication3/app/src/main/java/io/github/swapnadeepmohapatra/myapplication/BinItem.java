package io.github.swapnadeepmohapatra.myapplication;

public class BinItem {
    private String Date;
    private String Location;
    private String Name;
    private String Time;

    public BinItem(){

    }

    public BinItem(String date, String location, String name, String time) {
        Date = date;
        Location = location;
        Name = name;
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}


