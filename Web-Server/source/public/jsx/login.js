import React from "react";
import ReactDOM from "react-dom";

import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";

const util = require("./../scripts/utility/utility.js");
const $pages = $config.$pages;
const $sm = $config.$sm;


class Login extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			signUpFormDisplay:false
		};
		this.toggleSignUp = this.toggleSignUp.bind(this);
	}

	toggleSignUp(){
		this.setState((prevState)=> ({signUpFormDisplay:!prevState.signUpFormDisplay}));
	}

	render() {
		return (
			<div>
				<Header toggleSignUp={this.toggleSignUp}/>

				<SignInPanel />
				<SignUpPanel ref="signUpPanel" display={this.state.signUpFormDisplay} />
			</div>
		);
	}
}
//



function Header(props){
	return (
		<div id="header">
			<span id="title">SHAREBOOKS</span>

			<div id="signUpButton" onClick={props.toggleSignUp}>
				<span>Sign Up</span>
			</div>										
		</div>
	);
}


//Sign in panel for logging in with username and password
class SignInPanel extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			statusMessage: '',
			displayMessage: false,
			form:{
				username:"",
				password:""
			}
		};
		this.signIn = this.signIn.bind(this);
	}

	componentDidMount(){
		let formElemIds = ["signIn_username","signIn_password"];
		for(let i = 0, len = formElemIds.length; i < len; i++){
			let elemId = formElemIds[i];
			document.getElementById(elemId).addEventListener("change", (e)=>{
				updateFormValues(e,this);
			});
		}
	}

	forgotPassword(){

	}

	signIn(){
		var vals = this.state.form;
		//this.displayMessage("Bad request","message-warn");
		var validity = validateSignInValues(vals);
		if(!validity.allValid){
			this.setState({statusMessage:validity.message,displayMessage:true});
			return;
		}
		$httpService.signIn(vals, data => {
			data = JSON.parse(data);

            if(data.success){
                util.$storage.set("user",data.results[0]);
                $pages.home();
            }
            else{
            	this.setState({statusMessage:$sm.message(data.statusCode),displayMessage:true});
            }
		});
	}

	
	render() {
		const message = this.state.statusMessage;
		const usernameClass = "form-control margin-top-10 first";
		const passwordClass = "form-control margin-top-5";
		return (
			<div id="signInPanel" className="panel">

				<span className="heading">Login Credentials</span>

				<input type="email" id="signIn_username" name="username" className={usernameClass} placeholder="Username"/>

				<input type="password" id="signIn_password" name="password" className={passwordClass} placeholder="Password"/>

				<button type="button" id="signInButton" className="btn btn-lg btn-danger btn-block margin-top-20" onClick={() => {this.signIn();}}>Sign In</button>

				<div id="forgotPasswordLink" onClick={() => this.forgotPassword()}>Forgot Password</div>

				<Message display={this.state.displayMessage} message={message} />

			</div>
		);
	}
}
//



//Sign up panel for user registration
class SignUpPanel extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			statusMessage: '',
			displayMessage: false,
			form:{
				username:"",
				password:"",
				name:"",
				dob:"",
				address:"",
				city:"",
				state:"",
				pincode:"",
				mobileNo:""
			}
		}
		this.signUp = this.signUp.bind(this);
	}

	componentDidMount(){
		let formElemIds = ["username","password","name","dob","address","city","state","pincode","mobileNo"];
		for(let i = 0, len = formElemIds.length; i < len; i++){
			let elemId = formElemIds[i];
			document.getElementById(elemId).addEventListener("change", (e)=>{
				updateFormValues(e,this);
			});
		}
	}

	signUp(){
		var vals = this.state.form;
		//this.displayMessage("Bad request","message-warn");
		var validity = validateSignUpValues(vals);
		if(!validity.allValid){
			this.setState({statusMessage:validity.message,displayMessage:true});
			return;
		}
		$httpService.signUp({user:vals}, data => {
			data = JSON.parse(data);
            this.setState({statusMessage:$sm.message(data.statusCode),displayMessage:true});
		},()=>{});
	}

	render(){
		let display = this.props.display;
		let panelStyle = {display: "none"};
		if(display){
			panelStyle = {display: "block"};
		}
		const message = this.state.message;
		const messageClass = this.state.messageClass;
		//const signUpBtnClass = "btn btn-lg btn-danger btn-block";

		return (
			<div style={panelStyle} id="signUpPanel" className="panel">
				<div className="container">
				
					<h3 className="form-signin-heading">Create a new account</h3>

					<input ref="id" type="text" id="id" className="form-control hidden" placeholder="Username" defaultValue="-1" required/>

					<input name="username" type="email" id="username" className="form-control" placeholder="Username" required/>
					<input name="password" type="password" id="password" className="form-control" placeholder="Password" required/>
					<input name="name" type="text" id="name" className="form-control" placeholder="Name" required/>
					<input name="dob" type="text" id="dob" className="form-control" placeholder="dd/mm/yyyy" required/>
					<input name="address" type="text" id="address" className="form-control" placeholder="address" required/>
					<input name="city" type="text" id="city" className="form-control" placeholder="City" required/>
					<input name="state" type="text" id="state" className="form-control" placeholder="State" required/>
					<input name="pincode" type="text" id="pincode" className="form-control" placeholder="Pincode" required/>
					<input name="mobileNo" type="number" id="mobileNo" className="form-control" placeholder="Mobile Number" required/>

					<button type="button" id="userSignUpButton" className="btn btn-lg btn-danger btn-block" onClick={this.signUp}>Register Me</button>
					<Message display={this.state.displayMessage} message={this.state.statusMessage} />
				</div>
					
			</div>
		);
	}
}



class Message extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		//display will take a css display value depending on the boolean value 
		let display = "none";
		if(this.props.display){
			display = "block";
		}
		const style={display:display};

		return (
			<div style={style} className="message-warn">{this.props.message}</div>
		);
	}
}
//


function updateFormValues(e,component){
	const elem = e.target; 
	const field = elem.getAttribute("name");
	const val = elem.value;
	component.state.form[field] = val;
	removeMessage(component);
}

function removeMessage(component){
	component.setState({displayMessage:false});
}

function displayMessage(message,messageClass){
	//this.setState({message:message,messageClass:messageClass});
}


function validateSignInValues(vals){
	const $validations = util.$validations;
	var message = undefined;
	var allValid = true;
	if( !($validations.isValidEmail(vals.username) && $validations.isValidPassword(vals.password))){
		message = "Please enter valid username and password";
		allValid = false;
	}
	
	return {allValid:allValid,message:message};
}


function validateSignUpValues(vals){
	const $validations = util.$validations;
	var message = undefined;
	var allValid = true;
	if(!$validations.isValidEmail(vals.username)){
		message = "Please enter valid username";
		allValid = false;
	}
	else if(!$validations.isValidPassword(vals.password)){
		message = "Please enter valid password";
		allValid = false;
	}
	else if(!$validations.isValidName(vals.name)){
		message = "Please enter valid name";
		allValid = false;
	}
	else if(!$validations.isValidDOB(vals.dob)){
		message = "Please enter valid dob";
		allValid = false;
	}
	// else if(!$validations.isValidAddress(vals.address)){
	// 	message = "Please enter valid address";
	// 	allValid = false;
	// }
	else if(!$validations.isValidCity(vals.city)){
		message = "Please enter valid city";
		allValid = false;
	}
	else if(!$validations.isValidState(vals.state)){
		message = "Please enter valid state";
		allValid = false;
	}
	else if(!$validations.isValidPincode(vals.pincode)){
		message = "Please enter valid pincode";
		allValid = false;
	}
	else if(!$validations.isValidMobileNo(vals.mobileNo)){
		message = "Please enter valid mobileNo";
		allValid = false;
	}

	return {allValid:allValid,message:message};
}



ReactDOM.render(<Login/> , document.getElementById('main-container'));

