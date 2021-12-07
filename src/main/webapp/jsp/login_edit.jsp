<%--
  Created by IntelliJ IDEA.
  domain.User: uluan
  Date: 19.10.2021
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Смена пароля</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<t:header welcome="true" changePassword="false" usersPage="true"/>
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
<t:footer/>
</body>
</html>

