package com.sharebooks.dao.util;

import java.util.ArrayList;
import java.util.List;

import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.BookRequest;
import com.sharebooks.entities.coreEntities.Notification;
import com.sharebooks.entities.coreEntities.OneTimePassword;
import com.sharebooks.entities.coreEntities.PasswordResetLink;
import com.sharebooks.entities.coreEntities.Quote;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.entities.helperEntities.BookCategory;
import com.sharebooks.entities.helperEntities.City;
import com.sharebooks.entities.helperEntities.State;

public class EntityConverterUtility {

	// function for converting entity list into user list
	public static List<User> convertIntoUserList(List<Entity> list) throws Exception {
		try {
			List<User> userList = new ArrayList<User>();
			for (Entity entity : list) {
				if (entity instanceof User) {
					userList.add((User) entity);
				} else {
					throw new Exception("User list containing some other entity");
				}
			}

			return userList;
		} catch (Exception ex) {
			throw ex;
		}
	}

	public static List<BookCategory> convertIntoBookCategoryList(List<Entity> list) throws Exception {
		try {
			List<BookCategory> bookCategoryList = new ArrayList<BookCategory>();
			for (Entity entity : list) {
				if (entity instanceof BookCategory) {
					bookCategoryList.add((BookCategory) entity);
				} else {
					throw new Exception("BookCategory list containing some other entity");
				}
			}

			return bookCategoryList;
		} catch (Exception ex) {
			throw ex;
		}
	}

	// function for converting entity list into book list
	public static List<Book> convertIntoBookList(List<Entity> list) throws Exception {
		try {
			List<Book> bookList = new ArrayList<Book>();
			for (Entity entity : list) {
				if (entity instanceof Book) {
					bookList.add((Book) entity);
				} else {
					throw new Exception("Book list containing some other entity");
				}
			}

			return bookList;
		} catch (Exception ex) {
			throw ex;
		}
	}

	// function for converting entity list into book request list
	public static List<BookRequest> convertIntoBookRequestList(List<Entity> list) throws Exception {
		try {
			List<BookRequest> bookRequestList = new ArrayList<BookRequest>();
			for (Entity entity : list) {
				if (entity instanceof BookRequest) {
					bookRequestList.add((BookRequest) entity);
				} else {
					throw new Exception("Book Request list containing some other entity");
				}
			}

			return bookRequestList;
		} catch (Exception ex) {
			throw ex;
		}
	}

	// function for converting entity list into city list
	public static List<City> convertIntoCityList(List<Entity> list) throws Exception {
		try {
			List<City> cityList = new ArrayList<City>();
			for (Entity entity : list) {
				if (entity instanceof City) {
					cityList.add((City) entity);
				} else {
					throw new Exception("City list containing some other entity");
				}
			}

			return cityList;
		} catch (Exception ex) {
			throw ex;
		}
	}

	// function for converting entity list into state list
	public static List<State> convertIntoStateList(List<Entity> list) throws Exception {
		try {
			List<State> stateList = new ArrayList<State>();
			for (Entity entity : list) {
				if (entity instanceof State) {
					stateList.add((State) entity);
				} else {
					throw new Exception("State list containing some other entity");
				}
			}

			return stateList;
		} catch (Exception ex) {
			throw ex;
		}
	}

	// function for converting entity list into Notification list
	public static List<Notification> convertIntoNotificationList(List<Entity> list) throws Exception {
		try {
			List<Notification> notificationList = new ArrayList<Notification>();
			for (Entity entity : list) {
				if (entity instanceof Notification) {
					notificationList.add((Notification) entity);
				} else {
					throw new Exception("Notification list containing some other entity");
				}
			}

			return notificationList;
		} catch (Exception ex) {
			throw ex;
		}
	}

	// function for converting entity list into otp list
	public static List<OneTimePassword> convertIntoOTPList(List<Entity> list) throws Exception {
		try {
			List<OneTimePassword> otpList = new ArrayList<OneTimePassword>();
			for (Entity entity : list) {
				if (entity instanceof OneTimePassword) {
					otpList.add((OneTimePassword) entity);
				} else {
					throw new Exception("OTP list containing some other entity");
				}
			}

			return otpList;
		} catch (Exception ex) {
			throw ex;
		}
	}

	// function for converting entity list into otp list
	public static List<PasswordResetLink> convertIntoLinksList(List<Entity> list) throws Exception {
		try {
			List<PasswordResetLink> linkList = new ArrayList<PasswordResetLink>();
			for (Entity entity : list) {
				if (entity instanceof PasswordResetLink) {
					linkList.add((PasswordResetLink) entity);
				} else {
					throw new Exception("PasswordResetLink list containing some other entity");
				}
			}

			return linkList;
		} catch (Exception ex) {
			throw ex;
		}
	}

	// function for converting entity list into quote list
	public static List<Quote> convertIntoQuoteList(List<Entity> list) throws Exception {
		try {
			List<Quote> quoteList = new ArrayList<Quote>();
			for (Entity entity : list) {
				if (entity instanceof Quote) {
					quoteList.add((Quote) entity);
				} else {
					throw new Exception("Quote list containing some other entity");
				}
			}

			return quoteList;
		} catch (Exception ex) {
			throw ex;
		}
	}
}
