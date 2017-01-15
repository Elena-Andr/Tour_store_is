<%@ page language="java" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Интернет-магазин туров</title>
  </head>
  <body>
  <table border="1">
    <tr>
      <td>Название</td>
      <td>Описание</td>
      <td>Действие</td>
    </tr>
    <c:forEach items="${tours}" var="tour" varStatus="status">
      <tr valign="top">
        <td>${tour.getName()}</td>
        <td>${tour.getDescription()}</td>
      <td>
        <c:if test="${user_role == 'ROLE_ADMIN'}">
        <a href="${pageContext.servletContext.contextPath}/store/edit?id=${tour.getId()}">Редактировать</a>
        <a href="${pageContext.servletContext.contextPath}/store/delete?id=${tour.getId()}">Удалить</a>
        </c:if>
          <c:if test="${user_role == 'ROLE_USER'}">
              <a href="${pageContext.servletContext.contextPath}/store/order?id=${tour.getId()}">Купить</a>
          </c:if>
      </td>
    </tr>
  </c:forEach>
</table>
  <c:if test="${user_role == 'ROLE_ADMIN'}">
  <br><a href="${pageContext.servletContext.contextPath}/store/create">Добавить новый тур</a>
  <br><a href="${pageContext.servletContext.contextPath}/stat">Статистика продаж</a>
</c:if>
 <p>Вы вошли как ${username} </p>
  <c:if test="${user_role == 'guest'}">
    <p><a href="register">Регистрация</a></p>
    <p><a href="login">Вход</a></p>
  </c:if>
  <c:if test="${user_role != 'guest'}">
    <p><a href="${pageContext.servletContext.contextPath}/logout">Выйти</a></p>
  </c:if>
</body>
</html>
