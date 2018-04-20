
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




