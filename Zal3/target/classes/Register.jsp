<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/Register" method="POST">
<input type="text" name="login">
<input type="password" name="password">
<input type="password" name="confirmPassword">
<input type="text" name="email">
<input type="submit" value="Zarejestruj!">
</form>
<br>
<%
if(request.getAttribute("error")!= null){
	out.println(request.getAttribute("error"));
}

%>
</body>
</html>