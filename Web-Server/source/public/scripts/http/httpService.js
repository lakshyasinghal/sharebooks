/* jshint esversion: 6 */

const dummyRequestServer = require('./../data/dataRequestServer.js');

//will act as a singleton
var http = (function(){
	var instance;
	/*requestMode 2 implies data will be fetched from dummy server */
	var requestMode = 2;

	function Http(){
		//data is params here
		this.get = function(url , data , successHandler , failureHandler){
			if(requestMode == 2){
				var responseData = (dummyRequestServer[url])(data);
				successHandler(responseData);
				//console.log("$http response data : " + responseData);
			}
			else{
				var http = new XMLHttpRequest();

				var paramString = getParamString(data);
				
				if(paramString != ""){
					url = url + "?" + paramString;
				}
				//true value will make the request asynchronous
				http.open("GET", url, true);
				//Send the proper header information along with the request
				http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				http.onreadystatechange = function() {//Call a function when the state changes.
				    if(http.readyState == 4 && http.status == 200) {
				        //alert(http.responseText);
				        successHandler(http.responseText);
				    }
				    else{
				    	failureHandler(http.responseText);
				    }
				};
				http.send();
			}
		};

		this.post = function(url , data , successHandler , failureHandler){
			if(requestMode == 2){
				var responseData = (dummyRequestServer[url])(data);
				successHandler(responseData);
				console.log("$http responseData", responseData);
			}
			else{
				var http = new XMLHttpRequest();
				var params = getParamString(data);
				//true value will make the request asynchronous
				http.open("POST", url, true);
				//Send the proper header information along with the request
				http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				http.onreadystatechange = function() {//Call a function when the state changes.
				    if(http.readyState == 4 && http.status == 200) {
				        //alert(http.responseText);
				        successHandler(http.responseText);
				    }
				    else{
				    	failureHandler(http.responseText);
				    }
				};
				http.send(params);
			}
		};

		this.delete = function(){

		};

		this.put = function(){

		};
	}

	return {
		instance : function(){
			if(!instance){
				instance = new Http();
			}
			return instance;
		}
	};
})();
//getting singleton instance
var $http = http.instance();



//will convert params object into query string
function getParamString(paramsObject){
	var paramString = "";
	var i = 0;

	if(paramsObject != null){
		for(var prop in paramsObject){
			if(i != 0){
				paramString += "&";
			}
			paramString += prop + "=" + paramsObject[prop];
			i++;
		}
	}
	return paramString;
}



//httpService containing all the requests
$httpService = (function(){
	var SIGN_IN = "signIn";
	var SIGN_UP = "signUp";
	var SIGN_OUT = "signOut";
	var GET_BOOK = "getBook";
	var GET_ALL_BOOKS = "getAllBooks";
	var GET_BOOKS = "getBooks";
	var GET_USER = "getUser";
	var GET_USER_BY_ID = "getUserById";
	var FILTER_BY_CATEGORY = "filterByCategory";
	var GET_BOOKS_BY_SEARCH_STRING = "getBooksBySearchString";
	var ADD_BOOK = "addBook";
	var UPDATE_USER = "updateUser";
	var GET_NOTIFICATIONS = "getNotifications";
	var GET_ALL_RESULTS = "getAllResults";
	var GET_SIMILAR_BOOKS = "getSimilarBooks";
	var ADD_BOOK_REQUEST = "addBookRequest";
	var SEND_OTP = "sendOTP";
	var VERIFY_OTP = "verifyOTP";
	var SAVE_NEW_PASSWORD = "saveNewPassword";
	var GET_SUBCATEGORIES = "getSubcategories";
	var SAVE_FEEDBACK = "saveFeedback";
	var SAVE_COMPLAINT = "saveComplaint";
	var GET_PREFERENCE_OPTIONS = "getPreferenceOptions";
	var SAVE_PREFERENCES = "savePreferences";
	var GET_SELECTED_RESULT = "getSelectedResult";
	var SAVE_BOOK_REQUEST = "saveBookRequest";

	function HttpService(){

		this.signIn = httpMethodFactory("GET",SIGN_IN);
		this.signUp = httpMethodFactory("POST",SIGN_UP);
		this.signOut = httpMethodFactory("POST",SIGN_OUT);
		this.getBook = httpMethodFactory("GET",GET_BOOK);
		this.getAllBooks = httpMethodFactory("GET",GET_ALL_BOOKS);
		this.getBooks = httpMethodFactory("GET",GET_BOOKS);
		this.getUser = httpMethodFactory("GET",GET_USER);
		this.getUserById = httpMethodFactory("GET",GET_USER_BY_ID);
		this.filterByCategory = httpMethodFactory("POST",FILTER_BY_CATEGORY);
		this.getBooksBySearchString = httpMethodFactory("GET",GET_BOOKS_BY_SEARCH_STRING);
		this.addBook = httpMethodFactory("POST",ADD_BOOK);
		this.updateUser = httpMethodFactory("POST",UPDATE_USER);
		this.getNotifications = httpMethodFactory("GET",GET_NOTIFICATIONS);
		this.getAllResults = httpMethodFactory("GET",GET_ALL_RESULTS);
		this.getSimilarBooks = httpMethodFactory("POST",GET_SIMILAR_BOOKS);
		this.addBookRequest = httpMethodFactory("POST",ADD_BOOK_REQUEST);
		this.sendOTP = httpMethodFactory("POST",SEND_OTP);
		this.verifyOTP = httpMethodFactory("POST",VERIFY_OTP);
		this.saveNewPassword = httpMethodFactory("POST",SAVE_NEW_PASSWORD);
		this.getSubcategories = httpMethodFactory("GET",GET_SUBCATEGORIES);
		this.saveFeedback = httpMethodFactory("POST",SAVE_FEEDBACK);
		this.saveComplaint = httpMethodFactory("POST",SAVE_COMPLAINT);
		this.getPreferenceOptions = httpMethodFactory("GET",GET_PREFERENCE_OPTIONS);
		this.savePreferences = httpMethodFactory("POST",SAVE_PREFERENCES);
		this.getSelectedResult = httpMethodFactory("GET",GET_SELECTED_RESULT);
		this.saveBookRequest = httpMethodFactory("POST",SAVE_BOOK_REQUEST);
	}

	return new HttpService();
})();


//this fuction will act as factory and will generate functions with suitable parameters
function httpMethodFactory(request_type,serviceName){
	var func = undefined;

	switch (request_type) {
		case "GET":
			func = function(params,success,failure){
				$http.get(serviceName,params,success,failure);
			};
			break;
		case "POST":
			func = function(params,success,failure){
				$http.post(serviceName,params,success,failure);
			};
			break;
		default:
			break;
	}

	return func;
}




module.exports = $httpService;
