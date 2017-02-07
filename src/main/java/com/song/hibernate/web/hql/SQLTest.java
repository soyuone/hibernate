package com.song.hibernate.web.hql;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.CompanyPO;
import com.song.hibernate.service.model.po.UserInfoPO;

/**
 * <p>
 * Title: hibernate_[SQLQuery]_[SQL测试]
 * </p>
 * <p>
 * Description: [SQL测试]
 * </p>
 * 
 * @author songyushi
 * @version $Revision$ 2017年2月7日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@SuppressWarnings("deprecation")
public class SQLTest {
	public static void main(String[] args) {
		SQLTest SQLTest = new SQLTest();
		SQLTest.SQLFunction();
	}

	@SuppressWarnings("rawtypes")
	private void SQLFunction() {
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class).addAnnotatedClass(CompanyPO.class);
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		Session session = sf.openSession();
		session.beginTransaction();

		// 如果查询返回了某个数据表的全部数据列，且该数据表有对应的持久化类映射，此时可把查询结果转换成实体查询。
		// 将查询结果转换为实体，可以使用SQLQuery提供的多个重载的addEntity方法，addEntity中不能使用CompanyVO
		// Hibernate还支持将查询结果转换成多个实体，此时SQL字符串中应为不同数据表指定不同别名
		String queryString = " SELECT * FROM tb_company ";
		List list =
				session.createSQLQuery(queryString).addEntity("com.song.hibernate.service.model.po.CompanyPO").list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			CompanyPO companyPO = (CompanyPO) iterator.next();
			System.out.println(companyPO.toString());
		}
		session.getTransaction().commit();
	}
}
