/**
 * hive页面方法
 */

/**
 * 点击查询
 */
function submitHive(){
	if(confirm("确定要开始执行吗？")){
		getFormValue();//获取表单值
		setExternalParamToTemp();//获取外部变量输入值
		var queryObj = new Object();
		for(var id in tempDataTableInputs) {
			queryObj[id] = tempDataTableInputs[id]['value'];
			if(id == 'userId' && queryObj[id] == '') {
				alert("用户id不能为空");
				return;
			}
	    }
		if( typeof(hiveProperty) != "undefined" ){
			queryObj["sqlConfig"] = hiveProperty["sql"];
			queryObj["sqlParam"] = hiveProperty["param"];
			queryObj["serviceID"] = hiveProperty["url"];
			queryObj["hiveBasePath"] = hiveProperty["path"];
			queryObj["csvFields"] = JSON.stringify(hiveProperty["csvFields"]);
			jQuery.post("localService",queryObj,function(data) {
			});
		}
	}
}

/**
 * 表格查询
 * @returns
 */
function getHiveFileTable() {
	if(typeof(datatable)!="undefined"){
		datatable.destroy();
	}
	datatable = $('#dataGird')
	.on('init.dt', function (){
		data['data'] = datatable.data();
	})
	.DataTable(pageConfig);
}

/**
 * 删除文件
 */
function delHiveFile(path,id) {
	if(confirm("确认删除吗？")){
		var data = 'hiveBasePath='+path+'&pid='+id;
		$.ajax({
			type : "post",
			url : "deleteHiveFile",
			data : data,
			dataType : "text",
			success : function(data) {
				if($.trim(data) == 'success') {
					getHiveFileTable();
				}
			}
		});
	}
}
