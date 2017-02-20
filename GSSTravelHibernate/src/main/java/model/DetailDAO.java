package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class DetailDAO {
	public DetailDAO() {
		super();
	}

	private static final String tra_Count = "SELECT COUNT(*) AS count FROM Detail where tra_No =? and det_CanDate IS  NULL";
	private static final String selectFamRel = "select f.fam_Rel as fam_Rel from Detail d join Family f on d.fam_No=f.fam_No where d.det_CanDate is null and tra_No=? and d.emp_No=?";
	private static final String selectFamNoForselectFam = "from DetailVO where fam_No=? and tra_No=? and det_CanDate is null ";
	private static final String detail_Enter = "from DetailVO where emp_No=? and tra_No=? and det_CanDate is null order by det_Date";
	private static final String detail_Emp_No = "from DetailVO where tra_No=? and det_CanDate is null order by det_Date";
	private static final String detail_Count = "select count(f.fam_Name) as count from Detail d join Family f ON f.fam_No=d.fam_No where d.emp_No=? and d.tra_No=? and d.det_CanDate is null";
	private static final String updateDet_CanDate = "update DetailVO set det_CanDate=? where emp_No=? and tra_No=?";

	public List<String> selectFam_Rel(Integer emp_No, String tra_No) {
		List<String> fam_Rels = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(selectFamRel);
			query.setParameter(0, tra_No);
			query.setParameter(1, emp_No);
			fam_Rels = query.list();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return fam_Rels;
	}

	public boolean selectFam_No(Integer fam_No, String tra_No) {
		boolean bl = false;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(selectFamNoForselectFam);
			query.setParameter(0, fam_No);
			query.setParameter(1, tra_No);
			if (query.list().size() != 0) {
				bl = true;
			}
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return bl;
	}

	public int detail_Enter(String emp_No, String tra_No) {
		int a = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(detail_Enter);
			query.setParameter(0, emp_No);
			query.setParameter(1, tra_No);
			if (query.list().size() != 0) {
				a = 1;
			}
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return a;
	}

	public LinkedHashSet<Integer> detail_Emp_No(String tra_No) {
		LinkedHashSet<Integer> result = new LinkedHashSet<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(detail_Emp_No);
			query.setParameter(0, tra_No);
			List<DetailVO> details = query.list();
			for (DetailVO detail : details) {
				result.add(detail.getEmployee().getEmpNo());
			}
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return result;
	}

	public int detail_Count(Integer emp_No, String tra_No) {
		int count = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(detail_Count);
			query.setParameter(0, emp_No);
			query.setParameter(1, tra_No);
			count = (int) query.list().get(0);
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return count;
	}

	public int tra_count(String tra_No) {
		int count = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(tra_Count);
			query.setParameter(0, tra_No);
			count = (int) query.list().get(0);
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return count;
	}

	public Map<String, Long> tra_count() {
		Map<String, Long> result = new HashMap<String, Long>();
		TravelDAO travelDAO = new TravelDAO();
		List<String> allTra_No = travelDAO.selectTra_No();// 所有活動編號
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			for (String Tra_No : allTra_No) {
				SQLQuery query = session.createSQLQuery(tra_Count);
				query.setParameter(0, Tra_No);
				result.put(Tra_No, Long.parseLong(query.list().get(0).toString()));
			}
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return result;
	}

	public void tra_Enter(int emp_No, String fam_No, String tra_No, String det_Date, Float det_money) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			DetailVO detail = new DetailVO();
			EmployeeVO employee = new EmployeeVO(emp_No);
			if (fam_No != null) {
				FamilyVO family = new FamilyVO(fam_No);
				detail.setFamily(family);
			}
			TravelVO travel = new TravelVO(tra_No);
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());// 現在系統時間
			detail.setEmployee(employee);
			detail.setTravel(travel);
			detail.setDetDate(java.sql.Timestamp.valueOf(date));
			detail.setDetMoney(Double.parseDouble(det_money.toString()));
			session.save(detail);
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	public void updateDet_CanDate(String emp_No, String tra_No) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());// 現在系統時間
		try {
			Query query = session.createQuery(updateDet_CanDate);
			query.setParameter(0, date);
			query.setParameter(1, emp_No);
			query.setParameter(2, tra_No);
			query.executeUpdate();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	// 雅婷
	private static final String UPDATE_DETAIL_FOR_EMP_NO = "update Detail set det_note=? , det_noteMoney=? where emp_No=? and fam_No is null and tra_No=?";

	public boolean update_empNo(String det_note, float det_noteMoney, String tra_No, int emp_No) {
		int b = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(UPDATE_DETAIL_FOR_EMP_NO);
			query.setParameter(0, det_note);
			query.setParameter(1, det_noteMoney);
			query.setParameter(2, emp_No);
			query.setParameter(3, tra_No);
			b = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		if (b > 0) {
			return true;
		} else {
			return false;
		}
	}

	private static final String UPDATE_DETAIL_FOR_FAM_NO = "update Detail set det_note=? , det_noteMoney=? where fam_No=? and tra_No=?";

	public boolean update_famNo(String det_note, float det_noteMoney, String tra_No, int fam_No) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		int b = 0;
		try {
			SQLQuery query = session.createSQLQuery(UPDATE_DETAIL_FOR_FAM_NO);

			query.setParameter(0, det_note);
			query.setParameter(1, det_noteMoney);
			query.setParameter(2, fam_No);
			query.setParameter(3, tra_No);
			b = query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		if (b > 0) {
			return true;
		} else {
			return false;
		}
	}

	private static final String SELECT_BY_TRA_NO = "select d.tra_No, t.tra_Name , e.dept_No , e.emp_No , f.fam_No , e.emp_Name , e.emp_sub , f.fam_Name , d.det_money ,e.emp_subTra , d.det_note , d.det_noteMoney from Detail d join Employee e on d.emp_No=e.emp_No left join Family f on d.fam_No = f.fam_No left join Travel t on t.tra_No=d.tra_No where d.tra_No=? and d.det_CanDate is null";

	@SuppressWarnings("unchecked")
	public List<TotalAmountFormBean> selectBean(String tra_No) {
		List<Object[][]> result = null;
		List<TotalAmountFormBean> Beans = new ArrayList<TotalAmountFormBean>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(SELECT_BY_TRA_NO);
			query.setParameter(0, tra_No);
			result = query.list();

			for (Object[] obj : result) {
				TotalAmountFormBean bean = new TotalAmountFormBean();
				bean.setTraNo(obj[0].toString());
				bean.setTraName(obj[1].toString());
				bean.setDeptNo(obj[2].toString());
				bean.setEmpNo(Integer.parseInt(obj[3].toString()));
				if(obj[4] != null) {
					bean.setFamNo(Integer.parseInt(obj[4].toString()));
				} else {
					bean.setFamNo(0);
				}
				bean.setEmpName(obj[5].toString());
				bean.setEmpSub(Boolean.parseBoolean(obj[6].toString()));
				if (obj[7] != null) {
					bean.setFamName(obj[7].toString());
				} else {
					bean.setFamName("");
				}
				bean.setDetMoney(Float.parseFloat(obj[8].toString()));
				if (obj[9] != null) {
					bean.setEmpSubTra(obj[9].toString());
				} else {
					bean.setEmpSubTra("");
				}
				if (obj[10] != null) {
					bean.setDetNote(obj[10].toString());
				} else {
					bean.setDetNote("");
				}
				if (obj[11] != null) {
					bean.setDetNoteMoney(Float.parseFloat(obj[11].toString()));
				} else {
					bean.setDetNoteMoney(0);
				}
				Beans.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return Beans;
	}

}