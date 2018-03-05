package InterFaces;

import Product.ProductData;

public interface IController {
	
	public void registerView(IMyPanel imp);
	public void registerModul(IDataManager idm);
	
	// from IDataManager to controller
	public void notify(IProduct product , boolean clean);

	// from view to controller
	public void notify(ProductData productData);
	
	
	
}
