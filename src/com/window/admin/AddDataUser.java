package com.window.admin;

import com.database.Account;
import com.media.audio.Audio;
import com.media.gambar.Gambar;
import com.window.all.Beranda;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-12-10
 */
public class AddDataUser extends javax.swing.JFrame {

    /**
     * Digunakan untuk mendapatkan dan mengedit data dari akun
     */
    private final Account acc = new Account();
    /**
     * Digunakan untuk menyimpan data dari akun
     */
    private String username, namaLengkap, namaPanggilan, email, gender, tanggalLahir, 
                   pekerjaan, alamat, negara, password, connPass, tanggalDibuat, fotoProfile, type;
    /**
     * Digunakan untuk mengatur posisi dari window
     */
    private int x, y;
    /**
     * Digunakan untuk memainkan efek copyright
     */
    private boolean isPlay = true;
    
    public AddDataUser() {
        initComponents();
        
        this.setIconImage(Gambar.getWindowIcon());
        this.setLocationRelativeTo(null);
        this.inpTanggalLahir.setDate(new Date(2003, 7, 4));
        
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
            this.btnBeranda, this.btnInfo, this.btnAddDataDunia, this.btnAddDataIndo
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
    private boolean tambahData(){
        // digunakan untuk mengecek apakah negara yang dimasukan ada atau tidak
        SimpleDateFormat fm = new SimpleDateFormat("YYYY-MM-dd");
        // isValid digunakan untuk mengecek apakah data yang akan ditambahkan  valid atau tidak
        // isExist digunakan untuk mengecek apakah negara yang dimasukan user ada atau tidak didalam database
        boolean isValids = false, isExist;
        
        // mendapatkan data dari input JTextField
        username = this.inpUsername.getText();
        namaLengkap = this.inpNamaLengkap.getText();
        namaPanggilan = this.inpNamaPanggilan.getText();
        email = this.inpEmail.getText();
        alamat = this.inpAlamat.getText();
        negara = this.inpNegara.getText();
        pekerjaan = this.inpPekerjaan.getText();
        password = this.inpPassword.getText();
        connPass = this.inpKonnPassword.getText();
        
        // mendapatkan data dari gender dari JComboBox
        switch(this.inpGender.getSelectedIndex()){
            case 0: this.gender = "N"; break;
            case 1: this.gender = "L"; break;
            case 2: this.gender = "P"; break;
            default: this.gender = "N"; break;
        }
        
        // mendapatkan data dari tipe akun
        switch(this.inpTipeAkun.getSelectedIndex()){
            case 0: this.type = "User"; break;
            case 1: this.type = "Admin"; break;
            case 2: this.type = "Developer"; break;
            default: this.type = "User";
        }
        
         /* mendapatkan data dari tanggal lahir dari JDateChooser
            error handling akan berguna saat input tanggal lahir kosong 
            jika input tanggal lahir kosong maka kemungkinan akan menghasilkan error
        */ 
        try{
             this.tanggalLahir = String.valueOf(fm.format(this.inpTanggalLahir.getDate()));
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
        if(acc.isValidUsername(this.username)){
            changeColor(this.inpUsername, this.lblUsername, true);
            // mengecek email valid atau tidak
            if(acc.isValidEmail(this.email)){
                changeColor(this.inpEmail, this.lblEmail, true);
                // mengecek nama lengkap valid atau tidak
                if(acc.isValidNamalengkap(this.namaLengkap)){
                    changeColor(this.inpNamaLengkap, this.lblNamaLengkap, true);
                    // mengecek nama panggilan valid atau tidak
                    if(acc.isValidNamapanggilan(this.namaPanggilan)){
                        changeColor(this.inpNamaPanggilan, this.lblNamaPanggilan, true);
                        // mengecek tanggal lahir valid atau tidak
                        if(acc.isValidUmur(tanggalLahir)){
                            // mengubah warna line border pada JDateChoose ke warna biru
                            this.inpTanggalLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0,106,255)));
                            this.lblTglLahir.setForeground(new Color(0,0,0));
                            // mengecek apakah alamat valid atau tidak
                            if(acc.isValidAlamat(alamat)){
                                changeColor(this.inpAlamat, this.lblAlamat, true);
                                // mengecek apakah negara ada atau tidak 
                                if(acc.isValidAsalNegara(negara)){
                                    changeColor(this.inpNegara, this.lblNegara, true);
                                    // mengecek apakah pekerjaan valid atau tidak
                                    if(acc.isValidPekerjaan(pekerjaan)){
                                        changeColor(this.inpPekerjaan, this.lblPekerjaan, true);
                                            // mengecek apakah password valid atau tidak
                                        if(acc.isValidPassword(password)){
                                            changeColor(this.inpPassword, this.lblPassword, true);
                                            // mengecek apakah password dan konfirmasi password cocok atau tidak
                                            if(password.equals(connPass)){
                                                changeColor(this.inpPassword, this.lblPassword, true);
                                                changeColor(this.inpKonnPassword, this.lblKonPassword, true);
                                                // semua data sudah valid dan var isValids akan berubah menjadi True
                                                isValids = true;
                                            }else{
                                                Audio.play(Audio.SOUND_WARNING);
                                                JOptionPane.showMessageDialog(null, "Password dan konfirmasi password tidak cocok!", "Warning", JOptionPane.WARNING_MESSAGE);
                                                changeColor(this.inpPassword, this.lblPassword, false);
                                                changeColor(this.inpKonnPassword, this.lblKonPassword, false);
                                            }
                                        }else{
                                            changeColor(this.inpPassword, this.lblPassword, false);
                                        }                                        
                                    }else{
                                        changeColor(this.inpPekerjaan, this.lblPekerjaan, false);
                                    }
                                }else{
                                    changeColor(this.inpNegara, this.lblNegara, false);
                                }
                            }else{
                                changeColor(this.inpAlamat, this.lblAlamat, false);
                            }
                        }else{
                            // mengubah warna line border pada JDateChoose ke warna merah 
                            this.inpTanggalLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255,0,0)));
                            this.lblTglLahir.setForeground(new Color(255,255,255));
                        }
                    }else{
                        changeColor(this.inpNamaPanggilan, this.lblNamaPanggilan, false);
                    }
                }else{
                    changeColor(this.inpNamaLengkap, this.lblNamaLengkap, false);
                }
            }else{
                changeColor(this.inpEmail, this.lblEmail, false);
            }
        }else{
            changeColor(this.inpUsername, this.lblUsername, false);
        }
        
        // mengubah cursor ke cursor loading
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        // data akan ditambahkan jika isValids bernilai True
        if(isValids){
            // membuat query untuk menambahkan data
            String sql = "INSERT INTO `users` "
                       + "(`username`, `namalengkap`, `namapanggilan`, `email`, `gender`, `tgl_lahir`, `pekerjaan`, `alamat`, `negara`, `password`, `tgl_dibuat`, `fotoprofile`, `type`) "
                       + "VALUES "
                       + "('"+username+"', '"+namaLengkap+"', '"+namaPanggilan+"', '"+email+"', '"+gender+"', '"+tanggalLahir+"', '"+pekerjaan+"', '"+alamat+"', '"+negara+"', '"+password+"', '"+acc.getDateNow()+"', 'default', '"+type+"');";
            
            // menambahkan akun ke database
            try {
                int isAdd = acc.stat.executeUpdate(sql);
                // mengecek apakah akun berhasil ditambahkan atau tidak
                if(isAdd > 0){
                    // mereest cursor ke cursor default
                    this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    return true;
                }
            } catch (SQLException ex) {
                Audio.play(Audio.SOUND_ERROR);
                JOptionPane.showMessageDialog(null, "Terjadi keslahan saat akan menambahkan akun : " + ex.getMessage());
            }
            
        }
        
        // mereest cursor ke cursor default
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        return false; // akan mereturn false jika terjadi kesalahan akan menambahkan data
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
        lblUsername = new javax.swing.JLabel();
        inpUsername = new javax.swing.JTextField();
        inpNamaLengkap = new javax.swing.JTextField();
        lblNamaLengkap = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        lblTop = new javax.swing.JLabel();
        lblTglLahir = new javax.swing.JLabel();
        inpNamaPanggilan = new javax.swing.JTextField();
        lblNamaPanggilan = new javax.swing.JLabel();
        lbProfile = new javax.swing.JLabel();
        lblGender = new javax.swing.JLabel();
        inpAlamat = new javax.swing.JTextField();
        lblAlamat = new javax.swing.JLabel();
        inpNegara = new javax.swing.JTextField();
        lblNegara = new javax.swing.JLabel();
        line3 = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        line4 = new javax.swing.JSeparator();
        inpAddFoto = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();
        lblShowProfile = new javax.swing.JLabel();
        lblPekerjaan = new javax.swing.JLabel();
        inpPassword = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblApp = new javax.swing.JLabel();
        lblCopyright = new javax.swing.JLabel();
        inpPekerjaan = new javax.swing.JTextField();
        lblTipeAkun = new javax.swing.JLabel();
        inpGender = new javax.swing.JComboBox();
        inpTipeAkun = new javax.swing.JComboBox();
        inpTanggalLahir = new com.toedter.calendar.JDateChooser();
        inpKonnPassword = new javax.swing.JTextField();
        lblKonPassword = new javax.swing.JLabel();

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

        btnAddUser.setBackground(new java.awt.Color(34, 119, 237));
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

        lblUsername.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Username");

        inpUsername.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpUsername.setCaretColor(new java.awt.Color(255, 0, 0));
        inpUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpUsernameMouseClicked(evt);
            }
        });

        inpNamaLengkap.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpNamaLengkap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpNamaLengkap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpNamaLengkap.setCaretColor(new java.awt.Color(255, 0, 0));
        inpNamaLengkap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpNamaLengkapMouseClicked(evt);
            }
        });

        lblNamaLengkap.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaLengkap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaLengkap.setText("Nama Lengkap");

        inpEmail.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpEmail.setCaretColor(new java.awt.Color(255, 0, 0));
        inpEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpEmailMouseClicked(evt);
            }
        });

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("Email");

        lblTop.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Tambahkan Pengguna Baru");

        lblTglLahir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTglLahir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTglLahir.setText("Tanggal Lahir");

        inpNamaPanggilan.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpNamaPanggilan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpNamaPanggilan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpNamaPanggilan.setCaretColor(new java.awt.Color(255, 0, 0));
        inpNamaPanggilan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpNamaPanggilanMouseClicked(evt);
            }
        });

        lblNamaPanggilan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaPanggilan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaPanggilan.setText("Nama Panggilan");

        lbProfile.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lbProfile.setText("Foto Profile");

        lblGender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblGender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGender.setText("Jenis Kelamin");

        inpAlamat.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpAlamat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpAlamat.setCaretColor(new java.awt.Color(255, 0, 0));
        inpAlamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpAlamatMouseClicked(evt);
            }
        });

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAlamat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlamat.setText("Asal Kota");

        inpNegara.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpNegara.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpNegara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpNegara.setCaretColor(new java.awt.Color(255, 0, 0));
        inpNegara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpNegaraMouseClicked(evt);
            }
        });

        lblNegara.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNegara.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNegara.setText("Asal Negara");

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

        inpAddFoto.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        inpAddFoto.setText("Tambahkan Foto");
        inpAddFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpAddFotoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inpAddFotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inpAddFotoMouseExited(evt);
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

        lblShowProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShowProfile.setText("Profile");
        lblShowProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblPekerjaan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPekerjaan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPekerjaan.setText("Pekejaan");

        inpPassword.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpPassword.setCaretColor(new java.awt.Color(255, 0, 0));
        inpPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpPasswordMouseClicked(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassword.setText("Buat Password");

        lblApp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblApp.setText("App Covid-19 Pandemic");
        lblApp.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblCopyright.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCopyright.setText("© 2020. Achmad Baihaqi. All Rights Reserved.");

        inpPekerjaan.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpPekerjaan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpPekerjaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpPekerjaan.setCaretColor(new java.awt.Color(255, 0, 0));
        inpPekerjaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpPekerjaanMouseClicked(evt);
            }
        });

        lblTipeAkun.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTipeAkun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipeAkun.setText("Tipe Akun");

        inpGender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        inpGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                  Tidak Dicantumkan", "                           Laki-Laki", "                         Perempuan" }));
        inpGender.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        inpTipeAkun.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        inpTipeAkun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                               User", "                             Admin" }));
        inpTipeAkun.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        inpTanggalLahir.setBackground(new java.awt.Color(255, 255, 255));
        inpTanggalLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpTanggalLahir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        inpTanggalLahir.setMaxSelectableDate(new java.util.Date(11833840901000L));
        inpTanggalLahir.setMinSelectableDate(new java.util.Date(-5364683899000L));

        inpKonnPassword.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpKonnPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inpKonnPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        inpKonnPassword.setCaretColor(new java.awt.Color(255, 0, 0));
        inpKonnPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inpKonnPasswordMouseClicked(evt);
            }
        });

        lblKonPassword.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblKonPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKonPassword.setText("Konfirmasi Password");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(inpUsername)
                                            .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(inpNamaLengkap)
                                            .addComponent(lblNamaLengkap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblShowProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(inpAddFoto))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(lblGender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(inpAlamat)
                                            .addComponent(lblAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(inpGender, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(39, 39, 39))
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(inpPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblTipeAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(inpTipeAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(inpEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(inpTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inpPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(lblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inpNegara)
                                    .addComponent(lblNegara, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTglLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblNamaPanggilan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inpNamaPanggilan)
                                    .addComponent(inpKonnPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                    .addComponent(lblKonPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(line4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblApp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCopyright, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBatal)))
                        .addGap(33, 33, 33))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblTop, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPekerjaan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(line3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGap(438, 438, 438)
                                .addComponent(lblKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMinimaze)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                    .addComponent(lblShowProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lbProfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inpAddFoto)))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addGap(13, 13, 13)
                                .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNamaPanggilan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inpNamaPanggilan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblUsername)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(inpUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblNamaLengkap)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(inpNamaLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTglLahir)
                            .addComponent(lblGender))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inpGender, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(inpTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNegara)
                    .addComponent(lblAlamat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpNegara, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPekerjaan)
                    .addComponent(lblPassword))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKonPassword)
                    .addComponent(lblTipeAkun))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpKonnPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inpTipeAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(line4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApp, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBatal)
                    .addComponent(btnSimpan))
                .addGap(3, 3, 3)
                .addComponent(lblCopyright)
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

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        isPlay = false;
        acc.closeConnection();
        System.out.println("Menutup Window UpdateCovidDunia");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        acc.closeConnection();
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

    private void inpPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpPasswordMouseClicked

    }//GEN-LAST:event_inpPasswordMouseClicked

    private void lblKembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseExited
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK));
    }//GEN-LAST:event_lblKembaliMouseExited

    private void lblKembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseEntered
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK_ENTERED));
    }//GEN-LAST:event_lblKembaliMouseEntered

    private void lblKembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseClicked
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
    }//GEN-LAST:event_lblKembaliMouseClicked

    private void inpAddFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpAddFotoMouseExited
        this.inpAddFoto.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0);\">Tambahkan Foto</p></html>");
        this.inpAddFoto.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_inpAddFotoMouseExited

    private void inpAddFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpAddFotoMouseEntered
        this.inpAddFoto.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Tambahkan Foto</p></html>");
        this.inpAddFoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_inpAddFotoMouseEntered

    private void inpAddFotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpAddFotoMouseClicked
        Audio.play(Audio.SOUND_INFO);
        JOptionPane.showMessageDialog(null, "Fitur 'Tambah Foto' untuk saat ini belum tersedia!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_inpAddFotoMouseClicked

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
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

    private void inpNegaraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpNegaraMouseClicked

    }//GEN-LAST:event_inpNegaraMouseClicked

    private void inpAlamatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpAlamatMouseClicked

    }//GEN-LAST:event_inpAlamatMouseClicked

    private void inpNamaPanggilanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpNamaPanggilanMouseClicked

    }//GEN-LAST:event_inpNamaPanggilanMouseClicked

    private void inpEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpEmailMouseClicked

    }//GEN-LAST:event_inpEmailMouseClicked

    private void inpNamaLengkapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpNamaLengkapMouseClicked

    }//GEN-LAST:event_inpNamaLengkapMouseClicked

    private void inpUsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpUsernameMouseClicked

    }//GEN-LAST:event_inpUsernameMouseClicked

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

    private void inpPekerjaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpPekerjaanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_inpPekerjaanMouseClicked

    private void inpKonnPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inpKonnPasswordMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_inpKonnPasswordMouseClicked

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
            java.util.logging.Logger.getLogger(AddDataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddDataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddDataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddDataUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new AddDataUser().setVisible(true);
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
    private javax.swing.JLabel inpAddFoto;
    private javax.swing.JTextField inpAlamat;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JComboBox inpGender;
    private javax.swing.JTextField inpKonnPassword;
    private javax.swing.JTextField inpNamaLengkap;
    private javax.swing.JTextField inpNamaPanggilan;
    private javax.swing.JTextField inpNegara;
    private javax.swing.JTextField inpPassword;
    private javax.swing.JTextField inpPekerjaan;
    private com.toedter.calendar.JDateChooser inpTanggalLahir;
    private javax.swing.JComboBox inpTipeAkun;
    private javax.swing.JTextField inpUsername;
    private javax.swing.JLabel lbProfile;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblApp;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblKonPassword;
    private javax.swing.JLabel lblLeft;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamaLengkap;
    private javax.swing.JLabel lblNamaPanggilan;
    private javax.swing.JLabel lblNegara;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPekerjaan;
    private javax.swing.JLabel lblShowProfile;
    private javax.swing.JLabel lblTglLahir;
    private javax.swing.JLabel lblTipeAkun;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JSeparator line3;
    private javax.swing.JSeparator line4;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
