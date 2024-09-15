package movieticketshoppingsimulator.entities;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieticketshoppingsimulator.BoxOffice;
import view.panels.TotalPanel;

public class MovieTheater implements Runnable {

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
        try {
            BoxOffice.clientsPanel.finishingLine();
            
            sleep();
            
            TotalPanel totalPanel = new TotalPanel();
            
            double total = 0;
            
            total += this.salesChannelList.get("Rocky").getAmountOfMoney();
            total += this.salesChannelList.get("The Goonies").getAmountOfMoney();
            total += this.salesChannelList.get("Forrest Gump").getAmountOfMoney();
            
            totalPanel.setTotal(total);
            
            BoxOffice.updateFinalPanel(totalPanel);
            
            BoxOffice.executor.shutdown();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            Logger.getLogger(MovieTheater.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            Logger.getLogger(MovieTheater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
