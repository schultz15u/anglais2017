package model.object;

public class CompositionCollectionModel {
	private static int id = 1;
	
	public static void setId(int id){
		CompositionCollectionModel.id = id;
	}
	
	public static int getId(){
		return id;
	}
	
	
	private int idVideo;
	private int idCollection;
	
	public CompositionCollectionModel( int idCollection1) {
		idVideo = id;
		idCollection = idCollection1;
		id++;
		
	}
	
	public CompositionCollectionModel(int idVideo, int idCollection1) {
		idVideo = id;
		setIdCollection(idCollection1);
	}

	public int getIdVideo() {
		return idVideo;
	}

	public int getIdCollection() {
		return idCollection;
	}

	public void setIdCollection(int idCollection) {
		this.idCollection = idCollection;
	}
	
	
}
