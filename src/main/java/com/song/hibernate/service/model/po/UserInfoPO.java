package com.song.hibernate.service.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_userinfo")
public class UserInfoPO {

	private Integer id;// 主键id

	private String userid;// 用户id

	private String loginaccount;// 用户登录名

	private String userpassword;// 用户密码

	private String username;// 用户姓名

	private String usersex;// 性别

	private String jobnumber;// 工号

	private String userphone;// 联系电话

	private String usermail;// 电子邮箱

	private String attachoffice;// 所属部门

	private String userclass;// 用户级别

	private String lastupdatetime;// 最后更新时间

	public UserInfoPO() {
	}

	public UserInfoPO(Integer id, String userid, String loginaccount,
			String userpassword, String username, String usersex,
			String jobnumber, String userphone, String usermail,
			String attachoffice, String userclass, String lastupdatetime) {
		this.id = id;
		this.userid = userid;
		this.loginaccount = loginaccount;
		this.userpassword = userpassword;
		this.username = username;
		this.usersex = usersex;
		this.jobnumber = jobnumber;
		this.userphone = userphone;
		this.usermail = usermail;
		this.attachoffice = attachoffice;
		this.userclass = userclass;
		this.lastupdatetime = lastupdatetime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userid")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "loginaccount")
	public String getLoginaccount() {
		return loginaccount;
	}

	public void setLoginaccount(String loginaccount) {
		this.loginaccount = loginaccount;
	}

	@Column(name = "userpassword")
	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "usersex")
	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	@Column(name = "jobnumber")
	public String getJobnumber() {
		return jobnumber;
	}

	public void setJobnumber(String jobnumber) {
		this.jobnumber = jobnumber;
	}

	@Column(name = "userphone")
	public String getUserphone() {
		return userphone;
	}

	public void setUserphone(String userphone) {
		this.userphone = userphone;
	}

	@Column(name = "usermail")
	public String getUsermail() {
		return usermail;
	}

	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}

	@Column(name = "attachoffice")
	public String getAttachoffice() {
		return attachoffice;
	}

	public void setAttachoffice(String attachoffice) {
		this.attachoffice = attachoffice;
	}

	@Column(name = "userclass")
	public String getUserclass() {
		return userclass;
	}

	public void setUserclass(String userclass) {
		this.userclass = userclass;
	}

	@Column(name = "lastupdatetime")
	public String getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

}
