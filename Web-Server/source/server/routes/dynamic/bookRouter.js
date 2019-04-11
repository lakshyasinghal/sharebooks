const path = require("path");
const express = require("express");
const multer  =   require('multer');
const dummyServer = require(path.join(appRoot,"public/scripts/data/dataRequestServer.js"));
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
	console.log("request received at books api 1");
	res.json(dummyServer.getAllBooks());
});


router.get('/books/:id',function(req,res){
	res.send("Book with id : " + req.params.id);
});

//add new book
router.put('/books' , function(req,res){
	const bookName = req.body.name;
	console.log("request received at books api 3=>",req.body);
	console.log("request received at books api 3 for book=>",bookName);
	res.json({success:true,statusCode:13});
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