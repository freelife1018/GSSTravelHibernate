<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.t {
	border: 1px solid black;
}
.a{
	text-decoration:none; 
	color:black;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value="Cancel"/>" method="get">
		<h1>-本團報名資訊-</h1>
		<h3>活動名稱:${tra_Vo.traName}</h3>
		<h3>
			活動代碼:<input style="border: none; font-size: 14pt; width: 200px"
				type="text" value="${tra_Vo.traNo}" name="tra_No" readonly>
		</h3>
		<h2>已報名人數 ${tra_count}人依報名順序為:</h2>
		<c:forEach var="names" items="${name}">
			<c:set var="nameKey" value="${names}" />
			<h3>${names}(共${mp[nameKey]}人)</h3>
		</c:forEach>
		<h1>-報名人員-</h1>
		<h2>
			員編:<input style="border: none; font-size: 18pt; width: 40px"
				type="text" value="${emp_No}" name="emp_No" readonly>;姓名:${myName};報名順序:第${tra_order}名
		</h2>

		<c:if test="${familySize>0}">
			<table class="t">
				<tr class="t">
					<th class="t">報名</th>
					<th class="t">眷屬/親友</th>
					<th class="t">姓名</th>
				</tr>
				<c:forEach var="row" items="${familyVO}">
					<tr class="t">
						<td class="t"><input type=checkbox value="${row.famName}"
							onclick="return false" ${row.checked} name="fam"></td>
						<td class="t">${row.famRel}</td>
						<td class="t">${row.famName}</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
		<br><br><br>
		<c:if test="${tra_Vo.traOn!=tra_Vo.traOff}">
			<c:forEach var="room" items="${itemVo}">
				<table class="t">
					<tr class="t">
						<th class="t"></th>
						<th class="t">房型</th>
						<th class="t">費用</th>
					</tr>
					<tr>
						<td class="t"><input type=checkbox value="${room.itemMoney} "
							onclick="return false"  name="room"></td>
						<td class="t" width='150px' >${room.itemName}</td>
						<td class="t" width='70px'>${room.itemMoney}</td>
					</tr>
				</table>
			</c:forEach>
			<br> <br> 
		</c:if>
		<h3>團費試算 :年度可使用補助金額:${detail[0]};個人團費:${detail[1]};個人補助金:${detail[0]};補團費:${detail[2]}</h3>
		<h3 style="color: red">PS:團費試算僅供參考，需繳納費用以福委會通知為主</h3>
		<button><a href="<c:url value="/AllTravel"></c:url>" class="a">回上一頁</a></button><input type="submit" value="取消報名"> 
	</form>

</body>

</html>