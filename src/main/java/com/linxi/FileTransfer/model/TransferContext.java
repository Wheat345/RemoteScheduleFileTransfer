package com.linxi.FileTransfer.model;
/*
 * This class is pojo class that introduce all configurable properties
 * 
 * 
 * **/
import java.util.ArrayList;

public class TransferContext {

	private ArrayList<String> sourceHostsInfo;
	private String sourceDirectoryPath;
	private String destinationHostInfo;
	private String destinationPaths;
	public ArrayList<String> getSourceHostsInfo() {
		return sourceHostsInfo;
	}
	public void setSourceHostsInfo(ArrayList<String> sourceHostsInfo) {
		this.sourceHostsInfo = sourceHostsInfo;
	}
	public String getSourceDirectoryPath() {
		return sourceDirectoryPath;
	}
	public void setSourceDirectoryPath(String sourceDirectoryPath) {
		this.sourceDirectoryPath = sourceDirectoryPath;
	}
	public String getDestinationHostInfo() {
		return destinationHostInfo;
	}
	public void setDestinationHostInfo(String destinationHostInfo) {
		this.destinationHostInfo = destinationHostInfo;
	}
	public String getDestinationPaths() {
		return destinationPaths;
	}
	public void setDestinationPaths(String destinationPaths) {
		this.destinationPaths = destinationPaths;
	}

	
	
	
}
