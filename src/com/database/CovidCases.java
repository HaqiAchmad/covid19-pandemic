package com.database;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class CovidCases extends Database{

    private final LocalDateTime lc = LocalDateTime.now();
    private String sql;
    private final String TABEL_SELECTED;
    
    public static final String KASUS_DUNIA = "kasuscovid_dunia",
                               KASUS_INDO = "kasuscovid_indo";
    
    public static final String NEGARA_IDN = "negara_idn", NEGARA_ENG = "negara_eng", KODE_PROV = "kode", PROVINSI = "provinsi", KASUS = "kasus", KEMATIAN = "kematian", SEMBUH = "sembuh", 
                               AKTIF = "aktif", KRITIS = "kritis", POPULASI = "populasi", ODP = "odp", PDP = "pdp", OTG = "otg", TOTAL_KAB = "total_kab", ZONA_MERAH = "kab_zonamerah",
                               ZONA_ORANYE = "kab_zonaoranya", ZONA_HIJAU = "kab_zonahijau", DIUBAH = "diubah", BENUA = "benua", LAMBANG = "lambang", BENDERA = "bendera";
    
    public CovidCases(final String tabel){
        TABEL_SELECTED = tabel;
        this.startConnection();
    }

    public CovidCases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int getRows(){
        try{
            // untuk menyimpan total baris yang ada didalam tabel
            int rows = 0;
            // mendapatkan semua data yang ada didalam tabel
            res = stat.executeQuery("SELECT * FROM " + TABEL_SELECTED);
            // akan melakukan perulangan sampai tidak ada baris lagi yang ada didalam tabel
            while(res.next()){
                rows++; // var rows akan bertambah 1 setiap membaca 1 baris tabel
            }
            return rows;
        }catch(SQLException ex){
            this.restoreTabel(TABEL_SELECTED);
            System.out.println("Terjadi Kesalahan : " + ex.getMessage());
        }
        return -1;
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
            System.out.println("Terjadi Kesaalahan : " + ex.getMessage());
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
            this.restoreDatabase();
            System.out.println("Terjadi kesalahan : " + ex.getMessage());
        }
        return false;
    }
    
    public String getMultipeFields(String fields[]){
        String field = "";
        for (String buff : fields) {
            field += buff + ", ";
        }
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
                if(isExistField(KASUS_INDO, field)){
                    sql = "SELECT * FROM kasuscovid_indo WHERE kode = '"+ key +"' OR provinsi = '" +key+ "'";
                }
            }
            // mengeksekusi query yang barusan dibuat untuk mendapatkan data
            res = stat.executeQuery(sql);
            // mengecek apakah data yang dicari ada atau tidak
            if(res.next()){
                return res.getString(field);
            }else{
                this.restoreTabel(TABEL_SELECTED);
            }
        }catch(SQLException ex){
            this.restoreTabel(TABEL_SELECTED);
            System.out.println("Terjadi kesalahan : " + ex.getMessage());
        }
        return "Gagal Mendapatkan Data";
    }
    
    public Object[][] getData(final String key) throws SQLException{
        
        int rows = 0, rowMax;
        Object[][] obj;
        sql = "SELECT * FROM kasuscovid_dunia WHERE negara_idn LIKE '%"+key+"%' OR negara_eng LIKE '%"+key+"%' ORDER BY kasus DESC";
        rowMax = getRows(sql);
        obj = new Object[rowMax][5];    
        res = stat.executeQuery(sql);
            while(res.next()){
                obj[rows][0] = ""+ rows;
                obj[rows][1] = res.getString(1);
                obj[rows][2] = res.getString("kasus");
                obj[rows][3] = res.getString("sembuh");
                obj[rows][4] = res.getString("kematian");
                rows++;
            }
        return obj;
    }
    
    
    public Object[][] getData(String fields[], String key){
        try{
            Object[][] obj;
            int rows = 0, cols = 0;
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                sql = "SELECT " + getMultipeFields(fields) + " FROM kasuscovid_dunia WHERE negara_idn LIKE '%"+key+"%' OR negara_eng LIKE '%"+key+"%' ORDER BY kasus DESC";
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                sql = "SELECT " + getMultipeFields(fields) + " FROM kasuscovid_indo ORDER BY kasus DESC";
            }else{
                 return null;
            }
            System.out.println("Query -> " + sql);
            obj = new Object[getRows(sql)][fields.length];
            res = stat.executeQuery(sql);
            while(res.next()){
                for(int i = 0; i < fields.length; i++){
                    obj[rows][i] = res.getString(i+1);
                }
                rows++;
            }
            return obj;
        }catch(SQLException ex){
            System.out.println("Terjadi kesalahan : " + ex.getMessage());
        }
        return null;
    }
    
//    public Object[][] getData(String fields[], String key){
//        return null;
//    }
    
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
            this.restoreTabel(TABEL_SELECTED);
            System.out.println("Terjadi kesalahan : " + ex.getMessage());
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
            this.restoreTabel(TABEL_SELECTED);
            System.out.println("Terjadi kesalahan saat menghapus data");
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
            this.restoreTabel(TABEL_SELECTED);
            System.out.println("Terjadi kesalahan : " + ex.getMessage());
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
            this.restoreTabel(TABEL_SELECTED);
            System.out.println("Terjadi Kesalahan : " + ex.getMessage());
        }
        return -1;
    }
    
    public double getTingkatKesembuhan(final String key){
        try{
            int sembuh, kematian;
            // membuat query untuk mendapatkan tingkat kesembuhan
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                sql = "SELECT * FROM kasuscovid_dunia WHERE negara_idn = '"+ key +"' OR negara_eng = '"+ key +"'";
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                sql = "SELECT * FROM kasuscovid_indo WHERE kode = '"+ key +"' OR provinsi = '"+ key +"'";
            }else{
                return -1;
            }
            // mendapatkan data
            res = stat.executeQuery(sql);
            if(res.next()){
                kematian = res.getInt("kematian");
                sembuh = res.getInt("sembuh");
                if(kematian == -1 || sembuh == -1){
                    return -1;
                }else{
                    return (double) 100 - this.getTingkatKematian(key);
                }
            }
            
        }catch(SQLException ex){
            this.restoreTabel(TABEL_SELECTED);
            System.out.println("Terjadi kesalahan : " + ex.getMessage());
        }
        return -1;
    }
    
    public double getTingkatKematian(final String key){
        try{
            int kematian, sembuh;
            // membuat query untuk mendapatkan tingkat kesembuhan
            if(TABEL_SELECTED.equalsIgnoreCase(KASUS_DUNIA)){
                sql = "SELECT * FROM kasuscovid_dunia WHERE negara_idn = '"+ key +"' OR negara_eng = '"+ key +"'";
            }else if(TABEL_SELECTED.equalsIgnoreCase(KASUS_INDO)){
                sql = "SELECT * FROM kasuscovid_indo WHERE kode = '"+ key +"' OR provinsi = '"+ key +"'";
            }else{
                return -1;
            }
            // mendapatkan data
            res = stat.executeQuery(sql);
            if(res.next()){
                sembuh = res.getInt("sembuh");
                kematian = res.getInt("kematian");
                if(sembuh == -1 || kematian == -1){
                    return -1;
                }else{
                    return (double) (kematian * 100) / sembuh;
                }
            }
            
        }catch(SQLException ex){
            this.restoreTabel(TABEL_SELECTED);
            System.out.println("Terjadi kesalahan : " + ex.getMessage());
        }
        return -1;
    }
    
    public String getDateNow(){
        // mendapatkan data saat ini dengan menggunakan object dari class LocalDateTime
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
        return String.format("%,d", num);
    }
    
    
    public static void main(String[] args) throws SQLException {
        CovidCases caseDunia = new CovidCases(KASUS_DUNIA);
        CovidCases caseIndo = new CovidCases(KASUS_INDO);
        
        System.out.println(caseDunia.getTingkatKesembuhan(".Dunia."));
        System.out.println(caseDunia.getTingkatKematian(".Dunia."));
        System.out.println("");
        System.out.println(caseDunia.getMultipeFields(new String[]{NEGARA_IDN, KASUS, SEMBUH, KEMATIAN}));
        
//        caseIndo.show(caseIndo.getData());
        
        caseDunia.closeConnection();
        caseIndo.closeConnection();
    }
 
    
}
