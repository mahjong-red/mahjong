<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>main</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/js/jquery-easyui-1.5.2/themes/default/easyui.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/main/wu.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/main/icon.css" />" />

<script type="text/javascript" src="<c:url value="/static/js/jquery-1.8.0.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery-easyui-1.5.2/jquery.easyui.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/static/js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js" />"></script>
</head>

<body class="easyui-layout">
	<!-- begin of header -->
	<div class="wu-header"
		data-options="region:'north',border:false,split:true">
		<div class="wu-header-left">
			<h1>EasyUI Web Admin</h1>
		</div>
		<div class="wu-header-right">
			<p>
				<strong>admin</strong>，欢迎您！
			</p>
			<p>
				<a href="#">网站首页</a>|<a href="#">支持论坛</a>|<a href="#">帮助中心</a>|<a
					href="#">安全退出</a>
			</p>
		</div>
	</div>
	<!-- end of header -->
	<!-- begin of sidebar -->
	<div class="wu-sidebar"
		data-options="region:'west',split:true,border:true,title:'导航菜单'">
		<div class="easyui-accordion" data-options="border:false,fit:true">
			<c:forEach items="${resources }" var="item">
				<div title="${item.name }" data-options="iconCls:'${item.iconCls }'"
					style="padding: 5px;">
					<ul class="easyui-tree wu-side-tree">
						<c:forEach items="${item.children }" var="childrenItem">
							<li iconCls="${childrenItem.iconCls }"><a
								href="javascript:void(0)" data-icon="${childrenItem.iconCls }"
								data-link="${ctx }${childrenItem.url }" iframe="0">${childrenItem.name }</a></li>

						</c:forEach>
					</ul>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- end of sidebar -->
	<!-- begin of main -->
	<div class="wu-main" data-options="region:'center'">
		<div id="wu-tabs" class="easyui-tabs"
			data-options="border:false,fit:true">
			<div title="首页"
				data-options="href:'${ctx }/static/index.jsp',closable:false,iconCls:'icon-tip',cls:'pd3'"></div>
		</div>
	</div>
	<!-- end of main -->
	<!-- Tab右键功能菜单初始化 -->
	<div id="tabMenu" class="easyui-menu" style="width: 150px;">
		<div id="tabMenu-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="tabMenu-tabclose">关闭</div>
		<div id="tabMenu-tabcloseall">全部关闭</div>
		<div id="tabMenu-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="tabMenu-tabcloseright">当前页右侧全部关闭</div>
		<div id="tabMenu-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="tabMenu-exit">退出</div>
	</div>
	<!-- begin of footer -->
	<div class="wu-footer"
		data-options="region:'south',border:true,split:true">&copy; 2017
		mahjong All Rights Reserved</div>
	<!-- end of footer -->
	<script type="text/javascript">
		$(function() {
			$('.wu-side-tree a').bind("click", function() {
				var title = $(this).text();
				var url = $(this).attr('data-link');
				var iconCls = $(this).attr('data-icon');
				var iframe = $(this).attr('iframe') == 1 ? true : false;
				addTab(title, url, iconCls, iframe);
			});

			// 刷新
			$('#tabMenu-tabupdate').click(function() {
				var currTab = $('#tabs').tabs('getSelected');
				var url = $(currTab.panel('options').content).attr('src');
				$('#tabs').tabs('update', {
					tab : currTab,
					options : {
						content : createFrame(url)
					}
				});
			});
			// 关闭当前
			$('#tabMenu-tabclose').click(function() {
				var currtab_title = $('#tabMenu').data("currtab");
				$('#tabs').tabs('close', currtab_title);
			});
			// 全部关闭
			$('#tabMenu-tabcloseall').click(function() {
				$('.tabs-inner span').each(function(i, n) {
					var t = $(n).text();
					$('#tabs').tabs('close', t);
				});
			});
			// 关闭除当前之外的TAB
			$('#tabMenu-tabcloseother').click(function() {
				$('#tabMenu-tabcloseright').click();
				$('#tabMenu-tabcloseleft').click();
			});
			// 关闭当前右侧的TAB
			$('#tabMenu-tabcloseright').click(function() {
				var nextall = $('.tabs-selected').nextAll();
				if (nextall.length == 0) {
					msgShow('系统提示', '后边没有啦~~', 'error');
					//alert('后边没有啦~~');
					return false;
				}
				nextall.each(function(i, n) {
					var t = $('a:eq(0) span', $(n)).text();
					$('#tabs').tabs('close', t);
				});
				return false;
			});
			// 关闭当前左侧的TAB
			$('#tabMenu-tabcloseleft').click(function() {
				var prevall = $('.tabs-selected').prevAll();
				if (prevall.length == 0) {
					alert('到头了，前边没有啦~~');
					return false;
				}
				prevall.each(function(i, n) {
					var t = $('a:eq(0) span', $(n)).text();
					$('#tabs').tabs('close', t);
				});
				return false;
			});

			// 退出
			$("#tabMenu-exit").click(function() {
				$('#tabMenu').menu('hide');
			});
			
			$(".tabs-inner").bind('contextmenu', function(e) {
				$('#tabMenu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
				var subtitle = $(this).children(".tabs-closable").text();

				$('#tabMenu').data("currtab", subtitle);
				$('#tabs').tabs('select', subtitle);
				return false;
			});
		})

		/**
		 * Name 添加菜单选项
		 * Param title 名称
		 * Param href 链接
		 * Param iconCls 图标样式
		 * Param iframe 链接跳转方式（true为iframe，false为href）
		 */
		function addTab(title, href, iconCls, iframe) {
			var tabPanel = $('#wu-tabs');
			if (!tabPanel.tabs('exists', title)) {
				var content = '<iframe scrolling="auto" frameborder="0"  src="'
						+ href + '" style="width:100%;height:100%;"></iframe>';
				if (iframe) {
					tabPanel.tabs('add', {
						title : title,
						content : content,
						iconCls : iconCls,
						fit : true,
						cls : 'pd3',
						closable : true
					});
				} else {
					tabPanel.tabs('add', {
						title : title,
						href : href,
						iconCls : iconCls,
						fit : true,
						cls : 'pd3',
						closable : true
					});
				}
			} else {
				tabPanel.tabs('select', title);
			}
		}
	</script>
</body>
</html>