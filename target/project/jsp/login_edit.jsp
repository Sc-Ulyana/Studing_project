<%--
  Created by IntelliJ IDEA.
  classes.User: uluan
  Date: 19.10.2021
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Смена пароля</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<div style="text-align: center">
    <div id="header" style="display: inline-block;">Webapp</div>
    <div style="float: right"><a href="exit">
        <button class="form_button">Выход</button>
    </a></div>
    <div style="float: left" class="dropdown">
        <button class="mainmenubtn">Меню</button>
        <div class="dropdown-child">
            <a href="welcome.jhtml">Главная</a>
            <c:if test="${role=='ROLE_ADMIN'}">
                <a href="users.jhtml">Список пользователей</a>
            </c:if>
        </div>
    </div>
</div>
<div class="form_center">
    <form class="form" action="loginedit.jhtml" method="post">
        <h1 class="title">Смена пароля</h1>
        <div class="form_group">

            <input name="oldPassword" type="password" class="form_input" placeholder="Старый пароль" value="${oldPassword}"/>
        </div>
        <div class="form_group">
            <input name="newPassword" type="password" class="form_input" placeholder="Новый пароль" value="${newPassword}"/>
        </div>

        <div class="form_group">
            <input name="newPasswordRepeat" type="password" class="form_input" placeholder="Подтвердите новый пароль" value="${newPasswordRepeat}"/>
        </div>
        <p style="color: red"><c:out value="${message}"/></p>
        <button class="form_button" type="submit">Сменить пароль</button>
    </form>
</div>
<div id="footer">&copy; Ulyana Duhovich</div>
</body>
</html>

