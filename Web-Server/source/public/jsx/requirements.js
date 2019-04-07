import React from "react";
import ReactDOM from "react-dom";

import Header from "./modules/header.js";
import BackButton from "./modules/backButton.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";


const $pages = $config.$pages;
const $sm = $config.$sm;
const $global = $config.$global;


class Checkout extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		return (
			<div id="mainContainer" className="full-height">
				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={true}/>
				<div id="titleContainer">
					<BackButton/>
					<span className="pageTitle">REQUIREMENTS</span>
				</div>
				<Body orderType={"RENT"}/>
			</div>
		);
	}
}
//



class Body extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div style={{marginTop:'50px',height:'500px'}} id="bodyContainer">
				<Info/>
				<Requirements orderType={this.props.orderType}/>
			</div>
		);
	}
}
//


class Info extends React.Component {
	constructor(){
		super();
	}

	render(){
		const style = {display:'inline-block',width:'20%',height:"100%",borderRight:'1px solid #777777',float:"left"};

		return (
			<div style={style} id="infoContainer">
				<div id="bookInfo">
					<img id="bookImage" width="130" height="140"/>
				</div>

			</div>
		);
	}
}


class Requirements extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			orderType: this.props.orderType,
			termsChecked: false,
			updateRentOrBuy: true
		};
		this.toggleTermsCheck = this.toggleTermsCheck.bind(this);
	}

	toggleTermsCheck(){
		this.setState(prevState=>({termsChecked:!prevState.termsChecked,updateRentOrBuy:false}));
		this.state.updateRentOrBuy = true; 
	}

	proceed(){
		const isTermsChecked = this.state.termsChecked;
		const rentComponent = $global.ORDER_TYPE['1']==this.state.orderType;
		if(!isTermsChecked){
			alert("Please check terms and conditions first");
			return ; 
		}

	}

	render(){
		const orderType = this.props.orderType;
		const rentComponent = $global.ORDER_TYPE['1']==orderType;
		const buyComponent = $global.ORDER_TYPE['2']==orderType;
		const style = {display:'inline-block',width:'80%'};

		return (
			<div style={style} id="requirementsContainer">
				<div style={{paddingLeft:'125px'}} className="container">
					
					<div id="optionDescriptionContainer"></div>

					{rentComponent && <RentComponent rent={7} minDays={5}/>}

					{buyComponent && <BuyComponent shouldUpdate={this.state.updateRentOrBuy}/>}

					<TermsAndConditions toggleTermsCheck={this.toggleTermsCheck}/>

					<div id="proceedButtonContainer">
						<button className="btn btn-lg" id="proceedButton" onClick={()=>{this.proceed();}}>PROCEED</button>
					</div>

				</div>
			</div>
		);
	}
}



class RentComponent extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			rent: this.props.rent,
			minDays: this.props.minDays,
			days: this.props.minDays,
			totalRent: this.props.rent*this.props.minDays,
			displayMessage: false
		};
		this.incrementDays = this.incrementDays.bind(this);
		this.decrementDays = this.decrementDays.bind(this);
	}

	shouldComponentUpdate(nextProps, nextState) {
	    return nextProps.shouldUpdate;
	}

	incrementDays(){
		this.setState(prevState=>{
			let days = prevState.days+1;
			let totalRent = prevState.rent*days;
			return {days:days,totalRent:totalRent,displayMessage:false};
		});
	}

	decrementDays(){
		this.setState(prevState=>{
			const minDays = prevState.minDays;
			const days = prevState.days-1;
			const totalRent = prevState.rent*days;
			let newState = {days:days,totalRent:totalRent};
			if(days<minDays){
				newState = {displayMessage:true};
			}
			return newState;
		});
	}


	render(){
		const rent = this.state.rent;
		const totalRent = this.state.totalRent;
		const days = this.state.days;
		const minDays = this.state.minDays;
		const displayMessage = this.state.displayMessage;

		return (
			<div id="rentDetailsContainer" >
				<div id="chargesContainer">
					<div className="block1"><span>Rent Charges</span></div>
					<div className="block2">
						<span id="rent" className="box"><span className="boxText">{rent}</span></span>
						<span style={{position:'relative',left:'56px'}}>/ day</span>
					</div>
				</div>
				<div id="daysContainer">
					<div className="block1"><span>Days</span></div>
					<div className="block2">
						<div id="days" className="box">
							<span className="boxText">{days}</span>
							<div id="increaseDays"><span onClick={this.incrementDays}>+</span></div>
							<div id="decreaseDays"><span onClick={this.decrementDays}>-</span></div>
						</div>
					</div>
					<div className="block3">
						<span style={{display:displayMessage?'block':'none'}} id="daysMessage">Value cannot be less than {minDays}</span>
					</div>
				</div>
				<div id="totalAmountContainer">
					<div className="block1"><span>Total Rent</span></div>
					<div className="block2"><span id="totalRent" className="box"><span className="boxText">{totalRent}</span></span></div>
				</div>
			</div>
		);
	}
}
//



class BuyComponent extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="buyDetailsContainer">
				<div id="amountContainer">
					<div className="block1"><span>Amount</span></div>
					<div className="block2">Rs <span id="buyAmount"></span></div>
				</div>
			</div>
		);
	}
}


class TermsAndConditions extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		//const termsText = "I have checked terms and conditions and am ready to move further.";
		const termsURL = "/static/termsandconditions.html";
		return (
			<div id="termsAndConditions">
				<div className="checkbox">
				  <label><input type="checkbox" onClick={this.props.toggleTermsCheck} />I have checked <a href={termsURL} target="_blank">terms and conditions</a> and am ready to move further.</label>
				</div>
			</div>
		);
	}
}



class Result extends React.Component {
	constructor(props){
		super(props);
	}


	render(){
		const book = this.props.book;
		const user = this.props.user;
		//const bookInfo = this.renderBookInfo(book);
		//const userInfo = this.renderUserInfo(user);

		return (
			<div className="result">

				<div className="bookInfo">
					<div className="container">
						<img src={book.image} width="130" height="180"/>
				
						<div className="infoPanel">
							<div className="bookName">NAME : {book.name}</div>
							<div className="authorName">AUTHOR NAME : {book.authorName}</div>
							<div className="category">CATEGORY : {book.category}</div>
							<div className="subcategory">SUBCATEGORY : {book.subcategory}</div>
							<div className="pages">PAGES : {book.pages}</div>
							<div className="available">AVAILABLE : {book.available}</div>
						</div>

						<div className="buttonPanel">
							<button className="btn btn-danger buy">Buy</button>
							<button className="btn btn-danger rent">Rent</button>
						</div>
					</div>
				</div>

				<div className="userInfo">
					<div className="container">
				
						<div className="infoPanel">
							<div className="userName">NAME : {user.name}</div>
							<div className="age">AGE : {user.age}</div>
							<div className="address">ADDRESS : {user.address}</div>
							<div className="city">CITY : {user.city}</div>
							<div className="state">STATE : {user.state}</div>
							<div className="pincode">PINCODE : {user.pincode}</div>
							<div className="contactNo">CONTACT NO : {user.contactNo}</div>
						</div>

						<div className="buttonPanel">
							<button className="btn btn-lg btn-success">View Location On Map</button>
						</div>
					</div>
				</div>

			</div>
		);
	}
}
//



ReactDOM.render(<Checkout/> , document.getElementById('app'));

