package model;

import java.util.List;

public class FamilyService {
	private FamilyDAO familyDAO=new FamilyDAO();
	
	public List<FamilyVO> selectFam(Integer emp_No,String tra_No){
		return familyDAO.selectFam(emp_No,tra_No);
	}
}
