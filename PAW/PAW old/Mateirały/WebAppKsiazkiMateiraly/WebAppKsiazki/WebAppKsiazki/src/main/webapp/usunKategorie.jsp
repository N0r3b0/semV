<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Usuń kategorię</title>
    <link rel="stylesheet" href="css/ksiazki.css">
</head>
<body>
    <c:import url="/menu"/>
    
    <div id = "zawartosc">
        <h1>Usuń Kategorię</h1>
        
        <form action="servletUsunKategorie" method="post">
            ID Kategorii do Usunięcia: <input type="text" name="id"><br>
            <input type="submit" value="Usuń">
        </form>
        
        <c:if test="${not empty komunikat}">
            <p style="color: green">${komunikat}</p>
        </c:if>
    </div>
</body>
</html>
