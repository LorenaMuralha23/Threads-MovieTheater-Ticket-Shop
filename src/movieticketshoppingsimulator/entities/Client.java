package movieticketshoppingsimulator.entities;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieticketshoppingsimulator.BoxOffice;

public class Client implements Runnable{

    private MovieTheater movieTheater;

    public Client(MovieTheater MovieTheater) {
        this.movieTheater = MovieTheater;
    }
    
    @Override
    public void run() {
        try {
            BoxOffice.SEMAPHORE.acquire();
            sleep();
            System.out.println(Thread.currentThread().getName() +": Buying ticket...");
            int clientID = new Random().nextInt(1000);
            this.movieTheater.buyTicket(clientID);
            System.out.println("-----------------------------------\n");
            BoxOffice.SEMAPHORE.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void sleep(){
        try {
            int timeOfWait = new Random().nextInt(1000);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
    
}
