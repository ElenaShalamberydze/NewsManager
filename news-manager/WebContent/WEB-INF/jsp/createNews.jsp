<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>CreateNews</title>
	<fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="locale" var="loc" />
    <fmt:message bundle="${loc}" key="local.link.en" var="en"/>
    <fmt:message bundle="${loc}" key="local.link.ru" var="ru"/>
    <fmt:message bundle="${loc}" key="local.link.newsList" var="news_list"/>
    <fmt:message bundle="${loc}" key="local.link.addNews" var="add_news"/>
    <fmt:message bundle="${loc}" key="local.button.save" var="save_button"/>
    <fmt:message bundle="${loc}" key="local.button.cancel" var="cancel_button"/>
    <fmt:message bundle="${loc}" key="local.text.newsManagement" var="news_management"/>
    <fmt:message bundle="${loc}" key="local.text.newsTitle" var="news_title"/>
    <fmt:message bundle="${loc}" key="local.text.brief" var="news_brief"/>
    <fmt:message bundle="${loc}" key="local.text.content" var="news_content"/>
    <fmt:message bundle="${loc}" key="local.text.datePlaceholder" var="date_placeholder"/>
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
            	   	<input type="hidden" name="command" value="create"> 	
                	${news_title}
                	<input type="text" name="title"  placeholder="${news_title}" value="" required> <br />
                	${news_date} 
                	<input type="date" name="date" placeholder="${date_placeholder}" value="${requestScope.news.date}"> <br />                 	
                	${news_brief}  
                	<input type="text" name="brief" placeholder="${news_brief}" value="" required> <br />
               		${news_content} 
               		<input type="text" name="content" placeholder="${news_content}" value="" required> <br /><br />             	
                	<input type="submit" value="${save_button}">
                	<input type="button" value="${cancel_button}" onclick='location.href="/news-manager/controller?command=show_all"'>
            	</form>
			</td>
		</tr>
	</table>
</body>
</html>