layui.config({
    base: '/js/',
}).use('admin');
layui.use(['jquery', 'admin', 'table','form'], function() {
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
                field : 'address',
                title : '考试地点',
                align : 'center'
            }, {
                field : 'startTime',
                title : '开始时间',
                width: 100,
                align : 'center'
            }, {
                field : 'endTime',
                title : '结束时间',
                width: 100,
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
        table.reload('examListAdm',{
            where: {
                examName:$("#searchExamName").val(),
                courseId:$(".course .layui-this").attr("lay-value") == undefined?'':$(".course .layui-this").attr("lay-value"),
                teacherId:$(".teacher .layui-this").attr("lay-value") == undefined?'':$(".teacher .layui-this").attr("lay-value")
            }
        });
        return false;
    });

});