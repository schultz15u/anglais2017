package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import model.DataBase.ConnectionManager;

public abstract class GenericDAOJDBC<T> implements GenericDAO<T> {

	private boolean debug = true;

	@Override
	public T getById(int id) throws DAOException {
		return getUniqueResult(getByProperty(getColumnId(), () -> id, true));
	}

	/**
	 * @param sqlPropertyName
	 *            SQL column name used for searching
	 * @param lambda
	 *            lambda function returning the property value
	 * @param exactMatch
	 *            equality or like
	 * @return list of the objects whose property <code>sqlPropertyName</code>
	 *         matches the <code>lambda</code> return value.
	 * @throws DAOException
	 */
	public List<T> getByProperty(String sqlPropertyName, Supplier<Object> lambda, boolean exactMatch)
			throws DAOException {
		try {
			Object expectedValue = lambda.get();
			String sql = "SELECT " + getColumns() + "," + getColumnId() + " FROM " + getTableName() + " WHERE "
					+ sqlPropertyName;
			if (expectedValue == null) {
				sql += " IS NULL";
			} else if (exactMatch) {
				sql += " = ?";
			} else {
				sql += " LIKE ?";
			}
			PreparedStatement ps = prepareStatement(sql);
			if (expectedValue != null) {
				ps.setObject(1, expectedValue);
			}
			ResultSet rs = ps.executeQuery();
			List<T> ret = new ArrayList<>();
			while (rs.next()) {
				// il existe un résultat
				T t = map(rs);
				ret.add(t);
			}
			return ret;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void insert(T t) throws DAOException {
		try {
			String columns = getColumns() + ", " + getColumnId();
			String sql = "INSERT INTO " + getTableName() + "(" + columns + ") VALUES ("
					+ columns.replaceAll("[^,]+", "?") + ")";
			PreparedStatement ps = prepareStatement(sql);
			unmap(t, ps);
			ps.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public void update(T toUpdate) throws DAOException {
		try {
			String sql = "UPDATE " + getTableName() + " SET " + getColumns().replace(",", " = ?,") + " = ? WHERE "
					+ getColumnId() + "= ?";
			PreparedStatement ps = prepareStatement(sql);
			unmap(toUpdate, ps);
			ps.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(T toDelete) throws DAOException {
		try {
			String sql = "DELETE FROM " + getTableName() + " WHERE " + getColumnId() + " = ?";
			PreparedStatement ps = prepareStatement(sql);
			ps.setInt(1, getId(toDelete));
			ps.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	protected abstract T map(ResultSet rs) throws SQLException;

	protected abstract void unmap(T rule, PreparedStatement ps) throws SQLException;

	protected abstract String getColumns();

	protected abstract String getTableName();

	protected abstract String getColumnId();

	protected abstract int getId(T t);

	private void printSQL(String sql) {
		if (debug) {
			System.out.println("==> SQL : " + sql);
		}
	}

	/**
	 * Prepare a SQL statement
	 * 
	 * @param sql
	 *            the SQL query
	 * @return the preparedStatement
	 * @throws SQLException
	 */
	protected PreparedStatement prepareStatement(String sql) throws SQLException {
		Connection con = ConnectionManager.getInstance().getConnection();
		printSQL(sql);
		PreparedStatement ps;
		ps = con.prepareStatement(sql);
		return ps;
	}

	private T getUniqueResult(List<T> tmp) throws DAOException {
		if (tmp != null && tmp.size() == 1) {
			return tmp.get(1);
		} else {
			throw new DAOException("Invalid number of results");
		}
	}
}
