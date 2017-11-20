package com.epam.andrey_nikolaev.servlets_11.controller.action;

import com.epam.andrey_nikolaev.servlets_11.controller.exception.FailedChatOperationException;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.User;

public class UnkickUserAction extends Action {

	UnkickUserAction(DAOFactory daoFactory, ActionHelper action) {
		super(daoFactory, action);
	}

	@Override
	public ActionType execute() {
		User currentUser = actionHelper.getCurrentUser();
		User targetUser = actionHelper.getTargetUser();
		if (!daoFactory.getUserDAO().isLogged(currentUser.getName())) {
			throw new FailedChatOperationException("Необходимо выполнить вход");
		} else if (!"ADMIN".equals(daoFactory.getRoleDAO().getRole(currentUser.getName()))) {
			throw new FailedChatOperationException("Вы не админ");
		} else if (!daoFactory.getUserDAO().isKicked(targetUser.getName())) {
			throw new FailedChatOperationException("Выбранный пользователь не кикнут");
		}
		daoFactory.getUserDAO().unkick(targetUser);
		return ActionType.UNKICK;
	}

}
