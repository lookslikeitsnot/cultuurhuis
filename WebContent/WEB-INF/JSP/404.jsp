<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='404' />
</head>
<body>
	<h1>Het Cultuurhuis</h1>
	<h2>Sorry!</h2>
	<p>Dit adres bestaat niet</p>
	<a href="<c:url value='/'/>">Klik hier om alle voorstellingen te zien</a>
</body>
</html>