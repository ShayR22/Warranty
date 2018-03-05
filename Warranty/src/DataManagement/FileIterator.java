package DataManagement;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ListIterator;

import InterFaces.IProduct;
import Product.Product;


public class FileIterator <T extends IProduct> implements ListIterator<T>{

	//private final String RW = "rw";
	//private final String FILE_NOT_FOUND_EXCEPTION = "File does not exist or wrong input for path";
	private final String IO_EXCEPTION = "A problem accured in I/O data to/from the file";
	//private final String CLASS_NOT_FOUND_EXCEPTION = "There is a problem in classPath"; //TODO check should be thrown somewhere
	
	
	
	
	private final int IPRODUCT_TOTAL_SIZE = (Product.TOTAL_SIZE)*2; // *2 cuz of unicode


	private int index;
	private RandomAccessFile raf;
	private int size;
	
	public FileIterator(RandomAccessFile raf) {
		this.size =0;
		this.index =0;
		
		this.raf = raf;

		// if got a file that is not empty get the number of Semesters in it
		try {
			while (raf.getFilePointer() + (size * IPRODUCT_TOTAL_SIZE) < raf.length()) {
				size++;
			}
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
	}
	
	
	public void trancuteData() {
		this.size = 0;
		this.index = 0;
		try {
			raf.setLength(0);
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
	}
	
	@Override
	public void add(T e) {
		try {
			raf.seek(raf.length());
			e.writeProduct(raf);
			size++;
			index = size;
		} catch (IOException e1) {
			System.out.println(IO_EXCEPTION);
		}
		
	}

	@Override
	public boolean hasNext() {
		if (index < size) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasPrevious() {
		if (index > 0) {
			return true;
		}
		return false;
	}

	@Override
	public T next() {
		T temp = null;
		if (hasNext()) {
			temp = getNextICourse();
			index++;
			return temp;
		}
		return temp;
	}

	@Override
	public int nextIndex() {
		if (hasNext()) {
			return index + 1;
		}
		return -1;
	}

	@Override
	public T previous() {
		if(hasPrevious()) {
			try {
				index--;
				raf.seek(index * IPRODUCT_TOTAL_SIZE);
				
				T temp = getNextICourse();
				// set pointer in right place
				raf.seek(index * IPRODUCT_TOTAL_SIZE);

				return temp;

			} catch (IOException e1) {
				System.out.println(IO_EXCEPTION);
			}
		}
		return null;
	}


	@Override
	public int previousIndex() {
		if (hasPrevious()) {
			return index - 1;
		}
		return -1;
	}

	@Override
	public void remove() {
		int tempIndex = this.index - 1;
		ArrayList<T> allAfterDeleted = new ArrayList<T>();
		while(hasNext()) {
			allAfterDeleted.add(next());
		}
		try {
			raf.setLength(tempIndex * IPRODUCT_TOTAL_SIZE);
			this.index = tempIndex;
			for (T iCourse : allAfterDeleted) {
				add(iCourse);
			}
			
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
		
		
		
		
	}

	@Override
	public void set(T e) {
//		try {
//			// set pointer location to current Contact info (without (after) id)
//			// and rewrite it.
//			raf.seek(raf.getFilePointer() - (IPRODUCT_TOTAL_SIZE));
//			ICourse temp = (ICourse) e;
//			
//			FixedLengthStringIO.writeFixedLengthString(temp.getName(), Course.NAME_SIZE, raf);
//			FixedLengthStringIO.writeFixedLengthString("" + temp.getScore(), Course.SCORE_SIZE, raf);
//			FixedLengthStringIO.writeFixedLengthString("" + temp.getCreditPoints() , Course.CREDIT_SIZE, raf);
//		} catch (IOException ex) {
//			System.out.println(IO_EXCEPTION);
//		}
	}
	

	
	private T getNextICourse() {
		try {
			String name , pYearString , pMonthString , pDayString , eYearString , eMonthString , eDayString , warrantyString;
			int pYear , pMonth , pDay , eYear , eMonth , eDay , warranty;
			
			name = FixedLengthStringIO.readFixedLengthString(Product.NAME_SIZE, raf).trim();
			
			pYearString = FixedLengthStringIO.readFixedLengthString(Product.PURCHASE_DATE_YEAR_SIZE , raf).trim();
			pMonthString = FixedLengthStringIO.readFixedLengthString(Product.PURCHASE_DATE_MONTH_SIZE , raf).trim();
			pDayString = FixedLengthStringIO.readFixedLengthString(Product.PURCHASE_DATE_DAY_SIZE , raf).trim();
			
			eYearString = FixedLengthStringIO.readFixedLengthString(Product.EXPIRED_DATE_YEAR_SIZE , raf).trim();
			eMonthString = FixedLengthStringIO.readFixedLengthString(Product.EXPIRED_DATE_MONTH_SIZE , raf).trim();
			eDayString = FixedLengthStringIO.readFixedLengthString(Product.EXPIRED_DATE_DAY_SIZE , raf).trim();

			warrantyString = FixedLengthStringIO.readFixedLengthString(Product.WARRANTY_SIZE , raf).trim();
			
			pYear = Integer.valueOf(pYearString);
			pMonth = Integer.valueOf(pMonthString);
			pDay = Integer.valueOf(pDayString);
			
			eYear = Integer.valueOf(eYearString);
			eMonth = Integer.valueOf(eMonthString);
			eDay = Integer.valueOf(eDayString);
			
			warranty = Integer.valueOf(warrantyString);
			
			LocalDate pDate = LocalDate.of(pYear, pMonth, pDay);
			LocalDate eDate = LocalDate.of(eYear, eMonth, eDay);
			
			return initProductWorkAround(name, pDate, eDate, warranty);
			
		} catch (IOException e) {
			System.out.println(IO_EXCEPTION);
		}
		return null;
	}

	
	
	@SuppressWarnings({ "unchecked" })
	private T initProductWorkAround(String name ,LocalDate purchaseDate , LocalDate expireDate , int warranty) {
		return (T) new Product(name, purchaseDate, warranty);
	}
	
	

	
}



















