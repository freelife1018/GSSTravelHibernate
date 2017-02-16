package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TravelService;
import model.TravelVO;

@WebServlet("/controller/search.do")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/htm; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String number = request.getParameter("tra_No");
		String name = request.getParameter("tra_Name");
		String start = request.getParameter("startDay");
		String end = request.getParameter("endDay");
		String loca = request.getParameter("loc");

		PrintWriter out = response.getWriter();
		
		java.text.SimpleDateFormat simple = new java.text.SimpleDateFormat();
		simple.applyPattern("yyyy-MM-dd");
		
		TravelService ts=new TravelService();
		List<TravelVO> tv = null;
		try {
			tv = ts.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		if (number == null && name == null && start == "" && end == "" && request.getParameterValues("location") == null) {	
			out.print(ts.to_Json(tv));
			return;
		}
		if(number!=null || name!=null){
			if(number==null){number="";}
			if(name==null){name="";}
			tv = ts.get_by_no_name(number, name);
		}
		if(start!=null && start!=""){
			try {
				tv=ts.AfterOn(tv, simple.parse(start));
			} catch (ParseException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(end!=null && end!=""){
			try {
				tv=ts.BeforeOff(tv, simple.parse(end));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("loc") != null){
			String[] items = loca.replaceAll("\\[", "").replaceAll("\"","")
	                 .replaceAll("\\]", "").split(",");
			tv=ts.where(tv, items);
		}
		out.print(ts.to_Json(tv));
		return;

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
