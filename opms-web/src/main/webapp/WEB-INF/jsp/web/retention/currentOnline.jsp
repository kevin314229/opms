<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%@ include file="/common/header.jsp"%>
<script type="text/javascript">
$(function(){
	loadOptionBox({
		    url : "/background/operator/queryAll.html",
		    boxId : "operator",
		    optionValue : "id",
		    optionName : "name"
	});
	$("#seach").click("click", function() {//绑定按扭
			var seachParam = $("#fenye").serializeJson();
			$("#paging").ligerGrid({
                columns: [
                { display: '服务器', name: 'name'},
                { display: '当前在线人数', name: 'onlineCount', align: 'center',type:'int', totalSummary: {type: 'sum'}}
                ],   
                isScroll: false,
                width: "60%",
                sortName: "name",  
                method : "post",
                url: rootPath + "/online/now.html",
                parms: seachParam
            });
		});
	$("#operator").change(function(){
		var id = $("#operator").val();
		if (id > 0) {
		    loadOptionBox({
			    url : "/background/server/serverList.html?operatorId=" + id,
			    boxId : "server",
			    optionValue : "id",
			    optionName : "name"
			});
		}
	});
});


</script>
<body>
<div class="divBody">
		<div class="search">
			<form name="fenye" id="fenye">
				渠道：<select id="operator" name="operator"><option value="0" selected="selected">----所有----</option></select>
				  区服：<select id="server" name="server"><option value="0" selected="selected">----所有----</option></select>
				 <a class="btn btn-primary" href="javascript:void(0)" id="seach"> 查询</a>
			</form>
		</div>
		<div id="paging" class="pagclass"></div>
	</div>
</body>
</html>