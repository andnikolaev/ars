package com.epam.andrey_nikolaev.servlets_11.daolayer.dao.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.RoleDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.exception.DataSourceException;

public class OracleRoleDAO implements RoleDAO {
	private ConnectionPool poolConnections;
	private ResourceBundle resource;

	public OracleRoleDAO(ConnectionPool poolConnections) {
		this.poolConnections = poolConnections;
		resource = ResourceBundle.getBundle("resourses.sqlquery");
	}

	@Override
	public String getRole(String nick) {
		String role = null;
		ProxyConnection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = poolConnections.getConnection();
			stmt = connection.prepareStatement(resource.getString("query.getRole"));
			stmt.setString(1, nick);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				role = rs.getString("NAME");
			}
		} catch (SQLException | InterruptedException e) {
			throw new DataSourceException("Please try again later.");
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		return role;
	}

}
