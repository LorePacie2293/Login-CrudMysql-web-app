<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/insertForm.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Home book</title>
</head>
<body>
	<jsp:include page="/book/header.jsp"></jsp:include>

	<p style="color: red;">${errorString}</p>
	
	<c:catch var="tryCatch">
		<%
		String crud = request.getAttribute("crud").toString();
		%>
	</c:catch>

	<c:if test="${tryCatch != null}">
		<jsp:include page="/book/CrudMenu.jsp"></jsp:include>
	</c:if>
	<c:if test="${tryCatch == null}">
		<jsp:include page="/book/CrudMenu.jsp"></jsp:include>
		<c:choose>
			<c:when test="${crud == 1}">
				<jsp:include page="/book/createBook.jsp"></jsp:include>
			</c:when>


			<c:when test="${crud == 2}">
				<c:catch var="trySearch">
					<jsp:include page="/book/selectBook.jsp"></jsp:include>
					<%
					Boolean isSearch = Boolean.parseBoolean(request.getAttribute("search").toString());
					%>
				</c:catch>

				<c:if test="${trySearch == null}">
					<jsp:include page="/book/searchSelectBook.jsp"></jsp:include>
				</c:if>
			</c:when>
			<c:when test="${crud == 3}">
				<jsp:include page="/book/editBook.jsp"></jsp:include>
			</c:when>
			<c:otherwise>
				<jsp:include page="/book/CrudMenu.jsp"></jsp:include>
			</c:otherwise>
		</c:choose>
	</c:if>
	
	<table class="mainTable">
		<thead>
			<tr>
				<th>Id book</th>
				<th>Name book</th>
				<th>First name author</th>
				<th>Last name author</th>
				<th>Release year</th>
				<th>Stock_quantity</th>
				<th>Years</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
		
		<c:forEach items="${bookList}" var="book">
			<tbody>
				<tr>
					<td>${book.id_book}</td>
					<td>${book.name_book}</td>
					<td>${book.first_name}</td>
					<td>${book.last_name}</td>
					<td>${book.year_released}</td>
					<td>${book.stock_quantity}</td>
					<td>${book.pages}</td>
					<td><a
						href="${pageContext.request.contextPath}/homeBook/editBook?code=${book.id_book}">edit</a></td>
					<td><a
						href="${pageContext.request.contextPath}/homeBook/deleteBook?code=${book.id_book}">delete</a></td>
				</tr>
			</tbody>

		</c:forEach>
	</table>

	
</body>

</html>