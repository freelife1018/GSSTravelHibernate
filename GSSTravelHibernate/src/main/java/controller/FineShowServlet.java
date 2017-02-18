package controller;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FineService;
import model.FinesVO;
import model.ItemService;
import model.ItemVO;
import model.TravelService;
import model.TravelVO;

@WebServlet("/FineShowServlet")
public class FineShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FineService fineService = new FineService();
	private TravelService travelService = new TravelService();
	private ItemService itemService = new ItemService();
	int countI = 0;
	int countJ = 0;

	public FineShowServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		TravelVO travelBean = new TravelVO();
		ItemVO itemBean = new ItemVO();
		boolean power = true;
		power = true;
		List<TravelVO> tResult = travelService.select(travelBean);
		List<FinesVO> fResult = fineService.select();
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
