package movieticketshoppingsimulator.entities;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieticketshoppingsimulator.BoxOffice;
import static movieticketshoppingsimulator.BoxOffice.movieTheater;

public class SalesChannel implements Runnable {

//    private Semaphore semaphore = new Semaphore(3);
    private String movieName;
    private int numOfTickets;
    private Lock lock = new ReentrantLock();
    private BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>(15);
    public Map<String, Double> results = new ConcurrentHashMap<>();
    boolean executing;
    double ticketPrice;
    double amountOfMoney;

    public SalesChannel(String movieName, int numOfTickets, double ticketPrice) {
        this.movieName = movieName;
        this.numOfTickets = numOfTickets;
        this.executing = true;
        this.amountOfMoney = 0;
        this.ticketPrice = ticketPrice;
    }

    public BlockingQueue<Order> getOrderQueue() {
        return orderQueue;
    }

    public Map<String, Double> getResults() {
        return results;
    }
   
    public String getMovieName() {
        return movieName;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    public void setExecuting(boolean executing) {
        this.executing = executing;
    }

    @Override
    public void run() {
        try {
            while (executing) {
                
                Order orderToProccess = orderQueue.poll(5, TimeUnit.SECONDS);
                if (orderToProccess != null) {
                    int numOfTicketsToBuy = orderToProccess.getNumOfTicketsToBuy();
                    int clientId = orderToProccess.getClientId();

                    lock.lock();
                    
                    if ((numOfTickets - numOfTicketsToBuy) >= 0) {
                        //pode comprar
                        numOfTickets -= numOfTicketsToBuy;
                        System.out.println(numOfTicketsToBuy + " ticket(s) was sell to client: " + clientId + " for " + this.movieName + "\n");
                        this.amountOfMoney += numOfTicketsToBuy * ticketPrice;
                        sleep();
                        BoxOffice.updateTicketNumber(movieName, numOfTicketsToBuy);
                    } else {
                        System.out.println("Client " + clientId + " could not finish his order. No tickets avaliable for " + this.movieName + "\n");
                    }
                    
                    
                    lock.unlock();
                } else {
                    try {
                        executing = false;
                        this.results.put(this.movieName, this.amountOfMoney);
                        BoxOffice.cyclicBarrier.await();
                    } catch (BrokenBarrierException ex) {
                        Logger.getLogger(SalesChannel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(SalesChannel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sleep(){
        try {
            Thread.sleep(5200);
        } catch (InterruptedException ex) {
            Logger.getLogger(MovieTheater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
