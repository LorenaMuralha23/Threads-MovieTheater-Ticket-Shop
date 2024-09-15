package view.panels;

public class Movie1Panel extends javax.swing.JPanel {

    int totalOfTickets;

    public Movie1Panel(String movieName, int totalOfTickets) {
        initComponents();
        this.totalOfTickets = totalOfTickets;
        movieTitle.setText(movieName);
        String ticketsTxt = String.valueOf(this.totalOfTickets);
        movieTickets.setText(ticketsTxt);
    }

    public void decrementNumOfTicket(int ticketsSold) {
        if (totalOfTickets > 0) {
            totalOfTickets -= ticketsSold;
        }

        String totalTxt = String.valueOf(totalOfTickets);
        movieTickets.setText(totalTxt);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitle = new javax.swing.JLabel();
        movieTickets = new javax.swing.JLabel();
        movieTitle = new javax.swing.JLabel();

        panelTitle.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        panelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelTitle.setText("Avaliable tickets for");

        movieTickets.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        movieTickets.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movieTickets.setText("0");

        movieTitle.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        movieTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movieTitle.setText("Movie Name 1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
            .addComponent(movieTickets, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(movieTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(panelTitle)
                .addGap(18, 18, 18)
                .addComponent(movieTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(movieTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel movieTickets;
    private javax.swing.JLabel movieTitle;
    private javax.swing.JLabel panelTitle;
    // End of variables declaration//GEN-END:variables
}
