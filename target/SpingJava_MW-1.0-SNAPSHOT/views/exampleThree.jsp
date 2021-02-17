<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
</head>
<body>
<form:form method="post" action="exampleThree.html" modelAttribute="relation">
    <h3><spring:message code="label.relationList"/></h3>
    <c:if  test="${!empty relationList}">
        <table class="data">
            <tr>
                <th><spring:message code="label.timeFrom"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th><spring:message code="label.timeTo"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th><spring:message code="label.cityFrom"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
                <th><spring:message code="label.cityTo"/></th>
                <th>&nbsp;</th>
                <th>&nbsp;</th>
            </tr>
            <c:forEach items="${relationList}" var="relationTime">
                <tr>
                    <td>${relationTime.timeFrom}</td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <td>${relationTime.timeTo}</td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <td>${relationTime.relation.cityFrom.name}</td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <td>${relationTime.relation.cityTo.name}</td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
                    <td><a href="showRelation.html?relationId=${relationTime.id}"> Edit</a></td>
                    </sec:authorize>
                    <th> </th>
                    <th> </th>
                    <th>&nbsp;</th>
                    <td><a href="buyTicket/${relationTime.id}.html">buy ticket</a></td>
                    <th>&nbsp;</th>
                    <th>&nbsp;</th>
                    <th> </th>
                    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
                        <td><a href="deleteRelation.html?relationId=${relationTime.id}"> Delete</a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>

        </table>
    </c:if>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_MANAGER')">
    <td><a href="showRelation.html">Add </a></td>
    </sec:authorize>
</form:form>

</body>
</html>



