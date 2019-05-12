const assert = require('assert');
const path = require("path");

//const appRoot = path.resolve(_dirname);
const util = require('/Users/lakshya.singhal/Desktop/Personal/Tech/Apps/sharebooks/Web-Server/source/public/scripts/utility/utility.js');






/*------------------------------------basic test function-------------------------------------*/

function add(a,b){
	return a+b;
}


describe('Basic', function(){
	describe('add', function(){
		it('should return 5 with 2 and 3', function(){
			assert.equal(add(2,3), 5);
		});
	});
});


/*---------------------------------------------------------------------------------------------*/

// describe('Util', function(){
// 	describe('#clone()', function(){
// 		it('should return clone of an object', function(){
// 			var testObj = {name:'lakshya',age:28};

// 		});
// 	});
// });