import React from "react";
import ReactDOM from "react-dom";
import Header from "./modules/header.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import $storage from "./../scripts/utility/utility.js";
const $pages = $config.$pages;
const $sm = $config.$sm;



class Preferences extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			categories: [],
			categoryMapArr: [],  /* will be of the form [{category:"Maths",selected:false,index:0},{category:"Science",selected:true,index:1}]*/
			selectedCount:0
		};
		this.addPreference = this.addPreference.bind(this);
		this.removePreference = this.removePreference.bind(this);
		this.showPreferences = this.showPreferences.bind(this);
		this.savePreferences = this.savePreferences.bind(this);
		this.methods = {
			savePreferences: this.savePreferences,
			addPreference: this.addPreference,
			removePreference: this.removePreference,
	    };
	}

	componentDidMount() {
		$httpService.getPreferenceOptions(null,(res)=>{
			res = JSON.parse(res);
	 		if(res.success){
	 			this.state.categories = res.results;
	 			this.showPreferences();
	 		}
	 		else{
	 			alert("Didn't get any preference categories");
	 		}
		},()=>{});

	}

	//will be called in the beginning to buildcategoryMapArr and display preference values
	showPreferences(){
		const categoryMapArr = this.state.categoryMapArr;
		const categories = this.state.categories;

		for(let i = 0, len = categories.length; i < len; i++){
			let obj = {};
			obj.category = categories[i];
			obj.selected = false; 
			obj.key = i;
			categoryMapArr.push(obj);
		}
		this.setState({categoryMapArr:categoryMapArr});
	}

	savePreferences(){
		var selPrefs = [];   //selected preferences
		const categoryMapArr = this.state.categoryMapArr;
		for(let i = 0, len = categoryMapArr.length; i < len; i++){
			let obj = categoryMapArr[i];
			if(obj.selected){
				selPrefs.push(obj.category);
			}
		}
		$httpService.savePreferences({preferences:selPrefs},(res)=>{
			res = JSON.parse(res);
			if(res.success){alert("Preferences saved successfully");}
			else{alert("Preferences couldn't be saved");}
		},()=>{});
	}

	//will mark the preference as selected
	addPreference(key){
		let categoryMapArr = this.state.categoryMapArr;
		categoryMapArr[key].selected = true; 
		let selectedCount = this.state.selectedCount+1;
		this.setState({categoryMapArr:categoryMapArr,selectedCount:selectedCount});
	}

	//will mark the preference as not selected
	removePreference(key){
		let categoryMapArr = this.state.categoryMapArr;
		categoryMapArr[key].selected = false; 
		let selectedCount = this.state.selectedCount-1;
		this.setState({categoryMapArr:categoryMapArr,selectedCount:selectedCount});
	}

	render(){
		return (
			<div id="mainContainer">
				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={false}/>
				
				<Body selectedCount={this.state.selectedCount} categoryMapArr={this.state.categoryMapArr} methods={this.methods}/>
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
		const selCount = this.props.selectedCount;
		const methods = this.props.methods;
		const savePrefs = methods.savePreferences; 
		const categoryMapArr = this.props.categoryMapArr;

		return (
			<div id="bodyContainer">
				<div className="fixed" id="prefCount">{selCount}</div>

				<div id="header" className="center-align">
					<div id="text1">SELECT YOUR PREFERENCES</div>
					<div id="text2">Choose Atleast 5 Options</div>
				</div>

				<PreferencePanel categoryMapArr={categoryMapArr} methods={methods}/>

				<div id="submitContainer" className="center-align margin-top-20">
					<button id="submitBtn" className="btn btn-warning" onClick={savePrefs}>Submit</button>
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
		const categoryMapArr = this.props.categoryMapArr;
		const methods = this.props.methods;

		return (
			<div id="preferencePanel">
				{categoryMapArr.map((categoryObj,index)=>{
					return <Preference key={index} categoryObj={categoryObj} methods={methods}/>
				})}
			</div>
		);
	}
}
//








class Preference extends React.Component {
	constructor(props){
		super(props);
	}

	componentDidUpdate(prevProps, prevState) {
	    console.log("updated");
	}

	render(){
		const tickImg = "/static/tick.png";
		const categoryObj = this.props.categoryObj;
		const category = categoryObj.category;
		const sel = categoryObj.selected;
		const key = categoryObj.key;
		const methods = this.props.methods;
		const addPref = methods.addPreference;
		const remPref = methods.removePreference;
		const selecthandler = (function(key){
			if(sel){
				return function(){remPref(key);};
			}
			else{
				return function(){addPref(key);};
			}
		})(key);

		return (
			<div className="preference" onClick={selecthandler}>
				{sel && <img src={tickImg} className="tick" height="30" width="30"/>}
				<span className="prefText">{category}</span>
			</div>
		);
	}
}


// function Preference(props){

// 	render(){

// 		return (
// 			<div className="preference" onClick={this.clickHandler}>
// 				<img src={tick} className="tick" height="30" width="30"/>
// 				<span className="prefText">{category}</span>
// 			</div>
// 		);
// 	}
// }



ReactDOM.render(<Preferences/> , document.getElementById('app'));
