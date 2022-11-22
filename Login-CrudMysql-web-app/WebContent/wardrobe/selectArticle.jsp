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
	<form method="POST"
		action="${pageContext.request.contextPath}/home/selectArticle">
		<label from="nameArticle">Name</label> <input type="text"
			name="nameArticle"><br /> <label from="color">color</label> <input
			type="text" name="color"><br /> <label from="size">size</label>
		<input type="text" name="size"><br /> <label from="last_worn">last
			worn</label> <input type="text" name="last_worn"><br /> <input
			type="submit">
	</form>
</div>