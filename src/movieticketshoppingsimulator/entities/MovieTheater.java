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

    

    public void buyTicket(int clientID, int numOfTicketsToBuy) {
        lock.lock();
        try {
            if ((this.numOfTickets - numOfTicketsToBuy) >= 0) {
                this.numOfTickets -= numOfTicketsToBuy;
                System.out.println(numOfTicketsToBuy + " ticket(s) was sold to client " + clientID);
                System.out.println("Num of tickets: " + this.numOfTickets + "\n");
            }else{
                System.out.println(clientID + " could not buy tickets anymore."
                        + "\nNumber of tickets avaliable: " + this.numOfTickets +
                        "\nNumber of tickets client " + clientID + " wanted to buy: " + numOfTicketsToBuy + "\n");
                
            }
        } finally {
            lock.unlock();
        }
    }

}
