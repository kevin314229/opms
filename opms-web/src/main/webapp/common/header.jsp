<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	String contextPath = request.getContextPath();
	String baseHttpPath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextPath + "/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="basePath" value="<%=baseHttpPath%>" />
<title>游戏光年运营管理系统</title>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="${ctx}/css/basic.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/jslib/ligerUI/skins/Aqua/css/ligerui-all.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/css/opms.css">
<style type="text/css">
.l_err {
	background: none repeat scroll 0 0 #FFFCC7;
	border: 1px solid #FFC340;
	font-size: 12px;
	padding: 4px 8px;
	width: 200px;
	display: none;
}

.error {
	border: 3px solid #FFCCCC;
}
</style>

<script src="${ctx}/jslib/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${ctx}/jslib/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/jslib/jquery-validation/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/jslib/jquery-validation/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/jslib/ligerUI/js/ligerui.min.js"></script>
<script type="text/javascript" src="${ctx}/jslib/ligerUI/js/plugins/ligerDialog.js"></script>
<script type="text/javascript" src="${ctx}/jslib/grid.js"></script>
<script type="text/javascript" src="${ctx}/jslib/CustomersData.js"></script>

<script type="text/javascript">
    var rootPath = "${ctx}";
    //禁止右键
    //document.oncontextmenu=new Function('event.returnValue=false;');
    //禁止选中
    //document.onselectstart=new Function('event.returnValue=false;');
    //单独验证某一个input  class="isNum"
    function loadingShow() {
	$("body").append('<div class="divshow" style="display: none" id="divshow"></div>');
	document.getElementById("divshow").style.display = "block";
	var loadingContainer = $("div.loading");
	if (loadingContainer.length <= 0) {
	    loadingContainer = $("<div>", {
		Class : "loadingWhenSave",
		id : "loadingWhenSave"
	    });
	    var img = $("<img>", {
		src : "${pageContext.request.contextPath}/images/loading.gif"
	    });
	    loadingContainer.html("");
	    loadingContainer.append(img).css({
		position : "absolute", //"absolute",
		zIndex : "9999",
		textAlign : "center",
		// backgroundColor: "#000",
		border : "solid 1px back",
		paddingTop : "18px",
		fontSize : "14px",
		top : "30%",
		left : "40%"
	    });
	    loadingContainer.appendTo('body');
	}
    }

    function loadingHide() {
	document.getElementById("divshow").style.display = "none";
	$("#loadingWhenSave").remove();
    }
    
    function selectCheck(id, value) {
		var options = document.getElementById(id).options;
		for (var i = 0; i < options.length; i++) {
		    if (options[i].value == value) {
				options[i].selected = true;
				break;
		    }
		}
    }

    function closeWin() {
		$.ligerui.win.unmask();
		/* parent.$.ligerDialog.close(); //关闭弹出窗 */
		/* parent.$(".l-dialog,.l-window-mask").remove(); //直接移除，当第二次开启时无法开启*/
		parent.$(".l-dialog,.l-window-mask").css("display", "none");
    }
    
    (function() {
		 loadOptionBox = function (params) {
			var confs = {
				url: "",				//要访问的后台地址
				data: null,				//发送给后台的数据， 例如{nama:a,age:100}
				boxId: "selectId",			//要显示下拉列表select标签id
				optionValue: "optionValue_field",		//下拉列表option元素值在json对象中的属性名
				optionName: "--请选择--",	//对应的显示名在json对象中的属性名
				selectedValue: null		//选中的值
			};
			var conf = $.extend(confs, params);
			//异步加载所有菜单列表
			$.ajax({
			    type : "post", 
			    dataType : "json", //json格式的数据
			    async : true, //异步   不写的情况下 默认为true
			    url : rootPath + conf.url, 
			    data : conf.data,
			    success : function(data) {
					for (var i = 0; i < data.length; i++){
					    $("#" + conf.boxId).append("<option value='"+data[i][conf.optionValue]+"'>" + data[i][conf.optionName] + "</option>");
					}
					if (conf.selectedValue) {
					    selectCheck(conf.boxId, conf.selectedValue);
					}
			    }
			});
		 };
    })();
</script>
