<!DOCTYPE> 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<html>
<head>
	<%@include file="resources.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
   html,body{height:100%;width:100%;}
   body{background-color:#ffffff;font-size:12px;font-family: "Helvetica Neue", "Luxi Sans", "DejaVu Sans", Tahoma, "Hiragino Sans GB", STHeiti, "Microsoft YaHei";}
   .panel-body{border:0px;}
   fieldset.scheduler-border{border: 1px groove black;padding: 0 1.4em 1.4em 1.4em;margin: 0 0 1.5em 0;}
   legend.scheduler-border{width:100px;border-bottom:0px;font-size:1.2em;font-weight: bold;}
   .form1 .form-group{float:left;margin-left:5px;}
   .form1 .form-group input{width:120px;}
   .form1 label{display:block;width:100px}
   .text-style{font-size:14px;font-weight:bold; }
   #displayData a{text-decoration:none;}
   
    .ie8 .input-group-addon{
    	float: left;padding: 6px 25px 26px 12px
    }
	.ie7 .form1 .form-group input{
		height:23px;
	}
		.input-group-addon{
		height:23px;
	}
</style>
<%
String resultFieldName = (String)request.getAttribute("resultFieldName");
%>
</head>
<body>

<div class="panel panel-success" style="padding:0px;border:0px;border-bottom:0px;margin: 10px;">
	<div class="panel-body" style="background-color:#FFF;">
		<fieldset  class="scheduler-border">
			<legend class="scheduler-border">查询条件设置</legend>
                <form class="form1 form-inline" >					  
					 <div class="form-group">
						   <label>起始行</label>					
                           <input type="text" class="DataTableInput form-control" id="startRow" style="width:340px">               
					 </div>  
					 <div class="form-group">
						   <label>终止行</label>					
                           <input type="text" class="DataTableInput form-control" id="endRow" style="width:340px">               
					 </div> 
					  <div class="form-group">
						    <button type="button" id="select_button"  class="DataTableQueryButton btn btn-primary" style="margin-top:21px;">查询</button>
				      </div>
                      <div class="form-group">
                          <label></label>
                          <ul class="nav navbar-nav">
                              <li class="dropdown">
                                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">数据导出 <b class="caret"></b></a>
                                  <ul class="dropdown-menu">
                                      <li><a href="#" onclick="exportFile('csv')">CSV文件</a></li>
                                      <li><a href="#" onclick="exportFile('xls')">Excel文件</a></li>
                                  </ul>
                              </li>
                              <li class="dropdown">
                                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">图表<b class="caret"></b></a>
                                  <ul class="dropdown-menu">
                                      <li><a href="#" onclick="getEchartData(getXAxisData(),getYAxisData(),'line')">折线图</a></li>
                                      <li><a href="#" onclick="getEchartData(getXAxisData(),getYAxisData(),'bar')">柱状图</a></li>
                                      <li><a href="#" onclick="getEchartData(getXAxisData(),getYAxisData(),'pie')">饼图</a></li>
                                  </ul>
                              </li>
                          </ul>
                      </div>
				</form>		
		</fieldset>
	  </div>
	  
	  <div id="xAxis" style="height: 35px;margin: 10px;">
	  	<span style="font-size:19px;float: left;line-height: 35px;">横轴</span>
	  </div>
	  <div id="yAxis" style="height: 35px;margin: 10px;">
	  	<span style="font-size:19px;float: left;line-height: 35px;">纵轴</span>
	  </div>
	
	  <table id="example" style="text-align:center;" class="display" cellspacing="0" >
	        <thead>
	            <tr id="resultField">		
	            	<!-- <th style="width:10%;text-align:center;"  dataField=VODNAME>片名</th> -->		
	            </tr>
	        </thead>
	</table>
	
</div>


<!-- 图标窗口 -->
<div class="modal fade" id="dataChartBox" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
			</div>
			<div>
				<!-- 折线图区域 -->
				<div id="chartContainer" style="width: 640px;height:480px;" ></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>
	
<script type="text/javascript">
	var example;
	var resultFieldName = <%=resultFieldName%>;
	$(document).ready(function() {
		loadResultField();
		var config = {
		 	"select": {
	            "style": 'single'
	        },
	        "columnDefs" : [ ]
		};
		var customConfig = {};
		initMyDataTableField('example');
		initMyDataTable('example', 'getResultField_Result', config, customConfig);	
		example = tempMyDataTable;
	});
	
	function loadResultField() {
		for(var i=0;i<resultFieldName.length;i++) {
			$('#resultField').append("<th style=\"width:10%;text-align:center;\"  dataField="+resultFieldName[i]['id']+">"+resultFieldName[i]['name']+"</th>");
			$('#xAxis').append("<span class=\"analyse-radio xAxis\" index="+i+">"+resultFieldName[i]['name']+"</span>");
			$('#yAxis').append("<span class=\"analyse-radio yAxis\" index="+i+">"+resultFieldName[i]['name']+"</span>");
		}
		
		var xAxisRadios = $(".xAxis");
		xAxisRadios.bind("click",function() {
			var thisRadio = $(this);
			xAxisRadios.removeClass("analyse-radio-select");
			thisRadio.addClass("analyse-radio-select");
		}); 
		
		var yAxisCheckBoxs = $(".yAxis");
		yAxisCheckBoxs.bind("click",function() {
			var thisCheckBox = $(this);
			if(thisCheckBox.hasClass("analyse-radio-select")){
				thisCheckBox.removeClass("analyse-radio-select");
			} else {
				thisCheckBox.addClass("analyse-radio-select");
			}
		}); 
	}
	
	function getXAxisData() {
		var xAxisSelected = $("#xAxis").children(".analyse-radio-select");
		if(typeof xAxisSelected == 'undefined'){
			return -1;
		}
		var index = xAxisSelected.attr("index");
		return index;
	}
	
	function getYAxisData() {
		var yAxisSelected = $("#yAxis").children(".analyse-radio-select");
	    var yAxisSelectedIndexs = new Array();
	    if(typeof yAxisSelected == 'undefined'){
			return yAxisSelectedIndexs;
		}
		for(var i=0;i<yAxisSelected.length;i++) {
			yAxisSelectedIndexs.push($(yAxisSelected[i]).attr("index"))
		}
		return yAxisSelectedIndexs;
	}
	
 	function exportFile(data) {
		var r=confirm("确定导出吗?");
		if (r==true) {
			$.ajax({
				type : "post",
				url : "exportFile",
				dataType : "text",
				data: 'type='+data,
				asyn : false,
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(data) {
					alert("导出成功！");
					window.location.href = "downloadFile?path="+data;
	 			}
			})
		}
	}  	
 	
 	function getEchartData(xAxisData,yAxisData,type){
 		if(xAxisData.length==0 || yAxisData.length==0) {
 			alert("请选择维度");
 			return ;
 		}
 		var table = $('#example').DataTable();
 		$('#dataChartBox').modal({});
 		$("#chartContainer").html("");
 		var myChart = echarts.init(document.getElementById('chartContainer'));
 		
 		var trigger;
 		if(type == 'line') {
 			trigger = 'axis';
 		} else{
 			trigger = 'item';
 		}
 		if(type=='pie') {
 			var yAxisSeriesList = new Array();
 			var yAxisSeries = new Object();
 			yAxisSeries['name'] = resultFieldName[yAxisData[0]]['name'];
 			yAxisSeries['type'] = type;
 			var result = new Array();
 			for(var i=0;i<table.column(xAxisData).data().length;i++) {
 				result[i] =  {value:table.column(yAxisData[0]).data()[i], name:table.column(xAxisData).data()[i]}
 			}
 			yAxisSeries['data'] = result;
 			yAxisSeriesList.push(yAxisSeries);
 		} else {
 			var yAxisSeriesList = new Array();
 	 		var legends = new Array();
 	 		for(var i=0;i<yAxisData.length;i++) {
 	 			var yAxisSeries = new Object();
 	 			yAxisSeries['name'] = resultFieldName[yAxisData[i]]['name'];
 	 			yAxisSeries['type'] = type;
 	 			yAxisSeries['data'] = table.column(yAxisData[i]).data();
 	 			yAxisSeriesList.push(yAxisSeries);
 	 			legends.push(yAxisSeries['name']);
 	 		}
 		}
 		var option = {
 			tooltip: {
 				trigger: trigger
 			},
 			legend: {
 				data:legends
 			},
 			xAxis: {
 				data: table.column(xAxisData).data()
 			}, 
 			yAxis : {},
 			series: yAxisSeriesList
 		};
 		myChart.setOption(option);
 	}
</script>
</body>
</html>
