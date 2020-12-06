package com.window.admin;

import com.database.CovidCases;
import com.media.audio.Audio;
import com.media.gambar.Gambar;
import com.sun.glass.events.KeyEvent;
import com.window.all.Beranda;
import java.awt.Color;
import java.awt.Cursor;

import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-12-03
 */
public class UpdateCovidIndo extends javax.swing.JFrame {
    
    /**
     * Digunakan untuk mengambil dan mengedit data dari kasus covid-19 indonesia
     */
    private final CovidCases dataIndo = new CovidCases(CovidCases.KASUS_INDO);
    /**
     * Digunakan untuk menyimpan hasil filter dari input cari negara
     */
    private String keyword = "";
    /**
     * Digunakan untuk menyimpan data dari kasus covid indonesia yang berbentuk <code>String</code>
     */
    private String prov_selected, kode, provinsi, kasusPertama, diubah, website, lambang;
    /**
     * Fields / data yang akan ditampilkan ke dalam tabel
     */
    private final String[] fields = new String[]{CovidCases.PROVINSI, CovidCases.KASUS, CovidCases.SEMBUH, CovidCases.KEMATIAN};
    /**
     * Digunakan untuk menyimpan data dari kasus covid indonesia yang berbentuk <code>Integer</code>
     */
    private int positif, sembuh, kematian, aktif, totalKab, zonaMerah, zonaOren, zonaHijau;
    /**
     * Digunakan untuk mengatur posisi dari window
     */
    private int x, y;
    /**
     * Digunakan untuk mengedit data
     */
    private boolean isEdit = false;
    
    public UpdateCovidIndo() {
        initComponents();
        
        this.setIconImage(Gambar.getWindowIcon());
        this.setLocationRelativeTo(null);
        this.tabelProvinsi.setRowHeight(30);
        this.tabelProvinsi.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelProvinsi.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        this.btnSimpan.setVisible(false);
        this.btnBatal.setVisible(false);
        this.lblLambangProvinsi.setText("");
        
        prov_selected = "Jatim";
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
            this.btnBeranda, this.btnInfo, this.btnDataUser, this.btnDataCovidDunia, 
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
     * Method ini digunakan untuk mengatur apakah sebuah fields yang digunakan untuk mengedit data 
     * diperbolehkan untuk diedit atau tidak. Data diperbolehkan diedit jika parameter <code>editData</code> memiliki 
     * nilai <b>True</b>. Tapi jika parameter <code>editData</code> memiliki nilai <b>False</b> maka data tidak dapat diedit dan hanya 
     * bersifat read only saja.
     * <br><br>
     * Method ini juga digunakan untuk mengatur field-field mana yang tidak diperbolehkan untuk diedit oleh Admin. Field-Field
     * tersebut antara lain:
     * <UL>
     *  <LI> <b>Username :</b> Username tidak di perbolehkan di edit karena username pada <b>Database</b> bersifat <code>Primary Key</code> / tidak dapat didupliat. </LI>
     *  <LI> <b>Email :</b> Email tidak di perbolehkan di edit karena email pada <b>Database</b> bersifat <code>Primary Key</code> / tidak dapat didupliat.</LI>
     *  <LI> <b>Tipe Akun :</b> Tipe akun tidak dapat diedit karena tipe akun bersifat final (hanya dapat diubah saat pertama kali akun dibuat).</LI> 
     *  <LI> <b>Gender : </b> Gender tidak dapat diedit di window ini karena tidak memandai-nya teknologi yang dipakai.</LI>
     *  <LI> <b>Tanggal Lahir : </b> Tanggal lahir dari user tidak dapat diedit di window ini karena tidak memandai-nya teknologi yang dipakai.</L>
     *  <LI> <b>Password : </b> Password tidak dapat diedit karena merupakan sebuah privasi dari user.</LI>
     *  <LI> <b>Tanggal Dibuat : </b> Tanggal dibuat tidak bisa diedit karena bersifat final (hanya dapat diubah saat pertama kali akun dibuat).</LI>
     * </UL>
     * <br>
     * Field yang tidak disetbutkan diatas merupakan field yang bisa diedit oleh Admin. Selain itu method ini juga digunakan untuk merubah warna pada
     * line border field edit data dan foreground pada label edit data.
     * <br><br>
     * Jika parameter bernilai <b>True</b> maka line border pada field edit data akan memiliki warna biru dan label edit data akan memilik warna hitam. 
     * Tapi jika parameter bernilai <b>False</b> maka line border pada filed edit data akan memiliki warna hitam dan label edit data akan memiliki warna biru.
     * 
     * @param editData Jika <code>editData</code> benilai <b>True</b> maka data dapat diedit. Tapi jika parameter <code>editData</code> bernilai <b>False</b> maka data tidak dapat diedit.
     */
    private void setEditableData(final boolean editData){
        // isEdit nilai-nya akan sama dengan editData
        isEdit = editData;
        
        // label edit data
        JLabel[] labels = new JLabel[]{
            this.lblAktif, this.lblDiubah, this.lblKasusPertama, this.lblKematian, this.lblNamaKodeProv, this.lblNamaProv,
            this.lblPositif, this.lblSembuh, this.lblTotalKab, this.lblWebsite, this.lblZonaHijau, this.lblZonaMerah, this.lblZonaOren
        };
        // fields edit data
        JTextField[] edits = new JTextField[]{
            this.editAktif, this.editDiubah, this.editKasusPertama, this.editKematian, this.editKodeProv, this.editPositif, this.editProvinsi,
            this.editSembuh, this.editTotalKab, this.editWebsite, this.editZonaHijau, this.editZonaMerah, this.editZonaOranye
        };
        // fields edit data yang tidak bisa di edit oleh admin
        JTextField[] notEditable = new JTextField[]{
            this.editKodeProv, this.editProvinsi, this.editTotalKab, this.editKasusPertama, this.editDiubah
        };
        
        // jika parameter bernilai True
        if(isEdit){
            // menyembunyikan button edit dan menampilkan button simpan, batal
            this.btnEdit.setVisible(false);
            this.btnSimpan.setVisible(true);
            this.btnBatal.setVisible(true);
                // merubah fields edit data agar dapat diedit dan mengubah warna line border pada fields edit data ke warna biru
                for(JTextField field : edits){
                    // merubah fields edit data agar dapat diedit
                    field.setEditable(true);
                    field.setEnabled(true);
                    // merubah warna line border
                    field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,106,255)));
                }
                // menonaktifkan field-field yang tidak bisa diedit oleh admin
                for(JTextField noEdit : notEditable){
                    noEdit.setEditable(false);
                    noEdit.setEnabled(false);
                }
                // merubah warna foreground pada label edit ke warna hitam
                for(JLabel label : labels){
                    label.setForeground(new Color(0,0,0));
                }
        }else{// jika parameter bernilai False
            // menampilkan button edit dan menyembunyikan button simpan, batal
            this.btnEdit.setVisible(true);
            this.btnSimpan.setVisible(false);
            this.btnBatal.setVisible(false);
                // merubah fields edit data agar tidak dapat diedit dan mengubah warna line border pada fields edit data ke warna hitam
                for(JTextField field : edits){
                    // merubah fields edit data agar tidak dapat diedit
                    field.setEditable(true);
                    field.setEnabled(false);
                    // merubah warna foreground ke warna hitam
                    field.setDisabledTextColor(new Color(0,0,0));
                    // merubah warna line border
                    field.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
                }
                // merubah warna foreground pada label edit ke warna biru
                for(JLabel label : labels){
                    label.setForeground(new Color(10,72,201));
                }
        }
    }
    
    /**
     * Digunakan untuk menampilkan data kasus Covid-19 dunia ke dalam tabel berdasrakan keyword yang diinputkan user
     */
    private void dataTabel(){
        tabelProvinsi.setModel(new javax.swing.table.DefaultTableModel(
            dataIndo.getData(fields, keyword, true),
            new String [] {
                "Provinsi", "Positif", "Sembuh", "Kematian"
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
     * Digunakan untuk mendapatkan data Covid-19 yang ada didalam database melalui class CovidCases. 
     * Lalu datanya akan ditampilkan ke dalam window
     */
    private void showData(){
        // mengambil data kasus covid indo dari database
        kode = dataIndo.getData(CovidCases.KODE_PROV, this.prov_selected);
        provinsi = dataIndo.getData(CovidCases.PROVINSI, this.prov_selected);
        positif = dataIndo.getDataNumber(CovidCases.KASUS, this.prov_selected);
        sembuh = dataIndo.getDataNumber(CovidCases.SEMBUH, this.prov_selected);
        kematian = dataIndo.getDataNumber(CovidCases.KEMATIAN, this.prov_selected);
        aktif = dataIndo.getDataNumber(CovidCases.AKTIF, this.prov_selected);
        totalKab = dataIndo.getDataNumber(CovidCases.TOTAL_KAB, this.prov_selected);
        zonaMerah = dataIndo.getDataNumber(CovidCases.ZONA_MERAH, this.prov_selected);
        zonaOren = dataIndo.getDataNumber(CovidCases.ZONA_ORANYE, this.prov_selected);
        zonaHijau = dataIndo.getDataNumber(CovidCases.ZONA_HIJAU, this.prov_selected);
        kasusPertama = dataIndo.getData(CovidCases.KASUS_PERTAMA, this.prov_selected);
        diubah = dataIndo.getData(CovidCases.DIUBAH, this.prov_selected);
        website = dataIndo.getData(CovidCases.WEBSITE, this.prov_selected);
        lambang = dataIndo.getData(CovidCases.LAMBANG, this.prov_selected);
        
        // menampilkan data covid ke window
        this.lblLambangProvinsi.setIcon(Gambar.getLambangProvinsi(lambang));
        this.editKodeProv.setText(kode);
        this.editProvinsi.setText(provinsi);
        this.editPositif.setText(dataIndo.addDelim(positif));
        this.editSembuh.setText(dataIndo.addDelim(sembuh));
        this.editKematian.setText(dataIndo.addDelim(kematian));
        this.editAktif.setText(dataIndo.addDelim(aktif));
        this.editTotalKab.setText(dataIndo.addDelim(totalKab));
        this.editZonaMerah.setText(dataIndo.addDelim(zonaMerah));
        this.editZonaOranye.setText(dataIndo.addDelim(zonaOren));
        this.editZonaHijau.setText(dataIndo.addDelim(zonaHijau));
        this.editKasusPertama.setText(dataIndo.dateToString(kasusPertama));
        this.editDiubah.setText(dataIndo.dateToString(diubah));
        this.editWebsite.setText(website);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelProvinsi = new javax.swing.JTable();
        inpCariProvinsi = new javax.swing.JTextField();
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
        lblNamaProv = new javax.swing.JLabel();
        editProvinsi = new javax.swing.JTextField();
        editKodeProv = new javax.swing.JTextField();
        lblNamaKodeProv = new javax.swing.JLabel();
        editSembuh = new javax.swing.JTextField();
        lblSembuh = new javax.swing.JLabel();
        editPositif = new javax.swing.JTextField();
        lblPositif = new javax.swing.JLabel();
        lblEditData = new javax.swing.JLabel();
        editTotalKab = new javax.swing.JTextField();
        lblTotalKab = new javax.swing.JLabel();
        editKematian = new javax.swing.JTextField();
        lblKematian = new javax.swing.JLabel();
        lblProvinsi = new javax.swing.JLabel();
        lblAktif = new javax.swing.JLabel();
        editAktif = new javax.swing.JTextField();
        editZonaMerah = new javax.swing.JTextField();
        lblZonaMerah = new javax.swing.JLabel();
        editZonaOranye = new javax.swing.JTextField();
        lblZonaOren = new javax.swing.JLabel();
        editZonaHijau = new javax.swing.JTextField();
        lblZonaHijau = new javax.swing.JLabel();
        editWebsite = new javax.swing.JTextField();
        lblWebsite = new javax.swing.JLabel();
        line3 = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        line4 = new javax.swing.JSeparator();
        lblEditLambang = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();
        lblLambangProvinsi = new javax.swing.JLabel();
        editKasusPertama = new javax.swing.JTextField();
        lblKasusPertama = new javax.swing.JLabel();
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

        tabelProvinsi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tabelProvinsi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Provinsi", "Positif", "Sembuh", "Kematian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelProvinsi.setGridColor(new java.awt.Color(0, 0, 0));
        tabelProvinsi.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelProvinsi.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tabelProvinsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelProvinsiMouseClicked(evt);
            }
        });
        tabelProvinsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelProvinsiKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelProvinsi);

        inpCariProvinsi.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        inpCariProvinsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariProvinsiKeyTyped(evt);
            }
        });

        lblCari.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCari.setForeground(new java.awt.Color(224, 56, 56));
        lblCari.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCari.setText("Cari Provinsi");

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

        btnDataUser.setBackground(new java.awt.Color(49, 144, 215));
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

        btnDataCovidIndo.setBackground(new java.awt.Color(34, 119, 237));
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
        lblTop.setText("Data Kasus Covid-19 Di Indonesia");

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
        btnAdd.setText("Add Provinsi");
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

        lblNamaProv.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaProv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaProv.setText("Provinsi");

        editProvinsi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editProvinsi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editProvinsi.setText("Jawa Timur");
        editProvinsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editProvinsi.setCaretColor(new java.awt.Color(255, 0, 0));
        editProvinsi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editProvinsiMouseClicked(evt);
            }
        });

        editKodeProv.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editKodeProv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editKodeProv.setText("Jatim");
        editKodeProv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editKodeProv.setCaretColor(new java.awt.Color(255, 0, 0));
        editKodeProv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editKodeProvMouseClicked(evt);
            }
        });

        lblNamaKodeProv.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaKodeProv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaKodeProv.setText("Kode Provinsi");

        editSembuh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editSembuh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editSembuh.setText("53131");
        editSembuh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editSembuh.setCaretColor(new java.awt.Color(255, 0, 0));
        editSembuh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editSembuhMouseClicked(evt);
            }
        });

        lblSembuh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSembuh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSembuh.setText("Sembuh");

        editPositif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPositif.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPositif.setText("60190");
        editPositif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPositif.setCaretColor(new java.awt.Color(255, 0, 0));
        editPositif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editPositifMouseClicked(evt);
            }
        });

        lblPositif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPositif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPositif.setText("Kasus Positif");

        lblEditData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblEditData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditData.setText("Edit Data Kasus Covid-19");

        editTotalKab.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTotalKab.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTotalKab.setText("29");
        editTotalKab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTotalKab.setCaretColor(new java.awt.Color(255, 0, 0));
        editTotalKab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTotalKabMouseClicked(evt);
            }
        });

        lblTotalKab.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTotalKab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalKab.setText("Total Kabupaten");

        editKematian.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editKematian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editKematian.setText("4275");
        editKematian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editKematian.setCaretColor(new java.awt.Color(255, 0, 0));
        editKematian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editKematianMouseClicked(evt);
            }
        });

        lblKematian.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblKematian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKematian.setText("Kematian");

        lblProvinsi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblProvinsi.setText("Lambang Provinsi");

        lblAktif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAktif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAktif.setText("Kasus Aktif");

        editAktif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAktif.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAktif.setText("2784");
        editAktif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAktif.setCaretColor(new java.awt.Color(255, 0, 0));
        editAktif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editAktifMouseClicked(evt);
            }
        });

        editZonaMerah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editZonaMerah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editZonaMerah.setText("0");
        editZonaMerah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editZonaMerah.setCaretColor(new java.awt.Color(255, 0, 0));
        editZonaMerah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editZonaMerahMouseClicked(evt);
            }
        });

        lblZonaMerah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblZonaMerah.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblZonaMerah.setText("Kab. Zona Merah");

        editZonaOranye.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editZonaOranye.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editZonaOranye.setText("0");
        editZonaOranye.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editZonaOranye.setCaretColor(new java.awt.Color(255, 0, 0));
        editZonaOranye.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editZonaOranyeMouseClicked(evt);
            }
        });

        lblZonaOren.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblZonaOren.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblZonaOren.setText("Kab. Zona Oranye");

        editZonaHijau.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editZonaHijau.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editZonaHijau.setText("0");
        editZonaHijau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editZonaHijau.setCaretColor(new java.awt.Color(255, 0, 0));
        editZonaHijau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editZonaHijauMouseClicked(evt);
            }
        });

        lblZonaHijau.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblZonaHijau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblZonaHijau.setText("Kab. Zona Hijau");

        editWebsite.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editWebsite.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editWebsite.setText("infocovid19.jatimprov.go.id");
        editWebsite.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editWebsite.setCaretColor(new java.awt.Color(255, 0, 0));
        editWebsite.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editWebsiteMouseClicked(evt);
            }
        });

        lblWebsite.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblWebsite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWebsite.setText("Website Provinsi");

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

        lblEditLambang.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblEditLambang.setText("Edit Lambang");
        lblEditLambang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditLambangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEditLambangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEditLambangMouseExited(evt);
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

        lblLambangProvinsi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLambangProvinsi.setText("Lambang");

        editKasusPertama.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editKasusPertama.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editKasusPertama.setText("17 Maret 2020");
        editKasusPertama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editKasusPertama.setCaretColor(new java.awt.Color(255, 0, 0));
        editKasusPertama.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editKasusPertamaMouseClicked(evt);
            }
        });

        lblKasusPertama.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblKasusPertama.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKasusPertama.setText("Kasus Pertama");

        editDiubah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editDiubah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editDiubah.setText("27 November 2020");
        editDiubah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editDiubah.setCaretColor(new java.awt.Color(255, 0, 0));
        editDiubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editDiubahMouseClicked(evt);
            }
        });

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
                                    .addComponent(lblCari, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(inpCariProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGap(440, 440, 440)
                        .addComponent(lblKembali)
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
                                    .addComponent(editProvinsi)
                                    .addComponent(lblNamaProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editSembuh)
                                    .addComponent(lblSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblLambangProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblEditLambang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(48, 48, 48))
                                            .addGroup(pnlMainLayout.createSequentialGroup()
                                                .addComponent(lblProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addComponent(lblAktif, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(editAktif)
                                    .addComponent(editZonaMerah)
                                    .addComponent(lblZonaMerah, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(editZonaHijau)
                                    .addComponent(lblZonaHijau, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                                .addGap(39, 39, 39)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createSequentialGroup()
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(editTotalKab)
                                            .addComponent(lblTotalKab, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                            .addComponent(editZonaOranye)
                                            .addComponent(lblZonaOren, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                            .addComponent(editWebsite)
                                            .addComponent(lblWebsite, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(editKodeProv)
                                    .addComponent(lblNamaKodeProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editPositif)
                                    .addComponent(lblPositif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editKematian)
                                    .addComponent(lblKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(line3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(editKasusPertama)
                                    .addComponent(lblKasusPertama, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(lblCari, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                            .addComponent(inpCariProvinsi)))
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
                                    .addComponent(lblNamaKodeProv)
                                    .addComponent(lblProvinsi))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(editKodeProv, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblEditLambang)))
                            .addComponent(lblLambangProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblPositif)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editPositif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblKematian)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editKematian, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblNamaProv)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblSembuh)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editSembuh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalKab)
                            .addComponent(lblAktif))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editTotalKab, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editAktif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblZonaOren)
                            .addComponent(lblZonaMerah))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editZonaOranye, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editZonaMerah, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblZonaHijau)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editZonaHijau, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblWebsite)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editWebsite, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblKasusPertama)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editKasusPertama, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addContainerGap(22, Short.MAX_VALUE))))
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
        int jumlahProv = dataIndo.getRows("SELECT * FROM kasuscovid_indo");
        if(jumlahProv >= 34){
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Jumlah Provinsi Di Indonesia cuma ada 34!!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
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
        System.out.println("Membuka Window DeleteData");
        DeleteData delete = new DeleteData(DeleteData.UPDATE_INDO);
        delete.setLocation(this.getX(), this.getY());
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                delete.setVisible(true);
            }
        });
        dispose();        
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

    private void tabelProvinsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelProvinsiMouseClicked
        this.setEditableData(false);
        // mendapatkan negara yang dipilih oleh user
        this.prov_selected = this.tabelProvinsi.getValueAt(this.tabelProvinsi.getSelectedRow(), 0).toString();
        // menampilkan data dari negara yang dipilih oleh user
        showData();
    }//GEN-LAST:event_tabelProvinsiMouseClicked

    private void tabelProvinsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelProvinsiKeyPressed
        this.setEditableData(false);
        // menangkap event jika user menekan tombol arah atas atau arah bawah
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            // mendapatkan provinsi yang sedang dipilih
            prov_selected = this.tabelProvinsi.getValueAt(tabelProvinsi.getSelectedRow() - 1, 0).toString(); // -1 artinya berpindah mundur dari index dari negara sebelumnya ke index negara saat ini
            // mereset data kasus covid yang ditampilkan diwindow
            showData();
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            // mendapatkan provinsi yang sedang dipilih
            prov_selected = this.tabelProvinsi.getValueAt(tabelProvinsi.getSelectedRow() + 1, 0).toString(); // +1 artinya berpindah maju dari index dari negara sebelumnya ke index negara saat ini
            // mereset data kasus covid yang ditampilkan diwindow
            showData();
        }
    }//GEN-LAST:event_tabelProvinsiKeyPressed

    private void inpCariProvinsiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariProvinsiKeyTyped
        // mendapatkan provinsi yang dicari oleh user
        keyword = this.inpCariProvinsi.getText();
        // mendapatkan total data yang karakternya mirip degan negara yang sedang dicari user
        int row = dataIndo.getRows("SELECT * FROM kasuscovid_indo WHERE kode LIKE '%"+ keyword +"%' OR provinsi LIKE '%"+ keyword +"%' ORDER BY kasus DESC;");
        // mereset lbl show keyword
        this.lblKeyword.setText("Menampilkan "+ row +" data dengan keyword = \""+keyword+"\"");
        // mereset tabel
        dataTabel();
    }//GEN-LAST:event_inpCariProvinsiKeyTyped

    private void lblEditLambangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditLambangMouseClicked
        Audio.play(Audio.SOUND_INFO);
        JOptionPane.showMessageDialog(null, "Fitur 'Edit Lambang' untuk saat ini belum tersedia!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblEditLambangMouseClicked

    private void lblEditLambangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditLambangMouseEntered
        this.lblEditLambang.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Edit Lambang</p></html>");
        this.lblEditLambang.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblEditLambangMouseEntered

    private void lblEditLambangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditLambangMouseExited
        this.lblEditLambang.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0);\">Edit Lambang</p></html>");
        this.lblEditLambang.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblEditLambangMouseExited

    private void editKodeProvMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editKodeProvMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editKodeProvMouseClicked

    private void editProvinsiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProvinsiMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editProvinsiMouseClicked

    private void editPositifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPositifMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editPositifMouseClicked

    private void editSembuhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editSembuhMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editSembuhMouseClicked

    private void editKematianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editKematianMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editKematianMouseClicked

    private void editAktifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editAktifMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editAktifMouseClicked

    private void editTotalKabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTotalKabMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editTotalKabMouseClicked

    private void editZonaMerahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editZonaMerahMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editZonaMerahMouseClicked

    private void editZonaOranyeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editZonaOranyeMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editZonaOranyeMouseClicked

    private void editZonaHijauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editZonaHijauMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editZonaHijauMouseClicked

    private void editWebsiteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editWebsiteMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editWebsiteMouseClicked

    private void editKasusPertamaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editKasusPertamaMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editKasusPertamaMouseClicked

    private void editDiubahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editDiubahMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            
        }else{
           Audio.play(Audio.SOUND_WARNING);
           JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editDiubahMouseClicked
 
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
            java.util.logging.Logger.getLogger(UpdateCovidIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateCovidIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateCovidIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateCovidIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateCovidIndo().setVisible(true);
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
    private javax.swing.JTextField editDiubah;
    private javax.swing.JTextField editKasusPertama;
    private javax.swing.JTextField editKematian;
    private javax.swing.JTextField editKodeProv;
    private javax.swing.JTextField editPositif;
    private javax.swing.JTextField editProvinsi;
    private javax.swing.JTextField editSembuh;
    private javax.swing.JTextField editTotalKab;
    private javax.swing.JTextField editWebsite;
    private javax.swing.JTextField editZonaHijau;
    private javax.swing.JTextField editZonaMerah;
    private javax.swing.JTextField editZonaOranye;
    private javax.swing.JTextField inpCariProvinsi;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAktif;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDiubah;
    private javax.swing.JLabel lblEditData;
    private javax.swing.JLabel lblEditLambang;
    private javax.swing.JLabel lblKasusPertama;
    private javax.swing.JLabel lblKematian;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblKeyword;
    private javax.swing.JLabel lblLambangProvinsi;
    private javax.swing.JLabel lblLeft;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamaKodeProv;
    private javax.swing.JLabel lblNamaProv;
    private javax.swing.JLabel lblPositif;
    private javax.swing.JLabel lblProvinsi;
    private javax.swing.JLabel lblSembuh;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblTotalKab;
    private javax.swing.JLabel lblWebsite;
    private javax.swing.JLabel lblZonaHijau;
    private javax.swing.JLabel lblZonaMerah;
    private javax.swing.JLabel lblZonaOren;
    private javax.swing.JSeparator line1;
    private javax.swing.JSeparator line2;
    private javax.swing.JSeparator line3;
    private javax.swing.JSeparator line4;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTable tabelProvinsi;
    // End of variables declaration//GEN-END:variables
}
