package com.window.admin;

import com.database.CovidCases;
import com.media.audio.Audio;
import com.media.gambar.Gambar;
import com.window.all.Beranda;
import java.awt.Color;
import java.awt.Cursor;

import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-12-10
 */
public class AddDataIndo extends javax.swing.JFrame {

    /**
     * Digunakan untuk menambahkan sebuah data ke database
     */
    private final CovidCases dataIndo = new CovidCases(CovidCases.KASUS_INDO);
    /**
     * Digunakan untuk menyimpan data dari kasus covid indonesia yang berbentuk <code>String</code>
     */
    private String singkatanProv, provinsi, kasusPertama, diubah, website, lambang;
    /**
     * Digunakan untuk menyimpan data dari kasus covid indonesia yang berbentuk <code>Integer</code>
     */
    private int positif, sembuh, kematian, aktif, totalKab, zonaMerah, zonaOren, zonaHijau;
    /**
     * Digunakan untuk mengatur posisi dari window
     */
    private int x, y;
    /**
     * Digunakan untuk menyimpan data yang memiliki tipe long
     */
    private long populasi;
    /**
     * Digunakan untuk memainkan efek copyright
     */
    private boolean isPlay = true;
    
    public AddDataIndo() {
        initComponents();
        
        this.setIconImage(Gambar.getWindowIcon());
        this.setLocationRelativeTo(null);
        this.lblShowLambang.setText("");
        this.lblShowLambang.setIcon(Gambar.scaleImage(new java.io.File("src\\com\\media\\gambar\\lambang\\lambang-negara.jpeg"), 55, 60));
        
        this.copyrightEffect();
        
        // mengatur UI dari button yang ada didalam window ke BasicButtonUI
        JButton btns[] = new JButton[]{
           this.btnBatal, this.btnBeranda, this.btnAddUser, this.btnInfo, this.btnSimpan, this.btnAddDataDunia, this.btnAddDataIndo
        };
        for(JButton btn : btns){
            btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        }
        
        // mengatur MouseEvent Entered & Exited pada Button yang ada di dalam Panel pnlLeft
        JButton btnLeft[] = new JButton[]{
            this.btnBeranda, this.btnInfo, this.btnAddUser, this.btnAddDataDunia
        };
        for(JButton btn : btnLeft){
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
                    btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    btn.setBackground(new Color(28,100,230));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new Color(49,144,215));
                    btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });
        }
        
        
    }
    
    /**
     * Efek yang dijalankan pada label copyright
     */
    private void copyrightEffect(){
        
        // memainkan efek
         new Thread(new Runnable(){
        
            @Override
            public void run(){
                try{
                    int colors[][] = new int[][]{
                        {233,16,16}, // red
                        {3,86,187}, // blue
                        {204,0,204}, // purple
                        {11,194,17}, // green
                        {204,98,1}, // orange
                        {35,34,32} //black
                    };
                    while(isPlay){
                        for (int[] color : colors) {
                            lblCopyright.setForeground(new Color(color[0], color[1], color[2]));
                            Thread.sleep(700);
                            System.out.println("Loop in Window AddDataDunia=?OK");
                        }
                    }                    
                }catch(InterruptedException iex){
                    System.out.println("Error : " + iex.getMessage());
                }
            }
        }).start();
    }
    
    /**
     * Method ini digunakan untuk mengecek apakah sebuah data yang diinputakan oleh user melalui <code>JTextField</code> kosong 
     * atau tidak dan juga untuk mengecek apakah yang dinputkan merupakah sebuah angka atau tidak.
     * 
     * @param input JTextfield yang akan dicek input-nya
     * @param data Data jenis apa yang sedang dicek! data bisa berupa kasus positif, sembuh, kematian, aktif dan kritis.
     * @return Jika input kosong dan input yang dimasukan bukan number/angka maka akan mengegembalikan nilai <b>True</b>. 
     *         Tapi jika input tidak kosong dan yang dimasukan adalah angka maka akan mengembalikan nilai <b>False</b>.
     */
    private boolean isEmptyInput(JTextField input, final String data){
        try{
            // mengecek apakah input kosong atau tidak, jika kosong maka akan mengembalikan nilai false
            if(input.getText() == null || input.getText().equals("")){
                Audio.play(Audio.SOUND_WARNING);
                JOptionPane.showMessageDialog(null, "Input dari data " + data + " tidak boleh kosong!", "Data tidak valid!", JOptionPane.WARNING_MESSAGE);
                return true;
            // jika input tidak kosong maka input akan dicek apakah yg data yg diinputkan user angka atau tidak
            }else{ 
                // jika input dari user adalah n/a maka akan mengembalikan nilai false, (n/a adalah data yang kosong)
                if(input.getText().equalsIgnoreCase("n/a")){
                    return false;
                // mengecek apakah data yang diinputkan berupa angka atau tidak, jika angka maka akan mengembalikan nilai false
                }else if(dataIndo.isNumber(dataIndo.removeDelim(input.getText()))){
                    return false;
                }else{
                    Audio.play(Audio.SOUND_WARNING);
                    JOptionPane.showMessageDialog(null, "Input dari data kasus " + data + " harus berupa angka!", "Data tidak valid!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }catch(NumberFormatException nex){
            System.out.println("Terjadi kesalahan saat mengambil input : " + nex.getMessage());
        }
        return true;
    }
    
    /**
     * Jika <code>isValid</code> bernilai <b>True</b> maka TextField akan berwarna biru dan Label akan berwarna hitam, 
     * Tapi jika <code>isValid</code> bernilai <b>False</b> maka TextField dan Label akan berwarna merah
     * 
     * @param field TextField yang akan diubah warna-nya
     * @param label Label yang akan diubah warna-nya
     * @param isValid untuk menentukan warna dari TextField dan Label
     */
    private void changeColor(JTextField field, JLabel label, boolean isValid){
       // Jika isValid false
       if(!isValid){
           field.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255,0,0)));
           label.setForeground(new Color(255,0,0));
       }else{// Jika isValid true
           field.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0,106,255)));
           label.setForeground(new Color(0,0,0));
       }
    }
    
    /**
     * Method ini digunakan untuk menambahkan sebuah data kasus covid indonesia ke Database. 
     * Sebelumnya method akan mengambil input dari Textfield yang berisi data-data akun yang akan diedit. 
     * <br><br>
     * Lalu method akan mengecek apakah data-data yang akan diedit tersebut valid atau tidak datanya. 
     * Method akan mengecek semua data yang akan diedit tersebut sampai valid, Jika salah satu data yang dimasukan belum valid makan akan 
     * mucul notifikasi bahwa data tersebut belum valid dan warna border dan foreground pada Textfield dan Label akan berubah menjadi merah.
     * <br><br>
     * Jika semua data sudah valid maka proses selanjutnya adalah menyimpan data tersebut ke dalam <b>Database</b>. 
     * Jika ada data yang gagal disimpan maka method akan memunculkan notif bahwa ada gagal yang disimpan dan aplikasi akan 
     * secara otomatis merstore <b>Databse</b> dan mengembalikan nilai <b>False</b>. Tapi jika semua data berhasil disimpan maka method akan mengembalikan nilai <b>True</b>.
     * 
     * 
     * 
     * @return Jika proses edit berhasil maka akan mengembalikan nilai <b>True</b>. Tapi jika gagal maka akan mengembalikan nilai <b>False</b>. 
     */
    private boolean tambahData(){
        
        // isValid digunakan untuk mengecek apakah data yang diedit valid atau tidak
        boolean isValids = false;
        
        /*
            Digunakan untuk mengecek apakah input yang dimasukan user melalui JTextField kosong atau tidak. 
            dan juga digunakan untuk mengecek apakah input yang dimasukan user melalui JTextField adalah sebuah angka atau tidak. 
            Pengecekan tersebut menggunakan method isEmptyInput. Jika method isEmptyInput mengembalikan nilai False
            maka data dari input JTextField tersebut akan diambil dan dimasukan kedalam sebuah variabel dan warna line border pada JTextField 
            akan memilki warna biru, Tapi jika method isEmptyInput mengembalikan nilai True maka data tidak akan diambil dan method ini 
            akan mengembalikan nilai False, dan line border pada JTextField akan memiliki warna merah.
            .
        */
        
        // mengecek input dari nama singkatan provinsi
        if(this.inpSingkatanProv.getText().equals("")){
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Input dari singkatan Provinsi tidak boleh kosong!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            changeColor(this.inpSingkatanProv, this.lblSingkatanProv, false);
            return false;
        }else{
            changeColor(this.inpSingkatanProv, this.lblSingkatanProv, true);
        }
        // mengecek input dari nama provinsi
        if(this.inpNamaProv.getText().equals("")){
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Input dari nama Provinsi tidak boleh kosong!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            changeColor(this.inpNamaProv, this.lblNamaProv, false);
            return false;
        }else{
            changeColor(this.inpNamaProv, this.lblNamaProv, true);
        }
        // mengecek input dari penduduk
        if(this.isEmptyInput(this.inpPopulasi, "populasi")){
            changeColor(this.inpPopulasi, this.lblPopulasi, false);
            return false;
        }else{
            changeColor(this.inpPopulasi, this.lblPopulasi, true);
            this.populasi = Long.parseLong(dataIndo.removeDelim(this.inpPopulasi.getText())); // mengambil data populasi
            // jika populasi yg diinputkan jumlahnya lebih dari 2 miliar maka populasi tsb akan dibagi 10
            // ini digunakan untuk menghindari kesalahan saat nanti dikonversi ke dalam bentuk Integer
            if(this.populasi > 2000000000){
                this.populasi /= 10;
            }else if(this.populasi <= 0){ // jika populasi kosong maka scr default akan diatur ke 1
                this.populasi = 1;
            }
        }
        // mengecek input dari kasus positif
        if(this.isEmptyInput(this.inpPositif, "kasus positif")){
            changeColor(this.inpPositif, this.lblPositif, false);
            return false;
        }else{
            changeColor(this.inpPositif, this.lblPositif, true);
            this.positif = Integer.parseInt(dataIndo.removeDelim(this.inpPositif.getText())); // mengambil data kasus positif
        }
        // mengecek input dari kasus sembuh
        if(this.isEmptyInput(this.inpSembuh, "kasus sembuh")){
            changeColor(this.inpSembuh, this.lblSembuh, false);
            return false;
        }else{
            changeColor(this.inpSembuh, this.lblSembuh, true);
            this.sembuh = Integer.parseInt(dataIndo.removeDelim(this.inpSembuh.getText())); // mengambil data kasus sembuh
        }
        // mengecek input dari kasus kematian
        if(this.isEmptyInput(this.inpKematian, "kasus kematian")){
            changeColor(this.inpKematian, this.lblKematian, false);
            return false;
        }else{
            changeColor(this.inpKematian, this.lblKematian, true);
            this.kematian = Integer.parseInt(dataIndo.removeDelim(this.inpKematian.getText())); // mengambil data kasus kematian
        }
        // mengecek input dari kasus aktif
        if(this.isEmptyInput(this.inpAktif, "kasus aktif")){
            changeColor(this.inpAktif, this.lblAktif, false);
            return false;
        }else{
            changeColor(this.inpAktif, this.lblAktif, true);
            this.aktif = Integer.parseInt(dataIndo.removeDelim(this.inpAktif.getText())); // mengambil data kasus aktif
        }
        // mengecek input dari website resmi provinsi
        if(this.inpWebsite.getText().equals("")){
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Input dari Website tidak boleh kosong!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            changeColor(this.inpWebsite, this.lblWebsite, false);
            return false;
        }else{
            changeColor(this.inpWebsite, this.lblWebsite, true);
        }
        // mengecek input dari total kab
        if(this.isEmptyInput(this.inpTotalKab, "total kabupaten")){
            changeColor(this.inpTotalKab, this.lblTotalkab, false);
            return false;
        }else{
            changeColor(this.inpTotalKab, this.lblTotalkab, true);
            this.totalKab = Integer.parseInt(dataIndo.removeDelim(this.inpTotalKab.getText())); // mengambil data total kabupaten
        }
        // mengecek input dari zona merah
        if(this.isEmptyInput(this.inpZonaMerah, "zona merah")){
            changeColor(this.inpZonaMerah, this.lblZonaMerah, false);
            return false;
        }else{
            changeColor(this.inpZonaMerah, this.lblZonaMerah, true);
            this.zonaMerah = Integer.parseInt(dataIndo.removeDelim(this.inpZonaMerah.getText())); // mengambil data zona merah
        }
        // mengecek input dari zona oranye
        if(this.isEmptyInput(this.inpZonaOren, "zona oren")){
            changeColor(this.inpZonaOren, this.lblZonaOren, false);
            return false;
        }else{
            changeColor(this.inpZonaOren, this.lblZonaOren, true);
            this.zonaOren = Integer.parseInt(dataIndo.removeDelim(this.inpZonaOren.getText())); // mengambil data zona oranye
        }
        // mengecek input dari zona hijau
        if(this.isEmptyInput(this.inpZonaHijau, "zona hijau")){
            changeColor(this.inpZonaHijau, this.lblZonaHijau, false);
            return false;
        }else{
            changeColor(this.inpZonaHijau, this.lblZonaHijau, true);
            this.zonaHijau = Integer.parseInt(dataIndo.removeDelim(this.inpZonaHijau.getText())); // mengambil data zona hijau
        }
        // mengambil input dari website
        this.singkatanProv = this.inpSingkatanProv.getText();
        this.provinsi = this.inpNamaProv.getText();
        this.website = this.inpWebsite.getText();
        
        
        /**
         * Mengecek apakah sebuah data yg diinputkan oleh user valid atau tidak dengan menggunakan method-method dari 
         * class CovidCases, jika ada data yang tidak valid maka line border JTextField akan memiliki warna merah, Tapi jika 
         * valid makan akan memiliki warna biru, Data akan dicek satu-persatu hingga semua data yang diinputkan valid semua.
         * Jika data sudah valid semua maka variabel isValids nilai-nya akan berubah menjadi True
         */
        
        // mengecek data singkatan provinsi valid atatu tidak
        if(dataIndo.isValidSingkatanProv(singkatanProv)){
            changeColor(this.inpSingkatanProv, this.lblSingkatanProv, true);
            // mengecek data nama provinsi valid atau tidak
            if(dataIndo.isValidNamaProvinsi(provinsi)){
                changeColor(this.inpNamaProv, this.lblNamaProv, true);
                // mengecek data positif valid atau tidak
                if(dataIndo.isValidPositif((int)populasi, positif)){
                    changeColor(this.inpPositif, this.lblPositif, true);
                    // mengecek data populasi valid atatu tidak
                    if(dataIndo.isValidPopulasi(positif, populasi)){
                        changeColor(this.inpPopulasi, this.lblPopulasi, true);
                        // mengecek data kasus sembuh valid atau tidak
                        if(dataIndo.isValidSembuh(positif, sembuh)){
                            changeColor(this.inpSembuh, this.lblSembuh, true);
                            // mengecek data kasus kematian valid atau tidak
                            if(dataIndo.isValidKematian(positif, sembuh)){
                                changeColor(this.inpKematian, this.lblKematian, true);
                                // mengecek data kasus aktif valid atau tidak
                                if(dataIndo.isValidAktif(positif, aktif)){
                                    changeColor(this.inpAktif, this.lblAktif, true);
                                    // mengecek website valid atau tidak
                                    if(dataIndo.isValidWebsite(website)){
                                        changeColor(this.inpWebsite, this.lblWebsite, true);
                                        // mengecek data total kabupaten valid atu tidak
                                        if(dataIndo.isValidTotalKab(this.totalKab)){
                                            changeColor(this.inpTotalKab, this.lblTotalkab, true);
                                            // mengecek apakah data zona merah, oren dan hijau valid atau tidak
                                            if(dataIndo.isValidZona(totalKab, (zonaMerah + zonaOren + zonaHijau))){
                                                changeColor(this.inpZonaHijau, this.lblZonaHijau, true);
                                                changeColor(this.inpZonaOren, this.lblZonaOren, true);
                                                changeColor(this.inpZonaMerah, this.lblZonaMerah, true);
                                                // jika semua data sudah valid maka isValids akan bernilai true
                                                isValids = true;
                                            }else{
                                                changeColor(this.inpZonaHijau, this.lblZonaHijau, false);
                                                changeColor(this.inpZonaOren, this.lblZonaOren, false);
                                                changeColor(this.inpZonaMerah, this.lblZonaMerah, false);
                                            }
                                        }else{
                                            changeColor(this.inpTotalKab, this.lblTotalkab, false);
                                        }
                                    }else{
                                        changeColor(this.inpWebsite, this.lblWebsite, false);
                                    }
                                }else{
                                    changeColor(this.inpAktif, this.lblAktif, false);
                                }
                            }else{
                                changeColor(this.inpKematian, this.lblKematian, false);
                            }
                        }else{
                            changeColor(this.inpSembuh, this.lblSembuh, false);
                        }
                    }else{
                        changeColor(this.inpPopulasi, this.lblPopulasi, false);
                    }
                }else{
                    changeColor(this.inpPositif, this.lblPositif, false);
                }
            }else{
                changeColor(this.inpNamaProv, this.lblNamaProv, false);
            }
        }else{
            changeColor(this.inpSingkatanProv, this.lblSingkatanProv, false);
        }
        


         /**
          * Proses selanjutnya adalah proses menyimpan data yang tadi diinputkan oleh user degan method seData() pada class CovidCases.
          * Data akan disave satu persatu hingga habis. Jika ada salah satu data yang gagal disimpan. Maka data-data yang lain direset ke 
          * data yang sebelumnya.
          */
        
        // mengatur cursor ke cursor loading
         this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
         
         // data akan ditambahkan jika isValids berniali True
         if(isValids){
             // membuat query untuk menambahkan data
             String sql = "INSERT INTO `kasuscovid_indo` (`kode`, `provinsi`, `kasus`, `sembuh`, `kematian`, `aktif`, `populasi`, `total_kab`, `kab_zonamerah`, `kab_zonaoranye`, `kab_zonahijau`, `kasus_pertama`, `diubah`, `website`, `lambang`) "
                        + "\nVALUES" 
                        + "\n('"+singkatanProv+"', '"+provinsi+"', "+positif+", "+sembuh+", "+kematian+", "+aktif+", "+populasi+", "+totalKab+", "+zonaMerah+", "+zonaOren+", "+zonaHijau+", '"+dataIndo.getDateNow()+"', '"+dataIndo.getDateNow()+"', '"+website+"', 'lambang-negara.jpeg')";
            
            // menambahkan data ke database
            try {
                // mengeksekusi query sql
                int isAdd = dataIndo.stat.executeUpdate(sql);
                
                // mengecek apakah data berhasil ditambahkan atau tidak
                if(isAdd > 0){
                    // mereset cursor ke cursor defautl
                    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    return true;
                }
            } catch (SQLException ex) {
                Audio.play(Audio.SOUND_ERROR);
                JOptionPane.showMessageDialog(null, "Terjadi keslahan saat akan menambahkan akun : " + ex.getMessage());
            }
         }
         
        
        // mereset cursor ke cursor defautl
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        return false; // akan mereturn false jika terjadi kesalahan saat mengedit/menyimpan data
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlLeft = new javax.swing.JPanel();
        lblLeft = new javax.swing.JLabel();
        btnBeranda = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();
        btnAddUser = new javax.swing.JButton();
        btnAddDataDunia = new javax.swing.JButton();
        btnAddDataIndo = new javax.swing.JButton();
        lblMinimaze = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblSingkatanProv = new javax.swing.JLabel();
        inpSingkatanProv = new javax.swing.JTextField();
        inpPopulasi = new javax.swing.JTextField();
        lblPopulasi = new javax.swing.JLabel();
        inpNamaProv = new javax.swing.JTextField();
        lblNamaProv = new javax.swing.JLabel();
        lblTop = new javax.swing.JLabel();
        inpKematian = new javax.swing.JTextField();
        lblKematian = new javax.swing.JLabel();
        inpPositif = new javax.swing.JTextField();
        lblPositif = new javax.swing.JLabel();
        lblProvinsi = new javax.swing.JLabel();
        lblSembuh = new javax.swing.JLabel();
        inpSembuh = new javax.swing.JTextField();
        inpAktif = new javax.swing.JTextField();
        lblAktif = new javax.swing.JLabel();
        inpWebsite = new javax.swing.JTextField();
        lblWebsite = new javax.swing.JLabel();
        line3 = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        line4 = new javax.swing.JSeparator();
        lblInpLambang = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();
        lblShowLambang = new javax.swing.JLabel();
        lblTotalkab = new javax.swing.JLabel();
        inpZonaMerah = new javax.swing.JTextField();
        lblZonaMerah = new javax.swing.JLabel();
        lblApp = new javax.swing.JLabel();
        lblCopyright = new javax.swing.JLabel();
        inpTotalKab = new javax.swing.JTextField();
        inpZonaHijau = new javax.swing.JTextField();
        lblZonaHijau = new javax.swing.JLabel();
        lblZonaOren = new javax.swing.JLabel();
        inpZonaOren = new javax.swing.JTextField();

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
        pnlLeft.setMaximumSize(new java.awt.Dimension(69, 32767));
        pnlLeft.setMinimumSize(new java.awt.Dimension(69, 10));

        lblLeft.setPreferredSize(new java.awt.Dimension(65, 200));
        pnlLeft.add(lblLeft);

        btnBeranda.setBackground(new java.awt.Color(49, 144, 215));
        btnBeranda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-beranda.png"))); // NOI18N
        btnBeranda.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnBeranda.setPreferredSize(new java.awt.Dimension(43, 43));
        btnBeranda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBerandaActionPerformed(evt);
            }
        });
        pnlLeft.add(btnBeranda);

        btnInfo.setBackground(new java.awt.Color(49, 144, 215));
        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-info.png"))); // NOI18N
        btnInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInfo.setPreferredSize(new java.awt.Dimension(43, 43));
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });
        pnlLeft.add(btnInfo);

        btnAddUser.setBackground(new java.awt.Color(49, 144, 215));
        btnAddUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-users.png"))); // NOI18N
        btnAddUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAddUser.setPreferredSize(new java.awt.Dimension(43, 43));
        btnAddUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddUserActionPerformed(evt);
            }
        });
        pnlLeft.add(btnAddUser);

        btnAddDataDunia.setBackground(new java.awt.Color(49, 144, 215));
        btnAddDataDunia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-kasusdunia.png"))); // NOI18N
        btnAddDataDunia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAddDataDunia.setPreferredSize(new java.awt.Dimension(43, 43));
        btnAddDataDunia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDataDuniaActionPerformed(evt);
            }
        });
        pnlLeft.add(btnAddDataDunia);

        btnAddDataIndo.setBackground(new java.awt.Color(34, 119, 237));
        btnAddDataIndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-kasusindo.png"))); // NOI18N
        btnAddDataIndo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAddDataIndo.setPreferredSize(new java.awt.Dimension(43, 43));
        btnAddDataIndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDataIndoActionPerformed(evt);
            }
        });
        pnlLeft.add(btnAddDataIndo);

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

        lblSingkatanProv.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSingkatanProv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSingkatanProv.setText("Singkatan Provinsi");

        inpSingkatanProv.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpSingkatanProv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpSingkatanProv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpSingkatanProv.setCaretColor(new java.awt.Color(255, 0, 0));
        inpSingkatanProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpSingkatanProvMouseClicked(evt);
            }
        });

        inpPopulasi.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpPopulasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpPopulasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpPopulasi.setCaretColor(new java.awt.Color(255, 0, 0));
        inpPopulasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpPopulasiMouseClicked(evt);
            }
        });

        lblPopulasi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPopulasi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPopulasi.setText("Jumlah Penduduk");

        inpNamaProv.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpNamaProv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpNamaProv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpNamaProv.setCaretColor(new java.awt.Color(255, 0, 0));
        inpNamaProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpNamaProvMouseClicked(evt);
            }
        });

        lblNamaProv.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaProv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaProv.setText("Nama Provinsi");

        lblTop.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Tambahkan Data Provinsi");

        inpKematian.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpKematian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpKematian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpKematian.setCaretColor(new java.awt.Color(255, 0, 0));
        inpKematian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpKematianMouseClicked(evt);
            }
        });

        lblKematian.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblKematian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKematian.setText("Kematian");

        inpPositif.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpPositif.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpPositif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpPositif.setCaretColor(new java.awt.Color(255, 0, 0));
        inpPositif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpPositifMouseClicked(evt);
            }
        });

        lblPositif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPositif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPositif.setText("Kasus Positif");

        lblProvinsi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblProvinsi.setText("Lambang Provinsi");

        lblSembuh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSembuh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSembuh.setText("Sembuh");

        inpSembuh.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpSembuh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpSembuh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpSembuh.setCaretColor(new java.awt.Color(255, 0, 0));
        inpSembuh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpSembuhMouseClicked(evt);
            }
        });

        inpAktif.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpAktif.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpAktif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpAktif.setCaretColor(new java.awt.Color(255, 0, 0));
        inpAktif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpAktifMouseClicked(evt);
            }
        });

        lblAktif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAktif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAktif.setText("Kasus Aktif");

        inpWebsite.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpWebsite.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpWebsite.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpWebsite.setCaretColor(new java.awt.Color(255, 0, 0));
        inpWebsite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpWebsiteMouseClicked(evt);
            }
        });

        lblWebsite.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblWebsite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWebsite.setText("Website Resmi");

        line3.setForeground(new java.awt.Color(0, 0, 0));

        btnSimpan.setBackground(new java.awt.Color(34, 119, 237));
        btnSimpan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Tambah");
        btnSimpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSimpanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSimpanMouseExited(evt);
            }
        });
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setBackground(new java.awt.Color(220, 41, 41));
        btnBatal.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setText("Batal");
        btnBatal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBatalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBatalMouseExited(evt);
            }
        });
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        line4.setForeground(new java.awt.Color(0, 0, 0));

        lblInpLambang.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblInpLambang.setText("Tambahkan Lambang");
        lblInpLambang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInpLambangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblInpLambangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblInpLambangMouseExited(evt);
            }
        });

        lblKembali.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-back.png"))); // NOI18N
        lblKembali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblKembaliMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblKembaliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblKembaliMouseExited(evt);
            }
        });

        lblShowLambang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShowLambang.setText("Lambang");
        lblShowLambang.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblTotalkab.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTotalkab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalkab.setText("Total Kabupaten");

        inpZonaMerah.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpZonaMerah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpZonaMerah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpZonaMerah.setCaretColor(new java.awt.Color(255, 0, 0));
        inpZonaMerah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpZonaMerahMouseClicked(evt);
            }
        });

        lblZonaMerah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblZonaMerah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblZonaMerah.setText("Kab. Zona Merah");

        lblApp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblApp.setText("App Covid-19 Pandemic");
        lblApp.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblCopyright.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCopyright.setForeground(new java.awt.Color(35, 34, 32));
        lblCopyright.setText("© 2020. Achmad Baihaqi. All Rights Reserved.");

        inpTotalKab.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpTotalKab.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpTotalKab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpTotalKab.setCaretColor(new java.awt.Color(255, 0, 0));
        inpTotalKab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpTotalKabMouseClicked(evt);
            }
        });

        inpZonaHijau.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpZonaHijau.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpZonaHijau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpZonaHijau.setCaretColor(new java.awt.Color(255, 0, 0));
        inpZonaHijau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpZonaHijauMouseClicked(evt);
            }
        });

        lblZonaHijau.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblZonaHijau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblZonaHijau.setText("Kab. Zona Hijau");

        lblZonaOren.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblZonaOren.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblZonaOren.setText("Kab. Zona Oranye");

        inpZonaOren.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpZonaOren.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpZonaOren.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpZonaOren.setCaretColor(new java.awt.Color(255, 0, 0));
        inpZonaOren.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpZonaOrenMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(inpSingkatanProv)
                                            .addComponent(lblSingkatanProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(inpPopulasi)
                                            .addComponent(lblPopulasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblShowLambang, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblInpLambang))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(lblSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(inpSembuh)
                                            .addComponent(inpAktif)
                                            .addComponent(lblAktif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(39, 39, 39))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(inpTotalKab, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(inpZonaOren, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNamaProv, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inpNamaProv, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(inpZonaMerah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                        .addComponent(lblZonaMerah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inpWebsite, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblWebsite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inpKematian, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblKematian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPositif, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inpPositif, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(inpZonaHijau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                        .addComponent(lblZonaHijau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblTop, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTotalkab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(line3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                                    .addComponent(lblZonaOren, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(22, 22, 22))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(438, 438, 438)
                        .addComponent(lblKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMinimaze)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(line4)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCopyright, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(34, 34, 34)
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBatal)))
                        .addGap(33, 33, 33))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblClose, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblMinimaze, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(line3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblShowLambang, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblProvinsi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInpLambang)))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblNamaProv)
                        .addGap(13, 13, 13)
                        .addComponent(inpNamaProv, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPositif)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inpPositif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblSingkatanProv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpSingkatanProv, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblPopulasi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inpPopulasi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKematian)
                    .addComponent(lblSembuh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpKematian, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpSembuh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWebsite)
                    .addComponent(lblAktif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpAktif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalkab)
                    .addComponent(lblZonaMerah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpZonaMerah, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpTotalKab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblZonaOren)
                    .addComponent(lblZonaHijau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpZonaHijau, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpZonaOren, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(line4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSimpan)
                            .addComponent(btnBatal))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblApp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(lblCopyright)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        isPlay = false;
        dataIndo.closeConnection();
        System.out.println("Menutup Window UpdateCovidDunia");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dataIndo.closeConnection();
        System.out.println("-->     APLIKASI DITUTUP");
    }//GEN-LAST:event_formWindowClosing

    private void pnlMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlMainMousePressed

    private void pnlMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMouseDragged
        int xx = evt.getXOnScreen(),
        yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_pnlMainMouseDragged

    private void inpZonaMerahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpZonaMerahMouseClicked

    }//GEN-LAST:event_inpZonaMerahMouseClicked

    private void lblKembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseExited
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK));
    }//GEN-LAST:event_lblKembaliMouseExited

    private void lblKembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseEntered
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK_ENTERED));
    }//GEN-LAST:event_lblKembaliMouseEntered

    private void lblKembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseClicked
        System.out.println("Membuka Window AddDataIndo");
        UpdateCovidIndo updateIndo = new UpdateCovidIndo();
        updateIndo.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                updateIndo.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_lblKembaliMouseClicked

    private void lblInpLambangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInpLambangMouseExited
        this.lblInpLambang.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0);\">Tambahkan Bendera</p></html>");
        this.lblInpLambang.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblInpLambangMouseExited

    private void lblInpLambangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInpLambangMouseEntered
        this.lblInpLambang.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Tambahkan Bendera</p></html>");
        this.lblInpLambang.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblInpLambangMouseEntered

    private void lblInpLambangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInpLambangMouseClicked
        Audio.play(Audio.SOUND_INFO);
        JOptionPane.showMessageDialog(null, "Fitur 'Edit Bendera' untuk saat ini belum tersedia!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblInpLambangMouseClicked

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        System.out.println("Membuka Window AddDataIndo");
        UpdateCovidIndo updateIndo = new UpdateCovidIndo();
        updateIndo.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                updateIndo.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setBackground(new Color(220,41,41));
        this.btnSimpan.setBackground(new Color(34,119,237));
    }//GEN-LAST:event_btnBatalMouseExited

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setBackground(new Color(31,34,38));
        this.btnSimpan.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        boolean isSave = this.tambahData();
        // jika isSave bernilai True maka data berhasil disimpan
        if(isSave){
            Audio.play(Audio.SOUND_INFO);
            JOptionPane.showMessageDialog(null, "Selamat data berhasil ditambakan ke Database!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            
            // mereset window AddDataUser dengan menutup dan membuka window kembali
            System.out.println("Membuka Window AddDataIndo");
            AddDataIndo addData = new AddDataIndo();
            addData.setLocation(this.getX(), this.getY());

            java.awt.EventQueue.invokeLater(new Runnable(){

                @Override
                public void run(){
                    addData.setVisible(true);
                }
            });
            dispose();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
        this.btnSimpan.setBackground(new Color(34,119,237));
        this.btnBatal.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
        this.btnSimpan.setBackground(new Color(31,34,38));
        this.btnBatal.setBackground(new Color(34,119,237));
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void inpWebsiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpWebsiteMouseClicked

    }//GEN-LAST:event_inpWebsiteMouseClicked

    private void inpAktifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpAktifMouseClicked

    }//GEN-LAST:event_inpAktifMouseClicked

    private void inpSembuhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpSembuhMouseClicked

    }//GEN-LAST:event_inpSembuhMouseClicked

    private void inpPositifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpPositifMouseClicked

    }//GEN-LAST:event_inpPositifMouseClicked

    private void inpKematianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpKematianMouseClicked

    }//GEN-LAST:event_inpKematianMouseClicked

    private void inpNamaProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpNamaProvMouseClicked

    }//GEN-LAST:event_inpNamaProvMouseClicked

    private void inpPopulasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpPopulasiMouseClicked

    }//GEN-LAST:event_inpPopulasiMouseClicked

    private void inpSingkatanProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpSingkatanProvMouseClicked

    }//GEN-LAST:event_inpSingkatanProvMouseClicked

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

    private void btnAddDataIndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDataIndoActionPerformed

    }//GEN-LAST:event_btnAddDataIndoActionPerformed

    private void btnAddDataDuniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDataDuniaActionPerformed
        System.out.println("Membuka Window AddADataDunia");
        AddDataDunia addData = new AddDataDunia();
        addData.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                addData.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnAddDataDuniaActionPerformed

    private void btnAddUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddUserActionPerformed
        System.out.println("Membuka Window AddDataUser");
        AddDataUser addData = new AddDataUser();
        addData.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
        
            @Override
            public void run(){
                addData.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnAddUserActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
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
    }//GEN-LAST:event_btnInfoActionPerformed

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

    private void inpTotalKabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpTotalKabMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_inpTotalKabMouseClicked

    private void inpZonaHijauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpZonaHijauMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_inpZonaHijauMouseClicked

    private void inpZonaOrenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpZonaOrenMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_inpZonaOrenMouseClicked

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
            java.util.logging.Logger.getLogger(AddDataIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDataIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDataIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDataIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddDataIndo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDataDunia;
    private javax.swing.JButton btnAddDataIndo;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBeranda;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JTextField inpAktif;
    private javax.swing.JTextField inpKematian;
    private javax.swing.JTextField inpNamaProv;
    private javax.swing.JTextField inpPopulasi;
    private javax.swing.JTextField inpPositif;
    private javax.swing.JTextField inpSembuh;
    private javax.swing.JTextField inpSingkatanProv;
    private javax.swing.JTextField inpTotalKab;
    private javax.swing.JTextField inpWebsite;
    private javax.swing.JTextField inpZonaHijau;
    private javax.swing.JTextField inpZonaMerah;
    private javax.swing.JTextField inpZonaOren;
    private javax.swing.JLabel lblAktif;
    private javax.swing.JLabel lblApp;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblInpLambang;
    private javax.swing.JLabel lblKematian;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblLeft;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamaProv;
    private javax.swing.JLabel lblPopulasi;
    private javax.swing.JLabel lblPositif;
    private javax.swing.JLabel lblProvinsi;
    private javax.swing.JLabel lblSembuh;
    private javax.swing.JLabel lblShowLambang;
    private javax.swing.JLabel lblSingkatanProv;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblTotalkab;
    private javax.swing.JLabel lblWebsite;
    private javax.swing.JLabel lblZonaHijau;
    private javax.swing.JLabel lblZonaMerah;
    private javax.swing.JLabel lblZonaOren;
    private javax.swing.JSeparator line3;
    private javax.swing.JSeparator line4;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
