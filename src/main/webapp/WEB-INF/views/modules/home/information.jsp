<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript">
	$(document).ready(function () {
		
		dtable = $('#data_table').DataTable({
			ajax: {
		    	url: basePath + "/rest/home/queryInformation",
            	type: "post",
				contentType: "application/json",
				data: function(d) {
					d.info_title = $('#info_title_q').val();
				    return JSON.stringify(d);
				}
		    },
		    columns: [
		        {title: '标题', data: 'info_title', width: '40%', defaultContent: ''},
		        {title: '排序', data: 'sort', width: '20%', defaultContent: ''},
		        {title: '状态', data: 'valid', width: '20%', defaultContent: '',
		        	render: function(data, type, row, meta) {
		        		if( data )
		        		{
		        			return "启用";
		        		}
		        		else
		        		{
		        			return "禁用";
		        		}
			        }	
		        },
		        {title: '操作', data: null, width: '20%', defaultContent: '',
		        	render: function(data, type, row, meta) {
			        	return "<input type='button' value='修改' class='btn btn-primary btn-xs' onclick='updData(" + row.info_id + ");'/>&nbsp;&nbsp;"
			            	+ "<input type='button' value='删除' class='btn btn-danger btn-xs' onclick='delData(" + row.info_id + ");'/>";
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
		
	});	
	
	var addTitle = "资讯添加";
	var updTitle = "资讯修改";
	
	function resetData(modal_title)
	{
		$("#modal_title").html(modal_title);
		$("#info_id").val("");
		$("#info_title").val("");
		$("#info_desc").val("");
		$("#sort").val("");
		$("#valid").val("");
		$("#info_img").val("");
		$("#file").val("");
		$("#picsml").attr("src", "");
	}
	
	function valiData()
	{
		if( $("#info_title").val().length == 0 )
		{
			layer.msg("请输入标题");
			$("#info_title").focus();
			return false;
		}
		if( $("#info_desc").val().length == 0 )
		{
			layer.msg("请输入介绍");
			$("#info_desc").focus();
			return false;
		}
		if( $("#info_img").val().length == 0 )
		{
			layer.msg("请上传图片");
			return false;
		}
		if( isNaN($("#sort").val()) )
		{
			layer.msg("排序必须为数字");
			$("#sort").focus();
			return false;
		}
		if( $("#valid").val().length == 0 )
		{
			layer.msg("请选择状态");
			$("#valid").focus();
			return false;
		}
		return true;
	}
	
	function addData()
	{
		resetData(addTitle);
		$("#valid").val("1");
		$("#modal_dialog").modal("show");
	}
	
	function addDataSubmit()
	{
		if( ! valiData() )
		{
			return;
		}
		$.ajax({
            url: basePath + "/rest/home/addInformation",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"info_title":$("#info_title").val(),
            	"info_desc":$("#info_desc").val(),
            	"info_img":$("#info_img").val(),
            	"sort":parseInt($("#sort").val()),
            	"valid":$("#valid").val()
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
	
	function updData(info_id)
	{
		$.ajax({
            url: basePath + "/rest/home/getInformation",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"info_id":info_id
            }),
            success: function(data, status) { 
            	resetData(updTitle);
            	
            	$("#info_id").val(data.info_id);
        		$("#info_title").val(data.info_title);
        		$("#info_desc").val(data.info_desc);
        		$("#sort").val(data.sort);
        		$("#valid").val(data.valid ? "1" : "0");
        		$("#info_img").val(data.info_img);
        		if( data.info_img != null && data.info_img.length > 0 )
        		{
        			$("#picsml").attr("src", picPath + data.info_img);
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
            url: basePath + "/rest/home/updInformation",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"info_id":$("#info_id").val(),
            	"info_title":$("#info_title").val(),
            	"info_desc":$("#info_desc").val(),
            	"info_img":$("#info_img").val(),
            	"sort":parseInt($("#sort").val()),
            	"valid":$("#valid").val()
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
	
	function delData(info_id)
	{
		layer.confirm("您确定要删除这一条记录吗？", {
			btn: ['确定', '取消'] //按钮
		},
		function()
		{
			$.ajax({
	            url: basePath + "/rest/home/delInformation",
	            contentType: "application/json; charset=utf-8",
	            dataType: "json", 
	            type: "post", 
	            data: JSON.stringify({
	            	"info_id":info_id
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
                $("#info_img").val(data);
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
        <h4 class="panel-title">资讯管理</h4>
    </div>
    <div class="panel-body">
    <div class="table-responsive">
        <form class="form-inline">
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">标题</span>
			<input type="text" class="form-control" id="info_title_q"/>
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
        <div class="form-horizontal">
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>标题</label>
	            <div class="col-md-9">
	            	<input type="hidden" id="info_id"/>
	                <input type="text" class="form-control" id="info_title"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>介绍</label>
	            <div class="col-md-9">
	                <textarea class="form-control" rows="5" id="info_desc"></textarea>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>展示图片</label>
	            <div class="col-md-9">
	                <input type="hidden" id="info_img">
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
	            <label class="col-md-3 control-label">排序</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="sort"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>状态</label>
	            <div class="col-md-9">
	                <select class="form-control" id="valid">
		            <option value="">请选择</option>
		            <option value="1">启用</option>
		            <option value="0">禁用</option>
		            </select>
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
