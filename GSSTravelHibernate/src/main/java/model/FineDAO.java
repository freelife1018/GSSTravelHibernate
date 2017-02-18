package model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class FineDAO {
	public static final String INSERT_STMT = "INSERT INTO Fines VALUES(?, ?)";

	public List<FinesVO> select(int day) {
		List<FinesVO> result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery("FROM FinesVO WHERE fineDates=:day");
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
			Query query = session.createQuery("FROM FinesVO ORDER BY fineDates DESC");
			result = query.list();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public void insert(FinesVO fineBean) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			SQLQuery query = session.createSQLQuery(INSERT_STMT);
			query.setParameter(0, fineBean.getFineDates());
			query.setParameter(1, fineBean.getFinePer());
			session.save(fineBean);
		} catch (Exception e) {
			throw e;
		}
	}

	public void delete() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery("DELETE FROM FinesVO");
			query.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}
}
