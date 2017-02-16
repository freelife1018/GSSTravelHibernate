<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
    window.onload=function(){
    	if('${error.act ne true}' !=null &&'${error.act}' !=''){alert('${error.act}');}
    	else{;}
    }
	function check() {
		if (document.getElementById('account').value == '') {
			alert('帳號未填');
			document.getElementById('account').focus();
		} else if (document.getElementById('pwd').value == '') {
			alert('密碼未填');
			document.getElementById('pwd').focus();
		} else {
			document.getElementById('login.do').submit();
		}
	}
</script>
</head>
<body>
	<h1>LoginTitle</h1>
	<form method="POST" action=<c:url value="/login.do"/>	id="login.do">
		<label>account:</label> <input type="text" name='account' id='account' value=""/><br>
		<label>password:</label> <input type="text" name='pwd'  id='pwd' /><br>
		<input id='sub' type="button" value='submit' onclick='check()' /> <input type='reset' />
	</form>
</body>
</html>