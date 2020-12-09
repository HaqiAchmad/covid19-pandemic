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
 * @since 2020-12-09
 */
public class AddDataDunia extends javax.swing.JFrame {

    /**
     * Digunakan untuk mengambil dan mengedit data dari kasus covid-19 dunia
     */
    private final CovidCases dataDunia = new CovidCases(CovidCases.KASUS_DUNIA);
    /**
     * Digunakan untuk menyimpan hasil filter dari input cari negara
     */
    private String keyword = "";
    /**
     * Digunakan untuk menyimpan data dari kasus covid dunia yang bertipe <code>String</code>
     */
    private String negara_selected, negara_idn, negara_eng, diubah, benua, bendera;
    /**
     * Fields/Data yang akan ditampilkan kedalam tabel
     */
    private final String[] fields = new String[]{CovidCases.NEGARA_IDN, CovidCases.KASUS, CovidCases.SEMBUH, CovidCases.KEMATIAN};
    /**
     * Digunakan untuk menyimpan data dari kasus covid dunia yang bertipe <code>Integer</code>
     */
    private int positif, sembuh, kematian, kritis, aktif, populasi;
    /**
     * Digunakan untuk menyimpan hasil dari edit data yang memiliki tipe int
     */
    private int ePositif, eSembuh, eKematian, eKritis, eAktif;
    /**
     * Digunakan untuk menyimpan hasil dari edit data yang memiliki tipe long
     */
    private long ePopulasi;
    /**
     * Digunakan untuk mengatur posisi dari window
     */
    private int x, y;
    /**
     * Digunakan untuk mengedit data
     */
    private boolean isEdit = false;
    
    public AddDataDunia() {
        initComponents();
        
        this.setIconImage(Gambar.getWindowIcon());
        this.setLocationRelativeTo(null);
        this.lblShowBendera.setText("");
        this.lblShowBendera.setIcon(Gambar.getFlag(dataDunia.getData(CovidCases.BENDERA, "Dunia")));
        
        // mengatur UI dari button yang ada didalam window ke BasicButtonUI
        JButton btns[] = new JButton[]{
           this.btnBatal, this.btnBeranda, this.btnDataUser, this.btnInfo, this.btnSimpan, this.btnDataCovidDunia, this.btnDataCovidIndo
        };
        for(JButton btn : btns){
            btn.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        }
        
        // mengatur MouseEvent Entered & Exited pada Button yang ada di dalam Panel pnlLeft
        JButton btnLeft[] = new JButton[]{
            this.btnBeranda, this.btnInfo, this.btnDataUser, this.btnDataCovidIndo
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
    

    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlLeft = new javax.swing.JPanel();
        lblLeft = new javax.swing.JLabel();
        btnBeranda = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();
        btnDataUser = new javax.swing.JButton();
        btnDataCovidDunia = new javax.swing.JButton();
        btnDataCovidIndo = new javax.swing.JButton();
        lblMinimaze = new javax.swing.JLabel();
        lblClose = new javax.swing.JLabel();
        lblNamaNegara_IDN = new javax.swing.JLabel();
        editNegara_IDN = new javax.swing.JTextField();
        editPositif = new javax.swing.JTextField();
        lblPositif = new javax.swing.JLabel();
        editNegara_ENG = new javax.swing.JTextField();
        lblNamaNegara_ENG = new javax.swing.JLabel();
        lblEditData = new javax.swing.JLabel();
        editAktif = new javax.swing.JTextField();
        lblAktif = new javax.swing.JLabel();
        editKematian = new javax.swing.JTextField();
        lblKematian = new javax.swing.JLabel();
        lblBendera = new javax.swing.JLabel();
        lblSembuh = new javax.swing.JLabel();
        editSembuh = new javax.swing.JTextField();
        editKritis = new javax.swing.JTextField();
        lblKritis = new javax.swing.JLabel();
        editPopulasi = new javax.swing.JTextField();
        lblPopulasi = new javax.swing.JLabel();
        line3 = new javax.swing.JSeparator();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        line4 = new javax.swing.JSeparator();
        lblEditBendera = new javax.swing.JLabel();
        lblKembali = new javax.swing.JLabel();
        lblShowBendera = new javax.swing.JLabel();
        lblBenua = new javax.swing.JLabel();
        editDiubah = new javax.swing.JTextField();
        lblDiubah = new javax.swing.JLabel();
        inpBenua = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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

        btnDataCovidDunia.setBackground(new java.awt.Color(34, 119, 237));
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

        editNegara_IDN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNegara_IDN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNegara_IDN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNegara_IDN.setCaretColor(new java.awt.Color(255, 0, 0));
        editNegara_IDN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editNegara_IDNMouseClicked(evt);
            }
        });

        editPositif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPositif.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

        editNegara_ENG.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNegara_ENG.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNegara_ENG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNegara_ENG.setCaretColor(new java.awt.Color(255, 0, 0));
        editNegara_ENG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editNegara_ENGMouseClicked(evt);
            }
        });

        lblNamaNegara_ENG.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaNegara_ENG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaNegara_ENG.setText("Nama Negara (ENG)");

        lblEditData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblEditData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditData.setText("Tambahkan Data Negara");

        editAktif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAktif.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAktif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAktif.setCaretColor(new java.awt.Color(255, 0, 0));
        editAktif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editAktifMouseClicked(evt);
            }
        });

        lblAktif.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAktif.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAktif.setText("Kasus Aktif");

        editKematian.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editKematian.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

        lblBendera.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblBendera.setText("Bendera Negara");

        lblSembuh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblSembuh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSembuh.setText("Sembuh");

        editSembuh.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editSembuh.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editSembuh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editSembuh.setCaretColor(new java.awt.Color(255, 0, 0));
        editSembuh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editSembuhMouseClicked(evt);
            }
        });

        editKritis.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editKritis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editKritis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editKritis.setCaretColor(new java.awt.Color(255, 0, 0));
        editKritis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editKritisMouseClicked(evt);
            }
        });

        lblKritis.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblKritis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKritis.setText("Kasus Kritis");

        editPopulasi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPopulasi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPopulasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editPopulasi.setCaretColor(new java.awt.Color(255, 0, 0));
        editPopulasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editPopulasiMouseClicked(evt);
            }
        });

        lblPopulasi.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPopulasi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPopulasi.setText("Populasi");

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

        lblEditBendera.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblEditBendera.setText("Tambahkan Bendera");
        lblEditBendera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEditBenderaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblEditBenderaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblEditBenderaMouseExited(evt);
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

        editDiubah.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editDiubah.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editDiubah.setText("09 Desember 2020");
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

        inpBenua.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpBenua.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "                       null", "                       Asia", "                      Afrika", "               Amerika Selatan", "                Amerika Utara", "                     Eropa", "                   Oceania" }));
        inpBenua.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        jLabel1.setText("App Covid-19 Pandemic");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        jLabel2.setText("© 2020. Achmad Baihaqi. All Rights Reserved.");

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
                            .addComponent(lblEditData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(editNegara_IDN)
                                    .addComponent(lblNamaNegara_IDN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editPositif)
                                    .addComponent(lblPositif, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                                        .addComponent(lblShowBendera, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(lblBendera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(lblEditBendera, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(lblSembuh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(editSembuh)
                                    .addComponent(editKritis)
                                    .addComponent(lblKritis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(inpBenua, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(39, 39, 39)
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNamaNegara_ENG, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(editNegara_ENG, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(editDiubah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                        .addComponent(lblDiubah, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editPopulasi, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPopulasi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editAktif, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblAktif, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblKematian, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editKematian, javax.swing.GroupLayout.Alignment.LEADING))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblBenua, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(line3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(22, 22, 22))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(lblKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMinimaze)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(line4)
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(lblEditData, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(line3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblBendera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEditBendera))
                    .addComponent(lblShowBendera, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblNamaNegara_ENG)
                        .addGap(13, 13, 13)
                        .addComponent(editNegara_ENG, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblKematian)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editKematian, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblNamaNegara_IDN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editNegara_IDN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(lblPositif)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPositif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAktif)
                    .addComponent(lblSembuh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editAktif, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editSembuh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPopulasi)
                    .addComponent(lblKritis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editPopulasi, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editKritis, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBenua)
                    .addComponent(lblDiubah))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editDiubah)
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
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel2)
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

    private void editDiubahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editDiubahMouseClicked

    }//GEN-LAST:event_editDiubahMouseClicked

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

    private void lblEditBenderaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditBenderaMouseExited
        this.lblEditBendera.setText("<html><p style=\"text-decoration:none; color:rgb(0,0,0);\">Tambahkan Bendera</p></html>");
        this.lblEditBendera.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_lblEditBenderaMouseExited

    private void lblEditBenderaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditBenderaMouseEntered
        this.lblEditBendera.setText("<html><p style=\"text-decoration:underline; color:rgb(0,0,255);\">Tambahkan Bendera</p></html>");
        this.lblEditBendera.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_lblEditBenderaMouseEntered

    private void lblEditBenderaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEditBenderaMouseClicked
        Audio.play(Audio.SOUND_INFO);
        JOptionPane.showMessageDialog(null, "Fitur 'Edit Bendera' untuk saat ini belum tersedia!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_lblEditBenderaMouseClicked

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed

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

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
        this.btnSimpan.setBackground(new Color(34,119,237));
        this.btnBatal.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
        this.btnSimpan.setBackground(new Color(31,34,38));
        this.btnBatal.setBackground(new Color(34,119,237));
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void editPopulasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPopulasiMouseClicked

    }//GEN-LAST:event_editPopulasiMouseClicked

    private void editKritisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editKritisMouseClicked

    }//GEN-LAST:event_editKritisMouseClicked

    private void editSembuhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editSembuhMouseClicked

    }//GEN-LAST:event_editSembuhMouseClicked

    private void editKematianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editKematianMouseClicked

    }//GEN-LAST:event_editKematianMouseClicked

    private void editAktifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editAktifMouseClicked

    }//GEN-LAST:event_editAktifMouseClicked

    private void editNegara_ENGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editNegara_ENGMouseClicked

    }//GEN-LAST:event_editNegara_ENGMouseClicked

    private void editPositifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPositifMouseClicked

    }//GEN-LAST:event_editPositifMouseClicked

    private void editNegara_IDNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editNegara_IDNMouseClicked

    }//GEN-LAST:event_editNegara_IDNMouseClicked

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

    private void btnDataCovidDuniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataCovidDuniaActionPerformed
        System.out.println("Membuka Window UpdateCovidDunia");
        AddDataDunia updateCovidDunia = new AddDataDunia();
        updateCovidDunia.setLocation(this.getX(), this.getY());

        java.awt.EventQueue.invokeLater(new Runnable(){

            @Override
            public void run(){
                updateCovidDunia.setVisible(true);
            }
        });
        dispose();
    }//GEN-LAST:event_btnDataCovidDuniaActionPerformed

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
            public void run() {
                new AddDataDunia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBeranda;
    private javax.swing.JButton btnDataCovidDunia;
    private javax.swing.JButton btnDataCovidIndo;
    private javax.swing.JButton btnDataUser;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JTextField editAktif;
    private javax.swing.JTextField editDiubah;
    private javax.swing.JTextField editKematian;
    private javax.swing.JTextField editKritis;
    private javax.swing.JTextField editNegara_ENG;
    private javax.swing.JTextField editNegara_IDN;
    private javax.swing.JTextField editPopulasi;
    private javax.swing.JTextField editPositif;
    private javax.swing.JTextField editSembuh;
    private javax.swing.JComboBox inpBenua;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblAktif;
    private javax.swing.JLabel lblBendera;
    private javax.swing.JLabel lblBenua;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDiubah;
    private javax.swing.JLabel lblEditBendera;
    private javax.swing.JLabel lblEditData;
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
    private javax.swing.JSeparator line3;
    private javax.swing.JSeparator line4;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
