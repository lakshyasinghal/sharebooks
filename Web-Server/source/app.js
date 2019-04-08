const cache = require("./server/fileCache/fileCache.js");
const server = require("./server/server.js");

function configureCache(){
	cache.setCache();
}

function startServer(){
	server.start();
}


configureCache();
startServer();



