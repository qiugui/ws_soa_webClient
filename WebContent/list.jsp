<%@page import="cn.edu.qiugui.service.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
当前登录用户为：${loginUser.getNickname() }<br/>
<%	List<User> users = (List<User>)request.getAttribute("users"); 
	for (User user:users){
%>
	<%=user.getUsername() %>----<%=user.getPassword() %>----<%=user.getNickname() %>
	<a href="http://localhost:8080/ws_soa_webClient/user.do?method=delete&username=<%=user.getUsername() %>">删除</a><br/>
<%		
	}
%>

</body>
</html>