const path = require("path");

global.appRoot = path.resolve(__dirname);
global.config = require("./config/app.config.json");

const server = require("./server/server.js");


function startServer(){
	server.start();
}

startServer();



