import React from "react";
import ReactDOM from "react-dom";
import Header from "./modules/header.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import $storage from "./../scripts/utility/utility.js";
const $pages = $config.$pages;
const $sm = $config.$sm;


import tick from "./../resources/images/tick.png";



class Preferences extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			categories: [],
			categoryMapArr: []  //will contain all categories with the current selected status
		};
		this.savePreferences = this.savePreferences.bind(this);
		this.addPreference = this.addPreference.bind(this);
		this.removePreference = this.removePreference.bind(this);
		this.methods = {
			savePreferences: this.savePreferences,
			addPreference: this.addPreference,
			removePreference: this.removePreference,
	    };
	}

	componentDidMount() {
	    
	}

	getCategories(){

	}

	savePreferences(){

	}

	addPreference(){

	}

	removePreference(){

	}

	methods

	render(){
		return (
			<div id="mainContainer">
				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={false}/>
				
				<Body categories={this.state.categories} methods={this.methods}/>
			</div>
		);
	}
}
//


class Body extends React.Component {
	constructor(props){
		super(props);
	}

	render(){

		return (
			<div className="bodyContainer">
				<div className="fixed" id="prefCount">0</div>

				<div id="header" className="center-align">
					<div id="text1">SELECT YOUR PREFERENCES</div>
					<div id="text2">Go For Atleast 5 Options</div>
				</div>

				<PreferencePanel categories={this.props.categories} methods={this.props.methods}/>

				<div id="submitContainer" className="center-align margin-top-20">
					<button id="submitBtn" className="btn btn-warning" onClick={this.props.methods.savePreferences}>Submit</button>
				</div>
			</div>
		);
	}
}
//


class PreferencePanel extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		const categoryArr = this.props.categories;
		const methods = this.props.methods;

		return (
			<div id="preferencePanel">
				{categoryArr.map((category,index)=>{
					return <Preference key={index} category={category} methods={methods}/>
				})}
			</div>
		);
	}
}
//


class Preference extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			selected: false
		}
		this.clickHandler = this.clickHandler.bind(this);
	}

	clickHandler(){
		const methods = this.props.methods;
		const selected = this.state.selected;
		if(selected){
			
		}
		else{

		}

	}

	render(){
		const category = this.props.category;

		return (
			<div className="preference" onClick={this.clickHandler}>
				<img src={tick} className="tick" height="30" width="30"/>
				<span className="prefText">{category}</span>
			</div>
		);
	}
}



ReactDOM.render(<Preferences/> , document.getElementById('app'));
