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
         * @todo 判断是否显示首页
         * admin显示
         * student，teacher不显示
         */
        $.ajax({
            type: "POST",
            url: "/user/isAdmin",
            dataType: "json",
            success: function (data) {
                if (data.code === 200) {
                    $("#tabName").append("<li>我的桌面</li>");
                    $(".layui-show").append("<iframe src='/welcome' frameborder='0' scrolling='yes' class='weIframe'></iframe>")
                    form.render();
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

    /**
     * @todo 修改密码弹框
     */
    $("#editPassWord").on('click',function () {
        //打开弹出层
        layer.open({
            type : 1,
            title : "成绩管理",
            area: ['500px', '500px'],
            content: $('#passFrame'),
            cancel: function(index, layero){    //点击弹出层右上角X触发
                //关闭弹出层
                layer.closeAll();
            }
        });
    });

    /**
     * @todo 修改密码
     */
    form.on('submit(editPass)', function(data) {
        console.log(data.field);
        const  passinfo = data.field;
        if (passinfo.newPassWord === passinfo.reNewPassWord) {
            $.ajax({
                type: "POST",
                url: "/user/editPassword",
                data: JSON.stringify(data.field),
                dataType: "json",
                contentType:'application/json;charset=UTF-8',
                success: function (data) {
                    if (data.code === 200) {
                        layer.msg(data.msg + ",请重新登陆", {icon: 1}, function() {
                            location.href = '/logout';
                        });
                    } else {
                        layer.msg(data.msg,{icon: 2});
                    }
                }
            });
            //关闭弹出层
            layer.closeAll();
        } else {
            layer.msg("新输入的两次密码前后不一致",{icon: 2});
        }

        return false;
    });




});