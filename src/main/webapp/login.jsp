<%@page contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:base pageTitle="tList" isLoginPage="true">

    <c:if test="${invalidLogin == null}">
        <c:set var="invalidLogin" value="false"/>
    </c:if>

    <c:set var="formGroup" value="${invalidLogin ? 'form-group has-error' : 'form-group'}"/>

    <div class="container-fluid">
        <form method="post" action="login" class="form-login">
            <h1>Sign in</h1>

            <div class="${formGroup}">
                <label for="email" class="sr-only">Email address</label>
                <input type="email" id="email" name="email" class="form-control" 
                       placeholder="Email address" required autofocus
                       value="<c:out value="${email}" default=""/>">
            </div>

            <div class="${formGroup}">
                <label for="password" class="sr-only">Password</label>
                <input type="password" id="password" name="password" class="form-control"
                       placeholder="Password" required
                       aria-describedby="msg">
                <span id="msg" class="help-block">
                    <c:out value="${errorMsg}" default=""/>
                </span>
            </div>

            <button type="submit" class="btn btn-primary btn-block">Sign in</button>
        </form>
    </div>
</t:base>