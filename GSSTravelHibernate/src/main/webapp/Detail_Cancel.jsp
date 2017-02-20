<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>報名取消</title>
<script src='js/jquery-3.1.1.min.js'></script>
<style>
fieldset {
	width: 250px;
	border-radius: 20px;
	margin: auto;
}
</style>

</head>
<body onunload=unLoad();>
		<br />
		<form action=<c:url value="/detail"/> method="get"
			enctype="multipart/form-data">
			<fieldset>
				請輸入取消原因： <br />
				<textarea style="width: 250px; height: 100px; resize: none"
					name="det_CanNote"></textarea>
				<br /> <input type="text" id="can_detNo" name="can_detNo" style="display: none"> <input type="text" id="can_traNo"
					name="can_traNo" style="display: none"> <br /> <input
					name="prodaction" type="submit" value="送出">
				<button name="canClose" type="button" onclick="window.close()">關閉</button>

				<p style="color: red" hidden><%=session.getAttribute("DetCanError")%></p>
			</fieldset>
		</form>
		<script type="text/javascript">

$(function () {
	var temp = location.search;
	var index = temp.indexOf("can_detNo=");
	var index2 = "can_detNo".length+1;
	var det_No = temp.substring(index+index2);
	var index3 = det_No.indexOf("&can_traNo");
	
	var index4 = temp.indexOf("can_traNo=");
	var index5 = "can_traNo".length+1;
	var tra_No = temp.substring(index4+index5);
	var index6 = tra_No.indexOf("&prodaction");
	
	if(index3>=0){
		$("#can_detNo").attr("value",det_No.slice(0,index3));
	}else{
		$("#can_detNo").attr("value",det_No);
	}
	
	if(index6>=0){
		$("#can_traNo").attr("value",tra_No.slice(0,index6));
	}else{
		$("#can_traNo").attr("value",tra_No);
	}
	
});

var DetCanError="<%=session.getAttribute("DetCanError")%>";
<%session.removeAttribute("DetCanError");%>
if(DetCanError!="null"){
	$("p").show();
}

function unLoad(){
    window.opener.location.href = window.opener.location.href;
}
</script>
</body>
</html>