import React from "react";
import ReactDOM from "react-dom";
import "./../lib/styles/bootstrap.css";
import "./../styles/common.css";
import "./../styles/profile.css";



class Profile extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="mainContainer">
				<h2 id="heading">Login & Security</h2>
				
				<ProfileTable/>

				<div id="btnPanel">
					<button id="saveBtn" className="btn-warning">Save</button>
					<button id="doneBtn" className="btn-danger">Done</button>
				</div>
			</div>
		);
	}
}



class ProfileTable extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<table className="table table-bordered" id="profileTable">
				<tr>
					<td>Username</td>
					<td><input type="text" name="username" className="form-control" id="username" value="lakshyasinghal333@gmail.com"/></td>
					<td className="center-align"><button className="btn-info">Edit</button></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="text" name="username" className="form-control" id="username" value="lakshyasinghal333@gmail.com"/></td>
					<td className="center-align"><button className="btn-info">Edit</button></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" name="username" className="form-control" id="username" value="lakshyasinghal333@gmail.com"/></td>
					<td className="center-align"><button className="btn-info">Edit</button></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="username" className="form-control" id="username" value="lakshyasinghal333@gmail.com"/></td>
					<td className="center-align"><button className="btn-info">Edit</button></td>
				</tr>
			</table>
		);
	}
}


ReactDOM.render(<Profile/> , document.getElementById('app'));


























