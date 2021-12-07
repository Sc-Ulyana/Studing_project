<%--
  Created by IntelliJ IDEA.
  User: uluan
  Date: 12.11.2021
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Редактирование пользователя</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<t:header welcome="true" changePassword="false" usersPage="true"/>
<div class="form_center">
    <form class="form" style="margin-top:200px" action="useredit.jhtml" method="post">
        <h1 class="form_title">Редактирование пользователя <c:out value="${editUser.getLogin()}"/></h1>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <input name="name" type="text" class="form_input" placeholder="Имя" value="${editUser.getName()}"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <input name="login" type="text" class="form_input" placeholder="Логин" value="${editUser.getLogin()}"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <c:out value="${checkPassword}"/>
            <input name="password" type="password" class="form_input" placeholder="Пароль"
                   value="${editUser.getPassword()}"/>
        </div>
        <div class="form_group">
            <input name="age" type="number" class="form_input" placeholder="Возраст" value="${editUser.getAge()}"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <c:out value="${checkDate}"/>
            <input name="dateOfBirth" type="date" class="form_input" placeholder="Дата рождения" min="1940-01-01"
                   max="2021-01-01" value="${editUser.getDateOfBirth()}"/>
        </div>
        <div class="form_group">
            <input name="salary" type="text" class="form_input" placeholder="Зарплата" value="${editUser.getSalary()}"/>
        </div>

        <div class="form_group">
            <p>
                <input type="checkbox" class="custom-checkbox" id="1" onselect="${roleId == 1}" name="roleChoice"
                       value="ROLE_ADMIN"
                       <c:if test="${editUser.hasRole('ROLE_ADMIN')}">checked="checked"</c:if>>
                <label for="1">Админ</label>
            </p>
            <p>
                <input type="checkbox" class="custom-checkbox" id="2" name="roleChoice" value="ROLE_USER"
                       <c:if test="${editUser.hasRole('ROLE_USER')}">checked="checked"</c:if>>
                <label for="2">Пользователь</label>
            </p>
            <p>
                <input type="checkbox" class="custom-checkbox" id="3" name="roleChoice" value="ROLE_DEVELOPER"
                       <c:if test="${editUser.hasRole('ROLE_DEVELOPER')}">checked="checked"</c:if>>
                <label for="3">Разработчик</label>
            </p>
            <p>
                <input type="checkbox" class="custom-checkbox" id="4" name="roleChoice" value="ROLE_MANAGER"
                       <c:if test="${editUser.hasRole('ROLE_MANAGER')}">checked="checked"</c:if>>
                <label for="4">Менеджер</label>
            </p>
            <p>
                <input type="checkbox" class="custom-checkbox" id="5" name="roleChoice" value="ROLE_TRAINEE"
                       <c:if test="${editUser.hasRole('ROLE_TRAINEE')}">checked="checked"</c:if>>
                <label for="5">Стажер</label>
            </p>
        </div>
        <div class="form_group">
            <button class="form_button" type="submit">Сохранить</button>
        </div>
    </form>
</div>
<t:footer/>
</body>
</html>


