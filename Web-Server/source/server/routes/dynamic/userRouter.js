const path = require("path");
const express = require("express");
const axios = require("axios");
const qs = require("qs");
const requestGenerator = require(path.join(appRoot,"/server/http/httpClient.js"));
const enums = require(path.join(appRoot+"/models/enums/enums"));

const router = express.Router();

const STATUS_CODES = enums.STATUS_CODES;

router.post('/login',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res, function(data){
		const statusCode = data.statusCode;
		var user = null;
		if(statusCode==STATUS_CODES.LOGIN_SUCCESSFUL){
			user = JSON.parse(data.user);
		} 
		console.log("USER => ",user);
		req.session.user = user;
	}))();
});

//logout api
router.get('/logout',function(req,res){
	console.log("Logout request received");
	req.session.destroy();
	res.redirect("/");
});

router.put('/users',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

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




