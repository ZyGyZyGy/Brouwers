<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%> 
<!doctype html>
<html lang='nl'>
<head>
	<v:head title='Brouwers op alfabet' />
</head>
<body>
	<v:menu />
	<h1>Brouwers op alfabet</h1>
	<c:forEach items="${alleLetters}" var="letter">
		<spring:url var="url" value="/brouwers/opAlfabet/{eersteLetter}">
			<spring:param name="eersteLetter" value="${letter}"></spring:param>
		</spring:url>
		<h3 id="alfabet"><a href="${url}">${letter}</a></h3>
	</c:forEach>
	<c:if test="${not empty brouwers}">
		<ul>
			<c:forEach items="${brouwers}" var="brouwer">
				<li>${brouwer.naam} (${brouwer.adres.gemeente})</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>
