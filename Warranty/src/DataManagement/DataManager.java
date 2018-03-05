package DataManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.TreeSet;

import InterFaces.IController;
import InterFaces.IProduct;
import Product.AllProductStats;
import Product.Product;
import InterFaces.IDataManager;



public class DataManager implements IDataManager{

	private final String DIR_NAME = "Data (dont touch me and dont delete me)";
	private final String RW = "rw";
	private final String FILE_NOT_FOUND_EXCEPTION = "File does not exist or wrong input for path";
	private final String IO_EXCEPTION = "A problem accured in I/O data to/from the file";
	
	private RandomAccessFile raf;
	
	private AllProductStats allProductsStats; 

	private FileIterator<IProduct> myIterator;
	
	private String fileName;
	
	private IController controller;
	
	public DataManager(String fileName , IController icm) {
		
		String d = DIR_NAME;
		File f = new File(d);
		f.mkdir();


		this.fileName = fileName;
		this.controller = icm;
		allProductsStats = new AllProductStats();

		try {
			
			raf = new RandomAccessFile(f.getAbsolutePath() + "/" +this.fileName, RW);
			myIterator = new FileIterator<IProduct>(raf);
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println(FILE_NOT_FOUND_EXCEPTION);
		}
	}

	public void loadExistingData() {
		try {
			while(myIterator.hasPrevious()) {
				myIterator.previous();
			}
			
			while(raf.getFilePointer() < raf.length()) {
				IProduct temp = myIterator.next();
				if(temp != null) {
					allProductsStats.addProduct(temp);
					
				}
			}
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
		
		for (IProduct ic : allProductsStats.getAllProducts()) {
			controller.notify(ic , false);
		}
	}

	@Override
	public void notify(String[] data, boolean cd) {
		IProduct p = null;
		if(cd) {
			int year = Integer.valueOf(data[1].trim());
			int month = Integer.valueOf(data[2].trim());
			int day = Integer.valueOf(data[3].trim());
			
			LocalDate pDate = LocalDate.of(year, month , day);
			
			int warranty = Integer.valueOf(data[4].trim()); 
			p = new Product(data[0], pDate , warranty);
			myIterator.add(p);
			this.allProductsStats.addProduct(p);
		}
		else {
			deleteCourseFromDataBase(data);
		}
		
		if(p != null) {
			this.controller.notify(p , false);
		}
	}
	

	@Override
	public void notify(boolean update) {
	//	LocalDate now = LocalDate.now();
	//	this.allProductsStats.refreshProducts(now);
		
		cleanAndResetSystem();
	}
	
	
	private void deleteCourseFromDataBase(String data[]) {
		for (IProduct iCourse : this.allProductsStats.getAllProducts()) {
			if(compareCoursesByData(data, iCourse)) {
				this.allProductsStats.getAllProducts().remove(iCourse);
				break;
			}
		}
		cleanAndResetSystem();
		
	}
	
	private boolean compareCoursesByData(String data[] , IProduct c2) {
		LocalDate c2Date = c2.getPurchaseDate();
		
		if(data[0].trim().compareTo(c2.getName().trim()) != 0) {
			return false;
		}
		else if((Integer.valueOf(data[1])) != c2Date.getYear()) {
			return false;
		}
		else if(Double.valueOf(data[2]) != c2Date.getMonthValue()){
			return false;
		}
		else if(Integer.valueOf(data[3]) != c2Date.getDayOfMonth()) {
			return false;
		}
		else if(Integer.valueOf(data[4]) != c2.getWarrantyDurationMonths()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private void cleanAndResetSystem() {
		myIterator.trancuteData();
		this.controller.notify(null, true);
		
		
		for (IProduct iCourse : this.allProductsStats.getAllProducts()) {
			myIterator.add(iCourse);
			this.controller.notify(iCourse, false);
		}	
	}

	@Override
	public void notify(boolean sortAll, boolean sortYear , int year , int month) {
		TreeSet<IProduct> temp = new TreeSet<IProduct>();
		if(sortAll) {
			temp = this.allProductsStats.getAllProducts();
		}
		else {
			if(sortYear) {
				temp = this.allProductsStats.getByYearProducts(year);
			}
			else {
				temp = this.allProductsStats.getByYearAndMonthProducts(year, month);
			}
		}
		this.controller.notify(null, true);
		
		for (IProduct iCourse : temp) {
			this.controller.notify(iCourse, false);
		}	
		
		
	}
		
}






