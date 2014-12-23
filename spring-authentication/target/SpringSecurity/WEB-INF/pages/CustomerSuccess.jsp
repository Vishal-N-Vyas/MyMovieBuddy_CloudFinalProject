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
        	<h1 class="text-white font-w-100">Congrats ${customer.userName}, you have successfully been registered. Please login again. </h1><br/>
			</div>
			<form:form method="GET" action="/spring-authentication/">
			  <div class="form-group">
			    <button type="submit" value="Login" class="btn btn-primary btn-block btn-lg">Redirect to Login</button>
			  </div>			  
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