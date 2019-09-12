package io.github.swapnadeepmohapatra.busbookadmin.Model;

public class BusItem {

    private String name;
    private String time;
    private String number;

    public BusItem(String name, String time, String number) {
        this.name = name;
        this.time = time;
        this.number = number;
    }

    public BusItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
