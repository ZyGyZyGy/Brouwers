<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<head>
	<v:head title='Brouwers toevoegen' />
</head>
<body>
	<v:menu />
	<h1>Brouwers toevoegen</h1>
	<c:url value="/brouwers" var="url"/>
	<v:brouwerform url="${url}" knopTekst="Toevoegen"></v:brouwerform>
</body>
</html>
