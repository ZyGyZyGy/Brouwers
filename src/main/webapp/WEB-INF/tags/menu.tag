<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='security' uri='http://www.springframework.org/security/tags'%> 
<nav>
	<ul>
		<li><a href="<c:url value='/'/>">&#8962;</a></li>
		
		<li><a href="#">Brouwers</a>
			<ul>
				<li><a href="<c:url value='/brouwers'/>">Alle brouwers</a></li>
				<li><a href="<c:url value='/brouwers/beginnaam'/>">Brouwers op naam</a></li>
				<security:authorize url="/brouwers/toevoegen">
					<li><a href="<c:url value='/brouwers/toevoegen'/>">Brouwers toevoegen</a></li>
				</security:authorize>
				<li><a href="<c:url value='/brouwers/opAlfabet'/>">Brouwers op alfabet</a></li>
			</ul>
		</li>
		
		<c:if test="${pageContext.response.locale.language != 'nl'}">
			<c:url value="" var="nederlandsURL">
				<c:param name="locale" value="nl_be"/>
			</c:url>
			<li><a href="${nederlandsURL}"><img src="<c:url value='/images/nl.png'/>" alt="Nederlands" title="Nederlands"></a></li>
		</c:if>
		<c:if test="${pageContext.response.locale.language != 'en'}">
			<c:url value="" var="engelsURL">
				<c:param name="locale" value="en_us"/>
			</c:url>
			<li><a href="${engelsURL}"><img src='<c:url value="/images/en.png"></c:url>' alt="English" title="English"></a></li>
		</c:if>
		
		<security:authorize access="isAnonymous()">
			<li><a href="<c:url value='/login'/>">Aanmelden</a></li>
		</security:authorize>
		<security:authorize access="isAuthenticated()">
			<li>
				<form method="post" action="<c:url value='/logout'/>" id="logoutform">
					<input type="submit" value='<security:authentication property="name"/> afmelden' id="logoutbutton">
					<security:csrfInput/>
				</form>
			</li>
		</security:authorize>
	</ul>
</nav>
