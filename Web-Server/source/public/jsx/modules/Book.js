import React from "react";
import ReactDOM from "react-dom";

export default class Book extends React.Component {
	constructor(props){
		super(props);
	}


	render(){
		const book = this.props.book;
		const id = book.id;
		const img = book.image;
		const name = book.name;
		const authorName = book.authorName;

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

