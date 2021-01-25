<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log in - StackOverClone</title>
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css">
<style>

	.signup-ref {
		display: flex;
		align-items: center;
		flex-direction: row;
		justify-content: flex-start;
		margin-left: 10px;
	}
	

	.signup-ref a{
	  	color: rgb(0,30,255);
	  	height: 20px;
	  	width: 70px;
	  	font-weight: bold;
	  	margin: 0;
	  	background-color: white;
  	}
  	
  	.signup-ref a:link, .signup-ref a:visited {
  	  height: 20px;
	  width: 70px;
	  background-color: white;
	  color: rgb(0,30,255);
	  padding: 0;
	  font-weight: bold;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  border-radius: 0;
	}
	
	.signup-ref a:hover, .signup-ref a:active {
	  color: darkblue;
	}
</style>
</head>
<body>
 <section class="signup">
        <div class="wrapper">
            <div class="form__box">
                <form action="<%=request.getContextPath()%>/login" method="post">
                    <h1>Log in to StackOverClone!</h1>
                    <div class="form-box">
                        <label for="text">Username:</label>
                        <input type="text" id="text" name="username" placeholder="Enter your e-mail">
                    </div>
                    <div class="form-box">
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" placeholder="Enter your password">
                    </div>
                    <div class='signup-ref'>
                    	<p>Don't have an account?</p>
                    	<a class='signup' href="../DatabaseServlets/register.jsp">Sign up!</a>
                    </div>
                    <div class="control-buttons">
                        <button class="send btn-special-animation">Log in</button>
                    </div>
                </form>
            </div>
        </div>
    </section>
</body>
</html>