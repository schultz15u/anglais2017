package model.database.tables;

import model.database.entries.PackageEntry;
import model.database.entries.RuleEntry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PackageTable extends GenericTable<PackageEntry> {

	@Override
	protected PackageEntry map(ResultSet rs) throws SQLException {
		PackageEntry r = new PackageEntry();
		r.setName(rs.getString(1));
		r.setCanBeModifiedOutside(rs.getBoolean(2));
		r.setIdPack(rs.getInt(3));
		return r;
	}

	@Override
	protected void unmap(PackageEntry rule, PreparedStatement ps, boolean idShouldBeInserted) throws SQLException {
		ps.setString(1, rule.getName());
		ps.setBoolean(2, rule.canBeModifiedOutside());
		if (idShouldBeInserted)
			ps.setInt(3, rule.getIdPack());
	}

	@Override
	protected String getColumns() {
		return "name, can_be_modified_outside";
	}

	@Override
	protected String getTableName() {
		return "PACKAGE";
	}

	@Override
	protected String getColumnId() {
		return "id_pack";
	}

	@Override
	protected int getId(PackageEntry rm) {
		return rm.getIdPack();
	}
}
