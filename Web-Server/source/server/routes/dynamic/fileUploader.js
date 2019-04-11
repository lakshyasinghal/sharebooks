const express =   require("express");
const multer  =   require('multer');
const router = express.Router();

const uploadsDir = global.config.bookUploadsDir;

var storage =   multer.diskStorage({
  destination: function (req, file, callback) {
    callback(null, uploadsDir);
  },
  filename: function (req, file, callback) {
    callback(null, file.originalname);
  }
});

//var upload = multer({ storage : storage}).single('bookPhoto');

var upload = multer({ storage : storage}).single('bookImage');



router.post('/bookImage',function(req,res){
  debugger;
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




module.exports = router;