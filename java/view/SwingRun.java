package view;

import model.DAO.DAOException;
import model.DAO.RuleDAO;
import model.DAO.RuleDAOJDBC;
import model.DAO.SentenceDAO;
import model.DAO.SentenceDAOJDBC;
import model.object.RuleModel;
import model.object.SentenceModel;

public class SwingRun {

	public static void main(String[] args) {
		try {
			RuleDAO rd = new RuleDAOJDBC();
			RuleModel rm = rd.getById(1);
			System.out.println(rm);
			rd.delete(rm);
			rm.setName("bibi");
			rd.insert(rm);
			rm.setDetail("Hello");
			rd.update(rm);
			rm = rd.getById(2);
			System.out.println(rm);

			SentenceDAO rd2 = new SentenceDAOJDBC();
			SentenceModel rm2 = rd2.getById(22);
			System.out.println(rm2);
			rd2.delete(rm2);
			rm2.setPropOk("bibi2");
			rd2.insert(rm2);
			rm2.setDetail("Hello2");
			rd2.update(rm2);
			rm2 = rd2.getById(22);
			System.out.println(rm2);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		new MainScreen();
	}

}
