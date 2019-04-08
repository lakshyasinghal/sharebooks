const express = require("express");

var app = express();


function run(){
	console.log("in here");
	app.use('/static' , express.static('public/pages'));
}

const router = {
	run:run
};

module.exports = router;