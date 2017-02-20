package model.DAO;

/**
 * @author Claire
 *
 * @param <T>
 */
public interface GenericDAO<T> {

	/**
	 * @param id
	 * @return the object corresponding to the id, null if none
	 * @throws DAOException
	 */
	T getById(int id) throws DAOException;

	/**
	 * Insert a new object
	 * 
	 * @param toInsert
	 *            the object to insert
	 * 
	 * @throws DAOException
	 */
	void insert(T toInsert) throws DAOException;

	/**
	 * Update an object
	 * 
	 * @param toUpdate
	 *            the object to update
	 * 
	 * @throws DAOException
	 */
	void update(T toUpdate) throws DAOException;

	/**
	 * Delete an object
	 * 
	 * @param toDelete
	 *            the object to delete
	 * @throws DAOException
	 */
	void delete(T toDelete) throws DAOException;
}