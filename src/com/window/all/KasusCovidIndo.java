package com.window.all;

import com.media.gambar.Gambar;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-27
 */
public class KasusCovidIndo extends javax.swing.JFrame {

    private int x, y;
    
    public KasusCovidIndo() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.tabelKasus.getTableHeader().remove(0);
        this.tabelKasus.setRowHeight(29);
        this.tabelKasus.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelKasus.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblClose = new javax.swing.JLabel();
        lblMinimaze = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelKasus = new javax.swing.JTable();
        pnlBoxInfo = new javax.swing.JPanel();
        lblCopyright = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        lblInfo = new javax.swing.JLabel();
        lblInfoProvinsi = new javax.swing.JLabel();
        lblInfoPositif = new javax.swing.JLabel();
        lblInfoSembuh = new javax.swing.JLabel();
        lblInfoKematian = new javax.swing.JLabel();
        lblInfoAktif = new javax.swing.JLabel();
        lblInfoODP = new javax.swing.JLabel();
        valInfoProvinsi = new javax.swing.JLabel();
        valInfoPositif = new javax.swing.JLabel();
        valInfoSembuh = new javax.swing.JLabel();
        valInfoKematian = new javax.swing.JLabel();
        valInfoAktif = new javax.swing.JLabel();
        valInfoODP = new javax.swing.JLabel();
        lblInfoTotalKab = new javax.swing.JLabel();
        valInfoTotalKab = new javax.swing.JLabel();
        lblInfoKabZonaMerah = new javax.swing.JLabel();
        valInfoKabZonaMerah = new javax.swing.JLabel();
        lblInfoKabZonaOren = new javax.swing.JLabel();
        valInfoKabZonaOren = new javax.swing.JLabel();
        lblInfoKabZonaHijau = new javax.swing.JLabel();
        valInfoKabZonaHijau = new javax.swing.JLabel();
        lblInfoTingkatKesembuhan = new javax.swing.JLabel();
        valInfoTingkatKesembuhan = new javax.swing.JLabel();
        lblInfoTingkatKematian = new javax.swing.JLabel();
        valInfoTingkatKematian = new javax.swing.JLabel();
        lblInfoPDP = new javax.swing.JLabel();
        valInfoPDP = new javax.swing.JLabel();
        lblInfoPeringkat = new javax.swing.JLabel();
        valInfoPeringkat = new javax.swing.JLabel();
        lblInfoOTG = new javax.swing.JLabel();
        valInfoOTG = new javax.swing.JLabel();
        lblInfoTerakhirDiubah = new javax.swing.JLabel();
        valInfoTerahirDiubah = new javax.swing.JLabel();
        linePemisah = new javax.swing.JSeparator();
        lblShowKeyword = new javax.swing.JLabel();
        searchKeyword = new javax.swing.JTextField();
        lblCariProvinsi = new javax.swing.JLabel();
        lblTabelKasus = new javax.swing.JLabel();
        lblTop = new javax.swing.JLabel();
        lblProvinsi = new javax.swing.JLabel();
        lblTotalKasus = new javax.swing.JLabel();
        valTotalKasus = new javax.swing.JLabel();
        valTotalSembuh = new javax.swing.JLabel();
        lblTotalSembuh = new javax.swing.JLabel();
        valTotalKematian = new javax.swing.JLabel();
        lblTotalKematian = new javax.swing.JLabel();
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

        tabelKasus.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tabelKasus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Provinsi", "Positif", "Sembuh", "Kematian"
            }
        ));
        tabelKasus.setGridColor(new java.awt.Color(0, 0, 0));
        tabelKasus.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelKasus.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tabelKasus.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelKasus);

        pnlBoxInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnlBoxInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblCopyright.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCopyright.setText("Copyright © 2020. Achmad Baihaqi");

        pnlInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInfo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfo.setText("Informasi Lengkap");
        lblInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInfoProvinsi.setText("Provinsi");

        lblInfoPositif.setText("Kasus Positif");

        lblInfoSembuh.setText("Sembuh");

        lblInfoKematian.setText("Kematian");

        lblInfoAktif.setText("Kasus Aktif");

        lblInfoODP.setText("Total ODP");

        valInfoProvinsi.setText(": Jawa Timur");

        valInfoPositif.setText(": 80.041");

        valInfoSembuh.setText(": 72.538");

        valInfoKematian.setText(": 1.545");

        valInfoAktif.setText(": 5.983");

        valInfoODP.setText(": 166");

        lblInfoTotalKab.setText("Total Kabupaten");

        valInfoTotalKab.setText(": 32");

        lblInfoKabZonaMerah.setText("Kab Zona Merah");

        valInfoKabZonaMerah.setText(": 10");

        lblInfoKabZonaOren.setText("Kab Zona Oranye");

        valInfoKabZonaOren.setText(": 20");

        lblInfoKabZonaHijau.setText("Kab Zona Hjau");

        valInfoKabZonaHijau.setText(": 2");

        lblInfoTingkatKesembuhan.setText("Tingkat Kesembuhan");

        valInfoTingkatKesembuhan.setText(": 98.2%");

        lblInfoTingkatKematian.setText("Tingkat Kematian");

        valInfoTingkatKematian.setText(": 2.8%");

        lblInfoPDP.setText("Total PDP");

        valInfoPDP.setText(": 2,433");

        lblInfoPeringkat.setText("Kasus Terbanyak Ke");

        valInfoPeringkat.setText(": 3 dari 34 Provinsi");

        lblInfoOTG.setText("Total OTG");

        valInfoOTG.setText(": 1.433");

        lblInfoTerakhirDiubah.setText("Terakhir Diubah");

        valInfoTerahirDiubah.setText(": 2 November 2020");

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblInfoOTG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoPDP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoProvinsi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoPositif, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(lblInfoSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoAktif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoODP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valInfoProvinsi, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(valInfoAktif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoPositif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoODP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoPDP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoOTG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInfoKabZonaMerah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoKabZonaOren, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(lblInfoTotalKab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoKabZonaHijau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoTingkatKesembuhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoTingkatKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoPeringkat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoTerakhirDiubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valInfoTotalKab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoKabZonaMerah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoKabZonaHijau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoTingkatKesembuhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoTingkatKematian, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(valInfoKabZonaOren, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoPeringkat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoTerahirDiubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoProvinsi)
                    .addComponent(valInfoProvinsi)
                    .addComponent(lblInfoTotalKab)
                    .addComponent(valInfoTotalKab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoPositif)
                    .addComponent(valInfoPositif)
                    .addComponent(lblInfoKabZonaMerah)
                    .addComponent(valInfoKabZonaMerah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoSembuh)
                    .addComponent(valInfoSembuh)
                    .addComponent(lblInfoKabZonaOren)
                    .addComponent(valInfoKabZonaOren))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoKematian)
                    .addComponent(valInfoKematian)
                    .addComponent(lblInfoKabZonaHijau)
                    .addComponent(valInfoKabZonaHijau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoAktif)
                    .addComponent(valInfoAktif)
                    .addComponent(lblInfoTingkatKesembuhan)
                    .addComponent(valInfoTingkatKesembuhan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoODP)
                    .addComponent(valInfoODP)
                    .addComponent(lblInfoTingkatKematian)
                    .addComponent(valInfoTingkatKematian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoPDP)
                    .addComponent(valInfoPDP)
                    .addComponent(lblInfoPeringkat)
                    .addComponent(valInfoPeringkat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoOTG)
                    .addComponent(valInfoOTG)
                    .addComponent(lblInfoTerakhirDiubah)
                    .addComponent(valInfoTerahirDiubah))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBoxInfoLayout = new javax.swing.GroupLayout(pnlBoxInfo);
        pnlBoxInfo.setLayout(pnlBoxInfoLayout);
        pnlBoxInfoLayout.setHorizontalGroup(
            pnlBoxInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoxInfoLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlBoxInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBoxInfoLayout.createSequentialGroup()
                        .addComponent(lblCopyright, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE))))
        );
        pnlBoxInfoLayout.setVerticalGroup(
            pnlBoxInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoxInfoLayout.createSequentialGroup()
                .addComponent(pnlInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCopyright, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        linePemisah.setForeground(new java.awt.Color(0, 0, 0));
        linePemisah.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblShowKeyword.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblShowKeyword.setText("Menampilkan data dengan keyword = \"Jatim\"");

        searchKeyword.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        lblCariProvinsi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblCariProvinsi.setForeground(new java.awt.Color(224, 56, 56));
        lblCariProvinsi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCariProvinsi.setText("Cari Provinsi : ");

        lblTabelKasus.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTabelKasus.setForeground(new java.awt.Color(11, 113, 233));
        lblTabelKasus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTabelKasus.setText("Data Kasus Covid-19 Di Seluruh Provinsi");

        lblTop.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        lblTop.setForeground(new java.awt.Color(20, 19, 19));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Kasus Covid-19 di Provinsi ");

        lblProvinsi.setFont(new java.awt.Font("Dialog", 1, 19)); // NOI18N
        lblProvinsi.setForeground(new java.awt.Color(20, 19, 19));
        lblProvinsi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProvinsi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-bendera-sementara.png"))); // NOI18N
        lblProvinsi.setText("Jawa Timur");

        lblTotalKasus.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTotalKasus.setForeground(new java.awt.Color(212, 42, 42));
        lblTotalKasus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalKasus.setText("Total Kasus");

        valTotalKasus.setFont(new java.awt.Font("Ebrima", 1, 21)); // NOI18N
        valTotalKasus.setForeground(new java.awt.Color(31, 32, 34));
        valTotalKasus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valTotalKasus.setText("80.041");

        valTotalSembuh.setFont(new java.awt.Font("Ebrima", 1, 21)); // NOI18N
        valTotalSembuh.setForeground(new java.awt.Color(31, 32, 34));
        valTotalSembuh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valTotalSembuh.setText("72.538");

        lblTotalSembuh.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTotalSembuh.setForeground(new java.awt.Color(63, 148, 84));
        lblTotalSembuh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalSembuh.setText("Sembuh");

        valTotalKematian.setFont(new java.awt.Font("Ebrima", 1, 21)); // NOI18N
        valTotalKematian.setForeground(new java.awt.Color(31, 32, 34));
        valTotalKematian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valTotalKematian.setText("1.545");

        lblTotalKematian.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTotalKematian.setForeground(new java.awt.Color(81, 82, 87));
        lblTotalKematian.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTotalKematian.setText("Kematian");

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
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(pnlBoxInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProvinsi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valTotalKasus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalKasus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                            .addComponent(valTotalSembuh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalSembuh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valTotalKematian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTotalKematian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(linePemisah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblKembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMinimaze, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                .addGap(0, 4, Short.MAX_VALUE)
                                .addComponent(lblShowKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addComponent(lblCariProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(searchKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(8, 8, 8))
                                        .addComponent(lblTabelKasus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lblMinimaze, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lblKembali, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(lblTabelKasus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCariProvinsi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblShowKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(linePemisah, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblProvinsi)
                .addGap(21, 21, 21)
                .addComponent(lblTotalKasus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valTotalKasus, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalSembuh, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valTotalSembuh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotalKematian, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valTotalKematian, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlBoxInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void lblKembaliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseEntered
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK_ENTERED));
    }//GEN-LAST:event_lblKembaliMouseEntered

    private void lblKembaliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseExited
        this.lblKembali.setIcon(Gambar.getIcon(Gambar.IC_BACK));
    }//GEN-LAST:event_lblKembaliMouseExited

    private void lblKembaliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblKembaliMouseClicked
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
    }//GEN-LAST:event_lblKembaliMouseClicked

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
            java.util.logging.Logger.getLogger(KasusCovidIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KasusCovidIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KasusCovidIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KasusCovidIndo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KasusCovidIndo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCariProvinsi;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblInfoAktif;
    private javax.swing.JLabel lblInfoKabZonaHijau;
    private javax.swing.JLabel lblInfoKabZonaMerah;
    private javax.swing.JLabel lblInfoKabZonaOren;
    private javax.swing.JLabel lblInfoKematian;
    private javax.swing.JLabel lblInfoODP;
    private javax.swing.JLabel lblInfoOTG;
    private javax.swing.JLabel lblInfoPDP;
    private javax.swing.JLabel lblInfoPeringkat;
    private javax.swing.JLabel lblInfoPositif;
    private javax.swing.JLabel lblInfoProvinsi;
    private javax.swing.JLabel lblInfoSembuh;
    private javax.swing.JLabel lblInfoTerakhirDiubah;
    private javax.swing.JLabel lblInfoTingkatKematian;
    private javax.swing.JLabel lblInfoTingkatKesembuhan;
    private javax.swing.JLabel lblInfoTotalKab;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblProvinsi;
    private javax.swing.JLabel lblShowKeyword;
    private javax.swing.JLabel lblTabelKasus;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblTotalKasus;
    private javax.swing.JLabel lblTotalKematian;
    private javax.swing.JLabel lblTotalSembuh;
    private javax.swing.JSeparator linePemisah;
    private javax.swing.JPanel pnlBoxInfo;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextField searchKeyword;
    private javax.swing.JTable tabelKasus;
    private javax.swing.JLabel valInfoAktif;
    private javax.swing.JLabel valInfoKabZonaHijau;
    private javax.swing.JLabel valInfoKabZonaMerah;
    private javax.swing.JLabel valInfoKabZonaOren;
    private javax.swing.JLabel valInfoKematian;
    private javax.swing.JLabel valInfoODP;
    private javax.swing.JLabel valInfoOTG;
    private javax.swing.JLabel valInfoPDP;
    private javax.swing.JLabel valInfoPeringkat;
    private javax.swing.JLabel valInfoPositif;
    private javax.swing.JLabel valInfoProvinsi;
    private javax.swing.JLabel valInfoSembuh;
    private javax.swing.JLabel valInfoTerahirDiubah;
    private javax.swing.JLabel valInfoTingkatKematian;
    private javax.swing.JLabel valInfoTingkatKesembuhan;
    private javax.swing.JLabel valInfoTotalKab;
    private javax.swing.JLabel valTotalKasus;
    private javax.swing.JLabel valTotalKematian;
    private javax.swing.JLabel valTotalSembuh;
    // End of variables declaration//GEN-END:variables
}