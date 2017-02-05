package model.object;

public class UserModel {
	
	private static int id = 1;
	
	public static void setId(int id){
		UserModel.id = id;
	}
	
	public static int getId(){
		return id;
	}

	
	private int id_user;
	private String username;
	private String mail;
	private String password;
	private int id_comment;
	private int id_collection;
	
	public UserModel(String name, String adresse, String pass, int idc, int idco) {
		id_user = id;
		username = name;
		mail = adresse;
		password = pass;
		setId_comment(idc);
		setId_collection(idco);
	}

	public UserModel(int id, String name, String adresse, String pass, int idc, int idco) {
		id_user = id;
		username = name;
		mail = adresse;
		password = pass;
		setId_comment(idc);
		setId_collection(idco);
	}
	
	public int getIdUser() {
		return id_user;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId_comment() {
		return id_comment;
	}

	public void setId_comment(int id_comment) {
		this.id_comment = id_comment;
	}

	public int getId_collection() {
		return id_collection;
	}

	public void setId_collection(int id_collection) {
		this.id_collection = id_collection;
	}

}