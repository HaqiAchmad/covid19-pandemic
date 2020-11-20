package com.database;

public class CovidCases extends Database{

    private final String TABEL;
    public static final String CASE_WORLD = "kasuscovid_dunia",
                               CASE_INDO = "kasuscovid_indo";
    
    public CovidCases(final String tabel){
        TABEL = tabel;
        this.startConnection();
    }
    
    public static void main(String[] args) {
        CovidCases covid = new CovidCases(CASE_INDO);
        
        System.out.println(covid.TABEL);
        covid.closeConnection();
    }
    
}