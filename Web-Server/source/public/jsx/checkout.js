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
					<span className="pageTitle">CHECKOUT</span>
				</div>
				<Body/>
			</div>
		);
	}
}
//



class Body extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			termsChecked: false,
			selectedResult: null,
			rci: {}   //rent component info
		};
		this.update = this.update.bind(this);
		this.toggleTermsCheck = this.toggleTermsCheck.bind(this);
		this.proceed = this.proceed.bind(this);
		this.incrementDays = this.incrementDays.bind(this);
		this.decrementDays = this.decrementDays.bind(this);
	}

	componentDidMount() {
	    fetchSelectedResult(this.update);
	}

	update(res){
		let selectedResult = res.results[0];
		let rci = getRentCompInfo(selectedResult); 
		this.setState({selectedResult:selectedResult,rci:rci});
	}

	toggleTermsCheck(){
		this.setState(prevState=>({termsChecked:!prevState.termsChecked}));
		// this.state.updateRentOrBuy = true; 
	}

	proceed(){
		const isTermsChecked = this.state.termsChecked;
		const rentComponent = $global.ORDER_TYPE['1']==this.state.orderType;
		if(!isTermsChecked){
			alert("Please check terms and conditions first");
			return ; 
		}
		else{
			const orderType = this.state.selectedResult.orderType;
			const bookId = this.state.selectedResult.result.book.id;
			const days = this.state.rci.days;
			const totalRent = this.state.rci.totalRent;
			submitBookRequest(orderType,bookId,days,totalRent);
		}
	}

	incrementDays(){
		this.setState(prevState=>{
			let newRCI = prevState.rci;
			newRCI.days = newRCI.days+1;
			newRCI.totalRent = newRCI.rent*newRCI.days;
			newRCI.dispMinDaysMsg = false;
			return {rci:newRCI};
		});
	}

	decrementDays(){
		this.setState(prevState=>{
			let newRCI = prevState.rci;
			const minDays = newRCI.minDays;
			let days = newRCI.days-1;	
			if(days<minDays){
				newRCI.dispMinDaysMsg=true;
			}
			else{
				newRCI.days = days;
				newRCI.totalRent = newRCI.rent*newRCI.days;
			}
			return {rci:newRCI};
		});
	}


	render(){
		const selectedResult = this.state.selectedResult;
		const book = selectedResult?selectedResult.result.book:{};
		const orderType = selectedResult?selectedResult.orderType:undefined;

		return (
			<div style={{marginTop:'50px',height:'500px'}} id="bodyContainer">
				<Info book={book}/>
				<Requirements rci={this.state.rci} orderType={orderType} toggleTermsCheck={this.toggleTermsCheck}
				proceed={this.proceed} incrementDays={this.incrementDays} decrementDays={this.decrementDays}/>
			</div>
		);
	}
}
//


class Info extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		const book = this.props.book?this.props.book:{};

		return (
			<div id="infoContainer">
				<div className="bookInfo">
					<div className="container">
						<img src={book.image} width="130" height="180"/>
				
						<div className="infoPanel">
							<div className="bookName">NAME : {book.name}</div>
							<div className="authorName">AUTHOR : {book.authorName}</div>
							<div className="category">CATEGORY : {book.category}</div>
							<div className="subcategory">SUBCATEGORY : {book.subcategory}</div>
							<div className="pages">PAGES : {book.pages}</div>
						</div>
					</div>
				</div>
			</div>
		);
	}
}
//

class Requirements extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		const orderType = this.props.orderType;
		const rentComponent = $global.ORDER_TYPE.RENT==orderType;
		const buyComponent = $global.ORDER_TYPE.BUY==orderType;

		return (
			<div id="requirementsContainer">
				<div style={{paddingLeft:'125px'}} className="container">
					
					<div id="optionDescriptionContainer"></div>

					{rentComponent && <RentComponent info={this.props.rci} incrementDays={this.props.incrementDays} decrementDays={this.props.decrementDays} />}

					{buyComponent && <BuyComponent/>}

					<TermsAndConditions toggleTermsCheck={this.props.toggleTermsCheck}/>

					<div id="proceedButtonContainer">
						<button className="btn btn-lg" id="proceedButton" onClick={this.props.proceed}>PROCEED</button>
					</div>

				</div>
			</div>
		);
	}
}



class RentComponent extends React.Component {
	constructor(props){
		super(props);
	}

	// shouldComponentUpdate(nextProps, nextState) {
	// 	const shouldUpdate = nextProps.shouldUpdate;
	//     return shouldUpdate==undefined?true:shouldUpdate;
	// }

	render(){
		const info = this.props.info;
		const rent = info.rent;
		const minDays = info.minDays;
		const days = info.days;
		const totalRent = info.totalRent;
		const dispMinDaysMsg = info.dispMinDaysMsg;

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
							<div id="increaseDays"><span onClick={this.props.incrementDays}>+</span></div>
							<div id="decreaseDays"><span onClick={this.props.decrementDays}>-</span></div>
						</div>
					</div>
					<div className="block3">
						<span style={{display:dispMinDaysMsg?'block':'none'}} id="daysMessage">Value cannot be less than {minDays}</span>
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


//get rent component info
function getRentCompInfo(selectedResult){
	//rent comp details
	const rci = {};
	if(selectedResult){
		rci.rent = selectedResult.result.book.rentAmount;
		rci.minDays = 5;
		rci.days = 5; 
		rci.totalRent = rci.rent*rci.days;
		rci.dispMinDaysMsg = false;
	}
	return rci;
}


function fetchSelectedResult(successCallback){
	$httpService.getSelectedResult({},successCallback,undefined);
}

/* type is rent or buy */
function submitBookRequest(orderType,bookId,days,totalRent){
	let params = {orderType:orderType,bookId:bookId,days:days,totalRent:totalRent};
	$httpService.saveBookRequest(params,(res)=>{
		if(res.success){
			$pages.confirmation();
		}
		else{
			alert("Some error occurred");
		}
	});
}


ReactDOM.render(<Checkout/> , document.getElementById('app'));

