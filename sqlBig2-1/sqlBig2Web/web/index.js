(function(document) {
    var doc = document;
    var ele = {
        sub1: doc.getElementById("sub1"),
        sub2: doc.getElementById("sub2"),
        addAge: doc.getElementsByName("age")[0],
        teleno: doc.getElementsByName("tel")[0],
        oPopI: doc.querySelector("pop-insert"),
        oPopD: doc.querySelector("pop-delete"),
        oSub1: doc.querySelector("#sub1"),
        oSub2: doc.querySelector("#sub2"),
        oAjaxBtn: doc.querySelector(".ask"),
        oSqlBtn: doc.querySelector("a"),
        aInputs: doc.getElementsByTagName("input")
    };
    for(var i=0,len=ele.aInputs.length;i<len;i++) {
        (function(i) {
            ele.aInputs[i].onfocus = function() {
                ele.aInputs[i].parentNode.borderColor = "#ff0";
            }
        })(i);
    }
    ele.oAjaxBtn.addEventListener("mouseenter",function(){
        ele.oAjaxBtn.backgroundColor = "#ff0";
    })
    ele.oAjaxBtn.addEventListener("mouseleave",function() {
        ele.oAjaxBtn.backgroundColor = "#333";
    })
    ele.oSqlBtn.addEventListener("mouseenter",function() {
        ele.oSqlBtn.backgroundColor = "#ff0";
    })
    ele.oSqlBtn.addEventListener("mouseleave",function() {
        ele.oSqlBtn.backgroundColor = "#333";
    })

    ele.oAjaxBtn.addEventListener("click",function() {
        ajaxGet();
    })
    ele.addAge.onchange = function() {
        var temp = this.value.match(/\s/g);
        if(this.value.length>3){
            alert("请填写合适数据");
        }
    }

    function ajaxGet() {
        var xmlhttp,data;
        if(window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }else {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function() {
            if(xmlhttp.readyState==4&&xmlhttp.status==200) {
                data=JSON.parse(xmlhttp.response);
                console.log(data);
            }
        }
        xmlhttp.open("GET","./AjaxServlet?page=all",true);
        xmlhttp.send();
    }


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