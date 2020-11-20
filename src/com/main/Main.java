package com.main;

public class Main {
    
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            
            @Override
            public void run(){
                new com.window.all.LoadingWindow().setVisible(true);
            }
        });
        
    }
}
