const qs = require("qs");
const path = require("path");
const axios = require("axios");
const util = require(path.join(appRoot+"/models/utils/utils"));
const enums = require(path.join(appRoot+"/models/enums/enums"));

const REQUEST_TYPE = enums.REQUEST_TYPE;
const CONTENT_TYPE = enums.CONTENT_TYPE;
const ERROR_CODES = enums.ERROR_CODES;
/*NOTES*/
/* */



function generateAxiosRequestFunc(req , res , request_type ){
	const url = util.appBaseURL() + req.originalUrl;
	/*we are interested only in content-type header
	the rest of the headers will be self managed*/
	const headers = {"content-type":req.headers['content-type']};
	//get content type
	const content_type = util.getContentType(req.headers['content-type']);
	const body = req.body;
	const query = req.query;

	//if content type is url encoded data must be converted to query string otherwise can be sent as json
	var data = body;
	if(content_type==CONTENT_TYPE.FORM){
		data = qs.stringify(body);
	}


	var axiosRequest;
	if(request_type==REQUEST_TYPE.GET){
		axiosRequest = function(){
			axios.get(url ,data, headers)
			.then(generateResponseHandler(res))
			.catch(generateErrorHandler(res));
		};
	}
	else if(request_type==REQUEST_TYPE.POST){
		axiosRequest = function(){
			axios.post(url ,data, headers)
			.then(generateResponseHandler(res))
			.catch(generateErrorHandler(res));
		};
	}
	else if(request_type==REQUEST_TYPE.PUT){
		axiosRequest = function(){
			axios.put(url ,data, headers)
			.then(generateResponseHandler(res))
			.catch(generateErrorHandler(res));
		};
	}

	return axiosRequest;
}




/*the res is the original express request response object 
the closure function will provide the res object in the closure environment which otherwise wouldn't have been possible*/
function generateResponseHandler(res){
	/*We will send the status and data in res object available in response object. We don't need to do anything fancy here. */
	return (function(response){
		const status = response.status;
		const data = response.data;
		console.log("status =>",status);
		console.log("data =>",data);
		res.status(status).json(data);
	});
}




/*the res is the original express request response object 
the closure function will provide the res object in the closure environment which otherwise wouldn't have been possible*/  
function generateErrorHandler(res){

	/*We are going to return the response status and data as it is if available otherwise we will try to figure out
	the exception conditions and send a custom response */
	return  (function(error){
		try{
			const errorMessage = error.message;
			console.log("errorMessage =>",errorMessage);
			const response = error.response;
			var status,data;
			if(response){
				status = response.status;
				data = response.data;
				console.log("status =>",status);
				console.log("data =>",data);
				res.status(status).json(data);
			} 
			else{
				var error_type = util.getRequestErrorType(errorMessage);
				console.log("error_type =>",error_type);
				var statusCode = ERROR_CODES[error_type];
				console.log("error_code =>",statusCode);
				res.status(500).json(util.dataJson(statusCode));
			}
		}
		catch(err){
			//if nothing fits and exception occurs
			res.status(500).json(util.dataJson(ERROR_CODES.MYSTERIOUS));
		}
	});
}



module.exports = {generateAxiosRequestFunc};

