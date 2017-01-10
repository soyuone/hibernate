package com.song.hibernate.web;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.UserInfoPO;

@SuppressWarnings("deprecation")
public class HibernatePropertiesMain {

	public static void main(String[] args) {
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class);
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		//用Configuration实例创建SessionFactory实例
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		//创建Session,PO对象只有在Session的管理下才可完成数据库访问
		Session sess = sf.openSession();
		//开始事务
		Transaction tx = sess.beginTransaction();
		
		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setUsername("刘二");
		userInfoPO.setUserpassword("111111");
		userInfoPO.setLoginaccount("admin");
		userInfoPO.setJobnumber("1111");
		
		//保存消息
		sess.save(userInfoPO);
		//提交事务
		tx.commit();
		//关闭Session
		sess.close();
		sf.close();
	}
}
