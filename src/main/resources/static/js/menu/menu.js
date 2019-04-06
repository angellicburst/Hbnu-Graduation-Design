layui.config({
    base: '/js/',
}).use('admin');
layui.use(['jquery', 'admin', 'table','form'], function() {
    const $ = layui.jquery,
        table = layui.table,
        form = layui.form;

    /**
     * layui加载层，支持0-2
     */
    let loading = layer.load(2, {shade: false});

    $(function() {
        /**
         * @todo 定义table
         */
        table.render({
            elem : '#menuList',
            url : '/menus',
            toolbar : '#toolbarMenu',
            type : 'POST',
            width : 1200,
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
                sort : true
            }, {
                field : 'menuName',
                title : '菜单名称',
                align : 'center',
                width : 150,
                sort : true
            }, {
                field : 'url',
                title : 'URL',
                width : 200,
            }, {
                field : 'level',
                title : '菜单等级',
                align : 'center'
            }, {
                field : 'img',
                title : '菜单图片',
                width : 150
            }, {
                field : 'parentId',
                title : '父节点ID',
                align : 'center'
            }, {
                field : 'roleId',
                title : '访问角色',
                align : 'center',
                templet: '#roleTpl',
                width : 100
            }, {
                fixed : 'right',
                title :'操作',
                toolbar : '#barMenuMange',
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
         * 关闭加载按钮
         */
        layer.close(loading);

        /**
         * @todo 获取所有角色
         */
        myAjax("post","/getRoles",null,function(data) {
            let roles = "<option value=''></option>";
            layui.each(data, function(index, obj) {
                roles += "<option value='"+obj.id+"'>"+obj.role+"</option>"
            });
            $(".selectRoles").append(roles);
            form.render();
        },"json");

        /**
         * @todo 监听头部操作按钮（添加，批量删除）
         */
        table.on('toolbar(menu)', function(obj){
            let checkStatus = table.checkStatus(obj.config.id);
            let data = checkStatus.data;

            if (obj.event === 'add') {  //添加
                //打开弹出层
                layer.open({
                    type : 1,
                    title : "菜单添加",
                    area: ['520px', '650px'],
                    content: $('#addFrame'),
                    cancel: function(index, layero){   //点击弹出层右上角X触发
                        //清除所有弹出层数据
                        $("#addForm")[0].reset();
                        $("#editForm")[0].reset();
                        //关闭弹出层
                        layer.close(index);
                        return false;
                    }
                });
            } else if (obj.event === 'delMult') {   //批量删除
                //删除提示
                layer.confirm('确认删除'+data.length+'条？', function(){
                    //删除
                    delMult(JSON.stringify(data),"/delMenus");
                    //重新加载table
                    table.reload('menuList',{});
                });
            }
        });

        /**
         * @todo table 监听表格操作按钮（删除，更新）
         * @param obj
         * '+$('.layui-table').attr("lay-filter")+'
         */
        table.on('tool(menu)', function(obj){
            let data = obj.data;

            if(obj.event === 'del'){    //删除
                layer.confirm('确认删除？', function(index){
                    //删除
                    del(obj.data,"/delMenu");
                    //重新加载table
                    table.reload('menuList',{});
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){    //更新
                //打开弹出层
                layer.open({
                    type : 1,
                    title : "菜单编辑",
                    area: ['520px', '650px'],
                    content: $('#editFrame'),
                    cancel: function(index, layero){    //点击弹出层右上角X触发
                        //清除所有弹出层数据
                        $("#addForm")[0].reset();
                        $("#editForm")[0].reset();
                        //关闭弹出层
                        layer.close(index);
                        return false;
                    }
                });

                /*
                根据菜单等级判断
                等级1：可以上传图片，关闭父节点输入
                等级2：关闭上传图片，打开父节点输入
                 */
                if (data.level === 1) {
                    $("#editParentId").attr("disabled",true);
                    $("#editParentId").attr("class","layui-input layui-disabled");
                    $("#editParentId").css("background-color","#eee");
                } else if (data.level === 2) {
                    $("#editParentId").attr("disabled",false);
                    $("#editParentId").attr("class","layui-input");
                    $("#editParentId").css("background-color","#fff");
                }

                /*
                 * 获取要编辑的值
                 */
                ajax({
                    url: "/getMenu",
                    data: {
                        id: data.id
                    },
                    success:function () {
                        //表单初始赋值
                        form.val('menuFilter', data);
                        //隐藏域保存菜单Id和菜单图片名称
                        $("#saveId").val(data.id);
                        $("#saveImg").val(data.img);
                    }
                });
            }
        });
    });


});
