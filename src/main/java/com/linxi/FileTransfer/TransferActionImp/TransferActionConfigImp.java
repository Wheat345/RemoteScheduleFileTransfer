package com.linxi.FileTransfer.TransferActionImp;
/*
 * This class is properties collect class that read transfer file info from properties file
 * 
 * 
 * **/
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.linxi.FileTransfer.TransferAction.TransferActionConfig;

@Component
public class TransferActionConfigImp implements TransferActionConfig{
	private Properties properties = new Properties();
	@Value("${infoLocation}")
	private String infoLocation;
	public TransferActionConfigImp(){	
	}


	@Override
	public Properties loadProperties() {
		String location = infoLocation+"/conf/fileTransfer.properties";
        try (InputStream input = new FileInputStream(location)) {
        	properties.load(input);
        } catch (IOException io) {
            io.printStackTrace();
        }
		return properties;
	}
	
}
