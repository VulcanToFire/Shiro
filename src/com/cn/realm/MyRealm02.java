package com.cn.realm;

import java.util.HashSet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.cn.bean.User;

public class MyRealm02 extends AuthorizingRealm
{

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException
	{
		System.out.println("MyAuthenticatingRealm02");
		UsernamePasswordToken uToken = (UsernamePasswordToken) token;
		String username = uToken.getUsername();

		ByteSource salt = ByteSource.Util.bytes(username);

		System.out.println("根据usename从数据库获取该用户的SHA1密码");
		User user = new User();
		user.setUsername(username);
		user.setPassword(this.getDBCredentials("SHA1", "123", salt, 502));

		if (!username.equals(user.getUsername()))
		{
			throw new UnknownAccountException();
		}
		if ("Locked".equals("Lock"))
		{
			throw new LockedAccountException();
		}

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), salt,
				this.getName());
		return info;
	}

	public String getDBCredentials(String hashAlgorithmName, Object credentials, Object salt,
			int hashIterations)
	{
		Object object = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		return object.toString();
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
	{
		User principal = (User) principals.getPrimaryPrincipal();

		
		HashSet<String> roles = new HashSet<>();
		if ("zhangsan".equals(principal.getUsername()))
		{
			
			roles.add("manager");
		}

		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}

}
