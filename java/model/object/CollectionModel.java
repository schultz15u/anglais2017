package model.object;

import java.util.Observable;

public class CollectionModel extends Observable {
	private static int id = 1;
	
	public static void setId(int id){
		CollectionModel.id = id;
	}
	
	public static int getId(){
		return id;
	}

	private int idCollection;
	private String nameCollection;
	private int id_user;
	
	public CollectionModel (String nameCollection1, int id) {
		idCollection = id;
		nameCollection = nameCollection1;
		id_user = id;
		id++;
	}
	
	public CollectionModel (int id, String nameCollection1, int id1) {
		idCollection = id;
		nameCollection = nameCollection1;
		id_user = id1;
	}

	public int getIdCollection() {
		return idCollection;
	}

	public String getNameCollection() {
		return nameCollection;
	}

	public void setNameCollection(String nameCollection) {
		this.nameCollection = nameCollection;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
}
