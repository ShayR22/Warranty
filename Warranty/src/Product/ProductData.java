package Product;

import java.time.LocalDate;

import InterFaces.IProduct;

public class ProductData {
	
	
	private String name;
	
	private LocalDate purchaseDate;
	private LocalDate expiredDate;
	
	private int warrantyInMonths;
	
	private int yearSearch;
	private int monthSearch;

	private boolean isSearching;
	private boolean isSearchingAll;
	private boolean isSearchingOnlyYear;
	private boolean isUpdate;
	private boolean isCreating;

	
	public ProductData(IProduct product , boolean isCreating , boolean isUpdate ,boolean isSearching , boolean isSearchingAll ,boolean isSearchingOnlyYear ) {
		if(product !=null) {
			this.name = product.getName();
			this.purchaseDate = product.getPurchaseDate();
			this.expiredDate = product.getExpireDate();
			this.warrantyInMonths = product.getWarrantyDurationMonths();
			this.yearSearch = this.purchaseDate.getYear();
			this.monthSearch =  this.purchaseDate.getMonthValue();
		}
		this.isCreating = isCreating;
		this.isUpdate = isUpdate;
		this.isSearching = isSearching;
		this.isSearchingAll = isSearchingAll;
		this.isSearchingOnlyYear = isSearchingOnlyYear;
		
		
	}
	
	public boolean isSearchingOnlyYear() {
		return isSearchingOnlyYear;
	}

	public boolean isUpdate() {
		return isUpdate;
	}

	public boolean isCreating() {
		return isCreating;
	}
	
	public void setCreating(boolean create) {
		this.isCreating = create;
	}

	public void setSearch(int year , int month) {
		this.yearSearch = year;
		this.monthSearch = month;
	}

	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public int getWarrantyInMonths() {
		return warrantyInMonths;
	}

	public int getYearSearch() {
		return yearSearch;
	}

	public int getMonthSearch() {
		return monthSearch;
	}

	public boolean isSearching() {
		return isSearching;
	}

	public boolean isSearchingAll() {
		return isSearchingAll;
	}

	public void setSearching(boolean isSearching) {
		this.isSearching = isSearching;
	}

	public String getName() {
		return name;
	}


	
	
		
}
