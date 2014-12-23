<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login Page</title>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/select2.css">
    <link rel="stylesheet" href="resources/css/admin.css">
    <script src="resources/js/lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="resources/js/lib/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/lib/select2.js"></script>
		<link rel="stylesheet" href="resources/css/menu_styles.css">
</head>

 <body class="backstretch">
    <div id='cssmenu'> 
<ul>

   <li ><a href='./connectionDashBoard.jsp'><span>Movie Buddy DashBoard</span></a></li>
  <li  ><a href='./searchByZip.jsp'>Search Movies and Invite</a></li>
   <li ><a href='/spring-authentication/buildProfile'><span>Profile</span></a></li>
      <li  class='active'><a href='/spring-authentication/fileupload'><span>Upload Picture</span></a></li>
   <li  ><a href='./aboutUs.html'><span>About Us</span></a></li>
   <li><a href='./techStack.html'><span>Technology Stack</span></a></li>
  <li><a href='/spring-authentication/logoutuser'><span>Log out</span></a></li>
</ul>
</div><br><br>
 
	<div class="block-absolute-center login-form" id="loginBox">		
		<form:form method="POST" enctype="multipart/form-data" action="/spring-authentication/fileupload/upload">
		  <div class="text-center m-b-10">
	      	<h1 class="text-white font-w-100  input-glass">File to upload:</h1>
	      	<input class="btn btn-primary btn-block btn-lg" type="file" name="file"><br/>	      
			</div>
		  <div class="form-group">
		    <button type="submit" value="Upload" class="btn btn-primary btn-block btn-lg">Upload File</button>
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