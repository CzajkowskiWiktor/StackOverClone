<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add new User</title>
</head>
<body>
	<form action="/../DatabaseServlets/AddUser" method="post">
		<label for="id">User Id:</label><br>
	  <input type="number" id="id" name="id" placeholder="Enter an ID"><br>
	  <label for="name">First name:</label><br>
	  <input type="text" id="name" name="name" placeholder="Enter a name"><br>
	  <label for="last_name">Last name:</label><br>
	  <input type="text" id="last_name" name="last_name" placeholder="Enter a last name"><br>
	  <button type="submit">OK</button>
	  <a href="/../DatabaseServlets/index.jsp">
    	<button type="button">Cancel</button>
	  </a>
	</form>
</body>
</html>