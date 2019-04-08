import React from "react";
import ReactDOM from "react-dom";
import utilModules from "./modules/utility.js";
import Header from "./modules/header.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";

const $pages = $config.$pages;
const $sm = $config.$sm;
const $categories = $config.$categories;
const DropDown = utilModules.DropDown;
const Message = utilModules.Message;

class AddBook extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			book:{
				name:"",
				authorName:"",
				category:"",
				pages:"",
				buy:1,    
				buyAmount:"",
				rent:1 ,   
				rentAmount:""
			},
			messageConfig:{
				message:"",
				type: "",
				display: false
			}
		};
		this.updateInputDetails = this.updateInputDetails.bind(this);
		this.submit=this.submit.bind(this);
		this.addBookSuccess=this.addBookSuccess.bind(this);
		this.messageOffTimer=this.messageOffTimer.bind(this);
	} 

	messageOffTimer(){
		setTimeout(()=>{
			const messageConfig = {message:"",type:"",display:false};
			this.setState({messageConfig:messageConfig});
		}, 2000);
	}

	updateInputDetails(e){
		const elem = e.target;
		const fieldName = elem.getAttribute("name");
		const fieldVal = elem.value;

		this.state.book[fieldName] = fieldVal;
	}

	submit(){
		const newBook = this.state.book;
		let validation = validateBook(newBook);
		if(!validation.isValid){ //show error message
			const messageConfig = {message:validation.message,type:"error",display:true};
			this.setState({messageConfig:messageConfig});
			this.messageOffTimer();
		}
		else{
			addBook(newBook , this.addBookSuccess);
		}
	}

	addBookSuccess(res){
		res = JSON.parse(res);
		if(res.success){
			const messageConfig = {message:$sm.message(res.statusCode),type:"success",display:true};
			this.setState({messageConfig:messageConfig});
			this.messageOffTimer();
		}
	}

	render(){
		return (
			<div>
				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={true}
				 togglePopup={this.togglePopup}/>
				<Body messageConfig={this.state.messageConfig} inputHandler={this.updateInputDetails} submit={this.submit}/>
			</div>
		);
	}
}
//


class Body extends React.Component {
	constructor(props){
		super(props);
	}

	componentDidMount() {
	    const inputs = document.getElementsByTagName("input");
	    for(let i = 0, len = inputs.length; i < len; i++){
	    	inputs[i].addEventListener("change",this.props.inputHandler);
	    }
	  
	    const selectElems = document.getElementsByTagName("select");
	    for(let i = 0, len = selectElems.length; i < len; i++){
	    	selectElems[i].addEventListener("change",this.props.inputHandler);
	    }
	}

	render(){
		const availabilityOptionsMap = [{desc:"YES",value:1},{desc:"NO",value:0}];
		const categories = $categories;
		//const form = this.props.bookForm;
		const messageConfig = this.props.messageConfig;

		return (
			<div className="container">
    	
		    	<div className="pageTitle">Book Details</div>

		    	<div id="details">
		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">NAME</div>
		    			<div className="field col-lg-8"><input name="name" type="text" className="input form-control"/></div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">AUTHOR</div>
		    			<div className="field col-lg-8"><input name="authorName" type="text" className="input form-control"/></div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">CATEGORY</div>
		    			<div className="field col-lg-8">
							<DropDown name={"category"} isDefOptionReq={true} optionsMap={categories} className={"input form-control"}/>
		    			</div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">PAGES</div>
		    			<div className="field col-lg-8"><input name="pages" type="number" className="input form-control"/></div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">ADD IMAGE</div>
		    			<div className="field col-lg-8"><input name="image" type="text" className="input form-control"/></div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">AVAILABLE FOR BUYING</div>
		    			<div className="field col-lg-8">
		    				<DropDown name={"buy"} optionsMap={availabilityOptionsMap} className={"input form-control"}/>
		    			</div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">BUYING AMOUNT</div>
		    			<div className="field col-lg-8"><input name="buyAmount" type="text" className="input form-control"/></div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">AVAILABLE FOR RENT</div>
		    			<div className="field col-lg-8">
							<DropDown name={"rent"} optionsMap={availabilityOptionsMap} className={"input form-control"}/>
		    			</div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">RENT AMOUNT</div>
		    			<div className="field col-lg-8"><input name="rentAmount" type="text" className="input form-control"/></div>
		    		</div>
		    	</div>
		    	<div className="center-align margin-top-30">
		    		<button id="submitBtn" className="btn btn-warning" onClick={this.props.submit}>SUBMIT</button>
		    	</div>
		    	
		    	<Message type={messageConfig.type} display={messageConfig.display} message={messageConfig.message}/>
			</div>
		);
	}
}
//


function validateBook(book){
	let isValid = true,message="";
	const name=book.name.trim(),authorName=book.authorName.trim(),category=book.category.trim(),pages=book.pages.trim(),
	buy=book.buy,rent=book.rent,buyAmount=book.buyAmount.trim(),rentAmount=book.rentAmount.trim();
	if(name=="" || authorName=="" || category=="" || pages==""){
		isValid = false;message="Please fill all the fields";
	}
	else if(isNaN(pages)){
		isValid = false;message="Please enter a number value for pages";
	}
	else if(buy==1 && buyAmount==""){
		isValid = false;message="Please enter buy amount";
	}
	else if(rent==1 && rentAmount==""){
		isValid = false;message="Please enter rent amount";
	}
	else if(isNaN(buyAmount)){
		isValid = false;message="Please enter a number value for buy amount";
	}
	else if(isNaN(rentAmount)){
		isValid = false;message="Please enter a number value for rent amount";
	}
	return {isValid:isValid,message:message};
}


function addBook(book,successCallBack){
	$httpService.addBook({book:book},successCallBack);
}


ReactDOM.render(<AddBook/> , document.getElementById('app'));


