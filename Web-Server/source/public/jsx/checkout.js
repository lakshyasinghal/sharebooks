import React from "react";
import ReactDOM from "react-dom";
import "./../lib/styles/bootstrap.css";
import "./../styles/common.css";
import "./../styles/checkout.css";

import backArrow from "./../resources/images/backArrow.png";



class Checkout extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="mainContainer" className="full-height">

				<div id="bodyContainer">

					<div id="titleContainer">
						<div id="backButton" ng-click="goToPreviousPage()">
							<img src={backArrow} width="60" height="60"/>
						</div>

						<div id="homeButton" ng-click="goHome()">
							<span>HOME</span>
						</div>	

						<span className="pageTitle">CHECKOUT</span>
					</div>	
					
					<div id="checkoutInfoContainer">
						<div id="holdingContainer">
							<div id="bookImageContainer">
								<img id="bookImage" width="220" height="300"/>
							</div>
							<div id="detailsContainer">
								<div id="optionDescriptionContainer"></div>

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


								<div id="buyDetailsContainer">
									<div id="amountContainer">
										<div className="block1"><span>Amount</span></div>
										<div className="block2">Rs <span id="buyAmount"></span></div>
									</div>
								</div>

								<div id="proceedButtonContainer">
									<button className="btn btn-lg btn-success" id="proceedButton">PROCEED</button>
								</div>
							</div>

						</div>
					</div>

				</div>

			</div>
		);
	}
}




ReactDOM.render(<Checkout/> , document.getElementById('app'));