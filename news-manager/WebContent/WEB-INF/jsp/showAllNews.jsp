<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ShowAllNews</title>
	<fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.link.en" var="en" />
    <fmt:message bundle="${loc}" key="local.link.ru" var="ru" />
    <fmt:message bundle="${loc}" key="local.link.newsList" var="news_list" />
    <fmt:message bundle="${loc}" key="local.link.addNews" var="add_news" />
    <fmt:message bundle="${loc}" key="local.link.view" var="view" />
    <fmt:message bundle="${loc}" key="local.link.edit" var="edit" />
    <fmt:message bundle="${loc}" key="local.button.delete" var="delete_button" />
    <fmt:message bundle="${loc}" key="local.text.newsManagement" var="news_management"/>
</head>
<body>

	<h1>${news_management}</h1>
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
				<form action="/news-manager/controller" method="post">
                <input type="hidden" name="command" value="delete">
                <table>
                    <c:forEach var="news" items="${requestScope.newsList}" >
                        <tr>
                            <th colspan="2" align="left"><c:out value="${news.title}"/></th>
                            <td colspan="3" align="center"><c:out value="${news.date}"/></td>
                        </tr>
                        <tr>
                            <td><c:out value="${news.brief}"/></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><a href="/news-manager/controller?command=show_news&id=${news.id}">${view}</a></td>
                            <td><a href="/news-manager/controller?command=show_for_edit&id=${news.id}">${edit}</a></td>
                            <td><input type="checkbox" name="deleteId" value="${news.id}"></td>
                        </tr>
                        <tr>
                            <td><br><br></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td align="right" colspan="5">
                        	<c:if test = "${!empty requestScope.newsList}">
                        		<input type="submit" value="${delete_button}">                        	
                        	</c:if>
                        </td>
                    </tr>
                </table>
            	</form>
			</td>
		</tr>
	</table>
</body>
</html>