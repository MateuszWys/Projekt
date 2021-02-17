<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<p><spring:message code="label.menu"/></p>

<sec:authorize access="isAnonymous()">
<a href="/login.html"><spring:message code="label.login"/></a>
</br>
<a href="/registers.html"><spring:message code="label.register"/></a>
<br/>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
<a href="/listofusers.html"><spring:message code="label.userList"/> </a>
<br/>
<br/>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_MANAGER')">
<a href="/exampleTwo.html"><spring:message code="label.travelList"/></a>
<br/>
<br/>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_MANAGER')">
<a href="/exampleThree.html"><spring:message code="label.listOfTravel"/></a>
</sec:authorize>


<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>

<!-- csrf for log out-->
<form action="/logout" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

<br/>
<div>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p>
            <spring:message code="label.welcome"/> : ${pageContext.request.userPrincipal.name} |
            <a href="javascript:formSubmit()"> Logout</a>
        </p>
    </c:if>
</div>

