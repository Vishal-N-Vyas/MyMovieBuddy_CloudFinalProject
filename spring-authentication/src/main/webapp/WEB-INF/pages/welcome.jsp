<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="true"%>
<!DOCTYPE html>
<html>
  <head>
  <script src="/spring-authentication/resources/js/mainCode.js"></script>
<script type="text/javascript">
		setSiteWideValue('userName',"${userName}");
		setSiteWideValue('userId',"${userId}");
		setSiteWideValue('userId1',"${userId}");
		 
</script>
    <title>Login Page</title>
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="resources/css/admin.css">
   <script src="resources/js/lib/modernizr.js"></script>
   <link rel="stylesheet" href="resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/css/select2.css">
    <link rel="stylesheet" href="resources/css/admin.css">
    <script src="resources/js/lib/modernizr.js"></script>
    <script src="resources/js/lib/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="resources/js/lib/bootstrap.min.js"></script>
	<script type="text/javascript" src="resources/js/lib/select2.js"></script>
			<link rel="stylesheet" href="resources/css/menu_styles.css">
  </head>
  <body class="backstretch">
   <div id='cssmenu'> 
<ul>

   <li><a href='./connectionDashBoard.jsp'><span>Movie Buddy DashBoard</span></a></li>
  <li   class='active'><a href='./searchByZip.jsp'>
 
   Search Movies and Invite
 
</span></a></li>
   <li><a href='/spring-authentication/buildProfile'><span>Profile</span></a></li>
   <li><a href='/spring-authentication/fileupload'><span>Upload Picture</span></a></li>
   
   <li  ><a href='./aboutUs.html'><span>About Us</span></a></li>
   <li><a href='./techStack.html'><span>Technology Stack</span></a></li>
  <li><a href='/spring-authentication/logoutuser'><span>Log out</span></a></li>
</ul>
</div>

  
  
  <div class="text-center m-b-10">
     <h1 class="text-white p-b-0  input-glass">MyMovieBuddy.com</h1>
   </div>
  <div class="block-absolute-center login-form">
		<form:form action="/spring-authentication/searchByzip.jsp" method='GET' command="zipcode">
			<table>
				<tr>
					<td><input class="form-control input-glass" placeholder="ZIPCODE" type='text' name='zipcode'></td>
					<td colspan='2'><input  class="btn btn-primary btn-block btn-lg" name="submit" type="submit"
						value="Show Movies" /></td>
				</tr>
			</table>
		</form:form>
		<br/>
		<div >
		    <form:form action="/spring-authentication/fileupload" method='GET'>
			   <img style="width:100px;height:100px" alt="Profile Picture" src="${imgURL}"/>
			   <br/>
			   <table>
			    <tr>
			     <td colspan='2'><input class="btn btn-primary btn-block btn-lg" name="submit" type="submit"
			      value="Upload Profile Picture" /></td>
			    </tr>
			   </table>
			  </form:form>
			  <br/>
		    <div>
			<form:form action="/spring-authentication/buildProfile" method='GET'>
				<table>
					<tr>
						<td colspan='2'>
						<input class="btn btn-primary btn-block btn-lg" name="submit" type="submit" value="Edit Profile" /></td>
					</tr>
				</table>
			</form:form>
			</div>
			<br/>
			
			<div >
			<form:form action="/spring-authentication/logoutuser" method='GET'>
				<table>
					<tr>
						<td colspan='2'>
						<input  class="btn btn-primary btn-block btn-lg" name="submit" type="submit" value="Log out" />
						</td>
					</tr>
				</table>
			</form:form>
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