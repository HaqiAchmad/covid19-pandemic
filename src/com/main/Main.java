package com.main;

/**
 * 
 * @author Achmad Baihaqi
 * @since 2020-11-14
 */
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
 