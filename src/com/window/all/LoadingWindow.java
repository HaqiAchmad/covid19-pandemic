package com.window.all;

import com.database.Account;
import com.database.Database;
import com.media.gambar.Gambar;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-20
 * @version 1.0
 */
public class LoadingWindow extends javax.swing.JFrame {


    private final Database db = new Database();
    private Account acc;
    private int value = 0, delay = 50;
    
    public LoadingWindow() {
       initComponents();
       
       this.setIconImage(Gambar.getWindowIcon());
       this.setLocationRelativeTo(null);
       
        System.out.println("\n\n---------> COVID-19 PANDEMIC");
        System.out.println("---------> Covid-19 Data Tracker Application");
        System.out.println("---------> Copyright © 2020. Achmad Baihaqi.");
        System.out.println("---------> All Rights Reserved.");
        System.out.println("-->\n-->\n-->\n-->\n\n\n");
       
       this.loading();
    }

    private void loading(){

        new Thread(new Runnable(){
            
            @Override
            public void run(){
                try{
                    while(value < 100){
                        if(value >= 0 && value < 15){
                            lblInfo.setText("  Memulai Aplikasi...");
                            delay = 80;
                        }else if(value >= 15 && value < 30){
                            lblInfo.setText("  Mengecek Database...");
                            delay = 40;
                        }else if(value >= 30 && value < 45){
                            lblInfo.setText("  Menghubungkan ke Database...");
                            db.startConnection();
                            value = 45;
                            delay = 60;
                        }else if(value >= 45 && value < 60){
                            lblInfo.setText("  Memulihkan Database...");
                            db.restoreDatabase();
                            value = 60;
                            delay = 30;
                        }else if(value >= 60 && value < 70){
                            lblInfo.setText("  Mengecek Akun...");
                            acc = new Account();
                            value = 70;
                            delay = 45;
                        }else if(value >= 70 && value < 80){
                            lblInfo.setText("  Memulai Aplikasi...");
                            delay = 58;
                        }else if(value >= 80 && value < 100){
                            lblInfo.setText("  Membuka Covid-19 Pandemic...");
                            delay = 80;
                        }
                        System.out.println("LOADING COMPLETE : " + value + "/100%");
                        value++;
                        load.setValue(value);
                        lblValueLoad.setText("" + value + " %  ");
                        Thread.sleep(delay);
                    }
                    
                     if(acc.isLogin()){
                        dispose();
                        java.awt.EventQueue.invokeLater(new Runnable(){

                            @Override
                            public void run(){
                                new com.window.all.Beranda().setVisible(true);
                            }
                        });
                    }else{
                        dispose();
                        java.awt.EventQueue.invokeLater(new Runnable(){

                            @Override
                            public void run(){
                                new com.window.all.SignIn().setVisible(true);
                            }
                        });
                    }
                }catch(InterruptedException iex){
                    System.out.println(iex.getMessage());
                }
                db.closeConnection();
                acc.closeConnection();
            }
        }).start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        load = new javax.swing.JProgressBar();
        lblApp = new javax.swing.JLabel();
        lblInfo = new javax.swing.JLabel();
        lblValueLoad = new javax.swing.JLabel();
        lblCovid = new javax.swing.JLabel();
        lblIconVirus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        load.setBackground(new java.awt.Color(255, 255, 255));
        load.setForeground(new java.awt.Color(8, 0, 255));

        lblApp.setFont(new java.awt.Font("Euphemia", 1, 28)); // NOI18N
        lblApp.setForeground(new java.awt.Color(42, 132, 227));
        lblApp.setText(" Covid-19 Pandemic");
        lblApp.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblInfo.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblInfo.setText(" Information");

        lblValueLoad.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblValueLoad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValueLoad.setText("0 % ");

        lblCovid.setFont(new java.awt.Font("Euphemia", 1, 14)); // NOI18N
        lblCovid.setForeground(new java.awt.Color(217, 26, 26));
        lblCovid.setText("  Coronavirus Data Tracker Application");
        lblCovid.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblIconVirus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconVirus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-loadingwindow-virus.png"))); // NOI18N

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(load, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblApp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCovid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 311, Short.MAX_VALUE)
                .addComponent(lblIconVirus, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblValueLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIconVirus, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblApp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCovid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfo)
                    .addComponent(lblValueLoad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(load, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoadingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoadingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoadingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadingWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoadingWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblApp;
    private javax.swing.JLabel lblCovid;
    private javax.swing.JLabel lblIconVirus;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblValueLoad;
    private javax.swing.JProgressBar load;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
