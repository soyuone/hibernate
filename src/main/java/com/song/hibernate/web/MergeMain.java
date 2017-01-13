package com.song.hibernate.web;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.UserInfoPO;

/**
 * <p>
 * Title: hibernate_[merge]_[更新]
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
public class MergeMain {

	public static void main(String[] args) {
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class);
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);

		// 打开Session
		Session session = sf.openSession();
		session.beginTransaction();

		// 当没有调用userInfoPO对象的方法时使用load不会发送sql语句，使用get则会发送sql语句
		UserInfoPO userInfoPO = (UserInfoPO) session.load(UserInfoPO.class, 1);
		// UserInfoPO userInfoPO = (UserInfoPO) session.get(UserInfoPO.class, 1);

		userInfoPO.setUsermail("chinam@qq.com");

		// merge,update,saveOrUpdate当所有属性无任何变动时不会发送sql;merge,update,saveOrUpdate更新时set会包含所有属性
		// 只有显示指定@DynamicUpdate(true)下才会只更新变更的字段
		session.merge(userInfoPO);
		// session.update(userInfoPO);
		// session.saveOrUpdate(userInfoPO);
		session.getTransaction().commit();

	}

}
