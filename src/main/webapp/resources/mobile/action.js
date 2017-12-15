/**
 * 通用页面元素-手机版
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
		//数据填充
		for(var key in field.dimField) {
			$("#DAxCol").append("<div class=\"merge analyse-radio\" col=\""+key+"\" group='DAxCol'>"+field.dimField[key]+"</div>");
			$("#DAyCol").append("<div class=\"merge analyse-radio\" col=\""+key+"\" group='DAyCol'>"+field.dimField[key]+"</div>");
			$("#DAdataCol").append("<div class=\"merge analyse-radio\" col=\""+key+"\" group='DAdataCol'>"+field.dimField[key]+"</div>");
			$("#dimCol").append("<div class=\"dim analyse-checkbox\" col=\""+key+"\" group=\"dimCol\">"+field.dimField[key]+"</div>");
		}
		for(var key in field.dataField) {
			$("#dataCol").append("<div class=\"data analyse-checkbox\" col=\""+key+"\" group=\"dataCol\">"+field.dataField[key]+"</div>");
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
 * 下拉选择框动态获取数据
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
		if(tempInput.hasClass('query-dateinput')){
			temp['id'] = tempInput.attr('id');
			temp['type'] = "datebox";
			temp['value'] = tempInput.val();
		} else if(tempInput.attr("multiple") == "multiple") {//多选框
			temp['id'] = tempInput.attr('id');
			temp['type'] = "mulselect";
			var mulsel = "";
			$("#"+tempInput.attr('id') + " option").each(function() {
				if($(this).attr("selected") == "selected"){
					mulsel += $(this).attr("value")+",";
				}
			});
			temp['value'] = mulsel;
		} else {
			temp['id'] = tempInput.attr('id');
			temp['type'] = "textbox";
			temp['value'] = tempInput.val();
		}
		tempDataTableInputs.push(temp);
	}
	return tempDataTableInputs;
}

/**
 * 写入表单值
 * @param formObject 表单对象
 * @return 选项数组
 */
function setFormValue(formObject){
	for (var i = 0; i < formObject.length; i++) {
		if(formObject[i]["type"]=="textbox") {
			$("#"+formArray[i]["id"]).val(formObject[i]["value"]);
		}else if(formObject[i]["type"]=="mulselect") {
			$("#"+formArray[i]["id"]).combobox('setValues',formObject[i]["value"]);
		}else if(formObject[i]["type"]=="datebox"){
			("#"+formArray[i]["id"]).datebox('setValue',formObject[i]["value"]);
		}
	}
	return getFormValue();
}

function loading(status){
	var div='<div id="processLoading" style="width: 100%;height: 100%;line-height: 100%;background:white;text-align: center;color:#6c6c6c;position: absolute;top: 0;left: 0;margin:300px 0 0 0;">正在处理，请稍后……</div>';
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
		}
	});
}