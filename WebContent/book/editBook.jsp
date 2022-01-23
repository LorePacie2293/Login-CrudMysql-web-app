<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Edit Product</title>
      <link href="${pageContext.request.contextPath}/css/editForm.css" rel="stylesheet" type="text/css">
   </head>
   <body>
      <h3>Edit Product</h3>
      <p style="color: red;">${errString}</p>

      <c:if test="${book != null}">
         <div class = "editForm">
 				<form method = "POST" action = "${pageContext.request.contextPath}/homeBook/editBook">
 				<label from = "code">${book.id_book}</label>
 				<input type="hidden" name="code" value="${book.id_book}" />
 				<label from = "title book">Name</label><br>
		 		<input type = "text" name = "title_book" value = "${book.name_book}">
		 		<label from = "first_name_author">First name author</label><br>
		 		<input type = "text" name = "first_name_author" value = "${book.first_name }">
		 		<label from = "last_name_author">Last name author</label><br>
		 		<input type = "text" name = "last_name_author" value = "${book.last_name }">
		 		<label from = "release_year">release_year</label><br>
		 		<input type = "text" name = "release_year" value = "${book.year_released }">
		 		<label from = "stock_quantity">stock_quantity</label><br>
		 		<input type = "text" name = "stock_quantity" value = "${book.stock_quantity }">
		 		<label from = "pages">pages</label>
		 		<input type = "text" name = "pages" value = "${book.pages }">
		 		<input type = "submit">
 			</form>
 		</div>
      </c:if>

   </body>
</html>