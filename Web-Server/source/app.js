const express = require("express");
const bookRouter = require("./routes/dynamic/bookRouter.js");
//const config = require("./config/app.config.js");


//var $config = null;

var app = express();




function routing(){
	app.use('/static' , express.static('public/pages'));
	app.use('/static' , express.static('public/styles'));
	app.use('/static' , express.static('public/view-scripts'));
	app.use('/static' , express.static('public/resources/images'));
	app.use('/static' , express.static('public/resources/images/background'));
	app.use('/static' , express.static('public/resources/images/books'));


	app.get('/',function(req,res){
		res.send("WELCOME TO SHAREBOOKS");
	})

	//app.use('',bookRouter);


	var server = app.listen(8090);
}



routing();

// function config(){
// 	try {
// 		$config = config();
// 	} catch (err) {

// 	}
// }
