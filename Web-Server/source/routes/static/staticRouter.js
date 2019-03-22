//This router will be used to serve html pages for their corresponding view urls

const express = require("express");
const fs = require('fs');
const router = express.Router();

const foldPath = "./../html/";



fs.readdirSync(foldPath, function(err , files){
	if(err){

	}
	else{
		files.forEach(function(file){
			
		})
	}
})

//router.get('home');