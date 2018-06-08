package com.cn.service;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;


public class UserService
{
	/*shiroע��:
	 * ע��:��ʹ��shiroע����UserService����ʹ��@Service��ע��
	 * 1.@RequiresAuthentication:���ø�ע�����εķ���ʱ,�û���������֤��¼��
	 * 
	 * 2.@RequiresUser:���ø�ע�����εķ���ʱ,�û������ǵ�¼��;��������֤��¼��Ҳ������RemembeMe��¼
	 * 
	 * 3.@RequiresRoles:
	 * 		value:ָ����ɫ
	 * 		logical:ָ���û�Ҫӵ��ָ�������ý�ɫ,�����û�ֻ��ӵ�����е�һ����ɫ����		AND��OR
	 * 4.@RequiresPermissions
	 * 		value:ָ��Ȩ��
	 * 		logical:ָ���û�Ҫӵ��ָ��������Ȩ��,�����û�ֻ��ӵ�����е�һ��Ȩ�޼���		AND��OR
	 * */
	
	@RequiresRoles(value= {"admin","manager"},logical=Logical.AND)
	public void getUser()
	{
		System.out.println("��ȡuser");
	}

}
