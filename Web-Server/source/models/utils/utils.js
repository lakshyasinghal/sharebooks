const path = require("path");
const enums = require(path.join(appRoot+"/models/enums/enums"));

const REQUEST_ERROR_TYPE = enums.REQUEST_ERROR_TYPE;
const ERROR_CODES = enums.ERROR_CODES;
const CONTENT_TYPE = enums.CONTENT_TYPE;

//will return base url of type http://192.168.16.50:8000
function appBaseURL(){
	const appServer = config.appServer;
	const protocol = appServer.protocol;
	const host = appServer.host;
	const port = appServer.port;

	return protocol+'://'+host+':'+port;
}

//will return one of the values fom request error type enum using the error message
function getRequestErrorType(errMess){
	var error_type;
	if(errMess.indexOf("ECONNREFUSED")>-1){
		error_type = REQUEST_ERROR_TYPE.CONNECTION_ERROR;
	}
	else{
		error_type = REQUEST_ERROR_TYPE.UNKNOWN;
	}

	return error_type;
}


function dataJson(dev_status_code , user_status_code){
	if(!user_status_code){
		user_status_code = ERROR_CODES.SOMETHING_WRONG;
	}
	return {dev_status_code:dev_status_code,user_status_code:user_status_code};
}

/*source will be of type application/json;charSet-UTF8 */
function getContentType(source){
	var content_type;
	if(source.indexOf(CONTENT_TYPE.JSON)>-1){
		content_type = CONTENT_TYPE.JSON;
	}
	else if(source.indexOf(CONTENT_TYPE.FORM)>-1){
		content_type = CONTENT_TYPE.FORM;
	}

	return content_type;
}

module.exports = {appBaseURL,getRequestErrorType,dataJson,getContentType};


