/* jshint esversion: 6 */

const webpack = require('webpack');


const jsPath = "/Users/lakshya.singhal/Desktop/Personal/Tech/Apps/sharebooks/Web-Server/source/public/view-scripts";
const jsxPath = "/Users/lakshya.singhal/Desktop/Personal/Tech/Apps/sharebooks/Web-Server/source/public/jsx";
const _hotUpdateChunkFilename = './../hot/hot-update.js';
const _hotUpdateMainFilename = './../hot/hot-update.json';

const _resolve = {extensions: ['.jsx' , '.js' , '.es6']};
const _plugins = [new webpack.HotModuleReplacementPlugin()];
const _devServer = {hot:true};

const _module = {
			rules: [
			  {
			    test: /\.(js|jsx)$/,
			    exclude: /node_modules/,
			    use: ['babel-loader']
			  },
			  {
			    test: /\.css$/,
			    use: ['style-loader','css-loader']
			  },
			  {
			    test: /\.(pdf|jpg|png|gif|svg|ico)$/,
			    use: ['url-loader']
			  },
			]
		};



function addHotUpdateFilePath(){
	var temp_export = null;

	for(var i=0,len=_exports.length ; i<len ; i++){
		temp_export = _exports[i];
		temp_export.output.hotUpdateChunkFilename = _hotUpdateChunkFilename;
		temp_export.output.hotUpdateMainFilename = _hotUpdateMainFilename;
	}
}



var _exports = [{
	mode:"development",
	entry: jsxPath + "/about.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "about.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
},
{
	mode:"development",
	entry: jsxPath + "/requirements.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "requirements.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
},
{
	mode:"development",
	entry: jsxPath + "/complaints.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "complaints.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
},
{
	mode:"development",
	entry: jsxPath + "/confirmation.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "confirmation.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
},
{
	mode:"development",
	entry: jsxPath + "/feedback.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "feedback.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
},
{
	mode:"development",
	entry: jsxPath + "/home.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "home.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
},
{
	mode:"development",
	entry: jsxPath + "/login.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "login.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
},
{
	mode:"development",
	entry: jsxPath + "/preferences.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "preferences.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
},
{
	mode:"development",
	entry: jsxPath + "/profile.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "profile.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
},
{
	mode:"development",
	entry: jsxPath + "/reset.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "reset.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
},
{
	mode:"development",
	entry: jsxPath + "/results.js",
	output: {
	   path: jsPath,
	   publicPath: "/",
	   filename: "results.js"
	},
	module: _module,
	resolve: _resolve,
	plugins: _plugins,
	devServer: _devServer
}];

addHotUpdateFilePath();


module.exports = _exports;