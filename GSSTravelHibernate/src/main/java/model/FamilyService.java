package model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FamilyService {
	private FamilyDAO familyDAO=new FamilyDAO();
	private FineDAO ifineDao = new FineDAO();
	
	public List<FamilyVO> selectFam(Integer emp_No,String tra_No){
		return familyDAO.selectFam(emp_No,tra_No);
	}
	
	public JSONArray to_Json(List<FinesVO> fineBean){
		JSONArray array = new JSONArray();
		for(FinesVO v:fineBean){
			JSONObject obj = new JSONObject();
			obj.put("day", v.getFineDates());
			obj.put("percent", v.getFinePer());
			array.add(obj);
		}
		return array;
	}
	
	public List<FinesVO> select(FinesVO bean) {
		List<FinesVO> result = null;
		if (bean != null && bean.getFineDates() != 0) {
			List<FinesVO> temp = ifineDao.select(bean.getFineDates());
			if (temp != null) {
				result = new ArrayList<FinesVO>();
				result.addAll(temp);
			}
		} else {
			result = ifineDao.select();
		}
		return result;
	}

	public FinesVO insert(FinesVO bean) {
		FinesVO result = null;
		if (bean != null) {
			result = ifineDao.insert(bean);
		}
		return result;
	}
	public void delete(FinesVO bean) {
		ifineDao.delete(bean.getFineDates());
	}
}
