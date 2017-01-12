package com.song.hibernate.web;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.UserInfoPO;

/**
 * <p>
 * Title: hibernate_[二级缓存]_[ehcache开启前后]
 * </p>
 * <p>
 * Description: [测试类]
 * </p>
 * 
 * @author songyushi
 * @version $Revision$ 2017年1月12日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@SuppressWarnings("deprecation")
public class HibernateEhcahePropertiesMain {

	public static void main(String[] args) {
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class);
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);

		// 在未开启二级缓存情况下，打开第一、二个session会两次发送sql;开启二级缓存情况下，只在第一个session处发送sql
		// 打开第一个session
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings({ "rawtypes", "unused" })
		List list = sess.createQuery(" FROM UserInfoPO userInfo ").list();
		sess.getTransaction().commit();

		// 打开第二个session
		System.out.println("==========打开第二个Session============");
		Session sess2 = sf.openSession();
		sess2.beginTransaction();
		UserInfoPO userInfoPO = (UserInfoPO) sess2.load(UserInfoPO.class, 1);
		System.out.println(userInfoPO.getUsername());
		sess2.getTransaction().commit();

		// 无论是否开启二级缓存，第一次查询总会发送sql，第二次不会
		// 打开第三个session
		System.out.println("==========打开第三个Session============");
		Session sess3 = sf.openSession();
		sess3.beginTransaction();
		@SuppressWarnings("unchecked")
		List<UserInfoPO> listPO = (List<UserInfoPO>) sess3.createQuery(" FROM UserInfoPO userInfo ").list();
		System.out.println(listPO.get(0).getUsername());

		UserInfoPO userInfoPOThree = (UserInfoPO) sess3.load(UserInfoPO.class, 1);
		System.out.println(userInfoPOThree.getUsername());
		sess3.getTransaction().commit();

	}

}
