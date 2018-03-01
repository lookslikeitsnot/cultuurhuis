<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Cultuurhuis' />
</head>
<body>
	<h1>Het Cultuurhuis:voorstellingen</h1>
	<img src="<c:url value="/images/voorstellingen.png"/>"
		title="voorstellingen">
	<h2>Genres</h2>
	<ul>
		<c:forEach items="${genres}" var="genre">
			<c:url value='' var='url'>
				<c:param name="genreid" value="${genre.id}" />
			</c:url>
			<li><a href="${url}">${genre.naam}</a></li>
		</c:forEach>
		<c:if test="${empty fouten and not empty param.genreid}">
			<h2>${genre.naam}voorstellingen</h2>
			<table>
				<tr>
					<th>Datum</th>
					<th>Titel</th>
					<th>Uitvoerders</th>
					<th>Prijs</th>
					<th>Vrije plaatsen</th>
					<th>Reserveren</th>
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
						<td>${voorstelling.vrijePlaatsen}</td>
						<td>
							<c:if test="${voorstelling.vrijePlaatsen>0}">
								<c:url value='/reservatie.htm' var='reservatieURL'>
									<c:param name='id' value='${voorstelling.id}' />
								</c:url>
								<a href="${reservatieURL}">Reserveren</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<c:if test="${not empty fouten}">
		</c:if>
	</ul>
</body>
</html>