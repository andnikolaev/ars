package com.epam.andrey_nikolaev.servlets_11.controller.action.helpers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.andrey_nikolaev.servlets_11.controller.action.ActionHelper;
import com.epam.andrey_nikolaev.servlets_11.controller.exception.NeedRegistrationException;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.Message;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.User;

public class ChatActionHelper implements ActionHelper {

	private final String ADMIN_SUFFIX = "@epam.com";
	private HttpServletRequest request;
	private final int MESSAGE_STATUS_ID = 4;
	private final int ROLE_ADMIN = 1;
	private final int ROLE_USER = 2;

	public ChatActionHelper(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Message getMessageForSend() {
		User user = getCurrentUser();
		String text = request.getParameter("text");
		// text = HtmlEscaper.escapeHTML(text);
		System.out.println(request.getParameter("text"));
		return new Message(user.getRole(), MESSAGE_STATUS_ID, user.getName(), text, null);
	}

	@Override
	public User getUserForLoggedIn() {
		String nick = request.getParameter("name");
		User user = new User(nick, getUserRole(nick));
		return user;
	}

	@Override
	public User getCurrentUser() {
		User user = (User) request.getSession().getAttribute("currentUser");
		if (user == null) {
			throw new NeedRegistrationException("You should been register in this chat.");
		}
		return user;
	}

	@Override
	public User getTargetUser() {
		String targetUserNick = request.getParameter("targetUser");
		User user = new User(targetUserNick, getUserRole(targetUserNick));
		return user;
	}

	@Override
	public void invalidateSession() {
		request.getSession().invalidate();
	}

	@Override
	public void setCurrentUser(User user) {
		request.getSession().setAttribute("currentUser", user);
	}

	@Override
	public void setMessageList(List<Message> messageLast) {
		request.getSession().setAttribute("lastMessages", messageLast);
	}

	@Override
	public void setActiveUserList(List<User> allActiveUser) {
		request.getSession().setAttribute("activeUsers", allActiveUser);
	}

	private int getUserRole(String nick) {
		return nick.endsWith(ADMIN_SUFFIX) ? ROLE_ADMIN : ROLE_USER;
	}

}
