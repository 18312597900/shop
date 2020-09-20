<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"  pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	if (session.getAttribute("admin") == null)
		response.sendRedirect("/ecpbm/admin_login.jsp");
%>
<html>
    <head>
        <meta charset="UTF-8">
        <base href="<%=basePath%>">

<title>My JSP 'newslist.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
    <!-- 创建一个table -->
	<table id="userListDg" class="easyui-datagrid"></table>
	
	<!-- 创建工具栏 -->
	<div id="userListTb" style="padding:2px 5px;"><a href="javascript:void(0)"
		class="easyui-linkbutton" iconCls="icon-edit" plain="true"
		onclick="SetIsEnableUser(1);">启用客户</a> <a href="javascript:void(0)"
		class="easyui-linkbutton" iconCls="icon-remove"
		onclick="SetIsEnableUser(0);" plain="true">禁用客户</a>

		<a href="javascript:void(0)" class="easyui-linkbutton"
		   iconCls="icon-add" plain="true" onclick="adduser();">添加</a>
	</div>
	
	<!-- 创建搜索栏 -->
	<div id="searchUserListTb" style="padding:4px 3px;">
		<form id="searchUserListForm">
			<div style="padding:3px ">
				客户名称&nbsp;&nbsp;<input class="easyui-textbox" name="search_userName"
					id="search_userName" style="width:110px" /><a href="javascript:void(0)"
					class="easyui-linkbutton" iconCls="icon-search" plain="true"
					onclick="searchUserInfo();">查找</a>
			</div>			
		</form>
	</div>
<%--打开添加用户对话框--%>
	<div id="dlg_userinfo" class="easyui-dialog" title="添加用户"
		 closed="true" style="width: 500px;">
		<div style="padding: 10px 60px 20px 60px">
			<form id="ff_userinfo" method="POST" action=""
				  enctype="multipart/form-data">
				<table cellpadding="5">
					<tr>
						<td>用户状态:</td>
						<td>
							<select id="status" class="easyui-combobox" name="status"
									style="width: 150px;">
								<option value="1">启用</option>
								<option value="0">禁用</option>
							</select></td>
					</tr>
					<tr>
						<td>用户名称:</td>
						<td>
							<input class="easyui-textbox" type="text" id="userName"
								   name="userName" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>用户密码:</td>
						<td><input class="easyui-textbox" type="text" id="password"
								   name="password" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>真实姓名:</td>
						<td><input class="easyui-textbox" type="text" id="realName"
								   name="realName" data-options="required:true"></input></td>
					</tr>

					<tr>
						<td>性别:</td>
						<td>
							<select id="sex" class="easyui-combobox" name="sex"
									style="width: 150px;">
								<option value="男">男</option>
								<option value="女">女</option>
							</select></td>
					</tr>
					<tr>
						<td>用户地址:</td>
						<td><input class="easyui-textbox" type="text" id="address"
								   name="address" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input class="easyui-textbox" type="text" id="email"
								   name="email" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>创建日期:</td>
						<td>
							<input type="text" name="regDate"
								   id="regDate" class="easyui-datebox" style="width: 115px"
								   value="<%=new Date().toLocaleString()%>" />
						</td>

					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton"
				   onclick="saveUser();">保存</a> <a href="javascript:void(0)"
													  class="easyui-linkbutton" onclick="clearForm();">清空</a>
			</div>
		</div>
	</div>



	<script type="text/javascript">
		$(function() {
			$('#userListDg').datagrid({
				singleSelect : false,
				url : 'userinfo/list',
				queryParams : {}, //查询条件
				pagination : true, //启用分页
				pageSize : 5, //设置初始每页记录数（页大小）
				pageList : [ 5, 10, 15 ], //设置可供选择的页大小
				rownumbers : true, //显示行号
				fit : true, //设置自适应
				toolbar : '#userListTb', //为datagrid添加工具栏
				header : '#searchUserListTb', //为datagrid标头添加搜索栏
				columns : [ [ { //编辑datagrid的列
					title : '序号',
					field : 'id',
					align : 'center',
					checkbox : true
				}, {
					field : 'userName',
					title : '登录名',					
					width : 100
				}, {
					field : 'realName',
					title : '真实姓名',
					width : 80
				}, {
					field : 'sex',
					title : '性别',
					width : 100
				}, {
					field : 'address',
					title : '住址',
					width : 200
				} , {
					field : 'email',
					title : '邮箱',
					width : 150
				} , {
					field : 'regDate',
					title : '注册日期',
					width : 100
				} , {
					field : 'status',
					title : '客户状态',
					width : 100,
					formatter : function(value, row, index) {
						if (row.status==1) {
							return "启用";
						} else {
							return "禁用";
						}
					}
				} ] ]
			});
		});

		var urls;
		var data;

		// 打开新增商品对话框
		function adduser() {
			$('#dlg_userinfo').dialog('open').dialog('setTitle', '新增用户');
			$('#dlg_userinfo').form('clear');
			urls = 'userinfo/adduser';
		}
		// 清除新增商品对话框信息
		function clearForm() {
			$('#dlg_userinfo').form('clear');
		}
		// 保存用户信息
		function saveUser() {
			$("#ff_userinfo").form("submit", {
				url : urls, //使用参数
				success : function(result) {
					var result = eval('(' + result + ')');
					if (result.success == 'true') {
						$("#userListDg").datagrid("reload");

					}
					$("#dlg_userinfo").dialog("close");
					$.messager.show({
						title : "提示信息",
						msg : result.message
					});
				}
			});
		}
		function searchUserInfo() {
         			var userName = $('#search_userName').textbox("getValue");
         			$('#userListDg').datagrid('load', {
                        userName : userName
                    });
         		}
		
		
		// 设置启用或禁用客户
		function SetIsEnableUser(flag) {
			var rows = $("#userListDg").datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('Confirm', '确认要设置么?', function(r) {
					if (r) {
						var uids = "";
						for (var i = 0; i < rows.length; i++) {
							uids += rows[i].id + ",";
						}						
						$.post('userinfo/setIsEnableUser', {
							uids : uids,
							flag : flag
						}, function(result) {
							if (result.success == 'true') {
								$("#userListDg").datagrid('reload'); 
								$.messager.show({
									title : '提示信息',
									msg : result.message
								});
							} else {
								$.messager.show({
									title : '提示信息',
									msg : result.message
								});
							}
						}, 'json');

					}
				});
			} else {
				$.messager.alert('提示', '请选择要启用或禁用的客户', 'info');
			}
		}

	</script>
</body>
</html>
