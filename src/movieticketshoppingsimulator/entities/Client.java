package movieticketshoppingsimulator.entities;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieticketshoppingsimulator.BoxOffice;

public class Client implements Runnable {

    private MovieTheater movieTheater;
    private int numOfTicketsToBuy;

    public Client(MovieTheater MovieTheater, int numOfTicketsToBuy) {
        this.movieTheater = MovieTheater;
        this.numOfTicketsToBuy = numOfTicketsToBuy;
    }

    @Override
    public void run() {
        try {
            BoxOffice.SEMAPHORE.acquire();
            sleep();
            int clientID = new Random().nextInt(1000);
            System.out.println(Thread.currentThread().getName() + ": Buying ticket for " + clientID + "...");
            this.movieTheater.buyTicket(clientID, numOfTicketsToBuy);
            BoxOffice.SEMAPHORE.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sleep() {
        try {
            int timeOfWait = new Random().nextInt(1000);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}
