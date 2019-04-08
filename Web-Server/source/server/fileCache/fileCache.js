var fs = require("fs");

const htmlDirPath = "/Users/lakshya.singhal/Desktop/Personal/Tech/Apps/sharebooks/Web-Server/source/public/pages/";
const htmlFiles = ["about.html","checkout.html","complaints.html","confirmation.html","feedback.html","home.html","addBook.html","login.html",
"preferences.html","profile.html","reset.html","results.html"];

global.pageCache = {};


function readHTMLFiles(){
	for(let i=0,len=htmlFiles.length;i<len;i++){
		var htmlFile = htmlFiles[i];
		var filePath = htmlDirPath + htmlFile;
		var page = htmlFile.split('.html')[0];
		var data = fs.readFileSync(filePath);
		pageCache[page] = data.toString();
	}
}


function setCache(){
	readHTMLFiles();
}

module.exports = {setCache};
