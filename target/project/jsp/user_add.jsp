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
<t:header role="${role}" welcome="true" changePassword="false" usersPage="true"/>
<div class="form_center">
    <form class="form" action="useradd.jhtml" method="post">
        <h1 class="title">Добавление пользователя</h1>
        <div class="form_group">
            <input name="name" type="text" class="form_input" placeholder="Имя"/>
            <c:if test="${name==null}"><c:out value="${checkEmpty}"/></c:if>
        </div>
        <div class="form_group">
            <input name="surname" type="text" class="form_input" placeholder="Фамилия"/>
            <c:if test="${surname==null}"><c:out value="${checkEmpty}"/></c:if>
        </div>
        <div class="form_group">
            <input name="login" type="text" class="form_input" placeholder="Логин"/>
            <c:if test="${login==null}"><c:out value="${checkEmpty}"/></c:if>
            <c:out value="${checkLogin}"/>
        </div>
        <div class="form_group">
            <input name="password" type="password" class="form_input" placeholder="Пароль"/>
            <c:if test="${password==null}"><c:out value="${checkEmpty}"/></c:if>
            <c:out value="${checkPassword}"/>
        </div>
        <div class="form_group">
            <input name="email" type="text" class="form_input" placeholder="Email"/>
            <c:if test="${email==null}"><c:out value="${checkEmpty}"/></c:if>
            <c:out value="${checkEmail}"/>
        </div>
        <div class="form_group">
            <input name="dateOfBirth" type="date" class="form_input" placeholder="Дата рождения" min="1940-01-01" max="2021-01-01"/>
            <c:if test="${dateOfBirth==null}"><c:out value="${checkEmpty}"/></c:if>
            <c:out value="${checkDate}"/>
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
<t:footer/>
</body>
</html>


