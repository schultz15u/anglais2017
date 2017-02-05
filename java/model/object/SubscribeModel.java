package model.object;

public class SubscribeModel {
	private static int idUser;
	private static int idUser1;
	
	public SubscribeModel() {
		setIdUser(0);
		setIdUser1(0);
	}
	
	public SubscribeModel(int id, int id1) {
		SubscribeModel.idUser = id;
		SubscribeModel.idUser1 = id1; 
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public int getIdUser1() {
		return idUser1;
	}
	
	public static void setIdUser(int id) {
		idUser = id;
	}
	
	public static void setIdUser1(int id1) {
		idUser1 = id1;
	}
}
