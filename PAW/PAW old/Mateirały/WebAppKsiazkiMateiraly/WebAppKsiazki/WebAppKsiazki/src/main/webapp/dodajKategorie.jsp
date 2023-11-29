<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Dodaj kategorię</title>
    <link rel="stylesheet" href="css/ksiazki.css">
</head>
<body>
    <c:import url="/menu"/>

    <div id = "zawartosc">
        <h1>Dodaj Kategorię</h1>
        
        <form action="servletDodajKategorie" method="post">
            Opis: <input type="text" name="opis" required ><br>
            <input type="submit" value="Dodaj">
        </form>
        
        <c:if test="${not empty komunikat}">
            <p style="color: green">${komunikat}</p>
        </c:if>
    </div>
</body>
</html>
