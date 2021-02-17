<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
</head>
<body>
<form:form method="post" action="exampleTwo.html" modelAttribute="register">
    <h3><spring:message code="label.relationList"/></h3>
    <c:if  test="${!empty registerList}">
        <table class="data">
            <tr>
                <th><spring:message code="label.login"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th><spring:message code="label.seatNumber"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th><spring:message code="label.cityFrom"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th><spring:message code="label.cityTo"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${registerList}" var="register">
                <c:forEach items="${register.ticket}" var="ticke">
                    <tr>
                        <td>${register.login}</td>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <td>${ticke.seatNumber}</td>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <td>${ticke.relationTime.relation.cityFrom.name}</td>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <td>${ticke.relationTime.relation.cityTo.name}</td>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                        <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
                        <td><a href="deleteTicket?ticketId=${ticke.id}">delete</a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>

            </c:forEach>
        </table>
    </c:if>
</form:form>
</body>
</html>


