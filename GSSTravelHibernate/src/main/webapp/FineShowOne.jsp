<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>罰則一覽表</title>
<style>
td, input {
	border: 1px solid black;
	text-align: center;
}
</style>
</head>
<body>
	<form action="<c:url value="/FineShowOneServlet" />" method="GET">
		<c:if test="${countI+1 ne 0 && countJ+1 ne 0}">
			<table id="resultTable">
				<tr>
					<td>行程 ＼ 罰則</td>
					<c:forEach var="i" varStatus="statusI" begin="0" end="${countI}">
						<c:if test="${statusI.count==1}">
							<td>旅遊前${fSelect[i].fineDates}天通知<br>扣款總費用 * ${fSelect[i].finePer}%</td>
						</c:if>
						<c:if test="${statusI.count>1}">
							<td>旅遊前${fSelect[i].fineDates} ~ ${fSelect[i-1].fineDates-1}天通知<br>扣款總費用 * ${fSelect[i].finePer}%</td>
						</c:if>
					</c:forEach>
					<td>旅遊開始日<br>扣款總費用 * 100%</td>
				</tr>
				<tr>
					<td>${tSelect[0].traName}<br>${totalDays[0][countI+1]}</td>
						<c:forEach var="j" varStatus="statusJ" begin="0" end="${countI}">
							<c:if test="${statusJ.count==1}">
								<td>報名截止日 ~ ${totalDays[0][j]}<br>${iSelect[0].itemMoney} * ${fSelect[j].finePer}% = ${iSelect[0].itemMoney*fSelect[j].finePer/100}</td>
							</c:if>
							<c:if test="${statusJ.count!=1}">
								<td>${afterDay[0][j-1]} ~ ${totalDays[0][j]}<br>${iSelect[0].itemMoney} * ${fSelect[j].finePer}% = ${iSelect[0].itemMoney*fSelect[j].finePer/100}</td>
							</c:if>
						</c:forEach>
					<td>${totalDays[0][countI+1]}<br>${iSelect[0].itemMoney}</td>
				</tr>
			</table>
		</c:if>
		<c:if test="${countI+1 eq 0 && countJ+1 eq 0}">
				<h1>目前尚無行程或罰則資訊！</h1>
		</c:if>
	</form>
</body>
</html>