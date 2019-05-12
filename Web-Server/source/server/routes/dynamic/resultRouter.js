const path = require("path");
const express = require("express");
const multer  =   require('multer');
const requestGenerator = require(path.join(appRoot,"/server/http/httpClient.js"));
//const enums = require(path.join(appRoot+"/models/enums/enums"));

const router = express.Router();





/* get result */
router.get('/results/:bookUid' , function(req , res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

//get book by uid
router.get('/results',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});


	





module.exports = router;