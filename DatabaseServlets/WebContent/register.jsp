<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up - StackOverClone</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
 	<section class="signup">
        <div class="wrapper">
            <div class="form__box">
                <form action="<%=request.getContextPath()%>/register" method="post">
                    <h1>Sign up to StackOverClone!</h1>
                    <div class="form-box">
                        <label for="text">Username:</label>
                        <input type="text" id="text" name="username" placeholder="Enter your e-mail">
                    </div>
                    <div class="form-box">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" placeholder="Enter your password">
                    </div>

                    <div class="control-buttons">
                        <button class="send btn-special-animation">Sign up</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>