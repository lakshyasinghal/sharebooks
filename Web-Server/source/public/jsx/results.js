import React from "react";
import ReactDOM from "react-dom";

import Header from "./modules/header.js";



class Results extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		const backArrow = "/static/backArrow.png";

		return (
			<div id="mainContainer">
				<Header bookAdderDisplay={"none"} bookBrowserDisplay={"none"}/>

				<div id="bodyContainer">

					<div id="optionsContainer">
						<div id="backButton">
							<img src={backArrow} width="60" height="60"/>
						</div>
						<div id="headingText">
							<span>SELECTED BOOK</span>
						</div>
					</div>

					<div id="resultsContainer">						
					</div>

				</div>
			</div>
		);
	}
}


class Body extends React.Component {
	
}





ReactDOM.render(<Results/> , document.getElementById('app'));




















