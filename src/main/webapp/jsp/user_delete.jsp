<%--
  Created by IntelliJ IDEA.
  User: uluan
  Date: 11.11.2021
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <title>Удаление пользователя</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<div class="form_center">
    <div class="form">
    <h1 class="title">Удалить пользователя <c:out value="${usertodelete}"/>?</h1>
        <form style="float: left" action="userdelete.jhtml" method="post">
            <button class="form_button" type="submit">Да</button>
        </form>
        <button  class="form_button"><a href="users.jhtml">Нет</a></button>
    </div>
</div>
</div>
<t:footer/>
</body>
</html>
