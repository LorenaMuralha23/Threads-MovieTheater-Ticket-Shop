package movieticketshoppingsimulator.entities;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieticketshoppingsimulator.BoxOffice;

public class SalesChannel implements Runnable {

    private String movieName;
    private int numOfTickets;
    private Lock lock = new ReentrantLock();
    private BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>(15);
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

    public double getAmountOfMoney() {
        return amountOfMoney;
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
                sleep();
                if (numOfTickets > 0) {
                    Order orderToProccess = orderQueue.take();

                    int numOfTicketsToBuy = orderToProccess.getNumOfTicketsToBuy();
                    int clientId = orderToProccess.getClientId();

                    lock.lock();

                    if ((numOfTickets - numOfTicketsToBuy) >= 0) {
                        
                        numOfTickets -= numOfTicketsToBuy;
                        
                        this.amountOfMoney += numOfTicketsToBuy * ticketPrice;
                        sleep();
                        BoxOffice.updateTicketNumber(movieName, numOfTicketsToBuy);
                        BoxOffice.clientsPanel.decrementNumber();

                    } 

                    lock.unlock();

                } else {
                    try {
                        executing = false;
                        BoxOffice.cyclicBarrier.await();
                    } catch (BrokenBarrierException ex) {
                        Thread.currentThread().interrupt();
                        Logger.getLogger(SalesChannel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            Logger.getLogger(SalesChannel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sleep() {
        try {
            int timeToWait = new Random().nextInt(1000) + 1;
            Thread.sleep(timeToWait);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            Logger.getLogger(MovieTheater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
