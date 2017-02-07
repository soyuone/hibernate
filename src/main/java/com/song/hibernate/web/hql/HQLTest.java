package com.song.hibernate.web.hql;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.CompanyPO;
import com.song.hibernate.service.model.po.UserInfoPO;

@SuppressWarnings("deprecation")
public class HQLTest {

	public static void main(String[] args) {
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class).addAnnotatedClass(CompanyPO.class);
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		Session session = sf.openSession();
		session.beginTransaction();

		// 类似于SQL的规则，出现在SELECT后的属性，要么出现在聚集函数中，要么出现在group by的属性列表中。
		// having子句用于对分组进行过滤（分组之后的结果），因此having子句只能在有group by子句时才可以使用。
		// group by子句和order by子句中都不能包含算术表达式；group by子句和order by子句一起使用时 order by子句要在后面.
		System.out.println();
		System.out.println("测试HQL GROUP BY & HAVING");

		// MySQL不支持IS EMPTY,IS NOT EMPTY
		System.out.println();
		System.out.println("测试HQL IS NULL & IS EMPTY");

		// IN查询内部传数组即可
		System.out.println();
		System.out.println("测试HQL IN查询");
		String hqlIn = " FROM CompanyPO b WHERE b.jobnumber IN ( :jobnumber) ";
		String parameterArr = "2018,2019,2020";
		Query queryIn =
				session.createQuery(hqlIn).setParameterList("jobnumber", parameterArr.split(",")).setFirstResult(0);
		@SuppressWarnings("unchecked")
		List<CompanyPO> listIn = (List<CompanyPO>) queryIn.list();
		for (CompanyPO po : listIn) {
			System.out.println(po.toString());
		}

		// CONCAT or ||连接符作用相同
		System.out.println();
		System.out.println("测试HQL CONCAT or ||查询");
		// String hqlConcat = " SELECT CONCAT(b.company, b.jobnumber) FROM CompanyPO b ";
		String hqlConcat = " SELECT b.company || b.jobnumber FROM CompanyPO b ";
		@SuppressWarnings("rawtypes")
		List listConcat = session.createQuery(hqlConcat).list();
		if (null != listConcat && listConcat.size() > 0) {
			for (@SuppressWarnings("rawtypes")
			Iterator iterator = listConcat.iterator(); iterator.hasNext();) {
				Object object = (Object) iterator.next();
				System.out.println(object);
			}
		}

		System.out.println();
		System.out.println("测试HQL LIST返回");
		String hqlFrom = " FROM CompanyPO b ";
		Query query = session.createQuery(hqlFrom);
		// Set the maximum number of rows to retrieve.
		query.setMaxResults(1);
		@SuppressWarnings("rawtypes")
		List listFrom = query.list();
		@SuppressWarnings("unchecked")
		List<CompanyPO> listCompanyPO = (List<CompanyPO>) listFrom;
		if (null != listCompanyPO && listCompanyPO.size() > 0) {
			for (CompanyPO po : listCompanyPO) {
				System.out.println(po.toString());
			}
		}

		System.out.println();
		System.out.println("测试HQL SELECT查询");
		String querySelect =
				" SELECT a.company,a.jobnumber,b.loginaccount FROM CompanyPO a,UserInfoPO b WHERE a.jobnumber = b.jobnumber AND a.jobnumber < ? ";
		@SuppressWarnings("rawtypes")
		List listSelect = session.createQuery(querySelect).setParameter(0, "3020").list();
		if (null != listSelect && listSelect.size() > 0) {
			for (int i = 0; i < listSelect.size(); i++) {
				Object[] obj = (Object[]) listSelect.get(i);
				System.out.println("company: " + obj[0] + " ,jobnumber: " + obj[1] + " ,loginaccount:" + obj[2]);
			}
		}

		// 测试join查询
		// ERROR: Path expected for join!
		// HQL没有on关键字，另外hibernate如果要使用左连接，必须建立一对多或多对一或一对一的关联关系，如student和Teacher两个类，Student类里有一个Teacher属性，这样才可以左链接
		System.out.println();
		System.out.println("测试HQL JOIN连接查询");
		String hqlJoin =
				" SELECT a.company,a.jobnumber,b.loginaccount FROM CompanyPO a LEFT JOIN UserInfoPO b ON a.jobnumber = b.jobnumber WHERE a.jobnumber < :jobnumber ";
		@SuppressWarnings("rawtypes")
		List listJoin = session.createQuery(hqlJoin).setParameter("jobnumber", "3020").list();
		if (null != listJoin && listJoin.size() > 0) {
			for (int i = 0; i < listJoin.size(); i++) {
				Object[] obj = (Object[]) listJoin.get(i);
				System.out.println("company: " + obj[0] + " ,jobnumber: " + obj[1] + " ,loginaccount:" + obj[2]);
			}
		}
		session.getTransaction().commit();
	}

}
