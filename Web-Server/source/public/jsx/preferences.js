import React from "react";
import ReactDOM from "react-dom";
import "./../lib/styles/bootstrap.css";
import "./../styles/common.css";
import "./../styles/preferences.css";

import tick from "./../resources/images/tick.png";



class Preferences extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="mainContainer">
				<div className="fixed" id="prefCount">0</div>

				<div id="header" className="center-align">
					<div id="text1">SELECT YOUR PREFERENCES</div>
					<div id="text2">Go For Atleast 5 Options</div>
				</div>

				<div id="preferencePanel">
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">HTML5</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Javascript</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Java</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Maths</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Science</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Fiction</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Geography</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">History</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Maths</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Science</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Fiction</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Geography</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">History</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Science</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Fiction</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">Geography</span>
					</div>
					<div className="preference">
						<img src={tick} className="tick" height="30" width="30"/>
						<span className="prefText">History</span>
					</div>
				</div>	


				<div id="submitContainer" className="center-align margin-top-20"><button id="submitBtn" className="btn btn-warning">Submit</button></div>
			</div>
		);
	}
}



ReactDOM.render(<Preferences/> , document.getElementById('app'));











