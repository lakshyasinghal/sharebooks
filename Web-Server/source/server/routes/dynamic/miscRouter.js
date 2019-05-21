const express = require("express");
const path = require("path");
const requestGenerator = require(path.join(appRoot,"/server/http/httpClient.js"));
//const enums = require(path.join(appRoot+"/models/enums/enums"));

const router = express.Router();

router.put('/feedbacks/:userUid',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

module.exports = router;