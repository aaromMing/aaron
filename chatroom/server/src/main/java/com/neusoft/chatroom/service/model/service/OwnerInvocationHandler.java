package com.neusoft.chatroom.service.model.service;

import java.lang.reflect.*;

public class OwnerInvocationHandler implements InvocationHandler {
	IUserService IUserService;

	public OwnerInvocationHandler(IUserService IUserService) {
		this.IUserService = IUserService;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException {

		try {
			if (method.getName().startsWith("get")) {
				return method.invoke(IUserService, args);
			} else if (method.getName().equals("setHotOrNotRating")) {
				throw new IllegalAccessException();
			} else if (method.getName().startsWith("set")) {
				return method.invoke(IUserService, args);
			}
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
