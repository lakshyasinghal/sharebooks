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
			try{
				user = JSON.parse(data.user);
			}
			catch(err){
				user = data.user;
			}
		} 
		console.log("USER => ",user);
		req.session.user = user;
	}))();
});


router.get('/logout',function(req,res){
	console.log("Logout request received");
	req.session.destroy();
	res.redirect("/");
});

router.put('/users',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

router.post('/users/:uid/profile',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});


router.post('/users', function(req,res){
	
});


router.post('/users/:id([0-9]+)',function(req,res){
	
});

router.post('/users/:uid/preferences',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
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




