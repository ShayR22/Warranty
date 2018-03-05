package Product;

import java.time.LocalDate;
import java.util.TreeSet;
import InterFaces.IProduct;
import InterFaces.IStatistics;

public class AllProductStats implements IStatistics {

	private TreeSet<IProduct> allProducts;

	
	public AllProductStats() {
		this.allProducts = new TreeSet<IProduct>();
	}
	
	
	@Override
	public void addProduct(IProduct newCourse) {
		this.allProducts.add(newCourse);
	}

	@Override
	public int getNumberOfProducts() {
		return this.allProducts.size();
	}

	@Override
	public void setTreeSet(TreeSet<IProduct> treeSet) {
		if(treeSet != null) {
			this.allProducts = treeSet;
		}
		
	}

	@Override
	public TreeSet<IProduct> getAllProducts() {
		return this.allProducts;
	}
	

	@Override
	public TreeSet<IProduct> getByYearProducts(int year) {
		TreeSet<IProduct> temp = new TreeSet<IProduct>();
		for (IProduct iProduct : this.allProducts) {
			if(iProduct.getExpireDate().getYear() == year) {
				temp.add(iProduct);
			}
		}
		
		return temp;
	}

	@Override
	public TreeSet<IProduct> getByYearAndMonthProducts(int year, int month) {
		TreeSet<IProduct> temp = new TreeSet<IProduct>();
		for (IProduct iProduct : this.allProducts) {
			 if(iProduct.getExpireDate().getYear() == year && iProduct.getExpireDate().getMonthValue() == month) {
				System.out.println(iProduct.getExpireDate().getYear() + " --- " + iProduct.getExpireDate().getMonthValue());
				temp.add(iProduct);
			}
		}
	
		return temp;	
	
	}


	@Override
	public void refreshProducts(LocalDate localDate) {
		TreeSet<IProduct> temp = new TreeSet<IProduct>();
		
		for (IProduct iProduct : allProducts) {
			if(iProduct.getExpireDate().compareTo(localDate) >= 0) {
				temp.add(iProduct);
			}
		}
		this.allProducts = temp;
	}
	
	
	
}
