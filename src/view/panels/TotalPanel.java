package view.panels;

public class TotalPanel extends javax.swing.JPanel {

    public TotalPanel() {
        initComponents();
    }
    
    public void setTotal(double total){
        String totalTxt = String.valueOf(total);
        totalLabel.setText(totalTxt);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTitle = new javax.swing.JLabel();
        movieTitle = new javax.swing.JLabel();
        movieTickets1 = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();

        panelTitle.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        panelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelTitle.setText("Final Results");

        movieTitle.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        movieTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movieTitle.setText("Amount");

        movieTickets1.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        movieTickets1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movieTickets1.setText("R$");

        totalLabel.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N
        totalLabel.setText("0,00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
            .addComponent(movieTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(movieTickets1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(panelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(movieTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(movieTickets1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel movieTickets1;
    private javax.swing.JLabel movieTitle;
    private javax.swing.JLabel panelTitle;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
