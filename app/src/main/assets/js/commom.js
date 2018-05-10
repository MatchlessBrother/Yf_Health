var token = androidjs.getToken();
//var name = "";
//var telephone = "";
var apiUrl = "http://60.205.148.14:8000";

function ajax(url, postData, doSuccess, doFail, doComplete){
    var layerIndex;
    $.ajax({
    	url: apiUrl + url,
    	method: 'POST',
    	data: postData,
    	headers: {
        	memberToken:token 
        },
        beforeSend: function() {
            layerIndex = layer.load(1, {
              shade: [0.1,'#fff']
            });
        },
    	success: function(res){
        	if (typeof doSuccess == "function") {
                doSuccess(res);
            }
     	},
     	error:function(){
	        if (typeof doFail == "function") {
               doFail();
			}
	    },
	    complete:function(){
            layer.close(layerIndex);
	        if (typeof doComplete == "function") {
				doComplete();
			}
	    }
 	})
}
function ajaxUpload(url, postData, doSuccess, doFail, doComplete){
    var layerIndex;
    $.ajax({
        url: apiUrl + url,
        method: 'POST',
        data: postData,
        processData: false,
        contentType: false,
        cache: false,
        headers: {
        	memberToken:token 
        },
        beforeSend: function() {
            layerIndex = layer.load(1, {
              shade: [0.1,'#fff']
            });
        },
        success: function(res){
            if (typeof doSuccess == "function") {
                doSuccess(res);
            }
        },
        error:function(){
            if (typeof doFail == "function") {
               doFail();
            }
        },
        complete:function(){
            layer.close(layerIndex);
            if (typeof doComplete == "function") {
                doComplete();
            }
        }
    })
}
function ajaxNoLoading(url, postData, doSuccess, doFail, doComplete){
    $.ajax({
        url: apiUrl + url,
        method: 'POST',
        data: postData,
        headers: {
        	memberToken:token 
        },
        beforeSend: function() {
            
        },
        success: function(res){
            if (typeof doSuccess == "function") {
                doSuccess(res);
            }
        },
        error:function(){
            if (typeof doFail == "function") {
               doFail();
            }
        },
        complete:function(){
            if (typeof doComplete == "function") {
                doComplete();
            }
        }
    })
}
function setItem(key, value){
    if (typeof(Storage) == "undefined") {
        alert("当前运行环境不支持本地存储，请联系客服");
        return;
    }
    sessionStorage.setItem(key, value);
}
function removeItem(key){
    if (typeof(Storage) == "undefined") {
        alert("当前运行环境不支持本地存储，请联系客服");
        return;
    }
    return sessionStorage.removeItem(key);
}
function getItem(key){
    if (typeof(Storage) == "undefined") {
        alert("当前运行环境不支持本地存储，请联系客服");
        return;
    }
    return sessionStorage.getItem(key);
}
Date.prototype.format = function (fmt) {
    var o = {  
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "H+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S": this.getMilliseconds()
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}