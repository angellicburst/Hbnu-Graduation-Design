layui.use(['jquery', 'table'], function() {
    const $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    $(function () {
        /**
         * @todo 菜单修改
         */
        form.on('submit(editMenu)', function(){
            let editForm=document.querySelector("#editForm");
            let formdata=new FormData(editForm);
            console.log(formdata);
            $.ajax({
                url: '/editMenu' ,
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
                    $("#editForm")[0].reset();
                }
            });
            return false;
        });


        /**
         * @todo 根据选中的菜单等级来判断时候打开图片上传按钮
         */
        $("#editMenuImgBtn").on("click",function () {

            if ($(".editMenuLevel .layui-this").text() === "1") {
                $('#editMenuImg').click();
            } else if ($(".editMenuLevel .layui-this").text() === "2") {
                return;
            } else {
                layer.msg("请先选择菜单等级",{icon: 2});
            }

        });

        /**
         * @todo 文件上传后正则获取文件名然后再上传按钮后面显示图片的名称
         */
        $("#editMenuImg").change(function(){
            let filepath = $("#editMenuImg").val();
            let fileExt=filepath.replace(/.+\./,"");
            let strFileName=filepath.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");
            let filename = strFileName+"."+fileExt;
            $("#editMenuFileName").val(filename);
        });
    })
})