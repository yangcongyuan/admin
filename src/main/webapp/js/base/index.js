/*
 Template Name: Color Admin - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.3.1
 Version: 1.5.0
 Author: Sean Ngu
 Website: http://www.seantheme.com/color-admin-v1.5/admin/
 */

var getMonthName = function(e) {
    var t = [];
    t[0] = "January";
    t[1] = "February";
    t[2] = "March";
    t[3] = "April";
    t[4] = "May";
    t[5] = "Jun";
    t[6] = "July";
    t[7] = "August";
    t[8] = "September";
    t[9] = "October";
    t[10] = "November";
    t[11] = "December";
    return t[e]
};
var getDate = function(e) {
    var t = new Date(e);
    var n = t.getDate();
    var r = t.getMonth() + 1;
    var i = t.getFullYear();
    if (n < 10) {
        n = "0" + n
    }
    if (r < 10) {
        r = "0" + r
    }
    t = i + "-" + r + "-" + n;
    return t
};
var handleVisitorsLineChart = function() {
    var e = "#0D888B";
    var t = "#00ACAC";
    var n = "#3273B1";
    var r = "#348FE2";
    var i = "rgba(0,0,0,0.6)";
    var s = "rgba(255,255,255,0.4)";
    Morris.Line({
        element: "visitors-line-chart",
        data: [{
            x: "2014-02-01",
            y: 60,
            z: 30
        },
            {
                x: "2014-03-01",
                y: 70,
                z: 40
            },
            {
                x: "2014-04-01",
                y: 40,
                z: 10
            },
            {
                x: "2014-05-01",
                y: 100,
                z: 70
            },
            {
                x: "2014-06-01",
                y: 40,
                z: 10
            },
            {
                x: "2014-07-01",
                y: 80,
                z: 50
            },
            {
                x: "2014-08-01",
                y: 70,
                z: 40
            }],
        xkey: "x",
        ykeys: ["y", "z"],
        xLabelFormat: function(e) {
            e = getMonthName(e.getMonth());
            return e.toString()
        },
        labels: ["Page Views", "Unique Visitors"],
        lineColors: [e, n],
        pointFillColors: [t, r],
        lineWidth: "2px",
        pointStrokeColors: [i, i],
        resize: true,
        gridTextFamily: "Open Sans",
        gridTextColor: s,
        gridTextWeight: "normal",
        gridTextSize: "11px",
        gridLineColor: "rgba(0,0,0,0.5)",
        hideHover: "auto"
    })
};
var handleVisitorsDonutChart = function() {
    var e = "#00acac";
    var t = "#348fe2";
    Morris.Donut({
        element: "visitors-donut-chart",
        data: [{
            label: "New Visitors",
            value: 900
        },
            {
                label: "Return Visitors",
                value: 1200
            }],
        colors: [e, t],
        labelFamily: "Open Sans",
        labelColor: "rgba(255,255,255,0.4)",
        labelTextSize: "12px",
        backgroundColor: "#242a30"
    })
};
var handleVisitorsVectorMap = function() {
    if ($("#visitors-map").length !== 0) {
        map = new jvm.WorldMap({
            map: "world_merc_en",
            scaleColors: ["#e74c3c", "#0071a4"],
            container: $("#visitors-map"),
            normalizeFunction: "linear",
            hoverOpacity: .5,
            hoverColor: false,
            markerStyle: {
                initial: {
                    fill: "#4cabc7",
                    stroke: "transparent",
                    r: 3
                }
            },
            regions: [{
                attribute: "fill"
            }],
            regionStyle: {
                initial: {
                    fill: "rgb(97,109,125)",
                    "fill-opacity": 1,
                    stroke: "none",
                    "stroke-width": .4,
                    "stroke-opacity": 1
                },
                hover: {
                    "fill-opacity": .8
                },
                selected: {
                    fill: "yellow"
                },
                selectedHover: {}
            },
            series: {
                regions: [{
                    values: {
                        IN: "#00acac",
                        US: "#00acac",
                        KR: "#00acac"
                    }
                }]
            },
            focusOn: {
                x: .5,
                y: .5,
                scale: 2
            },
            backgroundColor: "#2d353c"
        })
    }
};
var handleScheduleCalendar = function() {
    var e = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
    var t = ["S", "M", "T", "W", "T", "F", "S"];
    var n = new Date,
        r = n.getMonth() + 1,
        i = n.getFullYear();
    var s = [["2/" + r + "/" + i, "Popover Title", "#", "#00acac", "Some contents here"], ["5/" + r + "/" + i, "Tooltip with link", "http://www.seantheme.com/color-admin-v1.3", "#2d353c"], ["18/" + r + "/" + i, "Popover with HTML Content", "#", "#2d353c", 'Some contents here <div class="text-right"><a href="http://www.google.com">view more >>></a></div>'], ["28/" + r + "/" + i, "Color Admin V1.3 Launched", "http://www.seantheme.com/color-admin-v1.3", "#2d353c"]];
    var o = $("#schedule-calendar");
    $(o).calendar({
        months: e,
        days: t,
        events: s,
        popover_options: {
            placement: "top",
            html: true
        }
    });
    $(o).find("td.event").each(function() {
        var e = $(this).css("background-color");
        $(this).removeAttr("style");
        $(this).find("a").css("background-color", e)
    });
    $(o).find(".icon-arrow-left, .icon-arrow-right").parent().on("click",
        function() {
            $(o).find("td.event").each(function() {
                var e = $(this).css("background-color");
                $(this).removeAttr("style");
                $(this).find("a").css("background-color", e)
            })
        })
};
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

var index = function() {
    "use strict";
    return {
        init: function() {
             handleVisitorsLineChart();
             handleVisitorsDonutChart();
             handleVisitorsVectorMap();
             handleScheduleCalendar();

        }
    }
} ()