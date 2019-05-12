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
			similarResults: [],
			displayPart: {
				curr:"selected",
				types: {
					1:"selected",2:"all"
				}
			}
		};
		this.getResultSuccHandler = this.getResultSuccHandler.bind(this);
		this.getSimilarResultsSuccHandler = this.getSimilarResultsSuccHandler.bind(this);
	}

	componentDidMount() {
		this.getSelectedBook();
	    getResult(this.state.selectedBook.uid,this.getResultSuccHandler);
	}


	getSelectedBook(){
		this.state.selectedBook = util.$storage.get("selectedBook");
	}

	getResultSuccHandler(res){
		const selectedBookResult = res.result;
		this.setState({selectedBookResult:selectedBookResult});
	}

	getSimilarResultsSuccHandler(res){
		const similarResults = res.results;
		this.setState({similarResults:similarResults});
	}

	goHome(){
		$pages.home();
	}

	render(){
		const displayTypes = this.state.displayPart.types;
		const displayPart = this.state.displayPart.curr;
		const selectedBookResult = this.state.selectedBookResult;
		var results = [];
		if(selectedBookResult){
			results.push(selectedBookResult);
		}

		if(displayPart==displayTypes["2"]){
			results = results.concat(similarResults);
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

    renderResult(result){
    	const book = result.book;
    	const user = result.user;
    	return (
    		<Result key={book.id} result={result} />
    	);
    }

    render() {
    	const results = this.props.results;
    	const ResultComps = results.map((result) =>{
			    				return this.renderResult(result);
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
		const result = this.props.result;
		const book = result.book;
		const user = result.user;
		//const bookInfo = this.renderBookInfo(book);
		//const userInfo = this.renderUserInfo(user);

		return (
			<div className="result">

				<div className="bookInfo">
					<div className="container">
						<img src={book.image} width="130" height="180"/>
				
						<div className="infoPanel">
							<div className="bookTitle">NAME : {book.title}</div>
							<div className="authorName">AUTHOR NAME : {book.author}</div>
							<div className="category">CATEGORY : {book.category}</div>
							<div className="pages">PAGES : {book.pages}</div>
							<div className="available">AVAILABLE : {book.available==1?'YES':'NO'}</div>
						</div>

						<div className="buttonPanel">
							<button className="btn btn-danger buy" disabled={book.buy==0 && book.available==0} onClick={()=>{buyBook(result);}}>Buy</button>
							<button className="btn btn-danger rent" disabled={book.rent==0 && book.available==0} onClick={()=>{rentBook(result);}}>Rent</button>
						</div>
					</div>
				</div>

				<div className="userInfo">
					<div className="container">
				
						<div className="infoPanel">
							<div className="name">NAME : {user.name}</div>
							<div className="city">CITY : {user.city}</div>
							<div className="state">STATE : {user.state}</div>
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


function getResult(bookUid,successHandler){
	$httpService.getResult([bookUid], null, successHandler);
}

function getSimilarResults(book,successHandler){
	$httpService.getSimilarResults([], book, successHandler);
}


function buyBook(result){
	util.$storage.set('finalizedResult',result);
	$pages.checkout(['buy',result.book.uid]);
}

function rentBook(result){
	util.$storage.set('finalizedResult',result);
	$pages.checkout(['rent',result.book.uid]);
}

ReactDOM.render(<Results/> , document.getElementById('app'));
