package movieticketshoppingsimulator.entities;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static movieticketshoppingsimulator.BoxOffice.clientsPanel;

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
            clientsPanel.incrementNumber();
            int clientID = new Random().nextInt(1000);
            int numOfTicketsToBuy = new Random().nextInt(10) + 1;
            this.order.setClientId(clientID);
            this.order.setNumOfTicketsToBuy(numOfTicketsToBuy);

            movieTheater.buyTicket(order, this.movieName);
           

        } catch (Exception ex) {
            Thread.currentThread().interrupt();
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sleep() {
        try {
            Thread.sleep(18000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

}
