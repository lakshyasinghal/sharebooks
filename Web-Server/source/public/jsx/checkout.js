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
		this.state = {
			finalizedResult : {},
			type : null,               //whether rental or buy
			rentInfo : {
				rentAmount: null,
				requiredDays: 0,
				totalRent: 0,
				startDate: null,
				deliveryType: 1,
				deliveryCharges: 0,
				dispMinDaysMsg: false,
				minDays: 5
			},
			buyInfo: {
				amount: 0,
				startDate: null,
				deliveryType: '',
				deliveryCharges: 0
			},
			//termsChecked: false,
		};
		this.initRentInfo = initRentInfo.bind(this);
		this.initBuyInfo = initBuyInfo.bind(this);
		//this.toggleTermsCheck = toggleTermsCheck.bind(this);
		this.incrementDays = incrementDays.bind(this);
		this.decrementDays = decrementDays.bind(this);
		this.updateInfoComp = updateInfoComp.bind(this);
		this.proceed = proceed.bind(this);
	}

	componentDidMount() {
	    this.init();
	}

	init(){	
		//debugger;
		const type = getTypeFromURL();
		this.state.type = type;
		const finalizedResult = util.$storage.get('finalizedResult');
		this.state.finalizedResult = finalizedResult;
		const book = finalizedResult.book;
		if(type==$global.ORDER_TYPE.RENT){
			this.initRentInfo(book);
		}
		else{
			this.initBuyInfo(book);
		}

		this.forceUpdate();
	}

	

	render(){
		const result = this.state.finalizedResult;
		const book = result?result.book:{};
		const rentInfo = this.state.rentInfo;
		const buyInfo = this.state.buyInfo;
		const type = this.state.type;
		const methods = {
			//toggleTermsCheck: this.toggleTermsCheck,
			incrementDays: this.incrementDays,
			decrementDays: this.decrementDays,
			updateInfoComp: this.updateInfoComp,
			proceed: this.proceed
		}

		return (
			<div id="mainContainer" className="full-height">
				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={true}/>
				<div id="titleContainer">
					<BackButton/>
					<span className="pageTitle">FILL OUT REQUIREMENTS</span>
				</div>
				<Body book={book} type={type} rentInfo={rentInfo} buyInfo={buyInfo} methods={methods}/>
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
			<div style={{marginTop:'50px',height:'500px'}} id="bodyContainer">
				<Info book={this.props.book}/>
				<Requirements rentInfo={this.props.rentInfo} buyInfo={this.props.buyInfo} type={this.props.type} methods={this.props.methods}/>
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
							<div className="bookName">NAME : {book.title}</div>
							<div className="authorName">AUTHOR : {book.author}</div>
							<div className="category">CATEGORY : {book.category}</div>
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
		//debugger;
		const type = this.props.type;
		const rentComponent = $global.ORDER_TYPE.RENT==type;
		const buyComponent = $global.ORDER_TYPE.BUY==type;

		return (
			<div id="requirementsContainer">
				<div style={{paddingLeft:'125px'}} className="container">
					
					<div id="optionDescriptionContainer"></div>

					{rentComponent && <RentComponent info={this.props.rentInfo} methods={this.props.methods} />}

					{buyComponent && <BuyComponent buyInfo={this.props.buyInfo}/>}

					<div id="proceedButtonContainer">
						<button className="btn btn-lg" id="proceedButton" onClick={this.props.methods.proceed}>PROCEED</button>
					</div>

				</div>
			</div>
		);
	}
}

// <TermsAndConditions toggleTermsCheck={this.props.methods.toggleTermsCheck}/>

class RentComponent extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		//debugger;
		const methods = this.props.methods;
		const info = this.props.info;
		const rent = info.rentAmount;
		const minDays = info.minDays;
		const requiredDays = info.requiredDays;
		const totalRent = info.totalRent;
		const dispMinDaysMsg = info.dispMinDaysMsg;
		const homeDTClassName = "optionTab " + (info.deliveryType==1?"selected":"");     //home delivery type class
		const pickupDTClassName = "optionTab " + (info.deliveryType==2?"selected":"");   // pickup delivery type class
		//const startDateStyle = {width:150};

		return (
			<div id="rentDetailsContainer" >
				<div id="chargesContainer">
					<div className="block1"><span>Rent Charges</span></div>
					<div className="block2">
						<span id="rent" className="box"><span className="boxText">{rent}</span></span>
						<span style={{position:'relative',left:'56px'}}> rupees per day</span>
					</div>
				</div>
				<div id="daysContainer">
					<div className="block1"><span>Days</span></div>
					<div className="block2">
						<div id="days" className="box">
							<span className="boxText">{requiredDays}</span>
							<div id="increaseDays"><span onClick={this.props.methods.incrementDays}>+</span></div>
							<div id="decreaseDays"><span onClick={this.props.methods.decrementDays}>-</span></div>
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
				<div id="startDateContainer">
					<div className="block1"><span>Start Date</span></div>
					<div className="block2"><input name="startDate" id="startDate" type="text" onChange={(e)=>{methods.updateInfoComp('startDate',e.target.value);}} className="form-control" placeholder="yyyy-mm-dd"/></div>
				</div>
				<div id="deliveryTypeContainer">
					<div className="block1"><span>Home Delivery</span></div>
					<div className="block2">
						<div className={homeDTClassName} onClick={(e)=>{methods.updateInfoComp('deliveryType',1);}}><span className="boxText">YES</span></div>
						<div className={pickupDTClassName} onClick={(e)=>{methods.updateInfoComp('deliveryType',2);}}><span className="boxText">NO</span></div>
					</div>
				</div>
			</div>
		);
	}
}
//



class BuyComponent extends React.Component {
	constructor(props){
		super(props);
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


// class TermsAndConditions extends React.Component {
// 	constructor(props){
// 		super(props);
// 	}

// 	render(){
// 		//const termsText = "I have checked terms and conditions and am ready to move further.";
// 		//const termsURL = "/static/termsandconditions.html";
// 		return (
// 			<div id="termsAndConditions">
// 				<div className="checkbox">
// 				  <label><input type="checkbox" onClick={this.props.toggleTermsCheck} />I have checked <a href="#" onClick={()=>{debugger;$pages.termsAndConditions(null,'blank');}}>terms and conditions</a> and am ready to move further.</label>
// 				</div>
// 			</div>
// 		);
// 	}
// }


function getTypeFromURL(){
	var type = window.location.pathname.split('/')[2];
	if(type=='rent'){
		return $global.ORDER_TYPE.RENT;
	}
	else{
		return $global.ORDER_TYPE.BUY;
	}
}


function initRentInfo(book){
	const ri = this.state.rentInfo;
	ri.rentAmount = book.rentAmount;
	ri.minDays = 5;
	ri.requiredDays = ri.minDays;
	ri.totalRent = ri.rentAmount*ri.requiredDays;
	ri.dispMinDaysMsg = false;
}

function initBuyInfo(book){
	this.state.buyInfo.amount = book.amount;
}


function updateInfoComp(propName,propVal){
	const type = this.state.type;
	var infoComp = null;
	if(type==1){
		infoComp = this.state.rentInfo;
	}
	else{
		infoComp = this.state.buyInfo;
	}
	infoComp[propName] = propVal;
	if(type==1){
		this.setState({rentInfo:infoComp});
	}
	else{
		this.setState({buyInfo:infoComp});
	}
}

// function toggleTermsCheck(){
// 	this.setState(prevState=>({termsChecked:!prevState.termsChecked}));
// }

function incrementDays(){
	this.setState(prevState=>{
		let newRI = prevState.rentInfo;
		newRI.requiredDays = newRI.requiredDays+1;
		newRI.totalRent = newRI.rentAmount*newRI.requiredDays;
		newRI.dispMinDaysMsg = false;
		return {rentInfo:newRI};
	});
}

function decrementDays(){
	this.setState(prevState=>{
		let newRI = prevState.rentInfo;
		const minDays = newRI.minDays;
		let requiredDays = newRI.days-1;	
		if(requiredDays<minDays){
			newRI.dispMinDaysMsg=true;
		}
		else{
			newRI.requiredDays = requiredDays;
			newRI.totalRent = newRI.rentAmount*newRI.requiredDays;
		}
		return {rentInfo:newRI};
	});
}


function proceed(){

	const finalizedResult = this.state.finalizedResult;
	const type = this.state.type;
	const rentInfo = this.state.rentInfo;
	const buyInfo = this.state.buyInfo;
	const totalAmount = this.state.totalAmount;

	createQuote(finalizedResult.book.uid,finalizedResult.user.uid,type,rentInfo,buyInfo,totalAmount);
	//const isTermsChecked = this.state.termsChecked;
	// const rentComponent = $global.ORDER_TYPE['1']==this.state.orderType;
	// if(!isTermsChecked){
	// 	alert("Please check terms and conditions first");
	// 	return ; 
	// }
	// else{
	// 	const type = this.state.type;
	// 	const bookId = this.state.selectedResult.result.book.id;
	// 	const days = this.state.rci.days;
	// 	const totalRent = this.state.rci.totalRent;
	// 	submitBookRequest(orderType,bookId,days,totalRent);
	// }
}


// bookUid
// userUid
// type
// rentInfo
// buyInfo
//totalAmount

function createQuote(bookUid,userUid,type,rentInfo,buyInfo,totalAmount){
	var quote = buildQuote(bookUid,userUid,type,rentInfo,buyInfo,totalAmount);
	//searchText will go in path params
	$httpService.createQuote([], quote, res => {
		var quoteUid = res.quoteUid;
		var typeDesc = type==1?"rent":"buy"; 
		$pages.summary([typeDesc,quoteUid]);
	});	
}

function buildQuote(bookUid,userUid,type,rentInfo,buyInfo,totalAmount){
	var quote = {};
	quote.bookUid = bookUid;
	quote.userUid = userUid;
	quote.status = 1;           //initial sttaus will be 1 by default
	quote.type = type;
	quote.rentInfo = null;
	quote.buyInfo = null;
	if(type==1){
		quote.rentInfo = buildRentInfo(rentInfo);
		if(!totalAmount || totalAmount<1){
			totalAmount = quote.rentInfo.totalRent + quote.rentInfo.deliveryCharges;
		}
	}
	else{
		quote.buyInfo = buildBuyInfo(buyInfo);
	}
	quote.totalAmount = totalAmount;

	return quote;
}

function buildRentInfo(rentInfo){
	var clonedRentInfo = util.clone(rentInfo);
	if(clonedRentInfo!=null){
		delete clonedRentInfo.dispMinDaysMsg;
		delete clonedRentInfo.minDays;
	}
	return clonedRentInfo;
}

function buildBuyInfo(){
	return null;
}


// /* type is rent or buy */
// function submitBookRequest(orderType,bookId,days,totalRent){
// 	let params = {orderType:orderType,bookId:bookId,days:days,totalRent:totalRent};
// 	$httpService.saveBookRequest([],params,(res)=>{
// 		$pages.confirmation();
// 	});
// }


ReactDOM.render(<Checkout/> , document.getElementById('app'));

