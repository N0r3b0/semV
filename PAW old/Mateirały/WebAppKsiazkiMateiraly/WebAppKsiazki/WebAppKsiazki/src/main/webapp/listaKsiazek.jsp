<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Lista Ksiazek</title>
	<link rel="stylesheet" href="css/ksiazki.css">
</head>
<body>
	<c:import url="/menu"/>
	<div id="zawartosc">
		<p>Witamy w naszej aplikacji, która ma pomagać w wyborze książek. Obecnie 
		możesz tu znaleźć informacje na temat ciekawych książek z 
		informatyki.</p>
	</div>
	
	
	<div id = "listaKsiazek">
		<p>Liczba książek: ${tk.size()}</p>
		<h2>Lista książek</h2>
		<c:forEach var="ksiazka" items="${tk}">
			<p>${ksiazka.idk}. ${ksiazka.tytul}<br> Data wydania: ${ksiazka.rokWydania}<br> Wydawnictwo: ${ksiazka.wyd.nazwa}</p>
		</c:forEach>
	</div>
</body>
</html>