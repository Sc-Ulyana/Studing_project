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
<t:header role="${role}" welcome="true" changePassword="false" usersPage="true"/>
<div class="form_center">
    <form class="form" action="useredit.jhtml" method="post">
        <h1 class="title">Редактирование пользователя <c:out value="${editUser.getLogin()}"/></h1>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <input name="name" type="text" class="form_input" placeholder="Имя" value="${editUser.getName()}"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <input name="surname" type="text" class="form_input" placeholder="Фамилия" value="${editUser.getSurname()}"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <input name="login" type="text" class="form_input" placeholder="Логин" value="${editUser.getLogin()}"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <c:out value="${checkPassword}"/>
            <input name="password" type="password" class="form_input" placeholder="Пароль" value="${editUser.getPassword()}"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <c:out value="${checkEmail}"/>
            <input name="email" type="text" class="form_input" placeholder="Email" value="${editUser.getEmail()}"/>
        </div>
        <div class="form_group">
            <c:out value="${checkEmpty}"/>
            <c:out value="${checkDate}"/>
            <input name="dateOfBirth" type="date" class="form_input" placeholder="Дата рождения" min="1940-01-01" max="2021-01-01" value="${editUser.getDateOfBirth()}"/>
        </div>
        <div class="form_group">
            <span class="custom-dropdown big">
            <select name="role">
                <c:choose>
                <c:when test="${editUser.getRole()=='ROLE_ADMIN'}">
                    <option>ROLE_ADMIN</option>
                    <option>ROLE_USER</option>
                </c:when>
                <c:otherwise>
                    <option>ROLE_USER</option>
                    <option>ROLE_ADMIN</option>
                </c:otherwise>
                </c:choose>
            </select>
            </span>
        </div>
        <button class="form_button" type="submit">Сохранить</button>
    </form>
</div>
<t:footer/>
</body>
</html>


