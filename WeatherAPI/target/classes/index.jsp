<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="WeatherServlet" >
            <select name ="city">
                <option value="warszawa">Warszawa</option>
                <option value="gdansk">Gdansk</option>
                <option value="krakow">Krakow</option>
                <option value="wroclaw">Wroclaw</option>
                <option value="poznan">Poznan</option>
                <option value="lodz">Lodz</option>
                <option value="katowice">Katowice</option>
            </select>
            <br><input type="submit" value="Sprawdz pogode">
        </form>
</body>
</html>