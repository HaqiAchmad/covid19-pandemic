package com.database;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Account extends Database{
    
    public Account(){
        this.startConnection();
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
    
    /**
     * peraturan pada username
     * - username harus diantara 5-30 karakter
     * - simbol yang boleh digunakan untuk username antara lain : . _ , ' 
     * - username harus belum ada yang memakai
     * 
     * @param username
     * @return 
     */
    public boolean isValidUsername(String username){
        // karakter yang tidak diperbolehkan untuk username
        String charBlock = "`~!@#$%^&*()-=+\\|[]{};:\"<>?/";
        username = username.toLowerCase();

        if(username.length() >= 5 && username.length() <= 30){
            if(isValidText(username, charBlock)){
                if(!isExistUser(username)){
                    return true;
                }else{
                    JOptionPane.showMessageDialog(null, "'" + username + "' Username tersebut sudah digunakan!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Simbol yang diizinkan hanyalah : . _ , '", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Panjang dari username harus diantara 5-30 karakter!", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
    }
    
    /**
     * peraturan dalam nama lengkap
     * - nama lengkap panjang-nya harus diantara 5-50 karakter
     * - nama lengkap tidak boleh menggandung angka
     * - simbol yang dapat digunakn untuk nama lengkap antara lain : . ,
     * 
     * @param nama
     * @return 
     */
    public boolean isValidNamalengkap(String nama){
        return false;
    }
    
    public boolean isValidNamapanggilan(String nama){
        return false;
    }
    
    /**
     * peraturan dalam email
     * - jenis email yang didukung hanyalah Gmail saja 
     * - panjang username gmail harus diantara 6-30 karakter
     * - simbol yang dapat digunakan pada gmail adalah . saja
     * - email harus belum ada yang memakai
     * 
     * @param email
     * @return 
     */
    public boolean isValidEmail(String email){
        if(!email.contains("@")){
            return false;
        }
        email = email.toLowerCase();
        String gmail = "@gmail.com", usernameGmail = 
               email.substring(0, email.lastIndexOf("@")),
               blokChar = "`~!@#$%^&*()-=+\\|[]{};:\"<>?/_,'";
        
        if(email.substring(email.lastIndexOf("@")).equals(gmail)){
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
    
    public static void main(String[] args) {
        
        String email = "hakiahmad@gmail.com";
        Account acc = new Account();
        System.out.println(acc.isExistUser("baihaqi"));
        System.out.println(acc.isValidUsername("kawaii"));
        System.out.println(acc.isExistUser("hakiahmad756@gmail.com"));
        System.out.println(acc.isValidEmail(email));
        
        
    }
    
}
