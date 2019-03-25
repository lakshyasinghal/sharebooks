import React from "react";
import ReactDOM from "react-dom";
import Header from "./modules/header.js";
import $config from "./../scripts/static/config.js";
const $pages = $config.$pages;
const $sm = $config.$sm;



const infoArr = [{heading:"Product",text:"The product sharebooks has been designed for book lovers, the ones who like to read and share books. Using this app you have at your disposal a virtual library or maybe something even better. You can borrow, buy books from other people and vice-versa."},
				{heading:"Team",text:"The team as of now consists of Lakshya Singhal and Sher Mohammad."},
				{heading:"Founder",text:"The founder of this app is Lakshya Singhal. He has done B.Tech from NIT Jalandhar. The other leading developer, Sher Mohammad has done B.Tech from IIT Delhi."}
			];




class AboutContainer extends React.Component {
	constructor(props){
		super(props);
		this.state = {
			infoArr: infoArr
		};
	}

	renderInfo(infoObj,key){
		return <Info key={key} heading={infoObj.heading} text={infoObj.text}/>
	}

	render(){
		const infoArr = this.state.infoArr;

		return (
			<div id="mainContainer">
				<Header homeDisplay={true} profileDisplay={true} adderDisplay={false} browserDisplay={false} notifDisplay={true}/>

				<h1 className="center-align">About Sharebooks</h1>
				<br/><br/>

				<div id="infoContainer">
					{infoArr.map((infoObj,i)=>{return this.renderInfo(infoObj,i);})}
				</div>
			</div>
		);
	}
}



function Info(props){

	return (
		<div>
			<h3>{props.heading}</h3>
			<h4>{props.text}</h4>
			<br/><br/>
		</div>
	);
}




ReactDOM.render(<AboutContainer/> , document.getElementById('app'));

