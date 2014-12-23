<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
    <title>MyMovieBuddy.com</title>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/admin.css">
	<script src="resources/js/lib/modernizr.js"></script>
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/select2.css">
    <link rel="stylesheet" href="resources/css/admin.css">
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
	<div class="container">
      <div class="signup">
        <div class="row">
          <div class="text-center m-b-10">
            <h1 class="text-white p-b-0">MyMovieBuddy.com</h1><br/><br/>
          </div>        
          <div class="block-absolute-center col-sm-4">
            <div class="m-t-30">
              <div class="p-t-30">
                <form:form method="POST" action="/spring-authentication/customer/register" commandName="customer" role="form" id="signupForm">
				<form:errors path="*" cssClass="errorblock" element="div"/>
                  <div class="form-group row">
                    <div class="col-xs-6">
                      <input type="text" class="form-control input-glass"
                        id="firstName" name="firstName" placeholder="First Name">
                    </div>
                    <div class="col-xs-6">
                      <input type="text" class="form-control input-glass"
                        id="lastName" name="lastName" placeholder="Last Name">
                    </div>
                  </div>
                  <div class="form-group">
                    <input type="email" class="form-control input-glass"
                      id="emailID" name="emailID" placeholder="Email">
                  </div>
                  <div class="form-group">
                    <input type="text" class="form-control input-glass"
                      id="userName" name="userName" placeholder="Username">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control input-glass"
                      id="password" name="password" placeholder="Password">
                  </div>
                  <div class="form-group">
                    <input type="password" class="form-control input-glass"
                      id="confirmPassword" name="confirmPassword" placeholder="Repeat Password">             
                  </div>
                  <div class="form-group">                     
                      <div class="col-sm-3">
						 <form:radiobutton path="sex" class="input-glass" value="M"/>Male 
					  </div>
					  <div class="col-sm-3">
						 <form:radiobutton path="sex" class="input-glass" value="F"/>Female 
					  </div>					  
                  </div>
                  <br/><br/>
                  <button type="submit" class="btn btn-primary btn-block btn-lg" value="login">Register</button>
                </form:form>
              </div>             
            </div>
          </div>        
        </div>
      </div>
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