package com.neusoft.chatroom.service.model.tools;

import java.lang.reflect.Proxy;

import com.neusoft.chatroom.api.service.IService;
import com.neusoft.chatroom.api.service.IUserService;
import com.neusoft.chatroom.service.model.service.ServiceInvocationHandler;
import com.neusoft.chatroom.service.model.service.UserService;

public class ServiceProxy {

	@SuppressWarnings("unchecked")
	public static <T extends IService> T getOwnerProxy(T service) {
		return (T) Proxy.newProxyInstance(service.getClass().getClassLoader(),
				service.getClass().getInterfaces(),
				new ServiceInvocationHandler(service));
	}

	public static <T extends IService> T getOwnerProxy(Class<T> service) {
		try {
			T newService = service.newInstance();
			System.out.println(newService);
			return getOwnerProxy(newService);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
//		IUserService service = new UserService();
		IUserService ownerProxy = ServiceProxy.getOwnerProxy(UserService.class);
		ownerProxy.selectAll();
	}

}
