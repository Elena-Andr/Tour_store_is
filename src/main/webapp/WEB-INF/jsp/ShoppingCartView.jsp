<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Корзина</title>
</head>
<body>
<h1>Мои покупки</h1>
<c:forEach items="${myOrders}" var="order" varStatus="status">
<br>${order}
</c:forEach>
<p><a href="/store">На главную</a></p>
</body>
</html>
