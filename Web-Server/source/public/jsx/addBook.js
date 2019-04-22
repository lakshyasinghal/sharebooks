import React from "react";
import ReactDOM from "react-dom";
import utilModules from "./modules/utility.js";
import Header from "./modules/header.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";

const $storage = util.$storage;
const $pages = $config.$pages;
const $sm = $config.$sm;
const $categories = $config.$categories;
const DropDown = utilModules.DropDown;
const Message = utilModules.Message;

const user = JSON.parse($storage.get("user"));

class AddBook extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			book:{
				id:-1,
				title:"",
				author:"",
				category:"",
				subcategory:"",
				pages:"",
				ownerUid:user.uid,
				imgSrc:"",
				available:1,
				buy:1,    
				rent:1, 
				buyAmount:"",  
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
		this.successCallback=this.successCallback.bind(this);
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
		const type = elem.getAttribute("type");
		const fieldVal = elem.value;
		this.state.book[fieldName] = fieldVal;
	}

	//correct the type of values if incorrect. Example- convert pages value into number value if it's a string
	typeCheck(book){
		book.pages = Number(book.pages);
		book.available = Number(book.available);
		book.buy = Number(book.buy);
		book.rent = Number(book.rent);
		book.buyAmount = Number(book.buyAmount);
		book.rentAmount = Number(book.rentAmount);
	}


	submit(){
		const newBook = this.state.book;
		this.typeCheck(newBook);
		let validation = validateBook(newBook);
		if(!validation.isValid){ //show error message
			const messageConfig = {message:validation.message,type:"error",display:true};
			this.setState({messageConfig:messageConfig});
			this.messageOffTimer();
		}
		else{
			addBook(newBook , this.successCallback);
		}
	}

	successCallback(res){
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
		    			<div className="field col-lg-8"><input name="title" type="text" className="input form-control"/></div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">AUTHOR</div>
		    			<div className="field col-lg-8"><input name="author" type="text" className="input form-control"/></div>
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
		    			<div className="field col-lg-8">
		    				<form id="uploadForm" encType="multipart/form-data" action="/api/bookImage" method="post">
								<input type="file" name="bookImage" className="input form-control"/>
		    				</form>
		    			</div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">AVAILABLE FOR BUYING</div>
		    			<div className="field col-lg-8">
		    				<DropDown name={"buy"} optionsMap={availabilityOptionsMap} className={"input form-control"}/>
		    			</div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">AVAILABLE FOR RENT</div>
		    			<div className="field col-lg-8">
							<DropDown name={"rent"} optionsMap={availabilityOptionsMap} className={"input form-control"}/>
		    			</div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">BUYING AMOUNT</div>
		    			<div className="field col-lg-8"><input name="buyAmount" type="number" className="input form-control"/></div>
		    		</div>

		    		<div className="row margin-top-15">
		    			<div className="fieldName col-lg-4">RENT AMOUNT</div>
		    			<div className="field col-lg-8"><input name="rentAmount" type="number" className="input form-control"/></div>
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

	const title=book.title.trim(),author=book.author.trim(),category=book.category.trim(),pages=book.pages,
	buy=book.buy,rent=book.rent,buyAmount=book.buyAmount,rentAmount=book.rentAmount;

	if(title=="" || author=="" || category=="" || pages==""){
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


function addBook(book,successCallback){
	//debugger;
	$httpService.addBook(book,successCallback);
	//setTimeout(uploadBookPhoto,3000);
}

function uploadBookPhoto(){
	//debugger;
	$('#uploadForm').submit(function() {
		console.log("File is uploading...");
        //$("#status").empty().text("File is uploading...");
        $(this).ajaxSubmit({
            error: function(xhr) {
        		status('Error: ' + xhr.status);
            },
            success: function(response) {
	        	//$("#status").empty().text(response);
	            console.log(response);
	        }
	    });
        //Very important line, it disable the page refresh.
    	return false;
    });

    setTimeout(()=>{$('#uploadForm').submit();},1000);
}


ReactDOM.render(<AddBook/> , document.getElementById('app'));


