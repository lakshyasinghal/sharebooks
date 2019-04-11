const express = require("express");
const bodyParser = require("body-parser");
const cookieParser = require("cookie-parser");
const session = require("express-session");
const routers = require("./routes/router");

const app = express();


function start(){
	configure();
	addStaticResources();
	addRouters();

	var server = app.listen(config.port,function(){
		console.log("Server started on port ",config.port);
	});
}

function configure(){
	app.use(bodyParser.json());
	app.use(bodyParser.urlencoded({extended:true}));
	app.use(cookieParser());
	//needs to be modified and made robust
	app.use(session({secret:config.sessionSecret,key:"",resave: true,saveUninitialized: false}));
}


function addRouters(){
	//the order is important here
	app.use('/api',routers.userRouter);
	app.use('/api',routers.bookRouter);
	app.use('/api',routers.fileUploader);
	addNoAPIHandlers();
	app.use('/',routers.staticRouter);
}

function addNoAPIHandlers(){
	app.post("/api/*",function(req,res){
		res.status(400).json({success:false,statusCode:0});
	});
	app.get("/api/*",function(req,res){
		res.status(400).json({success:false,statusCode:0});
	});
}


function addStaticResources(){
	//get all static resource paths and add them
	const paths = config.staticResourcePaths;
	for(var i=0,l=paths.length;i<l;i++){
		app.use('/static',express.static(paths[i]));
	}
}


module.exports = {start};



