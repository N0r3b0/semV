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
            <p style="color: green">${komunikat}</p>


    	
			<form action="servletDodajKsiazke" method="post">
				Tytuł: <input type="text" name="tytul" required ><br>
				Data wydania: <input type="text" name="dataWydania" pattern="\d{4}-\d{2}-\d{2}" placeholder="RRRR-MM-DD" required ><br>
				<label>
					Wydawnictwo:
					<select name="wydawnictwo" required >
						<c:forEach var="wydawnictwo" items="${wydawnictwa}">
						<option value=${wydawnictwo.idw}>${wydawnictwo.nazwa}
						</option>
						</c:forEach>
					</select>
				</label><br>
				<label>
					Kategoria:
					<select name="kategoria" required >
						<c:forEach var="kategoria" items="${kategorie}">
							<option value=${kategoria.idk}>${kategoria.opis}
							</option>
						</c:forEach>
					</select>
				</label><br>
				<input type="submit" value="Dodaj">
			</form>
	</div>
</body>
</html>