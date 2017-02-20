package model;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class DetailService {
	public DetailDAO detailDAO;
	public EmployeeDAO employeeDAO;
	public ItemDAO itemDAO;
	public FamilyDAO familyDAO;
	public TravelDAO travelDAO;
	public TotalAmountDAO totalAmountDAO;
	
	//雅婷
	public EmployeeService employeeService =new EmployeeService() ;
	
	
	public List<String> selectFam_Rel(int emp_No, String tra_No) {
		detailDAO=new DetailDAO();
		return detailDAO.selectFam_Rel(emp_No, tra_No);
	}

	public int ranking(String tra_No, String myName) {
		int ranking = 0;
		DetailService detailService = new DetailService();
		LinkedHashSet<String> names = detailService.detailName(tra_No);// 已經報明姓名
		Map<String, Integer> mp = detailService.detail(tra_No);// (姓名,人數)
		int x = 0;
		for (String name : names) {
			if (name.equals(myName)) {
				ranking = 1 + x;
			} else {
				x = x + mp.get(name);
			}
		}
		return ranking;
	}

	public int count(String tra_No) {
		return new DetailDAO().tra_count(tra_No);
	}
	public Map<String, Integer> detail(String tra_No) {
		Map<String, Integer> mp = new HashMap<>();
		detailDAO = new DetailDAO();
		employeeDAO = new EmployeeDAO();
		Set<Integer> detail_Emp_No = detailDAO.detail_Emp_No(tra_No);
		if(detail_Emp_No!=null){
			for (Integer emp_No : detail_Emp_No) {
				int count = detailDAO.detail_Count(emp_No, tra_No) + 1;
				String name = employeeDAO.selectEmp_Name(emp_No);
				mp.put(name, count);
			}
		}		
		return mp;
	}
	
	public LinkedHashSet<String> detailName(String tra_No) {
		LinkedHashSet<String> result = new LinkedHashSet<>();
		detailDAO = new DetailDAO();
		employeeDAO = new EmployeeDAO();
		LinkedHashSet<Integer> detail_Emp_No = detailDAO.detail_Emp_No(tra_No);
		if(detail_Emp_No!=null){
			for (Integer emp_No : detail_Emp_No) {
				String name = employeeDAO.selectEmp_Name(emp_No);
				result.add(name);
			}
		}	
		return result;
	}

	public boolean tra_Enter(String[] fams, String emp_No, String tra_No, String[] rooms)
			throws NumberFormatException, SQLException {
		detailDAO = new DetailDAO();
		itemDAO = new ItemDAO();
		familyDAO = new FamilyDAO();
		travelDAO = new TravelDAO();
		float money = 0;
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());// 現在系統時間
		List<ItemVO> itemVos = itemDAO.getFareMoney(tra_No);
		for (ItemVO itemVo : itemVos) {
			money += itemVo.getItemMoney();
		}
		if (rooms != null) {		
			for (String room : rooms) {
				money += Float.parseFloat(room);
			}
		}
		if (fams == null) {
			detailDAO.tra_Enter(Integer.parseInt(emp_No), null, tra_No, date, money);
			return false;
		} else {
			TravelVO vo = travelDAO.getAll(tra_No);
			int x = detailDAO.tra_count(tra_No);
			int total = vo.getTraTotal();
			int max = vo.getTraMax();
			if ((x + fams.length + 1) <= total) {
				if (fams.length + 1 <= max) {
					detailDAO.tra_Enter(Integer.parseInt(emp_No), null, tra_No, date, money);
					for (String fam : fams) {
						detailDAO.tra_Enter(Integer.parseInt(emp_No), familyDAO.selectfam_No(fam).toString(), tra_No,
								date, money);
					}					
					return false;
					
				} else {
					return true;
				}
			} else {
				return true;
			}
		}
	}// false報名成功true報名失敗

	public List<Float> drtail(String emp_No, String tra_No, String[] fams, String room[])
			throws NumberFormatException, SQLException {
		employeeDAO = new EmployeeDAO();
		Integer emp_Sub = employeeDAO.selectEmp_Sub(emp_No);
		float payMoney = 0;
		float counts = 0;
		float subMoney = 0;
		float titleMoney = 0;
		float friebdCounts = 0;
		long hireMonths = 0;
		List<Float> drtail = new ArrayList<>();
		List<ItemVO> itemVos = itemDAO.getFareMoney(tra_No);
		for (ItemVO itemVo : itemVos) {
			payMoney += itemVo.getItemMoney();
		}
		if (room != null) {
			for (String x : room) {
				payMoney += Float.parseFloat(x);
			}
		}
		if (fams == null) {
			counts = 1;
		} else {
			counts = (fams.length) + 1;
			for (String fam : fams) {
				familyDAO = new FamilyDAO();
				String fam_Rel = familyDAO.selectfam_Rel(emp_No, fam);
				if ("親友".equals(fam_Rel)) {
					friebdCounts += 1;
				}
			}
		}

		if (emp_Sub == 1) {
			employeeDAO = new EmployeeDAO();
			java.sql.Date hireDate = employeeDAO.select(Integer.parseInt(emp_No)).getEmpHireDate();
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());// 現在系統時間
			java.sql.Date today = java.sql.Date.valueOf(date);
			if (hireDate.getTime() / (24 * 60 * 60 * 1000) + 365 < today.getTime() / (24 * 60 * 60 * 1000)) {
				subMoney = 4500;
			} else {
				long x = today.getTime() / (24 * 60 * 60 * 1000) - hireDate.getTime() / (24 * 60 * 60 * 1000);// 相差天數

				hireMonths = x / 31;
				subMoney = 4500 / 12 * hireMonths;
			}
		} else {
			subMoney = 0;
		}

		if ((payMoney * (counts - friebdCounts)) <= subMoney) {
			titleMoney = 0 + (friebdCounts * payMoney);
		} else {
			titleMoney = (payMoney * (counts - friebdCounts)) - subMoney + (friebdCounts * payMoney);
		}
		drtail.add(subMoney);
		drtail.add(payMoney);
		drtail.add(counts);
		drtail.add(titleMoney);
		drtail.add((float) hireMonths);
		drtail.add(friebdCounts);
		return drtail;
	}

	public boolean decide(String emp_No, float TA_money) {		
		totalAmountDAO = new TotalAmountDAO();
		TotalAmountVO totalAmountVO = totalAmountDAO.selectTa_money(emp_No);
		if (totalAmountVO.getTaMoney() != 0) {
			if (TA_money < totalAmountVO.getTaMoney()) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}
	

	// 羅集
	public List<DetailBean> select(DetailBean bean) {
		List<DetailBean> result = new ArrayList<>();
		detailDAO = new DetailDAO();
		if (bean != null && bean.getTra_NO() != null) {
			result = detailDAO.select(bean.getTra_NO());
		}
		return result;
	}

	public String SELECT_Name(int Emp_No, String Name) {
		detailDAO = new DetailDAO();
		String result = detailDAO.select_emp_Name(Emp_No, Name);
		if (result == null) {
			result = detailDAO.select_fam_Name(Emp_No, Name);
		}
		return result;
	}

	
	
	public boolean insert(DetailVO bean) {
		detailDAO = new DetailDAO();
		if (bean != null) {
			detailDAO.insert(bean);
			Float TA_money = detailDAO.select_TotalMoney(bean.getEmployee().getEmpNo(), bean.getTravel().getTraNo());
			String top1_Tra_No = detailDAO.SELECT_top1_Tra_No(bean.getEmployee().getEmpNo());
			detailDAO.Update_TA(TA_money, bean.getEmployee().getEmpNo(), bean.getTravel().getTraNo());
			detailDAO.UPDATE_emp_SubTra(top1_Tra_No, bean.getEmployee().getEmpNo());
			return true;
		} else {
			return false;
		}
	}

	public boolean insert_emp(DetailVO bean) {
		detailDAO = new DetailDAO();
		if (bean != null) {
			detailDAO.insert_emp(bean);
			Float TA_money = detailDAO.select_TotalMoney(bean.getEmployee().getEmpNo(), bean.getTravel().getTraNo());
			String top1_Tra_No = detailDAO.SELECT_top1_Tra_No(bean.getEmployee().getEmpNo());
			detailDAO.INSERT_TA(bean.getTravel().getTraNo(), bean.getEmployee().getEmpNo(), TA_money);
			detailDAO.UPDATE_emp_SubTra(top1_Tra_No, bean.getEmployee().getEmpNo());
			return true;
		} else {
			return false;
		}
	}

	public boolean update(DetailBean bean) {
		boolean result = false;
		if (bean != null) {
			String Rel = detailDAO.Select_Rel(bean.getDet_No());
			int emp_No = detailDAO.select_emp_No(bean.getDet_No());
			if (Rel.equals("員工")) {
				String emp_SubTra = detailDAO.SELECT_emp_SubTra(emp_No);
				String top1_Tra_No = detailDAO.SELECT_top1_Tra_No(emp_No);
				String top2_Tra_No = detailDAO.SELECT_top2_Tra_No(emp_No);
				String canTra_No = bean.getTra_NO();
				if (canTra_No.equals(emp_SubTra)) {
					if (emp_SubTra.equals(top1_Tra_No)) {
						if (top1_Tra_No.equals(top2_Tra_No)) {
							detailDAO.UPDATE_emp_Sub(emp_No);
						} else {
							detailDAO.UPDATE_emp_SubTra(top2_Tra_No, emp_No);
						}
					}
				}
				detailDAO.DELETE_TA(canTra_No, emp_No);
				result = detailDAO.update(emp_No, bean.getDet_canNote(), bean.getTra_NO());
			} else {
				detailDAO.update_FamCanDate(bean.getDet_No(), bean.getDet_canNote());
				String top1_Tra_No = detailDAO.SELECT_top1_Tra_No(emp_No);
				detailDAO.UPDATE_emp_SubTra(top1_Tra_No, emp_No);
				Float TA_money = detailDAO.select_TotalMoney(emp_No, bean.getTra_NO());
				detailDAO.Update_TA(TA_money, emp_No, bean.getTra_NO());
			}
			result = true;
		}
		return result;
	}

	public Boolean update_empData(EmployeeVO bean) {
		Boolean result = false;
		if (bean != null) {
			result = detailDAO.UPDATE_empData(bean);
		}
		return result;
	}

	public Boolean update_famData(FamilyVO bean) {
		Boolean result = false;
		if (bean != null) {
			detailDAO.UPDATE_famData(bean);
			result = true;
		}
		return result;
	}
	
//	public TravelVO Count(String tra_No) {
//		travelDAO = new TravelDAO();
//		return travelDAO.Count(tra_No);
//	}

	public final Pattern TWPID_PATTERN = Pattern.compile("[ABCDEFGHJKLMNPQRSTUVXYWZIO][12]\\d{8}");

	public boolean isValidTWPID(String twpid) {
		boolean result = false;
		String pattern = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
		if (TWPID_PATTERN.matcher(twpid.toUpperCase()).matches()) {
			int code = pattern.indexOf(twpid.toUpperCase().charAt(0)) + 10;
			int sum = 0;
			sum = (int) (code / 10) + 9 * (code % 10) + 8 * (twpid.charAt(1) - '0') + 7 * (twpid.charAt(2) - '0')
					+ 6 * (twpid.charAt(3) - '0') + 5 * (twpid.charAt(4) - '0') + 4 * (twpid.charAt(5) - '0')
					+ 3 * (twpid.charAt(6) - '0') + 2 * (twpid.charAt(7) - '0') + 1 * (twpid.charAt(8) - '0')
					+ (twpid.charAt(9) - '0');
			if ((sum % 10) == 0) {
				result = true;
			}
		}
		return result;
	}
	// 雅婷
	public void updateDet_CanDate(String emp_No, String tra_No) {
		detailDAO = new DetailDAO();
		detailDAO.updateDet_CanDate(emp_No, tra_No);
	}
	public boolean update_empNo(String det_note, float det_noteMoney, String tra_No, int emp_No) {
		detailDAO = new DetailDAO();
		boolean b = true;
		b = detailDAO.update_empNo(det_note, det_noteMoney, tra_No, emp_No);
		return b;
	}
	public boolean update_famNo(String det_note, float det_noteMoney, String tra_No, int fam_No) {
		detailDAO = new DetailDAO();
		boolean b = true;
		b = detailDAO.update_famNo(det_note, det_noteMoney, tra_No, fam_No);
		return b;
	}  
	
	public List<TotalAmountFormBean> select(String tra_No) {
		detailDAO = new DetailDAO();
		List<TotalAmountFormBean> list = detailDAO.selectBean(tra_No);
		if (list != null) {
			for (TotalAmountFormBean bean : list) {
				if (bean.getFamNo() == 0) {
					if (bean.getEmpSubTra().equals(bean.getTraNo()) && !bean.getEmpSub()) {
						Integer emp_No = bean.getEmpNo();
						System.out.println(emp_No);
						String emp_NO=emp_No.toString();
						EmployeeVO employeeVO = employeeService.select(emp_NO);
						java.sql.Date hireDate = employeeVO.getEmpHireDate();
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());// 現在系統時間
						java.sql.Date today = java.sql.Date.valueOf(date);
						if (hireDate.getTime() / (24 * 60 * 60 * 1000) + 365 < today.getTime()
								/ (24 * 60 * 60 * 1000)) {
							double money = 4500;
							bean.setYearMoney(money);
						} else {
							long x = today.getTime() / (24 * 60 * 60 * 1000)
									- hireDate.getTime() / (24 * 60 * 60 * 1000);// 相差天數
							long hireMonths = x / 31;
							double money = 4500 / 12 * hireMonths;
							bean.setYearMoney(money);
							System.out.println("bean.getYearMoney="+bean.getYearMoney());
						}
					} else {
						bean.setYearMoney(0);
					}	
					System.out.println("bean.getYearMoney="+bean.getYearMoney());
				}
				familyDAO = new FamilyDAO();
				String fam_Rel = familyDAO.selectfam_Rel(Integer.toString(bean.getEmpNo()), bean.getFamName());
				if ("親友".equals(fam_Rel)) {
					bean.setFamSub(false);
				} else {
					bean.setFamSub(true);
				}
			}
		}
		return list;
	}
}
