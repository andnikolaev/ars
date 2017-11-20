package com.epam.andrey_nikolaev.servlets_11.daolayer.dao;

import java.util.List;

import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.Message;

public interface MessageDAO {
	void sendMessage(Message message);

	List<Message> getLastMessages(int count);

}
