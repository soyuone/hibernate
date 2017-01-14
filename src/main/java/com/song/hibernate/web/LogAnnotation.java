package com.song.hibernate.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.song.hibernate.service.model.po.Season;
import com.song.hibernate.service.model.po.UserInfoPO;

/**
 * <p>
 * Title: hibernate_[@Lob & save、update后load & date、datetime、time、timestamp]_[ehcache]
 * </p>
 * <p>
 * Description: [测试类]
 * </p>
 * 
 * @author SO
 * @version $Revision$ 2017年1月14日
 * @author (lastest modification by $Author$)
 * @since 20100901
 */
@SuppressWarnings("deprecation")
public class LogAnnotation {

	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public final static String SHORT_DATE_FORMAT = "yyyy-MM-dd";

	public final static String TIME_DATE_FORMAT = "HH:mm:ss";

	public static String getCurrentDateTime(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(Calendar.getInstance().getTime());
	}

	public static Date StringToDate(String date, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if (format != null) {
			sdf = new SimpleDateFormat(format);
		}
		else {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}

		return sdf.parse(date);
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		Configuration conf = new Configuration().addAnnotatedClass(UserInfoPO.class);
		ServiceRegistry serviceRegistry =
				new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();
		SessionFactory sf = conf.buildSessionFactory(serviceRegistry);
		// 打开第一个session
		Session sess = sf.openSession();
		sess.beginTransaction();

		// 创建对象
		UserInfoPO userInfo = new UserInfoPO();
		userInfo.setJobnumber("2019");
		userInfo.setLoginaccount("admin");
		userInfo.setUsermail("song@qq.com");
		userInfo.setUsername("秦明");
		userInfo.setUserpassword("222222");

		File file = new File("G:\\图片\\头像\\OK.jpg");
		byte[] content = new byte[(int) file.length()];
		new FileInputStream(file).read(content);
		userInfo.setPicture(content);

		// 保存
		sess.save(userInfo);
		sess.getTransaction().commit();

		// 打开第二个session
		System.out.println("Second Session");
		Session sessTwo = sf.openSession();
		sessTwo.beginTransaction();

		userInfo.setSeason(Season.SUMMER);
		// 由脱管状态转为持久化状态
		sessTwo.update(userInfo);
		sessTwo.getTransaction().commit();

		// 打开第三个session
		System.out.println("Third Session");
		Session sessThree = sf.openSession();
		sessThree.beginTransaction();

		// 在启用二级缓存情况下，此处仍会发送sql语句，表明先前的save及update并未将对象放入二级缓存中
		UserInfoPO userInfoPO = (UserInfoPO) sessThree.load(UserInfoPO.class, 23);
		userInfoPO.setUsermail("test@qq.com");

		// 测试时间类型
		String currentDate = LogAnnotation.getCurrentDateTime(LogAnnotation.DEFAULT_DATE_FORMAT);
		String currentDateOnly = LogAnnotation.getCurrentDateTime(LogAnnotation.SHORT_DATE_FORMAT);
		String currentTimeOnly = LogAnnotation.getCurrentDateTime(LogAnnotation.TIME_DATE_FORMAT);

		userInfoPO.setLastupdatetime(Timestamp.valueOf(currentDate));
		userInfoPO.setDatetimetest(Timestamp.valueOf(currentDate));
		userInfoPO.setDatetest(LogAnnotation.StringToDate(currentDateOnly, LogAnnotation.SHORT_DATE_FORMAT));
		userInfoPO.setTimetest(Time.valueOf(currentTimeOnly));

		sessThree.getTransaction().commit();
	}

}
