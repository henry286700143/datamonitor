	/*
	表格控件
	调用方法：viewStarTable('table1', 'testPageInfo', 1);<div id="table1"></div>
	tableId:容器的ID
	URL:取业务数据的URL
	page:第几页，一般初始化时填
	*/
	function viewStarTable(tableId, URL, param, page) {
		var data = param + "&page=" + page + "&fresh=" + Math.random();
		cPage = page;
		$('#waiting').show();
		$.ajax({
			type : "post",
			url : URL,
			data : data,
			dataType : "json",
			asyn : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				$('#waiting').hide();
				//取数据数据后绘制表格
				drawViewStarTable(tableId, URL, param ,data, page);
			}
		});
	}

	// 图片加载时404的处理
	function imgError(image){
	    $(image).attr("src", "resources/evaluate/image/nopic.png");
	}
	
	/*
	根据一行的数据绘制需要显示的内容，是最基本的绘制单元，不同类型的行数据需要重写
	rowData:一行的数据，为键值对
	 */
	function drawViewStarRowData(rowData) {
		var width = ((document.body.clientWidth-230)/5)*0.8;
		var innerWidth = width*0.8;
		var scoreWidth = width/3;
		var paddingLeftWidth = width*0.1;

		if(rowData=='0'){
			var result="<div style=\"padding-left:"+paddingLeftWidth+"px;padding-top:15px;padding-right:"+paddingLeftWidth+"px;width:"+width+"px;height:350px;text-align:center;\">";
			result=result+"			 <\/div>";
			return $("'"+result+"'");
		}
		var time = rowData['time'];
		var picUrl = getPicURL(rowData['picUrl']);
		var gradeSign = rowData['gradeSign'];
		var playSign = rowData['playSign'];
		var expense = rowData['expense'];
		var rtg = rowData['rtg'];
		var shr = rowData['shr'];
		var classGradeSign;
		var classGradeSignText;
		var classPlaySign;
		var classPlaySignText;
		var classExpense;
		var classExpenseText;
		var rtgText;
		var shrText;
		if (gradeSign == -1) {
			classGradeSign='comment_dark';
			classGradeSignText = '-';
		}else{
			classGradeSign='comment_icon';
			classGradeSignText = gradeSign == null || typeof(gradeSign) == "undefined"?"-":gradeSign.substr(0,3);
		}
		// 是否报销过，1：已报销；0：未报销；-1：未评价
		if (expense == -1) {
			classExpense='finance_alldark';
			classPlaySignText = '';
		}else if (expense == 0) {
			classExpense='finance_dark';
			classPlaySignText = '';
		}else{
			classExpense='finance_light';
			classPlaySignText = '';
		}
		// 是否播出过，1：播出；0：未播出；-1：未评价
		if (playSign == -1) {
			classPlaySign='repetition_alldark';
			classExpenseText = '';
		}else if (playSign == 0) {
			classPlaySign='repetition_dark';
			classExpenseText = '';
		}else{
			classPlaySign='repetition_light';
			classExpenseText = '';
		}
		var classRTG='rtg_icon';
		if (rtg == -1) {
			rtgText = "收视率：-";
		}else{
			rtgText = "收视率："+rtg;
		}
		var classShr='shr_icon';
		if (shr == -1) {
			shrText = '市场份额：-';
		}else{
			shrText = '市场份额：'+shr;
		}
		if (picUrl == -1) {
			//没有图片，则用默认图片
			picUrl = 'resources/evaluate/image/nopic.png';
		} else {
			picUrl = getPlayURL('http://192.168.101.26:8080'+picUrl);
		}
		var result = "<div style=\"padding-left:"+paddingLeftWidth+"px;padding-top:15px;padding-right:"+paddingLeftWidth+"px;width:"+width+"px;height:350px;text-align:center;\">";
		result = result + "					<div style=\"cursor: pointer;position:relative;width:"+width+"px;height:240px;\" onclick=\"goDetailPage('" + time + "');\">";
		result = result + "						<img style=\"width:"+width+"px;height:240px;\" src=\""+picUrl+"\" onerror=\"imgError(this);\">";
		result = result + "						<div style=\"position:absolute;left:0px;bottom:0px;filter:alpha(opacity:30);opacity:1;width:"+width+"px;height:20px;line-height: 20px;background: black;color: white;\">" + (time.indexOf('00:00:00')!=-1?time.split(" ")[0]:time) + "<\/div>";
		result = result + "					<\/div>";
		result = result + "					<div style=\"bottom:0px;width:"+width+"px;height:30px;margin-top:10px\">";
		result = result + "						<span style=\"float:left;display:block;width:"+scoreWidth+"px;line-height:30px;height:30px;background:white;\">";
		result = result + "							<div class=\""+classGradeSign+"\" style=\"width:66px;line-height:29px;height:29px;\">"+classGradeSignText+"<\/div>";
		result = result + "						<\/span>";
		result = result + "						<span style=\"float:left;display:block;width:"+scoreWidth+"px;line-height:30px;height:30px;background:white;text-align: right;\">";
		result = result + "							<div class=\""+classExpense+"\" style=\"width:40px;line-height:29px;height:29px;\">"+classPlaySignText+"<\/div>";
		result = result + "						<\/span>";
		result = result + "						<span style=\"float:left;display:block;width:"+scoreWidth+"px;line-height:30px;height:30px;background:white;text-align: right;\">";
		result = result + "							<div class=\""+classPlaySign+"\" style=\"line-height:29px;height:29px;margin-top: 3px;\">"+classExpenseText+"<\/div>";
		result = result + "						<\/span>";
		result = result + "					<\/div>";
		result = result + "					<div style=\"bottom:0px;width:"+width+"px;height:30px;\">";
		result = result + "						<div style=\"float:left;display:block;width:"+width+"px;line-height:30px;height:30px;background:white;\">";
		result = result + "							<span class=\""+classRTG+"\" style=\"width:"+width+"px;line-height:20px;height:20px;text-align: left;padding-left: 25px;\">"+rtgText+"<\/span>";
		result = result + "						<\/div>";
		result = result + "					<\/div>";
		result = result + "					<div style=\"bottom:0px;width:"+width+"px;height:30px;\">";
		result = result + "						<div style=\"float:left;display:block;width:"+width+"px;line-height:30px;height:30px;background:white;\">";
		result = result + "							<span class=\""+classShr+"\" style=\"width:"+width+"px;line-height:20px;height:20px;text-align: left;padding-left: 25px;\">"+shrText+"<\/span>";
		result = result + "						<\/div>";
		result = result + "					<\/div>";
		result = result + "			 <\/div>";
		return $("'" + result + "'");
	}

	/*
	绘制表格
	tableId:容器的ID
	URL:取业务数据的URL
	data:表格显示所需数据，对应JAVA类pageInfo
	page:第几页，一般初始化时填1
	 */
	function drawViewStarTable(tableId, URL, param, data, page) {

		//表格分两部分，表格行显示和底部页码，tableContainer为总体容器
		var tableContainer = $('#'+tableId);
		tableContainer.empty();
		if(data['data'].length==0){
			$('#NoResult').show();
			return;
		}
		$('#NoResult').hide();
		tableContainer.addClass('viewStarTableContainer');
		//表格行显示，viewStarTablePannelRCContainer为行列式布局，需指定每行多少个perRowSize
		var viewStarTablePannelRCContainer = $('<table cellspacing=\"0\" cellpadding=\"0\"></table>');
		viewStarTablePannelRCContainer.addClass('viewStarTablePannelRCContainer');
		tableContainer.append(viewStarTablePannelRCContainer);
		//底部页码
		var viewStarTablePageFooter = $('<div id=\"viewStarTablePageFooter\"></div>');
		viewStarTablePageFooter.addClass('viewStarTablePageFooter');
		tableContainer.append(viewStarTablePageFooter);
		//当前页
		var curPage = data['curPage'];
		//上一页
		var prePage = data['prePage'];
		//下一页
		var nextPage = data['nextPage'];
		//每行多少条
		var perRowSize = data['perRowSize'];
		//每页多少条
		var perPageSize = data['perPageSize'];
		//底部页面显示状态，数字为页数，-1为省略号
		var pageStates = data['pageStates'];
		//当前页的下标，需要着重显示
		var curPageDisplay = data['curPageDisplay'];
		//表格数据
		var tableData = data['data'];
		var tr;
		//遍历表格行数据，进行行列式布局
		for ( var i = 0; i < perPageSize; i++) {
			//每行多少个，重新换一个TR
			if (i % perRowSize == 0) {
				tr = $('<tr></tr>');
			}
			var td = $('<td></td>');
			//传入本行数据，绘制具体显示
			var content;
			if(i<tableData.length){
				content = drawViewStarRowData(tableData[i]);
			}else{
				content = drawViewStarRowData('0');
			}
			td.append(content);
			tr.append(td);
			viewStarTablePannelRCContainer.append(tr);
		}
		var footerWidth = 40 * (pageStates.length + 2);
		tr = $('<tr></tr>');
		td = $('<td colspan=\"'+perRowSize+'\" style=\"padding-top:10px\"></td>');
		center = $('<center></center>');
		tr.append(td);
		td.append(center);
		center.append(viewStarTablePageFooter);
		viewStarTablePageFooter.css('width', footerWidth + 60);
		viewStarTablePannelRCContainer.append(tr);
		//页码开始的"<"上一页，以下所有能点击的块都调用重新生成表格界面的函数，并传入相应要跳转的页码
		var viewStarTablePageNumberPrePage;
		// 如果只有一页，则上一页不能点击
		if(pageStates.length == 1) {
			viewStarTablePageNumberPrePage = $("<div id=\"viewStarTablePageNumber\" ><</div>");
		} else {
			viewStarTablePageNumberPrePage = $("<div id=\"viewStarTablePageNumber\" onclick=\"viewStarTable('" + tableId + "','" + URL + "','" + param + "'," + prePage + ")\"><</div>");
		}
		viewStarTablePageNumberPrePage.addClass('viewStarTablePageNumber');
		viewStarTablePageFooter.append(viewStarTablePageNumberPrePage);
		for ( var i = 0; i < pageStates.length; i++) {
			if (pageStates[i] == '-1') {
				//省略号
				var viewStarTablePageNumber = $('<div id=\"viewStarTablePageNumber\">...</div>');
				viewStarTablePageNumber.addClass('viewStarTablePageNumber');
				viewStarTablePageFooter.append(viewStarTablePageNumber);
			} else {
				//当前页着重显示
				if (i == curPageDisplay) {
					var viewStarTablePageNumber = $("<div id=\"viewStarTablePageNumber\">" + pageStates[i] + "</div>");
					viewStarTablePageNumber.addClass('viewStarTablePageNumber');
					viewStarTablePageNumber.addClass('viewStarTablePageNumberCurPage');
					viewStarTablePageFooter.append(viewStarTablePageNumber);
				}else{
					var viewStarTablePageNumber = $("<div id=\"viewStarTablePageNumber\" onclick=\"viewStarTable('" + tableId + "','" + URL + "','" + param + "'," + pageStates[i] + ")\">" + pageStates[i] + "</div>");
					viewStarTablePageNumber.addClass('viewStarTablePageNumber');
					viewStarTablePageFooter.append(viewStarTablePageNumber);
				}

			}
		}
		//页码开始的"<"下一页
		var viewStarTablePageNumberNextPage;
		// 如果只有一页，则下一页不能点击
		if(pageStates.length == 1) {
			viewStarTablePageNumberNextPage = $("<div id=\"viewStarTablePageNumber\" >></div>");
		} else {
			viewStarTablePageNumberNextPage = $("<div id=\"viewStarTablePageNumber\" onclick=\"viewStarTable('" + tableId + "','" + URL + "','" + param + "'," + nextPage + ")\">></div>");
		}
		viewStarTablePageNumberNextPage.addClass('viewStarTablePageNumber');
		viewStarTablePageFooter.append(viewStarTablePageNumberNextPage);
	}

