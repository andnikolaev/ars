package com.epam.andrey_nikolaev.servlets_11.daolayer.dao;

import java.util.List;

import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.User;

public interface UserDAO {
	void login(User loginingUser);

	boolean isLogged(String user);

	void logout(User logoutingUser);

	void kick(User admin, User kickableUser);

	void unkick(User user);

	boolean isKicked(String user);

	List<User> getAllLogged();
}
