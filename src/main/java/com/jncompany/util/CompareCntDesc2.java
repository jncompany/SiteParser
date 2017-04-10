package com.jncompany.util;

import java.util.Comparator;

import com.jncompany.vo.PostVo;

public class CompareCntDesc2 implements Comparator<PostVo>{
	 
	@Override
	public int compare(PostVo o1, PostVo o2) {
    	 return o1.getCnt() > o2.getCnt() ? -1 : o1.getCnt() < o2.getCnt() ? 1:0;
    } 

}
