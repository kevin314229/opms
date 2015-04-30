<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/common/header.jsp"%>
<script type="text/javascript">
	$(function() {
	    loadOptionBox({
		    url : "/background/operator/queryAll.html",
		    boxId : "operatorId",
		    optionValue : "id",
		    optionName : "name",
			selectedValue: "${serverInfo.operatorId}"
		});
		$("form").validate({
			submitHandler : function(form) {//必须写在验证前面，否则无法ajax提交
				$(form).ajaxSubmit({//验证新增是否成功
					type : "post",
					dataType : "json",
					success : function(data) {
						if (data.flag == "true") {
							$.ligerDialog.success('提交成功!', '提示', function() {
								parent.frames["serverList"].loadGird();
								closeWin();
							});
						} else {
							$.ligerDialog.warn("提交失败！！");
						}
					}
				});
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
		<form name="form" id="form" action="${ctx}/background/server/update.html" method="post">
			<table style="height: 200px;">
				<tbody>
					<tr>
						<td class="l_right">区服名称：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id='id' name="id" type="hidden" value="${serverInfo.id}">
								<input id="name" name="name" type="text" value="${serverInfo.name}">
							</div>
						</td>
						<td class="l_right">所属运营商：</td>
						<td class="l_left">
							<select name="operatorId" id="operatorId" style="width: 140px;">
							</select>
						</td>
					</tr>
					<tr>
						<td class="l_right">IP：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id="ip" name="ip" type="text" value="${serverInfo.ip}">
							</div>
						</td>
						<td class="l_right">端口：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id="port" name="port" type="text" value="${serverInfo.port}">
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_right">预设CPU使用率：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id="setCpuUsage" name="setCpuUsage" type="text" value="${serverInfo.setCpuUsage}">
							</div>
						</td>
						<td class="l_right">预设JVM使用率：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id="setJvmUsage" name="setJvmUsage" type="text" value="${serverInfo.setJvmUsage}">
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_right">预设RAM使用率：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id="setRamUsage" name="setRamUsage" type="text" value="${serverInfo.setRamUsage}">
							</div>
						</td>
						<td class="l_right">Email：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id="email" name="email" type="text" value="${serverInfo.email}">
							</div>
						</td>
					</tr>
					<tr>
						<td class="l_right">通讯KEY：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id="visitKey" name="visitKey" type="text" value="${serverInfo.visitKey}">
							</div>
						</td>
						<td class="l_right">备注：</td>
						<td class="l_left">
							<div class="opms_input">
								<input id="mark" name="mark" type="text" value="${serverInfo.mark}">
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
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