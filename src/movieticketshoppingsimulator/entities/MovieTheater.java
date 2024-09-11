package movieticketshoppingsimulator.entities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MovieTheater {

//    private List<Ticket> tickets = new ArrayList<>();
    public Lock lock = new ReentrantLock();
    
    private int numOfTickets;

    public MovieTheater() {
    }

    public MovieTheater(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public void setNumOfTickets(int numOfTickets) {
        this.numOfTickets = numOfTickets;
    }

    

    public void buyTicket(int clientID) {
        lock.lock();
        try {
            if (this.numOfTickets > 0) {
                this.numOfTickets--;
                System.out.println("A ticket was sold to client " + clientID);
                System.out.println("Num of tickets: " + this.numOfTickets);
            }else{
                System.out.println("No ticket avaliable");
            }
        } finally {
            lock.unlock();
        }
    }

}
