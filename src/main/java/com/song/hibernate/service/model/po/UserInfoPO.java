package com.song.hibernate.service.model.po;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Where;

//PO = POJO + 持久化注解
//PO必须在session的管理下才能同步到数据库，session由SessionFactory工厂产生，SessionFactory是数据库编译后的内存镜像，通常一个应用对应一个SessionFactory对象
//SessionFactory对象由Configuration对象生成，Configuration对象负责加载Hibernate配置文件
//SessionFactory是线程安全的，该对象可以在进程或集群的级别上为那些事务之间可重用的数据提供可选的二级缓存

//Session是程序与持久层之间交互操作的一个单线程对象，生存期很短，底层封装了JDBC连接，也是Transaction的工厂
//Session对象持有必选的一级缓存，在显式进行flush前 所有持久化操作的数据都在缓存中的Session对象处
//在某些情况下，一个Session内可能包含多个Transaction对象，虽然事务操作是可选的，但所有持久化操作都应在事务管理下进行，即使是只读操作
//PO的三种状态：瞬态，持久化，脱管状态

//Entity声明该类是一个Hibernate的持久化类
//Table指定该类映射的表(name属性默认映射表名与持久化类名相同)
//Cache指定对该类启用二级缓存，缓存策略为READ_WRITE读写缓存(如果程序要求使用序列化事务的隔离级别，则不能使用这种缓存策略)
//DynamicUpdate指定用于更新记录的update语句是否在运行时动态生成，并且只更新那些改变过的字段，该属性的默认值是false，开启该属性将导致hibernate需要更多时间生成sql语句
//Where注解的clause属性可指定一个附加的sql语句过滤条件，只要试图加载该持久化类的对象时，where条件就会生效
//BatchSize注解的size属性指定hibernate抓取集合属性或延迟加载的实例时每批抓取的实例数

@Entity
@Table(name = "tb_userinfo")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate(true)
@Where(clause = " id >= 1 ")
@BatchSize(size = 10)
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

	private Timestamp datetimetest;

	private Date datetest;

	private Time timetest;

	private Timestamp lastupdatetime;// 最后更新时间

	private String virtueValue;// 虚拟字段，测试@Formula

	private String transientValue;// 虚拟字段，测试@Transient

	private Season season;// 枚举类型

	private byte[] picture;// 测试@Lob

	public UserInfoPO() {
	}

	public UserInfoPO(Integer id, String userid, String loginaccount, String userpassword, String username,
			String usersex, String jobnumber, String userphone, String usermail, String attachoffice, String userclass,
			Timestamp lastupdatetime) {
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
	// GeneratedValue指定主键生成策略.AUTO:hibernate自动选择最适合底层数据库的主键生成策略（默认值），IDENTITY:选择自增长的主键生成策略
	// Column中name属性默认与成员变量名相同
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

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "datetimetest")
	public Timestamp getDatetimetest() {
		return datetimetest;
	}

	public void setDatetimetest(Timestamp datetimetest) {
		this.datetimetest = datetimetest;
	}

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "datetest")
	public Date getDatetest() {
		return datetest;
	}

	public void setDatetest(Date datetest) {
		this.datetest = datetest;
	}

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "timetest")
	public Time getTimetest() {
		return timetest;
	}

	public void setTimetest(Time timetest) {
		this.timetest = timetest;
	}

	@Column(name = "lastupdatetime")
	public Timestamp getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(Timestamp lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	// Formula中的英文括号必须有，内部是sql语句
	// @Where @Formula作用范围有区别
	@Formula(value = "(SELECT CONCAT(info.loginaccount,info.jobnumber) FROM tb_userinfo info where info.id = id)")
	public String getVirtueValue() {
		return virtueValue;
	}

	public void setVirtueValue(String virtueValue) {
		this.virtueValue = virtueValue;
	}

	// 在默认情况下，持久化类的所有属性会自动映射到数据表的数据列。如果在实际应用中不想持久保存某些属性，则可以考虑使用@Transient来修饰
	@Transient
	public String getTransientValue() {
		return transientValue;
	}

	public void setTransientValue(String transientValue) {
		this.transientValue = transientValue;
	}

	// EnumType.ORDINAL底层数据库保存枚举值的序号，EnumType.STRING底层数据库保存枚举值的名称
	@Enumerated(EnumType.STRING)
	@Column(name = "season")
	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	// hibernate使用@Lob来修饰大数据类型，当持久化类的属性为byte[]、Byte[]、或java.io.Serializable类型时，@Lob修饰的属性将
	// 映射为底层的Blob列，在数据库中列列类型指定为blob类型情况下 持久化对象中可以不用@Lob标注
	// 延迟加载某一列时使用@Basic(fetch = FetchType.LAZY)，等到真正需要该属性时才从底层数据库中加载数据
	@Lob
	@Column(name = "picture")
	@Basic(fetch = FetchType.LAZY)
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

}
