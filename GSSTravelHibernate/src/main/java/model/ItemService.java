package model;

import java.util.ArrayList;
import java.util.List;


public class ItemService {
	private ItemDAO itemDAO=new ItemDAO();
	
	public List<ItemVO> getFee(String tra_No)  {
		return itemDAO.getFee(tra_No);
	}

	public List<ItemVO> getRoomMoney(String tra_No){
		return itemDAO.getRoomMoney(tra_No);
	}
	
	public List<ItemVO> select(ItemVO bean) {
		List<ItemVO> result = null;
		ItemIdVO pk = new ItemIdVO();
		if (bean != null && pk.getItemNo() != 0) {
			List<ItemVO> temp = itemDAO.select();
			if (temp != null) {
				result = new ArrayList<ItemVO>();
				result.add((ItemVO) temp);
			}
		} else {
			result = itemDAO.select();
		}
		return result;
	}
}
