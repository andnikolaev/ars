package com.epam.andrey_nikolaev.servlets_11.controller.action;

import java.util.ResourceBundle;

import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;

public enum ActionType {
	SEND("chat.view.send") {
		@Override
		public Action getAction(DAOFactory daoFactory, ActionHelper actionHelper) {
			return new SendMessageAction(daoFactory, actionHelper);
		}
	},
	LOGIN("chat.view.login") {
		@Override
		public Action getAction(DAOFactory daoFactory, ActionHelper actionHelper) {
			return new LoginUserAction(daoFactory, actionHelper);
		}
	},
	LOGOUT("chat.view.logout") {
		@Override
		public Action getAction(DAOFactory daoFactory, ActionHelper actionHelper) {
			return new LogoutUserAction(daoFactory, actionHelper);
		}
	},
	KICK("chat.view.kick") {
		@Override
		public Action getAction(DAOFactory daoFactory, ActionHelper actionHelper) {
			return new KickUserAction(daoFactory, actionHelper);
		}
	},
	UNKICK("chat.view.unkick") {
		@Override
		public Action getAction(DAOFactory daoFactory, ActionHelper actionHelper) {
			return new UnkickUserAction(daoFactory, actionHelper);
		}
	},
	CHAT_MESSAGES("chat.view.chat_messages") {
		@Override
		public Action getAction(DAOFactory daoFactory, ActionHelper actionHelper) {
			return new GettingChatDataAction(daoFactory, actionHelper);
		}
	},
	ERROR("chat.view.error") {
		@Override
		public Action getAction(DAOFactory daoFactory, ActionHelper actionHelper) {
			return null;
		}
	};
	private final String chatPage;

	private ActionType(String page) {
		this.chatPage = ResourceBundle.getBundle("resourses.chatPages").getString(page);
	}

	public String getPage() {
		return chatPage;
	}

	public abstract Action getAction(DAOFactory daoFactory, ActionHelper actionHelper);
}
