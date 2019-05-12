import React from "react";
import ReactDOM from "react-dom";

import utilModules from "./modules/utility.js";
import Header from "./modules/header.js";
import Book from "./modules/Book.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";

const $pages = $config.$pages;
const $sm = $config.$sm;
const Loader = utilModules.Loader;


class Home extends React.Component {
	constructor(props){
		super(props);
		this.state =  {
			updateBody: true   //used in should component update
			//popupDisplay: "none"
		};
		this.search = this.search.bind(this);
	}

	componentDidMount(){
		//this.events();
	}

	search(searchText){
		this.refs.body.booksBySearch(searchText);
	}

	categorySelectionHandler(e){
		const category = e.target.innerText;
		this.refs.body.booksByCategory(category);
		//alert(category+" selected");
	}

	render(){
		const notifications = this.state.notifications;
		const books = this.state.selectedBooks;

		return (
			<div id="mainContainer" className="jumbotron full-height">
				<Loader/>
	
				<Header homeDisplay={false} profileDisplay={true} adderDisplay={true} browserDisplay={true} notifDisplay={true} 
				categoryHandler={(e)=>{this.categorySelectionHandler(e);}}/>
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
		if(e.key=="Enter"){
			this.props.searchFunc(e.target.value);
		}
	}

	render(){
		const placeholder = "Search books by name, author, category";
		return (
			<div id="searchContainer" className="row">
				<div className="col-sm-12 col-md-12">
					<input type="text" id="searchBooks" onKeyPress={(e)=>{this.search(e);}} placeholder={placeholder} className="form-control"/>
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
		this.getAllBooks = getAllBooks.bind(this);
		this.booksByCategory = booksByCategory.bind(this);
		this.booksBySearch = booksBySearch.bind(this);
		this.getBookById = getBookById.bind(this);
		this.selectBook = selectBook.bind(this);
	}

	componentDidMount() {
		console.log("Body component mounted");
	    this.getAllBooks();
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
		const BookComponents = books.map((book) => {
			const id=book.id;
			return (
				<Book key={book.id} book={book} onClick={(id)=>{this.selectBook(id)}} />
			);
		});

		return (
			<div id="bodyContainer" className="component-border-bottom">
				<div id="booksContainer" className="col-sm-12 col-md-12 full-height">
					{BookComponents}
				</div>
			</div>
		);
	}
}



function getAllBooks(){
	$httpService.getAllBooks([], {} , (res) => {
		var books = res.books;
		this.state.books = books;
		this.displayBooks(books);
	});
}

function booksByCategory(category , subcategory){
	//self.selectedBooks = [];
	//showPageLoader = true;
	
	// var params = {};
	// params.category = category?category:null;
	// params.subcategory = subcategory?subcategory:null;
	
	//call http service  
	$httpService.filterByCategory([category],null, res => {
		const books = res.books;
		this.displayBooks(books);
	});	//$logger.err("getBooksByCategory" , err.message);
}

/**
 Needs to be updated
 */
function booksBySearch(searchText){
	searchText = searchText.trim();
	const reqSearchLen = this.state.reqSearchLen;
	//return if search string length is less than required length to prevent useless searches
	if(searchText.length < reqSearchLen){
		alert("The search text length should be atleast "+reqSearchLen+" characters long.");
		return;
	}

	//searchText will go in path params
	$httpService.getBooksBySearchTerm([searchText], {}, res => {
		var books = res.books;
		this.displayBooks(books);
	});	
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
	$pages.results([book.uid]);
}




ReactDOM.render(<Home/> , document.getElementById('app'));


