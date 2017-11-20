package com.epam.andrey_nikolaev.servlets_11.controller.action;

import java.util.List;

import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.Message;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.User;

public interface ActionHelper {
	Message getMessageForSend();

	User getUserForLoggedIn();
	
	User getCurrentUser();

	User getTargetUser();

	void setCurrentUser(User user);

	void setMessageList(List<Message> last);

	void setActiveUserList(List<User> allLogged);
	
	void invalidateSession();
}
