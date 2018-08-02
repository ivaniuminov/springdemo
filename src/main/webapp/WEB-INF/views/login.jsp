<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Login</title>

    <link href="<c:out value="/resources/static/css/bootstrap.min.css" />" rel="stylesheet">

    <link href="<c:out value="/resources/static/css/signin.css" />" rel="stylesheet">

</head>

<body>

<div class="container">

    <spring:form method="post" action="/login" modelAttribute="user" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <%--<spring:label path="email" for="inputEmail" class="sr-only">Email address</spring:label>--%>
        <spring:input path="email" type="email" class="form-control" placeholder="Email address"  />
       <%-- <spring:label path="passsword" for="inputPassword" class="sr-only">Password</spring:label>--%>
        <spring:input path="password" type="password"  class="form-control" placeholder="Password" />
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </spring:form>

</div>

</body>
</html>