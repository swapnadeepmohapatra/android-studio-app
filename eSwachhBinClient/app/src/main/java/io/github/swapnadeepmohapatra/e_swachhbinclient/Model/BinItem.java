package io.github.swapnadeepmohapatra.e_swachhbinclient.Model;

public class BinItem {
    private String status;
    private String location;
    private String locationLat;
    private String locationLog;
    private String lastEmptied;
    private String image;

    public BinItem() {

    }

    public BinItem(String status, String location, String locationLat, String locationLog, String lastEmptied, String image) {
        this.status = status;
        this.location = location;
        this.locationLat = locationLat;
        this.locationLog = locationLog;
        this.lastEmptied = lastEmptied;
        this.image = image;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public String getLocationLog() {
        return locationLog;
    }

    public String getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public String getLastEmptied() {
        return lastEmptied;
    }

    public String getImage() {
        return image;
    }
}

