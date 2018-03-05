package InterFaces;

import java.io.RandomAccessFile;
import java.time.LocalDate;

public interface IProduct {

	public void setName(String name);
	public String getName();
	
	public void setPurchaseDate(LocalDate date);
	public LocalDate getPurchaseDate();
	
	public LocalDate getExpireDate();
	
	public void setWarrantyDurationMonths(int warrantyDurationMonths);
	public int getWarrantyDurationMonths();
	
	public void writeProduct(RandomAccessFile raf);
	
	public String toString();
	public String toStringPurchaseDate();
	public String toStringExpireDate();
	
	public int compareTo(IProduct product);
	
	
	
}
