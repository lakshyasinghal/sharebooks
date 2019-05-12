package com.sharebooks.response.enums;


/*The status enumeration will hold the status and their respective codes to describe the status of operations
These are meant for the end users using the application. */

/*The codes will be categorized
 Example 1-100 will belong to account
 100-200 will belong to books.
 
The status 0 will be a special status indicating that something went wrong.It will an abstract status meant for the user.The 
necessary code will be available via error code meant for developer only.*/ 
public enum Status {
	SOMETHING_WENT_WRONG(0,"Something went wrong.Your request couldn't be completed."),
	
	LOGIN_SUCCESSFUL(001 , "User login request successful."),
	INCORRECT_LOGIN_CREDENTIALS(002 , "Incorrect login credentials entered."),
	
	//status codes for books
	FETCH_ALL_BOOKS_SUCCESSFUL(101 , "Books fetched successfully."),
	FETCH_BOOK_BY_ID_SUCCESSFUL(102,"Book fetched by id successfully."),
	BOOK_CREATED_SUCCESSFULLY(103,"Book created successfully."),
	BOOK_NOT_CREATED(104,"Book could not be created due to unknown reasons.Please debug to find more."),
	BOOK_DELETED_SUCCESSFULLY(105,"Book deleted successfully."),
	BOOK_DELETION_FAILED(106,""),
	BOOK_ALREADY_EXISTS(107,""),
	BOOK_UPDATED_SUCCESSFULLY(108,"Book updated successfully."),
	BOOK_NOT_UPDATED(109,"Book could not be updated.Please debug to find more."),
	FETCH_BOOKS_BY_SEARCH_TERM_SUCCESSFUL(110,"Books fetched using search term successfully."),
	FETCH_BOOKS_BY_CATEGORY_SUCCESSFUL(111,"Books fetched using search term successfully."),
	
	//status codes for users
	FETCH_ALL_USERS_SUCCESSFUL(201 , "Users fetched successfully"),
	FETCH_USER_BY_ID_SUCCESSFUL(202,"User fetched by id successfully."),
	USER_CREATED_SUCCESSFULLY(203,"User created successfully"),
	USERNAME_ALREADY_EXISTS(204,"The username in the create new user request is already taken.Please use another one."),
	NO_USERS_EXIST(205,"No users exist in database."),
	USER_UPDATED_SUCCESSFULLY(206,"User updated successfully"),
	USER_NOT_UPDATED(207,"User could not be updated.Please debug to find more."),
	PREFERENCES_SAVED_SUCCESSFULLY(208,"User preferences saved successfully."),
	PREFERENCES_NOT_SAVED(209,"User preferences could not be saved."),
	USER_PROFILE_UPDATED(210,"User profile updated successfully."),
	USER_PROFILE_NOT_UPDATED(211,"User profile could not be updated."),
	USER_NOT_CREATED(203,"User could not be created."),
	
	//status codes for book requests
	FETCH_BOOK_REQUESTS_BY_UID_SUCCESSFUL(301,"Book request fetched successfully by owner uid."),
	BOOK_REQUEST_CREATED_SUCCESSFULLY(303,"Book request created successfully."),
	BOOK_REQUEST_UPDATED_SUCCESSFULLY(304,"Book request updated successfully."),
	BOOK_REQUEST_NOT_CREATED(305,"Book request could not be created."),
	BOOK_REQUEST_NOT_UPDATED(306,"Book request could not be updated."),
	BOOK_REQUEST_ACCEPTED_SUCCESSFULLY(307,"Book request accepted successfully."),
	BOOK_REQUEST_COULD_NOT_BE_ACCEPTED(308,"Book request could not be accepted."),
	BOOK_REQUEST_REJECTED_SUCCESSFULLY(309,"Book request rejected successfully."),
	BOOK_REQUEST_COULD_NOT_BE_REJECTED(310,"Book request could not be rejected."),
	
	//status codes for notifications
	FETCH_NOTIFICATIONS_SUCCESSFUL(401 , "Notifications fetched successfully."),
	FETCH_NOTIFICATIONS_FAILED(402 , "Notifications could not be fetched."),
	NO_NOTIFICATIONS(403, "Notifications do not exist."),
	
	//status codes for helper entities
	FETCH_STATES_SUCCESSFUL(501,"States fetched successfully."),
	STATES_COULD_NOT_BE_FETCHED(502,"States not fetched."),
	FETCH_CITIES_SUCCESSFUL(503,"Cities fetched successfully."),
	CITIES_COULD_NOT_BE_FETCHED(504,"Cities could not be fetched."),
	FETCH_BOOK_CATEGORIES_SUCCESSFUL(505,"Book categories fetched successfully."),
	BOOK_CATEGORIES_COULD_NOT_BE_FETCHED(506,"Book categories could not fetched."),
	
	
	//status codes for all steps related to creating order
	FETCH_QUOTE_SUCCESSFUL(601,"Quote fetched successfully."),
	FETCH_QUOTE_FAILED(602,"Quote could not be fetched."),
	QUOTE_CREATED_SUCCESSFULLY(603,"Quote created successfully."),
	QUOTE_NOT_CREATED(604,"Quote could not be created."),
	QUOTE_UPDATED_SUCCESSFULLY(605,"Quote updated successfully."),
	QUOTE_NOT_UPDATED(606,"Quote could not be updated."),
	FETCH_RESULT_SUCCESSFUL(607,"Result fetched successfully"),
	FETCH_RESULT_FAILED(608,"Result could not be fetched."),
	FETCH_SIMILAR_RESULTS_SUCCESSFUL(609,"Similar results fetched successfully."),
	FETCH_SIMILAR_RESULTS_FAILED(610,"Similar results could not be fetched."),
	QUOTE_CONFIRMED(611,"Quote has been confirmed."),
	QUOTE_NOT_CONFIRMED(612,"Quote could not be confirmed."),
	FETCH_SUMMARY_SUCCESSFUL(613,"Summary fetched successfully."),
	FETCH_SUMMARY_FAILED(614,""),
	
	//generic status codes
	NO_RESULTS_FOUND(801 , "No results found for given parameters."),
	OPERATION_UNSUCCESSFUL(802,"Operation unsuccessful.");
	
	private int id;
	private String desc;
	
	private Status(int id , String desc){
		this.id = id;
		this.desc = desc;
	}
	
	public int id(){
		return id;
	}
	
	public String desc(){
		return desc;
	}
}
