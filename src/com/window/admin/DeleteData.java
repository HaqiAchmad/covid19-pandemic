package com.window.admin;

import com.database.Account;
import com.database.CovidCases;
import com.media.audio.Audio;
import com.media.gambar.Gambar;

import java.awt.Cursor;
import javax.swing.JOptionPane;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-12-06
 */
public class DeleteData extends javax.swing.JFrame {
 
    private int x, y;
    
    private int tabel = 0;
    
    public static final int UPDATE_USER = 1, UPDATE_DUNIA = 2, UPDATE_INDO = 3;
    
    private String data;
    
    public DeleteData(final int tabel, final String data) {
        initComponents();
        
        this.tabel = tabel;
        this.data = data;
        
        this.setIconImage(Gambar.getWindowIcon());
        this.setLocationRelativeTo(null);
        this.btnHapus.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.lblData.setText("<html><p style=\"color:black;\">'<span style=\"color:rgb(222,34,34);\">"+data+"</span>'</p></html>");
        
        // mengatur teks pada JLabel lblTop berdasarkan tabel yang dipilih
        switch(tabel){
            case UPDATE_USER:
                this.lblTop.setText("Apakah Anda Yakin Ingin Menghapus Akun");
            break;
            case UPDATE_DUNIA:
                this.lblTop.setText("Apakah Anda Yakin Ingin Menghapus Negara");
            break;
            case UPDATE_INDO:
                this.lblTop.setText("Apakah Anda Yakin Ingin Menghapus Provinsi");
            break;
        }
    }
    
    public DeleteData() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        this.btnHapus.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
    }

    /**
     * Method ini digunakan untuk menghapus data berdasarkan tabel yang dipilih.
     * 
     * 
     * @return 
     */
    private boolean deleteData(){
        
        // digunakan untuk mengecek apakah data berhasil dihapus atau tidak
        boolean isDelete;
        
        // menghapus data berdasarkan tabel yang dipilih
        switch(tabel){
            // jika tabel users yang dipilih
            case UPDATE_USER:
                Account deleteAcc = new Account();
                
                    // mengecek apakah akun yg akan dihapus sedang digunakan untuk login atau tidak
                    if(deleteAcc.getActivedUser().equalsIgnoreCase(data)){
                        Audio.play(Audio.SOUND_ERROR);
                        JOptionPane.showMessageDialog(null, "Gagal menghapus data karena anda sedang login dengan akun ini!", "Error", JOptionPane.ERROR_MESSAGE);
                    // mengecek apakah akun yg dihapus adalah akun saya atau bukan
                    }else if(data.equalsIgnoreCase("baihaqi") || data.equalsIgnoreCase("hakiahmad756@gmail.com")){
                        Audio.play(Audio.SOUND_ERROR);
                        JOptionPane.showMessageDialog(null, "Akun dari 'baihaqi' tidak bisa dihapus\nKarena akun tersebut adalah akun milik Developer aplikasi ini!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        // menghapus data
                        isDelete = deleteAcc.deleteAccount(data);
                        // mengecek apakah data sudah berhasil dihapus atau tidak
                        if(isDelete){
                            return true;
                        }                        
                    }
                deleteAcc.closeConnection(); // menutup koneksi
            break;
            // jika tabel kasuscovid_dunia yang dipilih
            case UPDATE_DUNIA: 
                CovidCases dataDunia = new CovidCases(CovidCases.KASUS_DUNIA);
                // menghapus data
                isDelete = dataDunia.deleteData(data);
                    // mengecek apakah data sudah berhasil dihapus atau tidak
                    if(isDelete){
                        return true;
                    }
                dataDunia.closeConnection(); // menutup koneksi
            break;
            // jika tabel kasuscovid_indo yang dipilih
            case UPDATE_INDO:
                CovidCases dataIndo = new CovidCases(CovidCases.KASUS_INDO);
                // menghapus data
                isDelete = dataIndo.deleteData(data);
                    // mengecek apakah data sudah dihapus atau belum
                    if(isDelete){
                        return true;
                    }
                dataIndo.closeConnection(); // menutup koneksi
            break;
        }
        
        return false;
    }

    /**
     * Digunakan untuk menutup window
     */
    private void kembali(){
        java.awt.EventQueue.invokeLater(new Runnable(){

            @Override
            public void run(){
                switch(tabel){
                    case UPDATE_USER:
                        new UpdateUser().setVisible(true);
                    break;
                    case UPDATE_DUNIA:
                        new UpdateCovidDunia().setVisible(true);
                    break;
                    case UPDATE_INDO:
                        new UpdateCovidIndo().setVisible(true);
                    break;
                }
            }
        });
        dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlTop = new javax.swing.JPanel();
        lblClose = new javax.swing.JLabel();
        pnlBottom = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        lblTop = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblBottom = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlMainMouseDragged(evt);
            }
        });
        pnlMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlMainMousePressed(evt);
            }
        });

        pnlTop.setBackground(new java.awt.Color(151, 168, 179));

        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-close-black.png"))); // NOI18N
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCloseMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTopLayout.createSequentialGroup()
                .addContainerGap(363, Short.MAX_VALUE)
                .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClose, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(151, 168, 179));

        btnBatal.setBackground(new java.awt.Color(209, 217, 234));
        btnBatal.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(209, 217, 234));
        btnHapus.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBatal)
                    .addComponent(btnHapus))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        lblTop.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblTop.setForeground(new java.awt.Color(20, 80, 227));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Apakah Anda Yakin Ingin Menghapus");
        lblTop.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblData.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        lblData.setForeground(new java.awt.Color(222, 34, 34));
        lblData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblData.setText("Indonesia");

        lblBottom.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblBottom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBottom.setText("Dari Database?");
        lblBottom.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblData, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(lblBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBottom)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(pnlBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCloseMouseClicked

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_ENTERED));
    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_BLACK));
    }//GEN-LAST:event_lblCloseMouseExited

    private void pnlMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlMainMousePressed

    private void pnlMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMouseDragged
        int xx = evt.getXOnScreen(),
            yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_pnlMainMouseDragged

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // merubah cursor ke loading cursor
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        boolean isDelete = this.deleteData();
        // mengecek apakah data berhasil dihapus atau tidak, jika data behasil dihapus maka window akan diclose
        if(isDelete){
            Audio.play(Audio.SOUND_INFO);
            JOptionPane.showMessageDialog(null, "'" +data+"' berhasil dihapus dari Database!", "Info", JOptionPane.INFORMATION_MESSAGE);
            this.kembali();
        }
        
        // mereset cursor
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.kembali();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        
        System.out.println("Menutup Window UpdateCovidDunia");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        
        System.out.println("-->     APLIKASI DITUTUP");
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(DeleteData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new DeleteData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JLabel lblBottom;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblTop;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTop;
    // End of variables declaration//GEN-END:variables
}
