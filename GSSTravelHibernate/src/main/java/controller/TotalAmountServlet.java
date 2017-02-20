package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DetailService;
import model.EmployeeVO;
import model.TotalAmountService;
import model.TotalAmountVO;
import model.TravelVO;

@WebServlet(urlPatterns = { "/TotalAmountServlet" })
public class TotalAmountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TotalAmountService totalamountService;
	private DetailService detailService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 接收資料
		String prodaction = request.getParameter("prodaction");
		String[] temp1 = request.getParameterValues("emp_No");
		String tra_No = request.getParameter("tra_No");
		String[] temp2 = request.getParameterValues("TA_money");
		String[] det_note = request.getParameterValues("det_note");
		String[] temp3 = request.getParameterValues("det_noteMoney");
		String[] temp4 = request.getParameterValues("empfam");
		// 轉換資料
		HttpSession session = request.getSession();
		session.removeAttribute("Msg");
		
		if ("save".equals(prodaction)) {
			int[] emp_No = new int[temp1.length];
			float[] TA_money = new float[temp2.length];
			float[] det_noteMoney = new float[temp3.length];
			int[][] empfam = new int[temp4.length][2];
			try {
				int a = 0;
				for (String i : temp1) {
					emp_No[a] = Integer.parseInt(i);
					a++;
				}
				a = 0;
				for (String k : temp2) {
					TA_money[a] = Float.parseFloat(k);
					a++;
				}
				a = 0;
				for (String j : temp3) {
					if (j != "" && j.trim().length() != 0) {
						det_noteMoney[a] = Float.parseFloat(j);
					} else {
						det_noteMoney[a] = 0;
					}
					a++;
				}
				a = 0;
				String[] temp = new String[2];
				for (String k : temp4) {
					try {
						empfam[a][0] = Integer.parseInt(k);
					} catch (NumberFormatException e) {
						temp = k.split("/");
						empfam[a][0] = Integer.parseInt(temp[0]); // emp_No
						empfam[a][1] = Integer.parseInt(temp[1]); // fam_No
					}
					a++;
				}
				TotalAmountVO totalAmountVO = new TotalAmountVO();
				boolean b = true;
				for (int i = 0; i < emp_No.length; i++) {
					totalamountService = new TotalAmountService();
					totalAmountVO.setTaMoney(TA_money[i]);
					totalAmountVO.setTravel(new TravelVO(tra_No));
					totalAmountVO.setEmployee(new EmployeeVO(emp_No[i]));
					if (b) {
						b = totalamountService.update(totalAmountVO);
					} else {
						session.setAttribute("Msg", "新增TOTAMOUNT失敗");
					}
				}
				for (int i = 0; i < det_note.length; i++) {
					if (det_note[i] != null && det_note[i].trim().length() != 0) {
						detailService = new DetailService();
						if (empfam[i][1] == 0) {
							b = detailService.update_empNo(det_note[i], det_noteMoney[i], tra_No, empfam[i][0]);
						} else {
							b = detailService.update_famNo(det_note[i], det_noteMoney[i], tra_No, empfam[i][1]);
							System.out.println("ba="+b);
						}
						if (b == false) {
							session.setAttribute("Msg", "a更新DETAIL失敗");
						}
					} else if ((det_note[i] == null || det_note[i].trim().length() == 0) && det_noteMoney[i] == 0.0) {
						detailService = new DetailService();
						if (empfam[i][1] == 0) {
							b = detailService.update_empNo(det_note[i], det_noteMoney[i], tra_No, empfam[i][0]);
						} else {
							b = detailService.update_famNo(det_note[i], det_noteMoney[i], tra_No, empfam[i][1]);
						}
						if (b == false) {
							session.setAttribute("Msg", "b更新DETAIL失敗");
						}

					} else {
						session.setAttribute("Msg", "沒寫明細說明不可扣減免費用");
					}
				}
				if (session.getAttribute("Msg") == null) {
					session.setAttribute("Msg", "更新成功");	
				}
			} catch (NumberFormatException e) {
				session.setAttribute("Msg", "其他增減費用總額/應補團費  輸入非數字之字");
			}
			request.setAttribute("session", session);
			request.getRequestDispatcher("/TravelDetail?tra_no="+tra_No).forward(request, response);
			return;
		}if("Excel".equals(prodaction)){
			return;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
