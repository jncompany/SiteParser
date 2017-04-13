package com.jncompany;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jncompany.service.ParseService;

@Component 
public class CronJob {
	
	@Autowired
	private ParseService ps;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	
	//60초마다 실행
	@Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
		System.out.println("The time is now {}"+ dateFormat.format(new Date()));
		ps.processParse();
    }
		
}
