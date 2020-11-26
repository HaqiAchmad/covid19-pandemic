package com.window.all;

import com.database.CovidCases;
import com.media.gambar.Gambar;
import java.awt.event.KeyEvent;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-26
 */
public class KasusCovidDunia extends javax.swing.JFrame {

    private final CovidCases kasus = new CovidCases(CovidCases.KASUS_DUNIA);
    private int positif, sembuh, kematian, aktif, kritis, populasi, tingkatKematian, tingkatKesembuhan, peringkatKasus;
    private int x, y, rowMax = kasus.getRows("SELECT * FROM kasuscovid_dunia"), rows = 0;
    private String keyword, negara = "Dunia", benua, diubah, bendera;
    private final String[] fields = new String[]{CovidCases.NEGARA_IDN, CovidCases.KASUS, CovidCases.SEMBUH, CovidCases.KEMATIAN};
    
    public KasusCovidDunia() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.tabelKasus.getTableHeader().remove(0);
        this.tabelKasus.setRowHeight(29);
        this.tabelKasus.getTableHeader().setBackground(new java.awt.Color(255,255,255));
        this.tabelKasus.getTableHeader().setForeground(new java.awt.Color(0, 0, 0));
        
        dataTabel(this.fields, "");
        showCovidData();
    }
    
    
    
    private void showCovidData(){
        
        System.out.println("\nMendapatkan data kasus Covid-19 dari Negara " + negara);
        positif = kasus.getDataNumber(CovidCases.KASUS, negara);
        sembuh = kasus.getDataNumber(CovidCases.SEMBUH, negara);
        kematian = kasus.getDataNumber(CovidCases.KEMATIAN, negara);
        aktif = kasus.getDataNumber(CovidCases.AKTIF, negara);
        kritis = kasus.getDataNumber(CovidCases.KRITIS, negara);
        populasi = kasus.getDataNumber(CovidCases.POPULASI, negara);
        tingkatKematian = (int)kasus.getPresentase(kematian, sembuh);
        tingkatKesembuhan = (int)kasus.getPresentase(sembuh, kematian);
        peringkatKasus = kasus.getPeringkat(negara);
        benua = kasus.getData(CovidCases.BENUA, negara);
        bendera = kasus.getData(CovidCases.BENDERA, negara);
        diubah = kasus.getData(CovidCases.DIUBAH, negara);
        
        if(negara.equalsIgnoreCase("Dunia")){
            this.lblTop.setText("Kasus Covid-19 di Dunia");
            this.valInfoPopulasi.setText(kasus.addDelim(populasi*10L));
        }else{
            this.lblTop.setText("Kasus Covid-19 di Negara");
            this.valInfoPopulasi.setText(kasus.addDelim(populasi));
        }
        
        this.lblBenderaNegara.setIcon(Gambar.getFlag(bendera));
        this.lblBenderaNegara.setText(negara);
        this.valTotalKasus.setText(kasus.addDelim(positif));
        this.valTotalSembuh.setText(kasus.addDelim(sembuh));
        this.valTotalKematian.setText(kasus.addDelim(kematian));
        this.valInfoNegara.setText(negara);
        this.valInfoPositif.setText(kasus.addDelim(positif));
        this.valInfoSembuh.setText(kasus.addDelim(positif));
        this.valInfoKematian.setText(kasus.addDelim(kematian));
        this.valInfoAktif.setText(kasus.addDelim(aktif));
        this.valInfoKritis.setText(kasus.addDelim(kritis));
        this.valInfoTingkatKesembuhan.setText(""+tingkatKesembuhan + "%");
        this.valInfoTingkatKematian.setText(""+tingkatKematian + "%");
        this.valInfoPeringkatKasus.setText("" + peringkatKasus + " dari " + rowMax + " negara");
        this.valInfoBenua.setText(benua);
        this.valInfoTerakhirDiubah.setText(diubah);
        System.out.println("Data berhasil diambil\n");
        
    }
    
    private void dataTabel(String[] fields, String key){
        tabelKasus.setModel(new javax.swing.table.DefaultTableModel(
            kasus.getData(fields, key),
            new String [] {
                "Negara", "Positif", "Sembuh", "Kematian"
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
        lblInfoNegara = new javax.swing.JLabel();
        lblInfoPositif = new javax.swing.JLabel();
        lblInfoSembuh = new javax.swing.JLabel();
        lblInfoKematian = new javax.swing.JLabel();
        lblInfoAktif = new javax.swing.JLabel();
        lblInfoKritis = new javax.swing.JLabel();
        valInfoNegara = new javax.swing.JLabel();
        valInfoPositif = new javax.swing.JLabel();
        valInfoSembuh = new javax.swing.JLabel();
        valInfoKematian = new javax.swing.JLabel();
        valInfoAktif = new javax.swing.JLabel();
        valInfoKritis = new javax.swing.JLabel();
        lblInfoPopulasi = new javax.swing.JLabel();
        valInfoPopulasi = new javax.swing.JLabel();
        lblInfoBenua = new javax.swing.JLabel();
        valInfoBenua = new javax.swing.JLabel();
        lblInfoTingkatKesembuhan = new javax.swing.JLabel();
        valInfoTingkatKesembuhan = new javax.swing.JLabel();
        lblInfoTingkatKematian = new javax.swing.JLabel();
        valInfoTingkatKematian = new javax.swing.JLabel();
        lblInfoPeringkatKasus = new javax.swing.JLabel();
        valInfoPeringkatKasus = new javax.swing.JLabel();
        lblTerakhirDiubah = new javax.swing.JLabel();
        valInfoTerakhirDiubah = new javax.swing.JLabel();
        linePemisah = new javax.swing.JSeparator();
        lblShowKeyword = new javax.swing.JLabel();
        searchKeyword = new javax.swing.JTextField();
        lblTextKeyword = new javax.swing.JLabel();
        lblTabelKasus = new javax.swing.JLabel();
        lblTop = new javax.swing.JLabel();
        lblBenderaNegara = new javax.swing.JLabel();
        lblTotalKasus = new javax.swing.JLabel();
        valTotalKasus = new javax.swing.JLabel();
        valTotalSembuh = new javax.swing.JLabel();
        lblTotalSembuh = new javax.swing.JLabel();
        valTotalKematian = new javax.swing.JLabel();
        lblTotalKematian = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();

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
                "Negara", "Positif", "Sembuh", "Kematian"
            }
        ));
        tabelKasus.setGridColor(new java.awt.Color(0, 0, 0));
        tabelKasus.setSelectionBackground(new java.awt.Color(26, 164, 250));
        tabelKasus.setSelectionForeground(new java.awt.Color(250, 246, 246));
        tabelKasus.getTableHeader().setReorderingAllowed(false);
        tabelKasus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKasusMouseClicked(evt);
            }
        });
        tabelKasus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelKasusKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabelKasus);

        pnlBoxInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnlBoxInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblCopyright.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblCopyright.setText("Copyright © 2020. Achmad Baihaqi");
        lblCopyright.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCopyrightMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCopyrightMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCopyrightMouseExited(evt);
            }
        });

        pnlInfo.setBackground(new java.awt.Color(255, 255, 255));
        pnlInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInfo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInfo.setText("Informasi Lengkap");
        lblInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblInfoNegara.setText("Negara");

        lblInfoPositif.setText("Kasus Positif");

        lblInfoSembuh.setText("Sembuh");

        lblInfoKematian.setText("Kematian");

        lblInfoAktif.setText("Kasus Aktif");

        lblInfoKritis.setText("Kasus Kritis");

        valInfoNegara.setText(": Jepang");

        valInfoPositif.setText(": 80.041");

        valInfoSembuh.setText(": 72.538");

        valInfoKematian.setText(": 1.545");

        valInfoAktif.setText(": 5.983");

        valInfoKritis.setText(": 166");

        lblInfoPopulasi.setText("Populasi ");

        valInfoPopulasi.setText(": 126.384.252");

        lblInfoBenua.setText("Benua");

        valInfoBenua.setText(": Asia");

        lblInfoTingkatKesembuhan.setText("Tingkat Kesembuhan");

        valInfoTingkatKesembuhan.setText(": 98.5%");

        lblInfoTingkatKematian.setText("Tingkat Kematian");

        valInfoTingkatKematian.setText(": 2.5%");

        lblInfoPeringkatKasus.setText("Kasus terbanyak ke");

        valInfoPeringkatKasus.setText(": 40");

        lblTerakhirDiubah.setText("Terakhir diubah ");

        valInfoTerakhirDiubah.setText(": 30 Oktober 2020");

        javax.swing.GroupLayout pnlInfoLayout = new javax.swing.GroupLayout(pnlInfo);
        pnlInfo.setLayout(pnlInfoLayout);
        pnlInfoLayout.setHorizontalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblInfoNegara, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoPositif, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(lblInfoSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoAktif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoKritis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valInfoNegara, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(valInfoAktif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoPositif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoKritis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInfoBenua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoTingkatKesembuhan, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(lblInfoPopulasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoTingkatKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblInfoPeringkatKasus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTerakhirDiubah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(valInfoPopulasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoBenua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoTingkatKematian, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoPeringkatKasus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valInfoTerakhirDiubah, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(valInfoTingkatKesembuhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlInfoLayout.setVerticalGroup(
            pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInfoLayout.createSequentialGroup()
                .addComponent(lblInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoNegara)
                    .addComponent(valInfoNegara)
                    .addComponent(lblInfoPopulasi)
                    .addComponent(valInfoPopulasi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoPositif)
                    .addComponent(valInfoPositif)
                    .addComponent(lblInfoBenua)
                    .addComponent(valInfoBenua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoSembuh)
                    .addComponent(valInfoSembuh)
                    .addComponent(lblInfoTingkatKesembuhan)
                    .addComponent(valInfoTingkatKesembuhan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoKematian)
                    .addComponent(valInfoKematian)
                    .addComponent(lblInfoTingkatKematian)
                    .addComponent(valInfoTingkatKematian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoAktif)
                    .addComponent(valInfoAktif)
                    .addComponent(lblInfoPeringkatKasus)
                    .addComponent(valInfoPeringkatKasus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInfoKritis)
                    .addComponent(valInfoKritis)
                    .addComponent(lblTerakhirDiubah)
                    .addComponent(valInfoTerakhirDiubah))
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
                .addContainerGap()
                .addComponent(pnlInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCopyright, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        linePemisah.setForeground(new java.awt.Color(0, 0, 0));
        linePemisah.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblShowKeyword.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblShowKeyword.setText("Menampilkan data dengan keyword = \"\"");

        searchKeyword.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        searchKeyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeywordKeyTyped(evt);
            }
        });

        lblTextKeyword.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lblTextKeyword.setForeground(new java.awt.Color(224, 56, 56));
        lblTextKeyword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTextKeyword.setText("Cari Negara : ");

        lblTabelKasus.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTabelKasus.setForeground(new java.awt.Color(11, 113, 233));
        lblTabelKasus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTabelKasus.setText("Kasus Covid-19 di berbagai Negara di Dunia");

        lblTop.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        lblTop.setForeground(new java.awt.Color(20, 19, 19));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Kasus Covid-19 di Negara ");

        lblBenderaNegara.setFont(new java.awt.Font("Dialog", 1, 19)); // NOI18N
        lblBenderaNegara.setForeground(new java.awt.Color(20, 19, 19));
        lblBenderaNegara.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBenderaNegara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-bendera-sementara.png"))); // NOI18N
        lblBenderaNegara.setText("Jepang");

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
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(valTotalKasus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTotalKasus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                                    .addComponent(valTotalSembuh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTotalSembuh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(valTotalKematian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTotalKematian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(pnlBoxInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblBenderaNegara, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(linePemisah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
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
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(pnlMainLayout.createSequentialGroup()
                                            .addComponent(lblTextKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(searchKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(8, 8, 8))
                                        .addComponent(lblTabelKasus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 16, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblShowKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlBoxInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lblMinimaze, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                            .addComponent(lblKembali, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTabelKasus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(searchKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTextKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblShowKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(0, 23, Short.MAX_VALUE)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblBenderaNegara)
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
                                .addComponent(valTotalKematian, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(linePemisah, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
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

    private void tabelKasusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKasusMouseClicked
        negara = this.tabelKasus.getValueAt(this.tabelKasus.getSelectedRow(), 0).toString();
        showCovidData();
    }//GEN-LAST:event_tabelKasusMouseClicked

    private void tabelKasusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelKasusKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_UP){
            if(tabelKasus.getSelectedRow() >= 0){
                negara = this.tabelKasus.getValueAt(tabelKasus.getSelectedRow()-1, 0).toString();
                showCovidData();
            }
        }else if(evt.getKeyCode() == KeyEvent.VK_DOWN){
            if(tabelKasus.getSelectedRow() <= rowMax){
                negara = this.tabelKasus.getValueAt(tabelKasus.getSelectedRow()+1, 0).toString();
                showCovidData();
            }
        }
    }//GEN-LAST:event_tabelKasusKeyPressed

    private void searchKeywordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeywordKeyTyped
        keyword = searchKeyword.getText();
        rows = kasus.getRows("SELECT * FROM kasuscovid_dunia WHERE negara_idn LIKE '%"+keyword+"%' OR negara_eng LIKE '%"+keyword+"%' OR benua LIKE '%"+keyword+"%'");
        this.lblShowKeyword.setText("Menampilkan "+rows+" data dengan keyword = \""+keyword+"\"");
        
        tabelKasus.setModel(new DefaultTableModel(null, new String[]{"Negara", "Positif", "Sembuh", "Kematian"}));
        dataTabel(fields, keyword);
    }//GEN-LAST:event_searchKeywordKeyTyped

    private void lblCopyrightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCopyrightMouseClicked
        
    }//GEN-LAST:event_lblCopyrightMouseClicked

    private void lblCopyrightMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCopyrightMouseEntered
        
    }//GEN-LAST:event_lblCopyrightMouseEntered

    private void lblCopyrightMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCopyrightMouseExited
        
    }//GEN-LAST:event_lblCopyrightMouseExited

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        kasus.closeConnection();
        System.out.println("Menutup Window KasusCovidDunia");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        kasus.closeConnection();
        System.out.println("-->     APLIKASI DITUTUP");
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(KasusCovidDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KasusCovidDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KasusCovidDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KasusCovidDunia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KasusCovidDunia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBenderaNegara;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblCopyright;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JLabel lblInfoAktif;
    private javax.swing.JLabel lblInfoBenua;
    private javax.swing.JLabel lblInfoKematian;
    private javax.swing.JLabel lblInfoKritis;
    private javax.swing.JLabel lblInfoNegara;
    private javax.swing.JLabel lblInfoPeringkatKasus;
    private javax.swing.JLabel lblInfoPopulasi;
    private javax.swing.JLabel lblInfoPositif;
    private javax.swing.JLabel lblInfoSembuh;
    private javax.swing.JLabel lblInfoTingkatKematian;
    private javax.swing.JLabel lblInfoTingkatKesembuhan;
    private javax.swing.JLabel lblKembali;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblShowKeyword;
    private javax.swing.JLabel lblTabelKasus;
    private javax.swing.JLabel lblTerakhirDiubah;
    private javax.swing.JLabel lblTextKeyword;
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
    private javax.swing.JLabel valInfoBenua;
    private javax.swing.JLabel valInfoKematian;
    private javax.swing.JLabel valInfoKritis;
    private javax.swing.JLabel valInfoNegara;
    private javax.swing.JLabel valInfoPeringkatKasus;
    private javax.swing.JLabel valInfoPopulasi;
    private javax.swing.JLabel valInfoPositif;
    private javax.swing.JLabel valInfoSembuh;
    private javax.swing.JLabel valInfoTerakhirDiubah;
    private javax.swing.JLabel valInfoTingkatKematian;
    private javax.swing.JLabel valInfoTingkatKesembuhan;
    private javax.swing.JLabel valTotalKasus;
    private javax.swing.JLabel valTotalKematian;
    private javax.swing.JLabel valTotalSembuh;
    // End of variables declaration//GEN-END:variables
}