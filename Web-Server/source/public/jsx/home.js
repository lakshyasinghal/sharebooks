import React from "react";
import ReactDOM from "react-dom";

import Loader from "./modules/loader.js";
import Header from "./modules/header.js";
import Book from "./modules/Book.js";
import AddBookPopup from "./modules/AddBookPopup.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";

const $pages = $config.$pages;
const $sm = $config.$sm;



class Home extends React.Component {
	constructor(props){
		super(props);
		this.state =  {
			updateBody: true,   //used in should component update
			popupDisplay: "none"
		};
		this.togglePopup = this.togglePopup.bind(this);
		this.search = this.search.bind(this);
	}

	componentDidMount(){
		//this.events();
	}

	togglePopup(e){
		//debugger;
		e.preventDefault();
		e.stopPropagation();
		var newDisplay = undefined;
		if(this.state.popupDisplay=="none"){newDisplay="block";}
		else{newDisplay="none";}
		this.setState({updateBody:false , popupDisplay:newDisplay});
		//this.state.updateBody=true;
	}

	search(searchText){
		this.refs.body.booksBySearch(searchText);
	}


	render(){
		const notifications = this.state.notifications;
		const books = this.state.selectedBooks;

		return (
			<div id="mainContainer" className="jumbotron full-height">
				<Loader/>
				<AddBookPopup popupDisplay={this.state.popupDisplay}/>
				<Header homeDisplay={false} profileDisplay={true} adderDisplay={true} browserDisplay={true} notifDisplay={true}
				 togglePopup={this.togglePopup}/>
				<Search searchFunc={this.search}/>
				<Body ref="body" shouldUpdate={this.state.updateBody} />
			</div>
		);
	}
}
//




class Search extends React.Component {
	constructor(props){
		super(props);
		this.search = this.search.bind(this);
	}

	componentDidMount() {
	
	}

	search(e){
		if(e.keyCode==13){
			self.props.searchFunc(e.target.value);
		}
	}

	render(){
		const placeholder = "Search books by name, author, category";
		return (
			<div id="searchContainer" className="row">
				<div className="col-sm-12 col-md-12">
					<input type="text" id="searchBooks" onKeyPress={this.search} placeholder={placeholder} className="form-control"/>
				</div>
			</div>
		);
	}
}


class Body extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			books: [],
			selectedBooks: [],
			reqSearchLen: 5,
			moreSpecificSearchMessage: "Please be more specific in your search.\nPlease provide atleast 5 characters."
		}
		this.allBooks = allBooks.bind(this);
		this.booksByCategory = booksByCategory.bind(this);
		this.booksBySearch = booksBySearch.bind(this);
		this.getBookById = getBookById.bind(this);
		this.selectBook = selectBook.bind(this);
	}

	componentDidMount() {
		console.log("Body component mounted");
	    this.allBooks();
	}

	shouldComponentUpdate(nextProps, nextState) {
		return nextProps.shouldUpdate;
	}

	componentDidUpdate(prevProps, prevState) {
	    console.log("Body updated ", new Date());
	}

	displayBooks(books){
		this.setState({selectedBooks:books});
	}


	render(){
		const books = this.state.selectedBooks;
		const Books = books.map((book) => {
			const id=book.id;
			return (
				<Book key={book.id} book={book} onClick={(id)=>{this.selectBook(id)}} />
			);
		});

		return (
			<div id="bodyContainer" className="component-border-bottom">
				<div id="booksContainer" className="col-sm-12 col-md-12 full-height">
					{Books}
				</div>
			</div>
		);
	}
}



function allBooks(){
	$httpService.getAllBooks( {} , (res) => {
		var data = JSON.parse(response);
		if(data.success){
			var books = data.results;
			this.state.books = books;
			//selectedBooks = books;
			this.displayBooks(books);
		}
		else{
			alert("Error in success in getAllBooks");
			// if(data.statusCode == statusCodes.SESSION_DOES_NOT_EXIST){
			// 	location.reload();
			// }
		}
	} , () => {});
}

function booksByCategory(category , subcategory){
	//self.selectedBooks = [];
	//showPageLoader = true;
	var params = {};
	params["category"] = category;
	params["subcategory"] = subcategory;
	
	//call http service  
	$httpService.filterByCategory(params , res => {
		var data = JSON.parse(response);
		if(data.success){
			selectedBooks = data.results;
		}
		else{
			if(data.statusCode == statusCodes.SESSION_DOES_NOT_EXIST){
				location.reload();
			}
			//displayMessage($scope.messageContainerId , messages[data.statusCode - 1] , messageColors.WARNING);
		}
	} , () => {});	//$logger.err("getBooksByCategory" , err.message);
}

/**
 Needs to be updated
 */
function booksBySearch(searchText){
	searchText = searchText.trim();
	const reqSearchLen = this.state.reqSearchLen;
	//return if search string length is less than required length to prevent useless searches
	if(searchText.length < reqSearchLen){
		//$popupManager.showAlertPopup(moreSpecificSearchMessage);
		return;
	}
	/* needs to be updated */
	//$loaderManager.showLoader("id" , "pageLoader");
	// var params = {};
	// params["searchText"] = searchText;

	$httpService.getBooksBySearchString({searchText:searchText} , res => {
		//$loaderManager.hideLoader("id" , "pageLoader");
		var data = JSON.parse(response);
		if(data.success){
			var books = data.results;
			//selectedBooks = books;
			this.displayBooks(books);
		}
		else{
			//$messageManager.displayStatusMessage(data.statusCode , 2 , null);
		}
	} , () => {
		alert("Request failed");
	});	//$logger.err("getBooksBySearchString" , err.message);
}

function getBookById(id){
	var books = this.state.selectedBooks;
	for(var i=0 ; i<books.length ; i++){
		if(books[i].id == id){
			return books[i];
		}
	}
	return null;
}

function selectBook(bookId){
	//debugger;
	var book = this.getBookById(bookId);
	if(book==null){return ;}
	util.$storage.set("selectedBook",book);
	/* redirect to results page */
	$pages.results();
}




ReactDOM.render(<Home/> , document.getElementById('app'));


