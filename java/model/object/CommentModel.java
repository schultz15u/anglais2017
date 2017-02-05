package model.object;

public class CommentModel {
	
	private static int id = 1;
	
	public static void setId(int id){
		CommentModel.id = id;
	}
	
	public static int getId(){
		return id;
	}

	private int id_comment;
	private String texte_comment;
	private String date_comment;
	private int nb_like;
	private int id_comment_1;
	private int id_user;
	
	public CommentModel(String textComment, String dateComment, int nbLike, int idComment1, int idUser) {
		id_comment = id;
		texte_comment = textComment;
		date_comment = dateComment;
		nb_like = nbLike;
		id_comment_1= idComment1;
		id_user = idUser;
		id++;
	}
	
	public CommentModel(int id, String textComment, String dateComment, int nbLike, int idComment1, int idUser) {
		id_comment = id;
		texte_comment = textComment;
		date_comment = dateComment;
		nb_like = nbLike;
		id_comment_1= idComment1;
		id_user = idUser;
	}


	public int getIdComment() {
		return id_comment;
	}

	public String getTextComment() {
		return texte_comment;
	}

	public void setTextComment(String text_comment) {
		this.texte_comment = text_comment;
	}

	public String getDateComment() {
		return date_comment;
	}

	public void setDateComment(String date_comment) {
		this.date_comment = date_comment;
	}

	public int getNbLike() {
		return nb_like;
	}

	public void setNbLike(int nb_like) {
		this.nb_like = nb_like;
	}

	public int getIdComment1() {
		return id_comment_1;
	}

	public void setIdComment1(int id_comment_1) {
		this.id_comment_1 = id_comment_1;
	}

	public int getIdUser() {
		return id_user;
	}

	public void setIdUser(int id_user) {
		this.id_user = id_user;
	}

}
