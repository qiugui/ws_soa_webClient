<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="user.do">
	<input type="hidden" name="method" value="login" />
	UserName:<input type="text" name="username" /><br/>
	PassWord:<input type="password" name="password" /><br/>
	<input type="submit">
</form>
</body>
</html>