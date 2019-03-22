import React from "react";
import ReactDOM from "react-dom";
import "./../lib/styles/bootstrap.css";
import "./../styles/common.css";
import "./../styles/confirmation.css";



class Confirmation extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="mainContainer" className="center-align">
				<h3>Thank You For Using Sharebooks</h3>
				<br/><br/>
				<h4>Your request has been placed successfully. Please wait for confirmation from owner's side.</h4>
				<br/><br/><br/><br/>
				<div id="ratingSection">
					<h3>Please Rate Our App</h3>
					<br/>
					<button className="btn btn-info" id="sureBtn">Sure</button>
					<button className="btn btn-warning" id="laterBtn">Not now</button>
				</div>
			</div>
		);
	}
}



ReactDOM.render(<Confirmation/> , document.getElementById('app'));