const path = require("path");
const express = require("express");
const multer  =   require('multer');
const requestGenerator = require(path.join(appRoot,"/server/http/httpClient.js"));
//const enums = require(path.join(appRoot+"/models/enums/enums"));

const router = express.Router();


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
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

//get book by uid
router.get('/books/:uid',function(req,res){
	res.send("Book with uid : " + req.params.uid);
});

//get books with specified search term
router.get('/books/search/:searchTerm',function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});


//get book categories
router.get('/book-categories',function(req , res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});


//add new book
router.put('/books' , function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
});

//get books filtered by category
router.get('/books/category/:category' , function(req,res){
	(requestGenerator.generateAxiosRequestFunc(req, res))();
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