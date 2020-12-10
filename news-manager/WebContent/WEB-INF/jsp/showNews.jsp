<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ShowNews</title>
	<fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale" var="loc"/>
    <fmt:message bundle="${loc}" key="local.link.en" var="en"/>
    <fmt:message bundle="${loc}" key="local.link.ru" var="ru"/>
    <fmt:message bundle="${loc}" key="local.link.newsList" var="news_list"/>
    <fmt:message bundle="${loc}" key="local.link.addNews" var="add_news"/>
    <fmt:message bundle="${loc}" key="local.button.edit" var="edit_button"/>
    <fmt:message bundle="${loc}" key="local.button.delete" var="delete_button"/>
    <fmt:message bundle="${loc}" key="local.text.newsManagement" var="news_management"/>
    <fmt:message bundle="${loc}" key="local.text.newsTitle" var="news_title"/>
    <fmt:message bundle="${loc}" key="local.text.newsDate" var="news_date"/>
    <fmt:message bundle="${loc}" key="local.text.brief" var="news_brief"/>
    <fmt:message bundle="${loc}" key="local.text.content" var="news_content"/>
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
				<table>
                	<tr>
                    	<td>${news_title}</td>
                    	<td colspan="2">${requestScope.news.title}</td>
                	</tr>
                	<tr>
                    	<td>${news_date}</td>
                    	<td colspan="2">${requestScope.news.date}</td>
                	</tr>
                	<tr>
                    	<td>${news_brief}</td>
                    	<td colspan="2">${requestScope.news.brief}</td>
                	</tr>
                	<tr>
               	    	<td>${news_content}</td>
                    	<td colspan="2">${requestScope.news.content}</td>
                	</tr>
                	<tr>
                    	<td><br><br></td>
                	</tr>
                	<tr>
                    	<td></td>
                    	<td align="right">
                        	<form action="/news-manager/controller" method="post">
                        	    <input type="hidden" name="command" value="show_for_edit">
                        	    <input type="hidden" name="id" value="${requestScope.news.id}">
                          		<input type="submit" value="${edit_button}">
                        	</form>
                    	</td>
                    	<td>
                        	<form action="/news-manager/controller" method="post">
                            	<input type="hidden" name="command" value="delete">
                            	<input type="hidden" name="deleteId" value="${requestScope.news.id}">
                            	<input type="submit" value="${delete_button}">
                        	</form>
                    	</td>
                	</tr>
            	</table>
			</td>
		</tr>
	</table>
</body>
</html>