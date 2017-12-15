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
    /* box */
	.box{position:absolute;width:80%;left:30%;height:auto;z-index:100;background-color:#fff;border:1px #ddd solid;padding:1px;}
	.box h2{height:25px;font-size:14px;background-color:#aaa;position:relative;padding-left:10px;line-height:25px;color:#fff;}
	.box h2 a{position:absolute;right:5px;font-size:12px;color:#fff;}
	.box .list{padding:10px;}
	.box .list li{height:24px;line-height:24px;}
	.box .list li span{margin:0 5px 0 0;font-family:"宋体";font-size:12px;font-weight:400;color:#ddd;}
	.title_left{ font-size: 21px;font-weight: 700; line-height: 1; color: #000;  filter: alpha(opacity=20); opacity: .2;}
	#bg{background-color:#666;position:absolute;z-index:99;left:0;top:0;display:none;width:100%;height:100%;opacity:0.5;filter: alpha(opacity=50);-moz-opacity: 0.5;}
	
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
						   <label>事实表</label>					
                           <input type="text" class="DataTableInput form-control" id="factName" style="width:340px">               
					 </div>  
					 <div class="form-group">
						   <label>模板</label>					
                           <input type="text" class="DataTableInput form-control" id="tempName" style="width:340px">               
					 </div>  
					  <div class="form-group">
						    <button type="button" id="addCargoBtn" class="btn btn-primary" onclick="clearTime()" style="margin-top:21px;">清空</button>
				      </div>
					  <div class="form-group">
						    <button type="button" id="select_button"  class="DataTableQueryButton btn btn-primary" style="margin-top:21px;">查询</button>
				      </div>
                      <div class="form-group" style="margin-left: 20px;">
                            <button type="button" id="add_button"  class="DataTableQueryButton btn btn-primary" onclick="addTemp()" style="margin-top:21px;">新增</button>
                      </div>
				</form>		
		</fieldset>
	  </div>
	  <table id="example" style="text-align:center;" class="display" cellspacing="0" >
	        <thead>
	            <tr>	 
	            	<th style="width:10%;text-align:center;" dataField=FACTNAME>事实表</th>		
	            	<th style="width:10%;text-align:center;" dataField=TEMPNAME>模板</th>		
	            	<th style="width:10%;text-align:center;" dataField=USERNAME>创建人</th>		
	            	<th style="width:10%;text-align:center;" dataField=DELETE>删除</th>		
	            	<th style="width:10%;text-align:center;" dataField=DELETE>修改</th>		
	            	<th style="display:none" dataField=FID></th>		
	            	<th style="display:none" dataField=TID></th>		
	            </tr>
	        </thead>
	</table>

</div>
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
				  		data = data + "," + row['FID'] + "," + row['TID'];
				  		return getDelete(meta, data, "deleteTempFT");
				  	},
				  	"targets" : [3]
  			   },{
				  	"render" : function(data, type, row, meta){
				  		data = data + "," + row['FACTNAME'] + "," + row['TEMPNAME']+ "," + row['FID'] + "," + row['TID'];
				  		return getAlter(meta, data, "alterTempFT");
				  	},
				  	"targets" : [4]
  			   },
  			   {
             	  "targets": [ 5 ],
             	  "visible": false
               },
               {
             	  "targets": [ 6 ],
             	  "visible": false
               }
  			]
		};
		var customConfig = {};
		initMyDataTableField('example');
		initMyDataTable('example', 'getTempFT_Result', config,customConfig);	
		example = tempMyDataTable;
	});
	
	function addTemp() {
	    $('#myModalLabel').html('新增模板');
        $('#addTempBox').modal({});
		//初始化fileinput控件（第一次初始化）
	  	initFileInput("uploadfile", "addTemplate");
	}
	
	function alterTempFT(data) {
		var factName = data.split(",")[1];
		var tempName = data.split(",")[2];
		var fid = data.split(",")[3];
		var tid = data.split(",")[4];
        $('#myModalLabel').html('修改模板');
        $('#addTempBox').modal({});
		//初始化fileinput控件（第一次初始化）
	  	initFileInput("uploadfile", "addTemplate?FACTNAME="+factName+"&TEMPNAME="+tempName+"&FID="+fid+"&TID="+tid);
	}
	//初始化fileinput控件（第一次初始化）
	function initFileInput(ctrlName, uploadUrl) {    
      	var success = '';
      	var failed = '';
      	var flag = new Set();
	    var control = $('#' + ctrlName); 
	    control.fileinput({
	        language: 'zh', //设置语言
	        uploadAsync: true, //默认异步上传
	        uploadUrl: uploadUrl, //上传的地址
	        maxFileCount: 5, //表示允许同时上传的最大文件个数
	        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
	        msgValidationError: '文件上传失败',
	        elErrorContainer: '#kv-error-1',
	        enctype: 'multipart/form-data',
	        validateInitialCount:true,
	        showCaption: false,//是否显示标题
	        initialPreviewAsData: true,
	        initialCaption:true,
	        dropZoneEnabled: false//是否显示拖拽区域
	    }).on("fileuploaded", function (event, data, previewId, index) {
                var obj = data.response;
                var status = JSON.parse(obj).uploaded;
                var name = JSON.parse(obj).name;
            	flag.add(status);
                if(status == 'ok') {
                	success = success + name + '上传成功<br />';
                } else if(status == 'repeat') {
                	failed = failed + name + '文件名重复，无法上传<br />';
                } else if(status == 'error') {
                	failed = failed + '文件上传错误<br />';
                }
                $('#uploadfile').fileinput('destroy');
        }).on('filepreupload', function(event, data, previewId, index) {
	    	data.form.append('fileType',$('#fileType').val());
	    }).on('fileclear', function() {
	    	$('#kv-success-1').html('');
	    	$('#kv-success-1').hide();
	    }).on('filebatchuploadcomplete',  function(event, data, previewId, index) {
	    	flag.forEach(function (item) {
	    		if(item == 'ok') {
			    	$('#kv-success-1').append(success);
                	$('#kv-success-1').fadeIn();
		    	} else {
			    	$('#kv-error-1').append(failed);
                	$('#kv-error-1').fadeIn();
		    	}
	    	});
        	$('#select_button').click();
        	success = '';
        	failed = '';
        	flag.clear();
	    });
	}
	
	$(function () {
	    //点击关闭按钮的时候，遮罩层关闭
	    $(".close").click(function () {
	        $("#bg,.box").css("display", "none");
	    });
	});
	
	function clearTime() {
		$("#factName").val("");
		$("#tempName").val("");
	}
	
	function deleteTempFT(data) {
		var fid = data.split(",")[1];
		var tid = data.split(",")[2];
		var r=confirm("确定删除吗?");
		if (r==true) {
			$.ajax({
				type : "post",
				url : "deleteTempFT",
				dataType : "text",
				data:'FID='+fid+'&TID='+tid,
				asyn : false,
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(data) {
					alert("删除成功！");
					example.reDrawCurrentPage();
	 			}
			})
		}
	}
</script>

<!-- 新增模态框 -->
<div class="modal fade" id="addTempBox" tabindex="-1" role="dialog"
	 aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">模板上传</h4>
			</div>
			<div style="width: 90%;margin: 10px auto;">
				<div id="kv-success-1" class="alert alert-success fade in" style="margin-top:10px;display:none"></div>
				<div id="kv-error-1" style="margin-top:10px;display:none"></div>
				<div>
					<input id="uploadfile" name="uploadfile"  type="file" multiple class="file-loading">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<%--<button type="button" class="btn btn-primary" onclick="saveLabelInterface()">保存</button>--%>
			</div>
		</div>
	</div>
</div>

</body>
</html>
