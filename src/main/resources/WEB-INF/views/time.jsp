<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Current Time</title>
</head>
<body>
    <h1>Current Time</h1>

    <c:if test="${not empty currentTime}">
        <p>Current Time: ${currentTime}</p>
    </c:if>

    <c:if test="${not empty timezone}">
        <p>Timezone: ${timezone}</p>
    </c:if>
</body>
</html>