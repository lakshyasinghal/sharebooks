const express = require("express");


function Router(){
	const expRouter = express.Router();

	this.init = function(){
		expRouter.get('/api/users' , function(req , res){
			res.send("All users");
		});


		expRouter.get('/api/user/:id',function(req,res){
			res.send("User with id : " + req.params.id);
		});


		expRouter.put('/api/user' , function(req,res){
			res.send("Create user : ");
		});


		expRouter.post('/api/user' , function(req,res){
			res.send("Update user with id: " + req.params.id);
		});


		expRouter.delete('/api/user' , function(){
			res.send("Delete user with id:" + req.params.id);
		});
	};
}



module.exports = new Router();