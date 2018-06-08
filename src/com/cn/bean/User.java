package com.cn.bean;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class User implements Serializable
{
	
	private static final long serialVersionUID = -3030025549651371903L;

	@NotEmpty
	@Length(min=3,max=10)
	private String username;
	
	@NotEmpty
	@Length(min=3,max=8)
	private String password;
	
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	

}
