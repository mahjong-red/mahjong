<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" uri="/easyui-tags"%>
<t:datagrid name="texttemplatelist" checkbox="true" actionUrl="User/Find" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false"  ></t:dgCol>
	<t:dgCol title="用户名" field="username" ></t:dgCol>
	<t:dgCol title="昵称" field="nickname" ></t:dgCol>
	<t:dgCol title="性别" field="sex" ></t:dgCol>
	<t:dgCol title="状态" field="userStatus"></t:dgCol>
	<t:dgDelOpt title="删除" url="textTemplateController.do?del&id={id}" />
	<t:dgToolBar title="文本录入" icon="icon-add" url="User/Create" funname="add"></t:dgToolBar>
 	<t:dgToolBar title="文本编辑" icon="icon-edit" url="textTemplateController.do?addorUpdate" funname="update"></t:dgToolBar>
 	<t:dgToolBar title="批量删除"  icon="icon-remove" url="textTemplateController.do?doBatchDel" funname="deleteALLSelect" ></t:dgToolBar>
</t:datagrid>