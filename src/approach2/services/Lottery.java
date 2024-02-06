package approach2.services;

import approach2.models.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Lottery {
    private ArrayList<Integer> availableTickets;
    private ArrayList<User> registeredUsers;
    private final Random random = new Random();
    private int maxTickets = 0;
    private int ticketsArraySize = 100;
    private int numberOfTicketsSold = 0;
    public Lottery(){
        availableTickets = new ArrayList<>(ticketsArraySize);
        registeredUsers = new ArrayList<>();
        FillAvailableTickets();
    }
    public Lottery(int expectedTicketsToBeSold){
        ticketsArraySize = expectedTicketsToBeSold;
        availableTickets = new ArrayList<>(ticketsArraySize);
        registeredUsers = new ArrayList<>();
        FillAvailableTickets();
    }
    public Lottery(int expectedTicketsToBeSold, int numberOfExpectedUsers){
        ticketsArraySize = expectedTicketsToBeSold;
        availableTickets = new ArrayList<>(ticketsArraySize);
        registeredUsers = new ArrayList<>(numberOfExpectedUsers);
        FillAvailableTickets();
    }
    private void FillAvailableTickets(){
        for(int i=0;i<ticketsArraySize;i++){
            availableTickets.add(maxTickets + i);
        }
        maxTickets += ticketsArraySize;
    }
    public void PurchaseTickets(User user, int numberOfTickets){
        registeredUsers.add(user);
        int[] tickets = new int[numberOfTickets];
        if(availableTickets.size() < numberOfTickets){
            availableTickets.clear();
            FillAvailableTickets();
        }
        for(int i=0;i<numberOfTickets;i++){
            tickets[i] = availableTickets.remove(random.nextInt(0, availableTickets.size()));
        }
        user.setTicketsPurchased(tickets);
        numberOfTicketsSold += numberOfTickets;
    }
    public void RunLottery(){
        float totalDrawAmount = 20 * numberOfTicketsSold;
        HashSet<User> winners = new HashSet<>(4);
        while(winners.size() != 4){
            User user = SelectUserUsingProbability();
            if(!winners.contains(user)){
                winners.add(user);
                PrintWinner(user, winners.size(), totalDrawAmount * ((5 - winners.size())/10f), user.GetRandomTicket());
            }
        }
    }

    private User SelectUserUsingProbability(){
        float rand = random.nextFloat(0, 1);
        float cummulativeProbablity = 0;
        for(int i=0;i<registeredUsers.size();i++){
            User user = registeredUsers.get(i);
            cummulativeProbablity += (float)user.getNumberOfTicketsPurchased()/numberOfTicketsSold;
            if(cummulativeProbablity > rand){
                return user;
            }
        }

        return null;
    }

    private void PrintWinner(User user, int position, float amount, int ticketNumber){
        StringBuilder builder = new StringBuilder("\nWinner ");
        builder.append(position);
        builder.append(": ");
        builder.append("User ");
        builder.append(user.getUserId());
        builder.append(" won $");
        builder.append(amount);
        builder.append(" (Ticket Number: ");
        builder.append(ticketNumber);
        builder.append(")");
        System.out.println(builder);
    }

}
