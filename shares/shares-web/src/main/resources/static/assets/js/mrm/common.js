function getSaleArea(callback) {
    $.ajax({
        headers: {header_city_id: $('#header_city_id').val()},
        method: 'post',
        url: '/retailprice/public/getsalearea',
        data: {},
        dataType: 'json',
        success: function (json) {
            callback && callback(json["data"]);
        }
    });
}
let layerIndex = void 0;
$(document).ajaxStart(function(){
    layerIndex = layer.load(2,{shade: [0.1,'#fff']});
});

$(document).ajaxSuccess(function (event, request, setting) {
    var obj = JSON.parse(request.responseText);
    if (obj && obj.error_code && obj.error_code !=0) {
        postMsg({errObj: obj});
    }
});

$(document).ajaxComplete(function(){
    if(layerIndex != undefined)
        layer.close(layerIndex);
});

Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,                 //月份
        "d+": this.getDate(),                    //日
        "h+": this.getHours(),                   //小时
        "m+": this.getMinutes(),                 //分
        "s+": this.getSeconds(),                 //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds()             //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

function errorCodeObj(){
    return {
        "1000": "系统异常码",
        "1001": "系统繁忙异常码",
        "1002": "非法参数异常码",
        "1003": "资源已存在异常码",
        "1004": "资源不存在异常码",
        "1005": "系统未响应异常码",
        "1006": "无权限异常码",
        "1007": "序列化异常码",
        "1008": "远程调用异常码",
        "1009": "无权限异常码",
        "1010": "web异常码",
        "1011": "当前城市变化"
    };
    //{error_code: 1011, error: {msg: "当前城市变化"}}
}

//表格的标题行悬浮
function theadOnTop(table) {
    $(window).scroll(function() {calc()});
    $(window).resize(function() {calc()});

    table.on('load-success.bs.table', function() {
        $('ul.pagination').find('[class^="page-"]').on('click', function() {
            $(window).scrollTop(table.offset().top);
        });
    });
    function calc(){
        var tableTop = table.offset().top;
        var arrWidth = [];
        var tableTr = table.find('tbody').find('tr');
        var cloneThead = table.find('thead').clone().find('th').each(function(i, elem) {
            $(elem).css('width', arrWidth[i]);
        }).end().addClass('onTop').attr('style', 'position:fixed;top:0;background:#f8fafc;border-bottom:1px solid #dedede;');
        if ($(window).scrollTop() > tableTop) {
            tableTr.eq(10).find('td').each(function(i, elem) {
                arrWidth.push($(elem).outerWidth());
            });
            [].reverse.call(tableTr.parent().find('[data-index]')).each(function (i, elem) {
                if($(window).scrollTop() < $(elem).offset().top){
                    // $("#hover-tr").remove();
                }else{
                    if($("#hover-tr").data('exist') == undefined){
                        $(elem).after(cloneThead.find('tr').attr('id','hover-tr').attr('data-exist','1').attr('style','background:#f8fafc;').addClass('scroll-thead'));
                    }else{
                        $(elem).after($("#hover-tr"));
                    }
                    return false;
                }
            });
        } else {
            $("#hover-tr").remove();
        }
    }
}