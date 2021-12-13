<%--
  Created by IntelliJ IDEA.
  User: uluan
  Date: 12.11.2021
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление пользователя</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<t:header welcome="true" changePassword="false" usersPage="true"/>
<div class="form_center">
    <form class="form" style="margin-top: 230px" action="useradd.jhtml" method="post">
        <h1 class="form_title">Добавление пользователя</h1>
        <div class="form_group">
            <input name="name" type="text" class="form_input" placeholder="Имя"/>
            <p class="error"><c:out value="${checkEmpty}"/></p>
        </div>
        <div class="form_group">
            <input name="login" type="text" class="form_input" placeholder="Логин"/>
            <p class="error"><c:out value="${checkEmpty}"/>
                <c:out value="${checkLogin}"/></p>
        </div>
        <div class="form_group">
            <input name="password" type="password" class="form_input" placeholder="Пароль"/>
            <p class="error"><c:if test="${password==null}"><c:out value="${checkEmpty}"/></c:if>
                <c:out value="${checkPassword}"/></p>
        </div>

        <div class="form_group">
            <input name="age" type="number" class="form_input" placeholder="Возраст"/>
            <p class="error"><c:out value="${checkAge}"/>
                <c:out value="${emptyAge}"/></p>
        </div>
        <div class="form_group">
            <input name="dateOfBirth" type="date" class="form_input" placeholder="Дата рождения" min="1940-01-01"
                   max="2021-01-01"/>
            <p class="error"><c:if test="${dateOfBirth==null}"><c:out value="${checkEmpty}"/></c:if>
                <c:out value="${checkDate}"/></p>
        </div>
        <div class="form_group">
            <input name="salary" type="text" class="form_input" placeholder="Зарплата"/>
            <p class="error"><c:out value="${emptySalary}"/>
                <c:out value="${checkSalary}"/></p>
        </div>
        <div class="form_group">
            <p>
                <input type="checkbox" class="custom-checkbox" id="1" name="roleChoice" value="ROLE_ADMIN">
                <label for="1">Админ</label>
            </p>
            <p>
                <input type="checkbox" class="custom-checkbox" id="2" name="roleChoice" value="ROLE_USER">
                <label for="2">Пользователь</label>
            </p>
            <p>
                <input type="checkbox" class="custom-checkbox" id="3" name="roleChoice" value="ROLE_DEVELOPER">
                <label for="3">Разработчик</label>
            </p>
            <p>
                <input type="checkbox" class="custom-checkbox" id="4" name="roleChoice" value="ROLE_MANAGER">
                <label for="4">Менеджер</label>
            </p>
            <p>
                <input type="checkbox" class="custom-checkbox" id="5" name="roleChoice" value="ROLE_TRAINEE">
                <label for="5">Стажер</label>
            </p>
            <p class="error"><c:out value="${emptyRoles}"/></p>
        </div>
        <div class="form_group">
            <button class="form_button" type="submit">Сохранить</button>
        </div>
    </form>
</div>
<t:footer/>
</body>
</html>


