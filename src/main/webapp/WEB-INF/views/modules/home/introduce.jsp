<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript">
	$(document).ready(function () {
		
		dtable = $('#data_table').DataTable({
			ajax: {
		    	url: basePath + "/rest/home/queryIntroduce",
            	type: "post",
				contentType: "application/json",
				data: function(d) {
					d.intr_title = $('#intr_title_q').val();
				    return JSON.stringify(d);
				}
		    },
		    columns: [
		        {title: '标题', data: 'intr_title', width: '40%', defaultContent: ''},
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
			        	return "<input type='button' value='修改' class='btn btn-primary btn-xs' onclick='updData(" + row.intr_id + ");'/>&nbsp;&nbsp;"
			            	+ "<input type='button' value='删除' class='btn btn-danger btn-xs' onclick='delData(" + row.intr_id + ");'/>";
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
	
	var addTitle = "项目介绍添加";
	var updTitle = "项目介绍修改";
	
	function resetData(modal_title)
	{
		$("#modal_title").html(modal_title);
		$("#intr_id").val("");
		$("#intr_title").val("");
		$("#introduce").val("");
		$("#sort").val("");
		$("#valid").val("");
		$("#img_small").val("");
		$("#file1").val("");
		$("#picsml1").attr("src", "");
		$("#img_big").val("");
		$("#file2").val("");
		$("#picsml2").attr("src", "");
	}
	
	function valiData()
	{
		if( $("#intr_title").val().length == 0 )
		{
			layer.msg("请输入标题");
			$("#intr_title").focus();
			return false;
		}
		if( $("#introduce").val().length == 0 )
		{
			layer.msg("请输入介绍");
			$("#introduce").focus();
			return false;
		}
		if( $("#img_small").val().length == 0 )
		{
			layer.msg("请上传小图片");
			return false;
		}
		if( $("#img_big").val().length == 0 )
		{
			layer.msg("请上传大图片");
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
            url: basePath + "/rest/home/addIntroduce",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"intr_title":$("#intr_title").val(),
            	"introduce":$("#introduce").val(),
            	"img_small":$("#img_small").val(),
            	"img_big":$("#img_big").val(),
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
	
	function updData(intr_id)
	{
		$.ajax({
            url: basePath + "/rest/home/getIntroduce",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"intr_id":intr_id
            }),
            success: function(data, status) { 
            	resetData(updTitle);
            	
            	$("#intr_id").val(data.intr_id);
        		$("#intr_title").val(data.intr_title);
        		$("#introduce").val(data.introduce);
        		$("#sort").val(data.sort);
        		$("#valid").val(data.valid ? "1" : "0");
        		$("#img_small").val(data.img_small);
        		if( data.img_small != null && data.img_small.length > 0 )
        		{
        			$("#picsml1").attr("src", picPath + data.img_small);
        		}
        		$("#img_big").val(data.img_big);
        		if( data.img_big != null && data.img_big.length > 0 )
        		{
        			$("#picsml2").attr("src", picPath + data.img_big);
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
            url: basePath + "/rest/home/updIntroduce",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"intr_id":$("#intr_id").val(),
            	"intr_title":$("#intr_title").val(),
            	"introduce":$("#introduce").val(),
            	"img_small":$("#img_small").val(),
            	"img_big":$("#img_big").val(),
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
	
	function delData(intr_id)
	{
		layer.confirm("您确定要删除这一条记录吗？", {
			btn: ['确定', '取消'] //按钮
		},
		function()
		{
			$.ajax({
	            url: basePath + "/rest/home/delIntroduce",
	            contentType: "application/json; charset=utf-8",
	            dataType: "json", 
	            type: "post", 
	            data: JSON.stringify({
	            	"intr_id":intr_id
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
	
	function uploadFile(idx)
    {
        var formData = new FormData($("#uploadFileForm" + idx)[0]);
        $.ajax({
            url: basePath + "/rest/common/uploadimage/apartment",
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                if( idx == 1 )
                {
                	$("#img_small").val(data);
                }
                else if( idx == 2 )
                {
                	$("#img_big").val(data);
                }
                $("#picsml" + idx).attr("src", picPath + data);
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
        <h4 class="panel-title">项目介绍管理</h4>
    </div>
    <div class="panel-body">
    <div class="table-responsive">
        <form class="form-inline">
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">标题</span>
			<input type="text" class="form-control" id="intr_title_q"/>
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
	            	<input type="hidden" id="intr_id"/>
	                <input type="text" class="form-control" id="intr_title"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>介绍</label>
	            <div class="col-md-9">
	                <textarea class="form-control" rows="5" id="introduce"></textarea>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>小图片</label>
	            <div class="col-md-9">
	                <input type="hidden" id="img_small">
	                <img src="" id="picsml1" style="width:200px;height:200px;"/>
	                <span class="btn btn-warning fileinput-button">
                    <i class="fa fa-plus"></i>
                    <span>上传图片</span>
                    <form id="uploadFileForm1" style="margin:0">
                    <input type="file" id="file1" name="file" onchange="uploadFile(1)"/>
                    </form>
                    </span>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>大图片</label>
	            <div class="col-md-9">
	                <input type="hidden" id="img_big">
	                <img src="" id="picsml2" style="width:200px;height:200px;"/>
	                <span class="btn btn-warning fileinput-button">
                    <i class="fa fa-plus"></i>
                    <span>上传图片</span>
	                <form id="uploadFileForm2" style="margin:0">
	                <input type="file" id="file2" name="file" onchange="uploadFile(2)"/>
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
