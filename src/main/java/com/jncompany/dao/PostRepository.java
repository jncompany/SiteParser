package com.jncompany.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jncompany.vo.PostVo;

public interface PostRepository extends PagingAndSortingRepository<PostVo, Long>{

	List<PostVo> findBySite(String site);
	
	long count();
}
