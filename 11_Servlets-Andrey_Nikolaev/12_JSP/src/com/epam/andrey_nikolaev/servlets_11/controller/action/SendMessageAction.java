package com.epam.andrey_nikolaev.servlets_11.controller.action;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;

public class SendMessageAction extends Action {

	SendMessageAction(DAOFactory daoFactory, ActionHelper action) {
		super(daoFactory, action);
	}

	@Override
	public ActionType execute() {
		daoFactory.getMessageDAO().sendMessage(actionHelper.getMessageForSend());
		return ActionType.SEND;
	}

}
