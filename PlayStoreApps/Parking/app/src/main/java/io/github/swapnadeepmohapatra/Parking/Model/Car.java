package io.github.swapnadeepmohapatra.Parking.Model;

public class Car {
    private boolean state;
    private String id;
    private String ticket;
    private String place;
    private String location;

    public Car() {
    }

    public Car(boolean state, String id, String ticket, String place, String location) {
        this.state = state;
        this.id = id;
        this.ticket = ticket;
        this.place = place;
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
