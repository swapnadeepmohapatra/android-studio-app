package io.github.swapnadeepmohapatra.Parking.Model;

public class Parking {

    private String location;
    private String name;
    private String booked;
    private String empty;
    private String loc;

    public Parking(String location, String name, String booked, String empty, String loc) {
        this.location = location;
        this.name = name;
        this.booked = booked;
        this.empty = empty;
        this.loc = loc;
    }

    public Parking() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBooked() {
        return booked;
    }

    public void setBooked(String booked) {
        this.booked = booked;
    }

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
