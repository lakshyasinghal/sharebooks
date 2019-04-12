const path = require("path");
const express = require("express");
const axios = require("axios");
const qs = require("qs");
const requestGenerator = require(path.join(appRoot,"/server/http/httpClient.js"));
const enums = require(path.join(appRoot+"/models/enums/enums"));

const router = express.Router();
const REQUEST_TYPE = enums.REQUEST_TYPE;

router.post('/login',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res, REQUEST_TYPE.POST))();
});

// router.post('/login',function(req,res){
// 	console.log("Request received on users api 1");
// 	// console.log("Request body =>",req.body);
// 	// console.log("Request headers =>",req.headers['content-type']);
// 	// console.log("Request protocol =>",req.protocol);
// 	// console.log("Request host =>",req.headers.host);
// 	// console.log("Request url =>",req.url);
// 	console.log("Request url =>",req.originalUrl);

// 	const username = req.body.username;
// 	const password = req.body.password;

// 	var url = 'http://' + config.appServer.host + ':' + config.appServer.port + '/api' + '/login?name=lakshya';
// 	var contentType = req.headers['content-type'];
// 	var headers = {'content-type':req.headers['content-type']};
// 	var data = {username:username,password:password};


// 	axios.get(url ,qs.stringify(data), headers)
// 	.then(function(response){
// 		console.log("Data => ",response.data);
// 		console.log("Status => ",response.status);
// 	})
// 	.catch(function(error){
// 		console.log("Error message =>",error.message);
// 		console.log("Error response status=>",error.response);
// 		// console.log("Error response status=>",error.response.status);
// 		// console.log("Error response data=>",error.response.data);
// 	});


// });



//api 2
router.post('/users', function(req,res){
	console.log("Request received on users api 2 => ", req);
});

//api 3
router.post('/users/:id([0-9]+)',function(req,res){
	console.log("Request received on users api 3 => ", req);
	//make http request to application server
});



//api 4
router.get('/users/:id',function(req,res){
	//console.log("Request received on users api 4 => ", req.query);
});



// function sessionValidatorHandler(callback){

// 	return function(req,res){
// 		var session = req.session;
// 		if(!session.user){
// 			res.json({error:true,statusCode:0});
// 			return;
// 		}

// 		console.log("params => ",req.params);
// 		console.log("body => ",req.body);
// 	}

// }



module.exports = router;




