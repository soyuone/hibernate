package com.song.hibernate.web;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.UserInfoPO;

/**
 * <p>
 * Title: hibernate_[持久化状态]_[PO的状态]
 * </p>
 * <p>
 * Description: [测试类]
 * </p>
 * 
 * @author songyushi
 * @version $Revision$ 2017年1月13日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@SuppressWarnings("deprecation")
public class GetTOUpdateMain {
	public static void main(String[] args) {
		// 在加载了持久化实例后，实例就处于持久化状态，对持久化实例所作的修改会保存到数据库(会在Session flush前被自动保存到数据库，无须程序调用其它方法，
		// 不需要调用update方法)
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class);
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);

		// 打开第一个Session
		Session session = sf.openSession();
		session.beginTransaction();

		UserInfoPO userInfoPO = (UserInfoPO) session.load(UserInfoPO.class, 1);
		userInfoPO.setUsermail("xiaoming@qq.com");
		session.getTransaction().commit();
	}
}
