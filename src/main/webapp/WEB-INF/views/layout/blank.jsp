<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/base/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8" />
    <title id="sysTitle"></title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
    <meta content="" name="description" />
    <meta content="" name="author" />

    <title>
        <sitemesh:write property='title' /> 棋子湾平台后台管理系统
    </title>
    <link type="image/x-icon" href="${basePath}/images/base/sysicon.ico" rel="shortcut icon">
    <%@ include file="/WEB-INF/views/base/head.jsp"%>
    <sitemesh:write property='head' />
    <!-- ================== BEGIN BASE CSS STYLE ================== -->
    <link href="${pageContext.request.contextPath}/plugins/jpreloader/css/jpreloader.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/plugins/gritter/css/jquery.gritter.css" rel="stylesheet" />
    <!-- ================== BEGIN BASE JS ================== -->
    <script src="${pageContext.request.contextPath}/plugins/pace/pace.min.js"></script>

    <!-- ================== END BASE JS ================== -->

</head>
<body>
<!-- begin #page-loader -->
<div id="page-loader" class="fade in"><span class="spinner"></span></div>
<!-- end #page-loader -->

<!-- begin #page-container -->
<div id="page-container" class="fade page-sidebar-fixed page-header-fixed">
    <div  class="content">
        <%--<!-- begin breadcrumb -->--%>
        <%--<ol class="breadcrumb pull-right">--%>
            <%--<li><a href="javascript:;">Home</a></li>--%>
            <%--<li><a href="javascript:;">Dashboard</a></li>--%>
            <%--<li class="active"> board v2</li>--%>
        <%--</ol>--%>
        <!-- end breadcrumb -->
        <!-- begin page-header -->
        <h1 class="page-header">欢迎使用后台管理系统 <small></small></h1>
        <!-- end page-header -->
    </div>
    <!-- begin #header -->
    <div id="header" class="header navbar navbar-default navbar-fixed-top">
        <!-- begin container-fluid -->
        <div class="container-fluid">
            <!-- begin mobile sidebar expand / collapse button -->
            <div class="navbar-header">
                <a id="indexPageUrl" href="index.html" class="navbar-brand"><span class="navbar-logo"></span><span id="systemName"></span></a>
                <button type="button" class="navbar-toggle" data-click="sidebar-toggled">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- end mobile sidebar expand / collapse button -->

            <!-- begin header navigation right -->
            <ul class="nav navbar-nav navbar-right">
                <%--<li>--%>
                    <%--<form class="navbar-form full-width">--%>
                        <%--<div class="form-group">--%>
                            <%--<input type="text" class="form-control" placeholder="Enter keyword" />--%>
                            <%--<button type="submit" class="btn btn-search"><i class="fa fa-search"></i></button>--%>
                        <%--</div>--%>
                    <%--</form>--%>
                <%--</li>--%>
                <li class="dropdown" style="display: none">
                    <a href="javascript:;" data-toggle="dropdown" class="dropdown-toggle f-s-14">
                        <i class="fa fa-bell-o"></i>
                        <span class="label">5</span>
                    </a>
                    <ul  class="dropdown-menu media-list pull-right animated fadeInDown">
                        <li class="dropdown-header">Notifications (5)</li>
                        <li class="media">
                            <a href="javascript:;">
                                <div class="media-left"><i class="fa fa-bug media-object bg-red"></i></div>
                                <div class="media-body">
                                    <h6 class="media-heading">Server Error Reports</h6>
                                    <div class="text-muted f-s-11">3 minutes ago</div>
                                </div>
                            </a>
                        </li>
                        <li class="media">
                            <a href="javascript:;">
                                <div class="media-left"><img src="${pageContext.request.contextPath}/images/user-1.jpg" class="media-object" alt="" /></div>
                                <div class="media-body">
                                    <h6 class="media-heading">John Smith</h6>
                                    <p>Quisque pulvinar tellus sit amet sem scelerisque tincidunt.</p>
                                    <div class="text-muted f-s-11">25 minutes ago</div>
                                </div>
                            </a>
                        </li>
                        <li class="media">
                            <a href="javascript:;">
                                <div class="media-left"><img src="${pageContext.request.contextPath}/images/user-2.jpg" class="media-object" alt="" /></div>
                                <div class="media-body">
                                    <h6 class="media-heading">Olivia</h6>
                                    <p>Quisque pulvinar tellus sit amet sem scelerisque tincidunt.</p>
                                    <div class="text-muted f-s-11">35 minutes ago</div>
                                </div>
                            </a>
                        </li>
                        <li class="media">
                            <a href="javascript:;">
                                <div class="media-left"><i class="fa fa-plus media-object bg-green"></i></div>
                                <div class="media-body">
                                    <h6 class="media-heading"> New User Registered</h6>
                                    <div class="text-muted f-s-11">1 hour ago</div>
                                </div>
                            </a>
                        </li>
                        <li class="media">
                            <a href="javascript:;">
                                <div class="media-left"><i class="fa fa-envelope media-object bg-blue"></i></div>
                                <div class="media-body">
                                    <h6 class="media-heading"> New Email From John</h6>
                                    <div class="text-muted f-s-11">2 hour ago</div>
                                </div>
                            </a>
                        </li>
                        <%--<li class="dropdown-footer text-center">--%>
                            <%--<a href="javascript:;">View more</a>--%>
                        <%--</li>--%>
                    </ul>
                </li>
                <li  class="dropdown navbar-user">
                    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="" alt="" />
                        <span class="hidden-xs"></span> <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu animated fadeInLeft">
                        <%--<li class="arrow"></li>--%>
                        <%--<li><a id="editProfile" href="javascript:;">编辑个人资料</a></li>--%>
                        <%--<li><a id="inBox" href="javascript:;"><span class="badge badge-danger pull-right">0</span> 收件箱</a></li>--%>
                        <%--<li><a id="calendar" href="javascript:;">日历</a></li>--%>
                        <%--<li><a id="setting" href="javascript:;">设置</a></li>--%>
                        <%--<li class="divider"></li>--%>
                        <li ><a id="logout" href="javascript:;">退出</a></li>
                    </ul>
                </li>
            </ul>
            <!-- end header navigation right -->
        </div>
        <!-- end container-fluid -->
    </div>
    <!-- end #header -->

    <!-- begin #sidebar -->
    <div id="sidebar" class="sidebar">
        <!-- begin sidebar scrollbar -->
        <div data-scrollbar="true" data-height="100%">
            <!-- begin sidebar user -->
            <ul class="nav">
                <li class="nav-profile">
                    <div class="image">
                        <a href="javascript:;"><img src="" alt="" /></a>
                    </div>
                    <div class="info">
                        <p></p>
                        <small></small>
                    </div>
                </li>
            </ul>
            <!-- end sidebar user -->
            <!-- begin sidebar nav -->
            <ul  id="menuContainer" class="nav">

                <!-- end sidebar minify button -->
            </ul>
            <!-- end sidebar nav -->
        </div>
        <!-- end sidebar scrollbar -->
    </div>
    <div class="sidebar-bg"></div>
    <!-- end #sidebar -->


    <!-- begin theme-panel -->
    <div class="theme-panel">
        <a href="javascript:;" data-click="theme-panel-expand" class="theme-collapse-btn"><i class="fa fa-cog"></i></a>
        <div class="theme-panel-content">
            <h5 class="m-t-0">Color Theme</h5>
            <ul class="theme-list clearfix">
                <li class="active"><a href="javascript:;" class="bg-green" data-theme="default" data-click="theme-selector" data-toggle="tooltip" data-trigger="hover" data-container="body" data-title="Default">&nbsp;</a></li>
                <li><a href="javascript:;" class="bg-red" data-theme="red" data-click="theme-selector" data-toggle="tooltip" data-trigger="hover" data-container="body" data-title="Red">&nbsp;</a></li>
                <li><a href="javascript:;" class="bg-blue" data-theme="blue" data-click="theme-selector" data-toggle="tooltip" data-trigger="hover" data-container="body" data-title="Blue">&nbsp;</a></li>
                <li><a href="javascript:;" class="bg-purple" data-theme="purple" data-click="theme-selector" data-toggle="tooltip" data-trigger="hover" data-container="body" data-title="Purple">&nbsp;</a></li>
                <li><a href="javascript:;" class="bg-orange" data-theme="orange" data-click="theme-selector" data-toggle="tooltip" data-trigger="hover" data-container="body" data-title="Orange">&nbsp;</a></li>
                <li><a href="javascript:;" class="bg-black" data-theme="black" data-click="theme-selector" data-toggle="tooltip" data-trigger="hover" data-container="body" data-title="Black">&nbsp;</a></li>
            </ul>
            <div class="divider"></div>
            <div class="row m-t-10">
                <div class="col-md-5 control-label double-line">Header Styling</div>
                <div class="col-md-7">
                    <select name="header-styling" class="form-control input-sm">
                        <option value="1">default</option>
                        <option value="2">inverse</option>
                    </select>
                </div>
            </div>
            <div class="row m-t-10">
                <div class="col-md-5 control-label">Header</div>
                <div class="col-md-7">
                    <select name="header-fixed" class="form-control input-sm">
                        <option value="1">fixed</option>
                        <option value="2">default</option>
                    </select>
                </div>
            </div>
            <div class="row m-t-10">
                <div class="col-md-5 control-label double-line">Sidebar Styling</div>
                <div class="col-md-7">
                    <select name="sidebar-styling" class="form-control input-sm">
                        <option value="1">default</option>
                        <option value="2">grid</option>
                    </select>
                </div>
            </div>
            <div class="row m-t-10">
                <div class="col-md-5 control-label">Sidebar</div>
                <div class="col-md-7">
                    <select name="sidebar-fixed" class="form-control input-sm">
                        <option value="1">fixed</option>
                        <option value="2">default</option>
                    </select>
                </div>
            </div>
            <div class="row m-t-10">
                <div class="col-md-5 control-label double-line">Sidebar Gradient</div>
                <div class="col-md-7">
                    <select name="content-gradient" class="form-control input-sm">
                        <option value="1">disabled</option>
                        <option value="2">enabled</option>
                    </select>
                </div>
            </div>
            <div class="row m-t-10">
                <div class="col-md-5 control-label double-line">Content Styling</div>
                <div class="col-md-7">
                    <select name="content-styling" class="form-control input-sm">
                        <option value="1">default</option>
                        <option value="2">black</option>
                    </select>
                </div>
            </div>
            <div class="row m-t-10">
                <div class="col-md-12">
                    <a href="#" class="btn btn-inverse btn-block btn-sm" data-click="reset-local-storage"><i class="fa fa-refresh m-r-3"></i> Reset Local Storage</a>
                </div>
            </div>
        </div>
    </div>
    <!-- end theme-panel -->

    <!-- begin scroll to top btn -->
    <a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
    <!-- end scroll to top btn -->
    <section id="jSplash">
        <section>
            <p>Create your own <br/>
                <span style="font-size:30px; color:#FF6123;">Splash Screen</span>.</p>
        </section>
        <section>
            <p>Customize Progress Bar and Progress Percentage <br/>
                <span style="font-size:30px; color:#23FF27;">using CSS</span>.</p>
        </section>
        <section>
            <p>Preload all images in <br/>
                <span style="font-size:25px; color:#FF23FF;">&lt;img&gt; tag</span> +
                <span style="font-size:25px; color:#FF23FF;">CSS background-image</span>.</p>
        </section>
    </section>

</div>





<!-- end page container -->





<!-- ================== BEGIN BASE JS ==================-->

<!--<script src="${pageContext.request.contextPath}/plugins/jquery-cookie/jquery.cookie.js"></script>
 ================== END BASE JS ================== -->

<!-- ================== BEGIN PAGE LEVEL JS ==================
<script src="${pageContext.request.contextPath}/plugins/morris/raphael.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/morris/morris.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jquery-jvectormap/jquery-jvectormap-world-merc-en.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-calendar/js/bootstrap_calendar.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/gritter/js/jquery.gritter.js"></script>
<script src="${pageContext.request.contextPath}/js/dashboard-v2.min.js"></script>-->

<!-- ================== END PAGE LEVEL JS ================== -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/EternalGravitational.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/jpreloader/js/jpreloader.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/gritter/js/jquery.gritter.js"></script>

<!-- ================== BEGIN PAGE JS STYLE ================== -->
<div  class="content" style="background-color: #fff; min-height: 600px">
    <sitemesh:write property='body' />
</div>
</body>
<script>
    $(document).ready(function() {
        //初始化配件
        App.init();
        EternalGravitational.init();

    });
</script>

</html>



