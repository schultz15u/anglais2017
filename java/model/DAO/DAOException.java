package model.DAO;

public class DAOException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Throwable t) {
		super(message, t);
	}

	public DAOException(Throwable t) {
		super(t);
	}

}
