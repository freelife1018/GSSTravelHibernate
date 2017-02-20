package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class ItemDAO{

	private static final String select = "from ItemVO where tra_No=?";
	private static final String getFareMoney="from ItemVO  where tra_No = ? and item_name not like '%住宿%'";
	private static final String getRoomMoney="from ItemVO where tra_No=? and item_name like '%住宿%'";
	private static final String SELECT_ALL_STMT = "FROM ItemVO WHERE item_No=1 ORDER BY tra_No";//柯
	private static final String SELECT_ONE_STMT = "FROM ItemVO WHERE item_No=1 AND tra_No=?";//柯
	
	public ItemDAO() {
		super();
	}

	public List<ItemVO> getFee(String tra_No) {
		List<ItemVO> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(select);
			query.setParameter(0, tra_No);
			result = query.list();			
		} catch (RuntimeException ex) {
			throw ex;
		}
		return result;		
	}

	public List<ItemVO> getFareMoney(String tra_No) {
		List<ItemVO> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(getFareMoney);												
			query.setParameter(0, tra_No);
			result = query.list();			
		} catch (RuntimeException ex) {
			throw ex;
		}
		return result;	
	}

	public List<ItemVO> getRoomMoney(String tra_No) {
		List<ItemVO> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(getRoomMoney);
			query.setParameter(0, tra_No);
			result = query.list();			
		} catch (RuntimeException ex) {
			throw ex;
		}
		return result;
	}
	
	//柯
	public List<ItemVO> select() {
		List<ItemVO> result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery("FROM ItemVO WHERE item_No=1 ORDER BY tra_No");
			result = query.list();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public List<ItemVO> selectOne(String no) {
		List<ItemVO> result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery("FROM ItemVO WHERE item_No=1 AND tra_No=:no");
			query.setParameter("no", no);
			result = query.list();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
}
