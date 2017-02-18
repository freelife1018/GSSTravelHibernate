package model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FineService {
	private FineDAO ifineDao = new FineDAO();

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

	public List<FinesVO> select() {
		List<FinesVO> result = ifineDao.select();
		return result;
	}

	public void insert(FinesVO bean) {
		ifineDao.insert(bean);
	}
	
	public void delete() {
		ifineDao.delete();
	}

}
