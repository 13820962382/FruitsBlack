<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page isELIgnored="false"%>


<%
//    List<mode.CategoryEntity> categoryList = (List<CategoryEntity>) request.getSession().getAttribute("categoryList");
//    List<mode.FruitsEntity> fruitsList =(List<FruitsEntity>) request.getSession().getAttribute("fruitsList");

%>
<html>
<head>
    <title>水果专家后台管理</title>
</head>
<body>
<div class="page-header">
    <a class="btn btn-primary" id="addBtn" href="#" data-toggle="modal" data-target="#myModal">
        添加水果
    </a>
</div>
<table class="table table-bordered table-striped table-hover" style="margin-top: 20px">
    <thead class="">
    <tr>
        <th width="15%">水果名称</th>
        <th width="25%">所属分类</th>
        <th width="35%">说明</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${fruitsList!=null}">
            <c:forEach items="${fruitsList}" var="fruits" varStatus="status">
            <tr>
                <td>${fruits.fruitsName}</td>
                <td>${fruits.category}</td>
                <td>${fruits.des}</td>
                <td><a href="#">删除</a> &nbsp;&nbsp;&nbsp;
                    <a href="#">修改</a>
                </td>
            </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td>无数据</td>
                <td>无数据</td>
                <td>无数据</td>
                <td><a href="#">删除</a> &nbsp;&nbsp;&nbsp;
                    <a href="#">修改</a>
                </td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加水果
                </h4>
            </div>
            <div class="modal-body">

                <!--表单-->
                <form class="form-horizontal" role="form" method="post" action="/add.action?add=fruits&">
                    <div class="form-group">
                        <label for="firstName" class="col-sm-2 control-label">水果名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="firstName" name="fruitsName" placeholder="请输入水果名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="select" class="col-sm-2 control-label">所属分类</label>
                        <div class="col-sm-10">
                            <select class="form-control input-sm" id="select" name="categoryName">
                                <c:choose>
                                    <c:when test="${categoryList!=null}">
                                        <c:forEach items="${categoryList}" var="category" varStatus="status">
                                            <option value="${category.categoryName}"> ${category.categoryName} </option>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <option>请先创建分类</option>
                                    </c:otherwise>
                                </c:choose>

                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="desName" class="col-sm-2 control-label">水果说明（选填）</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="desName" placeholder="请输入分类说明">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="myFile" class="col-sm-2 control-label">水果图片（必填）</label>
                        <div class="col-sm-10">
                            <input class="form-control" id="myFile" name="fruitsImg"  type="file">
                        </div>

                        <div class="">
                            <img id="previewImg" width="200px" height="300px" src="../images/logo透明背景.png">
                        </div>
                    </div>

                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="addFruits">
                    提交更改
                </button>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<script>
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

<script>
    $(function(){
        $("#addFruits").click(function(){
            var firstName = $("#firstName").val()
            if (firstName!=""&&firstName!=null){
                // alert(firstname)
                $(".form-horizontal").submit();
            }
        });
    });

    //获取水果列表
    function getFruits() {
        $.ajax({
            type: "get",
            url: "/get.action?type=fruits",
            success: function(html) {
                // alert("请求成功 刷新fruits页面")
                // $('#content').html(html);
            },
            error:function () {
                alert("请求fruits失败")
            }
        });
    }

</script>

</body>
</html>