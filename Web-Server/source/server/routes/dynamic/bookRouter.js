const express = require("express");


function Router(){
	const expRouter = express.Router();

	this.init = function(){
		expRouter.get('/api/books' , function(req , res){
			res.send("All books");
		});


		expRouter.get('/api/book/:id',function(req,res){
			res.send("Book with id : " + req.params.id);
		});


		expRouter.put('/api/book' , function(req,res){
			res.send("Create book");
		});


		expRouter.post('/api/book/:id' , function(req,res){
			res.send("Update book with id: " + req.params.id);
		});


		expRouter.delete('/api/book/:id' , function(){
			res.send("Delete book with id:" + req.params.id);
		});
	};
}





module.exports = new Router();