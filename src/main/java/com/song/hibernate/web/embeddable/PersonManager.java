package com.song.hibernate.web.embeddable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

@SuppressWarnings("deprecation")
public class PersonManager {

	public static void main(String[] args) {
		// 可以多次调用addAnnotatedClass添加持久化类
		Configuration conf = new Configuration().addAnnotatedClass(Person.class);

		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		// 用Configuration实例创建SessionFactory实例
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		// 创建Session,PO对象只有在Session的管理下才可完成数据库访问
		Session sess = sf.openSession();
		// 开启事务
		sess.beginTransaction();

		// 创建Person对象
		Person person = new Person();

		person.setAge(20);
		person.setName(new Name("songyu", "shi"));
		sess.save(person);
		sess.getTransaction().commit();
	}

}
