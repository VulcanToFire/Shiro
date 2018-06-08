<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath }/UserHandler/login" method="POST" modelAttribute="user">
		用户名：<form:input path="username"/><form:errors path="username" delimiter=","></form:errors><br><br>
		密码：<form:password path="password"/><form:errors path="password" delimiter=","></form:errors><br><br>
		<input type="submit" value="提交">
	</form:form>
</body>
</html>