<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
</head>
<body>
<form:form method="post" action="listofusers.html" modelAttribute="register">
    <h3><spring:message code="label.userList"/></h3>
    <c:if  test="${!empty registerList}">
        <table class="data">
            <tr>
                <th><spring:message code="label.firstName"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th><spring:message code="label.lastName"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th><spring:message code="label.email"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th><spring:message code="label.telephone"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${registerList}" var="register">
                <tr>
                    <td>${register.firstName}</td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <td>${register.lastName}</td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <td>${register.email}</td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <td>${register.telephone}</td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <td><a href="delete/${register.id}.html">delete</a></td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <td><a href="/registers.html?registerId=${register.id}">edit</a></td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <td><a href="generatePdf-${register.id}">pdf</a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</form:form>
</body>
</html>


