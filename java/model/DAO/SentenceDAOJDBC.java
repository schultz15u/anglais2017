package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.object.SentenceModel;

public class SentenceDAOJDBC extends GenericDAOJDBC<SentenceModel> implements SentenceDAO {

	@Override
	public List<SentenceModel> getByIdRule(int idRule) throws DAOException {
		return getByProperty("id_rule", () -> idRule, true);
	}

	@Override
	protected SentenceModel map(ResultSet rs) throws SQLException {
		SentenceModel s = new SentenceModel();
		s.setDetail(rs.getString(1));
		s.setPropOk(rs.getString(2));
		s.setPropNo(rs.getString(3));
		s.setIdRule(rs.getInt(4));
		s.setIdSen(rs.getInt(5));
		return s;
	}

	@Override
	protected void unmap(SentenceModel sentence, PreparedStatement ps) throws SQLException {
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
	protected int getId(SentenceModel rm) {
		return rm.getIdSen();
	}
}
