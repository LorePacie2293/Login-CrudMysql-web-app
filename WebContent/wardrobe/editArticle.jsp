<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product</title>
<link href="${pageContext.request.contextPath}/css/editForm.css"
	rel="stylesheet" type="text/css">

</head>
<body>
	<h3>Edit Product</h3>
	<p style="color: red;">${errString}</p>

	<c:if test="${article != null}">
		<div class="editForm">
			<form method="POST"
				action="${pageContext.request.contextPath}/home/editArticle">
				<input type="hidden" name="code" value="${article.idArticle}" />
				<table border="0">
					<tr>
						<td>Article id</td>
						<td style="color: red;">${article.idArticle}</td>
					</tr>
					<tr>
						<td>Name</td>
						<td><input type="text" name="name"
							value="${article.nameArticle}" /></td>
					</tr>
					<tr>
						<td>Color</td>
						<td><input type="text" name="color" value="${article.color}" /></td>
					</tr>
					<tr>
						<td>Size</td>
						<td><input type="text" name="size" value="${article.size}" /></td>
					</tr>
					<tr>
						<td>Last worn</td>
						<td><input type="text" name="last_worn"
							value="${article.last_worn}" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Submit" /> <a
							href="${pageContext.request.contextPath}/home">Cancel</a></td>
					</tr>
				</table>
			</form>
		</div>

	</c:if>

</body>
</html>