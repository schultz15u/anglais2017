package model.database.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.database.entries.SentenceEntry;

public class SentenceTable extends GenericTable<SentenceEntry> {

	public List<SentenceEntry> getByIdRule(int idRule) throws SQLException {
		return getByProperty("id_rule", () -> idRule, true);
	}

	@Override
	protected SentenceEntry map(ResultSet rs) throws SQLException {
		SentenceEntry s = new SentenceEntry();
		s.setDetail(rs.getString(1));
		s.setPropOk(rs.getString(2));
		s.setPropNo(rs.getString(3));
		s.setIdRule(rs.getInt(4));
		s.setIdSen(rs.getInt(5));
		return s;
	}

	@Override
	protected void unmap(SentenceEntry sentence, PreparedStatement ps) throws SQLException {
		ps.setString(1, sentence.getDetail());
		ps.setString(2, sentence.getPropOk());
		ps.setString(3, sentence.getPropNo());
		ps.setInt(4, sentence.getIdRule());
		ps.setInt(5, sentence.getIdSen());
	}

	@Override
	protected String getColumns() {
		return "detail, prop_ok, prop_no, id_rule";
	}

	@Override
	protected String getTableName() {
		return "SENTENCE";
	}

	@Override
	protected String getColumnId() {
		return "id_sen";
	}

	@Override
	protected int getId(SentenceEntry rm) {
		return rm.getIdSen();
	}
}
