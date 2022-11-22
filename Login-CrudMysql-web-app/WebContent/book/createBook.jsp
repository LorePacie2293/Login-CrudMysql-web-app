
<html>
<head>
<link href="${pageContext.request.contextPath}/css/insertForm.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Product List</title>

</head>
</html>


<div class = "insertForm">
 	<h2>Inserisci libro</h2>
 	<form method = "POST" action = "${pageContext.request.contextPath}/homeBook/createBook">
 		<br/><label from = "title book">Name</label>
 		<input type = "text" name = "title_book">
 		<br/><label from = "first_name_author">First name author</label>
 		<input type = "text" name = "first_name_author">
 		<br/><label from = "last_name_author">Last name author</label>
 		<input type = "text" name = "last_name_author">
 		<br/><label from = "release_year">release_year</label>
 		<input type = "text" name = "release_year">
 		<br/><label from = "stock_quantity">stock_quantity</label>
 		<input type = "text" name = "stock_quantity">
 		<br/><label from = "pages">pages</label>
 		<input type = "text" name = "pages">
 		<input type = "submit">
 	</form>
 </div>
