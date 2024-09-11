package movieticketshoppingsimulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import movieticketshoppingsimulator.entities.Client;
import movieticketshoppingsimulator.entities.MovieTheater;

public class BoxOffice {

    public static Semaphore SEMAPHORE = new Semaphore(3);
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        int numOfTickets = 94;
        int numOfClients = 100;
        MovieTheater movieTheater = new MovieTheater(numOfTickets);

        for (int i = 0; i < numOfClients; i++) {
            executor.execute(new Client(movieTheater));
        }
        
        
        executor.shutdown();

    }

}
