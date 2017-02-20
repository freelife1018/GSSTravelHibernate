package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.HibernateUtil;

@WebFilter("/*")
public class loginCheck implements Filter {
	private FilterConfig fConfig = null;
	private SessionFactory factory = null;
	
	public loginCheck() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		// 請求源頭
		String path = servletRequest.getRequestURI();
		String pro = servletRequest.getContextPath();

		if (path.indexOf("login") > -1) {
			chain.doFilter(servletRequest, servletResponse);
		}

		else if (session.getAttribute("emp_No") == null || "".equals(session.getAttribute("emp_No"))) {
			servletResponse.sendRedirect(pro + "/notlogin.jsp");
		} else {
			// EmployeeDAO employeeDAO=new EmployeeDAO();//資料庫年度資訊
			// String date = new SimpleDateFormat("yyyy").format(new Date());//
			// 現在系統時
			// if(employeeDAO.year()!=Integer.parseInt(date)){
			// employeeDAO.updateYear(Integer.parseInt(date));
			// employeeDAO.updateEmp();
			// }
			Transaction tx = null;
			try {
				tx = factory.getCurrentSession().beginTransaction();
				chain.doFilter(request, response);
				tx.commit();
			} catch (Exception e) {
				tx.rollback();
				e.printStackTrace();
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
		factory = HibernateUtil.getSessionFactory();
	}

}
