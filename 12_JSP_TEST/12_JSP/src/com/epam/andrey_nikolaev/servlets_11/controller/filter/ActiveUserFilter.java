package com.epam.andrey_nikolaev.servlets_11.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.User;

/**
 * Servlet Filter implementation class Test
 */
@WebFilter("/Test")
public class ActiveUserFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public ActiveUserFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("active");
		DAOFactory daoFactory = (DAOFactory) request.getServletContext().getAttribute("daoFactory");
		HttpSession session = ((HttpServletRequest) request).getSession();
		User currentUser = (User) session.getAttribute("currentUser");
		if (currentUser != null && !daoFactory.getUserDAO().isKicked(currentUser.getName())) {
			System.out.println("activeUser");
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/chat");
		} else {
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
