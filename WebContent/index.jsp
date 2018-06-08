<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	首页<br><br>
	<a href="${pageContext.request.contextPath }/UserHandler/loginUI">登录</a><br><br>
	<a href="${pageContext.request.contextPath }/UserHandler/logout">退出</a><br><br>
	======================================================<br><br>
	<shiro:user>
		欢迎:<shiro:principal property="username"/>登录<br><br><br><br>
	</shiro:user>
	
	
	<shiro:hasRole name="admin"><a href="${pageContext.request.contextPath }/zhangsan.jsp">张三</a></shiro:hasRole>
	<shiro:hasAnyRoles name="admin,manager">您好</shiro:hasAnyRoles><br><br>
	
	<shiro:user>
		<shiro:lacksRole name="vip">您不是VIP</shiro:lacksRole>
	</shiro:user>
</body>
</html>