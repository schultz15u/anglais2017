package model.database.tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import model.database.ConnectionManager;

public abstract class GenericTable<T> {

	private boolean debug = true;

	/**
	 * @param id
	 * @return the object corresponding to the id, null if none
	 * @throws SQLException
	 */	
	public T getById(int id) throws SQLException {
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
	 * @throws SQLException
	 */
	public List<T> getByProperty(String sqlPropertyName, Supplier<Object> lambda, boolean exactMatch)
			throws SQLException {
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
				// il existe un r�sultat
				T t = map(rs);
				ret.add(t);
			}
			return ret;
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	public List<T> getAll() throws SQLException
	{
		try {
			String sql = "SELECT " + getColumns()  + "," + getColumnId() + " FROM " + getTableName();
			PreparedStatement ps = prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<T> ret = new ArrayList<>();
			while (rs.next()) {
				T t = map(rs);
				ret.add(t);
			}
			
			return ret;
		}
		catch (SQLException e) {
			throw new SQLException(e);
		}
		
	}
	
	/**
	  * Insert a new object
 	 * 
 	 * @param toInsert
 	 *            the object to insert
 	 * 
 	 * @throws SQLException
 	 */
	public void insert(T t) throws SQLException {
		try {
			String columns = getColumns()/* + ", " + getColumnId()*/;
			String sql = "INSERT INTO " + getTableName() + "(" + columns + ") VALUES ("
					+ columns.replaceAll("[^,]+", "?") + ")";
			PreparedStatement ps = prepareStatement(sql);
			unmap(t, ps, false);
			ps.execute();
		} catch (SQLException e) {
			throw new SQLException(e);
		}

	}
	
	/**
	 * Update an object
	 * 
	 * @param toUpdate
	 *            the object to update
	 * 
	 * @throws SQLException
	 */
	public void update(T toUpdate) throws SQLException {
		try {
			String sql = "UPDATE " + getTableName() + " SET " + getColumns().replace(",", " = ?,") + " = ? WHERE "
					+ getColumnId() + "= ?";
			PreparedStatement ps = prepareStatement(sql);
			unmap(toUpdate, ps, true);
			ps.execute();
		} catch (SQLException e) {
			throw new SQLException(e);
		}
	}
	
	/**
	 * Delete an object
	 * 
	 * @param toDelete
	 *            the object to delete
	 * @throws SQLException
	 */
	public void delete(T toDelete) throws SQLException {
		try {
			String sql = "DELETE FROM " + getTableName() + " WHERE " + getColumnId() + " = ?";
			PreparedStatement ps = prepareStatement(sql);
			ps.setInt(1, getId(toDelete));
			ps.execute();
		} catch (SQLException e) {
			throw new SQLException(e);
		}

	}
	
	public void deleteAll() throws SQLException {
		try {
			String sql = "DELETE FROM " + getTableName();
			PreparedStatement ps = prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			throw new SQLException(e);
		}

	}

	protected abstract T map(ResultSet rs) throws SQLException;

	protected abstract void unmap(T rule, PreparedStatement ps, boolean idShouldBeInserted) throws SQLException;

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

	private T getUniqueResult(List<T> tmp) throws SQLException {
		if (tmp != null && tmp.size() >= 1) {
			return tmp.get(0);
		} else {
			throw new SQLException("Invalid number of results");
		}
	}
}
