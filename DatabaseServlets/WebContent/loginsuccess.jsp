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
 <section class="user_main">
 	<h1 class="user_main-title">Welcome  <span class="user_main-title--username">${user.username}</span></h1> 
   	<div class="search-container">
		    <form action="<%=request.getContextPath()%>/searchQuestion" method="post">
		      	<input type="text" id="search_question" name="search_question" placeholder="Search for question title..." name="search">
		      	<button type="submit">Submit</button>
		    </form>
	</div>
   	<div class="user_main-buttons">
		<a href="../DatabaseServlets/ShowQuestions">Show All Questions</a>
		<a href="../DatabaseServlets/userQuestions">Yours Questions</a>
		<a href="../DatabaseServlets/userAnswers">Yours Answers</a>
		<a href="../DatabaseServlets/addQuestion.jsp">Add Question</a>
  	</div>
  	<div class="user_main-logout">
   		<a href="../DatabaseServlets/logout">Logout</a>
  	</div>
 </section>
</body>
</html>