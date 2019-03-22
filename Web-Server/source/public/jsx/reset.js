import React from "react";
import ReactDOM from "react-dom";
import "./../lib/styles/bootstrap.css";
import "./../styles/common.css";
import "./../styles/reset.css";



class Reset extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="mainContainer" class="center-align">

				<h1>Forgot Password</h1>
				<br/><br/>

				<div id="infoText">
					<div>Do not worry. We'll help you to reset your password.</div>
					<div>Enter your registered email id. Click on send otp button to receive otp in your mail account.</div>
				</div>
				<br/><br/>

				<div id="mainPanel" class="left-align">

					<div><input type="text" placeholder="Registered email id" class="form-control" id="emailInput"/></div>
					<button class="btn btn-danger" id="otpBtn">Send OTP</button>

					<div id="messageText"></div>
					<br/><br/>

					<div id="otpPanel">
						<span>
							<input type="text" placeholder="Enter OTP" class="form-control" id="otpInput"/>
							<button class="btn btn-danger" id="verifyBtn">Verify</button>
						</span>
						<div><span id="timer">03:39</span></div>
					</div>

					<br/><br/>

					<div id="passwordPanel">
						<input type="password" placeholder="Enter New Password" class="form-control" id="passwordInput"/>
						<input type="password" placeholder="Re-Enter Password" class="form-control" id="confirmPasswordInput"/>
						<div id="pwdMessage"></div>
						<button class="btn btn-warning" id="submitBtn">Submit</button>
					</div>

				</div>
			</div>
		);
	}
}



ReactDOM.render(<Reset/> , document.getElementById('app'));