import React from "react";
import ReactDOM from "react-dom";
import Header from "./modules/header.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import $storage from "./../scripts/utility/utility.js";
const $pages = $config.$pages;
const $sm = $config.$sm;


class Complaint extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="mainContainer">
				<Header bookAdderDisplay={"none"} bookBrowserDisplay={"none"}/>

				<h2 className="center-align heading">What Happened? Please Tell Us Everything.</h2>

				<div id="complaintContainer">
					<div className="text">Complaint</div>
					<textarea rows="10" cols="125" id="complaintBox"></textarea>
				</div>

				<div id="buttonContainer">
					<button className="btn btn-danger" id="submitBtn">Send Mail</button>
				</div>
			</div>
		);
	}
}



ReactDOM.render(<Complaint/> , document.getElementById('app'));



