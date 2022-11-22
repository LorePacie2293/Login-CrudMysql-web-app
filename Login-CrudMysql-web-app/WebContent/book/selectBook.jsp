<html>
<head>
<link href="${pageContext.request.contextPath}/css/selectForm.css"
	rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Product List</title>

</head>
</html>


 <div class = "selectForm">
 	<h2>Ricerca articolo</h2>
 	<form method = "POST" action = "${pageContext.request.contextPath}/homeBook/selectBook">
 		<label from = "nameBook">nameBook</label>
 		<input type = "text" name = "nameBook"><br/>
 		<label from = "authorName">authorName</label>
 		<input type = "text" name = "authorName"><br/>
 		<label from = "authorLastName">authorLastName</label>
 		<input type = "text" name = "authorLastName"><br/>
 		<label from = "release_year">release_year</label>
 		<input type = "text" name = "release_year"><br/>
 		<label from = "stock_quantity">stock_quantity</label>
 		<input type = "text" name = "stock_quantity"><br/>
 		<label from = "pages">pages</label>
 		<input type = "text" name = "pages"><br/>
 		<input type = "submit">
 	</form>
 </div>