<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en" class="no-js">
<head>
    <title>Eternal gravitational Back Stage Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Styles -->
    <link href="${pageContext.request.contextPath}/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/animate.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/style.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/style-responsive.min.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/css/theme/default.css" rel="stylesheet" id="theme" />
    <link href="${pageContext.request.contextPath}/plugins/layer/skin/layer.css" rel="stylesheet" id="theme" />
    <!-- ================== END BASE CSS STYLE ================== -->

	<script type="text/javascript">
	var basePath="${basePath}";
	</script>

    <script src="${pageContext.request.contextPath}/plugins/jquery/js/jquery-2.2.4.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/plugins/jquery/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/plugins/jquery-cookie/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/plugins/jquery-storageapi/jquery.storageapi.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/plugins/jquery/js/messages_zh.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/plugins/jquery/js/jquery-migrate-1.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/base/apps.min.js"></script>
   <%-- <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>

    <!-- Bootstrap -->

    <script src="${pageContext.request.contextPath}/js/base/apps.min.js"></script>
    <!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath}/crossbrowserjs/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath}/crossbrowserjs/respond.min.js"></script>
    <script src="${pageContext.request.contextPath}/crossbrowserjs/excanvas.min.js"></script>
    <![endif]-->
    <script src="${pageContext.request.contextPath}/plugins/jquery/js/jquery-migrate-1.1.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jquery-ui/ui/minified/jquery-ui.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/slimscroll/jquery.slimscroll.min.js"></script>--%>
    <script src="${pageContext.request.contextPath}/plugins/layer/layer.js"></script>


    <link href="${pageContext.request.contextPath}/css/preview/login.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-progressbar/js/bootstrap-progressbar.js"></script>
</head>
<body>

<div class="logo">
    <h4><a href="#"><img src="${pageContext.request.contextPath}/images/base/logoed.png" alt="" style="width:65%"></a></h4>
</div>
<div class="lock-holder">
    <div class="form-group pull-left input-username">
        <div class="input-group">
            <input type="text" class="form-control" id="loginName" value="admin"  placeholder="admin">
            <span class="input-group-addon"><i class="fa fa-user"></i></span>
        </div>
    </div>
    <i class="fa fa-ellipsis-h dot-left"></i>
    <i class="fa fa-ellipsis-h dot-right"></i>
    <div class="form-group pull-right input-password">
        <div class="input-group">
            <input type="password" class="form-control " id="password" value="123456" placeholder="************">
            <span class="input-group-addon"><i class="fa fa-key"></i></span>
        </div>
    </div>
</div>
<div class="avatar">
    <img src="${pageContext.request.contextPath}/images/base/defaultHeadPortrait.jpg" alt="">
</div>
<div class="submit">
    <button type="button" class="btn btn-success btn-submit"><i class="fa fa-unlock"></i> ENTER</button>
</div>


<script type="text/javascript">

    $(document).ready(function(){
       /* // 当遇到 401 状态码时，清空 Access_Cookie 中的 ACCESS_TOKEN，并跳转到登录页面
        $.ajaxSetup({
            statusCode: {
                401: function () {
                    $.removeCookie(Access_Cookie.ACCESS_TOKEN);
                    location.href = 'index.jsp';
                }
            }
        });
        // 当遇到 403 状态码时，清空 Access_Cookie 中的 MAIN_TOKEN，并跳转到登录页面
        $.ajaxSetup({
            statusCode: {
                403: function () {
                    $.removeCookie(Access_Cookie.MAIN_TOKEN);
                    location.href = 'index.jsp';
                }
            }
        });


        // 当发送 ajax 请求开始时，将 cookie 中的 token 与 username 放入 request header 中
        $(document).ajaxSend(function (event, xhr) {
            xhr.setRequestHeader(RequestHeader.MAIN_TOKEN, $.cookie(Access_Cookie.MAIN_TOKEN));
            xhr.setRequestHeader(RequestHeader.ACCESS_TOKEN, $.cookie(Access_Cookie.ACCESS_TOKEN));
        });*/

        /*// 当点击退出时，清空 cookie 中的 token，并发送退出 ajax 请求，最后跳转到登录页面
        $('#logout').click(function () {
            if (confirm('确定退出系统吗？')) {
                $.removeCookie(Access_Cookie.ACCESS_TOKEN);
                location.href = 'index.jsp';
            }
            return false;
        });*/

        $('.btn-submit').live("click",function(e){
            $('.input-username,dot-left').addClass('animated fadeOutRight')
            $('.input-password,dot-right').addClass('animated fadeOutLeft')
            $('.btn-submit').addClass('animated fadeOutUp')

            setTimeout(function () {
                        $('.avatar').addClass('avatar-top');
                        //$('.submit').html('<i class="fa fa-spinner fa-spin text-white"></i>');
                        $('.submit').html('<div class="progress"><div class="progress-bar progress-bar-success" aria-valuetransitiongoal="100"></div></div>');
                        $('.progress .progress-bar').progressbar();
                    },
                    500);
            $.ajax({
                url:"${pageContext.request.contextPath}/rest/doLogin",
                contentType: "application/json; charset=utf-8",
                dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
                type:"post", //传参方式，get 或post
                data:JSON.stringify({
                    "loginName":$("#loginName").val(),
                    "password":$("#password").val(),

                }),
                //传过去的参数，格式为 变量名：变量值
                success: function(data,status) {
                    /*console.log(data);
                    console.log(data.mainToken.value);
                    console.log(data.accessToken.value);*/
                    //若Ajax处理成功后的回调函数，text是返回的页面信息
                    $.localStorage.set(tokenStorage.MAIN_TOKEN, JSON.stringify(data.mainToken));
                    $.sessionStorage.set(tokenStorage.ACCESS_TOKEN, JSON.stringify(data.accessToken));

                    setTimeout(function () {
                                 window.location.href = '${pageContext.request.contextPath}/page/apartment/apartment.html';
                            },
                            1500);
                },
                error: function(request,status,message)
                {

                }
            });




        });


    });
</script>

</body>
</html>

