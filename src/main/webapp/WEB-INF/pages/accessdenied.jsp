<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Access Denied Page</title>
</head>
<body>
    <h2> Access Denied </h2>
    <br/>${message}<br/>

    Click here for<a href="<c:url value="/login" />"> Login</a>
</body>
</html>