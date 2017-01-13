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
 * Title: htmlmanange_[子系统名称]_[模块名]
 * </p>
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author songyushi
 * @version $Revision$ 2017年1月13日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@SuppressWarnings("deprecation")
public class WhereAnnotationMain {

	public static void main(String[] args) {
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class);
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);

		// 打开Session
		Session session = sf.openSession();
		session.beginTransaction();

		@SuppressWarnings("rawtypes")
		List list = session.createCriteria(UserInfoPO.class).list();
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				UserInfoPO po = (UserInfoPO) list.get(i);
				System.out.println("id:" + po.getId() + " virtueValue:" + po.getVirtueValue());
			}
		}

		session.getTransaction().commit();
	}

}
