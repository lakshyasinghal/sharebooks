import React from "react";
import ReactDOM from "react-dom";



export default class Loader extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			loaderClass: 'loader-medium'
		};
	}

	render(){
		const loaderClass = this.state.loaderClass;

		return (
			<div id="pageLoader" className={loaderClass}></div>
		);
	}
}
//
