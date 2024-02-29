//package TokoKomputer;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.HashMap;

public class TokoKomputer {
    public static String addTab(String text, int start) {
        String result = "";
        if (text.length() < (8 - start)) {
            result = text + "\t\t\t\t\t\t";
        } else if (text.length() < (16 - start)) {
            result = text + "\t\t\t\t\t";
        } else if (text.length() < (24 - start)) {
            result = text + "\t\t\t\t";
        } else if (text.length() < (32 - start)) {
            result = text + "\t\t\t";
        } else if (text.length() < (40 - start)) {
            result = text + "\t\t";
        } else if (text.length() < (48 - start)) {
            result = text + "\t";
        }
        return result;
    }
    
    public static String renderProductsList(Product[] Products, NumberFormat currencyFormat, String solidHr, String dotedHr) {
        System.out.printf("%s_\n|\n| List Produk Yang Tersedia\n%s\n",solidHr, dotedHr);
            // menampilkan list produk dengan looping
        for (int i = 0; i < Products.length; i++) {
            System.out.printf("| %s- %s \n", addTab(Products[i].Name(), 2), currencyFormat.format(Products[i].Prize()));
        }
        System.out.println("\nTulis Nama Barang Dengan \'Lengkap\' Untuk Memilih!");
    return "";}
    
    public static String renderShooper(HashMap<Integer, Integer> shooper, Product[] Products, NumberFormat currencyFormat, String solidHr, String dotedHr) {
        System.out.printf("\n_%s\n| PCS  NAMA BARANG \t\t\t\t| HARGA\n%s\n", solidHr, dotedHr);
        for (HashMap.Entry<Integer, Integer> item : shooper.entrySet()) {
            System.out.printf("|  %s   %s: %s\n", item.getValue().toString(), addTab(Products[item.getKey() - 1].Name(), 7), currencyFormat.format(Products[item.getKey() - 1].Prize()), item.getValue().toString());
        }
    return "";}
    
	public static void main (String[] args) {
        Boolean selectItem = true;
        Boolean showListProducts = true;
		Scanner scanner = new Scanner(System.in);
		HashMap<Integer, Integer> shooper = new HashMap<>();
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		String dotedHr = "|--------------------------------------------------------------------|";
		String solidHr = "_____________________________________________________________________";
		
		Product[] Products = {
            new Product("Tinta Printer", 95000), 
            new Product("Mouse Wireless Goojodoq", 65000), 
            new Product("Flashdisk 64GB", 87500), 
            new Product("Kabel Konektor", 15000), 
            new Product("Paket Anti Virus", 200000), 
            new Product("Keyboard Wireless", 850000),
            new Product("Mouse Pad TAFFGO MP002", 80000),
            new Product("Earphone Bluetooth TWS A30S", 98000),
            new Product("VGA NVIDIA RTX 3080", 37820000),
            new Product("Arduino Uno", 53500),
            new Product("ESP 8266", 37800)
        };
        
     	
		while (selectItem == true) {
            String itemNameSelected = "";
			int indexItemSelected = 0; 
			
			if (showListProducts == true) {
                renderProductsList(Products, currencyFormat, solidHr, dotedHr);
            }
            // menyimpan pilihan pengguna
            itemNameSelected = scanner.nextLine().toLowerCase();
			
			for (int i = 0; i < Products.length; i++) {
                // mengecek apakah pilihan valid
				if (itemNameSelected.equals(Products[i].Name().toLowerCase())) {
				    // jika valid simpan index produk
					indexItemSelected = i + 1;
				} else {
				    // list produk tidak akan ditampilkan
					showListProducts = false;
				}
			}
			
			// jika index != 0 maka akan dijalankan
			if (indexItemSelected != 0) {
                // inisialisasi nilai default pcs 
                int pcs = 1;
                // inisialisasi nilai default inputOptionValid 
                Boolean inputOptionValid = true;
                // melooping keranjang belanjaan
                for (HashMap.Entry<Integer, Integer> entry : shooper.entrySet()) {
                    // jika barang terpilih sudah ada di keranjang
                    if (entry.getKey() == indexItemSelected) {
                        // tambahkan 1 pcs lagi
                        pcs += entry.getValue();
                    }
                }
                
                // memperbarui barang dan pcs di keranjang belanjaan
                shooper.put(indexItemSelected, pcs);
                renderShooper(shooper, Products, currencyFormat, solidHr, dotedHr);
                // menampilkan dialog opsi ( $ / + / PCS )
                System.out.printf("\nAnda Telah Menambahkan \'%s\'\nKetik $ (bayar) atau + (tambah) atau PCS (Change PCS) : ", Products[indexItemSelected - 1].Name());
                
                // mengecek nilai inputOptionValid (default = true)
                while (inputOptionValid) {
                    // meminta pengguna memilih opsi ( $ / + / PCS )
                    String inputOption = scanner.nextLine().toLowerCase();
                    // mengecek Pilih pengguna
                    if (inputOption.equals("$")) {
                        // memberhentikan pengguna untuk memilih barang
                        selectItem = false;
                        // memberhentikan meminta pengguna memilih opsi ( $ / + / PCS )
                        inputOptionValid = false;
                    } else if (inputOption.equals("+")) {
                        // mengulangi pengguna untuk memilih barang
                        selectItem = true;
                        // memberhentikan meminta pengguna memilih opsi ( $ / + / PCS )
                        inputOptionValid = false;
                        // menampilkan list produk
                        showListProducts = true;
                    } else if (inputOption.equals("pcs")) {
                        renderShooper(shooper, Products, currencyFormat, solidHr, dotedHr);
                        System.out.println("\nSilahkan Masukkan Nama Barang Yang Ingin Diubah Jumlah PCS-nya!");
                        String ChangePCS = scanner.nextLine().toLowerCase();
                        for (HashMap.Entry<Integer, Integer> entry : shooper.entrySet()) {
                            if (ChangePCS.equals(Products[entry.getKey() - 1].Name().toLowerCase())) {
                                System.out.printf("\nMasukkan Jumlah PCS Untuk Produk \'%s\' : ", Products[entry.getKey() - 1].Name());
                                int countPCS = scanner.nextInt();
                                if (countPCS > 0) {
                                    shooper.put(entry.getKey(), countPCS);
                                    renderShooper(shooper, Products, currencyFormat, solidHr, dotedHr);
                                    System.out.printf("\nAnda Telah Mengubah PCS Produk \'%s\'\n", Products[entry.getKey() - 1].Name());
                                } else {
                                    System.out.print("\nInput \'Tidak Valid\' Merubah Jumlah PCS Dibatalkan!!\n");
                                    renderShooper(shooper, Products, currencyFormat, solidHr, dotedHr);
                                }
                            }
                        }
                    } else {
                        // menampilkan dialog jika input tidak Valid
                        System.out.print("Ketik $ (bayar) atau + (tambah) atau PCS (Change PCS) : ");
                    }
                }
            // menampilkan dialog jika input tidak Valid
            } else {
                // inisialisasi underlineDash dengan String kosong
                String underlineDash = "";
                // membuat underline pada input tidak Valid
                for (int i = 0; i < itemNameSelected.length(); i++) {
                    // menambahkan - sebanyak karakter inputnya
                    underlineDash += "-";
                }
                System.out.printf("%s\nTidak Valid! Silahkan Masukkan Nama Yang Sesuai!!\n", underlineDash);
	        }
		}
        // menampilkan header 'nota'
        System.out.printf("_%s\n|\n|\t\t\t    TOKO JAYA KOMPUTER \n|\t\t\t JL.Juanda No.64 Surabaya \n|%s\n", solidHr, solidHr);
        // inisialisasi totalBiaya dengan nilai 0
		int totalBiaya = 0;
		// melooping isi keranjang untuk 'nota'
        for (HashMap.Entry<Integer, Integer> entry : shooper.entrySet()) {
            // mendeklarasikan nilai biaya pada setiap barang
		    int biaya = Products[entry.getKey() - 1].Prize() * entry.getValue();
		    // menjumlahkan nilai biaya
			totalBiaya += biaya;
			// menampilkan isi keranjang
            System.out.printf("| %s %s: %s\n", entry.getValue().toString(), addTab(Products[entry.getKey() - 1].Name(), 4), currencyFormat.format(biaya));
		}
        // menampilkan total biaya
		System.out.printf("%s\n| * TOTAL BIAYA\t\t\t\t\t: %s\n|%s", dotedHr, currencyFormat.format(totalBiaya), solidHr);
	}
}
// membuat class baru yang berfungsi seperti objek di javascript 
class Product {

	private String _name;
    private int _prize;

    public Product(String name, int prize) {
        this._prize = prize;
        this._name = name;
    }

    public String Name() {
        return this._name;
    }

    public int Prize() {
        return this._prize;
    }
}
