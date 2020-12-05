package com.window.admin;

import com.database.Account;
import com.media.audio.Audio;
import com.media.gambar.Gambar;
import com.window.all.Beranda;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-12-03
 */
public class UpdateUser extends javax.swing.JFrame {

    /**
     * Digunakan untuk mendapatkan dan mengedit data dari akun
     */
    private final Account dataUser = new Account();
    /**
     * Digunakan untuk mencari akun dengan keyword tertentu
     */
    private String keyword = "";
    /**
     * Digunakan untuk menyimpan data dari akun
     */
    private String user_selected, username, namaLengkap, namaPanggilan, email, gender, tanggalLahir, 
                   pekerjaan, alamat, negara, password, tanggalDibuat, fotoProfile, type;
    /**
     * Fields / data yang akan ditampilkan ke dalam tabel
     */
    private final String[] fields = new String[]{Account.USERNAME, Account.NAMA_PANGGILAN, Account.EMAIL, Account.TYPE};
    /**
     * Digunakan untuk mengatur posisi dari window
     */
    private int x, y;
    
    /**
     * Digunakan untuk mengedit data
     */
    private boolean isEdit = false;
    
    public UpdateUser() {
        initComponents();
        
        this.setIconImage(Gambar.getWindowIcon());
        this.setLocationRelativeTo(null);
        this.tabelUsers.setRowHeight(30);
        this.tabelUsers.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelUsers.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        this.btnSimpan.setVisible(false);
        this.btnBatal.setVisible(false);
        
        this.user_selected = "baihaqi";
        dataTabel();
        showData();
        setEditableData(false);
        
         // mengatur UI dari button yang ada didalam window ke BasicButtonUI
        JButton btns[] = new JButton[]{
            this.btnAdd, this.btnBatal, this.btnBeranda, this.btnDataUser, this.btnEdit, this.btnHapus, this.btnInfo, this.btnSimpan, this.btnDataCovidDunia, this.btnDataCovidIndo
        };
        for(JButton btn : btns){
            btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        }
        
        // mengatur MouseEvent Entered & Exited pada Button yang ada di dalam Panel pnlLeft
        JButton btnLeft[] = new JButton[]{
            this.btnBeranda, this.btnInfo, this.btnDataCovidDunia, this.btnDataCovidIndo
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
     * Digunakan untuk mengatur apakah data akan diedit atau tidak 
     * 
     * @param edit jika True maka akan diedit jika False maka data tidak akan dedit
     */
    private void setEditableData(final boolean edit){

        JTextField[] edits = new JTextField[]{
            this.editAlamat, this.editAsalNegara, this.editEmail, this.editGender, this.editNamalengkap, this.editNamapanggilan, this.editPassword,
            this.editPekerjaan, this.editTglDibuat, this.editTglDibuat, this.editTglLahir, this.editTipeAkun, this.editUsername
        };
        
        isEdit = edit;
        
        if(isEdit){
            this.btnEdit.setVisible(false);
            this.btnSimpan.setVisible(true);
            this.btnBatal.setVisible(true);
            
                for(JTextField field : edits){
                    field.setEditable(true);
                    field.setEnabled(true);
                    field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,106,255)));
                }
        }else{
            this.btnEdit.setVisible(true);
            this.btnSimpan.setVisible(false);
            this.btnBatal.setVisible(false);
            
                for(JTextField field : edits){
                    field.setDisabledTextColor(new Color(0,0,0));
                    field.setEditable(true);
                    field.setEnabled(false);
                    field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
                }
        }
    }
    
    /**
     * Digunakan untuk menampilkan data dari akun ke dalam tabel
     */
    private void dataTabel(){
        tabelUsers.setModel(new javax.swing.table.DefaultTableModel(
            dataUser.getDataAccount(fields, keyword),
            new String [] {
                "Username", "Nama Panggilan", "Email", "Type "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    /**
     * Digunakan untuk mendapatkan data dari akun melalui class Account 
     * Lalu datanya akan ditampilkan ke dalam window
     */
    private void showData(){
        // mendapatkan data dari akun
        this.username = dataUser.getDataAccount(this.user_selected, Account.USERNAME);
        this.namaLengkap = dataUser.getDataAccount(this.user_selected, Account.NAMA_LENGKAP);
        this.namaPanggilan = dataUser.getDataAccount(this.user_selected, Account.NAMA_PANGGILAN);
        this.email = dataUser.getDataAccount(this.user_selected, Account.EMAIL);
        this.gender = dataUser.getDataAccount(this.user_selected, Account.GENDER);
        this.tanggalLahir = dataUser.getDataAccount(this.user_selected, Account.TGL_LAHIR);
        this.pekerjaan = dataUser.getDataAccount(this.user_selected, Account.PEKERJAAN);
        this.alamat = dataUser.getDataAccount(this.user_selected, Account.ALAMAT);
        this.negara = dataUser.getDataAccount(this.user_selected, Account.NEGARA);
        this.password = dataUser.getDataAccount(this.user_selected, Account.PASSWORD);
        this.tanggalDibuat = dataUser.getDataAccount(this.user_selected, Account.TGL_DIBUAT);
        this.fotoProfile = dataUser.getDataAccount(this.user_selected, Account.FOTO_PROFILE);
        this.type = dataUser.getDataAccount(this.user_selected, Account.TYPE);
        
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
            this.editGender.setText("Tidak Dicantumkan");
        }
        
        // jika pekerjaan bernilai null maka akan ditampilkan menjadi 'Tidak Dicantumkan'
        if(pekerjaan.equalsIgnoreCase("null")){
            this.editPekerjaan.setText("Tidak Dicantumkan");
        }else{
            this.editPekerjaan.setText(pekerjaan);
        }
        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelUsers = new javax.swing.JTable();
        inpCariUser = new javax.swing.JTextField();
        lblCari = new javax.swing.JLabel();
        line2 = new javax.swing.JSeparator();
        pnlLeft = new javax.swing.JPanel();
        lblLeft = new javax.swing.JLabel();
        btnBeranda = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();
        btnDataUser = new javax.swing.JButton();
        btnDataCovidDunia = new javax.swing.JButton();
        btnDataCovidIndo = new javax.swing.JButton();
        lblTop = new javax.swing.JLabel();
        lblMinimaze = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblKeyword = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        line1 = new javax.swing.JSeparator();
        lblEmail = new javax.swing.JLabel();
        editEmail = new javax.swing.JTextField();
        editUsername = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        editNamalengkap = new javax.swing.JTextField();
        lblNamalengkap = new javax.swing.JLabel();
        editTipeAkun = new javax.swing.JTextField();
        lblTipeAkun = new javax.swing.JLabel();
        lblEditData = new javax.swing.JLabel();
        editGender = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        editNamapanggilan = new javax.swing.JTextField();
        lblNamaPanggilan = new javax.swing.JLabel();
        lblPhotoProfile = new javax.swing.JLabel();
        lblProfile = new javax.swing.JLabel();
        lblTanggalLahir = new javax.swing.JLabel();
        editTglLahir = new javax.swing.JTextField();
        editAlamat = new javax.swing.JTextField();
        lblAlamat = new javax.swing.JLabel();
        editAsalNegara = new javax.swing.JTextField();
        lblAsalNegara = new javax.swing.JLabel();
        editPekerjaan = new javax.swing.JTextField();
        lblPekerjaan = new javax.swing.JLabel();
        editTglDibuat = new javax.swing.JTextField();
        lblDibuat = new javax.swing.JLabel();
        editPassword = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        line3 = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        line4 = new javax.swing.JSeparator();
        lblEditFoto = new javax.swing.JLabel();
        lblHapusFoto = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();

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

        tabelUsers.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tabelUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Username", "Nama Panggilan", "Email", "Type "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelUsers.setGridColor(new java.awt.Color(0, 0, 0));
        tabelUsers.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelUsers.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tabelUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelUsersMouseClicked(evt);
            }
        });
        tabelUsers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelUsersKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelUsers);

        inpCariUser.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        inpCariUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariUserKeyTyped(evt);
            }
        });

        lblCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCari.setForeground(new java.awt.Color(224, 56, 56));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari Username / Nama");

        line2.setForeground(new java.awt.Color(0, 0, 0));
        line2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        pnlLeft.setBackground(new java.awt.Color(49, 144, 215));
        pnlLeft.setMaximumSize(new java.awt.Dimension(69, 32767));
        pnlLeft.setMinimumSize(new java.awt.Dimension(69, 10));

        lblLeft.setPreferredSize(new java.awt.Dimension(65, 210));
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

        btnDataUser.setBackground(new java.awt.Color(34, 119, 237));
        btnDataUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-users.png"))); // NOI18N
        btnDataUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataUser.setPreferredSize(new java.awt.Dimension(43, 43));
        btnDataUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataUserActionPerformed(evt);
            }
        });
        pnlLeft.add(btnDataUser);

        btnDataCovidDunia.setBackground(new java.awt.Color(49, 144, 215));
        btnDataCovidDunia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-kasusdunia.png"))); // NOI18N
        btnDataCovidDunia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataCovidDunia.setPreferredSize(new java.awt.Dimension(43, 43));
        btnDataCovidDunia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataCovidDuniaActionPerformed(evt);
            }
        });
        pnlLeft.add(btnDataCovidDunia);

        btnDataCovidIndo.setBackground(new java.awt.Color(49, 144, 215));
        btnDataCovidIndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-kasusindo.png"))); // NOI18N
        btnDataCovidIndo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataCovidIndo.setPreferredSize(new java.awt.Dimension(43, 43));
        btnDataCovidIndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataCovidIndoActionPerformed(evt);
            }
        });
        pnlLeft.add(btnDataCovidIndo);

        lblTop.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Data User");

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

        lblKeyword.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblKeyword.setText("Menampilkan data dengan keyword = \"\"");

        btnAdd.setBackground(new java.awt.Color(34, 119, 237));
        btnAdd.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Add Akun");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAddMouseExited(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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

        btnHapus.setBackground(new java.awt.Color(220, 41, 41));
        btnHapus.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Hapus");
        btnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHapusMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHapusMouseExited(evt);
            }
        });
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        line1.setForeground(new java.awt.Color(0, 0, 0));

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("Email");

        editEmail.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editEmail.setText("hakiahmad756@gmail.com");
        editEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editEmail.setCaretColor(new java.awt.Color(255, 0, 0));
        editEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editEmailMouseClicked(evt);
            }
        });

        editUsername.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editUsername.setText("baihaqi");
        editUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editUsername.setCaretColor(new java.awt.Color(255, 0, 0));
        editUsername.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editUsernameMouseClicked(evt);
            }
        });

        lblUsername.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Username");

        editNamalengkap.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNamalengkap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNamalengkap.setText("Achmad Baihaqi");
        editNamalengkap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNamalengkap.setCaretColor(new java.awt.Color(255, 0, 0));
        editNamalengkap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editNamalengkapMouseClicked(evt);
            }
        });

        lblNamalengkap.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamalengkap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamalengkap.setText("Nama Lengkap");

        editTipeAkun.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTipeAkun.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTipeAkun.setText("Admin");
        editTipeAkun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTipeAkun.setCaretColor(new java.awt.Color(255, 0, 0));
        editTipeAkun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTipeAkunMouseClicked(evt);
            }
        });

        lblTipeAkun.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTipeAkun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipeAkun.setText("Tipe Akun");

        lblEditData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblEditData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditData.setText("Edit Data User");

        editGender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editGender.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editGender.setText("Laki-Laki");
        editGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editGender.setCaretColor(new java.awt.Color(255, 0, 0));
        editGender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editGenderMouseClicked(evt);
            }
        });

        lblGender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblGender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGender.setText("Jenis Kelamin");

        editNamapanggilan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNamapanggilan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNamapanggilan.setText("Baihaqi");
        editNamapanggilan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNamapanggilan.setCaretColor(new java.awt.Color(255, 0, 0));
        editNamapanggilan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editNamapanggilanMouseClicked(evt);
            }
        });

        lblNamaPanggilan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaPanggilan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaPanggilan.setText("Nama Panggilan");

        lblPhotoProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhotoProfile.setText("profile");
        lblPhotoProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProfile.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblProfile.setText("Photo Profile");

        lblTanggalLahir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTanggalLahir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTanggalLahir.setText("Tanggal Lahir");

        editTglLahir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTglLahir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTglLahir.setText("04 Agustus 2003");
        editTglLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTglLahir.setCaretColor(new java.awt.Color(255, 0, 0));
        editTglLahir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTglLahirMouseClicked(evt);
            }
        });

        editAlamat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAlamat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAlamat.setText("Jawa Timur");
        editAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAlamat.setCaretColor(new java.awt.Color(255, 0, 0));
        editAlamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editAlamatMouseClicked(evt);
            }
        });

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAlamat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlamat.setText("Alamat");

        editAsalNegara.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAsalNegara.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAsalNegara.setText("Indonesia");
        editAsalNegara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAsalNegara.setCaretColor(new java.awt.Color(255, 0, 0));
        editAsalNegara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editAsalNegaraMouseClicked(evt);
            }
        });

        lblAsalNegara.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAsalNegara.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAsalNegara.setText("Negara");

        editPekerjaan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPekerjaan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPekerjaan.setText("Software Enginer");
        editPekerjaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPekerjaan.setCaretColor(new java.awt.Color(255, 0, 0));
        editPekerjaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editPekerjaanMouseClicked(evt);
            }
        });

        lblPekerjaan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPekerjaan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPekerjaan.setText("Pekerjaan");

        editTglDibuat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTglDibuat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTglDibuat.setText("30 Oktober 2020");
        editTglDibuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTglDibuat.setCaretColor(new java.awt.Color(255, 0, 0));
        editTglDibuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTglDibuatMouseClicked(evt);
            }
        });

        lblDibuat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblDibuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDibuat.setText("Dibuat pada");

        editPassword.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPassword.setText("••••••••••••••••");
        editPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPassword.setCaretColor(new java.awt.Color(255, 0, 0));
        editPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editPasswordMouseClicked(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassword.setText("Password");

        line3.setForeground(new java.awt.Color(0, 0, 0));

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

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                    .addComponent(lblCari, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(inpCariUser, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlMainLayout.createSequentialGroup()
                                    .addComponent(btnAdd)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnHapus))
                                .addComponent(line1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(line2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(440, 440, 440)
                        .addComponent(lblKembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMinimaze, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBatal)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblEditData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(editEmail)
                                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(editNamalengkap)
                                            .addComponent(lblNamalengkap, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblPhotoProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                                        .addComponent(lblEditFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(lblHapusFoto)
                                                        .addGap(0, 0, Short.MAX_VALUE))))
                                            .addComponent(lblTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                            .addComponent(editTglLahir)
                                            .addComponent(editAlamat)
                                            .addComponent(lblAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                            .addComponent(editPekerjaan)
                                            .addComponent(lblPekerjaan, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                            .addComponent(editTglDibuat)
                                            .addComponent(lblDibuat, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                                        .addGap(39, 39, 39)
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(editGender)
                                                    .addComponent(lblGender, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                                    .addComponent(editAsalNegara)
                                                    .addComponent(lblAsalNegara, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                                    .addComponent(editPassword)
                                                    .addComponent(lblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(editUsername)
                                            .addComponent(lblUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(editTipeAkun)
                                            .addComponent(lblTipeAkun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(editNamapanggilan)
                                            .addComponent(lblNamaPanggilan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(line3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(line4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(23, 23, 23))))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(line2)
                .addContainerGap())
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblCari, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(inpCariUser)))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblClose, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lblMinimaze, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lblKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblEditData, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(line3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblKeyword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(line1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd)
                            .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblUsername)
                                    .addComponent(lblProfile))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(editUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEditFoto)
                                    .addComponent(lblHapusFoto)))
                            .addComponent(lblPhotoProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblTipeAkun)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editTipeAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNamaPanggilan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editNamapanggilan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNamalengkap)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editNamalengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGender)
                            .addComponent(lblTanggalLahir))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editGender, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAsalNegara)
                            .addComponent(lblAlamat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editAsalNegara, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblPekerjaan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblPassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lblDibuat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTglDibuat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(line4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEdit)
                            .addComponent(btnSimpan)
                            .addComponent(btnBatal))
                        .addContainerGap(26, Short.MAX_VALUE))))
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

    private void lblKembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseExited
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK));
    }//GEN-LAST:event_lblKembaliMouseExited

    private void lblKembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseEntered
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK_ENTERED));
    }//GEN-LAST:event_lblKembaliMouseEntered

    private void lblKembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseClicked
        System.out.println("Membuka Window DataAplikasi");
        DataAplikasi dataApp = new DataAplikasi();
        dataApp.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                dataApp.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_lblKembaliMouseClicked

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

    private void btnDataUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataUserActionPerformed
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
    }//GEN-LAST:event_btnDataUserActionPerformed

    private void btnDataCovidDuniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataCovidDuniaActionPerformed
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
    }//GEN-LAST:event_btnDataCovidDuniaActionPerformed

    private void btnDataCovidIndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataCovidIndoActionPerformed
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
    }//GEN-LAST:event_btnDataCovidIndoActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        this.btnAdd.setBackground(new Color(31,34,38));
        this.btnHapus.setBackground(new Color(34,119,237));
    }//GEN-LAST:event_btnAddMouseEntered

    private void btnAddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseExited
        this.btnAdd.setBackground(new Color(34,119,237));
        this.btnHapus.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnAddMouseExited

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseEntered
        this.btnHapus.setBackground(new Color(31,34,38));
        this.btnAdd.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnHapusMouseEntered

    private void btnHapusMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHapusMouseExited
        this.btnHapus.setBackground(new Color(220,41,41));
        this.btnAdd.setBackground(new Color(34,119,237));
    }//GEN-LAST:event_btnHapusMouseExited

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        this.setEditableData(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnEditMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseEntered
        this.btnEdit.setBackground(new Color(33,123,39));
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        this.btnEdit.setBackground(new Color(41,180,50));
    }//GEN-LAST:event_btnEditMouseExited

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
        this.btnSimpan.setBackground(new Color(31,34,38));
        this.btnBatal.setBackground(new Color(34,119,237));
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
        this.btnSimpan.setBackground(new Color(34,119,237));
        this.btnBatal.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.setEditableData(false);
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setBackground(new Color(31,34,38));
        this.btnSimpan.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setBackground(new Color(220,41,41));
        this.btnSimpan.setBackground(new Color(34,119,237));
    }//GEN-LAST:event_btnBatalMouseExited

    private void tabelUsersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelUsersMouseClicked
        this.setEditableData(false);
        // mendapatkan akun yang dipilih oleh user
        this.user_selected = this.tabelUsers.getValueAt(tabelUsers.getSelectedRow(), 0).toString();
        // menampilkan data yang dipilih user ke window
        showData();
    }//GEN-LAST:event_tabelUsersMouseClicked

    private void tabelUsersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelUsersKeyPressed
        this.setEditableData(false);
        // menangkap event jika user menekan tombol arah atas atau arah bawah
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            // mendapatkan user yang sedang dipilih
            this.user_selected = this.tabelUsers.getValueAt(tabelUsers.getSelectedRow() - 1, 0).toString(); // -1 artinya berpindah mundur dari index dari user sebelumnya ke index user saat ini
            // mereset data akun yang ditampilkan di window
            showData();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            // mendapatkan user yang sedang dipilih
            this.user_selected = this.tabelUsers.getValueAt(tabelUsers.getSelectedRow() + 1, 0).toString(); // +1 artinya berpindah maju dari index dari user sebelumnya ke index user saat ini
            // mereset data akun yang ditampilkan di window
            showData();            
        }
    }//GEN-LAST:event_tabelUsersKeyPressed

    private void inpCariUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariUserKeyTyped
        // mendapatkan keyword yang diinputkan user melalui inpCariUser
        this.keyword = inpCariUser.getText();
        // mendapatkan total akun yang karakternya mirip dengan keyword yg diinputkan user
        int row = dataUser.getTotalAkun("SELECT * FROM users WHERE username LIKE '%"+keyword+"%' OR namalengkap LIKE '%"+keyword+"%' OR namapanggilan LIKE '%"+keyword+"%' OR email LIKE '%"+keyword+"%' OR type LIKE '%"+keyword+"%'");
        // mereset lblKeyword
        this.lblKeyword.setText("Menampilkan "+row+" data dengan keyword = \""+keyword+"\"");
        // mereset data tabel berdasarkan keyword yang dimasukan user
        dataTabel();
    }//GEN-LAST:event_inpCariUserKeyTyped

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

    private void editUsernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editUsernameMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editUsernameMouseClicked

    private void editEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editEmailMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editEmailMouseClicked

    private void editTipeAkunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTipeAkunMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editTipeAkunMouseClicked

    private void editNamalengkapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editNamalengkapMouseClicked
         // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editNamalengkapMouseClicked

    private void editNamapanggilanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editNamapanggilanMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editNamapanggilanMouseClicked

    private void editTglLahirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTglLahirMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editTglLahirMouseClicked

    private void editGenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editGenderMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editGenderMouseClicked

    private void editAlamatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editAlamatMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editAlamatMouseClicked

    private void editAsalNegaraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editAsalNegaraMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editAsalNegaraMouseClicked

    private void editPekerjaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPekerjaanMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editPekerjaanMouseClicked

    private void editPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPasswordMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editPasswordMouseClicked

    private void editTglDibuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTglDibuatMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editTglDibuatMouseClicked
  
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
            java.util.logging.Logger.getLogger(UpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBeranda;
    private javax.swing.JButton btnDataCovidDunia;
    private javax.swing.JButton btnDataCovidIndo;
    private javax.swing.JButton btnDataUser;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnSimpan;
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
    private javax.swing.JTextField inpCariUser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblAsalNegara;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDibuat;
    private javax.swing.JLabel lblEditData;
    private javax.swing.JLabel lblEditFoto;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHapusFoto;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblKeyword;
    private javax.swing.JLabel lblLeft;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamaPanggilan;
    private javax.swing.JLabel lblNamalengkap;
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
    private javax.swing.JSeparator line3;
    private javax.swing.JSeparator line4;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTable tabelUsers;
    // End of variables declaration//GEN-END:variables
}
