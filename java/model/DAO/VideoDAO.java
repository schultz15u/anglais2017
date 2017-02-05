package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.object.VideoModel;


public class VideoDAO {

	private Connection connect;
	
	public VideoDAO(Connection c){
		connect = c;
		VideoModel.setId(getNumberOfVideo()+1);
	}
	
	public void create(VideoModel v){
		try {
			PreparedStatement prepare = this.connect.
					prepareStatement("INSERT INTO VIDEO (id_video,title_video,length,thumbnail,tag,status,date_upload_video,video_count,nb_like,id_user)"
								+ "VALUES (?,?,?,?,?,?,?,?,?,?)");
			prepare.setInt(1, v.getIdVideo());
			prepare.setString(2, v.getTitleVideo());
			prepare.setInt(3, v.getLength());
			prepare.setString(4, v.getThumbnail());
			prepare.setString(5, v.getTag());
			prepare.setString(6, v.getStatus());
			prepare.setString(7, v.getDate());
			prepare.setInt(8, v.getVideoCount());
			prepare.setInt(9, v.getNbLike());
			prepare.setInt(10, v.getIdUser());
			prepare.executeUpdate();
			prepare.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	public boolean exist(VideoModel v) {
		boolean result = true;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM VIDEO WHERE id_video = " + v.getIdVideo());
			result = res.next();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void update(VideoModel v){
		try {
			if (exist(v)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE VIDEO SET"
								+" title_video = '"+ v.getTitleVideo()
								+"', length = "+ v.getLength()
								+", thumbnail = '"+ v.getThumbnail()
								+"', tag = '"+ v.getTag()
								+"', status = '"+ v.getStatus()
								+"', date = "+ v.getDate()
								+", video_count = "+ v.getVideoCount()
								+", nb_like = "+ v.getNbLike()
								+", id_user = "+ v.getIdUser()
								+" WHERE id_video = " + v.getIdVideo());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(VideoModel v){
		try {
			if (exist(v)) {
				PreparedStatement prepare = this.connect
						.prepareStatement("DELETE FROM VIDEO"
								+" WHERE id_video = " + v.getIdVideo());
				prepare.executeUpdate();
				prepare.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		updateTableVideo(v.getIdVideo());
	}
	
	private void updateTableVideo(int idUDelete){
		int numberOfVideo = getNumberOfVideo();
		VideoModel.setId(numberOfVideo+1);
		for (int i = idUDelete; i < numberOfVideo+1 ;i++) {
			try {
				PreparedStatement prepare = this.connect
						.prepareStatement("UPDATE VIDEO SET"
								+" id_video = "+ i
								+" WHERE id_video = " + (i+1));
				prepare.executeUpdate();
				prepare.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	private int getNumberOfVideo() {
		int result = 0;
		try {
			ResultSet res = this.connect.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT count(*) FROM VIDEO");
			result = res.getInt(1);
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public VideoModel find(int i){
		VideoModel v = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM VIDEO WHERE id_video = " + i);
			if (result.next()) {
				String title_video = result.getString("title_video");
				int length = result.getInt("length");
				String thumbnail = result.getString("thumbnail");
				String tag = result.getString("tag");
				String status = result.getString("status");
				String date_upload_video = result.getString("date_upload_video");
				int video_count = result.getInt("video_count");
				int nb_like = result.getInt("nb_like");
				int id_user = result.getInt("id_user");
				v = new VideoModel(i,title_video, length, thumbnail, tag, status, date_upload_video, nb_like, video_count, id_user);
			}
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	

}
