package models;

public class UserReport {
	
	private String UserName;
	private int NumOfPurchase;
	private int TypeOfPurchase;
	private int UpdateClient;
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public int getNumOfPurchase() {
		return NumOfPurchase;
	}
	public void setNumOfPurchase(int numOfPurchase) {
		NumOfPurchase = numOfPurchase;
	}
	public int getTypeOfPurchase() {
		return TypeOfPurchase;
	}
	public void setTypeOfPurchase(int typeOfPurchase) {
		TypeOfPurchase = typeOfPurchase;
	}
	public int getUpdateClient() {
		return UpdateClient;
	}
	public void setUpdateClient(int updateClient) {
		UpdateClient = updateClient;
	}

}
