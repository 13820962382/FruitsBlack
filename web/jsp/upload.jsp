<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/5
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String basePath = pageContext.getServletContext().getContextPath();
%>
<html>
<head>
    <title>水果专家后台管理</title>
</head>
<body>
<div class="preview">
    <img id="previewImg" width="200px" height="300px" src="../images/logo透明背景.png">
    <div id="imgPath">${result}</div>
</div>

<form action="ftp.action" method="post" enctype="multipart/form-data">
    <input id="myFile" name="myFile"  class="input-lg" type="file">
    <input type="submit" name="post" value="上传到服务器"/>
</form>


<script>
    //判断ie浏览器的版本
    function getIeVersion() {
        var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
        var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1; //判断是否IE<11浏览器
        var isEdge = userAgent.indexOf("Edge") > -1 && !isIE; //判断是否IE的Edge浏览器
        var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
        if(isIE) {
            var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
            reIE.test(userAgent);
            var fIEVersion = parseFloat(RegExp["$1"]);
            if(fIEVersion == 7) {
                return 7;
            } else if(fIEVersion == 8) {
                return 8;
            } else if(fIEVersion == 9) {
                return 9;
            } else if(fIEVersion == 10) {
                return 10;
            } else {
                return 6;//IE版本<=7
            }
        } else if(isEdge) {
            return 'edge';//edge
        } else if(isIE11) {
            return 11; //IE11
        }else{
            return -1;//不是ie浏览器
        }
    }

    //获取上传文件的真实路径
    $("#myFile").change(function () {
        var myFile = document.getElementById("myFile");
        var previewImg = document.getElementById("previewImg");
        var imgPath = document.getElementById("imgPath");
        //取得浏览器的userAgent字符串
        var userAgent = navigator.userAgent;
        //判断是否是IE浏览器
        var isIe = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1;
        // alert("IE浏览器版本："+getIeVersion());
        //判断是否支持FileReader对象
        if(window.FileReader){
            var fileReader = new FileReader();
            fileReader.readAsDataURL(myFile.files[0]);
            fileReader.onload = function (ev) {
                var src = ev.target.result;
                // imgPath.innerHTML = src;
                previewImg.src = src;
            }
        }else if (isIe) {
            alert("IE浏览器版本："+getIeVersion());
            //ie8
            file.select();
            //ie下获取文件真实路径
            var realPath = document.selection.createRange().text;
            if(getIeVersion() < 7){
                previewImg.src = realPath;
            }
        }else{
            alert("IE浏览器版本："+getIeVersion() +"暂时不支持预览");
        }//isIE end

    })

</script>
</body>
</html>
