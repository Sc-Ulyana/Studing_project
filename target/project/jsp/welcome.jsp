<%--
  Created by IntelliJ IDEA.
  classes.User: uluan
  Date: 18.10.2021
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<div style="text-align: center">
    <div id="header" style="display: inline-block">Webapp</div>
    <div style="float: right"><a href="exit">
        <button class="form_button">Выход</button>
    </a></div>
    <div style="float: left" class="dropdown">
        <button class="mainmenubtn">Меню</button>
        <div class="dropdown-child">
            <c:if test="${role=='ROLE_ADMIN'}">
                <a href="users.jhtml">Список пользователей</a>
            </c:if>
            <a href="loginedit.jhtml">Сменить пароль</a>
        </div>
    </div>
</div>
<div align="right">
    <p><c:out value="${message}"/></p>
    <h1 class="title"><b>Добро пожаловать,<c:out value="${name} ${surname}"/></b></h1>
</div>
<div id="footer">&copy; Ulyana Duhovich</div>
</body>
</html>
