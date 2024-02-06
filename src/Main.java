import approach1.models.User;
import approach1.services.Lottery;

import java.util.Random;

public class Main {
    static long startTime;
    static long endTime;

    public static void main(String[] args) {
        TestApproach1Lottery();
        TestApproach2Lottery();
    }

    static void TestApproach1Lottery(){
        System.out.println("Testing Approach 1 Lottery...");
        //Creating users.
        Random random = new Random();
        int numberOfUsers = 1000;
        int expectedTicketsToBeSold = 1000000;
        int minTicketsPurchasePerUser = 999;
        int maxTicketsPurchasePerUser = 1000;
        User[] users = new User[numberOfUsers];
        for(int i=0;i<users.length;i++){
            users[i] = new User("User data simulated.");
        }

        //Initializing Lottery
        System.out.println("Initializing Lottery...");

        StartTimer();
        Lottery lottery = new Lottery(expectedTicketsToBeSold);
        StopTimer();

        //Purchasing Tickets
        System.out.println("Purchasing Tickets...");

        StartTimer();
        for(int i=0;i<users.length;i++){
            int numberOfTickets = random.nextInt(minTicketsPurchasePerUser, maxTicketsPurchasePerUser);
            for(int k=0;k<numberOfTickets;k++){
                lottery.PurchaseTicket(users[i]);
            }
        }
        StopTimer();

        //Running Lottery
        System.out.println("Running Lottery...");
        StartTimer();
        lottery.RunLottery();
        StopTimer();
    }
    static void TestApproach2Lottery(){
        System.out.println("Testing Approach 2 Lottery...");
        //Creating users.
        Random random = new Random();
        int numberOfUsers = 1000;
        int expectedTicketsToBeSold = 1000000;
        int minTicketsPurchasePerUser = 999;
        int maxTicketsPurchasePerUser = 1000;
        approach2.models.User[] users;
        users = new approach2.models.User[numberOfUsers];

        for(int i=0;i<users.length;i++){
            users[i] = new approach2.models.User("User data simulated.");
        }

        //Initializing Lottery
        System.out.println("Initializing Lottery...");

        StartTimer();
        approach2.services.Lottery lottery = new approach2.services.Lottery(expectedTicketsToBeSold, numberOfUsers);
        StopTimer();

        //Purchasing Tickets
        System.out.println("Purchasing Tickets...");

        StartTimer();
        for(int i=0;i<users.length;i++){
            int numberOfTickets = random.nextInt(minTicketsPurchasePerUser, maxTicketsPurchasePerUser);
            lottery.PurchaseTickets(users[i], numberOfTickets);
        }
        StopTimer();

        //Running Lottery
        System.out.println("Running Lottery...");
        StartTimer();
        lottery.RunLottery();
        StopTimer();
    }

    static void StartTimer(){
        startTime = System.nanoTime();
    }
    static void StopTimer(){
        endTime = System.nanoTime();
        PrintElapsedTime();
    }
    static void PrintElapsedTime(){
        long elapsedTime = (endTime - startTime) / 1000000;
        System.out.println("Elapsed time: " + elapsedTime + " milliseconds");
    }
}