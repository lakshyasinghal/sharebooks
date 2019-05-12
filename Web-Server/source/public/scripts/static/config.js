var $global = (function(){
	var global = {
		ORDER_TYPE:{
			RENT:1,
			BUY:2
		},
		WINDOW_TYPE:{
			BLANK:'blank'
		}
	};

	return global;
})();



var $pages = (function(){
	var IN = "/in";
	var LOG_IN = "/login";
	var SIGN_OUT = "/logout";
	var HOME = "/home";
	var RESULTS = "/results/?";               // will be results/bookuid
	var CHECKOUT = "/checkout/?/?";           //will be of type checkout/buy/bookUid or checkout/rent/bookUid
	var SUMMARY = "/summary/?/?";			  // will be summary/rent/quoteUid
	var CONFIRMATION = "/confirmation/?";   //will be of type confirmation/bookRequestUid
	var FEEDBACK = "/feedback";
	var PROFILE = "/profile";
	var COMPLAINTS = "/complaints";
	var HISTORY = "/history";
	var ABOUT = "/about";
	var RESET_PASSWORD = "/reset";
	var PREFERENCES = "/preferences";
	var ADD_BOOK = "/addBook";
	var TERMS_AND_CONDITIONS = "/terms-and-conditions";

	function Pages(){
		this.signIn = redirector(IN);
		this.signOut = redirector(SIGN_OUT);
		this.logIn = redirector(LOG_IN);
		this.home = redirector(HOME);
		this.results = redirector(RESULTS);
		this.checkout = redirector(CHECKOUT);
		this.summary = redirector(SUMMARY);
		this.confirmation = redirector(CONFIRMATION);
		this.termsAndConditions = redirector(TERMS_AND_CONDITIONS);
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
		return (function(pathParams,windowType){
			const finalURL = appendPathParams(url,pathParams);
			if(windowType && windowType=='blank'){
				window.open(finalURL);
			}
			else{
				window.location.href = finalURL;
			}
		});
	}

	return new Pages();
})();



//will convert view url of type checkout/?/? into checkout/buy/wqeh8ey-3i2eh-he2o3-h328
//the spaces will be replaced by '%20'
//need to handle exceptions ************************************
function appendPathParams(url, pathParams){
	if(!pathParams || pathParams.length==0){
		return url;
	}
	var urlTokens = url.split("?");
	for(var i=0,len=urlTokens.length-1;i<len;i++){
		urlTokens[i] += encodeURIComponent(pathParams[i]);  //encodeURIComponent will encode the spaces
	}
	return urlTokens.join('');
}
//*********Above function needs to be made common**********


/* status message service for status codes received in http response */
var $sm = (function(){
	//configuring status codes
	statusCodes = {
		"0" : "SOMETHING_WENT_WRONG",

		"1" : "LOGIN_SUCCESSFUL",
		"2" : "INCORRECT_LOGIN_CREDENTIALS",

		"101" : "FETCH_ALL_BOOKS_SUCCESSFUL",
		"102" : "FETCH_BOOK_BY_ID_SUCCESSFUL",
		"103" : "BOOK_CREATED_SUCCESSFULLY",
		"104" : "BOOK_NOT_CREATED",
		"105" : "BOOK_DELETED_SUCCESSFULLY",
		"106" : "BOOK_DELETION_FAILED",
		"107" : "BOOK_ALREADY_EXISTS",
		"108" : "BOOK_UPDATED_SUCCESSFULLY",
		"109" : "BOOK_NOT_UPDATED",

		"201" : "FETCH_ALL_USERS_SUCCESSFUL",
		"202" : "FETCH_USER_BY_ID_SUCCESSFUL",
		"203" : "USER_CREATED_SUCCESSFULLY",
		"204" : "USERNAME_ALREADY_EXISTS",
		"205" : "NO_USERS_EXIST",
		"206" : "USER_UPDATED_SUCCESSFULLY",
		"207" : "USER_NOT_UPDATED",

		"301" : "FETCH_BOOK_REQUESTS_BY_UID_SUCCESSFUL",
		"303" : "BOOK_REQUEST_CREATED_SUCCESSFULLY",
		"304" : "BOOK_REQUEST_UPDATED_SUCCESSFULLY",
		"305" : "BOOK_REQUEST_NOT_CREATED",
		"306" : "BOOK_REQUEST_NOT_UPDATED",
		"307" : "BOOK_REQUEST_ACCEPTED_SUCCESSFULLY",
		"308" : "BOOK_REQUEST_COULD_NOT_BE_ACCEPTED",
		"309" : "BOOK_REQUEST_REJECTED_SUCCESSFULLY",
		"310" : "BOOK_REQUEST_COULD_NOT_BE_REJECTED"
	};

	statusMessages = {
		"SOMETHING_WENT_WRONG":"Something went wrong. Please try after some time.",

		"LOGIN_SUCCESSFUL":"User login request successful.",
		"INCORRECT_LOGIN_CREDENTIALS":"Incorrect login credentials entered.",

		"FETCH_ALL_BOOKS_SUCCESSFUL":"Books fetched successfully.",
		"FETCH_BOOK_BY_ID_SUCCESSFUL":"Book fetched by id successfully.",
		"BOOK_CREATED_SUCCESSFULLY":"Book created successfully.",
		"BOOK_NOT_CREATED":"Book could not be created due to unknown reasons.",
		"BOOK_DELETED_SUCCESSFULLY":"Book deleted successfully.",
		"BOOK_DELETION_FAILED":"",
		"BOOK_ALREADY_EXISTS":"",
		"BOOK_UPDATED_SUCCESSFULLY":"Book updated successfully.",
		"BOOK_NOT_UPDATED":"Book could not be updated.",

		"FETCH_ALL_USERS_SUCCESSFUL":"Users fetched successfully",
		"FETCH_USER_BY_ID_SUCCESSFUL":"User fetched by id successfully.",
		"USER_CREATED_SUCCESSFULLY":"User created successfully",
		"USERNAME_ALREADY_EXISTS":"The username is already taken.Please use another one.",
		"NO_USERS_EXIST":"No users exist in database.",
		"USER_UPDATED_SUCCESSFULLY":"User updated successfully",
		"USER_NOT_UPDATED":"User could not be updated.",

		"FETCH_BOOK_REQUESTS_BY_UID_SUCCESSFUL":"Book request fetched successfully by owner uid.",
		"BOOK_REQUEST_CREATED_SUCCESSFULLY":"Book request created successfully.",
		"BOOK_REQUEST_UPDATED_SUCCESSFULLY":"Book request updated successfully.",
		"BOOK_REQUEST_NOT_CREATED":"Book request could not be created.",
		"BOOK_REQUEST_NOT_UPDATED":"Book request could not be updated.",
		"BOOK_REQUEST_ACCEPTED_SUCCESSFULLY":"Book request accepted successfully.",
		"BOOK_REQUEST_COULD_NOT_BE_ACCEPTED":"Book request could not be accepted.",
		"BOOK_REQUEST_REJECTED_SUCCESSFULLY":"Book request rejected successfully.",
		"BOOK_REQUEST_COULD_NOT_BE_REJECTED":"Book request could not be rejected."
	};



	return {
		message: function(code){
			return statusMessages[statusCodes[code]];
		}
	};

})();


// var $categories = [{desc:"Commerce",value:"Commerce"},
// 			   {desc:"Computer Science",value:"Computer Science"},
// 			   {desc:"Literature",value:"Literature"},
// 			   {desc:"Politics",value:"Politics"},
// 			   {desc:"Science",value:"Science"},
// 			   {desc:"Sports",value:"Sports"},
// 			   {desc:"Commerce",value:"Commerce"},
// 			   {desc:"Computer Science",value:"Computer Science"},
// 			   {desc:"Literature",value:"Literature"},
// 			   {desc:"Politics",value:"Politics"},
// 			   {desc:"Science",value:"Science"},
// 			   {desc:"Sports",value:"Sports"},
// 			   {desc:"Commerce",value:"Commerce"},
// 			   {desc:"Computer Science",value:"Computer Science"},
// 			   {desc:"Literature",value:"Literature"},
// 			   {desc:"Politics",value:"Politics"},
// 			   {desc:"Science",value:"Science"},
// 			   {desc:"Sports",value:"Sports"},
// 			   {desc:"Commerce",value:"Commerce"},
// 			   {desc:"Computer Science",value:"Computer Science"},
// 			   {desc:"Literature",value:"Literature"},
// 			   {desc:"Politics",value:"Politics"},
// 			   {desc:"Science",value:"Science"},
// 			   {desc:"Sports",value:"Sports"}
// 			  ];


var $config = {
	$global: $global,
	$pages: $pages,
	$sm: $sm
};


module.exports = $config;