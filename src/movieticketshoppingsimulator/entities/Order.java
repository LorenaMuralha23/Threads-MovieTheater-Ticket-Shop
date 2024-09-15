package movieticketshoppingsimulator.entities;

public class Order {

    private int clientId;
    private int numOfTicketsToBuy;
    

    public Order(int clientId, int numOfTicketsToBuy) {
        this.clientId = clientId;
        this.numOfTicketsToBuy = numOfTicketsToBuy;
    }

    public Order() {
    }

    
    
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getNumOfTicketsToBuy() {
        return numOfTicketsToBuy;
    }

    public void setNumOfTicketsToBuy(int numOfTicketsToBuy) {
        this.numOfTicketsToBuy = numOfTicketsToBuy;
    }
    
    
    
}
