package com.epam.andrey_nikolaev.servlets_11.controller.action;

import com.epam.andrey_nikolaev.servlets_11.controller.exception.FailedLogoutException;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.UserDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.User;

public class LogoutUserAction extends Action {

	LogoutUserAction(DAOFactory daoFactory, ActionHelper action) {
		super(daoFactory, action);
	}

	@Override
	public ActionType execute() {
		User user = actionHelper.getCurrentUser();
		UserDAO userDao = daoFactory.getUserDAO();
		if (!userDao.isLogged(user.getName())) {
			throw new FailedLogoutException("User are not logged.");
		}
		userDao.logout(user);
		actionHelper.invalidateSession();
		return ActionType.LOGOUT;
	}

}
