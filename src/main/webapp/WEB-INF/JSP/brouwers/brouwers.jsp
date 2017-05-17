<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='v' uri='http://vdab.be/tags'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%> 
<!doctype html>
<html lang='nl'>
<head>
	<v:head title='Alle brouwers' />
</head>
<body>
	<v:menu />
	<h1>Alle brouwers</h1>
	<table>
		<thead>
			<tr>
				<th>Nummer</th><th>Naam</th><th>Straat</th><th>HuisNr</th>
				<th>Postcode</th><th>Gemeente</th><th>Omzet</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${brouwers}" var="brouwer">
			<spring:url value="/temperatuur/{gemeente}" var="temperatuurURL">
				<spring:param name="gemeente" value="${brouwer.adres.gemeente}"></spring:param>
			</spring:url>
				<tr>
					<td>${brouwer.brouwerNr}</td><td>${brouwer.naam}</td><td>${brouwer.adres.straat}</td>
					<td>${brouwer.adres.huisNr}</td><td>${brouwer.adres.postcode}</td>
					<td>${brouwer.adres.gemeente} <a href="${temperatuurURL}">temperatuur</a></td><td>${brouwer.omzet}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
