
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <%--引用本地的bootstarp框架--%>
  <!--  <meta name="viewport" content="width=device-width, initial-scale=1">-->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/bootstrap.min.js"></script>

  <title>水果专家后台管理</title>

</head>
<body>
<div class="modal-header bg-primary">
  <h2 class="text-center modal-header">后台管理系统</h2>
</div>
<div class="container-fluid">
  <div class="row">
    <!--左侧菜单-->
    <div class="col-md-1 text-center bg-info" style="border-right: white solid 2px">
      <div class="bg-info h2">管理菜单</div>
      <ul class="nav nav-pills nav-stacked " style="border-top:solid 1px white">
        <h3>分类管理</h3>
        <li class="nav-tabs-justified" onclick="changeRight('jsp/addCategory.jsp')"><a href="#">添加分类</a></li>
        <li class="nav-tabs-justified" onclick="changeRight('jsp/addFruits.jsp')"><a href="#">添加水果</a></li>
      </ul>

      <ul class="nav nav-pills nav-stacked ">
        <h3>图片管理</h3>
        <li class="nav-tabs-justified"><a href="#" onclick="changeRight('jsp/upload.jsp')">上传图片</a></li>
        <li class="nav-tabs-justified"><a href="#" onclick="changeRight('jsp/editor.jsp')">编辑文字</a></li>
      </ul>
    </div>

    <div class="col-md-11 bg-info text-center"><h2>产品管理</h2></div>

    <!--右侧内容-->
    <div class="col-md-11" id="content">
      <h1 class="page-header"><i class="fa fa-cog fa-spin"></i>&nbsp;控制台<small>&nbsp;&nbsp;&nbsp;欢迎使用水果专家后台管理系统</small></h1>
      <h4>
        <strong>使用指南：</strong><br>
        <br><br>默认页面内容……
      </h4>

    </div>

  </div>
</div>

<script>
    function changeRight(url) {
        var url0 = document.URL;
        var num = url0.indexOf('?');
        var oldurl;
        if(num == -1) {
            oldurl = url0;
        } else {
            oldurl = url0.slice(0, num);
        }
        var newurl = oldurl + '?' + url;
        history.pushState(null, null, newurl);
        // var ajaxurl = url + '.html'
        var ajaxurl = url
        $.ajax({
            type: "post",
            url: ajaxurl,
            success: function(html) {
                $('#content').html(html);
            },
            error:function () {
                alert("请求失败")
            }
        });

    };

    //获取分类和水果列表
     getAction()
    function getAction() {
        $.ajax({
            type: "get",
            // url: "http://kidle.club:8080/fruitsmanager/get.action", //服务调试
            url: "/get.action",//本地调试
            success: function() {
                // alert("请求成功 刷新页面")

            },
            error:function () {
                alert("请求数据失败" )
            }
        });
    }



</script>
</body>
</html>
