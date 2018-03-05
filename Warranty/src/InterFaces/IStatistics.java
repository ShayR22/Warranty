package InterFaces;

import java.time.LocalDate;
import java.util.TreeSet;

public interface IStatistics {

	public void addProduct(IProduct newCourse);
	public int getNumberOfProducts();
	public void setTreeSet(TreeSet<IProduct> treeSet);
	public TreeSet<IProduct> getAllProducts();
	public TreeSet<IProduct> getByYearAndMonthProducts(int year , int month);
	public TreeSet<IProduct> getByYearProducts(int year);
	public void refreshProducts(LocalDate localDate);
	
	
}
