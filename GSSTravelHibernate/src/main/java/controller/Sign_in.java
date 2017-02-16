package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DetailService;
import model.EmployeeService;
import model.EmployeeVO;
import model.TotalAmountService;
import model.TravelService;
import model.TravelVO;

@WebServlet("/Sign_in")
public class Sign_in extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailService detailService=new DetailService();
	private EmployeeService employeeService=new EmployeeService();
	private TotalAmountService totalAmountService=new TotalAmountService();
	private TravelService travelService=new TravelService();
	public boolean bl=true;
	public boolean bl1=false;
	public List<Float> drtail;
	float TA_money;
	float sub_Money;
	List<String> decide;
	public Sign_in() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		try {
			String[] fams = req.getParameterValues("fam");
			String[] room = req.getParameterValues("room");
			String emp_No = req.getParameter("emp_No");
			String tra_No = req.getParameter("tra_No");
			bl = detailService.tra_Enter(fams, emp_No, tra_No,room);
			String Emp_SubTra = null;//使用補助金的旅遊編號
			String tra_Name = null;//使用補助金的旅遊名稱
			EmployeeVO employeeVO = employeeService.select(emp_No);
			
			if(!bl){						
				drtail=detailService.drtail(emp_No, tra_No, fams,room);
				TA_money=drtail.get(1)*drtail.get(2);
				sub_Money=drtail.get(1)*(drtail.get(2)-drtail.get(5));
				if(detailService.decide(emp_No,sub_Money)){						
					if(totalAmountService.counts(emp_No)>0 && employeeVO.isEmpSub()==false){
						bl1 =true;
						decide=new ArrayList<>();
						Emp_SubTra=employeeService.select(emp_No).getEmpSubTra();
						TravelVO travelvo = travelService.getAll(Emp_SubTra);
						tra_Name=travelvo.getTraName();
						TravelVO travelvo2 = travelService.getAll(tra_No);			
						decide.add(Emp_SubTra);
						decide.add(tra_Name);
						decide.add(tra_No);
						decide.add(travelvo2.getTraName());
					}
					String emp_SubTra = employeeService.select(emp_No).getEmpSubTra();
			
					if(emp_SubTra==null||emp_SubTra.equals("null")){
						employeeService.updateEmp_SubTra(tra_No, emp_No);
					}else{
						TravelVO travelVo = travelService.getAll(emp_SubTra);
						java.sql.Date tra_Off = travelVo.getTraOff();
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());//現在系統時間
						java.sql.Date today = java.sql.Date.valueOf(date);
						if(tra_Off.after(today)){
							employeeService.updateEmp_SubTra(tra_No, emp_No);							
						}						
					}					
				}
					totalAmountService.insertTotalAmount(tra_No, Integer.parseInt(emp_No), TA_money);							
					employeeService.updateEmp_Sub(false,emp_No);
					String email = employeeService.select(emp_No).getEmpMail();
					TravelVO travelVo = travelService.getAll(tra_No);
					String title=travelVo.getTraName()+"報名成功!!";
					String content="恭喜你報名成功!!"+"\n"+"行程編號:"+tra_No+"\n"+"行程名稱:"+travelVo.getTraName()+"行程開始日:"
									+travelVo.getTraOn()+"\n\n\n"+"最後取消時間:"+travelVo.getTraEnd()+"\n"+"PS:有問題請詢問福委會";
					new email().send(email, title, content);
			}
			HttpSession session = req.getSession();
			session.setAttribute("decide", decide);
			session.setAttribute("bl1", bl1);
			req.setAttribute("drtail", drtail);
			req.setAttribute("bl", bl);
			bl1=false;
			req.getRequestDispatcher("/display.jsp").forward(req, resp);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
			this.doGet(req, resp);
	}

}
