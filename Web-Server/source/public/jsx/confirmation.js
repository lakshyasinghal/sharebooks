import React from "react";
import ReactDOM from "react-dom";
import Header from "./modules/header.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";
const $pages = $config.$pages;
const $sm = $config.$sm;


class Confirmation extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	componentDidMount() {
	    this.events();
	}

	events(){
		//click on sure button will redirect to feedback page
		document.getElementById("sureBtn").addEventListener("click", ()=>{
			$pages.feedback();
		})

		//click on sure button will redirect to feedback page
		document.getElementById("laterBtn").addEventListener("click", ()=>{
			$pages.home();
		})
	}

	render(){
		const statusMessage = "Your request has been placed successfully. Please wait for confirmation from owner's side";

		return (
			<div id="mainContainer" className="center-align">

				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={false}/>

				<h3>Thank You For Using Sharebooks</h3>
				<br/><br/>
				<h4>{statusMessage}</h4>
				<br/><br/><br/><br/>
				<div id="ratingSection">
					<h3>Please Rate The App</h3>
					<br/>
					<button className="btn btn-info" id="sureBtn">Sure</button>
					<button className="btn btn-warning" id="laterBtn">Not now</button>
				</div>
			</div>
		);
	}
}



ReactDOM.render(<Confirmation/> , document.getElementById('app'));