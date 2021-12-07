<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ attribute name="role" required="true" %>--%>
<%@ attribute name="welcome" required="true" %>
<%@ attribute name="changePassword" required="true" %>
<%@ attribute name="usersPage" required="true" %>
<div style="text-align: center">
    <div id="header" style="display: inline-block">Webapp</div>
    <div style="float: right"><a href="exit">
        <button class="form_button">Выход</button>
    </a></div>
    <div style="float: left" class="dropdown">
        <button class="mainmenubtn">Меню</button>
        <div class="dropdown-child">
            <c:if test="${usersPage}">
                <c:forEach items="${role}" var="foo">
                    <tr>
                        <c:if test="${foo.name=='ROLE_ADMIN'}">
                            <a href="users.jhtml">Список пользователей</a>
                        </c:if>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${welcome}"><a href="welcome.jhtml">Главная</a></c:if>
            <c:if test="${changePassword}"><a href="loginedit.jhtml">Сменить пароль</a></c:if>
        </div>
    </div>
</div>