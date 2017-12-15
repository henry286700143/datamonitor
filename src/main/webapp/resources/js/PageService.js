/**
 * 通用页面元素
 * yangyue
 */

$(document).ready(function (){
	init();
	function init(){
		pageInit();
		checboxInit();
		radioInit();
		tabInit();
	}
	
	/**
	 * 页面初始化填充
	 */
	function pageInit(){
		if(typeof(field) != "undefined"){
			//数据填充
			if(typeof(field.dimField) != "undefined") {
				for(var key in field.dimField) {
					$("#DAxCol").append("<div class=\"merge analyse-radio\" col=\""+key+"\" group='DAxCol'>"+field.dimField[key]+"</div>");
					$("#DAyCol").append("<div class=\"merge analyse-radio\" col=\""+key+"\" group='DAyCol'>"+field.dimField[key]+"</div>");
					$("#DAdataCol").append("<div class=\"merge analyse-radio\" col=\""+key+"\" group='DAdataCol'>"+field.dimField[key]+"</div>");
					$("#dimCol").append("<div class=\"dim analyse-checkbox\" col=\""+key+"\" group=\"dimCol\">"+field.dimField[key]+"</div>");
				}
			}
			if(typeof(field.dataField) != "undefined") {
				for(var key in field.dataField) {
					$("#dataCol").append("<div class=\"data analyse-checkbox\" col=\""+key+"\" group=\"dataCol\">"+field.dataField[key]+"</div>");
				}
			}
		}
	}
	
	/**
	 * analyse-checkbox
	 * 通用多选按钮初始化
	 */
	function checboxInit(){
		var checkbox = $(".analyse-checkbox");
		for (var i = 0; i < checkbox.length; i++) {
			if($(checkbox[i]).attr("select") == "true"){
				$(checkbox[i]).addClass("analyse-checkbox-select");
			}
		}
		//点击事件
		$(".analyse-checkbox").bind('click',function(){
			if($(this).attr('select')=='true'){
				$(this).removeAttr('select');
				$(this).removeClass('analyse-checkbox-select');
			}else{
				$(this).attr('select','true');
				$(this).addClass('analyse-checkbox-select');
			}
		})
	}

	
	/**
	 * analyse-radio
	 * 通用多选按钮
	 */
	function radioInit(){
		//初始化
		var radio = $(".analyse-radio");
		for (var i = 0; i < radio.length; i++) {
			if($(radio[i]).attr("select") == "true"){
				$(radio[i]).addClass("analyse-radio-select");
			}
		}
		$('.analyse-radio').bind('click',function(){
			var radioGroup=$(this).attr('group');//获取单选组
			if($(this).attr('select')!='true'){
				$("[group='"+radioGroup+"']").removeAttr('select');
				$("[group='"+radioGroup+"']").removeClass('analyse-radio-select');
				$(this).attr('select','true');
				$(this).addClass('analyse-radio-select');
			}
		})
	}
	
	/**
	 * analyse-tab-radio analyse-tab-frame
	 * 标签页
	 */
	function tabInit(){
		//初始化
		var tab = $(".analyse-tab-radio");
		for (var i = 0; i < tab.length; i++) {
			if($(tab[i]).attr("select") == "true"){
				$(tab[i]).addClass("analyse-tab-radio-select");
				var tabGroup = $(tab[i]).attr("group");
				var tabTarget = $(tab[i]).attr("target");
				var tabFrame = $(".analyse-tab-frame[group='"+tabGroup+"']");
				for (var j = 0; j < tabFrame.length; j++) {
					if($(tabFrame[j]).attr("target") == tabTarget){
						$(tabFrame[j]).css("display","");
					}else{
						$(tabFrame[j]).css("display","none");
					}
				}
			}
		}
		$('.analyse-tab-radio').bind('click',function(){
			if($(this).attr('select')!='true'){
				var tabGroup = $(this).attr("group");//组
				$(".analyse-tab-radio[group='"+tabGroup+"']").removeAttr('select');
				$(".analyse-tab-radio[group='"+tabGroup+"']").removeClass('analyse-tab-radio-select');
				$(this).attr('select','true');
				$(this).addClass('analyse-tab-radio-select');
				//frame动作
				var tabTarget = $(this).attr("target");
				var tabFrame = $(".analyse-tab-frame[group='"+tabGroup+"']");
				for (var j = 0; j < tabFrame.length; j++) {
					if($(tabFrame[j]).attr("target") == tabTarget){
						$(tabFrame[j]).css("display","");
					}else{
						$(tabFrame[j]).css("display","none");
					}
				}
				//回调
				if($(this).attr('callback') != "" && $(this).attr('callback') != null) {
					eval($(this).attr('callback'));
				}
			}
		})
	}
	
})

/**
 * 通用多选按钮取值
 * @param group 组名
 * @param col 获取属性名
 * @returns Array
 */
function getChecbox(group,col) {
	var result = new Array();
	var checkboxGroup=$(".analyse-checkbox[group='"+group+"']");
	for (var i = 0; i < checkboxGroup.length; i++) {
		if($(checkboxGroup[i]).attr("select") == "true"){
			result.push($(checkboxGroup[i]).attr(col));
		}
	}
	return result;
}

/**
 * 通用单选按钮取值
 * @param group 组名
 * @param col 获取属性名
 * @returns String
 */
function getRadio(group,col) {
	var result = new Array();
	var radioGroup=$(".analyse-radio[group='"+group+"']");
	for (var i = 0; i < radioGroup.length; i++) {
		if($(radioGroup[i]).attr("select") == "true"){
			result=$(radioGroup[i]).attr(col);
		}
	}
	return result;
}

/**
 * 单项下拉选择框动态获取数据
 * @author liu
 */
function selectedAjaxDate(url,id,selected){
	var url1 = url.indexOf("?") == -1?url:url.substring(0,url.indexOf("?"));
	var data1 = url.indexOf("?") == -1?"":url.substring(url.indexOf("?")+1);
	$.ajax({
		type : "post",
		url : url1,
		data : data1,
		dataType : "text",
		success : function(data) {
			var dataJson = $.parseJSON(data);
			for(var p in dataJson){
				var temp = selected == dataJson[p].VALUE?"selected":"";
				$("#"+id).append("<option value='"+dataJson[p].ID+"' "+temp+">"+dataJson[p].VALUE+"</option>");
				
			}
		}
	});
}
/**
 * 多项下拉选择框动态获取数据
 * @author liu 
 */
function mulselectedAjaxDate(url,id,selected,value){
	var url1 = url.indexOf("?") == -1?url:url.substring(0,url.indexOf("?"));
	var data1 = url.indexOf("?") == -1?"":url.substring(url.indexOf("?")+1);
	var valueJson = $.parseJSON(value);
	$.ajax({
		type : "post",
		url : url1,
		data : data1,
		dataType : "text",
		success : function(data) {
			var dataJson = $.parseJSON(data);
			var json = new Array();
			
			for(var j=0,k=valueJson.length;j<k;j++){
				json.push({"ID":valueJson[j]['value'],"VALUE": valueJson[j]['text']});
				if(selected.indexOf(valueJson[j]['text']) != -1){
					$("#"+id).combobox("select", valueJson[j]['text']);
				}
			}
			for(var i=0,l=dataJson.length;i<l;i++){
				json.push({"ID":dataJson[i]['ID'],"VALUE": dataJson[i]['VALUE']});
				if(selected.indexOf(dataJson[i]['VALUE']) != -1){
					$("#"+id).combobox("select", dataJson[i]['VALUE']);
				}
		    }
			
			$("#"+id).combobox("loadData", json);
		}
	});
}
/**
 * 下拉选择框动态获取数据，带回传参数
 * @author liu
 */
function selGroupOption(id,url,groupid,valuesize){
	var obj = document.getElementById(id);
	var index = obj.selectedIndex;
	var value = obj.options[index].value;
	
	var groupobj = document.getElementById(groupid);
	var maxIndex = groupobj.length;
	
	$.ajax({
		type : "post",
		url : url,
		data : "id="+value,
		dataType : "text",
		success : function(data) {
			var dataJson = $.parseJSON(data);
			for(var i = valuesize; i < maxIndex;i++){
				$("#"+groupid+" option:last").remove();
			}
			for(var p in dataJson){
				$("#"+groupid).append("<option value='"+dataJson[p].ID+"'>"+dataJson[p].VALUE+"</option>");
			}
		}
	});
}

/**
 * 弹出窗
 * 保证页面首次加载效率，第一次弹出时加载默认数据
 * @param id id
 * @param url 内嵌跳转地址
 */
function createPopWindow(id,url){
	if( $('#'+id+'Frame').attr("src") == "#" ){
		document.getElementById(id+"Frame").src=url;
	}
	$('#'+id+'Window').window('open');
}
function closePopWindow(id){
	if( $('#'+id+'Frame').attr("src") == "#" ){
		document.getElementById(id+"Frame").src=url;
	}
	$('#'+id+'Window').window('open');
}

/**
 * 获取表单值,以数组形式写入formArray变量
 * @return 选项数组
 */
function getFormValue(){
	var DataTableInputs = $('.DataTableInput');
	for ( var i = 0; i < DataTableInputs.length; i++) {
		var tempInput = $(DataTableInputs[i]);
		var temp = new Object();
		if(tempInput.hasClass('easyui-combobox')){
			temp['type'] = "mulselect";
			temp['value'] = tempInput.combobox('getValues').join(",");
			tempDataTableInputs[tempInput.attr('id')]=temp;
		}else if(tempInput.hasClass('query-dateinput')){
			temp['type'] = "datebox";
			temp['value'] = tempInput.datebox('getValue');
			tempDataTableInputs[tempInput.attr('id')]=temp;
		} else if (tempInput.hasClass('query-jsoneditor')){
			temp['type'] = "jsoneditor";
			var json = eval(tempInput.attr('id')+"_editor").get();
			temp['value'] = JSON.stringify(json);
			tempDataTableInputs[tempInput.attr('id')]=temp;
		} else {
			temp['type'] = "textbox";
			temp['value'] = tempInput.val();
			tempDataTableInputs[tempInput.attr('id')]=temp;
		}
	}
	//mulquery数据
	var MulqueryInputs = $('.mulquery');
	var lastGroup = "";
	for ( var i = 0; i < MulqueryInputs.length; i++) {
		var tempInput = $(MulqueryInputs[i]);
		var temp = new Object();
		var groupName = tempInput.attr('group');
		if(lastGroup == "" || lastGroup != groupName){
			temp['type'] = "mulquery";
			temp['value'] = getRadio(groupName,"col");
			tempDataTableInputs[tempInput.attr('group')]=temp;
		}
		lastGroup = groupName;
	}
	return tempDataTableInputs;
}

/**
 * 写入表单值
 * @param formObject 表单对象
 * @return 选项数组
 */
function setFormValue(formObject){
	for ( var id in formObject ){
		if(formObject[id]["type"]=="textbox") {
			$("#"+id).val(formObject[id]["value"]);
		}else if(formObject[id]["type"]=="mulselect") {
			$("#"+id).combobox('setValues',formObject[id]["value"]);
		}else if(formObject[id]["type"]=="datebox"){
			$("#"+id).datebox('setValue',formObject[id]["value"]);
		}else if(formObject[id]["type"]=="jsoneditor"){
			eval(id+"_editor").set(JSON.parse(formObject[id]["value"]));
		}else if(formObject[id]["type"]=="externalParam"){
			try {
				if($('#'+id).hasClass('query-jsoneditor')) {
					eval(id+"_editor").set(eval('(' + formObject[id]["value"] + ')'));
				} else {
					$("#"+id).val(formObject[id]["value"]);
				}
			} catch (e) {
				
			}
		}else {
			try {
				$("#"+id).val(formObject[id]["value"]);
			} catch (e) {
				
			}
		}
	}
}

function loading(status){
	var div='<div id="processLoading" style="width: 180px;height: 30px;line-height: 30px;text-align: center;border: 2px solid #90c2f1;color:#6c6c6c;position: absolute;top: 50%;left: 50%;margin: -15px,0,0,-90px;">正在处理，请稍后……</div>';
	if(status){
		$("body").append(div);
	}else{
		$("#processLoading").remove();
	}
}

/**
 * 保存表单值
 * @param formObject 表单对象
 * @return 选项数组
 */
function saveformValue(table, type){
	var result = new Object();
	result['type'] = type;
	
	var variable2 = new Object();
	variable2['name'] = table;
	
	var map = new Array();
	
	var DataTableInputs = $('.ADDDataTableInput');
	for ( var i = 0; i < DataTableInputs.length; i++) {
		var tempInput = $(DataTableInputs[i]);
		var temp = new Object();
		if(tempInput.hasClass('easyui-combobox')){
			temp['id'] = tempInput.attr('id');
			temp['type'] = "mulselect";
			temp['value'] = "'"+tempInput.combobox('getValues').join(",")+"'";
		}else if(tempInput.hasClass('query-dateinput')){
			temp['id'] = tempInput.attr('id');
			temp['type'] = "datebox";
			temp['value'] = "to_date('"+tempInput.datebox('getValue')+"','yyyy-MM-dd')";
		} else {
			temp['id'] = tempInput.attr('id');
			temp['type'] = "textbox";
			temp['value'] = "'"+tempInput.val()+"'";
		}
		map.push(temp);
	}
	
	var column = new Array();
	var value = new Array();
	for(var p in map){
		column[p] = map[p].id;
		value[p] = map[p].value;
	}
	variable2['column'] = column;
	variable2['value'] = value;

	result['table'] = variable2;
	
	var str = JSON.stringify(result);
	$.ajax({
		type : "post",
		url : "update",
		data : "sqlJson="+str,
		dataType : "text",
		success : function(data) {
			alert("保存成功！");
			document.getElementById('light').style.display='none';
			clearformdata();
		}
	});
}
/**
 * 清空表单值
 */
function clearformdata(){
	var DataTableInputs = $('.ADDDataTableInput');
	for ( var i = 0; i < DataTableInputs.length; i++) {
		var tempInput = $(DataTableInputs[i]);
		var temp = new Object();
		if(tempInput.hasClass('easyui-combobox')){
			tempInput.combobox('getValues').clear;
		}else if(tempInput.hasClass('query-dateinput')){
			
		} else {
			tempInput.val("");
		}
	}
}
/**
 * 导入表单值
 * @param formObject 表单对象
 * @return 选项数组
 */
function importformValue(table, type, column){	
	var result = new Object();
	result['type'] = type;
	result['column'] = column;
	
	var variable2 = new Object();
	variable2['name'] = table;
	
	result['table'] = variable2;
	
	var str = JSON.stringify(result);

	$.ajax({
		type : "post",
		url : "query",
		data : "sqlJson="+str,
		dataType : "text",
		success : function(data) {
			
		}
	});
	document.getElementById('light').style.display='block';
}


/**
 * datatables加按钮
 * @param text 按钮文本
 * @param fun 调用函数string
 * @param href href方法
 */
function returnHtmlColItem(text,fun,href){
	//var html = "<div style=\"text-align:center\"><a href=\""+href+"\" onclick=\""+fun+"\" >" + text + "</a></div>";
	var html = "<a href=\""+href+"\" onclick=\""+fun+"\" >" + text + "</a>";
	return html;
}

/**
 * 通用页面模态框
 * @param title 标题
 * @param url 控制器url
 * @param width 宽度，为空全屏
 * @param height 高度，为空全屏
 */
function createModelBox(title,url,width,height){
	var fitMark = false;
	if(width == null || height == null || width == "" || height == "" ) {
		fitMark = true;
	}
	$('#commonModelBox').dialog({
	    title: title,
	    width: width,
	    height: height,
	    fit: fitMark,
	    closed: false,
	    cache: false,
	    href: url,
	    modal: true
	});
}

/**
 * 关闭模态框并刷新表格数据
 */
function closeRefresh() {
	try {
		datatableReload();
		parent.$('.DataTableQueryButton').click();
	} catch(e) {
		console.error('closeRefresh数据表格无法刷新:'+e);
	}
	closeModelBox();
	parent.closeModelBox();
}

/**
 * 关闭模态框
 */
function closeModelBox(){
	$('#commonModelBox').dialog('close');
}

/**
 * 删除请求调用
 * @param url 控制器url
 * @param obj 参数对象或json字符串
 * 	{'key':'value'} 或 "{'key':'value'}"
 */
function commonDelRequest(url,obj){
	if(typeof(obj) == 'string') {
		obj = eval(obj);
	}
	$.messager.confirm('Confirm','确认删除吗？',function(r){
	    if (r){
	        $.post(url,obj,function(result){
	        	result = $.trim(result);
	            if (result == "success"){
	            	$.messager.show({
	                    title: '提示',
	                    msg: '删除成功'
	                });
	            	datatableReload();
	            } else {
	                $.messager.show({// 错误框
	                    title: '错误',
	                    msg: '删除失败'
	                });
	            }
	        },'text');
	    }
	});
}

/**
 * 文件下载请求调用,从主表单获取数据
 * @param sqlconfig 下载做需的sql
 * @param message 弹出消息，可为空
 */
function fileDownloadByFromRequest(sqlconfig,title,message) {
	try {
		getFormValue();
	} catch(e) {
		console.error('fileDownloadRequest数据无法加载:'+e);
	}
	if(message != null & message !='') {
		if(!confirm(message)) {
			return;
		}
	}
	var data = "";
	var sqlParam = "sqlParam=";
	for ( var id in tempDataTableInputs ){
		data = data + id + '=' + tempDataTableInputs[id]['value'] + '&';
		sqlParam = sqlParam + id + ',';
	}
	data = data + "&fresh="+Math.random();
	sqlParam = sqlParam.substring(0,sqlParam.length - 1);
	
	$.ajax({
		type : "post",
		url : "http://127.0.0.1:8080/damsservice/service?serviceID=/downLoadService/downloadBy&"+sqlconfig+"&"+sqlParam+"&title="+title,
		data : data,
		dataType : "text",
		success : function(result) {
			if(result == '0'){
				alert('查询无数据！');
			}else{
				window.location="downloadCommon?path="+result;
			}
		}
	});
}

/**
 * 文件下载请求调用,用户自定义参数
 * @param url 控制器url
 * @param message 弹出消息，可为空
 * @param param 参数对象或json字符串
 * 	{'key':'value'} 或 "{'key':'value'}"
 *  
 */
function fileDownloadByParamRequest(url,message,param) {
	if(typeof(param) == 'string') {
		param = eval(param);
	}
	if(message != null & message !='') {
		if(!confirm(message)) {
			return;
		}
	}
	$("#commonDownloadForm").empty();
	$("#commonDownloadForm").attr("action",url);
	for (var key in param) {
		$("#commonDownloadForm").append('<input type="hidden" name="'+ key +'" value="'+ param[key] +'" />');
	}
	$("#commonDownloadForm").submit();
}

/**
 * commonFormSubmit数据提交
 * @param url 控制器名
 * @param parentParam 是否继承父框架数据
 */
function commonFormSubmit(url,parentParam){
	getFormValue();//获取表单值
	setExternalParamToTemp();//获取外部变量输入值
	if(parentParam){
		mergeObj(tempDataTableInputs,parent.tempDataTableInputs);
	}
	//数据加载
	var d = new Object();
	for ( var id in tempDataTableInputs ){
		d[id] = tempDataTableInputs[id]['value'];
    }
	jQuery.post(url,d,function(data) {
		if(data == 0){
			closeRefresh();
		}else{
			alert(data);
		}
	});
	//关闭模态框并刷新数据
}


function mergeObj(o1,o2){
    for(var key in o2){
        o1[key]=o2[key]
    }
}

/**
 * 修改功能数据加载
 */
function addUpdateValue(table,type,columnStr){

	var result = new Object();
	result['type'] = type;
	
	var map = new Array();
	
	var DataTableInputs = $('.DataTableInput');
	for ( var i = 0; i < DataTableInputs.length; i++) {
		var tempInput = $(DataTableInputs[i]);
		var temp = new Object();
		if(tempInput.hasClass('easyui-combobox')){
			temp['id'] = tempInput.attr('id');
			temp['type'] = "mulselect";
			temp['value'] = "'"+tempInput.combobox('getValues').join(",")+"'";
		}else if(tempInput.hasClass('query-dateinput')){
			temp['id'] = tempInput.attr('id');
			temp['type'] = "datebox";
			temp['value'] = "to_date('"+tempInput.datebox('getValue')+"','yyyy-MM-dd')";
		} else {
			temp['id'] = tempInput.attr('id');
			temp['type'] = "textbox";
			temp['value'] = "'"+tempInput.val()+"'";
		}
		map.push(temp);
	}
	
	var column = new Array();
	var value = new Array();
	
	if(type == 'insert'){
		for(var p in map){
			column[p] = map[p].id;
			value[p] = map[p].value;
		}
		
		var variable2 = new Object();
		
		variable2['name'] = table;
		variable2['column'] = column;
		variable2['value'] = value;

		result['table'] = variable2;
	}else if(type == 'update'){
		var map1 = new Array();
		var flag = 0;
		for(var p in map){
			if(columnStr.indexOf(map[p].id) == -1){
				column[flag] = map[p].id;
				value[flag] = map[p].value;
				flag++;
			}else{
				var temp1 = new Object();
				temp1['column'] = map[p].id;
				temp1['value'] = map[p].value;
				map1.push(temp1);
			}
		}
		
		var variable2 = new Object();
		
		variable2['column'] = column;
		variable2['value'] = value;

		var variable5 = new Object();
		variable5['and'] = map1;
		
		var variable3 = new Object();
		variable3['name'] = table;
		variable3['where'] = variable5;
		
		result['table'] = variable3;
		result['set'] = variable2;
	}
	
	var str = JSON.stringify(result);

	$.ajax({
		type : "post",
		url : "update",
		data : "sqlJson="+str,
		dataType : "text",
		success : function(data) {
			alert("保存成功！");
			closeRefresh();
			clearformdata();
		}
	});
}

/**
 * 通用表格数据预加载
 */
function setCommonFormByUrl(){
	try {
		if(typeof(initializationUrl) != 'undefined'){
			var	initializationData = new Object();
			if(externalParam != ''){
				initializationData = eval('(' + externalParam + ')');
			}
			jQuery.post(initializationUrl, initializationData, function(data) {
				data = initializationParalChange(data);
				for (var key in data){ 
					var temp = new Object();
					temp['type'] = "externalParam";
					temp['value'] = data[key];
					tempDataTableInputs[key]=temp;
				}
				setFormValue(tempDataTableInputs);
			}
			, "json")
		}
	} catch(e) {
		console.error('通用表格数据预加载错误');
	}
}

function initializationParalChange(data) {
	var initializationParalObj= new Object();
	var result = new Object();
	if(typeof(initializationParal) != 'undefined'){
		initializationParalObj = eval('(' + initializationParal + ')');
		for (var key in data){
			var key0 = key;
			for (var k in initializationParalObj) {
				if(key == k){
					key0 = initializationParalObj[k];
				}
			}
			result[key0]=data[key];
		}
	}
	return result;
}

/**
 * 放置外部变量进入tempDataTableInputs
 */
function setExternalParamToTemp(){
	try {
		if(externalParam != '') {
			var externalParamObj = eval('(' + externalParam + ')');
			if(typeof(externalParamObj) == 'object' && isNaN(externalParamObj.length)) {//不是数组
				for (var id in externalParamObj ){
					var temp = new Object();
					temp['type'] = "externalParam";
					temp['value'] = externalParamObj[id];
					tempDataTableInputs[id]=temp;
				}
			} else if(typeof(externalParamObj) == 'object') {
				for (var i = 0; i < externalParamObj.length; i++) {
					var temp = new Object();
					temp['type'] = externalParamObj[i]['type'];
					temp['value'] = externalParamObj[i]['value'];
					tempDataTableInputs[externalParamObj[i]['id']]=temp;
				}
			}
		}
	} catch(e) {
		console.error('外部传入数据无法加载');
	}
}

/**
 * 收集所有变量进入tempDataTableInputs
 */
function collectAllConditions(){
	getFormValue();
	setExternalParamToTemp();
}

