package model.database.tables;

import model.database.entries.ScoreEntry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScoreTable extends GenericTable<ScoreEntry> {

	/**
	 * @param pack
	 *            pack's id
	 * @return matching score
	 * @throws SQLException
	 */
	public List<ScoreEntry> getByPack(Integer pack) throws SQLException {
		return getByProperty("pack", () -> pack, true);
	}

	@Override
	protected ScoreEntry map(ResultSet rs) throws SQLException {
		ScoreEntry r = new ScoreEntry();
		r.setScore(rs.getFloat(1));
		r.setPack(rs.getInt(2));
		r.setIdScore(rs.getInt(3));
		return r;
	}

	@Override
	protected void unmap(ScoreEntry score, PreparedStatement ps, boolean idShouldBeInserted) throws SQLException {
		ps.setFloat(1, score.getScore());
		ps.setInt(2, score.getPack());
		if (idShouldBeInserted)
			ps.setInt(3, score.getIdScore());
	}

	@Override
	protected String getColumns() {
		return "score, pack";
	}

	@Override
	protected String getTableName() {
		return "score";
	}

	@Override
	protected String getColumnId() {
		return "id_score";
	}

	@Override
	protected int getId(ScoreEntry rm) {
		return rm.getIdScore();
	}
}
