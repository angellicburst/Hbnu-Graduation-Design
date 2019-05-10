layui.config({
    base: '/js/',
}).use('admin');
layui.use(['laydate','jquery', 'admin', 'table','form'], function() {
    const $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate,
        form = layui.form;



    $(function() {
        /**
         * layui加载层，支持0-2
         */
        let loading = layer.load(2, {shade: false});

        //执行laydate实例
        laydate.render({
            elem: '#addTimeScope'
            ,type: 'datetime'
            ,range: true
        });
        laydate.render({
            elem: '#editTimeScope'
            ,type: 'datetime'
            ,range: true
        });

        /**
         * @todo 定义table
         */
        table.render({
            elem : '#examListAdm',
            url : '/admin/getExams',
            toolbar : '#toolbar',
            id: 'examListAdm',
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
                width : 200,
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
                field : 'address',
                title : '考试地点',
                align : 'center'
            }, {
                field : 'startTime',
                title : '开始时间',
                width: 160,
                align : 'center'
            }, {
                field : 'endTime',
                title : '结束时间',
                width: 160,
                align : 'center'
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
                field : 'createDate',
                title : '创建时间',
                width: 120,
                align : 'center'
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
                $("#addCourse").append(courses);
                $("#editCourse").append(courses);
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
                $("#addTeacher").append(teachers);
                $("#editTeacher").append(teachers);
                form.render();
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
                $("#addDepartment").append(departments);
                form.render();
            }
        });

        /**
         * @todo 获取所有的专业
         */
        $.ajax({
            type: "POST",
            url: "/getMajors",
            dataType: "json",
            success: function (data) {
                let majors = "";
                layui.each(data.objs, function(index, obj) {
                    majors += "<option value='"+obj.id+"'>"+obj.major+"</option>"
                });
                $("#addMajor").append(majors);
                form.render();
            }
        });

        /**
         * @todo 获取所有的班级
         */
        $.ajax({
            type: "POST",
            url: "/getClas",
            dataType: "json",
            success: function (data) {
                let clas = "";
                layui.each(data.objs, function(index, obj) {
                    clas += "<option value='"+obj.id+"'>"+obj.cla+"</option>"
                });
                $("#addCla").append(clas);
                form.render();
            }
        });

        /**
         * 关闭加载按钮
         */
        layer.close(loading);

    });

    /**
     * @todo 院系专业班级三级联动
     */
    form.on('select(department)', function(data) {
        $.ajax({
            type: "POST",
            url: "/getMajorsByDep",
            data: {
                departmentId : data.value
            },
            dataType: "json",
            success: function (data) {

                $("#addMajor").empty();
                $("#addCla").empty();
                let majors = "<option value=''>专业</option>";
                let clas = "<option value=''>班级</option>";
                layui.each(data, function(index, obj) {
                    majors += "<option value='"+obj.id+"'>"+obj.major+"</option>"
                });
                $("#addMajor").append(majors);
                $("#addCla").append(clas);
                form.render();
            }
        });
        return false;
    });
    form.on('select(major)', function(data) {
        $.ajax({
            type: "POST",
            url: "/getClasByMaj",
            data: {
                majorId : data.value
            },
            dataType: "json",
            success: function (data) {

                $("#addCla").empty();
                let clas = "<option value=''>班级</option>";
                layui.each(data, function(index, obj) {
                    clas += "<option value='"+obj.id+"'>"+obj.cla+"</option>"
                });
                $("#addCla").append(clas);

                form.render();
            }
        });
        return false;
    });


    /**
     * @todo 模糊查询表单
     */
    form.on('submit(searchBtn)', function(){
        table.reload('examListAdm',{
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
                area: ['850px', '600px'],
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
                area: ['850px', '600px'],
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
                    width : 180
                } ] ],
                page : true,
                response: {
                    statusCode: 200,    //设置返回码为200，默认0
                    dataName: 'objs'    //设置返回对象为objs,默认data
                }
            });

            $("#examId").val(data.id);
            $("#courseId").val(data.courseId);
            $("#saveEndTime").val(data.endTime);

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
     * @todo examCla table 监听表格操作按钮（删除）
     * @param obj
     */
    table.on('tool(examCla)', function(obj) {
        let data = obj.data;

        if (obj.event === 'del') {    //删除
            layer.confirm('确认删除？', function (index) {
                //删除
                $.ajax({
                    type: "POST",
                    url: "/admin/delExamCla",
                    data: data,
                    dataType: "json",
                    success: function (data) {
                        if (data.code === 200) {
                            //刷新table
                            table.reload('claManageList',{});
                            layer.msg(data.msg, {icon: 1});
                        } else {
                            layer.msg(data.msg, {icon: 2});
                        }
                    }
                });
            });
        } if (obj.event === 'grade') {
            if (new Date($("#saveEndTime").val()).getTime() < new Date()) {
                let courseId = $("#courseId").val(),
                    claId = data.claId;


                table.render({
                    elem : '#gradeManageList',
                    url : '/admin/getGradesExamCla?courseId=' + courseId + '&claId=' +claId,
                    id: 'gradeManageList',
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
                        sort : true
                    }, {
                        field : 'student',
                        title :'学生',
                        width : 120,
                        align : 'center'
                    }, {
                        field : 'student_id',
                        title :'学生ID',
                        width : 80,
                        align : 'center',
                        hide: true
                    }, {
                        field : 'course',
                        title : '课程',
                        width : 190,
                        align : 'center'
                    }, {
                        field : 'course_id',
                        title :'课程ID',
                        width : 80,
                        align : 'center',
                        hide: true
                    }, {
                        field : 'cla',
                        title : '班级',
                        align : 'center'
                    }, {
                        field : 'claId',
                        title :'班级ID',
                        width : 80,
                        align : 'center',
                        hide: true
                    }, {
                        field : 'score',
                        title : '成绩',
                        align : 'center',
                        edit: 'text'
                    }, {
                        fixed : 'right',
                        title :'操作',
                        toolbar : '#barGradeMange',
                        align : 'center',
                        width : 130
                    } ] ],
                    page : true,
                    response: {
                        statusCode: 200,    //设置返回码为200，默认0
                        dataName: 'objs'    //设置返回对象为objs,默认data
                    }
                });



                //打开弹出层
                layer.open({
                    type : 1,
                    title : "成绩管理",
                    area: ['900px', '500px'],
                    content: $('#gradeManage'),
                    cancel: function(index, layero){    //点击弹出层右上角X触发
                        //清除所有弹出层数据
                        $("#addForm")[0].reset();
                        $("#editForm")[0].reset();
                        $("#addClaForm")[0].reset();
                        //关闭弹出层
                        layer.close(index);
                    }
                });
            } else {
                layer.msg("该考试还没有结束", {icon: 2});
            }

        }
    });

    /**
     * @todo grade table 监听表格操作按钮（删除）
     * @param obj
     */
    table.on('tool(examClaGrade)', function(obj) {
        let data = obj.data;

        data.examId = $("#examId").val();

        if (obj.event === 'addGrade') {    //添加成绩
            $.ajax({
                type: "POST",
                url: "/admin/editGrade",
                data: JSON.stringify(data),
                dataType: "json",
                contentType:'application/json;charset=UTF-8',
                success: function (data) {
                    if (data.code === 200) {
                        //刷新table
                        table.reload('gradeManageList',{});
                        table.reload('examListAdm',{});
                        layer.msg(data.msg,{icon: 1});
                    } else {
                        layer.msg(data.msg,{icon: 2});
                    }
                }
            });
        }
    });

    /**
     * @todo 添加考试
     */
    form.on('submit(addExam)', function(data) {
        $.ajax({
            type: "POST",
            url: "/admin/addExam",
            data: data.field,
            dataType: "json",
            success: function (data) {
                //关闭弹出层
                layer.closeAll();
                if (data.code === 200) {
                    //刷新table
                    table.reload('examListAdm',{});
                    layer.msg(data.msg,{icon: 1});
                } else {
                    layer.msg(data.msg,{icon: 2});
                }
                //清空弹出层的数据
                $("#addForm")[0].reset();
            }
        });
        return false;
    });

    /**
     * @todo 更新课程安排
     */
    form.on('submit(editExam)', function(data) {
        $.ajax({
            type: "POST",
            url: "/admin/editExam",
            data: JSON.stringify(data.field),
            dataType: "json",
            contentType:'application/json;charset=UTF-8',
            success: function (data) {
                //关闭弹出层
                layer.closeAll();
                if (data.code === 200) {
                    //刷新table
                    table.reload('examListAdm',{});
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