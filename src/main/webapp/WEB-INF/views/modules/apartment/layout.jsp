<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
	<script type="text/javascript">
	$(document).ready(function () {
		
		dtable = $('#data_table').DataTable({
			ajax: {
		    	url: basePath + "/rest/apartment/queryLayout",
            	type: "post",
				contentType: "application/json",
				data: function(d) {
					d.comm_id = $('#comm_id_q').val();
				    d.layout_name = $('#layout_name_q').val();
				    return JSON.stringify(d);
				}
		    },
		    columns: [
		        {title: '楼盘名称', data: 'comm_name', width: '40%', defaultContent: ''},
		        {title: '户型图名称', data: 'layout_name', width: '40%', defaultContent: ''},
		        {title: '操作', data: null, width: '20%', defaultContent: '',
		        	render: function(data, type, row, meta) {
			        	return "<input type='button' value='修改' class='btn btn-primary btn-xs' onclick='updData(" + row.layout_id + ");'/>&nbsp;&nbsp;"
			            	+ "<input type='button' value='删除' class='btn btn-danger btn-xs' onclick='delData(" + row.layout_id + ");'/>";
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
                $("#comm_id_q").html(str);
                $("#comm_id").html(str);
            },
            error: function(request, status, message) {
            	layer.msg("网络异常，请重试");
            }
        });
	});	
	
	var addTitle = "户型图添加";
	var updTitle = "户型图修改";
	
	function resetData(modal_title)
	{
		$("#modal_title").html(modal_title);
		$("#layout_id").val("");
		$("#comm_id").val("");
		$("#layout_name").val("");
		$("#layout_pic1").val("");
		$("#file1").val("");
		$("#picsml1").attr("src", "");
		$("#layout_pic2").val("");
		$("#file2").val("");
		$("#picsml2").attr("src", "");
		$("#layout_pic3").val("");
		$("#file3").val("");
		$("#picsml3").attr("src", "");
		$("#layout_pic4").val("");
		$("#file4").val("");
		$("#picsml4").attr("src", "");
		$("#layout_pic5").val("");
		$("#file5").val("");
		$("#picsml5").attr("src", "");
	}
	
	function valiData()
	{
		if( $("#comm_id").val().length == 0 )
		{
			layer.msg("请选择楼盘");
			$("#comm_id").focus();
			return false;
		}
		if( $("#layout_name").val().length == 0 )
		{
			layer.msg("请输入户型图名称");
			$("#layout_name").focus();
			return false;
		}
		if( $("#layout_pic1").val().length == 0 && $("#layout_pic2").val().length == 0 && 
			$("#layout_pic3").val().length == 0 && $("#layout_pic4").val().length == 0 && 
			$("#layout_pic5").val().length == 0 )
		{
			layer.msg("请至少上传一张图片");
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
            url: basePath + "/rest/apartment/addLayout",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"comm_id":$("#comm_id").val(),
            	"layout_name":$("#layout_name").val(),
            	"layout_pic1":$("#layout_pic1").val(),
            	"layout_pic2":$("#layout_pic2").val(),
            	"layout_pic3":$("#layout_pic3").val(),
            	"layout_pic4":$("#layout_pic4").val(),
            	"layout_pic5":$("#layout_pic5").val()
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
	
	function updData(layout_id)
	{
		$.ajax({
            url: basePath + "/rest/apartment/getLayout",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"layout_id":layout_id
            }),
            success: function(data, status) { 
            	resetData(updTitle);
            	
            	$("#layout_id").val(data.layout_id);
        		$("#comm_id").val(data.comm_id);
        		$("#layout_name").val(data.layout_name);
        		$("#layout_pic1").val(data.layout_pic1);
        		if( data.layout_pic1 != null && data.layout_pic1.length > 0 )
        		{
        			$("#picsml1").attr("src", picPath + data.layout_pic1);
        		}
        		$("#layout_pic2").val(data.layout_pic2);
        		if( data.layout_pic2 != null && data.layout_pic2.length > 0 )
        		{
        			$("#picsml2").attr("src", picPath + data.layout_pic2);
        		}
        		$("#layout_pic3").val(data.layout_pic3);
        		if( data.layout_pic3 != null && data.layout_pic3.length > 0 )
        		{
        			$("#picsml3").attr("src", picPath + data.layout_pic3);
        		}
        		$("#layout_pic4").val(data.layout_pic4);
        		if( data.layout_pic4 != null && data.layout_pic4.length > 0 )
        		{
        			$("#picsml4").attr("src", picPath + data.layout_pic4);
        		}
        		$("#layout_pic5").val(data.layout_pic5);
        		if( data.layout_pic5 != null && data.layout_pic5.length > 0 )
        		{
        			$("#picsml5").attr("src", picPath + data.layout_pic5);
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
            url: basePath + "/rest/apartment/updLayout",
            contentType: "application/json; charset=utf-8",
            dataType: "json", 
            type: "post", 
            data: JSON.stringify({
            	"layout_id":$("#layout_id").val(),
            	"comm_id":$("#comm_id").val(),
            	"layout_name":$("#layout_name").val(),
            	"layout_pic1":$("#layout_pic1").val(),
            	"layout_pic2":$("#layout_pic2").val(),
            	"layout_pic3":$("#layout_pic3").val(),
            	"layout_pic4":$("#layout_pic4").val(),
            	"layout_pic5":$("#layout_pic5").val()
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
	
	function delData(layout_id)
	{
		layer.confirm("您确定要删除这一条记录吗？", {
			btn: ['确定', '取消'] //按钮
		},
		function()
		{
			$.ajax({
	            url: basePath + "/rest/apartment/delLayout",
	            contentType: "application/json; charset=utf-8",
	            dataType: "json", 
	            type: "post", 
	            data: JSON.stringify({
	            	"layout_id":layout_id
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
                $("#layout_pic" + idx).val(data);
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
        <h4 class="panel-title">户型图管理</h4>
    </div>
    <div class="panel-body">
    <div class="table-responsive">
        <form class="form-inline">
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">楼盘名称</span>
			<select class="form-control" id="comm_id_q">
            <option value="">请选择</option>
            </select>
		</div>
		<div class="form-group m-r-10">
			<span style="margin-right:10px;">户型图名称</span>
			<input type="text" class="form-control" id="layout_name_q"/>
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
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>楼盘名称</label>
	            <div class="col-md-9">
	            	<input type="hidden" id="layout_id"/>
	                <select class="form-control" id="comm_id">
		            <option value="">请选择</option>
		            </select>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label"><span style="color:red;">*</span>户型图名称</label>
	            <div class="col-md-9">
	                <input type="text" class="form-control" id="layout_name"/>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label">展示图片</label>
	            <div class="col-md-9">
	                <input type="hidden" id="layout_pic1">
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
	            <label class="col-md-3 control-label">展示图片</label>
	            <div class="col-md-9">
	                <input type="hidden" id="layout_pic2">
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
	            <label class="col-md-3 control-label">展示图片</label>
	            <div class="col-md-9">
	                <input type="hidden" id="layout_pic3">
	                <img src="" id="picsml3" style="width:200px;height:200px;"/>
	                <span class="btn btn-warning fileinput-button">
                    <i class="fa fa-plus"></i>
                    <span>上传图片</span>
	                <form id="uploadFileForm3" style="margin:0">
	                <input type="file" id="file3" name="file" onchange="uploadFile(3)"/>
	                </form>
	                </span>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label">展示图片</label>
	            <div class="col-md-9">
	                <input type="hidden" id="layout_pic4">
	                <img src="" id="picsml4" style="width:200px;height:200px;"/>
	                <span class="btn btn-warning fileinput-button">
                    <i class="fa fa-plus"></i>
                    <span>上传图片</span>
	                <form id="uploadFileForm4" style="margin:0">
	                <input type="file" id="file4" name="file" onchange="uploadFile(4)"/>
	                </form>
	                </span>
	            </div>
	        </div>
	        <div class="form-group">
	            <label class="col-md-3 control-label">展示图片</label>
	            <div class="col-md-9">
	                <input type="hidden" id="layout_pic5">
	                <img src="" id="picsml5" style="width:200px;height:200px;"/>
	                <span class="btn btn-warning fileinput-button">
                    <i class="fa fa-plus"></i>
                    <span>上传图片</span>
	                <form id="uploadFileForm5" style="margin:0">
	                <input type="file" id="file5" name="file" onchange="uploadFile(5)"/>
	                </form>
	                </span>
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
