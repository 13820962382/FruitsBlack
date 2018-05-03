<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/5/3
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑页面</title>

    <script src="/js/wangEditor.min.js"></script>

</head>
<body>
<form id="form1" action="/ftp.action">
    <div id="editor" style="width:1024px;height:500px;"></div>

    <script type="text/javascript">
        var E = window.wangEditor
        var editor = new E('#editor')// 或者 var editor = new E( document.getElementById('editor') )

        editor.customConfig.uploadImgServer = '/ftp.action'//上传图片
        editor.customConfig.filename = "myFileName"

        editor.create()
    </script>
</form>
</body>
</html>
