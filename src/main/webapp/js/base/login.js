var Cookie = {
    TOKEN: 'cookie.token',
    LOGINNAME: 'cookie.username',
    THEME: 'cookie.theme',
    USERID: 'cookie.userId'
};

var RequestHeader = {
    TOKEN: 'Authorization',
};
/*
$(function () {

    // 当遇到 401 状态码时，清空 cookie 中的 token，并跳转到登录页面
    $.ajaxSetup({
        statusCode: {
            401: function () {
                $.removeCookie(Cookie.TOKEN);
                location.href = 'index.jsp';
            }
        }
    });


    // 当发送 ajax 请求开始时，将 cookie 中的 token 与 username 放入 request header 中
    $(document).ajaxSend(function (event, xhr) {
        xhr.setRequestHeader(RequestHeader.TOKEN, $.cookie(Cookie.TOKEN));
    });

    // 当点击退出时，清空 cookie 中的 token，并发送退出 ajax 请求，最后跳转到登录页面
    $('#logout').click(function () {
        if (confirm('确定退出系统吗？')) {
            $.removeCookie(Cookie.TOKEN);
            location.href = 'index.jsp';
        }
        return false;
    });
});*/
$(document).ready(function() {
    $("#loginButton").click(loginSystem);
});
function testToken()
{
    $.post(basePath+"/test",
        {
            test:"admin@qq.com",

        },function(data){
            // 登录成功，将 token 与 username 放入 cookie 中
            $.cookie(Cookie.TOKEN, data["token"]);

            location.href = basePath+"page/index.jsp";
        },"json");
}
function loginSystem()
{

    $.ajax({
        url: basePath+"/doLogin",
        dataType: 'json',
        data:/*$('#loginForm').serialize()*/{
            nameLogin:"admin@qq.com",
            pwdLogin:"admin"
        },
        type : 'POST',
        async: false,
        success : function(data) {
          alert("111");
        },
        error : function(data) {
            localStorage.removeItem('Live_token');
        }
    });
    //$("#commentForm").validate({
        //submitHandler: function() {
        /*$.post(basePath+"/doLogin",
            {
                nameLogin:"admin@qq.com",
                pwdLogin:"admin",
            },function(data){
                // 登录成功，将 token 与 username 放入 cookie 中
                $.cookie(Cookie.TOKEN, data["token"]);

                location.href = basePath+"page/index.html";
            },"json");*/
       // }
   // });
        //noinspection JSUnresolvedFunction
   /* $.post( basePath+"/doLogin",
            {
                loginCode:"15269103792",
                password:"111111",
            },function(data){
                alert("code:"+data["code"]+"   message:"+data["messageDesc"]);
                console.log(data["code"],data["messageDesc"],data["vo"]);
            },"json");*/

}