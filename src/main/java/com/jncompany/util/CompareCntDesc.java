package com.jncompany.util;

import java.util.Comparator;

import com.jncompany.vo.ItemVo;

public class CompareCntDesc implements Comparator<ItemVo>{
	 
	@Override
	public int compare(ItemVo o1, ItemVo o2) {
    	 return o1.getCnt() > o2.getCnt() ? -1 : o1.getCnt() < o2.getCnt() ? 1:0;
    } 

}
