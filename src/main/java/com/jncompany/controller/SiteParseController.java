package com.jncompany.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jncompany.dao.PostRepository;
import com.jncompany.service.ClienPaseService;
import com.jncompany.service.PpomParseService;
import com.jncompany.vo.PostVo;

@RestController
public class SiteParseController {
	
	private static final Logger log = LoggerFactory.getLogger(SiteParseController.class);
	
	@Autowired
	private PostRepository dao;
	
	@Autowired
	private ClienPaseService cp;
	@Autowired
	private PpomParseService ps;
	
	
	@RequestMapping("/list")
	public List<PostVo> list(PostVo post){

		System.out.println("/////////////////////////////////");
		
		List<PostVo> list =  (List<PostVo>) dao.findAll();
		
		for(PostVo pv : list){
			System.out.println("pv : "+pv.toString());
		}
		
		return list;
	}
	
	@RequestMapping("/add")
	public PostVo add(PostVo post) {

		PostVo helloData = dao.save(post);

		return helloData;
	}
	
	@RequestMapping("/parse")
	public String parse(){
		dao.deleteAll();
		cp.processParse();
		ps.processParse();
		
		return "redirect:/list";
	}


}
