package controller;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cancelservice;
import model.DetailService;
import model.EmployeeService;
import model.FamilyService;
import model.FamilyVO;
import model.ItemService;
import model.ItemVO;
import model.TravelService;
import model.TravelVO;

@WebServlet("/CancelServlet")
public class CancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailService detailService=new DetailService();
	private EmployeeService employeeService=new EmployeeService();
	private FamilyService familyService=new FamilyService();
	private TravelService travelService=new TravelService();
	private ItemService itemService=new ItemService();
	private Cancelservice cancelservice=new Cancelservice();
	public CancelServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String tra_No=request.getParameter("tra_No");//旅遊編號
		Integer emp_No=Integer.parseInt(request.getParameter("emp_No"));//員工標號
		String myName = employeeService.getName(emp_No);//登入者姓名		
		LinkedHashSet<String> names = detailService.detailName(tra_No);//已經報明姓名
		Map<String, Integer> mp = detailService.detail(tra_No);//(姓名,人數)
		List<FamilyVO> familyVO = familyService.selectFam(emp_No,tra_No);//親朋好友
		int familySize = familyVO.size();//親朋好友數量
		TravelVO tra_Vo = travelService.getAll(tra_No);
		List<ItemVO> itemVo = itemService.getRoomMoney(tra_No);
		List<Long> detail = cancelservice.detail(emp_No, tra_No);
		
		request.setAttribute("detail", detail);
		request.setAttribute("itemVo", itemVo);
		request.setAttribute("tra_Vo", tra_Vo);
		request.setAttribute("familySize", familySize);
		request.setAttribute("familyVO", familyVO);
		request.setAttribute("myName", myName);
		request.setAttribute("emp_No", emp_No);
		request.setAttribute("name", names);
		request.setAttribute("mp", mp);
		request.setAttribute("tra_count", detailService.count(tra_No));//單行程旅遊人數
		request.setAttribute("tra_order", detailService.ranking(tra_No,myName));//報名順序
		request.getRequestDispatcher("/Cancel.jsp").forward(request, response);;			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
