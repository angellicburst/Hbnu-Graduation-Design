/**
* 封装ajax函数
* 有5个参数，最后一个参数可选
*
* @param method(必选)    请求类型  get 和 post
* @param url(必选)       请求的url地址   相同域名下的页面（此函数不支持跨域请求）
* @param data(必选)      请求协带的数据  以js对象的形式定义，如：{name:'张三'}
* @param callback(必选)  回调函数,可接收一个参数，这个参数就是服务器响应的数据
* @param type(可选)      指定服务器响应的数据类型（可选值：json,xml,text），如果是json模式，则使用json解析数据，默认为text普通字符串
*/
function myAjax(method,url,data,callback,type) {
    //创建兼容 XMLHttpRequest 对象
    var xhr;
    if (window.XMLHttpRequest) {//IE7+, Firefox, Chrome, Opera, Safari
        xhr = new XMLHttpRequest();
    } else {// code for IE6, IE5
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }

    //给请求添加状态变化事件处理函数
    xhr.onreadystatechange = function () {
        //判断状态码
        if (xhr.status == 200 && xhr.readyState == 4) {
            //根据type参数，判断返回的内容需要进行怎样的处理
            if (type == 'json') {
                //获得 json 形式的响应数据，并使用parse方法解析
                var res = JSON.parse(xhr.responseText);
            } else if (type == 'xml') {
                //获得 XML 形式的响应数据
                var res = responseXML;
            } else {
                //获得字符串形式的响应数据
                var res = xhr.responseText;
            }
            //调用回调函数,并将响应数据传入回调函数
            callback(res);
        }
    };

    //判断data是否有数据
    var param = '';
    //这里使用stringify方法将js对象格式化为json字符串
    if (JSON.stringify(data) != '{}') {
        url += '?';
        for (var i in data) {
            param += i + '=' + data[i] + '&';   //将js对象重组，拼接成url参数存入param变量中
        }
        //使用slice函数提取一部分字符串，这里主要是为了去除拼接的最后一个&字符
        //slice函数：返回一个新的字符串。包括字符串从 start 开始（包括 start）到 end 结束（不包括 end）为止的所有字符。
        param = param.slice(0, param.length - 1);
    }

    //判断method是否为get
    if (method == "get") {
        //是则将数据拼接在url后面
        url = url + param;
    }


    //初始化请求
    xhr.open(method, url, true);

    //如果method == post
    if (method == "post") {
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        //发送请求
        xhr.send(param);
    } else {
        //发送请求
        xhr.send(null);
    }
}

/*
  *参数说明：
  *opts: {'可选参数'}
  **method: 请求方式:GET/POST,默认值:'POST';
  **url:    发送请求的地址, 默认值: 当前页地址;
  **data: string,json;
  **async: 是否异步:true/false,默认值:true;
  **cache: 是否缓存：true/false,默认值:true;
  **contentType: HTTP头信息，默认值：'application/x-www-form-urlencoded;charset=utf-8';
  **success: 请求成功后的回调函数;
  **error: 请求失败后的回调函数;
*/
function ajax(opts){
    //一.设置默认参数
    var defaults = {
        method: 'POST',
        url: '',
        data: '',
        dataType: 'json',
        async: true,
        cache: true,
        contentType:'application/x-www-form-urlencoded;charset=utf-8',
        success: function (){},
        error: function (){}
    };

    //二.用户参数覆盖默认参数
    for(var key in opts){
        defaults[key] = opts[key];
    }

    //三.对数据进行处理
    // if(typeof defaults.data === 'object'){    //处理 data
    //     var str = '';
    //     var value = '';
    //     for(var key in defaults.data){
    //         value = defaults.data[key];
    //         console.log(key);
    //         console.log(defaults.data);
    //         console.log(defaults.data[key]);
    //         if( defaults.data[key].indexOf('&') !== -1 ) value = defaults.data[key].replace(/&/g, escape('&'));   //对参数中有&进行兼容处理
    //         if( key.indexOf('&') !== -1 )  key = key.replace(/&/g, escape('&'));   //对参数中有&进行兼容处理
    //         str += key + '=' + value + '&';
    //     }
    //     defaults.data = str.substring(0, str.length - 1);
    // }
    var param = '';
    if (JSON.stringify(defaults.data) != '{}') {
        for (var i in defaults.data) {
            param += i + '=' + defaults.data[i] + '&';   //将js对象重组，拼接成url参数存入param变量中
        }
        //使用slice函数提取一部分字符串，这里主要是为了去除拼接的最后一个&字符
        //slice函数：返回一个新的字符串。包括字符串从 start 开始（包括 start）到 end 结束（不包括 end）为止的所有字符。
        defaults.data = param.slice(0, param.length - 1);
    }

    defaults.method = defaults.method.toUpperCase();    //处理 method

    defaults.cache = defaults.cache ? '' : '&' + new Date().getTime() ;//处理 cache

    if(defaults.method === 'GET' && (defaults.data || defaults.cache))    defaults.url += '?' + defaults.data + defaults.cache;    //处理 url

    //四.开始编写ajax
    //1.创建ajax对象
    var oXhr = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject('Microsoft.XMLHTTP');
    //2.和服务器建立联系，告诉服务器你要取什么文件
    oXhr.open(defaults.method, defaults.url, defaults.async);
    //3.发送请求
    if(defaults.method === 'GET')
        oXhr.send(null);
    else{
        oXhr.setRequestHeader("Content-type", defaults.contentType);
        oXhr.send(defaults.data);
    }
    //4.等待服务器回应
    oXhr.onreadystatechange = function (){
        if(oXhr.readyState === 4){
            if(oXhr.status === 200)
                defaults.success.call(oXhr, oXhr.responseText);
            else{
                defaults.error();
            }
        }
    };
}