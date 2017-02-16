<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<script>
		var pathNamejs = document.location.pathname;
		var indexjs = pathNamejs.substr(1).indexOf("/");
		var resultjs = pathNamejs.substr(0, indexjs + 1);
	</script>
	<input type='button' value='報名/查詢'
		onclick="window.location.href=resultjs+'/AllTravel';" />
	<input type='button' value='資料輸入'
		onclick="window.location.href=resultjs+'/register';" />
	<c:if test='${sessionScope.emp_Role==true}'>
		<input type='button' value='行程維護'
			onclick="window.location.href=resultjs+'/search.jsp';" />
		<input type='button' value='報名維護'
			onclick="window.location.href=resultjs+'/search.jsp';" />
		<input type='button' value='罰則維護'
			onclick="window.location.href=resultjs+'/FineSetting.jsp';" />
		<input type='button' value='旅費統計'
			onclick="window.location.href=resultjs+'/search1.jsp';" />
	</c:if>
	<br>
	<input type="button" value='登出' onclick="window.location.href=resultjs+'/LogOut.do';">
	<br>
	
	