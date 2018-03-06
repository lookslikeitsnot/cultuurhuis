<%@tag description='head onderdeel van pagina' pageEncoding='UTF-8'%>
<%@attribute name='title' required='true' type='java.lang.String'%>
<%@attribute name='imgtitle' type='java.lang.String'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<header>
	<c:if test="${not empty imgtitle}"><img src="<c:url value="/images/${imgtitle}.png"/>" alt="${title}"></c:if>	
	<h1>Het Cultuurhuis:${title}</h1>
</header>