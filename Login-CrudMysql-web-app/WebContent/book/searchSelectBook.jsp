<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book search</title>
</head>
<body>
	<table class="mainTable">
		<thead>
			<tr>
				<th>id book</th>
				<th>name book</th>
				<th>first name author</th>
				<th>last name author</th>
				<th>released year</th>
				<th>stock quantity</th>
				<th>pages</th>
			</tr>
		</thead>

		<c:forEach items="${bookSearch}" var="book">
			<tbody>
				<tr>
					<td>${book.id_book}</td>
					<td>${book.name_book}</td>
					<td>${book.first_name}</td>
					<td>${book.last_name}</td>
					<td>${book.year_released}</td>
					<td>${book.stock_quantity}</td>
					<td>${book.pages}</td>
				</tr>
			</tbody>

		</c:forEach>
	</table>
</body>
</html>