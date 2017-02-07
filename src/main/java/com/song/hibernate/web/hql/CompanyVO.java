package com.song.hibernate.web.hql;

public class CompanyVO {
	private Integer id;

	private String company;

	private String jobnumber;

	public CompanyVO(Integer id, String company, String jobnumber) {
		this.id = id;
		this.company = company;
		this.jobnumber = jobnumber;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

	@Override
	public String toString() {
		return "CompanyVO [id=" + id + ", company=" + company + ", jobnumber=" + jobnumber + "]";
	}

}
