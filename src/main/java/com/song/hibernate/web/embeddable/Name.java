package com.song.hibernate.web.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Parent;

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
// @Embeddable和@Entity类似，不需要指定任何属性，其修饰的类将作为持久化类的组件使用
@Embeddable
public class Name {

	private String first;

	private String last;

	@Parent
	private Person owner;// @Parent用于指明owner属性不是普通属性，而是包含Name组件的Person实体

	public Name() {
	}

	public Name(String first, String last) {
		this.first = first;
		this.last = last;
	}

	@Column(name = "person_firstname")
	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	@Column(name = "person_lastname")
	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}
