<%@page import="com.opms.util.SessionKey"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/header.jsp"%>
<script type="text/javascript">
    var tab = null;
    var tree = null;
    var openMainPage = false;
    function addTabEvent(tabid, text, url) {
	tab.removeTabItem(tabid);
	tab.addTabItem({
	    tabid : tabid,
	    text : text,
	    url : url
	});
    }
    $(function() {
	var h = document.getElementById("bom").offsetTop;
	$("#myHomePage").css("height", h - 38);
	$("#leftTree").css("height", h - 40);
	$(".leaf .leaf_body").css("height", h / 2 - 24 - 38);
	$("#flow-btn").click(function() {
	    if (openMainPage) {
		$("#container").slideDown(500);
		$("#myHomePage").slideUp(500);
		openMainPage = false;
	    } else {
		$("#container").slideUp(500);
		$("#myHomePage").slideDown(500);
		openMainPage = true;
	    }
	});
	$("#frameCenter").ligerTab({
	    height : h - 38,
	    showSwitch : true,
	    ShowSwitchInTab : true
	});
	tab = $("#frameCenter").ligerGetTabManager();
	$.ajax({
	    async : false, //请勿改成异步，下面有些程序依赖此请数据
	    type : "POST",
	    url : '${ctx}/background/resource/resources.html?id=' +
<%=request.getSession()
					.getAttribute(SessionKey.USER_SESSION_ID).toString()%>
    ,
	    dataType : 'json',
	    success : function(json) {
		var ul = $("#menu");
		ul.html('');
		var li = '';
		var data = json.resourceTree;
		$.each(data, function(i) {
		    li += '<li class="level1"><a href="javascript:void(0)">' + data[i].name + '</a>';
		    li += '<ul class="level2" id="level2_'+i+'">';
		    var jdata = data[i].children;
		    $.each(jdata, function(j) {
			li += '<li><a href="${pageContext.request.contextPath}'+jdata[j].resUrl+'" id="level2_'+jdata[j].resKey+'" name="'+jdata[j].name+'">' + jdata[j].name + '</a></li>';
		    });
		    li += '</ul></li>';
		});
		ul.html(li);
	    }
	});

	$('a[id^="level2_"]').unbind('click').bind('click', function(e) {
	    var sid = this.id;
	    sid = sid.substring(sid.indexOf("level2_") + 7, sid.length);
	    var name = this.name;
	    var url = this.href;
	    addTabEvent(sid, name, url);
	    return false;
	});
	$("#operatorUI").click("click", function() {
	    parent.$.ligerDialog().open({
		width : 200,
		height : 200,
		url : rootPath + "/background/operator/chooseUI.html",
		title : "选择运营平台",
		isHidden : true
	    });
	});
    });
</script>
<style type="text/css">
.leaf .leaf_body {
	font-size: 12px;
}

.skin ul {
	margin: 0;
	padding-top: 5px;
	width: 380px;
}

.skin ul li {
	list-style-type: none;
	float: left;
	cursor: pointer;
	margin-top: 5px;
}

.skin ul li span {
	list-style-type: none;
	float: left;
	width: 20px;
	height: 20px;
	cursor: pointer;
}

.skin {
	height: 100%;
	background: #FAFAFA;
	border-bottom: solid 1px #cccc;
	top: 0;
	left: 0;
	width: 100%;
	overflow: hidden;
	text-overflow: ellipsis;
}

/*黑色*/
.black {
	background: #000;
}

/*白色*/
.white {
	background: #FFFFFF;
}

/*浅绿*/
.light_green {
	background: #8cc540;
}

/*深绿*/
.dark_green {
	background: #009f5d;
}

/*暗蓝*/
.dark_blue {
	background: #019fa0;
}

/*蓝色*/
.blue {
	background: #019fde;
}

/*深蓝*/
.shen_blue {
	background: #007cdc;
}

/*深紫*/
.dark_purple {
	background: #887ddd;
}

/*浅紫*/
.light_purple {
	background: #cd7bdd;
}

/*粉色*/
.pink {
	background: #ff5675;
}

/*红色*/
.red {
	background: #ff1244;
}

/*橙色*/
.orange {
	background: #ff8345;
}

/*黄色*/
.yellow {
	background: #f8bd0b;
}

/*灰色*/
.gray {
	background: #d1d2d4;
}

.img01 {
	background: url("${ctx}/images/img01.jpg");
}

.img02 {
	background: url("${ctx}/images/img02.jpg");
}

.img03 {
	background: url("${ctx}/images/img03.jpg");
}
</style>
<script type="text/javascript">
    $(document).ready(function() {
	//为了安全 google chrome 等浏览器是禁止本地文件写Cookie的即file:///F:/Lord%20community/test/Untitled-2.html这样的以file开头的是不能写本地文件的
	var cookieClass = getCookie('class');//读取需要缓存的对象。
	$("body").attr("class", cookieClass);//
	$(".skin_list li span").each(function() {
	    $(this).click(function() {
		var className = $(this).attr("class");//保存当前选择的类名
		$("body").attr("class", className, 30);//把选中的类名给body
		function SetCookie(name, value, day)//两个参数，一个是cookie的名子，一个是值
		{
		    var exp = new Date(); //new Date("December 31, 9998");
		    exp.setTime(exp.getTime() + day * 24 * 60 * 60 * 1000);
		    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
		}
		SetCookie("class", className, 30);
	    });
	});
    });
    function getCookie(name)//取cookies函数       
    {
	var nameEQ = name + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
	    var c = ca[i];
	    while (c.charAt(0) == ' ')
		c = c.substring(1, c.length);
	    if (c.indexOf(nameEQ) == 0)
		return c.substring(nameEQ.length, c.length);
	}
	return null;
    }
</script>
<!-- **********************************时钟显示开始********************************** -->
<style type="text/css">
#ClockDate {
	margin: 30px;
	padding: 10px 0px;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 36px;
	text-shadow: 0 0 5px #00c6ff;
}

#ClockTime {
	margin: 30px;
	width: auto;
}

#ClockTime span {
	font-size: 36px;
	font-family: Arial, Helvetica, sans-serif;
	text-shadow: 0 0 5px #00c6ff;
}

#point {
	padding-left: 5px;
	padding-right: 5px;
}
</style>
<script type="text/javascript">
    $(document).ready(function() {
	var monthNames = [ "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" ];
	var dayNames = [ "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" ];
	var newDate = new Date();
	newDate.setDate(newDate.getDate());
	$('#ClockDate').html(dayNames[newDate.getDay()] + " " + newDate.getDate() + ' ' + monthNames[newDate.getMonth()] + ' ' + newDate.getFullYear());
	setInterval(function() {
	    var seconds = new Date().getSeconds();
	    $("#sec").html((seconds < 10 ? "0" : "") + seconds);
	}, 1000);

	setInterval(function() {
	    var minutes = new Date().getMinutes();
	    $("#min").html((minutes < 10 ? "0" : "") + minutes);
	}, 1000);

	setInterval(function() {
	    var hours = new Date().getHours();
	    $("#hours").html((hours < 10 ? "0" : "") + hours);
	}, 1000);

    });
</script>
<!-- **********************************时钟显示结束********************************** -->
</head>

<body>
	<div class="header" id="header">
		<span id="flow-btn" style="z-index: 0">主页</span>
	</div>

	<div id="index_body">
		<div id="container">
			<div id="part1" class="leaf">
				<div class="leaf_body">
					<div class="skin">
						<div>
							<!-- 时钟显示 -->
							<div id="ClockDate"></div>
							<div id="ClockTime">
								<span id="hours"></span> <span id="point">:</span> <span id="min"></span> <span id="point">:</span> <span id="sec"></span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="part2" class="leaf">
				<div class="leaf_body">
					<div class="skin">
						<table>
							<tr>
								<td width="80px" valign="top" style="padding-top: 10px;">更换背景：</td>
								<td><ul class="skin_list">
										<li><span class="red"></span>&nbsp;红色&nbsp;</li>
										<li><span class="black"></span>&nbsp;黑色&nbsp;</li>
										<li><span class="white"></span>&nbsp;白色&nbsp;</li>
										<li><span class="gray"></span>&nbsp;灰色&nbsp;</li>
										<li><span class="yellow"></span>&nbsp;黄色&nbsp;</li>
										<li><span class="orange"></span>&nbsp;橙色&nbsp;</li>
										<li><span class="pink"></span>&nbsp;粉色&nbsp;</li>
										<li><span class="light_purple"></span>&nbsp;浅紫&nbsp;</li>
										<li><span class="dark_purple"></span>&nbsp;深紫&nbsp;</li>
										<li><span class="shen_blue"></span>&nbsp;深蓝&nbsp;</li>
										<li><span class="blue"></span>&nbsp;蓝色&nbsp;</li>
										<li><span class="dark_blue"></span>&nbsp;暗蓝&nbsp;</li>
										<li><span class="light_green"></span>&nbsp;浅绿&nbsp;</li>
										<li><span class="dark_green"></span>&nbsp;深绿&nbsp;</li>
									</ul></td>
							</tr>
							<tr>
								<td width="80px" valign="top" style="padding-top: 10px;">背景图片：</td>
								<td><ul class="skin_list">
										<li><span class="img02" style="width: 100px; height: 100px;"></span>&nbsp;</li>
										<li><span class="img03" style="width: 100px; height: 100px;"></span>&nbsp;</li>
										<li><span class="img01" style="width: 100px; height: 100px;"></span></li>
									</ul></td>
							</tr>
						</table>

					</div>

				</div>
			</div>
			<div id="part3" class="leaf">
				<div class="leaf_body">
					<div class="skin">
						<input type="text" id="popTxt" value="选择运营平台"/>
						<div id="btn1"></div>

						<script type="text/javascript">
			    $("#btn1").ligerButton({
				text : '获取值',
				click : function() {
				    var value = $.ligerui.get("popTxt").getValue();
				    alert(value);
				}
			    });

			    $("#popTxt").ligerPopupEdit({
					condition : {
					    prefixID : 'condtion_',
					    fields : [ {
						name : 'CompanyName',
						type : 'text',
						label : '公司名'
					    } ]
					},
					grid : getGridOptions(true),
					valueField : 'Country',
					textField : 'Country',
					width : 200
			    });

			    function getGridOptions(checkbox) {
				var options = {
				    columns : [ {
					display : '顾客',
					name : 'CustomerID',
					align : 'left',
					width : 100,
					minWidth : 60
				    }, {
					display : '公司名',
					name : 'CompanyName',
					minWidth : 120,
					width : 100
				    }, {
					display : '联系名',
					name : 'ContactName',
					minWidth : 140,
					width : 100
				    }, {
					display : '电话',
					name : 'Phone',
					width : 100
				    }, {
					display : '城市',
					name : 'City',
					width : 100
				    }, {
					display : '国家',
					name : 'Country',
					width : 100
				    } ],
				    switchPageSizeApplyComboBox : false,
				    data : $.extend({}, CustomersData),
				    pageSize : 10,
				    checkbox : checkbox
				};
				return options;
			    }
			</script>
					</div>
				</div>
			</div>
			<div id="part4" class="leaf">
				<div class="leaf_body">
					<div class="skin">
						<div style="font-size: 14px; text-align: left; padding: 10px;">
							公告栏：<br> 1、这里写介绍说明
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="myHomePage">
			<div id="leftTree">
				<jsp:include page="/menu.jsp"></jsp:include>
			</div>
			<div id="frameCenter">
				<div class="box-content" title="我的主页">
					<div style="font-size: 14px; color: red;">
						<p>
							欢迎进入主页<br>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="bom" id="bom">Copyright &copy; 2015 游戏光年版权所有</div>
</body>

</html>