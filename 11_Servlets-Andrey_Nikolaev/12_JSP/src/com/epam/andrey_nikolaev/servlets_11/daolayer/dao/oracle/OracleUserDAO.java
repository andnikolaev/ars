package com.epam.andrey_nikolaev.servlets_11.daolayer.dao.oracle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.UserDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.exception.DataSourceException;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.User;

public class OracleUserDAO implements UserDAO {
	private ConnectionPool poolConnections;
	private ResourceBundle resource;
	private final String LOGIN_MESSAGE = "User logged in chat.";
	private final String LOGOUT_MESSAGE = "User logged out from chat.";
	private final String KICK_MESSAGE = "User %s was kicked by %s.";
	private final int LOGIN_STATUS = 1;
	private final int LOGOUT_STATUS = 2;
	private final int KICK_STATUS = 3;
	private final int MESSAGE_STATUS = 4;

	public OracleUserDAO(ConnectionPool poolConnections) {
		this.poolConnections = poolConnections;
		resource = ResourceBundle.getBundle("resourses.sqlquery");
	}

	@Override
	public void login(User loginingUser) {
		if (!isLogged(loginingUser.getName()) && !isKicked(loginingUser.getName())) {
			insertMessage(loginingUser.getName(), LOGIN_MESSAGE, loginingUser.getRole(), LOGIN_STATUS);
		}
	}

	@Override
	public boolean isLogged(String user) {
		boolean isLogged = false;
		ProxyConnection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = poolConnections.getConnection();
			stmt = connection.prepareStatement(resource.getString("query.isLogged"));
			stmt.setString(1, user);
			stmt.setInt(2, LOGIN_STATUS);
			stmt.setInt(3, MESSAGE_STATUS);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				isLogged = true;
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
		return isLogged;
	}

	@Override
	public void logout(User logoutingUser) {
		if (isLogged(logoutingUser.getName())) {
			insertMessage(logoutingUser.getName(), LOGOUT_MESSAGE, logoutingUser.getRole(), LOGOUT_STATUS);
		}
	}

	@Override
	public void kick(User admin, User kickableUser) {
		logout(kickableUser);
		insertMessage(kickableUser.getName(), String.format(KICK_MESSAGE, kickableUser.getName(), admin.getName()),
				kickableUser.getRole(), KICK_STATUS);
	}

	@Override
	public void unkick(User user) {
		if (isKicked(user.getName())) {
			ProxyConnection connection = null;
			PreparedStatement stmt = null;
			try {
				connection = poolConnections.getConnection();
				stmt = connection.prepareStatement(resource.getString("query.unkick"));
				stmt.setString(1, user.getName());
				// stmt.setInt(2, KICK_STATUS);
				stmt.executeUpdate();
				connection.commit();
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
		}
	}

	@Override
	public boolean isKicked(String user) {
		boolean isKicked = false;
		ProxyConnection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = poolConnections.getConnection();
			stmt = connection.prepareStatement(resource.getString("query.isKicked"));
			stmt.setString(1, user);
			stmt.setInt(2, KICK_STATUS);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				isKicked = true;
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
		return isKicked;
	}

	@Override
	public List<User> getAllLogged() {
		ProxyConnection connection = null;
		Statement stmt = null;
		List<User> users = null;
		try {
			connection = poolConnections.getConnection();
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(resource.getString("query.getAllLogged"));
			users = new LinkedList<User>();
			while (rs.next()) {
				String nick = rs.getString("NICK");
				int roleId = rs.getInt("ROLE_ID");
				users.add(new User(nick, roleId));
			}
		} catch (SQLException | InterruptedException e) {
			throw new DataSourceException("Please try again later.");
		} finally {

			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return users;
	}

	private void insertMessage(String nick, String text, int roleId, int StatusId) {
		ProxyConnection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = poolConnections.getConnection();
			stmt = connection.prepareStatement("INSERT INTO MESSAGE(NICK,MESSAGE,ROLE_ID,STATUS_ID) VALUES(?,?,?,?)");
			stmt.setString(1, nick);
			stmt.setString(2, text);
			stmt.setInt(3, roleId);
			stmt.setInt(4, StatusId);
			stmt.executeQuery();
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
	}
}
