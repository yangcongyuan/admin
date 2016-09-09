

var handleLoginGritterNotification = function() {
    $(window).load(function()
    {
        setTimeout(function()
        {
            $.ajax(
                {
                url:HOMEADRESS+"user/getUserBaseInfo",
                contentType: "application/json; charset=utf-8",
                dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
                type:"post", //传参方式，get 或post

                //传过去的参数，格式为 变量名：变量值
                success: function(data,status) {
                    //登录通知处理
                    $.gritter.add({
                        title: "欢迎回来, "+data.loginName+"!",
                        text: "开始工作! fighting!!!",
                        image: defaultHeadPortrait,
                        sticky: true,
                        time: "",
                        class_name: "my-sticky-class"
                    });

                    //个人中心信息处理
                    $(".navbar-user img").attr("src",defaultHeadPortrait);
                    $(".navbar-user .hidden-xs").text(data.loginName);

                    $(".nav-profile img").attr("src",defaultHeadPortrait);
                    $(".nav-profile .info p").text(data.loginName);
                    $(".nav-profile .info small").text(data.email);

                },
                error: function(request,status,message)
                {

                }
            });

        },
        1e3)
    })
};
var getMineMenuTree=function() {
                $.ajax(
                    {
                        url:HOMEADRESS+"menu/getMineMenuTree",
                        contentType: "application/json; charset=utf-8",
                        dataType:"text", //返回的数据类型,text 或者 json数据，建议为json
                        type:"post", //传参方式，get 或post

                        //传过去的参数，格式为 变量名：变量值
                        success: function(data,status) {
                            //console.log(data);
                            var htmlText=" <li class=\"nav-header\">菜单列表</li>"+data+"  </li>"+"  <li><a href=\"javascript:;\" class=\"sidebar-minify-btn\" data-click=\"sidebar-minify\"><i class=\"fa fa-angle-double-left\"></i></a></li>";
                            <!-- begin sidebar minify button -->
                            $("#menuContainer").html(htmlText);
                            handleSidebarMenu();
                            handleSidebarMinify();
                            //App.init();

                        },
                        error: function(request,status,message)
                        {

                        }
                    });

};



var logout=function(){
    //询问框
    layer.confirm('确定要退出吗?', {
        btn: ['确定','取消'] //按钮
    }, function(){
        layer.msg('系统正在退出', {
            icon: 1,
            time: 2000,
            end:function(){
                $.ajax({
                    url:HOMEADRESS+"doLogout",
                    contentType: "application/json; charset=utf-8",
                    dataType:"json", //返回的数据类型,text 或者 json数据，建议为json
                    type:"post", //传参方式，get 或post

                    //传过去的参数，格式为 变量名：变量值
                    success: function(data,status) {
                        $.sessionStorage.remove(tokenStorage.ACCESS_TOKEN);
                    },
                    error: function(request,status,message)
                    {

                    }
                });
                 location.href = HOMEADRESS;
            }
        });


    }, function(){
        //do nothing
    });
};
//create splash screen animation
var splashRotator=function(){
    var cur = $('#jSplash').children('.selected');
    var next = $(cur).next();

    if($(next).length != 0) {
        $(next).addClass('selected');
    } else {
        $('#jSplash').children('section:first-child').addClass('selected');
        next = $('#jSplash').children('section:first-child');
    }

    $(cur).removeClass('selected').fadeOut(800, function() {
        $(next).fadeIn(800);
    });
}

var jpreLoader4Welcome=function () {
   // $("#page-container").hide();
    $('body').jpreLoader({
        splashID: "#jSplash",
        loaderVPos: '70%',
        //autoClose: false,
        //closeBtnText: "Let's Begin!",
        splashFunction: function() {
            //passing Splash Screen script to jPreLoader
            $('#jSplash').children('section').not('.selected').hide();
            $('#jSplash').hide().fadeIn(800);

            timer = setInterval(function() {
                splashRotator();
            }, 4000);
        }
    }, function() {	//callback function
        clearInterval(timer);
        //$("#page-container").fadeIn(1000);
        /* $('#footer')
         .animate({"bottom":0}, 500);
         $('#header')
         .animate({"top":0}, 800, function() {
         $('#wrapper').fadeIn(1000);
         });*/
    });
}


var EternalGravitational = function() {
    "use strict";
    return {
        init: function() {

            //登录通知
           handleLoginGritterNotification();
            getMineMenuTree();
            jpreLoader4Welcome();
            $("#logout").click(logout);
        }
    }
} ()