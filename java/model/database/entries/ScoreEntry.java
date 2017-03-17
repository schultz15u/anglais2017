package model.database.entries;

public class ScoreEntry {

	private int idScore;
	private float score;
	private int pack;

	public ScoreEntry(int id, float score, int pack) {
		this.idScore = id;
		this.score = score;
		this.pack = pack;
	}

	public ScoreEntry() {

	}

	public int getIdScore() {
		return idScore;
	}

	public void setIdScore(int idScore) {
		this.idScore = idScore;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getPack() {
		return pack;
	}

	public void setPack(int pack) {
		this.pack = pack;
	}

	@Override
	public String toString() {
		return "ScoreModel [id_Score=" + idScore + ", score=" + score + ", pack=" + pack + "]";
	}

}
