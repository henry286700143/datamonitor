<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
</div>
</body>
</html>