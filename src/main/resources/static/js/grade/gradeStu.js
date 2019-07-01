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
            elem : '#stuGraList',
            url : '/student/getGraByStudentId',
            toolbar : '#toolbar',
            id: 'stuGraList',
            width : 800,
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
                field : 'course',
                title : '课程',
                align : 'center',
                sort : true
            }, {
                field : 'type',
                title :'类型',
                width : 100,
                align : 'center',
                templet: '#typeTpl'
            }, {
                field : 'num',
                title :'学分',
                width : 100,
                align : 'center'
            }, {
                field : 'score',
                title : '成绩',
                width : 120,
                align : 'center'
            } ] ],
            page : true,
            response: {
                statusCode: 200,    //设置返回码为200，默认0
                dataName: 'objs'    //设置返回对象为objs,默认data
            }
        });

        /**
         * 关闭加载按钮
         */
        layer.close(loading);

    });


});