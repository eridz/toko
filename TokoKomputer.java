import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.HashMap;

public class TokoKomputer {
    public static String addTab(String text, int start) {
        String result = "";
        if (text.length() < (8 - 2)) {
            result = text + "\t\t\t\t\t";
    	} else if (text.length() < (16 - start)) {
            result = text + "\t\t\t\t";
    	} else if (text.length() < (24 - start)) {
            result = text + "\t\t\t";
    	} else if (text.length() < (32 - start)) {
            result = text + "\t\t";
    	} else if (text.length() < (40 - start)) {
            result = text + "\t";
        }
    	
    	return result;
    }
    
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		HashMap<Integer, Integer> shooper = new HashMap<>();
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		String dotedHr = "|---------------------------------------------------------|";
		String solidHr = "__________________________________________________________";
		
		Product[] Products = {
    	new Product("Tinta Printer", 95000), 
    	new Product("Mouse Wireless", 65000), 
    	new Product("Flashdisk 64GB", 87500), 
    	new Product("Kabel Konektor", 15000), 
    	new Product("Paket Anti Virus", 200000), 
    	new Product("Keyboard Wireless", 850000),
    	new Product("Mouse Pad", 80000),
    	new Product("Earphone Bluetooth TWS A30S", 98000),
    	new Product("VGA NVIDIA RTX 3080", 37820000)
    	
    	};
    	
		while (true) {
			System.out.printf("%s_\n|\n| List Produk Yang Tersedia\n%s\n",solidHr, dotedHr);
			for (int i = 0; i < Products.length; i++) {
		       	 System.out.printf("| %s- %s \n",addTab(Products[i].Name(), 2), currencyFormat.format(Products[i].Prize()));
			}
			
			System.out.println("\nTulis Nama Barang Dengan \"Lengkap\" Untuk Memilih!");
			String itemName = scanner.nextLine().toUpperCase();
			
			int item = 0; 
			
			for (int i = 0; i < Products.length; i++) {
				if (itemName.equals(Products[i].Name().toUpperCase())) {
					item = i + 1;
				}
			}
			
			if (item <= Products.length && item > 0) {
			      	
	        	int pcs = 1;
		        for (HashMap.Entry<Integer, Integer> entry : shooper.entrySet()) {
			        if (entry.getKey() == item) {
			        	pcs += entry.getValue();
			        }
		        }
		        
		        shooper.put(item, pcs);
		        System.out.println("\n_" + solidHr + "\n| PCS  NAMA BARANG \t\t\t| HARGA\n" + dotedHr);
		        for (HashMap.Entry<Integer, Integer> entry : shooper.entrySet()) {
    	       		System.out.printf("|  %s   %s: %s\n", entry.getValue().toString(), addTab(Products[entry.getKey() - 1].Name(), 7), currencyFormat.format(Products[entry.getKey() - 1].Prize()), entry.getValue().toString());
		        }
		        System.out.printf("\nAnda Telah Memasukkan => %s\nKetik $ (bayar) atau + (tambah) atau - (mengurangi)\n", Products[item - 1].Name());
		        String end = scanner.nextLine().toLowerCase();
		        if (end.equals("$")) {
					int totalBiaya = 0;
		        	System.out.printf("_%s\n|\n|\t\t    TOKO JAYA KOMPUTER \n|\t\t JL.Juanda No.64 Surabaya \n|%s\n", solidHr, solidHr);
				    for (HashMap.Entry<Integer, Integer> entry : shooper.entrySet()) {
				    	int biaya = Products[entry.getKey() - 1].Prize() * entry.getValue();
			    	  	System.out.printf("| %s %s: %s\n", entry.getValue().toString(), addTab(Products[entry.getKey() - 1].Name(), 4), currencyFormat.format(biaya));
					    totalBiaya += biaya;
					}
					System.out.println(dotedHr);
					System.out.printf("| * TOTAL BIAYA\t\t\t\t: %s\n|%s", currencyFormat.format(totalBiaya), solidHr);
	    			return;
		        }
	        } else {
	        	System.out.println(solidHr + "_\nInputan Tidak Valid. Silahkan Masukkan Ulang!");
	        }
		}
	}
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
