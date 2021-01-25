<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>StackOverClone - Main Page</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
 <section class="admin_main">
  <h1 class="admin_main-title">You have logged successfully as <span class="admin_main-title--username">ADMIN</span></h1>
  <br>
  <div class="search-container">
		    <form action="<%=request.getContextPath()%>/searchQuestion" method="post">
		      	<input type="text" id="search_question" name="search_question" placeholder="Search for question title..." name="search">
		      	<button type="submit">Submit</button>
		    </form>
  </div>
  <div class="admin_main-buttons">
  	<a class="button" href="../DatabaseServlets/ShowAllUsers">Show All Users</a>
	<a class="button" href="../DatabaseServlets/ShowQuestions">Show All Questions</a>
	<a class="button" href="../DatabaseServlets/addQuestion.jsp">Add Question</a>
	<a class="button" href="../DatabaseServlets/deleteQuestion.jsp">Delete Question</a>
	<a class="button" href="../DatabaseServlets/deleteUser.jsp">Delete User</a>
	<a class="button" href="../DatabaseServlets/CreateTag">Add New Tag</a>
  </div>
  <div class="admin_main-logout">
   	<a class="button" href="../DatabaseServlets/logout">Logout</a>
  </div>
 </section>
</body>
</html>