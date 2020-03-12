package com.my.vo;

import java.util.Date;

public class Customer {
	
	private String id;
	private String pwd;
	private String name;
	private String gender;
	private Date date;
	private int status;
	
	public Customer() {
		
	}
		
	public Customer(String id, String pwd, String name) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
	}

	public Customer(String id, String pwd, String name, String gender, Date date, int status) {
		this(id, pwd, name);
		this.gender = gender;
		this.date = date;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", pwd=" + pwd + ", name=" + name + ", gender=" + gender + ", date=" + date
				+ ", status=" + status + "]";
	}
	
	

}
