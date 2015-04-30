<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/header.jsp"%>
<script type="text/javascript">
	$(function() {
		$("form").validate({
			submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
				$(form).ajaxSubmit({//验证新增是否成功
					type : "post",
					dataType : "json",
					success : function(data) {
						if (data.flag == "true") {
							$.ligerDialog.success('提交成功!', '提示', function() {
								parent.operator.loadGird();
								closeWin();
							});
						} else {
							$.ligerDialog.warn("提交失败！！");
						}
					}
				});
			},
			rules : {
			    name : "required",
			    area : "required",
			},
			messages : {
			    name: "输入不能为空",
			    area: "输入不能为空",
			},
			errorPlacement : function(error, element) {//自定义提示错误位置
				$(".l_err").css('display','block');
				$(".l_err").html(error.html());
			},
			success : function(label) {//验证通过后
				$(".l_err").css('display', 'none');
			}
		});
	});
	function saveWin() {
		$("#form").submit();
	}
</script>
</head>
<body>
	<div class="divdialog">
		<div class="l_err" style="width: 270px;"></div>
		<form name="form" id="form" action="${ctx}/background/operator/add.html" method="post">
			<table style="height: 200px;">
				<tbody>
					<tr>
						<td class="l_right">运营商名称：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id="name" name="name" type="text" value="">
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_right">国家或地区：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id="area" name="area" type="text" value="">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="l_btn_centent">
								<!-- saveWin_form   from是表单Id-->
								<a class="btn btn-primary" href="javascript:void(0)" id="saveWin_form" onclick="saveWin();"><span>保存</span> </a> <a class="btn btn-primary" href="javascript:void(0)" id="closeWin"
									onclick="closeWin()"><span>关闭</span> </a>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>