import React from "react";
import ReactDOM from "react-dom";

import Header from "./modules/header.js";
import BackButton from "./modules/backButton.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";

const $pages = $config.$pages;
const $sm = $config.$sm;


class Results extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			headingText: "RESULTS",
			selectedBook: null,
			selectedBookResult: null,
			allRelatedResults: [],
			displayPart: {
				curr:"all",
				types: {
					1:"selected",2:"all"
				}
			}
		};
	}

	componentDidMount() {
		this.getSelectedBook();
	    this.getAllResults();
	}

	noResults(){

	}

	getSelectedBook(){
		this.state.selectedBook = util.$storage.get("selectedBook");
	}


	/**
	 * will get the results in two parts. One will be selected book result and other will be all related results
	 * @return {[type]} [description]
	 */
	getAllResults(){
		var selectedBook = this.state.selectedBook;
		$httpService.getAllResults({book:selectedBook} , (res)=>{
			res = JSON.parse(res);
			if(res.success){
				const selectedBookResult = res.results[0];
				const allRelatedResults = res.results[1];
				this.setState({selectedBookResult:selectedBookResult,allRelatedResults:allRelatedResults});
			}
			else{
				this.noResults();
			}
		} , ()=>{});
	}

	goHome(){
		$pages.home();
	}

	render(){
		const displayTypes = this.state.displayPart.types;
		const displayPart = this.state.displayPart.curr;
		const selectedBookResult = this.state.selectedBookResult;
		var results = [];
		if(displayPart==displayTypes["1"] && selectedBookResult){
			results.push(this.state.selectedBookResult);
		}
		else{
			results = this.state.allRelatedResults;
		}

		return (
			<div id="mainContainer">
				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={true}/>

				<OptionPanel headingText={this.state.headingText} goHome={this.goHome}/>

				<Body results={results}/>
			</div>
		);
	}
}
//


class OptionPanel extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		const headingText = this.props.headingText;

		return (
			<div id="optionsContainer">
				<BackButton/>
				<div id="headingText">
					<span>{headingText}</span>
				</div>
			    
			</div>
		);
	}
}
//

class Body extends React.Component {
    constructor(props) {
        super(props);
    }

    renderResult(result,i){
    	return (
    		<Result key={i} book={result.book} user={result.user} />
    	);
    }

    render() {
    	const results = this.props.results;
    	const ResultComps = results.map((result,index) =>{
			    				return this.renderResult(result, index);
			    			});

    	return (
    		<div id="resultsContainer">	
    			{ResultComps}			
			</div>
    	);
    }
}
//



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



ReactDOM.render(<Results/> , document.getElementById('app'));
