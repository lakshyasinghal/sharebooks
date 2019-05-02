import React from "react";
import ReactDOM from "react-dom";
import Header from "./modules/header.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";
const $pages = $config.$pages;
const $sm = $config.$sm;



class Complaint extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			complaintText: ""
		};
	}

	componentDidMount() {
	    this.events();
	}

	events(){
		document.getElementById("complaintBox").addEventListener("change", e=>{
			e.preventDefault();
			e.stopPropagation();
			this.setState({complaintText: e.target.value});
		});

		document.getElementById("submitBtn").addEventListener("click", ()=>{this.submit();})
	}

	isSubmitValid(complaintText){
		debugger;
		if(complaintText=="" && complaintText.length<50){
			alert("Text cannot be less than 100 characters");
			return false;
		}
		return true;
	}

	submit(){
		let complaintText = this.state.complaintText.trim();
		if(!this.isSubmitValid(complaintText)){return ;}
		$httpService.saveComplaint([],{complaint:complaintText} , res=>{
			alert("Complaints saved successfully");
		});
	}


	render(){
		return (
			<div id="mainContainer">
				<Header homeDisplay={true} profileDisplay={false} adderDisplay={false} browserDisplay={false} notifDisplay={false}/>

				<h2 className="center-align heading">What Happened? Please Tell Us Everything</h2>

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



