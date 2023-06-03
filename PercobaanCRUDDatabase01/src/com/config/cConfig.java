package com.config;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;

public class cConfig {
    
    private static String JDBCDRIVER = "com.mysql.cj.jdbc.Driver";  //Loading class `com.mysql.jdbc.Driver'. This is deprecated
    private static String DBURL = "jdbc:mysql://localhost:3306/dbbarang";
    private static String USER = "root";
    private static String PASS = "";
    
    // ini untuk instansiasi object dari class DriverManager dan Connection
//    private static Connection connect; 
    private static java.sql.Connection connect; 
    private static Statement statement;
    private static ResultSet resultData;
    
    public static void connection(){
        // method untuk melakukan koneksi ke database
        try {
            // registrasi driver yang akan dipakai
            Class.forName(JDBCDRIVER); 
            
            // buat koneksi ke database
            connect = DriverManager.getConnection(DBURL, USER, PASS); 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getAllData(){
        cConfig.connection();
        
        // isi nilai default dari variabel data
        String data = "Maaf data tidak ada";
        
        try {
            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();
            
            
            // query select all data from database
            String query = "SELECT `idBarang`, `namaBarang` FROM `tblbarang` WHERE isActive = 1";
            
            // eksekusi query
            resultData = statement.executeQuery(query);
            
            // set variabel data jadi null
            data = "";
            
            while (resultData.next()){
               data += "\nID Barang : " + resultData.getInt("idBarang") + ", Nama Barang : " + resultData.getString("namaBarang")+"\n";
            }
            
            // close connection
            statement.close();
            connect.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return data;
    }
    
    public static String detailData(String id){
        
        cConfig.connection();
        
        String data = "data tidak ditemukan";
        
        try {
            statement = connect.createStatement();
            
            String query = "SELECT * FROM tblbarang WHERE isActive = 1 AND idBarang = " + id;
            
            resultData = statement.executeQuery(query);
            
            data = "";
            
            //untuk mengecek apakah resultData memiliki baris data sebelumnya.
            if(!resultData.isBeforeFirst()){
                data = "data tidak ditemukan";
            }
            else {
                while (resultData.next()){
                    data += "\n- ID Barang : " + resultData.getInt("idBarang")
                            + "\n- Nama Barang : " + resultData.getString("namaBarang")
                            + "\n- Stok Barang : " + resultData.getInt("stokBarang")
                            + "\n- Harga Barang : " + resultData.getInt("hargaBarang");
                }
            }
            
            // tutup statement dan koneksi
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return data;
    }
    
    public static String cariData (String keyword){
        cConfig.connection();
        
        String data = "";
        
        try {
            statement = connect.createStatement();
            
//            String query = "SELECT * FROM tblbarang WHERE idBarang LIKE %"+keyword+"% OR namaBarang LIKE %"+keyword+"% OR stokBarang LIKE %"+keyword+"% OR hargaBarang LIKE %"+keyword+"%;";
            String query = "SELECT * FROM tblbarang WHERE isActive = 1 AND idBarang LIKE '%" + keyword + "%' OR namaBarang LIKE '%" + keyword + "%' OR hargaBarang LIKE '%" + keyword + "%' OR stokBarang LIKE '%" + keyword + "%'";
            resultData = statement.executeQuery(query);
            
            if (!resultData.isBeforeFirst())data = "data tidak ditemukan";
            else{
                while (resultData.next()){
                    data += "\n- ID Barang : " + resultData.getInt("idBarang")
                            +"\n- Nama Barang : " + resultData.getString("namaBarang")
                            +"\n- Stok Barang : " + resultData.getInt("stokBarang")
                            +"\n- Harga Barang : " + resultData.getInt("hargaBarang")+"\n";
                }
            }
            
            // close statement dan close koneksi
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static boolean tambahBarang (String namaBarang, int stokBarang, int hargaBarang){
        
        cConfig.connection();
        boolean data = false;
        
        try {
            statement = connect.createStatement();
            
            String query = "INSERT INTO tblbarang (`namaBarang`, `stokBarang`, `hargaBarang`) VALUES ('"+ namaBarang +"', "+ stokBarang +", "+ hargaBarang +");";
            
            data = !statement.execute(query);
            
            //tutup
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return data;
    }
    
    
    public static boolean updateData(int idBarang, String namaBarang, int stokBarang, int hargaBarang){
        cConfig.connection();
        boolean data = false;
        
        try {
            statement = connect.createStatement();
            
            String queryCek = "SELECT * FROM tblbarang WHERE idBarang = " + idBarang;
            
            resultData = statement.executeQuery(queryCek);
            
            // variabel temporary
            String namaCek = "";
            int stokCek = -1, hargaCek = -1;
            
            // memasukkan value ke variabel temporary
            while (resultData.next()){
                namaCek = resultData.getString("namaBarang");
                stokCek = resultData.getInt("stokBarng");
                hargaCek = resultData.getInt("hargaBarang");
            }
            
            if(!namaBarang.equalsIgnoreCase("")){
                namaCek = namaBarang;
            }
            if(stokBarang >= 0){
                stokCek = stokBarang;
            }
            if(hargaBarang >= 0){
                hargaCek = hargaBarang;
            }
            
            String query = "UPDATE `tblbarang` SET `namaBarang`='"+ namaCek +"',`stokBarang`="+ stokCek +",`hargaBarang`="+ hargaCek +" WHERE isActive = 1 AND `idBarang`= "+ idBarang;
            
            data = !statement.execute(query);
            
            //tutup
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public static boolean deleteData(int idBarang){
        cConfig.connection();
        boolean data = false;
        
        try {
            statement = connect.createStatement();
            
            String query = "UPDATE tblbarang SET isActive = '0' WHERE idBarang = " + idBarang;
            
            data = !statement.execute(query);
            
            //tutup
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
