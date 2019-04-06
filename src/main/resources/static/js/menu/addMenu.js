layui.use(['jquery', 'table'], function() {
    const $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    $(function () {
        /**
         * @todo 用formdata包装form表单提交到控制层
         * layui自带文件上传是异步的，不能实现图片上传和表单同时提交，只能先上传图片再提交表单
         * 用formdata来实现图片和表单的同时提交
         */
        form.on('submit(addMenu)', function(){
            let addForm=document.querySelector("#addForm");
            let formdata=new FormData(addForm);
            $.ajax({
                url: '/addMenu' ,
                type: 'POST',
                data: formdata,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    //关闭弹出层
                    layer.closeAll();
                    //打印msg
                    layer.msg(data.msg,{icon: 1});
                    //刷新table
                    table.reload('menuList',{});
                    //清空弹出层的数据
                    $("#addForm")[0].reset();
                }
            });
            return false;
        });

        /**
         * @todo 根据选中的菜单等级来判断时候打开图片上传按钮
         */
        $("#addMenuImgBtn").on("click",function () {
            if ($(".addMenuLevel .layui-this").text() === "1") {
                $('#addMenuImg').click();
            } else if ($(".addMenuLevel .layui-this").text() === "2") {
                return;
            } else {
                layer.msg("请先选择菜单等级",{icon: 2});
            }

        });

        /**
         * @todo 根据菜单等级判断
         * 一级菜单没有父节点可以添加图片
         * 二级菜单有父节点不需要添加图片
         */
        form.on('select(addLevel)',function(data) {
            if (data.value === "1") {
                $("#addMenuImg").val("");
                $("#addMenuFileName").val("");
                $("#addParentId").attr("disabled",true);
                $("#addParentId").attr("class","layui-input layui-disabled");
                $("#addParentId").css("background-color","#eee");
            } else if (data.value === "2") {
                $("#addMenuImg").val("");
                $("#addMenuFileName").val("");
                $("#addParentId").attr("disabled",false);
                $("#addParentId").attr("class","layui-input");
                $("#addParentId").css("background-color","#fff");
            } else if (data.value === "") {
                $("#addMenuImg").val("");
                $("#addMenuFileName").val("");
                $("#addParentId").attr("disabled",false);
                $("#addParentId").attr("class","layui-input");
                $("#addParentId").css("background-color","#fff");
            }
            return false;
        });

        /**
         * @todo 文件上传后正则获取文件名然后再上传按钮后面显示图片的名称
         */
        $("#addMenuImg").change(function(){
            let filepath = $("#addMenuImg").val();
            let fileExt=filepath.replace(/.+\./,"");
            let strFileName=filepath.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");
            let filename = strFileName+"."+fileExt;
            $("#addMenuFileName").val(filename);
        });
    })
})