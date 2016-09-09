<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript">
	$(document).ready(function () {
		dtable = $('#data_table').DataTable({
			ajax: {
		    	url: basePath + "/rest/apartment/queryApartment",
            	type: "post",
				contentType: "application/json",
				data: function(d) {
					d.apar_type_id = $('#apar_type_id_q').val();
				    d.area_id = $('#area_id_q').val();
				    d.apar_house_type_id = $('#apar_house_type_id_q').val();
				    d.apar_name = $('#apar_name_q').val();
				    return JSON.stringify(d);
				}
		    },
		    columns: [
		        {title: '房屋标题', data: 'apar_name', width: '300px', defaultContent: ''},
		        {title: '楼盘名称', data: 'comm_name', width: '200px', defaultContent: ''},
		        {title: '楼号', data: 'building_no', width: '100px', defaultContent: ''},
		        {title: '单元号', data: 'unit_no', width: '100px', defaultContent: ''},
		        {title: '楼层号', data: 'floor_no', width: '100px', defaultContent: ''},
		        {title: '房间号', data: 'apar_no', width: '100px', defaultContent: ''},
		        {title: '房屋类型', data: 'apar_type', width: '150px', defaultContent: ''},
		        {title: '房屋户型', data: 'room', width: '150px', defaultContent: '',
		        	render: function(data, type, row, meta) {
		        		if( row.room != null && row.hall != null )
		        		{
		        			return row.room + "室" + row.hall + "厅";
		        		}
			        }
		        },
		        {title: '房屋面积', data: 'apar_area', width: '150px', defaultContent: ''},
		        {title: '实际面积', data: 'area', width: '100px', defaultContent: ''},
		        {title: '售价', data: 'sell_price', width: '100px', defaultContent: ''},
		        {title: '单价', data: 'unit_price', width: '100px', defaultContent: ''},
		        {title: '开盘日', data: 'open_day', width: '150px', defaultContent: ''},
		        {title: '网签日', data: 'net_day', width: '150px', defaultContent: ''},
		        {title: '状态', data: 'apar_status', width: '100px', defaultContent: '',
		        	render: function(data, type, row, meta) {
		        		if( data == 0 )
		        		{
		        			return "待售";
		        		}
		        		else if( data == 1 )
		        		{
		        			return "已售";
		        		}
		        		else if( data == 2 )
		        		{
		        			return "抢购";
		        		}
		        		else if( data == 3 )
		        		{
		        			return "转让";
		        		}
		        		else
		        		{
		        			return "";
		        		}
			        }	
		        },
		        {title: '操作', data: null, width: '100px', defaultContent: '',
		        	render: function(data, type, row, meta) {
			        	return "<input type='button' value='修改' class='btn btn-primary btn-xs' onclick='updData(" + row.apar_id + ");'/>&nbsp;&nbsp;"
			            	+ "<input type='button' value='删除' class='btn btn-danger btn-xs' onclick='delData(" + row.apar_id + ");'/>";
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
		    lengthMenu: [20],
		    language: Chinese_json
		});
		
		// 楼盘
		$.ajax({
            url: basePath + "/rest/apartment/queryCommunitySel",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            success: function(data, status) { 
            	var str = "<option value=''>请选择</option>";
                if( data != null && data.length > 0 )
                {
                    for( var i = 0; i < data.length; i++ )
                    {
                        str += "<option value='" + data[i].comm_id + "'>" + data[i].comm_name + "</option>"
                    }
                }
                $("#comm_id").html(str);
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
		// 房屋类型
		$.ajax({
            url: basePath + "/rest/apartment/queryApartmentTypeSel",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            success: function(data, status) { 
            	var str = "<option value=''>请选择</option>";
                if( data != null && data.length > 0 )
                {
                    for( var i = 0; i < data.length; i++ )
                    {
                        str += "<option value='" + data[i].id + "'>" + data[i].value + "</option>"
                    }
                }
                $("#apar_type_id_q").html(str);
                $("#apar_type_id").html(str);
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
		// 房屋面积
		$.ajax({
            url: basePath + "/rest/apartment/queryApartmentAreaSel",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            success: function(data, status) { 
            	var str = "<option value=''>请选择</option>";
                if( data != null && data.length > 0 )
                {
                    for( var i = 0; i < data.length; i++ )
                    {
                        str += "<option value='" + data[i].id + "'>" + data[i].value + "</option>"
                    }
                }
                $("#area_id_q").html(str);
                $("#area_id").html(str);
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
		// 房屋户型
		$.ajax({
            url: basePath + "/rest/apartment/queryApartmentHouseTypeSel",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            success: function(data, status) { 
            	var str = "<option value=''>请选择</option>";
                if( data != null && data.length > 0 )
                {
                    for( var i = 0; i < data.length; i++ )
                    {
                        str += "<option value='" + data[i].id + "'>" + 
                        	data[i].room + "室" + data[i].hall + "厅</option>"
                    }
                }
                $("#apar_house_type_id_q").html(str);
                $("#apar_house_type_id").html(str);
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
		// 室内图
		$.ajax({
            url: basePath + "/rest/apartment/queryModelSel",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            success: function(data, status) { 
            	var str = "<option value=''>请选择</option>";
                if( data != null && data.length > 0 )
                {
                    for( var i = 0; i < data.length; i++ )
                    {
                    	str += "<option value='" + data[i].model_id + "'>" + data[i].model_name + "</option>"
                    }
                }
                $("#model_id").html(str);
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
		// 户型图
		$.ajax({
            url: basePath + "/rest/apartment/queryLayoutSel",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            success: function(data, status) { 
            	var str = "<option value=''>请选择</option>";
                if( data != null && data.length > 0 )
                {
                    for( var i = 0; i < data.length; i++ )
                    {
                    	str += "<option value='" + data[i].layout_id + "'>" + data[i].layout_name + "</option>"
                    }
                }
                $("#layout_id").html(str);
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
		// 环境图
		$.ajax({
            url: basePath + "/rest/apartment/queryEnvironmentSel",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            success: function(data, status) { 
            	var str = "<option value=''>请选择</option>";
                if( data != null && data.length > 0 )
                {
                    for( var i = 0; i < data.length; i++ )
                    {
                    	str += "<option value='" + data[i].envi_id + "'>" + data[i].envi_name + "</option>"
                    }
                }
                $("#envi_id").html(str);
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
		
		$("#open_day").datepicker({
			todayHighlight : true,
			autoclose : true,
			language: "zh-CN",
			clearBtn: true,
			format: "yyyy-mm-dd"
		});
		
		$("#net_day").datepicker({
			todayHighlight : true,
			autoclose : true,
			language: "zh-CN",
			clearBtn: true,
			format: "yyyy-mm-dd"
		});
		
	});	
	
	var addTitle = "房屋添加";
	var updTitle = "房屋修改";
	
	function resetData(modal_title)
	{
		$("#modal_title").html(modal_title);
		$("#apar_id").val("");
		$("#apar_name").val("");
		$("#comm_id").val("");
		$("#building_no").val("");
		$("#unit_no").val("");
		$("#floor_no").val("");
		$("#apar_no").val("");
		$("#apar_type_id").val("");
		$("#apar_house_type_id").val("");
		$("#area_id").val("");
		$("#area").val("");
		$("#sell_price").val("");
		$("#unit_price").val("");
		$("#open_day").val("");
		$("#net_day").val("");
		$("#year").val("");
		$("#model_id").val("");
		$("#layout_id").val("");
		$("#envi_id").val("");
		$("#turned").val("");
		$("#decorate").val("");
		$("#floor_num").val("");
		$("#list_img").val("");
		$("#file").val("");
		$("#picsml").attr("src", "");
	}
	
	function valiData()
	{
		if( $("#apar_name").val().length == 0 )
		{
			layer.msg("请输入房屋标题");
			$("#apar_name").focus();
			return false;
		}
		if( $("#comm_id").val().length == 0 )
		{
			layer.msg("请选择楼盘名称");
			$("#comm_id").focus();
			return false;
		}
		if( $("#building_no").val().length == 0 )
		{
			layer.msg("请输入楼号");
			$("#building_no").focus();
			return false;
		}
		if( isNaN($("#building_no").val()) )
		{
			layer.msg("楼号必须为数字");
			$("#building_no").focus();
			return false;
		}
		if( $("#unit_no").val().length == 0 )
		{
			layer.msg("请输入单元号");
			$("#unit_no").focus();
			return false;
		}
		if( isNaN($("#unit_no").val()) )
		{
			layer.msg("单元号必须为数字");
			$("#unit_no").focus();
			return false;
		}
		if( $("#floor_no").val().length == 0 )
		{
			layer.msg("请输入楼层号");
			$("#floor_no").focus();
			return false;
		}
		if( isNaN($("#floor_no").val()) )
		{
			layer.msg("楼层号必须为数字");
			$("#floor_no").focus();
			return false;
		}
		if( $("#apar_no").val().length == 0 )
		{
			layer.msg("请输入房间号");
			$("#apar_no").focus();
			return false;
		}
		if( isNaN($("#apar_no").val()) )
		{
			layer.msg("房间号必须为数字");
			$("#apar_no").focus();
			return false;
		}
		if( $("#apar_type_id").val().length == 0 )
		{
			layer.msg("请选择房屋类型");
			$("#apar_type_id").focus();
			return false;
		}
		if( $("#apar_house_type_id").val().length == 0 )
		{
			layer.msg("请选择房屋户型");
			$("#apar_house_type_id").focus();
			return false;
		}
		if( $("#area_id").val().length == 0 )
		{
			layer.msg("请选择房屋面积");
			$("#area_id").focus();
			return false;
		}
		if( $("#area").val().length == 0 )
		{
			layer.msg("请输入实际面积");
			$("#area").focus();
			return false;
		}
		if( isNaN($("#area").val()) )
		{
			layer.msg("实际面积必须为数字");
			$("#area").focus();
			return false;
		}
		if( $("#sell_price").val().length == 0 )
		{
			layer.msg("请输入售价");
			$("#sell_price").focus();
			return false;
		}
		if( isNaN($("#sell_price").val()) )
		{
			layer.msg("售价必须为数字");
			$("#sell_price").focus();
			return false;
		}
		if( $("#unit_price").val().length == 0 )
		{
			layer.msg("请输入单价");
			$("#unit_price").focus();
			return false;
		}
		if( isNaN($("#unit_price").val()) )
		{
			layer.msg("单价必须为数字");
			$("#unit_price").focus();
			return false;
		}
		if( $("#open_day").val().length == 0 )
		{
			layer.msg("请选择开盘日");
			$("#open_day").focus();
			return false;
		}
		if( $("#net_day").val().length == 0 )
		{
			layer.msg("请选择网签日");
			$("#net_day").focus();
			return false;
		}
		if( $("#year").val().length == 0 )
		{
			layer.msg("请输入年代");
			$("#year").focus();
			return false;
		}
		if( isNaN($("#year").val()) )
		{
			layer.msg("年代必须为数字");
			$("#year").focus();
			return false;
		}
		if( isNaN($("#floor_num").val()) )
		{
			layer.msg("共几层必须为数字");
			$("#floor_num").focus();
			return false;
		}
		return true;
	}
	
	function addData()
	{
		resetData(addTitle);
		$("#modal_dialog").modal("show");
	}
	
	function addDataSubmit()
	{
		if( ! valiData() )
		{
			return;
		}
		$.ajax({
            url: basePath + "/rest/apartment/addApartment",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"apar_name":$("#apar_name").val(),
            	"comm_id":$("#comm_id").val(),
            	"building_no":$("#building_no").val(),
            	"unit_no":$("#unit_no").val(),
            	"floor_no":$("#floor_no").val(),
            	"apar_no":$("#apar_no").val(),
            	"apar_type_id":$("#apar_type_id").val(),
            	"apar_house_type_id":$("#apar_house_type_id").val(),
            	"area_id":$("#area_id").val(),
            	"area":$("#area").val(),
            	"sell_price":$("#sell_price").val(),
            	"unit_price":$("#unit_price").val(),
            	"open_day":$("#open_day").val(),
            	"net_day":$("#net_day").val(),
            	"year":$("#year").val(),
            	"model_id":parseInt($("#model_id").val()),
            	"layout_id":parseInt($("#layout_id").val()),
            	"envi_id":parseInt($("#envi_id").val()),
            	"turned":$("#turned").val(),
            	"decorate":$("#decorate").val(),
            	"floor_num":parseInt($("#floor_num").val()),
            	"list_img":$("#list_img").val()
            }),
            success: function(data, status) { 
            	$("#modal_dialog").modal("hide");
            	dtable.ajax.reload(null, false);
            	layer.msg("添加成功");
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
	}
	
	function updData(apar_id)
	{
		$.ajax({
            url: basePath + "/rest/apartment/getApartment",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"apar_id":apar_id
            }),
            success: function(data, status) { 
            	resetData(updTitle);
            	
            	$("#apar_id").val(data.apar_id);
        		$("#apar_name").val(data.apar_name);
        		$("#comm_id").val(data.comm_id);
        		$("#building_no").val(data.building_no);
        		$("#unit_no").val(data.unit_no);
        		$("#floor_no").val(data.floor_no);
        		$("#apar_no").val(data.apar_no);
        		$("#apar_type_id").val(data.apar_type_id);
        		$("#apar_house_type_id").val(data.apar_house_type_id);
        		$("#area_id").val(data.area_id);
        		$("#area").val(data.area);
        		$("#sell_price").val(data.sell_price);
        		$("#unit_price").val(data.unit_price);
        		$("#open_day").val(data.open_day);
        		$("#net_day").val(data.net_day);
        		$("#year").val(data.year);
        		$("#model_id").val(data.model_id);
        		$("#layout_id").val(data.layout_id);
        		$("#envi_id").val(data.envi_id);
        		$("#turned").val(data.turned);
        		$("#decorate").val(data.decorate);
        		$("#floor_num").val(data.floor_num);
        		$("#list_img").val(data.list_img);
        		if( data.list_img != null && data.list_img.length > 0 )
        		{
        			$("#picsml").attr("src", picPath + data.list_img);
        		}
        		
        		$("#modal_dialog").modal("show");
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
	}
	
	function updDataSubmit()
	{
		if( ! valiData() )
		{
			return;
		}
		$.ajax({
            url: basePath + "/rest/apartment/updApartment",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"apar_id":$("#apar_id").val(),
            	"apar_name":$("#apar_name").val(),
            	"comm_id":$("#comm_id").val(),
            	"building_no":$("#building_no").val(),
            	"unit_no":$("#unit_no").val(),
            	"floor_no":$("#floor_no").val(),
            	"apar_no":$("#apar_no").val(),
            	"apar_type_id":$("#apar_type_id").val(),
            	"apar_house_type_id":$("#apar_house_type_id").val(),
            	"area_id":$("#area_id").val(),
            	"area":$("#area").val(),
            	"sell_price":$("#sell_price").val(),
            	"unit_price":$("#unit_price").val(),
            	"open_day":$("#open_day").val(),
            	"net_day":$("#net_day").val(),
            	"year":$("#year").val(),
            	"model_id":parseInt($("#model_id").val()),
            	"layout_id":parseInt($("#layout_id").val()),
            	"envi_id":parseInt($("#envi_id").val()),
            	"turned":$("#turned").val(),
            	"decorate":$("#decorate").val(),
            	"floor_num":parseInt($("#floor_num").val()),
            	"list_img":$("#list_img").val()
            }),
            success: function(data, status) {
            	$("#modal_dialog").modal("hide");
            	dtable.ajax.reload(null, false);
            	layer.msg("修改成功");
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
	}
	
	function submitData()
	{
		if( $("#modal_title").html() == addTitle )
	{
		addDataSubmit();
	}
	else if( $("#modal_title").html() == updTitle )
	{
		updDataSubmit();
	}
	}
	
	function delData(apar_id)
	{
		layer.confirm("您确定要删除这一条记录吗？", {
			btn: ['确定', '取消'] //按钮
		},
		function()
		{
			$.ajax({
	            url: basePath + "/rest/apartment/delApartment",
	            contentType: "application/json; charset=utf-8",
	            dataType: "json", 
	            type: "post", 
	            data: JSON.stringify({
	            	"apar_id":apar_id
	            }),
	            success: function(data, status) { 
	            	dtable.ajax.reload(null, false);
	            	layer.msg("删除成功");
	            },
	            error: function(request, status, message) {
	            	layer.msg("网络异常，请重试");
	            }
	        });
		}, 
		function(closethislayer)
		{
			layer.close(closethislayer);
		});
	}
	
	function uploadFile()
    {
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
        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
        <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
        </div>
        <h4 class="panel-title">房屋管理</h4>
    </div>
    <div class="panel-body">
    <div class="table-responsive">
        <form class="form-inline">
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">房屋类型</span>
			<select class="form-control" id="apar_type_id_q">
            <option value="">请选择</option>
            </select>
		</div>
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">房屋面积</span>
			<select class="form-control" id="area_id_q">
            <option value="">请选择</option>
            </select>
		</div>
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">房屋户型</span>
			<select class="form-control" id="apar_house_type_id_q">
            <option value="">请选择</option>
            </select>
		</div>
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">房屋标题</span>
			<input type="text" class="form-control" id="apar_name_q"/>
		</div>
		<input type="button" value="查询" class="btn btn-success" onclick="dtable.ajax.reload();"/>&nbsp;&nbsp;
		<input type="button" value="添加" class="btn btn-success" onclick="addData();"/>
		</form>
    	
	    <table id="data_table" class="table table-striped table-bordered" style="width:2150px">
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
        	<div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>房屋标题</label>
	            <div class="col-md-9">
	            	<input type="hidden" id="apar_id"/>
	                <input type="text" class="form-control" id="apar_name"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>楼盘名称</label>
	            <div class="col-md-9">
	                <select class="form-control" id="comm_id">
		            <option value="">请选择</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>楼号</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="building_no"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>单元号</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="unit_no"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>楼层号</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="floor_no"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>房间号</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="apar_no"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>房屋类型</label>
	            <div class="col-md-9">
	                <select class="form-control" id="apar_type_id">
		            <option value="">请选择</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>房屋户型</label>
	            <div class="col-md-9">
	                <select class="form-control" id="apar_house_type_id">
		            <option value="">请选择</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>房屋面积</label>
	            <div class="col-md-9">
	                <select class="form-control" id="area_id">
		            <option value="">请选择</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>实际面积</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="area"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>售价</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="sell_price"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>单价</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="unit_price"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>开盘日</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="open_day"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>网签日</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="net_day"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>年代</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="year"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label">展示图片</label>
	            <div class="col-md-9">
	                <input type="hidden" id="list_img">
	                <img src="" id="picsml" style="width:200px;height:200px;"/>
	                <span class="btn btn-warning fileinput-button">
                    <i class="fa fa-plus"></i>
                    <span>上传图片</span>
	                <form id="uploadFileForm" style="margin:0">
	                <input type="file" id="file" name="file" onchange="uploadFile()"/>
	                </form>
	                </span>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label">室内图</label>
	            <div class="col-md-9">
	                <select class="form-control" id="model_id">
		            <option value="">请选择</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label">户型图</label>
	            <div class="col-md-9">
	                <select class="form-control" id="layout_id">
		            <option value="">请选择</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label">环境图</label>
	            <div class="col-md-9">
	                <select class="form-control" id="envi_id">
		            <option value="">请选择</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label">朝向</label>
	            <div class="col-md-9">
	                <select class="form-control" id="turned">
		            <option value="">请选择</option>
		            <option value="A">东</option>
		            <option value="B">南</option>
		            <option value="C">西</option>
		            <option value="D">北</option>
		            <option value="E">南北</option>
		            <option value="F">东西</option>
		            <option value="G">东南</option>
		            <option value="H">西南</option>
		            <option value="I">东北</option>
		            <option value="J">西北</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label">装修情况</label>
	            <div class="col-md-9">
	                <select class="form-control" id="decorate">
		            <option value="">请选择</option>
		            <option value="A">毛坯</option>
		            <option value="B">简单装修</option>
		            <option value="C">中等装修</option>
		            <option value="D">精装修</option>
		            <option value="E">豪华装修</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label">共几层</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="floor_num"/>
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
 
