package movieticketshoppingsimulator.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieticketshoppingsimulator.BoxOffice;
import static movieticketshoppingsimulator.BoxOffice.executor;

public class MovieTheater implements Runnable {

//    private List<Ticket> tickets = new ArrayList<>();
    public Lock lock = new ReentrantLock();

    public HashMap<String, SalesChannel> salesChannelList = new HashMap<>();
    

    public MovieTheater() {
    }

    
    public void addSalesChannel(String movieName, SalesChannel salesChannelToAdd) {
        this.salesChannelList.put(movieName, salesChannelToAdd);
    }

    public void buyTicket(Order order, String movieName) {
        try {

            this.salesChannelList.get(movieName).getOrderQueue().put(order);

        } catch (InterruptedException ex) {

            ex.printStackTrace();

        }
    }

    @Override
    public void run() {
        System.out.println("Fechando o caixa...");
        sleep();
        
        System.out.println("Ingressos sobrando para " + BoxOffice.salesChannel1.getMovieName() + ": " + BoxOffice.salesChannel1.getNumOfTickets());
        System.out.println("Ingressos sobrando para " + BoxOffice.salesChannel2.getMovieName() + ": " + BoxOffice.salesChannel2.getNumOfTickets());
        System.out.println("Ingressos sobrando para " + BoxOffice.salesChannel3.getMovieName() + ": " + BoxOffice.salesChannel3.getNumOfTickets());
        
        System.out.println("Dinheiro dos ingressos para " + BoxOffice.salesChannel1.getMovieName() + ": R$" + BoxOffice.salesChannel1.getResults().get(BoxOffice.salesChannel1.getMovieName()));
        System.out.println("Dinheiro dos ingressos para " + BoxOffice.salesChannel2.getMovieName() + ": R$" + BoxOffice.salesChannel2.getResults().get(BoxOffice.salesChannel2.getMovieName()));
        System.out.println("Dinheiro dos ingressos para " + BoxOffice.salesChannel3.getMovieName() + ": R$" + BoxOffice.salesChannel3.getResults().get(BoxOffice.salesChannel3.getMovieName()));

        BoxOffice.executor.shutdown();
    }

    
    public void sleep(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MovieTheater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
