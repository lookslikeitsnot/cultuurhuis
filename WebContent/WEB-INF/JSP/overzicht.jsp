<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Overzicht' />
</head>
<body>
	<vdab:header title='overzicht' imgtitle='overzicht'/>
	<a href="<c:url value='/index.htm'/>">Voorstellingen</a>
	<c:if test='${not empty gelukt}'>
		<h2>Gelukte reserveringen</h2>
		<table>
			<tr>
				<th>Datum</th>
				<th>Titel</th>
				<th>Uitvoerders</th>
				<th>Prijs</th>
				<th>Plaatsen</th>
			</tr>
			<c:forEach items="${gelukt}" var="voorstelling">
				<tr>
					<fmt:parseDate value="${voorstelling.key.datum}"
						pattern="yyyy-MM-dd'T'HH:mm" var="datum" />
					<fmt:formatDate value="${datum}" type="both"
						pattern="dd/MM/yy HH:mm" var="fmtDate" />
					<td><c:out value="${fmtDate}" /></td>
					<td>${voorstelling.key.titel}</td>
					<td>${voorstelling.key.uitvoerders}</td>
					<td><fmt:formatNumber value='${voorstelling.key.prijs}' type="currency" currencyCode="EUR"/></td>
					<td>${voorstelling.value}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test='${not empty mislukt}'>
		<h2>Mislukte reserveringen</h2>
		<table>
			<tr>
				<th>Datum</th>
				<th>Titel</th>
				<th>Uitvoerders</th>
				<th>Prijs</th>
				<th>Plaatsen</th>
				<th>Vrije plaatsen</th>
			</tr>
			<c:forEach items="${mislukt}" var="voorstelling">
				<tr>
					<fmt:parseDate value="${voorstelling.key.datum}"
						pattern="yyyy-MM-dd'T'HH:mm" var="datum" />
					<fmt:formatDate value="${datum}" type="both"
						pattern="dd/MM/yy HH:mm" var="fmtDate" />
					<td><c:out value="${fmtDate}" /></td>
					<td>${voorstelling.key.titel}</td>
					<td>${voorstelling.key.uitvoerders}</td>
					<td><fmt:formatNumber value='${voorstelling.key.prijs}' type="currency" currencyCode="EUR"/></td>
					<td>${voorstelling.value}</td>
					<td>${voorstelling.key.vrijePlaatsen}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
