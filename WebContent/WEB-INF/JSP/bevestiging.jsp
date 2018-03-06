<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Bevestiging reservaties' />
</head>
<body>
	<vdab:header title='bevestiging' />
	<c:if test="${not empty sessionScope.mandje}">
		<a href="<c:url value='/'/>">Voorstellingen</a>
		<a href="<c:url value='/mandje.htm'/>">Reservatiemandje</a>

	</c:if>
	<h2>Stap 1:wie ben je ?</h2>
	<form method="post">
		<label for="gebruikersnaam">Gebruikersnaam</label><br> <input
			type="text" name="gebruikersnaam" id="gebruikersnaam" autofocus
			required><br> <label for="paswoord">Paswoord</label><br>
		<input type="password" name="paswoord" id="paswoord" required><br>
		<input type="submit" value="Zoek me op" id="zoekKnop">
	</form>
	<form action="/cultuurHuis/nieuweklant.htm">
		<input type="submit" value="Ik ben nieuw" id="nieuwKnop">
	</form>
	<c:if test="${not empty klant}">
	${klant}
	</c:if>
	<c:if test="${not empty fouten}">
	${fouten.bevestig}
	</c:if>
	<h2>Stap 2:Bevestigen</h2>
	<form method="post" action="/cultuurHuis/overzicht.htm">
		<input type="submit" value="Bevestigen" name="bevestigen"
			id="bevestigKnop">
	</form>
	<script>
		var klant = '${klant}';
		console.log('klant:' + klant);
		if (klant == null || klant.length == 0) {
			document.getElementById("bevestigKnop").disabled = true;
		} else {
			document.getElementById("zoekKnop").disabled = true;
			document.getElementById("nieuwKnop").disabled = true;
			document.getElementById("gebruikersnaam").disabled = true;
			document.getElementById("paswoord").disabled = true;
		}
	</script>
</body>
</html>