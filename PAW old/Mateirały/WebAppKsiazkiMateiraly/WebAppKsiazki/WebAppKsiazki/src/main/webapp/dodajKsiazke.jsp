<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Dodaj ksiazke</title>
	<link rel="stylesheet" href="css/ksiazki.css">
</head>
<body>
	<c:import url="/menu"/>
	<div id = "zawartosc">
		<h1>Dodaj Książkę</h1>
    	<form action="servletDodajKsiazke" method="post">
        	Tytuł: <input type="text" name="tytul" required ><br>
        	Data wydania: <input type="text" name="dataWydania" pattern="\d{4}-\d{2}-\d{2}" placeholder="RRRR-MM-DD" required ><br>
        	Wydawnictwo: <input type="text" name="wydawnictwo" required ><br>
        	Kategoria: <input type="text" name="kategoria" required ><br>
        	<input type="submit" value="Dodaj">
    	</form>
    	

    	
    	<c:if test="${not empty komunikat}">
            <p style="color: green">${komunikat}</p>
        </c:if>
	</div>
</body>
</html>