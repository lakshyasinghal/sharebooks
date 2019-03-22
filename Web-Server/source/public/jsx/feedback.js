import React from "react";
import ReactDOM from "react-dom";
import "./../lib/styles/bootstrap.css";
import "./../styles/common.css";
import "./../styles/feedback.css";


class Feedback extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="mainContainer">
				<h2 className="center-align heading">Give Us Your Valuable Feedback</h2>

				<div id="ratingContainer">
					<div className="text">Give an overall rating for the app</div>
					<div className="rating">
						<div className="ratingBox"><span>0</span></div>
						<div className="ratingBox"><span>1</span></div>
						<div className="ratingBox"><span>2</span></div>
						<div className="ratingBox"><span>3</span></div>
						<div className="ratingBox"><span>4</span></div>
						<div className="ratingBox"><span>5</span></div>
						<div className="ratingBox"><span>6</span></div>
						<div className="ratingBox"><span>7</span></div>
						<div className="ratingBox"><span>8</span></div>
						<div className="ratingBox"><span>9</span></div>
						<div className="ratingBox"><span>10</span></div>
					</div>
				</div>

				<div id="commentsContainer">
					<div className="text">Comments</div>
					<textarea rows="10" cols="125" id="commentBox"></textarea>
				</div>

				<div id="buttonContainer">
					<button className="btn btn-info" id="submitBtn">Submit</button>
				</div>
			</div>
		);
	}
}


ReactDOM.render(<Feedback/> , document.getElementById('app'));