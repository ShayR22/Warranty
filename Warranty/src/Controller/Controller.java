package Controller;

import java.util.ArrayList;

import InterFaces.IController;
import InterFaces.IProduct;
import Product.ProductData;
import InterFaces.IDataManager;
import InterFaces.IMyPanel;

public class Controller implements IController {

	private ArrayList<IMyPanel> views;
	private ArrayList<IDataManager> moduls;
	
	public Controller() {
		views = new ArrayList<IMyPanel>();
		moduls = new ArrayList<IDataManager>();
	}
	
	@Override
	public void registerView(IMyPanel imp) {
		if(!views.contains(imp)) {
			views.add(imp);
		}
	}

	@Override
	public void registerModul(IDataManager idm) {
		if(!moduls.contains(idm)) {
			moduls.add(idm);
		}
	}

	@Override
	public void notify(IProduct product , boolean clean) {
		if(clean) {
			for (IMyPanel imp : views) {
				imp.notify(clean);
			}
		}
		else {
			if(product != null) {
				for (IMyPanel imp : views) {
					imp.notify(product);
				} 
			}
		}
	}

	@Override
	public void notify(ProductData productData) {
		if(productData.isSearching()) {
			modulSearchRequest(productData);
		}
		else if(productData.isUpdate()) {
			for (IDataManager iDataManager : moduls) {
				iDataManager.notify(productData.isUpdate());
			}
		}
		else {
			modulCreateRequest(productData);
		}
		
	}
	
	private void modulSearchRequest(ProductData productData) {
		for (IDataManager iDataManager : moduls) {
			iDataManager.notify(productData.isSearchingAll(), productData.isSearchingOnlyYear() ,productData.getYearSearch() ,productData.getMonthSearch());
		}
		
	}

	private String[] productDataStringfy(ProductData product) {
		String[] data = new String[5];
		data[0] = product.getName();
		data[1] = String.valueOf(product.getPurchaseDate().getYear()).trim();
		data[2] = String.valueOf(product.getPurchaseDate().getMonthValue()).trim();
		data[3] = String.valueOf(product.getPurchaseDate().getDayOfMonth()).trim();
		data[4] = String.valueOf(product.getWarrantyInMonths()).trim();
	
		return data;
	}
	
	private void modulCreateRequest(ProductData product) {
		if(product.getPurchaseDate() != null) {
			String[] data = productDataStringfy(product);
			
			for (IDataManager idm : moduls) {
				idm.notify(data, product.isCreating());
			}		
		}
	
	}


	

}
