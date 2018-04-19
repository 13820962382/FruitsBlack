<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/4/19
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>水果专家</title>
</head>
<body>
<div id="app-inner" style="">

    <div class="page clearfix" style="">
        <form class="form-horizontal form form-validate" id="form1" action="" method="post" enctype="multipart/form-data" novalidate="novalidate" style="">
            <h2>编辑水果</h2>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 col-md-2 control-label">水果名称</label>
                <div class="col-sm-9 col-xs-12">
                    <input type="text" class="form-control" name="title" value="灵芝烤鸡（45分钟出炉）" required="true" aria-required="true">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 col-md-2 control-label">水果分类</label>
                <div class="col-sm-9 col-xs-9 col-md-9">
                    <select name="cid" id="cid" class="form-control">
                        <option value="5514" selected="">美味烧烤</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-12 col-sm-3 col-md-2 control-label">水果缩略图</label>
                <div class="col-sm-9 col-xs-12">

                    <script type="text/javascript">
                        function showImageDialog(elm, opts, options) {
                            require(["util"], function(util){
                                var btn = $(elm);
                                var ipt = btn.parent().prev();
                                var val = ipt.val();
                                var img = ipt.parent().next().children();
                                options = {'global':false,'class_extra':'','direct':true,'multiple':false,'fileSizeLimit':5242880};
                                util.image(val, function(url){
                                    if(url.url){
                                        if(img.length > 0){
                                            img.get(0).src = url.url;
                                        }
                                        ipt.val(url.attachment);
                                        ipt.attr("filename",url.filename);
                                        ipt.attr("url",url.url);
                                    }
                                    if(url.media_id){
                                        if(img.length > 0){
                                            img.get(0).src = "";
                                        }
                                        ipt.val(url.media_id);
                                    }
                                }, options);
                            });
                        }
                        function deleteImage(elm){
                            $(elm).prev().attr("src", "./resource/images/nopic.jpg");
                            $(elm).parent().prev().find("input").val("");
                        }
                    </script>
                    <div class="input-group ">
                        <input type="text" name="thumb" value="images/34/2018/04/vYYBSZ92yWU99Qg29x2G24z9Ucw9sY.jpg" class="form-control" autocomplete="off">
                        <span class="input-group-btn">
				<button class="btn btn-default" type="button" onclick="showImageDialog(this);" data-original-title="" title="">选择图片</button>
			</span>
                    </div>
                    <div class="input-group " style="margin-top:.5em;">
                        <img src="http://fj.4000073322.com/images/34/2018/04/vYYBSZ92yWU99Qg29x2G24z9Ucw9sY.jpg" onerror="" class="img-responsive img-thumbnail" width="150">
                        <em class="close" style="position:absolute; top: 0px; right: -14px;" title="删除这张图片" onclick="deleteImage(this)">×</em>
                    </div>			</div>
            </div>


            <script type="text/javascript">
                function uploadMultiImage(elm) {
                    var name = $(elm).next().val();
                    util.image( "", function(urls){
                        $.each(urls, function(idx, url){
                            $(elm).parent().parent().next().append('<div class="multi-item"><img onerror="this.src=\'./resource/images/nopic.jpg\'; this.title=\'图片未找到.\'" src="'+url.url+'" class="img-responsive img-thumbnail"><input type="hidden" name="'+name+'[]" value="'+url.attachment+'"><em class="close" title="删除这张图片" onclick="deleteMultiImage(this)">×</em></div>');
                        });
                    }, {"multiple":true,"direct":false,"fileSizeLimit":5242880});
                }
                function deleteMultiImage(elm){
                    $(elm).parent().remove();
                }
            </script>

            <div class="form-group">
                <div class="col-sm-9 col-xs-9 col-md-9">
                    <input type="hidden" name="token" value="de2b4642">
                    <input type="submit" value="提交" class="btn btn-primary">
                </div>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        require(['clockpicker'], function($){
            $('.clockpicker :text').clockpicker({autoclose: true});

            $('#remove-time').click(function(){
                $("input[name='start_time1']").val("");
                $("input[name='end_time1']").val("");
            });

            $('#remove-time1').click(function(){
                $('input[name="start_time2"]').val("");
                $('input[name="end_time2"]').val("");
                $(this).parent().parent().parent().parent().parent().css("display", "none");
            });
        });
    </script>
</div>
</body>
</html>
