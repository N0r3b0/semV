<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="menu">
	<ul>
		<li><a href="index.jsp">Strona domowa</a></li>
		<li><a href="servletListaKsiazek">Wszystkie książki</a></li>
		<li class="podmenu">Kategorie
			<ul  class="listaKategorii">
			<!-- tutaj umieść elementy li z nazwami kategorii-->
				<c:forEach var="el" items="${ta}">
					<li><a href="servletListaKsiazek?id=${el.idk}">${el.opis}</a></li>
				</c:forEach>
			</ul>
		</li>
		<li  class="podmenu">Dodaj / Usuń
			<ul class="listaKategorii">
				<li><a href="servletDodajKsiazke">Dodaj książkę</a></li>
				<li><a href="usunKsiazke.jsp">Usuń książkę</a></li>
				<li><a href="dodajKategorie.jsp">Dodaj kategorię</a></li>
        		<li><a href="usunKategorie.jsp">Usuń kategorię</a></li>
        		<li><a href="dodajWydawnictwo.jsp">Dodaj wydawnictwo</a></li>
        		<li><a href="usunWydawnictwo.jsp">Usuń wydawnictwo</a></li>
			</ul>
		</li>
		<li><a href="servletListaWydawnictw">Kontakt</a></li>
	</ul>
</div>
