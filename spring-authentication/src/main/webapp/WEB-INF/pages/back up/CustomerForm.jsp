<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<style>
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

<body>
<h2>Spring's form tags example</h2>

<form:form method="POST" action="/spring-authentication/customer/register" commandName="customer">

<form:errors path="*" cssClass="errorblock" element="div"/>

<table>
<tr>
<td>Username : </td>
<td><form:input path="userName" /></td>
<td><form:errors path="userName" cssClass="error" /></td>
</tr>
<tr>
<td>First Name : </td>
<td><form:input path="firstName" /></td>
<td><form:errors path="firstName" cssClass="error" /></td>
</tr>
<tr>
<td>Last Name : </td>
<td><form:input path="lastName" /></td>
<td><form:errors path="lastName" cssClass="error" /></td>
</tr>
<tr>
<td>Email : </td>
<td><form:input path="emailID" /></td>
<td><form:errors path="emailID" cssClass="error" /></td>
</tr>
<tr>
<td>Password : </td>
<td><form:password path="password" /></td>
<td><form:errors path="password" cssClass="error" /></td>
</tr>
<tr>
<td>Confirm Password : </td>
<td><form:password path="confirmPassword" /></td>
<td><form:errors path="confirmPassword" cssClass="error" /></td>
</tr>
<tr>
<td>Sex : </td>
<td>
<form:radiobutton path="sex" value="M"/>Male 
<form:radiobutton path="sex" value="F"/>Female 
</td>
<td><form:errors path="sex" cssClass="error" /></td>
</tr>
<tr>
<td colspan="3">
<input type="submit" value="login" />
</td>
</tr>
</table>
</form:form>
</body>
</html>