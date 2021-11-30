<%--
  Created by IntelliJ IDEA.
  domain.User: uluan
  Date: 18.10.2021
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добро пожаловать</title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<t:header role="${role}" welcome="false" changePassword="true" usersPage="true"/>
<div align="right">
    <p><c:out value="${message}"/></p>
    <h1 class="title"><b>Добро пожаловать,<c:out value="${name} ${surname}"/></b></h1>
</div>
<t:footer/>
</body>
</html>
