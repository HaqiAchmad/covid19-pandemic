package com.database;

import com.media.audio.Audio;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Class ini digunakan untuk menghubungkan dan memutuskan koneksi ke <strong>Database</strong>, membackup <strong>Database</strong> dan menangani masalah yang terjadi pada <strong>Database.</strong>
 * Kondisi seperti <strong>CRUD</strong> tidak tersedia di class ini. Kondisi <strong>CRUD</strong> tersedia di class <code>Account</code> dan <code>CovidCases</code>.
 * <BR><BR>
 * <strong>Database</strong> pada aplikasi ini sebagian besar menggunakan <strong>MySQL Database</strong> dan sisanya menggunakan <strong>Database Text</strong>.
 * <UL>
 * <LI> <B>MySQL Database</B> digunakan untuk menyimpan data yang berhubungan dengan data Pengguna dan kasus Covid-19 di Indonesia maupun Dunia.
 * <LI> <B>Database Text</B> digunakan untuk menyimpan backup dari data Pengguna dan kasus Covid-19 di Indonesia maupun Dunia dari Database MySQL.
 * </UL>
 * 
 * Aplikasi ini sangat bergantung pada <strong>Database</strong> jika <strong>Database</strong> tidak ditemukan maka <strong>Database</strong> akan dibuat secara otomatis melalui method <code>restoreDatabase()</code>.
 * 
 * 
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-14
 * @version 1.0
 */
public class Database {
    
    /**
     * Object ini digunakan untuk membangun koneksi dengan <B>Database</B>
     */
    public Connection conn;
    /**
     * Digunakan untuk mengeksekusi query MySQL
     */
    public Statement stat;
    /**
     * Digunakan untuk mengambil output dari eksekusi query
     */
    public ResultSet res;
    /**
     * Folder yang akan digunakan untuk menyimpan file backup <b>Database</b>.
    */
    private final File folderBackup = new File("C:\\ProgramData\\Punya Haqi\\Covid-19 Pandemic 1.0\\database\\backup\\");
    /**
     * Attribute yang digunakan untuk menhubungkan Aplikasi ke <B>Database MySQL</B>
     */
    private static final String DRIVER = "com.mysql.jdbc.Driver",
                                DB_NAME = "app_covid19pandemic",
                                URL = "jdbc:mysql://localhost/" + DB_NAME,
                                USER = "root",
                                PASS = "";
    
    /**
     * Nama tabel yang ada didalam <B>Database MySQL</B>
     */
    public static final String KASUSCOVID_DUNIA = "kasuscovid_dunia", KASUSCOVID_INDO = "kasuscovid_indo", 
                               USERS = "users", ISLOGIN = "islogin";
    
    /**
     * Digunakan untuk menghubungkan aplikasi ke <B>Database</B>. 
     * <BR>
     * Pertama-tama method akan meregrister Driver yang dipakai.
     * Driver yang dipakai di aplikasi ini adalah <B>MySQL Driver</B>. Selanjutnya method akan melakukan koneksi ke <B>Database</B>.
     * Setelah berhasil terkoneksi ke <B>Database</B> method akan membuat object <code>Statement</code>
     * <BR><BR>
     * Terdapat dua exception yang mungkin akan terjadi di method ini yaitu <code>ClassNotFoundException</code> dan <code>SQLException</code>.
     * Exception akan ditangani dengan mendapatkan pesan error dari method <code>getMessage</code> pada kedua exception tersebut.
     * Beberapa pesan error yang dapat ditanggani di method ini antara lain: 
     * <UL>
     * <LI> <B>com.mysql.jdbc.Driver : </B> pesan error ini berarti aplikasi tidak dapat menemukan Driver yang akan dipakai untuk menkoneksikan aplikasi ke <B>Database</B>. 
     *      Aplikasi akan secara otomatis keluar sendiri jika mendapatkan pesan error ini. 
     * <LI> <B>Communications link failure : </B> pesan error ini bearti MySQL pada Komputer user belum diaktifkan. 
     *      Aplikasi akan secara otomatis keluar sendiri jika mendapatkan pesan error ini. 
     * <LI> <B>Access denied for user 'root'@'localhost' (using password: YES) : </B> pesan error ini bearti username atau password pada MySQL di komputer user tidak cocok dengan username dan password yang ada di Aplikasi ini.
     *      Aplikasi akan secara otomatis keluar sendiri jika mendapatkan pesan error ini. 
     * <LI> <B>Unknown database" : </B> pesan error ini bearti bahwa database MySQL aplikasi ini tidak ada di komputer user. 
     *      Method akan memulihkan database secara otomatis dengan method <code>restoreDatabase()</code> jika mendapatkan pesan error ini.
     * </UL>
     * <B>Note : </B> Jika pesan error tidak dikenali maka aplikasi akan keluar sendiri.
     */
    public void startConnection(){
        try{
            // Menghubungkan ke database
            Class.forName(DRIVER); 
            conn = DriverManager.getConnection(URL, USER, PASS); 
            stat = conn.createStatement(); 
            System.out.println("Berhasil terhubung ke database '" + DB_NAME + "'\n");
        }catch(ClassNotFoundException | SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            // Menanggani exception yang terjadi dengan cara mendapatkan pesan error dari exception tersebut.
            if(ex.getMessage().contains("com.mysql.jdbc.Driver")){
                JOptionPane.showMessageDialog(null, "MySQL Connector tidak dapat ditemukan", "Error", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }else if(ex.getMessage().contains("Communications link failure")){
                JOptionPane.showMessageDialog(null, "Sepertinya MySQL anda belum diaktifkan!!\nSilahkan aktifkan MySQL anda dan buka kembali aplikasi!!", "Error", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }else if(ex.getMessage().contains("Access denied for user")){
                JOptionPane.showMessageDialog(null, "Maaf untuk membuka aplikasi ini \nUsername dan password dari MySQL anda harus diatur ke default!\nMohon maaf atas ketidaknyamanan ini!", "Warning", JOptionPane.WARNING_MESSAGE);
            }else if(ex.getMessage().contains("Unknown database")){
                if(folderBackup.isDirectory()){
                    JOptionPane.showMessageDialog(null, "Klik 'OK' untuk memulihkan Database!", "Error", JOptionPane.WARNING_MESSAGE);
                }
                restoreDatabase();
            }else{
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    /**
     * Digunakan untuk menutup koneksi dari <B>Database</B> MySQL. 
     * Koneksi dari <B>Database</B> perlu ditutup jika sudah tidak digunakan lagi.
     * Sebelum menutup koneksi dari <B>Database</B> method akan mengecek apakah object 
     * <code>Connection</code>, <code>Statement</code> dan <code>ResultSet</code> kosong atau tidak.
     * Jika tidak maka koneksi dari <B>Database</B> akan ditutup. Jika tidak dicek kosong atau tidaknya objek maka saat objek kosong
     * lalu dipaksa untuk menutup koneksi dari Database maka akan menimbulkan exception <code>NullPointerException</code>.
     * Setalah koneksi dari <B>Database</B> berhasil diputus maka <b>Database</b> akan dibackup.
     */
    public void closeConnection(){
        try{
            backupDatabase();
            // Mengecek apakah conn kosong atau tidak, jika tidak maka akan diclose
            if(conn != null){
                conn.close();
            }
            // Mengecek apakah stat kosong atau tidak, jika tidak maka akan diclose
            if(stat != null){
                stat.close();
            }
            // Mengecek apakah res koson atau tidak, jika tidak maka akan diclose
            if(res != null){
                res.close();
            }
            System.out.println("Connection Closed : " + conn.isClosed());
            System.out.println("Statement  Closed : " + stat.isClosed());
            System.out.println("ResultSet  Closed : " + res.isClosed());
            System.out.println("Berhasil memutus koneksi database '" + DB_NAME + "'\n");
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Method ini digunakan untuk membackup data dari tabel yang ada di dalam <B>Database</B>. 
     * Data hasil backup tersebut akan disimpan dalam bentuk sebuah file yang terletak di package <code>com.databaes.backup</code> dan memiliki format .haqi.
     * Nama dari file backup selalu diawali dengan kata BACKUP dan dibelakang kata BACKUP tersebut adalah nama tabel yang akan dibackup.
     * <BR>
     * Contoh : BACKUP kasuscovid_dunia.haqi, BACKUP users.haqi
     * <BR> <BR>
     * Sebelum membackup data method akan mengecek terlebih dahulu apakah <B>Database</B> dan tabelnya ada atau tidak. 
     * Jika <B>Database</B> dan tabelnya tidak ditemukan maka <B>Database</B> dan tabelnya akan dipulihkan terlebih dahulu dengan method <code>restoreDatabase()</code> untuk menghindari error saat membackup nanti.
     * Method juga akan mengecek apakah tabel yang ada didalam <B>Database</B> kosong tanpa data atau tidak. Jika kosong maka akan dipulihkan terlebih dahulu dengan method <code>restoreDatabase()</code>.
     * Jika <B>Database</B> dan tabelnya ditemukan dan tabel tersebut tidak kosong maka data akan dibakcup.
     * <BR> <BR>
     * Proses backup akan dilakukan dengan membaca cara semua data yang ada didalam tabel. Lalu data tersebut disimpan ke dalam sebuah <code>String</code>.
     * Jika semua data yang ada didalam tabel sudah terbaca dan datanya disimpan kedalam bentuk <code>String</code>.
     * Maka proses selanjutnya ada memasukan data tersebut ke dalam file backup.
     * 
     */
    public void backupDatabase(){
        try{
            FileWriter file;
            BufferedWriter backup;
            String values;
            
            System.out.println("\n--> Membackup Database!!");
            // Mengecek apakah database dan tabel exist atau tidak!, jika database dan tabel tidak ditemukan maka database akan direstore
            if(!isExistDatabase() || !isExistTabel(KASUSCOVID_DUNIA) || !isExistTabel(KASUSCOVID_INDO) || !isExistTabel(USERS) || !isExistTabel(ISLOGIN)){
                JOptionPane.showMessageDialog(null, "Database Corrupt!!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Database corrupt!");
                restoreDatabase(); // merestore database
            }
            // Mengecek apakah tabel yang ada didalam databaes kosong atau tidak
            else if(isEmptyTabel(KASUSCOVID_DUNIA) || isEmptyTabel(KASUSCOVID_INDO) || isEmptyTabel(USERS)){
                JOptionPane.showMessageDialog(null, "Database Corrupt!!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Database corrupt!");
                restoreDatabase(); // merestore database
            }
            // Mengecek apakah folder yang digunakan untuk menyimpan hasil backup Database ada atau tidak, jika tidak ada maka akan dibuat
            else if(!folderBackup.isDirectory()){
                folderBackup.mkdirs();
            }
            
            // Membackup tabel kasuscovid_dunia
            System.out.println("Membackup data kasus Covid-19 Dunia...");
            file = new FileWriter(this.folderBackup.getPath() + "\\BACKUP kasuscovid_dunia.haqi"); // file backup tabel kasuscovid_dunia
            backup = new BufferedWriter(file);
            res = stat.executeQuery("SELECT * FROM kasuscovid_dunia");
           
            backup.write("INSERT INTO kasuscovid_dunia VALUES \n"); 
                // membaca semua data yang ada di dalam tabel, lalu menuliskan datanya ke file "BACKUP kasuscovid_dunia.haqi"
                while(res.next()){
                    // mendapatkan data yang ada didalam tabel
                    values = "('" + res.getString("negara_idn") + "', '" + res.getString("negara_eng") + "', " + res.getString("kasus") + ", " + res.getString("kematian") + ", " + res.getString("sembuh") + ", " + res.getString("aktif") + ", " + res.getString("kritis") + ", " + res.getString("populasi") + ", '" + res.getString("diubah") + "', '" + res.getString("benua") + "', '" + res.getString("bendera") + "')";
                    // menuliskan data yang ada didalam tabel ke file backup
                    if(!res.isLast()){
                        backup.write(values + ",");
                    }else{
                        backup.write(values + ";");
                    }
                    // membuat baris baru di file backup
                    backup.newLine();
                }
            backup.write("# Copyright © 2020. Achmad Baihaqi. All Rights Reserved.");
            backup.flush();
            System.out.println("Data kasus Covid-19 Dunia berhasil dibackup...");
            
            // Membackup tabel kasuscovid_indo
            System.out.println("Membackup data kasus Covid-19 Indonesia...");
            file = new FileWriter(this.folderBackup.getPath() + "\\BACKUP kasuscovid_indo.haqi"); // file backup tabel kasuscovid_indo
            backup = new BufferedWriter(file);
            res = stat.executeQuery("SELECT * FROM kasuscovid_indo");
            
            backup.write("INSERT INTO kasuscovid_indo VALUES \n");
                // membaca semua data yang ada didalam tabel, lalu menuliskan datanya ke file "BACKUP kasuscovid_indo.haqi"
                while(res.next()){
                    // mendapatkan data yang ada didalam tabel
                    values = "('" + res.getString("kode") + "', '" + res.getString("provinsi") + "', " + res.getString("kasus") + ", " + 
                            res.getString("sembuh") + ", " + res.getString("kematian") + ", " + res.getString("aktif") + ", " + res.getString("populasi") + ", " +  res.getString("total_kab") + ", " + res.getString("kab_zonamerah") + ", " + res.getString("kab_zonaoranye") + ", " + res.getString("kab_zonahijau") + ", '" + res.getString("kasus_pertama") + "', '" +  res.getString("diubah") + "', '" + res.getString("website") + "', '" + res.getString("lambang") + "')";
                    // menuliskan data yang ada didalam tabel ke file backup
                    if(!res.isLast()){
                        backup.write(values + ",");
                    }else{
                        backup.write(values + ";");
                    }
                    // membuat baris baru di file backup
                    backup.newLine();
                }
            backup.write("# Copyright © 2020. Achmad Baihaqi. All Rights Reserved.");
            backup.flush();
            System.out.println("Data kasus Covid-19 Indonesia berhasil dibackup...");
            
            // Membackup tabel users
            System.out.println("Membackup data dari pengguna Aplikasi...");
            file = new FileWriter(this.folderBackup.getPath() + "\\BACKUP users.haqi"); // file backup tabel users
            backup = new BufferedWriter(file);
            res = stat.executeQuery("SELECT * FROM users");
            
            backup.write("INSERT INTO users VALUES \n");
                // membaca semua data yang ada didalam tabel, lalu menuliskan datanya ke file "BACKUP users.haqi"
                while(res.next()){
                    // mendapatkan data yang ada didalam tabel
                    values = "('" + res.getString("username") + "', '" + res.getString("namalengkap") + "', '" + res.getString("namapanggilan") + "', '" + res.getString("email") + "', '" + res.getString("gender") + "', '" + res.getString("tgl_lahir") + "', '" + res.getString("pekerjaan") + "', '" + res.getString("alamat") + "', '" + res.getString("negara") + "', '" + res.getString("password") + "', '" + res.getString("tgl_dibuat") + "', '" + res.getString("fotoprofile") + "', '" + res.getString("type") + "')";
                    // menuliskan data yang ada didalam tabel ke file backup
                    if(!res.isLast()){
                        backup.write(values + ",");
                    }else{
                        backup.write(values + ";");
                    }
                    // membuat baris baru di file backup
                    backup.newLine();
                }
                backup.write("# Copyright © 2020. Achmad Baihaqi. All Rights Reserved.");
                backup.flush();
                System.out.println("Data dari pengguna aplikasi berhasil dibackup...");
                
            // Membackup tabel islogin
            file = new FileWriter(this.folderBackup.getPath() + "\\BACKUP islogin.haqi"); // file backup tabel islogin
            backup = new BufferedWriter(file);
            res = stat.executeQuery("SELECT * FROM islogin");
            
            backup.write("INSERT INTO islogin VALUES \n");
            System.out.println("Membackup data user login...");
                // membaca semua data yang ada didalam tabel, lalu menuliskan datanya ke file "BACKUP islogin.haqi"
                while(res.next()){
                    // mendapatkan data yang ada didalam tabel
                    values = "('" + res.getString("username") + "', '" + res.getString("namalengkap") + "', '" + res.getString("email") + "')";
                    // menuliskan data yang ada didalam tabel ke file backup
                    if(!res.isLast()){
                        backup.write(values + ",");
                    }else{
                        backup.write(values + ";");
                    }
                    // membuat baris baru di file backup
                    backup.newLine();
                }
                backup.write("# Copyright © 2020. Achmad Baihaqi. All Rights Reserved.");
                backup.flush();   
                System.out.println("Data user login berhasil dibackup...");
                
                System.out.println("--> Database berhasil dibackup!!\n");

        }catch(SQLException | IOException ex){
            if(ex.getMessage().contains("Acess is Denied")){
                System.out.println("Acess Denied : " + ex.getMessage());
            }else{
                Audio.play(Audio.SOUND_ERROR);
                JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat membackup database!!\n\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    /**
     * Digunakan untuk mengambil data backup dari tabel. 
     * Method akan mengambil semua data yang ada dialam file backup. 
     * Lalu datanya akan disimpan kedalam bentuk <code>String</code>.
     * Setelah semua data yang ada didalam file backup diambil.
     * Maka method akan mengembalikan data backup tersebut dalam bentuk <code>String</code>.
     * 
     * @param tabel tabel yang ingin diambil data backupnya 
     * @return Data backup dari tabel dalam bentuk <code>String</code>.
     *         Jika method gagal mengambil data dari file backup maka akan megembalikan nilai null.
     */
    public String getBakcupTabel(final String tabel){
        try{
            String filename = "\\BACKUP " + tabel + ".haqi", data = "", buffer;
            FileReader file = new FileReader(folderBackup.getPath() + filename); // file backup tabel
            BufferedReader getBck = new BufferedReader(file);
            System.out.println("\nMendapatkan data yang dibackup dari tabel " + tabel);
            
            // Mengambil semua data yang ada didalam file backup
            while((buffer = getBck.readLine()) != null){
                data += buffer + "\n";
            }
            System.out.println("Data backup dari tabel " + tabel + " berhasil diambil\n");
            // Mengembalikan data
            return data;    
        }catch(IOException ex){
            System.out.println("Gagal mengambil backup data : " + ex.getMessage());
        }
        return null; // akan mengembalikan nilai null jika gagal mengambil data dari file
    }
    
    /**
     * Method ini digunakan untuk memulihkan tabel yang tidak ditemukan dan tabel yang kosong tanpa data. 
     * Method akan mengecek apakah tabel tersebut ada atau tidak melalui method <code>isExistTabel(final String tabel)</code>.
     * <BR><BR>
     * Jika output dari method tersebut adalah <B>True</B> maka tersebut tabel ada/ditemukan dan selanjutnya akan dicek apakah tabel tersebut kosong tanpa data atau tidak jika kosong maka akan dipulihkan.
     * Tapi jika output dari method tersebut adalah <B>False</B> maka tabel tersebut tidak ditemukan dan tabel akan dibuat  
     * dengan menggunakan object <code>Statement</code> dan query untuk membuat tabel didapat dari method <code>DefaultDatabase.getStrukturTabel(final String tabel)</code> 
     * setelah berhasil membuat tabel maka data akan dipulihkan.
     * <BR><BR>
     * Setelah mengecek apakah tabel ada atau tidak didalam <B>Database</B> maka selanjutnya adalah 
     * memulihkan data tabel yang kosong method akan mengecek apakah tabel tersebut kosong atau tidak dengan menggunakan method <code>isEmptyTabel(final String tabel)</code>. 
     * Jika output dari method tersebut adalah <B>True</B> maka tabel tersebut kosong dan akan dilakukan pemulihan data tabel. 
     * Jika output dari method tersebut adalah <B>False</B> maka proses pemulihan data akan dilewati.
     * <BR><BR>
     * Sebelum melakukan pemulihan data method akan mengecek apakah file backup pada tabel ada atau tidak. <BR>
     * Jika ada maka method akan memanggil <code>getBakcupTabel(final String tabel)</code> yang digunakan 
     * untuk mendapatkan query <code>INSERT</code> untuk mengisi tabel yang kosong. <BR>
     * Jika file pada tabel tidak ditemukan maka method akan memanggil <code>DefaultDatabase.getDefaultData(final String tabel)</code> 
     * untuk mendapatkan query <code>INSERT</code> untuk mengisi tabel yang kosong dengan data saat pertama kali aplikasi diinstall.
     * 
     * 
     * @param tabel nama tabel yang akan dipulihkan 
     */
    public void restoreTabel(final String tabel){
        try{
            File f; // untuk mengecek apakah file backup exist ata tidak
            int create; // untuk mengecek apakah proses berhasil atau tidak
            String fileBackup = this.folderBackup.getPath() + "\\BACKUP " + tabel + ".haqi"; // file backup 
            
            // mengecek apakah folder yang menyimpan file backup ada atau tidak, jika tidak ada maka folder akan dibuat
            if(!this.folderBackup.isDirectory()){
                System.out.println("\nMemulihkan folder backup...\n");
                this.folderBackup.mkdirs();
            }
            
            System.out.println("\nMemulihkan tabel " + tabel);
            System.out.println("Mengecek tabel ada atau tidak!");
            // Mengecek apakah tabel ada didalam database atau tidak, jika tidak maka tabel akan dibuat.
            if(!isExistTabel(tabel)){
                // Membuat Tabel 
                System.out.println("Tabel " + tabel + " tidak ditemukan!");
                create = stat.executeUpdate(DefaultDatabase.getStrukturTabel(tabel));
                // Mengecek apakah tabel  berhasil dibuat atau tidak, jika berhasil maka data tabel dan primary key akan dipulihkan
                if(create == 0){
                    System.out.println("Tabel '" + tabel + "' berhasil dibuat!"); 
                    // Memmulihkan primary key
                    restorePrimaryKey(tabel);
                    // Memulihkan data dari tabel 
                     f = new File(fileBackup);
                     // Mengecek apakah file backup tabel ada atau tidak
                     if(f.exists()){ 
                         // proses memulihkan data tabel jika file backup ada 
                         create = stat.executeUpdate(this.getBakcupTabel(tabel));
                         // Mengecek apakah data berhasil dipulihkan atau tidak
                         if(create > 0){
                             System.out.println("Tabel '" + tabel + "' berhasil dipulihkan!\n");
                         }else{
                             System.out.println("Tabel '" + tabel + "' gagal dipulihkan!\n");
                         }
                     }else{
                         // proses memulihkan data tabel jika file backup tidak ada 
                         create = stat.executeUpdate(DefaultDatabase.getDefaultDataTabel(tabel));
                         // Mengecek apakah data berhasil dipulihkan atau tidak
                         if(create > 0){
                             System.out.println("Tabel '" + tabel + "' berhasil dipulihkan!\n");
                         }else{
                             System.out.println("Tabel '" + tabel + "' gagal dipulihkan!\n");
                         }
                     }
                }
            }else{ // jika tabel ada didalam database
                System.out.println("\nTabel '" + tabel + "' ditemukan!"); 
                // Memulihhkan primary key jika primary key tidak ada
                restorePrimaryKey(tabel);
                // Mengecek apakah kosong atau tidak, jika kosong maka data akan dipulihkan
                if(isEmptyTabel(tabel)){
                    System.out.println("Tabel '" + tabel + "' kosong!");
                    // Memulihkan data
                    f = new File(fileBackup);
                    // Mengecek apakah file backup tabel ada atau tidak
                    if(f.exists()){
                        // Proses memulihkan data jika file ada
                        create = stat.executeUpdate(this.getBakcupTabel(tabel));
                        // Mengecek apakah data berhasil dipulihkan atau tidak
                        if(create > 0){
                            System.out.println("Tabel '" + tabel + "' berhasil dipulihkan!\n");
                        }else{
                            System.out.println("Tabel '" + tabel + "' gagal dipulihkan!\n");
                        }
                    }else{
                        // Proses memulihkan data jika file backup tidak ada
                        create = stat.executeUpdate(DefaultDatabase.getDefaultDataTabel(tabel));
                        // Mengecek apakah data berhasil dipulihkan atau tidak
                        if(create > 0){
                            System.out.println("Tabel '" + tabel + "' berhasil dipulihkan!\n");
                        }else{
                            System.out.println("Tabel '" + tabel + "' gagal dipulihkan!\n");
                        }
                    }
                }
            }
            System.out.println("Tabel " + tabel +" berhasil dipulihkan\n");
        }catch(SQLException ex){
            System.out.println("Terjadi kesalahan!!\n ERROR : " + ex.getMessage());
            try{
                // Menangani exception yang terjadi
                stat.executeUpdate("DROP TABLE " + tabel); // menghapus tabel sebelumnya 
                stat.executeUpdate(DefaultDatabase.getStrukturTabel(tabel)); // membuat tabel 
                stat.executeUpdate(DefaultDatabase.getDefaultDataTabel(tabel)); // memulihkan data
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * Digunakan untuk memulihkan field yang bersifat Primary Key pada tabel saat tabel baru dipulihkan
     * Primary key sangat penting bagi aplikasi ini karena digunkan untuk menghindari duplikasi data pada <b>Database</b>.
     * Method akan mengambil query untuk memulihkan primary key dari method <code>getPrimary</code> dari class <code>DefaultDatabase</code>.
     * 
     * @param tabel
     */
    public void restorePrimaryKey(final String tabel){
        try{
            // digunakan untuk menampung query yg akan digunakan untuk memulihkan primary key
            String sql = "";
            // mendapatkan query dari method getPrimary dari class DefaultDatabase
            switch(tabel){
                case KASUSCOVID_DUNIA: sql = DefaultDatabase.getPrimaryKey(tabel); break;
                case KASUSCOVID_INDO: sql = DefaultDatabase.getPrimaryKey(tabel); break;
                case USERS: sql = DefaultDatabase.getPrimaryKey(tabel); break;
                case ISLOGIN: sql = DefaultDatabase.getPrimaryKey(tabel); break;
                default : JOptionPane.showMessageDialog(null, "Input tidak didukung"); break;
            }
            // mengeksekusi query
            int cek = stat.executeUpdate(sql);
            // mengecek apakah primary key berhasil ditambahka atau tidak
            if(cek == 0){
                System.out.println("Primary Key berhasil ditambahkan di tabel " + tabel);
            }else{
                JOptionPane.showMessageDialog(null, "Gagal menambahkan Primary Key!");
            }
        }catch(SQLException ex){
            System.out.println("Terjadi kesalahan saat memulihkan Primary key pada tabel " + tabel + " : " + ex.getMessage());
        }
    }
    
    /**
     * Digunakan untuk memulihkan <B>Database</B> dan tabel jika tidak ditemukan atau tabel dalam kondisi kosong tanpa data.
     * Pemulihan <B>Database</B> sangat penting untuk dilakukan karena Aplikasi ini sangat begantung pada <B>Database</B>.
     * Jika <B>Database</B> dan tabel tidak ditemukan atau ada tabel yang kosong tanpa data maka menyebabkan error pada Aplikasi.
     * <BR><BR>
     * Untuk mengecek apakah <B>Database</B> ada atau tidak maka diperlukan method <code>isExistDatabase()</code>. 
     * Jika output dari method tersebut adalah <strong>True</strong> maka <B>Database</B> tersebut ada/ditemukan. 
     * Tapi jika output dari method tersebut adalah <strong>False</strong> maka <B>Database</B> tersebut tidak ditemukan 
     * dan <B>Database</B> akan dibuat kembali. Jika <B>Database</B> berhasil dibuat maka langkah selanjutnya adalah 
     * memulihkan tabel yang ada didalam <B>Database</B> dengan method <code>restoreTabel(final String tabel)</code>. 
     * Jika <b>Database,</b> dan tabel yang ada didalam <b>Database</b> berhasil dipulihkan maka data yang ada didalam <b>Database</b>
     * akan dibackup menggunakan method <code>backupDatabase</code>
     * 
     */
    public void restoreDatabase(){
        try{
            System.out.println("\nMemulikan Database");
            System.out.println("Mengecek database ada atau tidak!");
            // Mengecek apakah database ada atau tidak, jika tidak maka akan database dibuat
            if(!isExistDatabase()){
                System.out.println("Database tidak ditemukan!");
                // Membuat database
                Class.forName(DRIVER);
                conn = DriverManager.getConnection("jdbc:mysql://localhost/", USER, PASS);
                stat = conn.createStatement();
                int create = stat.executeUpdate("CREATE DATABASE " + DB_NAME);
                // mengecek apakah database berhasil dibuat atau tidak
                if(create > 0){
                    System.out.println("Database '" + DB_NAME + "' berhasil dibuat!\n");
                    // menghubungkan ulang ke database
                    this.startConnection();
                }
            }else{
                System.out.println("Database ditemukan");
            }
            
            // Merestore tabel jika tabel tidak ditemukan atau kosong
            restoreTabel(KASUSCOVID_DUNIA);
            restoreTabel(KASUSCOVID_INDO);
            restoreTabel(USERS);
            restoreTabel(ISLOGIN);
            
            // Membackup data yang ada didalam Database
            this.backupDatabase();
            
            System.out.println("Database berhasil dipulihkan!");
            
        }catch(ClassNotFoundException | SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Digunakan untuk mengecek apakah sebuah field ada didalam tabel atau tidak. 
     * Method akan mengecek ada atau tidak field didalam tabel dengan cara melakukan perulangan terhadap array yang 
     * berisi field dari tabel. Jika field tersebut ditemukan didalam array tersebut maka field tersebut dinyatakan ada.
     * 
     * @param tabel tabel dari field yang ingin dicek ada atau tidaknya field tersebut
     * @param field field yang ingin dicek ada atau tidaknya
     * @return Jika field ditemukan maka akan mengembalikan nilai <B>True</B>.
     *         Tapi jika field tidak ditemukan maka akan mengembalikan niali <B>False</B>
     */
    public boolean isExistField(final String tabel, final String field){
        // daftar field yang ada dialam tabel kasuscovid_dunia, kasuscovid_indo, users dan islogin
        String fieldDunia[] = new String[]{"negara_idn", "negara_eng", "kasus", "kematian", "sembuh", "aktif", "kritis", "populasi", "diubah", "benua", "bendera"},
               fieldIndo[] = new String[]{"kode", "provinsi", "kasus", "sembuh", "kematian", "aktif", "populasi", "total_kab", "kab_zonamerah", "kab_zonaoranye", "kab_zonahijau", "kasus_pertama", "diubah", "website", "lambang"},
               fieldUsers[] = new String[]{"username", "namalengkap", "namapanggilan", "email", "gender", "tgl_lahir", "pekerjaan", "alamat", "negara", "password", "tgl_dibuat", "fotoprofile", "type"},
               fieldIslogin[] = new String[]{"username", "namalengkap", "email"};
        
        // mengecek apakah field ada atau tidak didalam tabel kasuscovid_dunia
        if(tabel.equalsIgnoreCase(KASUSCOVID_DUNIA)){
            for (String cari : fieldDunia) {
                if (field.equalsIgnoreCase(cari)) {
                    return true;
                }
            }
        // mengecek apakah field ada atau tidak didalam tabel kasuscovid_dunia
        }else if(tabel.equalsIgnoreCase(KASUSCOVID_INDO)){
            for (String cari : fieldIndo) {
                if (field.equalsIgnoreCase(cari)) {
                    return true;
                }
            }
        // mengecek apakah field ada atau tidak didalam tabel kasuscovid_dunia    
        }else if(tabel.equalsIgnoreCase(USERS)){
            for(String cari : fieldUsers){
                if(field.equalsIgnoreCase(cari)){
                    return true;
                }
            }
        // mengecek apakah field ada atau tidak didalam tabel kasuscovid_dunia    
        }else if(tabel.equalsIgnoreCase(ISLOGIN)){
            for(String cari : fieldIslogin){
                if(field.equalsIgnoreCase(cari)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * Untuk mengecek apakah sebuah tabel ada didalam <B>Database</B> atau tidak. 
     * Method akan mengecek ada atau tidaknya tabel di dalam <B>Database</B> dengan cara
     * mengambil semua data yang ada didalam tabel. 
     * Jika terjadi exception maka tabel dinyatakan tidak ada. Jika tidak terjadi exception maka tabel dinyatakan ada. 
     * 
     * @param tabel tabel yang akan dicek ada atau tidaknya tabel tersebut di dalam <B>Database.</B>.
     * @return Jika tabel ada didalam <B>Database</B> maka akan mengembalikan nilai <B>True</B>. 
     *         Jika tabel tidak ada didalam <B>Database</B> maka akan mengembalikan nilai <B>False</B>.
     */
    public boolean isExistTabel(final String tabel){
        try{
            String sql = "SELECT * FROM " + tabel;
            stat.executeQuery(sql);
            return true;
        }catch(SQLException ex){
            System.out.println("Tabel '" + tabel + "' tidak dapat ditemukan!");
        }
        return false;
    }
    
    /**
     * Method akan mengecek kosong atau tidaknya tabel dengan cara mengambil semua data yang ada didalam tabel 
     * menggunakan object <code>Statement</code> lalu outputnya akan disimpan pada object <code>ResultSet</code>. 
     * Pada <code>ResultSet</code> ada method yang bernama <code>next()</code>. 
     * Jika output dari method <code>next()</code> adalah <B>True</B> maka tabel tersebut tidak kosong.
     * Jika output dari method <code>next()</code>  adalah <B>False</B> maka tabel tersebut kosong.
     * 
     * @param tabel tabel yang akan dicek kosong atau tidaknya tabel tersebut.
     * @return Jika tabel kosong maka akan mengembalikan nilai <B>True</B>.
     *         Jika tabel tidak kosong maka akan mengembalikan nilai <B>False</B>.
     */
    public boolean isEmptyTabel(final String tabel){
        try{
            String sql = "SELECT * FROM " + tabel;
            res = stat.executeQuery(sql);
            return !res.next(); // jika tabel tidak kosong
        }catch(SQLException ex){
            System.out.println("Tabel '" + tabel + "' tidak dapat ditemukan!");
        }
        return true; // jika tabel kosong
    }
    
    /**
     * Method akan mengecek ada atau tidaknya <B>Database</B> dengan cara mencoba menkoneksikan Aplikasi ke <B>Database</B>.
     * Jika tidak terjadi exception maka <B>Database</B> dinyatakan ada. 
     * Jika terjadi exception maka <B>Database</B> dinyatakan tidak ada. 
     * 
     * @return Jika <B>Database</B> ditemukan maka akan mengembalikan nilai <B>True</B>
     *         Jika <B>Database</B> tidak ditemukan maka akan mengembalikan nilai <B>False</B>
     */
    public boolean isExistDatabase(){
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stat = conn.createStatement();
            return true;
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Database '" + DB_NAME + "' tidak dapat ditemukan!");
        }
        return false;
    }
    
}

/**
 * Class ini digunakan untuk mengatasi masalah jika file backup hilang 
 * dan ada tabel di <B>Database</B> yang tidak ditemukan atau dalam kondisi kosong tanpa data. 
 * Permasalah ini jelas akan menghasilkan error karena aplikasi tidak dapat mendapatkan data dari tabel yang ada didalam <B>Database</B>.
 * <BR><BR>
 * Semua method pada class ini outputnya akan menghasilkan query-query yang digunakan untuk 
 * membuat tabel yang tidak ditemukan di <B>Database</B> dan berisi perintah <code>INSERT</code> yang digunakan untuk mengembalikan data pada tabel yang kosong.
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-15
 * @version 1.0
 */
class DefaultDatabase{
    
    /**
     * Method ini akan megembalikan query untuk membuat tabel yang tidak ada didalam <B>Database</B>.
     * Adapun input yang bisa dimasukan untuk mendapatkan query tabel antara lain: 
     * <UL>
     * <LI> <B>KASUSCOVID_DUNIA : </B> akan menghasilkan query untuk membuat tabel kasuscovid_dunia jika tabel tersebut tidak ditemukan. 
     * <LI> <B>KASUSCOVID_INDO : </B> akan menghasilkan query untuk membuat tabel kasuscovid_indo jika tabel tersebut tidak ditemukan. 
     * <LI> <B>USERS : </B> akan menghasilkan query untuk membuat tabel users jika tabel tersebut tidak ditemukan. 
     * <LI> <B>ISLOGIN : </B> akan menghasilkan query untuk membuat tabel islogin jika tabel tersebut tidak ditemukan. 
     * </UL>
     * <B>Note : </B> Jika input yang dimasukan bukan pilihan diatas maka method akan mengembalikan nilai null.
     * 
     * @param tabel tabel yang ingin didapatkan query-nya
     * @return akan mengembalikan query untuk membuat tabel
     */
    protected static String getStrukturTabel(final String tabel){
        // Mengecek input dari user
        if(tabel.equalsIgnoreCase(Database.KASUSCOVID_DUNIA)){
            return getStrukturTabel_kasusCoviDunia();
        }else if(tabel.equalsIgnoreCase(Database.KASUSCOVID_INDO)){
            return getStrukturTabel_kasusCovidIndo();
        }else if(tabel.equalsIgnoreCase(Database.USERS)){
            return getStrukturTabel_users();
        }else if(tabel.equalsIgnoreCase(Database.ISLOGIN)){
            return getStrukturTabel_isLogin();
        }else{
            return null;
        }
    }
    
    /**
     * Akan mengembalikan sebuah query untuk membuat tabel kasuscovid_dunia jika tabel tersebut tidak ada didalam <B>Database</B>.
     * 
     * @return query untuk membuat tabel kasuscovid_dunia
     */
    protected static String getStrukturTabel_kasusCoviDunia(){
        return  "CREATE TABLE `kasuscovid_dunia` (\n" +
                "  `negara_idn` varchar(35) NOT NULL,\n" +
                "  `negara_eng` varchar(35) NOT NULL,\n" +
                "  `kasus` int(11) NOT NULL,\n" +
                "  `kematian` int(11) NOT NULL,\n" +
                "  `sembuh` int(11) NOT NULL,\n" +
                "  `aktif` int(11) NOT NULL,\n" +
                "  `kritis` int(11) NOT NULL,\n" +
                "  `populasi` int(11) NOT NULL,\n" +
                "  `diubah` date NOT NULL,\n" +
                "  `benua` enum('Asia','Afrika','Amerika Selatan','Amerika Utara','Eropa','Oceania','null') NOT NULL,\n" +
                "  `bendera` text NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
    }
    
     /**
     * Akan mengembalikan sebuah query untuk membuat tabel kasuscovid_indo jika tabel tersebut tidak ada didalam <B>Database</B>.
     * 
     * @return query untuk membuat tabel kasuscovid_indo
     */
    protected static String getStrukturTabel_kasusCovidIndo(){
        return  "CREATE TABLE `kasuscovid_indo` (\n" +
                "  `kode` varchar(10) NOT NULL,\n" +
                "  `provinsi` varchar(40) NOT NULL,\n" +
                "  `kasus` int(11) NOT NULL,\n" +
                "  `sembuh` int(11) NOT NULL,\n" +
                "  `kematian` int(11) NOT NULL,\n" +
                "  `aktif` int(11) NOT NULL,\n" +
                "  `populasi` int(11) NOT NULL,\n" +
                "  `total_kab` int(5) NOT NULL,\n" +
                "  `kab_zonamerah` int(5) NOT NULL,\n" +
                "  `kab_zonaoranye` int(5) NOT NULL,\n" +
                "  `kab_zonahijau` int(5) NOT NULL,\n" +
                "  `kasus_pertama` date NOT NULL,\n" +
                "  `diubah` date NOT NULL,\n" +
                "  `website` varchar(50) NOT NULL,\n" +
                "  `lambang` text NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
    }
    
     /**
     * Akan mengembalikan sebuah query untuk membuat tabel users jika tabel tersebut tidak ada didalam <B>Database</B>.
     * 
     * @return query untuk membuat tabel users
     */
    protected static String getStrukturTabel_users(){
        return  "CREATE TABLE `users` (\n" +
                "  `username` varchar(30) NOT NULL,\n" +
                "  `namalengkap` varchar(50) NOT NULL,\n" +
                "  `namapanggilan` varchar(20) NOT NULL,\n" +
                "  `email` varchar(40) NOT NULL,\n" +
                "  `gender` enum('L','P','N') NOT NULL,\n" +
                "  `tgl_lahir` date NOT NULL,\n" +
                "  `pekerjaan` varchar(30) NOT NULL,\n" +
                "  `alamat` varchar(35) NOT NULL,\n" +
                "  `negara` varchar(35) NOT NULL,\n" +
                "  `password` varchar(30) NOT NULL,\n" +
                "  `tgl_dibuat` date NOT NULL,\n" +
                "  `fotoprofile` text NOT NULL,\n" +
                "  `type` enum('Admin','User', 'Guest') NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
    }
    
     /**
     * Akan mengembalikan sebuah query untuk membuat tabel islogin jika tabel tersebut tidak ada didalam <B>Database</B>.
     * 
     * @return query untuk membuat tabel islogin
     */
    protected static String getStrukturTabel_isLogin(){
        return  "CREATE TABLE `islogin` (\n" +
                "  `username` varchar(30) NOT NULL,\n" +
                "  `namalengkap` varchar(50) NOT NULL,\n" +
                "  `email` varchar(40) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
    }
    
    /**
     * Method ini akan digunakan untuk memulihkan sebuah field yang primary key nya hilang pada tabel yg ada di <b>Database</b>.
     * Adapun input yang bisa dimasukan untuk mendapatkan query antara lain : 
     * <UL>
     * <LI> <B>KASUSCOVID_DUNIA : </B> akan menghasilkan query untuk memulihkan field yang bersifat primary key pada tabel kasuscovid_dunia
     * <LI> <B>KASUSCOVID_INDO : </B> akan menghasilkan query untuk memulihkan field yang bersifat primary key pada tabel kasuscovid_indo
     * <LI> <B>USERS : </B> akan menghasilkan query untuk memulihkan field yang bersifat primary key pada tabel users
     * <LI> <B>ISLOGIN : </B> akan menhasikaln empty query karena pada tabel islogin tidak mengandung primary key.
     * </UL>
     * <B>Note : </B> Jika input yang dimasukan bukan pilihan diatas maka method akan mengembalikan nilai null.
     * 
     * @param tabel tabel yang ingin didapatkan query-nya
     * @return akan mengembalikan query untuk memulihkan primary key
     */
    protected static String getPrimaryKey(final String tabel){
        // Mengecek input dari user
        if(tabel.equalsIgnoreCase(Database.KASUSCOVID_DUNIA)){
            return getPrimary_kasusCovidDunia();
        }else if(tabel.equalsIgnoreCase(Database.KASUSCOVID_INDO)){
            return getPrimary_kasusCovidIndo();
        }else if(tabel.equalsIgnoreCase(Database.USERS)){
            return getPrimary_users();
        }else if(tabel.equalsIgnoreCase(Database.ISLOGIN)){
            return "";
        }else{
            return null;
        }
    }
    
     /**
     * Akan mengembalikan sebuah query untuk memulihkan field yang bersifat Primary key pada tabel kasuscovid_dunia
     * 
     * @return query untuk memulihkan primary key pada tabel kasuscovid_dunia
     */
    protected static String getPrimary_kasusCovidDunia(){
        return  "ALTER TABLE `kasuscovid_dunia`\n" +
                "  ADD PRIMARY KEY (`negara_idn`,`negara_eng`);";
    }
    
     /**
     * Akan mengembalikan sebuah query untuk memulihkan field yang bersifat Primary key pada tabel kasuscovid_indo
     * 
     * @return query untuk memulihkan primary key pada tabel kasuscovid_indo
     */
    protected static String getPrimary_kasusCovidIndo(){
        return  "ALTER TABLE `kasuscovid_indo`\n" +
                "  ADD PRIMARY KEY (`kode`,`provinsi`,`website`);";
    }
    
     /**
     * Akan mengembalikan sebuah query untuk memulihkan field yang bersifat Primary key pada tabel users
     * 
     * @return query untuk memulihkan primary key pada tabel users
     */
    protected static String getPrimary_users(){
        return  "ALTER TABLE `users`\n" +
                "  ADD PRIMARY KEY (`username`,`email`);";
    }
    
    /**
     * Method ini akan megembalikan query untuk mengisi tabel yang kosong dengan perintah <code>INSERT</code>.
     * Adapun input yang bisa dimasukan untuk mendapatkan query antara lain : 
     * <UL>
     * <LI> <B>KASUSCOVID_DUNIA : </B> akan menghasilkan query untuk mengisi tabel kasuscovid_dunia jika tabel tersebut kosong tanpa data.
     * <LI> <B>KASUSCOVID_INDO : </B> akan menghasilkan query untuk mengisi tabel kasuscovid_indo jika tabel tersebut kosong tanpa data. 
     * <LI> <B>USERS : </B> akan menghasilkan query untuk mengisi tabel users jika tabel tersebut kosong tanpa data.
     * <LI> <B>ISLOGIN : </B> akan menghasilkan query untuk mengisi tabel islogin jika tabel tersebut kosong tanpa data.
     * </UL>
     * <B>Note : </B> Jika input yang dimasukan bukan pilihan diatas maka method akan mengembalikan nilai null.
     * 
     * @param tabel tabel yang ingin didapatkan query-nya
     * @return akan mengembalikan query untuk mengisi tabel yang kosong.
     */
    protected static String getDefaultDataTabel(final String tabel){
        // Mengecek input dari user
        if(tabel.equalsIgnoreCase(Database.KASUSCOVID_DUNIA)){
            return getDefaultDataTabel_kasusCovidDunia();
        }else if(tabel.equalsIgnoreCase(Database.KASUSCOVID_INDO)){
            return getDefaultDataTabel_kasusCovidIndo();
        }else if(tabel.equalsIgnoreCase(Database.USERS)){
            return getDefaultDataTabel_users();
        }else if(tabel.equalsIgnoreCase(Database.ISLOGIN)){
            return getDefaultDataTabel_isLogin();
        }else{
            return null;
        }
    }
    
     /**
     * Akan mengembalikan sebuah query untuk mengisi tabel kasuscovid_dunia jika tabel tersebut kosong.
     * 
     * @return query untuk mengisi tabel kasuscovid_dunia
     */
    protected static String getDefaultDataTabel_kasusCovidDunia(){
        return  "INSERT INTO `kasuscovid_dunia` (`negara_idn`, `negara_eng`, `kasus`, `kematian`, `sembuh`, `aktif`, `kritis`, `populasi`, `diubah`, `benua`, `bendera`) VALUES\n" +
                "('Afganistan', 'Afghanistan', 50536, 2054, 38799, 9683, 93, 39319204, '2020-12-20', 'Asia', 'bendera-afganistan.jpg'),\n" +
                "('Afrika Selatan', 'South Africa', 912477, 24539, 787782, 100156, 546, 59645103, '2020-12-20', 'Afrika', 'bendera-afrika-selatan.png'),\n" +
                "('Albania', 'Albania', 52542, 1074, 27831, 23637, 39, 2876367, '2020-12-20', 'Eropa', 'bendera-albania.jpg'),\n" +
                "('Aljazair', 'Algeria', 94781, 2659, 62869, 29253, 42, 44205533, '2020-12-20', 'Afrika', 'bendera-aljazair.png'),\n" +
                "('Amerika Serikat', 'USA', 18077768, 323401, 10545445, 7208922, 27963, 331849210, '2020-12-20', 'Amerika Utara', 'bendera-amerika-serikat.jpg'),\n" +
                "('Andorra', 'Andorra', 7519, 80, 6919, 520, 17, 77321, '2020-12-19', 'Eropa', 'bendera-andorra.png'),\n" +
                "('Angola', 'Angola', 16562, 384, 9345, 6833, 10, 33318335, '2020-12-19', 'Afrika', 'bendera-angola.gif'),\n" +
                "('Anguilla', 'Anguilla', 10, 0, 10, 6, 0, 15063, '2020-12-18', 'Amerika Utara', 'bendera-anguilla.png'),\n" +
                "('Antigua dan Barbuda', 'Antigua and Barbuda', 152, 5, 141, 5, 2, 98295, '2020-12-20', 'Amerika Utara', 'bendera-antigua-dan-barbuda.png'),\n" +
                "('Arab Saudi', 'Saudi Arabia', 360848, 6112, 351722, 3014, 425, 35057143, '2020-12-20', 'Asia', 'bendera-arab-saudi.jpg'),\n" +
                "('Argentina', 'Argentina', 1537169, 41763, 1362617, 132789, 132789, 45382916, '2020-12-20', 'Amerika Selatan', 'bendera-argentina.jpg'),\n" +
                "('Armenia', 'Armenia', 153173, 2616, 131931, 18626, 0, 2965757, '2020-12-20', 'Asia', 'bendera-armenia.png'),\n" +
                "('Aruba', 'Aruba', 5138, 47, 4928, 163, 4, 106971, '2020-12-19', 'Amerika Utara', 'bendera-aruba.png'),\n" +
                "('Australia', 'Australia', 28168, 908, 25701, 1559, 0, 25633109, '2020-12-20', 'Oceania', 'bendera-australia.jpg'),\n" +
                "('Austria', 'Austria', 337209, 5209, 300611, 31389, 489, 9029655, '2020-12-20', 'Eropa', 'bendera-austria.jpg'),\n" +
                "('Azerbaijan', 'Azerbaijan', 199127, 2175, 135462, 61490, 0, 10180421, '2020-12-20', 'Asia', 'bendera-azerbaijan.png'),\n" +
                "('Bahama', 'Bahamas', 7733, 164, 6109, 1460, -1, 394939, '2020-12-19', 'Amerika Utara', 'bendera-bahama.png'),\n" +
                "('Bahrain', 'Bahrain', 90062, 349, 88178, 1535, 9, 1727634, '2020-12-20', 'Asia', 'bendera-bahrain.png'),\n" +
                "('Bangladesh', 'Bangladesh', 499560, 7242, 435601, 56717, -1, 165070897, '2020-12-20', 'Asia', 'bendera-bangladesh.png'),\n" +
                "('Barbados', 'Barbados', 305, 7, 280, 20, 1, 287535, '2020-12-20', 'Amerika Utara', 'bendera-barbados.png'),\n" +
                "('Belanda', 'Netherlands', 676673, 10459, -1, -1, 560, 17152080, '2020-12-20', 'Eropa', 'bendera-belanda.jpg'),\n" +
                "('Belarus', 'Belarus', 171579, 1316, 149353, 20910, 0, 9447909, '2020-12-20', 'Eropa', 'bendera-belarus.png'),\n" +
                "('Belgia', 'Belgium', 623760, 18545, 42911, 562304, 539, 11612476, '2020-12-20', 'Eropa', 'bendera-belgia.jpg'),\n" +
                "('Belize', 'Belize', 9929, 215, 8214, 1500, 5, 400859, '2020-12-19', 'Amerika Utara', 'bendera-belize.png'),\n" +
                "('Benin', 'Benin', 3152, 44, 2972, 136, 0, 12264275, '2020-12-18', 'Afrika', 'bendera-benin.png'),\n" +
                "('Bermuda', 'Bermuda', 516, 9, 267, 240, 3, 62173, '2020-12-20', 'Amerika Utara', 'bendera-bermuda.svg.png'),\n" +
                "('Bhutan', 'Bhutan', 443, -1, 422, 21, 0, 775436, '2020-12-20', 'Asia', 'bendera-bhutan.png'),\n" +
                "('Bolivia', 'Bolivia', 149770, 9035, 127815, 12920, 71, 11744591, '2020-12-20', 'Amerika Selatan', 'bendera-bolivia.png'),\n" +
                "('Bosnia dan Herzegovina', 'Bosnia and Herzegovina', 105524, 3625, 70620, 31279, 0, 3271495, '2020-12-20', 'Eropa', 'bendera-bosnia-and-herzegovina.jpg'),\n" +
                "('Bostwana', 'Botswana', 13014, 38, 10535, 2441, 1, 2372842, '2020-12-18', 'Afrika', 'bendera-botswana.png'),\n" +
                "('Brasil', 'Brazil', 7213155, 186356, 6222764, 804035, 8318, 213221820, '2020-12-20', 'Amerika Selatan', 'bendera-brasil.jpg'),\n" +
                "('Britania Raya', 'UK', 2004219, 67075, -1, -1, 1340, 68047462, '2020-12-20', 'Eropa', 'bendera-inggris.jpg'),\n" +
                "('Brunei Darussalam', 'Brunei', 152, 3, 148, 1, 0, 439369, '2020-12-18', 'Asia', 'bendera-brunei-darussalam.png'),\n" +
                "('Bulgaria', 'Bulgaria', 191029, 6551, 98026, 86452, 542, 6924524, '2020-12-20', 'Eropa', 'bendera-bulgaria.png'),\n" +
                "('Burkina Faso', 'Burkina Faso', 4832, 74, 3403, 1355, 0, 21157626, '2020-12-19', 'Afrika', 'bendera-burkino-faso.png'),\n" +
                "('Burundi', 'Burundi', 761, 2, 687, 72, 0, 12047578, '2020-12-20', 'Afrika', 'bendera-burundi.jpg'),\n" +
                "('Cayman Kepulauan', 'Cayman Islands', 311, 2, 282, 27, 1, 66069, '2020-12-20', 'Amerika Utara', 'bendera-cayman_islands.svg.png'),\n" +
                "('Chad', 'Chad', 1818, 102, 1626, 90, 0, 16634766, '2020-12-18', 'Afrika', 'bendera-chad.png'),\n" +
                "('Chili', 'Chile', 583354, 16101, 554333, 12920, 672, 19190284, '2020-12-20', 'Amerika Selatan', 'bendera-chili.jpg'),\n" +
                "('Cina', 'China', 86829, 4634, 81886, 309, 5, 1439323776, '2020-12-20', 'Asia', 'bendera-republik-rakyat-china.jpg'),\n" +
                "('Curaçao', 'Curaçao', 3881, 11, 2210, 1660, 5, 164397, '2020-12-19', 'Amerika Utara', 'bendera-curacao.svg.png'),\n" +
                "('Denmark', 'Denmark', 131606, 1019, 89570, 41017, 86, 5801450, '2020-12-20', 'Eropa', 'bendera-denmark.jpg'),\n" +
                "('Djibouti', 'Djibouti', 5780, 61, 5641, 78, 0, 994453, '2020-12-19', 'Afrika', 'bendera-djibouti.png'),\n" +
                "('Dominika', 'Dominica', 88, 0, 83, 5, 0, 72067, '2020-12-14', 'Amerika Utara', 'bendera-dominika.png'),\n" +
                "('Dunia', 'World', 76626060, 1691942, 53751059, 21183059, 106379, 783331293, '2020-12-20', 'null', 'bendera-perserikatan-bangsa-bangsa.png'),\n" +
                "('Ekuador', 'Ecuador', 205920, 13948, 177951, 14021, 359, 17763305, '2020-12-20', 'Amerika Selatan', 'bendera-ekuador.jpg'),\n" +
                "('El Salvador', 'El Salvador', 43195, 1242, 39016, 2937, 86, 6501025, '2020-12-20', 'Amerika Utara', 'bendera-el-salvador.png'),\n" +
                "('Eritrea', 'Eritrea', 754, -1, 580, 174, 0, 3568479, '2020-12-20', 'Afrika', 'bendera-eritrea.jpg'),\n" +
                "('Estonia', 'Estonia', 21219, 170, 13488, 7561, 33, 1326940, '2020-12-19', 'Eropa', 'bendera-estonia.png'),\n" +
                "('Ethiopia', 'Ethiopia', 119494, 1846, 102153, 15495, 258, 116230450, '2020-12-20', 'Afrika', 'bendera-etiopia.png'),\n" +
                "('Fiji', 'Fiji', 46, 2, 42, 2, 0, 899381, '2020-12-18', 'Oceania', 'bendera-fiji.png'),\n" +
                "('Filipina', 'Philippines', 458044, 8911, 421086, 28047, 708, 110236832, '2020-12-20', 'Asia', 'bendera-filipina1.jpg'),\n" +
                "('Finlandia', 'Finland', 32853, 489, 22500, 9864, 30, 5544629, '2020-12-20', 'Eropa', 'bendera-finlandia.png'),\n" +
                "('Gabon', 'Gabon', 9400, 64, 9254, 82, 3, 2249124, '2020-12-19', 'Afrika', 'bendera-gabon.png'),\n" +
                "('Gambia', 'Gambia', 3786, 123, 3654, 9, 1, 2446776, '2020-12-18', 'Afrika', 'bendera-gambia.png'),\n" +
                "('Georgia', 'Georgia', 206907, 2055, 175842, 29010, -1, 3985680, '2020-12-20', 'Asia', 'bendera-georgia.png'),\n" +
                "('Ghana', 'Ghana', 53653, 331, 52331, 991, 15, 31362555, '2020-12-20', 'Afrika', 'bendera-ghana.jpg'),\n" +
                "('Gibraltar', 'Gibraltar', 1211, 6, 1058, 147, 1, 33686, '2020-12-20', 'Eropa', 'bendera-gibraltar.svg.png'),\n" +
                "('Greenland', 'Greenland', 19, 0, 18, 1, 0, 56815, '2020-12-14', 'Amerika Utara', 'bendera-greenland.svg.png'),\n" +
                "('Grenada', 'Grenada', 94, 0, 41, 44, 0, 112759, '2020-12-20', 'Amerika Utara', 'bendera-grenada.png'),\n" +
                "('Guadeloupe', 'Guadeloupe', 8524, 154, 2242, 6128, 5, 400155, '2020-12-18', 'Amerika Utara', 'bendera-guadeloupe.svg.png'),\n" +
                "('Guatemala', 'Guatemala', 132595, 4624, 120715, 7256, 5, 18063872, '2020-12-20', 'Amerika Utara', 'bendera-guatemala.jpg'),\n" +
                "('Guinea', 'Guinea', 13532, 80, 12905, 547, 24, 13290915, '2020-12-19', 'Afrika', 'bendera-guinea.png'),\n" +
                "('Guinea Khatulistiwa', 'Equatorial Guinea', 5214, 85, 5064, 65, 0, 1413204, '2020-12-18', 'Afrika', 'bendera-guinea-khatulistiwa.png'),\n" +
                "('Guinea-Bissau', 'Guinea-Bissau', 2447, 44, 2378, 25, 5, 1988716, '2020-12-18', 'Afrika', 'bendera-guinea-bissau.png'),\n" +
                "('Guyana', 'Guyana', 6076, 159, 5288, 629, 6, 788271, '2020-12-19', 'Amerika Selatan', 'bendera-guyana.png'),\n" +
                "('Guyana Prancis', 'French Guiana', 12281, 71, 9995, 2215, 0, 302122, '2020-12-19', 'Amerika Selatan', 'bendera-guyana.png'),\n" +
                "('Haiti', 'Haiti', 9648, 234, 8321, 1093, 0, 11465087, '2020-12-19', 'Amerika Utara', 'bendera-haiti.png'),\n" +
                "('Honduras', 'Honduras', 116212, 3023, 54002, 59187, 112, 9975269, '2020-12-20', 'Amerika Utara', 'bendera-honduras.jpg'),\n" +
                "('Hong Kong', 'Hong Kong', 8079, 129, 6736, 1214, 61, 7524452, '2020-12-19', 'Asia', 'bendera-hong_kong.svg.png'),\n" +
                "('Hungaria', 'Hungary', 300022, 7914, 93323, 198785, 538, 9649176, '2020-12-20', 'Eropa', 'bendera-hongaria.jpg'),\n" +
                "('India', 'India', 10031659, 145513, 9579681, 306465, 8944, 1385937994, '2020-12-20', 'Asia', 'bendera-india.png'),\n" +
                "('Indonesia', 'Indonesia', 657948, 19659, 536260, 102029, -1, 274874562, '2020-12-20', 'Asia', 'bendera-indonesia.jpg'),\n" +
                "('Iran', 'Iran', 1152072, 53448, 875943, 222681, 5591, 84476530, '2020-12-20', 'Asia', 'bendera-iran.jpg'),\n" +
                "('Iraq', 'Iraq', 583118, 12680, 519005, 51433, 184, 40624922, '2020-12-20', 'Asia', 'bendera-irak.jpg'),\n" +
                "('Irlandia', 'Ireland', 78776, 2154, 23364, 53258, 29, 4962632, '2020-12-20', 'Eropa', 'bendera-irlandia.png'),\n" +
                "('Islandia', 'Iceland', 5621, 28, 5467, 126, 3, 342245, '2020-12-19', 'Eropa', 'bendera-islandia.jpg'),\n" +
                "('Israel', 'Israel', 372886, -5, -5, -5, -1, 9197590, '2020-12-20', 'Asia', 'bendera-palestina.png'),\n" +
                "('Italia', 'Italy', 1938083, 68447, 1249470, 620166, 2784, 60422807, '2020-12-20', 'Eropa', 'bendera-italia.jpg'),\n" +
                "('Jamaika', 'Jamaica', 12135, 285, 8812, 3038, 10, 2967023, '2020-12-19', 'Amerika Utara', 'bendera-jamaika.png'),\n" +
                "('Jepang', 'Japan', 193031, 2828, 163308, 26895, 598, 126299995, '2020-12-20', 'Asia', 'bendera-jepang.jpg'),\n" +
                "('Jerman', 'Germany', 1493961, 26414, 1085500, 382047, 4939, 83905438, '2020-12-20', 'Eropa', 'bendera-jerman.jpg'),\n" +
                "('Kaledonia Baru', 'New Caledonia', 37, 0, 35, 2, 0, 286736, '2020-12-18', 'Oceania', 'bendera-new caledonia.png'),\n" +
                "('Kamboja', 'Cambodia', 362, -1, 341, 21, 0, 16822947, '2020-12-18', 'Asia', 'bendera-kamboja.jpg'),\n" +
                "('Kamerun', 'Cameroon', 25724, 445, 23851, 1428, 50, 26839799, '2020-12-20', 'Afrika', 'bendera-kameron.jpg'),\n" +
                "('Kanada', 'Canada', 501594, 14154, 411396, 76044, 694, 37891529, '2020-12-20', 'Amerika Utara', 'bendera-kanada.jpg'),\n" +
                "('Karibia Belanda', 'Caribbean Netherlands', 179, 3, 172, 8, 0, 26333, '2020-12-20', 'Amerika Utara', 'Bendera-Belanda.jpg'),\n" +
                "('Kazakhstan', 'Kazakhstan', 146584, 2147, 130776, 13661, 221, 18877808, '2020-12-20', 'Asia', 'bendera-kazakhstan.jpg'),\n" +
                "('Kenya', 'Kenya', 94151, 1633, 75559, 16959, 52, 54299590, '2020-12-20', 'Afrika', 'bendera-kenya.png'),\n" +
                "('Kepulauan Channel', 'Channel Islands', 2526, 52, 1535, 939, 10, 174586, '2020-12-19', 'Eropa', 'bendera-channel island.png'),\n" +
                "('Kepulauan Falkland', 'Falkland Islands', 23, 0, 17, 6, 0, 3525, '2020-12-18', 'Amerika Selatan', 'bendera-falkland_islands.svg.png'),\n" +
                "('Kepulauan Foroe', 'Faeroe Islands', 539, -1, 516, 23, 0, 48947, '2020-12-20', 'Eropa', 'bendera-faroe_islands.svg.png'),\n" +
                "('Kepulauan Marshall', 'Marshall Islands', 4, 0, 4, 0, 0, 59371, '2020-12-14', 'Oceania', 'Bendera-Marshall.jpg'),\n" +
                "('Kepulauan Turks dan Caicos', 'Turks and Caicos', 789, 6, 755, 28, 1, 38952, '2020-12-20', 'Amerika Utara', 'bendera-turks_and_caicos_islands.svg.png'),\n" +
                "('Kepulauan Virgin Britania Raya', 'British Virgin Islands', 86, 1, 74, 11, 0, 30322, '2020-12-20', 'Amerika Utara', 'bendera-british_virgin_islands.svg.png'),\n" +
                "('Kirgizstan', 'Kyrgyzstan', 78911, 1328, 72459, 5124, 94, 6572452, '2020-12-20', 'Asia', 'bendera-kirgizstan.png'),\n" +
                "('Kolombia', 'Colombia', 1496062, 40268, 1362543, 93251, 2578, 51127304, '2020-12-20', 'Amerika Selatan', 'bendera-kolumbia.png'),\n" +
                "('Komoro', 'Comoros', 643, 7, 610, 26, 0, 877869, '2020-12-18', 'Afrika', 'bendera-komoro.png'),\n" +
                "('Kongo', 'Congo', 6200, 100, 4988, 1112, 0, 5578520, '2020-12-18', 'Afrika', 'bendera-republik-kongo.png'),\n" +
                "('Korea Selatan', 'S. Korea', 49665, 674, 34722, 14269, 278, 51289233, '2020-12-20', 'Asia', 'bendera-korea.jpg'),\n" +
                "('Kosta Rika', 'Costa Rica', 157472, 1996, 125488, 29988, 254, 5115109, '2020-12-20', 'Amerika Utara', 'bendera-kostarika.jpg'),\n" +
                "('Kroasia', 'Croatia', 192987, 3101, 169768, 20118, 306, 4093701, '2020-12-20', 'Eropa', 'bendera-krosia.jpg'),\n" +
                "('Kuba', 'Cuba', 9893, 137, 8912, 844, 12, 11323471, '2020-12-19', 'Amerika Utara', 'bendera-kuba.png'),\n" +
                "('Kuwait', 'Kuwait', 147775, 918, 143641, 3216, 57, 4298930, '2020-12-20', 'Asia', 'bendera-kuwait.jpg'),\n" +
                "('Laos', 'Laos', 41, 0, 36, 0, 0, 7322977, '2020-12-18', 'Asia', 'bendera-laos.jpg'),\n" +
                "('Latvia', 'Latvia', 30297, 427, 20534, 9336, 47, 1876636, '2020-12-20', 'Eropa', 'bendera-latvia.png'),\n" +
                "('Lebanon', 'Lebanon', 156570, 1270, 110535, 44765, 420, 6811499, '2020-12-20', 'Asia', 'bendera-lebanon.png'),\n" +
                "('Lesotho', 'Lesotho', 2546, 48, 1445, 1053, 0, 2149920, '2020-12-19', 'Afrika', 'bendera-lesotho.png'),\n" +
                "('Liberia', 'Liberia', 1779, 83, 1406, 290, 2, 5110629, '2020-12-18', 'Afrika', 'bendera-liberia.jpg'),\n" +
                "('Libya', 'Libya', 93772, 1346, 63886, 28540, 0, 6913291, '2020-12-20', 'Afrika', 'bendera-libya.jpg'),\n" +
                "('Liechtenstein', 'Liechtenstein', 1635, 21, 1395, 219, 16, 38178, '2020-12-18', 'Eropa', 'bendera-liechtenstein.jpg'),\n" +
                "('Lituania', 'Lithuania', 109429, 1005, 50587, 57837, 198, 2704835, '2020-12-20', 'Eropa', 'bendera-lithuania.png'),\n" +
                "('Luksemburg', 'Luxembourg', 44067, 440, 35237, 8390, 43, 630545, '2020-12-20', 'Eropa', 'bendera-luxembourg.jpg'),\n" +
                "('Macedonia Utara', 'North Macedonia', 77949, 2274, 53345, 22330, 138, 2083335, '2020-12-20', 'Eropa', 'bendera-north_macedonia.svg.png'),\n" +
                "('Madagaskar', 'Madagascar', 17587, 259, 16992, 336, 16, 28007434, '2020-12-18', 'Afrika', 'bendera-madagascar.jpg'),\n" +
                "('Makau', 'Macao', 46, 0, 46, 0, 0, 653314, '2020-12-14', 'Asia', 'bendera-macau.svg.png'),\n" +
                "('Maladewa', 'Maldives', 13444, 48, 12836, 560, 12, 544808, '2020-12-19', 'Asia', 'bendera-maladewa.jpg'),\n" +
                "('Malawi', 'Malawi', 6148, 187, 5662, 299, 4, 19349642, '2020-12-19', 'Afrika', 'bendera-malawi.jpg'),\n" +
                "('Malaysia', 'Malaysia', 91969, 433, 76242, 15294, 112, 32552528, '2020-12-20', 'Asia', 'bendera-malaysia1.jpg'),\n" +
                "('Mali', 'Mali', 6120, 215, 3771, 2134, 0, 20509323, '2020-12-19', 'Afrika', 'bendera-mali.png'),\n" +
                "('Malta', 'Malta', 11621, 187, 9881, 1553, 18, 442077, '2020-12-19', 'Eropa', 'bendera-malta.jpg'),\n" +
                "('Maroko', 'Morocco', 415226, 6909, 375623, 32694, 1066, 37107516, '2020-12-20', 'Afrika', 'bendera-maroko.jpg'),\n" +
                "('Martinik', 'Martinique', 5634, 42, 98, 5494, 5, 375133, '2020-12-18', 'Amerika Utara', 'bendera-martinique.svg.png'),\n" +
                "('Mauritania', 'Mauritania', 12046, 264, 8426, 3356, 60, 4703951, '2020-12-19', 'Afrika', 'bendera-mauritania.png'),\n" +
                "('Mauritius', 'Mauritius', 524, 10, 489, 25, 0, 1272726, '2020-12-18', 'Afrika', 'bendera-mauritius.png'),\n" +
                "('Mayotte', 'Mayotte', 5708, 53, 2964, 2691, 4, 275745, '2020-12-19', 'Afrika', 'bendera-mayotte.png'),\n" +
                "('Meksiko', 'Mexico', 1313675, 117876, 971115, 224684, 3890, 129543309, '2020-12-20', 'Amerika Utara', 'bendera-meksiko.jpg'),\n" +
                "('Mesir', 'Egypt', 124891, 7069, 106481, 11341, 90, 103197841, '2020-12-20', 'Afrika', 'bendera-mesir.png'),\n" +
                "('Moldova', 'Moldova', 134578, 2727, 117002, 14849, 264, 4029692, '2020-12-20', 'Eropa', 'bendera-moldova.png'),\n" +
                "('Monako', 'Monaco', 723, 3, 616, 104, 4, 39368, '2020-12-20', 'Eropa', 'bendera-monako.png'),\n" +
                "('Mongolia', 'Mongolia', 961, -1, 509, 452, 4, 3301967, '2020-12-20', 'Asia', 'bendera-mongolia.png'),\n" +
                "('Montenegro', 'Montenegro', 43709, 624, 33788, 9297, 68, 628102, '2020-12-20', 'Eropa', 'bendera-montenegro.png'),\n" +
                "('Montserrat', 'Montserrat', 13, 1, 12, 0, 0, 4993, '2020-12-14', 'Amerika Utara', 'bendera-montserrat.svg.png'),\n" +
                "('Mozambik', 'Mozambique', 17477, 147, 15451, 1879, 0, 31643794, '2020-12-19', 'Afrika', 'bendera-mozambik.jpg'),\n" +
                "('Myanmar', 'Myanmar', 115187, 2424, 94118, 18645, 0, 54574739, '2020-12-20', 'Asia', 'bendera-myanmar.jpg'),\n" +
                "('Namibia', 'Namibia', 18041, 169, 15626, 2246, 29, 2561504, '2020-12-19', 'Afrika', 'bendera-namibia.png'),\n" +
                "('Nepal', 'Nepal', 253184, 1777, 242567, 8840, -1, 29371432, '2020-12-20', 'Asia', 'bendera-nepal.png'),\n" +
                "('Niger', 'Niger', 2560, 85, 1377, 1098, 9, 24592159, '2020-12-19', 'Afrika', 'bendera-niger.jpg'),\n" +
                "('Nigeria', 'Nigeria', 77933, 1218, 67784, 8931, 10, 207292385, '2020-12-20', 'Afrika', 'bendera-nigeria.jpg'),\n" +
                "('Nikaragua', 'Nicaragua', 5938, 163, 4225, 1550, 0, 6660035, '2020-12-18', 'Amerika Utara', 'bendera-nikaragua.png'),\n" +
                "('Norwegia', 'Norway', 43582, 404, 37658, 5520, 34, 5440392, '2020-12-20', 'Eropa', 'bendera-norwegia.jpg'),\n" +
                "('Oman', 'Oman', 127019, 1483, 119009, 6527, 43, 5164368, '2020-12-18', 'Asia', 'bendera-oman.png'),\n" +
                "('Pakistan', 'Pakistan', 457288, 9330, 407405, 40553, 2365, 222810091, '2020-12-20', 'Asia', 'bendera-pakistan.png'),\n" +
                "('Palestina', 'Palestine', 121216, 1117, 96461, 23638, 128, 5154248, '2020-12-20', 'Asia', 'bendera-palestina.png'),\n" +
                "('Panama', 'Panama', 209584, 3527, 171745, 34312, 182, 4345239, '2020-12-20', 'Amerika Utara', 'bendera-panama.jpg'),\n" +
                "('Pantai Gading', 'Ivory Coast', 21845, 133, 21401, 292, 0, 26668871, '2020-12-19', 'Afrika', 'bendera-pantai-gading.jpg'),\n" +
                "('Papua Nugini', 'Papua New Guinea', 760, 8, 601, 151, 0, 9022835, '2020-12-18', 'Oceania', 'bendera-papua-nugini.jpg'),\n" +
                "('Paraguay', 'Paraguay', 99157, 2072, 70943, 26142, 177, 7171966, '2020-12-20', 'Amerika Selatan', 'bendera-paraguay.png'),\n" +
                "('Peru', 'Peru', 995899, 37034, 930410, 28455, 1083, 33178246, '2020-12-20', 'Amerika Selatan', 'bendera-peru.gif'),\n" +
                "('Polandia', 'Poland', 1194110, 25254, 927723, 241133, 1695, 37827748, '2020-12-20', 'Eropa', 'bendera-polandia.jpg'),\n" +
                "('Polinesia Prancis', 'French Polynesia', 16182, 103, 4842, 11237, 21, 281643, '2020-12-19', 'Oceania', 'bendera-french_polynesia.svg.png'),\n" +
                "('Portugal', 'Portugal', 370787, 6063, 294814, 69910, 485, 10183160, '2020-12-20', 'Eropa', 'bendera-portugal.jpg'),\n" +
                "('Prancis', 'France', 2460555, 60418, 183571, 2216566, 2727, 65336705, '2020-12-20', 'Eropa', 'bendera-perancis.png'),\n" +
                "('Pulau Man', 'Isle of Man', 373, 25, 344, 4, 0, 85237, '2020-12-18', 'Eropa', 'bendera-isle_of_man.svg.png'),\n" +
                "('Qatar', 'Qatar', 141858, 243, 139521, 2094, 21, 2807805, '2020-12-20', 'Asia', 'bendera-qatar.jpg'),\n" +
                "('Republik Afrika Tengah', 'CAR', 4936, 63, 1924, 2949, 2, 4867383, '2020-12-14', 'Afrika', 'bendera-republik-afrika-tengah.png'),\n" +
                "('Republik Ceko', 'Czechia', 618836, 10271, 531622, 76943, 589, 10718001, '2020-12-20', 'Eropa', 'bendera-republik-ceko.jpg'),\n" +
                "('Republik Demokratik Kongo', 'DRC', 15397, 369, 13310, 1718, 0, 90766200, '2020-12-19', 'Afrika', 'bendera-demokratik-republik-kongo.jpg'),\n" +
                "('Republik Dominika', 'Dominican Republic', 159064, 2382, 123284, 33398, 259, 10896961, '2020-12-20', 'Amerika Utara', 'bendera-republik-dominika.png'),\n" +
                "('Réunion', 'Réunion', 8704, 42, 8219, 443, 5, 898200, '2020-12-19', 'Afrika', 'bendera-reounion.png'),\n" +
                "('Romania', 'Romania', 587944, 14296, 493379, 80269, 1274, 19179039, '2020-12-20', 'Eropa', 'bendera-rumania.jpg'),\n" +
                "('Rusia', 'Russia', 2819429, 50347, 2254742, 514340, 2300, 145949102, '2020-12-20', 'Eropa', 'bendera-rusia.jpg'),\n" +
                "('Rwanda', 'Rwanda', 7105, 57, 6089, 959, 0, 13095052, '2020-12-19', 'Afrika', 'bendera-rwanda.png'),\n" +
                "('Sahara Barat', 'Western Sahara', 10, 1, 8, 1, 0, 603874, '2020-12-14', 'Afrika', 'bendera-sahara barat.png'),\n" +
                "('Saint Barthélemy', 'St. Barth', 175, 1, 161, 13, 0, 9891, '2020-12-20', 'Amerika Utara', 'bendera-saint_barthelemy_(local).svg.png'),\n" +
                "('Saint Kitts dan Nevis', 'Saint Kitts and Nevis', 30, 0, 24, 6, 0, 53369, '2020-12-20', 'Amerika Utara', 'bendera-saint-kitts-dan-nevis.jpg'),\n" +
                "('Saint Lucia', 'Saint Lucia', 282, 5, 253, 24, 0, 184007, '2020-12-20', 'Amerika Utara', 'bendera-saint-lucia.png'),\n" +
                "('Saint Martin', 'Saint Martin', 801, 12, 675, 114, 7, 38961, '2020-12-14', 'Amerika Utara', 'bendera-saint-vincent-and-the-grenadines.png'),\n" +
                "('Saint Pierre dan Miquelon', 'Saint Pierre Miquelon', 14, 0, 14, 0, 0, 5781, '2020-12-18', 'Amerika Utara', 'bendera-saint-pierre_and_miquelon.svg.png'),\n" +
                "('Saint Vincent dan Grenadines', 'St. Vincent Grenadines', 100, 0, 82, 18, 0, 111100, '2020-12-18', 'Amerika Utara', 'bendera-sint_maarten.svg.png'),\n" +
                "('Samoa', 'Samoa', 2, 0, 2, 0, 0, 199010, '2020-12-14', 'Oceania', 'bendera-samoa.png'),\n" +
                "('San Marino', 'San Marino', 2049, 54, 1703, 292, 5, 33963, '2020-12-18', 'Eropa', 'bendera-san-marino.jpg'),\n" +
                "('Sao Tome dan Principe', 'Sao Tome and Principe', 1012, 17, 958, 37, 0, 220980, '2020-12-20', 'Afrika', 'bendera-sao-tome-and-principe.png'),\n" +
                "('Selandia Baru', 'New Zealand', 2110, 25, 2034, 51, 0, 5002100, '2020-12-18', 'Oceania', 'bendera-selandia-baru-new-zealand.png'),\n" +
                "('Senegal', 'Senegal', 17670, 361, 16493, 816, 25, 16939929, '2020-12-19', 'Afrika', 'bendera-senegal.png'),\n" +
                "('Serbia', 'Serbia', 296528, 2632, 31536, 262360, 326, 8721319, '2020-12-20', 'Eropa', 'bendera-serbia.jpg'),\n" +
                "('Seychelles', 'Seychelles', 202, 0, 184, 18, 0, 98622, '2020-12-18', 'Afrika', 'bendera-seychelles.png'),\n" +
                "('Sierra Leone', 'Sierra Leone', 2464, 75, 1854, 535, 0, 8049452, '2020-12-18', 'Afrika', 'bendera-sierra-leone.png'),\n" +
                "('Singapura', 'Singapore', 58403, 29, 58274, 100, 0, 5871127, '2020-12-20', 'Asia', 'bendera-singapura.jpg'),\n" +
                "('Sint Maarten (Prancis)', 'Sint Maarten', 1323, 26, 1159, 138, 4, 43095, '2020-12-20', 'Amerika Utara', 'bendera-sint_maarten.svg.png'),\n" +
                "('Siprus', 'Cyprus', 17057, 87, 2057, 14913, 16, 1211331, '2020-12-19', 'Asia', 'bendera-siprus.png'),\n" +
                "('Slovenia', 'Slovenia', 105013, 2314, 82541, 20158, 204, 2079068, '2020-12-20', 'Eropa', 'bendera-slovenia.png'),\n" +
                "('Slowakia', 'Slovakia', 149275, 1510, 106361, 41404, 304, 5460844, '2020-12-20', 'Eropa', 'bendera-slowakia.jpg'),\n" +
                "('Solomon', 'Solomon Islands', 17, 0, 5, 12, 0, 694379, '2020-12-14', 'Oceania', 'bendera-solomon.png'),\n" +
                "('Somalia', 'Somalia', 4662, 124, 3566, 972, 0, 16089895, '2020-12-18', 'Afrika', 'bendera-somalia.png'),\n" +
                "('Spanyol', 'Spain', 1817448, 48926, -1, -1, 1920, 46763009, '2020-12-20', 'Eropa', 'bendera-spanyol.jpg'),\n" +
                "('Sri Lanka', 'Sri Lanka', 36667, 171, 27552, 8944, 0, 21453933, '2020-12-20', 'Asia', 'bendera-srilanka.jpg'),\n" +
                "('Sudan', 'Sudan', 22621, 1425, 13024, 8172, 0, 44305343, '2020-12-19', 'Afrika', 'bendera-sudan.png'),\n" +
                "('Sudan Selatan', 'South Sudan', 3228, 62, 3090, 76, 1, 11252813, '2020-12-19', 'Afrika', 'bendera-sudan-selatan.png'),\n" +
                "('Suriah', 'Syria', 9928, 591, 4686, 4651, 0, 17689876, '2020-12-19', 'Asia', 'bendera-suriah.png'),\n" +
                "('Suriname', 'Suriname', 5459, 117, 5249, 93, 3, 589004, '2020-12-19', 'Amerika Selatan', 'bendera-suriname.png'),\n" +
                "('Swaziland', 'Eswatini', 7202, 136, 6624, 442, 67, 1165579, '2020-12-19', 'Afrika', 'bendera-swaziland.png'),\n" +
                "('Swedia', 'Sweden', 367120, 7993, -1, -1, 281, 10127752, '2020-12-20', 'Eropa', 'bendera-swedia.jpg'),\n" +
                "('Swiss', 'Switzerland', 403989, 6602, 311500, 85887, 437, 8683227, '2020-12-20', 'Eropa', 'bendera-swiss.gif'),\n" +
                "('Taiwan', 'Taiwan', 763, 7, 625, 131, 0, 23836347, '2020-12-20', 'Asia', 'bendera-taiwan.png'),\n" +
                "('Tajikistan', 'Tajikistan', 12923, 89, 12374, 460, 0, 9633156, '2020-12-19', 'Asia', 'bendera-tajikistan.png'),\n" +
                "('Tanjung Verde', 'Cabo Verde', 11526, 110, 11164, 252, 23, 558708, '2020-12-19', 'Afrika', 'bendera-tanjung-verde.png'),\n" +
                "('Tanzania', 'Tanzania', 509, 21, 183, 305, 7, 60488464, '2020-12-14', 'Afrika', 'bendera-tanzania.png'),\n" +
                "('Thailand', 'Thailand', 4331, 60, 4024, 247, 1, 69879453, '2020-12-19', 'Asia', 'bendera-thailand.jpg'),\n" +
                "('Timor Leste', 'Timor-Leste', 31, 0, 30, 1, 0, 1329677, '2020-12-14', 'Asia', 'bendera-timor-leste.png'),\n" +
                "('Togo', 'Togo', 3350, 66, 2933, 351, 0, 8365153, '2020-12-19', 'Afrika', 'bendera-togo.png'),\n" +
                "('Trinidad dan Tobago', 'Trinidad and Tobago', 6955, 123, 6405, 427, 2, 1401543, '2020-12-19', 'Amerika Utara', 'bendera-trinidad-and-tobago.png'),\n" +
                "('Tunisia', 'Tunisia', 119151, 4126, 87884, 27141, 287, 11874360, '2020-12-20', 'Afrika', 'bendera-tunisia.jpg'),\n" +
                "('Turki', 'Turkey', 2004285, 17851, 1779068, 207366, 5501, 84748019, '2020-12-20', 'Asia', 'bendera-turki.jpg'),\n" +
                "('Uganda', 'Uganda', 30702, 230, 10360, 20112, 0, 46379453, '2020-12-20', 'Afrika', 'bendera-uganda.png'),\n" +
                "('Ukraina', 'Ukraine', 956123, 16469, 574536, 365118, 177, 43613740, '2020-12-20', 'Eropa', 'bendera-ukraina.jpg'),\n" +
                "('Uni Emirat Arab', 'UAE', 192404, 634, 168129, 23641, 0, 9944188, '2020-12-20', 'Asia', 'bendera-uni-emirate-arab.png'),\n" +
                "('Uruguay', 'Uruguay', 11950, 109, 7699, 4142, 44, 3479188, '2020-12-19', 'Amerika Selatan', 'bendera-uruguay.jpg'),\n" +
                "('Uzbekistan', 'Uzbekistan', 75806, 612, 73099, 2095, 171, 33687059, '2020-12-20', 'Asia', 'bendera-uzbekistan.png'),\n" +
                "('Vanuatu', 'Vanuatu', 1, 0, 1, 0, 0, 310342, '2020-12-15', 'Oceania', 'bendera-vanuatu.png'),\n" +
                "('Vatikan', 'Vatican City', 27, 0, 15, 12, 0, 802, '2020-12-14', 'Eropa', 'bendera-vatikan.jpg'),\n" +
                "('Venezuela', 'Venezuela', 109781, 984, 104208, 4589, 54, 28399225, '2020-12-20', 'Amerika Selatan', 'bendera-venezuela.jpg'),\n" +
                "('Vietnam', 'Vietnam', 1407, 35, 1263, 109, 0, 97733842, '2020-12-18', 'Asia', 'bendera-vietnam.jpg'),\n" +
                "('Wallis dan Futuna', 'Wallis and Futuna', 4, 0, 1, 3, 0, 11148, '2020-12-18', 'Oceania', 'bendera-wallis-and-futuna.png'),\n" +
                "('Yaman', 'Yemen', 2087, 606, 1384, 97, 23, 30118960, '2020-12-18', 'Asia', 'bendera-yaman.png'),\n" +
                "('Yordania', 'Jordan', 272797, 3545, 239126, 30126, 334, 10248811, '2020-12-20', 'Asia', 'bendera-yordania.png'),\n" +
                "('Yunani', 'Greece', 130485, 4102, 9989, 116394, 534, 10399817, '2020-12-20', 'Eropa', 'bendera-yunani.jpg'),\n" +
                "('Zambia', 'Zambia', 18575, 373, 17729, 473, 0, 18612297, '2020-12-19', 'Afrika', 'bendera-zambia.png'),\n" +
                "('Zimbabwe', 'Zimbabwe', 12047, 316, 9894, 1837, 0, 14960094, '2020-12-19', 'Afrika', 'bendera-zimbabwe1.jpg');";
    }
    
     /**
     * Akan mengembalikan sebuah query untuk mengisi tabel kasuscovid_indo jika tabel tersebut kosong.
     * 
     * @return query untuk mengisi tabel kasuscovid_indo
     */
    protected static String getDefaultDataTabel_kasusCovidIndo(){
        return  "INSERT INTO `kasuscovid_indo` (`kode`, `provinsi`, `kasus`, `sembuh`, `kematian`, `aktif`, `populasi`, `total_kab`, `kab_zonamerah`, `kab_zonaoranye`, `kab_zonahijau`, `kasus_pertama`, `diubah`, `website`, `lambang`) VALUES\n" +
                "('Aceh', 'Aceh', 8569, 7059, 354, 1156, 5189500, 18, 0, 18, 0, '2020-03-26', '2020-12-20', 'covid19.acehprov.go.id', 'lambang-aceh.png'),\n" +
                "('Babel', 'Kep. Bangka Belitung', 1644, 1296, 26, 322, 1430900, 6, 0, 6, 0, '2020-03-30', '2020-12-20', 'covid19.babelprov.go.id', 'lambang-bangka-belitung.png'),\n" +
                "('Bali', 'Bali', 16077, 14649, 477, 951, 4246500, 8, 0, 6, 2, '2020-03-10', '2020-12-20', 'infocorona.baliprov.go.id', 'lambang-bali.png'),\n" +
                "('Banten', 'Banten', 15969, 9811, 390, 5768, 12448200, 4, 3, 1, 0, '2020-03-08', '2020-12-20', 'infocorona.bantenprov.go.id', 'lambang-banten.png'),\n" +
                "('Bengkulu', 'Bengkulu', 3001, 2014, 105, 882, 1934300, 9, 2, 7, 0, '2020-03-31', '2020-12-20', 'covid19.bengkuluprov.go.id', 'lambang-bengkulu.png'),\n" +
                "('DIY', 'DI Yogyakarta', 9287, 6178, 182, 2927, 3762200, 4, 1, 3, 0, '2020-03-13', '2020-12-20', 'corona.jogjaprov.go.id', 'lambang-yogyakarta.png'),\n" +
                "('Gorontalo', 'Gorontalo', 3365, 3179, 96, 90, 1168200, 5, 0, 5, 0, '2020-04-09', '2020-12-20', 'covid-19.gorontaloprov.go.id', 'lambang-gorontalo.jpg'),\n" +
                "('Jabar', 'Jawa Barat', 72896, 59754, 1094, 12048, 48037600, 18, 8, 10, 0, '2020-03-02', '2020-12-20', 'pikobar.jabarprov.go.id', 'lambang-jawa-barat.png'),\n" +
                "('Jakarta', 'DKI Jakarta', 161519, 144988, 3053, 13478, 10374200, 1, 1, 0, 0, '2020-03-02', '2020-12-20', 'corona.jakarta.go.id', 'lambang-jakarta.png'),\n" +
                "('Jambi', 'Jambi', 2901, 1940, 48, 913, 3515000, 9, 2, 7, 0, '2020-03-23', '2020-12-20', 'corona.jambiprov.go.id', 'lambang-jambi.jpg'),\n" +
                "('Jateng', 'Jawa Tengah', 70653, 47806, 2770, 20077, 34257900, 29, 17, 12, 0, '2020-03-09', '2020-12-20', 'corona.jatengprov.go.id', 'lambang-jawa-tengah.png'),\n" +
                "('Jatim', 'Jawa Timur', 74550, 63978, 5150, 5422, 39293000, 29, 6, 23, 0, '2020-03-17', '2020-12-20', 'infocovid19.jatimprov.go.id', 'lambang-jawa-timur.png'),\n" +
                "('Kalbar', 'Kalimanan Barat', 2927, 2517, 25, 385, 4932500, 12, 0, 12, 0, '2020-03-10', '2020-12-20', 'covid19.kalbarprov.go.id', 'lambang-kalimantan-barat.png'),\n" +
                "('Kalsel', 'Kalimantan Selatan', 14498, 13122, 563, 813, 4119800, 11, 0, 11, 0, '2020-03-22', '2020-12-20', 'corona.kalselprov.go.id', 'lambang-kalimantan-selatan.gif'),\n" +
                "('Kaltara', 'Kalimantan Utara', 2687, 1622, 33, 1032, 691100, 4, 1, 3, 0, '2020-03-28', '2020-12-20', 'coronainfo.kaltaraprov.go.id', 'lambang-kalimantan-utara.png'),\n" +
                "('Kalteng', 'Kalimantan Tengah', 8480, 5867, 243, 2370, 2605300, 13, 7, 6, 0, '2020-03-20', '2020-12-20', 'corona.kalteng.go.id', 'lambang-kalimantan-tengah.png'),\n" +
                "('Kaltim', 'Kalimantan Timur', 23926, 20169, 667, 3090, 3575400, 7, 0, 7, 0, '2020-03-18', '2020-12-20', 'covid19.kaltimprov.go.id', 'lambang-kalimantan-timur.png'),\n" +
                "('Kepri', 'Kepulauan Riau', 6528, 5427, 160, 941, 2082700, 5, 1, 4, 0, '2020-03-17', '2020-12-20', 'corona.kepriprov.go.id', 'lambang-kepulauan-riau.png'),\n" +
                "('Lampung', 'Lampung', 5220, 3442, 238, 1540, 8289600, 13, 1, 12, 0, '2020-03-18', '2020-12-20', 'covid19.lampungprov.go.id', 'lambang-lampung.png'),\n" +
                "('Maluku', 'Maluku', 5384, 3994, 70, 1320, 1744700, 9, 1, 0, 1, '2020-03-22', '2020-12-20', 'corona.malukuprov.go.id', 'lambang-maluku.png'),\n" +
                "('Malut', 'Maluku Utara', 2592, 2068, 86, 438, 1209300, 8, 0, 8, 0, '2020-03-23', '2020-12-20', 'corona.malutprov.go.id', 'lambang-maluku-utara.png'),\n" +
                "('NTB', 'Nusa Tenggara Barat', 5215, 4245, 258, 712, 4955600, 8, 1, 7, 0, '2020-03-24', '2020-12-20', 'corona.ntbprov.go.id', 'lambang-nusa-tenggara-barat.png'),\n" +
                "('NTT', 'Nusa Tenggara Timur', 1779, 936, 36, 807, 5287300, 21, 1, 20, 0, '2020-04-09', '2020-12-20', 'covid19.nttprov.go.id', 'lambang-nusa-tenggara-timur.png'),\n" +
                "('Pabar', 'Papua Barat', 5737, 5183, 98, 456, 915400, 12, 0, 11, 1, '2020-03-27', '2020-12-20', 'dinkes.papuabaratprov.go.id', 'lambang-papua-barat.png'),\n" +
                "('Papua', 'Papua', 12742, 5887, 146, 6709, 3265200, 28, 1, 22, 5, '2020-03-22', '2020-12-20', 'covid19.papua.go.id', 'lambang-papua.png'),\n" +
                "('Riau', 'Riau', 23606, 21059, 538, 2009, 6657900, 10, 1, 9, 0, '2020-03-18', '2020-12-20', 'corona.riau.go.id', 'lambang-riau.png'),\n" +
                "('Sulbar', 'Sulawesi Barat', 1689, 1429, 26, 234, 1331000, 6, 0, 6, 0, '2020-03-29', '2020-12-20', 'dinkes.sulbarprov.go.id', 'lambang-sulawesi-barat.png'),\n" +
                "('Sulsel', 'Sulawesi Selatan', 25274, 20263, 547, 4464, 8690300, 21, 0, 21, 0, '2020-03-19', '2020-12-20', 'covid19.sulselprov.go.id', 'lambang-sulawesi-selatan.png'),\n" +
                "('Sulteng', 'Sulawesi Tengah', 2700, 1611, 93, 996, 2966300, 12, 1, 11, 0, '2020-03-26', '2020-12-20', 'dinkes.sultengprov.go.id', 'lambang-sulawesi-tengah.png'),\n" +
                "('Sultra', 'Sulawesi Tenggara', 7478, 6354, 123, 1001, 2602400, 15, 1, 14, 0, '2020-03-19', '2020-12-20', 'dinkes.sultraprov.go.id', 'lambang-sulawesi-tenggara.png'),\n" +
                "('Sulut', 'Sulawesi Utara', 8780, 6206, 279, 2295, 2461000, 11, 0, 11, 0, '2020-03-14', '2020-12-20', 'corona.sulutprov.go.id', 'lambang-sulawesi-utara.png'),\n" +
                "('Sumbar', 'Sumatra Barat', 22331, 19057, 462, 2812, 5321500, 12, 1, 11, 0, '2020-03-26', '2020-12-20', 'corona.sumbarprov.go.id', 'lambang-sumatra-barat.png'),\n" +
                "('Sumsel', 'Sumatra Selatan', 10763, 8715, 574, 1474, 8267000, 13, 1, 12, 0, '2020-03-24', '2020-12-20', 'corona.sumselprov.go.id', 'lambang-sumatra-selatan.png'),\n" +
                "('Sumut', 'Sumatra Utara', 17181, 14435, 649, 2097, 14262100, 25, 5, 18, 2, '2020-03-18', '2020-12-20', 'corona.sulutprov.go.id', 'lambang-sumatra-utara.png');";
    }
    
     /**
     * Akan mengembalikan sebuah query untuk mengisi tabel users jika tabel tersebut kosong.
     * 
     * @return query untuk mengisi tabel users
     */    
    protected static String getDefaultDataTabel_users(){
        return  "INSERT INTO `users` (`username`, `namalengkap`, `namapanggilan`, `email`, `gender`, `tgl_lahir`, `pekerjaan`, `alamat`, `negara`, `password`, `tgl_dibuat`, `fotoprofile`, `type`) VALUES\n" +
                "('app.covid19', 'Coronavirus', 'Covid', 'coronavirus@gmail.com', 'N', '0001-01-01', 'Tidak Dicantumkan', 'Jawa Timur', 'Indonesia', 'baihaqi_2020', '2020-12-15', 'default', 'User')," + 
                "('baihaqi', 'Achmad Baihaqi', 'Baihaqi', 'hakiahmad756@gmail.com', 'L', '2003-08-04', 'Data Scientist', 'Tokyo (東京)', 'Jepang', 'pandemic', '2020-11-18', 'default', 'Admin'),\n" +
                "('bot.aan', 'Ahmad Ansori', 'Ansori', 'bot.aanblogme@gmail.com', 'L', '2002-06-01', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.ansori', '2020-11-29', 'default', 'User'),\n" +
                "('bot.adet', 'Adetya Wardani', 'Adet', 'bot.adetyawardani5@gmail.com', 'P', '2003-06-03', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.adet', '2020-11-29', 'default', 'User'),\n" +
                "('bot.agung', 'Agung Tri Laksono', 'Agung', 'bot.agungtrilaksono1287@gmail.com', 'L', '2003-12-13', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.agung', '2020-11-29', 'default', 'User'),\n" +
                "('bot.amal', 'Amalia Devi Fitriana', 'Amalia', 'bot.amaliadevifitriana@gmail.com', 'P', '2002-12-13', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.amal', '2020-11-29', 'default', 'User'),\n" +
                "('bot.ananta', 'Ananta Eka Prayoga', 'Ananta', 'bot.anantaprayoga25@gmail.com', 'L', '2003-01-28', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.ananta', '2020-11-29', 'default', 'User'),\n" +
                "('bot.aning', 'Aning Rahma Ambar Sari', 'Aning', 'bot.aningrahma123@gmail.com', 'P', '2002-05-22', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.aning', '2020-11-29', 'default', 'User'),\n" +
                "('bot.arahma', 'Arahma Handayani', 'Arahma', 'bot.arrahmahandayani@gmail.com', 'P', '2003-07-13', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.arahma', '2020-11-29', 'default', 'User'),\n" +
                "('bot.arifin', 'M. Arifin Mustofa', 'Arifin', 'bot.arifinmmustofa173@gmail.com', 'L', '2003-10-26', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.arifin', '2020-11-29', 'default', 'User'),\n" +
                "('bot.atun', 'Mar Atunnufita Sari', 'Atun', 'bot.nupita36@gmail.com', 'P', '2003-05-02', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.atun', '2020-11-29', 'default', 'User'),\n" +
                "('bot.baihaqi', 'Achmad Baihaqi', 'Baihaqi', 'bot.hakiahmad756@gmail.com', 'L', '2003-08-04', 'Cyber Security Analyst', 'Tokyo (東京)', 'Jepang', 'bot.baihaqi', '2020-11-29', 'default', 'User'),\n" +
                "('bot.bella', 'Bella Alfyo Alda Yulanda', 'Bella', 'bot.bellayulanda45@gmail.com', 'P', '2003-01-06', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.bella', '2020-11-29', 'default', 'User'),\n" +
                "('bot.bima', 'Fahrezian Arya Bima', 'Bima', 'bot.awal.in45@gmail.com', 'L', '2003-09-20', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.bima', '2020-11-29', 'default', 'User'),\n" +
                "('bot.cantika', 'Cantika Dea Mareta', 'Cantika', 'bot.cantikadea18@gmail.com', 'P', '2002-03-29', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.cantika', '2020-11-29', 'default', 'User'),\n" +
                "('bot.chuenk', 'M. Nur Kholis Chu Enk Yunani', 'Chu Enk', 'bot.chuenkaisyah15@gmail.com', 'L', '2003-06-02', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.chuenk', '2020-11-29', 'default', 'User'),\n" +
                "('bot.cindy', 'Cindy Qaula Agustin', 'Cindy', 'bot.qaulacindy@gmail.com', 'P', '2002-08-30', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.cindy', '2020-11-29', 'default', 'User'),\n" +
                "('bot.david', 'David Aldian Hidayat', 'David', 'bot.davidaldian15@gmail.com', 'L', '2002-06-17', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.david', '2020-11-29', 'default', 'User'),\n" +
                "('bot.deky', 'Deky Reza Saputra', 'Deky', 'bot.dekyreza8787@gmail.com', 'L', '2002-11-26', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.deky', '2020-11-29', 'default', 'User'),\n" +
                "('bot.dhino', 'Dhinno Haryasena', 'Dhinno', 'bot.dhinosena@gmail.com', 'L', '2001-10-06', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.dhino', '2020-11-29', 'default', 'User'),\n" +
                "('bot.didin', 'Didin Rakfil Beniafan', 'Didin', 'bot.rakfildidin@gmail.com', 'L', '2002-10-27', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.didin', '2020-11-29', 'default', 'User'),\n" +
                "('bot.disma', 'Disma Eka Nurtanti', 'Disma', 'bot.dismaeka22@gmail.com', 'P', '2002-12-22', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.disma', '2020-11-29', 'default', 'User'),\n" +
                "('bot.diva', 'Diva Sabriana', 'Diva', 'bot.divasabriana81@gmail.com', 'P', '2002-10-05', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.diva', '2020-11-29', 'default', 'User'),\n" +
                "('bot.dwi', 'Dwi Windarti', 'Dwi ', 'bot.dwiw46711@gmail.com', 'P', '2002-12-16', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot..dwi', '2020-11-29', 'default', 'User'),\n" +
                "('bot.elok', 'Elok Fadhilah Marta', 'Elok', 'bot.elokfadila567@gmail.com', 'P', '2003-03-19', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.elok', '2020-11-29', 'default', 'User'),\n" +
                "('bot.fadia', 'Fadia Irsania Putri', 'Fadia', 'bot.fadiairsania123@gmail.com', 'P', '2002-08-10', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.fadia', '2020-11-29', 'default', 'User'),\n" +
                "('bot.fatur', 'Fatur Riandy', 'Fatur', 'bot.riyandifatur868@gmail.com', 'L', '2002-09-20', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.fatur', '2020-11-29', 'default', 'User'),\n" +
                "('bot.fitri', 'Fitri Nur Fadillah', 'Fitri', 'bot.fitrinurfadillah0107@gmail.com', 'P', '2002-11-25', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.fitri', '2020-11-29', 'default', 'User'),\n" +
                "('bot.gita', 'Adelia Puteri Gita Novita Sari', 'Gita', 'bot.adelia2019adelia@gmail.com', 'P', '2003-08-30', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.gita', '2020-11-29', 'default', 'User'),\n" +
                "('bot.halim', 'Halim Bagus Perdana', 'Halim', 'bot.halimpaijo1@gmail.com', 'L', '2002-11-09', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.halim', '2020-11-29', 'default', 'User'),\n" +
                "('bot.ike', 'Ike Handayani', 'Ikke', 'bot.Ikehandayani189@gmail.com', 'P', '2002-10-03', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.ikke', '2020-11-29', 'default', 'User'),\n" +
                "('bot.ilham', 'Ilham Maulana', 'Ilham', 'bot.im285281@gmail.com', 'L', '2002-07-16', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.ilham', '2020-11-29', 'default', 'User'),\n" +
                "('bot.inna', 'Inna Fatahna', 'Inna', 'bot.innafatahna5@gmail.com', 'P', '2003-10-14', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.inna', '2020-11-29', 'default', 'User'),\n" +
                "('bot.julia', 'Julia Sabrina Putri', 'Julia', 'bot.juliasbrn123@gmail.com', 'P', '2002-07-20', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.julia', '2020-11-29', 'default', 'User'),\n" +
                "('bot.kritisna', 'Kristina Apriliya', 'Kristina', 'bot.agustinakristina8@gmail.com', 'P', '2003-04-14', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.kritisna', '2020-11-29', 'default', 'User'),\n" +
                "('bot.raihan', 'Ade Raihan Mahsa', 'Raihan', 'bot.aderaihanacaaca@gmail.com', 'L', '2002-11-19', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.raihan', '2020-11-29', 'default', 'User'),\n" +
                "('bot.zikul', 'Ahmad Rozikul Akbar', 'Rozikul', 'bot.ahmadrozikul76@gmail.com', 'L', '2002-03-26', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'bot.zikul', '2020-11-29', 'default', 'User'),\n" +
                "('chrome', 'Google Chrome', 'Chrome', 'google.chrome@gmail.com', 'N', '2008-09-02', 'App Developer', 'Silicon Valey', 'USA', 'google.yahoo.com', '2020-12-10', 'default', 'Admin'),\n" +
                "('kom.ayu', 'Novita Ayu Ismawati', 'Novita', 'kom.novitaismawati26@gmail.com', 'P', '2002-11-23', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom..ayu', '2020-11-30', 'default', 'User'),\n" +
                "('kom.bayu', 'Perkasa Bayu Utama', 'Bayu', 'kom.bayuutama714@gmail.com', 'L', '2002-01-25', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.bayu', '2020-11-30', 'default', 'User'),\n" +
                "('kom.bintang', 'Muhammad Bintang Ichtiar', 'Bintang', 'kom.kecilb647@gmail.com', 'L', '2002-11-04', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.bintang', '2020-11-30', 'default', 'User'),\n" +
                "('kom.dimas', 'Rangga Dimas Prayoga', 'Dimas', 'kom.ranggadimas46@gmail.com', 'L', '2002-12-04', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.dimas', '2020-11-30', 'default', 'User'),\n" +
                "('kom.farid', 'Muchammad Farid', 'Farid', 'kom.muchammadfarid399@gmail.com', 'L', '2003-01-23', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.farid', '2020-11-30', 'default', 'User'),\n" +
                "('kom.hadi', 'Pangestu Hadi Wijaya', 'Hadi', 'kom.jayapangestu73@gmail.com', 'L', '2003-02-15', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.hadi', '2020-11-30', 'default', 'User'),\n" +
                "('kom.istafid', 'Mohammad Istafid Fidllal Islam', 'Istafid', 'kom.tapitarg0000@gmail.com', 'L', '2002-12-19', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.istafid', '2020-11-30', 'default', 'User'),\n" +
                "('kom.mahes', 'Syahdhanimahes Rayendra', 'Syahdhanimahes', 'kom.yendramahes62@gmail.com', 'L', '2003-05-16', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.mahes', '2020-11-30', 'default', 'User'),\n" +
                "('kom.mellyana', 'Mellyana Tundjung', 'Mellyana', 'kom.tundjungmelly@gmail.com', 'P', '2002-08-15', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.mellyana', '2020-12-14', 'default', 'User'),\n" +
                "('kom.nanang', 'Nanang Abdul Cholil', 'Nanang', 'kom.nanangketegan1927@gmail.com', 'L', '2002-07-27', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.nanang', '2020-11-30', 'default', 'User'),\n" +
                "('kom.nazila', 'Putri Nazila', 'Nazila', 'kom.nazilaxo@gmail.com', 'P', '2002-10-10', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.nazila', '2020-11-30', 'default', 'User'),\n" +
                "('kom.nurul', 'Nurul Hamidatul Ala', 'Nurul', 'kom.alahamidatul@gmail.com', 'P', '2003-05-31', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.nurul', '2020-11-30', 'default', 'User'),\n" +
                "('kom.nyirrahma', 'Nyiurrahma Aurora Awaliyah', 'Nyiurrahma', 'kom.nyiurrahmaaurora@gmail.com', 'P', '2003-06-20', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.nyirrahma', '2020-11-30', 'default', 'User'),\n" +
                "('kom.raffi', 'Muh. Raffi Ramadhan', 'Raffi', 'kom.muhammadraffi2411@gmail.com', 'L', '2002-11-24', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.raffi', '2020-11-30', 'default', 'User'),\n" +
                "('kom.rahayu', 'Mia Indi Rahayu', 'Rahayu', 'kom.aw814524@gmail.com', 'P', '2003-03-10', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.rahayu', '2020-11-30', 'default', 'User'),\n" +
                "('kom.rahma', 'Rahma Cahyani', 'Rahma', 'kom.rahmacahyani9@gmail.com', 'P', '2003-06-20', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.rahma', '2020-11-30', 'default', 'User'),\n" +
                "('kom.rahmalia', 'Rahmalia Ariani', 'Rahmalia', 'kom.rahmaliaa8@gmail.com', 'P', '2003-04-04', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.rahmalia', '2020-11-30', 'default', 'User'),\n" +
                "('kom.ramadhani', 'Wahyu Ramadhani', 'Ramadhani', 'kom.ramadhaniwahyu255@gmail.com', 'L', '2002-11-13', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.ramadhani', '2020-11-30', 'default', 'User'),\n" +
                "('kom.ratna', 'Reni Ratna Sari', 'Ratna', 'kom.reniratna0305@gmail.com', 'P', '2003-05-03', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.ratna', '2020-11-30', 'default', 'User'),\n" +
                "('kom.ravillya', 'Ravillya Shafa Ramadhani Suseno', 'Ravillya', 'kom.ravillyashafa156@gmail.com', 'P', '2002-11-13', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.ravillya', '2020-11-30', 'default', 'User'),\n" +
                "('kom.retno', 'Retno Yunita Putri', 'Retno', 'kom.retnoyunita162@gmail.com', 'P', '2002-06-01', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.retno', '2020-11-30', 'default', 'User'),\n" +
                "('kom.rohma', 'Rohma Darul Istiqomah', 'Rohma', 'kom.rohmadarul5@gmail.com', 'P', '2002-11-29', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.rohma', '2020-11-30', 'default', 'User'),\n" +
                "('kom.rukanah', 'Siti Rukanah', 'Siti Rukanah', 'kom.anarosmala04@gmail.com', 'P', '2001-11-04', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.rukanah', '2020-11-30', 'default', 'User'),\n" +
                "('kom.septi', 'Septi Eka Vera Mulya Sari', 'Septi', 'kom.ekasepti245@gmail.com', 'P', '2003-09-23', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.septi', '2020-11-30', 'default', 'User'),\n" +
                "('kom.setyawati', 'Setyawati', 'Setyawati', 'kom.tyastywt@gmail.com', 'P', '2002-09-10', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.setyawati', '2020-11-30', 'default', 'User'),\n" +
                "('kom.siska', 'Siska Aprilya', 'Siska', 'kom.apriliasiska287@gmail.com', 'P', '2003-04-14', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.siska', '2020-11-30', 'default', 'User'),\n" +
                "('kom.sukma', 'Sukma Meynda Sari', 'Sukma', 'kom.sukmamydd@gmail.com', 'P', '2003-05-06', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.sukma', '2020-11-30', 'default', 'User'),\n" +
                "('kom.ulandari', 'Ulandari Setia Ningrum', 'Ulandari', 'kom.ulandarisetian@gmail.com', 'P', '2002-11-10', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.ulandari', '2020-11-30', 'default', 'User'),\n" +
                "('kom.vanni', 'Vanni Dinda Prameswari', 'Vanni', 'kom.vannidindasutjuan02@gmail.com', 'P', '2002-08-26', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.vanni', '2020-11-30', 'default', 'User'),\n" +
                "('kom.wahyu', 'Rangga Wahyu Irawan', 'Wahyu', 'kom.ranggawahyuirawan1915@gmail.com', 'L', '2003-01-23', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.wahyu', '2020-11-30', 'default', 'User'),\n" +
                "('kom.yessi', 'Yessi Widya Valiana', 'Yessi', 'kom.yesiwidya01@gmail.com', 'P', '2003-04-09', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.yessi', '2020-11-30', 'default', 'User'),\n" +
                "('kom.yoga', 'Yoga Kurniawan Wahyudi', 'Yoga', 'kom.yogak1811@gmail.com', 'L', '2003-05-08', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.yoga', '2020-11-30', 'default', 'User'),\n" +
                "('kom.yovanda', 'Yovanda Alvinan Mahendra', 'Yovanda', 'kom.alvinmofficial@gmail.com', 'L', '2003-01-07', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.yovanda', '2020-11-30', 'default', 'User'),\n" +
                "('kom.yuda', 'Perkasa Yuda Utama', 'Yuda', 'kom.perkasayuda01@gmail.com', 'L', '2002-01-25', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.yuda', '2020-11-30', 'default', 'User'),\n" +
                "('kom.yunita', 'Yunita Wulandari', 'Yunita', 'kom.ywulandari837@gmail.com', 'P', '2002-06-18', 'Software Engineer', 'Jawa Timur', 'Indonesia', 'kom.yunita', '2020-11-30', 'default', 'User'),\n" +
                "('smkn1kts', 'Achmad Baihaqi', 'Baihaqi', 'smkn1kts@gmail.com', 'N', '2003-08-04', 'Tidak Dicantumkan', 'Osaka', 'Jepang', 'rekayasa_perangkat_lunak', '2020-12-13', 'default', 'User'),\n" +
                "('バイハクイ', 'Achmad Baihaqi', 'Baihaqi', 'baihaqi.myapps@gmail.com', 'L', '2003-08-04', 'Android Developer', 'Osaka', 'Japan', '2020-08-04', '2020-12-14', 'default', 'User');";
    }

     /**
     * Akan mengembalikan sebuah query untuk mengosongkan tabel islogin.
     * Karena pada dasarnya tabel islogin kosong saat aplikasi baru pertama kali diinstall
     * 
     * @return query untuk mengisi tabel islogin
     */    
    protected static String getDefaultDataTabel_isLogin(){
        return 
               "TRUNCATE islogin;";
    }
}
 