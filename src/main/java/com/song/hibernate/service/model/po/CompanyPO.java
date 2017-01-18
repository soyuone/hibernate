package com.song.hibernate.service.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * Title: hibernate_[HQL]_[PO层]
 * </p>
 * <p>
 * Description: [company对象PO层]
 * </p>
 * 
 * @author songyushi
 * @version $Revision$ 2017年1月18日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Entity
@Table(name = "tb_company")
public class CompanyPO {

	private Integer id;

	private String company;

	private String jobnumber;

	public CompanyPO() {
	}

	public CompanyPO(Integer id, String company, String jobnumber) {
		this.id = id;
		this.company = company;
		this.jobnumber = jobnumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "company")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "jobnumber")
	public String getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

	@Override
	public String toString() {
		return "CompanyPO [id=" + id + ", company=" + company + ", jobnumber=" + jobnumber + "]";
	}

}
