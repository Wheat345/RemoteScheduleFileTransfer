package com.linxi.FileTransfer.TransferActionImp;
/*
 * This class includes all action method about remotely tranfer files.
 * 
 * 
 * **/
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linxi.FileTransfer.TransferAction.TransferAction;
import com.linxi.FileTransfer.model.TransferContext;
import com.linxi.FileTransfer.utils.TransferContextGenerator;
@Component
public class TransferActionImp implements TransferAction {
	private static final Logger logger = LoggerFactory.getLogger(TransferActionImp.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH");
	@Autowired
	TransferContextGenerator transferContextGenerator;

	@Override
	public boolean copy(String sourceHost, String sourcePath, String destinationHost, String destinationPath) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String destinationPathforEachHost = destinationPath+"/"+sourceHost+"/"+sdf.format(timestamp)+"/";
		String[] cmd = new String[]{"./bin/scp.sh", sourceHost, sourcePath, destinationHost, destinationPathforEachHost};
		try {
			int returnVaule = Runtime.getRuntime().exec(cmd).waitFor();
			if(returnVaule != 0) {
				logger.error("Host "+sourceHost+" copy action failed ");
				return false;
			}
			while(returnVaule == 0) {
				logger.info("copy files from "+sourceHost+" is fishished!");
				break;
			}
			return true;
			
		} catch (IOException e) {
			logger.error("Copy I/O issue! ", e);
		} catch (InterruptedException e) {
			logger.error("Copy Interrupted issue! ", e);
		}
		return false;		
	}

	@Override
	public void delete(String sourceHost, String sourcePath) {
		String[] cmd = new String[]{"./bin/rm.sh", sourceHost, sourcePath};
		try {
			int returnVaule = Runtime.getRuntime().exec(cmd).waitFor();
			if(returnVaule != 0) {
				logger.error("Host "+sourceHost+" delete action failed ");
			}
			while(returnVaule == 0) {
				logger.info("Delete files from "+sourceHost+" is fishished!");
				break;
			}
			
		} catch (IOException e) {
			logger.error("Deletaion I/O issue! ", e);
		} catch (InterruptedException e) {
			logger.error("Deletation Interrupted issue! ", e);
		}	
	}
	@Override
	public void process() {
		TransferContext transferContext = transferContextGenerator.process();
		ArrayList<String> sourceHostslist = transferContext.getSourceHostsInfo();
		String sourcePath = transferContext.getSourceDirectoryPath();
		String destinationHost = transferContext.getDestinationHostInfo();
		String destinationPaths = transferContext.getDestinationPaths();
		ExecutorService executorService = Executors.newFixedThreadPool(transferContext.getSourceHostsInfo().size());
        for (int i = 0; i < transferContext.getSourceHostsInfo().size(); i++) {
			int currentHost = i;
			executorService.execute(new Runnable() {
			    public void run() {
			        if(copy(sourceHostslist.get(currentHost), sourcePath, destinationHost, destinationPaths)) {
				        delete(sourceHostslist.get(currentHost), sourcePath);			        	
			        }
			    }		
			});
	      }
        executorService.shutdown();
	    while (!executorService.isTerminated()) {
	    }
	    logger.info("Finished all worker threads");

		
	}
	
}










