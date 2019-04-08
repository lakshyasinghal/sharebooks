import React from "react";
import ReactDOM from "react-dom";


class Loader extends React.Component {
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


class DropDown extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		}
	}

	renderOption(option,key){
		return (
			<option key={key} value={option.value}>{option.desc}</option>
		)
	}

	render(){
		const isDefOptionReq = this.props.isDefOptionReq;
		const optionsMap = this.props.optionsMap;

		return (
			<select name={this.props.name} id={this.props.id} className={this.props.className}>
				{isDefOptionReq && <option key="-1" value=""></option>}
				{optionsMap.map((option,key)=>{return this.renderOption(option,key);})}
			</select>
		);
	}
}
//


class Message extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		//display will take a css display value depending on the boolean value 

		let className = "center-align";
		if(this.props.display){className += " show";}
		else{className += " hidden";}
		
		const type = this.props.type;
		if(type){className+=" message-"+type;}

		return (
			<div className={className}>{this.props.message}</div>
		);
	}
}
//

export default {Loader,DropDown,Message};

