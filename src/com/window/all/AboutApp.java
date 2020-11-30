package com.window.all;

import com.database.Account;
import com.media.audio.Audio;
import com.media.gambar.Gambar;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;


/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-28
 */
public class AboutApp extends javax.swing.JFrame {

    private final Desktop desktop = Desktop.getDesktop();
    
    private final Account acc = new Account();
    
    /**
     * Digunakan untuk menyimpan data dari user seperti nama, tipe akun dan foto profile
     */
    private final String namaUser = acc.getDataAccount(acc.getActivedUser(), Account.NAMA_PANGGILAN), 
                         fotoProfile = acc.getDataAccount(acc.getActivedUser(), Account.FOTO_PROFILE),
                         tipeAkun = acc.getDataAccount(acc.getActivedUser(), Account.TYPE);
    /**
     * Digunakan untuk menyimpan nilai dari ratting yang diberikan user
     */
    private int ratting = 0;
    /**
     * Digunakan untuk menyimpan data dari total ratting
     */
    private int ratting1 = 3735, ratting2 = 9425, ratting3 = 6579, ratting4 = 32894, ratting5 = 74287, 
                totalRatting = (ratting1 + ratting2 + ratting3 + ratting4 + ratting5);
    /**
     * Digunakan untuk mendapatkan presentase dari total ratting yang akan digunakan untuk mengatur value dari progress bar
     */
    private int persenRatting1 = (int)getPesentaseRatting(ratting1), persenRatting2 = (int)getPesentaseRatting(ratting2),
                persenRatting3 = (int)getPesentaseRatting(ratting3), persenRatting4 = (int)getPesentaseRatting(ratting4),
                persenRatting5 = (int)getPesentaseRatting(ratting5);

    private int x, y;

    public AboutApp() {
        initComponents();
        
        this.setIconImage(Gambar.getWindowIcon());
        this.setLocationRelativeTo(null);
        this.btnTentangApp.setBackground(new Color(22,108,190));
        this.btnTentangApp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnKirimRatting.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnInfoApp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        // menampilkan data dari ratting yang diberikan user
        this.proRatting5.setValue(persenRatting5);
        this.valRatting5.setText(String.format("%,d user memberi ratting 5", ratting5));
        this.proRatting4.setValue(persenRatting4);
        this.valRatting4.setText(String.format("%,d user memberi ratting 4", ratting4));
        this.proRatting3.setValue(persenRatting3);
        this.valRatting3.setText(String.format("%,d user memberi ratting 3", ratting3));
        this.proRatting2.setValue(persenRatting2);
        this.valRatting2.setText(String.format("%,d user memberi ratting 2", ratting2));
        this.proRatting1.setValue(persenRatting1);
        this.valRatting1.setText(String.format("%,d user memberi ratting 1", ratting1));
        
        
        if(tipeAkun.equalsIgnoreCase("User")){
            this.btnInfoApp.setText("");
            this.btnInfoApp.setEnabled(false);
        }
        
        if(namaUser.length() <= 10){
            this.lblNamaUser.setText("Hi, " + namaUser);
        }else if(namaUser.length() > 17){
            this.lblNamaUser.setText(namaUser.substring(0, 17) + "...");
        }else{
            this.lblNamaUser.setText(namaUser);
        }
        
        
        JButton[] btns = new JButton[]{this.btnBeranda, this.btnApaCovid, this.btnGejala, this.btnBahaya, this.btnPencegahan, this.btnPenanganan, this.btnCovidDunia, this.btnCovidIndo};
        for(JButton btn : btns){
            
            btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
            
            btn.addMouseListener(new java.awt.event.MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                    btn.setBackground(new java.awt.Color(19,94,174));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    btn.setBackground(new java.awt.Color(49,144,215));
                }
            });

        }
        
    }
    
    private int getTotalRatting(final int ratting){
        switch(ratting){
            case 1: return ratting1; 
            case 2: return ratting2;
            case 3: return ratting3;
            case 4: return ratting4;
            case 5: return ratting5;
            default: return -1;
        }
    }
    
    private double getPesentaseRatting(final int ratting){
        System.out.println(ratting / totalRatting * 100);
        return (double) ratting / totalRatting * 100;
    }
    
    private void setChooseRatting(final int ratting){
        this.ratting = ratting;
        
        switch(ratting){
            case 1:
                chooseRatting1.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting2.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                chooseRatting3.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                chooseRatting4.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                chooseRatting5.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                break;
            case 2:
                chooseRatting1.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting2.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting3.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                chooseRatting4.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                chooseRatting5.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                break;
            case 3: 
                chooseRatting1.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting2.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting3.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting4.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                chooseRatting5.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                break;
            case 4: 
                chooseRatting1.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting2.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting3.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting4.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting5.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                break;
            case 5: 
                chooseRatting1.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting2.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting3.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting4.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                chooseRatting5.setIcon(Gambar.getIcon("ic-aboutapp-star-filled.png"));
                break;
            default:
                chooseRatting1.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                chooseRatting2.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                chooseRatting3.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                chooseRatting4.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                chooseRatting5.setIcon(Gambar.getIcon("ic-aboutapp-star.png"));
                break;
        }
    }

    private void updateRatting(JProgressBar pro, final int ratting){
            new Thread(new Runnable(){
                
                @Override
                public void run(){
                    for(int i = 0; i <= (int)getPesentaseRatting(getTotalRatting(5)); i++){
                        pro.setValue(i);
                        try {
                            Thread.sleep(80);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(AboutApp.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }                    
                }                
            }).start();
            
    }
    
    /**
     * Digunakan untuk mengecek apakah user tersambung ke inernet atau tidak
     * 
     * @return user tersambung ke inernet atau tidak
     */
    private boolean isConnectInternet(){
        Socket s = new Socket();
        InetSocketAddress inet = new InetSocketAddress("www.google.com", 80);
        
            try{
                s.connect(inet, 1000);
                return true;
            }catch(Exception ex){
                return false;
            }finally{
                try{
                    s.close();
                }catch(Exception ex){ 
                    Audio.play(Audio.SOUND_ERROR);
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengecek koneksi Internet!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }
    
    private void openLink(final String link){
        
        if(isConnectInternet()){
            try {
                desktop.browse(new URI(link));
            } catch (IOException | URISyntaxException ex) {
                Audio.play(Audio.SOUND_ERROR); 
                JOptionPane.showMessageDialog(null, "Gagal membuka link dari " + link, "Error", JOptionPane.WARNING_MESSAGE);
            }            
        }else{
            Audio.play(Audio.SOUND_INFO);
            JOptionPane.showMessageDialog(null, "Internet lu mati anjir!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
        
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlLeft = new javax.swing.JPanel();
        btnBeranda = new javax.swing.JButton();
        pnlAccount = new javax.swing.JPanel();
        lblPhotoProfile = new javax.swing.JLabel();
        lblNamaUser = new javax.swing.JLabel();
        lblEditProfile = new javax.swing.JLabel();
        btnApaCovid = new javax.swing.JButton();
        btnGejala = new javax.swing.JButton();
        btnBahaya = new javax.swing.JButton();
        btnPencegahan = new javax.swing.JButton();
        btnCovidDunia = new javax.swing.JButton();
        pnlLeftBottom = new javax.swing.JPanel();
        btnInfoApp = new javax.swing.JButton();
        btnPenanganan = new javax.swing.JButton();
        btnCovidIndo = new javax.swing.JButton();
        btnTentangApp = new javax.swing.JButton();
        pnlRatting = new javax.swing.JPanel();
        lblRatting = new javax.swing.JLabel();
        proRatting5 = new javax.swing.JProgressBar();
        proRatting4 = new javax.swing.JProgressBar();
        proRatting3 = new javax.swing.JProgressBar();
        proRatting2 = new javax.swing.JProgressBar();
        proRatting1 = new javax.swing.JProgressBar();
        valRatting5 = new javax.swing.JLabel();
        valRatting4 = new javax.swing.JLabel();
        valRatting3 = new javax.swing.JLabel();
        valRatting2 = new javax.swing.JLabel();
        valRatting1 = new javax.swing.JLabel();
        lblTotalRatting5 = new javax.swing.JLabel();
        lblTotalRatting4 = new javax.swing.JLabel();
        lblTotalRatting3 = new javax.swing.JLabel();
        lblTotalRatting2 = new javax.swing.JLabel();
        lblTotalRatting1 = new javax.swing.JLabel();
        chooseRatting1 = new javax.swing.JLabel();
        btnKirimRatting = new javax.swing.JButton();
        lineRattingApp = new javax.swing.JSeparator();
        chooseRatting2 = new javax.swing.JLabel();
        chooseRatting3 = new javax.swing.JLabel();
        chooseRatting4 = new javax.swing.JLabel();
        chooseRatting5 = new javax.swing.JLabel();
        lblKetApp = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        pnlKontak = new javax.swing.JPanel();
        lblKontak = new javax.swing.JLabel();
        lblIconGmail = new javax.swing.JLabel();
        lblGmail = new javax.swing.JLabel();
        lblIconWA = new javax.swing.JLabel();
        lblWA = new javax.swing.JLabel();
        lblIconIG = new javax.swing.JLabel();
        lblIG = new javax.swing.JLabel();
        lblMinimaze = new javax.swing.JLabel();
        lblTop = new javax.swing.JLabel();
        lblApp = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();
        lblVersiApp = new javax.swing.JLabel();
        valVersi = new javax.swing.JLabel();
        lblUkuranApp = new javax.swing.JLabel();
        valUkuran = new javax.swing.JLabel();
        lblTotalDownload = new javax.swing.JLabel();
        valTotalDonwload = new javax.swing.JLabel();
        lblPengguna = new javax.swing.JLabel();
        valPengguna = new javax.swing.JLabel();
        lblNamaApp = new javax.swing.JLabel();
        valNamaApp = new javax.swing.JLabel();
        lblDirilis = new javax.swing.JLabel();
        valDirilis = new javax.swing.JLabel();
        lblDiupdate = new javax.swing.JLabel();
        valDiupdate = new javax.swing.JLabel();
        lblBahasa = new javax.swing.JLabel();
        valBahasa = new javax.swing.JLabel();
        lblDatabase = new javax.swing.JLabel();
        valDatabase = new javax.swing.JLabel();
        lblDeveloper = new javax.swing.JLabel();
        valDeveloper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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

        pnlLeft.setBackground(new java.awt.Color(49, 144, 215));

        btnBeranda.setBackground(new java.awt.Color(49, 144, 215));
        btnBeranda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBeranda.setForeground(new java.awt.Color(255, 255, 255));
        btnBeranda.setText("Beranda");
        btnBeranda.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBerandaActionPerformed(evt);
            }
        });

        pnlAccount.setBackground(new java.awt.Color(33, 114, 175));

        lblPhotoProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhotoProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-profile.png"))); // NOI18N
        lblPhotoProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblPhotoProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPhotoProfileMouseClicked(evt);
            }
        });

        lblNamaUser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblNamaUser.setForeground(new java.awt.Color(255, 255, 255));
        lblNamaUser.setText("Achmad Baihaqi");

        lblEditProfile.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblEditProfile.setForeground(new java.awt.Color(255, 255, 255));
        lblEditProfile.setText("Informasi Akun");
        lblEditProfile.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblEditProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditProfileMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEditProfileMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEditProfileMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlAccountLayout = new javax.swing.GroupLayout(pnlAccount);
        pnlAccount.setLayout(pnlAccountLayout);
        pnlAccountLayout.setHorizontalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addComponent(lblPhotoProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEditProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaUser, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );
        pnlAccountLayout.setVerticalGroup(
            pnlAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPhotoProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNamaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEditProfile)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btnApaCovid.setBackground(new java.awt.Color(49, 144, 215));
        btnApaCovid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnApaCovid.setForeground(new java.awt.Color(255, 255, 255));
        btnApaCovid.setText("Apa itu Covid-19");
        btnApaCovid.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnApaCovid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApaCovidActionPerformed(evt);
            }
        });

        btnGejala.setBackground(new java.awt.Color(49, 144, 215));
        btnGejala.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGejala.setForeground(new java.awt.Color(255, 255, 255));
        btnGejala.setText("Gejala Covid-19");
        btnGejala.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnGejala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGejalaActionPerformed(evt);
            }
        });

        btnBahaya.setBackground(new java.awt.Color(49, 144, 215));
        btnBahaya.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBahaya.setForeground(new java.awt.Color(255, 255, 255));
        btnBahaya.setText("Bahaya Covid-19");
        btnBahaya.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBahaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBahayaActionPerformed(evt);
            }
        });

        btnPencegahan.setBackground(new java.awt.Color(49, 144, 215));
        btnPencegahan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPencegahan.setForeground(new java.awt.Color(255, 255, 255));
        btnPencegahan.setText("Pencegahan Covid-19");
        btnPencegahan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPencegahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPencegahanActionPerformed(evt);
            }
        });

        btnCovidDunia.setBackground(new java.awt.Color(49, 144, 215));
        btnCovidDunia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCovidDunia.setForeground(new java.awt.Color(255, 255, 255));
        btnCovidDunia.setText("Covid-19 di Dunia");
        btnCovidDunia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCovidDunia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCovidDuniaActionPerformed(evt);
            }
        });

        pnlLeftBottom.setBackground(new java.awt.Color(33, 114, 175));

        btnInfoApp.setBackground(new java.awt.Color(33, 114, 175));
        btnInfoApp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInfoApp.setForeground(new java.awt.Color(255, 255, 255));
        btnInfoApp.setText("Lihat Data Aplikasi");
        btnInfoApp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInfoApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInfoAppMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInfoAppMouseExited(evt);
            }
        });
        btnInfoApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoAppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftBottomLayout = new javax.swing.GroupLayout(pnlLeftBottom);
        pnlLeftBottom.setLayout(pnlLeftBottomLayout);
        pnlLeftBottomLayout.setHorizontalGroup(
            pnlLeftBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnInfoApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLeftBottomLayout.setVerticalGroup(
            pnlLeftBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLeftBottomLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnInfoApp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        btnPenanganan.setBackground(new java.awt.Color(49, 144, 215));
        btnPenanganan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPenanganan.setForeground(new java.awt.Color(255, 255, 255));
        btnPenanganan.setText("Penanganan Covid-19");
        btnPenanganan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnPenanganan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenangananActionPerformed(evt);
            }
        });

        btnCovidIndo.setBackground(new java.awt.Color(49, 144, 215));
        btnCovidIndo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCovidIndo.setForeground(new java.awt.Color(255, 255, 255));
        btnCovidIndo.setText("Covid-19 di Indonesia");
        btnCovidIndo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnCovidIndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCovidIndoActionPerformed(evt);
            }
        });

        btnTentangApp.setBackground(new java.awt.Color(49, 144, 215));
        btnTentangApp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTentangApp.setForeground(new java.awt.Color(255, 255, 255));
        btnTentangApp.setText("Tentang Aplikasi");
        btnTentangApp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnTentangApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTentangAppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnBeranda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnApaCovid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnGejala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBahaya, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPencegahan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCovidDunia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlLeftBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPenanganan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCovidIndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTentangApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLeftLayout.createSequentialGroup()
                .addComponent(pnlAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnBeranda, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnApaCovid, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGejala, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBahaya, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPencegahan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPenanganan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCovidDunia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCovidIndo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTentangApp, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(pnlLeftBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlRatting.setBackground(new java.awt.Color(255, 255, 255));
        pnlRatting.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRatting.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRatting.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRatting.setText("Ratting Aplikasi");
        lblRatting.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        proRatting5.setBackground(new java.awt.Color(255, 255, 255));
        proRatting5.setForeground(new java.awt.Color(51, 255, 0));
        proRatting5.setValue(63);

        proRatting4.setBackground(new java.awt.Color(255, 255, 255));
        proRatting4.setForeground(new java.awt.Color(51, 255, 0));
        proRatting4.setValue(16);

        proRatting3.setBackground(new java.awt.Color(255, 255, 255));
        proRatting3.setForeground(new java.awt.Color(51, 255, 0));
        proRatting3.setValue(9);

        proRatting2.setBackground(new java.awt.Color(255, 255, 255));
        proRatting2.setForeground(new java.awt.Color(51, 255, 0));
        proRatting2.setValue(15);

        proRatting1.setBackground(new java.awt.Color(255, 255, 255));
        proRatting1.setForeground(new java.awt.Color(51, 255, 0));
        proRatting1.setValue(7);

        valRatting5.setText("56.657 user memberi ratting 5");

        valRatting4.setText("15.677 user memberi ratting 4");

        valRatting3.setText("6.565 user memberi ratting 3");

        valRatting2.setText("12.345 user memberi ratting 2");

        valRatting1.setText("4.455 user memberi ratting 1");

        lblTotalRatting5.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblTotalRatting5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalRatting5.setText("5");
        lblTotalRatting5.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblTotalRatting4.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblTotalRatting4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalRatting4.setText("4");
        lblTotalRatting4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblTotalRatting3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblTotalRatting3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalRatting3.setText("3");
        lblTotalRatting3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblTotalRatting2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblTotalRatting2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalRatting2.setText("2");
        lblTotalRatting2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblTotalRatting1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        lblTotalRatting1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalRatting1.setText("1");
        lblTotalRatting1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        chooseRatting1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chooseRatting1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseRatting1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-aboutapp-star.png"))); // NOI18N
        chooseRatting1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseRatting1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chooseRatting1MouseEntered(evt);
            }
        });

        btnKirimRatting.setText("Kirim");
        btnKirimRatting.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnKirimRatting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKirimRattingMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKirimRattingMouseExited(evt);
            }
        });
        btnKirimRatting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimRattingActionPerformed(evt);
            }
        });

        lineRattingApp.setBackground(new java.awt.Color(0, 0, 0));

        chooseRatting2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chooseRatting2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseRatting2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-aboutapp-star.png"))); // NOI18N
        chooseRatting2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseRatting2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chooseRatting2MouseEntered(evt);
            }
        });

        chooseRatting3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chooseRatting3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseRatting3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-aboutapp-star.png"))); // NOI18N
        chooseRatting3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseRatting3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chooseRatting3MouseEntered(evt);
            }
        });

        chooseRatting4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chooseRatting4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseRatting4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-aboutapp-star.png"))); // NOI18N
        chooseRatting4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseRatting4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chooseRatting4MouseEntered(evt);
            }
        });

        chooseRatting5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        chooseRatting5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseRatting5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-aboutapp-star.png"))); // NOI18N
        chooseRatting5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseRatting5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                chooseRatting5MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnlRattingLayout = new javax.swing.GroupLayout(pnlRatting);
        pnlRatting.setLayout(pnlRattingLayout);
        pnlRattingLayout.setHorizontalGroup(
            pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRatting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRattingLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnKirimRatting, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(pnlRattingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRattingLayout.createSequentialGroup()
                        .addGroup(pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotalRatting3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalRatting2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalRatting1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalRatting4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalRatting5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(proRatting5, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(proRatting4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(proRatting3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(proRatting2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(proRatting1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valRatting5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valRatting4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valRatting3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valRatting2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valRatting1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlRattingLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lineRattingApp, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlRattingLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(chooseRatting1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chooseRatting2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chooseRatting3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chooseRatting4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(chooseRatting5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlRattingLayout.setVerticalGroup(
            pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRattingLayout.createSequentialGroup()
                .addComponent(lblRatting, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRattingLayout.createSequentialGroup()
                        .addComponent(proRatting5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(valRatting5))
                    .addComponent(lblTotalRatting5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlRattingLayout.createSequentialGroup()
                        .addComponent(proRatting4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valRatting4))
                    .addComponent(lblTotalRatting4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlRattingLayout.createSequentialGroup()
                        .addComponent(proRatting3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valRatting3))
                    .addComponent(lblTotalRatting3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlRattingLayout.createSequentialGroup()
                        .addComponent(proRatting2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(valRatting2))
                    .addComponent(lblTotalRatting2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlRattingLayout.createSequentialGroup()
                        .addComponent(proRatting1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(valRatting1))
                    .addComponent(lblTotalRatting1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(lineRattingApp, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRattingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chooseRatting4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooseRatting3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooseRatting1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(chooseRatting2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chooseRatting5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnKirimRatting, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addContainerGap())
        );

        lblKetApp.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        lblKetApp.setText("Aplikasi pemantauan Covid-19 di Dunia dan di Indonesia");

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

        pnlKontak.setBackground(new java.awt.Color(255, 255, 255));
        pnlKontak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblKontak.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblKontak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKontak.setText("Kontak Developer");
        lblKontak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblIconGmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconGmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-aboutapp-gmail.png"))); // NOI18N
        lblIconGmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconGmailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconGmailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconGmailMouseExited(evt);
            }
        });

        lblGmail.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblGmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGmail.setText("Pesan Gmail");
        lblGmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGmailMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblGmailMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblGmailMouseExited(evt);
            }
        });

        lblIconWA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconWA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-aboutapp-whatsapp.png"))); // NOI18N
        lblIconWA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconWAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconWAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconWAMouseExited(evt);
            }
        });

        lblWA.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblWA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWA.setText("Whatsapp");
        lblWA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblWAMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblWAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblWAMouseExited(evt);
            }
        });

        lblIconIG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconIG.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-aboutapp-instagram.png"))); // NOI18N
        lblIconIG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconIGMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIconIGMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIconIGMouseExited(evt);
            }
        });

        lblIG.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblIG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIG.setText("Instagram");
        lblIG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIGMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIGMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIGMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlKontakLayout = new javax.swing.GroupLayout(pnlKontak);
        pnlKontak.setLayout(pnlKontakLayout);
        pnlKontakLayout.setHorizontalGroup(
            pnlKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblKontak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlKontakLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIconGmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGmail, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconWA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblWA, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconIG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIG, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlKontakLayout.setVerticalGroup(
            pnlKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKontakLayout.createSequentialGroup()
                .addComponent(lblKontak, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIconWA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIconIG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIconGmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(pnlKontakLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGmail)
                    .addComponent(lblWA)
                    .addComponent(lblIG))
                .addGap(7, 7, 7))
        );

        lblMinimaze.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMinimaze.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-minimaze-black.png"))); // NOI18N
        lblMinimaze.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMinimazeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMinimazeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMinimazeMouseExited(evt);
            }
        });

        lblTop.setFont(new java.awt.Font("Ebrima", 1, 19)); // NOI18N
        lblTop.setText("Tentang Aplikasi Ini");

        lblApp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblApp.setText("App Covid-19 Pandemic");

        pnlInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInfo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfo.setText("Info Aplikasi");
        lblInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblVersiApp.setText("   Versi Aplikasi ");

        valVersi.setText(": 1.0");

        lblUkuranApp.setText("   Ukuran Aplikasi");

        valUkuran.setText(": 21 Mb");

        lblTotalDownload.setText("   Total Download");

        valTotalDonwload.setText(": 14.565.346 Download");

        lblPengguna.setText("   Pengguna saat ini");

        valPengguna.setText(": 75.656 Pengguna");

        lblNamaApp.setText("   Nama Aplikasi");

        valNamaApp.setText(": Covid-19 Pandemic");

        lblDirilis.setText("   Dirilis pada");

        valDirilis.setText(": 14 November 2020");

        lblDiupdate.setText("   Diupdate pada");

        valDiupdate.setText(": 1 Desember 2020");

        lblBahasa.setText("   Bahasa pemrograman");

        valBahasa.setText(": Java");

        lblDatabase.setText("   Database Aplikasi");

        valDatabase.setText(": MySQL");

        lblDeveloper.setText("   Developer");

        valDeveloper.setText(": Achmad Baihaqi");

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblDeveloper, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDatabase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBahasa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(lblDiupdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDirilis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNamaApp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPengguna, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotalDownload, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUkuranApp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVersiApp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valVersi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valUkuran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valTotalDonwload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valPengguna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valNamaApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDirilis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDiupdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valBahasa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDeveloper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaApp)
                    .addComponent(valNamaApp))
                .addGap(8, 8, 8)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVersiApp)
                    .addComponent(valVersi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUkuranApp)
                    .addComponent(valUkuran))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalDownload)
                    .addComponent(valTotalDonwload))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPengguna)
                    .addComponent(valPengguna))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDirilis)
                    .addComponent(valDirilis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiupdate)
                    .addComponent(valDiupdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBahasa)
                    .addComponent(valBahasa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatabase)
                    .addComponent(valDatabase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDeveloper)
                    .addComponent(valDeveloper))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pnlRatting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblKetApp, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(pnlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pnlKontak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 2, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblMinimaze, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMinimaze, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlKontak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(pnlRatting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblApp, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblKetApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void pnlMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlMainMousePressed

    private void pnlMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMouseDragged
        int xx = evt.getXOnScreen(),
            yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_pnlMainMouseDragged

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCloseMouseClicked

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_ENTERED));
    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_BLACK));
    }//GEN-LAST:event_lblCloseMouseExited

    private void lblMinimazeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseClicked
        this.setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimazeMouseClicked

    private void lblMinimazeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseEntered
        this.lblMinimaze.setIcon(Gambar.getIcon(Gambar.IC_MINIMAZE_ENTERED));
    }//GEN-LAST:event_lblMinimazeMouseEntered

    private void lblMinimazeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseExited
        this.lblMinimaze.setIcon(Gambar.getIcon(Gambar.IC_MINIMAZE_BLACK));
    }//GEN-LAST:event_lblMinimazeMouseExited

    private void btnBerandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBerandaActionPerformed
        System.out.println("Membuka Window Beranda");
        Beranda beranda = new Beranda();
        beranda.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                beranda.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnBerandaActionPerformed

    private void btnApaCovidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApaCovidActionPerformed
        System.out.println("Membuka Window ApaCovid");
        ApaCovid apaCovid = new ApaCovid();
        apaCovid.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                apaCovid.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnApaCovidActionPerformed

    private void btnGejalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGejalaActionPerformed
        System.out.println("Membuka Window GejalaCovid");
        GejalaCovid gejala = new GejalaCovid();
        gejala.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                gejala.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnGejalaActionPerformed

    private void btnBahayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBahayaActionPerformed
        System.out.println("Membuka Window BahayaCovid");
        BahayaCovid bahaya = new BahayaCovid();
        bahaya.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                bahaya.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnBahayaActionPerformed

    private void btnPencegahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPencegahanActionPerformed
        System.out.println("Membuka Window PencegahanCovid");
        PencegahanCovid pencegahan = new PencegahanCovid();
        pencegahan.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                pencegahan.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnPencegahanActionPerformed

    private void btnPenangananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenangananActionPerformed
        System.out.println("Membuka Window PenangananCovid");
        PenangananCovid penanganan = new PenangananCovid();
        penanganan.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                penanganan.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnPenangananActionPerformed

    private void btnCovidDuniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCovidDuniaActionPerformed
        System.out.println("Membuka Window KasusCovidDunia");
        KasusCovidDunia kasusDunia = new KasusCovidDunia();
        kasusDunia.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                kasusDunia.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnCovidDuniaActionPerformed

    private void btnCovidIndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCovidIndoActionPerformed
        System.out.println("Membuka Window KasusCovidIndo");
        KasusCovidIndo kasusIndo = new KasusCovidIndo();
        kasusIndo.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                kasusIndo.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnCovidIndoActionPerformed

    private void btnTentangAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTentangAppActionPerformed
        System.out.println("Membuka Window AboutApp");
        AboutApp tentang = new AboutApp();
        tentang.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                tentang.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnTentangAppActionPerformed

    private void btnInfoAppMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfoAppMouseEntered
        this.btnInfoApp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.btnInfoApp.setBackground(new Color(19,94,174));
    }//GEN-LAST:event_btnInfoAppMouseEntered

    private void btnInfoAppMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInfoAppMouseExited
        this.btnInfoApp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        this.btnInfoApp.setBackground(new Color(33,114,175));
    }//GEN-LAST:event_btnInfoAppMouseExited

    private void btnInfoAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoAppActionPerformed
        
    }//GEN-LAST:event_btnInfoAppActionPerformed

    private void lblPhotoProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPhotoProfileMouseClicked
        
    }//GEN-LAST:event_lblPhotoProfileMouseClicked

    private void lblEditProfileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditProfileMouseEntered
        this.lblEditProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.lblEditProfile.setText("<html><p style=\"text-decoration:underline;\">Informasi Akun</p></html>");
    }//GEN-LAST:event_lblEditProfileMouseEntered

    private void lblEditProfileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditProfileMouseExited
        this.lblEditProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        this.lblEditProfile.setText("<html><p style=\"text-decoration:none;\">Informasi Akun</p></html>");
    }//GEN-LAST:event_lblEditProfileMouseExited

    private void lblEditProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditProfileMouseClicked
        
    }//GEN-LAST:event_lblEditProfileMouseClicked

    private void btnKirimRattingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimRattingActionPerformed
        JLabel[] rattings = new JLabel[]{chooseRatting1, chooseRatting2, chooseRatting3, chooseRatting4, chooseRatting5};
        // mengecek apakah user sudah memberi ratting atau belum
        if(ratting <= 0){
            Audio.play(Audio.SOUND_INFO);
            JOptionPane.showMessageDialog(null, "Anda belum memilih ratting!", "Info", JOptionPane.INFORMATION_MESSAGE);
            for(JLabel choose : rattings){
                choose.setIcon(Gambar.getIcon("ic-aboutapp-star-error.png"));
            }
        }else{
            updateRatting(proRatting5, 1);
        }
    }//GEN-LAST:event_btnKirimRattingActionPerformed

    private void btnKirimRattingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKirimRattingMouseEntered
        // jika ratting belum dipilih maka icon pada button kirimRatting akan menampilkan icon berbentuk x 
        // tapi jika user sudah memilih ratting maka icon pada buton kirimRattin akan menampikna icn berbentuk ceklist
        if(ratting <= 0){
            this.btnKirimRatting.setIcon(Gambar.getIcon("ic-aboutapp-ratting-fail.png"));
        }else{
            this.btnKirimRatting.setIcon(Gambar.getIcon("ic-aboutapp-ratting-ok.png"));
        }
    }//GEN-LAST:event_btnKirimRattingMouseEntered

    private void btnKirimRattingMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKirimRattingMouseExited
        this.btnKirimRatting.setIcon(null);
    }//GEN-LAST:event_btnKirimRattingMouseExited

    private void chooseRatting1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseRatting1MouseClicked
        // jika ratting yang diklik user adalah 1 maka ratting akan direset ke 0
        if(ratting == 1){
            setChooseRatting(0); // mereset ratting
        }else{
            setChooseRatting(1);
        }
    }//GEN-LAST:event_chooseRatting1MouseClicked

    private void chooseRatting1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseRatting1MouseEntered
//        setChooseRatting(1);
    }//GEN-LAST:event_chooseRatting1MouseEntered

    private void chooseRatting2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseRatting2MouseClicked
        // jika ratting yang diklik user adalah 2 maka ratting akan direset ke 0
        if(ratting == 2){
            setChooseRatting(0); // mereset ratting
        }else{
            setChooseRatting(2);
        }
    }//GEN-LAST:event_chooseRatting2MouseClicked

    private void chooseRatting2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseRatting2MouseEntered
//        setChooseRatting(2);
    }//GEN-LAST:event_chooseRatting2MouseEntered

    private void chooseRatting3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseRatting3MouseClicked
        // jika ratting yang diklik user adalah 3 maka ratting akan direset ke 0
        if(ratting == 3){
            setChooseRatting(0); // mereset ratting
        }else{
            setChooseRatting(3);
        }
    }//GEN-LAST:event_chooseRatting3MouseClicked

    private void chooseRatting3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseRatting3MouseEntered
//        setChooseRatting(3);
    }//GEN-LAST:event_chooseRatting3MouseEntered

    private void chooseRatting4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseRatting4MouseClicked
        // jika ratting yang diklik user adalah 4 maka ratting akan direset ke 0
        if(ratting == 4){
            setChooseRatting(0); // mereset ratting
        }else{
            setChooseRatting(4);
        }
    }//GEN-LAST:event_chooseRatting4MouseClicked

    private void chooseRatting4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseRatting4MouseEntered
//        setChooseRatting(4);
    }//GEN-LAST:event_chooseRatting4MouseEntered

    private void chooseRatting5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseRatting5MouseClicked
        // jika ratting yang diklik user adalah 5 maka ratting akan direset ke 0
        if(ratting == 5){
            setChooseRatting(0); // mereset ratting
        }else{
            setChooseRatting(5);
        }
    }//GEN-LAST:event_chooseRatting5MouseClicked

    private void chooseRatting5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chooseRatting5MouseEntered
//        setChooseRatting(5);
    }//GEN-LAST:event_chooseRatting5MouseEntered

    private void lblIconGmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconGmailMouseClicked
        
    }//GEN-LAST:event_lblIconGmailMouseClicked

    private void lblIconGmailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconGmailMouseEntered
        this.lblIconGmail.setIcon(Gambar.getIcon("ic-aboutapp-gmail-entered.png"));
    }//GEN-LAST:event_lblIconGmailMouseEntered

    private void lblIconGmailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconGmailMouseExited
        this.lblIconGmail.setIcon(Gambar.getIcon("ic-aboutapp-gmail.png"));
    }//GEN-LAST:event_lblIconGmailMouseExited

    private void lblIconWAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconWAMouseClicked
        
    }//GEN-LAST:event_lblIconWAMouseClicked

    private void lblIconWAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconWAMouseEntered
        this.lblIconWA.setIcon(Gambar.getIcon("ic-aboutapp-whatsapp-entered.png"));
    }//GEN-LAST:event_lblIconWAMouseEntered

    private void lblIconWAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconWAMouseExited
        this.lblIconWA.setIcon(Gambar.getIcon("ic-aboutapp-whatsapp.png"));
    }//GEN-LAST:event_lblIconWAMouseExited

    private void lblIconIGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconIGMouseClicked
         
    }//GEN-LAST:event_lblIconIGMouseClicked

    private void lblIconIGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconIGMouseEntered
         this.lblIconIG.setIcon(Gambar.getIcon("ic-aboutapp-instagram-entered.png"));
    }//GEN-LAST:event_lblIconIGMouseEntered

    private void lblIconIGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconIGMouseExited
       this.lblIconIG.setIcon(Gambar.getIcon("ic-aboutapp-instagram.png"));
    }//GEN-LAST:event_lblIconIGMouseExited

    private void lblGmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGmailMouseClicked
        
    }//GEN-LAST:event_lblGmailMouseClicked

    private void lblGmailMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGmailMouseEntered
        
    }//GEN-LAST:event_lblGmailMouseEntered

    private void lblGmailMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGmailMouseExited
        
    }//GEN-LAST:event_lblGmailMouseExited

    private void lblWAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWAMouseClicked
        
    }//GEN-LAST:event_lblWAMouseClicked

    private void lblWAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWAMouseEntered
        
    }//GEN-LAST:event_lblWAMouseEntered

    private void lblWAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblWAMouseExited
        
    }//GEN-LAST:event_lblWAMouseExited

    private void lblIGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIGMouseClicked
        
    }//GEN-LAST:event_lblIGMouseClicked

    private void lblIGMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIGMouseEntered
        
    }//GEN-LAST:event_lblIGMouseEntered

    private void lblIGMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIGMouseExited
        
    }//GEN-LAST:event_lblIGMouseExited

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
            java.util.logging.Logger.getLogger(AboutApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AboutApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AboutApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AboutApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AboutApp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApaCovid;
    private javax.swing.JButton btnBahaya;
    private javax.swing.JButton btnBeranda;
    private javax.swing.JButton btnCovidDunia;
    private javax.swing.JButton btnCovidIndo;
    private javax.swing.JButton btnGejala;
    private javax.swing.JButton btnInfoApp;
    private javax.swing.JButton btnKirimRatting;
    private javax.swing.JButton btnPenanganan;
    private javax.swing.JButton btnPencegahan;
    private javax.swing.JButton btnTentangApp;
    private javax.swing.JLabel chooseRatting1;
    private javax.swing.JLabel chooseRatting2;
    private javax.swing.JLabel chooseRatting3;
    private javax.swing.JLabel chooseRatting4;
    private javax.swing.JLabel chooseRatting5;
    private javax.swing.JLabel lblApp;
    private javax.swing.JLabel lblBahasa;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDatabase;
    private javax.swing.JLabel lblDeveloper;
    private javax.swing.JLabel lblDirilis;
    private javax.swing.JLabel lblDiupdate;
    private javax.swing.JLabel lblEditProfile;
    private javax.swing.JLabel lblGmail;
    private javax.swing.JLabel lblIG;
    private javax.swing.JLabel lblIconGmail;
    private javax.swing.JLabel lblIconIG;
    private javax.swing.JLabel lblIconWA;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblKetApp;
    private javax.swing.JLabel lblKontak;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamaApp;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblPengguna;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblRatting;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblTotalDownload;
    private javax.swing.JLabel lblTotalRatting1;
    private javax.swing.JLabel lblTotalRatting2;
    private javax.swing.JLabel lblTotalRatting3;
    private javax.swing.JLabel lblTotalRatting4;
    private javax.swing.JLabel lblTotalRatting5;
    private javax.swing.JLabel lblUkuranApp;
    private javax.swing.JLabel lblVersiApp;
    private javax.swing.JLabel lblWA;
    private javax.swing.JSeparator lineRattingApp;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlKontak;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlRatting;
    private javax.swing.JProgressBar proRatting1;
    private javax.swing.JProgressBar proRatting2;
    private javax.swing.JProgressBar proRatting3;
    private javax.swing.JProgressBar proRatting4;
    private javax.swing.JProgressBar proRatting5;
    private javax.swing.JLabel valBahasa;
    private javax.swing.JLabel valDatabase;
    private javax.swing.JLabel valDeveloper;
    private javax.swing.JLabel valDirilis;
    private javax.swing.JLabel valDiupdate;
    private javax.swing.JLabel valNamaApp;
    private javax.swing.JLabel valPengguna;
    private javax.swing.JLabel valRatting1;
    private javax.swing.JLabel valRatting2;
    private javax.swing.JLabel valRatting3;
    private javax.swing.JLabel valRatting4;
    private javax.swing.JLabel valRatting5;
    private javax.swing.JLabel valTotalDonwload;
    private javax.swing.JLabel valUkuran;
    private javax.swing.JLabel valVersi;
    // End of variables declaration//GEN-END:variables
}
