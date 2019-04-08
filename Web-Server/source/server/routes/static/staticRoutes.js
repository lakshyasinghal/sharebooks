/*This file will contain the code to serve html pages for their corresponding view urls and other static resources such as 
 images,css,js */

const express = require("express");


function run(app){
	createPageRoutes(app);
	addImageResources(app);
	addCSSResources(app);
	addJSResources(app);
}

function pageRouteFactory(page){
	return (function(req,res){
		res.writeHead(200,{'Content-Type': 'text/html'});
		res.end(global.pageCache[page]);
	});
}

function createPageRoutes(app){
	app.get('/login',pageRouteFactory("login"));
	app.get('/about',pageRouteFactory("about"));
	app.get('/complaints',pageRouteFactory("complaints"));
	app.get('/preferences',pageRouteFactory("preferences"));
	app.get('/profile',pageRouteFactory("profile"));
	app.get('/reset',pageRouteFactory("reset"));
	app.get('/home',pageRouteFactory("home"));
	app.get('/results',pageRouteFactory("results"));
	app.get('/checkout',pageRouteFactory("checkout"));	
	app.get('/confirmation',pageRouteFactory("confirmation"));
	app.get('/feedback',pageRouteFactory("feedback"));	
}

function addImageResources(app){
	app.use('/static' , express.static('public/resources/images'));
	app.use('/static' , express.static('public/resources/images/background'));
	app.use('/static' , express.static('public/resources/images/books'));
}

function addCSSResources(app){
	app.use('/static' , express.static('public/styles'));
}

function addJSResources(app){
	app.use('/static' , express.static('public/view-scripts'));
}



module.exports = {run};


