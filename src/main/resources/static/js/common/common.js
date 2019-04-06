layui.define(['jquery','table','form','upload'], function() {
    const $ = layui.jquery;

    $(function () {

        /**
         * @todo 每个iframe顶端显示当前菜单目录
         */
        myAjax("post","/getMenu",{id: $("#menuId",parent.document).attr("value")},function(data){
            let curmenu = {
                id: data.id,
                level:data.level
            };

            myAjax("post","/getCurMenu",curmenu,function(data){
                let menu = JSON.parse(data);
                let nav = "<span lay-separator=''>/</span><a href='"+menu.url
                    +"'>"+menu.menuName
                    +"</a>";
                if (!$.isEmptyObject(menu.secMenu)) {
                    nav += "<span lay-separator=''>/</span><a href='"+menu.secMenu[0].secUrl
                        +"'><cite>"+menu.secMenu[0].secMenu
                        +"</cite></a>"
                }
                $(".page-nav span").append(nav);
            });

        },"json");

    });

})