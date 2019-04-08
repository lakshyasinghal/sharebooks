var $global = (function(){
	var global = {
		ORDER_TYPE:{
			RENT:1,
			BUY:2
		}
	};

	return global;
})();



$pages = (function(){
	var IN = "/in";
	var LOG_IN = "/login";
	var HOME = "/home";
	var RESULTS = "/results";
	var CHECKOUT = "/checkout";
	var CONFIRMATION = "/confirmation";
	var FEEDBACK = "/feedback";
	var PROFILE = "/profile";
	var COMPLAINTS = "/complaints";
	var HISTORY = "/history";
	var ABOUT = "/about";
	var RESET_PASSWORD = "/reset";
	var PREFERENCES = "/preferences";
	var ADD_BOOK = "/addBook";

	function Pages(){
		this.signIn = redirector(IN);
		this.logIn = redirector(LOG_IN);
		this.home = redirector(HOME);
		this.results = redirector(RESULTS);
		this.checkout = redirector(CHECKOUT);
		this.confirmation = redirector(CONFIRMATION);
		this.feedback = redirector(FEEDBACK);
		this.profile = redirector(PROFILE);
		this.complaints = redirector(COMPLAINTS);
		this.history = redirector(HISTORY);
		this.about = redirector(ABOUT);
		this.resetPassword = redirector(RESET_PASSWORD);
		this.preferences = redirector(PREFERENCES);
		this.addBook = redirector(ADD_BOOK);
	}

	function redirector(url){
		return (function(){
			window.location.href = url;
		});
	}

	return new Pages();
})();


/* status message service for status codes received in http response */
$sm = (function(){
	//configuring status codes
	statusCodes = {
		"1" : "ADD_USER_SUCCESSFUL",
		"2" : "GET_USER_SUCCESSFUL",
		"3" : "USER_UPDATE_SUCCESSFUL",
		"4" : "USER_UPDATE_FAILED",
		"5" : "USERNAME_ALREADY_EXISTS",
		"6" : "USERINFO_INCOMPLETE",
		"7" : "LOGOUT_SUCCESSFUL",
		"8" : "SESSION_ALREADY_EXPIRED",
		"9" : "SESSION_TIMEOUT",
		"10" : "LOGIN_FAILED",
		"11" : "LOGIN_SUCCESSFUL",
		"12" : "ADD_BOOK_FAILED",
		"13" : "ADD_BOOK_SUCCESSFUL",
		"14" : "GET_BOOKS_SUCCESSFUL",
		"15" : "GET_BOOKS_FAILED",
		"16" : "GET_BOOKS_BY_CATEGORY_SUCCESSFUL",
		"17" : "GET_BOOKS_BY_CATEGORY_FAILED",
		"18" : "GET_BOOKS_BY_SEARCH_SUCCESSFUL",
		"19" : "GET_BOOKS_BY_SEARCH_FAILED",
		"20" : "INCORRECT_REQUEST",
		"21" : "SESSION_DOES_NOT_EXIST",
		"22" : "GET_SIMILAR_BOOKS_SUCCESSFUL",
		"23" : "GET_SIMILAR_BOOKS_FAILED",
		"24" : "ADD_BOOK_REQUEST_SUCCESSFUL",
		"25" : "ADD_BOOK_REQUEST_FAILED",
		"26" : "GET_BOOK_REQUESTS_SUCCESSFUL",
		"27" : "GET_BOOK_REQUESTS_FAILED",
		"28" : "BOOK_ALREADY_EXISTS",
		"29" : "GET_ALL_BOOKS_SUCCESSFUL",
		"30" : "GET_ALL_BOOKS_FAILED",
		"31" : "GET_USER_BY_ID_SUCCESSFUL",
		"32" : "GET_USER_BY_ID_FAILED",
		"33" : "BOOK_REQUEST_SUCCESSFUL",
		"34" : "BOOK_REQUEST_FAILED",
		"35" : "SEND_OTP_SUCCESSFUL",
		"36" : "SEND_OTP_FAILED",
		"37" : "OTP_VERIFICATION_SUCCESSFUL",
		"38" : "OTP_VERIFICATION_FAILED",
		"39" : "GET_SUBCATEGORIES_SUCCESSFUL",
		"40" : "GET_SUBCATEGORIES_FAILED"
	};

	statusMessages = {
		ADD_USER_SUCCESSFUL : "Registered successfully",                                               
		GET_USER_SUCCESSFUL : "User retrieved successfully",                                           
		USER_UPDATE_SUCCESSFUL : "User updated successfully",                                             
		USER_UPDATE_FAILED : "User update failed",                                                  
		USERNAME_ALREADY_EXISTS : "Username already exists. Please choose a different username",           
		USERINFO_INCOMPLETE : "Please enter all the details for the user",                             
		LOGOUT_SUCCESSFUL : "You have been logged out",                                              
		SESSION_ALREADY_EXPIRED : "Your session is already expired",                                       
		SESSION_TIMEOUT : "Your session has timed out",                                            
		LOGIN_FAILED : "Login failed. Please check login credentials",                         
		LOGIN_SUCCESSFUL : "Logged in successfully",                                                
		ADD_BOOK_FAILED : "Book could not be added. Some error occurred",                          
		ADD_BOOK_SUCCESSFUL : "Book has been added successfully to the account",                      
		GET_BOOKS_SUCCESSFUL : "Books retrieved successfully",                                         
		GET_BOOKS_FAILED : "Books could not be fetched. Some error occurred",                       
		GET_BOOKS_BY_CATEGORY_SUCCESSFUL : "Books fetched successfully by category",                                
		GET_BOOKS_BY_CATEGORY_FAILED : "Books request by category failed",                                      
		GET_BOOKS_BY_SEARCH_SUCCESSFUL : "Books request by search string successful",                             
		GET_BOOKS_BY_SEARCH_FAILED : "Books request by search string failed",                                 
		INCORRECT_REQUEST : "Something is wrong with the request. Please check",                     
		SESSION_DOES_NOT_EXIST : "Session doesn't exist",                                                 
		GET_SIMILAR_BOOKS_SUCCESSFUL : "Get similar books to the selected book successfully",
		GET_SIMILAR_BOOKS_FAILED : "No similar books found to the book you requested for",					
		ADD_BOOK_REQUEST_SUCCESSFUL : "Your book request has been saved successfully",     					
		ADD_BOOK_REQUEST_FAILED : "Your book request couldn't be added.",
		GET_BOOK_REQUESTS_SUCCESSFUL : "Book requests retrieved successfully.",
		GET_BOOK_REQUESTS_FAILED : "Book requests could not be fetched.",
		BOOK_ALREADY_EXISTS : "The books you are trying to add already exists.",
		GET_ALL_BOOKS_SUCCESSFUL : "All books fetched successfully.",
		GET_ALL_BOOKS_FAILED : "Get all books request failed.",
		GET_USER_BY_ID_SUCCESSFUL : "User fetched by id successfully",
		GET_USER_BY_ID_FAILED : "User couldn't be fetched for the given id",
		BOOK_REQUEST_SUCCESSFUL : "Your book request has been processed successfully",
		BOOK_REQUEST_FAILED : "Your book request could not be request. Please try after sometime.",
		SEND_OTP_SUCCESSFUL : "OTP has been sent successfully.",
		SEND_OTP_FAILED : "OTP couldn't be sent.",
		OTP_VERIFICATION_SUCCESSFUL : "OTP verified successfully",
		OTP_VERIFICATION_FAILED : "OTP verification failed",
		GET_SUBCATEGORIES_SUCCESSFUL : "Subcategories retrieved succesfully",
		GET_SUBCATEGORIES_FAILED : "Get subcategories request failed"
	};



	return {
		message: function(code){
			return statusMessages[statusCodes[code]];
		}
	};

})();


$categories = [{desc:"Commerce",value:"Commerce"},
			   {desc:"Computer Science",value:"Computer Science"},
			   {desc:"Literature",value:"Literature"},
			   {desc:"Politics",value:"Politics"},
			   {desc:"Science",value:"Science"},
			   {desc:"Sports",value:"Sports"},
			   {desc:"Commerce",value:"Commerce"},
			   {desc:"Computer Science",value:"Computer Science"},
			   {desc:"Literature",value:"Literature"},
			   {desc:"Politics",value:"Politics"},
			   {desc:"Science",value:"Science"},
			   {desc:"Sports",value:"Sports"},
			   {desc:"Commerce",value:"Commerce"},
			   {desc:"Computer Science",value:"Computer Science"},
			   {desc:"Literature",value:"Literature"},
			   {desc:"Politics",value:"Politics"},
			   {desc:"Science",value:"Science"},
			   {desc:"Sports",value:"Sports"},
			   {desc:"Commerce",value:"Commerce"},
			   {desc:"Computer Science",value:"Computer Science"},
			   {desc:"Literature",value:"Literature"},
			   {desc:"Politics",value:"Politics"},
			   {desc:"Science",value:"Science"},
			   {desc:"Sports",value:"Sports"}
			  ];


$config = {
	$global: $global,
	$pages: $pages,
	$sm: $sm,
	$categories: $categories
};


module.exports = $config;