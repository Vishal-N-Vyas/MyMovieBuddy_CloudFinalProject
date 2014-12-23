<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<form:form method="GET" action="/spring-authentication/">
<table>
<tr>
<td>
Congrats
<td>
<td>${customer.userName} !</td>
<td>
 You have been successfully registered. Please log in again to use the app
</td>
</tr>
<tr>
<td colspan="3"><input type="submit" value="Login" /></td>
</tr>
</table>
</form:form>
</body>
</html>