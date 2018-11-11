<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		if (session.getAttribute("login") == null) {
			out.println("<a href='Login.jsp'>Zaloguj sie</a><br><a href='Register.jsp'>Rejestracja</a><br><a href='GrantPremium'>Nadaj premium</a><br>");
		}
		else if(session.getAttribute("login").equals("admin"))
		{
			out.println("<a href='Login'>Zaloguj sie</a><br><a href='Register'>Rejestracja</a><br><a href='GrantPremium.jsp'>Nadaj premium</a><br>");
		}
		else
		{
			out.println("<a href='Login'>Zaloguj sie</a><br><a href='Register'>Rejestracja</a><br><a href='GrantPremium'>Nadaj premium</a><br>");
		}
	
	%>
	<a href='Profile'>Profil</a>
	<br>
	<a href='Premium'>Premium</a>
	<br>
	<a href='ShowUsers'>Baza danych</a>
	<br>
	<a href='Logout'>Wyloguj sie</a>
	<br>
	<%
		if (request.getAttribute("notify") != null) {
			out.println(request.getAttribute("notify"));
		}
		if (session.getAttribute("login") != null) {
			out.println("Witaj " + session.getAttribute("login") + "<br>Id sesji: " + session.getId() + "<br>");
		} else {
			out.println("Witaj niezalogowany!<br>");
		}
	%>

</body>
</html>