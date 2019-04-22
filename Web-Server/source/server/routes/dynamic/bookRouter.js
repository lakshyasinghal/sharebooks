const path = require("path");
const express = require("express");
const multer  =   require('multer');
const axios = require("axios");
const requestGenerator = require(path.join(appRoot,"/server/http/httpClient.js"));
const enums = require(path.join(appRoot+"/models/enums/enums"));
//const dummyServer = require(path.join(appRoot,"public/scripts/data/dataRequestServer.js"));
const router = express.Router();

const STATUS_CODES = enums.STATUS_CODES;

//variables for uploading files
const uploadsDir = global.config.bookUploadsDir;
const storage =   multer.diskStorage({
  destination: function (req, file, callback) {
    callback(null, uploadsDir);
  },
  filename: function (req, file, callback) {
    callback(null, file.originalname);
  }
});
const upload = multer({ storage : storage}).single('bookImage');


/* get all books */
router.get('/books' , function(req , res){
	console.log("request received at books api 1");
	res.json({success:true,books:[]});
	//res.json(dummyServer.getAllBooks());
});


router.get('/books/:id([0-9]+)',function(req,res){
	res.send("Book with id : " + req.params.id);
});

//add new book
router.put('/books' , function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

//get books filtered by category
router.get('/books/category' , function(req,res){
	const category = req.query.category;
	console.log("request received");
	console.log("category =>",category);
	const books = dummyServer.filterByCategory(category);
	res.json({success:true,statusCode:13,books:books});
});

//adding image for book
// router.post('/bookImage',function(req,res){
//   console.log("Upload photo reequest received");
//   upload(req,res,function(err) {
//     if(err) {
//       console.log("error occurred - ",err);
//       return res.end("Error uploading file.");
//     }
//     console.log("File uploaded successfully");
//     res.end("File is uploaded");
//   });
// });  


router.post('/books/:id' , function(req,res){
	res.send("Update book with id: " + req.params.id);
});


router.delete('/books/:id' , function(){
	res.send("Delete book with id:" + req.params.id);
});
	





module.exports = router;