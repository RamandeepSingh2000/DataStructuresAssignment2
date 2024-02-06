package approach1.models;

public class TicketUserPair {
    private final int ticketNumber;
    private final User user;

    public TicketUserPair(int ticketNumber, User user) {
        this.ticketNumber = ticketNumber;
        this.user = user;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public User getUser() {
        return user;
    }
}
