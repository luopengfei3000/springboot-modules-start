package com.example.springbootjsp.entity;

public class ConfigInfo {
	private static final long serialVersionUID = 1L;
	private String id;
	private String configCode;
	private String configName;
	private String validFlag;
	private String note;

	private int configCount;

	public int getConfigCount() {
		return configCount;
	}

	public void setConfigCount(int configCount) {
		this.configCount = configCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}