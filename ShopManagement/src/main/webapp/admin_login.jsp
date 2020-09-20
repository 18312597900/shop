<%@ page language="java" contentType="text/html;charset=utf-8"  pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
    <head>
        <meta charset="UTF-8">
        <link href="css/style.css" rel='stylesheet' type='text/css' />
        <base href="<%=basePath%>">
<title>电子商务平台——后台登录页</title>
 <script src="js/jquery-2.1.0.js"></script>
</head>

<body>


	<div class="main">
		<div class="header" >
			<h1>后台登录！</h1>
		</div>
		<p></p><center>
			<form>
				<ul class="right-form">
					<div>
						<li><input type="text" id="name" placeholder="admin" required/></li>
						<li> <input type="password" id="pwd" placeholder="Password" required/></li>
                        <input type="button" value="注册" onclick="window.location.href='reg.jsp'"><input type="button" id="logbt" value="登录">
					</div>
				</ul>
			</form>
			</center>

		</div>
			<!-----start-copyright---->
   					<div class="copy-right">
						<p>Template 2014. More Templates <a href="http://h2design.taobao.com/" target="_blank" title="氢设计">氢设计</a> </p>
					</div>
				<!-----//end-copyright---->

                	         <script>
                              $(function(){
                                 $("#logbt").click(function(){
                                    var name = $("#name").val();
                                    var pwd = $("#pwd").val();
                                    var user={"name":name,"pwd":pwd};
                                 //   alert(user);
                                   $.ajax({
                                     //请求类型
                                     type:"POST",
                                     //编写json 设置属性 值
                                     //编码类型
                                     contentType:'application/json;charset=UTF-8',
                                     //请求路径
                                     url:"admininfo/login",

                                     //数据
                                     //data:'{"name":"王五","age":"30"}',
                                     //json对象需要 json转化
                                     data:JSON.stringify(user),
                                     //返回数据类型
                                     //dataType:"json",

                                     //处理函数
                                     success:function(data){
                                        //date服务器的json数据
                                        var re=JSON.parse(data);
                                        var result = eval('(' + re + ')');
                                      //  alert("666");
                                    //  $("#message").html(data.message);
                                        if(result.success== "true"){
                                            open("admin.jsp","_self");
                                        }else{
                                            alert("登录失败");
                                        }
                                     },
                                     error:function(){
                                        alert("error");
                                     }

                                 })
                              })
                            });
                         </script>
</body>

</html>