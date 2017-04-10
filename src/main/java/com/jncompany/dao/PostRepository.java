package com.jncompany.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jncompany.vo.PostVo;

public interface PostRepository extends CrudRepository<PostVo, Long>{

	List<PostVo> findBySite(String site);
}
