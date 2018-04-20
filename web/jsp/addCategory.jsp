<%@ page import="java.util.Set" %>
<%@ page import="mode.FruitsEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <title>- 后台管理系统 -</title>
</head>
<body>
<div class="page-header">
    <a class="btn btn-primary" href="#" onclick="getFruits()" data-toggle="modal" data-target="#categoryModal">
        添加分类
    </a>
</div>
<table class="table table-bordered table-striped table-hover" style="margin-top: 20px">

    <thead >
    <tr class="active">
        <th width="15%">水果分类</th>
        <th width="25%">水果数量</th>
        <th width="35%">说明</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${categoryList!=null}">
            <c:forEach items="${categoryList}" var="category" varStatus="status">
                <tr>
                    <td>${category.categoryName}</td>
                    <td>${category.totalFruits}</td>
                    <td>${category.des}</td>
                    <td><a href="/delete.action?type=category&categoryId=${category.categoryId}">删除</a> &nbsp;&nbsp;&nbsp;
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
<div class="modal fade" id="categoryModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加分类
                </h4>
            </div>
            <div class="modal-body">

                <!--表单-->
                <form class="form-horizontal" id="form1" role="form" method="post" action="#">
                    <input type="hidden" name="add" value="category"/>
                    <div class="form-group">
                        <label for="firstname" class="col-sm-2 control-label">分类名称</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="firstname" name="categoryName" placeholder="请输入分类名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="lastname" class="col-sm-2 control-label">分类说明（选填）</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="lastname" name="des" placeholder="请输入分类说明">
                        </div>
                    </div>

                </form>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="addCategory">
                    提交更改
                </button>

            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script>
    $(function(){
        $("#addCategory").click(function(){
            var firstname = $("#firstname").val()
            if (firstname!=""&&firstname!=null){
                // alert(firstname)
                $.ajax({
                    type: "post",
                    data: $('#form1').serialize(),
                    url: "http://kidle.club:8080/fruitsmanager/add.action?type=category",
                    success: function(html) {
                        // alert("请求成功 刷新category页面")
                        $(".form-horizontal").submit();
                        $('#content').html(html);
                    },
                    error:function () {
                        alert("添加category失败")
                    }
                });

            }
        });
    });

    //获取分类列表
    function getCategory() {
        $.ajax({
            type: "get",
            url: "/get.action?type=category",
            success: function(html) {
                // alert("请求成功 刷新category页面")
            },
            error:function () {
                alert("请求category失败")
            }
        });
    }
</script>

</body>
</html>