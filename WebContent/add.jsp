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
	<input type="hidden" name="method" value="add" />
	UserName:<input type="text" name="username" /><br/>
	NickName:<input type="text" name="nickname" /><br/>
	PassWord:<input type="password" name="password" /><br/>
	<input type="submit">
</form>
</body>
</html>