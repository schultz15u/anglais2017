package model.DAO;

import java.util.List;

import model.object.RuleModel;

public interface RuleDAO extends GenericDAO<RuleModel> {

	/**
	 * @param pack
	 *            pack's id
	 * @return matching rules
	 * @throws DAOException
	 */
	List<RuleModel> getByPack(Integer pack) throws DAOException;

	/**
	 * 
	 * @param partOfName
	 *            String contained in the rule's name
	 * @return the matching rules
	 * @throws DAOException
	 */
	List<RuleModel> getByName(String partOfName) throws DAOException;
}