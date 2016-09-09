<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript">
	$(document).ready(function () {
		
		dtable = $('#data_table').DataTable({
			ajax: {
		    	url: basePath + "/rest/apartment/queryHousePrice",
            	type: "post",
				contentType: "application/json",
				data: function(d) {
					d.price_type = $('#price_type_q').val();
				    d.dateFrom = $('#dateFrom_q').val();
				    d.dateTo = $('#dateTo_q').val();
				    return JSON.stringify(d);
				}
		    },
		    columns: [
		        {title: '房价类型', data: 'price_type', width: '30%', defaultContent: '',
		        	render: function(data, type, row, meta) {
		        		if( data == 0 )
		        		{
		        			return "棋子湾当前平均市价";
		        		}
		        		else if( data == 1 )
		        		{
		        			return "周边楼盘当前市价";
		        		}
		        		else if( data == 2 )
		        		{
		        			return "整个海南岛平均市价";
		        		}
			        }
		        },
		        {title: '发布日期', data: 'publish_date', width: '30%', defaultContent: ''},
		        {title: '房价', data: 'price', width: '20%', defaultContent: ''},
		        {title: '操作', data: null, width: '20%', defaultContent: '',
		        	render: function(data, type, row, meta) {
			        	return "<input type='button' value='修改' class='btn btn-primary btn-xs' onclick='updData(" + row.hp_id + ");'/>&nbsp;&nbsp;"
			            	+ "<input type='button' value='删除' class='btn btn-danger btn-xs' onclick='delData(" + row.hp_id + ");'/>";
			        }
		        }
		    ],
		    serverSide: true,
		    processing: true,
		    ordering: false,
		    searching: false,
		    scrollX: true,
		    scrollY: document.body.clientHeight - 410,
		    scrollCollapse: true,
		    pagingType: "full_numbers",
		    lengthChange: false,
		    lengthMenu: [20],
		    language: Chinese_json
		});
		
		$(".input-daterange").datepicker({
			todayHighlight : true,
			autoclose : true,
			language: "zh-CN",
			clearBtn: true,
			format: "yyyy-mm-dd"
		});
		
		$("#publish_date").datepicker({
			todayHighlight : true,
			autoclose : true,
			language: "zh-CN",
			clearBtn: true,
			format: "yyyy-mm-dd"
		});
		
	});	
	
	var addTitle = "房价添加";
	var updTitle = "房价修改";
	
	function resetData(modal_title)
	{
		$("#modal_title").html(modal_title);
		$("#hp_id").val("");
		$("#price_type").val("");
		$("#publish_date").val("");
		$("#price").val("");
	}
	
	function valiData()
	{
		if( $("#price_type").val().length == 0 )
		{
			layer.msg("请选择房价类型");
			$("#price_type").focus();
			return false;
		}
		if( $("#publish_date").val().length == 0 )
		{
			layer.msg("请选择发布日期");
			$("#publish_date").focus();
			return false;
		}
		if( $("#price").val().length == 0 )
		{
			layer.msg("请输入房价");
			$("#price").focus();
			return false;
		}
		if( isNaN($("#price").val()) )
		{
			layer.msg("房价必须为数字");
			$("#price").focus();
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
            url: basePath + "/rest/apartment/addHousePrice",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"price_type":$("#price_type").val(),
            	"publish_date":$("#publish_date").val(),
            	"price":$("#price").val()
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
	
	function updData(hp_id)
	{
		$.ajax({
            url: basePath + "/rest/apartment/getHousePrice",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"hp_id":hp_id
            }),
            success: function(data, status) { 
            	resetData(updTitle);
            	
            	$("#hp_id").val(data.hp_id);
        		$("#price_type").val(data.price_type);
        		$("#publish_date").val(data.publish_date);
        		$("#price").val(data.price);
        		
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
            url: basePath + "/rest/apartment/updHousePrice",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"hp_id":$("#hp_id").val(),
            	"price_type":$("#price_type").val(),
            	"publish_date":$("#publish_date").val(),
            	"price":$("#price").val()
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
	
	function delData(hp_id)
	{
		layer.confirm("您确定要删除这一条记录吗？", {
			btn: ['确定', '取消'] //按钮
		},
		function()
		{
			$.ajax({
	            url: basePath + "/rest/apartment/delHousePrice",
	            contentType: "application/json; charset=utf-8",
	            dataType: "json", 
	            type: "post", 
	            data: JSON.stringify({
	            	"hp_id":hp_id
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
        <h4 class="panel-title">房价管理</h4>
    </div>
    <div class="panel-body">
    <div class="table-responsive">
        <form class="form-inline">
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">房价类型</span>
			<select class="form-control" id="price_type_q">
            <option value="">请选择</option>
            <option value="0">棋子湾当前平均市价</option>
            <option value="1">周边楼盘当前市价</option>
            <option value="2">整个海南岛平均市价</option>
            </select>
		</div>
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">发布日期</span>
			<div class="input-group input-daterange">
            <input type="text" class="form-control" id="dateFrom_q"/>
            <span class="input-group-addon">到</span>
            <input type="text" class="form-control" id="dateTo_q"/>
            </div>
		</div>
		<input type="button" value="查询" class="btn btn-success" onclick="dtable.ajax.reload();"/>&nbsp;&nbsp;
		<input type="button" value="添加" class="btn btn-success" onclick="addData();"/>
		</form>
    	
	    <table id="data_table" class="table table-striped table-bordered" width="100%">
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
        <form class="form-horizontal">
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>房价类型</label>
	            <div class="col-md-9">
	            	<input type="hidden" id="hp_id"/>
	                <select class="form-control" id="price_type">
		            <option value="">请选择</option>
		            <option value="0">棋子湾当前平均市价</option>
		            <option value="1">周边楼盘当前市价</option>
		            <option value="2">整个海南岛平均市价</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>发布日期</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="publish_date"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>房价</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="price"/>
	            </div>
	        </div>
        </form>
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
