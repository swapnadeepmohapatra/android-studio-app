package io.github.swapnadeepmohapatra.nirmiti.Model;

public class ComplainItem {

    private String Date;
    private String Location;
    private String Name;
    private String Time;
    private String Message;

    public ComplainItem() {

    }

    public ComplainItem(String date, String location, String name, String time, String message) {
        Date = date;
        Location = location;
        Name = name;
        Time = time;
        Message = message;
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

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public void setTime(String time) {
        Time = time;
    }
}


