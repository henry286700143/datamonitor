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
</head>
<body>

<div class="panel panel-success" style="padding:0px;border:0px;border-bottom:0px;margin: 10px;">
	  <div class="panel-body" style="background-color:#FFF;">
		<fieldset  class="scheduler-border">
			<legend class="scheduler-border">查询条件设置</legend>
                <form class="form1 form-inline" >					  
					 <div class="form-group">
						   <label>标签名称</label>					
                           <input type="text" class="DataTableInput form-control" id="labelName" style="width:340px">               
					 </div>  
					  <div class="form-group">
						    <button type="button" id="addCargoBtn" class="btn btn-primary" onclick="clearTime()" style="margin-top:21px;">清空</button>
				      </div>
					  <div class="form-group">
						    <button type="button" id="select_button"  class="DataTableQueryButton btn btn-primary" style="margin-top:21px;">查询</button>
				      </div>
				      			  
				</form>		
		</fieldset>
	  </div>
	  <table id="example" style="text-align:center;" class="display" cellspacing="0" >
	        <thead>
	            <tr>	 
	            	<th style="width:10%;text-align:center;"  dataField=LABELNAME>名称</th>		
	            	<th style="width:10%;text-align:center;"  dataField=DELETE>删除</th>		
	            	<th style="width:10%;text-align:center;"  dataField=OPEN>打开</th>	
	            	<th style="display:none" dataField=LID></th>		
	            </tr>
	        </thead>
	</table>
</div>
<script type="text/javascript">
	var example;
	$(document).ready(function() {
		var config = {
		 	"select": {
	            "style": 'single'
	        },
	        "columnDefs" : [ 	
      				  {
      					  	"render" : function(data, type, row, meta){
      					  		data = data + "," + row['LID'] + "," + row['LABELNAME'] ;
      					  		return getDelete(meta, data, "deleteLabel");
      					  	},
      					  	"targets" : [1]
      				  },
      			      {
      			    	  	"render" : function(data, type, row, meta){
      			    	  		data = data + "," + row['LID'] ;
      			    	  		return getDetailInfomation(meta, data);
      			    	  	},
      			    	  	"targets" : [2]
      			      },
      	  			   {
      	             	  "targets": [ 3 ],
      	             	  "visible": false
      	               }
      			]
		};
		var customConfig = {};
		initMyDataTableField('example');
		initMyDataTable('example', 'getSOALabel_Result', config,customConfig);	
		example = tempMyDataTable;
	});
	function clearTime() {
		$("#labelName").val("");
	}
	
	function deleteLabel(data) {
		var lid = data.split(",")[1];
		var labelName = data.split(",")[2];
		var r=confirm("确定删除吗?");
		if (r==true) {
			$.ajax({
				type : "post",
				url : "deleteLabel",
				dataType : "text",
				data:'LID='+lid+'&LABELNAME='+labelName,
				asyn : false,
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(data) {
					alert("删除成功！");
					example.reDrawCurrentPage();
	 			}
			})
		}
	}
	
	 function getDetailLabel(data,label,url) {
		var lid = data.split(",")[1];
		

		$.ajax({
			type : "post",
			url : "detailLabel",
			dataType : "text",
			data:'LID='+lid,
			asyn : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				example.reDrawCurrentPage();
 			}
		})
	} 
	 function getDetailInfomation(meta,data){
			return "<p id=\""+meta['row']+"-"+meta['col']+"\" class=\"\" customvalue=\""+data+"\">"+"<a href=\"javascript:void(0)\" onclick=\"parent.addTab('"+data+"','SOAManager/getResultField')\">打开</a></p>";
		}

</script>
</body>
</html>
