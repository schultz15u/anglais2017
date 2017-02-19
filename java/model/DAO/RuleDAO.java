package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DataBase.ConnectionManager;
import model.object.RuleModel;

public class RuleDAO {

	public RuleModel getRuleById(int id) throws SQLException {
		Connection con = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps = con.prepareStatement("select name, detail, pack from RULE where id_rule = " + id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			// il existe un résultat
			RuleModel r = new RuleModel();
			r.setName(rs.getString(1));
			r.setDetail(rs.getString(2));
			r.setPack(rs.getInt(3));
			return r;
		}
		return null;
	}

	public void insertRule(RuleModel rule) throws SQLException {
		Connection con = ConnectionManager.getInstance().getConnection();
		String sql = "INSERT INTO RULE(ID_RULE, NAME) VALUES ( ? , ?)";
		System.out.println(sql);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, rule.getIdRule());
		ps.setString(2, rule.getName());
		ps.execute();
	}

	// TODO : getRulesByName & getRulesByPack (retourner liste)
}
