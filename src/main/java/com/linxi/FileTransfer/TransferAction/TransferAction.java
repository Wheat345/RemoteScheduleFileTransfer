package com.linxi.FileTransfer.TransferAction;
/*
 * This interface includes all action method about remotely tranfer files.
 * 
 * 
 * **/

public interface TransferAction {
	void process();
	boolean copy(String sourceHost, String sourcePath, String destinationHost, String destinationPath);
	void delete(String sourceHost, String sourcePath);	
}
