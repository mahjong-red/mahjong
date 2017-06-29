<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
您的登陆状态已失效，请<a href="#" onclick="goLoginPage('<c:url value="/views/login.jsp" />')">重新登录</a>
<script type="text/javascript">
	function goLoginPage(loginUrl){
		if (window != top) 
			top.location.href = loginUrl; 
		else
			location.href = loginUrl; 
	}
</script>
</body>
</html>