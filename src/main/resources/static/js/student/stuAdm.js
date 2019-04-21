layui.config({
	base: '/js/',
}).use('admin');
layui.use(['laydate', 'jquery', 'admin', 'table', 'upload'], function() {
	const $ = layui.jquery,
		table = layui.table,
		laydate = layui.laydate,
		form = layui.form,
		upload = layui.upload;

	$(function () {
		/**
		 * layui加载层，支持0-2
		 */
		let loading = layer.load(2, {shade: false});

		//执行laydate实例
		laydate.render({
			elem: '#searchCreateDate'//指定元素
		});
		laydate.render({
			elem: '#addCreateDate'//指定元素
		});

		/**
		 * @todo 定义table
		 */
		table.render({
			elem : '#stuListAdm',
			url : '/admin/getStudents',
			toolbar : '#toolbar',
			id: 'stuListAdm',
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
		$.ajax({
			type: "POST",
			url: "/getDepartments",
			dataType: "json",
			success: function (data) {
				let departments = "";
				layui.each(data.objs, function(index, obj) {
					departments += "<option value='"+obj.id+"'>"+obj.department+"</option>"
				});
				$(".selectDepartment").append(departments);
				$(".changeDep").append(departments);
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
				$(".selectMajor").append(majors);
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
				$(".selectCla").append(clas);
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
		form.render();
		return false;
	});

	/**
	 * @todo 监听头部操作按钮（添加，批量删除）
	 */
	table.on('toolbar(stuAdm)', function(obj){
		let checkStatus = table.checkStatus(obj.config.id);
		let data = checkStatus.data;

		if (obj.event === 'delMult') {   //批量删除
			//删除提示
			layer.confirm('确认删除'+data.length+'条？', function() {
				$.ajax({
					type: "POST",
					url: "/admin/delStudents",
					data: JSON.stringify(data),
					dataType: "json",
					contentType:"application/json",
					success: function (data) {
						if (data.code === 200) {
							layer.msg(data.msg,{icon: 1});
						} else {
							layer.msg(data.msg,{icon: 2});
						}
					}
				});
				form.render();
				//刷新table
				table.reload('stuListAdm',{});
				//关闭弹出层
				layer.closeAll();
			});
			console.log();
		}
	});

	/**
	 * @todo table 监听表格操作按钮（删除，更新）
	 * @param obj
	 */
	table.on('tool(stuAdm)', function(obj){
		let data = obj.data;

		if(obj.event === 'del') {    //删除
			layer.confirm('确认删除？', function(index) {
				//删除
				$.ajax({
					type: "POST",
					url: "/admin/delStudent",
					data: JSON.stringify(data),
					dataType: "json",
					contentType:'application/json;charset=UTF-8',
					success: function (data) {
						if (data.code === 200) {
							layer.msg(data.msg,{icon: 1});
						} else {
							layer.msg(data.msg,{icon: 2});
						}
					}
				});
				form.render();
				//刷新table
				table.reload('stuListAdm',{});
				//关闭弹出层
				layer.closeAll();

			});
		} else if(obj.event === 'edit') {    //更新
			//打开弹出层
			layer.open({
				type : 1,
				title : "菜单编辑",
				area: ['520px', '650px'],
				content: $('#editFrame'),
				cancel: function(index, layero) {    //点击弹出层右上角X触发
					//清除所有弹出层数据
					$("#addForm")[0].reset();
					$("#editForm")[0].reset();
					//关闭弹出层
					layer.closeAll();
				}
			});
		} else if(obj.event === 'stop' ||obj.event === 'use') {
			layer.confirm(obj.event === 'stop'?'确认停用？':'确认启用？', function(index) {
				//改变状态
				$.ajax({
					type: "POST",
					url: "/admin/changeStatus",
					data: JSON.stringify(data),
					dataType: "json",
					contentType:'application/json;charset=UTF-8',
					success: function (data) {
						if (data.code === 200) {
							layer.msg(data.msg,{icon: 1});
						} else {
							layer.msg(data.msg,{icon: 2});
						}
					}
				});
				form.render();
				//刷新table
				table.reload('stuListAdm',{});
				//关闭弹出层
				layer.closeAll();
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
				if (data === '[]') {
					$(".addMa select").attr("disabled",true);
					$(".addMa input").attr("class","layui-input layui-disabled");
					form.render();
					return;
				}
				$(".selectMajor").empty();
				let majors = "<option value=''>专业</option>";
				layui.each(data, function(index, obj) {
					majors += "<option value='"+obj.id+"'>"+obj.major+"</option>"
				});
				$(".selectMajor").append(majors);

				$(".addMa select").attr("disabled",false);
				$(".addMa input").attr("class","layui-input");

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
				if (data === '[]') {
					$(".addCla select").attr("disabled",true);
					$(".addCla input").attr("class","layui-input layui-disabled");
					form.render();
					return;
				}

				$(".selectCla").empty();
				let clas = "<option value=''>班级</option>";
				layui.each(data, function(index, obj) {
					clas += "<option value='"+obj.id+"'>"+obj.cla+"</option>"
				});
				$(".selectCla").append(clas);

				$(".addCla select").attr("disabled",false);
				$(".addCla input").attr("class",false);

				form.render();
			}
		});

		return false;
	});

	/**
	 * 批量导入学生
	 */
	upload.render({
		elem: '#addMult'
		,url: '/admin/addStudent'
		,method: 'POST'
		,accept: 'file'
		,exts: 'xls'
		,before: function(){
			layer.load(1, {shade: false});
		}
		,done: function(res){
			layer.closeAll(); //关闭loading
			if (res.code === 200) {
				//打印msg
				layer.msg(res.msg,{icon: 1});
				//刷新table
				table.reload('stuListAdm',{});
			} else {
				layer.msg(res.msg,{icon: 2});
			}
		}
		,error: function(){
			layer.closeAll('loading'); //关闭loading
		}
		,response: {
			statusCode: 200   //设置返回码为200，默认0
		}
	});

});