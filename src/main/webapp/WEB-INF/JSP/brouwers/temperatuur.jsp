<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html>
<html lang='nl'>
<head>
	<v:head title='Temperatuur in ${gemeente}' />
</head>
<body>
	<v:menu />
	<c:choose>
		<c:when test='${empty fout}'>
			<h1>Temperatuur in ${gemeente} is ${temperatuur}&#8451;</h1>
		</c:when>
		<c:otherwise>
			<div class='fout'>${fout}</div>
		</c:otherwise>
	</c:choose>
</body>
</html>
