package InterFaces;

public interface IDataManager {

	public void notify(String[] data , boolean cd);
	public void notify(boolean update);
	public void notify(boolean sortAll , boolean sortYear , int year , int month);
	
	public void loadExistingData();

}
