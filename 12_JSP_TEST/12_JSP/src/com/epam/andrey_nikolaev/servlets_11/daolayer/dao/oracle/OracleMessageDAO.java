package com.epam.andrey_nikolaev.servlets_11.daolayer.dao.oracle;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.MessageDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.exception.DataSourceException;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.Message;

public class OracleMessageDAO implements MessageDAO {
	private ConnectionPool poolConnections;
	private ResourceBundle resource;
	private final int MESSAGE_STATUS_ID = 4;

	public OracleMessageDAO(ConnectionPool poolConnections) {
		this.poolConnections = poolConnections;
		resource = ResourceBundle.getBundle("resourses.sqlquery");
	}

	@Override
	public void sendMessage(Message message) {
		ProxyConnection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = poolConnections.getConnection();
			stmt = connection.prepareStatement(resource.getString("query.sendMessage"));
			stmt.setString(1, message.getNick());
			stmt.setString(2, message.getText());
			stmt.setInt(3, message.getRoleId());
			stmt.setInt(4, MESSAGE_STATUS_ID);
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

	@Override
	public List<Message> getLastMessages(int count) {
		ProxyConnection connection = null;
		PreparedStatement stmt = null;
		List<Message> lastMessages = null;
		try {
			connection = poolConnections.getConnection();
			stmt = connection.prepareStatement(resource.getString("query.getLastMessages"));
			stmt.setInt(1, count);
			ResultSet rs = stmt.executeQuery();
			lastMessages = new ArrayList<Message>(count);
			while (rs.next()) {
				String text = rs.getString("MESSAGE");
				String nick = rs.getString("NICK");
				int roleId = rs.getInt("ROLE_ID");
				int statusId = rs.getInt("STATUS_ID");
				Timestamp ts = rs.getTimestamp("TIME_STAMP");
				Calendar date = Calendar.getInstance();
				date.setTime(new Date(ts.getTime()));
				lastMessages.add(new Message(roleId, statusId, nick, text, date));
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

		return lastMessages;
	}

}
