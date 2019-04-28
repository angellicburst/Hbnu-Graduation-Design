layui.config({
    base: '/js/',
}).use('admin');
layui.use(['laydate', 'jquery', 'admin', 'table', 'upload'], function() {
    const $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    $(function () {
        /**
         * layui加载层，支持0-2
         */
        let loading = layer.load(2, {shade: false});

        /**
         * @todo 定义table
         */
        table.render({
            elem : '#teaListAdm',
            url : '/admin/getTeachers',
            toolbar : '#toolbar',
            id: 'teaListAdm',
            width:1300,
            cellMinWidth : 80,
            page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                ,groups: 1 //只显示 1 个连续页码
                ,first: false //不显示首页
                ,last: false //不显示尾页
            },
            cols : [ [ {type:'checkbox'},{
                field : 'id',
                title : 'ID',
                align : 'center',
                width : 80,
                unresize : true,
                sort : true
            }, {
                field : 'name',
                title : '姓名',
                align : 'center',
                width : 150,
                sort : true
            }, {
                field : 'gender',
                title : '性别',
                align : 'center',
                width : 80,
                templet: '#genderTpl'
            }, {
                field : 'phone',
                title : '电话',
                width : 150,
                align : 'center'
            }, {
                field : 'email',
                title : '邮箱',
                width : 200,
                align : 'center'
            }, {
                field : 'year',
                title : '工龄',
                width : 80,
                align : 'center'
            },{
                field : 'status',
                title : '状态',
                align : 'center',
                width : 80,
                templet: '#statusTpl'
            },{
                field : 'department',
                title : '院系',
                align : 'center'
            },{
                field : 'departmentId',
                title : '院系ID',
                align : 'center',
                width : 80,
                hide: true
            },{
                fixed : 'right',
                title :'操作',
                toolbar : '#barTeaMange',
                width : 180,
                align : 'center'
            } ] ],
            page : true,
            response: {
                statusCode: 200,    //设置返回码为200，默认0
                dataName: 'objs'    //设置返回对象为objs,默认data
            }
        });

        /**
         * @todo 获取所有的院系
         */
        $.ajax({
            type: "POST",
            url: "/getDepartments",
            dataType: "json",
            success: function (data) {
                let departments = "";
                layui.each(data.objs, function(index, obj) {
                    departments += "<option value='"+obj.id+"'>"+obj.department+"</option>"
                });
                $("#searchDepartment").append(departments);
                $("#selectDepartment").append(departments);
                form.render();
            }
        });

        /**
         * @todo 关闭加载按钮
         */
        layer.close(loading);

    });

    /**
     * @todo 模糊查询表单
     */
    form.on('submit(searchBtn)', function(){
        table.reload('teaListAdm',{
            where: {
                id:$("#searchId").val(),
                name:$("#searchName").val(),
                status:$(".status .layui-this").attr("lay-value") == undefined?'':$(".status .layui-this").attr("lay-value"),
                departmentId:$(".department .layui-this").attr("lay-value") == undefined?'':$(".department .layui-this").attr("lay-value")
            }
        });
        return false;
    });

    /**
     * @todo 监听头部操作按钮（添加，批量删除）
     */
    table.on('toolbar(teaAdm)', function(obj){
        let checkStatus = table.checkStatus(obj.config.id);
        let data = checkStatus.data;

        if (obj.event === 'delMult') {   //批量删除
            //删除提示
            layer.confirm('确认删除'+data.length+'条？', function() {
                $.ajax({
                    type: "POST",
                    url: "/admin/delTeachers",
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType:"application/json",
                    success: function (data) {
                        if (data.code === 200) {
                            //刷新table
                            $(".layui-laypage-btn")[0].click();
                            layer.msg(data.msg,{icon: 1});
                        } else {
                            layer.msg(data.msg,{icon: 2});
                        }


                    }
                });

                //关闭弹出层
                layer.closeAll();
            });
        }
    });

    /**
     * @todo table 监听表格操作按钮（删除，更新）
     * @param obj
     */
    table.on('tool(teaAdm)', function(obj){
        let data = obj.data;

        if(obj.event === 'del') {    //删除
            layer.confirm('确认删除？', function(index) {
                //删除
                $.ajax({
                    type: "POST",
                    url: "/admin/delTeacher",
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType:'application/json;charset=UTF-8',
                    success: function (data) {
                        if (data.code === 200) {
                            //刷新table
                            $(".layui-laypage-btn")[0].click();
                            layer.msg(data.msg,{icon: 1});

                        } else {
                            layer.msg(data.msg,{icon: 2});
                        }

                    }
                });

                //关闭弹出层
                layer.closeAll();
            });
        } else if(obj.event === 'edit') {    //更新
            //打开弹出层
            layer.open({
                type : 1,
                title : "学生编辑",
                area: ['700px', '550px'],
                content: $('#editTeaFrame'),
                cancel: function(index, layero) {    //点击弹出层右上角X触发
                    //清除所有弹出层数据
                    $("#editForm")[0].reset();
                    //关闭弹出层
                    layer.closeAll();
                }

            });
            //表单初始赋值
            form.val('teaFilter', data);

            //id存入隐藏域
            $("#saveTeaId").val(data.id);
            $("#saveTeaStatus").val(data.status);
            $("#saveTeaUserId").val(data.userId);

        } else if(obj.event === 'stop' ||obj.event === 'use') {			//状态变更
            layer.confirm(obj.event === 'stop'?'确认停用？':'确认启用？', function(index) {
                //改变状态
                $.ajax({
                    type: "POST",
                    url: "/admin/teacher/changeStatus",
                    data: JSON.stringify(data),
                    dataType: "json",
                    contentType:'application/json;charset=UTF-8',
                    success: function (data) {
                        if (data.code === 200) {
                            //刷新table
                            $(".layui-laypage-btn")[0].click();
                            layer.msg(data.msg,{icon: 1});
                        } else {
                            layer.msg(data.msg,{icon: 2});
                        }
                    }
                });

                //关闭弹出层
                layer.closeAll();
            });
        }
    });

    /**
     * @todo 批量导入教师
     */
    $("#addTeasBtn").on("click",function () {
        $('#addTeas').click();
    });
    $("#addTeas").change(function () {
        if($(this).val() != ""){
            let addForm=document.querySelector("#addTeasForm");
            let formdata=new FormData(addForm);

            layer.load(1, {shade: false});

            $.ajax({
                url: '/admin/addTeachers',
                type: 'POST',
                data: formdata,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (res) {
                    //关闭loading
                    layer.closeAll();
                    form.render();
                    $("#addTeasForm")[0].reset();
                    //清空弹出层的数据
                    //$("#addStusForm")[0].reset();
                    if (res.code === 200) {
                        //打印msg
                        layer.msg(res.msg,{icon: 1});
                        //刷新table
                        $(".layui-laypage-btn")[0].click();
                    } else {
                        layer.msg(res.msg,{icon: 2});
                    }
                }
            });
        }
    });

    /**
     * @todo 教师更新
     */
    form.on('submit(editTea)', function(data) {
        $.ajax({
            type: "POST",
            url: "/admin/editTeacher",
            data: JSON.stringify(data.field),
            dataType: "json",
            contentType:'application/json;charset=UTF-8',
            success: function (data) {
                //关闭弹出层
                layer.closeAll();
                if (data.code === 200) {
                    //刷新table
                    $(".layui-laypage-btn")[0].click();
                    layer.msg(data.msg,{icon: 1});
                } else {
                    layer.msg(data.msg,{icon: 2});
                }
                //清空弹出层的数据
                $("#editForm")[0].reset();
            }
        });
        return false;
    });

});