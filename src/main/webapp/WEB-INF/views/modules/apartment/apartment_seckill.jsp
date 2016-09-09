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
					url: basePath + "/rest/apartment/seckill/list",
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
					{title: '房屋编号', data: 'apar_id', width: '20%', defaultContent: ''},
					{title: '房屋名称', data: 'apar_name', width: '20%', defaultContent: ''},
					{title: '房屋创建时间', data: 'create_time', width: '20%', defaultContent: ''},
					{title: '操作', data: null, width: '20%', defaultContent: '',
						render: function(data, type, row, meta) {
							var strHTML = "";
							if( row["apar_status"] == 0 || row["start_time"] == null){
								strHTML += "<input type='button' value='抢购设置' class='btn btn-primary btn-xs' onclick='settingData(" + row["apar_id"] + ");'/>&nbsp;&nbsp;&nbsp;";

							}
							if(row["start_time"] != null){
								if(row["apar_status"] == 2){
									strHTML += "<input type='button' value='下线' class='btn btn-primary btn-xs' onclick='updateStatusData(" + row["apar_id"] + ",false);'/>&nbsp;&nbsp;&nbsp;";
								}else if(row["apar_status"] == 0){
									strHTML += "<input type='button' value='上线' class='btn btn-primary btn-xs' onclick='updateStatusData(" + row["apar_id"] + ",true);'/>";
								}
							}

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

			$('#endTime').datetimepicker({
				pickDate: true,                 //en/disables the date picker
				pickTime: true,                 //en/disables the time picker
				useMinutes: true,               //en/disables the minutes picker
				useSeconds: true,               //en/disables the seconds picker
				useCurrent: true,               //when true, picker will set the value to the current date/time
				minuteStepping: 1,               //set the minute stepping
				pick12HourFormat: false,
				lang: 'cn'
			});
			$('#strTime').datetimepicker({
				pickDate: true,                 //en/disables the date picker
				pickTime: true,                 //en/disables the time picker
				useMinutes: true,               //en/disables the minutes picker
				useSeconds: true,               //en/disables the seconds picker
				useCurrent: true,               //when true, picker will set the value to the current date/time
				minuteStepping: 1,               //set the minute stepping
				pick12HourFormat: false,
				lang: 'cn'
			});

		});

		var addSettingTitle = "增加房屋设置";
		var updateSettingTitle = "修改房屋设置";
		var publishdTitle = "房屋上架/下架";

		function resetData(modal_title)
		{
			$("#apartmentId").val("");
			$("#strTime").val("");
			$("#userList").val("");
			$("#endTime").val("");
			$("#modal_title").html(modal_title);
		}
		//获取房屋设置
		function settingData(apar_id){

			$.ajax({
				url: basePath + "/rest/apartment/seckill/get",
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				type: "post",
				data: JSON.stringify({
					"apar_id":apar_id
				}),
				success: function(data, status) {
					if(data != 'null' && data != null){
						resetData(updateSettingTitle);
						$("#strTime").val(data.start_time);
						$("#endTime").val(data.end_time);
						$("#apartmentId").val(apar_id);
					}else{
						resetData(addSettingTitle);
						$("#apartmentId").val(apar_id);
					}
					$.ajax({
						url: basePath + "/rest/stage/user/list",
						contentType: "application/json; charset=utf-8",
						dataType: "json",
						type: "post",
						data: JSON.stringify({
						}),
						success: function(data, status) {
							var userListHTML = '<option value="0">请选择</option>';
							for(var i in data["data"]){
								if(data["data"][i]["id"] == data["userId"]){
									userListHTML+='<option selected value="'+data["data"][i]["id"]+'">'+data["data"][i]["cellphone"]+'</option>';
								}else{
									userListHTML+='<option value="'+data["data"][i]["id"]+'">'+data["data"][i]["cellphone"]+'</option>';
								}

							}
							$("#userList").html(userListHTML);
							$('.selectpicker').selectpicker({
								style: 'btn-info',
								size: 8
							});
						},
						error: function(request, status, message) {
							layer.msg("网络异常，请重试");
						}
					});


					$("#modal_dialog").modal("show");
				},
				error: function(request, status, message) {
					layer.msg("网络异常，请重试");
				}
			});
		}
		//提交房屋设置  保存或者修改
		function submitData()
		{
			if( $("#modal_title").html() == addSettingTitle )
			{
				console.log("add");
				addDataSubmit();
			}
			else if( $("#modal_title").html() == updateSettingTitle )
			{
				console.log("update");
				updDataSubmit();
			}
		}
		//效验参数
		function valiData()
		{
			if( $("#strTime").val().length == 0 )
			{
				layer.msg("请填写开始时间");
				$("#strTime").focus();
				return false;
			}
			if( $("#endTime").val().length == 0 )
			{
				layer.msg("请填写结束时间");
				$("#endTime").focus();
				return false;
			}
			return true;
		}
		function updDataSubmit()
		{
			if( ! valiData() )
			{
				return;
			}
			$.ajax({
				url: basePath + "/rest/apartment/seckill/edit",
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				type: "post",
				data: JSON.stringify({
					apartment_id: $("#apartmentId").val(),
					start_time: $("#strTime").val(),
					user_id: $("#userList").val(),
					end_time: $("#endTime").val()
				}),
				success: function(data, status) {
					$("#modal_dialog").modal("hide");
					dtable.ajax.reload(null, false);
					layer.msg("设置成功");
				},
				error: function(request, status, message) {
					layer.msg("网络异常，请重试");
				}
			});
		}
		function addDataSubmit()
		{
			if( ! valiData() )
			{
				return;
			}
			$.ajax({
				url: basePath + "/rest/apartment/seckill/add",
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				type: "post",
				data: JSON.stringify({
					apartment_id: $("#apartmentId").val(),
					start_time: $("#strTime").val(),
					user_id: $("#userList").val(),
					end_time: $("#endTime").val()
				}),
				success: function(data, status) {
					$("#modal_dialog").modal("hide");
					dtable.ajax.reload(null, false);
					layer.msg("设置成功");
				},
				error: function(request, status, message) {
					layer.msg("网络异常，请重试");
				}
			});
		}
		function updateStatusData(apar_id,status)
		{
			var message = "";
			if(status){
				message = "确定要上架吗？";
				url = "/rest/apartment/seckill/up";
			}
			else{
				message = "确认要下架吗？";
				url = "/rest/apartment/seckill/down";
			}
			layer.confirm(message, {
						btn: ['确定', '取消'] //按钮
					},
					function()
					{
						$.ajax({
							url: basePath + url,
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

				<table id="data_table" class="table table-striped table-bordered" style="width:1280px">
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
							<input type ="hidden" id = "apartmentId">
							<label class="col-md-3 control-label">内定人员</label>
							<div class="col-md-9">
								<select id = "userList" class="form-control selectpicker" data-size="10" data-live-search="true" data-style="btn-inverse">
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"><span style="color:red;">*</span>开始时间</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="strTime">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"><span style="color:red;">*</span>结束时间</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="endTime">
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
 
