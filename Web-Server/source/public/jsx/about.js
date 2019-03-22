import React from "react";
import ReactDOM from "react-dom";
import "./../lib/styles/bootstrap.css";
import "./../styles/common.css";
import "./../styles/about.css";



class AboutContainer extends React.Component {
	constructor(props){
		super(props);
		this.state = {

		};
	}

	render(){
		return (
			<div id="mainContainer">

				<h1 className="center-align">About Sharebooks</h1>
				<br/><br/>

				<div id="infoContainer">
					<h3>Product</h3>
					<h4>The product sharebooks has been designed for book lovers, the ones who like to read and share books. Using this app you have at your disposal a virtual library or maybe something even better. You can borrow, buy books from other people and vice-versa.</h4>

					<br/><br/>

					<h3>Team</h3>
					<h4>The team as of now consists of only one member, Mr. Lakshya Singhal</h4>

					<br/><br/>

					<h3>Founder</h3>
					<h4>The founder of this great app is Lakshya Singhal. He has done his B.Tech in computer science from NIT Jalandhar. Currently he is employed with one of the leading IT companies.</h4>
				</div>
				

			</div>
		);
	}
}


ReactDOM.render(<AboutContainer/> , document.getElementById('app'));