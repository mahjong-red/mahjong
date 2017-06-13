<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" uri="/easyui-tags"%>

<div id="resourceLayout" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',split:true" style="width:200px;padding:10px;">
		<ul class="easyui-tree" id="resourceTree" data-options="onContextMenu:resourceTreeContextMenu,url:'<c:url value="/Resource/LoadTree" />',onClick:resourceTreeClick" ></ul>
	</div>
	<div data-options="region:'center',split:true" style="width:200px;padding:3px;">
		<table id="resourceLayoutChild" class="easyui-datagrid" data-options="fit:true">
			<thead>
				<tr>
					<th data-options="field:'id'" width="80">ID</th>
					<th data-options="field:'url'" width="150">URL</th>
					<th data-options="field:'name'" width="100">标题</th>
					<th data-options="field:'sequence'" width="80">顺序</th>
					<th data-options="field:'resourceType'" width="150">资源类型</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
<div id="resourceTreeContextMenu" class="easyui-menu" style="width:150px;">
    <div data-options="iconCls:'icon-add'" onclick="addResourceTreeNode(true,'<c:url value="/Resource/Create" />')">添加节点</div>
    <div data-options="iconCls:'icon-add'" onclick="addResourceTreeNode(false,'<c:url value="/Resource/Create" />')">添加子节点</div>
    <div data-options="iconCls:'icon-edit'" onclick="editResourceTreeNode('<c:url value="/Resource/Update" />')">编辑节点</div>
    <div data-options="iconCls:'icon-remove'" onclick="deleteResourceTreeNode('<c:url value="/Resource/Delete" />')">删除节点</div>
</div>
<div id="ResourceTreeWindow" class="easyui-dialog" title="编辑" data-options="iconCls:'icon-edit',closed:true,buttons: [{
		text:'保存',iconCls:'icon-ok',handler:function(){ResourceEditFormsave();}}]" style="width:400px;padding:10px" >
	<form id="ResourceEditForm" method="post" class="easyui-form" action="<c:url value="/Resource/Create" />" >
		<table cellpadding="5" style="width:100%">
			<tr>
				<td width="30%">父节点: </td>
				<td><input class="easyui-textbox" type="text" id="ResourceEditFormParentName" readonly />
					<input class="easyui-textbox" type="hidden" id="ResourceEditFormParentId" name="parent" />
				</td>
			</tr>
			<tr>
				<td width="30%">标题: </td>
				<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" />
					<input type="hidden" name="id" />
				</td>
			</tr>
			<tr>
				<td>URL:</td>
				<td><input class="easyui-textbox" type="text" name="url" /></td>
			</tr>
			<tr>
				<td>排序:</td>
				<td><input class="easyui-textbox" type="text" name="sequence" /></td>
			</tr>
			<tr>
				<td>图标:</td>
				<td><input class="easyui-textbox" type="text" name="iconCls" /></td>
			</tr>
			<tr>
				<td>资源类型:</td>
				<td>
					<t:enumSelectTag id="ResourceEditFormResourceType" name="resourceType" enumClass="cn.mahjong.enums.persist.ResourceType"
					css="easyui-combobox" dataOptions="panelHeight:'auto',required:true,invalidMessage:'请选择类型',editable:false,validType:'comboxValidate(ResourceEditFormResourceType)'" ></t:enumSelectTag>
				</td>
			</tr>
		</table>
	</form>
</div>
<<script type="text/javascript">
	var resourceTreeContentMentNode;
	//点击树节点
	resourceTreeClick = function(node){
		var resultData = new Array();
		$($("#resourceTree").tree("getChildren",node.target)).each(function(index,element){
			if(element.attributes.parentId == node.id){
				var row = new Object();
				row.id = element.id;
				row.name = element.text;
				row.url = element.attributes.url;
				row.sequence = element.attributes.sequence;
				row.resourceType = element.attributes.resourceTypeName;
				resultData.push(row);
			}
		});
		$("#resourceLayoutChild").datagrid("loadData",resultData);
	}
	//右键树节点
	resourceTreeContextMenu = function(e,node){
		e.preventDefault();
		resourceTreeContentMentNode = node;
		$('#resourceTreeContextMenu').menu('show', {    
            left: e.pageX,    
            top: e.pageY
        });
	}
	$("#ResourceEditForm tr").each(function(){
	    $(this).find("td:first").attr('align','right');
	})
	addResourceTreeNode = function(b,url){
		$("#ResourceEditForm").form("clear");
		if(b){
			var parentNode = $("#resourceTree").tree("getParent",resourceTreeContentMentNode.target);
			if(parentNode){
				$("#ResourceEditFormParentName").textbox("setValue",parentNode.text);
				$("#ResourceEditFormParentId").textbox("setValue",parentNode.id);
			}else{
				$("#ResourceEditFormParentName").textbox("setValue","一级菜单");
				$("#ResourceEditFormParentId").textbox("setValue","");
			}
		}else{
			$("#ResourceEditFormParentName").textbox("setValue",resourceTreeContentMentNode.text);
			$("#ResourceEditFormParentId").textbox("setValue",resourceTreeContentMentNode.id);
		}
		$("#ResourceEditForm").form({"url":url});
		$("#ResourceTreeWindow").dialog("open");
	}
	editResourceTreeNode = function(url){
		var data = new Object();
		data.id = resourceTreeContentMentNode.id;
		data.name = resourceTreeContentMentNode.text;
		data.iconCls = resourceTreeContentMentNode.iconCls;
		data.url = resourceTreeContentMentNode.attributes.url;
		data.sequence = resourceTreeContentMentNode.attributes.sequence;
		data.resourceType = resourceTreeContentMentNode.attributes.resourceType;
		$("#ResourceEditForm").form("load",data);
		$("#ResourceEditFormParentId").textbox("setValue",resourceTreeContentMentNode.attributes.parentId);
		$("#ResourceEditFormParentName").textbox("setValue",resourceTreeContentMentNode.attributes.parentName);
		$("#ResourceEditForm").form({"url":url});
		$("#ResourceTreeWindow").dialog("open");
	}
	deleteResourceTreeNode = function(url){
		$.messager.confirm({
			title: '删除提示',
			msg: '确定删除《'+resourceTreeContentMentNode.text+'》吗？',
			fn: function(r){
				if (r){
					$.post(url,{'id':resourceTreeContentMentNode.id},function(data){
						if(data.code == '0'){
							$.messager.alert("提示","删除成功");
							$("#resourceTree").tree("reload");
						}else{
							$.messager.alert("提示",data.msg,"error");
						}
					},'json');
				}
			}
		});
	}
	ResourceEditFormsave = function(){
		$('#ResourceEditForm').form('submit',{
			onSubmit:function(){
				return $(this).form('enableValidation').form('validate');
			},
			success:function(data){
				data = eval('('+data+')');
				if(data.code == "0"){
					$.messager.alert('提示','操作成功。');
					$("#resourceTree").tree("reload");
					$("#ResourceTreeWindow").dialog("close");
				}else{
					$.messager.alert('提示','操作失败：<br />'+data.msg,'error');
				}
		    }
		});
	}
</script>
