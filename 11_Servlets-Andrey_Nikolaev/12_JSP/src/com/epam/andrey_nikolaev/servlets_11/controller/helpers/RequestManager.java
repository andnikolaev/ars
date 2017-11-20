package com.epam.andrey_nikolaev.servlets_11.controller.helpers;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.epam.andrey_nikolaev.servlets_11.controller.action.Action;
import com.epam.andrey_nikolaev.servlets_11.controller.action.ActionHelper;
import com.epam.andrey_nikolaev.servlets_11.controller.action.ActionType;
import com.epam.andrey_nikolaev.servlets_11.controller.action.helpers.ChatActionHelper;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;

public class RequestManager {
	private ResourceBundle rb = ResourceBundle.getBundle("resourses.chatPages");
	private HttpServletRequest request;

	public RequestManager(HttpServletRequest request) {
		this.request = request;
	}

	public Action getAction() {
		String action = request.getParameter("cmd");
		if (action == null) {
			throw new IllegalCommandException();
		}
		action = rb.getString(String.format("chat.%s", action));
		DAOFactory daoFactory = (DAOFactory) request.getServletContext().getAttribute("daoFactory");
		ActionHelper actionHelper = new ChatActionHelper(request);
		Action actionType = ActionType.valueOf(action.toUpperCase()).getAction(daoFactory, actionHelper);
		return actionType;
	}
}
