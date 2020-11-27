package com.database;

import com.media.audio.Audio;

import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-14
 */
public class CovidCases extends Database{

    private final LocalDateTime lc = LocalDateTime.now();
    private final String TABEL_SELECTED;
    private String sql;
    
    public static final String KASUS_DUNIA = "kasuscovid_dunia",
                               KASUS_INDO = "kasuscovid_indo";
    
    public static final String NEGARA_IDN = "negara_idn", NEGARA_ENG = "negara_eng", KODE_PROV = "kode", PROVINSI = "provinsi", KASUS = "kasus", KEMATIAN = "kematian", SEMBUH = "sembuh", 
                               AKTIF = "aktif", KRITIS = "kritis", POPULASI = "populasi", TOTAL_KAB = "total_kab", ZONA_MERAH = "kab_zonamerah",
                               ZONA_ORANYE = "kab_zonaoranye", ZONA_HIJAU = "kab_zonahijau", DIUBAH = "diubah", KASUS_PERTAMA = "kasus_pertama", WEBSITE = "website", BENUA = "benua", LAMBANG = "lambang", BENDERA = "bendera";
    
    public CovidCases(final String tabel){
        // jika input yang dimasukan bukan kasus_dunia maupun kasus_indo secara default tabel akan diatur ke kasus_dunia
        if(!tabel.equalsIgnoreCase(KASUS_DUNIA) && !tabel.equalsIgnoreCase(KASUS_INDO)){
            TABEL_SELECTED = KASUS_DUNIA;
        }else{
            TABEL_SELECTED = tabel;
        }
        this.startConnection();
    }
    
    public int getRows(final String sql){
        try{
            // untuk menyimpan total baris yang ada didalam tabel
            int rows = 0;
            // mendapatkan semua data yang ada didalam tabel berdasarkan query sql
            res = stat.executeQuery(sql);
            // akan melakukan perulangan sampai tidak ada baris lagi yang ada didalam tabel
            while(res.next()){
                rows++; // var rows akan bertambah 1 setiap membaca 1 baris tabel
            }
            return rows;
        }catch(SQLException ex){
            this.restoreDatabase();
            System.out.println("Terjadi kesalahan saat akan mendapatkan total rows pada tabel!\nError : " + ex.getMessage());
        }
        return -1;
    }
    
    public boolean addData(final String sql){
        try{
            // menambahkan data ke dalam tabel
            int add = stat.executeUpdate(sql);
            // mengecek apkah tabel berhasil ditambahkan atau tidak, jika berhasil maka database akan dibackup
            if(add > 0){
                this.backupDatabase(); // membackup database
                return true;
            }
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_INFO);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat akan menambahkan data\n " + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreDatabase();
        }
        return false;
    }
    
    /**
     * digunakan untuk mendapatkan fields yang berasal dari param field pada method getData
     * 
     * @param fields
     * @return 
     */
    private String getMultipleFields(String fields[]){
        String field = "";
        for (String buff : fields) {
            field += buff + ", ";
        }
        // membuang tanda koma diakhir String
        return field.substring(0, field.length()-2);
    }
    
    public String getData(final String field, final String key){
        try{
            // membuat query yang digunakan untuk mendapatkan data berdasarkan tabel yang dipilih
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                // mengecek apakah data/field ada didalam tabel ada atau tidak, jika ada maka query akan dibuat
                if(isExistField(KASUS_DUNIA, field)){
                    sql = "SELECT * FROM kasuscovid_dunia WHERE negara_idn = '"+ key +"' OR negara_eng = '"+ key +"'";
                }
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                // mengecek apakah data/field ada didalam tabel ada atau tidak, jika ada maka query akan dibuat
                if(isExistField(KASUS_INDO, field)){
                    sql = "SELECT * FROM kasuscovid_indo WHERE kode = '"+ key +"' OR provinsi = '" +key+ "'";
                }
            }
            System.out.println("Mengambil data "+ field +" dari tabel '" + TABEL_SELECTED + "' dengan keyword = '"+ key +"'");
            // mengeksekusi query yang barusan dibuat untuk mendapatkan data
            res = stat.executeQuery(sql);
            // mengecek apakah data yang dicari ada atau tidak
            if(res.next()){
                return res.getString(field);
            }else{
                this.restoreTabel(TABEL_SELECTED);
            }
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreTabel(TABEL_SELECTED);
        }
        return "Gagal Mendapatkan Data";
    }
    
    public int getDataNumber(final String field, final String key){
        try{
            // membuat query yang digunakan untuk mendapatkan data berdasarkan tabel yang dipilih
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                // mengecek apakah data/field ada didalam tabel ada atau tidak, jika ada maka query akan dibuat
                if(isExistField(KASUS_DUNIA, field)){
                    sql = "SELECT * FROM kasuscovid_dunia WHERE negara_idn = '"+ key +"' OR negara_eng = '"+ key +"'";
                }
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                // mengecek apakah data/field ada didalam tabel ada atau tidak, jika ada maka query akan dibuat
                if(isExistField(KASUS_INDO, field)){
                    sql = "SELECT * FROM kasuscovid_indo WHERE kode = '"+ key +"' OR provinsi = '" +key+ "'";
                }
            }
            System.out.println("Mengambil data "+ field +" dari tabel '" + TABEL_SELECTED + "' dengan keyword = '"+ key +"'");
            // mengeksekusi query yang barusan dibuat untuk mendapatkan data
            res = stat.executeQuery(sql);
            // mengecek apakah data yang dicari ada atau tidak
            if(res.next()){
                return Integer.parseInt(res.getString(field));
            }else{
                this.restoreTabel(TABEL_SELECTED);
            }
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreTabel(TABEL_SELECTED);
        }
        return -2;
    }
    
    public Object[][] getData(final String[] fields){
        try{
            Object[][] obj; // digunakan untuk menyimpan data yang berasal dari tabel
            int rows = 0;
            // membuat query yang digunakan untuk mendapatkan data berdasarkan tabel yang dipilih
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                sql = "SELECT " + getMultipleFields(fields) + " FROM kasuscovid_dunia ORDER BY kasus DESC;";
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                sql = "SELECT " + this.getMultipleFields(fields) + " FROM kasuscovid_indo ORDER BY kasus DESC;";
            }
            
            // mendefinisikan object berdasarkan panjang row dan colum yang ada didalam tabel
            obj = new Object[getRows(sql)][fields.length];
            // mengeksekusi query
            res = stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(res.next()){
                // menyimpan data dari tabel ke object
                for (int i = 0; i < fields.length; i++) {
                    obj[rows][i] = res.getString(i+1);
                }
                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
            }
            return obj;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreTabel(TABEL_SELECTED);
        }
        
        return null;
    }
    
    public Object[][] getData(final String[] fields, final String key){
        try{
            Object[][] obj;
            int rows = 0;
            // membuat query yang digunakan untuk mendapatkan data dari tabel berdasarkan tabel yang dipilih
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                sql = "SELECT " + getMultipleFields(fields) + " FROM kasuscovid_dunia WHERE negara_idn LIKE '%"+ key +"%' OR negara_eng LIKE '%"+ key +"%' OR benua LIKE '%"+ key +"%' ORDER BY kasus DESC;";
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                sql = "SELECT " + getMultipleFields(fields) + " FROM kasuscovid_indo WHERE kode = '"+ key +"' OR provinsi = '"+ key +"' ORDER BY kasus DESC;";
            }
            
            // mendefinisikan object berdasarkan total rows dan cols yang ada didalam tabel
            obj = new Object[getRows(sql)][fields.length];
            // mengeksekusi query
            res = stat.executeQuery(sql);
            // mendapatkan semua data yang ada didalam tabel
            while(res.next()){
                // menyimpan data dari tabel ke object
                for (int i = 0; i < fields.length; i++) {
                    obj[rows][i] = res.getString(i+1);
                }
                rows++; // rows akan bertambah 1 setiap selesai membaca 1 row pada tabel
            }
            return obj;
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat mengambil data dari database\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreTabel(TABEL_SELECTED);
        }
        return null;
    }
    
    public boolean setData(final String field, final String key, final String newData){
        try{
            // digunakan untuk mengecek apakah proses update berhasil atau tidak
            int update;
            // membuat query yang digunakan untuk mengupdate data berdasarkan tabel yang dipilih
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                // mengecek apakah field ada didalam tabel ada atau tidak, jika ada maka query akan dibuat
                if(isExistField(KASUS_DUNIA, field)){
                    sql = "UPDATE kasuscovid_dunia SET " + field + " = '"+ newData +"' WHERE negara_idn = '" + key +"' OR negara_eng = '"+ key +"';";
                }
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                // mengecek apakah field ada didalam tabel ada atau tidak, jika ada maka query akan dibuat
                if(isExistField(KASUS_INDO, field)){
                    sql = "UPDATE kasuscovid_indo SET " + field + " = '" + newData + "' WHERE kode = '"+ key +"' OR provinsi = '"+ key +"'";
                }
            }else{
                return false;
            }
            // proses mengupdate data
            update = stat.executeUpdate(sql);
            // mengecek apakah data berhasil diupdate atau tidak
            if(update > 0){
                this.backupDatabase();
                return true;
            }else{
                this.restoreTabel(TABEL_SELECTED);
            }
        }catch(Exception ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat akan mengedit data\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreTabel(TABEL_SELECTED);
        }
        return false;
    }
    
    public boolean deleteData(final String key){
        try{
            // digunakan untuk mengecek apakah data berhasil didelete atau tidak
            int delete;
            // membuat query untuk menghapus data berdasarkan tabel yang dipilih
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                sql = "DELETE FROM kasuscovid_dunia WHERE negara_idn = '"+ key +"' OR negara_eng = '"+ key +"'";
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                sql = "DELETE FROM kasuscovid_indo WHERE kode = '"+ key +"' OR provinsi = '"+ key +"'";
            }else{
                return false;
            }
            // menghapus data
            delete = stat.executeUpdate(sql);
            // mengecek apakah data berhasil dihapus atau tidak
            if(delete > 0){
                this.backupDatabase();
                return true;
            }else{
                this.restoreTabel(TABEL_SELECTED);
            }
            
        }catch(SQLException ex){
            Audio.play(Audio.SOUND_ERROR);
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat akan menghapus data!\n" + ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            this.restoreTabel(TABEL_SELECTED);
        }
        return false;
    }
    
    public boolean isExist(final String key){
        try{
            // membuat query yang digunakan untuk mengecek data ada atau tidak berdasarkan tabel yang dipilih
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                sql = "SELECT * FROM kasuscovid_dunia WHERE negara_idn = '"+ key +"' OR negara_eng = '"+ key +"'";
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                sql = "SELECT * FROM kasuscovid_indo WHERE kode = '"+ key +"' OR provinsi = '"+ key +"'";
            }else{
                return false;
            }
            // mengeksekusi query
            res = stat.executeQuery(sql);
            return res.next();
            
        }catch(SQLException ex){
            System.out.println("Terjadi kesalahan saat akan mengecek ada atau tidaknya data \nError : " + ex.getMessage());
            this.restoreTabel(TABEL_SELECTED);
        }
        return false;
    }
    
    public int getPeringkat(final String key){
        try{
            int peringkat = 0;
            // membuat query untuk mendapatkan semua data yang ada didalam tabel berdasarkan tabel yang dipilih
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                sql = "SELECT * FROM kasuscovid_dunia WHERE benua <> 'null' ORDER BY kasus DESC";
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                sql = "SELECT * FROM kasuscovid_indo WHERE kode <> 'indo' ORDER BY kasus DESC";
            }else{
                return -1;
            }
            // mendapatkan semua data yang ada didalam tabel
            res = stat.executeQuery(sql);
            while(res.next()){
                peringkat++; 
                // digunakan untuk mendapatkan peringkat berdasarkan tabel yang dipilih
                if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                    if(res.getString("negara_idn").equalsIgnoreCase(key) || res.getString("negara_eng").equalsIgnoreCase(key)){
                        return peringkat;
                    }                    
                }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                    if(res.getString("kode").equalsIgnoreCase(key) || res.getString("provinsi").equalsIgnoreCase(key)){
                        return peringkat;
                    }                    
                }
            }
        }catch(SQLException ex){
            System.out.println("Terjadi kesalahan saat akan mendapatkan peringkat : " + ex.getMessage());
            this.restoreTabel(TABEL_SELECTED);
        }
        return -1;
    }
    
    public double getPresentase(final int sample, final int populasi){
        if(sample < 0 || populasi < 0){
            return -1;
        }else if(sample > populasi){
            return 100 - (double) populasi / sample * 100;
        }else {
            return (double) sample / populasi * 100;
        }
    }
    
    public String getDateNow(){
        // mendapatkan tanggal saat ini dengan menggunakan object dari class LocalDateTime
        return "" + lc.getYear() + "-" + lc.getMonthValue() + "-" + lc.getDayOfMonth();
    }
    
    public String dateToString(final String date){
        // mengambil data seperti tahun, bulan dan hari dari param date 
        int tahun = Integer.parseInt(date.substring(0, date.indexOf("-"))),
            bulan = Integer.parseInt(date.substring(date.indexOf("-") + 1, date.lastIndexOf("-"))),
            hari = Integer.parseInt(date.substring(date.lastIndexOf("-") + 1));
        // digunakan untuk menyimpan data nama bulan
        String namaBulan;
        
        // mendapatkan nama bulan berdasarkan nilai bulan
        switch(bulan){
            case 1: namaBulan = "Januari"; break;
            case 2: namaBulan = "Februari"; break;
            case 3: namaBulan = "Maret"; break;
            case 4: namaBulan = "April"; break;
            case 5: namaBulan = "Mei"; break;
            case 6: namaBulan = "Juni"; break;
            case 7: namaBulan = "Juli"; break;
            case 8: namaBulan = "Agustus"; break;
            case 9: namaBulan = "September"; break;
            case 10: namaBulan = "Oktober"; break;
            case 11: namaBulan = "November"; break;
            case 12: namaBulan = "Desember"; break;
            default: return "Gagal Mendapatkan Data";
        }
        
        // contoh output : 20 November 2020
        return "" + hari + " " + namaBulan + " " + tahun;
    }
    
    public String addDelim(final int num){
        // menambahkan delimetri
        if(num >= 0){
            return String.format("%,d", num);
        }else if(num == -1){
            return "N/A";
        }else{
            return "Terjadi Error";
        }
            
    }
    
    public String addDelim(final long num){
        // menambahkan delimetri
        if(num >= 0){
            return String.format("%,d", num);
        }else if(num == -1){
            return "N/A";
        }else{
            return "Terjadi Error";
        }
            
    }

}
