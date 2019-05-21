import React from "react";
import ReactDOM from "react-dom";
import Header from "./modules/header.js";
import $httpService from "./../scripts/http/httpService.js";
import $config from "./../scripts/static/config.js";
import util from "./../scripts/utility/utility.js";
const $pages = $config.$pages;
const $sm = $config.$sm;



class Feedback extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			ratingRange:{min:0,max:10} ,
			selectedRating: undefined,
			comments: ""
		};
		this.updateComments = this.updateComments.bind(this);
		this.recordRating = this.recordRating.bind(this);
	}
	
	updateComments(e){
		this.state.comments = e.target.value.trim();
	}

	recordRating(rating){
		this.setState({selectedRating:rating});
	}

	isSubmitValid(){
		const rating = this.state.selectedRating;
		const comments = this.state.comments;
		if(rating==undefined){
			alert("Please select a rating");
			return false;
		}
		if(comments.trim()==""){
			alert("Please add comments.");
			return false;
		}
		return true;
	}

	submit(){
		const rating = this.state.selectedRating;
		const comments = this.state.comments;
		const userUid = util.getURLPathParamByIndex(2);
		if(!this.isSubmitValid()){
			return;
		}
		$httpService.saveFeedback([userUid],{rating:rating,comments:comments}, (res)=>{
			alert("Feedback saved successfully")
		});
	}

	render(){
		return (
			<div id="mainContainer">
				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={false}/>
				
				<h2 className="center-align heading">Give Us Your Valuable Feedback</h2>

				<Rating ref="rating" ratingRange={this.state.ratingRange} recordRating={this.recordRating} selectedRating={this.state.selectedRating}/>

				<Comments shouldUpdate={this.state.updateComments} updateComments={this.updateComments}/>

				<div id="buttonContainer">
					<button className="btn btn-info" id="submitBtn" onClick={()=>{this.submit();}}>Submit</button>
				</div>
			</div>
		);
	}
}
//


class Rating extends React.Component {
	constructor(props){
		super(props);
	}

	componentDidUpdate(prevProps, prevState) {
	    console.log("rating updated");
	}

	render(){
		const selectedRating = this.props.selectedRating;
		const ratingRange = this.props.ratingRange;
		let ratings = [];  //will be used to build ratingBoxes
		for(let i = ratingRange.min, length1 = ratingRange.max; i <= length1; i++){
			ratings.push(i);
		}

		return (
			<div id="ratingContainer">

				<div className="text">Give an overall rating for the app</div>

				<div className="rating">
					{ratings.map((rating)=>{
						return <RatingBox key={rating} rating={rating} selected={rating==selectedRating} recordRating={(rating)=>{this.props.recordRating(rating);}}/>;
					})}
				</div>
			</div>
		);
	}
}
//


class RatingBox extends React.Component {
	constructor(props){
		super(props);
	}

	render(){
		const rating = this.props.rating;
		const selected = this.props.selected;
		var style = null;
		if(selected){
			style = {backgroundColor: '#17a2b8',color: '#FFFFFF'}; 
		}

		return (
			<div className="ratingBox" style={style} onClick={()=>{this.props.recordRating(rating);}}><span>{rating}</span></div>
		);
	}
}


class Comments extends React.Component {
	constructor(props){
		super(props);
	}

	// shouldComponentUpdate(nextProps, nextState) {
	//     return nextProps.shouldUpdate;
	// }

	render(){
		return (
			<div id="commentsContainer">
				<div className="text">Comments</div>
				<textarea rows="10" cols="125" id="commentBox" onChange={this.props.updateComments}></textarea>
			</div>
		);
	}
}
//


ReactDOM.render(<Feedback/> , document.getElementById('app'));