<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
	
	
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.common-material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.material.mobile.min.css" />
	<script src="js/jquery-3.1.1.min.js"></script>
    <script src="https://kendo.cdn.telerik.com/2017.1.118/js/kendo.all.min.js"></script>

<!-- <div id="bar"></div>記得改 -->

<%@include file="SelectBar.jsp" %>
<form  action=<c:url value="/FamilyServlet"/>  method="post">

<table>
<span>員工編號</span>${empno}<br>
<span>姓名</span>${empname}<br>
<tr><span>手機</span><input type="text" name ="empphone" id="empphone"  value="${empphone}">  <div id=empphoneerror>${error.empphone}</div><br>
<span>保險受益人</span><input type="text" name ="empben" id="empben" value ="${empben}"><div id=empbenerror>${error.empben}</div><br>
<span>與受益人關係</span><input type="text" name ="empbenrel" id="empbenrel" value = "${empbenrel}"><div id=empbenrelerror>${error.empbenrel}</div><br>
<span>緊急聯絡人</span><input type="text" name ="empemg" id="empemg" value = "${empemg}" ><div id=empemgerror>${error.empemg}</div><br>
<tr><span>緊急聯絡人電話</span><input type="text" name ="empemgphone" id="empemgphone" value="${empemgphone}"><div id=empemgphoneerror>${error.empemgphone}</div><br>

<span>用餐</span>
<select name ="empeat">
<c:if test="${empeat=='葷'}">
<option  value="葷" selected>葷</option>
<option  value="素">素</option>
</c:if>

<c:if test="${empeat=='素'}">
<option  value="葷" >葷</option>
<option  value="素" selected>素</option>
</c:if></select><br>
<span>(如有特別要求請填寫於備註)</span><br>
<span>備註</span><input type="text" name ="empnote" id="empnote" value="${empnote}" ><div id=empnoteerror></div><br>
</table>

<div>
－眷屬親友資訊－<br>
*眷屬包含直系及旁系二等親，納入補助計算<br>
*親友為旁系三等親以上及朋友，不納入補助計算<br>
</div>


<table id="familytable">
<tr >
	<th></th>
	<th>*眷屬/親友</th>
	<th>*姓名</th>
	<th>*性別</th>
	<th>*身份證字號</th>
	<th>*生日</th>
	<th>*手機</th>
	<th>用餐/車位</th>
	<th>特殊身份</th>
	<th>*保險受益人</th>
	<th>*保險受益人關係</th>
	<th>*緊急聯絡人</th>
	<th>*緊急聯絡人電話</th>
	<th>*緊急聯絡人關係</th>
	<th>備註</th>
</tr>
			<!-- option裡面有空白??????? --><!--沒有抓到錯誤訊息-->		
	<c:if test="${famstartsize>0}">
	<c:forEach var="start" items="${famstart}" >
	  <tr>
		<td><input type="button" name ="delete" id="delete" value="刪除"></td>
		<td>
			<select name ="famrel" >	
				<c:if test="${start.fam_Rel=='親友'}">				
					<option value="眷屬" >眷屬</option>
					<option value="親友" selected>親友<option>
				</c:if>
				<c:if test="${start.fam_Rel=='眷屬'}">				
					<option value="眷屬" selected>眷屬</option>
					<option value="親友" >親友<option>
				</c:if>
			</select>
			</td>
		<td><input type="text" name ="famname" id="famname" value="${start.fam_Name}" ><div id="famnameerror" name="famnameerror">${error.famneme}</div></td>
		<td><select name ="famsex">  <!--  servlet抓name db抓值會抓進value值進去-->
		<c:if test="${start.fam_Sex=='男'}">
			<option value="女" >女</option>
			<option value="男" selected>男<option>
		</c:if>
		<c:if test="${start.fam_Sex=='女'}">
			<option value="女" selected>女</option>
			<option value="男" >男<option>
		</c:if>
		</select></td>
		
		<td><input type="text" name ="famid" id="famid" value="${start.fam_Id}"><div id="famiderror">${error.famid}</div></td><!-- getfamid()會抓到value值 -->
		<td><input type="date" id="fambdate" name="fambdate" value="${start.fam_Bdate}" /><div id="fambdateerror">${error.fambdate}${error.fambdatedate}</div></td>
		<td><input type="text" name ="famphone" id="famphone"  value="${start.fam_Phone}"><div id=famphoneerror>${error.famphone}</div></td>
		<td><select name ="fameat" >  <!-- 今天的日期 減去 他的生日 < 三歲  (剩幾天?) (看年底還是年初)  看年?  -->
			<c:if test="${start.fam_Eat=='葷'}">
				<option value="葷" selected>葷食</option>
				<option value="素">素食</option>
			</c:if>
			<c:if test="${start.fam_Eat=='素'}">
				<option value="葷" >葷</option>
				<option value="素" selected>素</option>
			</c:if>
			</select>


 			<select  name ="famcar" id='famcar'  style="width: 100px;">
	 			<c:if test="${start.fam_Car=='true'}">
					<option value="1" selected>有占車位</option>
					<option value="0" >不占車位</option>
				</c:if>
				<c:if test="${start.fam_Car=='false'}">
					<option value="1" >有占車位</option>
					<option value="0" selected>不占車位</option>
				</c:if>
 			</select>
			</td>
		
		<td><select class="multiselect" name ="famspa"  multiple="multiple" data-placeholder="請選擇" style="width: 200px;">
			 <c:if test="${start.fam_Bady=='false'} ${start.fam_kid=='false'} ${start.fam_Dis=='false'} ${start.fam_Mom=='false'}" >
			 <option value="no" Selected>請選擇</option>
			 </c:if>
			 
		     <c:if test="${start.fam_Bady=='false'}">
		     <option value="baby">幼童(0~3歲)</option>
			 </c:if>
			 <c:if test="${start.fam_Bady}">
			 <option value="baby" Selected>幼童(0~3歲)</option>
			 </c:if>
			 
			 <c:if test="${start.fam_kid=='false'}">
		     <option value="kid">兒童(4~11歲)</option>
			 </c:if>
			 <c:if test="${start.fam_kid}">
			 <option value="kid" Selected>兒童(4~11歲)</option>
			 </c:if>
		      <c:if test="${start.fam_Dis=='false'}">
		     <option value="dis">持身心障礙手冊</option>
		     </c:if>
		      <c:if test="${start.fam_Dis}">
		     <option value="dis" Selected>持身心障礙手冊</option>
		     </c:if>
		     <c:if test="${start.fam_Mom=='false'}">
		     <option value="mom">孕婦(媽媽手冊)</option>
		      </c:if>
		      <c:if test="${start.fam_Mom}">
		     <option value="mom" Selected>孕婦(媽媽手冊)</option>
		      </c:if>
		     </select>
		</td>
		
		<td><input type="text" name ="famben" id="famben" value="${start.fam_Ben}"><div id="fambenerror">${error.famben}</div></td>
		<td><input type="text" name ="fambenrel" id="fambenrel" value="${start.fam_BenRel}" ><div id="fambenrelerror">${error.fambenrel}</div></td>
		<td><input type="text" name ="famemg" id="famemg" value="${start.fam_Emg}"><div id="famemgerror">${error.famemg}</div></td>
		<td><input type="text" name ="famemgphpone" id="famemgphone" value="${start.fam_EmgPhone}"><div id="famemgphoneerror">${error.famemgphone}</div></td>
		<td><input type="text" name ="famemgrel" id="famemgrel" value="${start.fam_EmgRel}"><div id="famemgrelerror">${error.famemgrel}</div ></td>
		<td><input type="text" name ="famnote" id="famnote" value="${start.fam_Note}"><div id="famnoteerror"></div></td>
	</tr> 
	
	
	</c:forEach>
	</c:if>

</table>

<!--新增、儲存 -->
<input type="button" value="insert" id="insert" name ="button"><br>
<input type="submit" value ="save" id="save" name="button"><br>


</form>

	<table>
	<!-- 空白欄位 -->
	<tr name="repeat">
		<td><input type="button" name ="delete" id="delete" value="刪除"></td>
		<td>
			<select name ="famrel" >		
					<option value="眷屬" >眷屬</option>
					<option value="親友" >親友<option>
			</select>
			</td>
		<td><input type="text" name ="famname" id="famname"  ><div id="famnameerror">${error.famneme}</div></td>
		<td><select name ="famsex">  <!--  servlet抓name db抓值會抓進value值進去-->
			<option value="女" >女</option>
			<option value="男" >男<option>
		</select></td>
		<td><input type="text" name ="famid" id="famid" ><div id="famiderror">${error.famid}</div></td>
		<td><input type="date" id="fambdate" name="fambdate"  /><div id="fambdateerror">${error.fambdate}${error.fambdatedate}</div></td>
<%-- 		<td><input type="text" id="fambdate" name="fambdate" ><div id="fambdateerror">${error.fambdate}${error.fambdatedate}</div></td>  --%>
		<td><input type="text" name ="famphone" id="famphone"  >  <div id=famphoneerror>${error.famphone}</div></td> 
		<td><select name ="fameat">
				<option value="葷" >葷</option>
				<option value="素" >素</option>
			</select>
			<select  name ="famcar" id='famcar'  style="width: 100px;">
					<option value="1" >有占車位</option>
					<option value="0" selected>不占車位</option>
 			</select>
 		</td>
		<td>
			<select  name ="famspa"  id="multiselect"  multiple="multiple" data-placeholder="請選擇" style="width: 200px;">
		     <option>幼童(0~3歲)</option>
		     <option>兒童(4~11歲)</option>
		     <option>持身心障礙手冊</option>
		     <option>孕婦(媽媽手冊)</option>
		     </select>
		</td>
<!-- 		class="multiselect"   id="multiselect"-->
		<td><input type="text" name ="famben" id="famben" ><div id="fambenerror">${error.famben}</div></td>
		<td><input type="text" name ="fambenrel" id="fambenrel"><div id="fambenrelerror">${error.fambenrel}</div></td>
		<td><input type="text" name ="famemg" id="famemg"><div id="famemgerror">${error.famemg}</div ></td>
		<td><input type="text" name ="famemgphpone" id="famemgphone"><div id="famemgphoneerror">${error.famemgphone}</div></td>
		<td><input type="text" name ="famemgrel" id="famemgrel"><div id="famemgrelerror">${error.famemgrel}</div ></td>
		<td><input type="text" name ="famnote" id="famnote"><div id="famnoteerror"></div></td>
	</tr>
	</table>


<script>
$(function(){
	$(".multiselect").kendoMultiSelect({autoClose: false});
	$("tr[name='repeat']").hide();
	$("#familytable").attr("width","1200px").attr("border","3px").attr("border-collapse","collapse");
	$("#insert").click(
		function(){
			$("#familytable").append('<tr class=repeat >'+ $("tr[name='repeat']").html()+'</tr>');
			$(".repeat:last #multiselect").kendoMultiSelect({autoClose: false});
// 			$("select:last").kendoMultiSelect({autoClose: false});
// 			$("#multiselect").removeAttr("id");
// 			$("select:last").removeAttr("id");
			}
	);	
	$("#familytable").on("click","input[name*='delete']",function(){
		$("input[name*='delete']").parents("tr:last").remove();
		
	});
	
	function search() {
		if (xh != null) {
	
		var selectedValues = $('select[name="loca"]').val() ;
		if (selectedValues!= undefined) {
			url = url + "loc=" + JSON.stringify(selectedValues);
		}
		
		xh.addEventListener("readystatechange", ajaxReturn)
		xh.open("GET", url);
		xh.send();
		}else {
			alert("Your browser doesn't support JSON!");
		}
	}
	function ajaxReturn() {
		if (xh.readyState == 4){
			if (xh.status == 200) {
					
			}
		}
	}
	
	var empphone=/^09\d{2}-?\d{3}-?\d{3}$/;
	$("#empphone").blur(function(){
		if(empphone.test($(this).val())){
			$(this).css("border-color","green")
			$("#empphoneerror").text("");
		}else{
			$("#empphoneerror").text("不符合手機規則");
			$(this).css("border-color","red");
		}
	});
	
	var empben=/^[^\s].*[^\s]/;
	$("#empben").blur(function(){
		if(empben.test($(this).val())){
			$(this).css("border-color","green")
			$("#empbenerror").text("");
		}else{
			$("#empbenerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var empbenrel=/^[^\s].*\s*[^\s]/;
	$("#empbenrel").blur(function(){
		if(empbenrel.test($(this).val())){
			$(this).css("border-color","green")
			$("#empbenrelerror").text("");
		}else{
			$("#empbenrelerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var empemg=/^[^\s].*\s*[^\s]/;
	$("#empemg").blur(function(){
		if(empemg.test($(this).val())){
			$(this).css("border-color","green")
			$("#empemgerror").text("");
		}else{
			$("#empemgerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var empemgphone=/^09\d{2}-?\d{3}-?\d{3}$/;
	$("#empemgphone").blur(function(){
		if(empemgphone.test($(this).val())){
			$(this).css("border-color","green")
			$("#empemgphoneerror").text("");
		}else{
			$("#empemgphoneerror").text("不符合手機規則");
			$(this).css("border-color","red");
		}
	});
	
// 	var empnote=/^[\u4e00-\u9fa5]/;
// 	$("#empnote").blur(function(){
// 		if(empnote.test($(this).val())){
// 			$(this).css("border-color","green")
// 			$("#empnoteerror").text("");
// 		}else{
// 			$("#empnoteerror").text("需要為中文");
// 			$(this).css("border-color","red");
// 		}
// 	});
	
	var famname=/^[^\s].*\s*[^\s]/;
	var x= function (){
		if(famname.test($(this).val())){
			$(this).css("border-color","green")
			$("#famnameerror").text("");
		}else{
// 			$("#famnameerror").on("text",function(){$(this).text("需要為中文")});
			$(this).css("border-color","red");
		}
	};
	$("input[name*='famname']").on("blur",x);
// 	$("tr[name='repeat']>input[name*='famname]").on("blur",x);
	
	
	
	$("input[name*='famid']").on("blur",
			function checkID(idStr){
		  // 依照字母的編號排列，存入陣列備用。
		  var letters = new Array('A', 'B', 'C', 'D', 
		      'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 
		      'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 
		      'X', 'Y', 'W', 'Z', 'I', 'O');
		  // 儲存各個乘數
		  var multiply = new Array(1, 9, 8, 7, 6, 5, 
		                           4, 3, 2, 1);
		  var nums = new Array(2);
		  var firstChar;
		  var firstNum;
		  var lastNum;
		  var total = 0;
		  // 撰寫「正規表達式」。第一個字為英文字母，
		  // 第二個字為1或2，後面跟著8個數字，不分大小寫。
		  var regExpID=/^[a-z](1|2)\d{8}$/i; 
		  // 使用「正規表達式」檢驗格式
		  if (idStr.test(regExpID)==-1) {
		    // 基本格式錯誤
			alert("請仔細填寫身份證號碼");
		   return false;
		  } else {
			// 取出第一個字元和最後一個數字。
			firstChar = idStr.charAt(0).toUpperCase();
			lastNum = idStr.charAt(9);
		  }
		  // 找出第一個字母對應的數字，並轉換成兩位數數字。
		  for (var i=0; i<26; i++) {
			if (firstChar == letters[i]) {
			  firstNum = i + 10;
			  nums[0] = Math.floor(firstNum / 10);
			  nums[1] = firstNum - (nums[0] * 10);
			  break;
			} 
		  }
		  // 執行加總計算
		  for(var i=0; i<multiply.length; i++){
		    if (i<2) {
		      total += nums[i] * multiply[i];
		    } else {
		      total += parseInt(idStr.charAt(i-1)) * 
		               multiply[i];
		    }
		  }
		  // 和最後一個數字比對
		  if ((10 - (total % 10))!= lastNum) {
			alert("身份證號碼寫錯了！");
			return false;
		  } 
		  return true;
		}
	    );
	
	


// 		if(famid.test($(this).val())){
// 			$(this).css("border-color","green")
// // 			$("#famiderror").text("");
// 		}else{
// // 			$("#famiderror").text("需要為台灣身份證規格");
// 			$(this).css("border-color","red");
// 		}

	
	var fambdate=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/;
	$("input[name*='fambdate']").on("blur",function(){
		if(fambdate.test($(this).val())){
			$(this).css("border-color","green")
			$("#fambdateerror").text("");
		}else{
// 			$("#fambdateerror").text("需要為年-月-日的規格");
			$(this).css("border-color","red");
		}
	});
	
	var famphone=/^09\d{2}-?\d{3}-?\d{3}$/;
	$("input[name*='famphone']").on("blur",function(){
		if(famphone.test($(this).val())){
			$(this).css("border-color","green")
			$("#fambdateerror").text("");
		}else{
// 			$("#fambdateerror").text("需要為年-月-日的規格");
			$(this).css("border-color","red");
		}
	});
	
	var famben=/^[^\s].*\s*[^\s]/;
	$("input[name*='famben']").on("blur",function(){
		if(famben.test($(this).val())){
			$(this).css("border-color","green")
			$("#fambenerror").text("");
		}else{
// 			$("#fambenerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var fambenrel=/^[^\s].*\s*[^\s]/;
	$("input[name*='fambenrel']").on("blur",function(){
		if(fambenrel.test($(this).val())){
			$(this).css("border-color","green")
			$("#fambenrelerror").text("");
		}else{
// 			$("#fambenrelerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var famemg=/^[^\s].*\s*[^\s]/;
	$("input[name*='famemg']").on("blur",function(){
		if(famemg.test($(this).val())){
			$(this).css("border-color","green")
			$("#famemgerror").text("");
		}else{
// 			$("#famemgerror").text("需要為中文");
			$(this).css("border-color","red");
		}
	});
	
	var famemgphpone=/^09\d{2}-?\d{3}-?\d{3}$/;
	$("input[name*='famemgphpone']").on("blur",function(){
		if(famemgphpone.test($(this).val())){
			$(this).css("border-color","green")
			$("#famemgphoneerror").text("");
		}else{
// 			$("#famemgphoneerror").text("不符合手機規則");
			$(this).css("border-color","red");
		}
	});
	
	var famemgrel=/^[^\s].*\s*[^\s]/;
	$("input[name*='famemgrel']").on("blur",function(){
		if(famemgrel.test($(this).val())){
			$(this).css("border-color","green")
			$("#famemgrel").text("");
		}else{
// 			$("#famemgrel").text("不符合手機規則");
			$(this).css("border-color","red");
		}
	});
	
// 	var famnote=/.*\s/;
// 	$("input[name*='famnote']").on("blur",function(){
// 		if(famnote.test($(this).val())){
// 			$(this).css("border-color","green")
// 			$("#famnoteerror").text("");
// 		}else{
// // 			$("#famnoteerror").text("需要為中文");
// 			$(this).css("border-color","red");
// 		}
// 	});
	
});

	
</script>


</body>
</html>