<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>旅遊報名</title>
</head>
<style>
* {
	font-size: 13px;
}

tr, input {
	text-align: center;
	width: 100px;
}

textarea {
	text-align: center;
	border: 0;
	resize: none;
	font-size: 20px;
	width: 400px;
	overflow-y: visible;
	outline: none;
}

td {
	padding: 0px;
	text-align: center;
}
</style>

<body>
	<%@include file="SelectBar.jsp"%>
	<script>
			$('li').removeClass('now');
			$('li:eq(5)').addClass('now');
		</script>
	<div class='container-fluid'>
		<form action="<c:url value='/TotalAmountServlet' />" method="GET">
			<div>
				<textarea name="tra_Name" class="tra_Name" styple="fontSsize:20px" readonly>${traName}</textarea>
			</div>
			<input type="hidden" name="tra_No" class="tra_No" value="${traNo}">

			<table border="1" class="table table-bordereds" id="table">
				<thead>
					<tr>
						<td scope="row">部門代碼</td>
						<td scope="row">員工(/眷屬親友)編號</td>
						<td scope="row">姓名(/隸屬於哪位員工)</td>
						<td scope="row">年度可補助金額<br /> <span
							style="color: red; font-size: 10px">未到職一年者按比例給予計算</span></td>
						<td scope="row">個人可補助金額</td>
						<td scope="row">個人團費</td>
						<td scope="row">其他增減費用明細說明</td>
						<td scope="row">其他增減費用總額</td>
						<td scope="row">應補團費</td>
					</tr>
				</thead>
				<c:forEach var="list" items="${list}">
					<tr>
						<td><input type="text" name="dept_No" class="dept_No"
							value="${list.deptNo}" readonly></td>
						<td><c:if test="${list.famNo==0}">
								<input type="text" name="emp_No" class="emp No"
									value="${list.empNo}" readonly>
								<input type="hidden" name="empfam" value="${list.empNo}">
							</c:if> <c:if test="${list.famNo!=0}">
								<input type="text" name="empfam" class="fam No"
									value="${list.empNo}/${list.famNo}" readonly>
							</c:if></td>
						<td><c:if test="${list.famName==''}">
								<input type="text" class="Name" value="${list.empName}"
									readonly>
							</c:if> <c:if test="${list.famName!=''}">
								<input type="text" class="Name"
									value="${list.famName}/${list.empName}" readonly>
							</c:if></td>
						<c:if test="${list.famName==''}">
							<td><input type="text" class="years_money years_Money"
								value="${list.yearMoney}" readonly></td>
							<td><input type="text" class="person_money person_Money"
								value="" readonly> <input type="hidden" class="person"
								value="${list.empNo}"></td>
						</c:if>
						<c:if test="${list.famName!=''}">
							<td><input type="text" class="years_Money" value="0.0"
								readonly></td>
							<td><c:if test="${list.famSub}">
									<input type="text" class="personfam_money person_Money"
										value="0.0" readonly>
									<input type="hidden" class="person" value="${list.empNo}">
								</c:if> <c:if test="${!list.famSub}">
									<input type="text" class="onmoney person_Money" value="0.0"
										readonly>
								</c:if></td>
						</c:if>
						<td><input type="text" name="money" class="money Money"
							value="${list.detMoney}" readonly></td>
						<td><input type="text" name="det_note"
							class="det_note det_Note" value="${list.detNote}"></td>
						<td><input type="text" name="det_noteMoney"
							class="det_noteMoney det_NoteMoney" value="${list.detNoteMoney}"
							onkeyup="changeNotemoney()"></td>
						<td><c:if test="${list.famNo==0}">
								<input type="text" name="TA_money" class="TA_money ta_Money"
									value="">
							</c:if> <c:if test="${list.famNo!=0}">
								<input type="hidden" class="ta_Money" value="0">
							</c:if></td>
					</tr>
				</c:forEach>
			</table>
			<br />
			<div>
				<button type="submit" name="prodaction" value="save">儲存</button>

				<input type="button" onclick="excel()" value="匯出Excel">
			</div>
		</form>
		<c:if test="${session!=null}">
			<script>
				alert('${sessionScope.Msg}');
			</script> 
		</c:if> 
	</div>
</body>

<script>


	var $money = $(".money"); //個人團費
	var $emp = $(".emp"); //emp
	var $fam = $(".fam"); //fam
	var $TAmoney = $(".TA_money"); //應補團費
	var $noteMoney = $(".det_noteMoney"); //減免費用
	var $yearsmoney = $(".years_money");
	var $personemp = $(".person");
	var $personmoney = $(".person_money"); //會員個人可補助金額	
	var a = 0;
	var b = 0;
	$().ready(function() {
		$.each($personmoney, function(i, value) {
			var sum = Number(0);
			$.each($personemp, function(k, value) {
				if ($emp[i].value == $personemp[k].value) {
					sum = sum + Number($money[a].value);
					a++;
				}
			});
			if ($yearsmoney[i].value <= sum) {
				sum = $yearsmoney[i].value;
			}
			$personmoney[i].value = sum;
		});
		changeNotemoney();
	});
	function changeNotemoney() {
		a = 0;
		$.each($emp, function(keys, emp) {
			var sum = Number(0);
			sum = Number($money[keys].value) + Number($noteMoney[a].value) - Number($personmoney[keys].value);
			a++;
			$.each($fam, function(key, fam) {
				if ($fam[key].value.split("/")[0] == $emp[keys].value) {
					sum = sum + Number($money[a].value) + Number($noteMoney[a].value);
					a++;
				}
			});
			if (sum <= 0) {
				sum = 0;
			}
			$TAmoney[keys].value = sum;
		});
	}
	function excel(){
		var $tra_No=$(".tra_No");
		var $tra_Name=$(".tra_Name");
		var $dept_No=$(".dept_No");
		var $No=$(".No");
		var $Name=$(".Name");
		var $years_Money=$(".years_Money");
		var $person_Money=$(".person_Money");
		var $Money=$(".Money");
		var $det_Note=$(".det_Note");
		var $det_NoteMoney=$(".det_NoteMoney");
		var $ta_Money=$(".ta_Money");
			
		var tra_No=$tra_No.val();
		var tra_Name=$tra_Name.val();
		
		var dept_No= new Array();
		var No= new Array();
		var Name= new Array();
		var years_Money= new Array();
		var person_Money= new Array();
		var Money= new Array();
		var det_Note= new Array();
		var det_NoteMoney= new Array();
		var ta_Money= new Array();
		
		for(var i=0;i<$dept_No.length;i++){
			dept_No[i]=$dept_No[i].value;
			No[i]=$No[i].value;
			Name[i]=$Name[i].value;
			years_Money[i]=$years_Money[i].value;
			person_Money[i]=$person_Money[i].value;
			Money[i]=$Money[i].value;
			if(!$det_Note[i]){
				det_Note[i]="";
			}else{
				det_Note[i]=$det_Note[i].value;
			}
			if(!$det_NoteMoney[i]){
				det_NoteMoney[i]=0;
			}else{
				det_NoteMoney[i]=$det_NoteMoney[i].value;
			}
			ta_Money[i]=$ta_Money[i].value;
		}
		$.ajax({
			type:"POST",
			url:"/GSStravel/TotalAmountServlet",
			data:{
				tra_No:tra_No,
				tra_Name:tra_Name,
				prodaction:"Excel",
				dept_No:dept_No,
				No:No,
				Name:Name,
				years_Money:years_Money,
				person_Money:person_Money,
				Money:Money,
				det_Note:det_Note,
				det_NoteMoney:det_NoteMoney,
				ta_Money:ta_Money
			},  
			  
		})
	}
</script>
</html>