import React from "react";
import ReactDOM from "react-dom";


export default class Header extends React.Component {
	constructor(props){
		super(props);
		this.state =  {
			homeDisplay: this.props.homeDisplay,
			profileDisplay: this.props.profileDisplay,                    
			adderDisplay: this.props.adderDisplay, //book adder
			browserDisplay: this.props.browserDisplay, //book browser
			notifDisplay: this.props.notifDisplay
		};
	}

	componentDidMount() {
	    this.forceUpdate();
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
				{this.state.homeDisplay && <HomeIcon/>}
				{this.state.browserDisplay && <BookBrowser/>}
				{this.state.adderDisplay && <BookAdder onClick={this.props.togglePopup} />}
				{this.state.notifDisplay && <Notifications />}
				{this.state.profileDisplay && <Profile />}
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

class HomeIcon extends React.Component {
	constructor(props){
		super(props);
	}

	componentDidMount() {
	    this.events();
	}

	events(){
		document.getElementById("homeIcon").addEventListener('click',()=>{
			$pages.home();
		});
	}

	render(){
		const homeIcon = "/static/home.png";

		return (
			<div id="homeIcon" className="pointer vertical-center">
				<img src={homeIcon} className="pointer" width="35" height="35"/>
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

	openCategoriesPanel(){

	}

	render(){
		const downArrowImg = "/static/downArrow.png";
		let className = "vertical-center horizontal-center pointer";

		return (
			<div id="browseLink" className={className} onClick={()=>{this.openCategoriesPanel();}}>
				<span>Browse</span>
				<img src={downArrowImg} height="15" width="15" />
			</div>
		);
	}
}
//



class BookAdder extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		const addImg = "/static/add.png";

		return (
			<div id="addBookLink" className="pointer vertical-center" onClick={this.props.onClick}>
				<img src={addImg} height="20" width="20" id="addSignImage" className="pointer" />
				<span>ADD BOOK</span> 
			</div>
		);
	}
}



class Notifications extends React.Component {
	constructor(props){
		super(props);
		this.state={
			nbDisplay: false
		};
	}

	/*
	calculate nd display class
	needs to be made common 
	 */
	nbDisplay(){
		return !this.state.nbDisplay?"hidden":"";
	}


	render(){
		//const nbClass = this.nbClass();  //notification box class
		const pointerImg = "/static/pointer.ico";
		const notifImg = "/static/notificationIcon.png";

		return (
			<div id="notifications" className="pointer vertical-center" onClick={()=>{toggleDisplay('nbDisplay',this);}}>
				<div id="totalCount"></div>
				<img src={notifImg} id="notificationImage" width="30" height="30" />

				<div id="notificationBox" className={"horizontal-center " + (this.nbDisplay())}>
					<div className="notification"><div>Requests<span id="bookRequestCount" className="count"></span></div></div>
					<div className="notification"><div>Request Acceptances<span id="acceptanceCount" className="count"></span></div></div>
					<div className="notification"><div>Request Refusals<span id="refusalCount" className="count"></span></div></div>
					<div className="notification"><div>New Arrivals<span id="newArrivalCount" className="count"></span></div></div>
					<img src={pointerImg} width="15" height="15" className="horizontal-center" />
				</div>
			</div>
		);
	}
}


class Profile extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			plDisplay: false
		};
	}

	componentDidMount(){

	}

	/*needs to be made common*/
	plDisplay(){
		return !this.state.plDisplay?"hidden":"";
	}	

	render(){
		const profImg = "/static/userProfile.png";

		return (
			<div id="profile" className="pointer vertical-center" onClick={()=>{toggleDisplay('plDisplay',this);}}>
				<img src={profImg} id="profileImage" width="40" height="40" />

				<div id="profileList" className={this.plDisplay()}>	
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


function toggleDisplay(prop,component){
	component.state[prop] = !component.state[prop];
	component.forceUpdate();
}


// function toggleDisplay(dispProp,self){
// 	if(self.state[dispProp]=="none"){
// 		return "block";
// 	}
// 	return "none";
// }

