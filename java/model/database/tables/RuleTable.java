package model.database.tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.database.entries.RuleEntry;

public class RuleTable extends GenericTable<RuleEntry> {

	public List<RuleEntry> getByPack(Integer pack) throws SQLException {
		return getByProperty("pack", () -> pack, true);
	}

	public List<RuleEntry> getByName(String partOfName) throws SQLException {
		return getByProperty("name", () -> "%" + partOfName + "%", false);
	}

	@Override
	protected RuleEntry map(ResultSet rs) throws SQLException {
		RuleEntry r = new RuleEntry();
		r.setName(rs.getString(1));
		r.setDetail(rs.getString(2));
		r.setPack(rs.getInt(3));
		r.setIdRule(rs.getInt(4));
		return r;
	}

	@Override
	protected void unmap(RuleEntry rule, PreparedStatement ps) throws SQLException {
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
	protected int getId(RuleEntry rm) {
		return rm.getIdRule();
	}
}
