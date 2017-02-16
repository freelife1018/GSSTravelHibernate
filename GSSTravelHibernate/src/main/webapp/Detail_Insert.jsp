<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>報名明細新增</title>
</head>
<body>
<h2>－報名明細新增－</h2>
<form action=<c:url value="/detail_insert"/> method="post">
<p>報名活動代碼：${tra_no}</p>
<input type="hidden" name="tra_no" value="${tra_no}">

<table border="1">
		<tr>
			<th>員工編號</th>
			<th>報名人姓名</th>
			<th>報名總金額</th>
		</tr>
		<tr>
			<td><input type="text" name="emp_No"></td>
			<td><input type="text" name="name"></td>
			<td><input type="text" name="det_money"></td>
		</tr>
</table>
<p style="color:red ">${DetInError.InError}</p>
<br />
<input type="submit" name="prodaction" value="儲存">
<input type="submit" name="prodaction" value="回前頁">
</form>
</body>
</html>