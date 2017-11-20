package com.epam.andrey_nikolaev.servlets_11.controller.action;

import com.epam.andrey_nikolaev.servlets_11.controller.exception.FailedChatOperationException;
import com.epam.andrey_nikolaev.servlets_11.controller.exception.login.NickAlreadyTakenException;
import com.epam.andrey_nikolaev.servlets_11.controller.exception.login.UserKickedException;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.UserDAO;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.User;

public class LoginUserAction extends Action {

	LoginUserAction(DAOFactory daoFactory, ActionHelper action) {
		super(daoFactory, action);
	}

	@Override
	public ActionType execute() throws FailedChatOperationException {
		User user = actionHelper.getUserForLoggedIn();
		UserDAO userDao = daoFactory.getUserDAO();
		if (userDao.isLogged(user.getName())) {
			
			throw new NickAlreadyTakenException("User already logged.");
		} else if (userDao.isKicked(user.getName())) {
			throw new UserKickedException("User with such nick is kicked.");
		} else {
			userDao.login(user);
			actionHelper.setCurrentUser(user);
		}
		return ActionType.LOGIN;
	}

}
