<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Добавление нового тура</title>
</head>
<body>
<spring:form action="/store/create" method="POST" modelAttribute="tourEntity">
    <table>
        <tr>
            <td align="right" >Название: </td>
            <td>
                <spring:input path="name" type="text" maxlength="50"/>
            <td><span class="error"><form:errors path="name" /></span></td>
            </td>
        </tr>
        <tr>
            <td align="right" >Описание: </td>
            <td>
                <spring:input path="description" type="text" maxlength="50"/>
            <td><span class="error"><form:errors path="description" /></span></td>
            </td>
        </tr>
        <tr>
            <td><spring:button>Создать</spring:button></td>
        </tr>
    </table>
</spring:form>
</body>
</html>
