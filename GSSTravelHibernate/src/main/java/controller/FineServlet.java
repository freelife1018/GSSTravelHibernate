package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeeService;
import model.EmployeeVO;
import model.FineService;
import model.FinesVO;
import model.ItemService;
import model.ItemVO;
import model.TravelService;
import model.TravelVO;

@WebServlet("/FineServlet")
public class FineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FineService fineService = new FineService();
	private TravelService travelService = new TravelService();
	private ItemService itemService = new ItemService();
	private EmployeeService employeeService = new EmployeeService();
	int countI = 0;
	int countJ = 0;

	public FineServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 1.接收資料
		// 2.驗證資料
		// 3.轉換資料
		// 4.呼叫Model
		// 5.根據Model執行結果，決定需要顯示的View元件

		String set = request.getParameter("FineSetting");
		String save = request.getParameter("FineSave");
		String show = request.getParameter("FineShow");
		String email = request.getParameter("FineEmail");
		FinesVO fineBean = new FinesVO();
		TravelVO travelBean = new TravelVO();
		ItemVO itemBean = new ItemVO();

		boolean power = true;
		if ("儲存罰則".equals(save)) {
			power = false;
			String[] temp1 = request.getParameterValues("day");
			String[] temp2 = request.getParameterValues("percent");

			Map<String, String> error = new HashMap<String, String>();
			request.setAttribute("error", error);

			if (temp1 == null || temp2 == null) {
				fineService.delete(fineBean);
				List<TravelVO> tResult = travelService.select(travelBean);
				List<FinesVO> fResult = fineService.select(fineBean);
				countI = fResult.size() - 1;
				countJ = tResult.size() - 1;
				request.setAttribute("countI", countI);
				request.setAttribute("countJ", countJ);
				RequestDispatcher rd = request.getRequestDispatcher("/FineShow.jsp");
				rd.forward(request, response);
			} else {
				int count1 = 0;
				int count2 = 0;
				if ("儲存罰則".equals(save)) {
					for (int i = 0; i < temp1.length; i++) {
						if (temp1[i] == "") {
							count1 = 1;
						}
					}
					if (count1 == 1) {
						error.put("day", "請輸入取消日！");
					}
				}
				if ("儲存罰則".equals(save)) {
					for (int i = 0; i < temp1.length; i++) {
						if (temp2[i] == "") {
							count2 = 1;
						}
					}
					if (count2 == 1) {
						error.put("percent", "請輸入扣款比例！");
					}
				}

				int count3 = 0;
				int count4 = 0;
				int plus = 0;
				int hundred = 0;
				int check = 0;
				int[] day = new int[temp1.length];
				int[] dayCheck = new int[temp1.length];
				if (temp1[0] != "") {
					try {
						for (int i = 0; i < temp1.length; i++) {
							day[i] = Integer.parseInt(temp1[i]);
							dayCheck[i] = Integer.parseInt(temp1[i]);
							if (day[i] <= 0) {
								plus = 1;
							}
						}
						for (int j = 0; j < temp1.length - 1; j++) {
							for (int k = j + 1; k < temp1.length; k++) {
								if (day[j] == dayCheck[k]) {
									check = 1;
								}
							}
						}
						if ("儲存罰則".equals(save) && plus == 1) {
							error.put("day", "取消日必須為正整數！");
						}
						if ("儲存罰則".equals(save) && check == 1) {
							error.put("pk", "取消日已存在！");
						}
					} catch (NumberFormatException e) {
						for (int i = 0; i < temp1.length; i++) {
							if (count1 != 1 && temp1[i] != "") {
								count3 = 1;
							}
						}
						if ("儲存罰則".equals(save) && count3 == 1) {
							error.put("day", "取消日必須為正整數！");
						}
					}
				}

				float[] percent = new float[temp2.length];
				if (temp2[0] != "") {
					try {
						for (int i = 0; i < temp2.length; i++) {
							percent[i] = Float.parseFloat(temp2[i]);
							if (percent[i] <= 0 || percent[i] > 100) {
								hundred = 1;
							}
						}
						if ("儲存罰則".equals(save) && hundred == 1) {
							error.put("percent", "扣款比例必須為小於100的正數！");
						}
					} catch (NumberFormatException e) {
						for (int i = 0; i < temp1.length; i++) {
							if (count2 != 1 && temp2[i] != "") {
								count4 = 1;
							}
						}
						if ("儲存罰則".equals(save) && count4 == 1) {
							error.put("percent", "扣款比例必須為小於100的正數！");
						}
					}
				}
				
				if ("儲存罰則".equals(save)) {
					if (error != null && !error.isEmpty()) {
						List<FinesVO> result = fineService.select(fineBean);
						request.setAttribute("select", result);
						RequestDispatcher rd = request.getRequestDispatcher("/FineSetting.jsp");
						rd.forward(request, response);
						return;
					}
				}

				if ("儲存罰則".equals(save)) {
					fineService.delete(fineBean);
					for (int i = 0; i < temp1.length; i++) {
						fineBean.setFineDates(day[i]);
						fineBean.setFinePer(percent[i]);
						fineService.insert(fineBean);
					}
					response.sendRedirect(request.getContextPath() + "/FineShowServlet");
				}
			}
		}
		if ("異動通知".equals(email)) {
			power = true;
			List<EmployeeVO> result = employeeService.selectEmp();
			LinkedHashSet mailSet = new LinkedHashSet();	
			String[] empMail = new String[result.size()];
			email em = new email();
			String[] mail = new String[result.size()];
			Iterator mailIt = null;
			for (int i = 0; i < result.size(); i++) {
				empMail[i] = result.get(i).getEmpMail();
				mailSet.add(empMail[i]);
				mailIt = mailSet.iterator();
			}
			int i = 0;
			String emp = "";
			while (mailIt.hasNext()) {
				mail[i] = mailIt.next().toString();
				emp = emp + mail[i] + ",";
				i++;
			}
			em.send(emp, "罰則異動通知！", "您好！\n罰則有些許的變更，請注意您所報名的行程，謝謝！\n祝您旅途愉快！");
			response.sendRedirect(request.getContextPath() + "/FineShowServlet");
		}
		if ("罰則設定".equals(set)) {
			PrintWriter out = response.getWriter();
			List<FinesVO> result = fineService.select(fineBean);
			out.print(fineService.to_Json(result));
			return;
		}
//		if ("罰則設定".equals(set)) {
//			power = false;
//			List<FinesVO> result = fineService.select(fineBean);
//			request.setAttribute("select", result);
//			request.setAttribute("power", power);
//			RequestDispatcher rd = request.getRequestDispatcher("/view/FineSetting.jsp");
//			rd.forward(request, response);
//		}
		if ("罰則明細".equals(show)) {
			power = true;
			List<TravelVO> tResult = travelService.select(travelBean);
			List<FinesVO> fResult = fineService.select(fineBean);
			List<ItemVO> iResult = itemService.select(itemBean);
			countI = fResult.size() - 1;
			countJ = tResult.size() - 1;
			request.setAttribute("countI", countI);
			request.setAttribute("countJ", countJ);
			request.setAttribute("tSelect", tResult);
			request.setAttribute("fSelect", fResult);
			request.setAttribute("iSelect", iResult);
			request.setAttribute("power", power);

			String formatDay = null;
			String startDay = null;
			String[][] totalDays = new String[tResult.size()][fResult.size() + 1];
			String[][] afterDay = new String[tResult.size()][fResult.size() + 1];
			List<String> days = new ArrayList<String>();
			Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < tResult.size(); i++) {
				long date = tResult.get(i).getTraOn().getTime() / 1000;
				startDay = tResult.get(i).getTraOn().toString();
				days.add(startDay);
				for (int j = fResult.size() - 1; j >= 0; j--) {
					long beforeDay = date - 60 * 60 * 24 * fResult.get(j).getFineDates();
					tResult.get(i).getTraOn().setTime(beforeDay * 1000);
					formatDay = formatter.format(tResult.get(i).getTraOn());
					String[] num = formatDay.split("-");
					int d = 0;
					d = Integer.parseInt(num[2]) + 1;
					String ym = formatDay.substring(0, 8);
					if (d < 10) {
						afterDay[i][j] = (String) ym + "0" + d;
					} else {
						afterDay[i][j] = (String) ym + d;
					}
					days.add(formatDay);
				}
				Collections.sort(days);
				for (int k = 0; k <= fResult.size(); k++) {
					totalDays[i][k] = days.get(k).toString();
				}
				request.setAttribute("afterDay", afterDay);
				request.setAttribute("totalDays", totalDays);
				days.clear();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/FineShow.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
