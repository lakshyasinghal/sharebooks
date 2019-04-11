const express = require("express");

var router = express.Router();


//api 1
router.post('/users', function(req,res){
	console.log("Request received on users api 1 => ", req);
});

//api 2
router.post('/users/:id([0-9]+)',function(req,res){
	console.log("Request received on users api 2 => ", req);
	//make http request to application server
});

//api 3
router.post('/login',function(req,res){
	console.log("Request received on users api 3");
	console.log("Request body =>",req.body);
	const username = req.body.username;
	const password = req.body.password;

	if(username=='lakshya33' && password=="champion"){
		req.session.user = {};
		res.json({success:true});
	}
	else{
		res.json({success:false,statusCode:10});
	}
});

//api 4
router.get('/users/:id',function(req,res){
	//console.log("Request received on users api 4 => ", req.query);
	res.status(200).send("Hahahaha");
});



function sessionValidatorHandler(callback){

	return function(req,res){
		var session = req.session;
		if(!session.user){
			res.json({error:true,statusCode:0});
			return;
		}

		console.log("params => ",req.params);
		console.log("body => ",req.body);
	}

}



module.exports = router;




