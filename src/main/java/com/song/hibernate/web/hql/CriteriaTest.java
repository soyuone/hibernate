package com.song.hibernate.web.hql;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.CompanyPO;
import com.song.hibernate.service.model.po.UserInfoPO;

/**
 * <p>
 * Title: hibernate_[criteria]_[模块名]
 * </p>
 * <p>
 * Description: [criteria查询测试类]
 * </p>
 * 
 * @author songyushi
 * @version $Revision$ 2017年1月19日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@SuppressWarnings("deprecation")
public class CriteriaTest {

	public static void main(String[] args) {
		CriteriaTest criteriaTest = new CriteriaTest();
		criteriaTest.query();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void query() {
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class).addAnnotatedClass(CompanyPO.class);
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		Session session = sf.openSession();
		session.beginTransaction();

		//测试criteria eq,ne,gt,ge,lt,le等
		List list =
				session.createCriteria(CompanyPO.class).add(Restrictions.eq("jobnumber", "2019"))
						.add(Restrictions.ge("id", 1)).add(Restrictions.like("company", "党", MatchMode.ANYWHERE))
						.addOrder(Order.desc("company")).list();

		List<CompanyPO> listCompanyPO = (List<CompanyPO>) list;
		for (Iterator iterator = listCompanyPO.iterator(); iterator.hasNext();) {
			CompanyPO companyPO = (CompanyPO) iterator.next();
			System.out.println(companyPO.toString());
		}
		
		session.getTransaction().commit();
	}

}
