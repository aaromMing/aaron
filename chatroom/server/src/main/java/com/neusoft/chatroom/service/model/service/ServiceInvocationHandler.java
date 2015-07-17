package com.neusoft.chatroom.service.model.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.neusoft.chatroom.api.service.IService;

public class ServiceInvocationHandler implements InvocationHandler {
	IService IService;

	public ServiceInvocationHandler(IService IService) {
		this.IService = IService;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws IllegalAccessException {
//		System.out.println(method);
		try {
			Object invoke = method.invoke(IService, args);
			
			return invoke;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
//		try {
//			if (method.getName().startsWith("get")) {
//				return method.invoke(IService, args);
//			} else if (method.getName().equals("setHotOrNotRating")) {
//				throw new IllegalAccessException();
//			} else if (method.getName().startsWith("set")) {
//				return method.invoke(IService, args);
//			}
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
		return null;
	}
}
