<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/easyui-tags"%>
<t:datagrid name="userlist" checkbox="true" actionUrl="User/Find"
	fit="true" fitColumns="false" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="用户名" field="username" query="true" ></t:dgCol>
	<t:dgCol title="昵称" field="nickname" query="true"></t:dgCol>
	<t:dgCol title="性别" field="sex"></t:dgCol>
	<t:dgCol title="状态" field="userStatus"></t:dgCol>
	<t:dgDelOpt title="删除" url="textTemplateController.do?del&id={id}" />
	<t:dgToolBar title="文本录入" icon="icon-add" url="User/Create" onclick="$('#userlistWindow').dialog('open');"></t:dgToolBar>
	<t:dgToolBar title="文本编辑" icon="icon-edit" url="textTemplateController.do?addorUpdate" funname="update"></t:dgToolBar>
	<t:dgToolBar title="批量删除" icon="icon-remove" url="textTemplateController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
</t:datagrid>
<div id="userlistWindow" class="easyui-dialog" title="编辑" data-options="iconCls:'icon-edit',closed:true,buttons: [{
		text:'保存',iconCls:'icon-ok',handler:function(){save();}}]" style="width:400px;padding:10px" >
	<form id="UserCreateForm" method="post" class="easyui-form" action="<c:url value="/User/Create" />" >
		<table cellpadding="5">
			<tr>
				<td width="30%">用户名: </td>
				<td><input class="easyui-textbox" type="text" name="username"
					data-options="required:true,validType:'length[2,20]',invalidMessage:'用户名长度为2~20.'"></input></td>
			</tr>
			<tr>
				<td>昵称:</td>
				<td><input class="easyui-textbox" type="text" name="nickname"
					data-options="required:true,validType:'length[2,10]',invalidMessage:'昵称长度为2~10.'"></input></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input class="easyui-textbox" type="password" name="password"
					data-options="required:true,validType:'length[6,20]'"></input></td>
			</tr>
			<tr>
				<td>性别:</td>
				<td><t:enumSelectTag id="UserCreateFormSex" name="sex" enumClass="cn.mahjong.enums.persist.Sex" 
					css="easyui-combobox" dataOptions="required:true,invalidMessage:'请选择性别',editable:false,validType:'comboxValidate(UserCreateFormSex)'" ></t:enumSelectTag></td>
			</tr>
			<tr>
				<td>状态:</td>
				<td>
					<t:enumSelectTag id="UserCreateFormUserStatus" name="userStatus" enumClass="cn.mahjong.enums.persist.UserStatus"
					css="easyui-combobox" defaultVal="1" dataOptions="required:true,invalidMessage:'请选择状态',editable:false,validType:'comboxValidate(UserCreateFormUserStatus)'" ></t:enumSelectTag>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	$("#UserCreateForm tr").each(function(){
	    $(this).find("td:first").attr('align','right');
	})
	save = function(){
		$('#UserCreateForm').form('submit',{
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			}
		});
	}
</script>