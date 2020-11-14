package com.database;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
                                DATABASE = "app_covid19pandemic",
                                URL = "jdbc:mysql://localhost/" + DATABASE,
                                USER = "root",
                                PASS = "";
    
    public static final String KASUSCOVID_DUNIA = "kasuscovid_dunia", KASUSCOVID_INDO = "kasuscovid_indo", 
                               USERS = "users", ISLOGIN = "islogin";
    
    public void startConnection(){
        try{
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASS);
            stat = conn.createStatement();
            System.out.println("Berhasil terhubung ke database '" + DATABASE + "'");
        }catch(ClassNotFoundException | SQLException ex){
            
            if(ex.getMessage().contains("com.mysql.jdbc.Driver")){
                JOptionPane.showMessageDialog(null, "MySQL Connector tidak dapat ditemukan", "Error", JOptionPane.WARNING_MESSAGE);
            }else if(ex.getMessage().contains("Communications link failure")){
                JOptionPane.showMessageDialog(null, "MySQL anda belum diaktifkan!!", "Error", JOptionPane.WARNING_MESSAGE);
            }else if(ex.getMessage().contains("Unknown database")){
                JOptionPane.showMessageDialog(null, "Tidak dapat menemukan database '" + DATABASE + "'", "Error", JOptionPane.WARNING_MESSAGE);
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
            System.out.println("Berhasil memutus koneksi database '" + DATABASE + "'");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi Kesalahan!\n\nError message : "+ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void backupDatabase(){
        
    }
    
    public void restoreDatabase(){
        
    }
    
    public boolean isExistTabel(final String tabel){
        try{
            String sql = "SELECT * FROM " + tabel;
            stat.executeQuery(sql);
            return true;
        }catch(SQLException ex){
            System.out.println("Tabel " + tabel + " tidak dapat ditemukan!");
        }
        return false;
    }
    
    public boolean isEmptyTabel(final String tabel){
        try{
            String sql = "SELECT * FROM " + tabel;
            res = stat.executeQuery(sql);
            return !res.next();
        }catch(SQLException ex){
            System.out.println("Tabel " + tabel + " tidak dapat ditemukan!");
        }
        return true;
    }
    
    public boolean isExistDatabase(){
        try{
            conn = DriverManager.getConnection(URL, USER, PASS);
            stat = conn.createStatement();
            conn.close();
            stat.close();
            return true;
        }catch(SQLException ex){
            System.out.println("Database '" + DATABASE + "' tidak dapat ditemukan!");
        }
        return false;
    }
    
    public static void main(String[] args) {
        Database db = new Database();
        
        System.out.println(db.isExistDatabase());
        db.startConnection();
        System.out.println(db.isExistTabel(Database.KASUSCOVID_INDO));
        System.out.println(db.isEmptyTabel(Database.KASUSCOVID_DUNIA));
        db.closeConnection();
        
    }
}
