/**
* The functions in this file will read the app config json file and will provide an interface to use the properties.
*/

var fs = require("fs");




function config(){
	var config = fs.readFileInSync("./app.config.js");
	config = JSON.parse(config);
	var validation = validateConfig(config);
	if(!validation.success){
		throw validation.errMess;
	}
	return config;
}



/**
 * will validate all config properties in a recursive manner
 * @return {success:false,errMess:"Empty property host"} [description]
 */
function validate(){
	return {success:true};
}





module.exports = config;