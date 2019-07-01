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
            elem : '#stuGraListAdm',
            url : '/admin/getStuGrades',
            toolbar : '#toolbar',
            id: 'stuGraListAdm',
            width : 1000,
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
                toolbar : '#barStuMange',
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
                $("#selectDepartment").append(departments);
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
                $("#selectMajor").append(majors);
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
                $("#selectCla").append(clas);
                $("#searchCla").append(clas);
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
        table.reload('stuGraListAdm',{
            where: {
                id:$("#searchId").val(),
                name:$("#searchName").val(),
                departmentId:$(".department .layui-this").attr("lay-value") == undefined?'':$(".department .layui-this").attr("lay-value"),
                majorId:$(".major .layui-this").attr("lay-value") == undefined?'':$(".major .layui-this").attr("lay-value"),
                claId:$(".cla .layui-this").attr("lay-value") == undefined?'':$(".cla .layui-this").attr("lay-value")
            }
        });
        return false;
    });

    /**
     * @todo table 监听表格操作按钮（删除，更新）
     * @param obj
     */
    table.on('tool(grade)', function(obj){
        let data = obj.data;

        if(obj.event === 'gradeManage'){    //成绩管理
            table.render({
                elem : '#graManageList',
                url : '/admin/getGraByStudentId?studentId=' + data.id,
                toolbar : '#toolbar',
                id: 'graManageList',
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
                    field : 'studentId',
                    title : '学生ID',
                    align : 'center',
                    hide: true
                }, {
                    field : 'courseId',
                    title : '课程ID',
                    align : 'center',
                    hide: true
                }, {
                    field : 'claId',
                    title : '班级ID',
                    align : 'center',
                    hide: true
                }, {
                    field : 'course',
                    title :'课程',
                    align : 'center'
                }, {
                    field : 'num',
                    title :'学分',
                    width : 80,
                    align : 'center',
                }, {
                    field : 'type',
                    title : '类型',
                    align : 'center',
                    width : 100,
                    templet : '#typeTpl'
                }, {
                    field : 'score',
                    title :'成绩',
                    width : 80,
                    edit: 'text',
                    align : 'center',
                }, {
                    fixed : 'right',
                    title :'操作',
                    toolbar : '#barGradeMange',
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
                title : "成绩管理",
                area: ['600px', '750px'],
                content: $('#graManage'),
                cancel: function(index, layero){    //点击弹出层右上角X触发
                    //关闭弹出层
                    layer.closeAll();
                }
            });
        }
    });


    table.on('tool(gradeManage)', function(obj){
        let data = obj.data;

        if(obj.event === 'edit'){    //修改成绩
            $.ajax({
                type: "POST",
                url: "/admin/editGradeNC",
                data: JSON.stringify(data),
                dataType: "json",
                contentType:'application/json;charset=UTF-8',
                success: function (data) {
                    if (data.code === 200) {
                        //刷新table
                        table.reload('graManageList',{});
                        layer.msg(data.msg,{icon: 1});
                    } else {
                        layer.msg(data.msg,{icon: 2});
                    }
                }
            });
        }
    });

});