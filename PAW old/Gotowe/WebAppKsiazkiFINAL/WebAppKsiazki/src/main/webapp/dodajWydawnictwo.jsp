<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Dodaj wydawnictwo</title>
    <link rel="stylesheet" href="css/ksiazki.css">
</head>
<body>
    <c:import url="/menu"/>

    <div id = "zawartosc">
        <h1>Dodaj Wydawnictwo</h1>
        
        <form action="servletDodajWydawnictwo" method="post">
            Nazwa: <input type="text" name="nazwa" required ><br>
            Miasto: <input type="text" name="miasto" required ><br>
            Państwo: <input type="text" name="panstwo" required ><br>
            <input type="submit" value="Dodaj">
        </form>
        
        <c:if test="${not empty komunikat}">
            <p style="color: green">${komunikat}</p>
        </c:if>
    </div>
</body>
</html>
