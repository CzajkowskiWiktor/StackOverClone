<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete a User</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div class="delete_user">
	  <h1 class="delete_user-title">Delete a User From StackOverClone</h1>
	  <form class="delete_user__form" action="<%=request.getContextPath()%>/deleteUser" method="post">
	  		<div class="delete_user__form-box">
				<label for="username">User's username:</label><br>
			  	<input type="text" id="username" name="username" placeholder="Enter a user's username"><br>
			</div>
			<br>
			<div class="delete_user__form-box--buttons">
		  		<button class="delete" type="submit">Delete</button>
	    		<button class="cancel" type="button" onclick="history.back()">Cancel</button>
		  	</div>
	  </form>
 	</div>
</body>
</html>