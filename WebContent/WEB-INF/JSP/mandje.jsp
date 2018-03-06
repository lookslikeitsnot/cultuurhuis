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
	<vdab:header title='mandje' imgtitle='mandje'/>
	<c:if test="${empty voorstellingenEnPlaatsen}">
		<h2>Mandje is leeg</h2>
		${fouten.mandje}
	</c:if>
	<a href="<c:url value='/'/>">Voorstellingen</a>
	<c:if test="${not empty voorstellingenEnPlaatsen}">
		<a href="<c:url value='/bevestig.htm'/>">Bevestiging reservatie</a>
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
				<c:forEach items="${voorstellingenEnPlaatsen}" var="voorstelling">
					<tr>
						<fmt:parseDate value="${voorstelling.key.datum}"
							pattern="yyyy-MM-dd'T'HH:mm" var="datum" />
						<fmt:formatDate value="${datum}" type="both"
							pattern="dd/MM/yy HH:mm" var="fmtDate" />
						<td><c:out value="${fmtDate}" /></td>
						<td>${voorstelling.key.titel}</td>
						<td>${voorstelling.key.uitvoerders}</td>
						<td><fmt:formatNumber value='${voorstelling.key.prijs}'
								type="currency" currencyCode="EUR" /></td>
						<td>${voorstelling.value}</td>
						<td><input type="checkbox" name="voorstellingIds"
							value="${voorstelling.key.id}"></td>
					</tr>
				</c:forEach>
			</table>
		</form>
		<p>
			Te betalen:
			<fmt:formatNumber value='${mandjeWaarde}' type="currency"
				currencyCode="EUR" />
		</p>
	</c:if>
</body>
</html>