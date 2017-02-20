<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='js/jquery-3.1.1.min.js'></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<title>罰則一覽表</title>
<style>
.a {
	text-decoration: none;
	color: black;
}

table {
	font-size: 15px;
}

td>strong {
	color: #CC6600;
	font-size: 20px;
}

</style>


</head>
<body>
	<div class='container-fluid'>
		<div class='row'>
			<div class='col-md-5'></div>
			<div class='col-md-3'>
				<h1>行程內容</h1>
			</div>
		</div>
		<div class='row'>
			<div class='col-lg-1'></div>
			<div class='col-lg-10'>
				<ul class="nav nav-tabs">
					<li role="presentation"><a
						href="<c:url value="/FeeTravel?tra_No=${traveResult.traNo}"/>"><strong>行程</strong></a></li>
					<li role="presentation" class="active"><a
						href="<c:url value="/FineShowOneServlet?tra_No=${traveResult.traNo}"/>"><strong>罰則</strong></a></li>
				</ul>
			</div>
		</div>
		<div class='row'>
			<div class='col-md-1'></div>
			<div class='col-md-10'>
				<form action="<c:url value="/FineShowOneServlet" />" method="GET">
					<c:if test="${countI+1 ne 0 && countJ+1 ne 0}">
						<table id="resultTable" class='table table-condensed'>
							<tr>
								<td>行程 ＼ 罰則</td>
								<c:forEach var="i" varStatus="statusI" begin="0" end="${countI}">
									<c:if test="${statusI.count==1}">
										<td>旅遊前<strong>${fSelect[i].fineDates}</strong>天通知<br>扣款總費用
											* ${fSelect[i].finePer}%
										</td>
									</c:if>
									<c:if test="${statusI.count>1}">
										<c:if test="${fSelect[i].fineDates==fSelect[i-1].fineDates-1}">
											<td>旅遊前<strong>${fSelect[i].fineDates}</strong>天通知<br>扣款總費用 *
											${fSelect[i].finePer}%
										</td>
										</c:if>
										<c:if test="${fSelect[i].fineDates!=fSelect[i-1].fineDates-1}">
											<td>旅遊前<strong>${fSelect[i].fineDates} ～
												${fSelect[i-1].fineDates-1}</strong>天通知<br>扣款總費用 *
											${fSelect[i].finePer}%
										</td>
										</c:if>
									</c:if>
								</c:forEach>
								<td>旅遊<strong>開始</strong>日<br>扣款總費用 * 100%
								</td>
							</tr>
							<tr>
								<td>${tSelect[0].traName}<br>${totalDays[0][countI+1]}</td>
								<c:forEach var="j" varStatus="statusJ" begin="0" end="${countI}">
									<c:if test="${statusJ.count==1}">
										<td>報名截止日 ～ ${totalDays[0][j]}<br> <fmt:formatNumber
												value="${iSelect[0].itemMoney}" groupingUsed="true"
												type="currency" maxFractionDigits="0" /> *
											${fSelect[j].finePer}% ＝ <br> <strong><fmt:formatNumber
													value="${iSelect[0].itemMoney*fSelect[j].finePer/100}"
													groupingUsed="true" type="currency" maxFractionDigits="0" /></strong></td>
									</c:if>
									<c:if test="${statusJ.count!=1}">
										<c:if test="${afterDay[0][j-1]==totalDays[0][j]}">
											<td>${totalDays[0][j]}<br>
										</c:if>
										<c:if test="${afterDay[0][j-1]!=totalDays[0][j]}">
											<td>${afterDay[0][j-1]} ～ ${totalDays[0][j]}<br>
										</c:if>
										 <fmt:formatNumber
												value="${iSelect[0].itemMoney}" groupingUsed="true"
												type="currency" maxFractionDigits="0" /> *
											${fSelect[j].finePer}% ＝<br> <strong><fmt:formatNumber
													value="${iSelect[0].itemMoney*fSelect[j].finePer/100}"
													groupingUsed="true" type="currency" maxFractionDigits="0" /></strong></td>
									</c:if>
								</c:forEach>
								<td>${totalDays[0][countI+1]}<br> <!-- money*100% -->
									<br> <strong><fmt:formatNumber
											value="${iSelect[0].itemMoney}" groupingUsed="true"
											type="currency" maxFractionDigits="0" /></strong></td>
							</tr>
						</table>
					</c:if>
					<c:if test="${countI+1 eq 0 || countJ+1 eq 0}">
						<h1>目前尚無行程或罰則資訊！</h1>
					</c:if>
				</form>
				<script>
					var GSS = '<c:url value="/AllTravel" />';				
				</script>
				<br> <input type="button" value='回到報名/查詢'
					class='btn  btn-primary' onclick="window.location.href=GSS;" /> 
			</div>
			<div class='col-md-1'></div>
		</div>
	</div>
</body>
</html>