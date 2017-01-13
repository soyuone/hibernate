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
 * Title: hibernate_[脱管、持久化状态]_[PO的状态]
 * </p>
 * <p>
 * Description: [测试类]
 * </p>
 * 
 * @author SO
 * @version $Revision$ 2017年1月12日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@SuppressWarnings("deprecation")
public class HibernateDetachedMain {
	// 某个实例曾经处于持久化状态，但随着与之关联的Session被关闭，该对象就变成脱管状态。脱管状态对象的引用依然有效，对象可以继续修改。如果重新让脱管对象与
	// 某个Session关联，这个脱管对象会重新转换为持久化状态，而脱管期间的改动不会丢失，也可被写入数据库。正是因为这个功能，逻辑上的长事务成为可能，称为应用
	// 程序事务，即事务可以跨越用户的思考，因为当对象处于脱管状态时，对该对象的操作无须锁定数据库，不会造成性能的下降.
	public static void main(String[] args) {
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class);
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);

		// 打开第一个Session
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		UserInfoPO userInfoPO = new UserInfoPO();
		userInfoPO.setUsername("赵明");
		userInfoPO.setUserpassword("111111");
		userInfoPO.setLoginaccount("admin");
		userInfoPO.setJobnumber("2019");

		// session.save(userInfoPO);
		session.persist(userInfoPO);
		tx.commit();
		session.close();

		// 处于脱管状态时改
		userInfoPO.setUsermail("abc@qq.com");
		userInfoPO.setAttachoffice("研发");

		// 打开sessionOne
		Session sessionOne = sf.openSession();
		Transaction txOne = sessionOne.beginTransaction();
		// 将已经处于脱管状态的对象重新与Session关联
		//sessionOne.update(userInfoPO);
		sessionOne.saveOrUpdate(userInfoPO);
		
		txOne.commit();
		sessionOne.close();

	}
}
