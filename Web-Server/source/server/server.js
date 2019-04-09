const express = require("express");
const staticRoutes = require("./routes/static/staticRoutes.js");
const fileUploader = require("./routes/dynamic/fileUploader.js");

const app = express();

function configure(){
	staticRoutes.run(app);
}

function start(port){
	var server = app.listen(port);
}

function run(port){
	// configure();
	// start(port);
	var app = express();
	staticRoutes.run(app);
	fileUploader.configure(app);
	var server = app.listen(port);
}




module.exports = {run};