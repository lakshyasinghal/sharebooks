var $logger = require("./../logs/log.js");

const getURLPathParamByIndex = function(index){
	var param = window.location.pathname.split('/')[index];
	return param;
};

const $cookie = (function(){

	function CookiesHandler(){
		this.get = function(cookieName){
			var name = cookieName + "=";
			var cookiesArray = document.cookie.split(';');
			var currentCookie;
			for(var i=0 ; i<cookiesArray.length ; i++){
				currentCookie = cookiesArray[i];
				var tokens = currentCookie.split('=');
				if(tokens[0].trim() == cookieName){
					return tokens[1].trim();
				}
			}
			return null;
		};

		this.set = function(cookieName , cookieValue , days , path){
			if(!days){
				days = 0;
			}
			var expires = "";
			if(days != undefined){
				var date = new Date();
				date.setTime(date.getTime() + (days*24*60*60*1000));
				expires = date.toGMTString();
			}
			else{
				expires = "";
			}
			document.cookie = cookieName + "=" + cookieValue + "; expires=" + expires + "; path=/" + path;
		};

		this.delete = function(cookieName){
			try{
				
			}
			catch(err){
				//$logger.log("Error in delete in cookiesHandler - " + err.message);
			}
		};
	}
	return new CookiesHandler();
})();


//the storgae function will store item in session storage if available else in cookie
//Will return object if the item is json object
const $storage = (function(){

	function Storage(){
		this.set = function(key,value,storageType){
			if(value instanceof Object){
				value = JSON.stringify(value);
			}
			if(window.sessionStorage){
				sessionStorage.setItem(key, value);
			}
			else{
				$cookie.set(key,value);
			}
		};

		this.get = function(key){
			var item;
			if(window.sessionStorage){item = sessionStorage.getItem(key);}
			else{item = $cookie.get(key);}

			//try to parse if it is a stringified json object 
			try{item = JSON.parse(item);}
			catch(err){}

			return item;
		}
	}
	return new Storage();
})();


Array.prototype.copy = function(){

}


const clone = function(obj){
	//debugger;
	var clonedObj = null;
	if(obj instanceof Array){
		clonedObj = [];
		for(var i=0,len=obj.length;i<len;i++){
			clonedObj[i] = clone(obj[i]);
		}
	}
	else if(obj instanceof Object){
		clonedObj = {};
		for(var key in obj){
			clonedObj[key] = clone(obj[key]);
		}
	}
	else{
		clonedObj = obj;
	}
	return clonedObj;
}



//needs implementation
function isValidEmail(email){
	// var regex = /^[a-Z]{1,}[0-9]*[_]?[a-Z0-9]*@[a-Z]{2,15}\.[a-Z]{2,5}$/;
	// return regex.test(str);
	return true;
}

//needs implementation
function isValidPassword(password){
	return password!=undefined && password.length>6;
}

//needs implementation
function isValidDOB(dob){
	return true;
}

function isValidName(name){
	return true;
}

function isValidCity(city){
	return true;
}

function isValidState(state){
	return true;
}

function isValidPincode(pincode){
	return true;
}

function isValidContactNo(contactNo){
	return true;
}


var $validations = {
	isValidEmail:isValidEmail,
	isValidPassword:isValidPassword,
	isValidDOB:isValidDOB,
	isValidName:isValidName,
	isValidCity:isValidCity,
	isValidState:isValidState,
	isValidPincode:isValidPincode,
	isValidContactNo:isValidContactNo
}



var util = {
	$storage: $storage,
	$validations:$validations,
	clone:clone,
	getURLPathParamByIndex:getURLPathParamByIndex
};



module.exports = util;

