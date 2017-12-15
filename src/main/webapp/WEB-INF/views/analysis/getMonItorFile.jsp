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
						   <label>内容</label>					
                           <input type="text" class="DataTableInput form-control" id="content" style="width:340px">               
					 </div>
				      <div class="form-group">
						    <button type="button" id="select_button" class="DataTableQueryButton form-btn btn btn-primary"  style="margin-top:21px;">查询</button>
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
	            	<th style="width:10%;text-align:center;"  dataField=TIME>日期</th>		
	            	<th style="width:10%;text-align:center;"  dataField=CONTENT>内容</th>
	            	<th style="width:10%;text-align:center;"  dataField=UPDATETIME>更新时间</th>
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
	        				  	"render" : function(data, type, row, meta){
	        				  		/* data = data.replace(";","<br/>") ; */
	        				  		data = data.replace(new RegExp(/(;)/g),'<br/>');
	        				  		return data;
	        				  	},
	        				  	"targets" : [1]
	          			   }   
      			]
		};
		var customConfig = {};
		initMyDataTableField('example');
		initMyDataTable('example', 'getMonItorFileInfo', config,customConfig);	
		example = tempMyDataTable;
	});
	$(document).ready(function() {	
		document.onkeydown = function() {
			if (event.keyCode == 13) {
				$('#select_button').click();
			}
		}
	});
	function getDetailInfo(data) {
		alert(data);
	}
	
</script>
</body>
</html>
