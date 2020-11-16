package com.window.all;

import javax.swing.JOptionPane;



public class Message {
    
    public static void showErrorMessage(final String message, final String lokasi, final String note){
        String show = "Pesan :\n" + message + "\n\nLokasi :\n" + lokasi + "\n\nNote :\n" + note;
        JOptionPane.showMessageDialog(null, show, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void main(String[] args) {
        Message.showErrorMessage("ClassNotFoundException", Message.class.getName(), "perbaiki");
    }
    
}
