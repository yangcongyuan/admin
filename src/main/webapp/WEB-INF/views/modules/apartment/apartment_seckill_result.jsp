<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<link href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/css/datetimepicker.css" rel="stylesheet" media="screen">
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<link href="${pageContext.request.contextPath}/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" />
	<script src="${pageContext.request.contextPath}/plugins/bootstrap-select/bootstrap-select.min.js"></script>


	<script type="text/javascript">
		$(document).ready(function () {

			dtable = $('#data_table').DataTable({
				ajax: {
					url: basePath + "/rest/apartment/seckill/result/list",
					type: "post",
					contentType: "application/json",
					data: function(d) {
						d.searchText = $('#searchText').val();
						return JSON.stringify(d);
					}
				},
				columns: [
					{title: '房屋编号', data: 'apar_id', width: '8%', defaultContent: ''},
					{title: '房屋名称', data: 'apar_name', width: '17%', defaultContent: ''},
					{title: '抢购用户编号', data: 'id', width: '11%', defaultContent: ''},
					{title: '抢购用户名称', data: 'user_name', width: '17%', defaultContent: ''},
					{title: '抢购用户手机号', data: 'cellphone', width: '11%', defaultContent: ''},
					{title: '抢购时间', data: 'create_time', width: '15%', defaultContent: ''},
					{title: '状态', data: 'state', width: '6%', defaultContent: '',
							render: function(data, type, row, meta) {
								if (data == -1) {
									return "失败";
								}
								else if (data == 0) {
									return "成功";
								}
								else if (data == 1) {
									return "已付款";
								}
							}
					},
					{title: '操作', data: null, width: '11%', defaultContent: '',
						render: function(data, type, row, meta) {
							var strHTML = "";
							if(data["state"] == 0)
							strHTML += "<input type='button' value='确认付款' class='btn btn-primary btn-xs' onclick='updateStateData(" + row["apar_id"] + ");'/>&nbsp;&nbsp;&nbsp;";
							return strHTML;
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
		});

		function updateStateData(apar_id)
		{
			layer.confirm("确认已付款吗？", {
						btn: ['确定', '取消'] //按钮
					},
					function()
					{
						$.ajax({
							url: basePath + "/rest/apartment/seckill/result/pay",
							contentType: "application/json; charset=utf-8",
							dataType: "json",
							type: "post",
							data: JSON.stringify({
								"apar_id":apar_id
							}),
							success: function(data, status) {
								dtable.ajax.reload(null, false);
								layer.msg("操作成功");
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
			<h4 class="panel-title">房屋管理</h4>
		</div>

		<div class="panel-body">
			<div class="table-responsive">
				<form class="form-inline">
					<div class="form-group m-r-10">
						<span style="margin-right:10px;">用户手机号</span>
						<input type="text" class="form-control" id="searchText"/>
					</div>
					<input type="button" value="查询" class="btn btn-success" onclick="dtable.ajax.reload();"/>&nbsp;&nbsp;
				</form>

				<table id="data_table" class="table table-striped table-bordered" style="width:1280px">
				</table>
			</div>
		</div>
	</div>
	<!-- end panel -->
</div>
<!-- end row -->
<link href="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/css/datepicker3.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/ionRangeSlider/css/ion.rangeSlider.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/ionRangeSlider/css/ion.rangeSlider.skinNice.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/password-indicator/css/password-indicator.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/bootstrap-combobox/css/bootstrap-combobox.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/bootstrap-tagsinput/bootstrap-tagsinput.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/plugins/jquery-tag-it/css/jquery.tagit.css" rel="stylesheet" />

<script src="${pageContext.request.contextPath}/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/plugins/ionRangeSlider/js/ion-rangeSlider/ion.rangeSlider.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/masked-input/masked-input.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/password-indicator/js/password-indicator.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-combobox/js/bootstrap-combobox.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-tagsinput/bootstrap-tagsinput.min.js"></script>
<script src="${pageContext.request.contextPath}/plugins/bootstrap-tagsinput/bootstrap-tagsinput-typeahead.js"></script>
<script src="${pageContext.request.contextPath}/plugins/jquery-tag-it/js/tag-it.min.js"></script>

<script src="${pageContext.request.contextPath}/js/form-plugins.demo.min.js"></script>
<script src="${pageContext.request.contextPath}/js/apps.min.js"></script>
<script>
	$(document).ready(function() {
		App.init();
		//FormPlugins.init();
	});
</script>
</body>
</html>
 
