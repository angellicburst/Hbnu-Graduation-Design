layui.config({
    base: '/js/',
}).use('admin');
layui.use(['laydate','jquery', 'admin', 'table','form'], function() {
    const $ = layui.jquery,
        table = layui.table,
        form = layui.form;



    $(function() {
        /**
         * layui加载层，支持0-2
         */
        let loading = layer.load(2, {shade: false});

        /**
         * @todo 定义table
         */
        table.render({
            elem : '#endExamListAdm',
            url : '/admin/getEndExams',
            toolbar : '#toolbar',
            id: 'endExamListAdm',
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
                unresize : true,
                sort : true,
                hide: true
            }, {
                field : 'examName',
                title : '考试名称',
                align : 'center',
                width : 150,
                sort : true
            }, {
                field : 'info',
                title : '考试描述',
                width : 300,
            }, {
                field : 'total',
                title : '总分',
                align : 'center',
                width : 80,
            }, {
                field : 'course',
                title : '课程',
                align : 'center',
                width : 150
            }, {
                field : 'courseId',
                title : '课程ID',
                align : 'center',
                hide: true
            }, {
                field : 'teacher',
                title : '负责教师',
                width: 100,
                align : 'center'
            }, {
                field : 'teacherId',
                title : '教师ID',
                align : 'center',
                hide: true
            }, {
                field : 'status',
                title : '批改状态',
                align : 'center',
                templet: '#statusTpl',
                width:  100
            }, {
                fixed : 'right',
                title :'操作',
                toolbar : '#barExamMange',
                align : 'center',
                width : 200
            } ] ],
            page : true,
            response: {
                statusCode: 200,    //设置返回码为200，默认0
                dataName: 'objs'    //设置返回对象为objs,默认data
            }
        });


        /**
         * @todo 获取所有的课程
         */
        $.ajax({
            type: "POST",
            url: "/getCourses",
            dataType: "json",
            success: function (data) {
                let courses = "";
                layui.each(data.objs, function(index, obj) {
                    courses += "<option value='"+obj.id+"'>"+obj.course+"</option>"
                });
                $("#searchCourse").append(courses);
                form.render();
            }
        });

        /**
         * @todo 获取所有的老师
         */
        $.ajax({
            type: "POST",
            url: "/getAllTeachers",
            dataType: "json",
            success: function (data) {
                let teachers = "";
                layui.each(data.objs, function(index, obj) {
                    teachers += "<option value='"+obj.id+"'>"+obj.name+"</option>"
                });
                $("#searchTeacher").append(teachers);
                form.render();
            }
        });

        /**
         * 关闭加载按钮
         */
        layer.close(loading);

    });

    /**
     * @todo 模糊查询表单
     */
    form.on('submit(searchBtn)', function(){
        table.reload('endExamListAdm',{
            where: {
                examName:$("#searchExamName").val(),
                courseId:$(".course .layui-this").attr("lay-value") == undefined?'':$(".course .layui-this").attr("lay-value"),
                teacherId:$(".teacher .layui-this").attr("lay-value") == undefined?'':$(".teacher .layui-this").attr("lay-value")
            }
        });
        return false;
    });


    /**
     * @todo 监听头部操作按钮（添加，批量删除）
     */
    table.on('toolbar(exam)', function(obj){
        let checkStatus = table.checkStatus(obj.config.id);
        let data = checkStatus.data;

        if (obj.event === 'add') {  //添加
            //打开弹出层
            var that = this;
            layer.open({
                type : 1,
                title : "考试添加",
                area: ['850px', '750px'],
                content: $('#addFrame'),
                cancel: function(index, layero){   //点击弹出层右上角X触发
                    //清除所有弹出层数据
                    $("#addForm")[0].reset();
                    $("#editForm")[0].reset();
                    $("#addClaForm")[0].reset();
                    //关闭弹出层
                    layer.closeAll();
                    return false;
                }
            });
        } else if (obj.event === 'delMult') {   //批量删除
            //删除提示
            layer.confirm('确认删除'+data.length+'条？', function(){
                //删除
                $.ajax({
                    type: "POST",
                    url: "/admin/delExams",
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
    table.on('tool(exam)', function(obj){
        let data = obj.data;

        if(obj.event === 'del'){    //删除
            layer.confirm('确认删除？', function(index){
                //删除
                $.ajax({
                    type: "POST",
                    url: "/admin/delExam",
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

        } else if(obj.event === 'edit'){    //更新
            //打开弹出层
            layer.open({
                type : 1,
                title : "考试编辑",
                area: ['850px', '750px'],
                content: $('#editFrame'),
                cancel: function(index, layero){    //点击弹出层右上角X触发
                    //清除所有弹出层数据
                    $("#addForm")[0].reset();
                    $("#editForm")[0].reset();
                    $("#addClaForm")[0].reset();
                    //关闭弹出层
                    layer.closeAll();
                }
            });

            //表单初始赋值
            data.timeScope = data.startTime + " - " +data.endTime;
            form.val('examFilter', data);

            //存入隐藏域
            $("#saveExamId").val(data.id);

        } else if(obj.event === 'claManage') {
            table.render({
                elem : '#claManageList',
                url : '/admin/getExamClas?examId=' + data.id,
                id: 'claManageList',
                page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
                    layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
                    ,groups: 1 //只显示 1 个连续页码
                    ,first: false //不显示首页
                    ,last: false //不显示尾页
                },
                cols : [ [ {
                    field : 'id',
                    title : 'ID',
                    align : 'center',
                    unresize : true,
                    sort : true,
                    hide: true
                }, {
                    field : 'department',
                    title :'院系',
                    align : 'center'
                }, {
                    field : 'departmentId',
                    title :'院系ID',
                    width : 80,
                    align : 'center',
                    hide: true
                }, {
                    field : 'major',
                    title : '专业',
                    align : 'center'
                }, {
                    field : 'majorId',
                    title :'专业ID',
                    width : 80,
                    align : 'center',
                    hide: true
                }, {
                    field : 'cla',
                    title : '班级',
                    width : 100,
                    align : 'center'
                }, {
                    field : 'claId',
                    title :'班级ID',
                    width : 80,
                    align : 'center',
                    hide: true
                }, {
                    fixed : 'right',
                    title :'操作',
                    toolbar : '#barClaMange',
                    align : 'center',
                    width : 120
                } ] ],
                page : true,
                response: {
                    statusCode: 200,    //设置返回码为200，默认0
                    dataName: 'objs'    //设置返回对象为objs,默认data
                }
            });

            $("#examId").val(data.id);

            //打开弹出层
            layer.open({
                type : 1,
                title : "班级管理",
                area: ['600px', '500px'],
                content: $('#claManage'),
                cancel: function(index, layero){    //点击弹出层右上角X触发
                    //清除所有弹出层数据
                    $("#addForm")[0].reset();
                    $("#editForm")[0].reset();
                    $("#addClaForm")[0].reset();
                    //关闭弹出层
                    layer.closeAll();
                }
            });



        }
    });







    /**
     * @todo 为某一考试班添加级
     */
    form.on('submit(addCla)', function(data) {
        $.ajax({
            type: "POST",
            url: "/admin/addExamCla",
            data: data.field,
            dataType: "json",
            success: function (data) {
                if (data.code === 200) {
                    //刷新table
                    //$(".layui-laypage-btn")[0].click();
                    table.reload('claManageList',{});
                    layer.msg(data.msg,{icon: 1});
                } else {
                    layer.msg(data.msg,{icon: 2});
                }
                //清空弹出层的数据
                $("#addClaForm")[0].reset();
            }
        });
        return false;
    });



});