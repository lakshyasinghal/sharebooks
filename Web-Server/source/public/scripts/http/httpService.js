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
				        successHandler(http['responseText']);
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


var $http = http.instance();





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

	function HttpService(){

		this.in = function(params , success , failure){
			$http.get(IN , params , success , failure);
		};

		this.signIn = function(params , success , failure){
			$http.post(SIGN_IN , params , success , failure);
		};

		this.signOut = function(params , success , failure){
			$http.get(SIGN_OUT , params , success , failure);
		};

		this.getBook = function(params , success , failure){
			$http.get(GET_BOOK , params , success , failure);
		};

		this.getAllBooks = function(params , success , failure){
			$http.get(GET_ALL_BOOKS , params , success , failure);
		};

		this.getBooks = function(params , success , failure){
			$http.get(GET_BOOKS , params , success , failure);
		};

		this.getUser = function(params , success , failure){
			$http.get(GET_USER , params , success , failure);
		};

		this.getUserById = function(params , success , failure){
			$http.get(GET_USER_BY_ID , params , success , failure);
		};

		this.filterByCategory = function(params , success , failure){
			$http.get(FILTER_BY_CATEGORY , params , success , failure);
		};

		this.getBooksBySearchString = function(params , success , failure){
			$http.get(GET_BOOKS_BY_SEARCH_STRING , params , success , failure);
		};

		this.addBook = function(params , success , failure){
			$http.post(ADD_BOOK , params , success , failure);			
		};

		this.updateUser = function(params , success , failure){
			$http.post(UPDATE_USER , params , success , failure);
		};

		this.getNotifications = function(params , success , failure){
			$http.get(GET_NOTIFICATIONS , params , success , failure);
		};

		this.getAllResults = function(params,success,failure){
			$http.get(GET_ALL_RESULTS,params,success,failure);
		};

		this.getSimilarBooks = function(params , success , failure){
			$http.get(GET_SIMILAR_BOOKS , params , success , failure);
		};

		this.addBookRequest = function(params , success , failure){
			$http.post(ADD_BOOK_REQUEST , params , success , failure);
		};

		this.sendOTP = function(params , success , failure){
			$http.post(SEND_OTP , params , success , failure);
		};

		this.verifyOTP = function(params , success , failure){
			$http.post(VERIFY_OTP , params , success , failure);
		};

		this.saveNewPassword = function(params , success , failure){
			$http.post(SAVE_NEW_PASSWORD , params , success , failure);				
		};

		this.getSubcategories = function(params , success , failure){
			$http.get(GET_SUBCATEGORIES , params , success , failure);
		};

		this.saveFeedback = function(params , success , failure){
			$http.post(SAVE_FEEDBACK , params , success , failure);
		};

		this.saveComplaint = function(params , success , failure){
			$http.post(SAVE_COMPLAINT , params , success , failure);
		};
	}

	return new HttpService();
})();


module.exports = $httpService;
