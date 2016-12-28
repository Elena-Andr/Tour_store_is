<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Статистика</title>
</head>
<body>
<p>Статистика покупок</p>
<c:forEach items="${orders}" var="order" varStatus="status">
    <p>${order.toString()}</p>
</c:forEach>
<p><a href="/store">На главную</a></p>
</body>
</html>
