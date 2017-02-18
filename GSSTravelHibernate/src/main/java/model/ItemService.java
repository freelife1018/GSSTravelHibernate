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
	
	//柯
	public List<ItemVO> select(ItemVO bean) {
		List<ItemVO> result = itemDAO.select();
		return result;
	}
		
	public List<ItemVO> selectOne(String no) {
		List<ItemVO> result = itemDAO.selectOne(no);
		return result;
	}
}
