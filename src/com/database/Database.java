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
     * Attribute yang digunakan untuk menhubungkan Aplikasi ke <B>Database MySQL</B>
     */
    private static final String DRIVER = "com.mysql.jdbc.Driver",
                                DB_NAME = "aisyah",
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
                JOptionPane.showMessageDialog(null, "Klik 'OK' untuk memulihkan Database!", "Error", JOptionPane.WARNING_MESSAGE);
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
            System.out.println("Berhasil memutus koneksi database '" + DB_NAME + "'\n");
            backupDatabase();
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
            
            // Membackup tabel kasuscovid_dunia
            System.out.println("Membackup data kasus Covid-19 Dunia...");
            file = new FileWriter("src\\com\\database\\backup\\BACKUP kasuscovid_dunia.haqi"); // file backup tabel kasuscovid_dunia
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
            file = new FileWriter("src\\com\\database\\backup\\BACKUP kasuscovid_indo.haqi"); // file backup tabel kasuscovid_indo
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
            file = new FileWriter("src\\com\\database\\backup\\BACKUP users.haqi"); // file backup tabel users
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
            file = new FileWriter("src\\com\\database\\backup\\BACKUP islogin.haqi"); // file backup tabel islogin
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
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat membackup database!!\n\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
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
            String filename = "BACKUP " + tabel + ".haqi", data = "", buffer;
            FileReader file = new FileReader("src\\com\\database\\backup\\" + filename); // file backup tabel
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
            String fileBackup = "src\\com\\database\\backup\\BACKUP " + tabel + ".haqi"; // file backup 
            
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
    
    protected static String getPrimary_kasusCovidDunia(){
        return  "ALTER TABLE `kasuscovid_dunia`\n" +
                "  ADD PRIMARY KEY (`negara_idn`,`negara_eng`);";
    }
    
    protected static String getPrimary_kasusCovidIndo(){
        return  "ALTER TABLE `kasuscovid_indo`\n" +
                "  ADD PRIMARY KEY (`kode`,`provinsi`,`website`);";
    }
    
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
                "('Dunia', 'World', 32138014, 982722, 23708311, 7446981, 62385, 781423273, '2020-09-30', 'null', 'default'),\n" +
                "('Afganistan', 'Afghanistan', 39170, 1451, 32619, 5100, 93, 39124705, '2020-09-30', 'Asia', 'bendera-afganistan.jpg'),\n" +
                "('Afrika Selatan', 'South Africa', 667049, 16283, 595916, 54850, 539, 59481669, '2020-09-30', 'Afrika', 'bendera-afrika-selatan.png'),\n" +
                "('Albania', 'Albania', 12787, 370, 7139, 5278, 18, 2877060, '2020-09-30', 'Eropa', 'bendera-albania.jpg'),\n" +
                "('Aljazair', 'Algeria', 50400, 1698, 35428, 13274, 31, 44030062, '2020-09-30', 'Afrika', 'bendera-aljazair.png'),\n" +
                "('Amerika Serikat', 'USA', 7140137, 206598, 4399996, 2533543, 14086, 331452210, '2020-09-30', 'Amerika Utara', 'bendera-amerika-serikat.jpg'),\n" +
                "('Andorra', 'Andorra', 1753, 53, 1203, 497, 4, 77294, '2020-09-30', 'Eropa', 'bendera-andorra.png'),\n" +
                "('Angola', 'Angola', 4475, 162, 1503, 2810, 8, 33093817, '2020-09-30', 'Afrika', 'bendera-angola.gif'),\n" +
                "('Anguilla', 'Anguilla', 3, 0, 3, 0, 0, 15034, '2020-09-30', 'Amerika Utara', 'bendera-anguilla.png'),\n" +
                "('Antigua dan Barbuda', 'Antigua and Barbuda', 97, 3, 92, 2, 0, 98116, '2020-09-30', 'Amerika Utara', 'bendera-antigua-dan-barbuda.png'),\n" +
                "('Arab Saudi', 'Saudi Arabia', 331857, 4599, 314793, 12465, 1090, 34938554, '2020-09-30', 'Asia', 'bendera-arab-saudi.jpg'),\n" +
                "('Argentina', 'Argentina', 664799, 14376, 525486, 124937, 3511, 45291222, '2020-09-30', 'Amerika Selatan', 'bendera-argentina.jpg'),\n" +
                "('Armenia', 'Armenia', 48251, 945, 43266, 4040, 0, 2964535, '2020-09-30', 'Asia', 'bendera-armenia.png'),\n" +
                "('Aruba', 'Aruba', 3721, 25, 2501, 1195, 11, 106871, '2020-09-30', 'Amerika Utara', 'bendera-aruba.png'),\n" +
                "('Australia', 'Australia', 26980, 861, 24448, 1671, 10, 25567653, '2020-09-30', 'Oceania', 'bendera-australia.jpg'),\n" +
                "('Austria', 'Austria', 40816, 783, 31661, 8372, 78, 9018304, '2020-09-30', 'Eropa', 'bendera-austria.jpg'),\n" +
                "('Azerbaijan', 'Azerbaijan', 39686, 581, 37255, 1850, 0, 10160466, '2020-09-30', 'Asia', 'bendera-azerbaijan.png'),\n" +
                "('Bahama', 'Bahamas', 3618, 80, 1915, 1623, 79, 394108, '2020-09-30', 'Amerika Utara', 'bendera-bahama.png'),\n" +
                "('Bahrain', 'Bahrain', 67701, 231, 60853, 6617, 59, 1714632, '2020-09-30', 'Asia', 'bendera-bahrain.png'),\n" +
                "('Bangladesh', 'Bangladesh', 355384, 5072, 265092, 85220, 0, 165070897, '2020-09-30', 'Asia', 'bendera-bangladesh.png'),\n" +
                "('Barbados', 'Barbados', 189, 7, 174, 8, 0, 287457, '2020-09-30', 'Amerika Utara', 'bendera-barbados.png'),\n" +
                "('Belanda', 'Netherlands', 100597, 6296, -1, -1, 104, 17143713, '2020-09-30', 'Eropa', 'bendera-belanda.jpg'),\n" +
                "('Belarus', 'Belarus', 76651, 802, 73733, 2116, 0, 9448595, '2020-09-30', 'Eropa', 'bendera-belarus.png'),\n" +
                "('Belgia', 'Belgium', 106887, 9959, 19079, 77849, 95, 11601339, '2020-09-30', 'Eropa', 'bendera-belgia.jpg'),\n" +
                "('Belize', 'Belize', 1706, 22, 1019, 665, 3, 399260, '2020-09-30', 'Amerika Utara', 'bendera-belize.png'),\n" +
                "('Benin', 'Benin', 2325, 40, 1954, 331, 0, 12194632, '2020-09-30', 'Afrika', 'bendera-benin.png'),\n" +
                "('Bermuda', 'Bermuda', 181, 9, 48, 20, 0, 62224, '2020-09-30', 'Amerika Utara', 'bendera-bermuda.svg.png'),\n" +
                "('Bhutan', 'Bhutan', 261, 0, 196, 65, 0, 773580, '2020-09-30', 'Asia', 'bendera-bhutan.png'),\n" +
                "('Bolivia', 'Bolivia', 131990, 7731, 91556, 32703, 71, 11709345, '2020-09-30', 'Amerika Selatan', 'bendera-bolivia.png'),\n" +
                "('Bosnia dan Herzegovina', 'Bosnia and Herzegovina', 26081, 790, 18634, 6657, 0, 3275988, '2020-09-30', 'Eropa', 'bendera-bosnia-and-herzegovina.jpg'),\n" +
                "('Bostwana', 'Botswana', 2921, 16, 701, 2204, 1, 2362444, '2020-09-30', 'Afrika', 'bendera-botswana.png'),\n" +
                "('Brasil', 'Brazil', 4627780, 139065, 3992886, 495829, 8318, 212908548, '2020-09-30', 'Amerika Selatan', 'bendera-brasil.jpg'),\n" +
                "('Britania Raya', 'UK', 409729, 41862, -1, -1, 211, 67968701, '2020-09-30', 'Eropa', 'bendera-inggris.jpg'),\n" +
                "('Brunei Darussalam', 'Brunei', 146, 3, 142, 1, 0, 438454, '2020-09-30', 'Asia', 'bendera-brunei-darussalam.png'),\n" +
                "('Bulgaria', 'Bulgaria', 19283, 779, 13867, 4637, 29, 6936034, '2020-09-30', 'Eropa', 'bendera-bulgaria.png'),\n" +
                "('Burkina Faso', 'Burkina Faso', 1950, 56, 1260, 634, 0, 21031875, '2020-09-30', 'Afrika', 'bendera-burkino-faso.png'),\n" +
                "('Burundi', 'Burundi', 477, 1, 462, 14, 0, 11969834, '2020-09-30', 'Afrika', 'bendera-burundi.jpg'),\n" +
                "('Cayman Kepulauan', 'Cayman Islands', 210, 1, 205, 4, 0, 65899, '2020-09-30', 'Amerika Utara', 'bendera-cayman_islands.svg.png'),\n" +
                "('Chad', 'Chad', 1171, 82, 1003, 86, 0, 16531324, '2020-09-30', 'Afrika', 'bendera-chad.png'),\n" +
                "('Chili', 'Chile', 449903, 12345, 425165, 12393, 907, 19154009, '2020-09-30', 'Amerika Selatan', 'bendera-chili.jpg'),\n" +
                "('Cina', 'China', 85322, 4634, 80522, 166, 4, 1439323776, '2020-09-30', 'Asia', 'bendera-republik-rakyat-china.jpg'),\n" +
                "('Curaçao', 'Curaçao', 301, 1, 104, 196, 1, 164249, '2020-09-30', 'Amerika Utara', 'bendera-curacao.svg.png'),\n" +
                "('Denmark', 'Denmark', 24916, 645, 18646, 5625, 14, 5796947, '2020-09-30', 'Eropa', 'bendera-denmark.jpg'),\n" +
                "('Djibouti', 'Djibouti', 5407, 61, 5339, 7, 0, 991311, '2020-09-30', 'Afrika', 'bendera-djibouti.png'),\n" +
                "('Dominika', 'Dominica', 24, 0, 18, 6, 0, 72028, '2020-09-30', 'Amerika Utara', 'bendera-dominika.png'),\n" +
                "('Ekuador', 'Ecuador', 129892, 11171, 102852, 15869, 360, 17703980, '2020-09-30', 'Amerika Selatan', 'bendera-ekuador.jpg'),\n" +
                "('El Salvador', 'El Salvador', 28201, 823, 22326, 5052, 89, 6493797, '2020-09-30', 'Amerika Utara', 'bendera-el-salvador.png'),\n" +
                "('Eritrea', 'Eritrea', 369, 0, 330, 39, 0, 3557748, '2020-09-30', 'Afrika', 'bendera-eritrea.jpg'),\n" +
                "('Estonia', 'Estonia', 3076, 64, 2395, 617, 2, 1326744, '2020-09-30', 'Eropa', 'bendera-estonia.png'),\n" +
                "('Ethiopia', 'Ethiopia', 71687, 1148, 29461, 41078, 267, 115606131, '2020-09-30', 'Afrika', 'bendera-etiopia.png'),\n" +
                "('Fiji', 'Fiji', 32, 2, 28, 2, 1, 897946, '2020-09-30', 'Oceania', 'bendera-fiji.png'),\n" +
                "('Filipina', 'Philippines', 296755, 5127, 231928, 59700, 1758, 109918002, '2020-09-30', 'Asia', 'bendera-filipina1.jpg'),\n" +
                "('Finlandia', 'Finland', 9379, 343, 7850, 1186, 4, 5542730, '2020-09-30', 'Eropa', 'bendera-finlandia.png'),\n" +
                "('Gabon', 'Gabon', 8716, 54, 7906, 756, 2, 2237614, '2020-09-30', 'Afrika', 'bendera-gabon.png'),\n" +
                "('Gambia', 'Gambia', 3552, 110, 2012, 1430, 0, 2431879, '2020-09-30', 'Afrika', 'bendera-gambia.png'),\n" +
                "('Georgia', 'Georgia', 4399, 26, 1705, 2668, 0, 3987347, '2020-09-30', 'Asia', 'bendera-georgia.png'),\n" +
                "('Ghana', 'Ghana', 46222, 299, 45417, 506, 6, 31220495, '2020-09-30', 'Afrika', 'bendera-ghana.jpg'),\n" +
                "('Gibraltar', 'Gibraltar', 361, 0, 331, 30, 0, 33689, '2020-09-30', 'Eropa', 'bendera-gibraltar.svg.png'),\n" +
                "('Greenland', 'Greenland', 14, 0, 14, 0, 0, 56793, '2020-09-30', 'Amerika Utara', 'bendera-greenland.svg.png'),\n" +
                "('Grenada', 'Grenada', 24, 0, 24, 0, 0, 56793, '2020-09-30', 'Amerika Utara', 'bendera-grenada.png'),\n" +
                "('Guadeloupe', 'Guadeloupe', 4487, 42, 2199, 2246, 24, 400140, '2020-09-30', 'Amerika Utara', 'bendera-guadeloupe.svg.png'),\n" +
                "('Guatemala', 'Guatemala', 87442, 3154, 76459, 7829, 5, 17990424, '2020-09-30', 'Amerika Utara', 'bendera-guatemala.jpg'),\n" +
                "('Guinea', 'Guinea', 10434, 65, 9801, 568, 24, 13212769, '2020-09-30', 'Afrika', 'bendera-guinea.png'),\n" +
                "('Guinea Khatulistiwa', 'Equatorial Guinea', 5018, 83, 4530, 405, 4, 1413204, '2020-09-30', 'Afrika', 'bendera-guinea-khatulistiwa.png'),\n" +
                "('Guinea-Bissau', 'Guinea-Bissau', 2324, 39, 1549, 736, 5, 1978522, '2020-09-30', 'Afrika', 'bendera-guinea-bissau.png'),\n" +
                "('Guyana', 'Guyana', 2535, 69, 1464, 1002, 14, 787433, '2020-09-30', 'Amerika Selatan', 'bendera-guyana.png'),\n" +
                "('Guyana Prancis', 'French Guiana', 9762, 65, 9431, 266, 6, 300403, '2020-09-30', 'Amerika Selatan', 'bendera-guyana.png'),\n" +
                "('Haiti', 'Haiti', 8646, 225, 6551, 1870, 0, 11434330, '2020-09-30', 'Amerika Utara', 'bendera-haiti.png'),\n" +
                "('Honduras', 'Honduras', 72675, 2222, 24022, 46431, 20, 9940379, '2020-09-30', 'Amerika Utara', 'bendera-honduras.jpg'),\n" +
                "('Hong Kong', 'Hong Kong', 5057, 104, 4758, 195, 13, 7511174, '2020-09-30', 'Asia', 'bendera-hong_kong.svg.png'),\n" +
                "('Hungaria', 'Hungary', 21200, 709, 4818, 15673, 32, 9654581, '2020-09-30', 'Eropa', 'bendera-hongaria.jpg'),\n" +
                "('India', 'India', 5816103, 92317, 4752991, 970795, 8944, 1383159972, '2020-09-30', 'Asia', 'bendera-india.png'),\n" +
                "('Indonesia', 'Indonesia', 262022, 10105, 191853, 60064, -1, 274195355, '2020-09-30', 'Asia', 'bendera-indonesia.jpg'),\n" +
                "('Iran', 'Iran', 436319, 25015, 367829, 43475, 3957, 84241549, '2020-09-30', 'Asia', 'bendera-iran.jpg'),\n" +
                "('Iraq', 'Iraq', 337106, 8799, 268761, 59546, 527, 40427156, '2020-09-30', 'Asia', 'bendera-irak.jpg'),\n" +
                "('Irlandia', 'Ireland', 33675, 1794, 23364, 8517, 16, 4950431, '2020-09-30', 'Eropa', 'bendera-irlandia.png'),\n" +
                "('Islandia', 'Iceland', 2512, 10, 2150, 352, 0, 341755, '2020-09-30', 'Eropa', 'bendera-islandia.jpg'),\n" +
                "('Israel', 'Israel', 214458, 1378, 152294, 60786, 669, 9197590, '2020-09-30', 'Asia', 'bendera-palestina.png'),\n" +
                "('Italia', 'Italy', 302537, 35758, 220665, 46114, 244, 60440954, '2020-09-30', 'Eropa', 'bendera-italia.jpg'),\n" +
                "('Jamaika', 'Jamaica', 5395, 76, 1444, 3875, 7, 2964169, '2020-09-30', 'Amerika Utara', 'bendera-jamaika.png'),\n" +
                "('Jepang', 'Japan', 80041, 1520, 72538, 5983, 166, 126384252, '2020-09-30', 'Asia', 'bendera-jepang.jpg'),\n" +
                "('Jerman', 'Germany', 279205, 9508, 249500, 20197, 293, 83846303, '2020-09-30', 'Eropa', 'bendera-jerman.jpg'),\n" +
                "('Kaledonia Baru', 'New Caledonia', 26, 0, 26, 0, 0, 286129, '2020-09-30', 'Oceania', 'bendera-new caledonia.png'),\n" +
                "('Kamboja', 'Cambodia', 275, 0, 274, 1, 0, 16772360, '2020-09-30', 'Asia', 'bendera-kamboja.jpg'),\n" +
                "('Kamerun', 'Cameroon', 20712, 418, 19440, 854, 30, 26694924, '2020-09-30', 'Afrika', 'bendera-kameron.jpg'),\n" +
                "('Kanada', 'Canada', 147756, 9243, 127788, 10725, 86, 37818373, '2020-09-30', 'Amerika Utara', 'bendera-kanada.jpg'),\n" +
                "('Karibia Belanda', 'Caribbean Netherlands', 69, 1, 21, 47, 0, 26279, '2020-09-30', 'Amerika Utara', 'bendera-carribean-netherlands.svg.png'),\n" +
                "('Kazakhstan', 'Kazakhstan', 107590, 1699, 102360, 3531, 221, 18828728, '2020-09-30', 'Asia', 'bendera-kazakhstan.jpg'),\n" +
                "('Kenya', 'Kenya', 37489, 669, 24334, 12486, 44, 54040099, '2020-09-30', 'Afrika', 'bendera-kenya.png'),\n" +
                "('Kepulauan Channel', 'Channel Islands', 654, 48, 575, 31, 2, 174232, '2020-09-30', 'Eropa', 'bendera-channel island.png'),\n" +
                "('Kepulauan Falkland', 'Falkland Islands', 13, 0, 13, 0, 0, 3502, '2020-09-30', 'Amerika Selatan', 'bendera-falkland_islands.svg.png'),\n" +
                "('Kepulauan Foroe', 'Faeroe Islands', 451, 0, 416, 35, 0, 48906, '2020-09-30', 'Eropa', 'bendera-faroe_islands.svg.png'),\n" +
                "('Kepulauan Turks dan Caicos', 'Turks and Caicos', 676, 5, 588, 83, 3, 38837, '2020-09-30', 'Amerika Utara', 'bendera-turks_and_caicos_islands.svg.png'),\n" +
                "('Kepulauan Virgin Britania Raya', 'British Virgin Islands', 69, 1, 48, 20, 2, 30278, '2020-09-30', 'Amerika Utara', 'bendera-british_virgin_islands.svg.png'),\n" +
                "('Kirgizstan', 'Kyrgyzstan', 45932, 1063, 42147, 2722, 24, 6548902, '2020-09-30', 'Asia', 'bendera-kirgizstan.png'),\n" +
                "('Kolombia', 'Colombia', 784268, 24746, 662277, 97245, 863, 51007347, '2020-09-30', 'Amerika Selatan', 'bendera-kolumbia.png'),\n" +
                "('Komoro', 'Comoros', 474, 7, 453, 14, 0, 873811, '2020-09-30', 'Afrika', 'bendera-komoro.png'),\n" +
                "('Kongo', 'Congo', 5005, 89, 3887, 1029, 0, 5548744, '2020-09-30', 'Afrika', 'bendera-republik-kongo.png'),\n" +
                "('Korea Selatan', 'North Korea', 0, 0, 0, 0, 0, -1, '2020-11-15', 'Asia', 'default'),\n" +
                "('Korea Selatan', 'S. Korea', 23455, 395, 20978, 2082, 128, 51279620, '2020-09-30', 'Asia', 'bendera-korea.jpg'),\n" +
                "('Kosta Rika', 'Costa Rica', 68059, 781, 26136, 41142, 249, 5104825, '2020-09-30', 'Amerika Utara', 'bendera-kostarika.jpg'),\n" +
                "('Kroasia', 'Croatia', 15572, 261, 14111, 1200, 24, 4099274, '2020-09-30', 'Eropa', 'bendera-krosia.jpg'),\n" +
                "('Kuba', 'Cuba', 5270, 118, 4582, 570, 8, 11324995, '2020-09-30', 'Amerika Utara', 'bendera-kuba.png'),\n" +
                "('Kuwait', 'Kuwait', 101851, 592, 92961, 8298, 101, 4285118, '2020-09-30', 'Asia', 'bendera-kuwait.jpg'),\n" +
                "('Laos', 'Laos', 23, 0, 22, 1, 0, 651378, '2020-09-30', 'Asia', 'bendera-laos.jpg'),\n" +
                "('Latvia', 'Latvia', 1594, 36, 1248, 310, 0, 1881220, '2020-09-30', 'Eropa', 'bendera-latvia.png'),\n" +
                "('Lebanon', 'Lebanon', 32819, 329, 14112, 18378, 139, 6818148, '2020-09-30', 'Asia', 'bendera-lebanon.png'),\n" +
                "('Lesotho', 'Lesotho', 1558, 35, 797, 726, 0, 2146213, '2020-09-30', 'Afrika', 'bendera-lesotho.png'),\n" +
                "('Liberia', 'Liberia', 1338, 82, 1221, 35, 0, 5084576, '2020-09-30', 'Afrika', 'bendera-liberia.jpg'),\n" +
                "('Libya', 'Libya', 30632, 474, 16842, 13316, 0, 6892864, '2020-09-30', 'Afrika', 'bendera-libya.jpg'),\n" +
                "('Liechtenstein', 'Liechtenstein', 116, 1, 110, 5, 0, 38153, '2020-09-30', 'Eropa', 'bendera-liechtenstein.jpg'),\n" +
                "('Lituania', 'Lithuania', 4070, 89, 2253, 1728, 0, 2713177, '2020-09-30', 'Eropa', 'bendera-lithuania.png'),\n" +
                "('Luksemburg', 'Luxembourg', 8090, 124, 6862, 1104, 1, 628289, '2020-09-30', 'Eropa', 'bendera-luxembourg.jpg'),\n" +
                "('Macedonia Utara', 'North Macedonia', 17049, 710, 14186, 2153, 3, 2083354, '2020-09-30', 'Eropa', 'bendera-north_macedonia.svg.png'),\n" +
                "('Madagaskar', 'Madagascar', 16221, 228, 14867, 1126, 11, 27851325, '2020-09-30', 'Afrika', 'bendera-madagascar.jpg'),\n" +
                "('Makau', 'Macao', 46, 0, 46, 0, 0, 651378, '2020-09-30', 'Asia', 'bendera-macau.svg.png'),\n" +
                "('Maladewa', 'Maldives', 9939, 34, 8597, 1308, 12, 542724, '2020-09-30', 'Asia', 'bendera-maladewa.jpg'),\n" +
                "('Malawi', 'Malawi', 5747, 179, 4163, 1405, 4, 19241237, '2020-09-30', 'Afrika', 'bendera-malawi.jpg'),\n" +
                "('Malaysia', 'Malaysia', 10576, 133, 9666, 777, 6, 32461889, '2020-09-30', 'Asia', 'bendera-malaysia1.jpg'),\n" +
                "('Mali', 'Mali', 3041, 130, 2391, 520, 0, 20381309, '2020-09-30', 'Afrika', 'bendera-mali.png'),\n" +
                "('Malta', 'Malta', 2898, 27, 2191, 680, 0, 441817, '2020-09-30', 'Eropa', 'bendera-malta.jpg'),\n" +
                "('Maroko', 'Morocco', 110099, 1956, 90186, 17957, 289, 37011915, '2020-09-30', 'Afrika', 'bendera-maroko.jpg'),\n" +
                "('Martinik', 'Martinique', 1290, 20, 98, 1172, 9, 375197, '2020-09-30', 'Amerika Utara', 'bendera-martinique.svg.png'),\n" +
                "('Mauritania', 'Mauritania', 7433, 161, 7052, 220, 3, 4677146, '2020-09-30', 'Afrika', 'bendera-mauritania.png'),\n" +
                "('Mauritius', 'Mauritius', 367, 10, 343, 14, 0, 1272266, '2020-09-30', 'Afrika', 'bendera-mauritius.png'),\n" +
                "('Mayotte', 'Mayotte', 3541, 40, 2964, 537, 2, 274302, '2020-09-30', 'Afrika', 'bendera-mayotte.png'),\n" +
                "('Meksiko', 'Mexico', 710049, 74949, 510237, 124863, 2677, 129243704, '2020-09-30', 'Amerika Utara', 'bendera-meksiko.jpg'),\n" +
                "('Mesir', 'Egypt', 102513, 5835, 92644, 4034, 41, 102775319, '2020-09-30', 'Afrika', 'bendera-mesir.png'),\n" +
                "('Moldova', 'Moldova', 48232, 1244, 36071, 10917, 564, 4031759, '2020-09-30', 'Eropa', 'bendera-moldova.png'),\n" +
                "('Monako', 'Monaco', 199, 1, 165, 33, 1, 39306, '2020-09-30', 'Eropa', 'bendera-monako.png'),\n" +
                "('Mongolia', 'Mongolia', 313, 0, 303, 10, 1, 3290418, '2020-09-30', 'Asia', 'bendera-mongolia.png'),\n" +
                "('Montenegro', 'Montenegro', 9428, 151, 5728, 3549, 0, 628085, '2020-09-30', 'Eropa', 'bendera-montenegro.png'),\n" +
                "('Montserrat', 'Montserrat', 13, 1, 12, 0, 0, 4993, '2020-09-30', 'Amerika Utara', 'bendera-montserrat.svg.png'),\n" +
                "('Mozambik', 'Mozambique', 7399, 51, 4558, 2790, 0, 31451650, '2020-09-30', 'Afrika', 'bendera-mozambik.jpg'),\n" +
                "('Myanmar', 'Myanmar', 8515, 155, 2381, 5979, 0, 54495142, '2020-09-30', 'Asia', 'bendera-myanmar.jpg'),\n" +
                "('Namibia', 'Namibia', 10740, 119, 8482, 2139, 15, 2551433, '2020-09-30', 'Afrika', 'bendera-namibia.png'),\n" +
                "('Nepal', 'Nepal', 69301, 453, 50411, 18437, 0, 29256737, '2020-09-30', 'Asia', 'bendera-nepal.png'),\n" +
                "('Niger', 'Niger', 1194, 69, 1107, 18, 0, 24399460, '2020-09-30', 'Afrika', 'bendera-niger.jpg'),\n" +
                "('Nigeria', 'Nigeria', 57849, 1102, 49098, 7649, 7, 207292385, '2020-09-30', 'Afrika', 'bendera-nigeria.jpg'),\n" +
                "('Nikaragua', 'Nicaragua', 5073, 149, 2913, 2011, 0, 6642596, '2020-09-30', 'Amerika Utara', 'bendera-nikaragua.png'),\n" +
                "('Norwegia', 'Norway', 13277, 267, 10371, 2639, 2, 5431023, '2020-09-30', 'Eropa', 'bendera-norwegia.jpg'),\n" +
                "('Oman', 'Oman', 95907, 885, 86765, 8257, 185, 5135890, '2020-09-30', 'Asia', 'bendera-oman.png'),\n" +
                "('Pakistan', 'Pakistan', 309015, 6444, 294740, 7831, 544, 221871028, '2020-09-30', 'Asia', 'bendera-pakistan.png'),\n" +
                "('Palestina', 'Palestine', 37591, 274, 26934, 10383, 0, 5128259, '2020-09-30', 'Asia', 'bendera-palestina.png'),\n" +
                "('Panama', 'Panama', 107990, 2291, 84437, 21262, 122, 4330196, '2020-09-30', 'Amerika Utara', 'bendera-panama.jpg'),\n" +
                "('Pantai Gading', 'Ivory Coast', 19501, 120, 19003, 378, 0, 26525664, '2020-09-30', 'Afrika', 'bendera-pantai-gading.jpg'),\n" +
                "('Papua Nugini', 'Papua New Guinea', 527, 7, 232, 288, 0, 8985270, '2020-09-30', 'Oceania', 'bendera-papua-nugini.jpg'),\n" +
                "('Paraguay', 'Paraguay', 35571, 727, 19867, 14977, 157, 7152579, '2020-09-30', 'Amerika Selatan', 'bendera-paraguay.png'),\n" +
                "('Peru', 'Peru', 782695, 31870, 636489, 114336, 1381, 33076571, '2020-09-30', 'Amerika Selatan', 'bendera-peru.gif'),\n" +
                "('Polandia', 'Poland', 82809, 2369, 66158, 14282, 91, 37836886, '2020-09-30', 'Eropa', 'bendera-polandia.jpg'),\n" +
                "('Polinesia Prancis', 'French Polynesia', 1469, 5, 1237, 227, 5, 281284, '2020-09-30', 'Oceania', 'bendera-french_polynesia.svg.png'),\n" +
                "('Portugal', 'Portugal', 70465, 1928, 46290, 18586, 506, 19207285, '2020-09-30', 'Eropa', 'bendera-portugal.jpg'),\n" +
                "('Prancis', 'France', 481141, 31459, 93538, 356144, 951, 65307193, '2020-09-30', 'Eropa', 'bendera-perancis.png'),\n" +
                "('Pulau Man', 'Isle of Man', 340, 24, 312, 4, 0, 85137, '2020-09-30', 'Eropa', 'bendera-isle_of_man.svg.png'),\n" +
                "('Qatar', 'Qatar', 124425, 212, 121263, 2950, 62, 2807805, '2020-09-30', 'Asia', 'bendera-qatar.jpg'),\n" +
                "('Republik Afrika Tengah', 'CAR', 4804, 62, 1837, 2905, 2, 4849008, '2020-09-30', 'Afrika', 'bendera-republik-afrika-tengah.png'),\n" +
                "('Republik Ceko', 'Czechia', 55464, 555, 26709, 28200, 120, 10713617, '2020-09-30', 'Eropa', 'bendera-republik-ceko.jpg'),\n" +
                "('Republik Demokratik Kongo', 'DRC', 10555, 271, 10051, 233, 0, 90168360, '2020-09-30', 'Afrika', 'bendera-demokratik-republik-kongo.jpg'),\n" +
                "('Republik Dominika', 'Dominican Republic', 109737, 2074, 83434, 24229, 208, 10872904, '2020-09-30', 'Amerika Utara', 'bendera-republik-dominika.png'),\n" +
                "('Réunion', 'Réunion', 3501, 11, 2482, 1008, 0, 896806, '2020-09-30', 'Afrika', 'bendera-reounion.png'),\n" +
                "('Romania', 'Romania', 118054, 4591, 94877, 18586, 506, 19207285, '2020-09-30', 'Eropa', 'bendera-rumania.jpg'),\n" +
                "('Rusia', 'Russia', 1128836, 19948, 929829, 179059, 2300, 145949102, '2020-09-30', 'Eropa', 'bendera-rusia.jpg'),\n" +
                "('Rwanda', 'Rwanda', 4789, 27, 3050, 1712, 0, 13024661, '2020-09-30', 'Afrika', 'bendera-rwanda.png'),\n" +
                "('Sahara Barat', 'Western Sahara', 10, 1, 8, 1, 0, 600654, '2020-09-30', 'Afrika', 'bendera-sahara barat.png'),\n" +
                "('Saint Barthélemy', 'St. Barth', 45, 0, 25, 20, 0, 9884, '2020-09-30', 'Amerika Utara', 'bendera-saint_barthelemy_(local).svg.png'),\n" +
                "('Saint Kitts dan Nevis', 'Saint Kitts and Nevis', 19, 0, 17, 2, 0, 53286, '2020-09-30', 'Amerika Utara', 'bendera-saint-kitts-dan-nevis.jpg'),\n" +
                "('Saint Lucia', 'Saint Lucia', 27, 0, 26, 1, 0, 183822, '2020-09-30', 'Amerika Utara', 'bendera-saint-lucia.png'),\n" +
                "('Saint Martin', 'Saint Martin', 367, 8, 273, 86, 11, 38815, '2020-09-30', 'Amerika Utara', 'bendera-saint-vincent-and-the-grenadines.png'),\n" +
                "('Saint Pierre dan Miquelon', 'Saint Pierre Miquelon', 16, 0, 6, 10, 0, 5787, '2020-09-30', 'Amerika Utara', 'bendera-saint-pierre_and_miquelon.svg.png'),\n" +
                "('Saint Vincent dan Grenadines', 'St. Vincent Grenadines', 64, 0, 64, 0, 0, 111022, '2020-09-30', 'Amerika Utara', 'bendera-sint_maarten.svg.png'),\n" +
                "('San Marino', 'San Marino', 723, 42, 669, 12, 3, 33948, '2020-09-30', 'Eropa', 'bendera-san-marino.jpg'),\n" +
                "('Sao Tome dan Principe', 'Sao Tome and Principe', 910, 15, 881, 14, 0, 220089, '2020-09-30', 'Afrika', 'bendera-sao-tome-and-principe.png'),\n" +
                "('Selandia Baru', 'New Zealand', 1827, 25, 1737, 65, 3, 5002100, '2020-09-30', 'Oceania', 'bendera-selandia-baru-new-zealand.png'),\n" +
                "('Senegal', 'Senegal', 14816, 304, 11818, 2694, 24, 16843152, '2020-09-30', 'Afrika', 'bendera-senegal.png'),\n" +
                "('Serbia', 'Serbia', 33080, 744, 31536, 800, 22, 8729071, '2020-09-30', 'Eropa', 'bendera-serbia.jpg'),\n" +
                "('Seychelles', 'Seychelles', 143, 0, 140, 3, 0, 98490, '2020-09-30', 'Afrika', 'bendera-seychelles.png'),\n" +
                "('Sierra Leone', 'Sierra Leone', 2188, 72, 1666, 450, 0, 8013928, '2020-09-30', 'Afrika', 'bendera-sierra-leone.png'),\n" +
                "('Singapura', 'Singapore', 57654, 27, 57333, 294, 0, 5861084, '2020-09-30', 'Asia', 'bendera-singapura.jpg'),\n" +
                "('Sint Maarten (Prancis)', 'Sint Maarten', 616, 21, 517, 78, 6, 42988, '2020-09-30', 'Amerika Utara', 'bendera-sint_maarten.svg.png'),\n" +
                "('Siprus', 'Cyprus', 1663, 22, 1369, 272, 1, 1209413, '2020-09-30', 'Asia', 'bendera-siprus.png'),\n" +
                "('Slovenia', 'Slovenia', 4694, 143, 3168, 1383, 20, 2079005, '2020-09-30', 'Eropa', 'bendera-slovenia.png'),\n" +
                "('Slowakia', 'Slovakia', 7629, 41, 3978, 3610, 25, 5460261, '2020-09-30', 'Eropa', 'bendera-slowakia.jpg'),\n" +
                "('Somalia', 'Somalia', 3465, 98, 2877, 490, 0, 15992604, '2020-09-30', 'Afrika', 'bendera-somalia.png'),\n" +
                "('Spanyol', 'Spain', 693556, 31034, -1, -1, 1436, 46759015, '2020-09-30', 'Eropa', 'bendera-spanyol.jpg'),\n" +
                "('Sri Lanka', 'Sri Lanka', 3333, 13, 3142, 178, 0, 21434354, '2020-09-30', 'Asia', 'bendera-srilanka.jpg'),\n" +
                "('Sudan', 'Sudan', 13592, 836, 6764, 5992, 0, 44080967, '2020-09-30', 'Afrika', 'bendera-sudan.png'),\n" +
                "('Sudan Selatan', 'South Sudan', 2669, 49, 1290, 1330, 0, 11224136, '2020-09-30', 'Afrika', 'bendera-sudan-selatan.png'),\n" +
                "('Suriah', 'Syria', 3966, 183, 1013, 2770, 0, 17596682, '2020-09-30', 'Asia', 'bendera-suriah.png'),\n" +
                "('Suriname', 'Suriname', 4779, 101, 4560, 118, 5, 587842, '2020-09-30', 'Amerika Selatan', 'bendera-suriname.png'),\n" +
                "('Swaziland', 'Eswatini', 5375, 108, 4724, 543, 11, 1162955, '2020-09-30', 'Afrika', 'bendera-swaziland.png'),\n" +
                "('Swedia', 'Sweden', 89756, 5876, -1, -1, 15, 10113840, '2020-09-30', 'Eropa', 'bendera-swedia.jpg'),\n" +
                "('Swiss', 'Switzerland', 51492, 2061, 42300, 7131, 34, 8669241, '2020-09-30', 'Eropa', 'bendera-swiss.gif'),\n" +
                "('Taiwan', 'Taiwan', 509, 7, 480, 22, 0, 23826952, '2020-09-30', 'Asia', 'bendera-taiwan.png'),\n" +
                "('Tajikistan', 'Tajikistan', 9520, 74, 8296, 1150, 0, 9586218, '2020-09-30', 'Asia', 'bendera-tajikistan.png'),\n" +
                "('Tanjung Verde', 'Cabo Verde', 5479, 55, 4917, 507, 0, 557389, '2020-09-30', 'Afrika', 'bendera-tanjung-verde.png'),\n" +
                "('Tanzania', 'Tanzania', 509, 21, 183, 305, 7, 60115081, '2020-09-30', 'Afrika', 'bendera-tanzania.png'),\n" +
                "('Thailand', 'Thailand', 3519, 59, 3360, 100, 1, 69841277, '2020-09-30', 'Asia', 'bendera-thailand.jpg'),\n" +
                "('Timor Leste', 'Timor-Leste', 27, 0, 27, 0, 0, 1324179, '2020-09-30', 'Asia', 'bendera-timor-leste.png'),\n" +
                "('Togo', 'Togo', 1707, 180, 5032, 8093, 74, 11847346, '2020-09-30', 'Afrika', 'bendera-togo.png'),\n" +
                "('Trinidad dan Tobago', 'Trinidad and Tobago', 4136, 67, 1960, 2109, 11, 1400543, '2020-09-30', 'Amerika Utara', 'bendera-trinidad-and-tobago.png'),\n" +
                "('Tunisia', 'Tunisia', 13305, 180, 5032, 8093, 74, 11847346, '2020-09-30', 'Afrika', 'bendera-tunisia.jpg'),\n" +
                "('Turki', 'Turkey', 309790, 7785, 271964, 30041, 1573, 84549763, '2020-09-30', 'Asia', 'bendera-turki.jpg'),\n" +
                "('Uganda', 'Uganda', 7218, 71, 3611, 3536, 0, 46062181, '2020-09-30', 'Afrika', 'bendera-uganda.png'),\n" +
                "('Ukraina', 'Ukraine', 188106, 3757, 83458, 100891, 177, 43671581, '2020-09-30', 'Eropa', 'bendera-ukraina.jpg'),\n" +
                "('Uni Emirat Arab', 'UAE', 88532, 407, 77937, 10188, 0, 9918074, '2020-09-30', 'Asia', 'bendera-uni-emirate-arab.png'),\n" +
                "('Uruguay', 'Uruguay', 1946, 47, 1661, 238, 2, 3476531, '2020-09-30', 'Amerika Selatan', 'bendera-uruguay.jpg'),\n" +
                "('Uzbekistan', 'Uzbekistan', 53966, 447, 50441, 3078, 283, 33580993, '2020-09-30', 'Asia', 'bendera-uzbekistan.png'),\n" +
                "('Vatikan', 'Vatican City', 12, 0, 12, 0, 0, 801, '2020-09-30', 'Eropa', 'bendera-vatikan.jpg'),\n" +
                "('Venezuela', 'Venezuela', 69439, 574, 58759, 10106, 146, 28416979, '2020-09-30', 'Amerika Selatan', 'bendera-venezuela.jpg'),\n" +
                "('Vietnam', 'Vietnam', 1069, 35, 991, 43, 0, 97542604, '2020-09-30', 'Asia', 'bendera-vietnam.jpg'),\n" +
                "('Yaman', 'Yemen', 2029, 586, 1250, 193, 0, 29975045, '2020-09-30', 'Asia', 'bendera-yaman.png'),\n" +
                "('Yordania', 'Jordan', 6591, 36, 3937, 2618, 13, 10226688, '2020-09-30', 'Asia', 'bendera-yordania.png'),\n" +
                "('Yunani', 'Greece', 16286, 357, 9989, 5940, 73, 10411029, '2020-09-30', 'Eropa', 'bendera-yunani.jpg'),\n" +
                "('Zambia', 'Zambia', 14491, 332, 13643, 516, 0, 18499324, '2020-09-30', 'Afrika', 'bendera-zambia.png'),\n" +
                "('Zimbabwe', 'Zimbabwe', 7752, 227, 6043, 1482, 0, 14912782, '2020-09-30', 'Afrika', 'bendera-zimbabwe1.jpg');";
    }
    
     /**
     * Akan mengembalikan sebuah query untuk mengisi tabel kasuscovid_indo jika tabel tersebut kosong.
     * 
     * @return query untuk mengisi tabel kasuscovid_indo
     */
    protected static String getDefaultDataTabel_kasusCovidIndo(){
        return  "INSERT INTO `kasuscovid_indo` (`kode`, `provinsi`, `kasus`, `sembuh`, `kematian`, `aktif`, `populasi`, `total_kab`, `kab_zonamerah`, `kab_zonaoranye`, `kab_zonahijau`, `kasus_pertama`, `diubah`, `website`, `lambang`) VALUES\n" +
                "('Aceh', 'Aceh', 8244, 6580, 318, 1346, 5189500, 18, 0, 0, 0, '2020-03-26', '2020-11-27', 'covid19.acehprov.go.id', 'lambang-aceh.png'),\n" +
                "('Babel', 'Kep. Bangka Belitung', 944, 784, 12, 148, 1430900, 6, 0, 0, 0, '2020-03-30', '2020-11-27', 'covid19.babelprov.go.id', 'lambang-bangka-belitung.png'),\n" +
                "('Bali', 'Bali', 13500, 12267, 419, 814, 4246500, 8, 0, 0, 0, '2020-03-10', '2020-11-27', 'infocorona.baliprov.go.id', 'lambang-bali.png'),\n" +
                "('Banten', 'Banten', 12223, 8396, 318, 3509, 12448200, 4, 0, 0, 0, '2020-03-08', '2020-11-27', 'infocorona.bantenprov.go.id', 'lambang-banten.png'),\n" +
                "('Bengkulu', 'Bengkulu', 1665, 1287, 73, 305, 1934300, 9, 0, 0, 0, '2020-03-31', '2020-11-27', 'covid19.bengkuluprov.go.id', 'lambang-bengkulu.png'),\n" +
                "('DIY', 'DI Yogyakarta', 5556, 4200, 137, 1219, 3762200, 4, 0, 0, 0, '2020-03-13', '2020-11-27', 'corona.jogjaprov.go.id', 'lambang-yogyakarta.png'),\n" +
                "('Gorontalo', 'Gorontalo', 3073, 2956, 89, 28, 1168200, 5, 0, 0, 0, '2020-04-09', '2020-11-27', 'covid-19.gorontaloprov.go.id', 'lambang-gorontalo.jpg'),\n" +
                "('Jabar', 'Jawa Barat', 50482, 41932, 882, 7668, 48037600, 18, 0, 0, 0, '2020-03-02', '2020-11-27', 'pikobar.jabarprov.go.id', 'lambang-jawa-barat.png'),\n" +
                "('Jakarta', 'DKI Jakarta', 131525, 120194, 2592, 8739, 10374200, 1, 0, 0, 0, '2020-03-02', '2020-11-27', 'corona.jakarta.go.id', 'lambang-jakarta.png'),\n" +
                "('Jambi', 'Jambi', 1774, 1263, 36, 475, 3515000, 9, 0, 0, 0, '2020-03-23', '2020-11-27', 'corona.jambiprov.go.id', 'lambang-jambi.jpg'),\n" +
                "('Jateng', 'Jawa Tengah', 50880, 36681, 2197, 12002, 34257900, 29, 0, 0, 0, '2020-03-09', '2020-11-27', 'corona.jatengprov.go.id', 'lambang-jawa-tengah.png'),\n" +
                "('Jatim', 'Jawa Timur', 60190, 53131, 4275, 2784, 39293000, 29, 0, 0, 0, '2020-03-17', '2020-11-27', 'infocovid19.jatimprov.go.id', 'lambang-jawa-timur.png'),\n" +
                "('Kalbar', 'Kalimanan Barat', 2322, 1825, 22, 475, 4932500, 12, 0, 0, 0, '2020-03-10', '2020-11-27', 'covid19.kalbarprov.go.id', 'lambang-kalimantan-barat.png'),\n" +
                "('Kalsel', 'Kalimantan Selatan', 13047, 11859, 522, 666, 4119800, 11, 0, 0, 0, '2020-03-22', '2020-11-27', 'corona.kalselprov.go.id', 'lambang-kalimantan-selatan.gif'),\n" +
                "('Kaltara', 'Kalimantan Utara', 1320, 921, 17, 382, 691100, 4, 0, 0, 0, '2020-03-28', '2020-11-27', 'coronainfo.kaltaraprov.go.id', 'lambang-kalimantan-utara.png'),\n" +
                "('Kalteng', 'Kalimantan Tengah', 5603, 4526, 185, 892, 2605300, 13, 0, 0, 0, '2020-03-20', '2020-11-27', 'corona.kalteng.go.id', 'lambang-kalimantan-tengah.png'),\n" +
                "('Kaltim', 'Kalimantan Timur', 18916, 16255, 577, 2084, 3575400, 7, 0, 0, 0, '2020-03-18', '2020-11-17', 'covid19.kaltimprov.go.id', 'lambang-kalimantan-timur.png'),\n" +
                "('Kepri', 'Kepulauan Riau', 5304, 3998, 133, 1173, 2082700, 5, 0, 0, 0, '2020-03-17', '2020-11-27', 'corona.kepriprov.go.id', 'lambang-kepulauan-riau.png'),\n" +
                "('Lampung', 'Lampung', 3340, 1757, 135, 1448, 8289600, 13, 0, 0, 0, '2020-03-18', '2020-11-27', 'covid19.lampungprov.go.id', 'lambang-lampung.png'),\n" +
                "('Maluku', 'Maluku', 4428, 3755, 60, 613, 1744700, 9, 0, 0, 0, '2020-03-22', '2020-11-17', 'corona.malukuprov.go.id', 'lambang-maluku.png'),\n" +
                "('Malut', 'Maluku Utara', 2356, 1971, 81, 304, 1209300, 8, 0, 0, 0, '2020-03-23', '2020-11-27', 'corona.malutprov.go.id', 'lambang-maluku-utara.png'),\n" +
                "('NTB', 'Nusa Tenggara Barat', 4577, 3687, 237, 653, 4955600, 8, 0, 0, 0, '2020-03-24', '2020-11-27', 'corona.ntbprov.go.id', 'lambang-nusa-tenggara-barat.png'),\n" +
                "('NTT', 'Nusa Tenggara Timur', 1077, 663, 17, 397, 5287300, 21, 0, 0, 0, '2020-04-09', '2020-11-27', 'covid19.nttprov.go.id', 'lambang-nusa-tenggara-timur.png'),\n" +
                "('Pabar', 'Papua Barat', 5204, 4513, 82, 609, 915400, 12, 0, 0, 0, '2020-03-27', '2020-11-27', 'dinkes.papuabaratprov.go.id', 'lambang-papua-barat.png'),\n" +
                "('Papua', 'Papua', 9934, 5007, 137, 4790, 3265200, 28, 0, 0, 0, '2020-03-22', '2020-11-27', 'covid19.papua.go.id', 'lambang-papua.png'),\n" +
                "('Riau', 'Riau', 19335, 16769, 428, 2138, 6657900, 10, 0, 0, 0, '2020-03-18', '2020-11-27', 'corona.riau.go.id', 'lambang-riau.png'),\n" +
                "('Sulbar', 'Sulawesi Barat', 1404, 1241, 21, 142, 1331000, 6, 0, 0, 0, '2020-03-29', '2020-11-27', 'dinkes.sulbarprov.go.id', 'lambang-sulawesi-barat.png'),\n" +
                "('Sulsel', 'Sulawesi Selatan', 20206, 18208, 490, 1508, 8690300, 21, 0, 0, 0, '2020-03-19', '2020-11-27', 'covid19.sulselprov.go.id', 'lambang-sulawesi-selatan.png'),\n" +
                "('Sulteng', 'Sulawesi Tengah', 1700, 1016, 68, 616, 2966300, 12, 0, 0, 0, '2020-03-26', '2020-11-27', 'dinkes.sultengprov.go.id', 'lambang-sulawesi-tengah.png'),\n" +
                "('Sultra', 'Sulawesi Tenggara', 6265, 5057, 97, 1111, 2602400, 15, 0, 0, 0, '2020-03-19', '2020-11-27', 'dinkes.sultraprov.go.id', 'lambang-sulawesi-tenggara.png'),\n" +
                "('Sulut', 'Sulawesi Utara', 6594, 5211, 239, 1144, 2461000, 11, 0, 0, 0, '2020-03-14', '2020-11-27', 'corona.sulutprov.go.id', 'lambang-sulawesi-utara.png'),\n" +
                "('Sumbar', 'Sumatra Barat', 19251, 15638, 363, 3250, 5321500, 12, 0, 0, 0, '2020-03-26', '2020-11-27', 'corona.sumbarprov.go.id', 'lambang-sumatra-barat.png'),\n" +
                "('Sumsel', 'Sumatra Selatan', 9300, 7504, 494, 1302, 8267000, 13, 0, 0, 0, '2020-03-24', '2020-11-27', 'corona.sumselprov.go.id', 'lambang-sumatra-selatan.png'),\n" +
                "('Sumut', 'Sumatra Utara', 15235, 0, 600, 2034, 14262100, 25, 0, 0, 0, '2020-03-18', '2020-11-27', 'corona.sulutprov.go.id', 'lambang-sumatra-utara.png');";
    }
    
     /**
     * Akan mengembalikan sebuah query untuk mengisi tabel users jika tabel tersebut kosong.
     * 
     * @return query untuk mengisi tabel users
     */    
    protected static String getDefaultDataTabel_users(){
        return  "INSERT INTO `users` (`username`, `namalengkap`, `namapanggilan`, `email`, `gender`, `tgl_lahir`, `pekerjaan`, `alamat`, `negara`, `password`, `tgl_dibuat`, `fotoprofile`, `type`) VALUES\n" +
                "('aderaihan', 'Ade Raihan Mahsa', 'Raihan', 'aderaihanacaaca@gmail.com', 'L', '2002-11-19', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'aderaihan', '2020-11-18', 'default', 'User'),\n" +
                "('agung', 'Agung Tri Laksono', 'Agung', 'agungtrilaksono1287@gmail.com', 'L', '2003-12-13', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'agungtri', '2020-11-18', 'default', 'User'),\n" +
                "('ananta', 'Ananta Eka Prayoga', 'Ananta', 'anantaprayoga25@gmail.com', 'L', '2002-01-28', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'ananta1234', '2020-11-18', 'default', 'User'),\n" +
                "('ansori', 'Ahmad Ansori', 'Ansori', 'aanblogme@gmail.com', 'L', '2002-05-01', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'ansori1234', '2020-11-18', 'default', 'User'),\n" +
                "('arifin', 'M. Arifin Mustofa', 'Arifin', 'arifinmmustofa173@gmail.com', 'L', '2003-10-26', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'arifin1234', '2020-11-18', 'default', 'User'),\n" +
                "('baihaqi', 'Achmad Baihaqi', 'Baihaqi', 'hakiahmad756@gmail.com', 'L', '2003-08-04', 'Software Enginer', 'Jawa Timur', 'Indonesia', '12345678', '2020-11-15', 'default', 'Admin'),\n" +
                "('bima', 'Fahrezian Arya Bima', 'Bima', 'awal.in45@gmail.com', 'L', '2003-09-20', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'bima1234', '2020-11-18', 'default', 'User'),\n" +
                "('chuenk', 'M. Nur Kholis Chu Enk Yunani', 'Chu Enk', 'chuenkaisyah15@gmail.com', 'L', '2003-05-02', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'chu1234', '2020-11-18', 'default', 'User'),\n" +
                "('david', 'David Aldian Hidayat', 'David', 'davidaldian15@gmail.com', 'L', '2002-05-17', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'davidaldian', '2020-11-18', 'default', 'User'),\n" +
                "('deky', 'Deky Reza Saputra', 'Deky', 'dekyreza8787@gmail.com', 'L', '2002-11-28', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'dekyreza', '2020-11-18', 'default', 'User'),\n" +
                "('dhinno', 'Dhinno Haryasena', 'Dhinno', 'dhinosena@gmail.com', 'L', '2001-10-06', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'dhinno1234', '2020-11-18', 'default', 'User'),\n" +
                "('didin', 'Didin Rakfil Beniafan', 'Didin', 'rakfildidin@gmail.com', 'L', '2002-10-27', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'didin1234', '2020-11-18', 'default', 'User'),\n" +
                "('fatur', 'Fatur Riandy', 'Fatur', 'riyandifatur868@gmail.com', 'L', '2002-09-20', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'faturiandy', '2020-11-18', 'default', 'User'),\n" +
                "('halim', 'Halim Bagus Perdana', 'Halim', 'halimpaijo1@gmail.com', 'L', '2002-11-09', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'halimbagus', '2020-11-18', 'default', 'User'),\n" +
                "('maul', 'Ilham Maulana', 'Ilham', 'im285281@gmail.com', 'L', '2002-06-16', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'mauldesu', '2020-11-18', 'default', 'User'),\n" +
                "('rozikul', 'Ahmad Rozikul Akbar', 'Rozikul', 'ahmadrozikul76@gmail.com', 'L', '2002-03-26', 'Software Enginer', 'Jawa Timur', 'Indonesia', 'rozikulakbar', '2020-11-18', 'default', 'User');";
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
