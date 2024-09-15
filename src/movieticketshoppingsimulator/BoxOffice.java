package movieticketshoppingsimulator;

import java.awt.BorderLayout;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import movieticketshoppingsimulator.entities.Client;
import movieticketshoppingsimulator.entities.MovieTheater;
import movieticketshoppingsimulator.entities.SalesChannel;
import view.Frame1;
import view.Frame2;
import view.panels.ClientsPanel;
import view.panels.LoadingPanel;
import view.panels.Movie1Panel;
import view.panels.TotalPanel;

public class BoxOffice {

    public static ExecutorService executor = Executors.newCachedThreadPool();
    public static MovieTheater movieTheater = new MovieTheater();
    public static SalesChannel salesChannel1;
    public static SalesChannel salesChannel2;
    public static SalesChannel salesChannel3;
    public static CyclicBarrier cyclicBarrier;

    public static Frame1 frame1 = new Frame1();
    public static Frame1 movieFrame1 = new Frame1();
    public static Frame1 movieFrame2 = new Frame1();
    public static Frame1 movieFrame3 = new Frame1();
    public static Frame2 frame3 = new Frame2();

    public static ClientsPanel clientsPanel;
    public static Movie1Panel moviePanel1;
    public static Movie1Panel moviePanel2;
    public static Movie1Panel moviePanel3;
    public static LoadingPanel loadingPanel;

    public static void main(String[] args) {

        cyclicBarrier = new CyclicBarrier(3, BoxOffice.movieTheater);

        salesChannel1 = new SalesChannel("Rocky", 30, 5);
        salesChannel2 = new SalesChannel("The Goonies", 32, 5);
        salesChannel3 = new SalesChannel("Forrest Gump", 20, 8);

        startWindow();

        movieTheater.addSalesChannel(salesChannel1.getMovieName(), salesChannel1);
        movieTheater.addSalesChannel(salesChannel2.getMovieName(), salesChannel2);
        movieTheater.addSalesChannel(salesChannel3.getMovieName(), salesChannel3);

        executor.execute(salesChannel1);
        executor.execute(salesChannel2);
        executor.execute(salesChannel3);
        
        boolean cond1 = salesChannel1.getNumOfTickets() != 0;
        boolean cond2 = salesChannel2.getNumOfTickets() != 0;
        boolean cond3 = salesChannel3.getNumOfTickets() != 0;

        while(cond1 || cond2 || cond3) {

            String movieName = "";
            int movieNameDraw = new Random().nextInt(3) + 1;
            switch (movieNameDraw) {
                case 1:
                    movieName = "Rocky";
                    break;
                case 2:
                    movieName = "The Goonies";
                    break;
                case 3:
                    movieName = "Forrest Gump";
                    break;
                default:
                    throw new AssertionError();
            }
            
            sleepOneSecond();
            executor.submit(new Client(movieTheater, movieName));
            cond1 = salesChannel1.getNumOfTickets() != 0;
            cond2 = salesChannel2.getNumOfTickets() != 0;
            cond3 = salesChannel3.getNumOfTickets() != 0;
        }
    }

   
    public static void startWindow() {
        int x1 = 100;
        int movieX1 = 600;
        int movieX2 = 1100;
        int movieX3 = 100;
        int x4 = 900;
        int y = 200;
        int y2 = 600;

        frame1.setLayout(new BorderLayout());
        movieFrame1.setLayout(new BorderLayout());
        movieFrame2.setLayout(new BorderLayout());
        movieFrame3.setLayout(new BorderLayout());
        frame3.setLayout(new BorderLayout());

        clientsPanel = new ClientsPanel();
        moviePanel1 = new Movie1Panel(salesChannel1.getMovieName(), salesChannel1.getNumOfTickets());
        moviePanel2 = new Movie1Panel(salesChannel2.getMovieName(), salesChannel2.getNumOfTickets());
        moviePanel3 = new Movie1Panel(salesChannel3.getMovieName(), salesChannel3.getNumOfTickets());
        loadingPanel = new LoadingPanel();

        frame1.add(clientsPanel, BorderLayout.CENTER); 
        movieFrame1.add(moviePanel1, BorderLayout.CENTER);
        movieFrame2.add(moviePanel2, BorderLayout.CENTER);
        movieFrame3.add(moviePanel3, BorderLayout.CENTER);
        frame3.add(loadingPanel, BorderLayout.SOUTH);   
       

        
        frame1.setLocation(x1, y);    
        movieFrame1.setLocation(movieX1, y);    
        movieFrame2.setLocation(movieX2, y);    
        movieFrame3.setLocation(movieX3, y2);   
        frame3.setLocation(x4, y2);   

        frame1.pack();
        movieFrame1.pack();
        movieFrame2.pack();
        movieFrame3.pack();
        frame3.pack();

        frame1.setVisible(true);
        movieFrame1.setVisible(true);
        movieFrame2.setVisible(true);
        movieFrame3.setVisible(true);
        frame3.setVisible(true);
    }

    public static void updateTicketNumber(String movieName, int ticketsSold) {
        switch (movieName) {
            case "Rocky":
                moviePanel1.decrementNumOfTicket(ticketsSold);
                break;
            case "The Goonies":
                moviePanel2.decrementNumOfTicket(ticketsSold);
                break;
            case "Forrest Gump":
                moviePanel3.decrementNumOfTicket(ticketsSold);
                break;

        }
    }

    public static void updateFinalPanel(TotalPanel totalPanel) {
        frame3.getContentPane().remove(loadingPanel);
        frame3.add(totalPanel, BorderLayout.SOUTH);
        frame3.pack();
    }

    public static void sleepOneSecond() {
        try {
            int timeToWait = new Random().nextInt(1080) + 1;
            Thread.sleep(timeToWait);
        } catch (InterruptedException ex) {
            Logger.getLogger(MovieTheater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
