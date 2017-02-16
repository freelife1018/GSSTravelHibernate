package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class FamilyDAO {
	public FamilyDAO() {
		super();
	}
	private static final String selectfam_Rel = "from FamilyVO where emp_No=? and fam_Name=?";
	private static final String select="from FamilyVO where emp_No=?";
	private static final String selectfam_No="from FamilyVO where fam_Name=?";
	public static final String INSERT_STMT = "INSERT INTO Fines VALUES(?, ?)";
	
	public String selectfam_Rel(String emp_No, String fam_Name) {
		String fam_Rel = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(selectfam_Rel);
			query.setParameter(0, emp_No);
			query.setParameter(1, fam_Name);
			List<FamilyVO>familys=query.list();
			for(FamilyVO family:familys){
				fam_Rel=family.getFamRel();
			}
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return fam_Rel;
	}

	public List<FamilyVO> selectFam(Integer emp_No, String tra_No) {
		List<FamilyVO> familys = new ArrayList<>();
		DetailDAO detailDAO = new DetailDAO();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(select);
			query.setParameter(0, emp_No);
			List<FamilyVO> family = query.list();
			for (FamilyVO fam : family) {
				if (detailDAO.selectFam_No(fam.getFamNo(), tra_No)) {
					fam.setChecked("checked");
				} else {
					fam.setChecked("");
				}
				familys.add(fam);
			}
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return familys;
	}

	public Integer selectfam_No(String fam_Name) {
		Integer fam_No = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(selectfam_No);
			query.setParameter(0, fam_Name);
			List<FamilyVO>familys=query.list();
			if(familys!=null){
				for(FamilyVO family:familys){
					fam_No=family.getFamNo();
				}
			}
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return fam_No;
	}
	//皓元
	public List<FinesVO> select(int day) {
		List<FinesVO> result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery("FROM FineVO WHERE fineDates=:day");
			query.setParameter("day", day);
			result = query.list();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public List<FinesVO> select() {
		List<FinesVO> result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery("FROM FineVO ORDER BY fineDates DESC");
			result = query.list();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public FinesVO insert(FinesVO fineBean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		FinesVO result = null;
		try {
			SQLQuery query = session.createSQLQuery(INSERT_STMT);
			query.setParameter(0, fineBean.getFineDates());
			query.setParameter(1, fineBean.getFinePer());
			session.save(fineBean);
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public void delete(int day) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery("DELETE FROM FineVO");
			query.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
}
