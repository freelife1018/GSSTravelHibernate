<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>�ȹC���W</title>
<script type="text/javascript" src="/GSStravel/js/selectBar.js"></script>
<script type="text/javascript" src='js/LogOut.js'></script>
</head>
<style>
* {
	font-size: 13px;
}

tr, input {
	text-align: center;
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
	<div id='bar'></div>
	<input type="button" value='�n�X' onclick='logOut()'>
	<form action="<c:url value='/TotalAmountServlet' />" method="GET">
		<div>
			<textarea name="tra_Name" readonly>${tra_Name}</textarea>
		</div>
		<input type="hidden" name="tra_No" value="${tra_No}">
		<table border="1" id="table">
			<thead>
				<tr>
					<td>�����N�X</td>
					<td>���u(/���ݿˤ�)�s��</td>
					<td>�m�W(/���ݩ������u)</td>
					<td>�~�ץi�ɧU���B<br /> <span style="color: red; font-size: 10px">����¾�@�~�̫���ҵ����p��</span></td>
					<td>�ӤH�i�ɧU���B</td>
					<td>�ӤH�ζO</td>
					<td>��L�W��O�Ω��ӻ���</td>
					<td>��L�W��O���`�B </td>
					<td>���ɹζO</td>
				</tr>
			</thead>
			<c:forEach var="list" items="${list}">
				<tr>
					<td><input type="text" name="dept_No" value="${list.dept_No}" readonly></td>
					<td><c:if test="${list.fam_No==0}">
							<input type="text" name="emp_No" class="emp" value="${list.emp_No}" readonly>
							<input type="hidden" name="empfam" value="${list.emp_No}">
						</c:if><c:if test="${list.fam_No!=0}">
							<input type="text" name="empfam" class="fam" value="${list.emp_No}/${list.fam_No}" readonly>
						</c:if>
					</td>
					<td><c:if test="${list.fam_Name==NULL}"><input type="text" value="${list.emp_Name}" readonly></c:if>
						<c:if test="${list.fam_Name!=NULL}"><input type="text" value="${list.fam_Name}/${list.emp_Name}" readonly></c:if></td>
					<c:if test="${list.fam_Name==NULL}">
						<td><input type="text" class="years_money" value="${list.years_money}" readonly ></td>
						<td>
							<input type="text" class="person_money" value="" readonly >
							<input type="hidden" class="person" value="${list.emp_No}">
						</td>
					</c:if>
					<c:if test="${list.fam_Name!=NULL}">
						<td><input type="text" value="0.0" readonly></td>
						<td>
						<c:if test="${list.fam_sub}">
							<input type="text" class="personfam_money" value="0.0" readonly>
							<input type="hidden" class="person" value="${list.emp_No}" >
						</c:if>
						<c:if test="${!list.fam_sub}">
							<input type="text" class="onmoney" value="0.0" readonly >
						</c:if>
						</td>
					</c:if>
					<td><input type="text" name="money" class="money" value="${list.det_money}" readonly ></td>
					
					<td><input type="text" name="det_note" class="det_note" value="${list.det_note}"></td>
					<td><input type="text" name="det_noteMoney" class="det_noteMoney" value="${list.det_noteMoney}"></td>
					<td><c:if test="${list.fam_No==0}">
							<input type="text" name="TA_money" class="TA_money" value="">
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<div>
			<input type="submit" id="submit" value="�x�s" /> 
			<input type="submit" id="excel" value="�ץXExcel" />
		</div>
	</form>
	<c:if test="${session!=null}">
		<script>alert('${session}');</script>
	</c:if>
</body>
<script src="js/jquery-1.12.3.min.js"></script>
<script>
 	var $money = $(".money");					//�ӤH�ζO
 	var $emp = $(".emp");						//emp
 	var $fam = $(".fam");						//fam
 	var $TAmoney = $(".TA_money");				//���ɹζO
 	var $noteMoney= $(".det_noteMoney");		//��K�O��
  	var $yearsmoney = $(".years_money");
 	var $personemp=$(".person");
 	var $personmoney = $(".person_money");		//�|���ӤH�i�ɧU���B	
	var a=0;
 	var b=0;
 	console.log($fam);
	$.each($personmoney,function(i,value){
		var sum=Number(0);
 			$.each($personemp,function(k,value){
 				if($emp[i].value==$personemp[k].value){
 					sum=sum+Number($money[a].value);
					a++;
				}
			});
			if($yearsmoney[i].value<=sum){
				sum=$yearsmoney[i].value;
			}
		$personmoney[i].value=sum;		
 	});
 	a=0;
 	$.each($emp,function(keys,emp){
		var sum = Number(0);
		sum = Number($money[keys].value)+Number($noteMoney[a].value)-Number($personmoney[keys].value);
	 	a++;	
  	 	$.each($fam,function(key , fam){
	 		if ($fam[key].value.split("/")[0] == $emp[keys].value) {
  	 			sum = sum + Number($money[a].value)+Number($noteMoney[a].value);
 	 			a=a++;
 	 		}
	 	});
  	 	if(sum <= 0){
			sum=0;
		}
 	 	$TAmoney[keys].value = sum;
	});
</script>
</html>