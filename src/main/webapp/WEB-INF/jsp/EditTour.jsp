<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<spring:form action="/store/edit" method="POST" modelAttribute="tour">
    <input type="hidden" name="id" value="${tour.id}">
    <table>
        <tr>
            <td align="right" >Id: </td>
            <td align="right" >${tour.id}</td>
        </tr>
        <tr>
            <td align="right" >Название: </td>
            <td>
                <spring:input path="name" type="text" maxlength="50"/>
            </td>
        </tr>
        <tr>
            <td align="right" >Описание: </td>
            <td>
                <spring:input path="description" type="text" maxlength="50"/>
            </td>
        </tr>
        <tr>
            <td><spring:button>Изменить</spring:button></td>
        </tr>
    </table>
</spring:form>
</body>
</html>
