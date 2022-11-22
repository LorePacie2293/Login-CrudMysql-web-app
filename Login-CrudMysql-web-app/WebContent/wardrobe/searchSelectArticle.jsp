<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table class="mainTable">
		<thead>
			<tr>
				<th>Article</th>
				<th>Color</th>
				<th>Size</th>
				<th>Last worn</th>
			</tr>
		</thead>

		<c:forEach items="${articleSearch}" var="article">
			<tbody>
				<tr>
					<td>${article.nameArticle}</td>
					<td>${article.color}</td>
					<td>${article.size}</td>
					<td>${article.last_worn}</td>
				</tr>
			</tbody>

		</c:forEach>
	</table>
</body>
</html>