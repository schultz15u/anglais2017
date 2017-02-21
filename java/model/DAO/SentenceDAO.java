package model.DAO;

import java.util.List;

import model.object.SentenceModel;

public interface SentenceDAO extends GenericDAO<SentenceModel> {

	List<SentenceModel> getByIdRule(int idRule) throws DAOException;

}
