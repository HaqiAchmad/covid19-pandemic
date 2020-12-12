package com.window.all;

import com.database.Account;
import com.database.CovidCases;
import com.media.audio.Audio;
import com.media.gambar.Gambar;
import com.window.admin.DataAplikasi;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * 
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-24
 */
public class Beranda extends javax.swing.JFrame {

    private final CovidCases kasus = new CovidCases(CovidCases.KASUS_DUNIA);
    private final Account acc = new Account();
    
    private int positifDunia, kematianDunia, sembuhDunia, aktifDunia, kritisDunia, populasiDunia,
                positifIndo, kematianIndo, sembuhIndo, aktifIndo, kritisIndo, populasiIndo;
    
    private final String namaUser = acc.getDataAccount(acc.getActivedUser(), Account.NAMA_PANGGILAN), 
                         fotoProfile = acc.getDataAccount(acc.getActivedUser(), Account.FOTO_PROFILE),
                         lokasi = acc.getDataAccount(acc.getActivedUser(), Account.ALAMAT) + ", " + acc.getDataAccount(acc.getActivedUser(), Account.NEGARA),
                         tipeAkun = acc.getDataAccount(acc.getActivedUser(), Account.TYPE),
                         lastUpdate = kasus.getData(CovidCases.DIUBAH, "Dunia");
    private int x, y;

    public Beranda() {
        initComponents();
        
        this.getKasusDunia();
        this.getKasusIndonesia();
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        this.lblLastUpdate.setText("Terakhir diupdate : " + kasus.dateToString(lastUpdate));
        this.btnBeranda.setBackground(new Color(22,108,190));
        this.btnBeranda.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataApp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        // jika akun yang login memiliki tipe akun 'user' maka button dataApp akan tidak terlihat
        if(tipeAkun.equalsIgnoreCase("User")){
            this.btnDataApp.setText("");
            this.btnDataApp.setEnabled(false);
        }
        
        /* jika panjang dari nama panggilan user lebih dari 17 maka nama panggilan tersebut akan dipotong,
           tampilan window akan berubah jika nama panggilan dari user terlalu panjang
        */
        if(namaUser.length() <= 10){
            this.lblNamaUser.setText("Hi, " + namaUser);
        }else if(namaUser.length() > 17){
            this.lblNamaUser.setText(namaUser.substring(0, 17) + "...");
        }else{
            this.lblNamaUser.setText(namaUser);
        }
        
        if(lokasi.length() < 40){
            this.lblLokasi.setText(lokasi);
        }else{
            this.lblLokasi.setText(lokasi.substring(0, 40) + "...");
        }
        
        JButton[] btns = new JButton[]{this.btnApaCovid, this.btnGejala, this.btnBahaya, this.btnPencegahan, this.btnPenanganan, this.btnCovidDunia, this.btnCovidIndo, this.btnTentangApp};
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
    
    private void getKasusDunia(){
        positifDunia = kasus.getDataNumber(CovidCases.KASUS, "Dunia");
        sembuhDunia = kasus.getDataNumber(CovidCases.SEMBUH, "Dunia");
        kematianDunia = kasus.getDataNumber(CovidCases.KEMATIAN, "Dunia");
        aktifDunia = kasus.getDataNumber(CovidCases.AKTIF, "Dunia");
        kritisDunia = kasus.getDataNumber(CovidCases.KRITIS, "Dunia");
        populasiDunia = kasus.getDataNumber(CovidCases.POPULASI, "Dunia");
        
        this.valPositif_Dunia.setText(kasus.addDelim(positifDunia));
        this.valSembuh_Dunia.setText(kasus.addDelim(sembuhDunia));
        this.valKematian_Dunia.setText(kasus.addDelim(kematianDunia));
        this.valAktif_Dunia.setText(kasus.addDelim(aktifDunia));
        this.valKritis_Dunia.setText(kasus.addDelim(kritisDunia));
        this.valPopulasi_Dunia.setText(kasus.addDelim(populasiDunia*10L));
    }
    
    private void getKasusIndonesia(){
        positifIndo = kasus.getDataNumber(CovidCases.KASUS, "Indonesia");
        sembuhIndo = kasus.getDataNumber(CovidCases.SEMBUH, "Indonesia");
        kematianIndo = kasus.getDataNumber(CovidCases.KEMATIAN, "Indonesia");
        aktifIndo = kasus.getDataNumber(CovidCases.AKTIF, "Indonesia");
        kritisIndo = kasus.getDataNumber(CovidCases.KRITIS, "Indonesia");
        populasiIndo = kasus.getDataNumber(CovidCases.POPULASI, "Indonesia");
        
        this.valPositif_Indo.setText(kasus.addDelim(positifIndo));
        this.valSembuh_Indo.setText(kasus.addDelim(sembuhIndo));
        this.valKematian_Indo.setText(kasus.addDelim(kematianIndo));
        this.valAktif_Indo.setText(kasus.addDelim(aktifIndo));
        this.valKritis_Indo.setText(kasus.addDelim(kritisIndo));
        this.valPopulasi_Indo.setText(kasus.addDelim(populasiIndo));
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
        btnDataApp = new javax.swing.JButton();
        btnPenanganan = new javax.swing.JButton();
        btnCovidIndo = new javax.swing.JButton();
        btnTentangApp = new javax.swing.JButton();
        lblClose = new javax.swing.JLabel();
        lblMinimaze = new javax.swing.JLabel();
        lblDashboard = new javax.swing.JLabel();
        lineDuniaTop = new javax.swing.JSeparator();
        lineDuniaBottom = new javax.swing.JSeparator();
        lblKasusCovid_Dunia = new javax.swing.JLabel();
        lblPositif_Dunia = new javax.swing.JLabel();
        valPositif_Dunia = new javax.swing.JLabel();
        valSembuh_Dunia = new javax.swing.JLabel();
        lblSembuh_Dunia = new javax.swing.JLabel();
        valKematian_Dunia = new javax.swing.JLabel();
        lblAktif_Dunia = new javax.swing.JLabel();
        valAktif_Dunia = new javax.swing.JLabel();
        lblKematian_Dunia = new javax.swing.JLabel();
        lblPopulasi_Dunia = new javax.swing.JLabel();
        valPopulasi_Dunia = new javax.swing.JLabel();
        lblKritis_Dunia = new javax.swing.JLabel();
        valKritis_Dunia = new javax.swing.JLabel();
        lblLastUpdate = new javax.swing.JLabel();
        lblAktif_Indo = new javax.swing.JLabel();
        valAktif_Indo = new javax.swing.JLabel();
        lblKematian_Indo = new javax.swing.JLabel();
        lblPopulasi_Indo = new javax.swing.JLabel();
        valPopulasi_Indo = new javax.swing.JLabel();
        lblKritis_Indo = new javax.swing.JLabel();
        valKritis_Indo = new javax.swing.JLabel();
        lineIndoTop = new javax.swing.JSeparator();
        lineIndoBottom = new javax.swing.JSeparator();
        lblKasusCovid_Indo = new javax.swing.JLabel();
        lblPositif_Indo = new javax.swing.JLabel();
        valPositif_Indo = new javax.swing.JLabel();
        valSembuh_Indo = new javax.swing.JLabel();
        lblSembuh_Indo = new javax.swing.JLabel();
        valKematian_Indo = new javax.swing.JLabel();
        lblAppName = new javax.swing.JLabel();
        lblKetApp = new javax.swing.JLabel();
        lblLokasi = new javax.swing.JLabel();
        lblCopyright = new javax.swing.JLabel();

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
        pnlMain.setMaximumSize(new java.awt.Dimension(1002, 586));
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
        pnlLeft.setMaximumSize(new java.awt.Dimension(218, 555));

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
        pnlAccount.setMaximumSize(new java.awt.Dimension(194, 82));

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
        lblNamaUser.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblNamaUser.setInheritsPopupMenu(false);

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
                    .addComponent(lblEditProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addComponent(lblNamaUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        btnDataApp.setBackground(new java.awt.Color(33, 114, 175));
        btnDataApp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDataApp.setForeground(new java.awt.Color(255, 255, 255));
        btnDataApp.setText("Lihat Data Aplikasi");
        btnDataApp.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDataAppMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDataAppMouseExited(evt);
            }
        });
        btnDataApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataAppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlLeftBottomLayout = new javax.swing.GroupLayout(pnlLeftBottom);
        pnlLeftBottom.setLayout(pnlLeftBottomLayout);
        pnlLeftBottomLayout.setHorizontalGroup(
            pnlLeftBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDataApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLeftBottomLayout.setVerticalGroup(
            pnlLeftBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLeftBottomLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(btnDataApp, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(btnApaCovid, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            .addComponent(btnGejala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnBahaya, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            .addComponent(btnPencegahan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCovidDunia, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            .addComponent(pnlLeftBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPenanganan, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            .addComponent(btnCovidIndo, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            .addComponent(btnTentangApp, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
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

        lblDashboard.setFont(new java.awt.Font("Ebrima", 1, 19)); // NOI18N
        lblDashboard.setText("Beranda Pemantauan Covid-19");

        lineDuniaTop.setBackground(new java.awt.Color(16, 15, 15));
        lineDuniaTop.setForeground(new java.awt.Color(16, 15, 15));

        lineDuniaBottom.setBackground(new java.awt.Color(16, 15, 15));
        lineDuniaBottom.setForeground(new java.awt.Color(16, 15, 15));

        lblKasusCovid_Dunia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblKasusCovid_Dunia.setForeground(new java.awt.Color(244, 47, 47));
        lblKasusCovid_Dunia.setText("Kasus Covid-19 di Dunia");
        lblKasusCovid_Dunia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKasusCovid_DuniaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKasusCovid_DuniaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKasusCovid_DuniaMouseExited(evt);
            }
        });

        lblPositif_Dunia.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblPositif_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPositif_Dunia.setText("Kasus Positif");
        lblPositif_Dunia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valPositif_Dunia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPositif_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valPositif_Dunia.setText("32.138.014");

        valSembuh_Dunia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valSembuh_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valSembuh_Dunia.setText("23.708.311");

        lblSembuh_Dunia.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblSembuh_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSembuh_Dunia.setText("Sembuh");
        lblSembuh_Dunia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valKematian_Dunia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valKematian_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valKematian_Dunia.setText("982.722");

        lblAktif_Dunia.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblAktif_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAktif_Dunia.setText("Kasus Aktif");
        lblAktif_Dunia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valAktif_Dunia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valAktif_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valAktif_Dunia.setText("7.446.981");

        lblKematian_Dunia.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblKematian_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKematian_Dunia.setText("Kematian");
        lblKematian_Dunia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblPopulasi_Dunia.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblPopulasi_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPopulasi_Dunia.setText("Populasi");
        lblPopulasi_Dunia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valPopulasi_Dunia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPopulasi_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valPopulasi_Dunia.setText("7.814.232.739");

        lblKritis_Dunia.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblKritis_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKritis_Dunia.setText("Kasus Kritis");
        lblKritis_Dunia.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valKritis_Dunia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valKritis_Dunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valKritis_Dunia.setText("62.385");

        lblLastUpdate.setForeground(new java.awt.Color(44, 121, 163));
        lblLastUpdate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLastUpdate.setText("Terakhir diupdate : 30 September 2020");

        lblAktif_Indo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblAktif_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAktif_Indo.setText("Kasus Aktif");
        lblAktif_Indo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valAktif_Indo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valAktif_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valAktif_Indo.setText("60.064");

        lblKematian_Indo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblKematian_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKematian_Indo.setText("Kematian");
        lblKematian_Indo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblPopulasi_Indo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblPopulasi_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPopulasi_Indo.setText("Populasi");
        lblPopulasi_Indo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valPopulasi_Indo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPopulasi_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valPopulasi_Indo.setText("274.195.355");

        lblKritis_Indo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblKritis_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKritis_Indo.setText("Kasus Kritis");
        lblKritis_Indo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valKritis_Indo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valKritis_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valKritis_Indo.setText("N/A");

        lineIndoTop.setBackground(new java.awt.Color(16, 15, 15));
        lineIndoTop.setForeground(new java.awt.Color(16, 15, 15));

        lineIndoBottom.setBackground(new java.awt.Color(16, 15, 15));
        lineIndoBottom.setForeground(new java.awt.Color(16, 15, 15));

        lblKasusCovid_Indo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblKasusCovid_Indo.setForeground(new java.awt.Color(244, 47, 47));
        lblKasusCovid_Indo.setText("Kasus Covid-19 di Indonesia");
        lblKasusCovid_Indo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKasusCovid_IndoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKasusCovid_IndoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKasusCovid_IndoMouseExited(evt);
            }
        });

        lblPositif_Indo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblPositif_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPositif_Indo.setText("Kasus Positif");
        lblPositif_Indo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valPositif_Indo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valPositif_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valPositif_Indo.setText("262.022");

        valSembuh_Indo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valSembuh_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valSembuh_Indo.setText("191.853");

        lblSembuh_Indo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblSembuh_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSembuh_Indo.setText("Sembuh");
        lblSembuh_Indo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        valKematian_Indo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        valKematian_Indo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valKematian_Indo.setText("10.105");

        lblAppName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblAppName.setText("Covid-19 Pandemic");
        lblAppName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblKetApp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblKetApp.setText("Aplikasi pemantauan Covid-19 di Dunia dan Indonesia");
        lblKetApp.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblLokasi.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblLokasi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblLokasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-lokasi.png"))); // NOI18N
        lblLokasi.setText("Jawa Timur, Indonesia");
        lblLokasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLokasiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLokasiMouseExited(evt);
            }
        });

        lblCopyright.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCopyright.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCopyright.setText("Copyright © 2020. Achmad Baihaqi");
        lblCopyright.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCopyrightMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCopyrightMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCopyrightMouseExited(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblKetApp, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(165, 165, 165)
                                .addComponent(lblCopyright))
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                    .addComponent(lblAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lineDuniaTop, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
                                        .addComponent(lineDuniaBottom)
                                        .addComponent(lblDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlMainLayout.createSequentialGroup()
                                                    .addGap(8, 8, 8)
                                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lblPositif_Dunia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(valPositif_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lblSembuh_Dunia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(valSembuh_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(lblKasusCovid_Dunia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblKematian_Dunia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(valKematian_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblAktif_Dunia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(valAktif_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblKritis_Dunia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(valKritis_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblPopulasi_Dunia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(valPopulasi_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(lblLastUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lineIndoTop)
                                        .addComponent(lineIndoBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 737, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(pnlMainLayout.createSequentialGroup()
                                                    .addGap(8, 8, 8)
                                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lblPositif_Indo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(valPositif_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(lblSembuh_Indo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(valSembuh_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(lblKasusCovid_Indo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblKematian_Indo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(valKematian_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblAktif_Indo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(valAktif_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblKritis_Indo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(valKritis_Indo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(lblPopulasi_Indo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(valPopulasi_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblMinimaze, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMinimaze, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLastUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lineDuniaTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblKasusCovid_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblPositif_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valPositif_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblKematian_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valKematian_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblAktif_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valAktif_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblKritis_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valKritis_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblPopulasi_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valPopulasi_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblSembuh_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valSembuh_Dunia, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lineDuniaBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lineIndoTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblKasusCovid_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblPositif_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valPositif_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblKematian_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valKematian_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addComponent(lblAktif_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(valAktif_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblKritis_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valKritis_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblPopulasi_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valPopulasi_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblSembuh_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valSembuh_Indo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(lineIndoBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLokasi, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKetApp)
                    .addComponent(lblCopyright, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_BLACK));
    }//GEN-LAST:event_lblCloseMouseExited

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_ENTERED));
    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCloseMouseClicked

    private void lblMinimazeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseExited
        this.lblMinimaze.setIcon(Gambar.getIcon(Gambar.IC_MINIMAZE_BLACK));
    }//GEN-LAST:event_lblMinimazeMouseExited

    private void lblMinimazeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseEntered
        this.lblMinimaze.setIcon(Gambar.getIcon(Gambar.IC_MINIMAZE_ENTERED));
    }//GEN-LAST:event_lblMinimazeMouseEntered

    private void lblMinimazeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseClicked
        this.setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimazeMouseClicked

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

    private void btnDataAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataAppActionPerformed
        System.out.println("Membuka Window DataAplikasi");
        DataAplikasi data = new DataAplikasi();
        data.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                data.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnDataAppActionPerformed

    private void btnDataAppMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataAppMouseEntered
        // jika tipe akun yg login adalah admin maka event entered akan dilakukan
        if(tipeAkun.equalsIgnoreCase("Admin")){
            this.btnDataApp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            this.btnDataApp.setBackground(new Color(19,94,174));            
        }
    }//GEN-LAST:event_btnDataAppMouseEntered

    private void btnDataAppMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDataAppMouseExited
        // jika tipe akun yg login adalah admin maka event exited akan dilakukan
        if(tipeAkun.equalsIgnoreCase("Admin")){
            this.btnDataApp.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            this.btnDataApp.setBackground(new Color(33,114,175));            
        }
    }//GEN-LAST:event_btnDataAppMouseExited

    private void lblPhotoProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPhotoProfileMouseClicked
        System.out.println("Membuka Window InformasiAkun");
        InformasiAkun infoAkun = new InformasiAkun(InformasiAkun.BERANDA);
        infoAkun.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                infoAkun.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_lblPhotoProfileMouseClicked

    private void lblEditProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditProfileMouseClicked
        System.out.println("Membuka Window InformasiAkun");
        InformasiAkun infoAkun = new InformasiAkun(InformasiAkun.BERANDA);
        infoAkun.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                infoAkun.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_lblEditProfileMouseClicked

    private void lblEditProfileMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditProfileMouseEntered
        this.lblEditProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.lblEditProfile.setText("<html><p style=\"text-decoration:underline;\">Informasi Akun</p></html>");
    }//GEN-LAST:event_lblEditProfileMouseEntered

    private void lblEditProfileMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditProfileMouseExited
        this.lblEditProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        this.lblEditProfile.setText("<html><p style=\"text-decoration:none;\">Informasi Akun</p></html>");
    }//GEN-LAST:event_lblEditProfileMouseExited

    private void lblKasusCovid_DuniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKasusCovid_DuniaMouseClicked
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
    }//GEN-LAST:event_lblKasusCovid_DuniaMouseClicked

    private void lblKasusCovid_DuniaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKasusCovid_DuniaMouseEntered
        this.lblKasusCovid_Dunia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.lblKasusCovid_Dunia.setText("<html><p style=\"text-decoration:underline;\">Kasus Covid-19 di Dunia</p></html>");
    }//GEN-LAST:event_lblKasusCovid_DuniaMouseEntered

    private void lblKasusCovid_DuniaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKasusCovid_DuniaMouseExited
        this.lblKasusCovid_Dunia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        this.lblKasusCovid_Dunia.setText("<html><p style=\"text-decoration:none;\">Kasus Covid-19 di Dunia</p></html>");
    }//GEN-LAST:event_lblKasusCovid_DuniaMouseExited

    private void lblKasusCovid_IndoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKasusCovid_IndoMouseClicked
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
    }//GEN-LAST:event_lblKasusCovid_IndoMouseClicked

    private void lblKasusCovid_IndoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKasusCovid_IndoMouseEntered
        this.lblKasusCovid_Indo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.lblKasusCovid_Indo.setText("<html><p style=\"text-decoration:underline;\">Kasus Covid-19 di Indonesia</p></html>");
    }//GEN-LAST:event_lblKasusCovid_IndoMouseEntered

    private void lblKasusCovid_IndoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKasusCovid_IndoMouseExited
        this.lblKasusCovid_Indo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        this.lblKasusCovid_Indo.setText("<html><p style=\"text-decoration:none;\">Kasus Covid-19 di Indonesia</p></html>");
    }//GEN-LAST:event_lblKasusCovid_IndoMouseExited

    private void lblLokasiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLokasiMouseEntered
        this.lblLokasi.setIcon(Gambar.getIcon("ic-lokasi-entered.png"));
        this.lblLokasi.setText("<html><p style=\"text-decoration:underline;color:red;\">"+lokasi+"</p></html>");
    }//GEN-LAST:event_lblLokasiMouseEntered

    private void lblLokasiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLokasiMouseExited
        this.lblLokasi.setIcon(Gambar.getIcon("ic-lokasi.png"));
        this.lblLokasi.setText("<html><p style=\"text-decoration:none;\">"+lokasi+"</p></html>");
    }//GEN-LAST:event_lblLokasiMouseExited

    private void lblCopyrightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCopyrightMouseClicked
        Audio.play(Audio.SOUND_INFO);
        JOptionPane.showMessageDialog(null, "Copyright © 2020. Achmad Baihaqi. All Rights Reserved.", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        new Thread(new Runnable(){
        
            @Override
            public void run(){
                try{
                    String text = "Copyright © 2020. Achmad Baihaqi";
                    int loop = 0;
                    while(loop <= text.length()){
                        lblCopyright.setText(text.substring(0, loop));
                        loop++;
                        Thread.sleep(80);
                    }
                }catch(InterruptedException ex){
                    System.out.println("Terjadi kesalahan saat memainkan efek copyright : " + ex.getMessage());
                }
            }
        }).start();
    }//GEN-LAST:event_lblCopyrightMouseClicked

    private void lblCopyrightMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCopyrightMouseEntered
        this.lblCopyright.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        this.lblCopyright.setText("<html><p style=\"text-decoration:underline; color:rgb(24,82,126);\">Copyright © 2020. Achmad Baihaqi</p></html>");
    }//GEN-LAST:event_lblCopyrightMouseEntered

    private void lblCopyrightMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCopyrightMouseExited
        this.lblCopyright.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        this.lblCopyright.setText("<html><p style=\"text-decoration:none;\">Copyright © 2020. Achmad Baihaqi</p></html>");
    }//GEN-LAST:event_lblCopyrightMouseExited

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        acc.closeConnection();
        kasus.closeConnection();
        System.out.println("Menutup Window Beranda");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        acc.closeConnection();
        kasus.closeConnection();
        System.out.println("-->     APLIKASI DITUTUP");
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Beranda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Beranda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApaCovid;
    private javax.swing.JButton btnBahaya;
    private javax.swing.JButton btnBeranda;
    private javax.swing.JButton btnCovidDunia;
    private javax.swing.JButton btnCovidIndo;
    private javax.swing.JButton btnDataApp;
    private javax.swing.JButton btnGejala;
    private javax.swing.JButton btnPenanganan;
    private javax.swing.JButton btnPencegahan;
    private javax.swing.JButton btnTentangApp;
    private javax.swing.JLabel lblAktif_Dunia;
    private javax.swing.JLabel lblAktif_Indo;
    private javax.swing.JLabel lblAppName;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDashboard;
    private javax.swing.JLabel lblEditProfile;
    private javax.swing.JLabel lblKasusCovid_Dunia;
    private javax.swing.JLabel lblKasusCovid_Indo;
    private javax.swing.JLabel lblKematian_Dunia;
    private javax.swing.JLabel lblKematian_Indo;
    private javax.swing.JLabel lblKetApp;
    private javax.swing.JLabel lblKritis_Dunia;
    private javax.swing.JLabel lblKritis_Indo;
    private javax.swing.JLabel lblLastUpdate;
    private javax.swing.JLabel lblLokasi;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblPopulasi_Dunia;
    private javax.swing.JLabel lblPopulasi_Indo;
    private javax.swing.JLabel lblPositif_Dunia;
    private javax.swing.JLabel lblPositif_Indo;
    private javax.swing.JLabel lblSembuh_Dunia;
    private javax.swing.JLabel lblSembuh_Indo;
    private javax.swing.JSeparator lineDuniaBottom;
    private javax.swing.JSeparator lineDuniaTop;
    private javax.swing.JSeparator lineIndoBottom;
    private javax.swing.JSeparator lineIndoTop;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel valAktif_Dunia;
    private javax.swing.JLabel valAktif_Indo;
    private javax.swing.JLabel valKematian_Dunia;
    private javax.swing.JLabel valKematian_Indo;
    private javax.swing.JLabel valKritis_Dunia;
    private javax.swing.JLabel valKritis_Indo;
    private javax.swing.JLabel valPopulasi_Dunia;
    private javax.swing.JLabel valPopulasi_Indo;
    private javax.swing.JLabel valPositif_Dunia;
    private javax.swing.JLabel valPositif_Indo;
    private javax.swing.JLabel valSembuh_Dunia;
    private javax.swing.JLabel valSembuh_Indo;
    // End of variables declaration//GEN-END:variables
}
