package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Cancelservice {
	
	private DetailService detailService=new DetailService();
	private EmployeeService employeeService=new EmployeeService();
	private TotalAmountService totalAmountService=new TotalAmountService();
	private long sub;//補助金
	private long hireMonths;//到職幾個月
	private long money;//應補團費
	private long fam_Counts;//有一起參加的親朋好友"親友"的數量
	private long Counts=1;
	public List<Long> detail(Integer emp_No,String tra_No){
		List<Long> detail=new ArrayList<>();
		EmployeeVO employeeVo = employeeService.select(emp_No.toString());					
		java.sql.Date hireDate =employeeVo.getEmpHireDate();//員工到職日
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//現在系統時間
		java.sql.Date today = java.sql.Date.valueOf(date);		
		List<String> fam_Rels = detailService.selectFam_Rel(emp_No, tra_No);//有一起參加的親朋好友"關係"
		TotalAmountVO totalAmountVo = totalAmountService.select(emp_No,tra_No);
		long ta_Money =(long)totalAmountVo.getTaMoney();
		
		//如果點選取消的旅遊是正在使用補助金,去算補助金的額度,如果不是補助金為0元
		if(employeeVo.getEmpSubTra().equals(tra_No)){
			if(hireDate.getTime()/(24*60*60*1000)+365<today.getTime()/(24*60*60*1000)){
				sub=4500;				
			}else{				
				long x = today.getTime()/(24*60*60*1000)-hireDate.getTime()/(24*60*60*1000);//相差天數			
				hireMonths=x/31;
				sub=4500/12*hireMonths;			
			}
			if(fam_Rels!=null){
				for(String fam_Rel:fam_Rels){
					if("親友".equals(fam_Rel)){
						fam_Counts=fam_Counts+1;
					}
				}
			}
			if(fam_Rels!=null){				
				if((ta_Money/(fam_Rels.size()+Counts))*((fam_Rels.size()+Counts-fam_Counts))<sub){
					money=(ta_Money/(fam_Rels.size()+Counts))*fam_Counts;
				}else{
					money=ta_Money-sub;
				}							
			}else{
				money=0;
			}
			
		}else{
			sub=0;
			money=ta_Money;
			
		}
		detail.add(sub);		
		detail.add(ta_Money);
		detail.add(money);
		return detail;
	}	
	
}
