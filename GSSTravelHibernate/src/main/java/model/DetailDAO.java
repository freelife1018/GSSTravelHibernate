package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.BooleanType;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

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
	private static final String updateDet_CanDate="update DetailVO set det_CanDate=? where emp_No=? and tra_No=?";
	
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
	
	
	private static final String SELECT = "SELECT det_No, Detail.emp_No, ISNULL(Detail.fam_No,Detail.emp_No) as number, ISNULL(fam_Rel,'員工') as Rel, ISNULL(fam_Name, emp_Name) as Name, ISNULL(fam_Sex,emp_Sex) as Sex, ISNULL(fam_ID, emp_ID) as ID,ISNULL(fam_Bdate,emp_Bdate) as Bdate, ISNULL(fam_Phone,emp_Phone) as Phone,ISNULL(fam_eat,emp_Eat) as Eat, ISNULL(fam_Car,1) as Car, ISNULL(fam_Bady,0) as Bady, ISNULL(fam_kid,0) as Kid, ISNULL(fam_Dis,0) as Dis, ISNULL(fam_Mom,0) as Mom,ISNULL(fam_Ben,emp_Ben) as Ben, ISNULL(fam_BenRel,emp_BenRel) as BenRel, ISNULL(fam_Emg,emp_Emg) as Emg, ISNULL(fam_EmgPhone,emp_EmgPhone) as EmgPhone, det_Date, det_CanDate as CanDate, ISNULL(fam_Note,emp_Note) as Note, det_canNote FROM Detail full outer join family on  Detail.fam_No = family.fam_No full outer join Employee on Detail.emp_No = Employee.emp_No WHERE Tra_No = ? order by CanDate";

	@SuppressWarnings("unchecked")
	public List<DetailBean> select(String Tra_No) {
		List<Object[]> result = null;
		List<DetailBean> Bbean = new ArrayList<DetailBean>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			SQLQuery query = session.createSQLQuery(SELECT);
			query.addScalar("det_No", StringType.INSTANCE);
			query.addScalar("emp_No", StringType.INSTANCE);
			query.addScalar("number", IntegerType.INSTANCE);
			query.addScalar("Rel", StringType.INSTANCE);
			query.addScalar("Name", StringType.INSTANCE);
			query.addScalar("Sex", StringType.INSTANCE);
			query.addScalar("ID", StringType.INSTANCE);
			query.addScalar("Bdate",StringType.INSTANCE);
			query.addScalar("Phone", StringType.INSTANCE);
			query.addScalar("Eat", StringType.INSTANCE);
			query.addScalar("Car", BooleanType.INSTANCE);
			query.addScalar("Bady", BooleanType.INSTANCE);
			query.addScalar("kid", BooleanType.INSTANCE);
			query.addScalar("Dis", BooleanType.INSTANCE);
			query.addScalar("Mom", BooleanType.INSTANCE);
			query.addScalar("Ben", StringType.INSTANCE);
			query.addScalar("BenRel", StringType.INSTANCE);
			query.addScalar("Emg", StringType.INSTANCE);
			query.addScalar("EmgPhone", StringType.INSTANCE);
			query.addScalar("det_Date", StringType.INSTANCE);
			query.addScalar("CanDate", StringType.INSTANCE);
			query.addScalar("Note", StringType.INSTANCE);
			query.addScalar("det_canNote", StringType.INSTANCE);
			query.setParameter(0, Tra_No);
			result = query.list();
			for (Object[] x : result) {
				DetailBean Dbean = new DetailBean();
				Dbean.setDet_No(Integer.parseInt(x[0].toString()));
				Dbean.setEmp_No(Integer.parseInt(x[1].toString()));
				Dbean.setFam_No(Integer.parseInt(x[2].toString()));
				Dbean.setRel(x[3].toString());
				Dbean.setName(x[4].toString());
				Dbean.setSex(x[5].toString());
				Dbean.setID(x[6].toString());
				//Date date = sdf.parse(x[7].toString());
				Dbean.setBdate(x[7].toString());
				Dbean.setPhone(x[8].toString());
				Dbean.setEat(x[9].toString());
				Dbean.setCar(Boolean.parseBoolean(x[10].toString()));
				Dbean.setFam_Bady(Boolean.parseBoolean(x[11].toString()));
				Dbean.setFam_kid(Boolean.parseBoolean(x[12].toString()));
				Dbean.setFam_Dis(Boolean.parseBoolean(x[13].toString()));
				Dbean.setFam_Mom(Boolean.parseBoolean(x[14].toString()));
				Dbean.setBen(x[15].toString());
				Dbean.setBenRel(x[16].toString());
				Dbean.setEmg(x[17].toString());
				Dbean.setEmgPhone(x[18].toString());
				Dbean.setDet_Date(x[19].toString());
				if (x[20] != null) {
					Dbean.setDet_CanDate(x[20].toString());
				}
				if (x[21] != null) {
					Dbean.setNote(x[21].toString());
				}
				if (x[22] != null) {
					Dbean.setDet_canNote(x[22].toString());
				}
				Bbean.add(Dbean);
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return Bbean;
	}

	private static final String SELECT_emp_Name = "SELECT emp_No = '員工' from Employee where emp_No=? and emp_Name=?";

	public String select_emp_Name(int Emp_No, String Emp_Name) {
		String result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(SELECT_emp_Name);
			query.setParameter(0, Emp_No);
			query.setParameter(1, Emp_Name);
			result = (String) query.list().get(0);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	private static final String SELECT_fam_Name = "SELECT fam_No from Family where emp_No=? and fam_Name=?";

	public String select_fam_Name(int Emp_No, String Fam_Name) {
		String result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(SELECT_fam_Name);
			query.setParameter(0, Emp_No);
			query.setParameter(1, Fam_Name);
			result = query.list().get(0).toString();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	private static final String SELECT_emp_SubTra = "SELECT emp_SubTra FROM Employee where emp_No=?";

	public String SELECT_emp_SubTra(int Emp_No) {
		String result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(SELECT_emp_SubTra);
			query.setParameter(0, Emp_No);
			result = (String) query.list().get(0);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	private static final String SELECT_top1_Tra_No = "SELECT  TOP 1 Tra_No FROM (SELECT TOP 1  Detail.Tra_No, Detail.emp_No,SUM(det_money) as totalMoney FROM Detail full outer join family on  Detail.fam_No = family.fam_No full outer join Employee on Detail.emp_No = Employee.emp_No full outer join Travel on Detail.tra_No = Travel.tra_No WHERE Detail.emp_No=? and ISNULL(fam_Rel,'員工') <> '親友' and det_CanDate is null and tra_End>GETDATE() GROUP BY  Detail.emp_No,Detail.Tra_No ORDER BY totalMoney DESC )temp1";

	public String SELECT_top1_Tra_No(int Emp_No) {
		String result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(SELECT_top1_Tra_No);
			query.setParameter(0, Emp_No);
			result = (String) query.list().get(0);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	private static final String SELECT_top2_Tra_No = "SELECT  TOP 1 Tra_No FROM (SELECT TOP 2 Detail.Tra_No, Detail.emp_No,SUM(det_money) as totalMoney FROM Detail full outer join family on  Detail.fam_No = family.fam_No full outer join Employee on Detail.emp_No = Employee.emp_No full outer join Travel on Detail.tra_No = Travel.tra_No WHERE Detail.emp_No=? and ISNULL(fam_Rel,'員工') <> '親友' and det_CanDate is null and tra_End>GETDATE() GROUP BY  Detail.emp_No,Detail.Tra_No order by totalMoney DESC)temp1 ORDER BY totalMoney ";

	public String SELECT_top2_Tra_No(int Emp_No) {
		String result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(SELECT_top2_Tra_No);
			query.setParameter(0, Emp_No);
			result = (String) query.list().get(0);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	private static final String SELECT_emp_No = "SELECT emp_No FROM Detail WHERE det_No=?";

	public int select_emp_No(int det_No) {
		int result = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(SELECT_emp_No);
			query.setParameter(0, det_No);
			result = (int) query.list().get(0);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	private static final String SELECT_Rel = "SELECT ISNULL(fam_Rel,'員工') as Rel FROM Detail full outer join family on  Detail.fam_No = family.fam_No full outer join Employee on Detail.emp_No = Employee.emp_No  WHERE det_No = ?";

	public String Select_Rel(int det_No) {
		String result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(SELECT_Rel);
			query.setParameter(0, det_No);
			result = (String) query.list().get(0);
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	private static final String select_TotalMoney = "SELECT totalMoney FROM (SELECT Detail.Tra_No, Detail.emp_No,SUM(det_money) as totalMoney FROM Detail full outer join family on  Detail.fam_No = family.fam_No full outer join Employee on Detail.emp_No = Employee.emp_No full outer join Travel on Detail.tra_No = Travel.tra_No WHERE Detail.emp_No=? and Detail.Tra_No=? and det_CanDate is null GROUP BY  Detail.emp_No,Detail.Tra_No )temp1";

	public float select_TotalMoney(int emp_No, String Tra_No) {
		float result = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(select_TotalMoney);
			query.setParameter(0, emp_No);
			query.setParameter(1, Tra_No);
			result = Float.parseFloat(query.list().get(0).toString());
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	private static final String INSERT_Detail = "insert into Detail(emp_No,fam_No,tra_No,det_Date,det_money) values(?,?,?,GETDATE(),?)";

	public boolean insert(DetailVO bean) {
		boolean b = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(INSERT_Detail);
			if (bean != null) {
				query.setParameter(0, bean.getEmployee().getEmpNo());
				query.setParameter(1, bean.getFamily().getFamNo());
				query.setParameter(2, bean.getTravel().getTraNo());
				query.setParameter(3, bean.getDetMoney());
				query.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	private static final String INSERT_DetailEmp = "insert into Detail(emp_No,tra_No,det_Date,det_money) values(?,?,GETDATE(),?)";

	public boolean insert_emp(DetailVO bean) {
		boolean b = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(INSERT_DetailEmp);
			if (bean != null) {
				query.setParameter(0, bean.getEmployee().getEmpNo());
				query.setParameter(1, bean.getTravel().getTraNo());
				query.setParameter(2, bean.getDetMoney());
				query.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	private static final String INSERT_TA = "INSERT INTO TotalAmount(tra_No,emp_No,TA_money) values(?,?,?)";

	public boolean INSERT_TA(String Tra_No, int Emp_No, float TA_money) {
		boolean b = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(INSERT_TA);
			query.setParameter(0, Tra_No);
			query.setParameter(1, Emp_No);
			query.setParameter(2, TA_money);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	private static final String UPDATE_CanDate = "update Detail set det_CanDate=GETDATE(), det_canNote=? where emp_No=? and tra_No=? and det_CanDate is null";

	public boolean update(int emp_No, String det_canNote, String tra_No) {
		boolean result = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(UPDATE_CanDate);
			query.setParameter(0, det_canNote);
			query.setParameter(1, emp_No);
			query.setParameter(2, tra_No);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	private static final String UPDATE_FamCanDate = "update Detail set det_CanDate=GETDATE(), det_canNote=? where det_No=? and det_CanDate is null";

	public boolean update_FamCanDate(int det_No, String det_canNote) {
		boolean result = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(UPDATE_FamCanDate);
			query.setParameter(0, det_canNote);
			query.setParameter(1, det_No);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	private static final String UPDATE_emp_Sub = "update Employee set emp_Sub=1, emp_SubTra=NULL where emp_No=?";

	public boolean UPDATE_emp_Sub(int Emp_No) {
		boolean b = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(UPDATE_emp_Sub);
			query.setParameter(0, Emp_No);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	private static final String UPDATE_emp_SubTra = "update Employee set emp_Sub=0, emp_SubTra=? where emp_No=?";

	public boolean UPDATE_emp_SubTra(String Tra_No, int Emp_No) {
		boolean b = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(UPDATE_emp_SubTra);
			query.setParameter(0, Tra_No);
			query.setParameter(1, Emp_No);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	private static final String UPDATE_empData = "update Employee set emp_name=?, emp_Phone=?, emp_Sex=?, emp_ID=?, emp_Bdate=?, emp_Eat=?, emp_Ben=?, emp_BenRel=?, emp_Emg=?, emp_EmgPhone=?, emp_Note=?  where emp_No=?";

	public boolean UPDATE_empData(EmployeeVO bean) {
		boolean b = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(UPDATE_empData);
			query.setParameter(0, bean.getEmpName());
			query.setParameter(1, bean.getEmpPhone());
			query.setParameter(2, bean.getEmpSex());
			query.setParameter(3, bean.getEmpId());
			query.setParameter(4, bean.getEmpBdate());
			query.setParameter(5, bean.getEmpEat());
			query.setParameter(6, bean.getEmpBen());
			query.setParameter(7, bean.getEmpBenRel());
			query.setParameter(8, bean.getEmpEmg());
			query.setParameter(9, bean.getEmpEmgPhone());
			query.setParameter(10, bean.getEmpNote());
			query.setParameter(11, bean.getEmpNo());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	private static final String UPDATE_famData = "update Family set fam_Rel=?,fam_Name=?,fam_Phone=?,fam_Sex=?,fam_Id=?,fam_Bdate=?,fam_Eat=?,fam_Car=?,fam_Bady=?,fam_kid=?,fam_Dis=?,fam_Mom=?,fam_Ben=?,fam_BenRel=?,fam_Emg=?,fam_EmgPhone=?,fam_Note=? where fam_No=?";

	public boolean UPDATE_famData(FamilyVO bean) {
		boolean b = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(UPDATE_famData);
			query.setParameter(0, bean.getFamRel());
			query.setParameter(1, bean.getFamName());
			query.setParameter(2, bean.getFamPhone());
			query.setParameter(3, bean.getFamSex());
			query.setParameter(4, bean.getFamId());
			query.setParameter(5, bean.getFamBdate());
			query.setParameter(6, bean.getFamEat());
			query.setParameter(7, bean.isFamCar());
			query.setParameter(8, bean.isFamBady());
			query.setParameter(9, bean.isFamKid());
			query.setParameter(10, bean.isFamDis());
			query.setParameter(11, bean.isFamMom());
			query.setParameter(12, bean.getFamBen());
			query.setParameter(13, bean.getFamBenRel());
			query.setParameter(14, bean.getFamEmg());
			query.setParameter(15, bean.getFamEmgPhone());
			query.setParameter(16, bean.getFamNote());
			query.setParameter(17, bean.getFamNo());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	private static final String UPDATE_TA = "update TotalAmount set TA_money=? where emp_No=? and tra_No=?";

	public boolean Update_TA(float TA_money, int Emp_No, String Tra_No) {
		boolean b = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(UPDATE_TA);
			query.setParameter(0, TA_money);
			query.setParameter(1, Emp_No);
			query.setParameter(2, Tra_No);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

	private static final String DELETE_TA = "delete from TotalAmount where tra_No=? and emp_No=?";

	public boolean DELETE_TA(String Tra_No, int Emp_No) {
		boolean b = true;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(DELETE_TA);
			query.setParameter(0, Tra_No);
			query.setParameter(1, Emp_No);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			b = false;
		}
		return b;
	}

}