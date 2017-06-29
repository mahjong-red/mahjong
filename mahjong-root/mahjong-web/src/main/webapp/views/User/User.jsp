<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/easyui-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<t:easyui></t:easyui>
<t:datagrid name="userlist" checkbox="true" actionUrl="User/Find"
	fit="true" fitColumns="false" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="用户名" field="username" query="true" ></t:dgCol>
	<t:dgCol title="昵称" field="nickname" query="true"></t:dgCol>
	<t:dgCol title="性别" field="sexText"></t:dgCol>
	<t:dgCol title="状态" field="userStatusText"></t:dgCol>
	<t:dgCol title="角色" field="roleName"></t:dgCol>
	<t:dgDelOpt title="删除" url="textTemplateController.do?del&id={id}" />
	<t:dgToolBar title="新增用户" icon="icon-add" onclick="userlistCreate('${ctx}/User/Create')"></t:dgToolBar>
	<t:dgToolBar title="编辑用户" icon="icon-edit" onclick="userlistUpdate('${ctx}/User/Update')"></t:dgToolBar>
	<t:dgToolBar title="批量删除" icon="icon-remove" onclick="userlistDelete('${ctx}/User/Delete')"></t:dgToolBar>
</t:datagrid>
<div id="userlistWindow" class="easyui-dialog" title="编辑" data-options="iconCls:'icon-edit',closed:true,buttons: [{
		text:'保存',iconCls:'icon-ok',handler:function(){UserCreateFormSave();}}]" style="width:400px;padding:10px" >
	<form id="UserCreateForm" method="post" class="easyui-form" action="<c:url value="/User/Create" />" >
		<table cellpadding="5">
			<tr>
				<td width="30%">用户名: </td>
				<td><input class="easyui-textbox" type="text" name="username" autocomplete="off" style="width:180px"
					data-options="required:true,validType:'length[2,20]',invalidMessage:'用户名长度为2~20.'"></input>
					<input type="hidden" name="id" />
				</td>
			</tr>
			<tr>
				<td>昵称:</td>
				<td><input class="easyui-textbox" type="text" name="nickname" autocomplete="off" style="width:180px"
					data-options="required:true,validType:'length[2,10]',width:100,invalidMessage:'昵称长度为2~10.'"></input></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input class="easyui-textbox" type="password" id="UserCreateFormPassword" name="password" autocomplete="off" style="width:180px"
					data-options="required:true,validType:'length[6,20]'"></input></td>
			</tr>
			<tr>
				<td>性别:</td>
				<td><t:enumSelectTag id="UserCreateFormSex" name="sex" enumClass="cn.mahjong.enums.persist.Sex" style="'width:180px'"
					css="easyui-combobox" dataOptions="panelHeight:'auto',required:true,invalidMessage:'请选择性别',editable:false,validType:'comboxValidate(UserCreateFormSex)'" ></t:enumSelectTag></td>
			</tr>
			<tr>
				<td>状态:</td>
				<td>
					<t:enumSelectTag id="UserCreateFormUserStatus" name="userStatus" enumClass="cn.mahjong.enums.persist.UserStatus" style="'width:180px'"
					css="easyui-combobox" defaultVal="1" dataOptions="panelHeight:'auto',required:true,invalidMessage:'请选择状态',editable:false,validType:'comboxValidate(UserCreateFormUserStatus)'" ></t:enumSelectTag>
				</td>
			</tr>
			<tr>
				<td>权限:</td>
				<td>
					<select class="easyui-combobox" id="UserCreateFormRoleSet" name="roleSet" style="width:180px" data-options="multiple:true,panelHeight:'auto'" >
						<c:forEach items="${roleList }" var="item">
							<option value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	$("#UserCreateForm tr").each(function(){
	    $(this).find("td:first").attr('align','right');
	})
	UserCreateFormSave = function(){
		$('#UserCreateForm').form('submit',{
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(data){
				var data = eval('(' + data + ')');
				if(data.code == '0'){
					$('#userlistWindow').dialog('close');
					$('#UserCreateForm').form("reset");
					$('#userlist').datagrid("reload");
				}else{
					$.messager.alert('提示','操作失败！<br />'+data.msg,'error');
				}
		    }
		});
	}
	userlistCreate = function(url){
		$("#UserCreateForm").form({"url":url});
		$("#UserCreateFormPassword").textbox({'required':false});
		$('#userlistWindow').dialog('open');
		$("#UserCreateForm").form("clear");
	}
	userlistUpdate = function(url){
		var selectedRow = $('#userlist').datagrid("getSelected");
		if(selectedRow != null){
			userlistCreate(url);
			$("#UserCreateFormPassword").textbox({'required':false});//将密码字段置空
			console.info(selectedRow);
			$("#UserCreateForm").form("load",selectedRow);
			$("#UserCreateFormRoleSet").combobox("setValues",selectedRow.roleVal.split(","));
		}else{
			$.messager.alert('提示','请选择一行。','error');
		}
	}
	userlistDelete = function(url){
		var selectedRows= $('#userlist').datagrid("getSelections");
		if(selectedRows.length == 0){
			$.messager.alert('提示','请至少选择一行。','error');
		}else{
			var ids = '';
			$.each(selectedRows,function(i,item){
				if(item){
					ids += item.id;
					ids += ",";
				}
			});
			$.messager.confirm({
				title: '删除提示',
				msg: '确定删除吗？',
				fn: function(r){
					if (r){
						$.post(url,{'id':ids},function(data){
							if(data.code == '0'){
								$.messager.alert("提示","删除成功");
								$('#userlist').datagrid("reload");
							}else{
								$.messager.alert("提示",data.msg,"error");
							}
						},'json');
					}
				}
			});
		}
	}
</script>