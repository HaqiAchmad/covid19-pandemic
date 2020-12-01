package com.window.uas;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tester{
    
    public static void main(String[] args) throws URISyntaxException, IOException {
        File file = new File(new URI("https://drive.google.com/file/d/1WxAWFlZP_aCffECMcnLXQHe_wIzfO25N/view?usp=sharing"));
        FileReader f = new FileReader(file);
        BufferedReader baca = new BufferedReader(f);
        System.out.println(baca.readLine());
    }
}

class config {
    
    private static Connection mysqlconfig;
    public static Connection configDB() throws SQLException{
        try{
            String url = "jdbc:mysql://localhost:3306/siswa";
            String user = "root";
            String pass = "";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
            System.out.println("Konesksi Berhasil...");
            return mysqlconfig;
        }catch(SQLException ex){
            System.err.println("Terjadi Error : " + ex.getMessage());
        }
        return null;
    }
    
}

 class SoalNo4{
     
     public static void main(String[] args) {
         JOptionPane.showMessageDialog(null, "Penyimpanan Data Behasil", "Message", JOptionPane.INFORMATION_MESSAGE);
     }
 } 

class SoalNo5{
    
    public SoalNo5(){
        JFrame window = new JFrame();
        window.setSize(400, 200);
        window.setTitle("Jendela Program");
        window.setVisible(true);
    }
    
}