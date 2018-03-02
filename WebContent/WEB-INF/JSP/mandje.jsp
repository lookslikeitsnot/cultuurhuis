<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Mandje' />
</head>
<body>
	<vdab:header title='mandje' />
	<c:if test="${empty mandje}">
	<h2>Mandje is leeg</h2>
	<c:if test="${not empty fouten}">${fouten.mandje}</c:if>
	<a href="<c:url value='/'/>">Klik hier om alle voorstellingen te zien</a>
	</c:if>
	<c:if test="${not empty voorstellingen}">
	<form method="post">
		<table>
		
			<tr>
				<th>Datum</th>
				<th>Titel</th>
				<th>Uitvoerders</th>
				<th>Prijs</th>
				<th>Plaatsen</th>
				<th><input type="submit" value="Verwijderen"></th>
			</tr>
			<c:forEach items="${voorstellingen}" var="voorstelling">
					<tr>
						<fmt:parseDate value="${voorstelling.datum}"
							pattern="yyyy-MM-dd'T'HH:mm" var="datum" />
						<fmt:formatDate value="${datum}" type="both"
							pattern="dd/MM/yy HH:mm" var="fmtDate" />
						<td><c:out value="${fmtDate}" /></td>
						<td>${voorstelling.titel}</td>
						<td>${voorstelling.uitvoerders}</td>
						<td>&euro;${voorstelling.prijs}</td>
						<td>${voorstelling.gereserveerdePlaatsen}</td>
						<td>
							<input type="checkbox" name="voorstellingId" value="${voorstelling.id}">
						</td>
					</tr>
				</c:forEach>
		</table>
		</form>
	</c:if>
</body>
</html>