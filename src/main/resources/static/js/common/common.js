layui.define(['jquery','table','form','upload'], function() {
    const $ = layui.jquery;

    $(function () {

        /**
         * @todo 每个iframe顶端显示当前菜单目录
         */
        $.ajax({
            type: "POST",
            url: "/getMenu",
            data: {
                id: $("#menuId",parent.document).attr("value")
            },
            dataType: "json",
            success:function (data) {
                let curmenu = {
                    id: data.id,
                    level:data.level
                };

                $.ajax({
                    type: "POST",
                    url: "/getCurMenu",
                    data: curmenu,
                    dataType: "json",
                    success: function (data) {
                        let nav = "<span lay-separator=''>/</span><a href='"+data.url
                            +"'>"+data.menuName
                            +"</a>";
                        if (!$.isEmptyObject(data.secMenu)) {
                            nav += "<span lay-separator=''>/</span><a href='"+data.secMenu[0].secUrl
                                +"'><cite>"+data.secMenu[0].secMenu
                                +"</cite></a>"
                        }
                        $(".page-nav span").append(nav);
                    }
                })
            }
        });

    });

})