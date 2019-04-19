layui.config({
    base: '/js/',
}).use('admin');
layui.use(['jquery','form'], function() {
    const $ = layui.jquery,
        form = layui.form;

    $(function() {
        /**
         * layui加载层，支持0-2
         */
        let loading = layer.load(0, {shade: false});

        /**
         * @todo 页面右上角显示用户
         */
        $.ajax({
            type: "POST",
            url: "/showUser",
            dataType: "json",
            success: function (data) {
                if (!$.isEmptyObject(data)) {
                    $("#username").text(data.username);
                }
            }
        });

        /**
         * @todo 左侧菜单显示和顶部菜单搜索框
         */
        $.ajax({
           type: "POST",
           url: "/getMenus",
           dataType: "json",
           success: function (data) {
               layui.each(data, function(index, obj) {
                   let str = "<li value='"+obj.id
                       +"'><a _href='"+obj.url
                       +"'><i class='iconfont'><img src='/images/menu/"+obj.img
                       +"' ></i><cite>"+ obj.menuName
                       +"</cite><i class='iconfont nav_right'>&#xe697;</i></a>";
                   let search = "<option id='"+obj.id
                       +"'value='"+obj.url
                       +"'>"+obj.menuName
                       +"</option>";
                   if (!$.isEmptyObject(obj.secMenu)) {
                       search = "";
                       layui.each(obj.secMenu, function (i,o) {
                           str += "<ul class='sub-menu'><li value='"+o.secId
                               +"'><a _href='"+o.secUrl
                               +"'><i class='iconfont'>&#xe6a7;</i><cite>"+o.secMenu
                               +"</cite></a></li></ul>";
                           search += "<option id='"+o.secId
                               +"'value='"+o.secUrl
                               +"'>"+o.secMenu
                               +"</option>";
                       });
                   }
                   str += "</li>";
                   $("#nav").append(str);

                   $(".layui-input-inline select").append(search);

                   //刷新form避免select菜单搜索框不显示
                   form.render();
                   layer.close(loading);
               });
           }
        });

        /**
         * @todo 登出操作
         */
        $(".loginout").on('click', function() {
            location.href = '/logout';
        });

    });


});