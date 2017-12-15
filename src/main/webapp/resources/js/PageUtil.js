/*
设置一个元素的值
*/
function writeText(name,content){
	 $('#'+name).text(content);
}
/*
跳转页面
*/
function goNewPage(URL, flag) {
	//window.location=URL;
	if(flag){
		window.open(URL,'_blank'); // _blank 在新窗口打开
	}else{
		window.open(URL,'_self'); // _blank 在新窗口打开
	}
}
