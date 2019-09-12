package com.example.homepc.whatsappclone.Model;

public class GroupMessage {

    private String text;
    private String name;
    private String uID;

    public GroupMessage() {
    }

    public GroupMessage(String text, String name, String uID) {
        this.text = text;
        this.name = name;
        this.uID = uID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}