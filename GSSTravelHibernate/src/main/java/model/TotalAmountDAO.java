package model;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class TotalAmountDAO{
	public TotalAmountDAO() {
		super();
	}
	private static final String select = "from TotalAmountVO where emp_No=? and tra_No=?";
	private static final String counts="select count(emp_No)as count from TotalAmount where emp_No=?";
	private static final String selectAll ="from TotalAmountVO where emp_No=?";
	private static final String insertTotalAmount ="insert into TotalAmount (tra_No,emp_No,TA_money) values(?,?,?)";
	private static final String deleteTotalAmount ="Delete from TotalAmount where emp_No=? and tra_No=?";
	private static final String selectTa_money ="from TotalAmountVO where emp_No=? order by TA_money";
	
	public TotalAmountVO select(Integer emp_No,String tra_NO){
		TotalAmountVO totalAmountVo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(select);
			query.setParameter(0, emp_No);
			query.setParameter(1, tra_NO);
			List<TotalAmountVO>totalAmountVos=query.list();
			for(TotalAmountVO Vo:totalAmountVos){
				totalAmountVo=Vo;
			}
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return totalAmountVo;
	}

	public int counts(String emp_No) {
		int count = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(counts);
			query.setParameter(0, emp_No);
			count=(int)query.list().get(0);		
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return count;
	}

	public boolean selectAll(String emp_No) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(selectAll);
			query.setParameter(0, emp_No);	
			int a=query.list().size();		
			if(a!=0){
				return false;
			}
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return true;		
	}

	public void insertTotalAmount( String tra_No, int emp_No,Float TA_money) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {		
			SQLQuery query = session.createSQLQuery(insertTotalAmount);
			query.setParameter(0, tra_No);                                  
			query.setParameter(1, emp_No);
			query.setParameter(2, TA_money);
			query.executeUpdate();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}		
	}	

	public void deleteTotalAmount( String emp_No,String tra_No ) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {		
			SQLQuery query = session.createSQLQuery(deleteTotalAmount);
			query.setParameter(0, emp_No);                                  
			query.setParameter(1, tra_No);
			query.executeUpdate();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}	
	}

	public TotalAmountVO selectTa_money(String emp_No){
		TotalAmountVO totalAmountVo =new TotalAmountVO();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(selectTa_money);
			query.setParameter(0, emp_No);
			List<TotalAmountVO> totalAmountVos = query.list();
			if(totalAmountVos.size()!=0){		
				for(TotalAmountVO vo:totalAmountVos){
					totalAmountVo.setTaMoney(vo.getTaMoney());
					totalAmountVo.setTravel(vo.getTravel());
				}				
			}			
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return totalAmountVo;
	}
	
	//雅婷
	private static final String UPDATE_TOTALAMOUNT_FOR_EMP_NO = "update TotalAmount set TA_money=? where emp_No=? and tra_No=?";

	public boolean update(double TA_money, String tra_No, int emp_No) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery(UPDATE_TOTALAMOUNT_FOR_EMP_NO);

			query.setParameter(0, TA_money);
			query.setParameter(1, emp_No);
			query.setParameter(2, tra_No);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
