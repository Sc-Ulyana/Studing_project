<%--
  Created by IntelliJ IDEA.
  User: uluan
  Date: 12.11.2021
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование пользователя</title>
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
            <c:if test="${login=='admin'}">
                <a href="users.jhtml">Список пользователей</a>
            </c:if>
        </div>
    </div>
</div>
<div class="form_center">
    <form class="form" action="useradd.jhtml" method="post">
        <h1 class="title">Добавление пользователя</h1>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <input name="name" type="text" class="form_input" placeholder="Имя"/>
        </div>
        <div class="form_group">
            <input name="surname" type="text" class="form_input" placeholder="Фамилия"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <c:out value="${checkLogin}"/>
            <input name="login" type="text" class="form_input" placeholder="Логин"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <c:out value="${checkPassword}"/>
            <input name="password" type="password" class="form_input" placeholder="Пароль"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <c:out value="${checkEmail}"/>
            <input name="email" type="text" class="form_input" placeholder="Email"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <c:out value="${checkDate}"/>
            <input name="dateOfBirth" type="date" class="form_input" placeholder="Дата рождения" min="1940-01-01" max="2021-01-01" value="${editUser.getDateOfBirth()}"/>
        </div>
        <div class="form_group">
            <span class="custom-dropdown big">
            <select name="role">
                <option>ROLE_ADMIN</option>
                <option>ROLE_USER</option>>
            </select>
            </span>
        </div>
        <button class="form_button" type="submit">Сохранить</button>
    </form>
</div>
<div id="footer">&copy; Ulyana Duhovich</div>
</body>
</html>


