<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
     
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Error</title>
	<fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.link.en" var="en"/>
    <fmt:message bundle="${loc}" key="local.link.ru" var="ru"/>
    <fmt:message bundle="${loc}" key="local.link.newsList" var="news_list"/>
    <fmt:message bundle="${loc}" key="local.link.addNews" var="add_news"/>
    <fmt:message bundle="${loc}" key="local.message.error1" var="error1" />
    <fmt:message bundle="${loc}" key="local.message.error2" var="error2" />
    <fmt:message bundle="${loc}" key="local.message.error3" var="error3" />
    <fmt:message bundle="${loc}" key="local.message.error4" var="error4" />
    <fmt:message bundle="${loc}" key="local.text.newsManagement" var="news_management"/>
</head>
<body>

	<h1>${news_management}</h1> <br />
	<a href="/news-manager/controller?command=change_locale&lang=en">${en} </a>
	<a href="/news-manager/controller?command=change_locale&lang=ru">${ru}</a>
	<hr />
	
	<table>
		<tr> 
			<td valign="top">
				<table>
					<tr>
						<td><a href="/news-manager/controller?command=show_all">${news_list}</a></td>
					</tr>
					<tr>
						<td><a href="/news-manager/controller?command=to_create">${add_news}</a></td>
					</tr>
				</table>
			</td>
			<td>
				<c:set var="errorId" scope="request" value="${requestScope.errorId}" />
				<c:choose>
					<c:when test="${errorId == 1}">
						<c:out value="${error1}"/>
					</c:when>
					<c:when test="${errorId == 2}">
						<c:out value="${error2}"/>
					</c:when>
					<c:when test="${errorId == 3}">
						<c:out value="${error3}"/>
					</c:when>
					<c:when test="${errorId == 4}">
						<c:out value="${error4}"/>
					</c:when>
				</c:choose>									
			</td>
		</tr>
	</table>
		
</body>
</html>