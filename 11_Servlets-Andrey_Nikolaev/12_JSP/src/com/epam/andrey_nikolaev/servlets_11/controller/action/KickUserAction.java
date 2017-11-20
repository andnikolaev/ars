package com.epam.andrey_nikolaev.servlets_11.controller.action;

import com.epam.andrey_nikolaev.servlets_11.controller.exception.FailedChatOperationException;
import com.epam.andrey_nikolaev.servlets_11.daolayer.dao.abstract_factory.DAOFactory;
import com.epam.andrey_nikolaev.servlets_11.daolayer.transfer_object.User;

public class KickUserAction extends Action {

	KickUserAction(DAOFactory daoFactory, ActionHelper action) {
		super(daoFactory, action);
	}

	@Override
	public ActionType execute() {
		//TODO: ������� �������� ������ �� ������ �������
		User currentUser = actionHelper.getCurrentUser();
		User targetUser = actionHelper.getTargetUser();
		if (!daoFactory.getUserDAO().isLogged(currentUser.getName())) {
			throw new FailedChatOperationException("���������� ��������� ����");
		} else if (!"ADMIN".equals(daoFactory.getRoleDAO().getRole(currentUser.getName()))) {
			throw new FailedChatOperationException("�� �� �����");
		} else if (!daoFactory.getUserDAO().isLogged(targetUser.getName())) {
			throw new FailedChatOperationException("��������� ������������ �� ���������");
		} else if (daoFactory.getUserDAO().isKicked(targetUser.getName())) {
			throw new FailedChatOperationException("��������� ������������ ��� ������");
		}
		daoFactory.getUserDAO().kick(currentUser, targetUser);
		return ActionType.KICK;
	}

}
