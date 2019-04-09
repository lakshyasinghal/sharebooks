//var express =   require("express");
var multer  =   require('multer');

const uploadsDir = "/Users/lakshya.singhal/Desktop/uploads";

var storage =   multer.diskStorage({
  destination: function (req, file, callback) {  
    console.log("uploads dir path => ",__dirname);
    callback(null, uploadsDir);
  },
  filename: function (req, file, callback) {
    callback(null, file.originalname);
  }
});

//var upload = multer({ storage : storage}).single('bookPhoto');

var upload = multer({ storage : storage}).single('bookImage');

function configure(app){

 app.post('/api/bookImage',function(req,res){
  console.log("Upload photo reequest received");
  upload(req,res,function(err) {
    if(err) {
      console.log("error occurred - ",err);
      return res.end("Error uploading file.");
    }
    console.log("File uploaded successfully");
    res.end("File is uploaded");
  });
});  

}





module.exports = {configure};