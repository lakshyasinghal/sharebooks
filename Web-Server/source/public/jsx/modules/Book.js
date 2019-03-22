import React from "react";
import ReactDOM from "react-dom";

export default class Book extends React.Component {
	constructor(props){
		super(props);
	}


	render(){
		const id = this.props.id;
		const img = this.props.img;
		const name = this.props.name;
		const authorName = this.props.authorName;

		return (
			<div className="book" onClick={() => {this.props.onClick(id)}}>
				<div className="bookId hidden">{id}</div>
				<img src={img} alt="" className="bookImage" />
				<div className="bookName">{name}</div>
				<div className="bookAuthorName">{authorName}</div>
			</div>
		);
	}
}

