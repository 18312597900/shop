<%@ page language="java" contentType="text/html;charset=utf-8"  pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
    <head>
        <meta charset="UTF-8">
        <link href="css/indexstyle.css" rel='stylesheet' type='text/css' />
        <base href="<%=basePath%>">
         <script src="js/jquery-2.1.0.js"></script>
<title>HOME</title>

</head>
<body>
<h1>Flat Fashion Widget </h1>
<div id="app">
    <div id="su" v-if="username != '1' && username != null ">
        <h>你好：</h><span id="showusername"></span><h>。。。。。</h>
        <br/><br/> <button onclick="clearuser()">注销</button>
    </div>

    <div v-else>
        <input type="button" id="yhbt" value="用户登录"onclick="window.location.href='userlogin.jsp'">
        <br/><br/><input type="button" id="htbt" value="后台登录" onclick="window.location.href='admin_login.jsp'">
    </div>

</div>
<div class="main">
    <div class="bg-left">
    </div>
    <div class="slide">
        <div class="banner-info">
            <div class="cont1 span_2_of_a1 simpleCart_shelfItem">
                <h4>Lorem Ipsum</h4>
                <img src="images/5.png" alt=""/>
                <ul class="rating">
                    <li><a class="product-rate" href="#"> <label> </label></a> <span> </span></li>
                    <li><a href="#">1 Review(s) Add Review</a></li>
                </ul>
                <div class="clear"></div>
                <div class="price_single">
                    <span class="reducedfrom">$140.00</span>
                    <span class="amount item_price actual">$120.00</span><a href="#">click for offer</a>
                </div>
                <ul class="size">
                    <h3>Size</h3>
                    <li><a href="#">6 x 7</a></li>
                    <li><a href="#">6.5 x 7</a></li>
                    <li><a href="#">7 x 7</a></li>
                    <li><a href="#">7.7 x 9</a></li>
                </ul>
                <div class="btn_form button item_add item_1">
                    <form>
                        <input type="submit" value="Add to Cart" title="">
                    </form>
                </div>
            </div>

        </div>


    </div>

    <div class="clear"></div>
    <div class="bottom">
        <ul id="flexiselDemo3">
            <li>
                <div class="biseller-column">
                    <a class="lightbox" href="#one">
                        <img src="images/1.jpg" alt=""/>
                    </a>
                    <div class="lightbox-target" id="one">
                        <img src="images/1.jpg" alt=""/>
                        <a class="lightbox-close" href="#"> </a>

                        <div class="clear"> </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="biseller-column">
                    <a class="lightbox" href="#two">
                        <img src="images/3.jpg" alt=""/>
                    </a>
                    <div class="lightbox-target" id="two">
                        <img src="images/3.jpg" alt=""/>
                        <a class="lightbox-close" href="#"> </a>

                        <div class="clear"> </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="biseller-column">
                    <a class="lightbox" href="#four">
                        <img src="images/2.jpg" alt=""/>
                    </a>
                    <div class="lightbox-target" id="four">
                        <img src="images/2.jpg" alt=""/>
                        <a class="lightbox-close" href="#"> </a>

                        <div class="clear"> </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="biseller-column">
                    <a class="lightbox" href="#five">
                        <img src="images/5.jpg" alt=""/>
                    </a>
                    <div class="lightbox-target" id="five">
                        <img src="images/5.jpg" alt=""/>
                        <a class="lightbox-close" href="#"> </a>

                        <div class="clear"> </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="biseller-column">
                    <a class="lightbox" href="#six">
                        <img src="images/6.jpg" alt=""/>
                    </a>
                    <div class="lightbox-target" id="six">
                        <img src="images/6.jpg" alt=""/>
                        <a class="lightbox-close" href="#"> </a>

                        <div class="clear"> </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="biseller-column">
                    <a class="lightbox" href="#seven">
                        <img src="images/7.jpg" alt=""/>
                    </a>
                    <div class="lightbox-target" id="seven">
                        <img src="images/7.jpg" alt=""/>
                        <a class="lightbox-close" href="#"> </a>

                        <div class="clear"> </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="biseller-column">
                    <a class="lightbox" href="#night">
                        <img src="images/8.jpg" alt=""/>
                    </a>
                    <div class="lightbox-target" id="night">
                        <img src="images/8.jpg" alt=""/>
                        <a class="lightbox-close" href="#"> </a>

                        <div class="clear"> </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="biseller-column">
                    <a class="lightbox" href="#ten">
                        <img src="images/9.jpg" alt=""/>
                    </a>
                    <div class="lightbox-target" id="ten">
                        <img src="images/9.jpg" alt=""/>
                        <a class="lightbox-close" href="#"> </a>

                        <div class="clear"> </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>

    <script src="js/vue.js"></script>
    <script type="text/javascript">
        document.getElementById("showusername").innerHTML=localStorage.usernames;
        new Vue({
            el:"#app",
            data:{
                username: localStorage.usernames,
            }
        })

        function clearuser(){
            localStorage.usernames="1";
            location.reload();
        }

        $(window).load(function() {
            $("#flexiselDemo3").flexisel({
                visibleItems:6,
                animationSpeed: 1000,
                autoPlay: true,
                autoPlaySpeed: 3000,
                pauseOnHover: true,
                enableResponsiveBreakpoints: true,
                responsiveBreakpoints: {
                    portrait: {
                        changePoint:480,
                        visibleItems:3
                    },
                    landscape: {
                        changePoint:640,
                        visibleItems:3
                    },
                    tablet: {
                        changePoint:768,
                        visibleItems:4
                    }
                }
            });

        });
    </script>
    <script type="text/javascript" src="js/jquery.flexisel.js"></script>
</div>
</div>
<div class="clear"></div>
<!--End Calender-->
<div class="copy-right">
    <p>Copyright &copy; 2015.Company name All rights reserved.<a target="_blank" href="http://h2design.taobao.com/">氢设计</a></p>
</div>
</body>
</html>