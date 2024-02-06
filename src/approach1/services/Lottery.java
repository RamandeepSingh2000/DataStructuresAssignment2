package approach1.services;

import approach1.models.TicketUserPair;
import approach1.models.User;

import java.util.*;

public class Lottery {
    private ArrayList<Integer> availableTickets;
    private ArrayList<TicketUserPair> ticketDistributed;
    private final Random random = new Random();
    private int maxTickets = 0;
    private int ticketsArraySize = 100;
    public Lottery(){
        availableTickets = new ArrayList<>(ticketsArraySize);
        ticketDistributed = new ArrayList<>(ticketsArraySize);
        FillAvailableTickets();
    }
    public Lottery(int expectedTicketsToBeSold){
        ticketsArraySize = expectedTicketsToBeSold;
        ticketDistributed = new ArrayList<>(ticketsArraySize);
        availableTickets = new ArrayList<>(ticketsArraySize);
        FillAvailableTickets();
    }
    private void FillAvailableTickets(){
        for(int i=0;i<ticketsArraySize;i++){
            availableTickets.add(maxTickets + i);
        }
        maxTickets += ticketsArraySize;
    }
    public void PurchaseTicket(User user){
        ticketDistributed.add(new TicketUserPair(availableTickets.remove(random.nextInt(0, availableTickets.size())), user));
        if(availableTickets.isEmpty()){
            FillAvailableTickets();
        }
    }
    public void RunLottery(){
        float totalDrawAmount = 20 * ticketDistributed.size();
        HashSet<User> winners = new HashSet<>(4);
        while(winners.size() != 4){
            int index = random.nextInt(0, ticketDistributed.size());
            TicketUserPair pair = ticketDistributed.get(index);
            User user = pair.getUser();
            if(!winners.contains(user)){
                winners.add(user);
                PrintWinner(user, winners.size(), totalDrawAmount * ((5 - winners.size())/10f), pair.getTicketNumber());
            }
        }
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
