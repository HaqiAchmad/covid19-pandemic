package com.window.admin;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-12-03
 */
public class UpdateUser extends javax.swing.JFrame {

    int x, y;
    private Connection conn;
    private Statement stat;
    private ResultSet res;
    
    public UpdateUser() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        this.btnBeranda.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnInfo.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataUser.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataCovidDunia.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataCovidIndo.setUI(new javax.swing.plaf.basic.BasicButtonUI());
//        this.jButton8.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.tabelUsers.setRowHeight(30);
        this.tabelUsers.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelUsers.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        this.btnAdd.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnHapus.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnSimpan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.pnlMain.setUI(new javax.swing.plaf.basic.BasicPanelUI());
        
        this.btnSimpan.setVisible(false);
        this.btnBatal.setVisible(false);
        
        this.editEmail.setEditable(false);
        this.editUsername.setEditable(false);
        this.editGender.setEditable(false);
        this.editTipeAkun.setEditable(false);
//        this.editNamalengkap.setEditable(false);
//        this.editNamapanggilan.setEditable(false);
        this.editTglLahir.setEditable(false);
//        this.editAlamat.setEditable(false);
//        this.editAsalNegara.setEditable(false);
//        this.editPekerjaan.setEditable(false);
        this.editTglDibuat.setEditable(false);
        this.editPassword.setEditable(false);
        
        this.editUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editNamalengkap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editNamapanggilan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editTipeAkun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editTglLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editAsalNegara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editPekerjaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        this.editTglDibuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        this.scaleImage(new ImageIcon("D:\\Downloads\\Images\\profile.jpg"));
        this.startConnection();
        this.getUsers();
        
        JButton[] btns = new JButton[]{this.btnBeranda, this.btnInfo, this.btnDataCovidIndo, this.btnDataCovidDunia};
        for(JButton btn : btns){
            
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
                    btn.setBackground(new java.awt.Color(28,100,230));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setBackground(new java.awt.Color(49,144,215));
                }
            });

        }
        
    }
    
    private void scaleImage(ImageIcon icon){
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(lblPhotoProfile.getWidth(), lblPhotoProfile.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaleIcon = new ImageIcon(imgScale);
        lblPhotoProfile.setText("");
        lblPhotoProfile.setIcon(scaleIcon);
    }

    private void startConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/app_covid19tester", "root", "");
            stat = conn.createStatement();
            System.out.println("koneksi sukses");
        }catch(SQLException | ClassNotFoundException sx){
            System.out.println("koneksi gagal \n" + sx.toString());
        }
    }
    
    private void getUsers(){
        DefaultTableModel tbl = new DefaultTableModel();
        tbl.addColumn("Username");
        tbl.addColumn("Nama Panggilan");
        tbl.addColumn("Email");
        tbl.addColumn("Type");
        try{
            res = stat.executeQuery("SELECT * FROM users");
            while(res.next()){
                tbl.addRow(new Object[]{
                    res.getString("username"),
                    res.getString("namalengkap"),
                    res.getString("email"),
                    res.getString("type")
                });
            }
            this.tabelUsers.setModel(tbl);
        }catch(SQLException ex){
            System.out.println(ex);
        }
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
        ));
        tabelUsers.setGridColor(new java.awt.Color(0, 0, 0));
        tabelUsers.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelUsers.setSelectionForeground(new java.awt.Color(250, 246, 246));
        jScrollPane1.setViewportView(tabelUsers);

        inpCariUser.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

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
        pnlLeft.add(btnBeranda);

        btnInfo.setBackground(new java.awt.Color(49, 144, 215));
        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-info.png"))); // NOI18N
        btnInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnInfo.setPreferredSize(new java.awt.Dimension(43, 43));
        pnlLeft.add(btnInfo);

        btnDataUser.setBackground(new java.awt.Color(34, 119, 237));
        btnDataUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-users.png"))); // NOI18N
        btnDataUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataUser.setPreferredSize(new java.awt.Dimension(43, 43));
        pnlLeft.add(btnDataUser);

        btnDataCovidDunia.setBackground(new java.awt.Color(49, 144, 215));
        btnDataCovidDunia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-kasusdunia.png"))); // NOI18N
        btnDataCovidDunia.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataCovidDunia.setPreferredSize(new java.awt.Dimension(43, 43));
        pnlLeft.add(btnDataCovidDunia);

        btnDataCovidIndo.setBackground(new java.awt.Color(49, 144, 215));
        btnDataCovidIndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-kasusindo.png"))); // NOI18N
        btnDataCovidIndo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataCovidIndo.setPreferredSize(new java.awt.Dimension(43, 43));
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

        btnEdit.setBackground(new java.awt.Color(41, 180, 50));
        btnEdit.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");

        btnHapus.setBackground(new java.awt.Color(220, 41, 41));
        btnHapus.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Hapus");

        line1.setForeground(new java.awt.Color(0, 0, 0));

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmail.setText("Email");

        editEmail.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editEmail.setText("hakiahmad756@gmail.com");
        editEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editEmail.setCaretColor(new java.awt.Color(255, 0, 0));

        editUsername.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editUsername.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editUsername.setText("baihaqi");
        editUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editUsername.setCaretColor(new java.awt.Color(255, 0, 0));

        lblUsername.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsername.setText("Username");

        editNamalengkap.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNamalengkap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNamalengkap.setText("Achmad Baihaqi");
        editNamalengkap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNamalengkap.setCaretColor(new java.awt.Color(255, 0, 0));

        lblNamalengkap.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamalengkap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamalengkap.setText("Nama Lengkap");

        editTipeAkun.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTipeAkun.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTipeAkun.setText("Admin");
        editTipeAkun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTipeAkun.setCaretColor(new java.awt.Color(255, 0, 0));

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

        lblGender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblGender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGender.setText("Jenis Kelamin");

        editNamapanggilan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNamapanggilan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNamapanggilan.setText("Baihaqi");
        editNamapanggilan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNamapanggilan.setCaretColor(new java.awt.Color(255, 0, 0));

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

        editAlamat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAlamat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAlamat.setText("Jawa Timur");
        editAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAlamat.setCaretColor(new java.awt.Color(255, 0, 0));

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAlamat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlamat.setText("Alamat");

        editAsalNegara.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAsalNegara.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAsalNegara.setText("Indonesia");
        editAsalNegara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAsalNegara.setCaretColor(new java.awt.Color(255, 0, 0));

        lblAsalNegara.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAsalNegara.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAsalNegara.setText("Negara");

        editPekerjaan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPekerjaan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPekerjaan.setText("Software Enginer");
        editPekerjaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPekerjaan.setCaretColor(new java.awt.Color(255, 0, 0));

        lblPekerjaan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPekerjaan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPekerjaan.setText("Pekerjaan");

        editTglDibuat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTglDibuat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTglDibuat.setText("30 Oktober 2020");
        editTglDibuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTglDibuat.setCaretColor(new java.awt.Color(255, 0, 0));

        lblDibuat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblDibuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDibuat.setText("Dibuat pada");

        editPassword.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPassword.setText("••••••••••••••••");
        editPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPassword.setCaretColor(new java.awt.Color(255, 0, 0));

        lblPassword.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPassword.setText("Password");

        line3.setForeground(new java.awt.Color(0, 0, 0));

        btnSimpan.setBackground(new java.awt.Color(34, 119, 237));
        btnSimpan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan");

        btnBatal.setBackground(new java.awt.Color(220, 41, 41));
        btnBatal.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setText("Batal");

        line4.setForeground(new java.awt.Color(0, 0, 0));

        lblEditFoto.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblEditFoto.setText("Edit Foto");

        lblHapusFoto.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblHapusFoto.setText("Hapus Foto");

        lblKembali.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-back.png"))); // NOI18N

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
                        .addGap(432, 432, 432)
                        .addComponent(lblKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    }//GEN-LAST:event_lblCloseMouseExited

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered

    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                new com.window.admin.DataAplikasi().setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_lblCloseMouseClicked

    private void lblMinimazeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseExited

    }//GEN-LAST:event_lblMinimazeMouseExited

    private void lblMinimazeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseEntered

    }//GEN-LAST:event_lblMinimazeMouseEntered

    private void lblMinimazeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseClicked
        this.setState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimazeMouseClicked
    private boolean isEdit = false;
    public void changeColor(Color color){
        this.editUsername.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editNamalengkap.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editNamapanggilan.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editEmail.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editTipeAkun.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editTglLahir.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editAsalNegara.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editPekerjaan.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editGender.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editPassword.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editTglDibuat.setBorder(javax.swing.BorderFactory.createLineBorder(color));
    } 
   
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
