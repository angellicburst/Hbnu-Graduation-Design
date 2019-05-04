layui.define(['jquery'], function(exports) {
    const $ = layui.jquery;

    $(function () {


        $.ajax({
            type: "POST",
            url: "/admin/getHistogramData",
            dataType: "json",
            success: function (data) {
                $("#stuCounts").html(data.stuCounts);
                $("#teaCounts").html(data.teaCounts);
                $("#examCounts").html(data.examCounts);


                let dom = document.getElementById("container");
                let myChart = echarts.init(dom);
                let app = {};
                option = null;
                let posList = [
                    'left', 'right', 'top', 'bottom',
                    'inside',
                    'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
                    'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
                ];

                app.configParameters = {
                    rotate: {
                        min: -90,
                        max: 90
                    },
                    align: {
                        options: {
                            left: 'left',
                            center: 'center',
                            right: 'right'
                        }
                    },
                    verticalAlign: {
                        options: {
                            top: 'top',
                            middle: 'middle',
                            bottom: 'bottom'
                        }
                    },
                    position: {
                        options: echarts.util.reduce(posList, function (map, pos) {
                            map[pos] = pos;
                            return map;
                        }, {})
                    },
                    distance: {
                        min: 0,
                        max: 100
                    }
                };

                app.config = {
                    rotate: 90,
                    align: 'left',
                    verticalAlign: 'middle',
                    position: 'insideBottom',
                    distance: 15,
                    onChange: function () {
                        var labelOption = {
                            normal: {
                                rotate: app.config.rotate,
                                align: app.config.align,
                                verticalAlign: app.config.verticalAlign,
                                position: app.config.position,
                                distance: app.config.distance
                            }
                        };
                        myChart.setOption({
                            series: [{
                                label: labelOption
                            }, {
                                label: labelOption
                            }, {
                                label: labelOption
                            }, {
                                label: labelOption
                            }]
                        });
                    }
                };


                var labelOption = {
                    normal: {
                        show: true,
                        position: app.config.position,
                        distance: app.config.distance,
                        align: app.config.align,
                        verticalAlign: app.config.verticalAlign,
                        rotate: app.config.rotate,
                        formatter: '{c}  {name|{a}}',
                        fontSize: 16,
                        rich: {
                            name: {
                                textBorderColor: '#fff'
                            }
                        }
                    }
                };

                option = {
                    color: ['#003366', '#e5323e'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: ['学生', '考试']
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {show: false},
                            data: data.years
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: '学生',
                            type: 'bar',
                            barGap: 0,
                            label: labelOption,
                            data: data.stuCouByYea
                        },
                        {
                            name: '考试',
                            type: 'bar',
                            label: labelOption,
                            data: data.examCouByYea
                        }
                    ]
                };;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            }
        });

    });

})