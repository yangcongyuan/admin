<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript">


        $(document).ready(function () {

            dtable = $('#data_table').DataTable({
                ajax: {
                    url: basePath + "/rest/menu/getMenu",
                    type: "post",
                    contentType: "application/json",
                    data: function (d) {
                        return JSON.stringify(d);
                    }

                },

                columns: [
                    {title: '名称', data: 'name', width: '25%', defaultContent: ''},
                    {title: '链接', data: 'href', width: '25%', defaultContent: ''},
                    {title: '排序', data: 'sort', width: '25%', defaultContent: ''},

                    {
                        title: '操作', data: null, width: '25%', defaultContent: '',
                        render: function (data, type, row, meta) {

                            return "<input type='button' value='修改' class='btn btn-primary btn-xs' onclick='updData(" + row.id + ");'/>&nbsp;&nbsp;"
                                    + "<input type='button' value='删除' class='btn btn-danger btn-xs' onclick='delData(" + row.id + ");'/>";
                        }
                    }
                ],
                serverSide: true,
                processing: true,
                ordering: false,
                searching: false,
                autoWidth: false,
                scrollX: true,
                scrollY: document.body.clientHeight - 410,
                scrollCollapse: true,
                pagingType: "full_numbers",
                lengthChange: false,
                lengthMenu: [10],
                language: Chinese_json
            });

            // 添加菜单
            $.ajax({
                url: basePath + "/rest/menu/getSecondaryMenu",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: "post",
                success: function (data, status) {
                    var str = "<option value=''>请选择</option>";
                    str += "<option value='0'>一级菜单</option>";
                    if (data != null && data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            str += "<option value='" + data[i].id + "'>" + data[i].name + "</option>"
                        }
                    }
                    $("#parentId1").html(str);
                    $("#apar_type_id_q").html(str);
                },
                error: function (request, status, message) {
                    layer.msg("网络异常，请重试");
                }
            });


            $("#update_date").datepicker({
                todayHighlight: true,
                autoclose: true,
                language: "zh-CN",
                clearBtn: true,
                format: "yyyy-mm-dd"
            });

            $("#net_day").datepicker({
                todayHighlight: true,
                autoclose: true,
                language: "zh-CN",
                clearBtn: true,
                format: "yyyy-mm-dd"
            });

        });

        var addTitle = "房屋添加";
        var updTitle = "房屋修改";

        function resetData(modal_title) {
            $("#modal_title").html(modal_title);
            $("#parent_id").val("");
            $("#name").val("");
            $("#sort").val("");
            $("#href").val("");

        }

        function valiData() {


            if ($("#name").val().length == 0) {
                layer.msg("请输入菜单名称");
                $("#name").focus();
                return false;
            }
            if ($("#sort").val().length == 0) {
                layer.msg("请输排序号");
                $("#sort").focus();
                return false;
            }
            if ($("#href").val().length == 0) {
                layer.msg("请输入链接");
                $("#href").focus();
                return false;
            }


            return true;
        }


        function addData(i) {

            if (i == 0) {
                $("#parent1").hide();
                $("#parent").show();
            } else {
                $("#parent").hide();
                $("#parent1").show();
            }


            resetData(addTitle);
            $("#modal_dialog").modal("show");

        }

        function addDataSubmit() {

            if (!valiData()) {
                return;
            }
            $.ajax({
                url: basePath + "/rest/menu/insert",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: "post",
                data: JSON.stringify({
                    "parent_id": $("#parentId1").val(),         //添加菜单
                    "name": $("#name").val(),                    //菜单名称
                    "sort": $("#sort").val(),                    //菜单排序
                    "href": $("#href").val(),                    //菜单链接
                }),
                success: function (data, status) {
                    $("#modal_dialog").modal("hide");
                    dtable.ajax.reload(null, false);
                    layer.msg("添加成功");
                    window.location.reload();
                },
                error: function (request, status, message) {
                    layer.msg("网络异常，请重试");
                }
            });
        }
        //修改菜单
        function updData(id) {
            $.ajax({
                url: basePath + "/rest/menu/selectById",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: "post",
                data: JSON.stringify({
                    "id": id
                }),
                success: function (data, status) {
                    resetData(updTitle);
                    console.log(data);
                    $("#id").val(data.id);
                    $("#is_show").val(data.is_show)
                    $("#parent_id").val(data.parent_id);
                    $("#name").val(data.name);
                    $("#sort").val(data.sort);
                    $("#href").val(data.href);
                    $("#update_by").val(data.update_by);
                    $("#update_date").val(data.update_date);
                    $("#is_show").val(data.is_show);

                    $("#modal_dialog").modal("show");
                },
                error: function (request, status, message) {
                    layer.msg("网络异常，请重试");
                }
            });
        }

        function updDataSubmit() {
            if (!valiData()) {
                return;
            }
            $.ajax({
                url: basePath + "/rest/menu/updateByPrimaryKey",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: "post",
                data: JSON.stringify({
                    "id": $("#id").val(),
                    "parent_id": $("#parent_id").val(),          //父级编号
                    "name": $("#name").val(),                     //菜单名称
                    "sort": $("#sort").val(),                     //排序
                    "href": $("#href").val(),                     //链接
                    "create_by": $("#create_by").val,            //创建者
                    "update_by": $("#update_by").val(),          //更新者
                    "update_date": $("#update_date").val(),      //更新时间
                    "is_show":$("#is_show").val()
                }),
                success: function (data, status) {

                    $("#modal_dialog").modal("hide");
                    dtable.ajax.reload(null, false);
                    layer.msg("修改成功");
                },
                error: function (request, status, message) {
                    layer.msg("网络异常，请重试");
                }
            });
        }

        function submitData() {
            if ($("#modal_title").html() == addTitle) {
                addDataSubmit();
            }
            else if ($("#modal_title").html() == updTitle) {
                updDataSubmit();
            }
        }
        //删除菜单
        function delData(id) {
            layer.confirm("您确定要删除这一条记录吗？", {
                        btn: ['确定', '取消'] //按钮
                    },
                    function () {
                        $.ajax({
                            url: basePath + "/rest/menu/deleteById",
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            type: "post",
                            data: JSON.stringify({
                                "id": id
                            }),
                            success: function (data, status) {
                                dtable.ajax.reload(null, false);
                                layer.msg("删除成功");
                                window.location.reload();
                            },
                            error: function (request, status, message) {
                                layer.msg("网络异常，请重试");
                            }
                        });
                    },
                    function (closethislayer) {
                        layer.close(closethislayer);
                    });
        }

        function uploadFile() {
            var formData = new FormData($("#uploadFileForm")[0]);
            $.ajax({
                url: basePath + "/rest/common/uploadimage/apartment",
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    $("#list_img").val(data);
                    $("#picsml").attr("src", picPath + data);
                },
                error: function (returndata) {
                    layer.msg("网络异常，请重试");
                }
            });
        }


    </script>
</head>

<body>
<!-- begin row -->
<div class="row">
    <!-- begin panel -->
    <div class="panel panel-inverse">
        <div class="panel-heading">
            <div class="panel-heading-btn">
                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i
                        class="fa fa-expand"></i></a>
                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i
                        class="fa fa-repeat"></i></a>
                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i
                        class="fa fa-minus"></i></a>
                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i
                        class="fa fa-times"></i></a>
            </div>
            <h4 class="panel-title">菜单管理</h4>
        </div>
        <div class="panel-body">
            <div class="table-responsive">
                <form class="form-inline">
                    <%--<div class="form-group m-r-10">
                        <span style="margin-right:10px;">添加菜单</span>
                        <select class="form-control" id="apar_type_id_q">
                            <option value="">请选择</option>
                        </select>
                    </div>--%>
                    <%--<input id="stair" type="button" value="添加一級菜单" class="btn btn-success" onclick="addData(0);"/>&nbsp;&nbsp;&nbsp;--%>
                    <input id="stair_id" type="button" value="添加菜单" class="btn btn-success" onclick="addData(1);"/>
                </form>

                <table id="data_table" class="table table-striped table-bordered" style="width:1300px">
                </table>
            </div>
        </div>
    </div>
    <!-- end panel -->
</div>
<!-- end row -->

<!-- #modal-dialog -->
<div class="modal fade" id="modal_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="modal_title"></h4>
            </div>
            <div class="modal-body">
                <div class="panel-body">
                    <div class="form-horizontal">
                        <%-- <div class="form-group">
                             <label class="col-md-3 control-label"><span style="color:red;">*</span>父级编号</label>
                             <div class="col-md-9">
                                 <input type="hidden" id="id"/>
                                 <input type="text" class="form-control" id="parent_id"/>
                             </div>
                         </div>--%>

                        <%--<div class="form-group m-r-10" id="parent">
                            <span style="margin-right:10px;">一级菜单</span>
                            <select class="form-control" id="parentId">
                                <option value="">请选择</option>
                                <option value="0">一级菜单</option>
                            </select>
                        </div>--%>

                        <div class="form-group">
                            <label class="col-md-3 control-label"><span style="color:red;">*</span>添加菜单</label>
                            <div class="col-md-9">
                                <select class="form-control" id="parentId1">
                                    <option value="">请选择</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label"><span style="color:red;">*</span>名称</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><span style="color:red;">*</span>排序</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="sort"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-3 control-label"><span style="color:red;">*</span>链接</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="href"/>
                            </div>
                        </div>




                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a href="javascript:;" class="btn btn-sm btn-success" onclick="submitData();">提交</a>
                <a href="javascript:;" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
 
