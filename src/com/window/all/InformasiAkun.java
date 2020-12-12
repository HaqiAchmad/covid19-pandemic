package com.window.all;

import com.database.Account;
import com.media.gambar.Gambar;
import com.media.audio.Audio;
import com.window.admin.DataAplikasi;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-12-08
 */
public class InformasiAkun extends javax.swing.JFrame {

    /**
     * Digunakan untuk mendapatkan dan mengedit data dari akun
     */
    private final Account dataUser = new Account();
    
    /**
     * Digunakan untuk menyimpan data dari akun
     */
    private String username, namaLengkap, namaPanggilan, email, gender, tanggalLahir, 
                   pekerjaan, alamat, negara, password, tanggalDibuat, fotoProfile, type;
    
    private int fromWindow;
    
    private int x, y;

    public static final int  BERANDA = 0, APA_COVID = 1, GEJALA_COVID = 2, BAHAYA_COVID = 3, PENCEGAHAN_COVID = 4,
                             PENANGANAN_COVID = 5,  TENTANG_APP = 6, DATA_APLIKASI = 7;
    
    public InformasiAkun(final int fromWindow) {
        initComponents();
        
        this.fromWindow = fromWindow;
        
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        this.btnLogout.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
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
            this.editAlamat, this.editAsalNegara, this.editNamalengkap, this.editNamapanggilan, this.editPassword, this.editGender,
            this.editPekerjaan, this.editTglDibuat, this.editTglLahir, this.editTipeAkun, this.editUsername, this.editEmail
        };
        // mengatur field data dari akun agar tidak bisa diedit
        for(JTextField data : fields){
            data.setEnabled(false);
            data.setDisabledTextColor(new Color(0,0,0));
        }
    }
    
    public InformasiAkun() {
        initComponents();
    }
    
    private void kembali(){
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                switch(fromWindow){
                    case BERANDA: 
                        new Beranda().setVisible(true);
                        break;
                    case APA_COVID: 
                        new ApaCovid().setVisible(true);
                        break;
                    case GEJALA_COVID: 
                        new GejalaCovid().setVisible(true);
                        break;
                    case BAHAYA_COVID: 
                        new BahayaCovid().setVisible(true);
                        break;
                    case PENCEGAHAN_COVID: 
                        new PencegahanCovid().setVisible(true);
                        break;
                    case PENANGANAN_COVID: 
                        new PenangananCovid().setVisible(true);
                        break;
                    case TENTANG_APP: 
                        new AboutApp().setVisible(true);
                        break;
                    case DATA_APLIKASI: 
                        new DataAplikasi().setVisible(true);
                        break;
                    default : 
                        Audio.play(Audio.SOUND_WARNING);
                        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menekan tombol kembali!", "Pesan", JOptionPane.WARNING_MESSAGE);
                        break;
                }
            }
        });
        dispose();
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

        // jika tgl lahir = 0000-01-01 maka akan ditampilkan 'Tidak Dicantumkan'
        if(dataUser.dateToString(tanggalLahir).equalsIgnoreCase("1 Januari 1")){
            this.editTglLahir.setText("Tidak Dicantumkan");
        }else{
            this.editTglLahir.setText(dataUser.dateToString(tanggalLahir));
        }
        
        // jika gender bernilai L maka akan menampilkan text 'Laki-Laki' jika berniali P akan menampilkan 'Perempuan'
        if(gender.equalsIgnoreCase("L")){
            this.editGender.setText("Laki-Laki");
        }else if(gender.equalsIgnoreCase("P")){
            this.editGender.setText("Perempuan");
        }else{
        }
        
        // jika pekerjaan bernilai null maka akan ditampilkan menjadi 'Tidak Dicantumkan'
        if(pekerjaan.equalsIgnoreCase("null")){
            this.editPekerjaan.setText("Tidak Dicantumkan");
        }else{
            this.editPekerjaan.setText(pekerjaan);
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
        btnEdit = new javax.swing.JButton();
        lblTop = new javax.swing.JLabel();
        lblPhotoProfile = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        line1 = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();
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
        editTglLahir = new javax.swing.JTextField();
        line2 = new javax.swing.JSeparator();
        lblLoginAsDeveloper = new javax.swing.JLabel();
        lblLoginSebagai = new javax.swing.JLabel();
        editGender = new javax.swing.JTextField();

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

        btnEdit.setBackground(new java.awt.Color(41, 180, 50));
        btnEdit.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditMouseExited(evt);
            }
        });
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        lblTop.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Informasi Akun Anda");

        lblPhotoProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhotoProfile.setText("profile");
        lblPhotoProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProfile.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblProfile.setText("Photo Profile");

        line1.setForeground(new java.awt.Color(0, 0, 0));

        btnLogout.setBackground(new java.awt.Color(220, 41, 41));
        btnLogout.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("Logout");
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLogoutMouseExited(evt);
            }
        });
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
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

        editTglLahir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTglLahir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTglLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTglLahir.setCaretColor(new java.awt.Color(255, 0, 0));
        editTglLahir.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editTglLahir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTglLahirMouseClicked(evt);
            }
        });

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
        editGender.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editGender.setCaretColor(new java.awt.Color(255, 0, 0));
        editGender.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editGender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editGenderMouseClicked(evt);
            }
        });

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
                                    .addComponent(editGender, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(67, 67, 67)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editEmail, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editNamapanggilan, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNamapanggilan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editTglLahir, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTanggalLahir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblDibuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblAsalNegara, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editAsalNegara, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editTglDibuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(line2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLoginSebagai, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLoginAsDeveloper, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(94, 94, 94)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnLogout)))
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addComponent(editTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addComponent(btnEdit)
                        .addComponent(btnLogout))
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

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
//        this.btnEdit_Simpan.setBackground(new Color(33,123,39));
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
//        this.btnEdit_Simpan.setBackground(new Color(41,180,50));
    }//GEN-LAST:event_btnEditMouseExited

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        System.out.println("Membuka Window EditAkun");
        EditAkun editAkun = new EditAkun();
        editAkun.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                editAkun.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        this.btnLogout.setBackground(new Color(31,34,38));
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void btnLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseExited
        this.btnLogout.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnLogoutMouseExited

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // keluar dari window InformasiAkun
        System.out.println("Membuka Window ConfirmLogout");
        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable(){

            @Override
            public void run(){
                new ConfirmLogout().setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnLogoutActionPerformed

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
        kembali();
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
       Audio.play(Audio.SOUND_WARNING);
       JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editUsernameMouseClicked

    private void editNamalengkapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editNamalengkapMouseClicked
       Audio.play(Audio.SOUND_WARNING);
       JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editNamalengkapMouseClicked

    private void editAlamatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editAlamatMouseClicked
       Audio.play(Audio.SOUND_WARNING);
       JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editAlamatMouseClicked

    private void editPekerjaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPekerjaanMouseClicked
        Audio.play(Audio.SOUND_WARNING);
       JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editPekerjaanMouseClicked

    private void editTipeAkunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTipeAkunMouseClicked
        Audio.play(Audio.SOUND_WARNING);
        JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editTipeAkunMouseClicked

    private void editAsalNegaraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editAsalNegaraMouseClicked
       Audio.play(Audio.SOUND_WARNING);
       JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editAsalNegaraMouseClicked

    private void editPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPasswordMouseClicked
        Audio.play(Audio.SOUND_WARNING);
       JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editPasswordMouseClicked

    private void editTglDibuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTglDibuatMouseClicked
        Audio.play(Audio.SOUND_WARNING);
        JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editTglDibuatMouseClicked

    private void editEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editEmailMouseClicked
        Audio.play(Audio.SOUND_WARNING);
        JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editEmailMouseClicked

    private void editNamapanggilanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editNamapanggilanMouseClicked
        Audio.play(Audio.SOUND_WARNING);
        JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editNamapanggilanMouseClicked

    private void editTglLahirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTglLahirMouseClicked
       Audio.play(Audio.SOUND_WARNING);
       JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editTglLahirMouseClicked

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

    private void editGenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editGenderMouseClicked
       Audio.play(Audio.SOUND_WARNING);
       JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_editGenderMouseClicked

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
            java.util.logging.Logger.getLogger(InformasiAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformasiAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformasiAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformasiAkun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformasiAkun().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnLogout;
    private javax.swing.JTextField editAlamat;
    private javax.swing.JTextField editAsalNegara;
    private javax.swing.JTextField editEmail;
    private javax.swing.JTextField editGender;
    private javax.swing.JTextField editNamalengkap;
    private javax.swing.JTextField editNamapanggilan;
    private javax.swing.JTextField editPassword;
    private javax.swing.JTextField editPekerjaan;
    private javax.swing.JTextField editTglDibuat;
    private javax.swing.JTextField editTglLahir;
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
