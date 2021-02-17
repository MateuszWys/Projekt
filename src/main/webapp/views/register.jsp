<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head> <title>Rejestracja</title>
    <!-- Captcha Google -->
    <script src="https://www.google.com/recaptcha/api.js"></script>
</head>
<body>
<h1><spring:message code="label.register"/></h1>
<form:form method="post" action="addRegister.html" modelAttribute="register">
    <table>
        <tr>
            <td><form:hidden path="id"/>
        </tr>
        <tr>
            <td><form:label path="firstName"><spring:message code="label.firstName"/></form:label></td>
            <td><form:input path="firstName" /></td>
            <td><form:errors path="firstName"/></td>
        </tr>
        <tr>
            <td><form:label path="lastName"><spring:message code="label.lastName"/></form:label></td>
            <td><form:input path="lastName" /></td>
            <td><form:errors path="lastName"/></td>
        </tr>
        <tr>
            <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
            <td><form:input path="email" /></td>
            <td><form:errors path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
            <td><form:input path="telephone" /></td>
            <td><form:errors path="telephone"/></td>
        </tr>
        <tr>
            <td><form:label path="login"><spring:message code="label.login"/></form:label></td>
            <td><form:input path="login" /></td>
            <td><form:errors path="login"/></td>
        </tr>
        <tr>
            <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
            <td><form:input type="password" path="password" /></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <div class="g-recaptcha" data-sitekey="6Le5bqEUAAAAACq42aKoz1eJXYbSlIDXmYmfkXzI"></div>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${register.id==0}">
                    <input type="submit" value="<spring:message code="label.addAppUser"/>"/>
                </c:if>
                <c:if test="${register.id!=0}">
                    <input type="submit" value="<spring:message code="label.editAppUser"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
 </form:form>
</body>
</html>

