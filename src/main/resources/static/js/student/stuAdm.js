layui.config({
	base: '/js/',
}).use('admin');
layui.use(['laydate', 'jquery', 'admin', 'table'], function() {
	const $ = layui.jquery,
		table = layui.table;

	$(function () {
		/**
		 * layui加载层，支持0-2
		 */
		let loading = layer.load(2, {shade: false});

		/**
		 * @todo 定义table
		 */
		table.render({
			elem : '#stuListAdm',
			url : '/admin/getStudents',
			toolbar : '#toolbar',
			type : 'POST',
			//width : 1200,
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
				align : 'center'
			}, {
				field : 'email',
				title : '邮箱',
				width : 200,
				align : 'center'
			}, {
				field : 'year',
				title : '学年制',
				width : 80,
				align : 'center'
			}, {
				field : 'createDate',
				title : '入学时间',
				width : 120,
				align : 'center'
			},{
				field : 'status',
				title : '状态',
				align : 'center',
				width : 80,
				templet: '#statusTpl'
			}, {
				field : 'department',
				title :'院系',
				align : 'center'
			}, {
				field : 'major',
				title : '专业',
				align : 'center'
			}, {
				field : 'cla',
				title : '班级',
				width : 80,
				align : 'center'
			}, {
				fixed : 'right',
				title :'操作',
				toolbar : '#barStuMange',
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
	})


	/*用户-停用*/
	window.member_stop = function (obj, id) {
		layer.confirm('确认要停用吗？', function(index) {
			if($(obj).attr('title') == '启用') {

				//发异步把用户状态进行更改
				$(obj).attr('title', '停用')
				$(obj).find('i').html('&#xe62f;');

				$(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
				layer.msg('已停用!', {
					icon: 5,
					time: 1000
				});

			} else {
				$(obj).attr('title', '启用')
				$(obj).find('i').html('&#xe601;');

				$(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
				layer.msg('已启用!', {
					icon: 5,
					time: 1000
				});
			}
		});
	}

});