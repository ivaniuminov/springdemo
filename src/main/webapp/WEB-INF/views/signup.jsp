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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link href="<c:out value="/resources/static/css/signin.css" />" rel="stylesheet">

</head>

<body>

<div class="container">

    <spring:form method="post" action="/signup" modelAttribute="user" class="form-signin">
        <h2 class="form-signin-heading">Enter first name:</h2>
        <spring:input path="firstName" type="text" class="form-control" placeholder="First name" />
        <h2 class="form-signin-heading">Enter last name:</h2>
        <spring:input path="lastName" type="text" class="form-control" placeholder="Last name" />
        <h2 class="form-signin-heading">Enter e-mail:</h2>
        <spring:input path="email" type="email" class="form-control" placeholder="Email address"  />
        <h2 class="form-signin-heading">Enter password:</h2>
        <spring:input path="password" type="password"  class="form-control" placeholder="Password" />
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </spring:form>

    <%--<script>
        $("input").blur(function(){
            alert("This input field has lost its focus.");
        });

    </script>--%>

    <script>

        $("#email").blur(function(){
            var email = $("#email").val();

            json = {};
            json ["email"] = email;

            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "http://localhost:8080/email",
                data : JSON.stringify(json),
                dataType : 'json',
                timeout : 100000,
                success : function(data) {
                    $("#msg").html(data.msg);
                },
                error : function(e) {
                    $("#msg").html(data.msg);
                }
            });
        });

    </script>

</div>

</body>
</html>