package com.window.all;

import com.database.Account;
import com.media.audio.Audio;
import com.media.gambar.Gambar;

import java.awt.Color;
import javax.swing.JOptionPane;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-23
 */
public class SignUp extends javax.swing.JFrame {

    private final Account acc = new Account();
    private String username, namalengkap, namapanggilan, email, password, konpass;
    private int x, y;
    
    public SignUp() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setIconImage(Gambar.getWindowIcon());
        this.btnSignUp.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        lblClose = new javax.swing.JLabel();
        lblMinimaze = new javax.swing.JLabel();
        lblTop = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        inpUsername = new javax.swing.JTextField();
        lblNamaLengkap = new javax.swing.JLabel();
        inpNamaLengkap = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        inpEmail = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblKonfirmasi = new javax.swing.JLabel();
        inpPassword = new javax.swing.JPasswordField();
        inpKonfirmasi = new javax.swing.JPasswordField();
        viewKonn = new javax.swing.JLabel();
        viewPass = new javax.swing.JLabel();
        btnSignUp = new javax.swing.JButton();
        lblSignIn = new javax.swing.JLabel();
        lblNamaPanggilan = new javax.swing.JLabel();
        inpNamaPanggilan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pnlMain.setBackground(new java.awt.Color(21, 20, 20));
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
        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-close-white.png"))); // NOI18N
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
        lblMinimaze.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-minimaze-white.png"))); // NOI18N
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

        lblTop.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblTop.setForeground(new java.awt.Color(44, 119, 238));
        lblTop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTop.setText("Sign Up");

        lblUsername.setForeground(new java.awt.Color(243, 242, 246));
        lblUsername.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblUsername.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-signup-username.png"))); // NOI18N
        lblUsername.setText("Username");

        inpUsername.setBackground(new java.awt.Color(36, 37, 39));
        inpUsername.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpUsername.setForeground(new java.awt.Color(255, 255, 255));
        inpUsername.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 223, 230)));
        inpUsername.setCaretColor(new java.awt.Color(255, 255, 255));

        lblNamaLengkap.setForeground(new java.awt.Color(243, 242, 246));
        lblNamaLengkap.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNamaLengkap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-signup-nama.png"))); // NOI18N
        lblNamaLengkap.setText("Nama Lengkap");

        inpNamaLengkap.setBackground(new java.awt.Color(36, 37, 39));
        inpNamaLengkap.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpNamaLengkap.setForeground(new java.awt.Color(255, 255, 255));
        inpNamaLengkap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 223, 230)));
        inpNamaLengkap.setCaretColor(new java.awt.Color(255, 255, 255));

        lblEmail.setForeground(new java.awt.Color(243, 242, 246));
        lblEmail.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-signup-email.png"))); // NOI18N
        lblEmail.setText("Email");

        inpEmail.setBackground(new java.awt.Color(36, 37, 39));
        inpEmail.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpEmail.setForeground(new java.awt.Color(255, 255, 255));
        inpEmail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 223, 230)));
        inpEmail.setCaretColor(new java.awt.Color(255, 255, 255));

        lblPassword.setForeground(new java.awt.Color(243, 242, 246));
        lblPassword.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-signup-pass.png"))); // NOI18N
        lblPassword.setText("Password");

        lblKonfirmasi.setForeground(new java.awt.Color(243, 242, 246));
        lblKonfirmasi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKonfirmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-signup-konfirmasi.png"))); // NOI18N
        lblKonfirmasi.setText("Konfirmasi");

        inpPassword.setBackground(new java.awt.Color(36, 37, 39));
        inpPassword.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpPassword.setForeground(new java.awt.Color(255, 255, 255));
        inpPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 223, 230)));
        inpPassword.setCaretColor(new java.awt.Color(255, 255, 255));

        inpKonfirmasi.setBackground(new java.awt.Color(36, 37, 39));
        inpKonfirmasi.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpKonfirmasi.setForeground(new java.awt.Color(255, 255, 255));
        inpKonfirmasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 223, 230)));
        inpKonfirmasi.setCaretColor(new java.awt.Color(255, 255, 255));

        viewKonn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewKonn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-eye-close.png"))); // NOI18N
        viewKonn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewKonnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewKonnMouseExited(evt);
            }
        });

        viewPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-eye-close.png"))); // NOI18N
        viewPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewPassMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewPassMouseExited(evt);
            }
        });

        btnSignUp.setBackground(new java.awt.Color(250, 56, 56));
        btnSignUp.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setText("Sign Up");
        btnSignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSignUpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSignUpMouseExited(evt);
            }
        });
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });

        lblSignIn.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        lblSignIn.setForeground(new java.awt.Color(255, 255, 255));
        lblSignIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSignIn.setText("Sudah Punya akun? Login.");
        lblSignIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSignInMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSignInMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSignInMouseExited(evt);
            }
        });

        lblNamaPanggilan.setForeground(new java.awt.Color(243, 242, 246));
        lblNamaPanggilan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNamaPanggilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/media/gambar/icons/ic-signup-namapanggilan.png"))); // NOI18N
        lblNamaPanggilan.setText("Nama Panggilan");

        inpNamaPanggilan.setBackground(new java.awt.Color(36, 37, 39));
        inpNamaPanggilan.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        inpNamaPanggilan.setForeground(new java.awt.Color(255, 255, 255));
        inpNamaPanggilan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(211, 223, 230)));
        inpNamaPanggilan.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblMinimaze)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSignIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSignUp, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlMainLayout.createSequentialGroup()
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNamaPanggilan, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNamaLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblKonfirmasi, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(inpUsername, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpNamaLengkap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inpPassword, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inpKonfirmasi, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inpNamaPanggilan, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(viewKonn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
            .addComponent(lblTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClose, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMinimaze, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(lblTop, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(lblUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNamaLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNamaPanggilan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblKonfirmasi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(viewPass, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(viewKonn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(inpUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inpNamaLengkap, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inpNamaPanggilan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inpKonfirmasi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addComponent(btnSignUp)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(lblSignIn)
                .addGap(32, 32, 32))
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

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        // mendapatkan data dari user lalu data tersebut diubah kedalam bentuk lowercase
        username = this.inpUsername.getText().toLowerCase();
        namalengkap = this.inpNamaLengkap.getText().toLowerCase();
        namapanggilan = this.inpNamaPanggilan.getText().toLowerCase();
        email = this.inpEmail.getText().toLowerCase();
        password = this.inpPassword.getText();
        konpass = this.inpKonfirmasi.getText();
        
        // mengecek apakah pendaftaran akun memenuhi persyaratan atau tidak
        if(acc.createAccount(username, namalengkap, namapanggilan, email, password, konpass, "User")){
            // menampilkan pesan jika akun berhasil dibuat
            Audio.play(Audio.SOUND_INFO);
            JOptionPane.showMessageDialog(
                    null, "Hi, "+ namalengkap +".\nSelamat akun anda telah berhasil dibuat!\nSilahkan login kembali dengan akun yang barusan anda buat!\n\n"
                          + "Username : " + username + "\nNama Lengkap : "+ namalengkap +"\nNama Panggilan : "+ namapanggilan +"\nEmail : " + email + "\nPassword : •••••••••••\n\n--\nCopyright © 2020. Achmad Baihaqi.\n--", 
                    "Informasi", JOptionPane.INFORMATION_MESSAGE                                                                                                                                                                        
            );
            
            // membuka window login saat akun berhasil dibuat
            System.out.println("Membuka Window SignIn");
            dispose();
            java.awt.EventQueue.invokeLater(new Runnable(){
                
                @Override
                public void run(){
                    new SignIn().setVisible(true);
                }
            });
        }
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void btnSignUpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignUpMouseEntered
        this.btnSignUp.setBackground(new Color(0, 0, 0));
    }//GEN-LAST:event_btnSignUpMouseEntered

    private void btnSignUpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSignUpMouseExited
        this.btnSignUp.setBackground(new Color(250,56,56));
    }//GEN-LAST:event_btnSignUpMouseExited

    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCloseMouseClicked

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_ENTERED));
    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        this.lblClose.setIcon(Gambar.getIcon(Gambar.IC_CLOSE_WHITE));
    }//GEN-LAST:event_lblCloseMouseExited

    private void lblMinimazeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseClicked
         this.setExtendedState(javax.swing.JFrame.ICONIFIED);
    }//GEN-LAST:event_lblMinimazeMouseClicked

    private void lblMinimazeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseEntered
        this.lblMinimaze.setIcon(Gambar.getIcon(Gambar.IC_MINIMAZE_ENTERED));
    }//GEN-LAST:event_lblMinimazeMouseEntered

    private void lblMinimazeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimazeMouseExited
        this.lblMinimaze.setIcon(Gambar.getIcon(Gambar.IC_MINIMAZE_WHITE));
    }//GEN-LAST:event_lblMinimazeMouseExited

    private void lblSignInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSignInMouseClicked
        System.out.println("Membuka Window SignIn");
        this.dispose();
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                new SignIn().setVisible(true);
            }
        });
    }//GEN-LAST:event_lblSignInMouseClicked

    private void lblSignInMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSignInMouseEntered
        this.lblSignIn.setText("<html><p style=\"text-decoration: underline; color:rgb(17,150,226);\">Sudah punya akun? Login.</></html>");
    }//GEN-LAST:event_lblSignInMouseEntered

    private void lblSignInMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSignInMouseExited
        this.lblSignIn.setText("<html><p style=\"text-decoration: none; color:rgb(255,255,255);\">Sudah punya akun? Login.</></html>");
    }//GEN-LAST:event_lblSignInMouseExited

    private void viewPassMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewPassMouseEntered
        this.inpPassword.setEchoChar((char)0);
        this.viewPass.setIcon(Gambar.getIcon("ic-eye-open.png"));
    }//GEN-LAST:event_viewPassMouseEntered

    private void viewPassMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewPassMouseExited
        this.inpPassword.setEchoChar('•');
        this.viewPass.setIcon(Gambar.getIcon("ic-eye-close.png"));
    }//GEN-LAST:event_viewPassMouseExited

    private void viewKonnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewKonnMouseEntered
        this.inpKonfirmasi.setEchoChar((char)0);
        this.viewKonn.setIcon(Gambar.getIcon("ic-eye-open.png"));
    }//GEN-LAST:event_viewKonnMouseEntered

    private void viewKonnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewKonnMouseExited
        this.inpKonfirmasi.setEchoChar('•');
        this.viewKonn.setIcon(Gambar.getIcon("ic-eye-close.png"));
    }//GEN-LAST:event_viewKonnMouseExited

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        acc.closeConnection();
        System.out.println("Menutup Window SignIn");
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        acc.closeConnection();
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSignUp;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JPasswordField inpKonfirmasi;
    private javax.swing.JTextField inpNamaLengkap;
    private javax.swing.JTextField inpNamaPanggilan;
    private javax.swing.JPasswordField inpPassword;
    private javax.swing.JTextField inpUsername;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblKonfirmasi;
    private javax.swing.JLabel lblMinimaze;
    private javax.swing.JLabel lblNamaLengkap;
    private javax.swing.JLabel lblNamaPanggilan;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSignIn;
    private javax.swing.JLabel lblTop;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JLabel viewKonn;
    private javax.swing.JLabel viewPass;
    // End of variables declaration//GEN-END:variables
}
