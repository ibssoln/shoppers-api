package com.ibssoln.shoppers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {"com.ibssoln.shoppers.*", "com.ibssoln.shoppers.soap.inbound.inventory.config", "com.ibssoln.shoppers.soap.inbound.inventory.endpoint", "com.ibssoln.shoppers.soap.outbound.inventory.config", "com.ibssoln.shoppers.soap.outbound.inventory.client"})
public class ShoppersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppersApplication.class, args);
	}

	@Bean(name = "globalBatchExecutor")
	public Executor taskExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(50);
		//keep the queue capicity to the minimum as a new thread is not created & used until the existing queue is in full.
		executor.setQueueCapacity(1);
		executor.setThreadNamePrefix("globalBatchExecutor-");
		executor.initialize();
		return executor;
	}

}
