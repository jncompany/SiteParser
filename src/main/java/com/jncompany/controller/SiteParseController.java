package com.jncompany.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jncompany.dao.PostRepository;
import com.jncompany.service.ClienParseService;
import com.jncompany.service.PpomParseService;
import com.jncompany.vo.PostVo;

@RestController
public class SiteParseController {
	
	private static final Logger log = LoggerFactory.getLogger(SiteParseController.class);
	
	@Autowired
	private PostRepository dao;
	@Autowired
	private ClienParseService cp;
	@Autowired
	private PpomParseService ps;
	
	@CrossOrigin
	@RequestMapping("/list")
	public List<PostVo> list(PostVo post){
		return (List<PostVo>) dao.findAll();
	}
	
	@RequestMapping("/add")
	public PostVo add(PostVo post) {
		return  dao.save(post);
	}
	
	@RequestMapping("/parseClien")
	public void parseClien(HttpServletResponse response) throws IOException{
		
		dao.deleteAll();
		cp.processParse();
		response.sendRedirect("/list");
	}
	
	@RequestMapping("/parsePpom")
	public void parsePpom(HttpServletResponse response) throws IOException{
		
		dao.deleteAll();
		ps.processParse();
		response.sendRedirect("/list");
	}
	
	@RequestMapping("/parseAll")
	public void parse(HttpServletResponse response) throws IOException{
		
		System.out.println("cnt 1 : "+ getCurrentRowCount());
		
		dao.deleteAll();
		System.out.println("cnt 2 : "+ getCurrentRowCount());
		
		cp.processParse();
		System.out.println("cnt 3 : "+ getCurrentRowCount());
		
		ps.processParse();
		System.out.println("cnt 4 : "+ getCurrentRowCount());
		
		response.sendRedirect("/list");
	}
	
	
	public int getCurrentRowCount(){
		return ((List<PostVo>) dao.findAll()).size();
	}


}
