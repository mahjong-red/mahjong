<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
	<script type="text/javascript">
	$(function() {
		$('#texttemplatelist').datagrid({
			idField: 'id',
			url: '${ctx}/User/Find',
			fit: true,
			pagination: true,
			rownumbers: true,
			singleSelect: false,
			fitColumns: true,
			showFooter: true,
			frozenColumns: [[{
				field: 'id',
				width: 50,
				title: '编号'
			},
			]],
			columns: [[
			{
				field: 'username',
				title: '用户名',
				width: 180
			},
			{
				field: 'sex',
				title: '文本内容',
				width: 300
			},
			{
				field: 'createTime',
				title: '时间',
				width: 100
			},
			{
				field: 'opt',
				title: '操作',
				formatter: function(value, rec, index) {
					if (!rec.id) {
						return '';
					}
					return "[<a href='#' onclick=delObj('textTemplateController.do?del&id=" + rec.id + "','texttemplatelist')>删除</a>]";
				},
				width: 100
			}]],
			onLoadSuccess: function(data) {
				$("#texttemplatelist").datagrid("clearSelections");
			},
			onClickRow: function(rowIndex, rowData) {
				rowid = rowData.id;
				gridname = 'texttemplatelist';
			}
		});
		$('#texttemplatelist').datagrid('getPager').pagination({
			onBeforeRefresh: function(pageNumber, pageSize) {
				$(this).pagination('loading');
				$(this).pagination('loaded');
			}
		});
	});
		function reloadTable() {
			try {
				$('#' + gridname).datagrid('reload');
				$('#' + gridname).treegrid('reload');
			} catch (ex) {
			}
		}
		function reloadtexttemplatelist() {
			$('#texttemplatelist').datagrid('reload');
		}
		function gettexttemplatelistSelected(field) {
			return getSelected(field);
		}
		function getSelected(field) {
			var row = $('#' + gridname).datagrid('getSelected');
			if (row != null) {
				value = row[field];
			} else {
				value = '';
			}
			return value;
		}
		function gettexttemplatelistSelections(field) {
			var ids = [];
			var rows = $('#texttemplatelist').datagrid('getSelections');
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i][field]);
			}
			ids.join(',');
			return ids
		};
		function getSelectRows() {
			return $('#texttemplatelist').datagrid('getChecked');
		}
		function texttemplatelistsearch() {
			var queryParams = $('#texttemplatelist').datagrid('options').queryParams;
			$('#texttemplatelisttb').find('*').each(function() {
				queryParams[$(this).attr('name')] = $(this).val();
			});
			$('#texttemplatelist')
					.datagrid(
							{
								url : '${ctx}/User/Find',
								pageNumber : 1
							});
		}
		function dosearch(params) {
			var jsonparams = $.parseJSON(params);
			$('#texttemplatelist')
					.datagrid(
							{
								url : '${ctx}/User/Find',
								queryParams : jsonparams
							});
		}
		function texttemplatelistsearchbox(value, name) {
			var queryParams = $('#texttemplatelist').datagrid('options').queryParams;
			queryParams[name] = value;
			queryParams.searchfield = name;
			$('#texttemplatelist').datagrid('reload');
		}
		$('#texttemplatelistsearchbox').searchbox({
			searcher : function(value, name) {
				texttemplatelistsearchbox(value, name);
			},
			menu : '#texttemplatelistmm',
			prompt : '请输入查询关键字'
		});
		function EnterPress(e) {
			var e = e || window.event;
			if (e.keyCode == 13) {
				texttemplatelistsearch();
			}
		}
		function searchReset(name) {
			$("#" + name + "tb").find(":input").val("");
			texttemplatelistsearch();
		}
	</script>
	<table width="100%" id="texttemplatelist" toolbar="#texttemplatelisttb"></table>
	<div id="texttemplatelisttb" style="padding: 3px; height: auto">
		<div name="searchColums">
			<span style="display: -moz-inline-box; display: inline-block;"><span
				style="vertical-align: middle; display: -moz-inline-box; display: inline-block; width: 80px; text-align: right; text-overflow: ellipsis; -o-text-overflow: ellipsis; overflow: hidden; white-space: nowrap;"
				title="文本名称">文本名称：</span><input onkeypress="EnterPress(event)"
				onkeydown="EnterPress()" type="text" name="templateName"
				style="width: 100px" /></span>
				<span
				style="float: "><a href="#" class="easyui-linkbutton"
				iconCls="icon-search" onclick="texttemplatelistsearch()">查询</a><a
				href="#" class="easyui-linkbutton" iconCls="icon-reload"
				onclick="searchReset('texttemplatelist')">重置</a></span>
		</div>
		<div style="height: 30px;" class="datagrid-toolbar">
			<span style="float: left;"><a href="#"
				class="easyui-linkbutton" plain="true" icon="icon-add"
				onclick="add('文本录入','textTemplateController.do?addorUpdate','texttemplatelist',null,null)">文本录入</a><a
				href="#" class="easyui-linkbutton" plain="true" icon="icon-edit"
				onclick="update('文本编辑','textTemplateController.do?addorUpdate','texttemplatelist',null,null)">文本编辑</a><a
				href="#" class="easyui-linkbutton" plain="true" icon="icon-remove"
				onclick="deleteALLSelect('批量删除','textTemplateController.do?doBatchDel','texttemplatelist',null,null)">批量删除</a></span>
		</div>
</body>
</html>