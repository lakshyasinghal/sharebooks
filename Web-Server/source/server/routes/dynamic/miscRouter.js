const express = require("express");
const path = require("path");
const dummyServer = require(path.join(appRoot,"public/scripts/data/dataRequestServer.js"));

const router = express.Router();





router.get('/notifications',function(req,res){
	console.log("Get notifications request received");
	const notifications = dummyServer.getNotifications(1); //get notifications for user id 1
	res.json({success:true,statusCode:34,notifications:notifications});
});


module.exports = router;