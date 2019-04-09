layui.config({
	base: '/js/',
}).use('admin');
layui.use(['laydate', 'jquery', 'admin', 'table'], function() {
	const $ = layui.jquery,
		table = layui.table,
		laydate = layui.laydate,
		form = layui.form;

	$(function () {
		/**
		 * layui加载层，支持0-2
		 */
		let loading = layer.load(2, {shade: false});

		//执行一个laydate实例
		laydate.render({
			elem: '#searchCreateDate' //指定元素
		});

		/**
		 * @todo 定义table
		 */
		table.render({
			elem : '#stuListAdm',
			url : '/admin/getStudents',
			toolbar : '#toolbar',
			id: 'stuAdmTable',
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
		 * @todo 获取所有的院系
		 */
		ajax({
			url: "/getDepartments",
			success: function (data){
				let departments = "";
				layui.each(JSON.parse(data).objs, function(index, obj) {
					departments += "<option value='"+obj.id+"'>"+obj.department+"</option>"
				});
				$("#selectDepartment").append(departments);
				form.render();
			}
		});

		/**
		 * @todo 获取所有的专业
		 */
		ajax({
			url: "/getMajors",
			success: function (data){
				let majors = "";
				layui.each(JSON.parse(data).objs, function(index, obj) {
					majors += "<option value='"+obj.id+"'>"+obj.major+"</option>"
				});
				$("#selectMajor").append(majors);
				form.render();
			}
		});

		/**
		 * @todo 获取所有的班级
		 */
		ajax({
			url: "/getClas",
			success: function (data){
				let clas = "";
				layui.each(JSON.parse(data).objs, function(index, obj) {
					clas += "<option value='"+obj.id+"'>"+obj.cla+"</option>"
				});
				$("#selectCla").append(clas);
				form.render();
			}
		});



		/**
		 * 关闭加载按钮
		 */
		layer.close(loading);
	});

	form.on('submit(searchBtn)', function(){
		table.reload('stuAdmTable',{
			where: {
				id:$("#searchId").val(),
				name:$("#searchName").val(),
				dateStr:$("#searchCreateDate").val(),
				status:$(".status .layui-this").attr("lay-value") == undefined?'':$(".status .layui-this").attr("lay-value"),
				departmentId:$(".department .layui-this").attr("lay-value") == undefined?'':$(".department .layui-this").attr("lay-value"),
				majorId:$(".major .layui-this").attr("lay-value") == undefined?'':$(".major .layui-this").attr("lay-value"),
				claId:$(".cla .layui-this").attr("lay-value") == undefined?'':$(".cla .layui-this").attr("lay-value")
			}
		});
		form.render();
	});



});