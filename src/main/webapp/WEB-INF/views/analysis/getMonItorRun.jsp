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
   .general{-moz-border-radius:0px;-webkit-border-radius:0px;border-radius:0px;border:0px;border-bottom:0px;}
   .distances{margin:0px;padding:0px;}
   .list-group-item{border-left:0px;border-right:0px;padding:14px;}
   fieldset.scheduler-border{border: 1px groove black;padding: 0 1.4em 1.4em 1.4em;margin: 0 0 1.5em 0;}
   legend.scheduler-border{width:60px;border-bottom:0px;font-size:1.2em;font-weight: bold;}
   .form1 .form-group{float:left;margin-left:5px;}
   .form1 .form-group input{width:120px;}
   .form1 label{display:block;width:100px}
   .text-style{font-size:12px;font-weight:bold; }
   #displayData a{text-decoration:none;}
   .redTextColor{border-color: red;}
    .ie8 .input-group-addon{
    	float: left;padding: 6px 25px 26px 12px
    }
	.ie7 .form1 .form-group input{
		height:23px;
	}
		.input-group-addon{
		height:23px;
	}	
	.a{display:selected;}
</style>
</head>
<script type="text/javascript">
	var clickState = 0; //初始化点击状态
	function confirmRun() {
		var beginDate = document.getElementById("beginDate").value;
		var endDate = document.getElementById("endDate").value;
		if (beginDate == "" || endDate == "") {
			alert("请选择时间");
			return
		}
		if (clickState == 1) {
			alert("正在处理数据");
		} else {
			if (confirm("确定运行" + beginDate + "到" + endDate + "的数据吗?") == true) {
				clickState = 1;
				var data = "beginDate="+beginDate+"&endDate="+endDate;
				$.ajax({
					type: "post",
					url: "MonItorRun",
					data: data,
					dataType: "text",
					success: function(data) {
						clickState = 0
					}/* ,
					error: function(data) {
						console.log(data)
			            alert("处理失败");
			        } */
				});
			}
		}
	}
</script>
<body>

	<div class="panel panel-success" style="padding:0px;border:0px;border-bottom:0px;margin: 10px;">
	  <div class="panel-body" style="background-color:#FFF;">
		<fieldset class="scheduler-border">
			<legend class="scheduler-border">条件设置</legend>
                <form class="form1 form-inline">
					 <div class="form-group">
						   <label>开始时间</label>					
                           <div class="input-group input-append date form_datetime">
						      <input type="text" class="DataTableInput form-control" size="16" readonly id="beginDate" value=""/>
						      <div class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></div>
						   </div>          
					 </div>  					  
					 <div class="form-group">
						<label>结束时间</label>					
						 <div class="input-group input-append date form_datetime">
						      <input type="text" class="DataTableInput form-control" size="16" readonly id="endDate" value=""/>
						      <div class="input-group-addon" ><i class="glyphicon glyphicon-calendar"></i></div>
						 </div>
					  </div>	
				      <div class="form-group">
						    <button type="button" id="select_button" class="DataTableQueryButton form-btn btn btn-primary"  style="margin-top:21px;">查看</button>
				      </div>
				      <div class="form-group">
						    <button type="button" id="run_button" class="DataTableQueryButton form-btn btn btn-primary"  style="margin-top:21px;" onClick="confirmRun()">运行</button>
				      </div>					  
				</form>		
		</fieldset>
	  </div>
	</div>
	<script type="text/javascript">
	//日期样式
	$(".form_datetime").children(".form-control").datetimepicker({ 
		minView:"month",
		format: "yyyy-mm-dd",//显示格式
		todayBtn: true,//当前日期的按钮
		todayHighlight:true,//当前日前是否高亮
		language:'zh-CN', //语言选择
		autoclose: true //关闭时间的选择
		   });
	</script>
	  <table id="example" style="text-align:center;" class="display" cellspacing="0" >
	        <thead>
	            <tr>	 
	            	<th style="width:10%;text-align:center;"  dataField=fileName>文件名</th>		
	            	<th style="width:10%;text-align:center;"  dataField=state>处理状态</th>
	            	<th style="width:10%;text-align:center;"  dataField=startTime>开始时间</th>
	            	<th style="width:10%;text-align:center;"  dataField=finishTime>结束时间</th>
	            	<th style="width:10%;text-align:center;"  dataField=timeLength>处理时长</th>
	            </tr>
	        </thead>
	</table>

<script type="text/javascript">
	var isInitTable = false;
	var example;
	$(document).ready(function() {
		var config = {
			"select": {
	       		"style": 'single'
	        },
	        "columnDefs" : [
	        	{
			  		"render": function(data, type, row, meta) {
			  			data = data.replace(new RegExp(/(;)/g), '<br/>');
			  			return data;
			  		},
			  		"targets" : [1]
	          	}   
      		]
		};
		var customConfig = {};
		initMyDataTableField('example');
		initMyDataTable('example', 'getMonItorRunInfo', config, customConfig);
		example = tempMyDataTable;
	});
	$(document).ready(function() {	
		document.onkeydown = function() {
			if (event.keyCode == 13) {
				$('#select_button').click();
			}
		}
	});
</script>
</body>
</html>
