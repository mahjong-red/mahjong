<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/easyui-tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<t:easyui></t:easyui>
	<t:datagrid name="rolelist" checkbox="true" actionUrl="Role/Find"
		fit="true" fitColumns="false" idField="id" queryMode="group">
		<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
		<t:dgCol title="代码" field="code" query="true" ></t:dgCol>
		<t:dgCol title="名称" field="name" query="true"></t:dgCol>
		<%-- <t:dgCol title="资源" field="resourceSet" hidden="true"></t:dgCol> --%>
		<t:dgDelOpt title="删除" url="textTemplateController.do?del&id={id}" />
		<t:dgToolBar title="新增" icon="icon-add" onclick="rolelistCreate('${ctx}/Role/Create')"></t:dgToolBar>
		<t:dgToolBar title="编辑" icon="icon-edit" onclick="rolelistUpdate('${ctx}/Role/Update')"></t:dgToolBar>
		<t:dgToolBar title="批量删除" icon="icon-remove"  onclick="rolelistDelete('${ctx}/Role/Delete')"></t:dgToolBar>
		<t:dgToolBar title="刷新资源" icon="icon-reload"  onclick="reloadResource('${ctx}/Role/InitRole')"></t:dgToolBar>
	</t:datagrid>
	<div id="rolelistWindow" class="easyui-dialog" title="编辑" data-options="iconCls:'icon-edit',closed:true,buttons: [{
			text:'保存',iconCls:'icon-ok',handler:function(){RoleCreateFormSave();}}]" style="width:400px;padding:10px" >
		<form id="RoleCreateForm" method="post" class="easyui-form" >
			<table cellpadding="5" >
				<tr>
					<td width="30%">代码: </td>
					<td><input class="easyui-textbox" type="text" name="code"
						data-options="required:true,validType:'length[2,20]'"></input>
						<input type="hidden" name="id" />
					</td>
				</tr>
				<tr>
					<td>名称:</td>
					<td><input class="easyui-textbox" type="text" name="name"
						data-options="required:true,validType:'length[2,10]'"></input></td>
				</tr>
				<tr>
					<td >资源:</td>
					<td>
						<input class="easyui-combotree" id="RoleCreateFormResourceTree" data-options="url:'<c:url value="/Resource/LoadTree" />',
						method:'get',multiple:true,checkbox:true,panelHeight:'auto'" style="width:100%">
						<input class="easyui-textbox" name="resourceSet" id="RoleCreateFormResourceSet" type="hidden" >
						<%-- <ul class="easyui-tree" id="RoleCreateFormResourceTree"
							 data-options="url:'<c:url value="/Resource/LoadTree" />',checkbox:true" ></ul> --%>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$("#RoleCreateForm tr").each(function(){
		    $(this).find("td:first").attr('align','right');
		})
		RoleCreateFormSave = function(){
			var t = $("#RoleCreateFormResourceTree").combotree('tree');
			var n = t.tree('getChecked',  ['checked','indeterminate']);
			var ids = '';
			$.each(n,function(i,item){
				ids += item.id + ",";
			});
			$("#RoleCreateFormResourceSet").textbox("setValue",ids);
			$('#RoleCreateForm').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				success:function(data){
					data = eval('('+data+')');
					if(data.code == "0"){
						$.messager.alert('提示','操作成功。');
						$('#rolelist').datagrid("reload");
						$("#rolelistWindow").dialog("close");
					}else{
						$.messager.alert('提示','操作失败：<br />'+data.msg,'error');
					}
			    }
			});
		}
		rolelistWindowOpen = function(){
			$("#RoleCreateForm").form("clear");
			var tree = $("#RoleCreateFormResourceTree").combotree('tree');
			$.each(tree.tree('getChecked', ['checked']),function(i,item){
				tree.tree("uncheck",item.target);
			})
			$("#rolelistWindow").dialog("open");
		}
		rolelistCreate = function(url){
			$("#RoleCreateForm").form({"url":url});
			rolelistWindowOpen();
		}
		rolelistDelete = function(url){
			var selectedRows= $('#rolelist').datagrid("getSelections");
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
									$('#rolelist').datagrid("reload");
								}else{
									$.messager.alert("提示",data.msg,"error");
								}
							},'json');
						}
					}
				});
			}
		}
		reloadResource = function(url){
			$.get(url,null,function(data){
				if(data.code == '0'){
					$.messager.alert("提示","刷新成功");
				}else{
					$.messager.alert("提示",data.msg,"error");
				}
			},'json');
		}
		rolelistUpdate = function(url){
			var selectedRow = $('#rolelist').datagrid("getSelected");
			if(selectedRow != null){
				$("#RoleCreateForm").form({"url":url});
				rolelistWindowOpen();
				$("#RoleCreateForm").form("load",selectedRow);
				console.info("------------");
				$.each(selectedRow.resourceSet.split(","),function(i,item){
					if(item){
						var tree = $("#RoleCreateFormResourceTree").combotree('tree');
						var node = tree.tree("find",item);
						if(tree.tree('isLeaf', node.target)){
							tree.tree("check",node.target);
						}
					}
				});
			}else{
				$.messager.alert('提示','请选择一行。','error');
			}
		}
	</script>
</body>
</html>