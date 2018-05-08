(function(document) {
    var doc = document;
    var ele = {
        aBtns: doc.querySelectorAll("ul.btns li:not(.pre):not(.wait):not(.next):not(.sign):not(.jump-value):not(.jump)"),
        aTrs: doc.querySelectorAll("table tr:not(.head):not(.attr)")
    };
    console.log(ele.aBtns);
    for(var i=0,len=ele.aBtns.length;i<len;i++) {
        (function(i) {
            ele.aBtns[i].onclick = function() {
                clear();
                var sentData = parseInt(ele.aBtns[i].innerHTML,0);
                ajaxGet(sentData);
            }
        })(i);
    }


    //清空表格数据
    function clear() {
        for(var i=0;i<13;i++) {
            for(var j=0;j<4;j++) {
                ele.aTrs[i].getElementsByTagName("td")[j].innerHTML="";
            }
        }
    }
    function ajaxGet(page) {
        var xmlhttp;
        if(window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onload = function() {
            if(xmlhttp.readyState==4&&xmlhttp.status==200) {
                var data=JSON.parse(xmlhttp.response);
                clear();
                for(var key in data){
                    var aTds = ele.aTrs[parseInt(key%13)].querySelectorAll("td");
                    for(var i=0;i<4;i++) {
                        aTds[i].innerHTML = data[key][i];
                    }
                }
            }
        }
        xmlhttp.open("GET","./AjaxServlet?page="+page,true);
        xmlhttp.send();
    }
    //请求数据
    ajaxGet(1);

    function ajaxPost() {
        var xmlhttp,data;
        if(window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.open("post","./Servlet");
        xmlhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
        xmlhttp.send("value=hello");
        window.onreadystatechange = function() {
            if(xmlhttp.readyState==4&&xmlhttp.status==200) {
                data=xmlhttp.responseText;
                console.log(1);
                console.log(data);
            }else {
                console.log("失败");
            }
        }
        return data;
    }
})(document);