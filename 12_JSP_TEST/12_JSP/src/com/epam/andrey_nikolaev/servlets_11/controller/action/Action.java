package com.epam.andrey_nikolaev.servlets_11.controller.action;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;

public abstract class Action {
	protected DAOFactory daoFactory;
	protected ActionHelper actionHelper;

	Action(DAOFactory daoFactory, ActionHelper actionHelper) {
		this.daoFactory = daoFactory;
		this.actionHelper = actionHelper;
	}
	
	public abstract ActionType execute();
}
