<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="UserCreateForm" method="post" action="<c:url value="/User/Create" />" >
	<table cellpadding="5">
		<tr>
			<td width="20%">用户名: ${winname}</td>
			<td><input class="easyui-textbox" type="text" name="username"
				data-options="required:true,validType:'length[2,20]',invalidMessage:'用户名长度为2~20.'"></input></td>
		</tr>
		<tr>
			<td>昵称:</td>
			<td><input class="easyui-textbox" type="text" name="nickname"
				data-options="required:true,validType:'length[2,10]',invalidMessage:'昵称长度为2~10.'"></input></td>
		</tr>
		<tr>
			<td>昵称:</td>
			<td><input class="easyui-textbox" type="text" name="nickname"
				data-options="required:true,validType:'length[2,10]',invalidMessage:'昵称长度为2~10.'"></input></td>
		</tr>
		<tr>
			<td>昵称:</td>
			<td><input class="easyui-textbox" type="text" name="nickname"
				data-options="required:true,validType:'length[2,10]',invalidMessage:'昵称长度为2~10.'"></input></td>
		</tr>
		<tr>
			<td>password:</td>
			<td><input class="easyui-textbox" type="password" name="password"
				data-options="required:true,validType:'realName'"></input></td>
		</tr>
	</table>
</form>