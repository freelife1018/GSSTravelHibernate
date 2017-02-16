<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<meta http-equiv="refresh"
	content="0;url=http://localhost:8080/GSSTravelHibernate/AllTravel" />

<title>Insert title here</title>
</head>
<body>

</body>
<script type="text/javascript">
if(${bl}){	
 		alert("報名失敗!!報名人數超過可參加人數!!");   
}else{		
		if(${bl1}){
			var money;
			var subMoney=4500;
			if(${drtail[1]*drtail[2]} < subMoney){
				money=0;
			}else{
				money=${drtail[1]*drtail[2]}-subMoney;
			}
			if(${drtail[4]}!=0){
				subMoney=(subMoney/12)*${drtail[4]};
			}
			alert("報名成功!!"+"\n"+"團費試算  年度可使用補助金額:"+subMoney+";個人團費:${drtail[1]}*${drtail[2]}=${drtail[1]*drtail[2]}"+"\n"+"個人補助金:"+subMoney+";應補團費:"+money+"\n"+"PS:團費試算僅供參考，需繳納費用以福委會通知為主"+"\n"+"補助金轉移!!!!!"+"\n"+"從原本旅遊編碼:${decide[0]};旅遊名稱:${decide[1]}"+"\n"+"轉移至"+"\n"+"旅遊編碼:${decide[2]};旅遊名稱:${decide[3]}");
		
		}else{
		    alert("報名成功!!"+"\n"+"團費試算  年度可使用補助金額:${drtail[0]};個人團費:${drtail[1]}*${drtail[2]}=${drtail[1]*drtail[2]}"+"\n"+"個人補助金:${drtail[0]};應補團費:${drtail[3]}"+"\n"+"PS:團費試算僅供參考，需繳納費用以福委會通知為主");  			
		}
		
}
</script>
</html>