<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login登录</title>
</head>
<body>
<div class="row-fluid">
				<div class="login-box">
					<div class="icons">
						<a href="index.html"><i class="halflings-icon home"></i></a> <a href="#"><i class="halflings-icon cog"></i></a>
					</div>
					<h2>登录</h2>
					<form id="loginForm" class="form-horizontal" action="<c:url value="/j_spring_security_check" />" method="post">
						<fieldset>
							<div class="input-prepend" title="用户名">
								<span class="add-on"><i class="halflings-icon user"></i></span> 
								<input class="input-large span10" value="manye" name="username" id="username" type="text" placeholder="用户名" autocomplete="off" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="密码">
								<span class="add-on"><i class="halflings-icon lock"></i></span>
								<input class="input-large span10" value="123456" name="password" id="password" type="password" placeholder="密码" autocomplete="off"/>
							</div>
							<div class="clearfix"></div>
							<label class="remember" for="remember"><input type="checkbox" id="remember" />记住我</label>
							<div class="button-login">
								<button type="submit" class="btn btn-primary">登录</button>
							</div>
							<div class="clearfix"></div>
						</fieldset>
					</form>
					<c:if test="${not empty  SPRING_SECURITY_LAST_EXCEPTION}">
					<h2>
						<label class="bg-red disabled color-palette" style="width: 90%;color: red">
						登录失败<br />
						${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
						</label>
					</h2>
					</c:if>
					<hr>
				</div>
				<!--/span-->
			</div>
			<!--/row-->
</body>
</html>