package io.github.swapnadeepmohapatra.instagramclone.Model;

public class Message {

    private String userName;
    private String userPhotoUrl;
    private String userStatus;
    private String message;
    private String messagePhotoUrl;
    private String time;
    private String date;

    public Message() {

    }

    public Message(String userName, String userPhotoUrl, String userStatus, String message, String messagePhotoUrl, String time, String date) {
        this.userName = userName;
        this.userPhotoUrl = userPhotoUrl;
        this.userStatus = userStatus;
        this.message = message;
        this.messagePhotoUrl = messagePhotoUrl;
        this.time = time;
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessagePhotoUrl() {
        return messagePhotoUrl;
    }

    public void setMessagePhotoUrl(String messagePhotoUrl) {
        this.messagePhotoUrl = messagePhotoUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
