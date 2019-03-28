import React from "react";
import ReactDOM from "react-dom";

import Header from "./modules/header.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";

const $pages = $config.$pages;
const $sm = $config.$sm;


class Checkout extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		return (
			<div id="mainContainer" className="full-height">
				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={true}/>
				<TitlePanel/>
				<Body/>
			</div>
		);
	}
}
//


class TitlePanel extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		const backArrow = "/static/backArrow.png";
		return (
			<div id="titleContainer">
				<div id="backButton" >
					<img src={backArrow} width="60" height="60"/>
				</div>

				<span className="pageTitle">FILL YOUR REQUIREMENTS</span>
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
			<div id="bodyContainer">
				
				<Requirements/>

			</div>
		);
	}
}
//



class Requirements extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div style={{height:"521px"}} id="requirementsContainer">
				<div className="container">

					<BookInfo/>

					<div id="detailsContainer">
						<div id="optionDescriptionContainer"></div>

						<RentComponent/>

						<BuyComponent/>

						<div id="proceedButtonContainer">
							<button className="btn btn-lg btn-success" id="proceedButton">PROCEED</button>
						</div>
					</div>

				</div>
			</div>
		);
	}


}
//



class BookInfo extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="bookImageContainer">
				<img id="bookImage" width="220" height="300"/>
			</div>
		);
	}
}
//




class RentComponent extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="rentDetailsContainer" >
				<div id="chargesContainer">
					<div className="block1"><span>Charges</span></div>
					<div className="block2">Rs <span id="rentAmount"></span> per day</div>
				</div>
				<div id="daysContainer">
					<div className="block1"><span>Days</span></div>
					<div className="block2">
						<div id="days">
							<span id="daysCount"></span>
							<div id="increaseDays"><span>+</span></div>
							<div id="decreaseDays"><span>-</span></div>
						</div>
					</div>
					<div className="block3">
						<span id="daysMessage"></span>
					</div>
				</div>
				<div id="totalAmountContainer">
					<div className="block1"><span>Amount</span></div>
					<div className="block2"><span id="totalRentAmount"></span></div>
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




ReactDOM.render(<Checkout/> , document.getElementById('app'));




