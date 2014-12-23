<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
	<head>
      <style type="text/css">
         .tile {display: inline-block; border: 1px solid grey; background: silver; padding: 4px; text-align: center; font-size: 15px;width:250px; }	
      </style>
   </head>
	<div id="login-box">

		<h3>Login with Username and Password</h3>

		<form:form action="/spring-authentication/login" method='POST' commandName="user">

			<table>
				<tr>
					<td>Username:</td>
					<td><input type='text' name='userName'></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Sign in" /></td>
				</tr>
			</table>
		</form:form>
		<form:form action="/spring-authentication/customer" method='GET'>

			<table>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Register" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>