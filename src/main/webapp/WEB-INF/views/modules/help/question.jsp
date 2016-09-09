<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript">
	$(document).ready(function () {

		dtable = $('#data_table').DataTable({
			ajax: {
		    	url: basePath + "/rest/help/queryQuestion",
            	type: "post",
				contentType: "application/json",
				data: function(d) {
					d.type_id = $('#type_id_q').val();
				    d.question = $('#question_q').val();
				    return JSON.stringify(d);
				}
		    },
		    columns: [
		        {title: '问题类型', data: 'type_name', width: '20%', defaultContent: ''},
		        {title: '问题', data: 'question', width: '50%', defaultContent: ''},
		        {title: '状态', data: 'valid', width: '10%', defaultContent: '',
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
			        	return "<input type='button' value='修改' class='btn btn-primary btn-xs' onclick='updData(" + row.ques_id + ");'/>&nbsp;&nbsp;"
			            	+ "<input type='button' value='删除' class='btn btn-danger btn-xs' onclick='delData(" + row.ques_id + ");'/>";
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

		$.ajax({
            url: basePath + "/rest/help/queryQuestionTypeSel",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            type: "post",
            success: function(data, status) {
            	var str = "<option value=''>请选择</option>";
                if( data != null && data.length > 0 )
                {
                    for( var i = 0; i < data.length; i++ )
                    {
                        str += "<option value='" + data[i].type_id + "'>" + data[i].type_name + "</option>"
                    }
                }
                $("#type_id_q").html(str);
                $("#type_id").html(str);
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
	});	
	
	var addTitle = "常见问题添加";
	var updTitle = "常见问题修改";
	
	function resetData(modal_title)
	{
		$("#modal_title").html(modal_title);
		$("#ques_id").val("");
		$("#type_id").val("");
		$("#question").val("");
		$("#answer").val("");
		$("#valid").val("");
	}
	
	function valiData()
	{
		if( $("#type_id").val().length == 0 )
		{
			layer.msg("请选择问题类型");
			$("#type_id").focus();
			return false;
		}
		if( $("#question").val().length == 0 )
		{
			layer.msg("请输入问题");
			$("#question").focus();
			return false;
		}
		if( $("#answer").val().length == 0 )
		{
			layer.msg("请输入答案");
			$("#answer").focus();
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
            url: basePath + "/rest/help/addQuestion",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"type_id":$("#type_id").val(),
            	"question":$("#question").val(),
            	"answer":$("#answer").val(),
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
	
	function updData(ques_id)
	{
		$.ajax({
            url: basePath + "/rest/help/getQuestion",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"ques_id":ques_id
            }),
            success: function(data, status) { 
            	resetData(updTitle);
            	
            	$("#ques_id").val(data.ques_id);
        		$("#type_id").val(data.type_id);
        		$("#question").val(data.question);
        		$("#answer").val(data.answer);
        		$("#valid").val(data.valid ? "1" : "0");
        		
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
            url: basePath + "/rest/help/updQuestion",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"ques_id":$("#ques_id").val(),
            	"type_id":$("#type_id").val(),
            	"question":$("#question").val(),
            	"answer":$("#answer").val(),
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
	
	function delData(ques_id)
	{
		layer.confirm("您确定要删除这一条记录吗？", {
			btn: ['确定', '取消'] //按钮
		},
		function()
		{
			$.ajax({
	            url: basePath + "/rest/help/delQuestion",
	            contentType: "application/json; charset=utf-8",
	            dataType: "json", 
	            type: "post", 
	            data: JSON.stringify({
	            	"ques_id":ques_id
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
        <h4 class="panel-title">常见问题管理</h4>
    </div>
    <div class="panel-body">
    <div class="table-responsive">
        <form class="form-inline">
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">问题类型</span>
			<select class="form-control" id="type_id_q">
            <option value="">请选择</option>
            </select>
		</div>
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">问题</span>
			<input type="text" class="form-control" id="question_q"/>
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
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>问题类型</label>
	            <div class="col-md-9">
	            	<input type="hidden" id="ques_id"/>
	                <select class="form-control" id="type_id">
		            <option value="">请选择</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>问题</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="question"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>答案</label>
	            <div class="col-md-9">
	                <textarea class="form-control" rows="5" id="answer"></textarea>
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
 
