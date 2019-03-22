import React from "react";
import ReactDOM from "react-dom";

import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";


const $pages = $config.$pages;
const $sm = $config.$sm;


class Login extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render() {
		return (
			<div>
				<div id="header">
					<span id="title">SHAREBOOKS</span>

					<div id="signUpButton">
						<span>Sign Up</span>
					</div>										
				</div>

				<SignInPanel/>
				<SignUpPanel/>
			</div>
		);
	}
}
//


//Sign in panel for logging in with username and password
class SignInPanel extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			message: '',
			messageClass: 'hidden'
		};
		this.handleInputChange = this.handleInputChange.bind(this);
	}

	componentDidMount(){
		this._username = ReactDOM.findDOMNode(this.refs.username);
		this._password = ReactDOM.findDOMNode(this.refs.password);
		console.log("mounted");
	}

	handleInputChange(){
		this.removeMessage();
	}

	removeMessage(){
		this.displayMessage('','hidden');
	}

	displayMessage(message,messageClass){
		this.setState({message:message,messageClass:messageClass});
	}

	values(){
		var vals = {};
		vals.username = this._username.value;
		vals.password = this._password.value;
		console.log("vals : ",vals);
		return vals;
	}

	validate(vals){
		var message = undefined;
		var accepted = false;
		if(!validateEmail(vals.username)){
			message = "Please enter valid username";
		}
		else if(!validatePassword(vals.password)){message = "Please enter valid password";}
		else{accepted=true;}

		if(!accepted){this.displayMessage(message,'warn'); return false;}
		else{return true;}
	}

	signIn(){
		var vals = this.values();
		this.displayMessage("Bad request","message-warn");
		if(!this.validate(vals)){return null;}
		$httpService.signIn(vals, () => {
			data = JSON.parse(data);

            if(data.success){
                //self.modifyCookies();
                $pages.home();
            }
            else{
                displayMessage($sm.message(data.statusCode) , 'message-warn');
            }
		});
		//$pages.home();
	}

	
	render() {
		const message = this.state.message;
		const messageClass = this.state.messageClass;

		return (
			<div id="signInPanel" className="panel">
				<span className="heading">Login Credentials</span>
				<input ref="username" type="email" id="signIn_username" onChange={this.handleInputChange} className="form-control margin-top-10 first" placeholder="Username"/>
				<input ref="password" type="password" id="signIn_password" onChange={this.handleInputChange} className="form-control margin-top-5" placeholder="Password"/>
				<button type="button" id="signInButton" className="btn btn-lg btn-danger btn-block margin-top-20" onClick={() => this.signIn()}>Sign In</button>
				<div id="forgotPasswordLink" onClick={() => this.forgotPassword()}>Forgot Password</div>
				<Message messageClass={messageClass} message={message} />
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
			message: '',
			messageClass: 'hidden'
		}
	}

	componentDidMount(){
		console.log("mounted sign up panel");
	}


	removeMessage(){
		this.displayMessage('','hidden');
	}

	displayMessage(message,messageClass){
		this.setState({message:message,messageClass:messageClass});
	}

	values(){
		var vals = {};
		formElems = this.refs;
		for(var elem in formElems){
			vals[elem] = formElems[elem].value;
		}
		return vals;
	}

	validate(vals){

	}

	signUp(){
		const vals = values();

	}

	selectDOB(){

	}

	render(){
		const message = this.state.message;
		const messageClass = this.state.messageClass;
		//const signUpBtnClass = "btn btn-lg btn-danger btn-block";

		return (
			<div id="signUpPanel" className="panel">
				<div className="container">
				
					<h3 className="form-signin-heading">Create a new account</h3>

					<input ref="id" type="text" id="id" className="form-control hidden" placeholder="Username" defaultValue="-1" required/>

					<input ref="username" type="email" id="username" className="form-control" placeholder="Username" required/>

					<input ref="password" type="password" id="password" className="form-control" placeholder="Password" required/>
					<input ref="name" type="text" id="name" className="form-control" placeholder="Name" required/>
					<input ref="dob" type="text" id="birthday" className="form-control" placeholder="dd/mm/yyyy" required/>
					<input ref="address" type="text" id="address" className="form-control" placeholder="address" required/>
					<input ref="city" type="text" id="city" className="form-control" placeholder="City" required/>
					<input ref="state" type="text" id="state" className="form-control" placeholder="State" required/>
					<input ref="pincode" type="text" id="pincode" className="form-control" placeholder="Pincode" required/>
					<input ref="mobileNo" type="number" id="mobileNo" className="form-control" placeholder="Mobile Number" required/>
					<button type="button" id="userSignUpButton" className="btn btn-lg btn-danger btn-block" onClick={() => {this.signUp()}}>Register Me</button>
					<Message messageClass={messageClass} message={message} />
				</div>
					
			</div>
		);
	}
}



class CalenderInput extends React.Component {
	constructor(props){
		super(props);
	}

	componentDidMount(){

	}

	render(){
		return (
			<input type="text" className="calender"/>
		);
	}
}


class Message extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		return (
			<h4 className={this.props.messageClass}>{this.props.message}</h4>
		);
	}
}
//

function validateEmail(email){
	return false;
}

function validatePassword(password){
	return false;
}


ReactDOM.render(<Login/> , document.getElementById('main-container'));

