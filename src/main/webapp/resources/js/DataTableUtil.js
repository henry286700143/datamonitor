function initDataTableNormal1() {
	$.extend(true, $.fn.dataTable.defaults, {
		"processing" : true,
		"serverSide" : true,
		"ordering" : false,
		"searching" : false,
		"language" : {
			"decimal" : "",
			"emptyTable" : "No data available in table",
			"info" : "第_START_条到第_END_条,共 _TOTAL_条",
			"infoEmpty" : "Showing 0 to 0 of 0 entries",
			"infoFiltered" : "(filtered from _MAX_ total entries)",
			"infoPostFix" : "",
			"thousands" : ",",
			"lengthMenu" : "每页显示_MENU_条",
			"loadingRecords" : "加载中...",
			"processing" : "数据处理中...",
			"search" : "Search:",
			"zeroRecords" : "没有查询到数据，请重新设置查询条件",
			"paginate" : {
				"first" : "第一页",
				"last" : "最后一页",
				"next" : "下一页",
				"previous" : "上一页"
			},
			"aria" : {
				"sortAscending" : ": activate to sort column ascending",
				"sortDescending" : ": activate to sort column descending"
			}
		}
	});
}
var tempMyDataTable;
var tempDataTableInputs = new Array();
function MyDataTable(tableID, URL, config, customConfig) {
	this.load = function() {
		var columns = initMyDataTableField(tableID);
		this.datatable = $("#" + tableID + "").DataTable({
			"ajax" : {
				"url" : URL + "?fresh=" + Math.random(),
				"type" : "POST",
				"data" : function(d) {
					for ( var i = 0; i < tempDataTableInputs.length; i++) {
						d[tempDataTableInputs[i]['id']] = tempDataTableInputs[i]['value'];
					}
				}
			},
			"columns":columns,
			"columnDefs":config['columnDefs'],
			"dom": '<"toolbar">frtip',
			"select":config['select']	
		});
		var toolbars = customConfig['toolbar'];
		if(typeof(toolbars)!="undefined"){
			for ( var i = 0; i < toolbars.length; i++) {
				var temp = toolbars[i];
				$("div.toolbar").append("<input id=\""+temp['text']+"\" type=\"button\" class=\"btn btn-primary\" value=\""+temp['text']+"\" onclick=\""+temp['getData']+"('"+tableID+"','"+temp['url']+"')\" style='width:80px'/>");
			}
		}
	},
	this.DataTableInputReload = function() {
		var DataTableInputs = $('.DataTableInput');
		for ( var i = 0; i < DataTableInputs.length; i++) {
			var tempInput = $(DataTableInputs[i]);
			var temp = new Array();
			temp['id'] = tempInput.attr('id');
			temp['value'] = tempInput.val();
			tempDataTableInputs.push(temp);
		}
	},
	this.reload = function() {
		this.datatable.destroy();
		this.load();
	},
	this.bindEvent = function() {
		$('.DataTableQueryButton').bind('click', function() {
			tempMyDataTable.DataTableInputReload();
			tempMyDataTable.reload();
		});
	};
	this.reDrawCurrentPage = function() {
		this.datatable.order( [[ 1, 'asc' ]] ).draw( false ); // 此处的order暂时没用
	};
}

function getFields(tableID){
	var tempFields = $("#"+tableID+" th[dataField]");
	var fields = new Array();
	for(var i =0;i<tempFields.length;i++){
		fields.push($(tempFields[i]).attr('dataField'));
	}
	return fields;
}

function AllJsonData(tableID, url){	
	var data = $('.DataTableRowInput');
	//alert($('.DataTableRowInput').length);
	var fields = getFields(tableID);
	var rows = new Array();
	for(var i =0;i<data.length;i++){
		var row = $(data[i]).attr('id').split('-')[0];
		var col = $(data[i]).attr('id').split('-')[1];
		var value = $(data[i]).val();
		if((typeof($(data[i]).attr("customvalue"))!="undefined")){
			value = $(data[i]).attr("customvalue");
		}
		if (rows[row] != undefined) {
			rows[row][fields[col]] = value;
		}else{
			rows[row] = new Object();
			rows[row][fields[col]] = value;
		}
	}
	//alert(JSON.stringify(rows));
	$.ajax({
		type : "post",
		url : url,
		dataType : "json",
		data:'data='+JSON.stringify(rows),
		asyn : false,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {

		}
	});
}
function initMyDataTableField(tableID){
	var fields = $("#"+tableID+" th[dataField]");
	var configColumns = new Array();
	for ( var i = 0; i < fields.length; i++) {
		var configColumnsItem = new Object();
		configColumnsItem['data']=$(fields[i]).attr('dataField');
		configColumns.push(configColumnsItem);
	}
	return configColumns;
}
function initMyDataTable(tableID, URL, config, customConfig) {
	tempMyDataTable = new MyDataTable(tableID,URL,config, customConfig);
	tempMyDataTable.DataTableInputReload();
	tempMyDataTable.load();
	tempMyDataTable.bindEvent();
}
function getSelect(meta,data,dataName){
	var items = dataTableSelectData[dataName];
	var select = "<select id=\""+meta['row']+"-"+meta['col']+"\" class=\"DataTableRowInput\" style=\"width:100%\">";
	for(var i = 0;i<items.length;i++){
		var selected = '';
		if(items[i]['value'] == data){
			selected = 'selected';
		}
		select = select + "<option id=\""+items[i]['id']+"\" "+selected+" style=\"width:100%\">"+items[i]['value']+"</option>";
	}
	select = select + "</select>";
	return select;
}
function getText(meta,data){
	return "<input id=\""+meta['row']+"-"+meta['col']+"\" class=\"DataTableRowInput\" type=\"text\" style=\"width:100%\" value=\""+data+"\"/>";
}

function getDelete(meta,data, functionName){
	return "<p id=\""+meta['row']+"-"+meta['col']+"\" class=\"\" customvalue=\""+data+"\">"+"<a href=\"javascript:void(0)\" onclick=\""+functionName+"('"+data+"');\">删除</a>"+"</p>";
}

function getAlter(meta,data, functionName){
	return "<p id=\""+meta['row']+"-"+meta['col']+"\" class=\"\" customvalue=\""+data+"\">"+"<a href=\"javascript:void(0)\" onclick=\""+functionName+"('"+data+"');\">修改</a>"+"</p>";
}

function getOpen(meta,data, functionName){
	return "<p id=\""+meta['row']+"-"+meta['col']+"\" class=\"\" customvalue=\""+data+"\">"+"<a href=\"javascript:void(0)\" onclick=\""+functionName+"('"+data+"');\">打开</a>"+"</p>";
}

function getDetail(meta,data, functionName){
	return "<p id=\""+meta['row']+"-"+meta['col']+"\" class=\"\" customvalue=\""+data+"\">"+"<a href=\"javascript:void(0)\" onclick=\""+functionName+"('"+data+"');\">详细信息</a>"+"</p>";
}

function getLabel(meta,data){
	return "<p id=\""+meta['row']+"-"+meta['col']+"\" class=\"DataTableRowInput\" customvalue=\""+data+"\">"+data+"</p>";
}
$(document).ready(function() {
	initDataTableNormal1();
});
function selectJsonData(tableID, url){
	var table = $('#'+tableID).DataTable();
	var data = table.rows('.selected').data();
	var fields = getFields(tableID);
	var rows = new Array();
	for(var i =0;i<data.length;i++){
		for(var j = 0;j<fields.length;j++) {
			if (rows[i] != undefined) {
				rows[i][fields[j]] = data[i][fields[j]];
			}else{
				rows[i] = new Object();
				rows[i][fields[j]] = data[i][fields[j]];
			}
		}
	}	
	$.ajax({
		type : "post",
		url : url,
		dataType : "json",
		data:'data='+JSON.stringify(rows),
		asyn : false,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		success : function(data) {
			alert("删除成功！");
			tempMyDataTable.DataTableInputReload();
			tempMyDataTable.reload();
		}
	});
}