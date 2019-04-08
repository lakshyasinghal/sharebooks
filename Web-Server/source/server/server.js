const express = require("express");
const staticRoutes = require("./routes/static/staticRoutes.js");



function start(){
	var app = express();
	staticRoutes.run(app);
	var server = app.listen(8090);
	//console.log('server =>',server);
}


module.exports = {start};