<%@tag description='head onderdeel van pagina' pageEncoding='UTF-8'%>
<%@attribute name='title' required='true' type='java.lang.String'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<header>
<h1>Het Cultuurhuis:${title}</h1>
<img src="<c:url value="/images/${title}.png"/>"
		alt="${title}">
</header>