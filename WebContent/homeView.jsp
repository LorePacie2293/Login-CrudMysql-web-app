<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/insertForm.css" rel="stylesheet" type="text/css">

<meta charset="UTF-8">
<title>Product List</title>
</head>
<body>
	<jsp:include page="/wardrobe/header.jsp"></jsp:include>

	<p style="color: red;">${errorString}</p>

	<c:catch var="tryCatch">
		<%
		String crud = request.getAttribute("crud").toString();
		%>
	</c:catch>

	<c:if test="${tryCatch != null}">
		<jsp:include page="/wardrobe/CrudMenu.jsp"></jsp:include>
	</c:if>
	<c:if test="${tryCatch == null}">
		<jsp:include page="/wardrobe/CrudMenu.jsp"></jsp:include>
		<c:choose>
			<c:when test="${crud == 1}">
				<jsp:include page="/wardrobe/createArticle.jsp"></jsp:include>
			</c:when>


			<c:when test="${crud == 2}">
				<c:catch var="trySearch">
					<jsp:include page="/wardrobe/selectArticle.jsp"></jsp:include>
					<%
					Boolean isSearch = Boolean.parseBoolean(request.getAttribute("search").toString());
					%>
				</c:catch>

				<c:if test="${trySearch == null}">
					<jsp:include page="/wardrobe/searchSelectArticle.jsp"></jsp:include>
				</c:if>
			</c:when>
			<c:when test="${crud == 3}">
				<jsp:include page="/wardrobe/editArticle.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
				<jsp:include page="/wardrobe/CrudMenu.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
	</c:if>
	
	<table class="mainTable">
		<thead>
			<tr>
				<th><a href = "${pageContext.request.contextPath}/home?sort=article">Id Article</a></th>
				<th>Article</th>
				<th>Color</th>
				<th>Size</th>
				<th>Last worn</th>
				<th></th>
				<th></th>
			</tr>
		</thead>

		<c:forEach items="${articleList}" var="article">
			<tbody>
				<tr>
					<td>${article.idArticle}</td>
					<td>${article.nameArticle}</td>
					<td>${article.color}</td>
					<td>${article.size}</td>
					<td>${article.last_worn}</td>
					<td><a
						href="${pageContext.request.contextPath}/home/editArticle?code=${article.idArticle}">edit</a></td>
					<td><a
						href="${pageContext.request.contextPath}/home/deleteArticle?code=${article.idArticle}">delete</a></td>
				</tr>
			</tbody>

		</c:forEach>
	</table>

	
</body>

</html>