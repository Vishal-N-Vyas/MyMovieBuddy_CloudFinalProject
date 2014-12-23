<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page session="true"%>
<html>
<body>
	<head>
      <style type="text/css">
         .tile {display: inline-block; border: 1px solid grey; background: silver; padding: 4px; text-align: center; font-size: 15px;width:250px; }	
		 .error {
			color: #ff0000;
		 }
		 .errorblock{
			color: #000;
			background-color: #ffEEEE;
			border: 3px solid #ff0000;
			padding:8px;
			margin:16px;
		}
	  </style>
    </head>
	<div id="start-box">
		<form:form action="/spring-authentication/buildProfile" method='GET'>
			<table>
				<tr>
					<td colspan='2'>
					<input name="submit" type="submit" value="Edit Profile" /></td>
				</tr>
			</table>
		</form:form>
		<form:form action="/spring-authentication/logoutuser" method='GET'>
			<table>
				<tr>
					<td colspan='2'>
					<input name="submit" type="submit" value="Log out" />
					</td>
				</tr>
			</table>
		</form:form>
		<form:form action="/spring-authentication/fileupload" method='GET'>
			<img alt="defaultpic" src="${imgURL}"/>
			<table>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Upload Profile Picture" /></td>
				</tr>
			</table>
		</form:form>
		<form:form action="/spring-authentication/showmovies" method='GET' command="zipcode">
			<table>
				<tr>
					<td>Please enter your zipcode: </td>
					<td><input type='text' name='zipcode'></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="Show Movies" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>