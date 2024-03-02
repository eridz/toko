import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.HashMap;

public class TokoKomputer {
	public static void main (String[] args) {
        Boolean selectItem = true;
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
        
     	String massage = "";
		while (selectItem == true) {
			int indexProductSelected = 0; 
			
            Function.renderProductsList(Products, currencyFormat, solidHr, dotedHr, massage);
            
            String selectProduct = scanner.nextLine().toLowerCase();
			
			for (int i = 0; i < Products.length; i++) {
				if (selectProduct.equals(Products[i].Name().toLowerCase())) {
					indexProductSelected = i + 1;
				}
			}
			
			if (indexProductSelected != 0) {
                int pcs = 1;
                Boolean inputOptionValid = true;
                massage = "\nAnda Telah Memasukkan \'" + Products[indexProductSelected - 1].Name() + "\'";
                
                for (HashMap.Entry<Integer, Integer> entry : shooper.entrySet()) {
                    if (entry.getKey() == indexProductSelected) {
                        pcs += entry.getValue();
                        massage = "\nAnda Telah Menambah PCS Produk \'" + Products[indexProductSelected - 1].Name() + "\'";
                    }
                }
                shooper.put(indexProductSelected, pcs);
                
                while (inputOptionValid) {
                    Function.renderShooper(shooper, Products, currencyFormat, solidHr, dotedHr);
                    System.out.printf("%s\nKetik $ (bayar) atau + (tambah) atau PCS (jumlah PCS) : ", massage);
                    massage = "";
                    
                    String choiceMenu = scanner.nextLine();
                    if (choiceMenu.toLowerCase().equals("$")) {
                        selectItem = false;
                        inputOptionValid = false;
                    } else if (choiceMenu.toLowerCase().equals("+")) {
                        selectItem = true;
                        inputOptionValid = false;
                    } else if (choiceMenu.toLowerCase().equals("pcs")) {
                        Function.renderShooper(shooper, Products, currencyFormat, solidHr, dotedHr);
                        System.out.println("\nSilahkan Masukkan Nama Barang Yang Ingin Diubah Jumlah PCS-nya!");
                        String changePCS = scanner.nextLine().toLowerCase();
                        Boolean validName = true;
                        int key = 0, countPCS = 0;
                        
                        for (HashMap.Entry<Integer, Integer> entry : shooper.entrySet()) {
                            if (changePCS.equals(Products[entry.getKey() - 1].Name().toLowerCase())) {
                                validName = false;
                                System.out.printf("\nMasukkan Jumlah PCS Untuk Produk \'%s\' : ", Products[entry.getKey() - 1].Name());
                                countPCS = (int) Double.parseDouble(scanner.nextLine());
                                key = entry.getKey();
                            }
                        }
                        
                         if (countPCS == 0) {
                            massage = "\nAnda Telah Mengeluarkan \'" + Products[key - 1].Name() + "\' Dari Keranjang!! \nMengubah PCS Menjadi 0 Berarti Mengeluarkan Produk!!";
                            shooper.remove(key);
                        } else if (countPCS > 0) {
                            massage = "\nAnda Telah Mengubah PCS \'" + Products[key - 1].Name() + "\' Menjadi " + countPCS +  " !!";
                            shooper.put(key, countPCS);
                        } else if (countPCS < 0) {
                            massage = "\nNilai Harus Lebih Dari 0 (Nol), Mengubah PCS Dibatalkan!!";
                        }
                        
                        if (validName) {
                            massage = "\nProduk Tidak Ada Di Keranjang, Mengubah PCS Dibatalkan!!";
                        }
                    } else {
                        massage = "\nOpsi \'" + choiceMenu + "\' Tidak Tersedia, Pilih Opsi Yang Tersedia Dibawah Ini!!";
                    }
                }
                
            } else {
                massage = "\nInput Anda Tidak Valid! Mungkin Terdapat Kesalahan Dalam Mengetik!";
	        }
		}
        System.out.printf("\n_%s\n|\n|\t\t\t    TOKO JAYA KOMPUTER \n|\t\t\t JL.Juanda No.64 Surabaya \n|%s\n", solidHr, solidHr);
		int totalBiaya = 0;
        for (HashMap.Entry<Integer, Integer> entry : shooper.entrySet()) {
		    int biaya = Products[entry.getKey() - 1].Prize() * entry.getValue();
			totalBiaya += biaya;
            System.out.printf("| %s %s: %s\n", entry.getValue().toString(), Function.addTab(Products[entry.getKey() - 1].Name(), 4), currencyFormat.format(biaya));
		}
		System.out.printf("%s\n| * TOTAL BIAYA\t\t\t\t\t: %s\n|%s", dotedHr, currencyFormat.format(totalBiaya), solidHr);
	}
}

class Function {
    public static String renderShooper(HashMap<Integer, Integer> shooper, Product[] Products, NumberFormat currencyFormat, String solidHr, String dotedHr) {
        System.out.printf("\n_%s\n| PCS  NAMA BARANG \t\t\t\t| HARGA\n%s\n", solidHr, dotedHr);
        
        for (HashMap.Entry<Integer, Integer> item : shooper.entrySet()) {
            System.out.printf("|  %s   %s: %s\n", item.getValue().toString(), addTab(Products[item.getKey() - 1].Name(), 7), currencyFormat.format(Products[item.getKey() - 1].Prize()), item.getValue().toString());
        }
    return "";}
    
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
    return result;}
    
    public static String renderProductsList(Product[] Products, NumberFormat currencyFormat, String solidHr, String dotedHr, String massage) {
        System.out.printf("\n%s_\n|\n| List Produk Yang Tersedia\n%s\n",solidHr, dotedHr);
        
        for (int i = 0; i < Products.length; i++) {
            System.out.printf("| %s- %s \n", addTab(Products[i].Name(), 2), currencyFormat.format(Products[i].Prize()));
        }
        
        System.out.printf("%s\nTulis Nama Barang Dengan \'Lengkap\' Untuk Memilih!\n", massage);
    return "";}
}

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
