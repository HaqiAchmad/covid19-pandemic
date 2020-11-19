package com.database;

import java.io.File;
import java.time.LocalDateTime;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Account extends Database{
    
    private File file;
    private final LocalDateTime lc = LocalDateTime.now();
    public static final String USERNAME = "username", NAMA_LENGKAP = "namalengkap", NAMA_PANGGILAN = "namapanggilan", EMAIL = "email",
                               GENDER = "gender", TGL_LAHIR = "tgl_lahir", PEKERJAAN = "pekerjaan", ALAMAT = "alamat", NEGARA = "negara",
                               PASSWORD = "password", TGL_DIBUAT = "tgl_dibuat", FOTO_PROFILE = "fotoprofile", TYPE = "type";
    
    public Account(){
        this.startConnection();
    }
    
    public boolean createAccount(){
        return false;
    }
    
    public boolean login(){
        return false;
    }
 
    public boolean logout(){
        try{
            // menghapus semua data yang ada didalam tabel islogin
            int logout = stat.executeUpdate("TRUNCATE islogin");
            // mengecek apakah logout berhasil atau tidak
            if(logout == 0){
                this.backupDatabase();
                return true;
            }
        }catch(SQLException ex){
            System.out.println("ERROR : " + ex.getMessage());
        }
        return false;
    }
    
    public boolean changeFotoProfil(final String user, final File file){
        return false;
    }
    
    public boolean editAccount(final String user, final String data, final String newData){
        try{
            int edit;
            // mengecek apkah user ada atau tidak
            if(isExistUser(user)){
                // mengedit data
                edit = stat.executeUpdate("UPDATE users SET "+ data +" = '"+ newData +"' WHERE username = '"+ user +"' OR email = '"+ user +"'");
                // mengecek apakah data berhasil diedit atau belum
                if(edit > 0){
                    this.backupDatabase();
                    return true;
                }
            }else{
                this.restoreTabel(USERS);
                this.backupDatabase();
            }
        }catch(SQLException ex){
            System.out.println("ERROR : " + ex.getMessage());
            this.restoreTabel(USERS);
        }
        return false;
    }
    
    public String getDataAccount(final String user, final String data){
        try{
            // mengecek apakah akun ada didalam database atau tidak
            if(isExistUser(user)){
                // mendapatkan data
                res = stat.executeQuery("SELECT * FROM users WHERE username = '"+ user +"' OR email = '"+ user +"'");
                if(res.next()){
                    return res.getString(data); // mengembalikan data
                }
            }else{
                this.restoreTabel(USERS);
                this.backupDatabase();
            }
        }catch(SQLException ex){
            System.out.println("ERROR : " + ex.getMessage());
            this.restoreTabel(USERS);
        }
        return null;
    }
    
    public String getActivedUser(){
        try{
            // mendapatkan semua data yang ada didalam tabel islogin
            res = stat.executeQuery("SELECT * FROM islogin");
            if(res.next()){
                return res.getString("username");
            }
        }catch(SQLException ex){
            System.out.println("ERROR : " + ex.getMessage());
            this.restoreTabel(ISLOGIN);
        }
        return null;
    }
    
    public boolean deleteAccount(final String user){
        try{
            int deleteUsers, deleteIslogin;
            // mengecek apakah user ada atau tidak
            if(isExistUser(user)){
                // menghapus akun
                deleteUsers = stat.executeUpdate("DELETE FROM users WHERE username = '"+ user +"' OR email = '"+ user +"'");
                deleteIslogin = stat.executeUpdate("DELETE FROM islogin WHERE username = '"+ user +"' OR email = '"+ user +"'");       
                // mengecek apakah akun berhasil terhapus atau tidak
                if(deleteUsers > 0 || deleteIslogin > 0){
                    this.backupDatabase();
                    return true;
                }
            }
            return false;
        }catch(SQLException ex){
            System.out.println("ERROR : " + ex.getMessage());
            this.restoreTabel(USERS);
            this.restoreTabel(ISLOGIN);
        }
        return false;
    }
    
    public boolean isLogin(){
        try{
            // mengambil data dari islogin
            res = stat.executeQuery("SELECT * FROM islogin");
            return res.next();
        }catch(SQLException ex){
            System.out.println("Error : " + ex.getMessage());
            this.restoreTabel(ISLOGIN);
        }
        return false;
    }
    
    public boolean isExistUser(final String user){
        try{
            // mendapatkan data dari tabel users
            res = stat.executeQuery("SELECT * FROM users WHERE username = '"+ user +"' OR email = '"+ user +"'");
            return res.next(); 
        }catch(SQLException ex){
            System.out.println("Error : " +  ex.getMessage());
            this.restoreTabel(USERS);
        }
        return false;
    }
    
    public boolean isExistNegara(final String negara){
        try{
            // mendapatkan semua data yang ada didalam tabel kasuscovid_dunia
            res = stat.executeQuery("SELECT * FROM kasuscovid_dunia WHERE negara_idn = '"+ negara +"' OR negara_eng = '"+ negara +"'");
            return res.next();
        }catch(SQLException ex){
            System.out.println("Error : " + ex.getMessage());
            this.restoreTabel(KASUSCOVID_INDO);
        }
        return false;
    }
    
    public boolean isValidText(final String text, final String blokChar){
        for(int i = 0; i < text.length(); i++){
            for(int k = 0; k < blokChar.length(); k++){
                if(text.charAt(i) == blokChar.charAt(k)){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isValidUsername(String username){
        // mengecek apakah username kosong atau tidak
        if(username == null){
            return false;
        }
        // karakter yang tidak diperbolehkan untuk membuat username
        String charBlock = "`~!@#$%^*?()-=+\\|[]{};:\"<>/ "; 

        // mengecek apakah username memenuhi persyaratan atau tidak
        if(username.length() >= 4 && username.length() <= 20){
            if(!username.contains(" ")){
                if(isValidText(username, charBlock)){
                    if(!isExistUser(username)){
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "'" + username + "' Username tersebut sudah digunakan!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Simbol yang diizinkan hanyalah : . _ , ' &", "Info", JOptionPane.INFORMATION_MESSAGE);
                }                
            }else{
                JOptionPane.showMessageDialog(null, "Username tidak diizinkan untuk memakai spasi", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Panjang dari username harus diantara 4-20 karakter!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
    }
    
    public boolean isValidNamalengkap(String nama){
        // mengecek apakah nama kosong atau tidak
        if(nama == null){
            return false;
        }
        // karakter yang tidak diperbolehkan untuk membuat nama lengkap
        String blokChar = "0123456789`~!@$%^*()_+=\\|{[]}'\"<>?";
        // mengecek apakah nama memenuhi persyaratan atau tidak
        if(nama.length() >= 5 && nama.length() <= 50){
            if(isValidText(nama, blokChar)){
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Simbol yang didukung untuk nama lengkap hanyalah . # / & - : ;", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nama Lengkap harus diantara 5-50 karakter!!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
    }
    
    public boolean isValidNamapanggilan(String nama){
        // mengecek apakah nama kosong atau tidak
        if(nama == null){
            return false;
        }
        // karakter yang tidak diperbolehkan untuk membuat nama panggilan
        String blokChar = "0123456789`~!@#$%^&*()_-+=\\|{[]}:;'\"<>?/,.";
        // mengecek apakah nama memenuhi persyaratan atau tidak
        if(nama.length() >= 4 && nama.length() <= 15){
            if(isValidText(nama, blokChar)){
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Nama Panggilan tidak boleh menggunakan angka ataupun simbol", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Nama Panggilan harus diantara 4-15 karakter!!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
    }
    
    public boolean isValidEmail(String email){
        // Mengecek apakah email kosong atau tidak
        if(email == null){
            return false;
        }else if(!email.contains("@")){ // mengecek apakah usename mengandung @ atau tidak
            JOptionPane.showMessageDialog(null, "Email tidak valid", "Info", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        String usernameGmail = email.substring(0, email.lastIndexOf("@")), // mendapatkan username email
               blokChar = "`~!@#$%^&*()-=+\\|[]{};:\"<>?/_,'"; // karakter yang tidak diperbolehkan untuk membuat username email.
        
        // Mengecek apakah email memenuhi persyaratan atau tidak
        if(email.substring(email.lastIndexOf("@")).equals("@gmail.com")){
            if(usernameGmail.length() >= 6 && usernameGmail.length() <= 30){
                if(isValidText(usernameGmail, blokChar)){
                    if(!isExistUser(email)){
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "Email tersebut sudah terdaftar!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Simbol yang didukung hanya .", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Panjang username email harus diantara 6-30 karakter", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Saat ini hanya mendukung Gmail saja", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
    }
    
    public boolean isValidPassword(String password){
        // mengecek apakah password kosong atau tidak
        if(password == null){
            return false;
        }
        // mengecek apakah password memenuhi persyaratan atau tidak
        if(password.length() >= 8 && password.length() <= 30){
            return true;
        }else{
            JOptionPane.showMessageDialog(null, "Panjang password harus berkisar antara 8-30 karakter!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
    }
    
    public boolean isValidUmur(String tglLahir){
        try{
            // mengecek apakah tglLahir kosong atau tidak
            if(tglLahir == null){
                return false;
            }else if(!tglLahir.contains("-")){ // mengecek apakah tglLahir mengandung simbol - atau tidak
                return false;
            }
            // mendapatkan umur dari tanggal lahir
            int umur = lc.getYear() - Integer.parseInt(tglLahir.substring(0, tglLahir.indexOf("-")));
            // mengecek apakah umur valid atau tidak
            if(umur >= 5 && umur <= 200){
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Umur anda tidak diperbolehkan untuk mendaftar akun pada aplikasi ini\n Umur anda : " + umur, "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(NumberFormatException ex){
            System.out.println("Error : " + ex.getMessage());
        }
        return false;
    }
    
    public static void main(String[] args) {
        
        String email = "hakiahmad@gmail.com";
        Account acc = new Account();
        System.out.println("valid username : " + acc.isValidUsername("baihaqi02&"));
        System.out.println("valid nama lengkap : " + acc.isValidNamalengkap("Achmad Baihaqi S.kom"));
        System.out.println("valid nama panggilan : " + acc.isValidNamapanggilan("Baihaqi"));
        System.out.println("valid email : " + acc.isValidEmail("baihaqi@gmail.com"));
        System.out.println("valid password : " + acc.isValidPassword("Androidev2020"));
        System.out.println("valid umur : " + acc.isValidUmur("2015-08-04"));
        System.out.println("is exist user 'baihaqi' : " + acc.isExistUser("baihaqi"));
        System.out.println("is exist negara 'japan' : " + acc.isExistNegara("JAPAN"));
        System.out.println("is login : " + acc.isLogin());
        System.out.println("aktif : " + acc.getActivedUser());
        System.out.println("delete account : " + acc.deleteAccount(null));
        System.out.println("data account : " + acc.getDataAccount(acc.getActivedUser(), "namalengkap"));
        System.out.println("edit account : " + acc.editAccount(acc.getActivedUser(), "namalengkap", "yuzaki tsukasa"));
        System.out.println("edit account : " + acc.editAccount(acc.getActivedUser(), "namalengkap", "tsukuyomi tsukasa"));
        System.out.println("edit account : " + acc.editAccount(acc.getActivedUser(), "pekerjaan", "ibu rumah tangga"));
//        System.out.println("logout : " + acc.logout());
        
        System.out.println("\n\n=========================\n\n");
        System.out.println("username : " + acc.getDataAccount(acc.getActivedUser(), Account.USERNAME));
        System.out.println("nama lengkap : " + acc.getDataAccount(acc.getActivedUser(), Account.NAMA_LENGKAP));
        System.out.println("nama panggilan : " + acc.getDataAccount(acc.getActivedUser(), Account.NAMA_PANGGILAN));
        System.out.println("email : " + acc.getDataAccount(acc.getActivedUser(), Account.EMAIL));
        System.out.println("gender : " + acc.getDataAccount(acc.getActivedUser(), Account.GENDER));
        System.out.println("tanggal lahir : " + acc.getDataAccount(acc.getActivedUser(), Account.TGL_LAHIR));
        System.out.println("pekerjaan : " + acc.getDataAccount(acc.getActivedUser(), Account.PEKERJAAN));
        System.out.println("alamat : " + acc.getDataAccount(acc.getActivedUser(), Account.ALAMAT));
        System.out.println("negara : " + acc.getDataAccount(acc.getActivedUser(), Account.NEGARA));
        System.out.println("password : " + acc.getDataAccount(acc.getActivedUser(), Account.PASSWORD));
        System.out.println("tanggal dibuat : " + acc.getDataAccount(acc.getActivedUser(), Account.TGL_DIBUAT));
        System.out.println("foto profile : " + acc.getDataAccount(acc.getActivedUser(), Account.FOTO_PROFILE));
        System.out.println("type : " + acc.getDataAccount(acc.getActivedUser(), Account.TYPE));
        
        
        acc.backupDatabase();
        acc.closeConnection();
        
    }
}
/*
INSERT INTO users VALUES ('tsukasa', 'tsukuyomi tsukasa', 'tsukasa', 'tsukasachan@gmail.com', 'P', '2004-04-03', '', 'tokyo', 'japan', 'loveanime', '2020-11-19', 'default', 'Admin');
*/


/*
Ketentuan username
    - username harus diantara 4-20 karakter
    - username tidak boleh mengandung spasi
    - simbol yang boleh digunakan untuk username antara lain  . _ , ' &
    - username harus belum ada yang memakai
Ketentuan nama lengkap
    - nama lengkap panjangnya harus diantara 5-50 karakter
    - nama lengkap tidak boleh menggandung angka
    - simbol yang dapat digunakan untuk nama lengkap antara lain  . # / & - : ; ,
Ketentuan nama panggilan 
    - nama panggilan panjangnya harus diantara 4-15
    - nama panggilan tidak boleh menggandung angka
    - nama panggilan tidak boleh menggunakan simbol
Ketentuan email
     - jenis email yang didukung hanyalah Gmail saja 
     - panjang username gmail harus diantara 6-30 karakter
     - simbol yang dapat digunakan pada gmail adalah . saja
     - email harus belum ada yang memakai
Ketentuan password
    - panjang password harus diantara 8-30 karakter
Ketentuan umur
    - umur minimum untuk membuat akun adalah umur 5 tahun 
    - umur maximum untuk membuat akun adalah umur 200 tahun
*/