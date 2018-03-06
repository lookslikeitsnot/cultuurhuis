<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Nieuwe Klant' />
</head>
<body>
	<vdab:header title='nieuwe klant' imgtitle='nieuwe'/>
	<a href="<c:url value='/'/>">Voorstellingen</a>
	<a href="<c:url value='/mandje.htm'/>">Reservatiemandje</a>
	<a href="<c:url value='/bevestig.htm'/>">Bevestiging reservatie</a>
	<form method="post">
		<label for="voornaam">Voornaam</label><br> <input type="text"
			name="voornaam" id="voornaam" required><br> <label
			for="familienaam">Familienaam</label><br> <input type="text"
			name="familienaam" id="familienaam" required><br> <label
			for="straat">Straat</label><br> <input type="text" name="straat"
			id="straat" required><br> <label for="huisnr">Huisnr</label><br>
		<input type="text" name="huisnr" id="huisnr" required><br>
		<label for="postcode">Postcode</label><br> <input type="text"
			name="postcode" id="postcode" required><br> <label
			for="gemeente">Gemeente</label><br> <input type="text"
			name="gemeente" id="gemeente" required><br> <label
			for="gebruikersnaam">Gebruikersnaam</label><br> <input
			type="text" name="gebruikersnaam" id="gebruikersnaam" required><br>
		<label for="paswoord">Paswoord</label><br> <input type="password"
			name="paswoord" id="paswoord" required><br> <label
			for="paswoordHerhaald">Herhaal paswoord</label><br> <input
			type="password" name="paswoordHerhaald" id="paswoordHerhaald"
			required><br> <input type="submit" value="OK">
	</form>
	<c:if test='${not empty fouten}'>
		<ul>
			<c:forEach items='${fouten}' var='fout'>
				<li>${fout.key}${fout.value}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>