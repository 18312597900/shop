<%@ page language="java" contentType="text/html;charset=utf-8"  pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link rel="stylesheet" href="css\loginstyle.css">
</head>
<body>
    <div class="container">
        <img src="img/bc.jpg" alt="">
        <div class="panel">
            <div class="content login">
                <div class="switch">
                    <span id='login' class='active'>Login</span>
                </div>
                <form action="">
                    <div id='email' class="input"  placeholder='Email'><input type="text"></div>
                    <div class="input" placeholder='Username'><input type="text" id="users"></div>
                    <div class="input" placeholder='Password'><input type="password" id="passwords"></div>
                    <div id='repeat' class="input" placeholder='Repeat'><input type="password"></div>
                    <span>Forget?</span>
                    <button id="Ulogin">LOGIN</button>
                </form>

            </div>
        </div>
    </div>
</body>

<script src="js/jquery-2.1.0.js"></script>
<script src="js/vue.js"></script>
<script>

            $(function(){
                 $("#Ulogin").click(function(){
                    var name = $("#users").val();
                    var pwd = $("#passwords").val();
                    var user={"name":name,"pwd":pwd};
              //      alert(JSON.stringify(user));
                   $.ajax({
                     //编写json 设置属性 值
                     //请求路径
                     url:"admininfo/userlogin",
                     //编码类型
                     contentType:'application/json;charset=UTF-8',
                     //数据
                     //data:'{"name":"王五","age":"30"}',
                     //json对象需要 json转化
                     data:JSON.stringify(user),
                     //返回数据类型
                     //dataType:"json",
                     //请求类型
                     type:"POST",
                     //处理函数
                     success:function(data){
                        //date服务器的json数据
                        var re=JSON.parse(data);
                        var result = eval('(' + re + ')');
                   //     alert(data.success);
                    //  $("#message").html(data.message);
                        if(result.success== "true"){
                            localStorage.usernames=user.name;
                            open("index.jsp","_self");

                        }else{
                            localStorage.usernames="1";
                            alert("登录失败");
                        }
                     },
                     error:function(){
                        localStorage.usernames="1";
                        alert("error");
                     }
                 })
              })
            });

    $('.input input').on('focus',function(){
        $(this).parent().addClass('focus');
    })

    $('.input input').on('blur',function(){
        if($(this).val() === '')
            $(this).parent().removeClass('focus');
    })
</script>
</html>