/*This file will contain the code to serve html pages for their corresponding view urls and other static resources such as 
 images,css,js */

const express = require("express");
const path = require("path");
const router = express.Router();


/*This is a closure function which will generate function for getting pages
The redirect boolean value and redirectionURL will be used in case redirection is required
 */
function viewLocator(page,sessionRequired,redirect,redirectionURL){
	return (function(req,res){
		var session = req.session;
		console.log("Session => ",session);
		//if session is required and session user does not exist, redirect to login page
		if(sessionRequired && !session.user){
			res.redirect('/');
		}
		else if(redirect){
			res.redirect(redirectionURL);
		}
		else{
			res.sendFile(path.join(appRoot +'/public/pages/'+page));   //appRoot is the global property
		}
	});
}

(function createPageRoutes(){
	//router.get('/login',viewLocator("login.html",false));
	router.get('/about',viewLocator("about.html",true));
	router.get('/complaints',viewLocator("complaints.html",true));
	router.get('/preferences',viewLocator("preferences.html",true));
	router.get('/profile',viewLocator("profile.html",true));
	router.get('/reset',viewLocator("reset.html",true));
	router.get('/home',viewLocator("home.html",true));
	router.get('/addBook',viewLocator("addBook.html",true));
	router.get('/results',viewLocator("results.html",true));
	router.get('/checkout',viewLocator("checkout.html",true));	
	router.get('/confirmation',viewLocator("confirmation.html",true));
	router.get('/feedback',viewLocator("feedback.html",true));	
	router.get('/',viewLocator("login.html",false));
	router.get('*',viewLocator("url_error.html",false));
	//adding router to the app
})();



module.exports = router;


