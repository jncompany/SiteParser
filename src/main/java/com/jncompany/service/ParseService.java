package com.jncompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jncompany.dao.PostRepository;

@Service
public class ParseService {

	@Autowired
	private PostRepository dao;
	@Autowired
	private ClienParseService cp;
	@Autowired
	private PpomParseService ps;

	public void processParse() {

		dao.deleteAll();
		
		cp.processParse();
		
		ps.processParse();
		
	}

}
