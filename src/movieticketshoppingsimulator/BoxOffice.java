package movieticketshoppingsimulator;

import java.awt.BorderLayout;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import movieticketshoppingsimulator.entities.Client;
import movieticketshoppingsimulator.entities.MovieTheater;
import movieticketshoppingsimulator.entities.SalesChannel;
import view.Frame1;
import view.Frame2;
import view.panels.ClientsPanel;
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
    public static TotalPanel totalPanel;

    public static void main(String[] args) {
        
        cyclicBarrier = new CyclicBarrier(3, BoxOffice.movieTheater);

        salesChannel1 = new SalesChannel("Esposa de Mentirinha", 15, 5);
        salesChannel2 = new SalesChannel("Shrek 2", 20, 5);
        salesChannel3 = new SalesChannel("Batman - O Cavaleiro das Trevas", 10, 8);
        
        startWindow();
        
        movieTheater.addSalesChannel(salesChannel1.getMovieName(), salesChannel1);
        movieTheater.addSalesChannel(salesChannel2.getMovieName(), salesChannel2);
        movieTheater.addSalesChannel(salesChannel3.getMovieName(), salesChannel3);

        executor.execute(salesChannel1);
        executor.execute(salesChannel2);
        executor.execute(salesChannel3);

        for (int i = 0; i < 5; i++) {

            String movieName = "";
            int movieNameDraw = new Random().nextInt(3) + 1;
            sleepOneSecond();
            switch (movieNameDraw) {
                case 1:
                    movieName = "Esposa de Mentirinha";
                    break;
                case 2:
                    movieName = "Shrek 2";
                    break;
                case 3:
                    movieName = "Batman - O Cavaleiro das Trevas";
                    break;
                default:
                    throw new AssertionError();
            }

            executor.submit(new Client(movieTheater, movieName));
            clientsPanel.incrementNumber();
        }
    }

    public static void startWindow() {
        int x1 = 100;
        int movieX1 = 600;
        int movieX2 = 1100;
        int movieX3 = 100;
        int x4 = 600;
        int y = 200;
        int y2 = 600;

        // Reutilizando os frames já declarados
        frame1.setLayout(new BorderLayout());
        movieFrame1.setLayout(new BorderLayout());
        movieFrame2.setLayout(new BorderLayout());
        movieFrame3.setLayout(new BorderLayout());
        frame3.setLayout(new BorderLayout());

        // Criando os painéis
        clientsPanel = new ClientsPanel();
        moviePanel1 = new Movie1Panel(salesChannel1.getMovieName(), salesChannel1.getNumOfTickets());
        moviePanel2 = new Movie1Panel(salesChannel2.getMovieName(), salesChannel2.getNumOfTickets());
        moviePanel3 = new Movie1Panel(salesChannel3.getMovieName(),salesChannel3.getNumOfTickets());
        totalPanel = new TotalPanel();

        // Adicionando os painéis aos frames
        
        frame1.add(clientsPanel, BorderLayout.CENTER); // Adicionando painel de clientes
        movieFrame1.add(moviePanel1, BorderLayout.CENTER); 
        movieFrame2.add(moviePanel2, BorderLayout.CENTER); 
        movieFrame3.add(moviePanel3, BorderLayout.CENTER); 
        frame3.add(totalPanel, BorderLayout.SOUTH);    // Adicionando painel total
          // Adicionando painel de filmes

        // Configurando as localizações das janelas
        frame1.setLocation(x1, y);    // Posiciona frame1
        movieFrame1.setLocation(movieX1, y);    // Posiciona frame1
        movieFrame2.setLocation(movieX2, y);    // Posiciona frame1
        movieFrame3.setLocation(movieX3, y2);    // Posiciona frame1
        frame3.setLocation(x4, y2);   // Posiciona frame2

        // Ajusta o tamanho das janelas com base nos componentes
        frame1.pack();
        movieFrame1.pack();
        movieFrame2.pack();
        movieFrame3.pack();
        frame3.pack();

        // Tornando as janelas visíveis
        frame1.setVisible(true);
        movieFrame1.setVisible(true);
        movieFrame2.setVisible(true);
        movieFrame3.setVisible(true);
        frame3.setVisible(true);
    }
    
    public static void updateTicketNumber(String movieName, int ticketsSold){
        switch (movieName) {
            case "Esposa de Mentirinha":
                moviePanel1.decrementNumOfTicket(ticketsSold);
                break;
            case "Shrek 2":
                moviePanel2.decrementNumOfTicket(ticketsSold);
                break;
            case "Batman - O Cavaleiro das Trevas":
                moviePanel3.decrementNumOfTicket(ticketsSold);
                break;
                
        }
    }    
    
    public static void sleepOneSecond(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(MovieTheater.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
