package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ItemService;
import model.ItemVO;
import model.TravelVO;
import model.TravelService;

@WebServlet("/FeeTravel")
public class FeeTravel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemService itemService = new ItemService();
	private TravelService travelService=new TravelService();
    public FeeTravel() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ItemVO> itemResult;
		TravelVO traveResult;
		try {
			String tra_NO=request.getParameter("tra_No");//旅遊編號
			itemResult = itemService.getFee(tra_NO);
			traveResult = travelService.getAll(tra_NO);
			HttpSession session = request.getSession();
			session.setAttribute("itemResult", itemResult);
			session.setAttribute("traveResult", traveResult);
			request.getRequestDispatcher("/Travel_No.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doGet(request, response);
	}

}
