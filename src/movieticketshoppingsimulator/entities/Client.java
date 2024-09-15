package movieticketshoppingsimulator.entities;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieticketshoppingsimulator.BoxOffice;

public class Client implements Runnable {

    private MovieTheater movieTheater;
    private Order order;
    private String movieName;

    public Client(MovieTheater MovieTheater, String movieName) {
        this.movieTheater = MovieTheater;
        this.movieName = movieName;
        this.order = new Order();
    }

    @Override
    public void run() {
        try {

            int clientID = new Random().nextInt(1000);
            int numOfTicketsToBuy = new Random().nextInt(10) + 1;
            this.order.setClientId(clientID);
            this.order.setNumOfTicketsToBuy(numOfTicketsToBuy);

            System.out.println(order.getClientId() + " is trying to buy tickets for...");
//            sleep();

            movieTheater.buyTicket(order, this.movieName);
            sleep();
            BoxOffice.clientsPanel.decrementNumber();
        } catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}
