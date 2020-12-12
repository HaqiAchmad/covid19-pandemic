package com.window.admin;

import com.database.Account;
import com.database.CovidCases;
import com.media.audio.Audio;
import com.media.gambar.Gambar;
import com.window.all.AboutApp;
import com.window.all.ApaCovid;
import com.window.all.BahayaCovid;
import com.window.all.Beranda;
import com.window.all.GejalaCovid;
import com.window.all.InformasiAkun;
import com.window.all.KasusCovidDunia;
import com.window.all.KasusCovidIndo;
import com.window.all.PenangananCovid;
import com.window.all.PencegahanCovid;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-12-01
 */
public class DataAplikasi extends javax.swing.JFrame {

    /**
     * Digunakan untuk mendapatkan data dari user
     */
    private final Account acc = new Account();
    /**
     * Digunakan untuk mendapatkan data dari kasus covid dunia
     */
    private final CovidCases caseDunia = new CovidCases(CovidCases.KASUS_DUNIA);
    /**
     * Digunakan untuk mendapatka data dari kasus covid indo
     */
    private final CovidCases caseIndo = new CovidCases(CovidCases.KASUS_INDO);
    /**
     * Digunakan untuk menyimpan data dari user seperti nama, tipe akun dan foto profile
     */
    private final String namaUser = acc.getDataAccount(acc.getActivedUser(), Account.NAMA_PANGGILAN), 
                         fotoProfile = acc.getDataAccount(acc.getActivedUser(), Account.FOTO_PROFILE),
                         tipeAkun = acc.getDataAccount(acc.getActivedUser(), Account.TYPE);
    /**
     * Data yang akan ditampilkan ke window
     */
    private int totalUser = 14565346, totalPria = 8634971, totalWanita = 5940370, userIndo = 11542654, 
                userLuar = 3022692, akunUser = 14565130, online = 75454;
    private int x, y;
    /**
     * Digunakan untuk merandom data dari user
     */
    private boolean isPlay = true;
    
    public DataAplikasi() {
        initComponents();
        
        this.setIconImage(Gambar.getWindowIcon());
        this.setLocationRelativeTo(null);
        this.btnBeranda.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnApaCovid.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnGejala.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnPencegahan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBahaya.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnPenanganan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnCovidDunia.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnCovidIndo.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnTentangApp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataApp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        dataEnteredExited();
        getDataUser();
        getDataCovidDunia();
        getDataCovidIndo();
        
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
        
        JButton[] btns = new JButton[]{this.btnBeranda, this.btnApaCovid, this.btnGejala, this.btnBahaya, this.btnPencegahan, this.btnPenanganan, this.btnCovidDunia, this.btnCovidIndo, this.btnTentangApp};
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
    //
    private void dataEnteredExited(){
        // jlabel yang akan diatur mouse event-nya
        JLabel[] lbls = new JLabel[]{
            this.valDataUser_online, this.valDataUser_rata2Umur, this.valDataUser_tipeAdmin, this.valDataUser_tipeUser, this.valDataUser_userIndo, this.valDataUser_userLuar, this.valDataUser_userPria, this.valDataUser_userWanita, this.valDataUser_totalUser,
            this.valDataCovidDunia_aktif, this.valDataCovidDunia_diubah, this.valDataCovidDunia_kematian, this.valDataCovidDunia_kritis, this.valDataCovidDunia_populasi, this.valDataCovidDunia_positif, this.valDataCovidDunia_sembuh, this.valDataCovidDunia_tingkatKematian, this.valDataCovidDunia_tingkatSembuh, this.valDataCovidDunia_totalNegara,
            this.valDataCovidIndo_aktif, this.valDataCovidIndo_diubah, this.valDataCovidIndo_kasusPertama, this.valDataCovidIndo_kematian, this.valDataCovidIndo_positif, this.valDataCovidIndo_presentase, this.valDataCovidIndo_sembuh, this.valDataCovidIndo_totalKab, this.valDataCovidIndo_zonaHijau, this.valDataCovidIndo_zonaMerah, this.valDataCovidIndo_zonaOranye
        };
        for(JLabel lbl : lbls){
            lbl.addMouseListener(new java.awt.event.MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    Audio.play(Audio.SOUND_INFO);
                    JOptionPane.showMessageDialog(null, lbl.getText().substring(2));
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
                    lbl.setForeground(new Color(200,110,20));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
                    lbl.setForeground(new Color(0, 0, 0));
                }
            });
        }
    }
    /**
     * Digunakan untuk menampilkan data dari user seperti total user, total user dengan gende laki2/perempuan, dll
     * Data yang ditampilkan tidaklah asli seperti kenyataan-nya. kerana data akan ditampilkan secara random
     */
    private void getDataUser(){
        // digunakan untuk merandom data
        Random rand = new Random();
        
        // merandom data diatas
        new Thread(new Runnable(){
            
            @Override
            public void run(){
                try{
                    // num digunakan untuk merandom data dari pengguna online
                    // getRand digunakan untuk mendapatkan angka dari hasil random
                    int num, getRand;
                    // akan merandom data jika window masih dibuka
                    while(isPlay){
                        // merandom angka
                        getRand = rand.nextInt(100);
                        // jika hasil dari angka yg dirandom < 0 maka getRand akan scr default diatur ke 10
                        if(getRand >= 0){
                            getRand = 10;
                        }
                        // mengupdate data dengan menambahkan getRand
                        totalUser  += getRand;
                        totalPria += getRand / 2;
                        totalWanita += getRand / 2;
                        userIndo += getRand / 2;
                        userLuar += getRand / 2;
                        akunUser += getRand;
                        
                        // merandom pengguna online
                        num = rand.nextInt(5000);
                        // jika num adalah genap maka pengguna online akan bertambah 
                        // jika num adalah ganjil maka pengguna online akan bekurang
                        if((num % 2) == 0){
                            online += rand.nextInt(6000) + getRand;
                        }else {
                            online -= rand.nextInt(5000);
                        }
                        // merandom delay yg dilakukan
                        Thread.sleep(500 + rand.nextInt(5000));
                        
                        // menampilkan data ke winow
                        valDataUser_totalUser.setText(String.format(": %,d User", totalUser));
                        valDataUser_userPria.setText(String.format(": %,d User", totalPria));
                        valDataUser_userWanita.setText(String.format(": %,d User", totalWanita));
                        valDataUser_userIndo.setText(String.format(": %,d", userIndo));
                        valDataUser_userLuar.setText(String.format(": %,d", userLuar));
                        valDataUser_rata2Umur.setText(String.format(": %,d.%,d Tahun", 34, rand.nextInt(10)));
                        valDataUser_tipeUser.setText(String.format(": %,d", akunUser));
                        valDataUser_tipeAdmin.setText(String.format(": %,d", 216));
                        valDataUser_online.setText(String.format(": %,d", online));
                    }
                }catch(InterruptedException iex){
                    System.out.println("Tejadji kesalahan saat merandom data : " + iex.getMessage());
                }
            }
        }).start();
    }
    /**
     * Digunakan untuk menampilkan data dari kasus covid Dunia ke widow, Data yang akan ditampilkan ke window
     * didapatkan dengan menggunakan object dari class <code>CovidCases</code>. Method akan mendapatkan data terlebih dahulu dari object
     * tersebut setelah proses penggambilan data selesai maka data akan ditampilkan ke window
     */
    private void getDataCovidDunia(){
        // digunakan untuk mendapatkan data dari kasus covid dunia
        int positif = caseDunia.getDataNumber(CovidCases.KASUS, "Dunia"), sembuh = caseDunia.getDataNumber(CovidCases.SEMBUH, "Dunia"),
            kematian = caseDunia.getDataNumber(CovidCases.KEMATIAN, "Dunia"), aktif = caseDunia.getDataNumber(CovidCases.AKTIF, "Dunia"),
            kritis = caseDunia.getDataNumber(CovidCases.KRITIS, "Dunia"), populasi = caseDunia.getDataNumber(CovidCases.POPULASI, "Dunia"),
            totalNegara = caseDunia.getRows("SELECT * FROM kasuscovid_dunia");
        
        // menampilkan data dari kasus covid ke window
        this.valDataCovidDunia_positif.setText(": " + caseDunia.addDelim(positif));
        this.valDataCovidDunia_sembuh.setText(": " + caseDunia.addDelim(sembuh));
        this.valDataCovidDunia_kematian.setText(": " + caseDunia.addDelim(kematian));
        this.valDataCovidDunia_aktif.setText(": " + caseDunia.addDelim(aktif));
        this.valDataCovidDunia_kritis.setText(": " + caseDunia.addDelim(kritis));
        this.valDataCovidDunia_populasi.setText(": " + caseDunia.addDelim((long)populasi * 10L));
        this.valDataCovidDunia_totalNegara.setText(": " + caseDunia.addDelim(totalNegara) + " Negara");
        this.valDataCovidDunia_diubah.setText(": " + caseDunia.dateToString(caseDunia.getData(CovidCases.DIUBAH, "Dunia")));
        
        // menampilkan data tingkat kematian dan tingkat sembuh
        if(sembuh == -1 || kematian == -1){
            this.valDataCovidDunia_tingkatSembuh.setText(": N/A");
            this.valDataCovidDunia_tingkatKematian.setText(": N/A");
        }else{
            this.valDataCovidDunia_tingkatSembuh.setText(": " + caseDunia.getPresentase(sembuh, kematian) + "%");
            this.valDataCovidDunia_tingkatKematian.setText(": " + caseDunia.getPresentase(kematian, sembuh) + "%");
        }
    }
    /**
     * Digunakan untuk menampilkan data dari kasus covid Indo ke window, Data yang akan ditampilkan ke window
     * didapatkan dengan menggunakan object dari class <code>CovidCases</code>. Method akan mendapatkan data terlebih dahulu dari object
     * tersebut setelah proses penggambilan data selesai maka data akan ditampilkan ke window
     */
    private void getDataCovidIndo(){
        // digunakan untuk mendapatkan data dari kasus covid indo
        int positif = caseIndo.getTotalData(CovidCases.KASUS), sembuh = caseIndo.getTotalData(CovidCases.SEMBUH),
            kematian = caseIndo.getTotalData(CovidCases.KEMATIAN), aktif = caseIndo.getTotalData(CovidCases.AKTIF),
            totalKab = caseIndo.getTotalData(CovidCases.TOTAL_KAB), zonaMerah = caseIndo.getTotalData(CovidCases.ZONA_MERAH),
            zonaOren = caseIndo.getTotalData(CovidCases.ZONA_ORANYE), zonaHijau = caseIndo.getTotalData(CovidCases.ZONA_HIJAU);
        
        // menampilkan data ke window
        this.valDataCovidIndo_positif.setText(": " + caseIndo.addDelim(positif));
        this.valDataCovidIndo_sembuh.setText(": " + caseIndo.addDelim(sembuh));
        this.valDataCovidIndo_kematian.setText(": " + caseIndo.addDelim(kematian));
        this.valDataCovidIndo_aktif.setText(": " + caseIndo.addDelim(aktif));
        this.valDataCovidIndo_totalKab.setText(": " + caseIndo.addDelim(totalKab) + " Kabupaten");
        this.valDataCovidIndo_zonaMerah.setText(": " + caseIndo.addDelim(zonaMerah) + " Kabupaten");
        this.valDataCovidIndo_zonaOranye.setText(": " + caseIndo.addDelim(zonaOren) + " Kabupaten");
        this.valDataCovidIndo_zonaHijau.setText(": " + caseIndo.addDelim(zonaHijau) + " Kabupaten");
        this.valDataCovidIndo_presentase.setText(": " + caseDunia.getPresentase(positif, caseDunia.getDataNumber(CovidCases.KASUS, "Dunia")) + "%");
        this.valDataCovidIndo_kasusPertama.setText(": 2 Maret 2020");
        this.valDataCovidIndo_diubah.setText(": " + caseIndo.dateToString(caseDunia.getData(CovidCases.DIUBAH, "Indonesia")));
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
        lblTop = new javax.swing.JLabel();
        pnlDataUser = new javax.swing.JPanel();
        lblDataUser = new javax.swing.JLabel();
        lblDataUser_totalUser = new javax.swing.JLabel();
        valDataUser_totalUser = new javax.swing.JLabel();
        lblDataUser_genderPria = new javax.swing.JLabel();
        valDataUser_userPria = new javax.swing.JLabel();
        lblDataUser_genderWanita = new javax.swing.JLabel();
        valDataUser_userWanita = new javax.swing.JLabel();
        lblDataUser_userIndo = new javax.swing.JLabel();
        valDataUser_userIndo = new javax.swing.JLabel();
        lblDataUser_userLuarNegeri = new javax.swing.JLabel();
        valDataUser_userLuar = new javax.swing.JLabel();
        lblDataUser_rata2Umur = new javax.swing.JLabel();
        valDataUser_rata2Umur = new javax.swing.JLabel();
        lblDataUser_tipeAdmin = new javax.swing.JLabel();
        valDataUser_tipeAdmin = new javax.swing.JLabel();
        lblDataUser_tipeUser = new javax.swing.JLabel();
        valDataUser_tipeUser = new javax.swing.JLabel();
        lblLinkDataUser = new javax.swing.JLabel();
        lblDataUser_online = new javax.swing.JLabel();
        valDataUser_online = new javax.swing.JLabel();
        pnlDataCovidDunia = new javax.swing.JPanel();
        lblDataCovidDunia = new javax.swing.JLabel();
        lblLinkDataCovidDunia = new javax.swing.JLabel();
        valDataCovidDunia_positif = new javax.swing.JLabel();
        lblDataCovidDunia_positif = new javax.swing.JLabel();
        lblDataCovidDunia_sembuh = new javax.swing.JLabel();
        valDataCovidDunia_sembuh = new javax.swing.JLabel();
        lblDataCovidDunia_kematian = new javax.swing.JLabel();
        valDataCovidDunia_kematian = new javax.swing.JLabel();
        lblDataCovidDunia_kritis = new javax.swing.JLabel();
        valDataCovidDunia_kritis = new javax.swing.JLabel();
        lblDataCovidDunia_aktif = new javax.swing.JLabel();
        valDataCovidDunia_aktif = new javax.swing.JLabel();
        lblDataCovidDunia_populasi = new javax.swing.JLabel();
        valDataCovidDunia_populasi = new javax.swing.JLabel();
        lblDataCovidDunia_tingkatSembuh = new javax.swing.JLabel();
        valDataCovidDunia_tingkatSembuh = new javax.swing.JLabel();
        lblDataCovidDunia_tingkatKematian = new javax.swing.JLabel();
        valDataCovidDunia_tingkatKematian = new javax.swing.JLabel();
        lblDataCovidDunia_totalNegara = new javax.swing.JLabel();
        valDataCovidDunia_totalNegara = new javax.swing.JLabel();
        lblDataCovidDunia_diubah = new javax.swing.JLabel();
        valDataCovidDunia_diubah = new javax.swing.JLabel();
        pnlDataCovidIndo = new javax.swing.JPanel();
        lblDataCovidIndo = new javax.swing.JLabel();
        lblLinkDataCovidIndonesia = new javax.swing.JLabel();
        valDataCovidIndo_positif = new javax.swing.JLabel();
        lblDataCovidIndo_positif = new javax.swing.JLabel();
        lblDataCovidIndo_sembuh = new javax.swing.JLabel();
        valDataCovidIndo_sembuh = new javax.swing.JLabel();
        lblDataCovidIndo_kematian = new javax.swing.JLabel();
        valDataCovidIndo_kematian = new javax.swing.JLabel();
        lblDataCovidIndo_aktif = new javax.swing.JLabel();
        valDataCovidIndo_aktif = new javax.swing.JLabel();
        lblDataCovidIndo_totalKab = new javax.swing.JLabel();
        valDataCovidIndo_totalKab = new javax.swing.JLabel();
        lblDataCovidIndo_zonaMerah = new javax.swing.JLabel();
        valDataCovidIndo_zonaMerah = new javax.swing.JLabel();
        lblDataCovidIndo_zonaOranye = new javax.swing.JLabel();
        valDataCovidIndo_zonaOranye = new javax.swing.JLabel();
        lblDataCovidIndo_zonaHijau = new javax.swing.JLabel();
        valDataCovidIndo_zonaHijau = new javax.swing.JLabel();
        lblDataCovidIndo_presentaseKasus = new javax.swing.JLabel();
        valDataCovidIndo_presentase = new javax.swing.JLabel();
        lblDataCovidIndo_kasusPertama = new javax.swing.JLabel();
        valDataCovidIndo_kasusPertama = new javax.swing.JLabel();
        lblDataCovidIndo_diubah = new javax.swing.JLabel();
        valDataCovidIndo_diubah = new javax.swing.JLabel();
        lblAppName = new javax.swing.JLabel();
        lblKetApp = new javax.swing.JLabel();
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
        btnApaCovid.setText("Apa Itu Covid-19");
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

        lblTop.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTop.setText("Data Aplikasi Covid-19 Pandemic");

        pnlDataUser.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDataUser.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblDataUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataUser.setText("Lihat Data User");
        lblDataUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDataUser_totalUser.setText("Total User");

        valDataUser_totalUser.setText(": 14.565.346 User");

        lblDataUser_genderPria.setText("Total User Laki-Laki");

        valDataUser_userPria.setText(": 8.634.971 User");

        lblDataUser_genderWanita.setText("Total User Perempuan");

        valDataUser_userWanita.setText(": 5.940.370 User");

        lblDataUser_userIndo.setText("User Dari Indonesia");

        valDataUser_userIndo.setText(": 11.542.654");

        lblDataUser_userLuarNegeri.setText("User Dari Luar Negeri");

        valDataUser_userLuar.setText(": 3.022.692");

        lblDataUser_rata2Umur.setText("Rata-Rata Umur User");

        valDataUser_rata2Umur.setText(": 34 Tahun");

        lblDataUser_tipeAdmin.setText("Akun DenganTipe Admin");

        valDataUser_tipeAdmin.setText(": 216");

        lblDataUser_tipeUser.setText("Akun Dengan Tipe User");

        valDataUser_tipeUser.setText(": 14.565.130");

        lblLinkDataUser.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblLinkDataUser.setForeground(new java.awt.Color(255, 0, 24));
        lblLinkDataUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLinkDataUser.setText("Update Data User");
        lblLinkDataUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLinkDataUserMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLinkDataUserMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLinkDataUserMouseExited(evt);
            }
        });

        lblDataUser_online.setText("Pengguna Online");

        valDataUser_online.setText(": 75.454");

        javax.swing.GroupLayout pnlDataUserLayout = new javax.swing.GroupLayout(pnlDataUser);
        pnlDataUser.setLayout(pnlDataUserLayout);
        pnlDataUserLayout.setHorizontalGroup(
            pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDataUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblDataUser_tipeUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataUser_tipeAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(lblDataUser_rata2Umur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataUser_userLuarNegeri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataUser_userIndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataUser_genderWanita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataUser_genderPria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataUser_totalUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataUser_online, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valDataUser_totalUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataUser_userPria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataUser_userWanita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataUser_userIndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataUser_userLuar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataUser_rata2Umur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataUser_tipeAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataUser_tipeUser, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                    .addComponent(valDataUser_online, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(lblLinkDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlDataUserLayout.setVerticalGroup(
            pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataUserLayout.createSequentialGroup()
                .addComponent(lblDataUser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataUser_totalUser)
                    .addComponent(valDataUser_totalUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataUser_genderPria)
                    .addComponent(valDataUser_userPria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataUser_genderWanita)
                    .addComponent(valDataUser_userWanita))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataUser_userIndo)
                    .addComponent(valDataUser_userIndo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataUser_userLuarNegeri)
                    .addComponent(valDataUser_userLuar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataUser_rata2Umur)
                    .addComponent(valDataUser_rata2Umur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataUser_tipeAdmin)
                    .addComponent(valDataUser_tipeAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(valDataUser_tipeUser)
                    .addComponent(lblDataUser_tipeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDataUser_online, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataUser_online, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(lblLinkDataUser)
                .addGap(22, 22, 22))
        );

        pnlDataCovidDunia.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataCovidDunia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDataCovidDunia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblDataCovidDunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataCovidDunia.setText("Lihat Data Covid-19 Dunia");
        lblDataCovidDunia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLinkDataCovidDunia.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblLinkDataCovidDunia.setForeground(new java.awt.Color(255, 0, 24));
        lblLinkDataCovidDunia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLinkDataCovidDunia.setText("Update Data Covid-19 Dunia");
        lblLinkDataCovidDunia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLinkDataCovidDuniaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLinkDataCovidDuniaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLinkDataCovidDuniaMouseExited(evt);
            }
        });

        valDataCovidDunia_positif.setText(": 32.138.014");

        lblDataCovidDunia_positif.setText("Total Positif");

        lblDataCovidDunia_sembuh.setText("Total Sembuh");

        valDataCovidDunia_sembuh.setText(": 23.708.311");

        lblDataCovidDunia_kematian.setText("Total Kematian ");

        valDataCovidDunia_kematian.setText(": 982.722");

        lblDataCovidDunia_kritis.setText("Total Kasus Kritis");

        valDataCovidDunia_kritis.setText(": 7.446.981");

        lblDataCovidDunia_aktif.setText("Total Kasus Aktif");

        valDataCovidDunia_aktif.setText(": 62.385");

        lblDataCovidDunia_populasi.setText("Populasi");

        valDataCovidDunia_populasi.setText(": 7.814.232.739");

        lblDataCovidDunia_tingkatSembuh.setText("Tingkat Kesembuhan");

        valDataCovidDunia_tingkatSembuh.setText(": 98.5%");

        lblDataCovidDunia_tingkatKematian.setText("Tingkat Kematian ");

        valDataCovidDunia_tingkatKematian.setText(": 2.5%");

        lblDataCovidDunia_totalNegara.setText("Negara Terdampak Covid-19");

        valDataCovidDunia_totalNegara.setText(": 216");

        lblDataCovidDunia_diubah.setText("Terakhir Diubah");

        valDataCovidDunia_diubah.setText(": 05 November 2020");

        javax.swing.GroupLayout pnlDataCovidDuniaLayout = new javax.swing.GroupLayout(pnlDataCovidDunia);
        pnlDataCovidDunia.setLayout(pnlDataCovidDuniaLayout);
        pnlDataCovidDuniaLayout.setHorizontalGroup(
            pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataCovidDunia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblLinkDataCovidDunia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDataCovidDuniaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDataCovidDunia_diubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidDunia_totalNegara, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(lblDataCovidDunia_tingkatKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidDunia_tingkatSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidDunia_populasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidDunia_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidDunia_kritis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidDunia_kematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidDunia_sembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidDunia_positif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valDataCovidDunia_positif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidDunia_sembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidDunia_kematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidDunia_kritis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidDunia_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidDunia_populasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidDunia_tingkatSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidDunia_tingkatKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidDunia_totalNegara, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidDunia_diubah, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDataCovidDuniaLayout.setVerticalGroup(
            pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataCovidDuniaLayout.createSequentialGroup()
                .addComponent(lblDataCovidDunia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidDunia_positif)
                    .addComponent(valDataCovidDunia_positif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidDunia_sembuh)
                    .addComponent(valDataCovidDunia_sembuh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidDunia_kematian)
                    .addComponent(valDataCovidDunia_kematian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidDunia_kritis)
                    .addComponent(valDataCovidDunia_kritis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidDunia_aktif)
                    .addComponent(valDataCovidDunia_aktif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidDunia_populasi)
                    .addComponent(valDataCovidDunia_populasi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidDunia_tingkatSembuh)
                    .addComponent(valDataCovidDunia_tingkatSembuh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidDunia_tingkatKematian)
                    .addComponent(valDataCovidDunia_tingkatKematian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidDunia_totalNegara)
                    .addComponent(valDataCovidDunia_totalNegara))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidDuniaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidDunia_diubah)
                    .addComponent(valDataCovidDunia_diubah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLinkDataCovidDunia)
                .addGap(23, 23, 23))
        );

        pnlDataCovidIndo.setBackground(new java.awt.Color(255, 255, 255));
        pnlDataCovidIndo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDataCovidIndo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblDataCovidIndo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataCovidIndo.setText("Lihat Data Covid-19 Indonesia");
        lblDataCovidIndo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblLinkDataCovidIndonesia.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblLinkDataCovidIndonesia.setForeground(new java.awt.Color(255, 0, 24));
        lblLinkDataCovidIndonesia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLinkDataCovidIndonesia.setText("Update Data Covid-19 Indonesia");
        lblLinkDataCovidIndonesia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLinkDataCovidIndonesiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLinkDataCovidIndonesiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLinkDataCovidIndonesiaMouseExited(evt);
            }
        });

        valDataCovidIndo_positif.setText(": 262.022");

        lblDataCovidIndo_positif.setText("Total Positif");

        lblDataCovidIndo_sembuh.setText("Total Sembuh");

        valDataCovidIndo_sembuh.setText(": 191.853");

        lblDataCovidIndo_kematian.setText("Total Kematian");

        valDataCovidIndo_kematian.setText(": 1.545");

        lblDataCovidIndo_aktif.setText("Total Kasus Aktif");

        valDataCovidIndo_aktif.setText(": 5.983");

        lblDataCovidIndo_totalKab.setText("Total Kabupaten");

        valDataCovidIndo_totalKab.setText(": 416");

        lblDataCovidIndo_zonaMerah.setText("Kab. Zona Merah");

        valDataCovidIndo_zonaMerah.setText(": 138");

        lblDataCovidIndo_zonaOranye.setText("Kab. Zona Oranye");

        valDataCovidIndo_zonaOranye.setText(": 138");

        lblDataCovidIndo_zonaHijau.setText("Kab. Zona Hijau");

        valDataCovidIndo_zonaHijau.setText(": 138");

        lblDataCovidIndo_presentaseKasus.setText("Presentase Kasus");

        valDataCovidIndo_presentase.setText(": 5.7%");

        lblDataCovidIndo_kasusPertama.setText("Kasus Pertama Kali");

        valDataCovidIndo_kasusPertama.setText(": 2 Maret 2020");

        lblDataCovidIndo_diubah.setText("Terakhir Diubah");

        valDataCovidIndo_diubah.setText(": 3 Desember 2020");

        javax.swing.GroupLayout pnlDataCovidIndoLayout = new javax.swing.GroupLayout(pnlDataCovidIndo);
        pnlDataCovidIndo.setLayout(pnlDataCovidIndoLayout);
        pnlDataCovidIndoLayout.setHorizontalGroup(
            pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDataCovidIndo, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
            .addComponent(lblLinkDataCovidIndonesia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlDataCovidIndoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDataCovidIndo_diubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidIndo_kasusPertama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidIndo_presentaseKasus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidIndo_zonaHijau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidIndo_zonaOranye, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidIndo_zonaMerah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidIndo_totalKab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidIndo_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidIndo_kematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidIndo_sembuh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDataCovidIndo_positif, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valDataCovidIndo_positif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidIndo_sembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidIndo_kematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidIndo_aktif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidIndo_totalKab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidIndo_zonaMerah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidIndo_zonaOranye, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidIndo_zonaHijau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidIndo_presentase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidIndo_kasusPertama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valDataCovidIndo_diubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDataCovidIndoLayout.setVerticalGroup(
            pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDataCovidIndoLayout.createSequentialGroup()
                .addComponent(lblDataCovidIndo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_positif)
                    .addComponent(valDataCovidIndo_positif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_sembuh)
                    .addComponent(valDataCovidIndo_sembuh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_kematian)
                    .addComponent(valDataCovidIndo_kematian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_aktif)
                    .addComponent(valDataCovidIndo_aktif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_totalKab)
                    .addComponent(valDataCovidIndo_totalKab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_zonaMerah)
                    .addComponent(valDataCovidIndo_zonaMerah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_zonaOranye)
                    .addComponent(valDataCovidIndo_zonaOranye))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_zonaHijau)
                    .addComponent(valDataCovidIndo_zonaHijau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_presentaseKasus)
                    .addComponent(valDataCovidIndo_presentase))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_kasusPertama)
                    .addComponent(valDataCovidIndo_kasusPertama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDataCovidIndoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDataCovidIndo_diubah)
                    .addComponent(valDataCovidIndo_diubah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(lblLinkDataCovidIndonesia)
                .addGap(24, 24, 24))
        );

        lblAppName.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblAppName.setText("Covid-19 Pandemic");
        lblAppName.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblKetApp.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblKetApp.setText("Aplikasi pemantauan Covid-19 di Dunia dan Indonesia");
        lblKetApp.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblCopyright.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCopyright.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCopyright.setText("Copyright © 2020. Achmad Baihaqi.");
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
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblMinimaze)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblKetApp, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCopyright))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(13, 13, 13))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addComponent(pnlDataUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlDataCovidDunia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlDataCovidIndo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 28, Short.MAX_VALUE))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMinimaze, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDataCovidDunia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDataCovidIndo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDataUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addComponent(lblAppName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKetApp)
                    .addComponent(lblCopyright))
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

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_BLACK));
    }//GEN-LAST:event_lblCloseMouseExited

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_ENTERED));
    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        acc.closeConnection();
        caseDunia.closeConnection();
        caseIndo.closeConnection();
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
        com.window.admin.DataAplikasi data = new com.window.admin.DataAplikasi();
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
        InformasiAkun infoAkun = new InformasiAkun(InformasiAkun.DATA_APLIKASI);
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
        InformasiAkun infoAkun = new InformasiAkun(InformasiAkun.DATA_APLIKASI);
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

    private void lblLinkDataUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkDataUserMouseClicked
        System.out.println("Membuka Window UpdateUser");
        UpdateUser updateUser = new UpdateUser();
        updateUser.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                updateUser.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_lblLinkDataUserMouseClicked

    private void lblLinkDataUserMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkDataUserMouseEntered
        this.lblLinkDataUser.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblLinkDataUser.setText("<html><p style=\"text-decoration:underline; color:rgb(1,67,228)\">Update Data User</p></html>");
    }//GEN-LAST:event_lblLinkDataUserMouseEntered

    private void lblLinkDataUserMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkDataUserMouseExited
        this.lblLinkDataUser.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblLinkDataUser.setText("<html><p style=\"text-decoration:none; color:rgb(255,0,24)\">Update Data User</p></html>");
    }//GEN-LAST:event_lblLinkDataUserMouseExited

    private void lblLinkDataCovidDuniaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkDataCovidDuniaMouseClicked
        System.out.println("Membuka Window UpdateCovidDunia");
        UpdateCovidDunia updateCovidDunia = new UpdateCovidDunia();
        updateCovidDunia.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                updateCovidDunia.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_lblLinkDataCovidDuniaMouseClicked

    private void lblLinkDataCovidDuniaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkDataCovidDuniaMouseEntered
        this.lblLinkDataCovidDunia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblLinkDataCovidDunia.setText("<html><p style=\"text-decoration:underline; color:rgb(1,67,228)\">Update Data Covid-19 Dunia</p></html>");
    }//GEN-LAST:event_lblLinkDataCovidDuniaMouseEntered

    private void lblLinkDataCovidDuniaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkDataCovidDuniaMouseExited
        this.lblLinkDataCovidDunia.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblLinkDataCovidDunia.setText("<html><p style=\"text-decoration:none; color:rgb(255,0,24)\">Update Data Covid-19 Dunia</p></html>");
    }//GEN-LAST:event_lblLinkDataCovidDuniaMouseExited

    private void lblLinkDataCovidIndonesiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkDataCovidIndonesiaMouseClicked
        System.out.println("Membuka Window UpdateCovidIndo");
        UpdateCovidIndo updateCovidIndo = new UpdateCovidIndo();
        updateCovidIndo.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                updateCovidIndo.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_lblLinkDataCovidIndonesiaMouseClicked

    private void lblLinkDataCovidIndonesiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkDataCovidIndonesiaMouseEntered
        this.lblLinkDataCovidIndonesia.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.lblLinkDataCovidIndonesia.setText("<html><p style=\"text-decoration:underline; color:rgb(1,67,228)\">Update Data Covid-19 Indonesia</p></html>");
    }//GEN-LAST:event_lblLinkDataCovidIndonesiaMouseEntered

    private void lblLinkDataCovidIndonesiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLinkDataCovidIndonesiaMouseExited
        this.lblLinkDataCovidIndonesia.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        this.lblLinkDataCovidIndonesia.setText("<html><p style=\"text-decoration:none; color:rgb(255,0,24)\">Update Data Covid-19 Indonesia</p></html>");
    }//GEN-LAST:event_lblLinkDataCovidIndonesiaMouseExited

    private void lblCopyrightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCopyrightMouseClicked
        
    }//GEN-LAST:event_lblCopyrightMouseClicked

    private void lblCopyrightMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCopyrightMouseEntered
        this.lblCopyright.setText("<html><p style=\"text-decoration:underline; color:rgb(1,67,228);\">Copyright © 2020. Achmad Baihaqi.</p></html>");
    }//GEN-LAST:event_lblCopyrightMouseEntered

    private void lblCopyrightMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCopyrightMouseExited
        this.lblCopyright.setText("<html><p style=\"text-decoration:none; color:rgb(0, 0, 0);\">Copyright © 2020. Achmad Baihaqi.</p></html>");
    }//GEN-LAST:event_lblCopyrightMouseExited

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.out.println("Keluar dari Window DataAplikasi");
        isPlay = false;
        acc.closeConnection();
        caseDunia.closeConnection();
        caseIndo.closeConnection();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        acc.closeConnection();
        caseDunia.closeConnection();
        caseIndo.closeConnection();
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
            java.util.logging.Logger.getLogger(DataAplikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataAplikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataAplikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataAplikasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataAplikasi().setVisible(true);
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
    private javax.swing.JLabel lblAppName;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDataCovidDunia;
    private javax.swing.JLabel lblDataCovidDunia_aktif;
    private javax.swing.JLabel lblDataCovidDunia_diubah;
    private javax.swing.JLabel lblDataCovidDunia_kematian;
    private javax.swing.JLabel lblDataCovidDunia_kritis;
    private javax.swing.JLabel lblDataCovidDunia_populasi;
    private javax.swing.JLabel lblDataCovidDunia_positif;
    private javax.swing.JLabel lblDataCovidDunia_sembuh;
    private javax.swing.JLabel lblDataCovidDunia_tingkatKematian;
    private javax.swing.JLabel lblDataCovidDunia_tingkatSembuh;
    private javax.swing.JLabel lblDataCovidDunia_totalNegara;
    private javax.swing.JLabel lblDataCovidIndo;
    private javax.swing.JLabel lblDataCovidIndo_aktif;
    private javax.swing.JLabel lblDataCovidIndo_diubah;
    private javax.swing.JLabel lblDataCovidIndo_kasusPertama;
    private javax.swing.JLabel lblDataCovidIndo_kematian;
    private javax.swing.JLabel lblDataCovidIndo_positif;
    private javax.swing.JLabel lblDataCovidIndo_presentaseKasus;
    private javax.swing.JLabel lblDataCovidIndo_sembuh;
    private javax.swing.JLabel lblDataCovidIndo_totalKab;
    private javax.swing.JLabel lblDataCovidIndo_zonaHijau;
    private javax.swing.JLabel lblDataCovidIndo_zonaMerah;
    private javax.swing.JLabel lblDataCovidIndo_zonaOranye;
    private javax.swing.JLabel lblDataUser;
    private javax.swing.JLabel lblDataUser_genderPria;
    private javax.swing.JLabel lblDataUser_genderWanita;
    private javax.swing.JLabel lblDataUser_online;
    private javax.swing.JLabel lblDataUser_rata2Umur;
    private javax.swing.JLabel lblDataUser_tipeAdmin;
    private javax.swing.JLabel lblDataUser_tipeUser;
    private javax.swing.JLabel lblDataUser_totalUser;
    private javax.swing.JLabel lblDataUser_userIndo;
    private javax.swing.JLabel lblDataUser_userLuarNegeri;
    private javax.swing.JLabel lblEditProfile;
    private javax.swing.JLabel lblKetApp;
    private javax.swing.JLabel lblLinkDataCovidDunia;
    private javax.swing.JLabel lblLinkDataCovidIndonesia;
    private javax.swing.JLabel lblLinkDataUser;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamaUser;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblTop;
    private javax.swing.JPanel pnlAccount;
    private javax.swing.JPanel pnlDataCovidDunia;
    private javax.swing.JPanel pnlDataCovidIndo;
    private javax.swing.JPanel pnlDataUser;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlLeftBottom;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel valDataCovidDunia_aktif;
    private javax.swing.JLabel valDataCovidDunia_diubah;
    private javax.swing.JLabel valDataCovidDunia_kematian;
    private javax.swing.JLabel valDataCovidDunia_kritis;
    private javax.swing.JLabel valDataCovidDunia_populasi;
    private javax.swing.JLabel valDataCovidDunia_positif;
    private javax.swing.JLabel valDataCovidDunia_sembuh;
    private javax.swing.JLabel valDataCovidDunia_tingkatKematian;
    private javax.swing.JLabel valDataCovidDunia_tingkatSembuh;
    private javax.swing.JLabel valDataCovidDunia_totalNegara;
    private javax.swing.JLabel valDataCovidIndo_aktif;
    private javax.swing.JLabel valDataCovidIndo_diubah;
    private javax.swing.JLabel valDataCovidIndo_kasusPertama;
    private javax.swing.JLabel valDataCovidIndo_kematian;
    private javax.swing.JLabel valDataCovidIndo_positif;
    private javax.swing.JLabel valDataCovidIndo_presentase;
    private javax.swing.JLabel valDataCovidIndo_sembuh;
    private javax.swing.JLabel valDataCovidIndo_totalKab;
    private javax.swing.JLabel valDataCovidIndo_zonaHijau;
    private javax.swing.JLabel valDataCovidIndo_zonaMerah;
    private javax.swing.JLabel valDataCovidIndo_zonaOranye;
    private javax.swing.JLabel valDataUser_online;
    private javax.swing.JLabel valDataUser_rata2Umur;
    private javax.swing.JLabel valDataUser_tipeAdmin;
    private javax.swing.JLabel valDataUser_tipeUser;
    private javax.swing.JLabel valDataUser_totalUser;
    private javax.swing.JLabel valDataUser_userIndo;
    private javax.swing.JLabel valDataUser_userLuar;
    private javax.swing.JLabel valDataUser_userPria;
    private javax.swing.JLabel valDataUser_userWanita;
    // End of variables declaration//GEN-END:variables
}
