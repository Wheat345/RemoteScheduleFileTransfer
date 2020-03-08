package com.linxi.FileTransfer.utils;
/*
 * This class is to get initialed properties class
 * 
 * 
 * **/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linxi.FileTransfer.TransferActionImp.TransferActionConfigImp;
import com.linxi.FileTransfer.model.TransferContext;
@Component
public class TransferContextGenerator {
	private TransferContext transferContext;
	@Autowired
	TransferActionConfigImp transferActionConfigImp;
	
	public TransferActionConfigImp getTransferActionConfigImp() {
		return transferActionConfigImp;
	}

	public void setTransferActionConfigImp(TransferActionConfigImp transferActionConfigImp) {
		this.transferActionConfigImp = transferActionConfigImp;
	}

	public TransferContextGenerator(){

	}
	
	//mapping properties to context
	public TransferContext process() {
		Properties prop = transferActionConfigImp.loadProperties();
		transferContext = new TransferContext();
		transferContext.setSourceHostsInfo(new ArrayList<String>(Arrays.asList(prop.getProperty("sourceHostsInfo").split("\\s*,\\s*"))));
		transferContext.setSourceDirectoryPath(prop.getProperty("sourceDirectoryPath"));
		transferContext.setDestinationHostInfo(prop.getProperty("destinationHostInfo"));
		//TODO: enrich the destination path, so far only just source host name
		transferContext.setDestinationPaths(prop.getProperty("destinationDirectoryPath"));
		return transferContext;
	}

	public TransferContext getTransferContext() {
		return transferContext;
	}
	
}
