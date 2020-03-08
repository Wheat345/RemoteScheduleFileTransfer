package com.linxi.FileTransfer;
/*
 * entry point of remotely tranfer files application
 * 
 * 
 * **/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FileTransferApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(FileTransferApplication.class);  
	public static void main(String[] args) {
    	logger.info("Starting File Transfer application");
		SpringApplication.run(FileTransferApplication.class, args);
	}
}
	