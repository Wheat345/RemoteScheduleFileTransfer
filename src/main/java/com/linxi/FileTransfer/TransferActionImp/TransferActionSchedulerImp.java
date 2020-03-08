package com.linxi.FileTransfer.TransferActionImp;
/*
 * This class is to setup schedule about running this application
 * 
 * 
 * **/
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.linxi.FileTransfer.FileTransferApplication;
import com.linxi.FileTransfer.TransferAction.TransferActionScheduler;


@Component
public class TransferActionSchedulerImp implements TransferActionScheduler {
	private static final Logger logger = LoggerFactory.getLogger(FileTransferApplication.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Autowired
    TransferActionImp transferActionImp;

	//@Scheduled(cron = "0 0 */2 * * *") //every two hours
	//@Scheduled(cron = "0 * * * * *") //every one minute
	@Scheduled(cron = "0 0 2 * * *") //execute everyday at 2am
    public void scheduleTaskWithFixedRate() {
    	logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
		transferActionImp.process();
    }
}
