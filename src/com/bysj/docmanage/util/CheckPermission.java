package com.bysj.docmanage.util;

import com.bysj.docmanage.domain.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

//拦截器用来检查权限
public class CheckPermission extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		System.out.println("----------------->>>>>>>>>>>>>>拦截前");

		
		//获取当前用户信息
		User user=(User) ActionContext.getContext().getSession().get("user");
		//获取namespace
		//String namespace= invocation.getProxy().getNamespace();
		//获取action的名字
		String actionName= invocation.getProxy().getActionName();
		String PermUrl =  actionName;
		//String PermUrl = namespace + actionName;
		
		//如果未登录，转到登陆页面
		if(user==null)
		{
			
			//如果去登陆,放行
			if(PermUrl.startsWith("user_login")){
				return invocation.invoke();
			}
			else{
				//如果不是去登陆就转到loginUI
				return "loginUI";
			}
		}
		//如果已经登录
		
		else{
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+user.hasPermissionByUrl(PermUrl));
			if(user.hasPermissionByUrl(PermUrl))
			{
				//return "noPermissionError";
				return invocation.invoke();
			}else{
				return "noPermissionError";
			}
		}
		
	}

	
}
