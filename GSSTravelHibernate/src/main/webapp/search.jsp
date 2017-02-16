<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script>
	window.onload = function() {
		var id = document.getElementById('id');
		var tra_name = document.getElementById('tra_name');
		var Sdate = document.getElementById('Sdate');
		var Edate = document.getElementById('Edate');
		$(".multiselect").kendoMultiSelect({autoClose: false});
 		search();
	};
	var xh = new XMLHttpRequest();
	function search() {
		if (xh != null) {
			var selectedValues = $('select[name="loca"]').val() ;
			var pathName = document.location.pathname;
			var index = pathName.substr(1).indexOf("/");
			var result = pathName.substr(0, index + 1);
			var url = result + "/controller/search.do?";
			if (id.value != undefined && id.value != '') {
				url = url + "tra_No=" + id.value + "&";
			}
			if (tra_name.value != undefined && tra_name.value != '') {
				url = url + "tra_Name=" + tra_name.value + "&";
			}
			if (Sdate.value != undefined && Sdate.value != '') {
				url = url + "startDay=" + Sdate.value + "&";
			}
			if (Edate.value != undefined && Edate.value != '') {
				url = url + "endDay=" + Edate.value + "&";
			}
			if (selectedValues!= undefined) {
				url = url + "loc=" + JSON.stringify(selectedValues);
			}
			xh.addEventListener("readystatechange", ajaxReturn)
			xh.open("GET", url);
			xh.send();
		} else {
			alert("Your browser doesn't support JSON!");
		}
	}
	function ajaxReturn() {
		if (xh.readyState == 4)
			if (xh.status == 200) {
				var travel = JSON.parse(xh.responseText);
				var myBody = document.querySelector(".table>tbody");
				while (myBody.hasChildNodes()) {
					myBody.removeChild(myBody.lastChild);
				}
				var tr, td, a;
				var pathName = document.location.pathname;
				var index = pathName.substr(1).indexOf("/");
				var result = pathName.substr(0, index + 1);
				for (var i = 0; i < travel.length; i++) {
					tr = document.createElement('tr');
					td = document.createElement('td');
					a = document.createElement('a');
					a.setAttribute("href",result+"/detail?tra_no="+travel[i].id);
					a.appendChild(document.createTextNode(travel[i].id));
					td.appendChild(a);
					tr.appendChild(td);

					td = document.createElement('td');
					td.appendChild(document.createTextNode(travel[i].name));
					tr.appendChild(td);

					td = document.createElement('td');
					td.appendChild(document.createTextNode(travel[i].onDate));
					tr.appendChild(td);

					td = document.createElement('td');
					td.appendChild(document.createTextNode(travel[i].offDate));
					tr.appendChild(td);

					td = document.createElement('td');
					td.appendChild(document.createTextNode(travel[i].bDate));
					tr.appendChild(td);

					td = document.createElement('td');
					td.appendChild(document.createTextNode(travel[i].eDate));
					tr.appendChild(td);

					td = document.createElement('td');
					td.setAttribute("align","center");
					td.appendChild(document.createTextNode(travel[i].people));
					tr.appendChild(td);
					
					td = document.createElement('td');
					td.setAttribute("align","center");
					td.appendChild(document.createTextNode(travel[i].peopleNow));
					tr.appendChild(td);

					td = document.createElement('td');
					td.appendChild(document.createTextNode(travel[i].location));
					tr.appendChild(td);

					myBody.appendChild(tr);
				}

			}
	}
</script>
</head>

<body>
<link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.common-material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.material.min.css" />
    <link rel="stylesheet" href="https://kendo.cdn.telerik.com/2017.1.118/styles/kendo.material.mobile.min.css" />
 <script src="js/jquery-1.12.3.min.js"></script>
 <script src="https://kendo.cdn.telerik.com/2017.1.118/js/kendo.all.min.js"></script>
<%@include file="SelectBar.jsp" %>
<h2>報名維護</h2>
	<form>
		活動代碼:<input type='text' id='id' value='' /><br> 活動名稱:<input
			type='text' id='tra_name' value='' /><br> 開始日期:<input
			type='date' id='Sdate' name='startDay' value='' /> ~ <input
			type='date' id='Edate' name='endDay' value='' /> <br>活動地點:
<!-- 			 <input -->
<!-- 			type="checkbox" name="loc" value="東" />東 <input type="checkbox" -->
<!-- 			name="loc" value="西" />西 <input type="checkbox" name="loc" value="南" />南 -->
<!-- 		<input type="checkbox" name="loc" value="北" />北  -->
        <select class="multiselect" name ="loca" id='jj' multiple="multiple" data-placeholder="請選擇" style="width: 350px;">
        <option>東</option>
        <option>西</option>
        <option>南</option>
        <option>北</option>
        </select>
		<br> <input
			type="button" value="查詢" onclick="search()" /> <input type="reset">
	</form>
	<table class='table'  cellpadding="10">
		<thead>
			<tr>
				<th>活動代碼</th>
				<th>活動名稱</th>
				<th>活動開始</th>
				<th>活動結束</th>
				<th>登記開始</th>
				<th>登記結束</th>
				<th>人數上限</th>
				<th>已報名人數</th>
				<th>活動地點</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</body>
</html>