const cache = require("./server/fileCache/fileCache.js");
const server = require("./server/server.js");
const path = require("path");

global.appRoot = path.resolve(__dirname);
global.config = require("./config/app.config.json");


function configureCache(){
	cache.setCache();
}

function runServer(){
	//console.log(config.port);
	server.run(config.port);
}


configureCache();
runServer();



