/*This file will contain the code to serve html pages for their corresponding view urls and other static resources such as 
 images,css,js */

const express = require("express");
const path = require("path");
const router = express.Router();

function run(app){
	addHTMLResources(app);
	addImageResources(app);
	addCSSResources(app);
	addJSResources(app);
	addPages(app);

	createPageRoutes(app);
}


/*This is a closure function which will generate function for getting pages
The redirect boolean value and redirectionURL will be used in case redirection is required
 */
function pageRouteFactory(page,redirect,redirectionURL){
	return (function(req,res){
		// res.writeHead(200,{'Content-Type': 'text/html'});
		// res.end(global.pageCache[page]);
		console.log(appRoot);
		if(redirect){
			res.redirect(redirectionURL);
		}
		else{
			res.sendFile(path.join(appRoot +'/public/pages/'+page));
		}
	});
}

function createPageRoutes(app){
	router.get('/login',pageRouteFactory("login.html"));
	router.get('/about',pageRouteFactory("about.html"));
	router.get('/complaints',pageRouteFactory("complaints.html"));
	router.get('/preferences',pageRouteFactory("preferences.html"));
	router.get('/profile',pageRouteFactory("profile.html"));
	router.get('/reset',pageRouteFactory("reset.html"));
	router.get('/home',pageRouteFactory("home.html"));
	router.get('/addBook',pageRouteFactory("addBook.html"));
	router.get('/results',pageRouteFactory("results.html"));
	router.get('/checkout',pageRouteFactory("checkout.html"));	
	router.get('/confirmation',pageRouteFactory("confirmation.html"));
	router.get('/feedback',pageRouteFactory("feedback.html"));	
	router.get('/',pageRouteFactory(null,true,"/login"));

	//adding router to the app
	app.use('/',router);
}


function addHTMLResources(app){
	app.use(express.static("/public/pages"));
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

function addPages(app){
	app.use('/static' , express.static('public/pages'));
}

module.exports = {run};


