package io.github.swapnadeepmohapatra.nirmiti.Model;

public class BinItem {

    private String status;
    private String percentage;
    private String image;
    private String location;
    private String locationLat;
    private String locationLog;


    public BinItem() {

    }

    public BinItem(String status, String percentage, String image, String location, String locationLat, String locationLog) {
        this.status = status;
        this.percentage = percentage;
        this.image = image;
        this.location = location;
        this.locationLat = locationLat;
        this.locationLog = locationLog;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getpercentage() {
        return percentage;
    }

    public void setpercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(String locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationLog() {
        return locationLog;
    }

    public void setLocationLog(String locationLog) {
        this.locationLog = locationLog;
    }
}
