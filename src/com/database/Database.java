package com.database;

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

public class Database {
    
    public Connection conn;
    public Statement stat;
    public ResultSet res;
    
    private static final String DRIVER = "com.mysql.jdbc.Driver",
                                DB_NAME = "app_covid19tester",
                                URL = "jdbc:mysql://localhost/" + DB_NAME,
                                USER = "root",
                                PASS = "";
    
    public static final String KASUSCOVID_DUNIA = "kasuscovid_dunia", KASUSCOVID_INDO = "kasuscovid_indo", 
                               USERS = "users", ISLOGIN = "islogin";
        
    public void startConnection(){
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stat = conn.createStatement();
            System.out.println("Berhasil terhubung ke database '" + DB_NAME + "'\n");
        }catch(ClassNotFoundException | SQLException ex){
            
            if(ex.getMessage().contains("com.mysql.jdbc.Driver")){
                JOptionPane.showMessageDialog(null, "MySQL Connector tidak dapat ditemukan", "Error", JOptionPane.WARNING_MESSAGE);
            }else if(ex.getMessage().contains("Communications link failure")){
                JOptionPane.showMessageDialog(null, "MySQL anda belum diaktifkan!!", "Error", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }else if(ex.getMessage().contains("Unknown database")){
                JOptionPane.showMessageDialog(null, "Tidak dapat menemukan database '" + DB_NAME + "'", "Error", JOptionPane.WARNING_MESSAGE);
                restoreDatabase();
            }else{
                JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    public void closeConnection(){
        try{
            if(conn != null){
                conn.close();
            }
            if(stat != null){
                stat.close();
            }
            if(res != null){
                res.close();
            }
            System.out.println("Berhasil memutus koneksi database '" + DB_NAME + "'");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void backupDatabase(){
        try{
            FileWriter file;
            BufferedWriter backup;
            String values;
            
            // Mengecek apakah database dan tabel exist atau tidak!
            if(!isExistDatabase() || !isExistTabel(KASUSCOVID_DUNIA) || !isExistTabel(KASUSCOVID_INDO) || !isExistTabel(USERS) || !isExistTabel(ISLOGIN)){
                JOptionPane.showMessageDialog(null, "Database Corrupt!!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
                restoreDatabase();
            }
            // Mengecek apakah tabel yang ada didalam databaes kosong atau tidak
            else if(isEmptyTabel(KASUSCOVID_DUNIA) || isEmptyTabel(KASUSCOVID_INDO) || isEmptyTabel(USERS)){
                JOptionPane.showMessageDialog(null, "Database Corrupt!!", "Fatal Error", JOptionPane.ERROR_MESSAGE);
                restoreDatabase();
            }
            
            // Membackup tabel kasuscovid_dunia
            file = new FileWriter("src\\com\\database\\backup\\BACKUP kasuscovid_dunia.haqi");
            backup = new BufferedWriter(file);
            res = stat.executeQuery("SELECT * FROM kasuscovid_dunia");
           
            backup.write("INSERT INTO kasuscovid_dunia VALUES \n"); 
                // membaca semua data yang ada di dalam tabel, lalu menuliskan datanya ke file "BACKUP kasuscovid_dunia.haqi"
                while(res.next()){
                    values = "('" + res.getString("negara_idn") + "', '" + res.getString("negara_eng") + "', " + res.getString("kasus") + ", " + res.getString("kematian") + ", " + res.getString("sembuh") + ", " + res.getString("aktif") + ", " + res.getString("kritis") + ", " + res.getString("populasi") + ", '" + res.getString("diubah") + "', '" + res.getString("benua") + "', '" + res.getString("bendera") + "')";
                    if(!res.isLast()){
                        backup.write(values + ",");
                    }else{
                        backup.write(values + ";");
                    }
                    backup.newLine();
                }
            backup.write("# Copyright © 2020. Achmad Baihaqi. All Rights Reserved.");
            backup.flush();
            
            // Membackup tabel kasuscovid_indo
            file = new FileWriter("src\\com\\database\\backup\\BACKUP kasuscovid_indo.haqi");
            backup = new BufferedWriter(file);
            res = stat.executeQuery("SELECT * FROM kasuscovid_indo");
            
            backup.write("INSERT INTO kasuscovid_indo VALUES \n");
                // membaca semua data yang ada didalam tabel, lalu menuliskan datanya ke file "BACKUP kasuscovid_indo.haqi"
                while(res.next()){
                    values = "('" + res.getString("kode") + "', '" + res.getString("provinsi") + "', " + res.getString("kasus") + ", " + res.getString("sembuh") + ", " + res.getString("kematian") + ", " + res.getString("aktif") + ", " + res.getString("odp") + ", " + res.getString("pdp") + ", " + res.getString("otg") +", "+ res.getString("total_kab") +", "+ res.getString("kab_zonamerah") + ", " + res.getString("kab_zonaoranye") + ", " + res.getString("kab_zonahijau") + ", '" + res.getString("diubah") + "', '" + res.getString("lambang") + "')";
                    if(!res.isLast()){
                        backup.write(values + ",");
                    }else{
                        backup.write(values + ";");
                    }
                    backup.newLine();
                }
            backup.write("# Copyright © 2020. Achmad Baihaqi. All Rights Reserved.");
            backup.flush();
            
            // Membackup tabel users
            file = new FileWriter("src\\com\\database\\backup\\BACKUP users.haqi");
            backup = new BufferedWriter(file);
            res = stat.executeQuery("SELECT * FROM users");
            
            backup.write("INSERT INTO users VALUES \n");
                // membaca semua data yang ada didalam tabel, lalu menuliskan datanya ke file "BACKUP users.haqi"
                while(res.next()){
                    values = "('" + res.getString("username") + "', '" + res.getString("namalengkap") + "', '" + res.getString("namapanggilan") + "', '" + res.getString("email") + "', '" + res.getString("gender") + "', '" + res.getString("tgl_lahir") + "', '" + res.getString("perkerjaan") + "', '" + res.getString("alamat") + "', '" + res.getString("negara") + "', '" + res.getString("password") + "', '" + res.getString("tgl_dibuat") + "', '" + res.getString("fotoprofile") + "', '" + res.getString("type") + "')";
                    if(!res.isLast()){
                        backup.write(values + ",");
                    }else{
                        backup.write(values + ";");
                    }
                    backup.newLine();
                }
                backup.write("# Copyright © 2020. Achmad Baihaqi. All Rights Reserved.");
                backup.flush();
                
            // Membackup tabel islogin
            file = new FileWriter("src\\com\\database\\backup\\BACKUP islogin.haqi");
            backup = new BufferedWriter(file);
            res = stat.executeQuery("SELECT * FROM islogin");
            
            backup.write("INSERT INTO islogin VALUES \n");
                // membaca semua data yang ada didalam tabel, lalu menuliskan datanya ke file "BACKUP islogin.haqi"
                while(res.next()){
                    values = "('" + res.getString("username") + "', '" + res.getString("namalengkap") + "', '" + res.getString("email") + "')";
                    if(!res.isLast()){
                        backup.write(values + ",");
                    }else{
                        backup.write(values + ";");
                    }
                    backup.newLine();
                }
                backup.write("# Copyright © 2020. Achmad Baihaqi. All Rights Reserved.");
                backup.flush();                

        }catch(SQLException | IOException ex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat membackup database!!\n\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public String getBakcupDatabase(final String tabel){
        try{
            String filename = "BACKUP " + tabel + ".haqi", data = "", buffer;
            FileReader file = new FileReader("src\\com\\database\\backup\\" + filename);
            BufferedReader getBck = new BufferedReader(file);
                
                while((buffer = getBck.readLine()) != null){
                    data += buffer + "\n";
                }
            return data;    
        }catch(IOException ex){
            System.out.println("Gagal mengambil backup data : " + ex.getMessage());
        }
        return null;
    }
    
    public void restoreTabel(final String tabel){
        try{
            File f;
            int create; 
            String fileBackup = "src\\com\\database\\backup\\BACKUP " + tabel + ".haqi";
            
            // Mengecek apakah tabel ada didalam database atau tidak, jika tidak maka tabel akan dibuat.
            if(!isExistTabel(tabel)){
                // Membuat Tabel 
                create = stat.executeUpdate(DefaultDatabase.getDefaultStructurTable(tabel));
                // Mengecek apakah tabel  berhasil dibuat atau tidak, jika berhasil maka data tabel akan dipulihkan
                if(create == 0){
                    System.out.println("Tabel '" + tabel + "' berhasil dibuat!"); 
                    // Memulihkan data dari tabel 
                     f = new File(fileBackup);
                     // Mengecek apakah file backup tabel ada atau tidak
                     if(f.exists()){ 
                         // proses memulihkan data tabel jika file backup ada 
                         create = stat.executeUpdate(this.getBakcupDatabase(tabel));
                         // Mengecek apakah data berhasil dipulihkan atau tidak
                         if(create > 0){
                             System.out.println("Tabel '" + tabel + "' berhasil dipulihkan!\n");
                         }else{
                             System.out.println("Tabel '" + tabel + "' gagal dipulihkan!\n");
                         }
                     }else{
                         // proses memulihkan data tabel jika file backup tidak ada 
                         create = stat.executeUpdate(DefaultDatabase.getDefaultDataTable(tabel));
                         // Mengecek apakah data berhasil dipulihkan atau tidak
                         if(create > 0){
                             System.out.println("Tabel '" + tabel + "' berhasil dipulihkan!\n");
                         }else{
                             System.out.println("Tabel '" + tabel + "' gagal dipulihkan!\n");
                         }
                     }
                }
            }else{
                System.out.println("\nTabel '" + tabel + "' ditemukan!"); 
                // Mengecek apakah tabel  kosong atau tidak, jika kosong maka data akan dipulihkan
                if(isEmptyTabel(tabel)){
                    System.out.println("Tabel '" + tabel + "' kosong!");
                    // Memulihkan data
                    f = new File(fileBackup);
                    // Mengecek apakah file backup tabel  ada atau tidak
                    if(f.exists()){
                        // Proses memulihkan data jika file ada
                        create = stat.executeUpdate(this.getBakcupDatabase(tabel));
                        // Mengecek apakah data berhasil dipulihkan atau tidak
                        if(create > 0){
                            System.out.println("Tabel '" + tabel + "' berhasil dipulihkan!\n");
                        }else{
                            System.out.println("Tabel '" + tabel + "' gagal dipulihkan!\n");
                        }
                    }else{
                        // Proses memulihkan data jika file backup tidak ada
                        create = stat.executeUpdate(DefaultDatabase.getDefaultDataTable(tabel));
                        // Mengecek apakah data berhasil dipulihkan atau tidak
                        if(create > 0){
                            System.out.println("Tabel '" + tabel + "' berhasil dipulihkan!\n");
                        }else{
                            System.out.println("Tabel '" + tabel + "' gagal dipulihkan!\n");
                        }
                    }
                }
            }
            
        }catch(SQLException ex){
            System.out.println("Terjadi kesalahan!!\n ERROR : " + ex.getMessage());
            try{
                // Menangani error jika terjadi exception 
                stat.executeUpdate("DROP TABLE " + tabel); // menghapus tabel sebelumnya 
                stat.executeUpdate(DefaultDatabase.getDefaultStructurTable(tabel)); // membuat tabel 
                stat.executeUpdate(DefaultDatabase.getDefaultDataTable(tabel)); // memulihkan data
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void restoreDatabase(){
        try{
            // Mengecek apakah database ada atau tidak, jika tidak maka akan database dibuat
            if(!isExistDatabase()){
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
            
            restoreTabel(KASUSCOVID_DUNIA);
            restoreTabel(KASUSCOVID_INDO);
            restoreTabel(USERS);
            restoreTabel(ISLOGIN);
            
        }catch(ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan!\n\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
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
    
    public boolean isEmptyTabel(final String tabel){
        try{
            String sql = "SELECT * FROM " + tabel;
            res = stat.executeQuery(sql);
            return !res.next();
        }catch(SQLException ex){
            System.out.println("Tabel '" + tabel + "' tidak dapat ditemukan!");
        }
        return true;
    }
    
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
    
    public static void main(String[] args) {
        Database db = new Database();
        
        db.startConnection();
        db.backupDatabase();
        db.closeConnection();
    }
}

class DefaultDatabase{
    
    protected static String getDefaultStructurTable(final String tabel){
        if(tabel.equalsIgnoreCase(Database.KASUSCOVID_DUNIA)){
            return getDefaultTable_kasusCoviDunia();
        }else if(tabel.equalsIgnoreCase(Database.KASUSCOVID_INDO)){
            return getDefaultTable_kasusCovidIndo();
        }else if(tabel.equalsIgnoreCase(Database.USERS)){
            return getDefaultTable_users();
        }else if(tabel.equalsIgnoreCase(Database.ISLOGIN)){
            return getDefaultTable_isLogin();
        }else{
            return null;
        }
    }
    
    protected static String getDefaultDataTable(final String tabel){
        if(tabel.equalsIgnoreCase(Database.KASUSCOVID_DUNIA)){
            return getDefaultData_kasusCovidDunia();
        }else if(tabel.equalsIgnoreCase(Database.KASUSCOVID_INDO)){
            return getDefaultData_kasusCovidIndo();
        }else if(tabel.equalsIgnoreCase(Database.USERS)){
            return getDefaultData_users();
        }else if(tabel.equalsIgnoreCase(Database.ISLOGIN)){
            return getDefaultData_isLogin();
        }else{
            return null;
        }
    }
    
    protected static String getDefaultTable_kasusCoviDunia(){
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
    
    protected static String getDefaultTable_kasusCovidIndo(){
        return  "CREATE TABLE `kasuscovid_indo` (\n" +
                "  `kode` varchar(10) NOT NULL,\n" +
                "  `provinsi` varchar(40) NOT NULL,\n" +
                "  `kasus` int(11) NOT NULL,\n" +
                "  `sembuh` int(11) NOT NULL,\n" +
                "  `kematian` int(11) NOT NULL,\n" +
                "  `aktif` int(11) NOT NULL,\n" +
                "  `odp` int(11) NOT NULL,\n" +
                "  `pdp` int(11) NOT NULL,\n" +
                "  `otg` int(11) NOT NULL,\n" +
                "  `total_kab` int(5) NOT NULL,\n" +
                "  `kab_zonamerah` int(5) NOT NULL,\n" +
                "  `kab_zonaoranye` int(5) NOT NULL,\n" +
                "  `kab_zonahijau` int(5) NOT NULL,\n" +
                "  `diubah` date NOT NULL,\n" +
                "  `lambang` text NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
    }
    
    protected static String getDefaultTable_users(){
        return  "CREATE TABLE `users` (\n" +
                "  `username` varchar(30) NOT NULL,\n" +
                "  `namalengkap` varchar(50) NOT NULL,\n" +
                "  `namapanggilan` varchar(20) NOT NULL,\n" +
                "  `email` varchar(40) NOT NULL,\n" +
                "  `gender` enum('L','P') NOT NULL,\n" +
                "  `tgl_lahir` date NOT NULL,\n" +
                "  `perkerjaan` varchar(30) NOT NULL,\n" +
                "  `alamat` varchar(35) NOT NULL,\n" +
                "  `negara` varchar(35) NOT NULL,\n" +
                "  `password` varchar(30) NOT NULL,\n" +
                "  `tgl_dibuat` date NOT NULL,\n" +
                "  `fotoprofile` text NOT NULL,\n" +
                "  `type` enum('Admin','User') NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
    }
    
    protected static String getDefaultTable_isLogin(){
        return  "CREATE TABLE `islogin` (\n" +
                "  `username` varchar(30) NOT NULL,\n" +
                "  `namalengkap` varchar(50) NOT NULL,\n" +
                "  `email` varchar(40) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
    }
    
    protected static String getDefaultData_kasusCovidDunia(){
        return  "INSERT INTO `kasuscovid_dunia` (`negara_idn`, `negara_eng`, `kasus`, `kematian`, `sembuh`, `aktif`, `kritis`, `populasi`, `diubah`, `benua`, `bendera`) VALUES\n" +
                "('.Dunia.', '.World.', 32138014, 982722, 23708311, 7446981, 62385, 781423273, '2020-09-30', 'null', 'default'),\n" +
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
                "('Indonesia', 'Indonesia', 262022, 10105, 191853, 60064, 0, 274195355, '2020-09-30', 'Asia', 'bendera-indonesia.jpg'),\n" +
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
    
    protected static String getDefaultData_kasusCovidIndo(){
        return  "INSERT INTO `kasuscovid_indo` (`kode`, `provinsi`, `kasus`, `sembuh`, `kematian`, `aktif`, `odp`, `pdp`, `otg`, `total_kab`, `kab_zonamerah`, `kab_zonaoranye`, `kab_zonahijau`, `diubah`, `lambang`) VALUES\n" +
                "('Jatim', 'Jawa Timur', 32138014, 23708311, 982722, 7446981, 5454, 5435, 543, 35, 23, 12, 5, '2020-11-15', 'default');";
    }
    
    protected static String getDefaultData_users(){
        return  "INSERT INTO `users` (`username`, `namalengkap`, `namapanggilan`, `email`, `gender`, `tgl_lahir`, `perkerjaan`, `alamat`, `negara`, `password`, `tgl_dibuat`, `fotoprofile`, `type`) VALUES\n" +
                "('baihaqi', 'Achmad Baihaqi', 'Baihaqi', 'hakiahmad756@gmail.com', 'L', '2003-08-04', 'Software Enginer', 'Jawa Timur', 'Indonesia', '12345678', '2020-11-15', 'default', 'Admin');\n";
    }

    protected static String getDefaultData_isLogin(){
        return 
               "null";
    }
}
