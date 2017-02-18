package model;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Transaction;

public class EmployeeService {
	private EmployeeDAO employeeDAO=new EmployeeDAO();
	
	public String getName(Integer emp_No){
		return employeeDAO.selectEmp_Name(emp_No);
	}
	
	public void updateEmp_Sub(boolean emp_Sub,String emp_No){
		employeeDAO.updateEmp_Sub(emp_Sub,Integer.parseInt(emp_No));
	}

	public void updateEmp_SubTra(String tra_No, String emp_No){
		employeeDAO.updateEmp_SubTra(tra_No, emp_No);
	}
	
	public EmployeeVO select(String emp_NO){
		return employeeDAO.select(Integer.parseInt(emp_NO));
	}
	
	public EmployeeVO login(int account, String pwd) {
		Transaction tx=HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
		EmployeeVO bean = employeeDAO.select(account);
		if(bean!=null) {
			if(pwd!=null && pwd.length()!=0) {
				byte[] pass = bean.getEmpPw();
				byte[] temp = pwd.getBytes();
				if(Arrays.equals(pass, temp)) {
					tx.commit();
					return bean;
				}
			}
		}
		tx.commit();
		return null;
	}
	
	//柯
	public List<EmployeeVO> selectEmp() {
		return employeeDAO.selectFineEmail();
	}
}
