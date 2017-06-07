<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/easyui-tags"%>
<t:datagrid name="userlist" checkbox="true" actionUrl="User/Find"
	fit="true" fitColumns="false" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="用户名" field="username" query="true"></t:dgCol>
	<t:dgCol title="昵称" field="nickname" query="true"></t:dgCol>
	<t:dgCol title="性别" field="sex"></t:dgCol>
	<t:dgCol title="状态" field="userStatus"></t:dgCol>
	<t:dgDelOpt title="删除" url="textTemplateController.do?del&id={id}" />
	<t:dgToolBar title="文本录入" icon="icon-add" url="User/Create" funname="add"></t:dgToolBar>
	<t:dgToolBar title="文本编辑" icon="icon-edit" url="textTemplateController.do?addorUpdate" funname="update"></t:dgToolBar>
	<t:dgToolBar title="批量删除" icon="icon-remove" url="textTemplateController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
</t:datagrid>
<div id="userlistWindow"></div>
<script type="text/javascript">
	add = function(){
		$("#userlistWindow").dialog({
			href:'<c:url value="User/Create?winname=userlistWindow"></c:url>',
			width:400,
			iconCls:'icon-save',
			buttons:[{
				text:'保存',
				iconCls:'icon-ok',
				handler:function(p){
					alert('ok' + p);
				}
			},{
				text:'取消',
				handler:function(){
					alert('cancel');;
				}
			}]
		});
	}
	createWindow = function(){}
</script>