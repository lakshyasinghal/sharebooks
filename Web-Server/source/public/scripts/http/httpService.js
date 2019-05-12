/* jshint esversion: 6 */
const $config = require("./../static/config.js");
//const dummyServer = require('./../data/dataRequestServer.js');
const $sm = $config.$sm;

const REQUEST_TYPE = {
	POST: "POST",
	GET: "GET",
	PUT: "PUT",
	DELETE: "DELETE"
}

const CONTENT_TYPE = {
	JSON:"application/json",
	FORM:"application/x-www-form-urlencoded",
	PLAIN_TEXT:"text/plain"
};

//will act as a singleton
var http = (function(){
	var instance;
	/*requestMode 2 implies data will be fetched from dummy server */
	var requestMode = 1;

	function Http(){
		var urlPart1 = "/api/";
		//data is params here
		this.get = function(apiURL, content_type, pathParams, data , successCallback , failureCallback){
			if(requestMode == 2){
				var responseData = (dummyServer[url])(data);
				successCallback(responseData);
				//console.log("$http response data : " + responseData);
			}
			else{
				var http = new XMLHttpRequest();
				var paramString = getSuitableParams(data,content_type);
				apiURL = appendPathParams(apiURL, pathParams);
				
				if(paramString != ""){
					apiURL = apiURL + "?" + paramString;
				}
				//true value will make the request asynchronous
				http.open("GET", urlPart1+apiURL, true);
				http.setRequestHeader("Content-type", content_type);
				ajaxHeader(http);
				http.onreadystatechange = getOnReadyStateChangeCreator(http,successCallback,failureCallback);
				http.send();
			}
		};

		this.post = function(apiURL, content_type, pathParams, data , successCallback , failureCallback){
			if(requestMode == 2){
				var responseData = (dummyServer[url])(data);
				successCallback(responseData);
				console.log("$http responseData", responseData);
			}
			else{
				var http = new XMLHttpRequest();
				var params = getSuitableParams(data,content_type);
				apiURL = appendPathParams(apiURL, pathParams);
				//true value will make the request asynchronous
				http.open("POST", urlPart1+apiURL, true);
				http.setRequestHeader("Content-type", content_type);
				ajaxHeader(http);
				http.onreadystatechange = getOnReadyStateChangeCreator(http,successCallback,failureCallback);
				http.send(params);
			}
		};

		this.put = function(apiURL, content_type, pathParams, data , successCallback , failureCallback){
			var http = new XMLHttpRequest();
			var params = getSuitableParams(data,content_type);
			apiURL = appendPathParams(apiURL, pathParams);
			//true value will make the request asynchronous
			http.open("PUT", urlPart1+apiURL, true);
			http.setRequestHeader("Content-type", content_type);
			ajaxHeader(http);
			http.onreadystatechange = getOnReadyStateChangeCreator(http,successCallback,failureCallback);
			http.send(params);
		};

		this.delete = function(){

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


//we are this header so that we can recognize it as a ajax request at web server
function ajaxHeader(http){
	http.setRequestHeader("X-Requested-With", "XMLHttpRequest");
}


function getOnReadyStateChangeCreator(http,successCallback,failureCallback){
	return (function() {//Call a function when the state changes.
			    if(http.readyState == 4) {
			    	var content_type = http.getResponseHeader('content-type');
			    	var responseText = http.responseText;
			    	//if response text is json type, parse it so we don't have to do it in each callback function
			    	if(content_type.indexOf("json")>-1){
			    		responseText = JSON.parse(responseText);
			    	}

			       	if(http.status==200){
			       		if(responseText.success){
			       			//tryParseJsonValues(responseText);
			       			successCallback(responseText);
			       		}
			       		else{
			       			//show status message corresponding to status code
			       			//will use generic template on each page to display the message
			       		}
			       	}
			        else{
			        	if(failureCallback){
			        		failureCallback(responseText);
			        	}
			        	else{
			        		//for now using alert but needs to be replaced by proper error handling template
			        		var message = "Failure occurred";
			        		message+="\nStatus : "+ http.status;
			        		message+="\nFailure handler not defined";
			        		alert(message);
			        	}
			        }
			    }
			    else{
			    	console.log("http state=>",http.readyState);
			    }
			});
}


function getSuitableParams(data , content_type){
	var queryString = "";
	if(content_type==undefined){
		content_type = CONTENT_TYPE.FORM;
	}
	
	if(data!=null){
		if(content_type==CONTENT_TYPE.JSON){
			queryString = JSON.stringify(data);
		}
		else{
			queryString = getParamString(data);
		}
	}
	
	return queryString;
}



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


//will convert api url of type books/search/? into books/search/head-first-java
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


//this function will be extensively used to parse response object which might contain some unparsed json values
function tryParseJsonValues(obj){
	for(var key in obj){
		try{
			obj[key] = JSON.parse(obj[key]);
		}
		catch(err){

		}
	}
}



//httpService containing all the requests
$httpService = (function(){
	var SIGN_IN = "login";
	var SIGN_UP = "users";
	var SIGN_OUT = "logout";
	var ADD_BOOK = "books";
	var GET_BOOK = "books";        //will become api/books/2
	var GET_ALL_BOOKS = "books";   // will become api/books
	var GET_BOOKS = "getBooks";
	var GET_BOOKS_BY_SEARCH_TERM = "books/search/?";
	var GET_USER = "users";
	var GET_BOOK_CATEGORIES = "book-categories";
	var GET_USER_BY_ID = "getUserById";
	var FILTER_BY_CATEGORY = "books/category/?";         //api will be of type books/category/computer_science       
	var UPDATE_USER = "users";  
	var UPDATE_PROFILE = "users/?/profile";                         //  users/uid/profile
	var GET_NOTIFICATIONS = "notifications/?";             // notifications/userUid
	var GET_RESULT = "results/?";                          // results/bookUid
	var GET_SIMILAR_RESULTS = "results";
	var CREATE_QUOTE = "quotes";
	var GET_SUMMARY_INFO = "quotes/summary/?";              // quotes/summary/quoteUid
	var CONFIRM_QUOTE = "quotes/confirm/?";					// quotes/confirm/quoteUid
	//var GET_ALL_RESULTS = "getAllResults";
	var GET_SIMILAR_BOOKS = "getSimilarBooks";
	var ADD_BOOK_REQUEST = "bookRequests";
	var SEND_OTP = "sendOTP";
	var VERIFY_OTP = "verifyOTP";
	var SAVE_NEW_PASSWORD = "saveNewPassword";
	var GET_SUBCATEGORIES = "getSubcategories";
	var SAVE_FEEDBACK = "saveFeedback";
	var SAVE_COMPLAINT = "account/complaint";
	var GET_PREFERENCE_OPTIONS = "book-categories";
	var SAVE_PREFERENCES = "users/?/preferences";
	var GET_SELECTED_RESULT = "getSelectedResult";
	var SAVE_BOOK_REQUEST = "saveBookRequest";
	

	function HttpService(){
		this.signIn = httpMethodFactory(REQUEST_TYPE.POST,SIGN_IN,CONTENT_TYPE.FORM);
		this.signUp = httpMethodFactory(REQUEST_TYPE.PUT,SIGN_UP,CONTENT_TYPE.JSON);
		this.signOut = httpMethodFactory(REQUEST_TYPE.GET,SIGN_OUT,CONTENT_TYPE.FORM);
		this.addBook = httpMethodFactory(REQUEST_TYPE.PUT,ADD_BOOK,CONTENT_TYPE.JSON);
		this.getBook = httpMethodFactory(REQUEST_TYPE.GET,GET_BOOK,CONTENT_TYPE.FORM);
		this.getAllBooks = httpMethodFactory(REQUEST_TYPE.GET,GET_ALL_BOOKS,CONTENT_TYPE.FORM);
		this.getBooks = httpMethodFactory(REQUEST_TYPE.GET,GET_BOOKS,CONTENT_TYPE.FORM);
		this.getBooksBySearchTerm = httpMethodFactory(REQUEST_TYPE.GET,GET_BOOKS,CONTENT_TYPE.FORM);
		this.getUser = httpMethodFactory(REQUEST_TYPE.GET,GET_USER,CONTENT_TYPE.FORM);
		this.getBookCategories = httpMethodFactory(REQUEST_TYPE.GET,GET_BOOK_CATEGORIES,CONTENT_TYPE.FORM);
		this.getUserById = httpMethodFactory(REQUEST_TYPE.GET,GET_USER_BY_ID,CONTENT_TYPE.FORM);
		this.filterByCategory = httpMethodFactory(REQUEST_TYPE.GET,FILTER_BY_CATEGORY,CONTENT_TYPE.FORM);
		this.getBooksBySearchTerm = httpMethodFactory(REQUEST_TYPE.GET,GET_BOOKS_BY_SEARCH_TERM,CONTENT_TYPE.FORM);
		this.updateUser = httpMethodFactory(REQUEST_TYPE.POST,UPDATE_USER,CONTENT_TYPE.FORM);
		this.updateProfile = httpMethodFactory(REQUEST_TYPE.POST,UPDATE_PROFILE,CONTENT_TYPE.FORM);
		this.getNotifications = httpMethodFactory(REQUEST_TYPE.GET,GET_NOTIFICATIONS,CONTENT_TYPE.FORM);
		this.getResult = httpMethodFactory(REQUEST_TYPE.GET,GET_RESULT,null);
		this.getSimilarResults = httpMethodFactory(REQUEST_TYPE.GET,GET_SIMILAR_RESULTS,CONTENT_TYPE.JSON);
		this.createQuote = httpMethodFactory(REQUEST_TYPE.PUT,CREATE_QUOTE,CONTENT_TYPE.JSON);
		this.getSummaryInfo = httpMethodFactory(REQUEST_TYPE.GET,GET_SUMMARY_INFO,undefined);
		this.confirmQuote = httpMethodFactory(REQUEST_TYPE.POST,CONFIRM_QUOTE,undefined);
		//this.getAllResults = httpMethodFactory(REQUEST_TYPE.GET,GET_ALL_RESULTS,CONTENT_TYPE.FORM);
		this.getSimilarBooks = httpMethodFactory(REQUEST_TYPE.POST,GET_SIMILAR_BOOKS,CONTENT_TYPE.FORM);
		this.addBookRequest = httpMethodFactory(REQUEST_TYPE.POST,ADD_BOOK_REQUEST,CONTENT_TYPE.FORM);
		this.sendOTP = httpMethodFactory(REQUEST_TYPE.POST,SEND_OTP,CONTENT_TYPE.FORM);
		this.verifyOTP = httpMethodFactory(REQUEST_TYPE.POST,VERIFY_OTP,CONTENT_TYPE.FORM);
		this.saveNewPassword = httpMethodFactory(REQUEST_TYPE.POST,SAVE_NEW_PASSWORD,CONTENT_TYPE.FORM);
		this.getSubcategories = httpMethodFactory(REQUEST_TYPE.GET,GET_SUBCATEGORIES,CONTENT_TYPE.FORM);
		this.saveFeedback = httpMethodFactory(REQUEST_TYPE.POST,SAVE_FEEDBACK,CONTENT_TYPE.FORM);
		this.saveComplaint = httpMethodFactory(REQUEST_TYPE.POST,SAVE_COMPLAINT,CONTENT_TYPE.FORM);
		this.getPreferenceOptions = httpMethodFactory(REQUEST_TYPE.GET,GET_PREFERENCE_OPTIONS,CONTENT_TYPE.FORM);
		this.savePreferences = httpMethodFactory(REQUEST_TYPE.POST,SAVE_PREFERENCES,CONTENT_TYPE.JSON);
		this.getSelectedResult = httpMethodFactory(REQUEST_TYPE.GET,GET_SELECTED_RESULT,CONTENT_TYPE.FORM);
		this.saveBookRequest = httpMethodFactory(REQUEST_TYPE.POST,SAVE_BOOK_REQUEST,CONTENT_TYPE.FORM);
	}

	return new HttpService();
})();


/*this closure fuction will act as factory and will generate get,post and other functions with suitable apiURL and content type*/
function httpMethodFactory(request_type,apiName,content_type){
	var func = undefined;

	switch (request_type) {
		case "GET":
			func = function(pathParams,params,success,failure){
				$http.get(apiName,content_type,pathParams,params,success,failure);
			};
			break;
		case "POST":
			func = function(pathParams,params,success,failure){
				$http.post(apiName,content_type,pathParams,params,success,failure);
			};
			break;
		case "PUT":
			func = function(pathParams,params,success,failure){
				$http.put(apiName,content_type,pathParams,params,success,failure);
			};
			break;
		default:
			break;
	}

	return func;
}




module.exports = $httpService;
