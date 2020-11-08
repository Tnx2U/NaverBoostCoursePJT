<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" content="text/html"; charset=UTF-8>
<title>Main Page</title>
</head>
<body>
    <h1>This is main page</h1>
    <c:forEach items="${categoryList}" var="category">
        ${category.id}<br>
        ${category.name}<br>
    </c:forEach>
</body>
</html>