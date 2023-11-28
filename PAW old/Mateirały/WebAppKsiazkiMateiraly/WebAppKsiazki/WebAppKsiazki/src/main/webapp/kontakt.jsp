<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Kontakt</title>
    <link rel="stylesheet" href="css/ksiazki.css">
</head>
<body>
	<c:import url="/menu"/>
	
	<div id = "kontakt">
    	<h1>Adres Firmy XYZ</h1>
    	<p>123 ul. Przykładowa, 45678 Miasto, Kraj</p>

    	<h2>Wydawnictwa, z którymi współpracujemy:</h2>
    	<ul>
        	<c:forEach var="wydawnictwo" items="${listaWydawnictw}">
                <li>${wydawnictwo.nazwa} - ${wydawnictwo.miasto}, ${wydawnictwo.panstwo}</li>
            </c:forEach>
    	</ul>
    </div>
</body>
</html>