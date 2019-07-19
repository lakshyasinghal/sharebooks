package com.sharebooks.sources;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.DaoProperties;
import com.sharebooks.dao.generic.Dao;
import com.sharebooks.dao.sql.BookCategorySqlDao;
import com.sharebooks.dao.sql.BookRequestSqlDao;
import com.sharebooks.dao.sql.BookSqlDao;
import com.sharebooks.dao.sql.CitySqlDao;
import com.sharebooks.dao.sql.NotificationSqlDao;
import com.sharebooks.dao.sql.OTPSqlDao;
import com.sharebooks.dao.sql.OrderSqlDao;
import com.sharebooks.dao.sql.PasswordResetLinkSqlDao;
import com.sharebooks.dao.sql.PaymentSqlDao;
import com.sharebooks.dao.sql.QuoteSqlDao;
import com.sharebooks.dao.sql.StateSqlDao;
import com.sharebooks.dao.sql.UserSqlDao;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.BookRequest;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.sources.enums.DaoType;

public class DaoSource {
	private static final Logger LOGGER = Logger.getLogger(DaoSource.class.getName());
	private static Map<String, Dao> daoMap = new HashMap<String, Dao>();

	private DaoSource() {

	}

	public static void init() {
		LOGGER.debug("Entered DaoSource init");
		String daoType = AppConfig.daoProp(DaoProperties.DAO_TYPE);
		if (DaoType.SQL.desc().equals(daoType)) {
			new SqlDaoInitializer().init();
		} else if (DaoType.MYBATIS.desc().equals(daoType)) {
			new MyBatisDaoInitializer().init();
		}
		LOGGER.debug("Leaving DaoSource init");
	}

	@SuppressWarnings("unchecked")
	private static class SqlDaoInitializer {

		private void init() {
			initBookDao();
			initUserDao();
			initBookRequestDao();
			initStateDao();
			initCityDao();
			initBookCategoryDao();
			initNotificationDao();
			initOrderDao();
			initQuoteDao();
			initPaymentDao();
			initOTPDao();
			initPasswordResetLinkDao();
		}

		private static void initBookDao() {
			EntityFactory<Book> bookFactory = (EntityFactory<Book>) FactorySource
					.getEntityFactory(EntityType.BOOK.desc());
			daoMap.put(EntityType.BOOK.desc(), new BookSqlDao(bookFactory));
		}

		public static void initUserDao() {
			EntityFactory<User> userFactory = (EntityFactory<User>) FactorySource
					.getEntityFactory(EntityType.USER.desc());
			daoMap.put(EntityType.USER.desc(), new UserSqlDao(userFactory));
		}

		public static void initBookRequestDao() {
			EntityFactory<BookRequest> bookRequestFactory = (EntityFactory<BookRequest>) FactorySource
					.getEntityFactory(EntityType.BOOK_REQUEST.desc());
			daoMap.put(EntityType.BOOK_REQUEST.desc(), new BookRequestSqlDao(bookRequestFactory));
		}

		public static void initStateDao() {
			daoMap.put(EntityType.STATE.desc(), new StateSqlDao());
		}

		public static void initCityDao() {
			daoMap.put(EntityType.CITY.desc(), new CitySqlDao());
		}

		public static void initBookCategoryDao() {
			daoMap.put(EntityType.BOOK_CATEGORY.desc(), new BookCategorySqlDao());
		}

		public static void initNotificationDao() {
			daoMap.put(EntityType.NOTIFICATION.desc(), new NotificationSqlDao());
		}

		public static void initOrderDao() {
			daoMap.put(EntityType.ORDER.desc(), new OrderSqlDao());
		}

		public static void initQuoteDao() {
			daoMap.put(EntityType.Quote.desc(), new QuoteSqlDao());
		}

		public static void initPaymentDao() {
			daoMap.put("payment", new PaymentSqlDao());
		}

		public static void initOTPDao() {
			daoMap.put(EntityType.ONE_TIME_PASSWORD.desc(), new OTPSqlDao());
		}

		public static void initPasswordResetLinkDao() {
			daoMap.put(EntityType.PASSWORD_RESET_LINK.desc(), new PasswordResetLinkSqlDao());
		}
	}

	private static class MyBatisDaoInitializer {
		// SqlSessionFactory sqlSessionFactory = FactorySource.sqlSessionFactory();
		private void init() {
			// initBookDao();
			// initUserDao();
		}

//		@SuppressWarnings("unchecked")
//		private static void initBookDao(){
//			EntityFactory<Book> bookFactory = (EntityFactory<Book>)FactorySource.getEntityFactory("book");
//			daoMap.put(EntityType.BOOK.desc(), new BookSqlDao(bookFactory));
//		}

//		@SuppressWarnings("unchecked")
//		public static void initUserDao(){
//			EntityFactory<User> userFactory = (EntityFactory<User>)FactorySource.getEntityFactory("user");
//			daoMap.put(EntityType.USER.desc(), new UserSqlDao(userFactory));
//		}
	}

	public static Dao getDao(String name) {
		return daoMap.get(name);
	}

}
