<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>

    <c:forEach var="c" items="${categories}">
        <h1>
            <a href="<c:out value="/category?c_id=${c.id}"/>"><c:out value="${c.name}"/></a>
        </h1>
    </c:forEach>
</body>
</html>
