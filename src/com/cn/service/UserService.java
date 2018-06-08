package com.cn.service;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;


public class UserService
{
	/*shiro注解:
	 * 注意:若使用shiro注解则UserService不能使用@Service等注解
	 * 1.@RequiresAuthentication:调用该注解修饰的方法时,用户必须是认证登录的
	 * 
	 * 2.@RequiresUser:调用该注解修饰的方法时,用户必须是登录的;可以是认证登录、也可以是RemembeMe登录
	 * 
	 * 3.@RequiresRoles:
	 * 		value:指定角色
	 * 		logical:指定用户要拥有指定的所用角色,还是用户只需拥有其中的一个角色即可		AND、OR
	 * 4.@RequiresPermissions
	 * 		value:指定权限
	 * 		logical:指定用户要拥有指定的所用权限,还是用户只需拥有其中的一个权限即可		AND、OR
	 * */
	
	@RequiresRoles(value= {"admin","manager"},logical=Logical.AND)
	public void getUser()
	{
		System.out.println("获取user");
	}

}
