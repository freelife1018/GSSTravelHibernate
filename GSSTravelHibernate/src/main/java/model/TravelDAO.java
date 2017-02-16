package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

public class TravelDAO {
	public TravelDAO() {
		super();
	}

	private static final String selectAll = "from TravelVO";
	private static final String select = "from TravelVO where traNo=?";
	private static final String SEARCH_BY_NO_NAME = "from TravelVO where tra_NO like ? and tra_Name like ? ";
	
	public List<TravelVO> getAll() {
		List<TravelVO> result = new ArrayList<>();
		DetailDAO detailDAO = new DetailDAO();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(selectAll);
			List<TravelVO> travels = query.list();
			for (TravelVO travel : travels) {
				travel.setSignInTotal(detailDAO.tra_count(travel.getTraNo()));
				result.add(travel);
			}
		} catch (RuntimeException ex) {
			throw ex;
		}
		return result;
	}

	public TravelVO getAll(String tra_No) {
		List<TravelVO> travels;
		TravelVO result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(select);
			query.setParameter(0, tra_No);
			travels = query.list();
			result = travels.get(0);
		} catch (RuntimeException ex) {
			throw ex;
		}
		return result;
	}

	public Map<String, String> selectTra_NoTra_Beg() {
		Map<String, String> result = new HashMap<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(selectAll);
			List<TravelVO> travels = query.list();
			for (TravelVO travel : travels) {
				result.put(travel.getTraNo(), travel.getTraBeg().toString());
			}
		} catch (RuntimeException ex) {
			throw ex;
		}
		return result;
	}

	public Map<String, String> selectTra_NoTra_End() {
		Map<String, String> result = new HashMap<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(selectAll);
			List<TravelVO> travels = query.list();
			for (TravelVO travel : travels) {
				result.put(travel.getTraNo(), travel.getTraEnd().toString());
			}
		} catch (RuntimeException ex) {
			throw ex;
		}
		return result;
	}

	public List<String> selectTra_No() {
		List<String> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(selectAll);
			List<TravelVO> travels = query.list();
			for (TravelVO travel : travels) {
				result.add(travel.getTraNo());
			}
		} catch (RuntimeException ex) {
			throw ex;
		}
		return result;
	}

	public List<TravelVO> search(String no, String name) {
		List<TravelVO> result = null;
		if (no == null) {
			no = "";
		}
		if (name == null) {
			name = "";
		}
		Session ses = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = ses.createQuery(SEARCH_BY_NO_NAME);
		query.setParameter(0, "%" + no + "%");
		query.setParameter(1, "%" + name + "%");
		result = query.list();
		return result;
	}
	
	public List<TravelVO> select(String no) {
		List<TravelVO> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			result.add((TravelVO) session.get(TravelVO.class, no));
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public List<TravelVO> select() {
		List<TravelVO> result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery("FROM TravelVO");
			result = query.list();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
}
