//const connectionPool = require("./connectionPool.js");
const mysql = require("mysql");

const connection = mysql.createConnection({
	host: 'localhost',
	user: 'lakshyasinghal33',
	password: 'nitj1010',
	database: 'sharebooks_web'
});

const propertyDao = {

	getProperties: function(){
		const properties = {};
		connection.connect(function(err){
			if(!err){
				console.log("Connected successfully.");
			}
			else{
				console.log("Error in connecting.");
			}
		});
		

		connection.query("Select * From WebProperties", function(err, rows){
			connection.end();
			if(!err){
				rows.forEach(function(row){
					properties[row.name] = row.value;
				});
			}
		});
		

		return properties;
	}
}


// const propertyDao = {

// 	getProperties: function(){
// 		const properties = {};
// 		connectionPool.getConnection(function(err,connection){
// 			if(err){
// 				console.log("Error in getting conection.");
// 				return ;
// 			}

// 			connection.on('error', function(err){
// 				console.log("Error in sql operation.");
// 			});

// 			connection.query("Select * From WebProperties", function(err, rows){
// 				connection.release();
// 				if(!err){
// 					rows.forEach(function(row){
// 						properties[row.name] = row.value;
// 					});
// 				}
// 			});
// 		});

// 		return properties;
// 	}
// }


module.exports = propertyDao;

