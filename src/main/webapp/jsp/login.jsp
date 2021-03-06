<%--
  Created by IntelliJ IDEA.
  domain.User: uluan
  Date: 18.10.2021
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Аутентификация</title>
    <style><%@include file='../css/style.css' %></style>
</head>
<body>
<div class="form_center">
    <form class="form" action="login.jhtml" method="post">
        <h1 class="title">Аутентификация пользователя</h1>
        <div class="form_group">
            <input name="login" class="form_input" placeholder="Логин" value="${login}"/>
        </div>
        <div class="form_group">
            <input name="password" type="password" class="form_input" placeholder="Пароль" value="${password}"/>
        </div>
        <p class="error"><c:out value="${errorEntrance}"/></p>
        <button type="submit" class="form_button">Войти</button>
    </form>
</div>
<t:footer/>
</body>
</html>
