<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%> 
<%@taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<!doctype html>
<html lang='nl'>
<head>
	<v:head title='Brouwers op naam' />
</head>
<body>
	<v:menu />
	<h1>Brouwers op naam</h1>
	<c:url value="/brouwers" var="url"/>
	<form:form action="${url}" commandName="beginnaamForm" method="get">
		<form:label path="beginnaam">Begin van de naam:<form:errors path="beginnaam"/></form:label>
		<form:input path="beginnaam" autofocus="autofocus" type="text" required="required"/>
		<input type="submit" value="Zoeken">
	</form:form>
	<ul>
		<c:forEach items="${brouwers}" var="brouwer">
			<li>${brouwer.naam}</li>			
		</c:forEach>
	</ul>
</body>
</html>
