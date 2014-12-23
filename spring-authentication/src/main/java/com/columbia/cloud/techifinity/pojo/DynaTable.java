package com.columbia.cloud.techifinity.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DynaTable {
	List<Dummy> records;
	Integer queryRecordCount;
	Integer totalRecordCount;
	
	@JsonProperty
	public List<Dummy> getRecords() {
		return records;
	}
	public void setRecords(List<Dummy> records) {
		this.records = records;
	}
	
	@JsonProperty
	public Integer getQueryRecordCount() {
		return queryRecordCount;
	}
	public void setQueryRecordCount(Integer queryRecordCount) {
		this.queryRecordCount = queryRecordCount;
	}
	
	@JsonProperty
	public Integer getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(Integer totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
}
