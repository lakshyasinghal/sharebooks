import React from "react";
import ReactDOM from "react-dom";

import downArrow from "./../../resources/images/downArrow.png";
import add from "./../../resources/images/add.png";
import notificationIcon from "./../../resources/images/notificationIcon.png";
import pointer from "./../../resources/images/pointer.png";
import userProfile from "./../../resources/images/userProfile.png";


export default class Header extends React.Component {
	constructor(props){
		super(props);
		this.state =  {
			nbDisplay: "none",  //notifications box display
			profileDisplay: "none",
			bookAdderDisplay: this.props.bookAdderDisplay,
			bookBrowserDisplay: this.props.bookBrowserDisplay
		};
	}

	/*Needs to be modified
	 Needs to be added in common.js */
	toggleDisplay(dispProp){
		var newDisplay = toggleDisplay(dispProp,this);
		var obj = {}; obj[dispProp]=newDisplay;
		this.setState(obj);
	}

	render(){
		return (
			<div id="headContainer" className="row">
				
				<AppTitle/>
				<BookBrowser display={this.state.bookBrowserDisplay}/>
				<BookAdder onClick={this.props.togglePopup} display={this.state.bookAdderDisplay}/>
				<Notifications display={this.state.nbDisplay} onClick={() => {this.toggleDisplay("nbDisplay");}} />
				<Profile display={this.state.profileDisplay} onClick={() => {this.toggleDisplay("profileDisplay");}} />

			</div>
		);
	}
}
//


class AppTitle extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		return (
			<div id="appTitle" className="vertical-center">
				SHAREBOOKS
			</div>
		);
	}
}
//

class BookBrowser extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		const display = this.props.display;
		let className = "vertical-center horizontal-center pointer";
		if(display=="none"){
			className += " hidden";
		}

		return (
			<div id="browseLink" className={className} ng-click="browsingHandler.showCategoriesPanel()">
				<span>Browse</span>
				<img src={downArrow} height="15" width="15" />
			</div>
		);
	}
}
//



class BookAdder extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		const display = this.props.display;
		let className = "pointer vertical-center";
		if(display=="none"){
			className += " hidden";
		}

		return (
			<div id="addBookLink" className={className} onClick={this.props.onClick}>
				<img src={add} height="20" width="20" id="addSignImage" className="pointer" />
				<span>ADD BOOK</span> 
			</div>
		);
	}
}



class Notifications extends React.Component {
	constructor(props){
		super(props);
	}

	//calculate notification box class
	nbClass(){
		var nbClass = "horizontal-center";
		if(this.props.display=="none"){
			nbClass += " " + "hidden";
		}
		return nbClass;
	}


	render(){
		//notification box class
		const nbClass = this.nbClass();

		return (
			<div id="notificationContainer" className="pointer vertical-center">
				<div id="totalCount"></div>
				<img src={notificationIcon} onClick={this.props.onClick} id="notificationImage" width="30" height="30" />

				<div id="notificationBox" className={nbClass}>
					<div className="notification"><div>Requests<span id="bookRequestCount" className="count"></span></div></div>
					<div className="notification"><div>Request Acceptances<span id="acceptanceCount" className="count"></span></div></div>
					<div className="notification"><div>Request Refusals<span id="refusalCount" className="count"></span></div></div>
					<div className="notification"><div>New Arrivals<span id="newArrivalCount" className="count"></span></div></div>
					<img src={pointer} width="15" height="15" className="horizontal-center" />
				</div>
			</div>
		);
	}
}


class Profile extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	componentDidMount(){

	}

	//calculate profile list class
	plClass(){
		var plClass = "";
		if(this.props.display=="none"){
			plClass = "hidden";
		}
		return plClass;
	}	

	render(){
		const plClass = this.plClass();

		return (
			<div id="profile" className="pointer vertical-center">
				<img src={userProfile} onClick={this.props.onClick} id="profileImage" width="40" height="40" />

				<div id="profileList" className={plClass}>	
					<table id="profileListTable">
						<tbody>
							<tr>
								<td id="profileTab">Profile</td>
							</tr>
							<tr>
								<td id="historyTab">History</td>
							</tr>
							<tr>
								<td id="signOutTab">Sign Out</td>
							</tr>
							<tr>
								<td id="feedbackTab">Feedback</td>
							</tr>
							<tr>
								<td id="complaintsTab">Complaints</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		);
	}
}





function toggleDisplay(dispProp,self){
	if(self.state[dispProp]=="none"){
		return "block";
	}
	return "none";
}

