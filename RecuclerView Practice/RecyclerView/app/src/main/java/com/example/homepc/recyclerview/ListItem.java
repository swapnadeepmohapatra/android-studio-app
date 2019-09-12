package com.example.homepc.recyclerview;

public class ListItem {

    private String head;
    private String desc;
//    private String realName;
    private String firsApp;
    private String imageUrl;

    public ListItem(String head, String desc, String firsApp, String imageUrl) {
        this.head = head;
        this.desc = desc;
//        this.realName = realName;
        this.firsApp = firsApp;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

//    public String getRealName() {
//        return realName;
//    }

    public String getFirsApp() {
        return firsApp;
    }
}
