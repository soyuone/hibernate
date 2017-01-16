package com.song.hibernate.web.embeddable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * <p>
 * Title: hibernate_[映射组件属性]_[模块名]
 * </p>
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author SO
 * @version $Revision$ 2017年1月15日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@Entity
@Table(name = "person_inf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@BatchSize(size = 10)
public class Person {

	private Integer id;

	private Integer age;

	private Name name;// 持久化类的属性并不是基本数据类型，是一个复合类型的对象

	public Person() {
	}

	public Person(Integer id, Integer age, Name name) {
		this.id = id;
		this.age = age;
		this.name = name;
	}

	@Id
	@Column(name = "person_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

}
