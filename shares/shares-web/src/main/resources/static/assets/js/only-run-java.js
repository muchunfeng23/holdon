var menuList = [
    {name: 'index',url: '/autoprice/index.mc'},
    {name: 'failed',url: '/autoprice/failed.mc'},
    {name: 'history',url: '/autoprice/history.mc'},
    {name: 'audit',url: '/autoprice/audit.mc'},
    {name: 'config',url: '/autoprice/config.mc'}
];

function onlyRunJava(bool) {
    var cityId = $("#header_city_id");
    var city = '<div style="margin-left: 5px;"><label class="radio-inline"><input type="radio" name="citys" value="1"> 北京</label><label class="radio-inline"><input type="radio" name="citys" value="2"> 上海</label><label class="radio-inline"><input type="radio" name="citys" value="3"> 成都</label><label class="radio-inline"><input type="radio" name="citys" value="4"> 天津</label><label class="radio-inline"><input type="radio" name="citys" value="5"> 广州</label><label class="radio-inline"><input type="radio" name="citys" value="6"> 重庆</label><label class="radio-inline"><input type="radio" name="citys" value="7"> 长沙</label><label class="radio-inline"><input type="radio" name="citys" value="8"> 杭州</label><label class="radio-inline"><input type="radio" name="citys" value="9"> 武汉</label><label class="radio-inline"><input type="radio" name="citys" value="10"> 深圳</label><label class="radio-inline"><input type="radio" name="citys" value="12"> 苏州</label><label class="radio-inline"><input type="radio" name="citys" value="13"> 郑州</label><label class="radio-inline"><input type="radio" name="citys" value="14"> 无锡</label><label class="radio-inline"><input type="radio" name="citys" value="15"> 西安</label><label class="radio-inline"><input type="radio" name="citys" value="16"> 合肥</label><label class="radio-inline"><input type="radio" name="citys" value="17"> 南京</label><label class="radio-inline"><input type="radio" name="citys" value="24"> 济南</label><label class="radio-inline"><input type="radio" name="citys" value="28"> 温州</label><label class="radio-inline"><input type="radio" name="citys" value="30"> 常州</label><label class="radio-inline"><input type="radio" name="citys" value="31"> 青岛</label><label class="radio-inline"><input type="radio" name="citys" value="57"> 东莞</label></div>';
    var menu = '<ul class="nav nav-pills">';
    for(var i = 0, l = menuList.length; i < l; i++){
        menu += `<li role="presentation"><a href="${menuList[i].url}">${menuList[i].name}</a></li>`;
    }
    menu += '</ul>';
    if(bool){
        cityId.after(menu).after($(city).on('change', '[name=citys]', function(){
            $("#header_city_id").val($(this).val());
            cityId.trigger('cityIdGot');
        }));
    }
}