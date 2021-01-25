<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Answer updated Successfully</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<section class="question_added">
		<h1 class="question_added-title">Answer has been successfully updated!</h1>
		<div class="user_deleted-buttons">
			<a class="button" href="../DatabaseServlets/ShowQuestions">Show Questions</a>
			<a class="back" onclick="history.back()">Go Back</a>
		</div>
	</section>
</body>
</html>