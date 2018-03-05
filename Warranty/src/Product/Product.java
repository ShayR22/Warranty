package Product;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

import DataManagement.FixedLengthStringIO;

import InterFaces.IProduct;

public class Product implements IProduct , Comparable<IProduct> {
	
	public static final int NAME_SIZE = 50;
	
	public static final int EXPIRED_DATE_YEAR_SIZE = 4;
	public static final int EXPIRED_DATE_MONTH_SIZE = 2;
	public static final int EXPIRED_DATE_DAY_SIZE = 2;
	
	public static final int PURCHASE_DATE_YEAR_SIZE = 4;
	public static final int PURCHASE_DATE_MONTH_SIZE = 2;
	public static final int PURCHASE_DATE_DAY_SIZE = 2;
	
	public static final int WARRANTY_SIZE = 3;
	
	public static final int TOTAL_SIZE = EXPIRED_DATE_YEAR_SIZE + EXPIRED_DATE_MONTH_SIZE + EXPIRED_DATE_DAY_SIZE +
										 PURCHASE_DATE_YEAR_SIZE + PURCHASE_DATE_MONTH_SIZE + PURCHASE_DATE_DAY_SIZE 
										 + WARRANTY_SIZE;
	
	private String name;
	private LocalDate purchaseDate;
	private LocalDate expireDate;
	private int warrantyDurationMonths;
	
	
	public Product(String name , LocalDate purchaseDate , int warrantyDurationMonths) {

		this.name = name;
		this.purchaseDate = purchaseDate;
		this.warrantyDurationMonths = warrantyDurationMonths;
		

		int expiredYears = warrantyDurationMonths/12 + this.purchaseDate.getYear();
		int expiredMonth = warrantyDurationMonths%12 + this.purchaseDate.getMonthValue();
		if(expiredMonth > 12) {
			expiredMonth %= 12;
			expiredYears++;
		}
		
		 this.expireDate = LocalDate.of(expiredYears, expiredMonth, this.purchaseDate.getDayOfMonth());
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return "Product [name=" + name + ", purchaseDate=" + purchaseDate + ", expireDate=" + expireDate
				+ ", warrantyDurationMonths=" + warrantyDurationMonths + "]";
	}

	
	
	@Override
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	
	@Override
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	@Override
	
	public LocalDate getExpireDate() {
		return expireDate;
	}

	@Override
	public int getWarrantyDurationMonths() {
		return warrantyDurationMonths;
	}

	@Override
	public void setWarrantyDurationMonths(int warrantyDurationMonths) {
		this.warrantyDurationMonths = warrantyDurationMonths;
	}

	@Override
	public void writeProduct(RandomAccessFile raf) {
		try {
			FixedLengthStringIO.writeFixedLengthString(this.name, NAME_SIZE, raf);
			
			FixedLengthStringIO.writeFixedLengthString("" + this.purchaseDate.getYear(), PURCHASE_DATE_YEAR_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString("" + this.purchaseDate.getMonthValue(), PURCHASE_DATE_MONTH_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString("" + this.purchaseDate.getDayOfMonth(), PURCHASE_DATE_DAY_SIZE, raf);
			
			
			FixedLengthStringIO.writeFixedLengthString("" + this.expireDate.getYear(), EXPIRED_DATE_YEAR_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString("" + this.expireDate.getMonthValue(), EXPIRED_DATE_MONTH_SIZE, raf);
			FixedLengthStringIO.writeFixedLengthString("" + this.expireDate.getDayOfMonth(), EXPIRED_DATE_DAY_SIZE, raf);
			
			
			FixedLengthStringIO.writeFixedLengthString("" + this.warrantyDurationMonths, WARRANTY_SIZE , raf);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	

	@Override
	public int compareTo(IProduct product) {
		
		if(this.name.toLowerCase().compareTo(product.getName().toLowerCase()) == 0) {
			if(this.purchaseDate.compareTo(product.getPurchaseDate()) == 0) {
				if(this.warrantyDurationMonths == product.getWarrantyDurationMonths()) {
					return 0;
				}
				else if(this.warrantyDurationMonths > product.getWarrantyDurationMonths()) {
					return 1;
				}
				else {
					return -1;
				}
			}
			else {
				return this.purchaseDate.compareTo(product.getPurchaseDate());
			}
		}
		else {
			return this.name.toLowerCase().compareTo(product.getName().toLowerCase());
		}

		
	}

	@Override
	public String toStringPurchaseDate() {
		String year , month ,day;
		year = "" + this.purchaseDate.getYear();
		
		
		if(this.purchaseDate.getMonthValue() < 10) {
			month = "0" + this.purchaseDate.getMonthValue();
		}
		else {
			month = "" + this.purchaseDate.getMonthValue();
		}
		if(this.purchaseDate.getDayOfMonth() < 10) {
			day = "0" + this.purchaseDate.getDayOfMonth();
		}
		else {
			day = "" + this.purchaseDate.getDayOfMonth();
		}
		
		
		
		String purchaseDate = day + "." + month + "." + year;
		return purchaseDate;
	}

	@Override
	public String toStringExpireDate() {
		String year , month ,day;
		year = "" + this.expireDate.getYear();
		
		
		if(this.expireDate.getMonthValue() < 10) {
			month = "0" + this.expireDate.getMonthValue();
		}
		else {
			month = "" + this.expireDate.getMonthValue();
		}
		if(this.expireDate.getDayOfMonth() < 10) {
			day = "0" + this.expireDate.getDayOfMonth();
		}
		else {
			day = "" + this.expireDate.getDayOfMonth();
		}
		
		
		
		String expireDate = day + "." + month + "." + year;
		return expireDate;
	}



	



}
