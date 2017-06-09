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
    <div data-options="iconCls:'icon-add'" onclick="addResourceTreeNode(false)">添加节点</div>
    <div data-options="iconCls:'icon-add'" onclick="addResourceTreeNode(true)">添加子节点</div>
    <div data-options="iconCls:'icon-edit'" onclick="editResourceTreeNode()">编辑节点</div>
    <div data-options="iconCls:'icon-remove'">删除节点</div>
</div>
<div id="ResourceTreeWindow" class="easyui-dialog" title="编辑" data-options="iconCls:'icon-edit',closed:true,buttons: [{
		text:'保存',iconCls:'icon-ok',handler:function(){ResourceEditFormsave();}}]" style="width:400px;padding:10px" >
	<form id="ResourceEditForm" method="post" class="easyui-form" action="<c:url value="/Resource/Create" />" >
		<table cellpadding="5" style="width:100%">
			<tr>
				<td width="30%">父节点: </td>
				<td><input class="easyui-textbox" type="text" id="ResourceEditFormParentName" value="用户管理" readonly />
					<input type="hidden" name="id" />
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
				<td><input class="easyui-textbox" type="text" name="url" data-options="required:true" /></td>
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
				row.resourceType = element.attributes.resourceType;
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
	addResourceTreeNode = function(b){
		if(b){
			$("#ResourceEditFormParentName").val(resourceTreeContentMentNode.name);
		}else{
			var parentNode = $("#resourceTree").tree("getParent",resourceTreeContentMentNode.target);
			if(parentNode){}
			$("#ResourceEditFormParentName").val(resourceTreeContentMentNode.name);
		}
		//alert(resourceTreeContentMentNode.text);
		$("#ResourceTreeWindow").dialog("open");
	}
</script>
