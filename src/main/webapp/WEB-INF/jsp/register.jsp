<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<title>Регистрация</title>
</head>
<body>

<form:form method="POST" action="/register" modelAttribute="user" >
    <table>
        <tr>
            <td>Username:</td>
            <td><form:input path="name" /></td>
            <td><span class="error"><form:errors path="name" /></span></td>
        </tr>

        <tr>
            <td>Password:</td>
            <td><form:password path="password" /></td>
            <td><span class="error"><form:errors path="password" /></span></td>
        </tr>
        <tr>
            <td colspan="3"><input type="submit" value="Submit" /></td>
        </tr>
    </table>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form:form>
</body>
</html>
