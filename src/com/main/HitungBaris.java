package com.main;

import java.io.*;

public class HitungBaris {
    
    private static final String[] files = new String[]{
        "com.main.Main",
        "com.database.Database",
        "com.database.Account",
        "com.database.CovidCases",
        "com.media.audio.Audio",
        "com.media.gambar.Gambar",
        "com.window.all.LoadingWindow",
        "com.window.all.SignIn",
        "com.window.all.SignUp",
        "com.window.all.UbahPassword",
        "com.window.all.Beranda",
        "com.window.all.ApaCovid",
        "com.window.all.BahayaCovid",
        "com.window.all.GejalaCovid",
        "com.window.all.PenangananCovid",
        "com.window.all.PencegahanCovid",
        "com.window.all.KasusCovidDunia",
        "com.window.all.KasusCovidIndo",
        "com.window.all.AboutApp",
        "com.window.admin.DataAplikasi",
        "com.window.admin.UpdateUser",
        "com.window.admin.UpdateCovidDunia",
        "com.window.admin.UpdateCovidIndo"
    };
    
    private String getDirectory(final String file){
        String buffer  = "src\\";
        for(int i = 0; i < file.length(); i++){
            if(file.charAt(i) == '.'){
                buffer += "\\";
            }else{
                buffer += file.charAt(i);
            }
        }
        return buffer + ".java";
    }

    private int countLines(final String[] files) throws IOException{
        FileReader f;
        BufferedReader line;
        String file, buff = "";
        int lines = 0, lineLocal = 0;
        
        for(int i = 0; i < files.length; i++){
            file = getDirectory(files[i]);
            f = new FileReader(file);
            line = new BufferedReader(f);
            
                while((buff = line.readLine()) != null){
                    lineLocal++;
                }
                System.out.println(files[i] + " \t\t\t" + lineLocal + " baris");
                lines += lineLocal;
                lineLocal = 0;
        }
        return lines;
    }
    
    public static void main(String[] args) throws IOException {
        HitungBaris hitung = new HitungBaris();
        System.out.println(hitung.countLines(files));
        
    }
}