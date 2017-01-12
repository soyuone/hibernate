package com.song.hibernate.web;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.UserInfoPO;

/**
 * <p>
 * Title: hibernate_[配置文件]_[Properties]
 * </p>
 * <p>
 * Description: [hibernate配置文件置于Properties文件中]
 * </p>
 * 
 * @author songyushi
 * @version $Revision$ 2017年1月12日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@SuppressWarnings("deprecation")
public class HibernatePropertiesMain {
	/*
	 * Hibernate较之另一个ORM框架MyBatis，其更具有面向对象的特征。受Hibernate影响，Java EE规范抛弃了传统的Entity
	 * EJB规范，改为使用JPA作为持久层解决方案，而JPA实体完全可以当成Hibernate PO.使用 面向对象的编程语言和底层关系数据库的发展不协调，催生出了ORM框架，它可以把关系数据库包装成面向对象的模型.
	 * JPA规范是一种ORM规范，并不提供任何ORM实现，而ORM框架则负责为这些接口提供实现.Hibernate底层依然是基于JDBC
	 */
	public static void main(String[] args) {
		// 可以多次调用addAnnotatedClass添加持久化类
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class);

		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		// 用Configuration实例创建SessionFactory实例
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		// 创建Session,PO对象只有在Session的管理下才可完成数据库访问
		Session sess = sf.openSession();
		// 开始事务
		Transaction tx = sess.beginTransaction();

		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setUsername("刘二");
		userInfoPO.setUserpassword("111111");
		userInfoPO.setLoginaccount("admin");
		userInfoPO.setJobnumber("1111");

		// 保存消息
		sess.save(userInfoPO);
		// 提交事务
		tx.commit();
		// 关闭Session
		sess.close();
		sf.close();
	}
}
