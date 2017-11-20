package com.epam.andrey_nikolaev.servlets_11.daolayer.dao.oracle;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.xml.ws.WebServiceException;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.MessageDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.RoleDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.UserDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;

public class OracleDAOFactory extends DAOFactory {

	private ResourceBundle configData = ResourceBundle.getBundle("resourses.config");
	private ConnectionPool poolConnections;

	public OracleDAOFactory() throws Exception {
		Locale.setDefault(new Locale("en", "EN"));
		String url = configData.getString("URL");
		String driverName = configData.getString("driver");
		String user = configData.getString("user");
		String password = configData.getString("password");
		int connectionCount = Integer.parseInt(configData.getString("connectionCount"));
		poolConnections = ConnectionPool.getInstance(driverName, url, user, password, connectionCount);
	}

	public MessageDAO getMessageDAO() {
		return new OracleMessageDAO(poolConnections);
	}

	@Override
	public UserDAO getUserDAO() {
		return new OracleUserDAO(poolConnections);
	}

	@Override
	public RoleDAO getRoleDAO() {
		return new OracleRoleDAO(poolConnections);
	}

	@Override
	public void close() throws WebServiceException {
		poolConnections.releaseAllConnections();
	}

}
