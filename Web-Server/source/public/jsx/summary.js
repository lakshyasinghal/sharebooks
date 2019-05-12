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


class Summary extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			type:-1,
			summaryInfo: null,
			termsChecked: false		
		};
		this.toggleTermsCheck = toggleTermsCheck.bind(this);
		this.getSummaryInfo = getSummaryInfo.bind(this);
	}

	componentDidMount() {
	    this.state.type = getTypeFromURL();
	    this.getSummaryInfo(); 
	}
	

	render(){

		return (
			<div id="mainContainer" className="full-height">
				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={false}/>
				<div id="titleContainer">
					<BackButton/>
					<span className="pageTitle">SUMMARY</span>
				</div>
				<Body summaryInfo={this.state.summaryInfo} toggleTermsCheck={this.toggleTermsCheck} termsChecked={this.state.termsChecked}/>
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
		const termsChecked = this.props.termsChecked;

		return (
			<div style={{marginTop:'50px',height:'500px'}} id="bodyContainer">
				<Info info={this.props.summaryInfo}/>
				<TermsAndConditions toggleTermsCheck={this.props.toggleTermsCheck} />
				<div id="confirmButtonContainer">
					<button className="btn btn-lg" id="confirmButton"  onClick={()=>{confirm(termsChecked);}}>CONFIRM</button>
				</div>
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
		const info = this.props.info?this.props.info:{};
		const bookInfo = info.bookInfo?info.bookInfo:{};
		const quoteInfo = info.quoteInfo?info.quoteInfo:{};

		return (
			<div id="infoContainer">
				<BookInfo info={bookInfo}/>
				<QuoteInfo info={quoteInfo}/>
			</div>
		);
	}
}
//





class BookInfo extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		const bookInfo = this.props.info?this.props.info:{};

		return (
			<div id="bookInfoContainer">
				<div className="relative">
					<div className="inline-block absolute">
						<img src={bookInfo.image} width="130" height="180"/>
					</div>
					
					<div className="inline-block absolute infoPanel">
						<div className="bookName">TITLE : {bookInfo.title}</div>
						<div className="authorName">AUTHOR : {bookInfo.author}</div>
						<div className="category">CATEGORY : {bookInfo.category}</div>
						<div className="pages">PAGES : {bookInfo.pages}</div>
						<div className="bookValue">BOOK VALUE : {bookInfo.buyAmount}</div>
					</div>
				</div>
			</div>
		);
	}
}
//

class QuoteInfo extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		const info = this.props.info?this.props.info:{};
		const rentInfo = info.rentInfo?info.rentInfo:{};
		const buyInfo = info.buyInfo?info.buyInfo:{};

		return (
			<div id="quoteInfoContainer">
				<div>
					<div className="quoteType">TYPE : {info.type==1?"RENTAL":"BUY"}</div>
					<div className="rentAmount">RENT : {rentInfo.rentAmount}</div>
					<div className="requiredDays">REQUIRED DAYS : {rentInfo.requiredDays}</div>
					<div className="totalRent">TOTAL RENT : {rentInfo.totalRent}</div>
					<div className="startDate">START DATE : {rentInfo.startDate}</div>
					<div className="homeDelivery">HOME DELIVERY : {rentInfo.deliveryType==1?"YES":"NO"}</div>
					<div className="deliveryCharges">DELIVERY CHARGES : {rentInfo.deliveryCharges}</div>
					<div className="totalAmount">TOTAL AMOUNT : {info.totalAmount}</div>
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
		//const termsURL = "/static/termsandconditions.html";
		//termsChecked = this.props.termsChecked;

		return (
			<div id="termsAndConditions">
				<div className="checkbox">
				  <label><input type="checkbox" onClick={this.props.toggleTermsCheck} />I have checked <a href="#" onClick={()=>{$pages.termsAndConditions(null,'blank');}}>terms and conditions</a></label>
				</div>
			</div>
		);
	}
}


/*----------------------------------------------------functions--------------------------------------------------------------*/

function getSummaryInfo(){
	const quoteUid = util.getURLPathParamByIndex(3);
	$httpService.getSummaryInfo([quoteUid],null,res=>{
		this.setState({summaryInfo:res.summaryInfo});
	});
}

//needs to be made common across the files
function getTypeFromURL(){
	var type = window.location.pathname.split('/')[2];
	if(type=='rent'){
		return $global.ORDER_TYPE.RENT;
	}
	else{
		return $global.ORDER_TYPE.BUY;
	}
}


function toggleTermsCheck(){
	this.setState(prevState=>({termsChecked:!prevState.termsChecked}));
}


function confirm(termsChecked){
	if(!termsChecked){
		alert("Please check terms and conditions first");
		return ; 
	}

	const quoteUid = util.getURLPathParamByIndex(3);
	$httpService.confirmQuote([quoteUid],null,res=>{
		$pages.confirmation([res.bookRequestUid]);
	});
}


// /* type is rent or buy */
// function submitBookRequest(orderType,bookId,days,totalRent){
// 	let params = {orderType:orderType,bookId:bookId,days:days,totalRent:totalRent};
// 	$httpService.saveBookRequest([],params,(res)=>{
// 		$pages.confirmation();
// 	});
// }


ReactDOM.render(<Summary/> , document.getElementById('app'));

