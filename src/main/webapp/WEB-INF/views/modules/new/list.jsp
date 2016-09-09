<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>


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
						<span style="margin-right:10px;">搜索</span>
						<input type="text" class="form-control" id="search"/>
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
<div class="modal fade" id="modal_dialog" style="z-index: 1000 !important;">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 900px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="modal_title"></h4>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<form class="form-horizontal">
						<div class="form-group">
							<input type="hidden" id="id"/>
							<label class="col-md-1 control-label"><span style="color:red;">*</span>标题</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="title"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label"><span style="color:red;">*</span>简介</label>
							<div class="col-md-9">
								<textarea id="info" class="form-control" rows="5"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label"><span style="color:red;">*</span>状态</label>
							<div class="col-md-9">
								<select class="form-control" id="publish">
									<option value="">请选择</option>
									<option value="1">发布</option>
									<option value="0">不发布</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-1 control-label"><span style="color:red;">*</span>问题</label>
							<div class="col-md-9">
								<!-- 加载编辑器的容器 -->
								<script id="editor" type="text/plain" style="width:700px;height:450px;"></script>
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
<!-- 配置文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/ueditor/ueditor.all.js"></script>
<script>
	var ue=UE.getEditor('editor');

	function createEditor() {
		enableBtn();
		UE.getEditor('editor');
	}
</script>
<script>

	$(document).ready(function () {
		dtable = $('#data_table').DataTable({
			ajax: {
				url: basePath + "/rest/new/list",
				type: "post",
				contentType: "application/json",
				data: function(d) {
					d.search = $('#search').val();
					return JSON.stringify(d);
				}
			},
			columns: [
				{title: '编号', data: 'id', width: '20%', defaultContent: ''},
				{title: '标题', data: 'title', width: '50%', defaultContent: ''},
				{title: '状态', data: 'publish', width: '10%', defaultContent: '',
					render: function(data, type, row, meta) {
						if( data )
						{
							return "发布";
						}
						else
						{
							return "未发布";
						}
					}
				},
				{title: '操作', data: null, width: '20%', defaultContent: '',
					render: function(data, type, row, meta) {
						return "<input type='button' value='修改' class='btn btn-primary btn-xs' onclick='updData(" + row.id + ");'/>&nbsp;&nbsp;"
								+ "<input type='button' value='删除' class='btn btn-danger btn-xs' onclick='delData(" + row.id + ");'/>";
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
	var addTitle = "新闻添加";
	var updTitle = "新闻修改";
	function addData()
	{
		resetData(addTitle);
		$("#valid").val("1");
		$("#title").val("");
		$("#info").val("");
		ue.setContent("");
		$("#valid").val("1");

		$("#modal_dialog").modal("show");
	}
	function resetData(modal_title)
	{
		$("#modal_title").html(modal_title);
		$("#title").val("");
		$("#publish").val("");
	}

	//表单验证
	function valiData()
	{
		if( $("#title").val().length == 0 )
		{
			layer.msg("请输入标题");
			$("#title").focus();
			return false;
		}
		if( $("#info").val().length == 0 )
		{
			layer.msg("请输入简介");
			$("#info").focus();
			return false;
		}
		if( $("#publish").val().length == 0 )
		{
			layer.msg("请选择状态");
			$("#publish").focus();
			return false;
		}
		return true;
	}

	//提交表单
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
	//添加
	function addDataSubmit()
	{
		if( ! valiData() )
		{
			return;
		}
		if (!UE.getEditor('editor').hasContents()){
			alrt('请先填写内容!');
			return;
		}
		var content = UE.getEditor('editor').getContent();
		$.ajax({
			url: basePath + "/rest/new/add",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			type: "post",
			data: JSON.stringify({
				"title":$("#title").val(),
				"info":$("#info").val(),
				"publish":$("#publish").val(),
				"detail":content
			}),
			success: function(data, status) {
				$("#modal_dialog").modal("hide");
				dtable.ajax.reload(null, false);
			},
			error: function(request, status, message) {
				layer.msg("网络异常，请重试");
			}
		});
	}
	function updData(id)
	{
		$.ajax({
			url: basePath + "/rest/new/get",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			type: "post",
			data: JSON.stringify({
				"id":id
			}),
			success: function(data, status) {
				resetData(updTitle);
				$("#id").val(data.id);
				$("#title").val(data.title);
				$("#info").val(data.info);
				$("#publish").val(data.publish ? "1" : "0");
				UE.getEditor('editor').setContent(data.detail);
				$("#modal_dialog").modal("show");
			},
			error: function(request, status, message) {
				layer.msg("网络异常，请重试");
			}
		});
	}

	//修改
	function updDataSubmit()
	{
		if( ! valiData() )
		{
			return;
		}
		var content = UE.getEditor('editor').getContent();
		$.ajax({
			url: basePath + "/rest/new/update",
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			type: "post",
			data: JSON.stringify({
				"id":$("#id").val(),
				"title":$("#title").val(),
				"info":$("#info").val(),
				"publish":$("#publish").val(),
				"detail":content
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
	function delData(id)
	{
		layer.confirm("您确定要删除这一条记录吗？", {
					btn: ['确定', '取消'] //按钮
				},
				function()
				{
					$.ajax({
						url: basePath + "/rest/new/del",
						contentType: "application/json; charset=utf-8",
						dataType: "json",
						type: "post",
						data: JSON.stringify({
							"id":id
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
</body>
</html>
 
