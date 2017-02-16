package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class ItemDAO{

	private static final String select = "from ItemVO where tra_No=?";
	private static final String getFareMoney="from ItemVO  where tra_No = ? and item_name not like '%住宿%'";
	private static final String getRoomMoney="from ItemVO where tra_No=? and item_name like '%住宿%'";
	private static final String SELECT_ONE_STMT = "select item_Money FROM Item WHERE item_No=? ORDER BY tra_No";
	
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

	public List<ItemVO> select() {
		List<ItemVO> result = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(SELECT_ONE_STMT);
			query.setParameter(0, 1);
			List<Double> list= query.list();
			for(Double a:list){
				ItemVO itemVO=new ItemVO();
				itemVO.setItemMoney(a);
				result.add(itemVO);
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
}
