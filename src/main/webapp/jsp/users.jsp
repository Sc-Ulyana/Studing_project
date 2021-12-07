<%--
  Created by IntelliJ IDEA.
  User: uluan
  Date: 20.10.2021
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <title>Список пользователей</title>
    <meta charset="UTF-8"/>
    <style>
        <%@include file='../css/style.css' %>
    </style>
    <script src="https://kit.fontawesome.com/0ec0648ba6.js" crossorigin="anonymous"></script>
</head>
<t:header welcome="true" changePassword="true" usersPage="false"/>
<div style="text-align: center">
    <p class="title" style="display: inline-block">Список пользователей</p>
    <button class="round_button" style="background-color: #029202; margin-left: 20px;"><a href="useradd.jhtml"><i class="fas fa-user-plus"></i></a></button>
</div>
<c:out value="${message}"/>
<table>
    <tr>
        <th>Имя</th>
<%--        <th>Фамилия</th>--%>
        <th>Логин</th>
        <th>Пароль</th>
        <th>Возраст</th>
        <th>Дата рождения</th>
        <th>Зарплата</th>
        <th>Роль</th>
        <th> </th>
    </tr>

    <c:forEach var="user" items="${usersList}">
        <tr>
            <td><a href="useredit.jhtml?usertoedit=${user.getLogin()}"><c:out value="${user.getName()}"/></a></td>
<%--            <td><c:out value="${user.getSurname()}"/></td>--%>
            <td><c:out value="${user.getLogin()}"/></td>
            <td><c:out value="${user.getPassword()}"/></td>
            <td><c:out value="${user.getAge()}"/></td>
            <td><c:out value="${user.getDateOfBirth()}"/></td>
            <td><c:out value="${user.getSalary()}"/></td>
            <td><c:out value="${user.getRoles()}"/></td>
            <td><button class="round_button"  style="background-color: #ff0000"><a href="userdelete.jhtml?usertodelete=${user.getLogin()}"><i class="fas fa-user-minus"></i></a></button></td>
        </tr>
    </c:forEach>
</table>
<t:footer/>
</body>
</html>
