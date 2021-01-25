<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Question Failure!</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	<section class="question_added_fail">
		<h1 class="question_added-title"><span class="question_added-failure">Failure!</span></h1>
		<h1 class="question_added-title">Question has not been updated!</h1>
		<div class="question_added-failure-buttons">
			<a class="back" onclick="history.back()">Go Back</a>
		</div>
	</section>
</body>
</html>