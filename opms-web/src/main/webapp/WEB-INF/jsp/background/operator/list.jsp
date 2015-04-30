<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/header.jsp"%>
<script type="text/javascript">
	var dialog;
	var grid;
	var tab = null;
	$(function() {
		$("#frameBody").ligerTab({
			height: 1000,
			changeHeightOnResize: true,
		});
		tab = $("#frameBody").ligerGetTabManager();
		grid = window.opms.ui.grid({
					id : 'paging',
					l_column : [ {
						colkey : "id",
						name : "ID",
						width : "50px"
					}, {
						colkey : "name",
						name : "运营商名称",
						width : "70px"
					}, {
						colkey : "area",
						name : "国家或地区",
						width : "70px"
					} ],
					jsonUrl : '${pageContext.request.contextPath}/background/operator/query.html',
					checkbox : true
				});
		$("#seach").click("click", function() {//绑定按扭
			var searchParams = $("#fenye").serializeJson();
			grid.setOptions({
				data : searchParams
			});
		});
		$("#add").click("click", function() {//绑定按扭
			dialog = parent.$.ligerDialog.open({
				width : 330,
				height : 280,
				url : rootPath + '/background/operator/addUI.html',
				title : "增加运营商",
				isHidden : false
			//关闭对话框时是否只是隐藏，还是销毁对话框
			});
		});
		$("#editView").click("click", function() {//绑定按扭
							var cbox = grid.getSelectedCheckbox();
							if (cbox.length > 1 || cbox == "") {
								parent.$.ligerDialog.alert("只能选中一个");
								return;
							}
							dialog = parent.$.ligerDialog
									.open({
										width : 330,
										height : 280,
										url : rootPath
												+ '/background/operator/editUI.html?operatorId='
												+ cbox,
										title : "修改运营商",
										isHidden : false
									});
						});
		$("#serverList").click("click", function() {//绑定按扭
			var cbox = grid.selectRow();//多选则默认最后一个复选框有效
			if (cbox.id == undefined || cbox.id == "") {
				parent.$.ligerDialog.alert("请选择一条数据!");
				return;
			}
			var url = "${basePath}/background/server/list.html?operatorId=" + cbox.id;
			tab.addTabItem({
				tabid : "serverList",
				text : "区服列表",
				url : url
			});
		});
		$("#deleteView")
				.click(
						"click",
						function() {//绑定按扭
							var cbox = grid.getSelectedCheckbox();
							if (cbox == "") {
								parent.$.ligerDialog.alert("请选择删除项！！");
								return;
							}
							parent.$.ligerDialog
									.confirm(
											'删除后不能恢复，确定删除吗？',
											function(confirm) {
												if (confirm) {
													$
															.ajax({
																type : "post", //使用get方法访问后台
																dataType : "json", //json格式的数据
																async : false, //同步   不写的情况下 默认为true
																url : rootPath
																		+ '/background/operator/deleteById.html', //要访问的后台地址
																data : {
																	ids : cbox.join(",")
																}, //要发送的数据
																success : function(
																		data) {
																	if (data.flag == "true") {
																		parent.$.ligerDialog
																				.success(
																						'删除成功!',
																						'提示',
																						function() {
																							loadGird();//重新加载表格数据
																						});
																	} else {
																		parent.$.ligerDialog
																				.warn("删除失败！！");
																	}
																}
															});
												}
											});
						});
	});
	function loadGird() {
		grid.loadData();
	}
</script>
</head>
<body>
<div id="frameBody">
	<div class="divBody" title="运营商管理">
		<div class="search">
			<form name="fenye" id="fenye">
				名称：<input type="text" name="accountName" value="${param.name}" style="height: 20px" /> <a class="btn btn-primary" href="javascript:void(0)" id="seach"> 查询 </a>
			</form>
		</div>
		<div class="topBtn">
			<a class="btn btn-primary" href="javascript:void(0)" id="add"> <i class="icon-zoom-add icon-white"></i> <span>add</span>
			</a> <a class="btn btn-info" href="javascript:void(0)" id="editView"> <i class="icon-edit icon-white"></i> Edit
			</a> <a class="btn btn-danger" href="javascript:void(0)" id="deleteView"> <i class="icon-trash icon-white"></i> Delete
			</a> <a class="btn btn-large btn-success" href="javascript:void(0)" id="serverList"> 查看区服 </a>
		</div>
		<div id="paging" class="pagclass"></div>
	</div>
</div>
</body>
</html>