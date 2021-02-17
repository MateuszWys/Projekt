<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
</head>
<body>
<form:form method="post" action="addRelation.html" modelAttribute="relationTime">
    <table>
        <tr>
            <td><form:hidden path="id"/>
            <td><form:hidden path="relation.cityFrom.id"/></td>
            <td><form:hidden path="relation.cityTo.id"/></td>
            <td><form:hidden path="relation.id"/></td>
        </tr>
        <tr>
            <td><form:label path="relation.cityFrom.name"><spring:message code="label.cityFrom"/></form:label></td>
            <td><form:input path="relation.cityFrom.name" /></td>
            <td><form:errors path="relation.cityFrom.name"/></td>
        </tr>
        <tr>
            <td><form:label path="relation.cityTo.name"><spring:message code="label.cityTo"/></form:label></td>
            <td><form:input path="relation.cityTo.name" /></td>
            <td><form:errors path="relation.cityTo.name"/></td>
        </tr>
        <tr>
            <td><form:label path="timeFrom"><spring:message code="label.timeFrom"/></form:label></td>
            <td><form:input path="timeFrom" /></td>
            <td><form:errors path="timeFrom"/></td>
        </tr>
        <tr>
            <td><form:label path="timeTo"><spring:message code="label.timeTo"/></form:label></td>
            <td><form:input path="timeTo" /></td>
            <td><form:errors path="timeTo"/></td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${relationTime.id==0}">
                    <input type="submit" value="<spring:message code="label.addTravel"/>"/>
                </c:if>
                <c:if test="${relationTime.id!=0}">
                    <input type="submit" value="<spring:message code="label.editTravel"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>



