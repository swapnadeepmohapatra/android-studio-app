package io.github.swapnadeepmohapatra.busbookadmin.Model;

public class UserItem {
    private String username;
    private String email;
    private String rfid;
    private int money;

    public UserItem() {

    }

    public UserItem(String username, String email, String rfid, int money) {
        this.username = username;
        this.email = email;
        this.rfid = rfid;
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
