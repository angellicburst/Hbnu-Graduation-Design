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
			elem : '#stuListTea',
			url : '/teacher/getStudents',
			toolbar : '#toolbar',
			id: 'stuListTea',
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
				field : 'status',
				title : '状态',
				align : 'center',
				width : 80,
				templet: '#statusTpl'
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
		 * @todo 关闭加载按钮
		 */
		layer.close(loading);

	});

	/**
	 * @todo 模糊查询表单
	 */
	form.on('submit(searchBtn)', function(){
		table.reload('stuListAdm',{
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
		return false;
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

				$("#selectMajor").empty();
				$("#selectCla").empty();
				let majors = "<option value=''>专业</option>";
				let clas = "<option value=''>班级</option>";
				layui.each(data, function(index, obj) {
					majors += "<option value='"+obj.id+"'>"+obj.major+"</option>"
				});
				$("#selectMajor").append(majors);
				$("#selectCla").append(clas);
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

				$("#selectCla").empty();
				let clas = "<option value=''>班级</option>";
				layui.each(data, function(index, obj) {
					clas += "<option value='"+obj.id+"'>"+obj.cla+"</option>"
				});
				$("#selectCla").append(clas);

				form.render();
			}
		});
		return false;
	});

});