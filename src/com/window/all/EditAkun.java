package com.window.all;

import com.database.Account;
import com.database.CovidCases;
import com.media.gambar.Gambar;
import com.media.audio.Audio;

import java.awt.Color;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-12-12
 */
public class EditAkun extends javax.swing.JFrame {

    /**
     * Digunakan untuk mendapatkan dan mengedit data dari akun
     */
    private final Account dataUser = new Account();
    
    /**
     * Digunakan untuk menyimpan data dari akun
     */
    private String username, namaLengkap, namaPanggilan, email, gender, tanggalLahir, 
                   pekerjaan, alamat, negara, password, tanggalDibuat, fotoProfile, type;
    
    /**
     * Digunakan untuk menyimpan hasil dari edit data akun
     */
    private String eNamaLengkap, eNamaPanggilan, ePekerjaan, eAlamat, eNegara;
    
    private int x, y;
    
    public EditAkun() {
        initComponents();        
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnSimpan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.showData();
        
        // tampilan text akan berbeda antara user dan admin
        if(this.type.equalsIgnoreCase("User")){
            this.lblLoginSebagai.setText("Anda saat ini login sebagai User!");
            this.lblLoginAsDeveloper.setText("Coba login sebagai Admin / Developer?");
        }else{
            this.lblLoginSebagai.setText("Anda saat ini login sebagai Developer!");
            this.lblLoginAsDeveloper.setText("Copyright © 2020. Achmad Baihaqi");
        }
        
        // fields edit data
        JTextField[] fields = new JTextField[]{
            this.editUsername, this.editEmail, this.editTipeAkun, this.editTglDibuat, this.editPassword
        };
        // mengatur field data dari akun agar tidak bisa diedit
        for(JTextField data : fields){
            data.setEditable(false);
            data.setEnabled(false);
            data.setDisabledTextColor(new Color(0,0,0));
        }
    }
    
    /**
     * Method ini digunakan untuk melakukan perubahan data dari sebuah akun lalu akan menyimpan perbuhan tersebut. 
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
    private boolean editData(){
        // digunakan untuk mengecek apakah negara yang dimasukan ada atau tidak
        CovidCases dataNegara = new CovidCases(CovidCases.KASUS_DUNIA);
        // digunakan untuk mendapatkan tanggal lahir
        SimpleDateFormat fm = new SimpleDateFormat("YYYY-MM-dd");
        // isValid digunakan untuk mengecek apakah data yang diedit valid atau tidak
        // isSave digunakan untuk mengecek semua data sudah tersimpan atau belum
        boolean isValids = false, isSave = false;
        
        // mendapatkan data dari input JTextField
        namaLengkap = this.editNamalengkap.getText();
        namaPanggilan = this.editNamapanggilan.getText();
        alamat = this.editAlamat.getText();
        negara = this.editAsalNegara.getText();
        pekerjaan = this.editPekerjaan.getText();
        
        // mendapatkan data dari gender dari JComboBox
        switch(this.editGender.getSelectedIndex()){
            case 0: this.gender = "N"; break;
            case 1: this.gender = "L"; break;
            case 2: this.gender = "P"; break;
            default: this.gender = "N"; break;
        }
        
        
         /* mendapatkan data dari tanggal lahir dari JDateChooser
            error handling akan berguna saat input tanggal lahir kosong 
            jika input tanggal lahir kosong maka kemungkinan akan menghasilkan error
        */ 
        try{
             this.tanggalLahir = String.valueOf(fm.format(this.editTanggalLahir.getDate()));
        }catch(NullPointerException e){
            this.tanggalLahir = "0001-01-01";
            System.out.println("Terjadi kesalahan saat mengambil tanggal lahir : " + e.getMessage());
        }
        
        /**
         * Mengecek apakah sebuah data yg diinputkan oleh user valid atau tidak dengan menggunakan method-method dari 
         * class Account dan CovidCases, jika ada data yang tidak valid maka line border JTextField akan memiliki warna merah, Tapi jika 
         * valid makan akan memiliki warna biru, Data akan dicek satu-persatu hingga semua data yang diinputkan valid semua.
         * Jika data sudah valid semua maka variabel isValids nilai-nya akan berubah menjadi True
         */
        
        // mengecek username valid atau tidak
        if(dataUser.isValidUsername(this.username)){
            changeColor(this.editUsername, this.lblUsername, true);
            // mengecek email valid atau tidak
            if(dataUser.isValidEmail(this.email)){
                changeColor(this.editEmail, this.lblEmail, true);
                // mengecek nama lengkap valid atau tidak
                if(dataUser.isValidNamalengkap(this.namaLengkap)){
                    changeColor(this.editNamalengkap, this.lblNamalengkap, true);
                    // mengecek nama panggilan valid atau tidak
                    if(dataUser.isValidNamapanggilan(this.namaPanggilan)){
                        changeColor(this.editNamapanggilan, this.lblNamapanggilan, true);
                        // mengecek tanggal lahir valid atau tidak
                        if(dataUser.isValidUmur(tanggalLahir)){
                            // mengubah warna line border pada JDateChoose ke warna biru
                            this.editTanggalLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0,106,255)));
                            this.lblTanggalLahir.setForeground(new Color(0,0,0));
                            // mengecek apakah alamat valid atau tidak
                            if(dataUser.isValidAlamat(alamat)){
                                changeColor(this.editAlamat, this.lblAlamat, true);
                                // mengecek apakah negara ada atau tidak 
                                if(dataNegara.isExist(negara)){
                                    changeColor(this.editAsalNegara, this.lblAsalNegara, true);
                                    // mengecek apakah pekerjaan valid atau tidak
                                    if(dataUser.isValidPekerjaan(pekerjaan)){
                                        changeColor(this.editPekerjaan, this.lblPekerjaan, true);
                                            // mengecek apakah password valid atau tidak
                                        if(dataUser.isValidPassword(password)){
                                            changeColor(this.editPassword, this.lblPassword, true);
                                            // semua data sudah valid dan var isValids akan berubah menjadi True
                                            isValids = true;
                                        }else{
                                            changeColor(this.editPassword, this.lblPassword, false);
                                        }                                        
                                    }else{
                                        changeColor(this.editPekerjaan, this.lblPekerjaan, false);
                                    }
                                }else{
                                    Audio.play(Audio.SOUND_WARNING);
                                    JOptionPane.showMessageDialog(null, "'" + negara + "' negara tersebut tidak ditemukan!", "Warning", JOptionPane.WARNING_MESSAGE);
                                    changeColor(this.editAsalNegara, this.lblAsalNegara, false);
                                }
                            }else{
                                changeColor(this.editAlamat, this.lblAlamat, false);
                            }
                        }else{
                            // mengubah warna line border pada JDateChoose ke warna merah 
                            this.editTanggalLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255,0,0)));
                            this.lblTanggalLahir.setForeground(new Color(255,255,255));
                        }
                    }else{
                        changeColor(this.editNamapanggilan, this.lblNamapanggilan, false);
                    }
                }else{
                    changeColor(this.editNamalengkap, this.lblNamalengkap, false);
                }
            }else{
                changeColor(this.editEmail, this.lblEmail, false);
            }
        }else{
            changeColor(this.editUsername, this.lblUsername, false);
        }
        
        // mengubah cursor ke cursor loading
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        // mengecek apakah semua data yang diedit valid atau tidak, jika valid maka data akan disimpan
        if(isValids){
            // menyimpan data dari nama lengkap
            if(dataUser.editAccount(username, Account.NAMA_LENGKAP, eNamaLengkap)){
                // menyimpan data dari nama panggilan
                if(dataUser.editAccount(username, Account.NAMA_PANGGILAN, eNamaPanggilan)){
                    // menyimpan data dari alamat
                    if(dataUser.editAccount(username, Account.ALAMAT, eAlamat)){
                        // menyimpan data dari negara
                        if(dataUser.editAccount(username, Account.NEGARA, eNegara)){
                            // menyimpan data dari pekerjaan
                            if(dataUser.editAccount(username, Account.PEKERJAAN, ePekerjaan)){
                                // data sudah tersimpan semua dan variabel isSave bernilai True
                                isSave = true;   
                            }
                        }
                    }
                }
            }
        }
        
         // jika proses penyimpanan data berhasil
         if(isSave){
             // mereset cursor ke cursor defautl
             this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
             return true; // mengembalikan nilai true
         // jika proses penyimpanan data gagal
         }else if(isValids && !isSave){
             // mereset data yang diedit ke data sebelumnya
             dataUser.editAccount(this.username, Account.NAMA_LENGKAP, this.namaLengkap);
             dataUser.editAccount(this.username, Account.NAMA_PANGGILAN, this.namaPanggilan);
             dataUser.editAccount(this.username, Account.ALAMAT, this.alamat);
             dataUser.editAccount(this.username, Account.NEGARA, this.negara);
             dataUser.editAccount(this.username, Account.PEKERJAAN, this.pekerjaan);
         }
        
        // mereest cursor ke cursor default
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        return false; // akan mereturn false jika terjadi kesalahan akan menambahkan data
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
     * Digunakan untuk mendapatkan data dari akun melalui class Account 
     * Lalu datanya akan ditampilkan ke dalam window
     */
    private void showData(){
        // mendapatkan data dari akun
        this.username = dataUser.getActivedUser();
        this.namaLengkap = dataUser.getDataAccount(this.username, Account.NAMA_LENGKAP);
        this.namaPanggilan = dataUser.getDataAccount(this.username, Account.NAMA_PANGGILAN);
        this.email = dataUser.getDataAccount(this.username, Account.EMAIL);
        this.gender = dataUser.getDataAccount(this.username, Account.GENDER);
        this.tanggalLahir = dataUser.getDataAccount(this.username, Account.TGL_LAHIR);
        this.pekerjaan = dataUser.getDataAccount(this.username, Account.PEKERJAAN);
        this.alamat = dataUser.getDataAccount(this.username, Account.ALAMAT);
        this.negara = dataUser.getDataAccount(this.username, Account.NEGARA);
        this.password = dataUser.getDataAccount(this.username, Account.PASSWORD);
        this.tanggalDibuat = dataUser.getDataAccount(this.username, Account.TGL_DIBUAT);
        this.fotoProfile = dataUser.getDataAccount(this.username, Account.FOTO_PROFILE);
        this.type = dataUser.getDataAccount(this.username, Account.TYPE);
        
        // menampilkan data akun ke window
        this.editUsername.setText(username);
        this.editNamalengkap.setText(namaLengkap);
        this.editNamapanggilan.setText(namaPanggilan);
        this.editEmail.setText(email);
        this.editPekerjaan.setText(pekerjaan);
        this.editAlamat.setText(alamat);
        this.editAsalNegara.setText(negara);
        this.editPassword.setText(setVisiblePass(password));
        this.editTglDibuat.setText(dataUser.dateToString(tanggalDibuat));
        this.editTipeAkun.setText(type);
        
        // jika gender bernilai L maka akan menampilkan text 'Laki-Laki' jika berniali P akan menampilkan 'Perempuan'
        if(gender.equalsIgnoreCase("L")){
            this.editGender.setSelectedIndex(1);
        }else if(gender.equalsIgnoreCase("P")){
            this.editGender.setSelectedIndex(2);
        }else{
            this.editGender.setSelectedIndex(0);
        }
        
        // jika pekerjaan bernilai null maka akan ditampilkan menjadi 'Tidak Dicantumkan'
        if(pekerjaan.equalsIgnoreCase("null")){
            this.editPekerjaan.setText("Tidak Dicantumkan");
        }else{
            this.editPekerjaan.setText(pekerjaan);
        }
        
        // mendapatkan data tanggal lahir 
        try{
            int tahun = Integer.parseInt(tanggalLahir.substring(0,4)),
                bulan = Integer.parseInt(tanggalLahir.substring(5, 7)),
                tanggal = Integer.parseInt(tanggalLahir.substring(8));
            this.editTanggalLahir.setDate(new Date(tahun, bulan -1, tanggal));            
        }catch(java.lang.NumberFormatException e){
            this.editTanggalLahir.setDate(new Date(00, 01, 01));
        }
        
        System.out.println("");
    }
    
    private String setVisiblePass(final String pass){
        String buff = "";
        for (int i = 0; i < pass.length(); i++) {
            buff += "•";
        }
        return buff;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlLeft = new javax.swing.JPanel();
        lblLeft = new javax.swing.JLabel();
        lblMinimaze = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();
        lblTop = new javax.swing.JLabel();
        lblPhotoProfile = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        line1 = new javax.swing.JSeparator();
        btnBatal = new javax.swing.JButton();
        lblEditFoto = new javax.swing.JLabel();
        lblHapusFoto = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        editUsername = new javax.swing.JTextField();
        editNamalengkap = new javax.swing.JTextField();
        lblNamalengkap = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        lblTipeAkun = new javax.swing.JLabel();
        lblAlamat = new javax.swing.JLabel();
        editAlamat = new javax.swing.JTextField();
        editPekerjaan = new javax.swing.JTextField();
        lblPekerjaan = new javax.swing.JLabel();
        editTipeAkun = new javax.swing.JTextField();
        lblTanggalLahir = new javax.swing.JLabel();
        lblDibuat = new javax.swing.JLabel();
        lblAsalNegara = new javax.swing.JLabel();
        editAsalNegara = new javax.swing.JTextField();
        editPassword = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        editTglDibuat = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        editEmail = new javax.swing.JTextField();
        editNamapanggilan = new javax.swing.JTextField();
        lblNamapanggilan = new javax.swing.JLabel();
        line2 = new javax.swing.JSeparator();
        lblLoginAsDeveloper = new javax.swing.JLabel();
        lblLoginSebagai = new javax.swing.JLabel();
        editGender = new javax.swing.JComboBox();
        editTanggalLahir = new com.toedter.calendar.JDateChooser();

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
        pnlLeft.setMaximumSize(new java.awt.Dimension(69, 32767));
        pnlLeft.setMinimumSize(new java.awt.Dimension(69, 10));

        lblLeft.setPreferredSize(new java.awt.Dimension(65, 210));
        pnlLeft.add(lblLeft);

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

        btnSimpan.setBackground(new java.awt.Color(34, 119, 237));
        btnSimpan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan");
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

        lblTop.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Edit Informasi Akun");

        lblPhotoProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhotoProfile.setText("profile");
        lblPhotoProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProfile.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblProfile.setText("Photo Profile");

        line1.setForeground(new java.awt.Color(0, 0, 0));

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

        lblEditFoto.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblEditFoto.setText("Edit Foto");
        lblEditFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditFotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEditFotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEditFotoMouseExited(evt);
            }
        });

        lblHapusFoto.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblHapusFoto.setText("Hapus Foto");
        lblHapusFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHapusFotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHapusFotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHapusFotoMouseExited(evt);
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

        lblUsername.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Username");

        editUsername.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editUsername.setCaretColor(new java.awt.Color(255, 0, 0));
        editUsername.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editUsernameMouseClicked(evt);
            }
        });

        editNamalengkap.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNamalengkap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNamalengkap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNamalengkap.setCaretColor(new java.awt.Color(255, 0, 0));
        editNamalengkap.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editNamalengkap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editNamalengkapMouseClicked(evt);
            }
        });

        lblNamalengkap.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamalengkap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamalengkap.setText("Nama  Lengkap");

        lblGender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblGender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGender.setText("Jenis Kelamin");

        lblTipeAkun.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTipeAkun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipeAkun.setText("Tipe Akun");

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAlamat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlamat.setText("Asal Kota");

        editAlamat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAlamat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAlamat.setCaretColor(new java.awt.Color(255, 0, 0));
        editAlamat.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editAlamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editAlamatMouseClicked(evt);
            }
        });

        editPekerjaan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPekerjaan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPekerjaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPekerjaan.setCaretColor(new java.awt.Color(255, 0, 0));
        editPekerjaan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editPekerjaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editPekerjaanMouseClicked(evt);
            }
        });

        lblPekerjaan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPekerjaan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPekerjaan.setText("Pekerjaan");

        editTipeAkun.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTipeAkun.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTipeAkun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTipeAkun.setCaretColor(new java.awt.Color(255, 0, 0));
        editTipeAkun.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editTipeAkun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTipeAkunMouseClicked(evt);
            }
        });

        lblTanggalLahir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTanggalLahir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTanggalLahir.setText("Tanggal Lahir");

        lblDibuat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblDibuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDibuat.setText("Tanggal Dibuat");

        lblAsalNegara.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAsalNegara.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAsalNegara.setText("Asal Negara");

        editAsalNegara.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAsalNegara.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAsalNegara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAsalNegara.setCaretColor(new java.awt.Color(255, 0, 0));
        editAsalNegara.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editAsalNegara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editAsalNegaraMouseClicked(evt);
            }
        });

        editPassword.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPassword.setCaretColor(new java.awt.Color(255, 0, 0));
        editPassword.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editPasswordMouseClicked(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassword.setText("Password");

        editTglDibuat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTglDibuat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTglDibuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTglDibuat.setCaretColor(new java.awt.Color(255, 0, 0));
        editTglDibuat.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editTglDibuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTglDibuatMouseClicked(evt);
            }
        });

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("Email");

        editEmail.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editEmail.setCaretColor(new java.awt.Color(255, 0, 0));
        editEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editEmailMouseClicked(evt);
            }
        });

        editNamapanggilan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNamapanggilan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNamapanggilan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNamapanggilan.setCaretColor(new java.awt.Color(255, 0, 0));
        editNamapanggilan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editNamapanggilan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editNamapanggilanMouseClicked(evt);
            }
        });

        lblNamapanggilan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamapanggilan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamapanggilan.setText("Nama  Panggilan");

        line2.setForeground(new java.awt.Color(0, 0, 0));

        lblLoginAsDeveloper.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblLoginAsDeveloper.setText("Coba login sebagai Admin / Developer?");
        lblLoginAsDeveloper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoginAsDeveloperMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLoginAsDeveloperMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLoginAsDeveloperMouseExited(evt);
            }
        });

        lblLoginSebagai.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblLoginSebagai.setText("Anda saat ini login sebagai User!");

        editGender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                  Tidak Dicantumkan", "                           Laki-Laki", "                         Perempuan" }));
        editGender.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        editTanggalLahir.setBackground(new java.awt.Color(255, 255, 255));
        editTanggalLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTanggalLahir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTanggalLahir.setMaxSelectableDate(new java.util.Date(1609437701000L));
        editTanggalLahir.setMinSelectableDate(new java.util.Date(-2209010299000L));

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblKembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMinimaze, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                    .addComponent(lblPhotoProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addComponent(lblEditFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(lblHapusFoto)
                                            .addGap(0, 49, Short.MAX_VALUE)))))
                            .addComponent(line1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editUsername, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editNamalengkap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(lblNamalengkap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblGender, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTipeAkun, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAlamat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editAlamat, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editPekerjaan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(lblPekerjaan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editTipeAkun, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(editGender, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(67, 67, 67)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editNamapanggilan, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNamapanggilan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTanggalLahir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDibuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAsalNegara, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editAsalNegara, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editTglDibuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editTanggalLahir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(line2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLoginSebagai, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLoginAsDeveloper, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(92, 92, 92)
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBatal)))
                        .addContainerGap(20, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(line1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblProfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditFoto)
                            .addComponent(lblHapusFoto)))
                    .addComponent(lblPhotoProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblUsername)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNamalengkap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editNamalengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblGender)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editGender, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(lblAlamat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPekerjaan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTipeAkun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTipeAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNamapanggilan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editNamapanggilan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTanggalLahir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(lblAsalNegara)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editAsalNegara, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDibuat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTglDibuat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addComponent(line2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSimpan)
                        .addComponent(btnBatal))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblLoginSebagai, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLoginAsDeveloper)))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblMinimazeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseClicked
        this.setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimazeMouseClicked

    private void lblMinimazeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseEntered
        this.lblMinimaze.setIcon(Gambar.getIcon(Gambar.IC_MINIMAZE_ENTERED));
    }//GEN-LAST:event_lblMinimazeMouseEntered

    private void lblMinimazeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseExited
        this.lblMinimaze.setIcon(Gambar.getIcon(Gambar.IC_MINIMAZE_BLACK));
    }//GEN-LAST:event_lblMinimazeMouseExited

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCloseMouseClicked

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_ENTERED));
    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_BLACK));
    }//GEN-LAST:event_lblCloseMouseExited

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
//        this.btnEdit_Simpan.setBackground(new Color(33,123,39));
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
//        this.btnEdit_Simpan.setBackground(new Color(41,180,50));
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        boolean isSave = this.editData();
        // jika edit data berhasil maka window EditAkun akan ditutup dan window Informasi Akun akan dibuka
        if(isSave){
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
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setBackground(new Color(31,34,38));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnBatalMouseExited

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
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
    }//GEN-LAST:event_btnBatalActionPerformed

    private void lblEditFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditFotoMouseClicked
        Audio.play(Audio.SOUND_INFO);
        JOptionPane.showMessageDialog(null, "Fitur 'Edit Foto' untuk saat ini belum tesedia!!\n\nCopyright © 2020. Achmad Baihaqi.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblEditFotoMouseClicked

    private void lblEditFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditFotoMouseEntered
        this.lblEditFoto.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Edit Foto</p></html>");
        this.lblEditFoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblEditFotoMouseEntered

    private void lblEditFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditFotoMouseExited
        this.lblEditFoto.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0);\">Edit Foto</p></html>");
        this.lblEditFoto.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblEditFotoMouseExited

    private void lblHapusFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHapusFotoMouseClicked
        Audio.play(Audio.SOUND_INFO);
        JOptionPane.showMessageDialog(null, "Fitur 'Hapus Foto' untuk saat ini belum tesedia!!\n\nCopyright © 2020. Achmad Baihaqi.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblHapusFotoMouseClicked

    private void lblHapusFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHapusFotoMouseEntered
        this.lblHapusFoto.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Hapus Foto</p></html>");
        this.lblHapusFoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblHapusFotoMouseEntered

    private void lblHapusFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHapusFotoMouseExited
        this.lblHapusFoto.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0);\">Hapus Foto</p></html>");
        this.lblHapusFoto.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblHapusFotoMouseExited

    private void lblKembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseClicked
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
    }//GEN-LAST:event_lblKembaliMouseClicked

    private void lblKembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseEntered
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK_ENTERED));
    }//GEN-LAST:event_lblKembaliMouseEntered

    private void lblKembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseExited
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK));
    }//GEN-LAST:event_lblKembaliMouseExited

    private void pnlMainMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMouseDragged
        int xx = evt.getXOnScreen(),
        yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_pnlMainMouseDragged

    private void pnlMainMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMainMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_pnlMainMousePressed

    private void editUsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editUsernameMouseClicked

    }//GEN-LAST:event_editUsernameMouseClicked

    private void editNamalengkapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editNamalengkapMouseClicked

    }//GEN-LAST:event_editNamalengkapMouseClicked

    private void editAlamatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editAlamatMouseClicked

    }//GEN-LAST:event_editAlamatMouseClicked

    private void editPekerjaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPekerjaanMouseClicked

    }//GEN-LAST:event_editPekerjaanMouseClicked

    private void editTipeAkunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTipeAkunMouseClicked

    }//GEN-LAST:event_editTipeAkunMouseClicked

    private void editAsalNegaraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editAsalNegaraMouseClicked
    
    }//GEN-LAST:event_editAsalNegaraMouseClicked

    private void editPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPasswordMouseClicked
        System.out.println("Membuka Window UbahPassword");
        UbahPassword ubah = new UbahPassword(UbahPassword.EDIT_AKUN);
        ubah.setLocation(this.getX(), this.getY());
        System.out.println("Membuka Window UbahPassword");

        java.awt.EventQueue.invokeLater(new Runnable(){

            @Override
            public void run(){
                ubah.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_editPasswordMouseClicked

    private void editTglDibuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTglDibuatMouseClicked
      
    }//GEN-LAST:event_editTglDibuatMouseClicked

    private void editEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editEmailMouseClicked

    }//GEN-LAST:event_editEmailMouseClicked

    private void editNamapanggilanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editNamapanggilanMouseClicked

    }//GEN-LAST:event_editNamapanggilanMouseClicked

    private void lblLoginAsDeveloperMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginAsDeveloperMouseClicked
        if(this.type.equalsIgnoreCase("User")){
            dataUser.login("baihaqi", dataUser.getDataAccount("baihaqi", Account.PASSWORD));
            dispose();
            java.awt.EventQueue.invokeLater(new Runnable(){
                
                @Override
                public void run(){
                    new LoadingWindow().setVisible(true);
                }
            });
            Audio.play(Audio.SOUND_INFO);
            JOptionPane.showMessageDialog(null, "Anda saat ini login sebagai admin!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
        }else{
            
        }
    }//GEN-LAST:event_lblLoginAsDeveloperMouseClicked

    private void lblLoginAsDeveloperMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginAsDeveloperMouseEntered
        if(this.type.equalsIgnoreCase("User")){
            this.lblLoginAsDeveloper.setText("<html><p style=\"text-decoration:underline; color:rgb(18,62,215);\">Coba login sebagai Admin / Developer?</p></html>");
        }else{
            this.lblLoginAsDeveloper.setText("<html><p style=\"text-decoration:underline; color:rgb(18,62,215);\">Copyright © 2020. Achmad Baihaqi.</p></html>");
        }
        this.lblLoginAsDeveloper.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblLoginAsDeveloperMouseEntered

    private void lblLoginAsDeveloperMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginAsDeveloperMouseExited
        if(this.type.equalsIgnoreCase("User")){
            this.lblLoginAsDeveloper.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0) \">Coba login sebagai Admin / Developer?</p></html>");
        }else{
            this.lblLoginAsDeveloper.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0)\">Copyright © 2020. Achmad Baihaqi.</p></html>");
        }
        this.lblLoginAsDeveloper.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblLoginAsDeveloperMouseExited

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
            java.util.logging.Logger.getLogger(EditAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditAkun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JTextField editAlamat;
    private javax.swing.JTextField editAsalNegara;
    private javax.swing.JTextField editEmail;
    private javax.swing.JComboBox editGender;
    private javax.swing.JTextField editNamalengkap;
    private javax.swing.JTextField editNamapanggilan;
    private javax.swing.JTextField editPassword;
    private javax.swing.JTextField editPekerjaan;
    private com.toedter.calendar.JDateChooser editTanggalLahir;
    private javax.swing.JTextField editTglDibuat;
    private javax.swing.JTextField editTipeAkun;
    private javax.swing.JTextField editUsername;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblAsalNegara;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDibuat;
    private javax.swing.JLabel lblEditFoto;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHapusFoto;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblLeft;
    private javax.swing.JLabel lblLoginAsDeveloper;
    private javax.swing.JLabel lblLoginSebagai;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamalengkap;
    private javax.swing.JLabel lblNamapanggilan;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPekerjaan;
    private javax.swing.JLabel lblPhotoProfile;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblTanggalLahir;
    private javax.swing.JLabel lblTipeAkun;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JSeparator line1;
    private javax.swing.JSeparator line2;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
