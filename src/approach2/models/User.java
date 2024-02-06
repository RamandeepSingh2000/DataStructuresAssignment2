package approach2.models;

import java.util.Random;

public class User {
    private static int lastUserId = -1;
    private int userId;
    private String userData; //This represents username, contact info, etc.
    private int[] ticketsPurchased;
    public User(String userData){
        userId = ++lastUserId;
        this.userData = userData;
    }
    public int getUserId() {
        return userId;
    }
    public String getUserData(){
        return userData;
    }
    public int[] getTicketsPurchased() {
        return ticketsPurchased;
    }
    public void setTicketsPurchased(int[] ticketsPurchased) {
        this.ticketsPurchased = ticketsPurchased;
    }
    public int GetRandomTicket(){
        return ticketsPurchased[new Random().nextInt(0, ticketsPurchased.length)];
    }
    public int getNumberOfTicketsPurchased(){
        return ticketsPurchased.length;
    }
}
