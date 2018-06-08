package com.cn.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapFactory
{
	public LinkedHashMap<String, String> getFilterChainDefinitionMap()
	{
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		//从数据库获取  资源路径=过滤器
		map.put("/", "anon");
		map.put("/index.jsp", "anon");
		map.put("/UserHandler/login", "anon");
		map.put("/UserHandler/logout", "logout");
		map.put("/zhangsan.jsp", "authc,roles[admin,manager]");
		map.put("/a.jsp", "authc");
		map.put("/**", "user");
		return map;
		
	}

}
