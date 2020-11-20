package com.database;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 * Class ini merupakan inheritance dari class <code>Database</code>.
 * Fungsi dari class ini adalah untuk menangani segala kondisi yang berhubungan dengan akun seperti <I>SignUp, SignIn, Logout, dll</I>. 
 * User diwajibkan untuk memiliki akun terlebih dahulu sebelum masuk ke Aplikasi.
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-14
 * @version 1.0
 */
public class Account extends Database{
    
    /**
     * Digunakan untuk membuat folder yang akan digunakan untuk menyimpan data dari user
     */
    private File file;
    /**
     * Digunakan untuk mendapatkan waktu saat ini seperti tanggal, bulan dan tahun
     */
    private final LocalDateTime lc = LocalDateTime.now();
    /**
     * Field yang ada didalam tabel users.
     * Attribute ini digunakan sebagai keyword untuk medapatkan atau mengedit data dari user
     */
    public static final String USERNAME = "username", NAMA_LENGKAP = "namalengkap", NAMA_PANGGILAN = "namapanggilan", EMAIL = "email",
                               GENDER = "gender", TGL_LAHIR = "tgl_lahir", PEKERJAAN = "pekerjaan", ALAMAT = "alamat", NEGARA = "negara",
                               PASSWORD = "password", TGL_DIBUAT = "tgl_dibuat", FOTO_PROFILE = "fotoprofile", TYPE = "type";
    
    public Account(){
        this.startConnection();
    }
    
    /**
     * Method ini digunakan untuk membuat akun baru
     * 
     * @param username input username
     * @param namaLengkap input nama lengkap
     * @param namaPanggilan input nama panggilan
     * @param email input email
     * @param password input password
     * @param tipe input tipe akun
     * @return Jika akun berhasil dibuat maka akan mengembalikan nilai <B>True</B>. 
     *         Tapi jika akun gagal dibuat maka akan mengembalikan nilai <B>False</B>
     */
    public boolean createAccount(final String username, final String namaLengkap, final String namaPanggilan, final String email, final String password, final String tipe){
        try{
            // digunakan untuk mengecek apakah akun berhail dibuat atau tidak
            int create;
            // mendapatkan tanggal saat ini
            String tanggal = "" + lc.getYear() + "-" + lc.getMonthValue() + "-" + lc.getDayOfMonth();
                        
            // mengecek apakah username valid atau tidak
            if(isValidUsername(username)){
                // mengecek apakah nama lengkap valid atau tidak
                if(isValidNamalengkap(namaLengkap)){
                    // mengecek apakah nama panggilan valid atau tidak
                    if(isValidNamapanggilan(namaPanggilan)){
                        // mengecek apakah email valid atau tidak
                        if(isValidEmail(email)){
                            // mengecek apakah password valid atau tidak
                            if(isValidPassword(password)){
                                // membuat akun
                                create = stat.executeUpdate("INSERT INTO users VALUES ('"+username+"', '"+namaLengkap+"', '"+namaPanggilan+"', '"+email+"', 'N', '0000-01-01', 'null', 'jawa timur', 'indonesia', '"+password+"', '"+tanggal+"', 'default', '"+tipe+"')");
                                // mengecek akun berhasil dibuat atau tidak
                                if(create > 0){
                                    //membuat folder baru yang digunakan untuk menyimpan data user
                                    file = new File("src\\com\\database\\users\\" + email + "\\profile");
                                    file.mkdirs();
                                    // membackup database
                                    this.backupDatabase();
                                    return true;
                                }else{
                                    System.out.println("Gagal membuat akun");
                                }
                            }
                        }
                    }
                }
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat membuat akun!!\n\n Error : " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreTabel(USERS);
        }
        return false;
    }
    
    /**
     * Method ini adalah method yang akan digunakan pengguna aplikasi untuk login.
     * 
     * @param user username / email yang ingin dipakai untuk login
     * @param password password dari akun
     * @return Jika login berhasil maka akan mengembalikan nilai <B>True</B>. 
     *         Tapi jika login gagal maka akan mengembalikan nilai <B>False</B>
     */
    public boolean login(final String user, final String password){
        try{
            // digunakan untuk mengecek apakah user berhasil login atau tidak
            int login;

            // mengecek user sebelumnya sudah login atau belum, jika sudah maka akan dilogout
            if(isLogin()){
                logout(); // melogout
            }
            // mengecek apakah user terdaftar atau tidak
            if(isExistUser(user)){ 
                // mengecek apakah password yang di inputkan cocok atau tidak
                if(getDataAccount(user, Account.PASSWORD).equals(password)){ 
                    login = stat.executeUpdate("INSERT INTO islogin VALUES ('"+getDataAccount(user, Account.USERNAME)+"', '"+getDataAccount(user, Account.NAMA_LENGKAP)+"', '"+getDataAccount(user, Account.EMAIL)+"')");
                    // mengecek apakah login berhasil atau tidak
                    if(login > 0){
                        // mengecek apakah folder penyimpanan data akun ada atau tidak, jika tidak maka akan dibuat
                        file = new File("src\\com\\database\\users\\" + getDataAccount(user, Account.EMAIL) + "\\profile");
                        if(!file.exists()){
                            file.mkdirs();
                        }
                        // membackup database
                        this.backupDatabase();
                        return true;
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Password tidak cocok", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                // memulihkan tabel jika tabel bermasalah
                this.restoreTabel(USERS);
                JOptionPane.showMessageDialog(null, "Username tersebut belum terdaftar!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat login\n\nError: " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreTabel(USERS);
            this.restoreTabel(ISLOGIN);
            this.backupDatabase();
        }
        return false;
    }
 
    public boolean loginAsGuest(){
        try{
            // untuk mengecek apakah akun tamu berhasil dibuat atau tidak
            int create;
            // mengecek apakah akun guest ada atau tidak, jika tidak maka akan dibuat
            if(!isExistUser("guest")){
                // membuat akun guest
               create = stat.executeUpdate("INSERT INTO users VALUES ('guest', 'achmad baihaqi', 'baihaqi', 'abc', 'L', '2003-08-04', 'android developer', 'jawa timur', 'indonesia', '#', '1234-05-06', 'default', 'Guest')");
               //  mengecek apakah akun sukses dibuat atau tidak
               if(create == 0){
                   JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mencoba masuk sebagai tamu!!", "Info", JOptionPane.INFORMATION_MESSAGE);
               }
            }
            // login dengan akun guest
           this.login("guest", "#");
           return true;
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat melakukan login\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    
    public boolean logout(){
        try{
            // menghapus semua data yang ada didalam tabel islogin
            int logout = stat.executeUpdate("TRUNCATE islogin");
            // mengecek apakah logout berhasil atau tidak
            if(logout == 0){
                // membackup database
                this.backupDatabase();
                return true;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat logout\n\nError : " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreTabel(ISLOGIN);
        }
        return false;
    }
    
    public boolean setFotoProfil(final String user, final File file){
        return false;
    }
    
    public File getFotoProfil(final String user){
        return new File("");
    }
    
    public boolean editAccount(final String user, final String data, final String newData){
        try{
            // digunakan untuk mengecek apakah akun berhasil diedit atau belum
            int edit;
            
            // mengecek apakah user ada atau tidak
            if(isExistUser(user)){
                // mengedit data
                edit = stat.executeUpdate("UPDATE users SET "+ data +" = '"+ newData +"' WHERE username = '"+ user +"' OR email = '"+ user +"'");
                // mengecek apakah data berhasil diedit atau tidak, jika berhasil maka database akan dibackup
                if(edit > 0){
                    // membackup database
                    this.backupDatabase();
                    return true;
                }
            }else{
                // memulihkan tabel jika tabel bermasalah
                this.restoreTabel(USERS);
                this.backupDatabase();
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengedit akun\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
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
                // mengecek apakah data berhasil diambil atau tidak
                if(res.next()){
                    return res.getString(data); // mengembalikan data
                }
            }else{
                // memulihkan tabel jika tabel bemasalah
                this.restoreTabel(USERS);
                this.backupDatabase();
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data!\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreTabel(USERS);
        }
        return null;
    }
    
    public String getActivedUser(){
        try{
            // mendapatkan semua data yang ada didalam tabel islogin
            res = stat.executeQuery("SELECT * FROM islogin");
            // mengecek apakah islogin kosong atau tidak
            if(res.next()){
                return res.getString("username"); // mengembalikan data
            }
        }catch(SQLException ex){
            System.out.println("Terjadi kesalahan saat mengambil user yang sedang aktif : " + ex.getMessage());
            this.restoreTabel(ISLOGIN);
        }
        return null;
    }
    
    public boolean deleteAccount(final String user){
        try{
            // untuk mengecek apakah data sudah terhapus atau belum
            int deleteUsers, deleteIslogin;
            // mengecek apakah user ada atau tidak
            if(isExistUser(user)){
                // menghapus akun dari tabel users dan tabel islogin
                deleteUsers = stat.executeUpdate("DELETE FROM users WHERE username = '"+ user +"' OR email = '"+ user +"'");
                deleteIslogin = stat.executeUpdate("DELETE FROM islogin WHERE username = '"+ user +"' OR email = '"+ user +"'");
                
                // mengecek apakah akun berhasil terhapus atau tidak, jika berhasil maka database akan dibackup
                if(deleteUsers > 0 || deleteIslogin > 0){
                    // membackup database
                    this.backupDatabase();
                    return true;
                }
            }
            return false;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus akun!\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
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
            System.out.println("Terjadi kesalahan saat mengecek user sudah login atau belum : " + ex.getMessage());
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
            System.out.println("Terjadi kesalahan saat mengecek user : " +  ex.getMessage());
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
            System.out.println("Terjadi kesalahan saat mengecek negara : " + ex.getMessage());
            this.restoreTabel(KASUSCOVID_DUNIA);
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

        // panjang username harus diantara 4-20 karakter
        if(username.length() >= 4 && username.length() <= 20){
            // username tidak boleh menggunakan kata null atau guest
            if(!username.equalsIgnoreCase("null") || !username.equalsIgnoreCase("guest")){
                // username tidak boleh mengandung spasi
                if(!username.contains(" ")){
                    // username tidak boleh menggunakan karakter yang tidak diperbolehkan
                    if(isValidText(username, charBlock)){
                        // username harus belum digunakan
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
                JOptionPane.showMessageDialog(null, username + " - kata tersebut dilarang digunakan untuk username", "Info", JOptionPane.INFORMATION_MESSAGE);
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
        String blokChar = "`~!@$%^*()_+=\\|{[]}'\"<>?";
        
        // nama lengkap harus diantara 5-50 karakter
        if(nama.length() >= 5 && nama.length() <= 50){
            // nama lengkap tidak boleh menggunakan angka
            if(isValidText(nama, "0123456789")){
                // nama lengkap tidak boleh menggunakan karakter yang tidak diperbolehkan
                if(isValidText(nama, blokChar)){
                    return true;
                }else{
                    JOptionPane.showMessageDialog(null, "Simbol yang bisa digunakan hanyalah  . # / & - : ;", "Info", JOptionPane.INFORMATION_MESSAGE);
                }                
            }else{
                JOptionPane.showMessageDialog(null, "Nama Lengkap tidak boleh mengandung angka!\n ", "Info", JOptionPane.INFORMATION_MESSAGE);
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
        String blokChar = "`~!@#$%^&*()_-+=\\|{[]}:;'\"<>?/,.";
        // nama panggilan harus diantara 4-15 karakter
        if(nama.length() >= 4 && nama.length() <= 15){
            // nama panggilan tidak boleh menggunakan karakter yang tidak diperbolehkan
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
        
        // email yang didukung hanya gmail saja
        if(email.substring(email.lastIndexOf("@")).equals("@gmail.com")){
            // email harus diantara 6-30 karakter saja
            if(usernameGmail.length() >= 6 && usernameGmail.length() <= 30){
                // email tidak boleh menggunakan karakter yang tidak diperbolehkan
                if(isValidText(usernameGmail, blokChar)){
                    // email harus belum ada yang menggunakan 
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
        // password harus diantara 8-30 karakter
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
        
        System.out.println(acc.loginAsGuest());
        
        acc.backupDatabase();
        acc.closeConnection();
        
    }
}
