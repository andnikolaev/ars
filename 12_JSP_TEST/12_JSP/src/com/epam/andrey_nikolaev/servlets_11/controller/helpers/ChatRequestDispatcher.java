package com.epam.andrey_nikolaev.servlets_11.controller.helpers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.andrey_nikolaev.servlets_11.controller.action.ActionType;

public class ChatRequestDispatcher {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private List<Exception> errors;

	public ChatRequestDispatcher(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		errors = new LinkedList<>();
		request.getSession().removeAttribute("errors");
	}

	public void dispatch(ActionType action) {
		try {
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				response.sendRedirect("error");
			} else {
				switch (action) {
				case LOGIN:
					response.sendRedirect(action.getPage());
					break;
				case LOGOUT:
					response.sendRedirect(action.getPage());
					break;
				case SEND:
					response.sendRedirect(action.getPage());
					break;
				case KICK:
					response.sendRedirect(action.getPage());
					break;
				case UNKICK:
					response.sendRedirect(action.getPage());
					break;
				case CHAT_MESSAGES:
					request.getRequestDispatcher(action.getPage()).forward(request, response);
					break;
				case ERROR:
					throw new RuntimeException("Сделать что-нибудь!");
				default:
					break;
				}
			}
		} catch (IOException | ServletException e) {
			// TODO логировать
			e.printStackTrace();
		}
	}

	public void addError(Exception error) {
		errors.add(error);
	}
}
