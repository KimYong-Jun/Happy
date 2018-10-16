<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HappyBoard</title>
</head>
<body>
<header><h1>${title}</h1></header>
<jsp:include page="top.jsp"/>
<div>
<jsp:include page="${target}.jsp"/>
</div>
<jsp:include page="bottom.jsp"/>
</body>
</html>