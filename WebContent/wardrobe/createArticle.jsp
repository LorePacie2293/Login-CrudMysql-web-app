
<html>
<head>
<link href="${pageContext.request.contextPath}/css/insertForm.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>Product List</title>

</head>
</html>


<div class="insertForm">
	<div class = "title">Inserisci articolo</div>
	<form method="POST"
		action="${pageContext.request.contextPath}/home/createArticle">
		<label from="nameArticle">Name</label> <input type="text"
			name="nameArticle"><br /> <label from="color">color</label>
		<input type="text" name="color"><br /> <label from="size">size</label>
		<input type="text" name="size"><br /> <label from="last_worn">last
			worn</label> <input type="text" name="last_worn"><br /> <input
			type="submit">
	</form>
</div>



