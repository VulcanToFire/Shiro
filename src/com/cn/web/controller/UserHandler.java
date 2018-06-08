package com.cn.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.bean.User;
import com.cn.service.UserService;

@Controller
@RequestMapping("/UserHandler")
public class UserHandler
{
	private static final String SUCCESS = "success";
	private static final String LOGIN = "loginUI";
	@Autowired
	private UserService userService;
	
	@RequestMapping("/loginUI")
	public String loginUI(Map<String, Object> map)
	{
		map.put("user", new User());
		return LOGIN;
	}
	 
	@RequestMapping("/login")
	public String login(@Valid User user,BindingResult result,Map<String,Object> map)
	{
		if (result.getErrorCount() > 0)
		{
			System.out.println("表单校验失败!");
			map.put("user", user);
			return LOGIN;
		}
		System.out.println("表单校验成功!");
		
		
		Subject currentUser = SecurityUtils.getSubject();
		
		if (!currentUser.isAuthenticated())
		{
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
			token.setRememberMe(true);//可以在beans.xml的securityManager属性rememberMeManager.cookie.maxAge设置RememberMe时间
			try
			{
				
				currentUser.login(token);
			} catch (UnknownAccountException uae)
			{
				FieldError fieldError = new FieldError("user", "username", user.getUsername(),false, null, null, "用户名不存在");
				result.addError(fieldError);
				System.out.println("用户名 " + token.getPrincipal() + " 不存在");
				
				return LOGIN;
			} catch (IncorrectCredentialsException ice)
			{
				FieldError fieldError = new FieldError("user", "password", user.getUsername(),false, null, null, "密码错误");
				result.addError(fieldError);
				System.out.println("您提供的用户名" + token.getPrincipal() + " 的密码错误");
				return LOGIN;
			} catch (LockedAccountException lae)
			{
				FieldError fieldError = new FieldError("user", "username", user.getUsername(),false, null, null, "用户名已被锁定");
				result.addError(fieldError);
				System.out.println("当前用户 " + token.getPrincipal() + " 已被锁定");
				return LOGIN;
			} catch (AuthenticationException ae)
			{
				FieldError fieldError = new FieldError("user", "username", user.getUsername(),false, null, null, "用户名或密码错误");
				result.addError(fieldError);
				return LOGIN;
			}
		}

		System.out.println("用户 " + currentUser.getPrincipal() + " 成功登陆");
		return "redirect:/01.jsp";
	}

	@RequestMapping("/execute")
	public String execute()
	{
		userService.getUser();
		return SUCCESS;
	}

}
