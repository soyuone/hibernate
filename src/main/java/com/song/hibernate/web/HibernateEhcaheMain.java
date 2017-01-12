package com.song.hibernate.web;

import java.util.List;
import java.util.Map;

import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.UserInfoPO;

/**
 * <p>
 * Title: hibernate_[二级缓存]_[ehcache]
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
public class HibernateEhcaheMain {
	public static void main(String[] args) {
		Configuration conf = new Configuration().configure();
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);

		// 第一个session
		Session sess = sf.openSession();
		sess.beginTransaction();
		@SuppressWarnings("rawtypes")
		List list = sess.createQuery(" FROM UserInfoPO userInfo ").list();
		sess.getTransaction().commit();
		System.out.println("==========================");

		// 打开第二个session
		Session sess2 = sf.openSession();
		sess2.beginTransaction();
		// 根据主键加载实体，系统直接从二级缓存中读取
		// 因此不会发出查询sql
		UserInfoPO userInfoPO = (UserInfoPO) sess2.load(UserInfoPO.class, 1);
		System.out.println(userInfoPO.getUsername());
		sess2.getTransaction().commit();

		// 测试二级缓存统计功能
		System.out.println("二级缓存的统计功能");
		@SuppressWarnings("rawtypes")
		Map cacheEntries =
				sf.getStatistics().getSecondLevelCacheStatistics("com.song.hibernate.service.model.po.UserInfoPO")
						.getEntries();
		System.out.println(cacheEntries);

		// 测试管理二级缓存功能
		Cache cache = sf.getCache();
		// 清除二级缓存中指定的对象
		cache.evictEntity(UserInfoPO.class, 1);

		// 打开第三个session
		Session sess3 = sf.openSession();
		sess3.beginTransaction();
		// 根据主键加载实体，因为已从二级缓存中移除所以会从数据库中查找并发出查询sql
		UserInfoPO userInfoPO3 = (UserInfoPO) sess3.load(UserInfoPO.class, 1);
		System.out.println(userInfoPO3.getUsername());
		sess3.getTransaction().commit();
	}
}
