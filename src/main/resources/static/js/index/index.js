layui.config({
    base: '/js/',
}).use('admin');
layui.use(['jquery','form'], function() {
    var $ = layui.jquery;
    var form = layui.form;
    $(function() {
        /**
         * @todo 页面右上角显示用户
         */
        myAjax("post","/showUser",null,function(data){
            if (!$.isEmptyObject(data)) {
                //解析json，不然带有双引号
                const jsonObj = JSON.parse( data );
                $("#username").text(jsonObj.username);
            }
        });

        /**
         * @todo 左侧菜单显示和顶部菜单搜索框
         */
        myAjax("post","/getMenus",null,function(data){
            layui.each(JSON.parse(data), function(index, obj) {
                let str = "<li value='"+obj.id
                    +"'><a _href='"+obj.url
                    +"'><i class='iconfont'><img src='/images/"+obj.img
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
            });
        });

        /**
         * @todo 登出操作
         */
        $(".loginout").on('click', function() {
            location.href = '/logout';
        });


        function getLeftNav() {

        }

    });


});