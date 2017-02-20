package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.DataBase.ConnectionManager;

public abstract class GenericDAOJDBC<T> implements GenericDAO<T> {

	private boolean debug = true;

	@Override
	public T getById(int id) throws DAOException {
		Connection con = ConnectionManager.getInstance().getConnection();
		PreparedStatement ps;
		try {
			String sql = "SELECT " + getColumns() + "," + getColumnId() + " FROM " + getTableName() + " WHERE "
					+ getColumnId() + " = " + id;
			printSQL(sql);
			ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// il existe un résultat
				T t = map(rs);
				return t;
			}
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public void insert(T t) throws DAOException {
		Connection con = ConnectionManager.getInstance().getConnection();
		String columns = getColumns() + ", " + getColumnId();
		String sql = "INSERT INTO " + getTableName() + "(" + columns + ") VALUES (" + columns.replaceAll("[^,]+", "?")
				+ ")";
		printSQL(sql);
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			unmap(t, ps);
			ps.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public void update(T toUpdate) throws DAOException {
		Connection con = ConnectionManager.getInstance().getConnection();
		String sql = "UPDATE " + getTableName() + " SET " + getColumns().replace(",", " = ?,") + " = ? WHERE "
				+ getColumnId() + "= ?";
		printSQL(sql);
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			unmap(toUpdate, ps);
			ps.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(T toDelete) throws DAOException {
		Connection con = ConnectionManager.getInstance().getConnection();
		String sql = "DELETE FROM " + getTableName() + " WHERE " + getColumnId() + " = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
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

}
