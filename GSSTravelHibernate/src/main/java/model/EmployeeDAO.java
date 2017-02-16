package model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class EmployeeDAO {
	public EmployeeDAO() {
		super();
	}

	private static final String select = "from EmployeeVO where emp_No=?";
	private static final String updateEmp_Sub = "update EmployeeVO set emp_Sub=? where emp_No=?";
	private static final String updateEmp_SubTra = "update EmployeeVO set emp_SubTra = ? where emp_No=?";

	public String selectEmp_Name(Integer emp_No) {
		String name = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(select);
			query.setParameter(0, emp_No);
			EmployeeVO employee = (EmployeeVO) query.list().get(0);
			name = employee.getEmpName();
		} catch (RuntimeException ex) {
			throw ex;
		}
		return name;
	}

	public Integer selectEmp_Sub(String emp_No) {
		int Emp_Sub = 0;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(select);
			query.setParameter(0, emp_No);
			List<EmployeeVO> employees = query.list();
			for (EmployeeVO employee : employees) {
				if (employee.isEmpSub()) {
					Emp_Sub = 1;
				}
			}
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return Emp_Sub;
	}// 1未使用補助金0以使用補助金

	public void updateEmp_Sub(boolean emp_Sub, Integer emp_No) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(updateEmp_Sub);
			query.setParameter(0, emp_Sub);
			query.setParameter(1, emp_No);
			query.executeUpdate();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	public EmployeeVO select(Integer emp_No) {
		EmployeeVO employee = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(select);
			query.setParameter(0, emp_No);
			List<EmployeeVO> employees = query.list();
			for (EmployeeVO vo : employees) {
				return vo;
			}
		} catch (RuntimeException ex) {
			throw ex;
		}
		return employee;
	}

	public void updateEmp_SubTra(String tra_No, String emp_No) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Query query = session.createQuery(updateEmp_SubTra);
			query.setParameter(0, tra_No);
			query.setParameter(1, emp_No);
			query.executeUpdate();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}

	public List<EmployeeVO> selectFineEmail() {
		List<EmployeeVO> result = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			result = new ArrayList<EmployeeVO>();
			Query query = session.createQuery("FROM EmployeeVO");
			result = query.list();
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

}