const express = require("express");
const path = require("path");
const requestGenerator = require(path.join(appRoot,"/server/http/httpClient.js"));
//const enums = require(path.join(appRoot+"/models/enums/enums"));

const router = express.Router();


router.put('/quotes',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

//for updating the quote
router.post('/quotes/:uid',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

//for confirming the quote
router.post('/quotes/confirm/:uid',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

router.get('/quotes/:uid',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

// for getting quote summary
router.get('/quotes/summary/:uid',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

module.exports = router;