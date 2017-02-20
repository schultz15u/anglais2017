package model.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.object.RuleModel;

public class RuleDAOJDBC extends GenericDAOJDBC<RuleModel> implements RuleDAO {

	@Override
	protected RuleModel map(ResultSet rs) throws SQLException {
		RuleModel r = new RuleModel();
		r.setName(rs.getString(1));
		r.setDetail(rs.getString(2));
		r.setPack(rs.getInt(3));
		r.setIdRule(rs.getInt(4));
		return r;
	}

	@Override
	protected void unmap(RuleModel rule, PreparedStatement ps) throws SQLException {
		ps.setString(1, rule.getName());
		ps.setString(2, rule.getDetail());
		ps.setInt(3, rule.getPack());
		ps.setInt(4, rule.getIdRule());
	}

	@Override
	protected String getColumns() {
		return "name, detail, pack";
	}

	@Override
	protected String getTableName() {
		return "RULE";
	}

	@Override
	protected String getColumnId() {
		return "id_rule";
	}

	@Override
	protected int getId(RuleModel rm) {
		return rm.getIdRule();
	}

	// TODO : getRulesByName & getRulesByPack (retourner liste)
}
