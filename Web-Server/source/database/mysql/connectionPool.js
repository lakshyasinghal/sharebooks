var mysql = require('mysql');

var connectionPool = null;


function createConnectionPool(){
	const mySql = config.mySQL;

	// connectionPool = mysql.createPool({
	// 	connectionLimit : 100,
	// 	host: mySql.host,
	// 	user: mySQL.user,
	// 	password: mySQL.password,
	// 	database: mySQL.database
	// });

	connectionPool = mysql.createPool({
		connectionLimit : 100,
		host: 'localhost',
		user: 'lakshyasinghal33',
		password: 'nitj1010',
		database: 'sharebooks_web'
	});
}


function getConnectionPool(){
	if(connectionPool){
		console.log("Creating connection pool.");
		createConnectionPool();
	}

	return connectionPool;
}



module.exports = getConnectionPool();