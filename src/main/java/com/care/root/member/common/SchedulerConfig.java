package com.care.root.member.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.care.root.board.service.BoardFileService;

@Configuration
//@EnableScheduling
public class SchedulerConfig {
	@Autowired BoardFileService fs;
	@Scheduled(cron="*/10 * * * * *")
	public void test01() {
		System.out.println("10초마다 실행 : "+fs);
	}
	@Scheduled(cron="0-59 * * * * *")
	public void test02() {
		System.out.println("===매초 실행===");
	}
}
