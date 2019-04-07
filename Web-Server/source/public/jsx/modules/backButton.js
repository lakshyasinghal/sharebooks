import React from "react";
import ReactDOM from "react-dom";


export default class BackButton extends React.Component {
	

	render(){
		const backArrow = "/static/backArrow.png";
		return (
			<div id="backButton">
				<img src={backArrow} width="60" height="60"/>
			</div>
		);
	}
}