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
 * @since 2020-12-09
 */
public class AddDataDunia extends javax.swing.JFrame {

    /**
     * Digunakan untuk menambahkan sebuah data ke database
     */
    private final CovidCases dataDunia = new CovidCases(CovidCases.KASUS_DUNIA);
    /**
     * Digunakan untuk menyimpan data dari kasus covid dunia yang bertipe <code>String</code>
     */
    private String negara_idn, negara_eng, diubah, benua, bendera;
    /**
     * Digunakan untuk menyimpan data dari kasus covid dunia yang bertipe <code>Integer</code>
     */
    private int positif, sembuh, kematian, kritis, aktif;            
    /**
     * Digunakan untuk menyimpan data dari kasus covid dunia yang bertipe <code>Long</code>
     */
    private long populasi;
    /**
     * Digunakan untuk mengatur posisi dari window
     */
    private int x, y;
    /**
     * Digunakan untuk memainkan efek copyright
     */
    private boolean isPlay = true;
    
    public AddDataDunia() {
        initComponents();
        
        this.setIconImage(Gambar.getWindowIcon());
        this.setLocationRelativeTo(null);
        this.lblShowBendera.setText("");
        this.lblShowBendera.setIcon(Gambar.getFlag(dataDunia.getData(CovidCases.BENDERA, "Dunia")));
        this.inpDiubah.setText(dataDunia.dateToString(dataDunia.getDateNow()));
        this.inpDiubah.setEditable(false);
        this.inpDiubah.setEnabled(false);
        this.inpDiubah.setDisabledTextColor(new Color(0,0,0));
        
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
            this.btnBeranda, this.btnInfo, this.btnAddUser, this.btnAddDataIndo
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
                }else if(dataDunia.isNumber(dataDunia.removeDelim(input.getText()))){
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
     * Method ini digunakan untuk menambahkan sebuah data kasus covid dunia ke Database. 
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
        
        // mengecek input dari negara_idn kosong atau tidak
        if(this.inpNegara_IDN.getText().equals("")){
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Input dari Negara IDN (Nama Negara dalam bahasa Indonesia) \nTidak boleh kosong!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            changeColor(this.inpNegara_IDN, this.lblNamaNegara_IDN, false);
            return false;
        }else{
            changeColor(this.inpNegara_IDN, this.lblNamaNegara_IDN, true);
        }
        // mengecek input dari negara_eng kosong atau tidak
        if(this.inpNegara_ENG.getText().equals("")){
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Input dari Negara ENG (Nama Negara dalam bahasa Inggris) \nTidak boleh kosong!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            changeColor(this.inpNegara_ENG, this.lblNamaNegara_ENG, false);
            return false;
        }else{
            changeColor(this.inpNegara_ENG, this.lblNamaNegara_ENG, true);
        }
        // mengecek input dari populasi dunia
        if(this.isEmptyInput(this.inpPopulasi, "populasi")){
            changeColor(this.inpPopulasi, this.lblPopulasi, false);
            return false;
        }else{
            changeColor(this.inpPopulasi, this.lblPopulasi, true);
            this.populasi = Long.parseLong(dataDunia.removeDelim(this.inpPopulasi.getText())); // mengambil data populasi
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
            this.positif = Integer.parseInt(dataDunia.removeDelim(this.inpPositif.getText())); // mengambil data kasus positif
        }
        // mengecek input dari kasus kematian
        if(this.isEmptyInput(this.inpKematian, "kasus kematian")){
            changeColor(this.inpKematian, this.lblKematian, false);
            return false;
        }else{
            changeColor(this.inpKematian, this.lblKematian, true);
            this.kematian = Integer.parseInt(dataDunia.removeDelim(this.inpKematian.getText())); // mengambil data kasus kematian
        }
        // mengecek input dari kasus sembuh
        if(this.isEmptyInput(this.inpSembuh, "kasus sembuh")){
            changeColor(this.inpSembuh, this.lblSembuh, false);
            return false;
        }else{
            changeColor(this.inpSembuh, this.lblSembuh, true);
            this.sembuh = Integer.parseInt(dataDunia.removeDelim(this.inpSembuh.getText())); // mengambil data kasus sembuh
        }
        // mengecek input dari kasus aktif
        if(this.isEmptyInput(this.inpAktif, "kasus aktif")){
            changeColor(this.inpAktif, this.lblAktif, false);
            return false;
        }else{
            changeColor(this.inpAktif, this.lblAktif, true);
            this.aktif = Integer.parseInt(dataDunia.removeDelim(this.inpAktif.getText())); // mengambil data kasus aktif
        }
        // mengecek input dari kasus kritis
        if(this.isEmptyInput(this.inpKritis, "kasus kritis")){
            changeColor(this.inpKritis, this.lblKritis, false);
            return false;
        }else{
            changeColor(this.inpKritis, this.lblKritis, true);
            this.kritis = Integer.parseInt(dataDunia.removeDelim(this.inpKritis.getText())); // mengambi data kasus kritis
        }

        // mendapatkan data yang bertipe data String
        this.negara_idn = this.inpNegara_IDN.getText();
        this.negara_eng = this.inpNegara_ENG.getText();
        this.diubah = dataDunia.getDateNow();
        
        // mendapatkan data yg dari JComboBox
        switch(this.inpBenua.getSelectedIndex()){
            case 0: this.benua = "null"; break; 
            case 1: this.benua = "Asia"; break; 
            case 2: this.benua = "Afrika"; break; 
            case 3: this.benua = "Amerika Selatan"; break; 
            case 4: this.benua = "Amerika Utara"; break; 
            case 5: this.benua = "Eropa"; break; 
            case 6: this.benua = "Oceania"; break; 
            default : this.benua = "null";
        }
        
        /**
         * Mengecek apakah sebuah data yg diinputkan oleh user valid atau tidak dengan menggunakan method-method dari 
         * class CovidCases, jika ada data yang tidak valid maka line border JTextField akan memiliki warna merah, Tapi jika 
         * valid makan akan memiliki warna biru, Data akan dicek satu-persatu hingga semua data yang diinputkan valid semua.
         * Jika data sudah valid semua maka variabel isValids nilai-nya akan berubah menjadi True
         */
        
        // mengecek apakah negara_idn valid atau tidak
        if(dataDunia.isValidNamaNegara(negara_idn)){
            changeColor(this.inpNegara_IDN, this.lblNamaNegara_IDN, true);
            // mengecek apakah negara_eng valid atau tidak
            if(dataDunia.isValidNamaNegara(negara_eng)){
                changeColor(this.inpNegara_ENG, this.lblNamaNegara_ENG, true);
                // mengecek apakah data dari kasus positif valid atau tidak
                if(dataDunia.isValidPositif((int)populasi, positif)){
                    changeColor(this.inpPositif, this.lblPositif, true);
                    // mengecek apakah data dari kasus kematian valid atau tidak
                    if(dataDunia.isValidKematian(positif, kematian)){
                        changeColor(this.inpKematian, this.lblKematian, true);
                        // mengecek apakah data dari kasus sembuh valid atau tidak
                        if(dataDunia.isValidSembuh(positif, sembuh)){
                            changeColor(this.inpSembuh, this.lblSembuh, true);
                            // mengecek apakah data dari kasus aktif valid atau tidak
                            if(dataDunia.isValidAktif(positif, aktif)){
                                changeColor(this.inpAktif, this.lblAktif, true);
                                // mengecek apakah data dari kasus kritis valid atau tidak
                                if(dataDunia.isValidKritis(positif, kritis)){
                                    changeColor(this.inpKritis, this.lblKritis, true);
                                    // mengecek apakah data dari populasi valid atau tidak
                                    if(dataDunia.isValidPopulasi(positif, populasi)){
                                        changeColor(this.inpPopulasi, this.lblPopulasi, true);
                                        // mengecek apakah benua valid atau tidak
                                        if(dataDunia.isValidBenua(benua)){
                                            this.inpBenua.setForeground(new Color(0,0,0));
                                            // data sudah valid semua dan variabel isValids bernilai True
                                            isValids = true; 
                                        }else{
                                            this.inpBenua.setForeground(new Color(255,0,0));
                                        }
                                    }else{
                                        changeColor(this.inpPopulasi, this.lblPopulasi, false);
                                    }
                                }else{
                                    changeColor(this.inpKritis, this.lblKritis, false);
                                }
                            }else{
                                changeColor(this.inpAktif, this.lblAktif, false);
                            }
                        }else{
                            changeColor(this.inpSembuh, this.lblSembuh, false);
                        }
                    }else{
                        changeColor(this.inpKematian, this.lblKematian, false);
                    }
                }else{
                    changeColor(this.inpPositif, this.lblPositif, false);
                }

            }else{
                changeColor(this.inpNegara_ENG, this.lblNamaNegara_ENG, false);
            }
        }else{
            changeColor(this.inpNegara_IDN, this.lblNamaNegara_IDN, false);
        }
        

         /**
          * Proses selanjutnya adalah proses menyimpan data yang tadi diinputkan oleh user degan method seData() pada class CovidCases.
          * Data akan disave satu persatu hingga habis. Jika ada salah satu data yang gagal disimpan. Maka data-data yang lain direset ke 
          * data yang sebelumnya.
          */

         // mengatur cursor ke cursor loading
         this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            
         // jika isValids bernilai True maka data akan ditambahkan
         if(isValids){
             // membuat query untuk menambahkan data
             String sql = "INSERT INTO `kasuscovid_dunia` (`negara_idn`, `negara_eng`, `kasus`, `kematian`, `sembuh`, `aktif`, `kritis`, `populasi`, `diubah`, `benua`, `bendera`) "
                        + "\nVALUES" 
                        + "\n('"+negara_idn+"', '"+negara_eng+"', "+positif+", "+kematian+", "+sembuh+", "+aktif+", "+kritis+", "+populasi+", '"+dataDunia.getDateNow()+"', '"+benua+"', 'bendera-perserikatan-bangsa-bangsa.png')";
             
            // menambahkan data ke database
            try {
                // mengeksekusi query sql
                int isAdd = dataDunia.stat.executeUpdate(sql);
                
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
        lblNamaNegara_IDN = new javax.swing.JLabel();
        inpNegara_IDN = new javax.swing.JTextField();
        inpPopulasi = new javax.swing.JTextField();
        lblPopulasi = new javax.swing.JLabel();
        inpNegara_ENG = new javax.swing.JTextField();
        lblNamaNegara_ENG = new javax.swing.JLabel();
        lblTop = new javax.swing.JLabel();
        inpSembuh = new javax.swing.JTextField();
        lblSembuh = new javax.swing.JLabel();
        inpPositif = new javax.swing.JTextField();
        lblPositif = new javax.swing.JLabel();
        lblBendera = new javax.swing.JLabel();
        lblKematian = new javax.swing.JLabel();
        inpKematian = new javax.swing.JTextField();
        inpAktif = new javax.swing.JTextField();
        lblAktif = new javax.swing.JLabel();
        inpKritis = new javax.swing.JTextField();
        lblKritis = new javax.swing.JLabel();
        line3 = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        line4 = new javax.swing.JSeparator();
        lblInpBendera = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();
        lblShowBendera = new javax.swing.JLabel();
        lblBenua = new javax.swing.JLabel();
        inpDiubah = new javax.swing.JTextField();
        lblDiubah = new javax.swing.JLabel();
        inpBenua = new javax.swing.JComboBox();
        lblApp = new javax.swing.JLabel();
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

        btnAddDataDunia.setBackground(new java.awt.Color(34, 119, 237));
        btnAddDataDunia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-kasusdunia.png"))); // NOI18N
        btnAddDataDunia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAddDataDunia.setPreferredSize(new java.awt.Dimension(43, 43));
        btnAddDataDunia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDataDuniaActionPerformed(evt);
            }
        });
        pnlLeft.add(btnAddDataDunia);

        btnAddDataIndo.setBackground(new java.awt.Color(49, 144, 215));
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

        lblNamaNegara_IDN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaNegara_IDN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaNegara_IDN.setText("Nama Negara (IDN)");

        inpNegara_IDN.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpNegara_IDN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpNegara_IDN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpNegara_IDN.setCaretColor(new java.awt.Color(255, 0, 0));
        inpNegara_IDN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpNegara_IDNMouseClicked(evt);
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
        lblPopulasi.setText("Populasi Negara");

        inpNegara_ENG.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpNegara_ENG.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpNegara_ENG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpNegara_ENG.setCaretColor(new java.awt.Color(255, 0, 0));
        inpNegara_ENG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpNegara_ENGMouseClicked(evt);
            }
        });

        lblNamaNegara_ENG.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaNegara_ENG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaNegara_ENG.setText("Nama Negara (ENG)");

        lblTop.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Tambahkan Data Negara");

        inpSembuh.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpSembuh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpSembuh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpSembuh.setCaretColor(new java.awt.Color(255, 0, 0));
        inpSembuh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpSembuhMouseClicked(evt);
            }
        });

        lblSembuh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSembuh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSembuh.setText("Sembuh");

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

        lblBendera.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblBendera.setText("Bendera Negara");

        lblKematian.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblKematian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKematian.setText("Kematian");

        inpKematian.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpKematian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpKematian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpKematian.setCaretColor(new java.awt.Color(255, 0, 0));
        inpKematian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpKematianMouseClicked(evt);
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

        inpKritis.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpKritis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpKritis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpKritis.setCaretColor(new java.awt.Color(255, 0, 0));
        inpKritis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpKritisMouseClicked(evt);
            }
        });

        lblKritis.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblKritis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKritis.setText("Kasus Kritis");

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

        lblInpBendera.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblInpBendera.setText("Tambahkan Bendera");
        lblInpBendera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblInpBenderaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblInpBenderaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblInpBenderaMouseExited(evt);
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

        lblShowBendera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShowBendera.setText("Bendera");
        lblShowBendera.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblBenua.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblBenua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBenua.setText("Benua");

        inpDiubah.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpDiubah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpDiubah.setText("09 Desember 2020");
        inpDiubah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpDiubah.setCaretColor(new java.awt.Color(255, 0, 0));
        inpDiubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpDiubahMouseClicked(evt);
            }
        });

        lblDiubah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblDiubah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiubah.setText("Dibuat Pada");

        inpBenua.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpBenua.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                       null", "                       Asia", "                      Afrika", "               Amerika Selatan", "                Amerika Utara", "                      Eropa", "                    Oceania" }));
        inpBenua.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblApp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblApp.setText("App Covid-19 Pandemic");
        lblApp.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblCopyright.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCopyright.setText("© 2020. Achmad Baihaqi. All Rights Reserved.");

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
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inpNegara_IDN)
                                    .addComponent(lblNamaNegara_IDN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inpPopulasi)
                                    .addComponent(lblPopulasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblShowBendera, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblBendera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblInpBendera, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lblKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inpKematian)
                                    .addComponent(inpAktif)
                                    .addComponent(lblAktif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inpBenua, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(39, 39, 39)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNamaNegara_ENG, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inpNegara_ENG, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(inpDiubah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                        .addComponent(lblDiubah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inpKritis, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblKritis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inpSembuh, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblSembuh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblPositif, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inpPositif, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblTop, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblBenua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(line3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
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
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblBendera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInpBendera))
                    .addComponent(lblShowBendera, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblNamaNegara_ENG)
                        .addGap(13, 13, 13)
                        .addComponent(inpNegara_ENG, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPositif)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inpPositif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblNamaNegara_IDN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpNegara_IDN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblPopulasi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inpPopulasi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSembuh)
                    .addComponent(lblKematian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpSembuh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpKematian, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKritis)
                    .addComponent(lblAktif))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpKritis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpAktif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBenua)
                    .addComponent(lblDiubah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inpDiubah)
                    .addComponent(inpBenua, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
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
        dataDunia.closeConnection();
        System.out.println("Menutup Window UpdateCovidDunia");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dataDunia.closeConnection();
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

    private void inpDiubahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpDiubahMouseClicked
        Audio.play(Audio.SOUND_WARNING);
        JOptionPane.showMessageDialog(null, "Data ini tidak bisa diubah!", "Pesan", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_inpDiubahMouseClicked

    private void lblKembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseExited
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK));
    }//GEN-LAST:event_lblKembaliMouseExited

    private void lblKembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseEntered
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK_ENTERED));
    }//GEN-LAST:event_lblKembaliMouseEntered

    private void lblKembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseClicked
        System.out.println("Membuka Window UpdateCovidDunia");
        UpdateCovidDunia updateDunia = new UpdateCovidDunia();
        updateDunia.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                updateDunia.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_lblKembaliMouseClicked

    private void lblInpBenderaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInpBenderaMouseExited
        this.lblInpBendera.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0);\">Tambahkan Bendera</p></html>");
        this.lblInpBendera.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblInpBenderaMouseExited

    private void lblInpBenderaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInpBenderaMouseEntered
        this.lblInpBendera.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Tambahkan Bendera</p></html>");
        this.lblInpBendera.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblInpBenderaMouseEntered

    private void lblInpBenderaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblInpBenderaMouseClicked
        Audio.play(Audio.SOUND_INFO);
        JOptionPane.showMessageDialog(null, "Fitur 'Tambah Bendera' untuk saat ini belum tersedia!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblInpBenderaMouseClicked

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        System.out.println("Membuka Window UpdateCovidDunia");
        UpdateCovidDunia updateDunia = new UpdateCovidDunia();
        updateDunia.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                updateDunia.setVisible(true);
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

    private void inpKritisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpKritisMouseClicked

    }//GEN-LAST:event_inpKritisMouseClicked

    private void inpAktifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpAktifMouseClicked

    }//GEN-LAST:event_inpAktifMouseClicked

    private void inpKematianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpKematianMouseClicked

    }//GEN-LAST:event_inpKematianMouseClicked

    private void inpPositifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpPositifMouseClicked

    }//GEN-LAST:event_inpPositifMouseClicked

    private void inpSembuhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpSembuhMouseClicked

    }//GEN-LAST:event_inpSembuhMouseClicked

    private void inpNegara_ENGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpNegara_ENGMouseClicked

    }//GEN-LAST:event_inpNegara_ENGMouseClicked

    private void inpPopulasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpPopulasiMouseClicked

    }//GEN-LAST:event_inpPopulasiMouseClicked

    private void inpNegara_IDNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpNegara_IDNMouseClicked

    }//GEN-LAST:event_inpNegara_IDNMouseClicked

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
    }//GEN-LAST:event_btnAddDataIndoActionPerformed

    private void btnAddDataDuniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDataDuniaActionPerformed

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
            java.util.logging.Logger.getLogger(AddDataDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDataDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDataDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDataDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new AddDataDunia().setVisible(true);
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
    private javax.swing.JComboBox inpBenua;
    private javax.swing.JTextField inpDiubah;
    private javax.swing.JTextField inpKematian;
    private javax.swing.JTextField inpKritis;
    private javax.swing.JTextField inpNegara_ENG;
    private javax.swing.JTextField inpNegara_IDN;
    private javax.swing.JTextField inpPopulasi;
    private javax.swing.JTextField inpPositif;
    private javax.swing.JTextField inpSembuh;
    private javax.swing.JLabel lblAktif;
    private javax.swing.JLabel lblApp;
    private javax.swing.JLabel lblBendera;
    private javax.swing.JLabel lblBenua;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblDiubah;
    private javax.swing.JLabel lblInpBendera;
    private javax.swing.JLabel lblKematian;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblKritis;
    private javax.swing.JLabel lblLeft;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamaNegara_ENG;
    private javax.swing.JLabel lblNamaNegara_IDN;
    private javax.swing.JLabel lblPopulasi;
    private javax.swing.JLabel lblPositif;
    private javax.swing.JLabel lblSembuh;
    private javax.swing.JLabel lblShowBendera;
    private javax.swing.JLabel lblTop;
    private javax.swing.JSeparator line3;
    private javax.swing.JSeparator line4;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
