<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>	
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/select2.css">
    <link rel="stylesheet" href="resources/css/admin.css">
    <script src="resources/js/lib/modernizr.js"></script>
    <script src="resources/js/lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="resources/js/lib/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/lib/select2.js"></script>
</head>
<body class="backstretch">
  		<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="alert alert-danger" role="alert">${msg}</div>
		</c:if>       
		<div class="block-absolute-center login-form" id="loginBox">
			<div class="text-center m-b-10">
        	<h1 class="text-white font-w-100">MyMovieBuddy.com</h1><br/><br/>
			</div>
			<form:form action="/spring-authentication/login" method='POST' commandName="user">
			  <div class="form-group">
			    <input type="text" name="userName" class="form-control input-glass"
			    	id="userName" placeholder="Username">
			  </div>
			  <div class="form-group">
			    <input type="password" name="password" class="form-control input-glass"
			    	id="password" placeholder="Password">
			  </div>
			  <button type="submit" class="btn btn-primary btn-block btn-lg">Sign in</button>
			</form:form>
			<form:form action="/spring-authentication/customer" method='GET'>	
				<button type="submit" name="submit" value="Register" class="btn btn-primary btn-block btn-lg">New User? Register!</button>
			</form:form>									
		</div>		
    <script src="https://code.jquery.com/jquery.js"></script>
    <script src="resources/js/lib/backstretch.js"></script>
    <script>
      $(".backstretch").backstretch([
        "resources/backgrounds/wg_blurred_backgrounds_8.jpg"
      ]);
    </script>
  </body>
</html>