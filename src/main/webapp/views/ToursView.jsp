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
        <%
          if(request.getSession().getAttribute("userrole") != null) {
            String role = (String) request.getSession().getAttribute("userrole");
            if (role.equals("admin")) {%>
        <a href="${pageContext.servletContext.contextPath}/store/edit?id=${tour.getId()}">Редактировать</a>
        <a href="${pageContext.servletContext.contextPath}/store/delete?id=${tour.getId()}">Удалить</a>
        <%
            }else{
        %>
        <a href="${pageContext.servletContext.contextPath}/store/order?id=${tour.getId()}">Купить</a>
        <%
            }
          }
        %>
      </td>
    </tr>
  </c:forEach>
</table>
  <%
    if(request.getSession().getAttribute("userrole") != null) {
      String role = (String) request.getSession().getAttribute("userrole");
      if (role.equals("admin")) {%>
  <br><a href="${pageContext.servletContext.contextPath}/store/create">Добавить новый тур</a>
  <br><a href="${pageContext.servletContext.contextPath}/stat">Статистика продаж</a>
  <%
      }
    }
  %>
 <%
   String userName = (String) request.getSession().getAttribute("username");
   if(userName == null){%>
 <p><a href="register">Регистрация</a></p>
 <p><a href="login">Вход</a></p><%
   }else {%>
 <p>Вы вошли как <%=userName%></p>
 <p><a href="${pageContext.servletContext.contextPath}/logout">Выйти</a></p><%
   }
 %>
</body>
</html>
