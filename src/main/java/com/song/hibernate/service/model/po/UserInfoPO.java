package com.song.hibernate.service.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

//PO = POJO + 持久化注解
//PO必须在session的管理下才能同步到数据库，session由SessionFactory工厂产生，SessionFactory是数据库编译后的内存镜像，通常一个应用对应一个SessionFactory对象
//SessionFactory对象由Configuration对象生成，Configuration对象负责加载Hibernate配置文件
//SessionFactory是线程安全的，该对象可以在进程或集群的级别上为那些事务之间可重用的数据提供可选的二级缓存

//Session是程序与持久层之间交互操作的一个单线程对象，生存期很短，底层封装了JDBC连接，也是Transaction的工厂
//Session对象持有必选的一级缓存，在显式进行flush前 所有持久化操作的数据都在缓存中的Session对象处
//在某些情况下，一个Session内可能包含多个Transaction对象，虽然事务操作是可选的，但所有持久化操作都应在事务管理下进行，即使是只读操作
//PO的三种状态：瞬态，持久化，脱管状态

//Entity声明该类是一个Hibernate的持久化类
//Table指定该类映射的表
//Cache指定二级对该类启用二级缓存，缓存策略为READ_WRITE读写缓存(如果程序要求使用序列化事务的隔离级别，则不能使用这种缓存策略)
@Entity
@Table(name = "tb_userinfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

	public UserInfoPO(Integer id, String userid, String loginaccount, String userpassword, String username,
			String usersex, String jobnumber, String userphone, String usermail, String attachoffice, String userclass,
			String lastupdatetime) {
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

	// Id持久化标识，通常映射到表的主键列
	// GeneratedValue指定主键生成策略
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
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
