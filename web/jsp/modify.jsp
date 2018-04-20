<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/19
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>水果专家</title>
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
                <li class="nav-tabs-justified"><a href="#" onclick="changeRight('')">编辑文字</a></li>
            </ul>
        </div>

        <div class="col-md-11 bg-info text-center"><h2>编辑水果</h2></div>

        <!--右侧内容-->
        <div class="col-md-11" id="content">
            <div id="app-inner" style="">
                <div class="page clearfix" style="">
                    <form class="form-horizontal form form-validate" id="form1" action="${pageContext.request.contextPath}/modify.action" method="post"
                          enctype="multipart/form-data" novalidate="novalidate" style="">

                        <div class="form-group">
                            <label class="col-xs-12 col-sm-3 col-md-2 control-label">水果名称</label>
                            <div class="col-sm-9 col-xs-12">
                                <input type="text" class="form-control" name="fruitsName" value="${fruit.fruitsName}"
                                       required="true" aria-required="true">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-12 col-sm-3 col-md-2 control-label">水果分类</label>
                            <div class="col-sm-9 col-xs-9 col-md-9">
                                <%--<input type="text" class="form-control" name="categoryName" value="${fruit.category.categoryName}"--%>
                                       <%--required="true" aria-required="true">--%>
                                    <select class="form-control input-sm" id="select" name="categoryName">
                                        <option value="${fruit.category.categoryName}" selected> ${fruit.category.categoryName} </option>
                                        <c:choose>
                                            <c:when test="${options!=null}">
                                                <c:forEach items="${options}" var="category" varStatus="status">
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
                            <label for="desName" class="col-sm-2 control-label">水果说明</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="des" id="desName" placeholder="${fruit.des}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="myFile" class="col-sm-2 control-label">水果图片（必填）</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="myFile" name="fruitsImg"  type="file">
                            </div>

                            <div class="col-sm-offset-2">
                                <img id="previewImg" width="200px" height="300px" src="http://kidle.club:8080/upload/${fruit.fruitsImg}">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2">
                                <input type="hidden" name="fruitsId" value="${fruit.fruitsId}">
                                <input type="submit" value="提交" class="btn btn-primary">
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>

    </div>
</div>
<script type="text/javascript" src="/js/upload.js"></script>
<script type="text/javascript" src="/js/chang-url.js"></script>
</body>
</html>
