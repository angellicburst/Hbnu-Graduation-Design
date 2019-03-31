layui.config({
	base: '/js/',
}).use('admin');
layui.use(['laydate', 'jquery', 'admin', 'table'], function() {
	var laydate = layui.laydate,
		$ = layui.jquery,
		admin = layui.admin,
		table = layui.table;
	//执行一个laydate实例
	laydate.render({
		elem : '#start' // 指定元素
	});
	// 执行一个laydate实例
	laydate.render({
		elem : '#end' // 指定元素
	});
	table.render({
		elem : '#memberList',
		url : '/json',
        page: { //支持传入 laypage 组件的所有参数（某些参数除外，如：jump/elem） - 详见文档
        layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
            //,curr: 5 //设定初始在第 5 页
            ,groups: 1 //只显示 1 个连续页码
            ,first: false //不显示首页
            ,last: false //不显示尾页
		},
		cols : [ [ {
			field : 'id',
			title : 'ID',
			width : 80,
			fixed : 'left',
			unresize : true,
			sort : true
		}, {
			field : 'username',
			title : '用户名',
			width : 100,
			sort : true
		}, {
			field : 'gender',
			title : '性别',
			width : 80,
		}, {
			field : 'phone',
			title : '电话',
			width : 160
		}, {
			field : 'email',
			title : '邮箱',
			width : 200
		}, {
			field : 'address',
			title : '地址',
			width : 300
		}, {
			field : 'createDate',
			title : '创建时间',
			width : 150
		}, {
			field : 'status',
			title : '状态',
			width : 80
		}, {
			fixed: 'right', 
			title:'操作', 
			toolbar: '#barDemo', 
			width:150
		} ] ],
		page : true
	});
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

	/*用户-删除*/
	window.member_del = function (obj, id) {
		layer.confirm('确认要删除吗？', function(index) {
			//发异步删除数据
			$(obj).parents("tr").remove();
			layer.msg('已删除!', {
				icon: 1,
				time: 1000
			});
		});
	}

	window.delAll = function (argument) {
		var data = tableCheck.getData();
		layer.confirm('确认要删除吗？' + data, function(index) {
			//捉到所有被选中的，发异步进行删除
			layer.msg('删除成功', {
				icon: 1
			});
			$(".layui-form-checked").not('.header').parents('tr').remove();
		});
	}

	/**
	 * @todo 每个iframe顶端显示当前菜单目录
	 */
	myAjax("post","/getMenu",{id: $("#menuId",parent.document).attr("value")},function(data){
		let curmenu = {
			id: data.id,
			level:data.level
		};

		myAjax("post","/getCurMenu",curmenu,function(data){
			menu = JSON.parse(data);
			let nav = "<span lay-separator=''>/</span><a href='"+menu.url
				+"'>"+menu.menuName
				+"</a>";
			if (!$.isEmptyObject(menu.secMenu)) {
				nav += "<span lay-separator=''>/</span><a href='"+menu.secMenu[0].secUrl
					+"'><cite>"+menu.secMenu[0].secMenu
					+"</cite></a>"
			}
			$(".layui-breadcrumb").append(nav);
		});
	},"json");
});