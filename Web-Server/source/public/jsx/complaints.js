import React from "react";
import ReactDOM from "react-dom";
import "./../lib/styles/bootstrap.css";
import "./../styles/common.css";
import "./../styles/complaints.css";



class ComplaintContainer extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="mainContainer">
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



ReactDOM.render(<ComplaintContainer/> , document.getElementById('app'));



