// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.format = function(format) {
	var args = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"h+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
		"S" : this.getMilliseconds()
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var i in args) {
		var n = args[i];
		if (new RegExp("(" + i + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n
					: ("00" + n).substr(("" + n).length));
	}
	return format;
};

/**
 ** 时间计算天数，小时数，分钟数，秒数
 ** curtime, updatetime 格式 yyyy-MM-dd HH:mm:ss
 */
function timeCalculate(curtime, updatetime) {
	var date1 = new Date(Date.parse(updatetime.replace(/-/g,   "/")));
	var date2 = new Date(Date.parse(curtime.replace(/-/g,   "/")));
	var s1 = date1.getTime();
	var s2 = date2.getTime();
	var total = (s2 - s1)/1000;
	var day = parseInt(total / (24*60*60));//计算整数天数
	var afterDay = total - day*24*60*60;//取得算出天数后剩余的秒数
	var hour = parseInt(afterDay/(60*60));//计算整数小时数
	var afterHour = total - day*24*60*60 - hour*60*60;//取得算出小时数后剩余的秒数
	var min = parseInt(afterHour/60);//计算整数分
	var afterMin = total - day*24*60*60 - hour*60*60 - min*60;//取得算出分后剩余的秒数
	
	if(day+hour+min+afterMin == 0) {
		return "刚刚";
	}
	if(day != 0) {
		return day + "天前";
	}
	if(hour != 0) {
		return hour + "小时前";
	}
	if(min != 0) {
		return min + "分钟前";
	}
	if(afterMin != 0) {
		return afterMin + "秒前";
	}
}