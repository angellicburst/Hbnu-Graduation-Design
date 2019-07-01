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
            elem : '#arrCouListTea',
            url : '/teacher/getArrangeCourses',
            toolbar : '#toolbar',
            id: 'arrCouListTea',
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
                $("#searchCla").append(clas);
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
        table.reload('arrCouListTea',{
            where: {
                courseId:$(".course .layui-this").attr("lay-value") == undefined?'':$(".course .layui-this").attr("lay-value"),
                departmentId:$(".department .layui-this").attr("lay-value") == undefined?'':$(".department .layui-this").attr("lay-value"),
                majorId:$(".major .layui-this").attr("lay-value") == undefined?'':$(".major .layui-this").attr("lay-value"),
                claId:$(".cla .layui-this").attr("lay-value") == undefined?'':$(".cla .layui-this").attr("lay-value")
            }
        });
        return false;
    });

});