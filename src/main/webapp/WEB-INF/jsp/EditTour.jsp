<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/store/edit" method="POST">
    <input type="hidden" name="id" value="${id}">
    <table>
        <tr>
            <td align="right" >Id: </td>
            <td align="right" >${id}</td>
        </tr>
        <tr>
            <td align="right" >Название: </td>
            <td>
                <input type="text" name="name" value="${name}">
            </td>
        </tr>
        <tr>
            <td align="right" >Описание: </td>
            <td>
                <input type="text" name="description" value="${description}">
            </td>
        </tr>
        <tr>
            <td><input type="submit" align="center" value="Изменить"/></td>
        </tr>
    </table>
</form>
</body>
</html>
