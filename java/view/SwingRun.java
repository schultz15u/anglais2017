package view;

import java.sql.SQLException;

import model.DAO.RuleDAO;
import model.object.RuleModel;

public class SwingRun {

	public static void main(String[] args) {
		try {
			RuleModel rule = new RuleModel(2, "b'im", "bam", 5);
			RuleDAO rd = new RuleDAO();
			rd.insertRule(rule);
			System.out.println(rd.getRuleById(2).getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
