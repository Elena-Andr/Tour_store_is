<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<h2>Login with Username and Password</h2>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
    <div class="msg">${msg}</div>
</c:if>

<form name='loginForm' action="<c:url value='/login' />" method='POST'>
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='NAME'></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='PASSWORD' /></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="submit" /></td>
        </tr>
    </table>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
</form>
    <p><a href="store">На главную</a></p>
</body>
</html>
