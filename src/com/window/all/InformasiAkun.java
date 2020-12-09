package com.window.all;

import com.media.gambar.Gambar;
import com.media.audio.Audio;
import com.window.admin.DataAplikasi;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JOptionPane;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-12-08
 */
public class InformasiAkun extends javax.swing.JFrame {

    private boolean isEdit = false;
    
    private int x, y;

    public InformasiAkun() {
        initComponents();
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
        lblEmail = new javax.swing.JLabel();
        editEmail = new javax.swing.JTextField();
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

        lblEmail.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblEmail.setText("Username");

        editEmail.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editEmail.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        editEmail.setText("baihaqi");
        editEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editEmail.setCaretColor(new java.awt.Color(255, 0, 0));
        editEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editEmailMouseClicked(evt);
            }
        });

        editNamalengkap.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNamalengkap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNamalengkap.setText("Achmad Baihaqi");
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
        lblNamalengkap.setText("Nama Lengkap");

        editTipeAkun.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTipeAkun.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTipeAkun.setText("Baihaqi");
        editTipeAkun.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTipeAkun.setCaretColor(new java.awt.Color(255, 0, 0));
        editTipeAkun.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editTipeAkun.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTipeAkunMouseClicked(evt);
            }
        });

        lblTipeAkun.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTipeAkun.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipeAkun.setText("Nama Panggilan");

        lblEditData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblEditData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEditData.setText("Informasi Akun Anda");

        editGender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editGender.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editGender.setText("Jepang");
        editGender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editGender.setCaretColor(new java.awt.Color(255, 0, 0));
        editGender.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editGender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editGenderMouseClicked(evt);
            }
        });

        lblGender.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblGender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGender.setText("Asal Negara");

        editNamapanggilan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editNamapanggilan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editNamapanggilan.setText("04 Agustus 2003");
        editNamapanggilan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editNamapanggilan.setCaretColor(new java.awt.Color(255, 0, 0));
        editNamapanggilan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editNamapanggilan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editNamapanggilanMouseClicked(evt);
            }
        });

        lblNamaPanggilan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblNamaPanggilan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNamaPanggilan.setText("Tanggal Lahir");

        lblPhotoProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhotoProfile.setText("profile");
        lblPhotoProfile.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblProfile.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblProfile.setText("Photo Profile");

        lblTanggalLahir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblTanggalLahir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTanggalLahir.setText("Jenis Kelamin");

        editTglLahir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTglLahir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTglLahir.setText("Laki-Laki");
        editTglLahir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTglLahir.setCaretColor(new java.awt.Color(255, 0, 0));
        editTglLahir.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editTglLahir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editTglLahirMouseClicked(evt);
            }
        });

        editAlamat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAlamat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAlamat.setText("Tokyo");
        editAlamat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAlamat.setCaretColor(new java.awt.Color(255, 0, 0));
        editAlamat.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editAlamat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editAlamatMouseClicked(evt);
            }
        });

        lblAlamat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAlamat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAlamat.setText("Asal Kota");

        editAsalNegara.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editAsalNegara.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editAsalNegara.setText("••••••••••••••••");
        editAsalNegara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editAsalNegara.setCaretColor(new java.awt.Color(255, 0, 0));
        editAsalNegara.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        editAsalNegara.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editAsalNegaraMouseClicked(evt);
            }
        });

        lblAsalNegara.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblAsalNegara.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAsalNegara.setText("Password");

        editPekerjaan.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editPekerjaan.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editPekerjaan.setText("Software Enginer");
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

        editTglDibuat.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        editTglDibuat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        editTglDibuat.setText("30 Oktober 2020");
        editTglDibuat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 106, 255)));
        editTglDibuat.setCaretColor(new java.awt.Color(255, 0, 0));
        editTglDibuat.setDisabledTextColor(new java.awt.Color(0, 0, 0));
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
        editPassword.setText("Admin");
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
        lblPassword.setText("Tipe Akun");

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
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblKembali)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMinimaze, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblEditData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(line3, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(editNamalengkap)
                                        .addComponent(lblNamalengkap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                        .addComponent(lblTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editTglLahir)
                                        .addComponent(editAlamat)
                                        .addComponent(lblAlamat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editPekerjaan)
                                        .addComponent(lblPekerjaan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editTglDibuat)
                                        .addComponent(lblDibuat, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(102, 102, 102)
                                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblTipeAkun, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editTipeAkun, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNamaPanggilan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editNamapanggilan, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(editGender, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblGender, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editAsalNegara, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                        .addComponent(lblAsalNegara, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblPassword, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(editEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlMainLayout.createSequentialGroup()
                                .addGap(325, 325, 325)
                                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBatal)))
                        .addContainerGap(41, Short.MAX_VALUE))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblClose, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblMinimaze, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(lblKembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(lblEditData, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(line3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblProfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEditFoto)
                            .addComponent(lblHapusFoto)))
                    .addComponent(lblPhotoProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblNamalengkap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editNamalengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTanggalLahir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTglLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAlamat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPekerjaan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDibuat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTglDibuat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addComponent(lblTipeAkun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editTipeAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNamaPanggilan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editNamapanggilan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblGender)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editGender, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAsalNegara)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editAsalNegara, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit)
                    .addComponent(btnSimpan)
                    .addComponent(btnBatal))
                .addGap(29, 29, 29))
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
        this.btnEdit.setBackground(new Color(33,123,39));
    }//GEN-LAST:event_btnEditMouseEntered

    private void btnEditMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseExited
        this.btnEdit.setBackground(new Color(41,180,50));
    }//GEN-LAST:event_btnEditMouseExited

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

    }//GEN-LAST:event_btnEditActionPerformed

    private void editEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editEmailMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Email dari akun tidak dapat diedit!", "Pesan", JOptionPane.INFORMATION_MESSAGE);
        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editEmailMouseClicked

    private void editNamalengkapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editNamalengkapMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){

        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editNamalengkapMouseClicked

    private void editTipeAkunMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTipeAkunMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){

        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editTipeAkunMouseClicked

    private void editGenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editGenderMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){

        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editGenderMouseClicked

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

    private void editTglDibuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editTglDibuatMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){

        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editTglDibuatMouseClicked

    private void editPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editPasswordMouseClicked
        // mengecek apakah isEdit bernilai True atau tidak jika isEdit bernilai True maka pengeditan akan diizinkan
        if(isEdit){

        }else{
            Audio.play(Audio.SOUND_WARNING);
            JOptionPane.showMessageDialog(null, "Silahkan klik tombol 'Edit' terlebih dahulu untuk mengedit sebuah data!!", "Peringatan!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_editPasswordMouseClicked

    private void btnSimpanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseEntered
        this.btnSimpan.setBackground(new Color(31,34,38));
        this.btnBatal.setBackground(new Color(34,119,237));
    }//GEN-LAST:event_btnSimpanMouseEntered

    private void btnSimpanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSimpanMouseExited
        this.btnSimpan.setBackground(new Color(34,119,237));
        this.btnBatal.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnSimpanMouseExited

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed

    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseEntered
        this.btnBatal.setBackground(new Color(31,34,38));
        this.btnSimpan.setBackground(new Color(220,41,41));
    }//GEN-LAST:event_btnBatalMouseEntered

    private void btnBatalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBatalMouseExited
        this.btnBatal.setBackground(new Color(220,41,41));
        this.btnSimpan.setBackground(new Color(34,119,237));
    }//GEN-LAST:event_btnBatalMouseExited

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed

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
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnEdit;
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
    private javax.swing.JLabel lblAlamat;
    private javax.swing.JLabel lblAsalNegara;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblDibuat;
    private javax.swing.JLabel lblEditData;
    private javax.swing.JLabel lblEditFoto;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHapusFoto;
    private javax.swing.JLabel lblKembali;
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
    private javax.swing.JSeparator line3;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration//GEN-END:variables
}
