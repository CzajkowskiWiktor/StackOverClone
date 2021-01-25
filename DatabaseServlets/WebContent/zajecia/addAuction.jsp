<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new Auction</title>
</head>
<body>
	<form action="/../DatabaseServlets/AddAuction" method="post">
		<label for="ida">Auction Id:</label><br>
	  	<input type="number" id="ida" name="ida" placeholder="Enter an IDa"><br>
	  	<label for="name">Auction name:</label><br>
	  	<input type="text" id="name" name="name" placeholder="Enter an auction name"><br>
	  	<label for="price">Price:</label><br>
	  	<input type="number" id="price" name="price" placeholder="Enter an auction price"><br>
	  	<label for="idu">User Id:</label><br>
	  	<input type="number" id="idu" name="idu" placeholder="Enter an IDu"><br>
	  	<button type="submit">OK</button>
	  	<a href="/../DatabaseServlets/index.jsp">
    		<button type="button">Cancel</button>
	  	</a>
	</form>
</body>
</html>