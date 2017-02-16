package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogOut.do")
public class LogOut_AND_RoleControll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogOut_AND_RoleControll() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (request.getParameter("role") == null || request.getParameter("role") == "") {
			session.removeAttribute("emp_No");
			session.removeAttribute("emp_Role");
			session.removeAttribute("emp_Name");
			String path=request.getContextPath();
			response.sendRedirect(path+"/login.jsp");
			return;
		} else {
			PrintWriter pt = response.getWriter();
			pt.print(session.getAttribute("emp_Role"));
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
