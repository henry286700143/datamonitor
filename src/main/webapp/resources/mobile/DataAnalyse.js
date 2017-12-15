/**
 * 数据分析项-手机版
 * @param tempData
 * @param row
 * @param col
 * @param dataCol
 */
function dataAnalyse(tempData,row,col,dataCol){
	var rows = new Array();
	var cols = new Array();
	var rowsMap = new Object();
	var colsMap = new Object();
	var colrowMap = new Object();
	for ( var i = 0; i < tempData.length; i++) {
		if(typeof(rowsMap[tempData[i][row]]) == 'undefined'){
			rowsMap[tempData[i][row]] = '1';
			rows.push(tempData[i][row]);
		}
		if(typeof(colsMap[tempData[i][col]]) == 'undefined'){
			colsMap[tempData[i][col]] = '1';
			cols.push(tempData[i][col]);
		}
		var key = tempData[i][row]+ tempData[i][col];

		if(typeof(colrowMap[key]) == 'undefined'){
			colrowMap[key] = tempData[i][dataCol];
		}else{
			colrowMap[key] = parseFloat(colrowMap[key]) + parseFloat(tempData[i][dataCol]);
		}

	}

	var newData = new Array();
	for ( var i = 0; i < rows.length; i++) {
		var newObject = new Object();
		newObject[row] = rows[i];
		for ( var j = 0; j < cols.length; j++) {
			var key = rows[i]+ cols[j];
			if(typeof(colrowMap[key]) != 'undefined'){
				newObject[cols[j]] = colrowMap[key];
			}else{
				newObject[cols[j]] = 0;
			}
		}
		newData.push(newObject);

	}
	/* var tempSortObject = new Array();
			for ( var i = 0; i < newData.length; i++) {
				for ( var j = i+1; j < newData.length; j++) {
					if(newData[i][row] < newData[j][row]){
						alert(1);
						tempSortObject = newData[i][row];
						newData[i][row] = newData[j][row];
						newData[j][row] = tempSortObject;
					}
				}
			}
 */


	var columns = new Array();
	/* var newObject1 = new Object();
	newObject1['data'] = row;
	columns.push(newObject1); */
	var columns = new Array();
	for ( var i = 0; i < cols.length; i++) {
		var newObject = new Object();
		newObject['data'] = cols[i];
		newObject['title'] = cols[i];
		columns.push(newObject);
	}
	columns.sort(function(a,b){return a['title']-b['title'];})
	var newObject1 = new Object();
	newObject1['title'] = '时间';
	newObject1['data'] = row;
	columns.splice(0, 0, newObject1);


	if(typeof(datatableAnalyze)!="undefined"){
		datatableAnalyze.destroy();
		datatableAnalyze = null;
		$('#analyzeDataGird').empty();
	}
	var c ={
		"ordering" : true,
		"serverSide" : false,
	    "data": newData,
	    "columns":columns,
	    "scrollX": true//方向滚动
	};
	datatableAnalyze = $('#analyzeDataGird').DataTable(c);

	var tempField = new Object();
	var tempSelectField  = new Object();

	var tempDimField = new Object();
	var tempDataField = new Object();
	var tempFieldType = new Object();

	tempField['dimField'] = tempDimField;
	tempField['dataField'] = tempDataField;
	tempField['fieldType'] = tempFieldType;


	var tempSelectCal = new Array();

	var tempSelectDimField = new Array();
	var tempSelectDataField = new Array();

	tempSelectField['dimField'] = tempSelectDimField;
	tempSelectField['dataField'] = tempSelectDataField;
	tempSelectField['merge'] = "1";
	tempSelectCal.push("sum");
	tempSelectField['cal'] = tempSelectCal;

	for ( var i = 0; i < columns.length; i++) {
		tempField['dimField'][columns[i]['data']] = columns[i]['data'];
		tempField['dataField'][columns[i]['data']] = columns[i]['data'];
	  if(i == 0){
		 tempField['fieldType'][columns[i]['data']] = 'string';
		 tempSelectField['dimField'].push(columns[i]['data']);
	  }else{
		 tempField['fieldType'][columns[i]['data']] = 'num';
		 tempSelectField['dataField'].push(columns[i]['data']);
	  }
	}
	var tempNewData = new Object();
	tempNewData['data'] = newData;
	$('#dataChartContainer').empty();
	initChart('dataChartContainer', tempNewData, tempField, tempSelectField);
}

/**
 * 多维分析项
 * @param chartSelectField
 */
function drawDimensionChart(chartSelectField){
    $('#chartContainer').empty();
	if(chartSelectField['merge']=="1"){
		initChart('chartContainer',data, field, chartSelectField);
	}
	if(chartSelectField['merge']=="2"){
		var selectDimField = chartSelectField['dimField'];
		for(var i = 0;i<selectDimField.length;i++){
			var tempSelectField = new Object();
			var tempDimField = new Array();
			tempDimField.push(selectDimField[i]);
			tempSelectField['dimField']=tempDimField;
			tempSelectField['dataField']=chartSelectField['dataField'];
			tempSelectField['merge']=chartSelectField['merge'];
			tempSelectField['cal']=chartSelectField['cal'];
			initChart('chartContainer',data, field, tempSelectField);
		}
	}
};

/**
 * 多维分析数据准备
 * @param chartId
 * @param chartData
 * @param chartField
 * @param chartSelectField
 */
function initChart(chartId, chartData, chartField, chartSelectField){
	var originData = chartData['data'];
	var tempData = new Array();
	var dimField = chartField['dimField'];
	var dataField = chartField['dataField'];
	var fieldType = chartField['fieldType'];
	var cal = chartSelectField['cal'];

	var selectDimField = chartSelectField['dimField'];
	var selectDataField = chartSelectField['dataField'];
	var tempObjectMap = new Object();
	var tempAvgMap= new Object();
	var tempMinMap = new Object();
	var tempMaxMap = new Object();

	for(var i = 0;i<originData.length;i++){
		var tempObject = new Object();
		var tempAvg = new Object();
		var tempMin = new Object();
		var tempMax = new Object();
		var flag = true;
		var tempName = '';
		for(var j = 0;j<selectDimField.length;j++){
			tempName = tempName + "-"+ originData[i][selectDimField[j]];
		}

		if(typeof(tempObjectMap[tempName]) == 'undefined'){
			for(var j = 0;j<selectDimField.length;j++){
				tempObject[selectDimField[j]]=originData[i][selectDimField[j]];
			}
			for(var j = 0;j<selectDataField.length;j++){
				if(fieldType[selectDataField[j]]=='num'){
					tempObject[selectDataField[j]]=originData[i][selectDataField[j]];
					tempMin[selectDataField[j]] = tempObject[selectDataField[j]];
					tempMax[selectDataField[j]] = tempObject[selectDataField[j]];
				}else if(fieldType[selectDataField[j]]=='string'){
					tempObject[selectDataField[j]]="1";
					tempMin[selectDataField[j]] = 1;
					tempMax[selectDataField[j]] = 1;
				}
				tempAvg[selectDataField[j]] = 1;
			}
			tempData.push(tempObject);
			tempObjectMap[tempName] = tempObject;
			tempAvgMap[tempName] = tempAvg;
			tempMinMap[tempName] = tempMin;
			tempMaxMap[tempName] = tempMax;

		}else{
			for(var j = 0;j<selectDataField.length;j++){
				var curValue = parseFloat(tempObjectMap[tempName][selectDataField[j]]);
				if(fieldType[selectDataField[j]]=='num'){
					tempObjectMap[tempName][selectDataField[j]]= parseFloat(tempObjectMap[tempName][selectDataField[j]]) +parseFloat(originData[i][selectDataField[j]]);
					tempMinMap[tempName][selectDataField[j]] =  curValue<parseFloat(tempMinMap[tempName][selectDataField[j]])?curValue:parseFloat(tempMinMap[tempName][selectDataField[j]]);
					tempMaxMap[tempName][selectDataField[j]] =  curValue>parseFloat(tempMaxMap[tempName][selectDataField[j]])?curValue:parseFloat(tempMaxMap[tempName][selectDataField[j]]);
				}else if(fieldType[selectDataField[j]]=='string'){
					tempObjectMap[tempName][selectDataField[j]]= parseFloat(tempObjectMap[tempName][selectDataField[j]]) +1;
				}
				tempAvgMap[tempName][selectDataField[j]] =  parseFloat(tempAvgMap[tempName][selectDataField[j]])+1;
			}
		}
	}
	
	var legend = new Array();
	for(var i = 0;i<selectDataField.length;i++){
		legend.push(dataField[selectDataField[i]]);
	}

	var xAxis = new Array();
	var xAxisData = new Object();
	xAxisData['type'] = 'category';
	xAxisData['boundaryGap'] = false;
	var xAxisArrayData = new Array();
	for(var i = 0;i<tempData.length;i++){
		var tempName = '';
		for(var j = 0;j<selectDimField.length;j++){
			if(j == 0){
				tempName = tempData[i][selectDimField[j]];
			}else{
				tempName = tempName + "/" + tempData[i][selectDimField[j]];
			}
		}
		xAxisArrayData.push(tempName);
	}
	xAxisData['data'] = xAxisArrayData;
	xAxis.push(xAxisData);

	var series = new Array();
	for(var i = 0;i<cal.length;i++){
		for(var j = 0;j<selectDataField.length;j++){
			var seriesData = new Object();
			var calName = '';
			if(cal[i] == 'avg'){
				calName = '平均值';
			}else if(cal[i] == 'min'){
				calName = '最小值';
			}else if(cal[i] == 'max'){
				calName = '最大值';
			}

			seriesData['name'] = dataField[selectDataField[j]]+calName;
			seriesData['type'] = 'line';
			var dataArray = new Array();
			var newTempData = new Array();

			for(var k = 0;k<tempData.length;k++){
				var newTempObject = new Object();
				for(var m = 0;m<selectDimField.length;m++){
					newTempObject[selectDimField[m]] = tempData[k][selectDimField[m]];
				}
				for(var m = 0;m<selectDataField.length;m++){
					newTempObject[selectDataField[m]] = tempData[k][selectDataField[m]];
				}
				newTempData.push(newTempObject);
			}

			for(var k = 0;k<newTempData.length;k++){
				var newTempObject = new Object();
				for(var m = 0;m<selectDimField.length;m++){
					newTempObject[selectDimField[m]] = newTempData[k][selectDimField[m]];
				}
				for(var m = 0;m<selectDataField.length;m++){
					newTempObject[selectDataField[m]] = newTempData[k][selectDataField[m]];
				}
				var tempName = '';
				for(var m= 0;m<selectDimField.length;m++){
					tempName = tempName + "-"+ newTempData[k][selectDimField[m]];
				}

				if(cal[i] == 'avg'){
					newTempObject[selectDataField[j]] = parseFloat(newTempObject[selectDataField[j]])/parseFloat(tempAvgMap[tempName][selectDataField[j]]);
				}else if(cal[i] == 'min'){
					newTempObject[selectDataField[j]] = parseFloat(tempMinMap[tempName][selectDataField[j]]);
				}else if(cal[i] == 'max'){
					newTempObject[selectDataField[j]] = parseFloat(tempMaxMap[tempName][selectDataField[j]]);
				}
				dataArray.push(newTempObject[selectDataField[j]]);

			}

			seriesData['data'] = dataArray;
			series.push(seriesData);
		}
	}
	//排序
	var sortData;
	var xString = "";
	for (var i = 0; i < xAxis[0]['data'].length; i++){
		for (var j = 0; j < xAxis[0]['data'].length-i-1; j++){
			if (xAxis[0]["data"][j] > xAxis[0]["data"][j + 1]){
				xString = xAxis[0]["data"][j + 1];
				xAxis[0]["data"][j + 1] = xAxis[0]["data"][j];
				xAxis[0]["data"][j] = xString;
				for(var k=0;k<series.length;k++){
					sortData = series[k]["data"][j + 1];
					series[k]["data"][j + 1] = series[k]["data"][j];
					series[k]["data"][j] = sortData;
				}					
			}
		}
	} 
	
	//四舍五入
	for(var i=0;i<series.length;i++){
		for(var j;j<series[i]["data"].length;j++){
			if(checkDecimal(series[i]["data"][j])){
				series[i]["data"][j]=series[i]["data"][j].toFixed(roundNum);
			}
		}
	}
	
    createChart(chartId,legend,xAxis,series);
}

//判断是否为小数
function checkDecimal(c) {
    var r= /^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
    return r.test(c);
}

/**
 * 基于准备好的dom，初始化echarts图表
 */ 
function createChart(chartId, chartLegend,chartXAxis,chartSeries){
  var id = Math.random();
	 $('#'+chartId).append($("<div id=\""+id+"\" style=\"height:400px\"></div>"));
 var myChart = echarts.init(document.getElementById(id));
   option = {
 	    tooltip : {
 	        trigger: 'axis'
 	    },
// 	    legend: {
// 	        data:chartLegend,
// 	        x:'center',
// 	    	y:'bottom'
// 	    },
 	    toolbox: {
 	        show : true,
 	        feature : {
 	            restore : {show: true},
 	        }
 	    },
 	    calculable : true,
 	    xAxis : chartXAxis,
 	    yAxis : [
 	        {
 	            type : 'value',
 	        }
 	    ],
 	    series : chartSeries
 	};
     // 为echarts对象加载数据
     myChart.setOption(option);
 }