package com.song.hibernate.web.hql;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.CompanyPO;
import com.song.hibernate.service.model.po.UserInfoPO;

/**
 * <p>
 * Title: hibernate_[criteria]_[projection]
 * </p>
 * <p>
 * Description: [测试投影运算]
 * </p>
 * 
 * @author songyushi
 * @version $Revision$ 2017年2月7日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@SuppressWarnings("deprecation")
public class ProjectionTest {

	public static void main(String[] args) {
		ProjectionTest pro = new ProjectionTest();
		pro.projectionFunction();
	}

	@SuppressWarnings("rawtypes")
	private void projectionFunction() {
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class).addAnnotatedClass(CompanyPO.class);
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		Session session = sf.openSession();
		session.beginTransaction();

		// 对于增加了投影运算后的条件查询，查询返回的结果是数组，数组的前N个元素依次是投影运算的结果
		List listResult =
				session.createCriteria("com.song.hibernate.service.model.po.CompanyPO")
						.setProjection(
								Projections.projectionList().add(Projections.rowCount()).add(Projections.max("id"))
										.add(Projections.groupProperty("company"))).addOrder(Order.desc("id")).list();

		for (Iterator iterator = listResult.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			System.out.println("rowCount: " + object[0]);
			System.out.println("max-id: " + object[1]);
			System.out.println(object[2]);
		}
		session.getTransaction().commit();
	}
}
