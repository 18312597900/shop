<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	if (session.getAttribute("admin") == null)
		response.sendRedirect("/ecpbm_test/admin_login.jsp");
%>

<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'createorder.jsp' starting page</title>

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

	<!-- 定义table,用于创建easy ui的datagrid控件 -->
	<table id="usbox"></table>

	<!-- 创建datagrid控件的工具栏 -->
	<div id="ordertb" style="padding: 2px 5px;">
		<a href="javascript:void(0)" class="easyui-linkbutton"
		   iconCls="icon-add" plain="true" onclick="adduser();">添加用户</a><a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-save" plain="true" onclick="saveuser();">保存用户</a><a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="removeuser();">删除用户</a>
	</div>

	<!-- 创建订单主表录入布局 -->
	<div id="divOrderInfo">
		<div style="padding: 3px">
			用户名称 <input type="text" name="create_username"
							 id="create_username" class="easyui-textbox"
							 style="width: 115px" /> &nbsp;&nbsp;
			密码 <input type="text" name="create_password"
				id="create_password" class="easyui-textbox"
				style="width: 115px" />

			真实姓名 <input type="text" name="create_realName"
					  id="create_realName" class="easyui-textbox"
					  style="width: 115px" />
			Email <input type="text" name="create_email"
						id="create_email" class="easyui-textbox"
						style="width: 115px" />
			地址 <input type="text" name="create_address"
					  id="create_address" class="easyui-textbox"
					  style="width: 115px" />
			&nbsp;
		</div>
		<div style="padding: 3px">
			创建日期&nbsp;<input type="text" name="create_regDate"
				id="create_regDate" class="easyui-datebox" style="width: 115px"
				value="<%=new Date().toLocaleString()%>" /> &nbsp;&nbsp;
			用户状态&nbsp;<select id="create_status" class="easyui-combobox"
				name="create_status" style="width: 115px;">
				<option value="未付款" selected>未禁用</option>
				<option value="已付款">已禁用</option>
			</select>
		</div>
	</div>

<script type="text/javascript">	      
	var $usbox = $('#usbox');
	$(function() {
		$usbox.datagrid({
			rownumbers : true,
			singleSelect : false,
			fit : true,
			toolbar : '#ordertb',
			header : '#divOrderInfo',
			columns : [ [ {
				title : '序号',
				field : '',
				align : 'center',
				checkbox : true
			}, {
				field : 'pid',
				title : '用户名称',
				width : 300,
				editor : {
					type : 'combobox',
					options : {
						valueField : 'id',
						textField : 'name',
						url : 'productinfo/getOnSaleProduct',
						onChange: function (newValue, oldValue) {
	                       var rows = $usbox.datagrid('getRows');
	                       var orderprice=0;
	                       for (var i = 0; i < rows.length; i++) {
	                           var pidEd = $('#usbox').datagrid('getEditor', {
	                               index: i,
	                               field: 'pid'
	                           });
	                           var priceEd = $('#usbox').datagrid('getEditor', {
	                               index: i,
	                               field: 'price'
	                           });
	                           var totalpriceEd = $('#usbox').datagrid('getEditor', {
	                               index: i,
	                               field: 'totalprice'
	                           });
	                           var numEd = $('#usbox').datagrid('getEditor', {
	                               index: i,
	                               field: 'num'
	                           });
	                           if (pidEd != null){
	                           	var pid=$(pidEd.target).combobox('getValue');
	                           	$.ajax({
								  type: 'POST',
								  url: 'productinfo/getPriceById',
								  data: {pid : pid},
								  success:  function(result) {
										$(priceEd.target).numberbox('setValue',result);
										$(totalpriceEd.target).numberbox('setValue',result * $(numEd.target).numberbox('getValue'));
										orderprice=Number(orderprice)+Number($(totalpriceEd.target).numberbox('getValue'));
								  },
								  dataType: 'json',
								  async : false
								});
	                          }
	                       }
	                       $("#create_orderprice").textbox("setValue",orderprice);
	                    }
					}
				}
			}, {
				field : 'price',
				title : '单价',
				width : 80,
				editor: {
					type : "numberbox",
					options: {
						editable : false
					}
				}
			} , {
				field : 'num',
				title : '数量',
				width : 50,
				editor : {
					type : 'numberbox',
					options :{
						onChange: function (newValue, oldValue) {
	                        var rows = $usbox.datagrid('getRows');
	                        var orderprice=0;
	                        for (var i = 0; i < rows.length; i++) {
	                            var priceEd = $('#usbox').datagrid('getEditor', {
	                                index: i,
	                                field: 'price'
	                            });
	                            var totalpriceEd = $('#usbox').datagrid('getEditor', {
	                                index: i,
	                                field: 'totalprice'
	                            });
	                            var numEd = $('#usbox').datagrid('getEditor', {
	                                index: i,
	                                field: 'num'
	                            });
	                            $(totalpriceEd.target).numberbox('setValue',$(priceEd.target).numberbox('getValue') * $(numEd.target).numberbox('getValue'));
	                            orderprice=Number(orderprice)+Number($(totalpriceEd.target).numberbox('getValue'));
	                        }
	                        $("#create_orderprice").textbox("setValue",orderprice);
						}
					}
	             }
			}, {
				field : 'totalprice',
				title : '小计',
				width : 100,
				editor: {
					type : "numberbox",
					options: {
						editable : false
					}
				}
			}  ] ]
		});
	});

		// datagrid中添加记录行
		function adduser() {
			$usbox.datagrid('appendRow', {
				num : '1',
				price : '0',
				totalprice : '0'
			});
			var rows = $usbox.datagrid('getRows');
			// 让添加的行处于可编辑状态
			$usbox.datagrid('beginEdit', rows.length - 1);
		}

		// datagrid中删除记录行
		function removeuser() {
		    // 获取所选择的行记录
			var rows = $usbox.datagrid('getSelections');
			if (rows.length > 0) {
				// 获取“订单金额”文本域的值
				var create_orderprice =  $("#create_orderprice").textbox("getValue");
				// 遍历选中的行记录，以更新订单金额
				for (var i = 0; i < rows.length; i++) {
					var index = $usbox.datagrid('getRowIndex', rows[i]);
					var totalpriceEd = $('#usbox').datagrid('getEditor', {
                          index: index,
                          field: 'totalprice'
		             });
					create_orderprice = create_orderprice - Number($(totalpriceEd.target).numberbox('getValue'));
					$usbox.datagrid('deleteRow', index);
				}
				$("#create_orderprice").textbox("setValue",create_orderprice);
			} else {
				$.messager.alert('提示', '请选择要删除的行', 'info');
			}
		}

		// 保存订单
		function saveuser() {
		    // 获取订单客户
			var uid = $("#create_uid").combobox("getValue");
			if(uid==0){
				$.messager.alert('提示', '请选择客户名称', 'info');
			} else {
				// 取消datagrid控件的行编辑状态
				create_endEdit();
				// 定义orderinfo存放订单主表数据
				var orderinfo = [];
				// 获取订单时间
				var ordertime = $("#create_ordertime").datebox("getValue");
				// 获取订单状态
				var status = $("#create_status").combobox("getValue");
				// 获取订单金额
				var orderprice = $("#create_orderprice").textbox("getValue");
				orderinfo.push({
					ordertime : ordertime,
					uid : uid,
					status : status,
					orderprice : orderprice
				});
				// 获取订单明细（即datagrid控件中的记录）
				if ($usbox.datagrid('getChanges').length) {
					// 获取datagrid控件中插入的记录行
					var inserted = $usbox.datagrid('getChanges', "inserted");
					// 获取datagrid控件中删除的记录行
					var deleted = $usbox.datagrid('getChanges', "deleted");
					// 获取datagrid控件中更新的记录行
					var updated = $usbox.datagrid('getChanges', "updated");
					// 定义effectRow,保存inserted和orderinfo
					var effectRow = new Object();
					if (inserted.length) {
						effectRow["inserted"] = JSON.stringify(inserted);
					}
					effectRow["orderinfo"] = JSON.stringify(orderinfo);
					// 提交请求
					$.post(
					"orderinfo/commitOrder",
					effectRow,
					function(data) {
						if (data == 'success') {
							$.messager.alert("提示", "创建成功！");
							$usbox.datagrid('acceptChanges');
							if ($('#tabs').tabs('exists', '创建订单')) {
								$('#tabs').tabs('close', '创建订单');
							}
							$("#orderDg").datagrid('reload');
						} else {
							$.messager.alert("提示", "创建失败！");
						}
					});
				}
			}

		}

		// 取消datagrid控件的行编辑状态
		function create_endEdit() {
			var rows = $usbox.datagrid('getRows');
			for (var i = 0; i < rows.length; i++) {
				$usbox.datagrid('endEdit', i);
			}
		}
	</script>

</body>
</html>
