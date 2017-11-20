package com.epam.andrey_nikolaev.servlets_11.controller.action;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;

public class GettingChatDataAction extends Action {

	GettingChatDataAction(DAOFactory daoFactory, ActionHelper action) {
		super(daoFactory, action);
	}

	@Override
	public ActionType execute() {
		actionHelper.setMessageList(daoFactory.getMessageDAO().getLastMessages(10));
		actionHelper.setActiveUserList(daoFactory.getUserDAO().getAllLogged());
		return ActionType.CHAT_MESSAGES;
	}

}
