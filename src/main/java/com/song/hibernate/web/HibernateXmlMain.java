package com.song.hibernate.web;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.UserInfoPO;

@SuppressWarnings("deprecation")
public class HibernateXmlMain {

	public static void main(String[] args) {
		// Hibernate配置文件的默认文件名为hibernate.cfg.xml,当程序调用Configuration对象的configure()方法时默认加载此文件
		// 实例化Configuration，不带参数的configure（）方法默认加载hibernate.cfg.xml，若传入参数则加载指定文件
		Configuration conf = new Configuration().configure();

		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		// 用Configuration实例创建SessionFactory实例
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		// 创建Session,PO对象只有在Session的管理下才可完成数据库访问
		Session sess = sf.openSession();
		// 开始事务
		Transaction tx = sess.beginTransaction();

		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setUsername("刘大");
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
