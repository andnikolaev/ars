package com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object;

import java.util.Calendar;

public class Message {
	private int roleId;
	private int statusId;
	private String nick;
	private String text;
	private Calendar timestamp;

	public Message() {
	}

	public Message(int roleId, int statusId, String nick, String text, Calendar timestamp) {
		this.roleId = roleId;
		this.statusId = statusId;
		this.nick = nick;
		this.text = text;
		this.timestamp = timestamp;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int role) {
		this.roleId = role;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusTitle) {
		this.statusId = statusTitle;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Message [roleTitle=" + roleId + ", statusTitle=" + statusId + ", nick=" + nick + ", text=" + text
				+ ", timestamp=" + timestamp + "]";
	}

}
