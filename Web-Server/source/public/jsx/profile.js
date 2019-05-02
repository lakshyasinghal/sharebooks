import React from "react";
import ReactDOM from "react-dom";
import Header from "./modules/header.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";
const $pages = $config.$pages;
const $sm = $config.$sm;
const $storage = util.$storage;

//const inputInfoArr = [{desc:'Name',prop:'name'},{desc:'Email',prop:'username'},{desc:'Mobile Number',prop:'contactNo'},{desc:'Password',prop:'password'}];


class Profile extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			user: {},
			modifiedDetails: {
				name:null,
				username:null,
				contactNo:null,
				password:null
			},
			readOnlyValues: {
				name:true,
				username:true,
				mobile:true,
				password:true
			}
		};
		this.updateProfile = updateProfile.bind(this);
		this.updateHandler = this.updateHandler.bind(this);
		this.updateReadOnly = this.updateReadOnly.bind(this);
	}

	componentDidMount() {
	    this.getUser();
	}


	getUser(){
		var user = util.$storage.get('user');
		this.state.user = user;
		//setting the initila state for modifiedDetails
		{
			this.state.modifiedDetails.name = user.name;
			this.state.modifiedDetails.username = user.username;
			this.state.modifiedDetails.contactNo = user.contactNo;
			this.state.modifiedDetails.password = user.password;
		}
		this.forceUpdate();
	}

	updateReadOnly(fieldName){
		const readOnlyValues = this.state.readOnlyValues;
		readOnlyValues[fieldName] = false;
		this.setState({readOnlyValues:readOnlyValues});
	}


	/**
	 * [will be called on every input change]
	 * @param  {[type]} prop   [user property for which input value has changed]
	 * @param  {[type]} newVal [current input value]
	 */
	updateHandler(prop,newVal){
		const modifiedDetails = this.state.modifiedDetails;
		modifiedDetails[prop] = newVal;
	}

	


	render(){
		return (
			<div id="mainContainer">
			    <Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={true}/>

				<h2 id="heading">Login & Security</h2>
				
				<ProfileTable user={this.state.user} readOnlyValues={this.state.readOnlyValues} updateReadOnly={this.updateReadOnly} updateHandler={this.updateHandler}/>

				<div id="btnPanel">
					<button id="saveBtn" className="btn-warning" onClick={()=>{this.updateProfile(this.state.modifiedDetails);}}>Save</button>
					<button id="doneBtn" className="btn-danger" onClick={()=>{$pages.home();}}>Done</button>
				</div>
			</div>
		);
	}
}



class ProfileTable extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		const user = this.props.user;
		const readOnlyValues = this.props.readOnlyValues;
		const updateHandler = this.props.updateHandler;
		const valChangeHandler = function(prop){
			return function(e){
				updateHandler(prop , e.target.value);
			}
		};
		const updateReadOnly = this.props.updateReadOnly;

		return (
			<table className="table table-bordered" id="profileTable">
				<tbody>
					<tr>
						<td>Name</td>
						<td><input type="text" readOnly={readOnlyValues.name} className="form-control" id="name" defaultValue={user.name} onChange={valChangeHandler('name')} /></td>
						<td className="center-align"><button className="btn-info" onClick={()=>{updateReadOnly('name');}}>Edit</button></td>
					</tr>
					<tr>
						<td>Username</td>
						<td><input type="text" readOnly={readOnlyValues.username} className="form-control" id="username" defaultValue={user.username} onChange={valChangeHandler('username')} /></td>
						<td className="center-align"><button className="btn-info" onClick={()=>{updateReadOnly('username');}}>Edit</button></td>
					</tr>
					<tr>
						<td>Mobile number</td>
						<td><input type="text" readOnly={readOnlyValues.mobile} className="form-control" id="contactNo" defaultValue={user.contactNo} onChange={valChangeHandler('contactNo')} /></td>
						<td className="center-align"><button className="btn-info" onClick={()=>{updateReadOnly('mobile');}}>Edit</button></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" readOnly={readOnlyValues.password} className="form-control" id="pasword" defaultValue={user.password} onChange={valChangeHandler('password')} /></td>
						<td className="center-align"><button className="btn-info" onClick={()=>{updateReadOnly('password');}}>Edit</button></td>
					</tr>
				</tbody>
			</table>
		);
	}
}



function updateProfile(modifiedDetails){
	const user = $storage.get('user');
	//const updateDetails = this.state.updateDetails;
	$httpService.updateProfile([user.uid], modifiedDetails, (res)=>{
		alert("user updated successfully");
	});
}


//to be used in future
// class ProfileRow extends React.Component {
// 	constructor(props){
// 		super(props);
// 		this.state = {

// 		}
// 	}

// 	render(){
// 		const inputInfo = this.props.inputInfo;
// 		let desc = inputInfo.desc;
// 		let prop = inputInfo.prop;
// 		const user = this.props.user;

// 		return (
// 			<tr>
// 				<td>{desc}</td>
// 				<td><input type="text" className="form-control" defaultValue={user[prop]}/></td>
// 				<td className="center-align"><button className="btn-info">Edit</button></td>
// 			</tr>
// 		);
// 	}
// }


ReactDOM.render(<Profile/> , document.getElementById('app'));












