<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" language="javascript" src="<c:url value="/resources/js/jquery-2.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>" />
    <!-- jqueryui -->
    <script type="text/javascript" src="<c:url value="/resources/jquery-ui/jquery-ui.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/jquery-ui/jquery-ui.chinese.js"/>"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/jquery-ui/realtimedata.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/icon.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/nav.css"/>" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/bootstrap/extend/bootscrapSelect/css/bootstrap-select.min.css"/>" />
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/extend/bootscrapSelect/js/bootstrap-select.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/extend/bootscrapSelect/js/i18n/defaults-zh_CN.min.js"/>"></script>
    <title>监控系统</title>
    <script type="text/javascript">
        $(function(){
            windowSize();
        });

        $(window).resize(function(){
            windowSize();
        });

        function windowSize() {
            var height = $(window).height();
            var width = $(window).width();
            var tabFrameHight = height-80;
            $("#tabs").css("height",tabFrameHight);

        }

        //iframe切换
        function go(url,obj){
            var item=$("div[name=list-item]");
            for(var i=0;i<item.length;i++){
                $(item[i]).removeClass("list-down");
            }
            $(obj).addClass("list-down");
            document.getElementById("mainFrame").src=url;
        }

        //注销
        function exit() {
            if (confirm("您确定要退出系统吗?")) {
                $.ajax({
                    type : "post",
                    url : "Auth/logout",
                    dataType : "text",
                    data:'',
                    asyn : false,
                    contentType : "application/x-www-form-urlencoded; charset=utf-8",
                    success : function(data) {
                        var result = data.replace(/(^\s*)|(\s*$)/g, "");
                        if(result == "success"){
                            window.location.href = "index.jsp";
                        } else {
                            custom.Msg.alert("注销失败！")
                        }
                    }
                })
            }
        }

        var tabs;
        $(function() {
            //初始化标签选择
            tabs = $("#tabs").tabs();
            // 关闭图标：当点击时移除标签页
            tabs.delegate("span.closebtn", "click", function() {
                var panelId = $(this).closest("li").attr("subid");
                $(this).closest("li").remove().attr("aria-controls");
                $("#" + panelId).remove();
                tabs.tabs("refresh");
            });
            $('.load').click();

            $('#projectSelect').on('click', function () {
                var text = $('#projectSelect').find('option:selected').html();
                alert(text);
            });
        })

        //新建标签页
        function addTab(label, url) {
            var id = "tabs" + encodeURIComponent(url).replace(new RegExp("%","g"),"");
            if ($("#" + id).html() == undefined) {//如果已经打开就激活它
                //li中不能自定义id，这里使用subid代替
                var li = "<li subid='"+id+"'><a href='#"+id+"'>"
                    + label
                    + "</a> <span class='closebtn' style='float: left;' role='presentation'></span></li>";
                var tabContentHtml = '<iframe class="mainframe"  src="' + url + '" ></iframe>';
                tabs.find(".ui-tabs-nav").append(li);
                tabs.append( "<div id='" + id + "' class='subTab'>" + tabContentHtml + "</div>" );
                tabs.tabs("refresh");
            }
            existing = tabs.find("[subid='" + id + "']");
            var index = tabs.find(".ui-tabs-nav").find("li").index(existing);
            tabs.tabs("option", "active", index);
        }

        //弹出新窗口
        function addNewPopWindow(url){
            window.open(url);
        }
    </script>
</head>
<body style="overflow: hidden">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <span class="navbar-brand login-logo"></span>
                <a class="navbar-brand" href="#">监控系统</a>
            </div>
            <%--<form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <select id="projectSelect" class="selectpicker form-control" >
                        <option>option1</option>
                        <option>option2</option>
                        <option>option3</option>
                        <option>option4</option>
                    </select>
                </div>
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <select class="selectpicker form-control" >
                            <option>option1</option>
                            <option>option2</option>
                            <option>option3</option>
                            <option>option4</option>
                        </select>
                    </div>
                </form>--%>
                <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">我的账户</a></li>
                    <li><a href="#" onclick="exit()">注销</a></li>
                </ul>
                <!-- <form class="navbar-form navbar-right">
                  <input type="text" class="form-control" placeholder="功能搜索">
                </form> -->
            </div>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-2 col-md-1 sidebar">
                <ul class="nav nav-sidebar" style="margin-top:60px;">
                    <li><a href="#" onclick="addTab('HADOOP','getMonItorHadoop')" >HADOOP</a></li>
                    <li><a href="#" onclick="addTab('数据库','getMonItor')" >数据库</a></li>
                    <li><a href="#" class="load" onclick="addTab('文件','getMonItorFile')" >文件</a></li>
                	<li><a href="#" onclick="addTab('运行', 'getMonItorRun')">运行</a></li>
                </ul>

            </div>
            <div class="col-sm-10 col-sm-offset-2 col-md-11 col-md-offset-1 main" style="padding: 0;">
                <div id="tabs" style="height: 100%;">
                    <ul>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</body>
</html>