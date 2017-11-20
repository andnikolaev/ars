package com.epam.andrey_nikolaev.servlets_11.controller.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.User;

/**
 * Application Lifecycle Listener implementation class SessionTimeoutListener
 *
 */
@WebListener
public class SessionTimeoutListener implements HttpSessionListener {

	/**
	 * Default constructor.
	 */
	public SessionTimeoutListener() {
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("Session was created");
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		System.out.println("Session was destroyed.");
		User currentUser = (User) arg0.getSession().getAttribute("currentUser");

		if (currentUser != null) {
			DAOFactory daoFactory = (DAOFactory) arg0.getSession().getServletContext().getAttribute("daoFactory");
			daoFactory.getUserDAO().logout(currentUser);
			//TODO: throw new Exception
		}
	}

}
