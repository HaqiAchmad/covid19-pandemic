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
public class UpdateCovidDunia extends javax.swing.JFrame {

    int x, y;
    private Connection conn;
    private Statement stat;
    private ResultSet res;
    
    public UpdateCovidDunia() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        this.btnBeranda.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnInfo.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataUser.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataCovidDunia.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnDataCovidIndo.setUI(new javax.swing.plaf.basic.BasicButtonUI());
//        this.jButton8.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
        this.tabelNegara.setRowHeight(30);
        this.tabelNegara.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelNegara.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        this.btnAdd.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnEdit.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnHapus.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnSimpan.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.btnBatal.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        this.pnlMain.setUI(new javax.swing.plaf.basic.BasicPanelUI());
        
//        this.btnSimpan.setVisible(false);
//        this.btnBatal.setVisible(false);
        
        this.editPeringkat.setEditable(false);
//        this.editNegara_ENG.setEditable(false);
//        this.editNegara_IDN.setEditable(false);
//        this.editKritis.setEditable(false);
//        this.editKematian.setEditable(false);
        this.editAktif.setEditable(false);
        this.editTingkatKematian.setEditable(false);
        this.editTingkatKesembuhan.setEditable(false);
        this.editDiubah.setEditable(false);
        
//        this.editPeringkat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editPositif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editSembuh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editNegara_IDN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editNegara_ENG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editKematian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editAktif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editPopulasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editTingkatKesembuhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editKritis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editTingkatKematian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editDiubah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
//        this.editBenua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        this.scaleImage(new ImageIcon("S:\\Source Code\\Java Programming\\Java Project\\Covid-19 Pandemic\\src\\com\\media\\flags\\bendera-jepang.jpg"));
        this.startConnection();
        this.getUsers();
        
        JButton[] btns = new JButton[]{this.btnBeranda, this.btnInfo, this.btnDataUser, this.btnDataCovidIndo};
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
        Image imgScale = img.getScaledInstance(this.lblShowBendera.getWidth(), this.lblShowBendera.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaleIcon = new ImageIcon(imgScale);
        lblShowBendera.setText("");
        lblShowBendera.setIcon(scaleIcon);
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
        tbl.addColumn("Negara");
        tbl.addColumn("Positif");
        tbl.addColumn("Sembuh");
        tbl.addColumn("Kematian");
        try{
            res = stat.executeQuery("SELECT * FROM kasuscovid_dunia ORDER BY kasus DESC");
            while(res.next()){
                tbl.addRow(new Object[]{
                    res.getString("negara_idn"),
                    res.getString("kasus"),
                    res.getString("sembuh"),
                    res.getString("kematian")
                });
            }
            this.tabelNegara.setModel(tbl);
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelNegara = new javax.swing.JTable();
        inpCari = new javax.swing.JTextField();
        lblCariNegara = new javax.swing.JLabel();
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
        lblNamaNegara_IDN = new javax.swing.JLabel();
        editNegara_IDN = new javax.swing.JTextField();
        editPeringkat = new javax.swing.JTextField();
        lblPeringkat = new javax.swing.JLabel();
        editPositif = new javax.swing.JTextField();
        lblPositif = new javax.swing.JLabel();
        editNegara_ENG = new javax.swing.JTextField();
        lblNamaNegara_ENG = new javax.swing.JLabel();
        lblEditData = new javax.swing.JLabel();
        editKritis = new javax.swing.JTextField();
        lblKritis = new javax.swing.JLabel();
        editSembuh = new javax.swing.JTextField();
        lblSembuh = new javax.swing.JLabel();
        lblBendera = new javax.swing.JLabel();
        lblKematian = new javax.swing.JLabel();
        editKematian = new javax.swing.JTextField();
        editAktif = new javax.swing.JTextField();
        lblAktif = new javax.swing.JLabel();
        editPopulasi = new javax.swing.JTextField();
        lblPopulasi = new javax.swing.JLabel();
        editTingkatKesembuhan = new javax.swing.JTextField();
        lblTingkatSembuh = new javax.swing.JLabel();
        editTingkatKematian = new javax.swing.JTextField();
        lblTingkatKematian = new javax.swing.JLabel();
        line3 = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        line4 = new javax.swing.JSeparator();
        lblEditBendera = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();
        lblShowBendera = new javax.swing.JLabel();
        editBenua = new javax.swing.JTextField();
        lblBenua = new javax.swing.JLabel();
        editDiubah = new javax.swing.JTextField();
        lblDiubah = new javax.swing.JLabel();

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

        tabelNegara.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tabelNegara.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Negara", "Positif", "Sembuh", "Kematian"
            }
        ));
        tabelNegara.setGridColor(new java.awt.Color(0, 0, 0));
        tabelNegara.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelNegara.setSelectionForeground(new java.awt.Color(250, 246, 246));
        jScrollPane1.setViewportView(tabelNegara);

        inpCari.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        lblCariNegara.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCariNegara.setForeground(new java.awt.Color(224, 56, 56));
        lblCariNegara.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCariNegara.setText("Cari Negara");

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

        btnDataUser.setBackground(new java.awt.Color(49, 144, 215));
        btnDataUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-update-users.png"))); // NOI18N
        btnDataUser.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDataUser.setPreferredSize(new java.awt.Dimension(43, 43));
        pnlLeft.add(btnDataUser);

        btnDataCovidDunia.setBackground(new java.awt.Color(34, 119, 237));
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
        lblTop.setText("Data Kasus Covid-19 Di Dunia");

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
        btnAdd.setText("Add Negara");

        btnEdit.setBackground(new java.awt.Color(41, 180, 50));
        btnEdit.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setText("Edit");

        btnHapus.setBackground(new java.awt.Color(220, 41, 41));
        btnHapus.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnHapus.setForeground(new java.awt.Color(255, 255, 255));
        btnHapus.setText("Hapus");

        line1.setForeground(new java.awt.Color(0, 0, 0));

        lblNamaNegara_IDN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaNegara_IDN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaNegara_IDN.setText("Nama Negara (IDN)");

        editNegara_IDN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNegara_IDN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNegara_IDN.setText("Jepang");
        editNegara_IDN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNegara_IDN.setCaretColor(new java.awt.Color(255, 0, 0));

        editPeringkat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPeringkat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPeringkat.setText("56");
        editPeringkat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPeringkat.setCaretColor(new java.awt.Color(255, 0, 0));

        lblPeringkat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPeringkat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPeringkat.setText("Peringkat Kasus Terbanyak");

        editPositif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPositif.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPositif.setText("80041");
        editPositif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPositif.setCaretColor(new java.awt.Color(255, 0, 0));

        lblPositif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPositif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPositif.setText("Kasus Positif");

        editNegara_ENG.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNegara_ENG.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNegara_ENG.setText("Japan");
        editNegara_ENG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNegara_ENG.setCaretColor(new java.awt.Color(255, 0, 0));

        lblNamaNegara_ENG.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaNegara_ENG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaNegara_ENG.setText("Nama Negara (ENG)");

        lblEditData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblEditData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditData.setText("Edit Data Kasus Covid-19");

        editKritis.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editKritis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editKritis.setText("166");
        editKritis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editKritis.setCaretColor(new java.awt.Color(255, 0, 0));

        lblKritis.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblKritis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKritis.setText("Kasus Kritis");

        editSembuh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editSembuh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editSembuh.setText("72538");
        editSembuh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editSembuh.setCaretColor(new java.awt.Color(255, 0, 0));

        lblSembuh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSembuh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSembuh.setText("Sembuh");

        lblBendera.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblBendera.setText("Bendera Negara");

        lblKematian.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblKematian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKematian.setText("Kematian");

        editKematian.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editKematian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editKematian.setText("1520");
        editKematian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editKematian.setCaretColor(new java.awt.Color(255, 0, 0));

        editAktif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAktif.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAktif.setText("5983");
        editAktif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAktif.setCaretColor(new java.awt.Color(255, 0, 0));

        lblAktif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAktif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAktif.setText("Kasus Aktif");

        editPopulasi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPopulasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPopulasi.setText("126384252");
        editPopulasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPopulasi.setCaretColor(new java.awt.Color(255, 0, 0));

        lblPopulasi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPopulasi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPopulasi.setText("Populasi");

        editTingkatKesembuhan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTingkatKesembuhan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTingkatKesembuhan.setText("98.5%");
        editTingkatKesembuhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTingkatKesembuhan.setCaretColor(new java.awt.Color(255, 0, 0));

        lblTingkatSembuh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTingkatSembuh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTingkatSembuh.setText("Tingkat Kesembuhan");

        editTingkatKematian.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTingkatKematian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTingkatKematian.setText("2.5%");
        editTingkatKematian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTingkatKematian.setCaretColor(new java.awt.Color(255, 0, 0));

        lblTingkatKematian.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTingkatKematian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTingkatKematian.setText("Tingkat Kematian");

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

        lblEditBendera.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblEditBendera.setText("Edit Bendera");

        lblKembali.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-back.png"))); // NOI18N

        lblShowBendera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblShowBendera.setText("Bendera");

        editBenua.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editBenua.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editBenua.setText("Asia");
        editBenua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editBenua.setCaretColor(new java.awt.Color(255, 0, 0));

        lblBenua.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblBenua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBenua.setText("Benua");

        editDiubah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editDiubah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editDiubah.setText("30 Oktober 2020");
        editDiubah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editDiubah.setCaretColor(new java.awt.Color(255, 0, 0));

        lblDiubah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblDiubah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDiubah.setText("Terakhir Diubah");

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
                                    .addComponent(lblCariNegara, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(inpCari, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlMainLayout.createSequentialGroup()
                                    .addComponent(btnAdd)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnHapus))
                                .addComponent(line1))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 13, Short.MAX_VALUE)))
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
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEditData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editNegara_IDN)
                                    .addComponent(lblNamaNegara_IDN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editPositif)
                                    .addComponent(lblPositif, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblShowBendera, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblEditBendera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(48, 48, 48))
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblBendera, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addComponent(lblKematian, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(editKematian)
                                    .addComponent(editAktif)
                                    .addComponent(lblAktif, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(editTingkatKesembuhan)
                                    .addComponent(lblTingkatSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                                .addGap(39, 39, 39)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(editKritis)
                                            .addComponent(lblKritis, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                            .addComponent(editPopulasi)
                                            .addComponent(lblPopulasi, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                            .addComponent(editTingkatKematian)
                                            .addComponent(lblTingkatKematian, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(editPeringkat)
                                    .addComponent(lblPeringkat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editNegara_ENG)
                                    .addComponent(lblNamaNegara_ENG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editSembuh)
                                    .addComponent(lblSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(line3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editBenua)
                                    .addComponent(lblBenua, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSimpan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBatal)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editDiubah)
                                    .addComponent(lblDiubah, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(line4))
                        .addGap(23, 23, 23))))
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
                            .addComponent(lblCariNegara, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(inpCari)))
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
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPeringkat)
                                    .addComponent(lblBendera))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(editPeringkat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEditBendera)))
                            .addComponent(lblShowBendera, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblNamaNegara_ENG)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editNegara_ENG, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSembuh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editSembuh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblNamaNegara_IDN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editNegara_IDN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblPositif)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editPositif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKritis)
                            .addComponent(lblKematian))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editKritis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editKematian, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPopulasi)
                            .addComponent(lblAktif))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editPopulasi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editAktif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblTingkatSembuh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editTingkatKesembuhan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblTingkatKematian)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editTingkatKematian, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblBenua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editBenua, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblDiubah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editDiubah, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        this.editPeringkat.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editPositif.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editSembuh.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editNegara_IDN.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editNegara_ENG.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editKematian.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editAktif.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editPopulasi.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editTingkatKesembuhan.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editKritis.setBorder(javax.swing.BorderFactory.createLineBorder(color));
        this.editTingkatKematian.setBorder(javax.swing.BorderFactory.createLineBorder(color));
//        this.editTglDibuat.setBorder(javax.swing.BorderFactory.createLineBorder(color));
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
            java.util.logging.Logger.getLogger(UpdateCovidDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateCovidDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateCovidDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateCovidDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateCovidDunia().setVisible(true);
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
    private javax.swing.JTextField editAktif;
    private javax.swing.JTextField editBenua;
    private javax.swing.JTextField editDiubah;
    private javax.swing.JTextField editKematian;
    private javax.swing.JTextField editKritis;
    private javax.swing.JTextField editNegara_ENG;
    private javax.swing.JTextField editNegara_IDN;
    private javax.swing.JTextField editPeringkat;
    private javax.swing.JTextField editPopulasi;
    private javax.swing.JTextField editPositif;
    private javax.swing.JTextField editSembuh;
    private javax.swing.JTextField editTingkatKematian;
    private javax.swing.JTextField editTingkatKesembuhan;
    private javax.swing.JTextField inpCari;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAktif;
    private javax.swing.JLabel lblBendera;
    private javax.swing.JLabel lblBenua;
    private javax.swing.JLabel lblCariNegara;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDiubah;
    private javax.swing.JLabel lblEditBendera;
    private javax.swing.JLabel lblEditData;
    private javax.swing.JLabel lblKematian;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblKeyword;
    private javax.swing.JLabel lblKritis;
    private javax.swing.JLabel lblLeft;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamaNegara_ENG;
    private javax.swing.JLabel lblNamaNegara_IDN;
    private javax.swing.JLabel lblPeringkat;
    private javax.swing.JLabel lblPopulasi;
    private javax.swing.JLabel lblPositif;
    private javax.swing.JLabel lblSembuh;
    private javax.swing.JLabel lblShowBendera;
    private javax.swing.JLabel lblTingkatKematian;
    private javax.swing.JLabel lblTingkatSembuh;
    private javax.swing.JLabel lblTop;
    private javax.swing.JSeparator line1;
    private javax.swing.JSeparator line2;
    private javax.swing.JSeparator line3;
    private javax.swing.JSeparator line4;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTable tabelNegara;
    // End of variables declaration//GEN-END:variables
}
