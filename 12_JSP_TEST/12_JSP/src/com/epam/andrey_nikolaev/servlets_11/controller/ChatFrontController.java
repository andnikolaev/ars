package com.epam.andrey_nikolaev.servlets_11.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.andrey_nikolaev.servlets_11.controller.action.Action;
import com.epam.andrey_nikolaev.servlets_11.controller.action.ActionType;
import com.epam.andrey_nikolaev.servlets_11.controller.exception.login.FailedLoginException;
import com.epam.andrey_nikolaev.servlets_11.controller.helpers.ChatRequestDispatcher;
import com.epam.andrey_nikolaev.servlets_11.controller.helpers.RequestManager;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;

/**
 * Servlet implementation class ChatFrontController
 */
@WebServlet("/ChatFrontController")
public class ChatFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChatFrontController() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);
		config.getServletContext().setAttribute("daoFactory", daoFactory);
	}

	public void destroy() {
		DAOFactory daoFactory = (DAOFactory) getServletContext().getAttribute("daoFactory");
		try {
			daoFactory.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ChatRequestDispatcher dispatcher = new ChatRequestDispatcher(request, response);
		RequestManager requestManager = new RequestManager(request);
		try {
			Action command = requestManager.getAction();
			ActionType action = command.execute();
			dispatcher.dispatch(action);
		} catch (FailedLoginException e) {
			// request.getRequestDispatcher("/EmptyProject/index.jsp").forward(request,
			// response);
			request.getSession().setAttribute("errorLoginMessage", e.getMessage());
			// request.setAttribute("error", e.getMessage());

			// корень или это
			response.sendRedirect(request.getContextPath());
		}
	}
}
