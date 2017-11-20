package com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory;

import java.io.Closeable;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.MessageDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.RoleDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.UserDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.oracle.OracleDAOFactory;

public abstract class DAOFactory implements Closeable {
	public static final int XML = 1;
	public static final int ORACLE = 2;

	public abstract MessageDAO getMessageDAO();

	public abstract UserDAO getUserDAO();

	public abstract RoleDAO getRoleDAO();

	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case XML:
			return null;
		case ORACLE:
			try {
				return new OracleDAOFactory();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			return null;
		}
	}

}
