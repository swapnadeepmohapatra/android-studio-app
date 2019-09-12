package io.github.swapnadeepmohapatra.Parking.Model;

public class Book {
    private String Ref;
    private String Ticket;
    private String Seat;

    public Book(String ref, String ticket, String seat) {
        Ref = ref;
        Ticket = ticket;
        Seat = seat;
    }

    public Book() {
    }

    public String getRef() {
        return Ref;
    }

    public void setRef(String ref) {
        Ref = ref;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public String getSeat() {
        return Seat;
    }

    public void setSeat(String seat) {
        Seat = seat;
    }
}
