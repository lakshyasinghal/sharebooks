import React from "react";
import ReactDOM from "react-dom";

export default class Book extends React.Component {
	constructor(props){
		super(props);
	}


	render(){
		const book = this.props.book;
		const id = book.id;
		const imgSrc = book.imgSrc || "static/book3.jpg";
		const title = book.title;
		const author = book.author;

		return (
			<div className="book" onClick={() => {this.props.onClick(id)}}>
				<div className="bookId hidden">{id}</div>
				<img src={imgSrc} alt="" className="bookImage" />
				<div className="bookName">{title}</div>
				<div className="bookAuthorName">{author}</div>
			</div>
		);
	}
}

