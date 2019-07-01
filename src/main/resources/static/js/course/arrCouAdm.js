layui.config({
    base: '/js/',
}).use('admin');
layui.use(['laydate', 'jquery', 'admin', 'table', 'upload'], function() {
    const $ = layui.jquery,
        table = layui.table,
        laydate = layui.laydate,
        form = layui.form;

    $(function () {
        /**
         * layui加载层，支持0-2
         */
        let loading = layer.load(2, {shade: false});

        //执行laydate实例
        laydate.render({
            elem: '#addDateScope'//指定元素
            ,range: true
        });
        laydate.render({
            elem: '#editDateScope'//指定元素
            ,range: true
        });

        /**
         * @todo 定义table
         */
        table.render({
            elem : '#arrCouListAdm',
            url : '/admin/getArrangeCourses',
            toolbar : '#toolbar',
            id: 'arrCouList',
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
                hide: true,
                unresize : true,
                sort : true
            }, {
                field : 'course',
                title : '课程',
                align : 'center',
                sort : true
            }, {
                field : 'courseId',
                title : '课程ID',
                align : 'center',
                hide: true
            }, {
                field : 'teacher',
                title : '教师',
                align : 'center',
                width : 100
            }, {
                field : 'address',
                title : '上课地址',
                align : 'center',
                width : 200,
            }, {
                field : 'startDate',
                title : '开始时间',
                width : 110,
                align : 'center'
            }, {
                field : 'endDate',
                title : '结束时间',
                width : 110,
                align : 'center'
            },{
                field : 'method',
                title :'上课方式',
                width : 100,
                templet: '#methodTpl',
                align : 'center'
            },{
                field : 'week',
                title :'上课时间',
                width : 100,
                templet: '#weekTpl',
                align : 'center'
            }, {
                field : 'node',
                title :'具体时间',
                width : 100,
                templet: '#nodeTpl',
                align : 'center'
            }, {
                field : 'department',
                title :'院系',
                width : 210,
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
                width : 180,
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
                width : 80,
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
                toolbar : '#barCouMange',
                width : 130,
                align : 'center'
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
                $("#addCourse").append(courses);
                $("#editCourse").append(courses);
                $("#searchCourse").append(courses);
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
                $("#editDepartment").append(departments);
                $("#searchDepartment").append(departments);
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
                $("#editMajor").append(majors);
                $("#searchMajor").append(majors);
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
                $("#editCla").append(clas);
                $("#searchCla").append(clas);
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
                $("#addTeacher").append(teachers);
                $("#editTeacher").append(teachers);
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
        table.reload('arrCouList',{
            where: {
                courseId:$(".course .layui-this").attr("lay-value") == undefined?'':$(".course .layui-this").attr("lay-value"),
                departmentId:$(".department .layui-this").attr("lay-value") == undefined?'':$(".department .layui-this").attr("lay-value"),
                majorId:$(".major .layui-this").attr("lay-value") == undefined?'':$(".major .layui-this").attr("lay-value"),
                claId:$(".cla .layui-this").attr("lay-value") == undefined?'':$(".cla .layui-this").attr("lay-value")
            }
        });
        return false;
    });

    /**
     * @todo 监听头部操作按钮（添加，批量删除）
     */
    table.on('toolbar(arrCouAdm)', function(obj){
        let checkStatus = table.checkStatus(obj.config.id);
        let data = checkStatus.data;

        if (obj.event === 'add') {  //添加
            //打开弹出层
            layer.open({
                type : 1,
                title : "菜单添加",
                area: ['1000px', '700px'],
                content: $('#addFrame'),
                cancel: function(index, layero){   //点击弹出层右上角X触发
                    //清除所有弹出层数据
                    $("#addForm")[0].reset();
                    $("#editForm")[0].reset();
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
                    url: "/admin/delCourseArranges",
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
    table.on('tool(arrCouAdm)', function(obj){
        let data = obj.data;

        if(obj.event === 'del'){    //删除
            layer.confirm('确认删除？', function(index){
                //删除
                $.ajax({
                    type: "POST",
                    url: "/admin/delCourseArrange",
                    data: data,
                    dataType: "json",
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
                title : "课程安排编辑",
                area: ['1000px', '700px'],
                content: $('#editFrame'),
                cancel: function(index, layero){    //点击弹出层右上角X触发
                    //清除所有弹出层数据
                    $("#addForm")[0].reset();
                    $("#editForm")[0].reset();
                    //关闭弹出层
                    layer.closeAll();
                }
            });

            //表单初始赋值
            data.dateScope = data.startDate + " - " +data.endDate;
            form.val('arrCouFilter', data);

            //获取院系ID，专业ID，班级ID
            let depId = data.departmentId;
            let maId = data.majorId;
            let claId = data.claId;

            //隐藏域保存菜单Id和菜单图片名称
            $("#saveId").val(data.id);

            //三级联动
            $.ajax({
                type: "POST",
                url: "/getMajorsByDep",
                data: {
                    departmentId : depId
                },
                dataType: "json",
                success: function (data) {
                    $("#editMajor").empty();
                    $("#editCla").empty();
                    let majors = "<option value=''>专业</option>";
                    layui.each(data, function(index, obj) {

                        if (maId == obj.id) {
                            majors += "<option value='"+obj.id+"' selected>"+obj.major+"</option>"
                        } else {
                            majors += "<option value='"+obj.id+"'>"+obj.major+"</option>"
                        }

                    });
                    $("#editMajor").append(majors);
                    form.render();
                    $.ajax({
                        type: "POST",
                        url: "/getClasByMaj",
                        data: {
                            majorId : maId
                        },
                        dataType: "json",
                        success: function (data) {
                            $("#editCla").empty();
                            let clas = "<option value=''>班级</option>";
                            layui.each(data, function(index, obj) {
                                if (claId == obj.id) {
                                    clas += "<option value='"+obj.id+"' selected>"+obj.cla+"</option>"
                                } else {
                                    clas += "<option value='"+obj.id+"'>"+obj.cla+"</option>"
                                }

                            });
                            $("#editCla").append(clas);
                            form.render();
                        }
                    });
                }
            });
        }
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
                $("#editMajor").empty();
                $("#editCla").empty();
                let majors = "<option value=''>专业</option>";
                let clas = "<option value=''>班级</option>";
                layui.each(data, function(index, obj) {
                    majors += "<option value='"+obj.id+"'>"+obj.major+"</option>"
                });

                $("#addMajor").append(majors);
                $("#addCla").append(clas);
                $("#editMajor").append(majors);
                $("#editCla").append(clas);
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
                $("#editCla").empty();
                let clas = "<option value=''>班级</option>";
                layui.each(data, function(index, obj) {
                    clas += "<option value='"+obj.id+"'>"+obj.cla+"</option>"
                });
                $("#addCla").append(clas);
                $("#editCla").append(clas);
                form.render();
            }
        });
        return false;
    });

    /**
     * @todo 添加课程安排
     */
    form.on('submit(addArrCou)', function(data) {
        $.ajax({
            type: "POST",
            url: "/admin/addCourseArrange",
            data: data.field,
            dataType: "json",
            //contentType:'application/json;charset=UTF-8',
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
                $("#addForm")[0].reset();
            }
        });
        return false;
    });

    /**
     * @todo 更新课程安排
     */
    form.on('submit(editArrCou)', function(data) {
        $.ajax({
            type: "POST",
            url: "/admin/editCourseArrange",
            data: data.field,
            dataType: "json",
            //contentType:'application/json;charset=UTF-8',
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