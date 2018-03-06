<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Reservatie' />
</head>
<body>
	<vdab:header title='reserveren'/>
	<c:url value='/index.htm' var='homeURL' />
	<a href="${homeURL}">Voorstellingen</a>
	<c:if test="${not empty voorstelling}">
		<dl>
			<dt>Voorstelling:</dt>
			<dd>${voorstelling.titel}</dd>
			<dt>Uitvoerders:</dt>
			<dd>${voorstelling.uitvoerders}</dd>
			<dt>Datum:</dt>
			<fmt:parseDate value="${voorstelling.datum}"
				pattern="yyyy-MM-dd'T'HH:mm" var="datum" />
			<fmt:formatDate value="${datum}" type="both" pattern="dd/MM/yy HH:mm"
				var="fmtDate" />
			<dd>
				<c:out value="${fmtDate}" />
			</dd>
			<dt>Prijs:</dt>
			<dd><fmt:formatNumber value='${voorstelling.prijs}' type="currency" currencyCode="EUR"/></dd>
			<dt>Vrije plaatsen:</dt>
			<dd>${voorstelling.vrijePlaatsen}</dd>
			<dt>Plaatsen</dt>
			<dd>
			<form method="post">
			<input name="id" type="hidden" value="${voorstelling.id}">
			<input name="aantalplaatsen" value="${alinmandje}" 
			type="number" min="1" max="${voorstelling.vrijePlaatsen}" 
			autofocus required>${fouten.aantalplaatsen}
			<input type="submit" value="Reserveren">
			</form>
			</dd>
		</dl>
	</c:if>
</body>
</html>