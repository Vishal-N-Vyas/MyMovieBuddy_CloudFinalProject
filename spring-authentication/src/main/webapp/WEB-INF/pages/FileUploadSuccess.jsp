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
	</head>
 <body class="backstretch">

<div class="block-absolute-center login-form" id="loginBox">
	<div class="text-center m-b-10">
      	<h1 class="text-white font-w-100">FileName : "<strong> ${fileName} </strong>" - Upload Successful.</h1><br/>
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