package com.view;

import com.config.cConfig;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class cView {
    private static Scanner inputUser = new Scanner (System.in);
    
    public static void index(){
        menu:
        while(true){
            System.out.print(
            "\n=== MENU ==="
            + "\n1. Lihat Semua Data Barang"
            + "\n2. Detail Data Barang"
            + "\n3. Cari Data Barang"
            + "\n4. Tambah Data Barang"
            + "\n5. Update Data Barang"
            + "\n6. Delete Data Barang"
            + "\n0. exit"
            + "\npilih [1/2/3/4/5/0] : ");
            String pilihan = inputUser.next();
            
            switch (pilihan){
                case "1":
                    cView.getAllData();
                    break;
                case "2":
                    cView.detailData();
                    break;
                case "3":
                    cView.cariData();
                    break;
                case "4":
                    cView.tambahData();
                    break;
                case "5":
                    cView.updateData();
                    break;
                case "6":
                    cView.deleteData();
                    break;
                case "0":
                    System.out.println("\nTERIMAKASIH!!");
                    break menu;
                default:
                    System.err.println("\npilihan anda salah");
                    System.err.flush();
            }
        }
    }
    
    // method untuk melihat data
    public static void getAllData(){
        System.out.println("\n::: DATA BARANG :::");
        
//         call database, call dulu method yang memanggil database nya
        System.out.println(cConfig.getAllData());
    }
    
    public static void detailData(){
        System.out.print("\nMasukkan ID : ");
        String id = inputUser.next();
        
        System.out.println("\n::: DETAIL DATA :::");
        System.out.println(cConfig.detailData(id));
        
    }
    
    public static void cariData(){
        System.out.print("\nMasukkan keyword : ");
        String keyword = inputUser.next();
        
        System.out.println("\n::: DETAIL DATA :::");
        System.out.println(cConfig.cariData(keyword));
    }
    
    public static void tambahData(){
        System.out.println("\n::: TAMBAH DATA :::");
        System.out.print("Nama Barang : ");
        String namaBarang = inputUser.nextLine();
        System.out.print("Stok Barang : ");
        int stokBarang = inputUser.nextInt();
        System.out.print("Harga Barang:");
        int hargaBarang = inputUser.nextInt();
        
        if(cConfig.tambahBarang(namaBarang, stokBarang, hargaBarang)){
            System.out.println("-= DATA BERHASIL DITAMBAHKAN =-");
        }else {
            System.err.println("DATA TIDAK BERHASIL DITAMBAHKAN");
            System.err.flush();
        }
    }
    
    public static void updateData (){
        System.out.println("\n::: UPDATE DATA :::");
        System.out.print("ID Barang : ");
        int idBarang = inputUser.nextInt();
        inputUser.nextLine(); // biar baguss
        System.out.print("Nama Barang : ");
        String namaBarang = inputUser.nextLine();
        System.out.print("Stok Barang : ");
        int stokBarang = inputUser.nextInt();
        System.out.print("Harga Barang:");
        int hargaBarang = inputUser.nextInt();
        
        if(cConfig.tambahBarang(namaBarang, stokBarang, hargaBarang)){
            System.out.println("-= UPDATE BARANG BERHASIL =-");
        }else {
            System.err.println("UPDATE BARANG GAGAL");
            System.err.flush();
        }
    }
    
    public static void deleteData(){
        System.out.println("\n::: UPDATE DATA :::");
        System.out.print("ID Barang : ");
        int idBarang = inputUser.nextInt();
//        inputUser.nextLine(); // biar baguss
        
        if(cConfig.deleteData(idBarang)){
            System.out.println("-= HAPUS DATA BARANG BERHASIL =-");
        }else{
            System.err.println("HAPUS BARANG GAGAL  ");
            System.err.flush();
        }
    }
    
    
    
    
    
    
    
    
    
    
}
