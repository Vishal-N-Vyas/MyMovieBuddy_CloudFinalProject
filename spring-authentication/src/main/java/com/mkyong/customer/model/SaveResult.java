package com.mkyong.customer.model;

import java.util.List;

public class SaveResult {
	private String successMsg;
	private List<String> listSaved;
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public List<String> getListSaved() {
		return listSaved;
	}
	public void setListSaved(List<String> listSaved) {
		this.listSaved = listSaved;
	}
	
	
}
